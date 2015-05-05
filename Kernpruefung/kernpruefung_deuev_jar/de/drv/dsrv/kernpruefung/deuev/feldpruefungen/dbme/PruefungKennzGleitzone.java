package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbme;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumNachDatum;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungGleicherString;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNotInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld Kennzeichen Gleitzone.
 */
public class PruefungKennzGleitzone extends AbstractFeldPruefung<FeldNameDBME, FehlerNummerDBME> {
	
	private static final String KEINE_GLZREGELUNG = "0";
	private static final String STORNIERUNG = "J";
	private static final String KEINE_STORNIERUNG = "N";
	
	private static final List<String> GLZ_UEBERGANG = Arrays.asList("J", "N");
	private static final List<String> ERLBT_ZEICHEN_020 = Arrays.asList("", "J", "N", "0", "1", "2");
	private static final List<String> MELD_021 = Arrays.asList("10", "11", "12", "13");
	private static final List<String> MELD_022 = Arrays.asList("AGDEU", "KVDEU", "KVTRV", "RVTKV");
	private static final List<String> UNZ_MELD_024 = Arrays.asList("1", "2");
	private static final List<String> ZUL_MELD_024 = Arrays.asList("102", "121", "122", "123", "103", "105", "107",
			"109", "110", "111", "127", "141", "144", "142", "143", "202", "209", "210", "203", "207", "208");
	private static final List<String> MELD_025 = Arrays.asList("301", "302", "303", "304", "305", "306");
	
	// @formatter:off
	private static final Date AUSNAHME_UEBERG_DAT = new GregorianCalendar(2007, 0, 1).getTime();  // SUPPRESS CHECKSTYLE MagicNumber
	// @formatter:on
	private static final String UBERGANGS_DATUM = "20061231";
	
	private final transient Feld<FeldNameDBME> feldDBMEKennzSto;
	private transient Feld<FeldNameDSME> feldDSMEPersgr;
	private transient Feld<FeldNameDSME> feldDSMEGd;
	private final transient String vfmm;
	private final transient Date verarbDat;
	
	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            das Feld mit dem Kennzeichen der Gleitzeitreglung
	 * @param feldDBMEKennzSto
	 *            das Feld KennzeichenStorno aus dem Baustein DBME
	 * @param bausteinDSME
	 *            der Baustein DSME
	 * @param vfmm
	 *            der OPCODE VFMM
	 * @param verarbDat
	 *            das Verarbeitungsdatum
	 */
	public PruefungKennzGleitzone(final Feld<FeldNameDBME> feld, final Feld<FeldNameDBME> feldDBMEKennzSto,
			final Baustein<FeldNameDSME> bausteinDSME, final String vfmm, final Date verarbDat) {
		super(feld);
		this.feldDBMEKennzSto = feldDBMEKennzSto;
		if (bausteinDSME != null) {
			this.feldDSMEGd = bausteinDSME.getFeld(FeldNameDSME.ABGABEGRUND);
			this.feldDSMEPersgr = bausteinDSME.getFeld(FeldNameDSME.PERSONENGRUPPE);
		}
		this.vfmm = vfmm;
		this.verarbDat = verarbDat;
	}
	
	/**
	 * Initialisiere die bausteinuebergreifenden Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {
		final String kennzGlValue = StringUtils.stripToEmpty(this.getFeld().getTrimmedValue()).toUpperCase();
		
		if (this.isPruefbar(FehlerNummerDBME.DBME021, this.feldDBMEKennzSto, this.feldDSMEGd)) {
			final String kennzStValue = StringUtils.stripToEmpty(this.feldDBMEKennzSto.getTrimmedValue()).toUpperCase();
			if (kennzGlValue.isEmpty() && !(STORNIERUNG.equals(kennzStValue))) {
				final PruefungInList me021 = new PruefungInList(MELD_021, this.feldDSMEGd, true);
				this.addPruefungBausteinUebergreifend(me021, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME021));
			}
		}
		
		if (this.isPruefbar(Arrays.asList(FehlerNummerDBME.DBME024, FehlerNummerDBME.DBME025), this.feldDSMEPersgr)) {
			final String persgrValue = this.feldDSMEPersgr.getTrimmedValue();
			if (MELD_025.contains(persgrValue)) {
				final PruefungGleicherString me025 = new PruefungGleicherString(this.getFeld(), KEINE_GLZREGELUNG);
				this.addPruefungBausteinUebergreifend(me025, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME025));
			}
			
			if (ZUL_MELD_024.contains(persgrValue)) {
				final PruefungNotInList me024 = new PruefungNotInList(UNZ_MELD_024, this.getFeld(), true);
				this.addPruefungBausteinUebergreifend(me024, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME024));
			}
		}
	}
	
	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {
		final String kennzGlValue = StringUtils.stripToEmpty(this.getFeld().getTrimmedValue()).toUpperCase();
		if (this.isPruefbar(FehlerNummerDBME.DBME022, this.feldDBMEKennzSto) && GLZ_UEBERGANG.contains(kennzGlValue)
				&& MELD_022.contains(this.vfmm)
				&& KEINE_STORNIERUNG.equals(this.feldDBMEKennzSto.getTrimmedValue())) {
			
			final PruefungDatumNachDatum me022 = new PruefungDatumNachDatum(new Feld<FeldNameDBME>(UBERGANGS_DATUM),
					this.verarbDat);
			this.addPruefungFeldUebergreifend(me022, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME022));
			
			// wenn Ausnahme zutrifft, Feld-Wert anpassen
			final String kennzStValue = StringUtils.stripToEmpty(this.feldDBMEKennzSto.getTrimmedValue()).toUpperCase();
			if (this.isAusnahmeUebergang(kennzStValue)) {
				this.getFeld().setValue(KEINE_GLZREGELUNG);
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
		
		final PruefungInList me020 = new PruefungInList(ERLBT_ZEICHEN_020, this.getFeld(), true);
		this.addPruefung(me020, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME020));
	}
	
	private boolean isAusnahmeUebergang(final String kennzStValue) {
		return KEINE_STORNIERUNG.equals(kennzStValue) && MELD_022.contains(this.vfmm)
				&& this.verarbDat.before(AUSNAHME_UEBERG_DAT);
	}
}
