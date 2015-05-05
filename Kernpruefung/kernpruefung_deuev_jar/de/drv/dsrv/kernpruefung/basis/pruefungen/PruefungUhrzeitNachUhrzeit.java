package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Validiert, ob die uebergebene Uhrzeit nach der Uhrzeit, die im Feld angegeben
 * ist, ist, falls es derselbe Tag ist. Oder einfacher gesagt: Validiert, ob der
 * uebergebene Zeitpunkt spaeter ist. Fehlernummer: DSME058
 */
public class PruefungUhrzeitNachUhrzeit extends AbstractPruefung {

	private final Date vergleichsDatum;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 * @param vergleichsDatum
	 *            Datum mit der evtl. gleichen Uhrzeit
	 */
	public PruefungUhrzeitNachUhrzeit(final Feld<? extends FeldName> feld, final Date vergleichsDatum) {
		super(feld);

		this.vergleichsDatum = vergleichsDatum;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null && vergleichsDatum != null) {
			final String feldValue = getFeld().getTrimmedValue();

			final DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.GERMANY);
			dateFormat.setLenient(false);
			try {
				final Date feldDatum = dateFormat.parse(feldValue);

				if (feldDatum.before(vergleichsDatum)) {
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
					"Fehler in der Pruefung >Uebergebene Uhrzeit liegt nach dem Verarbeitungsdatum< "
							+ "Wert des Feldes fehlt oder nicht initialisierter Wert Verarbeitungsdatum.");
		}

		return erfolgreich;
	}

}
