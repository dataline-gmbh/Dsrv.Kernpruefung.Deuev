package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.utils.Sonderzeichen;

/**
 * Validiert, ob gleiche Sonder- und Leerzeichen mehrfach aufeinanderfolgen.
 * Fehlernummer: NA/GB010. Fehlernummer: NA030. Fehlernummer: AN180.
 */
public class PruefungMehrfachGleichesSonderOderLeerzeichen extends AbstractPruefung {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungMehrfachGleichesSonderOderLeerzeichen(final Feld<?> feld) {
		super(feld);
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
					if ((Sonderzeichen.ALL_SPECIAL_CHARACTERS.contains(Character.toString(vorgaenger)))
							&& nachfolger.equals(vorgaenger)) {
						fehlerStelle++;
					}
				}
			}
			if (fehlerStelle >= 1) {
				erfolgreich = false;
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Gleiche Sonder- und Leerzeichen folgen mehrfach aufeinander< "
							+ "Wert des Feldes fehlt.");
		}
		return erfolgreich;
	}
}
