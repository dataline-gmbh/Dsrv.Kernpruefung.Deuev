package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Datensatz;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBSO;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld MMKV aus dem Baustein DSME.
 */
public class PruefungMmkv extends AbstractPruefungSchalterleisteFeld<FeldNameDSME, FehlerNummerDSME> {

	private final Baustein<FeldNameDBME> bausteinDbme;
	private final Baustein<FeldNameDBSO> bausteinDbso;
	
	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param datensatz
	 *            Datensatz DSME
	 */
	public PruefungMmkv(final Feld<FeldNameDSME> feld, final Baustein<FeldNameDBME> bausteinDbme, final Baustein<FeldNameDBSO> bausteinDbso, final Datensatz datensatz) {
		super(feld, datensatz);
		
		this.bausteinDbme = bausteinDbme;
		this.bausteinDbso = bausteinDbso;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		if (this.getFeld().getTrimmedValue().length() == 0 && 
				((bausteinDbme != null && bausteinDbme.getFeld(FeldNameDBME.KENNZ_STORNO).getTrimmedValue().compareTo("J") == 0) || 
				 (bausteinDbso != null && bausteinDbso.getFeld(FeldNameDBSO.KENNZ_STORNO_SOFORT).getTrimmedValue().compareTo("J") == 0) ))
			this.getFeld().setValue("N");
		
		addPruefungenOptional(FehlerNummerDSME.DSME560, FehlerNummerDSME.DSME941, BausteinName.DBKV);
	}

}
