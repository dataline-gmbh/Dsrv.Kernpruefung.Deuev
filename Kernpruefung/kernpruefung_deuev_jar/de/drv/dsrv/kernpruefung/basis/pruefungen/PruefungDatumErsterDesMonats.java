package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert ob das Feld dem Ersten des Monats entspricht. Das zulaessige
 * Datumsformat ist "yyyyMMdd".
 */
public class PruefungDatumErsterDesMonats extends AbstractPruefung {

	private static final String ERSTER = "01";
	private static final int DAYSTART = 6;
	private static final int DAYEND = 8;
	private static final int LAENGE = 8;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungDatumErsterDesMonats(final Feld<?> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			if (feldValue.length() >= LAENGE && feldValue.substring(DAYSTART, DAYEND).equals(ERSTER)) {
				erfolgreich = true;
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Wert ist ein logisch richtiges Datum< "
					+ "Wert des Feldes fehlt.");
		}

		return erfolgreich;
	}
}
