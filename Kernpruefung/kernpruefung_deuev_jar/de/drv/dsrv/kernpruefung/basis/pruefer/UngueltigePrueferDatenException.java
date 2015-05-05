package de.drv.dsrv.kernpruefung.basis.pruefer;

/**
 * Definiert Ausnahme fuer die fehlerhafte Initialisierung eines Pruefers.
 */
public class UngueltigePrueferDatenException extends Exception {

	private static final long serialVersionUID = 5545863314231887325L;

	/**
	 * Konstruktor.
	 * 
	 * @param message
	 *            Fehlermeldung
	 */
	public UngueltigePrueferDatenException(final String message) {
		super(message);
	}
}
