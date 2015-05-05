package de.drv.dsrv.kernpruefung.deuev.model.feld;

import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Definiert Feldnamen fuer den Baustein DBNA.
 */
public enum FeldNameDBNA implements FeldName {

	/**
	 * Kennung datenbaustein DBNA.
	 */
	KENNUNG("KENNUNG", "KE"),
	/**
	 * Familienname.
	 */
	FAMILIENNAME("FAMILIENNAME", "FMNA"),
	/**
	 * Vorname(n).
	 */
	VORNAME("VORNAME", "VONA"),
	/**
	 * Vorsatzwort.
	 */
	VORSATZWORT("VORSATZWORT", "VOSA"),
	/**
	 * Namenszusatz.
	 */
	NAMENSZUSATZ("NAMENSZUSATZ", "NAZU"),
	/**
	 * Titel.
	 */
	TITEL("TITEL", "TITEL"),
	/**
	 * BLANK.
	 */
	KENNZ_AEND_BER("KENNZ-AEND-BER", "KENNZAB");

	private String name;
	private String kurzName;

	private FeldNameDBNA(final String name, final String kurzName) {
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
