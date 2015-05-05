package de.drv.dsrv.kernpruefung.deuev.model.feld;

import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Definiert Feldnamen fuer den Baustein DBAN.
 */
public enum FeldNameDBAN implements FeldName {

	/**
	 * Kennung Datenbaustein DBAN.
	 */
	KENNUNG("KENNUNG", "KE"),
	/**
	 * Laender-Kennzeichen bei Auslandsanschriften.
	 */
	LAENDER_KENNZ("LAENDER-KENNZ", "LDKZ"),
	/**
	 * Postleitzahl.
	 */
	PLZ("PLZ", "PLZ"),
	/**
	 * Wohnort.
	 */
	WOHNORT("WOHNORT", "ORT"),
	/**
	 * Strasse.
	 */
	STRASSE("STRASSE", "STR"),
	/**
	 * Hausnummer.
	 */
	HAUS_NR("HAUS-NR", "NR"),
	/**
	 * Anschriftenzusatz.
	 */
	ADR_ZUSATZ("ADR-ZUSATZ", "ADRZU");

	private String name;
	private String kurzName;

	private FeldNameDBAN(final String name, final String kurzName) {
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
