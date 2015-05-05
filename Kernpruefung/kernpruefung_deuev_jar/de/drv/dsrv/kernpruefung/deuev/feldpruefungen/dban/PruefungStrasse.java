package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dban;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBeginntMitBuchstabeZifferZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBeginntMitZiffernfolgeFolgtBuchstabeZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBuchstabeZeichenVorErsterZiffer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBuchstabenZiffernZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDreiGleicheAnfangsBuchstabenOderZeichenfolge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungEndetMitBuchstabeZifferZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungGrossbuchstabeOderMinZweiBuchstabenZiffernZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungMehrfachGleichesSonderOderLeerzeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungVorPunktBuchstabeZiffer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.Sonderzeichen;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBAN;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBAN;

/**
 * Pruefung fuer das Feld Strasse aus dem Baustein DBAN.
 */
public class PruefungStrasse extends AbstractFeldPruefung<FeldNameDBAN, FehlerNummerDBAN> {

	private static final List<String> ZULAESSIGE_ZEICHENFOLGE_AN151 = Arrays.asList("III.", "MMM-Str");
	private static final List<Character> ERLAUBTE_ZEICHEN = Arrays.asList(Sonderzeichen.FULL_STOP, Sonderzeichen.HYPHEN_MINUS, Sonderzeichen.APOSTROPHE, Sonderzeichen.SPACE, Sonderzeichen.SOLIDUS, Sonderzeichen.COMMA, Sonderzeichen.LEFT_PARENTHESIS, Sonderzeichen.RIGHT_PARENTHESIS, Sonderzeichen.QUOTATION_MARK);
	private static final int MIN_LAENGE = 2;
	private static final List<Character> ERLAUBTE_ZEICHEN_START = Arrays.asList(Sonderzeichen.FULL_STOP, Sonderzeichen.APOSTROPHE, Sonderzeichen.QUOTATION_MARK);
	private static final List<Character> ERLAUBTE_ZEICHEN_ZIFFERNFOLGE = Arrays.asList(Sonderzeichen.FULL_STOP, Sonderzeichen.HYPHEN_MINUS, Sonderzeichen.SPACE);
	private static final List<Character> ERLAUBTE_ZEICHEN_ZIFFERNFOLGE_AUSLAND = Arrays.asList(Sonderzeichen.FULL_STOP, Sonderzeichen.HYPHEN_MINUS, Sonderzeichen.SPACE, Sonderzeichen.COMMA, Sonderzeichen.SOLIDUS);
	private static final List<Character> ERLAUBTE_ZEICHEN_VOR_ZIFFERNFOLGE = Arrays.asList(Sonderzeichen.FULL_STOP, Sonderzeichen.SPACE);
	private static final List<Character> ERLAUBTE_ZEICHEN_ENDE = Arrays.asList(Sonderzeichen.FULL_STOP, Sonderzeichen.RIGHT_PARENTHESIS, Sonderzeichen.QUOTATION_MARK, Sonderzeichen.APOSTROPHE, Sonderzeichen.HYPHEN_MINUS);

	private static final String LDKZ_D = "D";
	private static final String LDKZ_OFW = "OFW";
	private final Feld<FeldNameDBAN> feldLdkz;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            das zu pruefende Feld
	 * @param feldLdkz
	 *            Feld Laenderkennzeichen aus DBAN
	 */
	public PruefungStrasse(final Feld<FeldNameDBAN> feld, final Feld<FeldNameDBAN> feldLdkz) {
		super(feld);

		this.feldLdkz = feldLdkz;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {

		// Pruefungen nur durchfuehren, wenn die Strasse gesetzt ist. Bei den
		// felduebergreifenden Pruefungen wird ermittelt, ob eine Grundstellung
		// erlaubt ist.
		if (StringUtils.isNotBlank(getFeld().getTrimmedValue())) {
			final PruefungMehrfachGleichesSonderOderLeerzeichen an150 = new PruefungMehrfachGleichesSonderOderLeerzeichen(getFeld());
			addPruefung(an150, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN150));

			final PruefungDreiGleicheAnfangsBuchstabenOderZeichenfolge an151 = new PruefungDreiGleicheAnfangsBuchstabenOderZeichenfolge(ZULAESSIGE_ZEICHENFOLGE_AN151, getFeld());
			addPruefung(an151, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN151));

			final PruefungBuchstabenZiffernZeichen an156 = new PruefungBuchstabenZiffernZeichen(ERLAUBTE_ZEICHEN, getFeld());
			addPruefung(an156, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN156));

			final PruefungGrossbuchstabeOderMinZweiBuchstabenZiffernZeichen an158 = new PruefungGrossbuchstabeOderMinZweiBuchstabenZiffernZeichen(ERLAUBTE_ZEICHEN, MIN_LAENGE, getFeld());
			addPruefung(an158, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN158));

			final PruefungBeginntMitBuchstabeZifferZeichen an160 = new PruefungBeginntMitBuchstabeZifferZeichen(ERLAUBTE_ZEICHEN_START, getFeld());
			addPruefung(an160, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN160));

			final PruefungBuchstabeZeichenVorErsterZiffer an164 = new PruefungBuchstabeZeichenVorErsterZiffer(ERLAUBTE_ZEICHEN_VOR_ZIFFERNFOLGE, getFeld());
			addPruefung(an164, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN164));

			final PruefungVorPunktBuchstabeZiffer an166 = new PruefungVorPunktBuchstabeZiffer(getFeld());
			addPruefung(an166, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN166));

			final PruefungEndetMitBuchstabeZifferZeichen an168 = new PruefungEndetMitBuchstabeZifferZeichen(getFeld(), ERLAUBTE_ZEICHEN_ENDE);
			addPruefung(an168, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN168));
		}
	}

	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {
		// Falls Strasse nicht gesetzt ist, ueberpruefen, ob das erlaubt ist.
		if (StringUtils.isBlank(getFeld().getTrimmedValue())) {
			if (isPruefbar(FehlerNummerDBAN.DBAN154, feldLdkz) && !(StringUtils.isBlank(feldLdkz.getTrimmedValue()) || LDKZ_D.equals(feldLdkz.getTrimmedValue()) || LDKZ_OFW.equals(feldLdkz.getTrimmedValue()))) {
				final PruefungNichtLeer an154 = new PruefungNichtLeer(getFeld());
				addPruefungFeldUebergreifend(an154, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN154));
			}
		} else {
			
			final PruefungBeginntMitZiffernfolgeFolgtBuchstabeZeichen an162 = new PruefungBeginntMitZiffernfolgeFolgtBuchstabeZeichen(ERLAUBTE_ZEICHEN_ZIFFERNFOLGE_AUSLAND, getFeld());
			addPruefungFeldUebergreifend(an162, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN162));
			
			// TODO Damit Java wie Cobol läuft, wird die Strasse auch mit / und , zugelassen wie bei Inlandanschriften
//			if (isPruefbar(FehlerNummerDBAN.DBAN162, feldLdkz) && !(StringUtils.isBlank(feldLdkz.getTrimmedValue()) || LDKZ_D.equals(feldLdkz.getTrimmedValue()) || LDKZ_OFW.equals(feldLdkz.getTrimmedValue()))) {
//				final PruefungBeginntMitZiffernfolgeFolgtBuchstabeZeichen an162 = new PruefungBeginntMitZiffernfolgeFolgtBuchstabeZeichen(ERLAUBTE_ZEICHEN_ZIFFERNFOLGE_AUSLAND, getFeld());
//				addPruefungFeldUebergreifend(an162, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN162));
//			} else if (isPruefbar(FehlerNummerDBAN.DBAN162, feldLdkz)) {
//				final PruefungBeginntMitZiffernfolgeFolgtBuchstabeZeichen an162 = new PruefungBeginntMitZiffernfolgeFolgtBuchstabeZeichen(ERLAUBTE_ZEICHEN_ZIFFERNFOLGE, getFeld());
//				addPruefungFeldUebergreifend(an162, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN162));
//			}
		}
	}
}
