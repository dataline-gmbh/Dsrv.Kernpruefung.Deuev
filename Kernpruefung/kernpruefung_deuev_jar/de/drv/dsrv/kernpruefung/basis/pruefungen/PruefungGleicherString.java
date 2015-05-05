package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Validiert ob der getrimmte Feld-Wert mit dem uebergebenen Wert identisch ist
 * (case insensitive).
 */
public class PruefungGleicherString extends AbstractPruefung {

	private final String vergleich;
	private final boolean sollTrimmen;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu vergleichenden Wert
	 * @param vergleich
	 *            der zu vergleichende Wert
	 */
	public PruefungGleicherString(final Feld<? extends FeldName> feld, final String vergleich) {
		this(feld, vergleich, true);
	}

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu vergleichenden Wert.
	 * @param vergleich
	 *            der zu vergleichende Wert.
	 * @param trimmen
	 *            bei <code>true</code> wird auf den getrimmten Feldwert
	 *            verglichen. Bei <code>false</code> wird auf den den
	 *            ungetrimmten Feldwert verglichen. <br/>
	 *            <i>Beispiel</i>: trimmen=true, Feldwert="foo ",
	 *            Vergleichswert="foo" => Rückgabewert=true
	 */
	public PruefungGleicherString(final Feld<? extends FeldName> feld, final String vergleich, boolean trimmen) {
		super(feld);
		this.vergleich = vergleich;
		sollTrimmen = trimmen;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = true;

		if (getFeld() != null && getFeld().getValue() != null) {
			final String feldValue = (sollTrimmen) ? getFeld().getTrimmedValue() : getFeld().getValue();

			if (!feldValue.equalsIgnoreCase(vergleich)) {
				erfolgreich = false;
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >gleicher String< " + "Wert des Feldes fehlt.");
		}

		return erfolgreich;
	}

}
