package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Validiert, ob der uebergeben Wert nur aus Buchstaben (a-z, A-Z), Ziffern oder
 * uebergebenen Zeichen besteht. Umlaute sind nicht erlaubt. Fehlernummer:
 * DSME161
 */
public class PruefungBuchstabeAZazZiffernZeichen extends AbstractPruefung {

	private final List<Character> erlaubteZeichen;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 * @param erlaubteZeichen
	 *            Liste der erlaubten Zeichen
	 */
	public PruefungBuchstabeAZazZiffernZeichen(final Feld<? extends FeldName> feld,
			final List<Character> erlaubteZeichen) {
		super(feld);
		this.erlaubteZeichen = erlaubteZeichen;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = true;

		if (getFeld() != null && getFeld().getTrimmedValue() != null && erlaubteZeichen != null) {
			String feldValue = getFeld().getTrimmedValue();
			final String regEx = "[a-zA-Z0-9]";

			// entferne alle Buchstaben und Ziffern, sodass nur noch die
			// Sonderzeichen ueberprueft werden muessen
			feldValue = feldValue.replaceAll(regEx, "");

			// falls kein Zeichen mehr vorhanden ist waren es nur Buchstaben
			// oder Ziffern
			if (feldValue.length() > 0) {
				Character zeichen = null;
				for (int i = 0; i < feldValue.length(); i++) {
					zeichen = feldValue.charAt(i);
					if (!(erlaubteZeichen.contains(zeichen))) {
						erfolgreich = false;
						break;
					}
				}
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Wert besteht aus Buchstaben A-Za-z, Ziffern und Zeichen< "
							+ "Wert des Feldes fehlt oder nicht initialisierte Liste der erlaubten Werte.");
		}

		return erfolgreich;
	}

}
