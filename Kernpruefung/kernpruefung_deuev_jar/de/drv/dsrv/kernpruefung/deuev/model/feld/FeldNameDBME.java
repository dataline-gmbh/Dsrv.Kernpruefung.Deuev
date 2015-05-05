package de.drv.dsrv.kernpruefung.deuev.model.feld;

import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Definiert Feldnamen fuer den Baustein DBME.
 */
public enum FeldNameDBME implements FeldName {

	/**
	 * Kennung Datenbaustein DBME.
	 */
	KENNUNG("KENNUNG", "KE"),
	/**
	 * Kennzeichen, Stornierung einer abgegebenen Meldung.
	 */
	KENNZ_STORNO("KENNZ-STORNO", "KENNZST"),
	/**
	 * Kennzeichen, dass der Beschaeftigte Entgelte im Sinne der
	 * Gleitzonenregelung erhaelt.
	 */
	KENNZ_GLEITZONE("KENNZ-GLEITZONE", "KENNZGLE"),
	/**
	 * Beginn des Zeitraums, fuer den die Meldung gelten soll.
	 */
	ZEITRAUM_BEGINN("ZEITRAUM-BEGINN", "ZRBG"),
	/**
	 * Ende des Zeitraums, fuer den die Meldung gelten soll.
	 */
	ZEITRAUM_ENDE("ZEITRAUM-ENDE", "ZREN"),
	/**
	 * Anzahl der Tage fuer kurzfristig Beschaftigte.
	 */
	ZAHL_TAGE("ZAHL-TAGE", "ZLTG"),
	/**
	 * Waehrungskennzeichen.
	 */
	WAEHRUNGS_KENNZ("WAEHRUNGS-KENNZ", "WG"),
	/**
	 * Entgelt in vollen DM/Euro.
	 */
	ENTGELT("ENTGELT", "EG"),
	/**
	 * Beitragsgruppenschluessel.
	 */
	BEITRAGS_GRUPPE("BEITRAGS-GRUPPE", "BYGR"),
	/**
	 * Taetigkeitsschluessel der Bundesagentur fuer Arbeit.
	 */
	TAETIGKEITS_SC("TAETIGKEITS-SC", "TTSC"),
	/**
	 * Kennzeichen Betriebsstaette.
	 */
	KENNZ_RECHTSKREIS("KENNZ-RECHTSKREIS", "KENNZRK"),
	/**
	 * Kennzeichen Mehrfachbeschaeftigter.
	 */
	KENNZ_MEHRFACH("KENNZ-MEHRFACH", "KENNZMF");

	private String name;
	private String kurzName;

	private FeldNameDBME(final String name, final String kurzName) {
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
