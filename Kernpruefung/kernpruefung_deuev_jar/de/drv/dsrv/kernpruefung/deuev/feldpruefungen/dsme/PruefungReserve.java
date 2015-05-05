package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld Reserve aus dem Baustein DSME.
 */
public class PruefungReserve extends AbstractFeldPruefung<FeldNameDSME, FehlerNummerDSME> {

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungReserve(final Feld<FeldNameDSME> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungLaenge me610 = new PruefungLaenge(0, getFeld());
		addPruefung(me610, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME610));
	}

}
