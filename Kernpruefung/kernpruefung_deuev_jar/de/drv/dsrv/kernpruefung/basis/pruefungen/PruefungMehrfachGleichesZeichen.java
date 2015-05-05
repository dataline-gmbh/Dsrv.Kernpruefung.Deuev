package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Validiert, ob ein Zeichen mehrfach aufeinander folgt (verboten).
 * Fehlernummer: AN024;
 */
public class PruefungMehrfachGleichesZeichen extends AbstractPruefung {

	private final List<Character> verboteneZeichen;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 * @param verboteneZeichen
	 *            Liste der verbotenen Zeichen
	 */
	public PruefungMehrfachGleichesZeichen(Feld<? extends FeldName> feld, final List<Character> verboteneZeichen) {
		super(feld);

		this.verboteneZeichen = verboteneZeichen;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = true;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			int fehlerStelle = 0;

			Character vorgaenger = null;
			Character nachfolger = null;

			for (int i = 0; i < feldValue.length(); i++) {
				nachfolger = vorgaenger;
				vorgaenger = feldValue.charAt(i);
				if (vorgaenger != null && nachfolger != null) {
					if ((verboteneZeichen.contains(vorgaenger)) && nachfolger.equals(vorgaenger)) {
						fehlerStelle++;
					}
				}
			}
			if (fehlerStelle >= 1) {
				erfolgreich = false;
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Gleiche Zeichen folgen mehrfach aufeinander< "
					+ "Wert des Feldes fehlt.");
		}
		return erfolgreich;
	}

}
