package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert, ob der uebergebene Wert aus einem Zeichen besteht und dieses
 * Zeichen ein Grossbuchstabe ist. Fehlernummer: AN158.
 */
public class PruefungGrossbuchstabe extends AbstractPruefung {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungGrossbuchstabe(final Feld<?> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();
			if (feldValue.length() == 1) {
				final Character zeichen = feldValue.charAt(0);
				if (Character.getType(zeichen) == Character.UPPERCASE_LETTER) {
					erfolgreich = true;
				}
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Wert besteht aus einem Grossbuchstaben< "
					+ "Wert des Feldes fehlt.");
		}
		return erfolgreich;
	}
}
