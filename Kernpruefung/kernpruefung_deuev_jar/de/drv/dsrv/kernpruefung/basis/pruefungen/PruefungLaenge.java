package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert, ob der uebergebene Wert die angegebene Laenge besitzt.
 */
public class PruefungLaenge extends AbstractPruefung {

	private final transient int laenge;

	/**
	 * Konstruktor.
	 * 
	 * @param laenge
	 *            zulaessige Laenge
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungLaenge(final int laenge, final Feld<?> feld) {
		super(feld);
		this.laenge = laenge;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = false;
		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			if (feldValue.length() == laenge) {
				erfolgreich = true;
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Wert besitzt eine konkrete uebergebene Laenge< "
							+ "Wert des Feldes fehlt.");
		}
		return erfolgreich;
	}
}
