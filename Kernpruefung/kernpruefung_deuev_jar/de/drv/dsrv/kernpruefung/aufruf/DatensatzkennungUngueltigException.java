package de.drv.dsrv.kernpruefung.aufruf;

/**
 * Wird geworfen, wenn die Datensatzkennung ungueltig ist.
 */
public class DatensatzkennungUngueltigException extends Exception {

	private static final long serialVersionUID = 4391547120502601115L;

	/**
	 * Konstruktor.
	 * 
	 * @param message
	 *            die Nachricht fuer diese Ausnahme
	 */
	public DatensatzkennungUngueltigException(final String message) {
		super(message);
	}

}
