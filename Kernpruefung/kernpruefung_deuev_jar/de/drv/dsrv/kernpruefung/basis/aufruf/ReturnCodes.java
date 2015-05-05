package de.drv.dsrv.kernpruefung.basis.aufruf;

/**
 * Return Codes, die von der Java-Kernpruefung zurueckgegeben werden, und die
 * Auskunft ueber den Verlauf der Pruefung geben.
 */
public interface ReturnCodes {

	/**
	 * Liefert den numerischen Wert (Code) des Return Codes.
	 * 
	 * @return numerischer Wert (Code) des Return Codes
	 */
	String getCode();

	/**
	 * Liefert die Beschreibung des Return Codes.
	 * 
	 * @return Beschreibung des Return Codes
	 */
	String getText();
}
