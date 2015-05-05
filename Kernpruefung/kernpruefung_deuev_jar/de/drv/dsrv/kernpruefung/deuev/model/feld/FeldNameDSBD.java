package de.drv.dsrv.kernpruefung.deuev.model.feld;

import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Definiert Feldnamen fuer den Baustein DSBD.
 */
public enum FeldNameDSBD implements FeldName {

	/**
	 * Kennung, um welchen Datensatzes es sich handelt (DSBD).	
	 */
	KENNUNG("KENNUNG", "KE"),

	/**
	 * Verfahren, für das der Datensatz bestimmt ist.
	 */
	VERFAHREN("VERFAHREN", "VF"),

	/**
	 * Betriebsnummer des Erstellers des Datensatzes.
	 */
	BBNR_ABSENDER("BBNR-ABSENDER", "BBNRAB"),

	/**
	 * Betriebsnummer des Empfaengers des Datensatzes.
	 */
	BBNR_EMPFAENGER("BBNR-EMPFAENGER", "BNNREP"),

	/**
	 * Versionsnummer des uebermittelten Datensatzes.
	 */
	VERSIONS_NR("VERSIONS-NR", "VERNR"),

	/**
	 * Zeitpunkt der Erstellung des Datensatzes.
	 */
	DATUM_ERSTELLUNG("DATUM-ERSTELLUNG", "ED"),

	/**
	 * Kennzeichnung fuer fehlerhafte Datensaetze.
	 */
	FEHLER_KENNZ("FEHLER-KENNZ", "FEKZ"),
	
	/**
	 * Anzahl der Fehler des Datensatzes.
	 */
	FEHLER_ANZAHL("FEHLER-ANZAHL", "FEAN"),

	/**
	 * Betriebsnummer der Betriebsstaette, fuer die die Meldung vorgenommen
	 * wird.
	 */
	BBNR_BETRIEBSSTAETTE("BBNR-BETRIEBSSTAETTE", "BBNRBS"),

	/**
	 * Reservefeld.
	 */
	RESERVE1("RESERVE1", "RESERVE1"),

	/**
	 * Betriebsnummer der Abrechnungsstelle.
	 */
	BBNR_ABRECHNUNGSSTELLE("BBNR-ABRECHNUNGSSTELLE", "BBNRAS"),

	/**
	 * Grund der Abgabe.
	 */
	ABGABEGRUND("ABGABEGRUND", "GD"),

	/**
	 * Wirtschaftsunterklasse nach der Klassifikation WZ2008.
	 */
	WIRTSCHAFTSUNTERKLASSE("WIRTSCHAFTSUNTERKLASSE", "WUKL"),

	/**
	 * Name / Bezeichnung des Betriebes - Teil 1.
	 */
	NAME_BEZEICHNUNG1("NAME-BEZEICHNUNG1", "NAME1"),

	/**
	 * Postleitzahl -(zustellbezogen).
	 */
	POSTLEITZAHL_ZUSTELL("POSTLEITZAHL-ZUSTELL", "PLZZU"),

	/**
	 * Ort des Betriebes.
	 */
	ORT("ORT", "ORT"),

	/**
	 * Strasse des Betriebes.
	 */
	STRASSE("STRASSE", "STR"),

	/**
	 * Hausnummer des Betriebes.
	 */
	HAUSNUMMER("HAUSNUMMER", "HNR"),

	/**
	 * Postleitzahl - (postfachbezogen).
	 */
	POSTLEITZAHL_POSTFACH("POSTLEITZAHL-POSTFACH", "PLZPO"),

	/**
	 * Bestaetigung ueber die Betriebstaetigkeit bzw. Einstellung der
	 * Betriebstaetigkeit.
	 */
	RUHEND_KENNZEICHEN("RUHEND-KENNZEICHEN", "RUHEND"),

	/**
	 * Betriebsnummer der "meldenden Stelle" (unternehmensintern).
	 */
	MELDENDE_STELLE("MELDENDE-STELLE", "BBNRME"),

	/**
	 * Geschlecht zur Anrede des Ansprechpartners.
	 */
	ANREDE_ANSPRECHPARTNER("ANREDE-ANSPRECHPARTNER", "ANR-AP"),

	/**
	 * E-Mail-Adresse des Ansprechpartners.
	 */
	EMAIL_ANSPRECHPARTNER("EMAIL-ANSPRECHPARTNER", "EMAIL-AP"),

	/**
	 * Betriebsnummer der fuer den Beschaeftigten zustaendigen Einzugsstelle
	 * oder berufsstaendischen Versorgungseinrichtung.
	 */
	BBNR_KK("BBNR-KK", "BBNRKK"),
	
	/**
	 * Reservefeld.
	 */
	RESERVE2("RESERVE2", "RESERVE2"),

	/**
	 * Datenbaustein DBKA - Abweichende Korrespondenzanschrift vorhanden.
	 */
	MM_ABWEICHENDE_ANSCHRIFT("MM-ABWEICHENDE-ANSCHRIFT", "MMKA"),

	/**
	 * Datenbaustein DBTN - Teilnahmepflichten vorhanden.
	 */
	MM_TEILNAHME_PFLICHTEN("MM-TEILNAHME-PFLICHTEN", "MMTN"),

	/**
	 * Reservefeld.
	 */
	RESERVE3("RESERVE3", "RESERVE3");

	private String name;
	private String kurzName;

	private FeldNameDSBD(final String name, final String kurzName) {
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
