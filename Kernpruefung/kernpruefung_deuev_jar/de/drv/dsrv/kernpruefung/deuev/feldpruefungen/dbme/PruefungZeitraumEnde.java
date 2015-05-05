package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbme;

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
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBausteinNichtVorhanden;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumEndeDesJahres;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumGleicherMonat;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumGleichesJahr;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumLetzterDesMonats;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumLogischRichtig;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumNachDatum;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumVorDatum;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLeerNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNotInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils.DateUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld Zeitraum-Ende.
 */
public class PruefungZeitraumEnde extends AbstractFeldPruefung<FeldNameDBME, FehlerNummerDBME> {
	
	private static final String KEINESTORNIERUNG = "N";
	
	private static final String MELD_037 = "201";
	private static final String MELD_060 = "49";
	private static final String MELD_065_PG = "210";
	private static final String MELD_064 = "304";
	
	private static final List<String> MELD_054 = Arrays.asList("10", "11", "12", "13");
	private static final List<String> MELD_033 = Arrays.asList("109", "110", "202", "209", "210");
	private static final List<String> MELD_065_GD = Arrays.asList("50", "51", "52", "53", "54");
	private static final List<String> MELD_056 = Arrays.asList("10", "11", "12", "13");
	private static final List<String> MELD_058 = Arrays.asList("70", "72");
	private static final List<String> MELD_061_GD = Arrays.asList("50", "70");
	private static final List<String> MELD_061_VU = Arrays.asList("01085914", "28180427");
	private static final List<String> MELD_062 = Arrays.asList("54", "55", "91");
	private static final List<String> MELD_063 = Arrays.asList("54", "55", "91");
	private static final List<String> MELD_069 = Arrays.asList("1", "2");
	
	// @formatter:off
	private static final Date DAT_033 = new GregorianCalendar(2003, 2, 31).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_037 = new GregorianCalendar(2003, 2, 31).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_065 = new GregorianCalendar(1999, 2, 31).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_064 = new GregorianCalendar(2004, 11, 31).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_069 = new GregorianCalendar(2003, 3, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	// @formatter:on
	
	private final transient Feld<FeldNameDBME> feldDBMEZb;
	private final transient Feld<FeldNameDBME> feldDBMEKennzSto;
	private final transient Feld<FeldNameDBME> feldDBMEKennzGle;
	private transient Feld<FeldNameDSME> feldDSMEMmKnvSee;
	private transient Feld<FeldNameDSME> feldDSMEBbnrvu;
	private transient Feld<FeldNameDSME> feldDSMEGd;
	private transient Feld<FeldNameDSME> feldDSMEPersgr;
	
	private final transient Date verarbDatum;
	private final transient Datensatz datensatz;
	
	/**
	 * Konstruktor.
	 * 
	 * @param feldDBMEZe
	 *            das Feld Zeitraum-Ende
	 * @param feldDBMEZb
	 *            das Feld Zeitraum-Beginn aus dem Baustein DBME
	 * @param feldDBMEKennzSto
	 *            das Feld KennzeichenStorno aus dem Baustein DBME
	 * @param feldDBMEKennzGle
	 *            das Feld mit dem Kennzeichen der Gleitzeitreglung aus dem
	 *            Baustein DBME
	 * @param verarbDatum
	 *            das Verarbeitungsdatum
	 * @param datensatz
	 *            der Datensatz
	 */
	public PruefungZeitraumEnde(final Feld<FeldNameDBME> feldDBMEZe, final Feld<FeldNameDBME> feldDBMEZb,
			final Feld<FeldNameDBME> feldDBMEKennzSto, final Feld<FeldNameDBME> feldDBMEKennzGle,
			final Date verarbDatum, final Datensatz datensatz) {
		super(feldDBMEZe);
		this.feldDBMEZb = feldDBMEZb;
		this.feldDBMEKennzSto = feldDBMEKennzSto;
		this.feldDBMEKennzGle = feldDBMEKennzGle;
		
		@SuppressWarnings("unchecked")
		final Baustein<FeldNameDSME> bausteinDSME = (Baustein<FeldNameDSME>) datensatz.getBaustein(BausteinName.DSME);
		if (bausteinDSME != null) {
			this.feldDSMEMmKnvSee = bausteinDSME.getFeld(FeldNameDSME.MM_KNV_SEE);
			this.feldDSMEBbnrvu = bausteinDSME.getFeld(FeldNameDSME.BBNR_VU);
			this.feldDSMEGd = bausteinDSME.getFeld(FeldNameDSME.ABGABEGRUND);
			this.feldDSMEPersgr = bausteinDSME.getFeld(FeldNameDSME.PERSONENGRUPPE);
		}
		
		this.verarbDatum = verarbDatum;
		this.datensatz = datensatz;
	}
	
	/**
	 * Fuegt die Pruefungen fuer Meldungen ungleich Anmeldungen hinzu. Alle
	 * Pruefungen sind bausteinuebergreifend.
	 * 
	 * @param feldPersgrValue
	 *            der Wert des Feldes Personengruppe (Baustein DSME)
	 * @throws UngueltigeDatenException
	 */
	private void addPruefungenUngleichAnmeldung() throws UngueltigeDatenException {
		
		if (this.isPruefbar(Arrays.asList(FehlerNummerDBME.DBME056, FehlerNummerDBME.DBME057), this.feldDBMEZb)) {
			final PruefungDatumNachDatum me056 = new PruefungDatumNachDatum(this.getFeld(), this.feldDBMEZb);
			this.addPruefungBausteinUebergreifend(me056, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME056));
			
			final PruefungDatumGleichesJahr me057 = new PruefungDatumGleichesJahr(this.getFeld(), this.feldDBMEZb);
			this.addPruefungBausteinUebergreifend(me057, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME057));
		}
		
		final String feldDSMEGdValue = this.feldDSMEGd.getTrimmedValue();
		if (MELD_058.contains(feldDSMEGdValue)) {
			final Date grenze058 = DateUtils.berechneNeuesDatumJahr(this.verarbDatum, 2, true);
			final PruefungDatumVorDatum me058 = new PruefungDatumVorDatum(this.getFeld(), grenze058);
			this.addPruefungBausteinUebergreifend(me058, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME058));
		} else {
			final Date grenze059 = DateUtils.berechneNeuesDatumMonat(this.verarbDatum, 1, true);
			final PruefungDatumVorDatum me059 = new PruefungDatumVorDatum(this.getFeld(), grenze059);
			this.addPruefungBausteinUebergreifend(me059, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME059));
		}
		
		if (MELD_060.equals(feldDSMEGdValue)) {
			final PruefungDatumVorDatum me060 = new PruefungDatumVorDatum(this.getFeld(), this.verarbDatum);
			this.addPruefungBausteinUebergreifend(me060, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME060));
		}
		
		if (this.isPruefbar(FehlerNummerDBME.DBME061, this.feldDSMEBbnrvu) && MELD_061_GD.contains(feldDSMEGdValue)
				&& !MELD_061_VU.contains(this.feldDSMEBbnrvu.getTrimmedValue())) {
			final PruefungDatumEndeDesJahres me061 = new PruefungDatumEndeDesJahres(this.getFeld());
			this.addPruefungBausteinUebergreifend(me061, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME061));
		}
		
		if (this.isPruefbar(FehlerNummerDBME.DBME062, this.feldDBMEZb) && MELD_062.contains(feldDSMEGdValue)) {
			final PruefungDatumGleicherMonat me062 = new PruefungDatumGleicherMonat(this.getFeld(), this.feldDBMEZb);
			this.addPruefungBausteinUebergreifend(me062, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME062));
		}
		
		if (MELD_063.contains(feldDSMEGdValue)) {
			final PruefungDatumLetzterDesMonats me063 = new PruefungDatumLetzterDesMonats(this.getFeld());
			this.addPruefungBausteinUebergreifend(me063, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME063));
		}
	}
	
	/**
	 * Initialisiere die bausteinuebergreifenden Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {
		
		if (this.isPruefbar(Arrays.asList(FehlerNummerDBME.DBME052, FehlerNummerDBME.DBME054), this.feldDSMEGd)) {
			if (MELD_054.contains(this.feldDSMEGd.getTrimmedValue())) {
				final PruefungLeerNumerisch me054 = new PruefungLeerNumerisch(this.getFeld());
				this.addPruefungBausteinUebergreifend(me054, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME054));
			} else {
				final PruefungDatumLogischRichtig me034 = new PruefungDatumLogischRichtig(this.getFeld());
				this.addPruefungBausteinUebergreifend(me034, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME052));
			}
		}
		
		final boolean zrenIsDatum = DateUtils.isLogischKorrektesDatum(this.getFeld(), new SimpleDateFormat("yyyyMMdd",
				Locale.GERMANY));
		
		if (this.isPruefbar(Arrays.asList(FehlerNummerDBME.DBME033, FehlerNummerDBME.DBME037, FehlerNummerDBME.DBME065,
				FehlerNummerDBME.DBME064), this.feldDSMEPersgr)) {
			if (zrenIsDatum) {
				final String feldPersgrValue = StringUtils.stripToEmpty(this.feldDSMEPersgr.getTrimmedValue());
				final Date zrenDate = DateUtils.parseDate(this.getFeld());
				
				if (this.isPruefbar(FehlerNummerDBME.DBME033, this.feldDSMEMmKnvSee)
						&& MELD_033.contains(feldPersgrValue) && zrenDate.after(DAT_033)) {
					final PruefungBausteinNichtVorhanden me033 = new PruefungBausteinNichtVorhanden(this.feldDSMEMmKnvSee,
							this.datensatz, BausteinName.DBKS);
					this.addPruefungBausteinUebergreifend(me033, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME033));
				}
				
				if (this.isPruefbar(FehlerNummerDBME.DBME037, this.feldDBMEKennzSto)
						&& KEINESTORNIERUNG.equals(this.feldDBMEKennzSto.getTrimmedValue())
						&& MELD_037.equals(feldPersgrValue)) {
					final PruefungDatumVorDatum me037 = new PruefungDatumVorDatum(this.getFeld(), DAT_037);
					this.addPruefungBausteinUebergreifend(me037, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME037));
				}
				
				if (this.isPruefbar(FehlerNummerDBME.DBME065, this.feldDSMEGd) && MELD_065_PG.equals(feldPersgrValue)
						&& zrenDate.after(DAT_065)) {
					final PruefungNotInList me065 = new PruefungNotInList(MELD_065_GD, this.feldDSMEGd);
					this.addPruefungBausteinUebergreifend(me065, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME065));
				}
				
				if (this.isPruefbar(Arrays.asList(FehlerNummerDBME.DBME056,
						FehlerNummerDBME.DBME057, FehlerNummerDBME.DBME058, FehlerNummerDBME.DBME059,
						FehlerNummerDBME.DBME060, FehlerNummerDBME.DBME061, FehlerNummerDBME.DBME062,
						FehlerNummerDBME.DBME063), this.feldDSMEGd)
						&& !MELD_056.contains(this.feldDSMEGd.getTrimmedValue())) {
					this.addPruefungenUngleichAnmeldung();
				}
				
				if (MELD_064.equals(feldPersgrValue)) {
					final PruefungDatumVorDatum me064 = new PruefungDatumVorDatum(this.getFeld(), DAT_064);
					this.addPruefungBausteinUebergreifend(me064, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME064));
				}
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
		if (this.isPruefbar(FehlerNummerDBME.DBME069, this.feldDBMEZb, this.feldDBMEKennzGle, this.feldDBMEKennzSto)) {
			final boolean zrenIsDatum = DateUtils.isLogischKorrektesDatum(this.getFeld(), new SimpleDateFormat("yyyyMMdd",
					Locale.GERMANY));
			
			if (zrenIsDatum
					&& MELD_069.contains(this.feldDBMEKennzGle.getTrimmedValue())
					&& KEINESTORNIERUNG.equals(StringUtils.stripToEmpty(this.feldDBMEKennzSto.getTrimmedValue())
							.toUpperCase())) {
				final PruefungDatumNachDatum me069 = new PruefungDatumNachDatum(this.getFeld(), DAT_069);
				this.addPruefungFeldUebergreifend(me069, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME069));
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
		final PruefungNichtLeer me050a = new PruefungNichtLeer(this.getFeld());
		this.addPruefung(me050a, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME050));
		
		final PruefungNumerisch me050b = new PruefungNumerisch(this.getFeld(), true);
		this.addPruefung(me050b, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME050));
	}
}
