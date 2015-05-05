package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.HashSet;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Diese Pruefung ermittelt, ob eine Zeichenkette ausschliesslich aus
 * erlaubten/qualifizierten Zeichen besteht. Ein Zeichen wird dabei als
 * erlaubt/qualifiziert bezeichnet, wenn es im Vergleichszeichensatz enthalten
 * ist.
 */
public class PruefungBestehtAusZeichensatz extends AbstractPruefung {

	private final HashSet<Character> zeichensatz;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            das zu ueberpruefende Feld, dessen Feldwert validiert wird.
	 * @param erlaubterZeichensatz
	 *            die Menge an Zeichen, die als erlaubt/qualifiziert gilt.
	 *            Ausschliesslich Zeichen dieser Menge sind im Feldwert erlaubt.
	 */
	public PruefungBestehtAusZeichensatz(Feld<? extends FeldName> feld, String erlaubterZeichensatz) {
		super(feld);

		this.zeichensatz = new HashSet<Character>();
		for (char aktuellesZeichen : erlaubterZeichensatz.toCharArray()) {
			this.zeichensatz.add(aktuellesZeichen);
		}
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean ergebnis = false;

		if (getFeld() != null && getFeld().getValue() != null) {
			final String feldWert = getFeld().getValue();

			// Der leere String ist immer korrekt!
			ergebnis = true;

			for (int i = 0; i < feldWert.length(); i++) {
				if (!zeichensatz.contains(feldWert.charAt(i))) {
					ergebnis = false;
					break;
				}
			}

		}

		return ergebnis;
	}

}
