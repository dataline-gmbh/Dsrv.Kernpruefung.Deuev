package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbme;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumGleicherMonat;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungFuegeFehlerHinzu;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungKleinerGleich;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLeerNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtGleicherString;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeerNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.basis.wertelisten.WertelistenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils.DateUtils;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils.EntgeltUtil;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld Entgelt.
 */
public class PruefungEntgelt extends AbstractFeldPruefung<FeldNameDBME, FehlerNummerDBME> {

	private static final int TAGE_JAHR = 360;
	private static final int TAGE_ZWEI_MONATE = 60;
	private static final int TAGE_DREI_MONATE = 90;
	private static final int TSATZ_DM = 21;
	private static final int TSATZ_E_2003 = 14;
	private static final int TSATZ_E_VOR_2003 = 11;
	private static final int TSATZ_E_AB_2013 = 15;
	private static final String DMARK = "D";
	private static final String EURO = "E";
	private static final String ZERO = "0";
	private static final String EINS = "000001";
	private static final String STORNIERUNG = "J";

	private static final String MELD_091 = "302";
	private static final List<String> MELD_092_GD = Arrays.asList("10", "11", "12", "13", "91", "94", "95");
	private static final List<String> MELD_092_PERSGR = Arrays.asList("110", "190", "202", "210", "301", "303", "304", "306");
	private static final String MELD_093_P_SEE = "140";
	private static final String MELD_093_S_SEE = "000";
	private static final String MELD_093_BYGR_SEE = "0000";
	private static final List<String> MELD_093_GD = Arrays.asList("51", "52", "53");
	private static final List<String> MELD_093_094_P = Arrays.asList("110", "120", "190", "203", "202", "210");
	private static final List<String> MELD_094_GD = Arrays.asList("50", "51", "52", "53", "59", "70");
	private static final String MELD_96_GD = "55";

	private static final List<String> MELD_98_P = Arrays.asList("207", "208");
	private static final List<String> MELD_100_P = Arrays.asList("201", "209");
	private static final String MELD_102_P = "305";
	private static final List<String> MELD_105_P = Arrays.asList("109", "209");

	private static final List<String> VFMM_097 = Arrays.asList("AGDEU", "WLTKV", "KVTWL");

	private static final List<String> NICHT_ERLAUBTE_WERTE_PERSGR_DBME101 = Arrays.asList("110");

	// @formatter:off
	private static final Date DAT_091 = new GregorianCalendar(1990, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_100 = new GregorianCalendar(2003, 3, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_105 = new GregorianCalendar(2003, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_105_B = new GregorianCalendar(2013, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_105_B_2015 = new GregorianCalendar(2015, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_105_E_2018 = new GregorianCalendar(2018, 11, 31).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	// @formatter:on

	private final transient Feld<FeldNameDBME> feldDBMEBygr;
	private final transient Feld<FeldNameDBME> feldDBMEKennzrk;
	private final transient Feld<FeldNameDBME> feldDBMEZrbg;
	private final transient Feld<FeldNameDBME> feldDBMEZren;
	private final transient Feld<FeldNameDBME> feldDBMEKennzSto;
	private final transient Feld<FeldNameDBME> feldDBMEWg;
	private final transient String vfmm;
	private transient Feld<FeldNameDSME> feldDSMEbbnrvu;
	private transient Feld<FeldNameDSME> feldDSMEPersgr;
	private transient Feld<FeldNameDSME> feldDSMEGd;
	private transient Feld<FeldNameDSME> feldDSMESasc;
	private transient Feld<FeldNameDSME> feldDSMEVstr;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem Entgelt
	 * @param feldDBMEBygr
	 *            das Feld Beitrags-Gruppe aus dem Baustein DBME
	 * @param feldDBMEKennzrk
	 *            das Feld Kennz-Rechtskreis aus dem Baustein DBME
	 * @param feldDBMEZrbg
	 *            das Feld Zeitraum-Beginn aus dem Baustein DBME
	 * @param feldDBMEZren
	 *            das Feld Zeitraum-Ende aus dem Baustein DBME
	 * @param feldDBMEKennzSto
	 *            das Feld KennzeichenStorno aus dem Baustein DBME
	 * @param feldDBMEWg
	 *            das Feld Waehrungskennzeichen aus dem Baustein DBME
	 * @param bausteinDSME
	 * @param vfmm
	 *            der OPCODE VFMM
	 */
	// @formatter:off
	public PruefungEntgelt(final Feld<FeldNameDBME> feld,
			final Feld<FeldNameDBME> feldDBMEBygr, // SUPPRESS CHECKSTYLE ParameterNumber
			final Feld<FeldNameDBME> feldDBMEKennzrk, final Feld<FeldNameDBME> feldDBMEZrbg, final Feld<FeldNameDBME> feldDBMEZren, final Feld<FeldNameDBME> feldDBMEKennzSto, final Feld<FeldNameDBME> feldDBMEWg,
			final Baustein<FeldNameDSME> bausteinDSME, final String vfmm) {
		// @formatter:on
		super(feld);
		this.feldDBMEBygr = feldDBMEBygr;
		this.feldDBMEKennzrk = feldDBMEKennzrk;
		this.feldDBMEZrbg = feldDBMEZrbg;
		this.feldDBMEZren = feldDBMEZren;
		this.feldDBMEKennzSto = feldDBMEKennzSto;
		this.feldDBMEWg = feldDBMEWg;
		if (bausteinDSME != null) {
			this.feldDSMEbbnrvu = bausteinDSME.getFeld(FeldNameDSME.BBNR_VU);
			this.feldDSMEPersgr = bausteinDSME.getFeld(FeldNameDSME.PERSONENGRUPPE);
			this.feldDSMEGd = bausteinDSME.getFeld(FeldNameDSME.ABGABEGRUND);
			this.feldDSMESasc = bausteinDSME.getFeld(FeldNameDSME.STAATSANGEHOERIGKEITS_SC);
			this.feldDSMEVstr = bausteinDSME.getFeld(FeldNameDSME.VSTR);
		}
		this.vfmm = vfmm;
	}

	/**
	 * Fuegt die allgemeine Pruefung auf Ueberschreitung der Beitragsbemessungsgrenze hinzu (DBME096).
	 * 
	 * @param anteiligeBBG
	 *            die anteilige Beitragsbessungsgrenze (Entgeltgrenze)
	 */
	private void addAllgemeineEntgeltPruefung(final Double anteiligeBBG) {
		final Double zuschlag = Double.valueOf(1.033333);
		final Integer geltendeBezugsgr = this.getAufgerundet(anteiligeBBG * zuschlag);

		final PruefungKleinerGleich me096 = new PruefungKleinerGleich(this.getFeld(), geltendeBezugsgr.doubleValue());
		this.addPruefungBausteinUebergreifend(me096, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME096));
	}

	/**
	 * Fuegt die, je nach Waehrung zutreffende, Pruefung fuer die Personen aus Feld MELD_100_P hinzu.
	 * 
	 * @param anteiligeBBG
	 *            die anteilige Beitragsbemessungsgrenze
	 * @param umfassendeMonate
	 *            die umfassenden Monate
	 */
	private void addPruefungenMELD100P(final String persgrValue, final Double anteiligeBBG, final int umfassendeMonate) {
		if (this.isMeldungInEuro()) {
			final Integer entgeltGrenzeEuro = 767 * umfassendeMonate;
			final PruefungKleinerGleich me100euro = new PruefungKleinerGleich(this.getFeld(), Double.valueOf(entgeltGrenzeEuro));
			this.addPruefungBausteinUebergreifend(me100euro, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME100));
		} else if (this.isMeldungInDM()) {
			final Integer entgeltGrenzeDM = 1500 * umfassendeMonate;
			final PruefungKleinerGleich me100dm = new PruefungKleinerGleich(this.getFeld(), Double.valueOf(entgeltGrenzeDM));
			this.addPruefungBausteinUebergreifend(me100dm, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME100));
		}
	}

	/**
	 * Berechne die umfassten Monate (Monate vom ZRGB bis ZREN).
	 * 
	 * @param zrbgDate
	 *            das Datum des Feldes ZeitraumBeginn (zrbg)
	 * @param zrenDate
	 *            das Datum des Feldes ZeitraumEnde (zren)
	 * @return Anzahl volle Monate
	 */
	private int berechneMonate(final Date zrbgDate, final Date zrenDate) {
		final Calendar zrbgCal = new GregorianCalendar(Locale.GERMANY);
		zrbgCal.setTime(zrbgDate);
		final Calendar zrenCal = new GregorianCalendar(Locale.GERMANY);
		zrenCal.setTime(zrenDate);

		return zrenCal.get(Calendar.MONTH) - zrbgCal.get(Calendar.MONTH) + 1;
	}

	/**
	 * Berechne die auf den Zeitraum umgerechnete, anteilige Beitragsbemessungsgrenze.
	 * 
	 * @return die anteilige Beitragsbemessungsgrenze
	 * @throws UngueltigeDatenException
	 * @throws WertelistenException
	 */
	private Double getAnteiligeBeitragsbemessungsgrenze() throws UngueltigeDatenException, WertelistenException {
		return EntgeltUtil.getInstance().getAnteiligeBeitrBemGr(this.feldDSMEPersgr, this.feldDBMEZrbg, this.feldDBMEZren, this.feldDBMEWg, this.feldDBMEKennzrk, this.feldDSMEbbnrvu, this.feldDSMEVstr);
	}

	/**
	 * Berechne die auf den Zeitraum umgerechnete anteilige Bezugsgroesse.
	 * 
	 * @return die anteilige Bezugsgroesse
	 * @throws UngueltigeDatenException
	 * @throws WertelistenException
	 */
	private Double getAnteiligeBezugsgroesse() throws UngueltigeDatenException, WertelistenException {
		return EntgeltUtil.getInstance().getAnteiligeBezGr(this.feldDSMEPersgr, this.feldDBMEZrbg, this.feldDBMEZren, this.feldDBMEWg, this.feldDBMEKennzrk, this.feldDSMEVstr);
	}

	/**
	 * Gibt einen (stets) aufgerundeten Wert zurueck.
	 * 
	 * @param wert
	 *            der Ausgangswert
	 * @return aufgerundeter Wert
	 */
	private int getAufgerundet(final Double wert) {
		return (int) Math.ceil(wert);
	}

	/**
	 * Berechnet die Bezugsgroesse fuer geringfuegig Beschaeftigte bis 2015 (unter Beruecksichtigung der Rahmenbedingungen aus Entscheidungstabelle
	 * ab770000_PY-Hoechstbetrag-geringfuegig-Beschaeftigte.doc).
	 * 
	 * @param zrbgDate
	 *            der Zeitraum Beginn (zrbg)
	 * @return die Bezugsgroesse fuer geringfuegig Beschaeftigte
	 * @throws UngueltigeDatenException
	 * @throws WertelistenException
	 */
	private Integer getBezugsgrGeringBeschaeftigteBis2015(final Date zrbgDate) throws UngueltigeDatenException, WertelistenException {
		Integer geltendeBezugsgr = null;

		final Double jahresBBG = this.getJahresBbg();
		final int anzahlTage = EntgeltUtil.getInstance().getAnzahlTage(this.feldDSMEPersgr, this.feldDBMEZrbg, this.feldDBMEZren);
		final Double zweiMonatsGrenze = jahresBBG * TAGE_ZWEI_MONATE / TAGE_JAHR;

		if (anzahlTage > TAGE_ZWEI_MONATE) {
			final int anzahlRestlTage = anzahlTage - TAGE_ZWEI_MONATE;
			if (this.isMeldungInEuro()) {
				if (zrbgDate.before(DAT_105)) {
					geltendeBezugsgr = this.getAufgerundet(zweiMonatsGrenze + anzahlRestlTage * TSATZ_E_VOR_2003);
				} else if (zrbgDate.before(DAT_105_B)) {
					geltendeBezugsgr = this.getAufgerundet(zweiMonatsGrenze + anzahlRestlTage * TSATZ_E_2003);
				} else {
					geltendeBezugsgr = this.getAufgerundet(zweiMonatsGrenze + anzahlRestlTage * TSATZ_E_AB_2013);
				}
			} else if (this.isMeldungInDM()) {
				geltendeBezugsgr = this.getAufgerundet(zweiMonatsGrenze + anzahlRestlTage * TSATZ_DM);
			}
		} else {
			geltendeBezugsgr = this.getAufgerundet(zweiMonatsGrenze);
		}

		return geltendeBezugsgr;
	}

	/**
	 * Berechnet die Bezugsgroesse fuer geringfuegig Beschaeftigte ab 2015 (unter Beruecksichtigung der Rahmenbedingungen aus Entscheidungstabelle
	 * ab770000_PY-Hoechstbetrag-geringfuegig-Beschaeftigte.doc).
	 * 
	 * @param zrbgDate
	 *            der Zeitraum Beginn (zrbg)
	 * @return die Bezugsgroesse fuer geringfuegig Beschaeftigte
	 * @throws UngueltigeDatenException
	 * @throws WertelistenException
	 */
	private Integer getBezugsgrGeringBeschaeftigteAb2015(final Date zrbgDate) throws UngueltigeDatenException, WertelistenException {
		Integer geltendeBezugsgr = null;

		final Double jahresBBG = this.getJahresBbg();
		final int anzahlTage = EntgeltUtil.getInstance().getAnzahlTage(this.feldDSMEPersgr, this.feldDBMEZrbg, this.feldDBMEZren);
		final Double dreiMonatsGrenze = jahresBBG * TAGE_DREI_MONATE / TAGE_JAHR;

		if (anzahlTage > TAGE_DREI_MONATE) {
			final int anzahlRestlTage = anzahlTage - TAGE_DREI_MONATE;
			if (this.isMeldungInEuro()) {
				geltendeBezugsgr = this.getAufgerundet(dreiMonatsGrenze + anzahlRestlTage * TSATZ_E_AB_2013);
			} else if (this.isMeldungInDM()) {
				geltendeBezugsgr = this.getAufgerundet(dreiMonatsGrenze + anzahlRestlTage * TSATZ_DM);
			}
		} else {
			geltendeBezugsgr = this.getAufgerundet(dreiMonatsGrenze);
		}

		return geltendeBezugsgr;
	}

	/**
	 * Berechnet die Jahres-Beitragsbemessungsgrenze.
	 * 
	 * @return die Jahres-Beitragsbemessungsgrenze
	 * @throws UngueltigeDatenException
	 * @throws WertelistenException
	 */
	private Double getJahresBbg() throws UngueltigeDatenException, WertelistenException {
		return EntgeltUtil.getInstance().getJahresBeitrBemGr(this.feldDBMEZrbg, this.feldDBMEWg, this.feldDBMEKennzrk, this.feldDSMEbbnrvu, this.feldDSMEVstr);
	}

	/**
	 * Initialisiere die bausteinuebergreifenden Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {

		final String persgrValue = this.feldDSMEPersgr.getTrimmedValue();

		if (this.isPruefbar(FehlerNummerDBME.DBME092, this.feldDSMEPersgr, this.feldDSMEGd) && MELD_092_GD.contains(this.feldDSMEGd.getTrimmedValue()) || MELD_092_PERSGR.contains(persgrValue)) {
			final PruefungLeerNumerisch me092 = new PruefungLeerNumerisch(this.getFeld());
			this.addPruefungBausteinUebergreifend(me092, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME092));
		}

		if (this.isPruefbar(FehlerNummerDBME.DBME102, this.feldDSMEPersgr) && MELD_102_P.equals(persgrValue)) {
			final PruefungNichtLeerNumerisch me102 = new PruefungNichtLeerNumerisch(this.getFeld());
			this.addPruefungBausteinUebergreifend(me102, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME102));
		}

		final boolean zrbgIsDatum = DateUtils.isLogischKorrektesDatum(this.feldDBMEZrbg.getTrimmedValue());
		final boolean zrenIsDatum = DateUtils.isLogischKorrektesDatum(this.feldDBMEZren.getTrimmedValue());

		// Pruefungen abhaengig von Zeitraeumen durchfuhren
		if (this.isPruefbar(
				Arrays.asList(FehlerNummerDBME.DBME091, FehlerNummerDBME.DBME093, FehlerNummerDBME.DBME094, FehlerNummerDBME.DBME096, FehlerNummerDBME.DBME098, FehlerNummerDBME.DBME100, FehlerNummerDBME.DBME105, FehlerNummerDBME.DBME101),
				this.feldDBMEZrbg, this.feldDBMEZren)
				&& zrbgIsDatum && zrenIsDatum) {
			final Date zrenDate = DateUtils.parseDate(this.feldDBMEZren);

			if (this.isPruefbar(FehlerNummerDBME.DBME091, this.feldDSMEPersgr) && MELD_091.equals(persgrValue) && zrenDate.before(DAT_091)) {
				final PruefungLeerNumerisch me091 = new PruefungLeerNumerisch(this.getFeld());
				this.addPruefungBausteinUebergreifend(me091, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME091));
			}

			final String sascValue = this.feldDSMESasc.getTrimmedValue();
			final String bygrValue = this.feldDBMEBygr.getTrimmedValue();
			final String feldValue = this.getFeld().getTrimmedValue();

			if (this.isPruefbar(Arrays.asList(FehlerNummerDBME.DBME093, FehlerNummerDBME.DBME094), this.feldDSMEPersgr, this.feldDSMESasc, this.feldDBMEBygr, this.feldDSMEGd) && this.isPruefBedingungMe093u094(persgrValue, sascValue, bygrValue)) {
				if (this.isAbgabeGruende(MELD_093_GD)) {
					if (StringUtils.isEmptyNumeric(feldValue)) {
						final PruefungDatumGleicherMonat me093 = new PruefungDatumGleicherMonat(this.feldDBMEZrbg, this.feldDBMEZren);
						this.addPruefungBausteinUebergreifend(me093, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME093));
					}
				} else if (this.isAbgabeGruende(MELD_094_GD) || ("54".equals(feldDSMEGd.getTrimmedValue()) && "N".equals(feldDBMEKennzSto.getTrimmedValue()))) {
					final PruefungNichtLeerNumerisch me094 = new PruefungNichtLeerNumerisch(this.getFeld());
					this.addPruefungBausteinUebergreifend(me094, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME094));
				}
			}

			if (this.isPruefbar(Arrays.asList(FehlerNummerDBME.DBME096, FehlerNummerDBME.DBME098, FehlerNummerDBME.DBME100, FehlerNummerDBME.DBME105), this.feldDSMEGd, this.feldDSMEPersgr, this.feldDBMEWg, this.feldDBMEKennzrk, this.feldDSMEbbnrvu,
					this.feldDSMEVstr) && this.isBBGPruefbar(feldValue)) {

				final Date zrbgDate = DateUtils.parseDate(this.feldDBMEZrbg);

				// Pflegepersonen
				if (MELD_98_P.contains(persgrValue)) {
					try {
						// die anteilige Bezugsgroesse in der passenden Waehrung
						// ohne Zuschlaege
						final Double anteiligeBezugsgr = this.getAnteiligeBezugsgroesse();

						// die massgeblich geltende Bezugsgroesse (inklusive
						// aller Rahmenbedingungen)
						final Integer geltendeBezugsgr = this.getAufgerundet(anteiligeBezugsgr * 0.8);

						final PruefungKleinerGleich me098 = new PruefungKleinerGleich(this.getFeld(), geltendeBezugsgr.doubleValue());
						this.addPruefungBausteinUebergreifend(me098, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME098));
					} catch (final WertelistenException e) {
						final PruefungFuegeFehlerHinzu me098 = new PruefungFuegeFehlerHinzu(this.getFeld());
						this.addPruefungBausteinUebergreifend(me098, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME098));
					}
					// geringfuegig Beschaeftigte
					// (ab770000_PY-Hoechstbetrag-geringfuegig-Beschaeftigte.doc)
				} else if (MELD_105_P.contains(persgrValue) && "N".equals(feldDBMEKennzSto.getTrimmedValue())) {
					try {
						
						if (zrbgDate.before(DAT_105_B_2015) || zrbgDate.after(DAT_105_E_2018)) {
							final Integer geltendeBezugsgr = this.getBezugsgrGeringBeschaeftigteBis2015(zrbgDate);
							final PruefungKleinerGleich me105 = new PruefungKleinerGleich(this.getFeld(), geltendeBezugsgr.doubleValue());
							this.addPruefungBausteinUebergreifend(me105, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME105));
						} else {
							final Integer geltendeBezugsgr = this.getBezugsgrGeringBeschaeftigteAb2015(zrbgDate);
							final PruefungKleinerGleich me103 = new PruefungKleinerGleich(this.getFeld(), geltendeBezugsgr.doubleValue());
							this.addPruefungBausteinUebergreifend(me103, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME103));
						}
						
					} catch (final WertelistenException e) {
						final PruefungFuegeFehlerHinzu me105 = new PruefungFuegeFehlerHinzu(this.getFeld());
						this.addPruefungBausteinUebergreifend(me105, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME105));
					}
					// Haushaltsscheckverfahren und Persgr 209 (geringfuegig
					// Beschaeftigte) (ab730000_PY-BBG-Haushaltsscheck.doc)
				} else if (MELD_100_P.contains(persgrValue) && zrbgDate.before(DAT_100) && "N".equals(feldDBMEKennzSto.getTrimmedValue())) {
					try {
						final Double anteiligeBBG = this.getAnteiligeBeitragsbemessungsgrenze();
						final int umfassendeMonate = this.berechneMonate(zrbgDate, zrenDate);
						this.addPruefungenMELD100P(persgrValue, anteiligeBBG, umfassendeMonate);
					} catch (final WertelistenException e) {
						final PruefungFuegeFehlerHinzu me100 = new PruefungFuegeFehlerHinzu(this.getFeld());
						this.addPruefungBausteinUebergreifend(me100, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME100));
					}
				}

				// uebrige Beschaeftigte
				else if ("N".equals(feldDBMEKennzSto.getTrimmedValue())) {
					try {
						final Double anteiligeBBG = this.getAnteiligeBeitragsbemessungsgrenze();
						this.addAllgemeineEntgeltPruefung(anteiligeBBG);
					} catch (final WertelistenException e) {
						final PruefungFuegeFehlerHinzu me096 = new PruefungFuegeFehlerHinzu(this.getFeld());
						this.addPruefungBausteinUebergreifend(me096, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME096));
					}
				}
			}

			// DBME101
			if (isPruefbar(FehlerNummerDBME.DBME101, feldDSMEGd, feldDBMEKennzSto, feldDSMEPersgr)) {
				if ("N".equals(feldDBMEKennzSto.getTrimmedValue()) && "34".equals(feldDSMEGd.getTrimmedValue()) && "000000".equals(getFeld().getTrimmedValue()) && !NICHT_ERLAUBTE_WERTE_PERSGR_DBME101.contains(feldDSMEPersgr.getTrimmedValue())) {

					final Date zrbgDate = DateUtils.parseDate(this.feldDBMEZrbg);
					final Date zrenDateMax = DateUtils.berechneNeuesDatumMonat(zrbgDate, 2, false);

					final GregorianCalendar gregCalMax = new GregorianCalendar();
					gregCalMax.setTime(zrenDateMax);

					final int zrenMonFeld = Integer.parseInt(this.feldDBMEZren.getTrimmedValue().substring(4, 6));
					final int zrenJahrFeld = Integer.parseInt(this.feldDBMEZren.getTrimmedValue().substring(0, 4));
					final int zrenMonMax = gregCalMax.get(Calendar.MONTH) + ((zrenJahrFeld == gregCalMax.get(Calendar.YEAR)) ? 1 : 13);

					if (zrenMonMax < zrenMonFeld) {
						final PruefungFuegeFehlerHinzu me101 = new PruefungFuegeFehlerHinzu(getFeld());
						addPruefungBausteinUebergreifend(me101, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME101));
					}
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
		if (this.isPruefbar(FehlerNummerDBME.DBME095, this.feldDBMEWg) && StringUtils.isNotEmptyNumeric(this.getFeld().getTrimmedValue())) {
			final PruefungNichtLeer me095 = new PruefungNichtLeer(this.feldDBMEWg);
			this.addPruefungFeldUebergreifend(me095, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME095));
		}

		if (this.isPruefbar(FehlerNummerDBME.DBME097, this.feldDBMEKennzSto) && !this.isAusnahme097()) {
			final PruefungNichtGleicherString me097 = new PruefungNichtGleicherString(this.getFeld(), EINS);
			this.addPruefungFeldUebergreifend(me097, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME097));
		}
	}

	/**
	 * Initialisiere die feldinternen Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer me090a = new PruefungNichtLeer(this.getFeld());
		this.addPruefung(me090a, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME090));

		final PruefungNumerisch me090b = new PruefungNumerisch(this.getFeld(), true);
		this.addPruefung(me090b, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME090));
	}

	/**
	 * Prueft, ob das Feld Abgabegruende keinen der uebergebenen Abgabegruende enthaelt.
	 * 
	 * @param abgabeGruende
	 *            the abgabe gruende
	 * @return true, if successful
	 * @throws UngueltigeDatenException
	 *             the ungueltige daten exception
	 */
	private boolean isAbgabeGruende(final List<String> abgabeGruende) throws UngueltigeDatenException {
		return abgabeGruende.contains(this.feldDSMEGd.getTrimmedValue());
	}

	/**
	 * Prueft, ob die Ausnahme der Fehlernummer DBME097 zutrifft.
	 * 
	 * @return true, if is ausnahme097
	 */
	private boolean isAusnahme097() {
		return VFMM_097.contains(this.vfmm) || STORNIERUNG.equals(this.feldDBMEKennzSto.getTrimmedValue());
	}

	/**
	 * Prueft ob das Entgelt den Bedingungen aus Entscheidungstabelle 'ab530307_Logik_PY-Entgelt.doc' entspricht.
	 * 
	 * @param feldValue
	 *            the feld value
	 * @return true, if is bBG pruefbar
	 */
	private boolean isBBGPruefbar(final String feldValue) {
		return StringUtils.isNumeric(feldValue) && StringUtils.isNotEmptyNumeric(feldValue) && !ZERO.equals(feldValue) && !MELD_96_GD.equals(this.feldDSMEGd.getTrimmedValue());
	}

	/**
	 * Prueft, ob die Meldung in DM vorliegt.
	 * 
	 * @return true, if is Meldung in DM
	 */
	private boolean isMeldungInDM() {
		return DMARK.equals(this.feldDBMEWg.getTrimmedValue());
	}

	/**
	 * Prueft, ob die Meldung in EURO vorliegt.
	 * 
	 * @return true, if is Meldung in EURO
	 */
	private boolean isMeldungInEuro() {
		return EURO.equals(this.feldDBMEWg.getTrimmedValue());
	}

	/**
	 * Prueft, ob es sich um nichtdeutsche Seeleute ohne Angabe einer Beitragsgruppe handelt.
	 * 
	 * @param persgrValue
	 *            der Feld-Wert Personengruppe
	 * @param sascValue
	 *            der Feld-Wert Staatsangehoerigkeitsschluessel
	 * @param feldDBMEBygr
	 *            der Feld-Wert Beitragsgruppe
	 * @return true, if is nichtdeutsche Seeleute
	 */
	private boolean isNichtdeutscheSeeleute(final String persgrValue, final String sascValue, final String bygrValue) {
		return MELD_093_P_SEE.equals(persgrValue) && !MELD_093_S_SEE.equals(sascValue) && MELD_093_BYGR_SEE.equals(bygrValue);
	}

	/**
	 * Prueft, ob eine der Bedingungen fuer die Pruefungen zu den Fehlernummern DBME093 und DBME094 gegeben ist.
	 * 
	 * @return true, wenn Bedingungen erfuellt sind und eine Pruefung vorzunehmen ist
	 * @throws UngueltigeDatenException
	 */
	private boolean isPruefBedingungMe093u094(final String persgrValue, final String sascValue, final String bygrValue) throws UngueltigeDatenException {
		return !(this.isNichtdeutscheSeeleute(persgrValue, sascValue, bygrValue) || MELD_093_094_P.contains(persgrValue));
	}
}
