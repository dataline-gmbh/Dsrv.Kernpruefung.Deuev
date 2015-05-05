package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.utils.Zeichen;

/**
 * Validiert, ob der uebergebene Wert nur aus Buchstaben und angegebenen
 * Sonderzeichen besteht, die mehrfach vorkommen duerfen. Fehlernummer: NA044.
 */
public class PruefungBuchstabenZeichen extends AbstractPruefung {

	private final transient List<Character> erlaubteZeichen;

	/**
	 * Konstruktor.
	 * 
	 * @param erlaubteZeichen
	 *            Liste mit zulaessigen Zeichen
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungBuchstabenZeichen(final List<Character> erlaubteZeichen, final Feld<?> feld) {
		super(feld);
		this.erlaubteZeichen = erlaubteZeichen;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = true;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			Character zeichen = null;
			for (int i = 0; i < feldValue.length(); i++) {
				zeichen = feldValue.charAt(i);
				if (!(Zeichen.ALL_ALPHABETIC_CHARACTERS.contains(zeichen.toString()) || erlaubteZeichen
						.contains(zeichen))) {
					erfolgreich = false;
					break;
				}
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Wert besteht nur aus Buchstaben oder Sonderzeichen< "
							+ "Wert des Feldes fehlt oder nicht initialisierte Liste der erlaubten Werte");
		}
		return erfolgreich;
	}
}
