package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.utils.Sonderzeichen;
import de.drv.dsrv.kernpruefung.basis.utils.Zeichen;

/**
 * Validiert, ob der uebergebene Wert nur Buchstaben, Sonderzeichen oder einem
 * Punkt besteht. Buchstaben und Sonderzeichen duerfen dabei mehrfach und der
 * Punkt nur ein Mal vorkommen.
 */
public class PruefungBuchstabenZeichenPunkt extends AbstractPruefung {

	private final List<Character> erlaubteZeichen;

	/**
	 * Konstruktor.
	 * 
	 * @param erlaubteZeichen
	 *            Liste mit zulaessigen Zeichen
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungBuchstabenZeichenPunkt(final List<Character> erlaubteZeichen, final Feld<?> feld) {
		super(feld);
		this.erlaubteZeichen = erlaubteZeichen;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = true;

		if (getFeld() != null && getFeld().getTrimmedValue() != null && erlaubteZeichen != null) {
			final String feldValue = getFeld().getTrimmedValue();

			Character zeichen = null;
			int j = 0;
			for (int i = 0; i < feldValue.length(); i++) {
				zeichen = feldValue.charAt(i);
				if (!(Zeichen.ALL_ALPHABETIC_CHARACTERS.contains(zeichen.toString()) || erlaubteZeichen
						.contains(zeichen))) {
					erfolgreich = false;
					break;
				}
				if (zeichen == Sonderzeichen.FULL_STOP) {
					j = j + 1;
				}
				if (j >= 2) {
					erfolgreich = false;
					break;
				}
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Wert besteht nur aus Buchstaben, Sonderzeichen oder einem Punkt< "
							+ "Wert des Feldes fehlt oder nicht initialisierte Liste der erlaubten Werte.");
		}
		return erfolgreich;
	}
}
