package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;

/**
 * Validiert, ob der uebergebene Wert nicht leer ist. Fehlernummer: NA005.
 * Fehlernummer: AN018.
 */
public class PruefungNichtLeer extends AbstractPruefung {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungNichtLeer(final Feld<?> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = false;
		final Feld<?> feld = getFeld();
		if (feld == null || feld.getTrimmedValue() == null) {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Pruefung nicht leer< Feld oder Wert ist null.");
		} else {
			erfolgreich = StringUtils.isNotEmpty(feld.getTrimmedValue());
		}

		return erfolgreich;
	}
}
