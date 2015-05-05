package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.basis.utils.Zeichen;

/**
 * Validiert, ob das letzte Zeichen in dem uebergebenen Wert ein Buchstabe ist.
 * Fehlernummer: NA036.
 */
public class PruefungEndetMitBuchstabe extends AbstractPruefung {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungEndetMitBuchstabe(final Feld<?> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			if (StringUtils.isNotEmpty(feldValue)) {
				final Character letztesZeichen = feldValue.charAt(feldValue.length() - 1);
				if (Zeichen.ALL_ALPHABETIC_CHARACTERS.contains(Character.toString(letztesZeichen))) {
					erfolgreich = true;
				}
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Letzte Zeichen in dem Wert ist ein Buchstabe< "
					+ "Wert des Feldes fehlt.");
		}
		return erfolgreich;
	}

}
