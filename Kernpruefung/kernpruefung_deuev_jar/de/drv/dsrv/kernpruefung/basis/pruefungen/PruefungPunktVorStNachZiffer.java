package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.utils.Sonderzeichen;
import de.drv.dsrv.kernpruefung.basis.utils.Zeichen;

/**
 * Validiert, ob in dem uebergebenen Wert vor einem Punkt immer "St" steht oder
 * dieser an der letzten Stelle ist und davor eine Ziffer. Fehlernummer:
 * NA/GB016.
 */
public class PruefungPunktVorStNachZiffer extends AbstractPruefung {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungPunktVorStNachZiffer(final Feld<?> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = true;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			int index = feldValue.indexOf(Sonderzeichen.FULL_STOP, 0);
			if (index == 0) {
				erfolgreich = false;
			}
			while ((index > 0) && erfolgreich) {

				boolean punktIstOk = false;

				// Punkt ist erlaubt, falls er hinter "St" steht
				if ((index > 1) && feldValue.substring(index - 2, index).equalsIgnoreCase(Zeichen.ST)) {
					punktIstOk = true;
				}
				// Punkt ist erlaubt, falls er am Ende und hinter einer Zahl
				// steht
				else if ((index == feldValue.length() - 1) && Character.isDigit(feldValue.charAt(index - 1))) {
					punktIstOk = true;
				}

				if (!punktIstOk) {
					erfolgreich = false;
				} else {
					if (index != feldValue.length() - 1) {
						index = feldValue.indexOf('.', index + 1);
						continue;
					} else {
						break;
					}
				}
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Vor einem Punkt steht immer St oder dieser ist an der letzten Stelle und davor eine Ziffer< "
							+ "Wert des Feldes fehlt.");
		}
		return erfolgreich;
	}
}
