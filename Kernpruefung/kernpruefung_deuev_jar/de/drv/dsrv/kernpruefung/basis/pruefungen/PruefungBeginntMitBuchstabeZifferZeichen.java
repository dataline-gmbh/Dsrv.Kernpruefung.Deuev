package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Validiert, ob das erste Zeichen ein Buchstabe, eine Ziffer oder mit einem
 * Zeichen beginnt aus der Liste erlaubteZeichen ist. Fehlernummer: AN160.
 */

public class PruefungBeginntMitBuchstabeZifferZeichen extends AbstractPruefung {

	private final transient PruefungBeginntMitBuchstabe pruefungBeginntBuchstabe;
	private final transient PruefungBeginntMitZiffer pruefungBeginntZiffer;
	private final transient PruefungBeginntMitZeichen pruefungBeginntZeichen;

	/**
	 * Konstruktor.
	 * 
	 * @param erlaubteZeichen
	 *            Liste der erlaubten Zeichen
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 * 
	 */
	public PruefungBeginntMitBuchstabeZifferZeichen(final List<Character> erlaubteZeichen, Feld<? extends FeldName> feld) {
		super(feld);
		pruefungBeginntBuchstabe = new PruefungBeginntMitBuchstabe(feld);
		pruefungBeginntZiffer = new PruefungBeginntMitZiffer(feld);
		pruefungBeginntZeichen = new PruefungBeginntMitZeichen(erlaubteZeichen, feld);

	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;
		try {
			if ((pruefungBeginntBuchstabe.pruefe() || pruefungBeginntZiffer.pruefe() || pruefungBeginntZeichen.pruefe())) {
				erfolgreich = true;
			}
		} catch (UngueltigeDatenException e) {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Wert beginnt mit einem Buchstabe, einer Ziffer oder einem Zeichen< "
							+ "Wert des Feldes fehlt.");
		}

		return erfolgreich;
	}
}
