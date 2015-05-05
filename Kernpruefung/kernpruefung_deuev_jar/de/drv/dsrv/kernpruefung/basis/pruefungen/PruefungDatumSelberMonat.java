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

/**
 * Validiert, ob beide Daten dengleichen Monat (also dasselbe Jahr) haben.
 * Fehlernummer: KV056
 */
public class PruefungDatumSelberMonat extends AbstractPruefung {

	private final Date vergleichsDatum;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 * @param vergleichsDatum
	 *            Datum mit dem evtl. selben Monat
	 */
	public PruefungDatumSelberMonat(final Feld<? extends FeldName> feld, final Date vergleichsDatum) {
		super(feld);

		this.vergleichsDatum = vergleichsDatum;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = true;

		if (getFeld() != null && getFeld().getTrimmedValue() != null && vergleichsDatum != null) {

			final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.GERMANY);
			dateFormat.setLenient(false);
			Date datumFeld = null;
			try {
				datumFeld = dateFormat.parse(getFeld().getTrimmedValue());
			} catch (ParseException e) {
				erfolgreich = false;
				if (LOGGER.isLoggable(Level.FINE)) {
					LOGGER.log(Level.FINE, "Uebergebener Wert ist nicht im Format yyyyMMdd \""
							+ getFeld().getTrimmedValue() + "\".");
				}
			}

			if (erfolgreich) {
				final Calendar cal = Calendar.getInstance();
				cal.setTime(datumFeld);

				final Calendar cal2 = Calendar.getInstance();
				cal2.setTime(vergleichsDatum);

				erfolgreich = cal.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
						&& cal.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Uebergebenes Datum liegt im selben Monat< "
					+ "Wert des Feldes oder Vergleichsdatum fehlt.");
		}

		return erfolgreich;
	}
}
