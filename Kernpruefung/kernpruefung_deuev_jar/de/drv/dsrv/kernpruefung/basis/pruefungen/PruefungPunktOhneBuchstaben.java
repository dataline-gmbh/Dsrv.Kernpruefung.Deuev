package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.utils.Sonderzeichen;
import de.drv.dsrv.kernpruefung.basis.utils.Zeichen;

/**
 * Validiert, ob in dem uebergebenen Wert vor einem Punkt immer ein Buchstabe
 * steht. Fehlernummer: NA088. Fehlernummer: AN128.
 */
public class PruefungPunktOhneBuchstaben extends AbstractPruefung {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungPunktOhneBuchstaben(final Feld<?> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			int fehlerhaftePunkte = 0;

			Character vorgaenger = null;
			Character nachfolger = null;

			for (int i = 0; i < feldValue.length(); i++) {
				nachfolger = vorgaenger;
				vorgaenger = feldValue.charAt(i);
				if (vorgaenger == Sonderzeichen.FULL_STOP
						&& (nachfolger == null || !(Zeichen.ALL_ALPHABETIC_CHARACTERS.contains(Character
								.toString(nachfolger))))) {
					fehlerhaftePunkte++;
				}
			}
			if (fehlerhaftePunkte < 1) {
				erfolgreich = true;
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Vor einem Punkt steht immer ein Buchstabe< "
					+ "Wert des Feldes fehlt.");
		}

		return erfolgreich;
	}
}
