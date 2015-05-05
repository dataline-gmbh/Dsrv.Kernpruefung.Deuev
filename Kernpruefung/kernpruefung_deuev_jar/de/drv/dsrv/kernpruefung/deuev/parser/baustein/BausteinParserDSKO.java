package de.drv.dsrv.kernpruefung.deuev.parser.baustein;

import java.util.ArrayList;

import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.parser.AbstractBausteinParser;
import de.drv.dsrv.kernpruefung.basis.parser.DatensatzAufbauException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSKO;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSKO;

/**
 * Parser fuer den Baustein DSKO.
 */
public class BausteinParserDSKO extends AbstractBausteinParser<FeldNameDSKO> {

	private static final int INDEX_KENNUNG_BEGINN = 0;
	private static final int INDEX_KENNUNG_ENDE = 4;

	private static final int INDEX_VERSIONSNUMMER_BEGINN = 39;
	private static final int INDEX_VERSIONSNUMMER_ENDE = 41;

	private static final int INDEX_ERSTELLDATUM_BEGINN = INDEX_VERSIONSNUMMER_ENDE;
	private static final int INDEX_ERSTELLDATUM_ENDE = 61;

	private static final int INDEX_FEHLERKENNZEICHEN_BEGINN = INDEX_ERSTELLDATUM_ENDE;
	private static final int INDEX_FEHLERKENNZEICHEN_ENDE = 62;

	private static final int INDEX_FEHLERANZAHL_BEGINN = INDEX_FEHLERKENNZEICHEN_ENDE;
	private static final int INDEX_FEHLERANZAHL_ENDE = 63;

	private static final int INDEX_NAME1_BEGINN = 93;
	private static final int INDEX_NAME1_ENDE = 123;

	private static final int INDEX_PLZ_BEGINN = 183;
	private static final int INDEX_PLZ_ENDE = 193;

	private static final int INDEX_ORT_BEGINN = INDEX_PLZ_ENDE;
	private static final int INDEX_ORT_ENDE = 227;

	private static final int INDEX_ANREDE_ANSP_BEGINN = 269;
	private static final int INDEX_ANREDE_ANSP_ENDE = 270;

	private static final int INDEX_NAME_ANSP_BEGINN = INDEX_ANREDE_ANSP_ENDE;
	private static final int INDEX_NAME_ANSP_ENDE = 300;

	private static final int INDEX_TELEFON_ANSP_BEGINN = INDEX_NAME_ANSP_ENDE;
	private static final int INDEX_TELEFON_ANSP_ENDE = 320;

	private static final int INDEX_EMAIL_BEGINN = 340;
	private static final int INDEX_EMAIL_ENDE = 410;

	private static final int INDEX_VER_BESTAETIGUNG_BEGINN = INDEX_EMAIL_ENDE;
	private static final int INDEX_VER_BESTAETIGUNG_ENDE = 411;

	private static final int INDEX_KENNZ_FEHLRUECK_BEGINN = INDEX_VER_BESTAETIGUNG_ENDE;
	private static final int INDEX_KENNZ_FEHLRUECK_ENDE = 412;

	private static final int INDEX_RESERVE_BEGINN = INDEX_KENNZ_FEHLRUECK_ENDE;
	private static final int INDEX_RESERVE_ENDE = 415;

	private static final int LAENGE_DSKO = 415;

	@Override
	public Baustein<FeldNameDSKO> parse(final String datensatz) throws DatensatzAufbauException {

		final ArrayList<Feld<FeldNameDSKO>> felder = new ArrayList<Feld<FeldNameDSKO>>();
		felder.add(parseFeld(FeldNameDSKO.KENNUNG, INDEX_KENNUNG_BEGINN, INDEX_KENNUNG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSKO.VERSIONS_NR, INDEX_VERSIONSNUMMER_BEGINN, INDEX_VERSIONSNUMMER_ENDE,
				datensatz));
		felder.add(parseFeld(FeldNameDSKO.DATUM_ERSTELLUNG, INDEX_ERSTELLDATUM_BEGINN, INDEX_ERSTELLDATUM_ENDE,
				datensatz));
		felder.add(parseFeld(FeldNameDSKO.FEHLER_KENNZ, INDEX_FEHLERKENNZEICHEN_BEGINN, INDEX_FEHLERKENNZEICHEN_ENDE,
				datensatz));
		felder.add(parseFeld(FeldNameDSKO.FEHLER_ANZAHL, INDEX_FEHLERANZAHL_BEGINN, INDEX_FEHLERANZAHL_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSKO.NAME1_ABSENDER, INDEX_NAME1_BEGINN, INDEX_NAME1_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSKO.PLZ_BETRIEB, INDEX_PLZ_BEGINN, INDEX_PLZ_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSKO.ORT_BETRIEB, INDEX_ORT_BEGINN, INDEX_ORT_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSKO.ANREDE_ANSPRECHPARTNER, INDEX_ANREDE_ANSP_BEGINN, INDEX_ANREDE_ANSP_ENDE,
				datensatz));
		felder.add(parseFeld(FeldNameDSKO.NAME_ANSPRECHPARTNER, INDEX_NAME_ANSP_BEGINN, INDEX_NAME_ANSP_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSKO.TELEFON_ANSPRECHPARTNER, INDEX_TELEFON_ANSP_BEGINN, INDEX_TELEFON_ANSP_ENDE,
				datensatz));
		felder.add(parseFeld(FeldNameDSKO.EMAIL_EMPFAENGER_PROTOKOLLE, INDEX_EMAIL_BEGINN, INDEX_EMAIL_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSKO.VER_BESTAETIGUNG, INDEX_VER_BESTAETIGUNG_BEGINN, INDEX_VER_BESTAETIGUNG_ENDE,
				datensatz));
		felder.add(parseFeld(FeldNameDSKO.KENNZ_FEHLRUECK, INDEX_KENNZ_FEHLRUECK_BEGINN, INDEX_KENNZ_FEHLRUECK_ENDE,
				datensatz));
		felder.add(parseFeld(FeldNameDSKO.RESERVE, INDEX_RESERVE_BEGINN, INDEX_RESERVE_ENDE, datensatz));

		return new Baustein<FeldNameDSKO>(BausteinName.DSKO, felder);

	}

	@Override
	public int getLaenge(final String datensatz) throws DatensatzAufbauException {
		return LAENGE_DSKO;
	}

	@Override
	public Fehler<? extends FehlerNummer> getFehlerBausteinNichtVorhanden() {
		return new Fehler<FehlerNummer>(FehlerNummerDSKO.DSKO910);
	}

}
