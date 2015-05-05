package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbtn;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBTN;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBTN;

/**
 * Pruefung fuer das Feld Reserve aus dem Baustein DBTN.
 */
public class PruefungReserve extends AbstractFeldPruefung<FeldNameDBTN, FehlerNummerDBTN> {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungReserve(final Feld<FeldNameDBTN> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungLaenge tn260 = new PruefungLaenge(0, getFeld());
		addPruefung(tn260, new Fehler<FehlerNummerDBTN>(FehlerNummerDBTN.DBTN260));
	}
}
