package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Gibt immer <code>false</code> zurueck und damit immer einen Fehler. Kann z.B.
 * verwendet werden, wenn eine Exception gefangen wurde. Damit bleibt die Logik
 * in den Feldpruefungen einheitlich.
 */
public class PruefungFuegeFehlerHinzu extends AbstractPruefung {

	/**
	 * @param feld
	 */
	public PruefungFuegeFehlerHinzu(final Feld<? extends FeldName> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		return false;
	}

}
