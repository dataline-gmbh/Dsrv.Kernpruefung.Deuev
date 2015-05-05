package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert, ob der uebergebene Wert aus 20 Zeichen besteht und ob die Uhrzeit
 * (Position 9 bis 14) einen logisch korrekten Wert hat.
 */
public class PruefungUhrzeitLogischRichtig extends AbstractPruefung {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            das zu pruefende Feld
	 */
	public PruefungUhrzeitLogischRichtig(final Feld<?> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			final DateFormat dateFormat = new SimpleDateFormat("HHmmss", Locale.GERMANY);
			dateFormat.setLenient(false);
			try {
				dateFormat.parse(feldValue);
				erfolgreich = true;
			} catch (final ParseException e) {
				if (LOGGER.isLoggable(Level.FINE)) {
					LOGGER.log(Level.FINE, e.toString());
				}
				// Ausnahme wird nicht weitergeleitet, das es richtig ist, dass
				// die Pruefung fehl schlaegt.
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Wert ist eine logisch richtige Uhrzeit< "
					+ "Wert des Feldes fehlt oder das Feld hat ungenuegende Laenge.");
		}

		return erfolgreich;
	}
}
