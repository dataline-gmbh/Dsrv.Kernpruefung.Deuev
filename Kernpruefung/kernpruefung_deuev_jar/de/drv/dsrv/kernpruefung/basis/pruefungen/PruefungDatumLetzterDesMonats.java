package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert ob das Feld dem Ersten des Monats entspricht. Das zulaessige
 * Datumsformat ist "yyyyMMdd".
 */
public class PruefungDatumLetzterDesMonats extends AbstractPruefung {

	private final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.GERMANY);

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungDatumLetzterDesMonats(final Feld<?> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {

			final String feldValue = getFeld().getTrimmedValue();
			dateFormat.setLenient(false);

			try {
				final Calendar cal = Calendar.getInstance();
				final Date feldDatum = dateFormat.parse(feldValue);
				cal.setTime(feldDatum);
				final Integer day = cal.get(Calendar.DAY_OF_MONTH);

				if (day.equals(cal.getActualMaximum(Calendar.DAY_OF_MONTH))) {
					erfolgreich = true;
				}
			} catch (final ParseException e) {
				throw new UngueltigeDatenException("Uebergebener Wert >" + feldValue + "< ist kein gueltiges Datum: "
						+ e.toString(), e);
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Uebergebenes Datum liegt nach dem Verarbeitungsdatum< "
							+ "Wert des Feldes fehlt oder nicht initialisierter Wert Verarbeitungsdatum.");
		}

		return erfolgreich;
	}
}
