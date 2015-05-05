package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert, ob das uebergebene Datum nicht mehr als angegebene Anzahl der
 * Jahre vor einem konkreten Datum (z.B. Verarbeitungsdatum) liegt und ob der
 * uebergebene Wert ein gueltiges Datum im Format <code>yyyyMMdd</code> ist.
 */
public class PruefungDatumJahreVorDatum extends AbstractPruefung {

	private final transient Date datum;
	private final transient int jahre;

	private final transient boolean gleichesDatum;

	/**
	 * Konstruktor.
	 * 
	 * @param datum
	 *            Datum
	 * @param jahre
	 *            Anzahl der Jahre
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungDatumJahreVorDatum(final Date datum, final int jahre, final Feld<?> feld) {
		super(feld);
		this.jahre = jahre;
		this.datum = datum;
		this.gleichesDatum = false;
	}

	/**
	 * Konstruktor.
	 * 
	 * @param datum
	 *            Datum
	 * @param jahre
	 *            Anzahl der Jahre
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 * @param gleichesDatum
	 *            soll im Vergleich das gleiche Datum als Erfolg gezaehlt werden
	 */
	public PruefungDatumJahreVorDatum(final Date datum, final int jahre, final Feld<?> feld, boolean gleichesDatum) {
		super(feld);
		this.jahre = jahre;
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

				final Calendar calendar = Calendar.getInstance();
				calendar.setTime(datum);
				calendar.add(Calendar.YEAR, -this.jahre);
				final Date verarbJahr = calendar.getTime();

				if (verarbJahr.before(feldDatum) || (gleichesDatum && verarbJahr.equals(feldDatum))) {
					erfolgreich = true;
				}
			} catch (final ParseException e) {
				throw new UngueltigeDatenException("Uebergebener Wert >" + feldValue + "< ist kein gueltiges Datum: "
						+ e.toString(), e);
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Uebergebenes Datum liegt vor dem Verarbeitungsdatum< "
							+ "Wert des Feldes fehlt oder nicht initialisierter Wert Verarbeitungsdatum.");
		}

		return erfolgreich;
	}
}
