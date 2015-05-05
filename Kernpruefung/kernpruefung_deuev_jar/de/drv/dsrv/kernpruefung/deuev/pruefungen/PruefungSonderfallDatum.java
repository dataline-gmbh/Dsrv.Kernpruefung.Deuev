package de.drv.dsrv.kernpruefung.deuev.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.AbstractPruefung;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;

/**
 * Validiert, ob ein uebergebenes Datum 00 als Tag enthaelt, wenn die
 * Monatsangabe 00 ist. Fehlernummer: GB102.
 */
public class PruefungSonderfallDatum extends AbstractPruefung {

	private static final int LAENGE_DATUM = 8;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungSonderfallDatum(final Feld<?> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = true;
		final String feldValue = getFeld().getTrimmedValue();

		if (feldValue != null && feldValue.length() == LAENGE_DATUM) {
			if (!StringUtils.isNumeric(feldValue)) {
				throw new UngueltigeDatenException("uebergebener Wert >" + feldValue
						+ "< ist kein gueltiges Datum. Mindestens ein Wert ist keine Ziffer.");
			}
			final String monat = feldValue.substring(4, 6);
			final String tag = feldValue.substring(6, 8);
			if ("00".equals(monat) && !"00".equals(tag)) {
				erfolgreich = false;
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Datum enthaelt 00 als Tag, wenn die Monatsangabe 00 ist< "
							+ "Wert des Feldes fehlt.");
		}

		return erfolgreich;
	}
}
