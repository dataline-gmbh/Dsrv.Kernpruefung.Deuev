package de.drv.dsrv.kernpruefung.deuev.parser.datensatz;

import java.util.AbstractMap;

import de.drv.dsrv.kernpruefung.basis.parser.AbstractDatensatzParser;
import de.drv.dsrv.kernpruefung.basis.parser.BausteinReihenfolge;
import de.drv.dsrv.kernpruefung.basis.parser.DatensatzAufbauException;
import de.drv.dsrv.kernpruefung.deuev.model.BausteinReihenfolgeVNIF;

/**
 * "Liest" einen Fake-Datensatz fuer VNIF ein. Die Felder muessen nicht mehr
 * getestet werden. So bleibt aber alles sauber in der Architektur.
 */
public class DatensatzParserVNIF extends AbstractDatensatzParser {

	/**
	 * Konstruktor.
	 */
	public DatensatzParserVNIF() {
		super(null, BausteinReihenfolgeVNIF.VNIF);
	}

	@Override
	protected void parseSchalterleiste() throws DatensatzAufbauException {
		// VNIF hinzufuegen, muss immer vorhanden sein und ist nicht in der
		// Schalterleiste.
		getSchalterleiste().add(
				new AbstractMap.SimpleEntry<BausteinReihenfolge, Boolean>(BausteinReihenfolgeVNIF.VNIF, true));
	}
}
