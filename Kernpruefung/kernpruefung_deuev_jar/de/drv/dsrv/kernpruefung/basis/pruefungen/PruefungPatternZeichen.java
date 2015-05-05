package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Validiert, ob das uebergebene Feld nur aus den erlaubten Zeichen des Patterns
 * und den Sonderzeichen besteht. Kann z.B. gebraucht werden, wenn nur
 * Grossbuchstaben ohne Umlaute erlaubt sind ([A-Z]) zusammen mit Sonderzeichen
 * wie - oder *. Fehlernummer DSBD580.
 */
public class PruefungPatternZeichen extends AbstractPruefung {

	private final String pattern;
	private final List<Character> zeichen;

	/**
	 * Konstruktur.
	 * 
	 * @param feld
	 *            das zu pruefende Feld
	 * @param pattern
	 *            Regex
	 * @param zeichen
	 *            erlaubte Sonderzeichen
	 */
	public PruefungPatternZeichen(final Feld<? extends FeldName> feld, final String pattern,
			final List<Character> zeichen) {
		super(feld);

		this.pattern = pattern;
		this.zeichen = zeichen;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = true;

		if (getFeld() != null && getFeld().getTrimmedValue() != null && zeichen != null) {
			String feldValue = getFeld().getTrimmedValue();

			// entferne alle Zeichen des Patterns, sodass nur noch die
			// Zeichen ueberprueft werden muessen
			feldValue = feldValue.replaceAll(pattern, "");

			// falls kein Zeichen mehr vorhanden ist waren es nur Buchstaben
			// oder Ziffern
			if (feldValue.length() > 0) {
				Character z = null;
				for (int i = 0; i < feldValue.length(); i++) {
					z = feldValue.charAt(i);
					if (!(zeichen.contains(z))) {
						erfolgreich = false;
						break;
					}
				}
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Wert besteht nur aus im Pattern erlaubte Zeichen und Sonderzeichen< "
							+ "Wert des Feldes fehlt oder nicht initialisierte Liste der erlaubten Werte.");
		}

		return erfolgreich;
	}
}
