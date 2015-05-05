package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert, ob der uebergebene Wert eine definierte Mindestlaenge besitzt.
 * Fehlernummer: AN158.
 */
public class PruefungMinLaenge extends AbstractPruefung {

	private final transient int minLaenge;

	/**
	 * Konstruktor.
	 * 
	 * @param minLaenge
	 *            Mindestlaenge des Feldes
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungMinLaenge(final int minLaenge, final Feld<?> feld) {
		super(feld);
		this.minLaenge = minLaenge;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			if (feldValue.length() >= minLaenge) {
				erfolgreich = true;
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Wert besitzt eine definierte Mindestlaenge< "
					+ "Wert des Feldes fehlt.");
		}
		return erfolgreich;
	}
}
