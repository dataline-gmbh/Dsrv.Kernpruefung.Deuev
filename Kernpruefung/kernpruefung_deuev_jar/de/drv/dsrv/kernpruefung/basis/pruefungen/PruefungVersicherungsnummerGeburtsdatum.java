package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Validiert das Geburtsdatum der Versicherungsnummer nach Ziffer 3.1.1.2.
 * Fehlernummer: VR084.
 */
public class PruefungVersicherungsnummerGeburtsdatum extends AbstractPruefung {

	private static final int LAENGE_DATUM = 6;
	private static final int ANZAHL_MONATE = 12;
	private static final int MODULO = 32;
	private static final int INDEX_TAG_BEGINN = 0;
	private static final int INDEX_TAG_ENDE = 2;
	private static final int INDEX_MONAT_BEGINN = INDEX_TAG_ENDE;
	private static final int INDEX_MONAT_ENDE = 4;
	private static final int INDEX_JAHR_BEGINN = INDEX_MONAT_ENDE;
	private static final int INDEX_JAHR_ENDE = 6;

	private static final int TAG_UNZULAESSIG_MONAT_0 = 97;

	private static final List<Integer> TAGE_UNZULAESSIG = Arrays.asList(96, 98, 99);

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungVersicherungsnummerGeburtsdatum(final Feld<? extends FeldName> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = false;

		if (getFeld().getTrimmedValue().length() == LAENGE_DATUM) {
			try {
				int tag = Integer.parseInt(getFeld().getTrimmedValue().substring(INDEX_TAG_BEGINN, INDEX_TAG_ENDE));
				final int tagOrig = tag;
				final int monat = Integer.parseInt(getFeld().getTrimmedValue().substring(INDEX_MONAT_BEGINN,
						INDEX_MONAT_ENDE));

				final int jahr = Integer.parseInt(getFeld().getTrimmedValue().substring(INDEX_JAHR_BEGINN,
						INDEX_JAHR_ENDE));

				if (!TAGE_UNZULAESSIG.contains(tag) && monat >= 0 && monat <= ANZAHL_MONATE) {
					tag = tag % MODULO;

					if (monat == 0 && tagOrig != TAG_UNZULAESSIG_MONAT_0) {
						erfolgreich = true;
					} else if (tag == 0) {
						erfolgreich = true;
					} else {
						final DateFormat dateFormat = new SimpleDateFormat("ddMMyy", Locale.GERMANY);
						dateFormat.setLenient(false);
						try {
							final DecimalFormat df = new DecimalFormat("00");
							final String datum = df.format(tag) + df.format(monat) + df.format(jahr);
							dateFormat.parse(datum);
							erfolgreich = true;
						} catch (final ParseException e) {
							if (LOGGER.isLoggable(Level.FINE)) {
								LOGGER.log(Level.FINE, e.toString());
							}
							// Ausnahme wird nicht weitergeleitet, das es
							// richtig ist, dass
							// die Pruefung fehl schlaegt.
						}
					}
				}
			} catch (final Exception e) {
				erfolgreich = false;
			}
		}

		return erfolgreich;
	}

}
