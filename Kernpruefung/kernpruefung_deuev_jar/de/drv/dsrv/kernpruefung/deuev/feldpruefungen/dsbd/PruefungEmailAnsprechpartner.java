package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungEmail;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungPatternZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.Sonderzeichen;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.basis.utils.Zeichen;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSBD;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSBD;

/**
 * Pruefung fuer das Feld Email-Ansprechpartner aus dem Baustein DSBD.
 */
public class PruefungEmailAnsprechpartner extends AbstractFeldPruefung<FeldNameDSBD, FehlerNummerDSBD> {

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
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungEmailAnsprechpartner(final Feld<FeldNameDSBD> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		if (StringUtils.isNotBlank(getFeld().getTrimmedValue())) {
			final PruefungPatternZeichen bd580 = new PruefungPatternZeichen(getFeld(), "[A-Z"
					+ Zeichen.LATIN_CAPITAL_LETTER_A_WITH_DIAERESIS + Zeichen.LATIN_CAPITAL_LETTER_O_WITH_DIAERESIS
					+ Zeichen.LATIN_CAPITAL_LETTER_U_WITH_DIAERESIS + "a-z"
					+ Zeichen.LATIN_SMALL_LETTER_A_WITH_DIAERESIS + Zeichen.LATIN_SMALL_LETTER_O_WITH_DIAERESIS
					+ Zeichen.LATIN_SMALL_LETTER_U_WITH_DIAERESIS + "0-9]", ZULAESSIGE_ZEICHEN);
			addPruefung(bd580, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD580));

			final PruefungEmail bd582 = new PruefungEmail(getFeld());
			addPruefung(bd582, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD582));
		}
	}
}
