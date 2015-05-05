package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dban;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBeginntMitBuchstabe;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBuchstabenZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBuchstabenZiffernZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDreiGleicheAnfangsBuchstaben;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungEndetMitBuchstabeZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungEndetMitBuchstabeZifferZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungMehrfachGleichesSonderOderLeerzeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungMindestensZweiBuchstaben;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungPunktOhneBuchstaben;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.Sonderzeichen;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBAN;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBAN;

/**
 * Pruefung fuer das Feld Wohnort aus dem Baustein DBAN.
 */
public class PruefungWohnort extends AbstractFeldPruefung<FeldNameDBAN, FehlerNummerDBAN> {

	private static final String LDKZ_D = "D";
	private static final String LDKZ_OFW = "OFW";

	private static final List<Character> ERLAUBTE_ZEICHEN = Arrays.asList(Sonderzeichen.FULL_STOP, Sonderzeichen.COMMA,
			Sonderzeichen.SPACE, Sonderzeichen.HYPHEN_MINUS, Sonderzeichen.SOLIDUS, Sonderzeichen.LEFT_PARENTHESIS,
			Sonderzeichen.RIGHT_PARENTHESIS);
	private static final List<Character> ERLAUBTE_ZEICHEN_ENDE = Arrays.asList(Sonderzeichen.FULL_STOP,
			Sonderzeichen.RIGHT_PARENTHESIS);
	private static final List<Character> ERLAUBTE_ZEICHEN_AUSLAND = Arrays.asList(Sonderzeichen.SPACE,
			Sonderzeichen.FULL_STOP, Sonderzeichen.HYPHEN_MINUS, Sonderzeichen.COMMA, Sonderzeichen.SOLIDUS,
			Sonderzeichen.APOSTROPHE, Sonderzeichen.LEFT_PARENTHESIS, Sonderzeichen.RIGHT_PARENTHESIS);

	private final Feld<FeldNameDBAN> feldLdkz;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldLdkz
	 *            Feld Laenderkennzeichen aus DBAN
	 */
	public PruefungWohnort(final Feld<FeldNameDBAN> feld, final Feld<FeldNameDBAN> feldLdkz) {
		super(feld);

		this.feldLdkz = feldLdkz;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		if (StringUtils.isNotBlank(getFeld().getTrimmedValue())) {
			final PruefungMehrfachGleichesSonderOderLeerzeichen an120 = new PruefungMehrfachGleichesSonderOderLeerzeichen(
					getFeld());
			addPruefung(an120, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN120));

			final PruefungDreiGleicheAnfangsBuchstaben an121 = new PruefungDreiGleicheAnfangsBuchstaben(getFeld());
			addPruefung(an121, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN121));

			final PruefungBeginntMitBuchstabe an124 = new PruefungBeginntMitBuchstabe(getFeld());
			addPruefung(an124, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN124));

			final PruefungMindestensZweiBuchstaben an130 = new PruefungMindestensZweiBuchstaben(getFeld());
			addPruefung(an130, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN130));
		}
	}

	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {
		final List<FehlerNummerDBAN> fehlernummern = new LinkedList<FehlerNummerDBAN>();
		fehlernummern.add(FehlerNummerDBAN.DBAN118);
		fehlernummern.add(FehlerNummerDBAN.DBAN126);
		fehlernummern.add(FehlerNummerDBAN.DBAN128);
		fehlernummern.add(FehlerNummerDBAN.DBAN132);
		fehlernummern.add(FehlerNummerDBAN.DBAN140);
		fehlernummern.add(FehlerNummerDBAN.DBAN144);

		if (isPruefbar(fehlernummern, feldLdkz)) {
			if (StringUtils.isBlank(getFeld().getTrimmedValue())) {
				if (!(LDKZ_OFW.equals(feldLdkz.getTrimmedValue()))) {
					final PruefungNichtLeer an118 = new PruefungNichtLeer(getFeld());
					addPruefungFeldUebergreifend(an118, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN118));
				}
			} else {
				// Inlandsanschrift
				if (StringUtils.isBlank(feldLdkz.getTrimmedValue()) || LDKZ_D.equals(feldLdkz.getTrimmedValue())) {
					final PruefungBuchstabenZeichen an126 = new PruefungBuchstabenZeichen(ERLAUBTE_ZEICHEN, getFeld());
					addPruefungFeldUebergreifend(an126, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN126));

					final PruefungPunktOhneBuchstaben an128 = new PruefungPunktOhneBuchstaben(getFeld());
					addPruefungFeldUebergreifend(an128, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN128));

					final PruefungEndetMitBuchstabeZeichen an132 = new PruefungEndetMitBuchstabeZeichen(getFeld(),
							ERLAUBTE_ZEICHEN_ENDE);
					addPruefungFeldUebergreifend(an132, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN132));
				}

				// Auslandsanschrift
				if (!(StringUtils.isBlank(feldLdkz.getTrimmedValue()) || LDKZ_D.equals(feldLdkz.getTrimmedValue()) || LDKZ_OFW
						.equals(feldLdkz.getTrimmedValue()))) {
					final PruefungBuchstabenZiffernZeichen an140 = new PruefungBuchstabenZiffernZeichen(
							ERLAUBTE_ZEICHEN_AUSLAND, getFeld());
					addPruefungFeldUebergreifend(an140, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN140));

					final PruefungEndetMitBuchstabeZifferZeichen an144 = new PruefungEndetMitBuchstabeZifferZeichen(
							getFeld(), ERLAUBTE_ZEICHEN_ENDE);
					addPruefungFeldUebergreifend(an144, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN144));
				}
			}
		}
	}
}
