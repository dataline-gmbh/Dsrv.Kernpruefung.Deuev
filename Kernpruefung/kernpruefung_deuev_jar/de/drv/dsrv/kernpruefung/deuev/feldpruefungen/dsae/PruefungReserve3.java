package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSAE;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;

/**
 * Pruefung fuer das Feld Reserve3 aus dem Baustein DSAE.
 */
public class PruefungReserve3 extends AbstractFeldPruefung<FeldNameDSAE, FehlerNummerDSAE> {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungReserve3(Feld<FeldNameDSAE> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungLaenge ae430 = new PruefungLaenge(0, getFeld());
		addPruefung(ae430, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE430));
	}

}