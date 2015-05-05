package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert, ob das erste und das letzte Zeichen ein Buchstabe oder ein "+"
 * Zeichen sind. Fehlernummer: NA036.
 */
public class PruefungBeginntEndetMitBuchstabeZeichen extends AbstractPruefung {

	private final transient PruefungBeginntMitBuchstabe pruefungBeginntBuchstabe;
	private final transient PruefungEndetMitBuchstabe pruefungEndetBuchstabe;
	private final transient PruefungBeginntMitZeichen pruefungBeginntZeichen;
	private final transient PruefungEndetMitZeichen pruefungEndetZeichen;

	/**
	 * Konstruktor.
	 * 
	 * @param erlaubteZeichen
	 *            Liste mit zulaessigen Zeichen
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungBeginntEndetMitBuchstabeZeichen(final List<Character> erlaubteZeichen, final Feld<?> feld) {
		super(feld);
		pruefungBeginntZeichen = new PruefungBeginntMitZeichen(erlaubteZeichen, feld);
		pruefungEndetZeichen = new PruefungEndetMitZeichen(erlaubteZeichen, feld);
		pruefungBeginntBuchstabe = new PruefungBeginntMitBuchstabe(feld);
		pruefungEndetBuchstabe = new PruefungEndetMitBuchstabe(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;
		if ((pruefungBeginntBuchstabe.pruefe() || pruefungBeginntZeichen.pruefe())
				&& (pruefungEndetBuchstabe.pruefe() || pruefungEndetZeichen.pruefe())) {
			erfolgreich = true;
		}

		return erfolgreich;
	}
}
