package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.basis.utils.Zeichen;

/**
 * Validiert, ob der uebergebene Wert mit einem Buchstaben oder Zeichen beginnt.
 * Fehlernummer: GB020.
 */
public class PruefungBeginntMitBuchstabeZeichen extends AbstractPruefung {

	private final List<Character> erlaubteZeichen;

	/**
	 * Konstruktor.
	 * 
	 * @param erlaubteZeichen
	 *            Liste mit zulaessigen Zeichen
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungBeginntMitBuchstabeZeichen(final List<Character> erlaubteZeichen, final Feld<?> feld) {
		super(feld);
		this.erlaubteZeichen = erlaubteZeichen;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			if (StringUtils.isNotEmpty(feldValue)) {
				final Character erstesZeichen = feldValue.charAt(0);
				if (Zeichen.ALL_ALPHABETIC_CHARACTERS.contains(erstesZeichen.toString())
						|| erlaubteZeichen.contains(erstesZeichen)) {
					erfolgreich = true;
				}
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Wert beginnt mit einem Buchstaben oder Zeichen< "
							+ "Wert des Feldes fehlt.");
		}
		return erfolgreich;
	}

}
