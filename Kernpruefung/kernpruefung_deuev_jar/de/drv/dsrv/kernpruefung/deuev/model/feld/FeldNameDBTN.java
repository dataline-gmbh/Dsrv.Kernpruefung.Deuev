package de.drv.dsrv.kernpruefung.deuev.model.feld;

import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Gelistet sind alle Feldnamen, die fuer eine Pruefung benoetigt werden, aus
 * dem Baustein DBTN.
 */
public enum FeldNameDBTN implements FeldName {

	/**
	 * Kennung, um welchen Datenbaustein es sich handelt.
	 */
	KENNUNG("KENNUNG", "KE"),

	/**
	 * Entscheidung, ob der Betrieb der Sofortmeldepflicht unterliegt.
	 */
	SOFORTMELDEPFLICHT("SOFORTMELDEPFLICHT", "SOFOPFL"),

	/**
	 * Datum der Entscheidung zur Sofortmeldepflicht.
	 */
	ENTSCHEIDUNG_SO("EINTSCHEIDUNG-SO", "DATENTSO"),

	/**
	 * Datum, ab wann die Verpflichtung zur Abgabe einer Sofortmeldung besteht
	 * bzw. nicht besteht:
	 */
	GUELTIGKEIT_SO("GUELTIGKEIT-SO", "GUELTSO"),

	/**
	 * Betriebsnummer der Krankenkasse, die über die Sofortmeldepflicht
	 * entschieden hat.
	 */
	KK_ENTSCHEIDUNG_SO("KK-ENTSCHEIDUNG-SO", "BBNRENTSO"),

	/**
	 * Entscheidung, ob der Betrieb der Insolvenzgeldumlage- pflicht unterliegt.
	 */
	INSOLVENZGELD("INSOLVENZGELD", "INSOLVUPFL"),

	/**
	 * Datum der Entscheidung zur Insolvenzgeldumlage-pflicht.
	 */
	ENTSCHEIDUNG_IU("ENTSCHEIDUNG-IU", "DATENTIU"),

	/**
	 * Datum, ab wann die Teilnahme an der Insolvenzgeldumlage- pflicht besteht
	 * oder nicht.
	 */
	GUELTIGKEIT_IU("GUELTIGKEIT-IU", "GUELTIU"),

	/**
	 * Betriebsnummer der Krankenkasse, die über die Insolvenzgeldumlage-
	 * pflicht entschieden hat.
	 */
	KK_ENTSCHEIDUNG_IU("KK-ENTSCHEIDUNG-IU", "BBNRENTIU"),

	/**
	 * Entscheidung, ob der Betrieb der Umlagepflicht U1 unterliegt.
	 */
	UMLAGEPFLICHT_U1("UMLAGEPFLICHT-U1", "U1PFL"),

	/**
	 * Datum der Entscheidung zur Umlagepflicht U1.
	 */
	DATUM_ENTSCHEIDUNG_U1("DATUM-ENTSCHEIDUNG-U1", "DATENTU1"),

	/**
	 * Datum, ab wann die Teilnahme an der Umlage 1 besteht oder nicht.
	 */
	GUELTIGKEIT_U1("GUELTIGKEIT-U1", "GUELTU1"),

	/**
	 * Betriebsnummer der Krankenkasse, die über die Umlagepflicht U1
	 * entschieden hat.
	 */
	KK_ENTSCHEIDUNG_U1("KK-ENTSCHEIDUNG-U1", "BBNRENTU1"),

	/**
	 * Zulaessig ist nur die Grundstellung (Leerzeichen).
	 */
	RESERVE("RESERVE", "RESERVE");

	private String name;
	private String kurzName;

	private FeldNameDBTN(final String name, final String kurzName) {
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
