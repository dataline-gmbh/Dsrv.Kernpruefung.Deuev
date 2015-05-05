package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbna;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBeginntMitBuchstabe;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBeginntMitZeichenUndEndetMitLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBeginntNichtMitZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBuchstabenZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDreiGleicheAnfangsBuchstaben;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungEndetMitBuchstabeZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungMehrfachGleichesSonderOderLeerzeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungMindestensZweiBuchstaben;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNotInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungVorNachBindestrichenKeineLeerzeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.Sonderzeichen;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.basis.utils.Zeichen;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBNA;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBNA;

/**
 * Pruefung fuer das Feld Vorname.
 */
public class PruefungVorname extends AbstractFeldPruefung<FeldNameDBNA, FehlerNummerDBNA> {

	private static final int SUBSTART = 2;
	private static final int SUBEND = 5;
	private static final int MINLENGTH = 5;
	private static final String TRV = "TRV";
	private static final String TWL = "TWL";
	private boolean ZURECHT_NICHT_VORHANDENER_NAME_FLAG = true;
	private static final List<Character> ERLAUBTE_ZEICHEN_NA034 = Arrays.asList(' ', '-', Sonderzeichen.APOSTROPHE);
	private static final List<Character> VERBOTENE_ZEICHEN_NA036 = Arrays.asList(Zeichen.LATIN_SMALL_LETTER_SHARP_S);
	private static final List<Character> ERLAUBTE_ZEICHEN_NA036 = Arrays.asList(Sonderzeichen.APOSTROPHE);
	private static final List<Character> ZURECHT_NICHT_VORHANDENER_NAME = Arrays.asList('+');

	private static final List<String> UNZULAESSIGE_FIKTIVE_WERTE = Arrays.asList("ohne", "unbekannt", "herr", "frau");
	private static final List<String> UNZULAESSIGE_WERTE = Arrays.asList("storno", "unbekannt", "gel" + Zeichen.LATIN_SMALL_LETTER_O_WITH_DIAERESIS + "scht", "geloescht", "ung" + Zeichen.LATIN_SMALL_LETTER_U_WITH_DIAERESIS + "ltig", "ungueltig",
			"ohne", "herr", "frau");

	private final transient Feld<FeldNameDBNA> feldNachname;
	private final transient String vfmm;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            das Feld Vorname
	 * @param feldNachname
	 *            das Feld Nachname
	 * @param vfmm
	 *            das Verfahrensmerkmal
	 */
	public PruefungVorname(final Feld<FeldNameDBNA> feld, final Feld<FeldNameDBNA> feldNachname, final String vfmm) {
		super(feld);
		this.feldNachname = feldNachname;
		this.vfmm = vfmm;
	}

	/**
	 * Initialisiere die felduebergreifenden Pruefungen.
	 */
	@Override
	public void initialisiereFeldUebergreifendePruefungen() {
		if (ZURECHT_NICHT_VORHANDENER_NAME_FLAG) {
			if (this.isPruefbar(Arrays.asList(FehlerNummerDBNA.DBNA035, FehlerNummerDBNA.DBNA038), this.feldNachname)) {
				if (this.feldNachname.getTrimmedValue().equalsIgnoreCase(this.getFeld().getTrimmedValue())) {
					final PruefungNotInList na038a = new PruefungNotInList(UNZULAESSIGE_WERTE, this.getFeld());
					this.addPruefungFeldUebergreifend(na038a, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA038));

					final PruefungNotInList na038b = new PruefungNotInList(UNZULAESSIGE_FIKTIVE_WERTE, this.getFeld());
					this.addPruefungFeldUebergreifend(na038b, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA038));
				} else {
					final PruefungNotInList na035 = new PruefungNotInList(UNZULAESSIGE_FIKTIVE_WERTE, this.getFeld());
					this.addPruefungFeldUebergreifend(na035, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA035));
				}
			}
		}
	}

	/**
	 * Initialisiere die feldinternen Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {

		final PruefungNichtLeer na028 = new PruefungNichtLeer(this.getFeld());
		this.addPruefung(na028, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA028));

		if (this.getFeld().getValue().indexOf(ZURECHT_NICHT_VORHANDENER_NAME.get(0)) >= 0 && this.getFeld().getTrimmedValue().length() >= 1) {
			ZURECHT_NICHT_VORHANDENER_NAME_FLAG = false;

			final PruefungBeginntMitZeichenUndEndetMitLeer na037 = new PruefungBeginntMitZeichenUndEndetMitLeer(ZURECHT_NICHT_VORHANDENER_NAME, this.getFeld());
			this.addPruefung(na037, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA037));

			if(isPruefbar(FehlerNummerDBNA.DBNA039, feldNachname)) {
				final PruefungBeginntNichtMitZeichen na039 = new PruefungBeginntNichtMitZeichen(ZURECHT_NICHT_VORHANDENER_NAME, feldNachname);
				this.addPruefung(na039, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA039));
			}
		} else if (this.getFeld().getValue().indexOf(ZURECHT_NICHT_VORHANDENER_NAME.get(0)) == -1) {
			if (StringUtils.isNotBlank(this.vfmm) && this.vfmm.length() >= MINLENGTH) {
				final String meldung = this.vfmm.substring(SUBSTART, SUBEND).toUpperCase();
				if (TWL.equals(meldung) || TRV.equals(meldung)) {
					final PruefungMindestensZweiBuchstaben na029 = new PruefungMindestensZweiBuchstaben(this.getFeld());
					this.addPruefung(na029, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA029));
				}
			}

			final PruefungMehrfachGleichesSonderOderLeerzeichen na030 = new PruefungMehrfachGleichesSonderOderLeerzeichen(this.getFeld());
			this.addPruefung(na030, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA030));

			final PruefungDreiGleicheAnfangsBuchstaben na031 = new PruefungDreiGleicheAnfangsBuchstaben(this.getFeld());
			this.addPruefung(na031, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA031));

			final PruefungVorNachBindestrichenKeineLeerzeichen na032 = new PruefungVorNachBindestrichenKeineLeerzeichen(this.getFeld());
			this.addPruefung(na032, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA032));

			final PruefungBuchstabenZeichen na034 = new PruefungBuchstabenZeichen(ERLAUBTE_ZEICHEN_NA034, this.getFeld());
			this.addPruefung(na034, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA034));

			final PruefungBeginntMitBuchstabe na036BegBuchst = new PruefungBeginntMitBuchstabe(this.getFeld());
			this.addPruefung(na036BegBuchst, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA036));

			final PruefungBeginntNichtMitZeichen na036Szett = new PruefungBeginntNichtMitZeichen(VERBOTENE_ZEICHEN_NA036, this.getFeld());
			this.addPruefung(na036Szett, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA036));

			final PruefungEndetMitBuchstabeZeichen na036EndeBstb = new PruefungEndetMitBuchstabeZeichen(this.getFeld(), ERLAUBTE_ZEICHEN_NA036);
			this.addPruefung(na036EndeBstb, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA036));
		}
	}

}
