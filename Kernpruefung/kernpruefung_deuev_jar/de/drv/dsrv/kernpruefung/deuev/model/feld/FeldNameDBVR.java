package de.drv.dsrv.kernpruefung.deuev.model.feld;

import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Definiert Feldnamen fuer den Baustein DBVR.
 */
public enum FeldNameDBVR implements FeldName {

	/**
	 * Kennung Datenbaustein DBVR.
	 */
	KENNUNG("KENNUNG", "KE"),
	/**
	 * Abgabegrund.
	 */
	ABGABEGRUND("ABGABEGRUND", "GDMQ"),
	/**
	 * Bereichsnummer der Vergabeanstalt.
	 */
	BEREICHS_NR_VA("BEREICHS-NR-VA", "BRNR"),
	/**
	 * Versicherungsnummer.
	 */
	VSNR_VERGABE("VSNR-VERGABE", "VSNRZH");

	private String name;
	private String kurzName;

	private FeldNameDBVR(final String name, final String kurzName) {
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
