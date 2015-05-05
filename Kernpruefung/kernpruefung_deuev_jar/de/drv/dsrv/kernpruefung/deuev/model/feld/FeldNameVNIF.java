package de.drv.dsrv.kernpruefung.deuev.model.feld;

import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Definiert Feldnamen fuer den Baustein VNIF.
 */
public enum FeldNameVNIF implements FeldName {

	/**
	 * Kennung Datenbaustein VNIF.
	 */
	KENNUNG("KENNUNG", "KE"),
	/**
	 * Verfahren.
	 */
	VERFAHREN("VERFAHREN", "VF");

	private String name;
	private String kurzName;

	/**
	 * Konstruktor.
	 * 
	 * @param name
	 *            Name des Feldes
	 * @param kurzName
	 *            Kurzbezeichnung des Feldes
	 */
	private FeldNameVNIF(final String name, final String kurzName) {
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
