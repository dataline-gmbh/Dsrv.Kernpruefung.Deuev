package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;

/**
 * Validiert, ob der uebergebene numerische Wert nicht leer ist (Grundstellung).
 * Fehlernummer: AN018.
 */
public class PruefungNichtLeerNumerisch extends AbstractPruefung {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungNichtLeerNumerisch(final Feld<?> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;
		final Feld<?> feld = getFeld();
		if (feld == null || feld.getTrimmedValue() == null) {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Pruefung nicht leer numerisch< Feld oder Wert ist null.");
		} else {
			erfolgreich = StringUtils.isNotEmptyNumeric(feld.getValue());
		}

		return erfolgreich;
	}
}
