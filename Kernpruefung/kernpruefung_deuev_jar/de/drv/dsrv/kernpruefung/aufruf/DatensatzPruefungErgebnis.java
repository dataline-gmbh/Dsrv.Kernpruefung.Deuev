package de.drv.dsrv.kernpruefung.aufruf;


/**
 * Definiert das Ergebnis der Pruefung fuer einen Datensatz.
 */
public class DatensatzPruefungErgebnis {

	private String returnCode;
	private String datensatz;

	/**
	 * @return <code>ReturnCode</code>
	 */
	public String getReturnCode() {
		return returnCode;
	}

	/**
	 * @param returnCode
	 */
	public void setReturnCode(final String returnCode) {
		this.returnCode = returnCode;
	}

	/**
	 * @return Gibt den Datensatz zurueck.
	 */
	public String getDatensatz() {
		return datensatz;
	}

	/**
	 * @param datensatz
	 */
	public void setDatensatz(final String datensatz) {
		this.datensatz = datensatz;
	}

	@Override
	public String toString() {
		return "PruefungErgebnis [returnCode=" + returnCode + ", datensatz=" + datensatz + "]";
	}
}
