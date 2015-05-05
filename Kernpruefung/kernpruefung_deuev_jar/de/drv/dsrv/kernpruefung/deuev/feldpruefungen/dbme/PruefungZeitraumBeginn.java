package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbme;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Datensatz;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.Hinweis;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBausteinNichtVorhanden;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBausteinVorhanden;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumErsterDesMonats;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumIntervall;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumLogischRichtig;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumNachDatum;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumVorDatum;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils.DateUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBGB;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld Zeitraum-Beginn.
 */
public class PruefungZeitraumBeginn extends AbstractFeldPruefung<FeldNameDBME, FehlerNummerDBME> {

	private static final String AGDEU = "AGDEU";
	private static final String KEINESTORNIERUNG = "N";
	private static final String SEEKK = "99086875";
	private static final String VSTR_031 = "0B";
	private static final int LETZTE_STELLE_JAHR = 8;

	private static final String MELD_027 = "57";
	private static final String MELD_028 = "10";
	private static final String MELD_026 = "124";
	private static final String MELD_045 = "120";
	private static final String MELD_048 = "201";
	private static final String MELD_049 = "40";
	private static final String MELD_051 = "304";
	private static final String MELD_067 = "305";
	private static final String MELD_068 = "306";
	private static final String MELD_071 = "190";
	private static final List<String> MELD_032 = Arrays.asList("55", "56");
	private static final List<String> MELD_035 = Arrays.asList("303", "304");
	private static final List<String> MELD_038 = Arrays.asList("10", "11", "12", "13", "40");
	private static final List<String> MELD_039 = Arrays.asList("1", "2");
	private static final List<String> MELD_040 = Arrays.asList("10", "11", "12", "13", "40", "70", "72");
	private static final List<String> MELD_041_029 = Arrays.asList("109", "110", "202", "209", "210");
	private static final List<String> MELD_042 = Arrays.asList("70", "72");
	private static final List<String> MELD_043 = Arrays.asList("121", "122", "144", "123");
	private static final List<String> MELD_044 = Arrays.asList("54", "91", "55");
	private static final List<String> MELD_046 = Arrays.asList("207", "208");
	private static final List<String> MELD_047 = Arrays.asList("302", "305");
	private static final List<String> MELD_053 = Arrays.asList("103", "142");
	private static final List<String> MELD_055 = Arrays.asList("103", "142");
	private static final List<String> MELD_066 = Arrays.asList("140", "141", "142", "143", "149");

	private static final List<String> VFMM_023 = Arrays.asList("PVTRV", "BWTRV", "BZTRV", "KSTRV", "SOTBF", "UETBF",
			"DSTBF", "ZFTRV", "AGBVD");
	private static final List<String> GD_023 = Arrays.asList("30", "31", "32", "33", "34", "35", "36", "40", "49",
			"50", "51", "52", "53", "54", "55", "57", "71");
	private static final List<String> PERSGR_023 = Arrays.asList("108", "111", "143", "203", "204", "205", "207",
			"208", "209", "210", "301", "302", "303", "304", "305", "306");

	// @formatter:off
	private static final Date DAT_023 = new GregorianCalendar(2008, 11, 31).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_026 = new GregorianCalendar(2011, 11, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_027 = new GregorianCalendar(2007, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_028 = new GregorianCalendar(2005, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_029 = new GregorianCalendar(2003, 2, 31).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_031 = new GregorianCalendar(2004, 11, 31).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_032 = new GregorianCalendar(1999, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_036 = new GregorianCalendar(1973, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_039 = new GregorianCalendar(2003, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_041 = new GregorianCalendar(1999, 3, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_043 = new GregorianCalendar(2012, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_045U = new GregorianCalendar(1999, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_045O = new GregorianCalendar(2002, 11, 31).getTime();// SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_046 = new GregorianCalendar(1995, 3, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_048U = new GregorianCalendar(1997, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_048O = new GregorianCalendar(2003, 02, 31).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_049 = new GregorianCalendar(2003, 3, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_051 = new GregorianCalendar(2002, 7, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_053 = new GregorianCalendar(1989, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_066 = new GregorianCalendar(2008, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_067 = new GregorianCalendar(2007, 11, 18).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_068 = new GregorianCalendar(2011, 11, 13).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_071 = new GregorianCalendar(2010, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	// @formatter:on

	private final transient Feld<FeldNameDBME> feldDBMEKennzSto;
	private final transient Feld<FeldNameDBME> feldDBMEKennzGle;

	private transient Feld<FeldNameDSME> feldDSMEMmKnvSee;
	private transient Feld<FeldNameDSME> feldDSMEBbnrkk;
	private transient Feld<FeldNameDSME> feldDSMEEd;
	private transient Feld<FeldNameDSME> feldDSMEGd;
	private transient Feld<FeldNameDSME> feldDSMEKennzStat;
	private transient Feld<FeldNameDSME> feldDSMEMmuv;
	private transient Feld<FeldNameDSME> feldDSMEPersgr;
	private transient Feld<FeldNameDSME> feldDSMEVsnr;
	private transient Feld<FeldNameDSME> feldDSMEVstr;

	private transient Feld<FeldNameDBGB> feldDBGBGeburtsdatum;

	private final transient Date verarbDatum;
	private final transient String vfmm;
	private final transient Datensatz datensatz;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            das Feld Zeitraum-Beginn
	 * @param feldDBMEKennzSto
	 *            Feld KennzeichenStorno aus dem Baustein DBME
	 * @param feldDBMEKennzGle
	 *            das Feld mit dem Kennzeichen der Gleitzeitreglung aus dem
	 *            Baustein DBME
	 * @param datensatz
	 *            der Datensatz
	 * @param verarbDatum
	 *            das Verarbeitungsdatum
	 * @param vfmm
	 *            der OPCODE VFMM
	 */
	public PruefungZeitraumBeginn(final Feld<FeldNameDBME> feld, final Feld<FeldNameDBME> feldDBMEKennzSto,
			final Feld<FeldNameDBME> feldDBMEKennzGle, final Datensatz datensatz, final Date verarbDatum,
			final String vfmm) {
		super(feld);
		this.datensatz = datensatz;

		this.feldDBMEKennzSto = feldDBMEKennzSto;
		this.feldDBMEKennzGle = feldDBMEKennzGle;

		@SuppressWarnings("unchecked")
		final Baustein<FeldNameDSME> bausteinDSME = (Baustein<FeldNameDSME>) datensatz.getBaustein(BausteinName.DSME);
		if (bausteinDSME != null) {
			this.feldDSMEMmKnvSee = bausteinDSME.getFeld(FeldNameDSME.MM_KNV_SEE);
			this.feldDSMEBbnrkk = bausteinDSME.getFeld(FeldNameDSME.BBNR_KK);
			this.feldDSMEEd = bausteinDSME.getFeld(FeldNameDSME.DATUM_ERSTELLUNG);
			this.feldDSMEGd = bausteinDSME.getFeld(FeldNameDSME.ABGABEGRUND);
			this.feldDSMEKennzStat = bausteinDSME.getFeld(FeldNameDSME.KENNZ_STATUS);
			this.feldDSMEMmuv = bausteinDSME.getFeld(FeldNameDSME.MM_UVDATEN);
			this.feldDSMEPersgr = bausteinDSME.getFeld(FeldNameDSME.PERSONENGRUPPE);
			this.feldDSMEVsnr = bausteinDSME.getFeld(FeldNameDSME.VSNR);
			this.feldDSMEVstr = bausteinDSME.getFeld(FeldNameDSME.VSTR);
		}

		@SuppressWarnings("unchecked")
		final Baustein<FeldNameDBGB> bausteinDBGB = (Baustein<FeldNameDBGB>) datensatz.getBaustein(BausteinName.DBGB);
		if (bausteinDBGB != null) {
			this.feldDBGBGeburtsdatum = bausteinDBGB.getFeld(FeldNameDBGB.GEBURTSDATUM);
		}

		this.verarbDatum = verarbDatum;
		this.vfmm = vfmm;
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
		} else if (this.feldDBGBGeburtsdatum != null) {
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
	@Override 	// SUPPRESS CHECKSTYLE MethodLength
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {
		// @formatter:on

		final String feldGdValue = this.feldDSMEGd.getTrimmedValue();
		final String feldKennzStoValue = this.feldDBMEKennzSto.getTrimmedValue().toUpperCase();

		if (this.isPruefbar(
				Arrays.asList(FehlerNummerDBME.DBME038, FehlerNummerDBME.DBME040, FehlerNummerDBME.DBME027,
						FehlerNummerDBME.DBME028, FehlerNummerDBME.DBME044, FehlerNummerDBME.DBME032,
						FehlerNummerDBME.DBME042), this.feldDSMEGd)) {
			if (MELD_038.contains(feldGdValue)) {
				final Date grenze038 = DateUtils.berechneNeuesDatumMonat(this.verarbDatum, 2, true);

				final PruefungDatumVorDatum me038 = new PruefungDatumVorDatum(this.getFeld(), grenze038);
				this.addPruefungBausteinUebergreifend(me038, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME038));
			}

			if (!MELD_040.contains(feldGdValue)) {
				final Date grenze040 = DateUtils.berechneNeuesDatumMonat(this.verarbDatum, 1, true);

				final PruefungDatumVorDatum me040 = new PruefungDatumVorDatum(this.getFeld(), grenze040);
				this.addPruefungBausteinUebergreifend(me040, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME040));
			}

			if (MELD_027.equals(feldGdValue)) {
				final PruefungDatumNachDatum me027 = new PruefungDatumNachDatum(this.getFeld(), DAT_027);
				this.addPruefungBausteinUebergreifend(me027, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME027));
			}

			if (this.isPruefbar(FehlerNummerDBME.DBME028, this.feldDBMEKennzSto)
					&& (MELD_028.equals(feldGdValue) && KEINESTORNIERUNG.equals(feldKennzStoValue))
					&& StringUtils.isNotBlank(this.feldDSMEKennzStat.getValue())) {
				final PruefungDatumNachDatum me028 = new PruefungDatumNachDatum(this.getFeld(), DAT_028);
				this.addPruefungBausteinUebergreifend(me028, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME028));
			}

			if (MELD_044.contains(feldGdValue)) {
				final PruefungDatumErsterDesMonats me044 = new PruefungDatumErsterDesMonats(this.getFeld());
				this.addPruefungBausteinUebergreifend(me044, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME044));
			}

			if (MELD_032.contains(feldGdValue)) {
				final PruefungDatumNachDatum me032 = new PruefungDatumNachDatum(this.getFeld(), DAT_032);
				this.addPruefungBausteinUebergreifend(me032, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME032));
			}

			if (MELD_042.contains(feldGdValue)) {
				final Date grenze042 = DateUtils.berechneNeuesDatumJahr(this.verarbDatum, 2, true);

				final PruefungDatumVorDatum me042 = new PruefungDatumVorDatum(this.getFeld(), grenze042);
				this.addPruefungBausteinUebergreifend(me042, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME042));
			}
		}

		if (this.isPruefbar(
				Arrays.asList(FehlerNummerDBME.DBME041, FehlerNummerDBME.DBME029, FehlerNummerDBME.DBME043,
						FehlerNummerDBME.DBME026, FehlerNummerDBME.DBME045, FehlerNummerDBME.DBME046,
						FehlerNummerDBME.DBME053), this.feldDSMEPersgr)) {
			final String feldPersgrValue = this.feldDSMEPersgr.getTrimmedValue();
			if (MELD_041_029.contains(feldPersgrValue)) {
				final PruefungDatumNachDatum me041a = new PruefungDatumNachDatum(this.getFeld(), DAT_041);
				this.addPruefungBausteinUebergreifend(me041a, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME041));
			}

			final Date zrbg = DateUtils.parseDate(this.getFeld());
			if (this.isPruefbar(FehlerNummerDBME.DBME029, this.feldDSMEMmKnvSee)
					&& MELD_041_029.contains(feldPersgrValue) && zrbg.after(DAT_029)) {
				final PruefungBausteinNichtVorhanden me029 = new PruefungBausteinNichtVorhanden(this.feldDSMEMmKnvSee,
						this.datensatz, BausteinName.DBKS);
				this.addPruefungBausteinUebergreifend(me029, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME029));
			}

			if (MELD_043.contains(feldPersgrValue)) {
				final PruefungDatumNachDatum me043 = new PruefungDatumNachDatum(this.getFeld(), DAT_043);
				this.addPruefungBausteinUebergreifend(me043, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME043));
			}

			if (MELD_026.equals(feldPersgrValue)) {
				final PruefungDatumNachDatum me026 = new PruefungDatumNachDatum(this.getFeld(), DAT_026);
				this.addPruefungBausteinUebergreifend(me026, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME026));
			}

			if (this.isPruefbar(FehlerNummerDBME.DBME045, this.feldDBMEKennzSto)
					&& KEINESTORNIERUNG.equals(feldKennzStoValue) && MELD_045.equals(feldPersgrValue)) {
				final PruefungDatumIntervall me045 = new PruefungDatumIntervall(this.getFeld(), DAT_045U, DAT_045O);
				this.addPruefungBausteinUebergreifend(me045, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME045));
			}

			if (MELD_046.contains(feldPersgrValue)) {
				final PruefungDatumNachDatum me046 = new PruefungDatumNachDatum(this.getFeld(), DAT_046);
				this.addPruefungBausteinUebergreifend(me046, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME046));
			}

			if (this.isPruefbar(FehlerNummerDBME.DBME053, this.feldDBMEKennzSto) && MELD_053.contains(feldPersgrValue)
					&& KEINESTORNIERUNG.equals(feldKennzStoValue)) {
				final PruefungDatumNachDatum me053 = new PruefungDatumNachDatum(this.getFeld(), DAT_053);
				this.addPruefungBausteinUebergreifend(me053, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME053));
			}

			if (this.isPruefbar(
					Arrays.asList(FehlerNummerDBME.DBME047, FehlerNummerDBME.DBME035, FehlerNummerDBME.DBME055),
					this.feldDBMEKennzSto, this.feldDSMEVsnr)
					&& (this.feldDBGBGeburtsdatum == null || this
							.isPruefbar(Arrays.asList(FehlerNummerDBME.DBME047, FehlerNummerDBME.DBME035,
									FehlerNummerDBME.DBME055), this.feldDBGBGeburtsdatum))) {
				final Date gebDat = this.getGebDat();
				if (gebDat != null) {
					if (MELD_047.contains(feldPersgrValue)) {
						final Date geb17 = DateUtils.berechneDatumGeburtstag(gebDat, 17);
						final PruefungDatumNachDatum me047 = new PruefungDatumNachDatum(this.getFeld(), geb17);
						this.addPruefungBausteinUebergreifend(me047, new Fehler<FehlerNummerDBME>(
								FehlerNummerDBME.DBME047));
					}

					if (MELD_035.contains(feldPersgrValue)) {
						final Date geb16 = DateUtils.berechneDatumGeburtstag(gebDat, 16);
						final PruefungDatumNachDatum me035 = new PruefungDatumNachDatum(this.getFeld(), geb16);
						this.addPruefungBausteinUebergreifend(me035, new Fehler<FehlerNummerDBME>(
								FehlerNummerDBME.DBME035));
					}

					if (KEINESTORNIERUNG.equals(feldKennzStoValue) && MELD_055.contains(feldPersgrValue)) {
						final Date geb55 = DateUtils.berechneDatumGeburtstag(gebDat, 55);
						final PruefungDatumNachDatum me055 = new PruefungDatumNachDatum(this.getFeld(), geb55);
						this.addPruefungBausteinUebergreifend(me055, new Fehler<FehlerNummerDBME>(
								FehlerNummerDBME.DBME055));
					}
				}
			}

			if (MELD_051.equals(feldPersgrValue)) {
				final PruefungDatumNachDatum me051 = new PruefungDatumNachDatum(this.getFeld(), DAT_051);
				this.addPruefungBausteinUebergreifend(me051, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME051));
			}

			if (this.isPruefbar(FehlerNummerDBME.DBME048, this.feldDBMEKennzSto)
					&& KEINESTORNIERUNG.equals(feldKennzStoValue) && MELD_048.equals(feldPersgrValue)) {
				final PruefungDatumIntervall me048 = new PruefungDatumIntervall(this.getFeld(), DAT_048U, DAT_048O);
				this.addPruefungBausteinUebergreifend(me048, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME048));
			}

			if (this.isPruefbar(FehlerNummerDBME.DBME066, this.feldDBMEKennzSto)
					&& KEINESTORNIERUNG.equals(feldKennzStoValue) && MELD_066.contains(feldPersgrValue)) {
				final String bbnrkkValue = this.feldDSMEBbnrkk.getTrimmedValue();
				if (!SEEKK.equals(bbnrkkValue)) {
					final PruefungDatumNachDatum me066 = new PruefungDatumNachDatum(this.getFeld(), DAT_066);
					this.addPruefungBausteinUebergreifend(me066, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME066));
				}
			}

			if (this.isPruefbar(FehlerNummerDBME.DBME023, this.feldDSMEEd)) {
				final String edString = this.feldDSMEEd.getTrimmedValue().substring(0, LETZTE_STELLE_JAHR);
				Date edDate = null;
				if (DateUtils.isLogischKorrektesDatum(edString)) {
					edDate = DateUtils.parseDate(edString);
				}

				if (zrbg.after(DAT_023) && edDate != null && edDate.after(DAT_023)) {
					if (!(VFMM_023.contains(this.vfmm) || !GD_023.contains(feldGdValue) || PERSGR_023
							.contains(feldPersgrValue))) {
						final PruefungBausteinVorhanden me023 = new PruefungBausteinVorhanden(this.feldDSMEMmuv,
								this.datensatz, BausteinName.DBUV);
						this.addPruefungBausteinUebergreifend(me023, new Fehler<FehlerNummerDBME>(
								FehlerNummerDBME.DBME023));
					}
				}
			}

			if (MELD_067.equals(feldPersgrValue)) {
				final PruefungDatumNachDatum me067 = new PruefungDatumNachDatum(this.getFeld(), DAT_067);
				this.addPruefungBausteinUebergreifend(me067, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME067));
			}

			if (MELD_071.equals(feldPersgrValue)) {
				final PruefungDatumNachDatum me071 = new PruefungDatumNachDatum(this.getFeld(), DAT_071);
				this.addPruefungBausteinUebergreifend(me071, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME071));
			}

			if (MELD_068.equals(feldPersgrValue)) {
				final PruefungDatumNachDatum me068 = new PruefungDatumNachDatum(this.getFeld(), DAT_068);
				this.addPruefungBausteinUebergreifend(me068, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME068));
			}
		}

		if (this.isPruefbar(Arrays.asList(FehlerNummerDBME.DBME031, FehlerNummerDBME.DBME049), this.feldDSMEVstr,
				this.feldDBMEKennzSto, this.feldDSMEGd)) {
			final String feldVstrValue = this.feldDSMEVstr.getTrimmedValue().toUpperCase();
			if (KEINESTORNIERUNG.equals(feldKennzStoValue) && VSTR_031.equals(feldVstrValue)) {
				final PruefungDatumVorDatum me031 = new PruefungDatumVorDatum(this.getFeld(), DAT_031);
				this.addPruefungBausteinUebergreifend(me031, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME031));
			}

			if (MELD_049.equals(feldGdValue) && this.feldDSMEVsnr != null
					&& StringUtils.isBlank(this.feldDSMEVsnr.getValue())) {
				final PruefungDatumNachDatum me049 = new PruefungDatumNachDatum(this.getFeld(), DAT_049);
				this.addPruefungBausteinUebergreifend(me049, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME049));
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
		if (this.isPruefbar(FehlerNummerDBME.DBME039, this.feldDBMEKennzSto, this.feldDBMEKennzGle)
				&& KEINESTORNIERUNG.equals(this.feldDBMEKennzSto.getTrimmedValue())
				&& MELD_039.contains(this.feldDBMEKennzGle.getTrimmedValue())) {
			final PruefungDatumNachDatum me039 = new PruefungDatumNachDatum(this.getFeld(), DAT_039);
			this.addPruefungFeldUebergreifend(me039, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME039));
		}
	}

	/**
	 * Initialisiere die feldinternen Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {

		final PruefungNichtLeer me030a = new PruefungNichtLeer(this.getFeld());
		this.addPruefung(me030a, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME030));

		final PruefungNumerisch me030b = new PruefungNumerisch(this.getFeld(), true);
		this.addPruefung(me030b, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME030));

		final PruefungDatumLogischRichtig me034 = new PruefungDatumLogischRichtig(this.getFeld());
		this.addPruefung(me034, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME034));

		if (DateUtils.isLogischKorrektesDatum(this.getFeld(), new SimpleDateFormat("yyyyMMdd", Locale.GERMANY))) {

			final PruefungDatumNachDatum me036 = new PruefungDatumNachDatum(this.getFeld(), DAT_036);
			this.addPruefung(me036, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME036));

			if (StringUtils.isNotBlank(this.vfmm) && AGDEU.equals(this.vfmm)) {
				// TODO Damit Java wie Cobol läuft, darf hier nicht vom Jahresende der Verarbeitungsdatum ausgegangen werden
				final Date grenzeH10 = DateUtils.berechneNeuesDatumJahr(this.verarbDatum, -5, false);

				final PruefungDatumNachDatum meH10 = new PruefungDatumNachDatum(this.getFeld(), grenzeH10);
				this.addPruefung(meH10, new Hinweis<FehlerNummerDBME>(FehlerNummerDBME.DBMEH10));
			}
		}
	}
}
