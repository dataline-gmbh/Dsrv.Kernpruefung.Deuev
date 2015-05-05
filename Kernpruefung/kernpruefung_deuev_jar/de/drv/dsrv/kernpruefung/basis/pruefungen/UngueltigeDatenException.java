package de.drv.dsrv.kernpruefung.basis.pruefungen;

/**
 * Ausnahme wird ausgeloest, wenn an eine Pruefung ungueltige Daten uebergeben
 * werden.
 */
public class UngueltigeDatenException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor.
	 * 
	 * @param message
	 *            Fehlermeldung
	 */
	public UngueltigeDatenException(final String message) {
		super(message);
	}

	/**
	 * Konstruktor.
	 * 
	 * @param message
	 *            Fehlermeldung
	 * @param throwable
	 *            Ausnahme
	 */
	public UngueltigeDatenException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
}
