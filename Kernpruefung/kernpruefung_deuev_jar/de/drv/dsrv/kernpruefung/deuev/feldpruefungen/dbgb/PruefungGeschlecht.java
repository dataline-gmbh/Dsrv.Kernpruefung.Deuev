package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbgb;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInterval;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBGB;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBGB;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.ListenTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Pruefung fuer das Feld mit Geschlecht.
 */
public class PruefungGeschlecht extends AbstractFeldPruefung<FeldNameDBGB, FehlerNummerDBGB> {
	
	private static final int SNR_ENDE = 11;
	private static final int SNR_START = 9;
	private static final int MIN_M = 0;
	private static final int MAX_M = 49;
	private static final int MIN_W = 50;
	private static final int MAX_W = 99;
	private static final int MINLENGTH = 11;
	private static final List<String> ERLAUBTE_ZEICHEN_GB120 = Arrays.asList("M", "W");
	
	private static final int START_VSNR_BB = 0;
	private static final int ENDE_VSNR_BB = 2;
	
	private final transient List<String> itvsnrListe = WertelistenVerwaltungDeuev.getInstance().getWerteliste(
			ListenTypDeuev.INTERIMSVERSICHERUNGSNUMMER);
	
	private transient Feld<FeldNameDSME> vsnr;
	
	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            das zu pruefende Feld
	 * @param bausteinDSME
	 *            der Baustein DSME
	 */
	public PruefungGeschlecht(final Feld<FeldNameDBGB> feld, final Baustein<FeldNameDSME> bausteinDSME) {
		super(feld);
		if (bausteinDSME != null) {
			this.vsnr = bausteinDSME.getFeld(FeldNameDSME.VSNR);
		}
	}
	
	/**
	 * Extrahiert die die Seriennummer der Interimsversicherungsnummer. Kann die
	 * Extraktion nicht erfolgen, wird "-1" zurueckgegeben.
	 * 
	 * @return die extrahierte Seriennummer
	 */
	private int extrahiereSnr() {
		// versuche substring zu extrahieren
		String serienNrSubString = "-1";
		if (this.vsnr != null && StringUtils.isNotBlank(this.vsnr.getTrimmedValue())
				&& this.vsnr.getTrimmedValue().length() >= MINLENGTH) {
			serienNrSubString = this.vsnr.getTrimmedValue().substring(SNR_START, SNR_ENDE);
		}
		
		// versuche Integer zu parsen
		int serienNr = -1;
		try {
			serienNr = Integer.parseInt(serienNrSubString);
		} catch (final NumberFormatException e) {
			LOGGER.log(
					Level.FINE,
					"Fehler in der Pruefung >Geschlecht<. Der Wert des Feldes VSNR [{0}] hat ein nicht lesbares Format - {1}",
					new Object[] { e.getLocalizedMessage(), this.vsnr });
		}
		return serienNr;
	}
	
	/**
	 * Initialisiere die feldinternen Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungInList gb120 = new PruefungInList(ERLAUBTE_ZEICHEN_GB120, this.getFeld(), true);
		this.addPruefungBausteinUebergreifend(gb120, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB120));
	}
	
	/**
	 * Initialisiere die bausteinuebergreifenden Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {
		if (this.isPruefbar(Arrays.asList(FehlerNummerDBGB.DBGB122, FehlerNummerDBGB.DBGB124), this.vsnr)
				&& this.isItvsnr(this.vsnr)) {
			final int serienNr = this.extrahiereSnr();
			final String geschlecht = this.getFeld().getTrimmedValue().toUpperCase();
			
			if ("M".equals(geschlecht)) {
				if (!(serienNr >= MIN_M && serienNr <= MAX_M)) {
					final PruefungInterval gb122 = new PruefungInterval(MIN_M, MAX_M,
							new Feld<FeldNameDBGB>(geschlecht));
					this.addPruefungBausteinUebergreifend(gb122, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB122));
				}
			} else if ("W".equals(geschlecht)) {
				if (!(serienNr >= MIN_W && serienNr <= MAX_W)) {
					final PruefungInterval gb124 = new PruefungInterval(MIN_W, MAX_W,
							new Feld<FeldNameDBGB>(geschlecht));
					this.addPruefungBausteinUebergreifend(gb124, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB124));
				}
			}
		}
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
			erfolgreich = this.itvsnrListe.contains(bereichsNr);
		}
		
		return erfolgreich;
	}
}
