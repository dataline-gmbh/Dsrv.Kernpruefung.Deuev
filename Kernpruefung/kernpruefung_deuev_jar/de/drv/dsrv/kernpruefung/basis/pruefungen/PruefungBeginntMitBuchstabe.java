package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.basis.utils.Zeichen;

/**
 * Validiert, ob der uebergebene Wert mit einem Buchstaben beginnt.
 * Fehlernummer: AN185, NA086.
 */
public class PruefungBeginntMitBuchstabe extends AbstractPruefung {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungBeginntMitBuchstabe(final Feld<?> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			if (StringUtils.isNotEmpty(feldValue)) {
				final Character erstesZeichen = feldValue.charAt(0);
				if (Zeichen.ALL_ALPHABETIC_CHARACTERS.contains(erstesZeichen.toString())) {
					erfolgreich = true;
				}
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Wert beginnt mit einem Buchstaben< "
					+ "Wert des Feldes fehlt.");
		}
		return erfolgreich;
	}
}
