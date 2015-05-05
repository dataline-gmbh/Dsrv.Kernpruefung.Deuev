package de.drv.dsrv.kernpruefung.deuev.model.feld;

import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Gelistet sind alle Feldnamen, die fuer eine Pruefung benoetigt werden, aus
 * dem Baustein DBSO.
 */
public enum FeldNameDBSO implements FeldName {
	
	/**
	 * Kennung, um welchen Datenbaustein es sich handelt (DBSO).
	 */
	KENNUNG("KENNUNG", "KE"),
	
	/**
	 * Kennzeichen, Stornierung einer bereits abgegebenen Sofortmeldung.
	 */
	KENNZ_STORNO_SOFORT("KENNZ-STORNO-SOFORT", "KENNZSTSO"),
	
	/**
	 * Beginn des Zeitraums, für den die Sofort-meldung gelten soll (Beschäftigungsbe-ginn), in der Form:
	 * jhjjmmtt.
	 */
	ZEITRAUM_BEGINN_SOFORT("ZEITRAUM-BEGINN-SOFORT", "ZRGBSO");

	private String name;
	private String kurzName;

	private FeldNameDBSO(final String name, final String kurzName) {
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
