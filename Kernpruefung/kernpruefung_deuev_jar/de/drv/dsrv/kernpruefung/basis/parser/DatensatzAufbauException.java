package de.drv.dsrv.kernpruefung.basis.parser;

import de.drv.dsrv.kernpruefung.basis.model.Fehler;

/**
 * Repraesentiert eine Ausnahme bei dem Aufbau eines Datensatzes.
 */
public class DatensatzAufbauException extends Exception {

	private static final long serialVersionUID = -8325290513573510748L;
	private Fehler<?> fehler;

	/**
	 * Konstruktor.
	 * 
	 * @param message
	 *            die Nachricht fuer diese Ausnahme
	 */
	public DatensatzAufbauException(final String message) {
		super(message);
	}

	/**
	 * Konstruktor mit Fehler.
	 * 
	 * @param message
	 *            die Nachricht fuer diese Ausnahme
	 * @param fehler
	 *            es kann ein Fehler mit angegeben werden, der dann als
	 *            Fehlerbaustein angehaengt wird.
	 */
	public DatensatzAufbauException(final String message, Fehler<?> fehler) {
		super(message);
		this.fehler = fehler;
	}

	/**
	 * @return Fehler der Exception.
	 */
	public Fehler<?> getFehler() {
		return fehler;
	}
}
