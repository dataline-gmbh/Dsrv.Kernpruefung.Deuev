package de.drv.dsrv.kernpruefung.basis.wertelisten;

/**
 * Interface fuer die Wertelisten, die ueber die
 * {@link AbstractWertelistenVerwaltung} angefordert werden koennen.
 */
public interface ListenTyp {

	/**
	 * Gibt den absoluten Pfad zum Speicherort der Listen zurueck.
	 * 
	 * @return {@link String} Speicherort
	 */
	String getSpeicherort();

	/**
	 * Gibt den Namen der Liste zurueck.
	 * 
	 * @return {@link String} Name
	 */
	String getName();

}
