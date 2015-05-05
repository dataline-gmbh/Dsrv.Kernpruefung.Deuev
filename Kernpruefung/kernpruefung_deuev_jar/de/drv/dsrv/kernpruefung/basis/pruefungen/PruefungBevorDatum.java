package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert, der Wert des zu pruefenden Feldes ein Datum im Format
 * <code>yyyyMMdd</code> ist und vor oder gleich dem angegebenen Datum (z.B.
 * Verarbeitungsdatum) ist.
 */
public class PruefungBevorDatum extends AbstractPruefung {

	private final transient Date datum;
	private final transient boolean gleichesDatum;

	/**
	 * Konstruktor.
	 * 
	 * @param datum
	 *            Verarbeitungsdatum
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungBevorDatum(final Date datum, final Feld<?> feld) {
		super(feld);
		this.datum = datum;
		this.gleichesDatum = false;
	}

	/**
	 * Konstruktor.
	 * 
	 * @param datum
	 *            Verarbeitungsdatum
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 * @param gleichesDatum
	 *            soll im Vergleich das gleiche Datum als Erfolg gezaehlt werden
	 */
	public PruefungBevorDatum(final Date datum, final Feld<?> feld, boolean gleichesDatum) {
		super(feld);
		this.datum = datum;
		this.gleichesDatum = gleichesDatum;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = false;
		if (getFeld() != null && getFeld().getTrimmedValue() != null && datum != null) {
			final String feldValue = getFeld().getTrimmedValue();
			final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.GERMANY);
			dateFormat.setLenient(false);

			try {
				final Date feldDatum = dateFormat.parse(feldValue);

				if (datum.after(feldDatum) || (gleichesDatum && datum.equals(feldDatum))) {
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
			throw new UngueltigeDatenException("Fehler in der Pruefung >Datum liegt nach Vearbeitungsdatum< "
					+ "Verarbeitungsdatum und/oder Wert des Feldes fehlt.");
		}

		return erfolgreich;
	}
}
