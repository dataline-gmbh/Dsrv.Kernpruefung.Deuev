package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Validiert ob der getrimmte Feld-Wert mit dem uebergebenen Wert nicht
 * identisch ist (case insensitive).
 */
public class PruefungNichtGleicherString extends AbstractPruefung {

	private final String vergleich;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu vergleichenden Wert
	 * @param vergleich
	 *            der zu vergleichende Wert
	 */
	public PruefungNichtGleicherString(final Feld<? extends FeldName> feld, final String vergleich) {
		super(feld);
		this.vergleich = vergleich;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		return !(new PruefungGleicherString(this.getFeld(), this.vergleich).pruefe());
	}

}
