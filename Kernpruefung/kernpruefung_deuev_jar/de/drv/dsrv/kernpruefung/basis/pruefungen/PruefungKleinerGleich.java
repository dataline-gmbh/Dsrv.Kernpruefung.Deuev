package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Prueft, ob der Wert eines Feldes kleiner/gleich zu einem Vergleichswert ist.
 * Hinweis: Dezimalseparator ist 'Punkt'.
 */
public class PruefungKleinerGleich extends AbstractPruefung {

	private final Double vergleichsWert;

	// Definiert wo das Komma gesetzt wird. 0 = es wird eine ganze Zahl
	// uebergeben, 2 = es gibt 2 Nachkommastellen. Aus 12345 wird dann z.B.
	// 123,45
	private final int nachkommastellen;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 * @param vergleichsWert
	 */
	public PruefungKleinerGleich(final Feld<?> feld, Double vergleichsWert) {
		this(feld, vergleichsWert, 0);
	}

	public PruefungKleinerGleich(final Feld<?> feld, Double vergleichsWert, final int nachkommastellen) {
		super(feld);
		this.nachkommastellen = nachkommastellen;
		this.vergleichsWert = vergleichsWert;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getValue() != null && this.vergleichsWert != null) {
			try {
				final StringBuilder builder = new StringBuilder(getFeld().getTrimmedValue());
				builder.insert(builder.length() - nachkommastellen, '.');
				final Double feldValue = Double.parseDouble(builder.toString());

				if (Double.compare(feldValue, this.vergleichsWert) <= 0) {
					erfolgreich = true;
				}
			} catch (final NumberFormatException e) {
				throw new UngueltigeDatenException("Uebergebener Wert >" + getFeld().getTrimmedValue()
						+ "< ist kein gueltiger Zahlenwert: " + e.toString(), e);
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Wert kleiner gleich Vergleichswert< "
					+ "Wert des Feldes fehlt oder nicht initialisierter Wert Verarbeitungsdatum.");
		}

		return erfolgreich;
	}
}
