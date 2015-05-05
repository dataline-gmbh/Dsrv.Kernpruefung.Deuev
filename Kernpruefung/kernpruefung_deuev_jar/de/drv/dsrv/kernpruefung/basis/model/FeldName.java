package de.drv.dsrv.kernpruefung.basis.model;

/**
 * Name des Feldes.
 */
public interface FeldName {

	/**
	 * Gibt den vollstaendigen Namen des Feldes zurueck.
	 * 
	 * @return vollstaendiger Name des Feldes
	 */
	String getName();

	/**
	 * Gibt die Kurzbezeichnung des Feldes zurueck.
	 * 
	 * @return Kurzbezeichnung des Feldes
	 */
	String getKurzName();
}
