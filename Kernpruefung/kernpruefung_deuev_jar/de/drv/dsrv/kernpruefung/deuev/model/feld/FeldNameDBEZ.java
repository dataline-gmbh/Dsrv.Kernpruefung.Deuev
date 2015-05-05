package de.drv.dsrv.kernpruefung.deuev.model.feld;

import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Definiert Feldnamen fuer den Baustein DBEZ.
 */
public enum FeldNameDBEZ implements FeldName {

	/**
	 * Kennung, um welchen Datenbaustein es sich handelt.
	 */
	KENNUNG("KENNUNG", "KE"),

	/**
	 * Kennzeichen Stornierung.
	 */
	KENNZ_STORNO("KENNZ-STORNO", "KENNZST"),

	/**
	 * Angaben zur Leistungsart.
	 */
	LEISTUNGSART("LEISTUNGSART", "LEAT"),

	/**
	 * Grund der Abgabe.
	 */
	ABGABEGRUND("ABGABEGRUND", "GDMQ"),

	/**
	 * Beginn des Zeitraums, fuer den die Meldung gelten soll.
	 */
	ZEITRAUM_BEGINN("ZEITRAUM-BEGINN", "ZRGB"),

	/**
	 * Ende des Zeitraumes, fuer den die Meldung gelten soll.
	 */
	ZEITRAUM_ENDE("ZEITRAUM-ENDE", "ZREN"),

	/**
	 * Waehrungskennzeichen.
	 */
	WAEHRUNGSKENNZ("WAEHRUNGSKENNZ", "WG"),

	/**
	 * Entgelt in vollen DM/Euro.
	 */
	ENTGELT("ENTGELT", "EG"),

	/**
	 * Beitragsanteil.
	 */
	BEITRAGSANTEIL("BEITRAGSANTEIL", "BY"),

	/**
	 * Kennzeichen Rechtskreis.
	 */
	KENNZ_RECHTSKREIS("KENNZ-RECHTSKREIS", "KENNZRK"),

	/**
	 * Wiedereingliederungs-fall.
	 */
	KENNZ_WIEDEREINGLIEDERUNG("KENNZ-WIEDEREINGLIEDERUNG", "MMWE");

	private String name;
	private String kurzName;

	private FeldNameDBEZ(final String name, final String kurzName) {
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
