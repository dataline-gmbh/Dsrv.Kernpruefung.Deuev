package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKV;

/**
 * Pruefung fuer das Feld Reserve3 aus dem Baustein DBKV.
 */
public class PruefungReserve3 extends AbstractFeldPruefung<FeldNameDBKV, FehlerNummerDBKV> {

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungReserve3(final Feld<FeldNameDBKV> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungLaenge kv290 = new PruefungLaenge(0, getFeld());
		addPruefung(kv290, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV290));
	}

}
