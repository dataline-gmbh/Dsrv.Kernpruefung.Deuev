package de.drv.dsrv.kernpruefung.deuev.model;

import de.drv.dsrv.kernpruefung.basis.parser.BausteinReihenfolge;

/**
 * Gibt die Reihenfolge der Bausteine im Datensatz DSKO vor.
 */
public enum BausteinReihenfolgeDSKO implements BausteinReihenfolge {

	/**
	 * Datenbaustein Kommunikation.
	 */
	DSKO;

	@Override
	public String getName() {
		return name();
	}

	@Override
	public int getOrdinal() {
		return ordinal();
	}
}
