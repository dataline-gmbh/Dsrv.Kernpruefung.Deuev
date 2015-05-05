package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;

/**
 * Validiert, ob der uebergebene numerische Wert leer ist (Grundstellung).
 */
public class PruefungLeerNumerisch extends AbstractPruefung {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungLeerNumerisch(final Feld<?> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;
		final Feld<?> feld = getFeld();
		if (feld == null || feld.getValue() == null) {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Pruefung nicht leer numerisch< Feld oder Wert ist null.");
		} else {
			erfolgreich = StringUtils.isEmptyNumeric(feld.getValue());
		}

		return erfolgreich;
	}
}
