package de.drv.dsrv.kernpruefung.basis.parser;

/**
 * Das Interface dient nur dazu, dass fuer jeden Datensatz eine eigene
 * BausteinReihenfolge definiert werden kann und z.B. im Datensatzparser
 * verwendet werden kann.
 */
public interface BausteinReihenfolge {

	/**
	 * Gibt den Namen des Bausteins zurueck.
	 * 
	 * @return Name des Bausteins
	 */
	String getName();

	/**
	 * Gibt die Position zurueck.
	 * 
	 * @return Position.
	 */
	int getOrdinal();

}
