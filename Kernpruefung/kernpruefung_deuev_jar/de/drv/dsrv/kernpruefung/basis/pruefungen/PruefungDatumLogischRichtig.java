package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert die logische Richtigkeit des uebergebenen Datums. Fehlernummer:
 * GB104. Fehlernummer: ST201.
 */
public class PruefungDatumLogischRichtig extends AbstractPruefung {

	// default DateFormat
	private DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.GERMANY);

	/**
	 * Konstruktor mit default DateFormat <code>yyyyMMdd</code>.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungDatumLogischRichtig(final Feld<?> feld) {
		super(feld);
	}

	/**
	 * Konstruktor mit waehlbarem DateFormat.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 * @param dateFormat
	 *            das Datumsformat des zu pruefenden Wertes
	 */
	public PruefungDatumLogischRichtig(final Feld<?> feld, DateFormat dateFormat) {
		super(feld);
		this.dateFormat = dateFormat;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			this.dateFormat.setLenient(false);
			try {
				dateFormat.parse(feldValue);
				erfolgreich = true;
			} catch (final ParseException e) {
				;
				// Ausnahme wird nicht weitergeleitet, das es richtig ist, dass
				// die Pruefung fehl schlaegt.
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Wert ist ein logisch richtiges Datum< "
					+ "Wert des Feldes fehlt.");
		}

		return erfolgreich;
	}
}
