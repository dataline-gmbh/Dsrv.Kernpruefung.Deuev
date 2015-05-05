package de.drv.dsrv.kernpruefung.basis.fehler;

/**
 * Diese Ausnahme wird ausgeloest, wenn die Fehlertexte nicht korrekt definiert
 * sind, so dass z.B. zu einer Fehlernummer in der Kernpruefung keine
 * Fehlertexte ermitellt werden konnten.
 */
public class FehlerTextNichtVorhandenException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor.
	 * 
	 * @param message
	 *            die Nachricht fuer diese Ausnahme
	 */
	public FehlerTextNichtVorhandenException(final String message) {
		super(message);
	}

	/**
	 * Konstruktor.
	 * 
	 * @param throwable
	 *            die Ursache fuer das Auftreten der Ausnahme
	 */
	public FehlerTextNichtVorhandenException(final Throwable throwable) {
		super(throwable);
	}
}
