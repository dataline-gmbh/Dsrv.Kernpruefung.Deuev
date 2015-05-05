package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.utils.Sonderzeichen;

/**
 * Validiert, ob der uebergebene Wert Bindestriche enthaelt und ob vor oder nach
 * diesem ein Leerzeichen folgt. Fehlernummer: NA/GB012. Fehlernummer: NA032.
 */
public class PruefungVorNachBindestrichenKeineLeerzeichen extends AbstractPruefung {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungVorNachBindestrichenKeineLeerzeichen(final Feld<?> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = true;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			for (int i = 0; i < feldValue.length(); i++) {
				if (feldValue.charAt(i) == Sonderzeichen.SPACE) {
					if (i != feldValue.length() - 1) {
						if (feldValue.charAt(i + 1) == Sonderzeichen.HYPHEN_MINUS) {
							erfolgreich = false;
							break;
						}
					}
				} else if (feldValue.charAt(i) == Sonderzeichen.HYPHEN_MINUS) {
					if (i != feldValue.length() - 1) {
						if (feldValue.charAt(i + 1) == Sonderzeichen.SPACE) {
							erfolgreich = false;
							break;
						}
					}
				}
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Vor oder nach Bindestrich ein Leerzeichen< "
					+ "Wert des Feldes fehlt.");
		}
		return erfolgreich;
	}
}
