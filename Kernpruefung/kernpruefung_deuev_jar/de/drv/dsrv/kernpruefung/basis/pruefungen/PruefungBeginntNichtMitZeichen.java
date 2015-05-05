package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert, ob der uebergebene Wert mit einem Zeichen aus der Liste
 * verboteneZeichen beginnt. Fehlernummer: GB020.
 */
public class PruefungBeginntNichtMitZeichen extends AbstractPruefung {

	private final transient PruefungBeginntMitZeichen pruefungBeginntZeichen;

	/**
	 * Konstruktor.
	 * 
	 * @param verboteneZeichen
	 *            Liste mit unzulaessigen Zeichen
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungBeginntNichtMitZeichen(final List<Character> verboteneZeichen, final Feld<?> feld) {
		super(feld);
		pruefungBeginntZeichen = new PruefungBeginntMitZeichen(verboteneZeichen, feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;
		if (!pruefungBeginntZeichen.pruefe()) {
			erfolgreich = true;
		}

		return erfolgreich;
	}
}
