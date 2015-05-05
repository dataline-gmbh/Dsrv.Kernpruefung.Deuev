package de.drv.dsrv.kernpruefung.deuev.parser.baustein;

import java.util.ArrayList;

import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.parser.AbstractBausteinParser;
import de.drv.dsrv.kernpruefung.basis.parser.DatensatzAufbauException;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSBD;

/**
 * Parser fuer den Baustein DSBD.
 */
public class BausteinParserDSBD extends AbstractBausteinParser<FeldNameDSBD> {

	private static final int INDEX_KENNUNG_BEGINN = 0;
	private static final int INDEX_KENNUNG_ENDE = 4;

	private static final int INDEX_VERFAHREN_BEGINN = INDEX_KENNUNG_ENDE;
	private static final int INDEX_VERFAHREN_ENDE = 9;

	private static final int INDEX_BBNR_ABSENDER_BEGINN = INDEX_VERFAHREN_ENDE;
	private static final int INDEX_BBNR_ABSENDER_ENDE = 24;

	private static final int INDEX_BBNR_EMPFAENGER_BEGINN = INDEX_BBNR_ABSENDER_ENDE;
	private static final int INDEX_BBNR_EMPFAENGER_ENDE = 39;

	private static final int INDEX_VERSION_BEGINN = INDEX_BBNR_EMPFAENGER_ENDE;
	private static final int INDEX_VERSION_ENDE = 41;

	private static final int INDEX_DATUM_ERSTELLUNG_BEGINN = INDEX_VERSION_ENDE;
	private static final int INDEX_DATUM_ERSTELLUNG_ENDE = 61;

	private static final int INDEX_FEHLERKENNZEICHNUNG_BEGINN = INDEX_DATUM_ERSTELLUNG_ENDE;
	private static final int INDEX_FEHLERKENNZEICHNUNG_ENDE = 62;

	private static final int INDEX_FEHLERANZAHL_BEGINN = INDEX_FEHLERKENNZEICHNUNG_ENDE;
	private static final int INDEX_FEHLERANZAHL_ENDE = 63;

	private static final int INDEX_BBNR_BETRIEBSSTAETTE_BEGINN = INDEX_FEHLERANZAHL_ENDE;
	private static final int INDEX_BBNR_BETRIEBSSTAETTE_ENDE = 78;

	private static final int INDEX_RESERVE_1_BEGINN = INDEX_BBNR_BETRIEBSSTAETTE_ENDE;
	private static final int INDEX_RESERVE_1_ENDE = 89;

	private static final int INDEX_BBNR_ABRECHNUNGSSTELLE_BEGINN = INDEX_RESERVE_1_ENDE;
	private static final int INDEX_BBNR_ABRECHNUNGSSTELLE_ENDE = 104;

	private static final int INDEX_ABGABEGRUND_BEGINN = INDEX_BBNR_ABRECHNUNGSSTELLE_ENDE;
	private static final int INDEX_ABGABEGRUND_ENDE = 106;

	private static final int INDEX_WIRTSCHAFTSUNTERKLASSE_BEGINN = INDEX_ABGABEGRUND_ENDE;
	private static final int INDEX_WIRTSCHAFTSUNTERKLASSE_ENDE = 111;

	private static final int INDEX_NAME_BEZEICHNUNG_1_BEGINN = INDEX_WIRTSCHAFTSUNTERKLASSE_ENDE;
	private static final int INDEX_NAME_BEZEICHNUNG_1_ENDE = 141;

	private static final int INDEX_POSTLEITZAHL_ZUSTELL_BEGINN = 201;
	private static final int INDEX_POSTLEITZAHL_ZUSTELL_ENDE = 211;

	private static final int INDEX_ORT_BEGINN = INDEX_POSTLEITZAHL_ZUSTELL_ENDE;
	private static final int INDEX_ORT_ENDE = 245;

	private static final int INDEX_STRASSE_BEGINN = INDEX_ORT_ENDE;
	private static final int INDEX_STRASSE_ENDE = 278;

	private static final int INDEX_HAUSNUMMER_BEGINN = INDEX_STRASSE_ENDE;
	private static final int INDEX_HAUSNUMMER_ENDE = 287;

	private static final int INDEX_POSTLEITZAHL_POSTFACH_BEGINN = INDEX_HAUSNUMMER_ENDE;
	private static final int INDEX_POSTLEITZAHL_POSTFACH_ENDE = 297;

	private static final int INDEX_RUHEND_KENNZEICHEN_BEGINN = 307;
	private static final int INDEX_RUHEND_KENNZEICHEN_ENDE = 308;

	private static final int INDEX_MELDENDE_STELLE_BEGINN = INDEX_RUHEND_KENNZEICHEN_ENDE;
	private static final int INDEX_MELDENDE_STELLE_ENDE = 323;

	private static final int INDEX_ANSPRECHPARTNER_BEGINN = INDEX_MELDENDE_STELLE_ENDE;
	private static final int INDEX_ANSPRECHPARTNER_ENDE = 324;

	private static final int INDEX_EMAIL_ANSPRECHPARTNER_BEGINN = 394;
	private static final int INDEX_EMAIL_ANSPRECHPARTNER_ENDE = 464;

	private static final int INDEX_BBNR_KK_BEGINN = 504;
	private static final int INDEX_BBNR_KK_ENDE = 519;

	private static final int INDEX_RESERVE_2_BEGINN = INDEX_BBNR_KK_ENDE;
	private static final int INDEX_RESERVE_2_ENDE = 534;

	private static final int INDEX_MMKA_BEGINN = INDEX_RESERVE_2_ENDE;
	private static final int INDEX_MMKA_ENDE = 535;

	private static final int INDEX_MMTN_BEGINN = INDEX_MMKA_ENDE;
	private static final int INDEX_MMTN_ENDE = 536;

	private static final int INDEX_RESERVE_3_BEGINN = INDEX_MMTN_ENDE;
	private static final int INDEX_RESERVE_3_ENDE = 541;

	private static final int LAENGE = 541;

	@Override
	public Baustein<FeldNameDSBD> parse(final String datensatz) throws DatensatzAufbauException {
		final ArrayList<Feld<FeldNameDSBD>> felder = new ArrayList<Feld<FeldNameDSBD>>();
		felder.add(parseFeld(FeldNameDSBD.KENNUNG, INDEX_KENNUNG_BEGINN, INDEX_KENNUNG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSBD.VERFAHREN, INDEX_VERFAHREN_BEGINN, INDEX_VERFAHREN_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSBD.BBNR_ABSENDER, INDEX_BBNR_ABSENDER_BEGINN, INDEX_BBNR_ABSENDER_ENDE,
				datensatz));
		felder.add(parseFeld(FeldNameDSBD.BBNR_EMPFAENGER, INDEX_BBNR_EMPFAENGER_BEGINN, INDEX_BBNR_EMPFAENGER_ENDE,
				datensatz));
		felder.add(parseFeld(FeldNameDSBD.VERSIONS_NR, INDEX_VERSION_BEGINN, INDEX_VERSION_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSBD.DATUM_ERSTELLUNG, INDEX_DATUM_ERSTELLUNG_BEGINN, INDEX_DATUM_ERSTELLUNG_ENDE,
				datensatz));
		felder.add(parseFeld(FeldNameDSBD.FEHLER_KENNZ, INDEX_FEHLERKENNZEICHNUNG_BEGINN,
				INDEX_FEHLERKENNZEICHNUNG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSBD.FEHLER_ANZAHL, INDEX_FEHLERANZAHL_BEGINN, INDEX_FEHLERANZAHL_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSBD.BBNR_BETRIEBSSTAETTE, INDEX_BBNR_BETRIEBSSTAETTE_BEGINN,
				INDEX_BBNR_BETRIEBSSTAETTE_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSBD.RESERVE1, INDEX_RESERVE_1_BEGINN, INDEX_RESERVE_1_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSBD.BBNR_ABRECHNUNGSSTELLE, INDEX_BBNR_ABRECHNUNGSSTELLE_BEGINN,
				INDEX_BBNR_ABRECHNUNGSSTELLE_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSBD.ABGABEGRUND, INDEX_ABGABEGRUND_BEGINN, INDEX_ABGABEGRUND_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSBD.WIRTSCHAFTSUNTERKLASSE, INDEX_WIRTSCHAFTSUNTERKLASSE_BEGINN,
				INDEX_WIRTSCHAFTSUNTERKLASSE_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSBD.NAME_BEZEICHNUNG1, INDEX_NAME_BEZEICHNUNG_1_BEGINN,
				INDEX_NAME_BEZEICHNUNG_1_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSBD.POSTLEITZAHL_ZUSTELL, INDEX_POSTLEITZAHL_ZUSTELL_BEGINN,
				INDEX_POSTLEITZAHL_ZUSTELL_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSBD.ORT, INDEX_ORT_BEGINN, INDEX_ORT_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSBD.STRASSE, INDEX_STRASSE_BEGINN, INDEX_STRASSE_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSBD.HAUSNUMMER, INDEX_HAUSNUMMER_BEGINN, INDEX_HAUSNUMMER_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSBD.POSTLEITZAHL_POSTFACH, INDEX_POSTLEITZAHL_POSTFACH_BEGINN,
				INDEX_POSTLEITZAHL_POSTFACH_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSBD.RUHEND_KENNZEICHEN, INDEX_RUHEND_KENNZEICHEN_BEGINN,
				INDEX_RUHEND_KENNZEICHEN_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSBD.MELDENDE_STELLE, INDEX_MELDENDE_STELLE_BEGINN, INDEX_MELDENDE_STELLE_ENDE,
				datensatz));
		felder.add(parseFeld(FeldNameDSBD.ANREDE_ANSPRECHPARTNER, INDEX_ANSPRECHPARTNER_BEGINN,
				INDEX_ANSPRECHPARTNER_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSBD.EMAIL_ANSPRECHPARTNER, INDEX_EMAIL_ANSPRECHPARTNER_BEGINN,
				INDEX_EMAIL_ANSPRECHPARTNER_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSBD.BBNR_KK, INDEX_BBNR_KK_BEGINN, INDEX_BBNR_KK_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSBD.RESERVE2, INDEX_RESERVE_2_BEGINN, INDEX_RESERVE_2_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSBD.MM_ABWEICHENDE_ANSCHRIFT, INDEX_MMKA_BEGINN, INDEX_MMKA_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSBD.MM_TEILNAHME_PFLICHTEN, INDEX_MMTN_BEGINN, INDEX_MMTN_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSBD.RESERVE3, INDEX_RESERVE_3_BEGINN, INDEX_RESERVE_3_ENDE, datensatz));

		return new Baustein<FeldNameDSBD>(BausteinName.DSBD, felder);
	}

	@Override
	public int getLaenge(final String datensatz) throws DatensatzAufbauException {
		return LAENGE;
	}

	@Override
	public Fehler<? extends FehlerNummer> getFehlerBausteinNichtVorhanden() {
		return null;
	}

}
