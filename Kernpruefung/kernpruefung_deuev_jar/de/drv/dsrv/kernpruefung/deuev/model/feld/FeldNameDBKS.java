package de.drv.dsrv.kernpruefung.deuev.model.feld;

import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Definiert Feldnamen fuer den Baustein DBKS.
 * 
 * Liste nicht vollstaendig, es wurden nur die Feldnamen uebernommen, zu denen
 * es auch ein Kuerzel gab (kursiv geschrieben in Anlage 9.4).
 */
public enum FeldNameDBKS implements FeldName {

	/**
	 * Kennung Datenbaustein DBKS.
	 */
	KENNUNG("KENNUNG", "KE"),

	/**
	 * Kennzeichen Daten vorhanden fuer K = knappschaftl. SV, S = See-SV.
	 */
	KENNZ_KNV_SEE("KENNZ-KNV-SEE", "KENNZKS"),

	/**
	 * Seemaennische Berufsgruppen.
	 */
	BERUFSGRUPPEN("BERUFSGRUPPEN", "BGR"),

	/**
	 * Versicherungsarten.
	 */
	VERSICHERUNGSARTEN("VERSICHERUNGSARTEN", "VA"),

	/**
	 * Fahrzeuggruppen.
	 */
	FAHRZEUGGRUPPEN("FAHRZEUGGRUPPEN", "FGR"),

	/**
	 * Seemaennische Befaehigungszeugnisse (Patente).
	 */
	PATENTE("PATENTE", "PAT"),

	/**
	 * Formloser Antrag auf Befreiung von der Rentenversicherungspflicht fuer
	 * nichtdeutsche Seeleute (gilt nur zur Fristwahrung).
	 */
	ANTRAG_AUF_RV_BEFREIUNG("ANTRAG AUF RV-BEFREUNG", "AQRVB"),

	/**
	 * Angabe der VKNR zur Speicherung im Rentenversicherungskonto (BQ-Format).
	 * Feld wird von der See-KK vor Weiterleitung an die RV gefuellt.
	 */
	VKNR("VKNR", "VKNR");

	private String name;
	private String kurzName;

	private FeldNameDBKS(final String name, final String kurzName) {
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
