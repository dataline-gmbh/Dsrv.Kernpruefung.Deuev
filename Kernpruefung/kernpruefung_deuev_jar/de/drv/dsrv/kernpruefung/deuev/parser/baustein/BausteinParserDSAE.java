package de.drv.dsrv.kernpruefung.deuev.parser.baustein;

import java.util.ArrayList;

import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.parser.AbstractBausteinParser;
import de.drv.dsrv.kernpruefung.basis.parser.DatensatzAufbauException;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;

/**
 * Parser fuer den Baustein DSAE.
 */
public class BausteinParserDSAE extends AbstractBausteinParser<FeldNameDSAE> {

	private static final int INDEX_KENNUNG_BEGINN = 0;
	private static final int INDEX_KENNUNG_ENDE = 4;

	private static final int INDEX_VERFAHREN_BEGINN = INDEX_KENNUNG_ENDE;
	private static final int INDEX_VERFAHREN_ENDE = 9;

	private static final int INDEX_BBNRAB_BEGINN = INDEX_VERFAHREN_ENDE;
	private static final int INDEX_BBNRAB_ENDE = 24;

	private static final int INDEX_BBNREP_BEGINN = INDEX_BBNRAB_ENDE;
	private static final int INDEX_BBNREP_ENDE = 39;

	private static final int INDEX_VERNR_BEGINN = INDEX_BBNREP_ENDE;
	private static final int INDEX_VERNR_ENDE = 41;

	private static final int INDEX_ED_BEGINN = INDEX_VERNR_ENDE;
	private static final int INDEX_ED_ENDE = 61;

	private static final int INDEX_FEKZ_BEGINN = INDEX_ED_ENDE;
	private static final int INDEX_FEKZ_ENDE = 62;

	private static final int INDEX_FEAN_BEGINN = INDEX_FEKZ_ENDE;
	private static final int INDEX_FEAN_ENDE = 63;

	private static final int INDEX_VSNR_BEGINN = INDEX_FEAN_ENDE;
	private static final int INDEX_VSNR_ENDE = 75;

	private static final int INDEX_VSTR_BEGINN = INDEX_VSNR_ENDE;
	private static final int INDEX_VSTR_ENDE = 77;

	private static final int INDEX_BBNRVU_BEGINN = INDEX_VSTR_ENDE;
	private static final int INDEX_BBNRVU_ENDE = 92;

	private static final int INDEX_AZ_VU_BEGINN = INDEX_BBNRVU_ENDE;
	private static final int INDEX_AZ_VU_ENDE = 112;

	private static final int INDEX_RESERVE1_BEGINN = INDEX_AZ_VU_ENDE;
	private static final int INDEX_RESERVE1_ENDE = 170;

	private static final int INDEX_MMAZ_BEGINN = INDEX_RESERVE1_ENDE;
	private static final int INDEX_MMAZ_ENDE = 171;

	private static final int INDEX_MMEZ_BEGINN = INDEX_MMAZ_ENDE;
	private static final int INDEX_MMEZ_ENDE = 172;

	private static final int INDEX_RESERVE2_BEGINN = INDEX_MMEZ_ENDE;
	private static final int INDEX_RESERVE2_ENDE = 180;

	private static final int INDEX_KENNZUE_BEGINN = INDEX_RESERVE2_ENDE;
	private static final int INDEX_KENNZUE_ENDE = 181;

	private static final int INDEX_RESERVE3_BEGINN = INDEX_KENNZUE_ENDE;
	private static final int INDEX_RESERVE3_ENDE = 186;

	private static final int INDEX_VERNRKP_BEGINN = INDEX_RESERVE3_ENDE;
	private static final int INDEX_VERNRKP_ENDE = 188;

	private static final int INDEX_RESERVE4_BEGINN = INDEX_VERNRKP_ENDE;
	private static final int INDEX_RESERVE4_ENDE = 190;

	private static final int LAENGE_DSAE = 190;

	@Override
	public Baustein<FeldNameDSAE> parse(String datensatz)
			throws DatensatzAufbauException {
		final ArrayList<Feld<FeldNameDSAE>> felder = new ArrayList<Feld<FeldNameDSAE>>();
		felder.add(parseFeld(FeldNameDSAE.KENNUNG, INDEX_KENNUNG_BEGINN,
				INDEX_KENNUNG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSAE.VERFAHREN, INDEX_VERFAHREN_BEGINN,
				INDEX_VERFAHREN_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSAE.BBNR_ABSENDER, INDEX_BBNRAB_BEGINN,
				INDEX_BBNRAB_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSAE.BBNR_EMPFAENGER, INDEX_BBNREP_BEGINN,
				INDEX_BBNREP_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSAE.VERSIONS_NR, INDEX_VERNR_BEGINN,
				INDEX_VERNR_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSAE.DATUM_ERSTELLUNG, INDEX_ED_BEGINN,
				INDEX_ED_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSAE.FEHLER_KENNZ, INDEX_FEKZ_BEGINN,
				INDEX_FEKZ_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSAE.FEHLER_ANZAHL, INDEX_FEAN_BEGINN,
				INDEX_FEAN_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSAE.VSNR, INDEX_VSNR_BEGINN,
				INDEX_VSNR_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSAE.VSTR, INDEX_VSTR_BEGINN,
				INDEX_VSTR_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSAE.BBNR_VU, INDEX_BBNRVU_BEGINN,
				INDEX_BBNRVU_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSAE.AKTENZEICHEN_VERURSACHER,
				INDEX_AZ_VU_BEGINN, INDEX_AZ_VU_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSAE.RESERVE1, INDEX_RESERVE1_BEGINN,
				INDEX_RESERVE1_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSAE.MM_ANRECHNUNGSZEITEN,
				INDEX_MMAZ_BEGINN, INDEX_MMAZ_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSAE.MM_ENTGELT_ERSATZLEISTUNGSZEITEN,
				INDEX_MMEZ_BEGINN, INDEX_MMEZ_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSAE.RESERVE2, INDEX_RESERVE2_BEGINN,
				INDEX_RESERVE2_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSAE.KENNZ_UEBERGANG,
				INDEX_KENNZUE_BEGINN, INDEX_KENNZUE_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSAE.RESERVE3, INDEX_RESERVE3_BEGINN,
				INDEX_RESERVE3_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSAE.VERSIONS_NR_KP, INDEX_VERNRKP_BEGINN,
				INDEX_VERNRKP_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDSAE.RESERVE4, INDEX_RESERVE4_BEGINN,
				INDEX_RESERVE4_ENDE, datensatz));

		return new Baustein<FeldNameDSAE>(BausteinName.DSAE, felder);
	}

	@Override
	public int getLaenge(String datensatz) throws DatensatzAufbauException {
		return LAENGE_DSAE;
	}

	@Override
	public Fehler<? extends FehlerNummer> getFehlerBausteinNichtVorhanden() {
		return null;
	}
}
