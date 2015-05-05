package de.drv.dsrv.kernpruefung.deuev.model;

import de.drv.dsrv.kernpruefung.basis.parser.BausteinReihenfolge;

/**
 * Der Index gibt die Reihenfolge der Bausteine fuer VNIF an: VNIF entspricht 0
 * (erster Baustein).
 */
public enum BausteinReihenfolgeVNIF implements BausteinReihenfolge {

	/**
	 * Datenbaustein VNIF.
	 */
	VNIF;

	@Override
	public String getName() {
		return name();
	}

	@Override
	public int getOrdinal() {
		return ordinal();
	}
}
