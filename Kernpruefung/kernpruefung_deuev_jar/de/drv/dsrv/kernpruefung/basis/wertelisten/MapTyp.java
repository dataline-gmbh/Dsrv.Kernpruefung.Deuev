package de.drv.dsrv.kernpruefung.basis.wertelisten;

/**
 * Interface fuer die Properties, die ueber die
 * {@link AbstractWertelistenVerwaltung} angefordert werden koennen.
 */
public interface MapTyp {

	/**
	 * Gibt den absoluten Pfad zum Speicherort der Properties zurueck.
	 * 
	 * @return {@link String} Speicherort
	 */
	String getSpeicherOrt();

	/**
	 * Gibt den Namen der Properties zurueck.
	 * 
	 * @return {@link String} Name
	 */
	String getName();

}
