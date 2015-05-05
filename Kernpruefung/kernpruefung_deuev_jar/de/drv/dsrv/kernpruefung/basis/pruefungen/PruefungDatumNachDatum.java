package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;

/**
 * Validiert, ob das uebergebe Datum (feld) nach oder am selben Tag des
 * uebergeben Datums liegt. Der Wert des Feldes muss im Format yyyyMMdd
 * uebergeben werden.
 */
public class PruefungDatumNachDatum extends AbstractPruefung {

	private Date datO;
	private final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.GERMANY);

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 * @param datumsObergrenze
	 */
	public PruefungDatumNachDatum(final Feld<? extends FeldName> feld, final Date datumsObergrenze) {
		super(feld);
		this.datO = datumsObergrenze;
	}

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 * @param feld2
	 * @throws UngueltigeDatenException
	 */
	public PruefungDatumNachDatum(final Feld<? extends FeldName> feld, final Feld<? extends FeldName> feld2)
			throws UngueltigeDatenException {
		super(feld);

		if (feld2 != null && StringUtils.isNotBlank(feld2.getTrimmedValue())) {

			final String feld2Value = feld2.getTrimmedValue();
			dateFormat.setLenient(false);

			try {
				this.datO = dateFormat.parse(feld2Value);
			} catch (final ParseException e) {
				throw new UngueltigeDatenException("Uebergebener Wert >" + feld2Value + "< ist kein gueltiges Datum: "
						+ e.toString(), e);
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Uebergebenes Datum liegt nach dem Verarbeitungsdatum< "
							+ "Wert des Feldes fehlt oder nicht initialisierter Wert Verarbeitungsdatum.");
		}
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null && datO != null) {

			final String feldValue = getFeld().getTrimmedValue();

			// Uhrzeit entfernen, fuer Vergleiche am selben Tag
			final Calendar cal = Calendar.getInstance();
			cal.setTime(datO);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			datO = cal.getTime();

			dateFormat.setLenient(false);
			try {
				final Date feldDatum = dateFormat.parse(feldValue);

				if (feldDatum.compareTo(datO) >= 0) {
					erfolgreich = true;
				}
			} catch (final ParseException e) {
				if (LOGGER.isLoggable(Level.FINE)) {
					LOGGER.log(Level.FINE, e.toString());
				}
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
