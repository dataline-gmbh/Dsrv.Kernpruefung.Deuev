package de.drv.dsrv.kernpruefung.deuev.model.feld;

import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Gelistet sind alle Feldnamen, die fuer eine Pruefung benoetigt werden, aus
 * dem Baustein DBSV.
 */
public enum FeldNameDBSV implements FeldName {

	/**
	 * Kennung, um welchen Datenbaustein es sich handelt (DBSO).
	 */
	KENNUNG("KENNUNG", "KE"),

	/**
	 * Kennzeichen, ob ein SV-Ausweis zu erstellen ist.
	 */
	KENNZ_SVA("KENNZ-SVA", "KENNZSVA");

	private String name;
	private String kurzName;

	private FeldNameDBSV(final String name, final String kurzName) {
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
