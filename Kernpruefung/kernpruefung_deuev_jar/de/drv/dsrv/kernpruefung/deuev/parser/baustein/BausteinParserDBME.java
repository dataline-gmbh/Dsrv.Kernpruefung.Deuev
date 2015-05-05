package de.drv.dsrv.kernpruefung.deuev.parser.baustein;

import java.util.ArrayList;

import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.parser.AbstractBausteinParser;
import de.drv.dsrv.kernpruefung.basis.parser.DatensatzAufbauException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;

/**
 * Parser fuer den Baustein DBME.
 */
public class BausteinParserDBME extends AbstractBausteinParser<FeldNameDBME> {

	private static final int INDEX_KENNUNG_BEGINN = 0;
	private static final int INDEX_KENNUNG_ENDE = 4;

	private static final int INDEX_KENNZST_BEGINN = INDEX_KENNUNG_ENDE;
	private static final int INDEX_KENNZST_ENDE = 5;

	private static final int INDEX_KENNZGLE_BEGINN = INDEX_KENNZST_ENDE;
	private static final int INDEX_KENNZGLE_ENDE = 6;

	private static final int INDEX_ZRBG_BEGINN = INDEX_KENNZGLE_ENDE;
	private static final int IDNEX_ZRBG_ENDE = 14;

	private static final int INDEX_ZREN_BEGINN = IDNEX_ZRBG_ENDE;
	private static final int INDEX_ZREN_ENDE = 22;

	private static final int INDEX_ZLTG_BEGINN = INDEX_ZREN_ENDE;
	private static final int IDNEX_ZLTG_ENDE = 24;

	private static final int INDEX_WG_BEGINN = IDNEX_ZLTG_ENDE;
	private static final int IDNEX_WG_ENDE = 25;

	private static final int INDEX_EG_BEGINN = IDNEX_WG_ENDE;
	private static final int INDEX_EG_ENDE = 31;

	private static final int INDEX_BYGR_BEGINN = INDEX_EG_ENDE;
	private static final int INDEX_BYGR_ENDE = 35;

	private static final int INDEX_TTSC_BEGINN = INDEX_BYGR_ENDE;
	private static final int INDEX_TTSC_ENDE = 44;

	private static final int INDEX_KENNZRK_BEGINN = INDEX_TTSC_ENDE;
	private static final int IDNEX_KENNZRK_ENDE = 45;

	private static final int INDEX_KENNZMF_BEGINN = IDNEX_KENNZRK_ENDE;
	private static final int INDEX_KENNZMF_ENDE = 46;

	private static final int LAENGE_DBME = 46;

	@Override
	public Baustein<FeldNameDBME> parse(final String datensatz) throws DatensatzAufbauException {

		final ArrayList<Feld<FeldNameDBME>> felder = new ArrayList<Feld<FeldNameDBME>>();
		felder.add(parseFeld(FeldNameDBME.KENNUNG, INDEX_KENNUNG_BEGINN, INDEX_KENNUNG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBME.KENNZ_STORNO, INDEX_KENNZST_BEGINN, INDEX_KENNZST_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBME.KENNZ_GLEITZONE, INDEX_KENNZGLE_BEGINN, INDEX_KENNZGLE_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBME.ZEITRAUM_BEGINN, INDEX_ZRBG_BEGINN, IDNEX_ZRBG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBME.ZEITRAUM_ENDE, INDEX_ZREN_BEGINN, INDEX_ZREN_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBME.ZAHL_TAGE, INDEX_ZLTG_BEGINN, IDNEX_ZLTG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBME.WAEHRUNGS_KENNZ, INDEX_WG_BEGINN, IDNEX_WG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBME.ENTGELT, INDEX_EG_BEGINN, INDEX_EG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBME.BEITRAGS_GRUPPE, INDEX_BYGR_BEGINN, INDEX_BYGR_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBME.TAETIGKEITS_SC, INDEX_TTSC_BEGINN, INDEX_TTSC_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBME.KENNZ_RECHTSKREIS, INDEX_KENNZRK_BEGINN, IDNEX_KENNZRK_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBME.KENNZ_MEHRFACH, INDEX_KENNZMF_BEGINN, INDEX_KENNZMF_ENDE, datensatz));

		return new Baustein<FeldNameDBME>(BausteinName.DBME, felder);
	}

	@Override
	public int getLaenge(final String datensatz) throws DatensatzAufbauException {
		return LAENGE_DBME;
	}

	@Override
	public Fehler<? extends FehlerNummer> getFehlerBausteinNichtVorhanden() {
		return new Fehler<FehlerNummer>(FehlerNummerDSME.DSME930);
	}
}
