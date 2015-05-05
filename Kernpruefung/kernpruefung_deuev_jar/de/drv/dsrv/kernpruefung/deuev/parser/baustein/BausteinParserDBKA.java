package de.drv.dsrv.kernpruefung.deuev.parser.baustein;

import java.util.ArrayList;

import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.parser.AbstractBausteinParser;
import de.drv.dsrv.kernpruefung.basis.parser.DatensatzAufbauException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSBD;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKA;

/**
 * Parser fuer den Baustein DBKA.
 */
public class BausteinParserDBKA extends AbstractBausteinParser<FeldNameDBKA> {

	private static final int LAENGE = 208;

	private static final int INDEX_KENNUNG_BEGINN = 0;
	private static final int INDEX_KENNUNG_ENDE = 4;

	private static final int INDEX_NAME_BEZEICHNUNG_1_BEGINN = INDEX_KENNUNG_ENDE;
	private static final int INDEX_NAME_BEZEICHNUNG_1_ENDE = 34;

	private static final int INDEX_POSTLEITZHAL_ZUSTELL_BEGINN = 94;
	private static final int INDEX_POSTLEITZAHL_ZUSTELL_ENDE = 104;

	private static final int INDEX_ORT_BEGINN = INDEX_POSTLEITZAHL_ZUSTELL_ENDE;
	private static final int INDEX_ORT_ENDE = 138;

	private static final int INDEX_STRASSE_BEGINN = INDEX_ORT_ENDE;
	private static final int INDEX_STRASSE_ENDE = 171;

	private static final int INDEX_HAUSNUMMER_BEGINN = INDEX_STRASSE_ENDE;
	private static final int INDEX_HAUSNUMMER_ENDE = 180;

	private static final int INDEX_POSTLEITZAHL_POSTFACH_BEGINN = INDEX_HAUSNUMMER_ENDE;
	private static final int INDEX_POSTLEITZAHL_POSTFACH_ENDE = 190;

	private static final int INDEX_RESERVE_BEGINN = 200;
	private static final int INDEX_RESERVE_ENDE = 208;

	@Override
	public Baustein<FeldNameDBKA> parse(final String datensatz) throws DatensatzAufbauException {
		final ArrayList<Feld<FeldNameDBKA>> felder = new ArrayList<Feld<FeldNameDBKA>>();
		felder.add(parseFeld(FeldNameDBKA.KENNUNG, INDEX_KENNUNG_BEGINN, INDEX_KENNUNG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKA.NAME_BEZEICHNUNG1, INDEX_NAME_BEZEICHNUNG_1_BEGINN,
				INDEX_NAME_BEZEICHNUNG_1_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKA.POSTLEITZAHL_ZUSTELL, INDEX_POSTLEITZHAL_ZUSTELL_BEGINN,
				INDEX_POSTLEITZAHL_ZUSTELL_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKA.ORT, INDEX_ORT_BEGINN, INDEX_ORT_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKA.STRASSE, INDEX_STRASSE_BEGINN, INDEX_STRASSE_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKA.HAUSNUMMER, INDEX_HAUSNUMMER_BEGINN, INDEX_HAUSNUMMER_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKA.POSTLEITZAHL_POSTFACH, INDEX_POSTLEITZAHL_POSTFACH_BEGINN,
				INDEX_POSTLEITZAHL_POSTFACH_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKA.RESERVE, INDEX_RESERVE_BEGINN, INDEX_RESERVE_ENDE, datensatz));

		return new Baustein<FeldNameDBKA>(BausteinName.DBKA, felder);
	}

	@Override
	public int getLaenge(final String datensatz) throws DatensatzAufbauException {
		return LAENGE;
	}

	@Override
	public Fehler<? extends FehlerNummer> getFehlerBausteinNichtVorhanden() {
		return new Fehler<FehlerNummer>(FehlerNummerDSBD.DSBD930);
	}
}
