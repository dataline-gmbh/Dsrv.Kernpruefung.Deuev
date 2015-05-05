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
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKV;

/**
 * Parser fuer den Baustein DBKV.
 */
public class BausteinParserDBKV extends AbstractBausteinParser<FeldNameDBKV> {

	private static final int INDEX_KENNUNG_BEGINN = 0;
	private static final int INDEX_KENNUNG_ENDE = 4;

	private static final int INDEX_KENNZST_BEGINN = INDEX_KENNUNG_ENDE;
	private static final int INDEX_KENNZST_ENDE = 5;

	private static final int INDEX_KVGD_BEGINN = INDEX_KENNZST_ENDE;
	private static final int INDEX_KVGD_ENDE = 7;

	private static final int INDEX_SVTAGE_BEGINN = INDEX_KVGD_ENDE;
	private static final int INDEX_SVTAGE_ENDE = 9;

	private static final int INDEX_ZRBG_BEGINN = INDEX_SVTAGE_ENDE;
	private static final int INDEX_ZRBG_ENDE = 17;

	private static final int INDEX_ZREN_BEGINN = INDEX_ZRBG_ENDE;
	private static final int INDEX_ZREN_ENDE = 25;

	private static final int INDEX_LFDEG_BEGINN = INDEX_ZREN_ENDE;
	private static final int INDEX_LFDEG_ENDE = 33;

	private static final int INDEX_EZEG_BEGINN = INDEX_LFDEG_ENDE;
	private static final int INDEX_EZEG_ENDE = 41;

	private static final int INDEX_BBGRU_KUG_BEGINN = INDEX_EZEG_ENDE;
	private static final int INDEX_BBGRU_KUG_ENDE = 49;

	private static final int INDEX_KENNZGLESV_BEGINN = INDEX_BBGRU_KUG_ENDE;
	private static final int INDEX_KENNZGLESV_ENDE = 50;

	private static final int INDEX_RESERVE1_BEGINN = INDEX_KENNZGLESV_ENDE;
	private static final int INDEX_RESERVE1_ENDE = 51;

	private static final int INDEX_RESERVE2_BEGINN = INDEX_RESERVE1_ENDE;
	private static final int INDEX_RESERVE2_ENDE = 52;

	private static final int INDEX_RJEG_BEGINN = INDEX_RESERVE2_ENDE;
	private static final int INDEX_RJEG_ENDE = 60;

	private static final int INDEX_BBGRU_ATG_BEGINN = INDEX_RJEG_ENDE;
	private static final int INDEX_BBGRU_ATG_ENDE = 68;

	private static final int INDEX_BYGR_BEGINN = INDEX_BBGRU_ATG_ENDE;
	private static final int INDEX_BYGR_ENDE = 72;

	private static final int INDEX_KENNZRK_BEGINN = INDEX_BYGR_ENDE;
	private static final int INDEX_KENNZRK_ENDE = 73;
	
	private static final int INDEX_LFDKV_BEGINN = INDEX_KENNZRK_ENDE;
	private static final int INDEX_LFDKV_ENDE = 81;
	
	private static final int INDEX_LFDRV_BEGINN = INDEX_LFDKV_ENDE;
	private static final int INDEX_LFDRV_ENDE = 89;
	
	private static final int INDEX_LFDAV_BEGINN = INDEX_LFDRV_ENDE;
	private static final int INDEX_LFDAV_ENDE = 97;

	private static final int INDEX_RESERVE3_BEGINN = INDEX_LFDAV_ENDE;
	private static final int INDEX_RESERVE3_ENDE = 150;
	
	private static final int LAENGE_DBKV = 150;

	@Override
	public Baustein<FeldNameDBKV> parse(final String datensatz) throws DatensatzAufbauException {

		final ArrayList<Feld<FeldNameDBKV>> felder = new ArrayList<Feld<FeldNameDBKV>>();
		felder.add(parseFeld(FeldNameDBKV.KENNUNG, INDEX_KENNUNG_BEGINN, INDEX_KENNUNG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKV.KENNZ_STORNO, INDEX_KENNZST_BEGINN, INDEX_KENNZST_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKV.KV_GRUND, INDEX_KVGD_BEGINN, INDEX_KVGD_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKV.SV_TAGE, INDEX_SVTAGE_BEGINN, INDEX_SVTAGE_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKV.ZEITRAUM_BEGINN, INDEX_ZRBG_BEGINN, INDEX_ZRBG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKV.ZEITRAUM_ENDE, INDEX_ZREN_BEGINN, INDEX_ZREN_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKV.LAUFENDES_ENTGELT, INDEX_LFDEG_BEGINN, INDEX_LFDEG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKV.EINMALIGES_ENTGELT, INDEX_EZEG_BEGINN, INDEX_EZEG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKV.BEITRAGS_BEMMESUNGSGRUNDLAGE_KURZ_ARBEITERGELD, INDEX_BBGRU_KUG_BEGINN, INDEX_BBGRU_KUG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKV.KENNZ_GLEITZONE, INDEX_KENNZGLESV_BEGINN, INDEX_KENNZGLESV_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKV.RESERVE1, INDEX_RESERVE1_BEGINN, INDEX_RESERVE1_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKV.RESERVE2, INDEX_RESERVE2_BEGINN, INDEX_RESERVE2_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKV.REGELMAESSIGES_JAHRESENTGELT, INDEX_RJEG_BEGINN, INDEX_RJEG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKV.BEITRAGSBEMESSUNGSGRUNDLAGE_ENTGELT_ALTERSTEILZEIT, INDEX_BBGRU_ATG_BEGINN, INDEX_BBGRU_ATG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKV.BEITRAGS_GRUPPE, INDEX_BYGR_BEGINN, INDEX_BYGR_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKV.KENNZ_RECHTSKREIS, INDEX_KENNZRK_BEGINN, INDEX_KENNZRK_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKV.LAUFENDES_ENTGELT_KV, INDEX_LFDKV_BEGINN, INDEX_LFDKV_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKV.LAUFENDES_ENTGELT_RV, INDEX_LFDRV_BEGINN, INDEX_LFDRV_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKV.LAUFENDES_ENTGELT_AV, INDEX_LFDAV_BEGINN, INDEX_LFDAV_ENDE, datensatz));		
		felder.add(parseFeld(FeldNameDBKV.RESERVE3, INDEX_RESERVE3_BEGINN, INDEX_RESERVE3_ENDE, datensatz));

		return new Baustein<FeldNameDBKV>(BausteinName.DBKV, felder);
	}

	@Override
	public int getLaenge(final String datensatz) throws DatensatzAufbauException {
		return LAENGE_DBKV;
	}

	@Override
	public Fehler<? extends FehlerNummer> getFehlerBausteinNichtVorhanden() {
		return new Fehler<FehlerNummer>(FehlerNummerDSME.DSME941);
	}

}
