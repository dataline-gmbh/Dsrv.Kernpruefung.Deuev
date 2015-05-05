package de.drv.dsrv.kernpruefung.basis.logging;

import java.io.OutputStream;
import java.util.logging.StreamHandler;

/**
 * Bietet neben den Console- und dem FileHandler die Moeglichkeit an, auf einen
 * Stream zu loggen.
 */
public class DeuevStreamHandler extends StreamHandler {

	/**
	 * Konstruktor.
	 * 
	 * @param os
	 *            der Stream auf den geloggt werden soll.
	 */
	public DeuevStreamHandler(final OutputStream os) {
		setOutputStream(os);
	}

}
