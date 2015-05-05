package de.drv.dsrv.kernpruefung.deuev.model.feld;

import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Definiert Feldnamen fuer den Baustein DSAE.
 */
public enum FeldNameDSAE implements FeldName {

	/**
	 * Kennung Datenbaustein DSAE.
	 */
	KENNUNG("KENNUNG", "KE"),
	/**
	 * Verfahren MELD.
	 */
	VERFAHREN("VERFAHREN", "VF"),
	/**
	 * Betriebsnummer des Erstellers des Datensatzes.
	 */
	BBNR_ABSENDER("BBNR-ABSENDER", "BBNRAB"),
	/**
	 * Betriebsnummer des Empfaengers.
	 */
	BBNR_EMPFAENGER("BBNR-EMPFAENGER", "BBNREP"),
	/**
	 * Versionsnummer.
	 */
	VERSIONS_NR("VERSIONS-NR", "VERNR"),
	/**
	 * Zeitpunkt der Erstellung des Datensatzes.
	 */
	DATUM_ERSTELLUNG("DATUM-ERSTELLUNG", "ED"),
	/**
	 * Fehlerkennzeichen.
	 */
	FEHLER_KENNZ("FEHLER-KENNZ", "FEKZ"),
	/**
	 * Fehleranzahl.
	 */
	FEHLER_ANZAHL("FEHLER-ANZAHL", "FEAN"),
	/**
	 * Versicherungsnummer.
	 */
	VSNR("VSNR", "VSNR"),
	/**
	 * Versicherungstraeger, fuer den die Meldung bestimmt ist.
	 */
	VSTR("VSTR", "VSTR"),
	/**
	 * Betriebsnummer der Meldebehoerde.
	 */
	BBNR_VU("BBNR-VU", "BBNRVU"),
	/**
	 * Dieses Feld steht dem Verursacher zur Verfuegung.
	 */
	AKTENZEICHEN_VERURSACHER("AKTENZEICHEN-VERURSACHER", "AZ-VU"),
	/**
	 * BLANK.
	 */
	RESERVE1("RESERVE1", "RESERVE1"),
	/**
	 * Merkmal, Datenbaustein DBAZ-Anrechnungszeiten vorhanden.
	 */
	MM_ANRECHNUNGSZEITEN("MM-ANRECHNUNGSZEITEN", "MMAZ"),
	/**
	 * Merkmal, Datenbaustein DBEZ-Entgeltersatzleistungszeiten vorhanden.
	 */
	MM_ENTGELT_ERSATZLEISTUNGSZEITEN("MM-ENTGELT-ERSATZLEISTUNGSZEITEN", "MMEZ"),
	/**
	 * BLANK.
	 */
	RESERVE2("RESERVE2", "RESERVE2"),
	/**
	 * Kennzeichen, aus welchem Verfahren die Meldung erstellt wurde.
	 */
	KENNZ_UEBERGANG("KENNZ-UEBERGANG", "KENNZUE"),
	/**
	 * BLANK.
	 */
	RESERVE3("RESERVE3", "RESERVE3"),
	/**
	 * Versionsnummer des Kernpruefungsprogramms mit der der Datensatz geprueft
	 * wurde.
	 */
	VERSIONS_NR_KP("VERSIONS-NR-KP", "VERNRKP"),
	/**
	 * BLANK.
	 */
	RESERVE4("RESERVE4", "RESERVE4");

	private FeldNameDSAE(final String name, final String kurzName) {
		this.name = name;
		this.kurzName = kurzName;
	}

	private String name;
	private String kurzName;

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getKurzName() {
		return this.kurzName;
	}

}
