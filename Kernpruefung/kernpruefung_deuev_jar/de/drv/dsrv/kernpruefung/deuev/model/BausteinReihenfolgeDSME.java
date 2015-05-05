package de.drv.dsrv.kernpruefung.deuev.model;

import de.drv.dsrv.kernpruefung.basis.parser.BausteinReihenfolge;

/**
 * Der Index gibt die Reihenfolge der Bausteine fuer DSME an: DSME entspricht 0
 * (erster Baustein), DBNA entspricht 1 (zweiter Baustein) usw.
 */
public enum BausteinReihenfolgeDSME implements BausteinReihenfolge {

	/**
	 * Datenbaustein Meldung.
	 */
	DSME,
	/**
	 * Datenbaustein Meldesachverhalt.
	 */
	DBME,
	/**
	 * Datenbaustein Name.
	 */
	DBNA,
	/**
	 * Datenbaustein Geburtsangaben.
	 */
	DBGB,
	/**
	 * Datenbaustein Anschrift.
	 */
	DBAN,
	/**
	 * Datenbaustein Europaeische VSNR.
	 */
	DBEU,
	/**
	 * Datenbaustein Unfallversicherung.
	 */
	DBUV,
	/**
	 * Datenbaustein Knappschaft/See.
	 */
	DBKS,
	/**
	 * Datenbaustein Sozialversicherungsaus.
	 */
	DBSV,
	/**
	 * Datenbaustein Vergabe/Rueckmeldung.
	 */
	DBVR,
	/**
	 * Datenbaustein Rueckmeldung des Zusammentreffens bei geringfuegiger
	 * Beschaeftigung.
	 */
	DBRG,
	/**
	 * Datenbaustein Sofortmeldung.
	 */
	DBSO,
	/**
	 * Datenbaustein Krankenversicherung.
	 */
	DBKV,
	/**
	 * Datenbaustein Fehler.
	 */
	DBFE;

	@Override
	public String getName() {
		return name();
	}

	@Override
	public int getOrdinal() {
		return ordinal();
	}
}
