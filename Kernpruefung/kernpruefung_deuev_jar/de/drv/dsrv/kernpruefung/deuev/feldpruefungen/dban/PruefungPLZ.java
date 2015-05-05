package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dban;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefungErgebnis;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBuchstabenZiffernZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungMehrfachGleichesZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungZahlBereichUndLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.Sonderzeichen;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBAN;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBAN;

/**
 * Pruefung fuer das Feld Postleitzahl aus dem Baustein DBAN.
 */
public class PruefungPLZ extends AbstractFeldPruefung<FeldNameDBAN, FehlerNummerDBAN> {

	private static final int PLZ_LAENGE = 5;
	private static final int MIN_BEREICH = 1000;
	private static final int MAX_BEREICH = 99999;

	private static final List<Character> MEHRFACH_BINDESTRICH = Arrays.asList(Sonderzeichen.HYPHEN_MINUS);
	private static final List<Character> ERLAUBTE_ZEICHEN_AUSLAND = Arrays.asList(Sonderzeichen.HYPHEN_MINUS,
			Sonderzeichen.SPACE);

	private static final String LDKZ_D = "D";
	private static final String LDKZ_OFW = "OFW";
	private static final String LDKZ_A = "A";
	private static final String LDKZ_B = "B";
	private static final String LDKZ_CDN = "CDN";
	private static final String LDKZ_CH = "CH";
	private static final String LDKZ_DK = "DK";
	private static final String LDKZ_F = "F";
	private static final String LDKZ_NL = "NL";
	private static final String LDKZ_PL = "PL";
	private static final String LDKZ_CZ = "CZ";
	private static final String LDKZ_L = "L";

	private static final int LAENGE_A_B_CH_DK_L = 4;
	private static final int LAENGE_F = 5;
	private static final int LAENGE_NL_CDN = 7;
	private static final int LAENGE_PL_CZ = 6;

	private static final List<Integer> INDEX_CDN_NUMMER = Arrays.asList(1, 4, 6);
	private static final List<Integer> INDEX_CDN_BUCHSTABE = Arrays.asList(0, 2, 5);
	private static final int INDEX_CDN_BLANK = 3;

	private static final int INDEX_NL_NUMMER_START = 0;
	private static final int INDEX_NL_NUMMER_ENDE = 4;
	private static final int INDEX_NL_SPACE = 4;
	private static final int INDEX_NL_BUCHSTABE_START = 5;
	private static final int INDEX_NL_BUCHSTABE_ENDE = 6;

	private static final int INDEX_PL_BINDESTRICH = 2;
	private static final int INDEX_PL_NUMMER_START_A = 0;
	private static final int INDEX_PL_NUMMER_ENDE_A = 2;
	private static final int INDEX_PL_NUMMER_START_B = 3;
	private static final int INDEX_PL_NUMMER_ENDE_B = 6;

	private static final int INDEX_CZ_SPACE = 3;
	private static final int INDEX_CZ_NUMMER_START_A = 0;
	private static final int INDEX_CZ_NUMMER_ENDE_A = 3;
	private static final int INDEX_CZ_NUMMER_START_B = 4;
	private static final int INDEX_CZ_NUMMER_ENDE_B = 6;

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
	public PruefungPLZ(final Feld<FeldNameDBAN> feld, final Feld<FeldNameDBAN> feldLdkz) {
		super(feld);

		this.feldLdkz = feldLdkz;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		// Wenn die PLZ leer ist (fuer Auslandsanschriften ohne festen
		// Wohnsitz), sind weitere Pruefungen nicht mehr noetig und wuerden
		// ausserdem eine Exception werfen.
		if (StringUtils.isNotBlank(getFeld().getTrimmedValue())) {
			final PruefungMehrfachGleichesZeichen an024 = new PruefungMehrfachGleichesZeichen(getFeld(),
					MEHRFACH_BINDESTRICH);
			addPruefung(an024, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN024));
		}
	}

	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {

		final List<FehlerNummerDBAN> fehlernummern = new LinkedList<FehlerNummerDBAN>();
		fehlernummern.add(FehlerNummerDBAN.DBAN018);
		fehlernummern.add(FehlerNummerDBAN.DBAN020);
		fehlernummern.add(FehlerNummerDBAN.DBAN022);

		if (isPruefbar(fehlernummern, feldLdkz)) {
			// Pruefen, ob es sich um einen festen Wohnsitz und nicht um eine
			// Auslandsanschrift handelt
			if (StringUtils.isBlank(feldLdkz.getTrimmedValue()) || LDKZ_D.equals(feldLdkz.getTrimmedValue())) {
				final PruefungNichtLeer an018 = new PruefungNichtLeer(getFeld());
				addPruefungFeldUebergreifend(an018, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN018));
			}

			// Wenn die PLZ leer ist (fuer Auslandsanschriften ohne festen
			// Wohnsitz), sind weitere Pruefungen nicht mehr noetig und wuerden
			// ausserdem eine Exception werfen.
			if (StringUtils.isNotBlank(getFeld().getTrimmedValue())) {
				if (StringUtils.isBlank(feldLdkz.getTrimmedValue()) || LDKZ_D.equals(feldLdkz.getTrimmedValue())) {
					final PruefungZahlBereichUndLaenge an020 = new PruefungZahlBereichUndLaenge(getFeld(), PLZ_LAENGE,
							MIN_BEREICH, MAX_BEREICH);
					addPruefungFeldUebergreifend(an020, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN020));
				}

				// Auslandsanschrift
				if (!(StringUtils.isBlank(feldLdkz.getTrimmedValue()) || LDKZ_D.equals(feldLdkz.getTrimmedValue()) || LDKZ_OFW
						.equals(feldLdkz.getTrimmedValue()))) {
					final PruefungBuchstabenZiffernZeichen an022 = new PruefungBuchstabenZiffernZeichen(
							ERLAUBTE_ZEICHEN_AUSLAND, getFeld());
					addPruefungFeldUebergreifend(an022, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN022));
				}
			}
		}
	}

	@Override
	public FeldPruefungErgebnis<FehlerNummerDBAN> pruefeFeldUebergreifend() throws UngueltigeDatenException {

		// Ergebnis der Pruefung
		final FeldPruefungErgebnis<FehlerNummerDBAN> pruefungErgebnis = super.pruefeFeldUebergreifend();
		if (!pruefungErgebnis.isErfolgreichInklHinweis()) {
			return pruefungErgebnis;
		}

		if (isPruefbar(FehlerNummerDBAN.DBAN026, getFeld(), feldLdkz)) {
			// Pruefungen der Tabelle aus Anlage 18 ausfuehren (auslaendische
			// PLZ)
			// Auslandsanschrift
			if (!(StringUtils.isBlank(feldLdkz.getTrimmedValue()) || LDKZ_D.equals(feldLdkz.getTrimmedValue()) || LDKZ_OFW
					.equals(feldLdkz.getTrimmedValue())) && !StringUtils.isBlank(getFeld().getTrimmedValue())) {

				final String plz = getFeld().getTrimmedValue();
				final String ldkz = feldLdkz.getTrimmedValue();

				// NNNN
				if (ldkz.equals(LDKZ_A) || ldkz.equals(LDKZ_B) || ldkz.equals(LDKZ_CH) || ldkz.equals(LDKZ_DK)
						|| ldkz.equals(LDKZ_L)) {

					if (plz.length() != LAENGE_A_B_CH_DK_L) {
						pruefungErgebnis.addFehler(new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN026));
						return pruefungErgebnis;
					}

					try {
						Integer.parseInt(plz);
					} catch (final NumberFormatException e) {
						pruefungErgebnis.addFehler(new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN026));
						return pruefungErgebnis;
					}

					// ANA NAN
				} else if (ldkz.equals(LDKZ_CDN)) {
					if (plz.length() != LAENGE_NL_CDN) {
						pruefungErgebnis.addFehler(new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN026));
						return pruefungErgebnis;
					}

					for (final Integer idx : INDEX_CDN_BUCHSTABE) {
						final Character buchstabe = plz.charAt(idx);
						// TODO Damit Java wie Cobol läuft, werden Kleinbuchstaben  erlaubt.
//						|| Character.isLowerCase(buchstabe)
						if (!Character.isLetter(buchstabe)) {
							pruefungErgebnis.addFehler(new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN026));
							return pruefungErgebnis;
						}
					}

					for (final Integer idx : INDEX_CDN_NUMMER) {
						final Character nummer = plz.charAt(idx);
						if (!Character.isDigit(nummer)) {
							pruefungErgebnis.addFehler(new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN026));
							return pruefungErgebnis;
						}
					}

					if (!Character.isWhitespace(plz.charAt(INDEX_CDN_BLANK))) {
						pruefungErgebnis.addFehler(new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN026));
						return pruefungErgebnis;
					}

					// NNNNN
				} else if (ldkz.equals(LDKZ_F)) {

					if (plz.length() != LAENGE_F) {
						pruefungErgebnis.addFehler(new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN026));
						return pruefungErgebnis;
					}

					try {
						Integer.parseInt(plz);
					} catch (final NumberFormatException e) {
						pruefungErgebnis.addFehler(new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN026));
						return pruefungErgebnis;
					}

					// NNNN AA
				} else if (ldkz.equals(LDKZ_NL)) {

					if (plz.length() != LAENGE_NL_CDN) {
						pruefungErgebnis.addFehler(new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN026));
						return pruefungErgebnis;
					}

					try {
						Integer.parseInt(plz.substring(INDEX_NL_NUMMER_START, INDEX_NL_NUMMER_ENDE));
					} catch (final NumberFormatException e) {
						pruefungErgebnis.addFehler(new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN026));
						return pruefungErgebnis;
					}

					if (plz.charAt(INDEX_NL_SPACE) != ' '
							|| !Character.isLetter(plz.charAt(INDEX_NL_BUCHSTABE_START))
							|| !Character.isLetter(plz.charAt(INDEX_NL_BUCHSTABE_ENDE))) {
//						 TODO Damit Java wie Cobol läuft, werden Kleinbuchstaben  erlaubt.						
//							|| Character.isLowerCase(plz.charAt(INDEX_NL_BUCHSTABE_START))
//							|| Character.isLowerCase(plz.charAt(INDEX_NL_BUCHSTABE_ENDE))) {
						pruefungErgebnis.addFehler(new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN026));
						return pruefungErgebnis;
					}

				} else if (ldkz.equals(LDKZ_PL)) {

					if (plz.length() != LAENGE_PL_CZ) {
						pruefungErgebnis.addFehler(new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN026));
						return pruefungErgebnis;
					}

					if (plz.charAt(INDEX_PL_BINDESTRICH) != '-') {
						pruefungErgebnis.addFehler(new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN026));
						return pruefungErgebnis;
					}

					try {
						Integer.parseInt(plz.substring(INDEX_PL_NUMMER_START_A, INDEX_PL_NUMMER_ENDE_A));
						Integer.parseInt(plz.substring(INDEX_PL_NUMMER_START_B, INDEX_PL_NUMMER_ENDE_B));
					} catch (final NumberFormatException e) {
						pruefungErgebnis.addFehler(new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN026));
						return pruefungErgebnis;
					}

				} else if (ldkz.equals(LDKZ_CZ)) {

					if (plz.length() != LAENGE_PL_CZ) {
						pruefungErgebnis.addFehler(new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN026));
						return pruefungErgebnis;
					}

					if (plz.charAt(INDEX_CZ_SPACE) != ' ') {
						pruefungErgebnis.addFehler(new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN026));
						return pruefungErgebnis;
					}

					try {
						Integer.parseInt(plz.substring(INDEX_CZ_NUMMER_START_A, INDEX_CZ_NUMMER_ENDE_A));
						Integer.parseInt(plz.substring(INDEX_CZ_NUMMER_START_B, INDEX_CZ_NUMMER_ENDE_B));
					} catch (final NumberFormatException e) {
						pruefungErgebnis.addFehler(new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN026));
						return pruefungErgebnis;
					}
				}
			}
		}

		return pruefungErgebnis;
	}
}
