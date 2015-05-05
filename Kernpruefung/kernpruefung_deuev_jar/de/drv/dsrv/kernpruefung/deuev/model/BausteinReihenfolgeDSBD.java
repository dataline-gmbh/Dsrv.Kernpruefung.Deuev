package de.drv.dsrv.kernpruefung.deuev.model;

import de.drv.dsrv.kernpruefung.basis.parser.BausteinReihenfolge;

/**
 * Der Index gibt die Reihenfolge der Bausteine fuer DEUEV-DSBD an: DSBD
 * entspricht 0 (erster Baustein), DBKA entspricht 1 (zweiter Baustein) usw..
 */
public enum BausteinReihenfolgeDSBD implements BausteinReihenfolge {
	/**
	 * Datensatzbaustein Betriebsdatenpflege.
	 */
	DSBD,

	/**
	 * Datenbaustein Abweichende Korrespondenzanschrift.
	 */
	DBKA,

	/**
	 * Datenbaustein Teilnahmepflichten.
	 */
	DBTN;

	@Override
	public String getName() {
		return name();
	}

	@Override
	public int getOrdinal() {
		return ordinal();
	}

}
