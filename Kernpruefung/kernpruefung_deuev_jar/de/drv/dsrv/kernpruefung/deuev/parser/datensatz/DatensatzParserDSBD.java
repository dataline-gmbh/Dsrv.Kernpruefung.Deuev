package de.drv.dsrv.kernpruefung.deuev.parser.datensatz;

import java.util.AbstractMap;
import java.util.logging.Level;

import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.parser.AbstractDatensatzParser;
import de.drv.dsrv.kernpruefung.basis.parser.BausteinReihenfolge;
import de.drv.dsrv.kernpruefung.basis.parser.DatensatzAufbauException;
import de.drv.dsrv.kernpruefung.deuev.model.BausteinReihenfolgeDSBD;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSBD;

/**
 * Liest einen Datensatz mit folgenden Bausteinen ein: DSBD, DBKA, DBTN. Der
 * Aufbau der einzelnen Bausteine ist in der Dokumentation Anlage
 * 9.3_Vers2.46.pdf zu finden.
 */
public class DatensatzParserDSBD extends AbstractDatensatzParser {

	private static final int ST_DBKA = 534;
	private static final int ST_DBTN = 535;

	/**
	 * Konstruktor. Setzt die konkreten Werte fuer Fehler und
	 * Bausteinreihenfolge.
	 */
	public DatensatzParserDSBD() {
		super(new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD910), BausteinReihenfolgeDSBD.DSBD);
	}

	/**
	 * Schalterleiste wird ausgelesen und in einer Map gespeichert, wobei der
	 * Wert zeigt, ob der entsprechende Baustein vorhanden sein muss.
	 * 
	 * @throws DatensatzAufbauExceptionDEUEV
	 *             - Falls der Datensatz zu kurz ist wird mit der Fehlernummer
	 *             DSBD910 abgebrochen.
	 */
	@Override
	protected void parseSchalterleiste() throws DatensatzAufbauException {
		try {
			// DSBD hinzufuegen, muss immer vorhanden sein und ist nicht in der
			// Schalterleiste.
			getSchalterleiste().add(
					new AbstractMap.SimpleEntry<BausteinReihenfolge, Boolean>(BausteinReihenfolgeDSBD.DSBD, true));

			getSchalterleiste().add(
					new AbstractMap.SimpleEntry<BausteinReihenfolge, Boolean>(BausteinReihenfolgeDSBD.DBKA,
							(getDatensatzWert().charAt(ST_DBKA) == 'J')));
			getSchalterleiste().add(
					new AbstractMap.SimpleEntry<BausteinReihenfolge, Boolean>(BausteinReihenfolgeDSBD.DBTN,
							(getDatensatzWert().charAt(ST_DBTN) == 'J')));

			if (LOGGER.isLoggable(Level.FINE)) {
				LOGGER.log(Level.FINE, "schalterleiste: " + getSchalterleiste());
			}

		} catch (final StringIndexOutOfBoundsException e) {
			if (LOGGER.isLoggable(Level.FINE)) {
				LOGGER.log(Level.FINE, e.toString());
			}
			throw new DatensatzAufbauException("Fehler beim Einlesen des Datensatzes, die Laenge ist falsch.",
					new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD910));
		}
	}
}
