package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.utils.Zeichen;

/**
 * Validiert, ob der uebergebene Wert aus mindestens zwei Buchstaben besteht.
 * Fehlernummer: GB007, AN130.
 */
public class PruefungMindestensZweiBuchstaben extends AbstractPruefung {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungMindestensZweiBuchstaben(final Feld<?> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			int anzahlBuchstaben = 0;
			for (int i = 0; i < feldValue.length(); i++) {
				if (Zeichen.ALL_ALPHABETIC_CHARACTERS.contains(Character.toString(feldValue.charAt(i)))) {
					anzahlBuchstaben++;
					if (anzahlBuchstaben == 2) {
						erfolgreich = true;
						break;
					}
				}
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Wert besteht aus mindestens zwei Buchstaben< "
					+ "Wert des Feldes fehlt.");
		}
		return erfolgreich;
	}

}
