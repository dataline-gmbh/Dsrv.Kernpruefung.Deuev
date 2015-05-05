package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dban;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBAN;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBAN;

/**
 * Pruefung fuer das Feld Anschriftenzusatz aus dem Baustein DBAN.
 */
public class PruefungAnschriftenzusatz extends AbstractFeldPruefung<FeldNameDBAN, FehlerNummerDBAN> {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 */
	public PruefungAnschriftenzusatz(final Feld<FeldNameDBAN> feld) {
		super(feld);
	}
}
