package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert, ob der uebergebene Wert eine definierte Maximallaenge besitzt.
 * Fehlernummer: UV122.
 */
public class PruefungMaxLaenge extends AbstractPruefung {

	private final transient int maxLaenge;

	/**
	 * Konstruktor.
	 * 
	 * @param MaxLaenge
	 *            Maximallaenge des Feldes
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungMaxLaenge(final int MaxLaenge, final Feld<?> feld) {
		super(feld);
		this.maxLaenge = MaxLaenge;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			if (feldValue.length() <= maxLaenge) {
				erfolgreich = true;
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Wert besitzt eine definierte Maximallaenge< "
					+ "Wert des Feldes fehlt.");
		}
		return erfolgreich;
	}
}
