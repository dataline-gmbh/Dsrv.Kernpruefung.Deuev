package de.drv.dsrv.kernpruefung.deuev.parser.datensatz;

import java.util.AbstractMap;

import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.parser.AbstractDatensatzParser;
import de.drv.dsrv.kernpruefung.basis.parser.BausteinReihenfolge;
import de.drv.dsrv.kernpruefung.basis.parser.DatensatzAufbauException;
import de.drv.dsrv.kernpruefung.deuev.model.BausteinReihenfolgeDSKO;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSKO;

/**
 * Datensatzparser fuer den Datensatz DSKO.
 */
public class DatensatzParserDSKO extends AbstractDatensatzParser {

	/**
	 * Konstruktor. Setzt die konkreten Werte fuer Fehler und
	 * Bausteinreihenfolge.
	 */
	public DatensatzParserDSKO() {
		super(new Fehler<FehlerNummerDSKO>(FehlerNummerDSKO.DSKO910), BausteinReihenfolgeDSKO.DSKO);
	}

	@Override
	protected void parseSchalterleiste() throws DatensatzAufbauException {
		// DSKO hinzufuegen, muss immer vorhanden sein und ist nicht in der
		// Schalterleiste.
		getSchalterleiste().add(
				new AbstractMap.SimpleEntry<BausteinReihenfolge, Boolean>(BausteinReihenfolgeDSKO.DSKO, true));
	}
}
