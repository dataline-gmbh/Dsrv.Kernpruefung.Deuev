package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Validiert, ob das uebergeben Datum zwischen zwei Daten liegt. Der zu
 * untersuchende Datumsbereich versteht sich inklusive der angegebenen
 * Datumsgrenzen. Der Wert des Feldes muss im Format yyyyMMdd uebergeben werden.
 */
public class PruefungDatumIntervall extends AbstractPruefung {

	private Date datU;
	private Date datO;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 * @param datumsUntergrenze
	 * @param datumsObergrenze
	 */
	public PruefungDatumIntervall(final Feld<? extends FeldName> feld, final Date datumsUntergrenze,
			final Date datumsObergrenze) {
		super(feld);
		this.datU = datumsUntergrenze;
		this.datO = datumsObergrenze;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null && datU != null && datO != null) {
			final String feldValue = getFeld().getTrimmedValue();

			// Uhrzeit entfernen, fuer Vergleiche am selben Tag
			final Calendar calU = Calendar.getInstance();
			calU.setTime(datU);
			calU.set(Calendar.HOUR_OF_DAY, 0);
			calU.set(Calendar.MINUTE, 0);
			calU.set(Calendar.SECOND, 0);
			datU = calU.getTime();

			final Calendar calO = Calendar.getInstance();
			calO.setTime(datO);
			calO.set(Calendar.HOUR_OF_DAY, 0);
			calO.set(Calendar.MINUTE, 0);
			calO.set(Calendar.SECOND, 0);
			datO = calO.getTime();

			final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.GERMANY);
			dateFormat.setLenient(false);
			try {
				final Date feldDatum = dateFormat.parse(feldValue);

				if (feldDatum.compareTo(datU) >= 0 && feldDatum.compareTo(datO) <= 0) {
					erfolgreich = true;
				}
			} catch (final ParseException e) {
				throw new UngueltigeDatenException("Uebergebener Wert >" + feldValue + "< ist kein gueltiges Datum: "
						+ e.toString(), e);
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Uebergebenes Datum liegt im Datumsbereich< "
					+ "Wert des Feldes fehlt oder nicht initialisierter Wert Verarbeitungsdatum.");
		}

		return erfolgreich;
	}
}
