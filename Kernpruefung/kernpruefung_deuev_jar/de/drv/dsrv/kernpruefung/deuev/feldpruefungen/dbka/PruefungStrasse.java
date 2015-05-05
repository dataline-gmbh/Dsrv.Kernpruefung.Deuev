package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbka;

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
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungVorPunktBuchstabeZiffer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.Sonderzeichen;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKA;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKA;

/**
 * Pruefung fuer das Feld Strasse aus dem Baustein DBKA.
 */
public class PruefungStrasse extends AbstractFeldPruefung<FeldNameDBKA, FehlerNummerDBKA> {

	private static final List<String> ZULAESSIGER_ANFANG = Arrays.asList("III.", "MMM-Str");
	private static final List<Character> ERLAUBTE_ZEICHEN = Arrays.asList(Sonderzeichen.SPACE, Sonderzeichen.FULL_STOP,
			Sonderzeichen.COMMA, Sonderzeichen.HYPHEN_MINUS, Sonderzeichen.SOLIDUS, Sonderzeichen.APOSTROPHE,
			Sonderzeichen.LEFT_PARENTHESIS, Sonderzeichen.RIGHT_PARENTHESIS, Sonderzeichen.QUOTATION_MARK);
	private static final List<Character> BEGINNT_MIT = Arrays.asList(Sonderzeichen.APOSTROPHE,
			Sonderzeichen.QUOTATION_MARK);
	private static final List<Character> ERLAUBTE_ZEICHEN_ZIFFERNFOLGE_BEGINN = Arrays.asList(Sonderzeichen.FULL_STOP,
			Sonderzeichen.SPACE, Sonderzeichen.HYPHEN_MINUS);
	private static final List<Character> ERLAUBTE_ZEICHEN_ZIFFERNFOLGE_MITTE = Arrays.asList(Sonderzeichen.SPACE,
			Sonderzeichen.FULL_STOP);
	private static final List<Character> ENDET_MIT = Arrays.asList(Sonderzeichen.FULL_STOP,
			Sonderzeichen.RIGHT_PARENTHESIS, Sonderzeichen.QUOTATION_MARK);

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungStrasse(final Feld<FeldNameDBKA> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		if (StringUtils.isNotBlank(getFeld().getTrimmedValue())) {
			final PruefungMehrfachGleichesSonderOderLeerzeichen bd360 = new PruefungMehrfachGleichesSonderOderLeerzeichen(
					getFeld());
			addPruefung(bd360, new Fehler<FehlerNummerDBKA>(FehlerNummerDBKA.DBKA160));

			final PruefungDreiGleicheAnfangsBuchstabenOderZeichenfolge bd362 = new PruefungDreiGleicheAnfangsBuchstabenOderZeichenfolge(
					ZULAESSIGER_ANFANG, getFeld());
			addPruefung(bd362, new Fehler<FehlerNummerDBKA>(FehlerNummerDBKA.DBKA162));

			final PruefungBuchstabenZiffernZeichen bd364 = new PruefungBuchstabenZiffernZeichen(ERLAUBTE_ZEICHEN,
					getFeld());
			addPruefung(bd364, new Fehler<FehlerNummerDBKA>(FehlerNummerDBKA.DBKA164));

			final PruefungGrossbuchstabeOderMinZweiBuchstabenZiffernZeichen bd366 = new PruefungGrossbuchstabeOderMinZweiBuchstabenZiffernZeichen(
					ERLAUBTE_ZEICHEN, 2, getFeld());
			addPruefung(bd366, new Fehler<FehlerNummerDBKA>(FehlerNummerDBKA.DBKA166));

			final PruefungBeginntMitBuchstabeZifferZeichen bd368 = new PruefungBeginntMitBuchstabeZifferZeichen(
					BEGINNT_MIT, getFeld());
			addPruefung(bd368, new Fehler<FehlerNummerDBKA>(FehlerNummerDBKA.DBKA168));

			final PruefungBeginntMitZiffernfolgeFolgtBuchstabeZeichen bd370 = new PruefungBeginntMitZiffernfolgeFolgtBuchstabeZeichen(
					ERLAUBTE_ZEICHEN_ZIFFERNFOLGE_BEGINN, getFeld());
			addPruefung(bd370, new Fehler<FehlerNummerDBKA>(FehlerNummerDBKA.DBKA170));

			final PruefungBuchstabeZeichenVorErsterZiffer bd372 = new PruefungBuchstabeZeichenVorErsterZiffer(
					ERLAUBTE_ZEICHEN_ZIFFERNFOLGE_MITTE, getFeld());
			addPruefung(bd372, new Fehler<FehlerNummerDBKA>(FehlerNummerDBKA.DBKA172));

			final PruefungVorPunktBuchstabeZiffer bd374 = new PruefungVorPunktBuchstabeZiffer(getFeld());
			addPruefung(bd374, new Fehler<FehlerNummerDBKA>(FehlerNummerDBKA.DBKA174));

			final PruefungEndetMitBuchstabeZifferZeichen bd376 = new PruefungEndetMitBuchstabeZifferZeichen(getFeld(),
					ENDET_MIT);
			addPruefung(bd376, new Fehler<FehlerNummerDBKA>(FehlerNummerDBKA.DBKA176));
		}
	}
}
