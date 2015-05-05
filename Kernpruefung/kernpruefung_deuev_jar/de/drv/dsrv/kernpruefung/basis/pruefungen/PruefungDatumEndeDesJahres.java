package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert ob das Feld dem Ende eines Jahres entspricht (also dem 31.12.). Das
 * zulaessige Datumsformat ist "yyyyMMdd".
 */
public class PruefungDatumEndeDesJahres extends AbstractPruefung {

	private static final String JAHRESENDE = "1231";
	private static final int MONTHSTART = 4;
	private static final int DAYEND = 8;
	private static final int LAENGE = 8;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungDatumEndeDesJahres(final Feld<?> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			if (feldValue.length() >= LAENGE && feldValue.substring(MONTHSTART, DAYEND).equals(JAHRESENDE)) {
				erfolgreich = true;
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Wert ist ein logisch richtiges Datum< "
					+ "Wert des Feldes fehlt.");
		}

		return erfolgreich;
	}
}
