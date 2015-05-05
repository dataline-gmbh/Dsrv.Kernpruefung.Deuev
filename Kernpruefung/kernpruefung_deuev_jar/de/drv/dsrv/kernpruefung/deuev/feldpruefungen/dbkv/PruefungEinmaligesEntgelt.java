package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKV;

/**
 * Pruefung fuer das Feld einmaliges Entgelt aus dem Baustein DBKV.
 */
public class PruefungEinmaligesEntgelt extends AbstractFeldPruefung<FeldNameDBKV, FehlerNummerDBKV> {

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungEinmaligesEntgelt(final Feld<FeldNameDBKV> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch kv070 = new PruefungNumerisch(getFeld(), true);
		addPruefung(kv070, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV070));
	}

}
