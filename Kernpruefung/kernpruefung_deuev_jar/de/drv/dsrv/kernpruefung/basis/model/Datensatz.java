package de.drv.dsrv.kernpruefung.basis.model;

import java.util.List;

/**
 * Definiert Methoden fuer einen Datensatz.
 */
public interface Datensatz {

	/**
	 * Ermittelt zu dem angegebenen Namen den zugehorigen Baustein im Datensatz.
	 * 
	 * @param name
	 *            Name des Bausteins
	 * @param clazz
	 *            class-Referenz der Implementierungsklasse des Namenkreises
	 * 
	 * @return Baustein, der dem angegebenen Namen entspricht
	 */
	Baustein<? extends FeldName> getBaustein(final BausteinName name);

	/**
	 * Gibt an, ob der Baustein mit dem angegebenen Namen im Datensatz vorhanden
	 * ist.
	 * 
	 * @param name
	 *            Name des Bausteins
	 * @return <code>true</code>, falls der Baustein mit dem angegebenen Namen
	 *         vorhanden ist, <code>false</code> ansonsten
	 */
	boolean containsBaustein(final BausteinName name);

	/**
	 * Gibt die Liste aller Bausteine des Datensatzes zurueck.
	 * 
	 * @return Liste aller Bausteine des Datensatzes
	 */
	List<Baustein<? extends FeldName>> getBausteine();

}