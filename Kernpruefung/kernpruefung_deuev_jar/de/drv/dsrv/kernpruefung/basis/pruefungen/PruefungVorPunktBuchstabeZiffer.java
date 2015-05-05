package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.utils.Sonderzeichen;
import de.drv.dsrv.kernpruefung.basis.utils.Zeichen;

/**
 * Validiert, ob in dem uebergebenen Wert vor einem Punkt immer ein Buchstabe
 * oder eine Ziffer steht. Fehlernummer: AN188.
 */
public class PruefungVorPunktBuchstabeZiffer extends AbstractPruefung {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungVorPunktBuchstabeZiffer(final Feld<?> feld) {
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
								.toString(nachfolger)) || Character.isDigit(nachfolger)))) {
					fehlerhaftePunkte++;
				}
			}
			if (fehlerhaftePunkte < 1) {
				erfolgreich = true;
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Vor einem Punkt steht immer ein Buchstabe oder eine Ziffer< "
							+ "Wert des Feldes fehlt.");
		}

		return erfolgreich;
	}
}
