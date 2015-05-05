package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert, ob der uebergebene Wert nur aus Ziffern und Sonderzeichen
 * besteht.
 */
public class PruefungZiffernZeichen extends AbstractPruefung {

	private final transient List<Character> erlaubteZeichen;

	/**
	 * Konstruktor.
	 * 
	 * @param erlaubteZeichen
	 *            Liste mit zulaessigen Zeichen
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungZiffernZeichen(final List<Character> erlaubteZeichen, final Feld<?> feld) {
		super(feld);
		this.erlaubteZeichen = erlaubteZeichen;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = true;

		if (getFeld() != null && getFeld().getTrimmedValue() != null && erlaubteZeichen != null) {
			final String feldValue = getFeld().getTrimmedValue();

			Character zeichen = null;
			for (int i = 0; i < feldValue.length(); i++) {
				zeichen = feldValue.charAt(i);
				if (!(Character.isDigit(zeichen) || erlaubteZeichen.contains(zeichen))) {
					erfolgreich = false;
					break;
				}
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Wert besteht nur aus Ziffern, Sonderzeichen< "
							+ "Wert des Feldes fehlt oder nicht initialisierte Liste der erlaubten Werte.");
		}
		return erfolgreich;
	}
}
