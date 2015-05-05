package de.drv.dsrv.kernpruefung.deuev.model;

import de.drv.dsrv.kernpruefung.basis.aufruf.ReturnCodes;

/**
 * Return Codes, die von der Java-Kernpruefung zurueckgegeben werden, und die
 * Auskunft ueber den Verlauf der Pruefung geben.
 */
public enum ReturnCodesDeuev implements ReturnCodes {

	/**
	 * Pruefung erfolgreich durchgefuehrt.
	 */
	ERFOLG_OHNEFEHLER(
			"0000",
			"Pruefung konnte durchgefuehrt werden und es wurden keine Fehler im Datensatz festgestellt"),

	/**
	 * Pruefung mit Fehlern durchgefuehrt.
	 */
	ERFOLG_MITFEHLER("1000", "Pruefung konnte durchgefuehrt werden und es wurden Fehler im Datensatz festgestellt"),

	/**
	 * Verfahrensmerkmal nicht DEUEV.
	 */
	FEHLER_VERFAHRENSMERKMAL("1001", "Verfahrensmerkmal entspricht nicht dem erlaubten Wert."),

	/**
	 * Kennung des Datensatzes ungueltig.
	 */
	FEHLER_DATENSATZ_KENNUNG("1002", "ungueltige Datensatzkennung (nicht DSKO, DSBD, DSME, DSAE)"),

	/**
	 * Keine Verwendung des Datensatzes.
	 */
	KEINE_VERWENDUNG("1003", "Datensatz nicht geprueft (?), soll nicht verwendet werden"),

	/**
	 * Keine Pruefung durchgefuehrt.
	 */
	FEHLER_FEHLERKENNZEICHEN(
			"1100",
			"Dem Programm wurde ein bereits als fehlerhaft gekennzeichneter Datensatz uebergeben (Fehlerkennzeichen ungleich 0), es konnte keine Pruefung durchgefuehrt werden."),

	/**
	 * Fehler und Hinweise im Datensatz.
	 */
	FEHLER_HINWEISE_DATENSATZ("1200", "Im Datensatz wurden sowohl Fehler als auch Hinweise festgestellt."),

	/**
	 * Hinweis.
	 */
	HINWEIS("2000", "Hinweis"),

	/**
	 * Technischer Fehler.
	 */
	TECHNISCHER_FEHLER("9000", "Technischer Fehler");

	/**
	 * Liefert den numerischen Wert (Code) des Return Codes.
	 * 
	 * @return numerischer Wert (Code) des Return Codes
	 */
	@Override
	public String getCode() {
		return code;
	}

	/**
	 * Liefert die Beschreibung des Return Codes.
	 * 
	 * @return Beschreibung des Return Codes
	 */
	@Override
	public String getText() {
		return text;
	}

	private final String code;
	private final String text;

	/**
	 * Konstruktor.
	 * 
	 * @param code
	 *            Wert des Return Codes
	 * @param text
	 *            Beschreibung des Return Codes
	 */
	ReturnCodesDeuev(final String code, final String text) {
		this.code = code;
		this.text = text;
	}
}
