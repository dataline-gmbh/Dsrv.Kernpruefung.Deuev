package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbgb;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBevorDatum;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumJahreVorDatum;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumLogischRichtig;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungFuegeFehlerHinzu;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungGleicherString;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils.DateUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBGB;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBGB;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;
import de.drv.dsrv.kernpruefung.deuev.pruefungen.PruefungVersicherungsnummerGeburtsdatum;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.ListenTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Pruefungen fuer das Feld Geburtsdatum mit Abhaengigkeit zu dem Feld
 * Staatsangehoerigkeit aus dem Baustein DSME.
 */
public class PruefungGeburtsdatum extends AbstractFeldPruefung<FeldNameDBGB, FehlerNummerDBGB> {
	
	private static final String DEUTSCHER = "000";
	private static final int INTERVAL_JAHRE = 150;
	private static final int MINLENGTH = 8;
	private static final int START_GB_YY = 2;
	private static final int ENDE_GB_TT = 8;
	private static final int START_VSNR_BB = 0;
	private static final int ENDE_VSNR_BB = 2;
	private static final int START_DATUM_VSNR = 2;
	private static final int ENDE_DATUM_VSNR = 8;
	
	private final transient List<String> itvsnrliste = WertelistenVerwaltungDeuev.getInstance().getWerteliste(
			ListenTypDeuev.INTERIMSVERSICHERUNGSNUMMER);
	
	private transient Feld<FeldNameDSME> feldDSMESc;
	private transient Feld<FeldNameDSME> vsnr;
	private final transient Date verarbDatum;
	
	/**
	 * Konstruktor.
	 * 
	 * @param gebDatumfeld
	 *            Feld mit dem Geburtsdatum
	 * @param bausteinDSME
	 *            der Baustein DSME
	 * @param verarbDatum
	 *            Verarbeitungsdatum
	 */
	public PruefungGeburtsdatum(final Feld<FeldNameDBGB> gebDatumfeld, final Baustein<FeldNameDSME> bausteinDSME,
			final Date verarbDatum) {
		super(gebDatumfeld);
		
		if (bausteinDSME != null) {
			this.feldDSMESc = bausteinDSME.getFeld(FeldNameDSME.STAATSANGEHOERIGKEITS_SC);
			this.vsnr = bausteinDSME.getFeld(FeldNameDSME.VSNR);
		}
		this.verarbDatum = verarbDatum;
	}
	
	/**
	 * Aendert das Datumsformat von <code>ddMMyy</code> nach <code>yyMMdd</code>
	 * .
	 * 
	 * @param datum
	 *            das Datum
	 * @return das Datum im Format <code>yyMMdd</code>
	 */
	private String changeDateFormat(final String datum) {
		final String day = datum.substring(0, 2);
		final String month = datum.substring(2, 4);
		final String year = datum.substring(4, 6);
		
		return year + month + day;
	}
	
	/**
	 * Berechne das korrigierte VSNR-Geburtsdatum. Das Tages-Datum wird
	 * zunaechst Modulo 32 gerechnet. Anschliessend wird das Datums-Format auf
	 * das Format des DBGB GB angepasst.
	 * 
	 * @param datumVsnr
	 *            the datum vsnr
	 * @return the korrigiertes vsnr datum
	 */
	private String getKorrigiertesVsnrDatum(final String datumVsnr) {
		final String korrDat = DateUtils.berechneVsnrDatum(datumVsnr);
		return this.changeDateFormat(korrDat);
	}
	
	/**
	 * Prueft anhand des Staatsangehoerigkeitsschluessels, ob es sich um einen
	 * Auslaender handelt. In diesem Fall wird zusaetzlich geprueft, ob es sich
	 * um einen Sonderfalldatum handelt. Anschliessend wird die Pruefung mit dem
	 * gegebenen oder dem korrigierten Sonderfalldatum fortgesetzt.
	 * 
	 * @throws UngueltigeDatenException
	 */
	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {
		// zur Validierung des Datums muss der Sonderfall (0000/00) korrigiert
		// werden, spaeter wird geprueft, ob der Sonderfall zutreffen darf
		final String korrigieresDatum = DateUtils.korrigiereLeerDatum(this.getFeld().getTrimmedValue());
		final Feld<FeldNameDBGB> korrGebDat = new Feld<FeldNameDBGB>(korrigieresDatum);
		
		if (this.isPruefbar(Arrays.asList(FehlerNummerDBGB.DBGB102, FehlerNummerDBGB.DBGB104), this.feldDSMESc)) {
			if (!DEUTSCHER.equals(this.feldDSMESc.getTrimmedValue())) {
				final boolean gb102u104 = new PruefungDatumLogischRichtig(korrGebDat).pruefe();
				final int geburtsDatumMonat = Integer.parseInt(korrigieresDatum.substring(4, 6));
				if(!gb102u104 &&  geburtsDatumMonat> 12) {
					final PruefungFuegeFehlerHinzu gb104 = new PruefungFuegeFehlerHinzu(getFeld());
					this.addPruefungBausteinUebergreifend(gb104, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB104));
				} else if (!gb102u104) {
					final PruefungFuegeFehlerHinzu gb102 = new PruefungFuegeFehlerHinzu(getFeld());
					this.addPruefungBausteinUebergreifend(gb102, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB102));
				}
			} else {
				// allgemeine Pruefung auf logische Richtigkeit (auf korrigiertem Datum, da evtl. Sonderfall zugelassen)
				final PruefungDatumLogischRichtig gb104 = new PruefungDatumLogischRichtig(this.getFeld());
				this.addPruefungBausteinUebergreifend(gb104, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB104));
			}
		}
		
		final PruefungDatumJahreVorDatum gb106 = new PruefungDatumJahreVorDatum(this.verarbDatum, INTERVAL_JAHRE, korrGebDat, true);
		this.addPruefungBausteinUebergreifend(gb106, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB106));
		
		final PruefungBevorDatum gb107 = new PruefungBevorDatum(this.verarbDatum, korrGebDat, true);
		this.addPruefungBausteinUebergreifend(gb107, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB107));
		
		// Pruefung GB110 immmer mit gegebenen Datum durchfuehren
		this.pruefeGB110(this.getFeld());
	}
	
	/**
	 * Initialisiere die feldinternen Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer gb100a = new PruefungNichtLeer(this.getFeld());
		this.addPruefung(gb100a, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB100));
		
		final PruefungNumerisch gb100b = new PruefungNumerisch(this.getFeld(), true);
		this.addPruefung(gb100b, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB100));
	}
	
	/**
	 * Prueft, ob es sich um eine Interrimsversicherungsnummer (ITVSNR) handelt.
	 * 
	 * @param feld
	 *            das Feld mit der Versicherungsnummer
	 * @return true, if is itvsnr
	 * @throws UngueltigeDatenException
	 */
	private boolean isItvsnr(final Feld<FeldNameDSME> feld) throws UngueltigeDatenException {
		boolean erfolgreich = false;
		if (this.vsnr != null && StringUtils.isNotBlank(this.vsnr.getTrimmedValue())
				&& this.vsnr.getValue().length() >= MINLENGTH) {
			final String bereichsNr = this.vsnr.getTrimmedValue().substring(START_VSNR_BB, ENDE_VSNR_BB);
			erfolgreich = this.itvsnrliste.contains(bereichsNr);
		}
		
		return erfolgreich;
	}
	
	/**
	 * Prueft, ob das in der VSNR enthaltene Datum logisch richtig ist.
	 * 
	 * @return true, if is vsnr datum logisch richtig
	 * @throws UngueltigeDatenException
	 */
	private boolean isVsnrDatumLogischRichtig(final String datumVsnr) throws UngueltigeDatenException {
		return new PruefungVersicherungsnummerGeburtsdatum(new Feld<FeldNameDSME>(datumVsnr)).pruefe();
	}
	
	/**
	 * Pruefe auf Fehlernummer GB110.
	 * 
	 * @param gebDat
	 *            das Feld Geburtsdatum
	 * @param pruefungErgebnis
	 *            das bisherige Pruefungsergebnis
	 * @throws UngueltigeDatenException
	 */
	private void pruefeGB110(final Feld<FeldNameDBGB> gebDat) throws UngueltigeDatenException {
		final String feldValue = gebDat.getTrimmedValue();
		
		if (this.isPruefbar(FehlerNummerDBGB.DBGB110, this.vsnr) && this.isItvsnr(this.vsnr)) {
			final String datumVsnr = this.vsnr.getTrimmedValue().substring(START_DATUM_VSNR, ENDE_DATUM_VSNR);
			final String datumTag = datumVsnr.substring(0, 2);
			final String datumMonat = datumVsnr.substring(2, 4);
			
			if (this.isVsnrDatumLogischRichtig(datumVsnr) && !datumTag.equals("00") && !datumMonat.equals("00")) {
				final String gebDatKurz = feldValue.substring(START_GB_YY, ENDE_GB_TT);
				final PruefungGleicherString gb110 = new PruefungGleicherString(new Feld<FeldNameDBGB>(gebDatKurz),
						this.getKorrigiertesVsnrDatum(datumVsnr));
				this.addPruefungBausteinUebergreifend(gb110, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB110));
			}
		}
	}
}
