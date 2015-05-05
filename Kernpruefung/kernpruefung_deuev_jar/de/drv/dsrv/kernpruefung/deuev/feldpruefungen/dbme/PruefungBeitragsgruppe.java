package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbme;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumNachDatum;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumVorDatum;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungGleicherString;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtGleicherString;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeerNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNotInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils.DateUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBGB;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;
import de.drv.dsrv.kernpruefung.deuev.pruefungen.PruefungBeitragsgruppeKombination;
import de.drv.dsrv.kernpruefung.deuev.pruefungen.PruefungBeitragsgruppeZulaessig;
import de.drv.dsrv.kernpruefung.deuev.pruefungen.PruefungIstBeitragsgruppe;

/**
 * Pruefung fuer das Feld Anzahl Tage.
 */
public class PruefungBeitragsgruppe extends AbstractFeldPruefung<FeldNameDBME, FehlerNummerDBME> {

	private enum BYGR {
		KV, RV, ALV, PV
	}

	private static final int BYGR_LENGTH = 4;

	private static final int INDEX_1 = 1;
	private static final int INDEX_2 = 2;
	private static final int INDEX_3 = 3;
	private static final int INDEX_4 = 4;

	private static final int INDEX_0 = 0;

	private static final Integer PERSGR_LGTH = 3;
	private static final String STORNIERUNG = "J";
	private static final String KEINESTORNIERUNG = "N";
	
	private static final List<String> VFMM = Arrays.asList("AGDEU", "AGTRV", "KVDEU", "WLTKV", "KSTKV", "ABGVD", "BVAGD");
	
	private static final Character HUNDERT = '1';
	private static final String BYGR_LEER = "0000";
	private static final String SC_DEU = "000";
	private static final String SEELEUTE = "140";
	private static final String UNST_BESCH = "205";

	private static final List<String> MELD_106_RV = Arrays.asList("2", "4", "6");
//	 TODO Damit Java wie Cobol läuft, musste die Personengruppe 106 als Ausnahme mit angegeben werden.	
	private static final List<String> MELD_107 = Arrays.asList("106", "110", "190", "202", "210", "304", "306");
	private static final List<String> MELD_109_GL = Arrays.asList("1", "2");
	private static final List<String> MELD_109_RV = Arrays.asList("5", "6");

	private static final String MELD_113_KV = "6";
	private static final String MELD_113_RV = "1";
	private static final List<String> ERLAUBTE_WERTE_PERSGR_DBME113 = Arrays.asList("109", "209");

	private static final List<String> MELD_114 = Arrays.asList("110", "202", "210", "304", "306");
	private static final List<String> MELD_115_RV = Arrays.asList("5", "6");
	private static final List<String> MELD_115_PERSGR = Arrays.asList("109", "209");

	private static final String MELD_116_PERSGR = "108";
	private static final List<String> MELD_116_KV = Arrays.asList("0", "3", "4", "9");
	private static final List<String> MELD_116_RV = Arrays.asList("0", "1", "2", "9");
	private static final List<String> MELD_116_ALV = Arrays.asList("0", "9");
	private static final List<String> MELD_116_PV = Arrays.asList("0", "1", "2", "9");

	private static final List<String> MELD_117_P = Arrays.asList("301", "302", "303", "305");
	private static final String BYGR_117 = "0100";

	private static final String MELD_118_PERSGR = "116";
	private static final List<String> MELD_118_KV = Arrays.asList("0", "3");
	private static final List<String> MELD_118_RV = Arrays.asList("0", "1", "2", "9");
	private static final List<String> MELD_118_ALV = Arrays.asList("0", "9");
	private static final List<String> MELD_118_PV = Arrays.asList("0", "1", "2", "9");

	private static final List<String> MELD_119_PERSGR = Arrays.asList("109", "209");
	private static final List<String> MELD_119_ALV = Arrays.asList("0", "1", "2");

	private static final String MELD_120_PERSGR = "119";
	private static final List<String> MELD_120_RV = Arrays.asList("3", "4", "9");

	private static final List<String> MELD_121_P = Arrays.asList("301", "302", "303", "305");
	private static final String BYGR_121 = "0110";

	private static final String MELD_122_KV = "5";
	private static final List<String> MELD_124_PV = Arrays.asList("1", "2");
	private static final String MELD_125_KV = "2";

	private static final List<String> GD_GEB_DAT = Arrays.asList("10", "11", "12", "13", "40");

	private static final String MELD_126_ALV = "1";
	private static final String MELD_128_ALV = "2";

	private static final List<String> MELD_129_BBNRVU = Arrays.asList("980", "098");
	private static final List<String> MELD_129_BBNRKK = Arrays.asList("98094032", "98094037");
	private static final String MELD_129_RV = "0";

	private static final List<String> MELD_130 = Arrays.asList("0A", "0C", "AC", "BA", "BC");
	private static final List<String> MELD_130_RV = Arrays.asList("0", "1", "3", "5", "9");

	private static final String MELD_131_P = "140";
	private static final String MELD_131_S = "000";
	private static final Object MELD_131_B = "0000";
	private static final List<String> MELD_131_BBNRKK = Arrays.asList("99086875", "98000006");

	private static final List<String> MELD_132 = Arrays.asList("0B", "0G", "AB", "AG", "BB", "BG");
	private static final List<String> MELD_132_RV = Arrays.asList("0", "2", "4", "6", "9");

	private static final List<String> MELD_133_KB = Arrays.asList("110", "202", "210");
	private static final List<String> MELD_133_GB = Arrays.asList("109", "209");
	private static final List<String> MELD_133_GB_RV = Arrays.asList("1", "2");
	private static final String MELD_133_P_KV = "6";
	private static final List<String> MELD_133_P_RV = Arrays.asList("5", "6");
	private static final List<String> MELD_133_BBNRKK = Arrays.asList("98000006", "98094032");

	private static final String MELD_134_P = "205";
	private static final List<String> MELD_134_RV = Arrays.asList("0", "1", "2", "3", "4", "9");

	private static final List<String> MELD_135_P = Arrays.asList("301", "302", "303", "305");
	private static final List<String> BYGR_135 = Arrays.asList("0100", "0110", "0200");

	private static final String MELD_136_P = "203";
	private static final String MELD_136_VFMM = "KSTRV";
	private static final String BYGR_136 = "0200";

	private static final String MELD_137_P = "203";
	private static final String MELD_137_VFMM = "KSTKV";
	private static final List<String> BYGR_137 = Arrays.asList("100", "200", "300");

	private static final List<String> MELD_138_P = Arrays.asList("207", "208");
	private static final List<String> BYGR_138 = Arrays.asList(BYGR_117, "0200");

	private static final String MELD_139_P = "203";
	private static final String MELD_139_VFMM = "KSTRV";
	private static final String BYGR_139 = BYGR_117;

	// @formatter:off
	private static final Date DAT_106 = new GregorianCalendar(2004, 11, 31).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_117 = new GregorianCalendar(2006, 11, 31).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_121 = new GregorianCalendar(2006, 1, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_122_124 = new GregorianCalendar(1995, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_125 = new GregorianCalendar(2008, 11, 31).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_129 = new GregorianCalendar(2007, 3, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_133 = new GregorianCalendar(2003, 2, 31).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_136 = new GregorianCalendar(2005, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_139 = new GregorianCalendar(2004, 11, 31).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	
	// @formatter:on

	private final transient Feld<FeldNameDBME> feldDBMEKennzGl;
	private final transient Feld<FeldNameDBME> feldDBMEZrbg;
	private final transient Feld<FeldNameDBME> feldDBMEZren;
	private final transient Feld<FeldNameDBME> feldDBMEKennzSto;

	private transient Feld<FeldNameDSME> feldDSMEKe;
	private transient Feld<FeldNameDSME> feldDSMEBbnrvu;
	private transient Feld<FeldNameDSME> feldDSMEBbnrkk;
	private transient Feld<FeldNameDSME> feldDSMEGd;
	private transient Feld<FeldNameDSME> feldDSMEPersgr;
	private transient Feld<FeldNameDSME> feldDSMEStaatsAng;
	private transient Feld<FeldNameDSME> feldDSMEVsnr;
	private transient Feld<FeldNameDSME> feldDSMEVstr;

	private transient Feld<FeldNameDBGB> feldDBGBGeburtsdatum;

	private final transient Date verarbDatum;
	private final transient String vfmm;

	private final transient String bygrAlv;
	private final transient String bygrRv;
	private final transient String bygrKv;
	private final transient String bygrPv;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            das Feld Anzahl Tage
	 * @param feldDBMEKennzSto
	 *            das Feld Kennzeichen Stornierung
	 * @param feldDBMEKennzGl
	 *            the feld Kennzeichen Gleitzeit
	 * @param feldDBMEZrbg
	 *            das Feld Zeitraum-Beginn
	 * @param feldDBMEZren
	 *            das Feld Zeitraum-Ende
	 * @param bausteinDSME
	 *            der Baustein DSME
	 * @param bausteinDBGB
	 *            der Baustein DBGB
	 * @param verarbDatum
	 *            das Verarbeitungsdatum
	 * @param vfmm
	 *            das Verfahrensmerkmal
	 */
	// @formatter:off
	public PruefungBeitragsgruppe(final Feld<FeldNameDBME> feld, final Feld<FeldNameDBME> feldDBMEKennzSto,  // SUPPRESS CHECKSTYLE ParameterNumber
			final Feld<FeldNameDBME> feldDBMEKennzGl, final Feld<FeldNameDBME> feldDBMEZrbg,
			final Feld<FeldNameDBME> feldDBMEZren, final Baustein<FeldNameDSME> bausteinDSME,
			final Baustein<FeldNameDBGB> bausteinDBGB, final Date verarbDatum, final String vfmm) {
		// @formatter:on
		super(feld);
		this.feldDBMEKennzSto = feldDBMEKennzSto;
		this.feldDBMEKennzGl = feldDBMEKennzGl;
		this.feldDBMEZrbg = feldDBMEZrbg;
		this.feldDBMEZren = feldDBMEZren;
		this.verarbDatum = verarbDatum;
		this.vfmm = vfmm;

		if (bausteinDSME != null) {
			this.feldDSMEKe = bausteinDSME.getFeld(FeldNameDSME.KENNUNG);
			this.feldDSMEBbnrkk = bausteinDSME.getFeld(FeldNameDSME.BBNR_KK);
			this.feldDSMEBbnrvu = bausteinDSME.getFeld(FeldNameDSME.BBNR_VU);
			this.feldDSMEGd = bausteinDSME.getFeld(FeldNameDSME.ABGABEGRUND);
			this.feldDSMEPersgr = bausteinDSME.getFeld(FeldNameDSME.PERSONENGRUPPE);
			this.feldDSMEStaatsAng = bausteinDSME.getFeld(FeldNameDSME.STAATSANGEHOERIGKEITS_SC);
			this.feldDSMEVsnr = bausteinDSME.getFeld(FeldNameDSME.VSNR);
			this.feldDSMEVstr = bausteinDSME.getFeld(FeldNameDSME.VSTR);
		}

		if (bausteinDBGB != null) {
			this.feldDBGBGeburtsdatum = bausteinDBGB.getFeld(FeldNameDBGB.GEBURTSDATUM);
		}

		this.bygrKv = this.getBygrSchluessel(BYGR.KV);
		this.bygrRv = this.getBygrSchluessel(BYGR.RV);
		this.bygrAlv = this.getBygrSchluessel(BYGR.ALV);
		this.bygrPv = this.getBygrSchluessel(BYGR.PV);

		if (LOGGER.isLoggable(Level.FINE)) {
			LOGGER.log(Level.FINE, "[DBME Beitragsgruppe] \"" + feld.getValue() + "\"");
		}
	}

	/**
	 * Gibt das der Meldung entsprechenden Bezugssdatum zurück.
	 * 
	 * @param zrbg
	 *            das Datum Zeitraum-Beginn
	 * @param zren
	 *            das Datum Zeitraum-Ende
	 * @return das der Meldung entsprechenden Bezugssdatum
	 * @throws UngueltigeDatenException
	 */
	private Feld<FeldNameDBME> getBezugsdatum(final Feld<FeldNameDBME> zrbg, final Feld<FeldNameDBME> zren)
			throws UngueltigeDatenException {
		Feld<FeldNameDBME> datum;
		if (GD_GEB_DAT.contains(this.feldDSMEGd.getTrimmedValue())) {
			datum = zrbg;
		} else {
			datum = zren;
		}
		return datum;
	}

	/**
	 * Gibt den Wert des uebergebenen Beitragsgruppenschluessels gemaess Anlage
	 * 1 zurueck. Der Beitragsgruppenschluessel besteht aus je einer Ziffer fuer
	 * KV, RV, ALV und PV.
	 * 
	 * @param bygr
	 *            the bygr
	 * @return the bygr schluessel
	 */
	private String getBygrSchluessel(final BYGR bygr) {
		String schluessel = null;

		if (this.getFeld() != null && this.getFeld().getTrimmedValue().length() == BYGR_LENGTH) {
			final String feldValue = this.getFeld().getTrimmedValue();

			switch (bygr) {
			case KV:
				schluessel = feldValue.substring(INDEX_0, INDEX_1);
				break;
			case RV:
				schluessel = feldValue.substring(INDEX_1, INDEX_2);
				break;
			case ALV:
				schluessel = feldValue.substring(INDEX_2, INDEX_3);
				break;
			case PV:
				schluessel = feldValue.substring(INDEX_3, INDEX_4);
				break;
			default:
				break;
			}

		}
		return schluessel;
	}

	/**
	 * Falls das Geburtsdatum in der VSNR angegeben ist, wird dieses
	 * herangezogen. Ansonsten das Geburtsdatum aus dem Baustein DBGB.
	 * 
	 * Fuer den Fall VSNR: Berechnet das Geburtsdatum aus der VSNR. Hierzu wird
	 * das Datum aus der VSNR extrahiert, ggf. zurueck gerechnet (zur Kodierung
	 * siehe Anlage "Allgemeines Rundschreiben") und ggf. mit Platzhaltern
	 * versehen (bei Auslaendern mit "00"-Datum).
	 * 
	 * Fuer den Fall DBGB: ggf. Platzhalter beachten.
	 * 
	 * Laut DRV ist entweder das Geburtsdatum in der VSNR oder in DBGB gesetzt.
	 * 
	 * @return das Geburtsdatum
	 */
	private Date getGebDat() {
		Date gebDat = null;

		if (StringUtils.isNotBlank(this.feldDSMEVsnr.getValue())) {
			gebDat = DateUtils.getDatVsnr(this.feldDSMEVsnr, this.verarbDatum);
		} else {
			try {
				final String korrigiertesDatum = DateUtils.korrigiereLeerDatum(this.feldDBGBGeburtsdatum
						.getTrimmedValue());
				final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				gebDat = sdf.parse(korrigiertesDatum);
			} catch (final ParseException e) {
				// Hier kann keine ParseException auftreten, da zuvor bereits
				// die Korrektheit ueberprueft wurde
				;
			}
		}

		return gebDat;
	}

	/**
	 * Initialisiere die bausteinuebergreifenden Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	// @formatter:off
	@Override // SUPPRESS CHECKSTYLE MethodLength
	// @formatter:on
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {
		final String feldKennzStoValue = this.feldDBMEKennzSto.getTrimmedValue();
		if (this.isPruefbar(FehlerNummerDBME.DBME108, this.feldDBMEKennzSto, this.feldDSMEPersgr)
				&& KEINESTORNIERUNG.equals(feldKennzStoValue) && this.feldDSMEPersgr != null
				&& this.isHunderter(this.feldDSMEPersgr)) {
			final PruefungBeitragsgruppeKombination me108 = new PruefungBeitragsgruppeKombination(this.getFeld(), this.feldDSMEPersgr);
			this.addPruefungBausteinUebergreifend(me108, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME108));
		}
		
		final String feldPersgrValue = this.feldDSMEPersgr.getTrimmedValue();
		if (this.isPruefbar(Arrays.asList(FehlerNummerDBME.DBME114), this.feldDSMEPersgr)) {
			if (MELD_114.contains(feldPersgrValue)) {
				final PruefungGleicherString me114 = new PruefungGleicherString(this.getFeld(), BYGR_LEER);
				this.addPruefungBausteinUebergreifend(me114, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME114));
			}
		}
		
		// feldDBGBGeburtsdatum kann null sein. (In dem Fall ist aber das
		// Geburtsdatum aus der VSNR gesetzt)
		if (this.isPruefbar(Arrays.asList(FehlerNummerDBME.DBME126, FehlerNummerDBME.DBME128), this.feldDSMEVsnr,
				this.feldDSMEGd, this.feldDBMEZrbg, this.feldDBMEZren)
				&& (this.feldDBGBGeburtsdatum == null || this.isPruefbar(
						Arrays.asList(FehlerNummerDBME.DBME126, FehlerNummerDBME.DBME128), this.feldDBGBGeburtsdatum))) {

			final Date gebDat = this.getGebDat();
			final Feld<FeldNameDBME> bezugsDatum = this.getBezugsdatum(this.feldDBMEZrbg, this.feldDBMEZren);
			final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.GERMANY);
			final boolean bezDatIsDatum = DateUtils.isLogischKorrektesDatum(bezugsDatum, dateFormat);

			if ((feldDSMEVsnr.getTrimmedValue().length() > 0 && !"DSAE".equals(feldDSMEKe.getTrimmedValue()) && VFMM.contains(vfmm)) && bezDatIsDatum && MELD_126_ALV.equals(this.bygrAlv)) {
				final Date geb67 = DateUtils.berechneDatumGeburtstag(gebDat, 67);
				final Date monatsEndeGeb67 = DateUtils.berechneNeuesDatumMonat(geb67, 0, true);

				final PruefungDatumVorDatum me126 = new PruefungDatumVorDatum(bezugsDatum, monatsEndeGeb67);
				this.addPruefungBausteinUebergreifend(me126, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME126));
			}
			
			if (bezDatIsDatum && KEINESTORNIERUNG.equals(feldKennzStoValue) && MELD_128_ALV.equals(this.bygrAlv)) {
				final Date geb55 = DateUtils.berechneDatumGeburtstag(gebDat, 55);

				final PruefungDatumNachDatum me128 = new PruefungDatumNachDatum(bezugsDatum, geb55);
				this.addPruefungBausteinUebergreifend(me128, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME128));
			}
		}
		
		if (this.isPruefbar(FehlerNummerDBME.DBME109, this.feldDBMEKennzGl)
				&& MELD_109_GL.contains(this.feldDBMEKennzGl.getTrimmedValue())) {
			final PruefungNotInList me109 = new PruefungNotInList(MELD_109_RV, new Feld<FeldNameDBME>(this.bygrRv));
			this.addPruefungBausteinUebergreifend(me109, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME109));
		}

		Date zrbgDate = null;
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.GERMANY);
		final boolean zrbgIsDatum = DateUtils.isLogischKorrektesDatum(this.feldDBMEZrbg, dateFormat);
		if (zrbgIsDatum) {
			zrbgDate = DateUtils.parseDate(this.feldDBMEZrbg);
		}

		Date zrenDate = null;
		final boolean zrenIsDatum = DateUtils.isLogischKorrektesDatum(this.feldDBMEZren, dateFormat);
		if (zrenIsDatum) {
			zrenDate = DateUtils.parseDate(this.feldDBMEZren);
		}
		
		if (this.isPruefbar(FehlerNummerDBME.DBME139, this.feldDBMEZrbg) && MELD_139_P.equals(feldPersgrValue)
				&& MELD_139_VFMM.equals(this.vfmm) && zrbgIsDatum && zrbgDate.after(DAT_139)) {
			final PruefungGleicherString me139 = new PruefungGleicherString(this.getFeld(), BYGR_139);
			this.addPruefungBausteinUebergreifend(me139, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME139));
		}
		
		if (this.isPruefbar(Arrays.asList(FehlerNummerDBME.DBME106, FehlerNummerDBME.DBME125), this.feldDBMEZrbg)) {	
			if (this.feldDBMEKennzSto.getTrimmedValue().equals("N") && zrbgIsDatum && MELD_106_RV.contains(this.bygrRv)) {
				final PruefungDatumVorDatum me106 = new PruefungDatumVorDatum(this.feldDBMEZrbg, DAT_106);
				this.addPruefungBausteinUebergreifend(me106, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME106));
			}
			
			if(zrbgIsDatum) {
				if (MELD_125_KV.equals(this.bygrKv)) {
					final PruefungDatumVorDatum me125 = new PruefungDatumVorDatum(this.feldDBMEZrbg, DAT_125);
					this.addPruefungBausteinUebergreifend(me125, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME125));
				}
			}
		}
		
		if (this.isPruefbar(FehlerNummerDBME.DBME107, this.feldDSMEPersgr, this.feldDSMEStaatsAng, this.feldDBMEKennzSto) && !this.isAusnahmeME107()) {
			final PruefungNichtLeerNumerisch me107 = new PruefungNichtLeerNumerisch(this.getFeld());
			this.addPruefungBausteinUebergreifend(me107, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME107));
		}

		if (this.isPruefbar(Arrays.asList(FehlerNummerDBME.DBME116, FehlerNummerDBME.DBME119,
				FehlerNummerDBME.DBME118, FehlerNummerDBME.DBME120), this.feldDSMEPersgr)) {
			if (MELD_116_PERSGR.equals(feldPersgrValue)) {
				final PruefungBeitragsgruppeZulaessig me116 = new PruefungBeitragsgruppeZulaessig(this.getFeld(),
						MELD_116_KV, MELD_116_RV, MELD_116_ALV, MELD_116_PV);
				this.addPruefungBausteinUebergreifend(me116, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME116));
			}

			if (MELD_119_PERSGR.contains(feldPersgrValue)) {
				final PruefungInList me119 = new PruefungInList(MELD_119_ALV, new Feld<FeldNameDBME>(this.bygrAlv));
				this.addPruefungBausteinUebergreifend(me119, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME119));
			}

			if (MELD_118_PERSGR.equals(feldPersgrValue)) {
				final PruefungBeitragsgruppeZulaessig me118 = new PruefungBeitragsgruppeZulaessig(this.getFeld(),
						MELD_118_KV, MELD_118_RV, MELD_118_ALV, MELD_118_PV);
				this.addPruefungBausteinUebergreifend(me118, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME118));
			}

			if (MELD_120_PERSGR.equals(feldPersgrValue)) {
				final PruefungInList me120 = new PruefungInList(MELD_120_RV, new Feld<FeldNameDBME>(this.bygrRv));
				this.addPruefungBausteinUebergreifend(me120, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME120));
			}
		}

		if (this.isPruefbar(Arrays.asList(FehlerNummerDBME.DBME130, FehlerNummerDBME.DBME132), this.feldDSMEVstr)) {
			final String vstrValue = this.feldDSMEVstr.getTrimmedValue();
			if (MELD_130.contains(vstrValue)) {
				final PruefungInList me130 = new PruefungInList(MELD_130_RV, new Feld<FeldNameDBME>(this.bygrRv));
				this.addPruefungBausteinUebergreifend(me130, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME130));
			}

			if (MELD_132.contains(vstrValue)) {
				final PruefungInList me132 = new PruefungInList(MELD_132_RV, new Feld<FeldNameDBME>(this.bygrRv));
				this.addPruefungBausteinUebergreifend(me132, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME132));
			}
		}
		
		if (this.isPruefbar(FehlerNummerDBME.DBME133, this.feldDBMEKennzSto, this.feldDSMEPersgr, this.feldDBMEZrbg,
				this.feldDBMEZren, this.feldDSMEBbnrkk)
				&& KEINESTORNIERUNG.equals(feldKennzStoValue)
				&& (this.isKurzBesch() || this.isGerBeschVollRV() || this.isGerBeschPauschalKV() || this
						.isGerBeschPauschalRV())
				&& (zrbgIsDatum && zrbgDate.after(DAT_133) || zrenIsDatum && zrenDate.after(DAT_133))) {
			final PruefungInList me133 = new PruefungInList(MELD_133_BBNRKK, this.feldDSMEBbnrkk);
			this.addPruefungBausteinUebergreifend(me133, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME133));
		}

		if (this.isPruefbar(Arrays.asList(FehlerNummerDBME.DBME134, FehlerNummerDBME.DBME136, FehlerNummerDBME.DBME139,
				FehlerNummerDBME.DBME137, FehlerNummerDBME.DBME138, FehlerNummerDBME.DBME117, FehlerNummerDBME.DBME121,
				FehlerNummerDBME.DBME135, FehlerNummerDBME.DBME115), this.feldDSMEPersgr)) {
			if (MELD_134_P.equals(feldPersgrValue)) {
				final PruefungInList me134 = new PruefungInList(MELD_134_RV, new Feld<FeldNameDBME>(this.bygrRv));
				this.addPruefungBausteinUebergreifend(me134, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME134));
			}

			if (this.isPruefbar(FehlerNummerDBME.DBME136, this.feldDBMEZrbg) && MELD_136_P.equals(feldPersgrValue)
					&& MELD_136_VFMM.equals(this.vfmm) && zrbgIsDatum && zrbgDate.before(DAT_136)) {
				final PruefungGleicherString me136 = new PruefungGleicherString(this.getFeld(), BYGR_136);
				this.addPruefungBausteinUebergreifend(me136, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME136));
			}

			if (MELD_137_P.equals(feldPersgrValue) && MELD_137_VFMM.equals(this.vfmm)) {
				final String byrgKurz = this.getFeld().getValue().substring(INDEX_0, INDEX_3);
				final PruefungInList me137 = new PruefungInList(BYGR_137, new Feld<FeldNameDBME>(byrgKurz));
				this.addPruefungBausteinUebergreifend(me137, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME137));
			}

			if (MELD_138_P.contains(feldPersgrValue)) {
				final PruefungInList me138 = new PruefungInList(BYGR_138, this.getFeld());
				this.addPruefungBausteinUebergreifend(me138, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME138));
			}

			if (this.isPruefbar(FehlerNummerDBME.DBME117, this.feldDBMEZrbg) && MELD_117_P.contains(feldPersgrValue)
					&& zrbgIsDatum && zrbgDate.after(DAT_117)) {
				final PruefungNichtGleicherString me117 = new PruefungNichtGleicherString(this.getFeld(), BYGR_117);
				this.addPruefungBausteinUebergreifend(me117, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME117));
			}

			if (this.isPruefbar(FehlerNummerDBME.DBME121, this.feldDBMEZrbg) && MELD_121_P.contains(feldPersgrValue)
					&& zrbgIsDatum && BYGR_121.equals(this.getFeld().getTrimmedValue())) {
				final PruefungDatumNachDatum me121 = new PruefungDatumNachDatum(this.feldDBMEZrbg, DAT_121);
				this.addPruefungBausteinUebergreifend(me121, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME121));
			}

			if (MELD_135_P.contains(feldPersgrValue)) {
				final PruefungInList me135 = new PruefungInList(BYGR_135, this.getFeld());
				this.addPruefungBausteinUebergreifend(me135, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME135));
			}
			
			if (MELD_115_RV.contains(this.bygrRv)) {
				final PruefungInList me115 = new PruefungInList(MELD_115_PERSGR, this.feldDSMEPersgr);
				this.addPruefungBausteinUebergreifend(me115, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME115));
			}
		}

		if (this.isPruefbar(FehlerNummerDBME.DBME131, this.feldDBMEKennzSto, this.feldDSMEBbnrkk, this.feldDSMEPersgr,
				this.feldDSMEStaatsAng)
				&& KEINESTORNIERUNG.equals(feldKennzStoValue)
				&& MELD_131_P.equals(feldPersgrValue)
				&& !MELD_131_S.equals(this.feldDSMEStaatsAng.getTrimmedValue())
				&& MELD_131_B.equals(this.getFeld().getTrimmedValue())) {
			final PruefungInList me131 = new PruefungInList(MELD_131_BBNRKK, this.feldDSMEBbnrkk);
			this.addPruefungBausteinUebergreifend(me131, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME131));
		}

		if (this.isPruefbar(FehlerNummerDBME.DBME129, this.feldDBMEKennzSto, this.feldDBMEZrbg, this.feldDSMEBbnrvu,
				this.feldDSMEBbnrkk)
				&& KEINESTORNIERUNG.equals(feldKennzStoValue)
				&& zrbgIsDatum
				&& zrbgDate.before(DAT_129) && !MELD_129_RV.contains(this.bygrRv)) {
			final String bbnrvuKurz = this.feldDSMEBbnrvu.getValue().substring(INDEX_0, INDEX_3);
			if (MELD_129_BBNRVU.contains(bbnrvuKurz)) {
				final PruefungInList me129 = new PruefungInList(MELD_129_BBNRKK, this.feldDSMEBbnrkk);
				this.addPruefungBausteinUebergreifend(me129, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME129));
			}
		}

		if (this.isPruefbar(FehlerNummerDBME.DBME113, this.feldDBMEKennzSto, this.feldDSMEPersgr)) {
			if (KEINESTORNIERUNG.equals(feldKennzStoValue) && MELD_113_KV.equals(bygrKv) && MELD_113_RV.equals(bygrRv)) {
				final PruefungInList me113 = new PruefungInList(ERLAUBTE_WERTE_PERSGR_DBME113, feldDSMEPersgr);
				addPruefungBausteinUebergreifend(me113, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME113));
			}
		}
	}

	/**
	 * Initialisiere die felduebergreifenden Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {

		if (this.isPruefbar(Arrays.asList(FehlerNummerDBME.DBME122, FehlerNummerDBME.DBME124), this.feldDBMEZrbg)) {

			final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.GERMANY);
			final boolean zrbgIsDatum = DateUtils.isLogischKorrektesDatum(this.feldDBMEZrbg, dateFormat);

			if (zrbgIsDatum) {
				if (MELD_122_KV.equals(this.bygrKv)) {
					final PruefungDatumNachDatum me122 = new PruefungDatumNachDatum(this.feldDBMEZrbg, DAT_122_124);
					this.addPruefungFeldUebergreifend(me122, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME122));
				}

				if (MELD_124_PV.contains(this.bygrPv)) {
					final PruefungDatumNachDatum me124 = new PruefungDatumNachDatum(this.feldDBMEZrbg, DAT_122_124);
					this.addPruefungFeldUebergreifend(me124, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME124));
				}
			}
		}
	}

	/**
	 * Initialisiere die feldinternen Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer me110a = new PruefungNichtLeer(this.getFeld());
		this.addPruefung(me110a, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME110));

		final PruefungNumerisch me110b = new PruefungNumerisch(this.getFeld(), true);
		this.addPruefung(me110b, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME110));

		final PruefungIstBeitragsgruppe me111 = new PruefungIstBeitragsgruppe(this.getFeld());
		this.addPruefung(me111, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME111));
	}

	/**
	 * Prueft, ob die Meldung die Bedingungen der Ausnahme zu Fehlernummer
	 * DBME107 entspricht.
	 * 
	 * @return true, if is Ausnahme ME107
	 * @throws UngueltigeDatenException
	 */
	private boolean isAusnahmeME107() throws UngueltigeDatenException {
		return this.isNichtDeuSeeleute() || MELD_107.contains(this.feldDSMEPersgr.getTrimmedValue())
				|| this.isStorUnstBesch();
	}

	private boolean isGerBeschPauschalKV() {
		return MELD_133_P_KV.equals(this.bygrKv);
	}

	private boolean isGerBeschPauschalRV() throws UngueltigeDatenException {
		return MELD_133_P_RV.contains(this.bygrRv);
	}

	private boolean isGerBeschVollRV() throws UngueltigeDatenException {
		return MELD_133_GB.contains(this.feldDSMEPersgr.getTrimmedValue()) && MELD_133_GB_RV.contains(this.bygrRv);
	}

	/**
	 * Prueft ob der Feldwert dreistellig ist und mit '1' beginnt (und somit ein
	 * Hunderter-Wert ist).
	 * 
	 * @param persgr
	 *            das zu pruefende Feld Personengruppe
	 * @return true, if is hunderter
	 */
	private boolean isHunderter(final Feld<FeldNameDSME> persgr) {
		final String value = persgr.getTrimmedValue();
		return value != null && PERSGR_LGTH.equals(value.length()) && HUNDERT.equals(value.charAt(0));
	}

	private boolean isKurzBesch() throws UngueltigeDatenException {
		return MELD_133_KB.contains(this.feldDSMEPersgr.getTrimmedValue());
	}

	/**
	 * Prueft, ob eine Meldung fuer nicht-deutsche Seeleute vorliegt.
	 * 
	 * @return true, if is nicht-deutsche Seeleute
	 */
	private boolean isNichtDeuSeeleute() {
		return SEELEUTE.equals(this.feldDSMEPersgr.getTrimmedValue())
				&& !SC_DEU.equals(this.feldDSMEStaatsAng.getTrimmedValue());
	}

	/**
	 * Prueft, ob eine Stornierung fuer unstaendig Beschaeftigte vorliegt.
	 * 
	 * @return true, if is Stornierung fuer unstaendig Beschaeftigte
	 */
	private boolean isStorUnstBesch() {
		return STORNIERUNG.equals(this.feldDBMEKennzSto.getTrimmedValue())
				&& UNST_BESCH.equals(this.feldDSMEPersgr.getTrimmedValue());
	}
}
