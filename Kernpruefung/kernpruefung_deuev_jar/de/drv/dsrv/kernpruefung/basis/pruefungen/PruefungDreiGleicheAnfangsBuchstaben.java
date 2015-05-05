package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.utils.Zeichen;

/**
 * Validiert, ob der uebergebene Wert mit drei gleichen aufeinanderfolgenden
 * Buchstaben beginnt. Fehlernummer: NA011. Fehlernummer: AN121.
 */
public class PruefungDreiGleicheAnfangsBuchstaben extends AbstractPruefung {

	private static final int ANZAHL_ANFANGSBUCHSTABEN = 3;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungDreiGleicheAnfangsBuchstaben(final Feld<?> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			if (feldValue.length() >= ANZAHL_ANFANGSBUCHSTABEN) {
				final char erstesZeichen = Character.toUpperCase(feldValue.charAt(0));
				final char zweitesZeichen = Character.toUpperCase(feldValue.charAt(1));
				final char drittesZeichen = Character.toUpperCase(feldValue.charAt(2));

				if (erstesZeichen == zweitesZeichen && erstesZeichen == drittesZeichen) {
					if (!Zeichen.ALL_ALPHABETIC_CHARACTERS.contains(Character.toString(erstesZeichen))) {
						erfolgreich = true;
					}
				} else {
					erfolgreich = true;
				}
			} else {
				erfolgreich = true;
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Wert beginnt mit drei gleichen aufeinanderfolgenden Buchstaben< "
							+ "Wert des Feldes fehlt.");
		}
		return erfolgreich;
	}
}
