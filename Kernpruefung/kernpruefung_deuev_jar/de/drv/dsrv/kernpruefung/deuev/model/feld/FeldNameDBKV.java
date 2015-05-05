package de.drv.dsrv.kernpruefung.deuev.model.feld;

import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Gelistet sind alle Feldnamen, die fuer eine Pruefung benoetigt werden, aus
 * dem Baustein DBKV.
 */
public enum FeldNameDBKV implements FeldName {

	/**
	 * Kennung, um welchen Datenbaustein es sich handelt (DBKV).
	 */
	KENNUNG("KENNUNG", "KE"),

	/**
	 * Kennzeichen, Stornierung einer bereits abgegebenen Meldung.
	 */
	KENNZ_STORNO("KENNZ-STORNO", "KENNZST"),

	/**
	 * Grund fuer die Besonderheiten bei der Abgabe der KV-Daten.
	 */
	KV_GRUND("KV-GRUND", "KVGD"),

	/**
	 * Anzahl der Tage, fuer die eine Beitragspflicht zur Sozialversicherung im
	 * Abrechnungsmonat besteht (SV-Tage).
	 */
	SV_TAGE("SV-TAGE", "SVTG"),

	/**
	 * Beginn des Zeitraums, fuer den die Meldung gelten soll
	 * (Beschaeftigungsbeginn oder Beginn des Abrechnungszeitraums).
	 */
	ZEITRAUM_BEGINN("ZEITRAUM-BEGINN", "ZRGB-KV"),

	/**
	 * Ende des Zeitraumes, fuer den die Meldung gelten soll
	 * (Beschaeftigungsende oder Ende des Abrechnungszeitraums).
	 */
	ZEITRAUM_ENDE("ZEITRAUM-ENDE", "ZREN-KV"),

	/**
	 * Laufendes Entgelt in Eurocent.
	 */
	LAUFENDES_ENTGELT("LAUFENDES-ENTGELT", "LFDEG"),

	/**
	 * Einmalig gezahltes Entgelt in Eurocent.
	 */
	EINMALIGES_ENTGELT("EINMALIGES-ENTGELT", "EZEG"),

	/**
	 * Beitragspflichtige Einnahme in der gesetzlichen Rentenversicherung bei
	 * Bezug von Kurzarbeitergeld nach § 163 Absatz 6 SGB VI in Eurocent.
	 */
	BEITRAGS_BEMMESUNGSGRUNDLAGE_KURZ_ARBEITERGELD("BEITRAGS-BEMMESUNGSGRUNDLAGE-KURZ-ARBEITERGELD", "BBGRU-KUG"),

	/**
	 * Kennzeichen, dass der Beschaeftigte Entgelte im Sinne der
	 * Gleitzonenregelung erhaelt.
	 */
	KENNZ_GLEITZONE("KENNZ-GLEITZONE", "KENNZGL-SV"),

	/**
	 * Reservefeld.
	 */
	RESERVE1("RESERVE1", "RESERVE1"),

	/**
	 * Reservefeld.
	 */
	RESERVE2("RESERVE2", "RESERVE2"),

	/**
	 * Regelmaessiges Jahresentgelt in Eurocent.
	 */
	REGELMAESSIGES_JAHRESENTGELT("REGELMAESSIGES-JAHRESENTGELT", "RJEG"),

	/**
	 * Beitragspflichtige Einnahmen in der gesetzlichen Rentenversicherung bei
	 * Bezug von Aufstockungsbetraegen nach § 163 Absatz 5 Satz 1 SGB VI in
	 * Eurocent.
	 */
	BEITRAGSBEMESSUNGSGRUNDLAGE_ENTGELT_ALTERSTEILZEIT("BEITRAGSBEMESSUNGSGRUNDLADE-ENTGELT-ALTERSTEILZEIT",
			"BBGRU-ATG"),

	/**
	 * Beitragsgruppenschluessel gemaess Anlage 1.
	 */
	BEITRAGS_GRUPPE("BEITRAGS-GRUPPE", "BYGR"),

	/**
	 * Kennzeichen Rechtskreis.
	 */
	KENNZ_RECHTSKREIS("KENNZ-RECHTSKREIS", "KENNZRK"),

	/**
	 * Laufendes Entgelt KV in Eurocent.
	 */
	LAUFENDES_ENTGELT_KV("LAUFENDES-ENTGELT-KV", "LFDKV"),

	/**
	 * Laufendes Entgelt RV in Eurocent.
	 */
	LAUFENDES_ENTGELT_RV("LAUFENDES-ENTGELT-RV", "LFDRV"),
	
	/**
	 * Laufendes Entgelt AV in Eurocent.
	 */
	LAUFENDES_ENTGELT_AV("LAUFENDES-ENTGELT-AV", "LFDAV"),
	
	/**
	 * Reservefeld.
	 */
	RESERVE3("RESERVE3", "RESERVE3");

	private String name;
	private String kurzName;

	private FeldNameDBKV(final String name, final String kurzName) {
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
