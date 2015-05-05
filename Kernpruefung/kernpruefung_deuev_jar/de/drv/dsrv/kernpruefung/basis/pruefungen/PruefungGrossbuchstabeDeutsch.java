package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.utils.Zeichen;

/**
 * Validiert, ob der uebergebene Wert aus einem Zeichen besteht und dieses
 * Zeichen ein Grossbuchstabe ist. Fehlernummer: AN158.
 */
public class PruefungGrossbuchstabeDeutsch extends AbstractPruefung {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungGrossbuchstabeDeutsch(final Feld<?> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();
			if (feldValue.length() == 1) {
				final Character zeichen = feldValue.charAt(0);
				if (Zeichen.ALL_ALPHABETIC_CHARACTERS_DEUTSCH.contains(zeichen.toString())) {
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
