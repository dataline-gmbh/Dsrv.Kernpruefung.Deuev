package de.drv.dsrv.kernpruefung.basis.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dient als Proxy für Informationen aus CSV-Dateien.
 */
public final class CSVContentProvider {

	private static final CSVContentProvider SINGLETON = new CSVContentProvider();
	private final Map<String, CSVRepresenter> csvTabellenVerzeichnis;

	private CSVContentProvider() {
		csvTabellenVerzeichnis = Collections.synchronizedMap(new HashMap<String, CSVRepresenter>());
	}
	public static CSVContentProvider getInstance() {
		return SINGLETON;
	}

	/**
	 * Speichert eine CSV Tabelle gemaess den Richtlinien.
	 * 
	 * @param dateiInhalt
	 *            Inhalt der CSV-Datei. (zeilenweise)
	 * @param tabellenForm
	 *            Art der intern verwendeten Datenstruktur.
	 * @param schluesselID
	 *            Schluessel zur Identifikation der CSV-Datei/Tabelle.
	 * @retrun <code>null</code> falls bisher noch keine CSV-Datei mit diesem
	 *         Schluessel im Verzeichnis vorliegt, sonst wird die
	 *         ueberschriebene CSV-Datei zurueck geliefert.
	 */
	public CSVRepresenter putCSVTabelle(final List<String> dateiInhalt, final CSVRepresenter tabellenForm,
			final String schluesselID) {

		CSVRepresenter ergebnis = null;

		for (String aktuelleZeile : dateiInhalt) {
			tabellenForm.putZeile(aktuelleZeile);
		}

		synchronized (csvTabellenVerzeichnis) {
			ergebnis = CSVContentProvider.SINGLETON.csvTabellenVerzeichnis.put(schluesselID, tabellenForm);
		}

		return ergebnis;
	}

	/**
	 * Liefert das Abbild der CSV Tabelle oder <code>null</code>.
	 * 
	 * @param schluesselID
	 *            Schluessel zur Identifikation der CSV-Datei/Tabelle.
	 * @return die CSV Tabelle.
	 */
	public CSVRepresenter getCSVTabelle(final String schluesselID) {
		CSVRepresenter ergebnis = null;

		synchronized (csvTabellenVerzeichnis) {
			ergebnis = csvTabellenVerzeichnis.get(schluesselID);
		}

		return ergebnis;
	}

}
