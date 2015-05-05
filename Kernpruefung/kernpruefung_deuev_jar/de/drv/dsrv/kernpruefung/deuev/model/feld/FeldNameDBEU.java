package de.drv.dsrv.kernpruefung.deuev.model.feld;

import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Definiert Feldnamen fuer den Baustein DBEU.
 */
public enum FeldNameDBEU implements FeldName {

	/**
	 * Kennung Datenbaustein DBEU.
	 */
	KENNUNG("KENNUNG", "KE"),
	/**
	 * Geburtsland.
	 */
	GB_LAND("GB-LAND", "GBLD"),
	/**
	 * BLANK.
	 */
	EUVSNR("EUVSNR", "EUVSNR");

	private String name;
	private String kurzName;

	private FeldNameDBEU(final String name, final String kurzName) {
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
