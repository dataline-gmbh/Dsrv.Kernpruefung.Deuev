package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsko;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungEmail;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungPatternZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.Sonderzeichen;
import de.drv.dsrv.kernpruefung.basis.utils.Zeichen;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSKO;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSKO;

/**
 * Pruefung fuer das Feld E-Mail-Empfaengerprotokolle aus dem Baustein DSKO.
 */
public class PruefungEmailEmpfaengerProtokolle extends AbstractFeldPruefung<FeldNameDSKO, FehlerNummerDSKO> {

	private static final List<Character> ZULAESSIGE_ZEICHEN = Arrays.asList(Sonderzeichen.EXCLAMATION_MARK,
			Sonderzeichen.QUOTATION_MARK, Sonderzeichen.NUMBER_SIGN, Sonderzeichen.DOLLAR_SIGN,
			Sonderzeichen.PERCENT_SIGN, Sonderzeichen.AMPERSAND, Sonderzeichen.APOSTROPHE,
			Sonderzeichen.LEFT_PARENTHESIS, Sonderzeichen.RIGHT_PARENTHESIS, Sonderzeichen.ASTERISK,
			Sonderzeichen.PLUS_SIGN, Sonderzeichen.COMMA, Sonderzeichen.HYPHEN_MINUS, Sonderzeichen.FULL_STOP,
			Sonderzeichen.SOLIDUS, Sonderzeichen.COLON, Sonderzeichen.SEMICOLON, Sonderzeichen.LESS_THAN_SIGN,
			Sonderzeichen.EQUALS_SIGN, Sonderzeichen.GREATER_THAN_SIGN, Sonderzeichen.QUESTION_MARK,
			Sonderzeichen.SECTION_SIGN, Sonderzeichen.COMMERCIAL_AT, Sonderzeichen.CIRCUMFLEX_ACCENT,
			Sonderzeichen.LOW_LINE, Sonderzeichen.GRAVE_ACCENT);

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungEmailEmpfaengerProtokolle(final Feld<FeldNameDSKO> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer ko605 = new PruefungNichtLeer(getFeld());
		addPruefung(ko605, new Fehler<FehlerNummerDSKO>(FehlerNummerDSKO.DSKO605));

		final PruefungPatternZeichen ko610 = new PruefungPatternZeichen(getFeld(), "[A-Z"
				+ Zeichen.LATIN_CAPITAL_LETTER_A_WITH_DIAERESIS + Zeichen.LATIN_CAPITAL_LETTER_O_WITH_DIAERESIS
				+ Zeichen.LATIN_CAPITAL_LETTER_U_WITH_DIAERESIS + "a-z" + Zeichen.LATIN_SMALL_LETTER_A_WITH_DIAERESIS
				+ Zeichen.LATIN_SMALL_LETTER_O_WITH_DIAERESIS + Zeichen.LATIN_SMALL_LETTER_U_WITH_DIAERESIS + "0-9]",
				ZULAESSIGE_ZEICHEN);
		addPruefung(ko610, new Fehler<FehlerNummerDSKO>(FehlerNummerDSKO.DSKO610));

		final PruefungEmail ko612 = new PruefungEmail(getFeld());
		addPruefung(ko612, new Fehler<FehlerNummerDSKO>(FehlerNummerDSKO.DSKO612));
	}

}
