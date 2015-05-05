package de.drv.dsrv.kernpruefung.deuev.model.feld;

import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Definiert Feldnamen fuer den Baustein DBAZ.
 */
public enum FeldNameDBAZ implements FeldName {

	/**
	 * Kennung Datenbaustein DBAZ.
	 */
	KENNUNG("KENNUNG", "KE"),
	/**
	 * Kennzeichen Stornierung.
	 */
	KENNZ_STORNO("KENNZ_STORNO", "KENNZST"),
	/**
	 * Angaben zu der gemeldeten Zeit.
	 */
	ART_DER_ZEIT("ART_DER_ZEIT", "LEAT"),
	/**
	 * Beginn des Zeitraums, fuer den die Meldung gelten soll.
	 */
	ZEITRAUM_BEGINN("ZEITRAUM_BEGINN", "ZRBG"),
	/**
	 * Beginn des Zeitraumes, fuer den die Meldung gelten soll.
	 */
	ZEITRAUM_ENDE("ZEITRAUM_ENDE", "ZREN");

	private String name;
	private String kurzName;

	private FeldNameDBAZ(final String name, final String kurzName) {
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
