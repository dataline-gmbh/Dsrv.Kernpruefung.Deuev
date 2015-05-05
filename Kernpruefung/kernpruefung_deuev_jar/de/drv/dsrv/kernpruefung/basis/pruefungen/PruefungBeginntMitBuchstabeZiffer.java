package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert, ob das erste Zeichen ein Buchstabe oder eine Ziffer ist.
 * Fehlernummer: AN185.
 */
public class PruefungBeginntMitBuchstabeZiffer extends AbstractPruefung {

	private final transient PruefungBeginntMitBuchstabe pruefungBeginntBuchstabe;
	private final transient PruefungBeginntMitZiffer pruefungBeginntZiffer;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungBeginntMitBuchstabeZiffer(final Feld<?> feld) {
		super(feld);
		pruefungBeginntBuchstabe = new PruefungBeginntMitBuchstabe(feld);
		pruefungBeginntZiffer = new PruefungBeginntMitZiffer(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;
		if ((pruefungBeginntBuchstabe.pruefe() || pruefungBeginntZiffer.pruefe())) {
			erfolgreich = true;
		}

		return erfolgreich;
	}
}
