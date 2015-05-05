package de.drv.dsrv.kernpruefung.deuev.parser.datensatz;

import java.util.AbstractMap;
import java.util.logging.Level;

import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.parser.AbstractDatensatzParser;
import de.drv.dsrv.kernpruefung.basis.parser.BausteinReihenfolge;
import de.drv.dsrv.kernpruefung.basis.parser.DatensatzAufbauException;
import de.drv.dsrv.kernpruefung.deuev.model.BausteinReihenfolgeDSAE;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSAE;

/**
 * Liest einen Datensatz mit folgenden Bausteinen ein: DSAE, DBAZ, DBEZ. Der
 * Aufbau der einzelnen Bausteine ist in der Dokumentation Anlage
 * 9.5_Vers2.49.pdf zu finden.
 */
public class DatensatzParserDSAE extends AbstractDatensatzParser {

	private static final int ST_DBAZ = 170;
	private static final int ST_DBEZ = 171;

	/**
	 * Konstruktor. Setzt die konkreten Werte fuer Fehler und
	 * Bausteinreihenfolge.
	 */
	public DatensatzParserDSAE() {
		super(new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE910), BausteinReihenfolgeDSAE.DSAE);
	}

	/**
	 * Schalterleiste wird ausgelesen und in einer Map gespeichert, wobei der
	 * Wert zeigt, ob der entsprechende Baustein vorhanden sein muss.
	 * 
	 * @throws DatensatzAufbauExceptionDEUEV
	 *             falls der Datensatz zu kurz ist wird mit der Fehlernummer
	 *             DSAE910 abgebrochen
	 */
	@Override
	protected void parseSchalterleiste() throws DatensatzAufbauException {

		try {
			// DSAE hinzufuegen, muss immer vorhanden sein und ist nicht in der
			// Schalterleiste.
			getSchalterleiste().add(
					new AbstractMap.SimpleEntry<BausteinReihenfolge, Boolean>(BausteinReihenfolgeDSAE.DSAE, true));

			getSchalterleiste().add(
					new AbstractMap.SimpleEntry<BausteinReihenfolge, Boolean>(BausteinReihenfolgeDSAE.DBAZ,
							(getDatensatzWert().charAt(ST_DBAZ) == 'J')));
			getSchalterleiste().add(
					new AbstractMap.SimpleEntry<BausteinReihenfolge, Boolean>(BausteinReihenfolgeDSAE.DBEZ,
							(getDatensatzWert().charAt(ST_DBEZ) == 'J')));

			if (LOGGER.isLoggable(Level.FINE)) {
				LOGGER.log(Level.FINE, "schalterleiste: " + getSchalterleiste());
			}

		} catch (final StringIndexOutOfBoundsException e) {
			LOGGER.log(Level.FINE, e.toString());
			throw new DatensatzAufbauException("Fehler beim Einlesen des Datensatzes, die Laenge ist falsch.",
					new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE910));
		}
	}
}
