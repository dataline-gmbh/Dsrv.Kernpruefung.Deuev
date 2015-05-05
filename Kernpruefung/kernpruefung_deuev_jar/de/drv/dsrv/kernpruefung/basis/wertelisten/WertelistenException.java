package de.drv.dsrv.kernpruefung.basis.wertelisten;

/**
 * Repraesentiert eine Ausnahme bei dem Zugriff auf die Wertelisten.
 */
public class WertelistenException extends Exception {

	private static final long serialVersionUID = 220494868765925213L;

	/**
	 * Konstruktor.
	 * 
	 * @param message
	 *            die Nachricht fuer diese Ausnahme
	 * @param cause
	 *            die Ursache fuer das Auftreten der Ausnahme
	 */
	public WertelistenException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Konstruktor.
	 * 
	 * @param message
	 *            die Nachricht fuer diese Ausnahme
	 */
	public WertelistenException(String message) {
		super(message);
	}
}
