package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbuv;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBUV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBUV;

/**
 * Pruefung fuer das Feld Reserve aus dem Baustein DBUV.
 */
public class PruefungReserve extends AbstractFeldPruefung<FeldNameDBUV, FehlerNummerDBUV> {

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungReserve(final Feld<FeldNameDBUV> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungLaenge uv040 = new PruefungLaenge(0, getFeld());
		addPruefung(uv040, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV040));
	}

}
