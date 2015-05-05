package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert, ob der uebergebene Wert mehr als 2 Ziffern oder 2 Ziffern, die
 * nicht unmittelbar hintereinander stehen, enthaelt. Fehlernummer: NA/GB015.
 */
public class PruefungMehrfachZiffern extends AbstractPruefung {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungMehrfachZiffern(final Feld<?> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			int anzahlZiffern = 0;
			Integer ersteZifferIndex = null;
			Integer zweiteZifferIndex = null;
			Character vorgaenger = null;
			for (int i = 0; i < feldValue.length(); i++) {
				vorgaenger = feldValue.charAt(i);
				if (vorgaenger != null && Character.isDigit(vorgaenger)) {

					anzahlZiffern++;
					if (ersteZifferIndex == null) {
						ersteZifferIndex = i;
					} else {
						if (zweiteZifferIndex == null) {
							zweiteZifferIndex = i;
						}
					}
				}
			}

			if (anzahlZiffern < 2) {
				erfolgreich = true;
			} else {
				if (anzahlZiffern > 2) {
					erfolgreich = false;
				} else {
					if (ersteZifferIndex != null && zweiteZifferIndex != null) {
						if (zweiteZifferIndex - ersteZifferIndex == 1) {
							erfolgreich = true;
						}
					}
				}
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Wert enthaelt mehr als 2 Ziffern oder 2 Ziffern, die nicht unmittelbar hintereinander stehen< "
							+ "Wert des Feldes fehlt.");
		}
		return erfolgreich;
	}
}
