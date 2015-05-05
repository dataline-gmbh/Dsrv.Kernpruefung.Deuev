package de.drv.dsrv.kernpruefung.deuev.model.feld;

import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Gelistet sind alle Feldnamen, die fuer eine Pruefung benoetigt werden, aus
 * dem Baustein DSKO.
 */
public enum FeldNameDSKO implements FeldName {

	/**
	 * Kennung des Datensatzes Kommunikation.
	 */
	KENNUNG("KENNUNG", "KE"),

	/**
	 * Betriebsnummer des Absenders der Datei.
	 */
	BBNR_ABSENDER("BBNR-ABSENDER", "BBNRAB"),

	/**
	 * Betriebsnummer des Empfaengers der Datei.
	 */
	BBNR_EMPFAENGER("BBNR-EMFPAENGER", "BBNREP"),

	/**
	 * Versionsnummer des uebermittelten Datensatzes Kommunikation (DSKO).
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
	 * Betriebsnummer des Erstellers der Datei.
	 */
	BBNR_ERSTELLER("BBNR-ERSTELLER", "BBNRER"),

	/**
	 * Produkt-Identifier des geprueften Softwareproduktes, das beim Ersteller
	 * der Datei eingesetzt wird. Sie wird von der ITSG, eindeutig fuer jedes
	 * systemuntersuchte Programm, vergeben.
	 */
	PRODUKT_IDENTIFIER("PRODUKT-IDENTIFIER", "PROD-ID"),

	/**
	 * Modifikations-Identifier des geprueften Softwareproduktes, das beim
	 * Ersteller der Datei eingesetzt wird. Sie wird je gepruefter
	 * Produktversion von der ITSG vergeben.
	 */
	MODIFIKATIONS_IDENTIFIER("MODIFIKATIONS-IDENTIFIER", "MOD-ID"),

	/**
	 * Name des Erstellers der Datei.
	 */
	NAME1_ABSENDER("NAME1-ABSENDER", "NAME1"),

	/**
	 * Zweiter Namensbestandteil des Erstellers der Datei.
	 */
	NAME2_ABSENDER("NAME2-ABSENDER", "NAME2"),

	/**
	 * Dritter Namensbestandteil des Erstellers der Datei.
	 */
	NAME3_ABSENDER("NAME3-ABSENDER", "NAME3"),

	/**
	 * Postleitzahl des Erstellers der Datei.
	 */
	PLZ_BETRIEB("PLZ-BETRIEB", "PLZ"),

	/**
	 * Betriebssitz des Erstellers der Datei.
	 */
	ORT_BETRIEB("ORT-BETRIEB", "ORT"),

	/**
	 * Strasse des Betriebssitzes des Erstellers der Datei.
	 */
	STRASSE_BETRIEB("STRASSE-BETRIEB", "STR"),

	/**
	 * Hausnummer des Betriebssitzes des Erstellers der Datei.
	 */
	HAUS_NR_BETRIEB("HAUS-NR-BETRIEB", "NR"),

	/**
	 * Anrede des Ansprechpartners beim Ersteller der Datei.
	 */
	ANREDE_ANSPRECHPARTNER("ANREDE-ANSPRECHPARTNER", "ANR-AP"),

	/**
	 * Name des DEUEV-Ansprechpartners beim Ersteller der Datei.
	 */
	NAME_ANSPRECHPARTNER("NAME-ANSPRECHPARTNER", "NAME-AP"),

	/**
	 * Rufnummer des DEUEV-Ansprechpartners beim Ersteller der Datei gemaess DIN
	 * 5008.
	 */
	TELEFON_ANSPRECHPARTNER("TELEFON-ANSPRECHPARTNER", "TEL-AP"),

	/**
	 * Faxrufnummer des DEUEV-Ansprechpartners beim Ersteller der Datei gemaess
	 * DIN 5008.
	 */
	FAX_ANSPRECHPARTNER("FAX-ANSPRECHPARTNER", "FAX-AP"),

	/**
	 * E-Mail-Adresse des Empfaengers der Protokolle beim Ersteller der Datei.
	 */
	EMAIL_EMPFAENGER_PROTOKOLLE("EMAIL-EMPFAENGER-PROTOKOLLE", "EMAIL-AP"),

	/**
	 * Wird eine Bestätigung der fehlerfreien Verarbeitung gewuenscht.
	 */
	VER_BESTAETIGUNG("VER-BESTAETIGUNG", "VERBEST"),

	/**
	 * Verschluesselte Rueckgabe fehlerhafter Datensaetze bzw. Datenbausteine
	 * mit angehaengten Fehlerdatenbausteinen und sonstigen Rueckmeldungen
	 * mittels Datensatz erwuenscht.
	 */
	KENNZ_FEHLRUECK("KENNZ-FEHLRUECK", "FERUECK"),

	/**
	 * Reserve.
	 */
	RESERVE("RESERVE", "RESERVE");

	private String name;
	private String kurzName;

	private FeldNameDSKO(final String name, final String kurzName) {
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
