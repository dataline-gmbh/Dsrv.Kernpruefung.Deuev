package de.drv.dsrv.kernpruefung.deuev.parser.baustein;

import java.util.ArrayList;

import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.parser.AbstractBausteinParser;
import de.drv.dsrv.kernpruefung.basis.parser.DatensatzAufbauException;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Parser fuer den Baustein DSME.
 */
public class BausteinParserDSME extends AbstractBausteinParser<FeldNameDSME> {

	private static final int LAENGE_KENNUNG = 4;
	private static final int LAENGE_VF = 5;
	private static final int LAENGE_BBNR_ABS = 15;
	private static final int LAENGE_BBNR_EMP = 15;
	private static final int LAENGE_VERS = 2;
	private static final int LAENGE_DATUM = 20;
	private static final int LAENGE_FEKZ = 1;
	private static final int LAENGE_FEAN = 1;
	private static final int LAENGE_VSNR = 12;
	private static final int LAENGE_VSTR = 2;
	private static final int LAENGE_BBNR_VU = 15;
	private static final int LAENGE_AZ_VU = 20;
	private static final int LAENGE_BBNR_KK = 15;
	private static final int LAENGE_AZ_KK = 20;
	private static final int LAENGE_BBNR_AS = 15;
	private static final int LAENGE_PERSGR = 3;
	private static final int LAENGE_GD = 2;
	private static final int LAENGE_SASC = 3;
	private static final int LAENGE_MMME = 1;
	private static final int LAENGE_MMNA = 1;
	private static final int LAENGE_MMGB = 1;
	private static final int LAENGE_MMAN = 1;
	private static final int LAENGE_MMEU = 1;
	private static final int LAENGE_MMUV = 1;
	private static final int LAENGE_MMKS = 1;
	private static final int LAENGE_MMSV = 1;
	private static final int LAENGE_MMVR = 1;
	private static final int LAENGE_MMRG = 1;
	private static final int LAENGE_KENNZUE = 1;
	private static final int LAENGE_MMUEB = 1;
	private static final int LAENGE_KENNZUP = 1;
	private static final int LAENGE_MMSO = 1;
	private static final int LAENGE_KENNZSTA = 1;
	private static final int LAENGE_MMUE = 1;
	private static final int LAENGE_VERS_KP = 2;
	private static final int LAENGE_MMKV = 1;
	private static final int LAENGE_RESERVE = 1;

	private static final int LAENGE_DSME = 190;

	private int offset;

	/**
	 * Konstruktor. Setzt <code>offset</code> auf 0.
	 */
	public BausteinParserDSME() {
		offset = 0;
	}

	@Override
	public Baustein<FeldNameDSME> parse(final String datensatz) throws DatensatzAufbauException {

		final ArrayList<Feld<FeldNameDSME>> felder = new ArrayList<Feld<FeldNameDSME>>();
		felder.add(parseFeld(FeldNameDSME.KENNUNG, offset, offset + LAENGE_KENNUNG, datensatz));
		offset += LAENGE_KENNUNG;

		felder.add(parseFeld(FeldNameDSME.VERFAHREN, offset, offset + LAENGE_VF, datensatz));
		offset += LAENGE_VF;

		felder.add(parseFeld(FeldNameDSME.BBNR_ABSENDER, offset, offset + LAENGE_BBNR_ABS, datensatz));
		offset += LAENGE_BBNR_ABS;

		felder.add(parseFeld(FeldNameDSME.BBNR_EMPFAENGER, offset, offset + LAENGE_BBNR_EMP, datensatz));
		offset += LAENGE_BBNR_EMP;

		felder.add(parseFeld(FeldNameDSME.VERSIONS_NR, offset, offset + LAENGE_VERS, datensatz));
		offset += LAENGE_VERS;

		felder.add(parseFeld(FeldNameDSME.DATUM_ERSTELLUNG, offset, offset + LAENGE_DATUM, datensatz));
		offset += LAENGE_DATUM;

		felder.add(parseFeld(FeldNameDSME.FEHLER_KENNZ, offset, offset + LAENGE_FEKZ, datensatz));
		offset += LAENGE_FEKZ;

		felder.add(parseFeld(FeldNameDSME.FEHLER_ANZAHL, offset, offset + LAENGE_FEAN, datensatz));
		offset += LAENGE_FEAN;

		felder.add(parseFeld(FeldNameDSME.VSNR, offset, offset + LAENGE_VSNR, datensatz));
		offset += LAENGE_VSNR;

		felder.add(parseFeld(FeldNameDSME.VSTR, offset, offset + LAENGE_VSTR, datensatz));
		offset += LAENGE_VSTR;

		felder.add(parseFeld(FeldNameDSME.BBNR_VU, offset, offset + LAENGE_BBNR_VU, datensatz));
		offset += LAENGE_BBNR_VU;

		felder.add(parseFeld(FeldNameDSME.AKTENZEICHEN_VERURSACHER, offset, offset + LAENGE_AZ_VU, datensatz));
		offset += LAENGE_AZ_VU;

		felder.add(parseFeld(FeldNameDSME.BBNR_KK, offset, offset + LAENGE_BBNR_KK, datensatz));
		offset += LAENGE_BBNR_KK;

		felder.add(parseFeld(FeldNameDSME.AKTENZEICHEN_KK, offset, offset + LAENGE_AZ_KK, datensatz));
		offset += LAENGE_AZ_KK;

		felder.add(parseFeld(FeldNameDSME.BBNR_ABRECHNUNGSSTELLE, offset, offset + LAENGE_BBNR_AS, datensatz));
		offset += LAENGE_BBNR_AS;

		felder.add(parseFeld(FeldNameDSME.PERSONENGRUPPE, offset, offset + LAENGE_PERSGR, datensatz));
		offset += LAENGE_PERSGR;

		felder.add(parseFeld(FeldNameDSME.ABGABEGRUND, offset, offset + LAENGE_GD, datensatz));
		offset += LAENGE_GD;

		felder.add(parseFeld(FeldNameDSME.STAATSANGEHOERIGKEITS_SC, offset, offset + LAENGE_SASC, datensatz));
		offset += LAENGE_SASC;

		felder.add(parseFeld(FeldNameDSME.MM_MELDEDATEN, offset, offset + LAENGE_MMME, datensatz));
		offset += LAENGE_MMME;

		felder.add(parseFeld(FeldNameDSME.MM_NAME, offset, offset + LAENGE_MMNA, datensatz));
		offset += LAENGE_MMNA;

		felder.add(parseFeld(FeldNameDSME.MM_GEBNAME, offset, offset + LAENGE_MMGB, datensatz));
		offset += LAENGE_MMGB;

		felder.add(parseFeld(FeldNameDSME.MM_ANSCHRIFT, offset, offset + LAENGE_MMAN, datensatz));
		offset += LAENGE_MMAN;

		felder.add(parseFeld(FeldNameDSME.MM_EUDATEN, offset, offset + LAENGE_MMEU, datensatz));
		offset += LAENGE_MMEU;

		felder.add(parseFeld(FeldNameDSME.MM_UVDATEN, offset, offset + LAENGE_MMUV, datensatz));
		offset += LAENGE_MMUV;

		felder.add(parseFeld(FeldNameDSME.MM_KNV_SEE, offset, offset + LAENGE_MMKS, datensatz));
		offset += LAENGE_MMKS;

		felder.add(parseFeld(FeldNameDSME.MM_SVA, offset, offset + LAENGE_MMSV, datensatz));
		offset += LAENGE_MMSV;

		felder.add(parseFeld(FeldNameDSME.MM_VERGABE_RUECKMELDUNG, offset, offset + LAENGE_MMVR, datensatz));
		offset += LAENGE_MMVR;

		felder.add(parseFeld(FeldNameDSME.MM_RUECKMELDUNG_GERINGFUEGIG, offset, offset + LAENGE_MMRG, datensatz));
		offset += LAENGE_MMRG;

		felder.add(parseFeld(FeldNameDSME.KENNZ_UEBERGANG, offset, offset + LAENGE_KENNZUE, datensatz));
		offset += LAENGE_KENNZUE;

		felder.add(parseFeld(FeldNameDSME.MM_UEBERMITTLUNG, offset, offset + LAENGE_MMUEB, datensatz));
		offset += LAENGE_MMUEB;

		felder.add(parseFeld(FeldNameDSME.KENNZ_UNIPOST_GEPRUEFT, offset, offset + LAENGE_KENNZUP, datensatz));
		offset += LAENGE_KENNZUP;

		felder.add(parseFeld(FeldNameDSME.MM_SOFORT, offset, offset + LAENGE_MMSO, datensatz));
		offset += LAENGE_MMSO;

		felder.add(parseFeld(FeldNameDSME.KENNZ_STATUS, offset, offset + LAENGE_KENNZSTA, datensatz));
		offset += LAENGE_KENNZSTA;

		felder.add(parseFeld(FeldNameDSME.MM_UEBERW_EINZUGSVG, offset, offset + LAENGE_MMUE, datensatz));
		offset += LAENGE_MMUE;

		felder.add(parseFeld(FeldNameDSME.VERSIONS_NR_KP, offset, offset + LAENGE_VERS_KP, datensatz));
		offset += LAENGE_VERS_KP;

		felder.add(parseFeld(FeldNameDSME.MM_KVDATEN, offset, offset + LAENGE_MMKV, datensatz));
		offset += LAENGE_MMKV;

		felder.add(parseFeld(FeldNameDSME.RESERVE, offset, offset + LAENGE_RESERVE, datensatz));
		offset += LAENGE_RESERVE;

		return new Baustein<FeldNameDSME>(BausteinName.DSME, felder);
	}

	@Override
	public int getLaenge(final String datensatz) throws DatensatzAufbauException {
		return LAENGE_DSME;
	}

	@Override
	public Fehler<? extends FehlerNummer> getFehlerBausteinNichtVorhanden() {
		return null;
	}

}
