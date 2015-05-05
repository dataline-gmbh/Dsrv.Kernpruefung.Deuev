package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Prueft, ob der Feldwert groesser oder gleich dem uebergebenen Vergleichswert
 * ist.
 */
public class PruefungGroesserGleich extends AbstractPruefung {

	private final int vergleichsWert;

	public PruefungGroesserGleich(Feld<? extends FeldName> feld, int vergleichsWert) {
		super(feld);

		this.vergleichsWert = vergleichsWert;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {

			int feldWert;

			try {

				feldWert = Integer.parseInt(getFeld().getTrimmedValue());

				if (feldWert >= vergleichsWert) {
					erfolgreich = true;
				}

			} catch (NumberFormatException e) {
				// Pruefung wird mit Rueckgabewert false als nicht erfolgreich
				// beendet.
				return erfolgreich;
			}
		}

		return erfolgreich;
	}

}
