package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert, ob in dem uebergebenen Wert Zahlen (1-n Ziffern) stehen und vor
 * diesen ein Leerzeichen. Fehlernummer: NA018, GB018.
 */
public class PruefungLeerzeichenVorZiffer extends AbstractPruefung {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungLeerzeichenVorZiffer(final Feld<?> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = true;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			int fehlerStelle = 0;

			Character vorgaenger = null;
			Character nachfolger = null;

			if (feldValue.length() >= 2) {
				for (int i = 0; i < feldValue.length(); i++) {
					nachfolger = vorgaenger;
					vorgaenger = feldValue.charAt(i);
					if (Character.isDigit(vorgaenger)
							&& (((nachfolger != null) && (nachfolger != ' ') && !(Character.isDigit(nachfolger))))) {
						fehlerStelle++;
					}
				}
				if (fehlerStelle >= 1) {
					erfolgreich = false;
				}
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Wert enthaelt Ziffern und vor diesen steht ein Leerzeichen< "
							+ "Wert des Feldes fehlt.");
		}
		return erfolgreich;
	}
}
