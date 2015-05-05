package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert, ob das erste und das letzte Zeichen ein Buchstabe oder eine Ziffer
 * sind. Fehlernummer: AN176.
 */
public class PruefungBeginntEndetBuchstabeZiffer extends AbstractPruefung {

	private final transient PruefungBeginntMitBuchstabe pruefungBeginntBuchstabe;
	private final transient PruefungEndetMitBuchstabe pruefungEndetBuchstabe;
	private final transient PruefungBeginntMitZiffer pruefungBeginntZiffer;
	private final transient PruefungEndetMitZiffer pruefungEndetZiffer;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungBeginntEndetBuchstabeZiffer(final Feld<?> feld) {
		super(feld);
		pruefungBeginntBuchstabe = new PruefungBeginntMitBuchstabe(feld);
		pruefungEndetBuchstabe = new PruefungEndetMitBuchstabe(feld);
		pruefungBeginntZiffer = new PruefungBeginntMitZiffer(feld);
		pruefungEndetZiffer = new PruefungEndetMitZiffer(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;
		if ((pruefungBeginntBuchstabe.pruefe() || pruefungBeginntZiffer.pruefe())
				&& (pruefungEndetBuchstabe.pruefe() || pruefungEndetZiffer.pruefe())) {
			erfolgreich = true;
		}

		return erfolgreich;
	}
}
