package de.drv.dsrv.kernpruefung.deuev.model.feld;

import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Gelistet sind alle Feldnamen, die fuer eine Pruefung benoetigt werden, aus
 * dem Baustein DBRG.
 */
public enum FeldNameDBRG implements FeldName {

	/**
	 * Kennung, um welchen Baustein es sich handelt. (DBRG)
	 */
	KENNUNG("KENNUNG", "KE"),

	/**
	 * Anzahl der angehaengten Teile.
	 */
	ZAEHLER("ZAEHLER", "ANRG");

	private String name;
	private String kurzName;

	private FeldNameDBRG(final String name, final String kurzName) {
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
