package de.drv.dsrv.kernpruefung.deuev.parser.datensatz;

import java.util.AbstractMap;
import java.util.logging.Level;

import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.parser.AbstractDatensatzParser;
import de.drv.dsrv.kernpruefung.basis.parser.BausteinReihenfolge;
import de.drv.dsrv.kernpruefung.basis.parser.DatensatzAufbauException;
import de.drv.dsrv.kernpruefung.deuev.model.BausteinReihenfolgeDSME;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;

/**
 * Liest einen Datensatz mit folgenden Bausteinen ein: DSME, DBME, DBNA, DBGB,
 * DBAN, DBEU, DBUV, DBKS, DBSV, DBVR, DBRG, DBSO, DBKV, DBFE, Der Aufbau der
 * einzelnen Bausteine ist in der Dokumentation Anlage 9.4_Vers2.49.pdf zu
 * finden.
 */
public class DatensatzParserDSME extends AbstractDatensatzParser {

	private static final int ST_DBME = 170;
	private static final int ST_DBNA = 171;
	private static final int ST_DBGB = 172;
	private static final int ST_DBAN = 173;
	private static final int ST_DBEU = 174;
	private static final int ST_DBUV = 175;
	private static final int ST_DBKS = 176;
	private static final int ST_DBSV = 177;
	private static final int ST_DBVR = 178;
	private static final int ST_DBRG = 179;
	private static final int ST_DBSO = 183;
	private static final int ST_DBKV = 188;

	/**
	 * Konstruktor. Setzt die konkreten Werte fuer Fehler und
	 * Bausteinreihenfolge.
	 */
	public DatensatzParserDSME() {
		super(new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME910), BausteinReihenfolgeDSME.DSME);
	}

	/**
	 * getSchalterleiste() wird ausgelesen und in einer Map gespeichert, wobei
	 * der Wert zeigt, ob der entsprechende Baustein vorhanden sein muss.
	 * 
	 * @throws DatensatzAufbauExceptionDEUEV
	 *             - Falls der Datensatz zu kurz ist wird mit der Fehlernummer
	 *             DSME910 abgebrochen.
	 */
	@Override
	protected void parseSchalterleiste() throws DatensatzAufbauException {
		try {
			// DSME hinzufuegen, muss immer vorhanden sein und ist nicht in der
			// getSchalterleiste().

			getSchalterleiste().add(
					new AbstractMap.SimpleEntry<BausteinReihenfolge, Boolean>(BausteinReihenfolgeDSME.DSME, true));

			getSchalterleiste().add(
					new AbstractMap.SimpleEntry<BausteinReihenfolge, Boolean>(BausteinReihenfolgeDSME.DBME,
							(getDatensatzWert().charAt(ST_DBME) == 'J')));
			getSchalterleiste().add(
					new AbstractMap.SimpleEntry<BausteinReihenfolge, Boolean>(BausteinReihenfolgeDSME.DBNA,
							(getDatensatzWert().charAt(ST_DBNA) == 'J')));
			getSchalterleiste().add(
					new AbstractMap.SimpleEntry<BausteinReihenfolge, Boolean>(BausteinReihenfolgeDSME.DBGB,
							(getDatensatzWert().charAt(ST_DBGB) == 'J')));
			getSchalterleiste().add(
					new AbstractMap.SimpleEntry<BausteinReihenfolge, Boolean>(BausteinReihenfolgeDSME.DBAN,
							(getDatensatzWert().charAt(ST_DBAN) == 'J')));
			getSchalterleiste().add(
					new AbstractMap.SimpleEntry<BausteinReihenfolge, Boolean>(BausteinReihenfolgeDSME.DBEU,
							(getDatensatzWert().charAt(ST_DBEU) == 'J')));
			getSchalterleiste().add(
					new AbstractMap.SimpleEntry<BausteinReihenfolge, Boolean>(BausteinReihenfolgeDSME.DBUV,
							(getDatensatzWert().charAt(ST_DBUV) == 'J')));
			getSchalterleiste().add(
					new AbstractMap.SimpleEntry<BausteinReihenfolge, Boolean>(BausteinReihenfolgeDSME.DBKS,
							(getDatensatzWert().charAt(ST_DBKS) == 'J')));
			getSchalterleiste().add(
					new AbstractMap.SimpleEntry<BausteinReihenfolge, Boolean>(BausteinReihenfolgeDSME.DBSV,
							(getDatensatzWert().charAt(ST_DBSV) == 'J')));
			getSchalterleiste().add(
					new AbstractMap.SimpleEntry<BausteinReihenfolge, Boolean>(BausteinReihenfolgeDSME.DBVR,
							(getDatensatzWert().charAt(ST_DBVR) == 'J')));
			getSchalterleiste().add(
					new AbstractMap.SimpleEntry<BausteinReihenfolge, Boolean>(BausteinReihenfolgeDSME.DBRG,
							(getDatensatzWert().charAt(ST_DBRG) == 'J')));
			getSchalterleiste().add(
					new AbstractMap.SimpleEntry<BausteinReihenfolge, Boolean>(BausteinReihenfolgeDSME.DBSO,
							(getDatensatzWert().charAt(ST_DBSO) == 'J')));
			getSchalterleiste().add(
					new AbstractMap.SimpleEntry<BausteinReihenfolge, Boolean>(BausteinReihenfolgeDSME.DBKV,
							(getDatensatzWert().charAt(ST_DBKV) == 'J')));

			if (LOGGER.isLoggable(Level.FINE)) {
				LOGGER.log(Level.FINE, "getSchalterleiste(): " + getSchalterleiste());
			}

		} catch (final StringIndexOutOfBoundsException e) {
			LOGGER.log(Level.FINE, e.toString());
			throw new DatensatzAufbauException("Fehler beim Einlesen des Datensatzes, die Laenge ist falsch.",
					new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME910));
		}
	}
}
