package de.drv.dsrv.kernpruefung.basis.model;

/**
 * Es gibt zwei verschiedene Arten von Rueckmeldungen: Fehler und Hinweise.
 * Hinweise erkennt man am 'H' zwischen Bausteinteilkennung und Nummer.
 */
public enum FehlerArt {

	/**
	 * Alle Fehlernummern mit dem Aufbau "<AAAA000>", wobei A fuer einen
	 * Grossbuchstaben steht und 0 fuer eine Ziffer.
	 */
	FEHLER,

	/**
	 * Alle Fehlernummern mit dem Aufbau "<AAAA>H<00>", wobei A fuer einen
	 * Grossbuchstaben steht und 0 fuer eine Ziffer.
	 */
	HINWEIS;

}
