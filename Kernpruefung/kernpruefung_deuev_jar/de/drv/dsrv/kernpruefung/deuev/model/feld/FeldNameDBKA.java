package de.drv.dsrv.kernpruefung.deuev.model.feld;

import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Definiert Feldnamen fuer den Baustein DBKA.
 */
public enum FeldNameDBKA implements FeldName {

	/**
	 * Kennung, um welchen Datenbaustein es sich handelt.
	 */
	KENNUNG("KENNUNG", "KE"),

	/**
	 * Name / Bezeichnung - Teil 1.
	 */
	NAME_BEZEICHNUNG1("NAME-BEZEICHNUNG1", "NAME1"),

	/**
	 * Postleitzahl -zustellbezogen.
	 */
	POSTLEITZAHL_ZUSTELL("POSTLEITZAHL-ZUSTELL", "PLZZU"),

	/**
	 * Ort.
	 */
	ORT("ORT", "ORT"),

	/**
	 * Srasse.
	 */
	STRASSE("STRASSE", "STR"),

	/**
	 * Hausnummer.
	 */
	HAUSNUMMER("HAUSNUMMER", "HNR"),

	/**
	 * Postleitzahl -postfachbezogen.
	 */
	POSTLEITZAHL_POSTFACH("POSTLEITZAHL-POSTFACH", "PLZPO"),

	/**
	 * Reservefeld.
	 */
	RESERVE("RESERVE", "RESERVE");

	private String name;
	private String kurzName;

	private FeldNameDBKA(final String name, final String kurzName) {
		this.name = name;
		this.kurzName = kurzName;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getKurzName() {
		return this.kurzName;
	}
}
