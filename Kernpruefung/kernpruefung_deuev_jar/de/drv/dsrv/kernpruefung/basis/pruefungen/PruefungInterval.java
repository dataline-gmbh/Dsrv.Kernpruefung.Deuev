package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert, ob der uebergebene Wert in dem Interval [von, bis] ist.<br/>
 * <br/>
 * <b>Beachte:</b> Fuehrende Nullen des Feldwertes werden bei dieser Pruefung
 * ignoriert.
 * 
 */
public class PruefungInterval extends AbstractPruefung {

	private final transient int von;
	private final transient int bis;

	/**
	 * Konstruktor.
	 * 
	 * @param von
	 *            Anfang des zulaessigen Intervals
	 * @param bis
	 *            Ende des zulaessigen Intervals
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungInterval(final int von, final int bis, final Feld<?> feld) {
		super(feld);
		this.von = von;
		this.bis = bis;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			try {
				final int val = Integer.parseInt(feldValue);
				if (val >= von && val <= bis) {
					erfolgreich = true;
				}
			} catch (final NumberFormatException e) {
				// Bei einer Exception wird erfolgreich nicht auf true gesetzt
				// und somit ein Fehlerbaustein angehaengt.
				;
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Wert ist in dem Interval [von, bis]< "
					+ "Wert des Feldes fehlt.");
		}
		return erfolgreich;
	}

}
