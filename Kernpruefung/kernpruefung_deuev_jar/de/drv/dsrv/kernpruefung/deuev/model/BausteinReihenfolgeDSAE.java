package de.drv.dsrv.kernpruefung.deuev.model;

import de.drv.dsrv.kernpruefung.basis.parser.BausteinReihenfolge;

/**
 * Der Index gibt die Reihenfolge der Bausteine fuer DSAE an: DSAE entspricht 0
 * (erster Baustein), DBAZ entspricht 1 (zweiter Baustein) usw.
 */
public enum BausteinReihenfolgeDSAE implements BausteinReihenfolge {
	/**
	 * Datensatz: DSAE - Meldungen von Entgeltersatzleistungen und
	 * Anrechnungszeiten der Leistungsträger an die Rentenversicherung.
	 */
	DSAE,
	/**
	 * Datenbaustein: DBAZ - Anrechnungszeiten.
	 */
	DBAZ,
	/**
	 * Datenbaustein: DBEZ - Entgeltersatzleistungszeiten.
	 */
	DBEZ;

	@Override
	public String getName() {
		return name();
	}

	@Override
	public int getOrdinal() {
		return ordinal();
	}
}
