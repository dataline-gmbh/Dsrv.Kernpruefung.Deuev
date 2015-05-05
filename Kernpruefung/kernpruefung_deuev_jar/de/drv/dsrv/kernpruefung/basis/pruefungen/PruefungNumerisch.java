package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;

/**
 * Validiert, ob der uebergebene Wert nur aus numerischen Zeichen besteht.
 * Fehlernummer: GB100. Fehlernummer: EU010. Fehlernummer: ST200.
 */
public class PruefungNumerisch extends AbstractPruefung {

	private final boolean numerisch;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 * @param numerisch
	 *            <code>true</code>, falls das Feld numerisch ist und keine
	 *            Leerzeichen vorkommen duerfen; <code>false</code>, falls das
	 *            Feld alphanumerisch ist
	 */
	public PruefungNumerisch(final Feld<?> feld, boolean numerisch) {
		super(feld);

		this.numerisch = numerisch;
	}

	/**
	 * Konstruktor fuer die alphanumerische Variante.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 * 
	 */
	public PruefungNumerisch(final Feld<?> feld) {
		super(feld);

		this.numerisch = false;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null && getFeld().getValue() != null) {
			String feldValue;
			if (numerisch) {
				feldValue = getFeld().getValue();
			} else {
				feldValue = getFeld().getTrimmedValue();
			}

			if (StringUtils.isNumeric(feldValue)) {
				erfolgreich = true;
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Wert besteht nur aus numerischen Zeichen< "
					+ "Wert des Feldes fehlt.");
		}
		return erfolgreich;
	}
}
