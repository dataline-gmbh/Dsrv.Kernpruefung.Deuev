package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSBD;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSBD;

/**
 * Pruefung fuer das Feld Reserve1 aus dem Baustein DSBD.
 */
public class PruefungReserve1 extends AbstractFeldPruefung<FeldNameDSBD, FehlerNummerDSBD> {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungReserve1(final Feld<FeldNameDSBD> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungLaenge db160 = new PruefungLaenge(0, getFeld());
		addPruefung(db160, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD160));
	}
}
