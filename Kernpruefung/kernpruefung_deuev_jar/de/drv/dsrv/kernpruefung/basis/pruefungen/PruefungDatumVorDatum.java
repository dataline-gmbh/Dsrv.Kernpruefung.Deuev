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
 * Validiert, ob das uebergebe Datum (feld) vor oder am selben Tag des
 * uebergeben Datums liegt. Der Wert des Feldes muss im Format yyyyMMdd
 * uebergeben werden.
 */
public class PruefungDatumVorDatum extends AbstractPruefung {

	private Date datU;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 * @param datumsUntergrenze
	 */
	public PruefungDatumVorDatum(final Feld<? extends FeldName> feld, final Date datumsUntergrenze) {
		super(feld);
		this.datU = datumsUntergrenze;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null && datU != null) {

			final String feldValue = getFeld().getTrimmedValue();

			// Uhrzeit entfernen, fuer Vergleiche am selben Tag
			final Calendar cal = Calendar.getInstance();
			cal.setTime(datU);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			datU = cal.getTime();

			final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.GERMANY);
			dateFormat.setLenient(false);
			try {
				final Date feldDatum = dateFormat.parse(feldValue);

				if (feldDatum.compareTo(datU) <= 0) {
					erfolgreich = true;
				}
			} catch (final ParseException e) {
				throw new UngueltigeDatenException("Uebergebener Wert >" + feldValue + "< ist kein gueltiges Datum: "
						+ e.toString(), e);
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Uebergebenes Datum liegt vor dem Verarbeitungsdatum< "
							+ "Wert des Feldes oder Datumuntergrenze fehlt.");
		}

		return erfolgreich;
	}

}
