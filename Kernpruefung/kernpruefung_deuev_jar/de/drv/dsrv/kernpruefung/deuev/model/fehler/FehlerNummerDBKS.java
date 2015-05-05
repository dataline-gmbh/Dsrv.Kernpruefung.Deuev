package de.drv.dsrv.kernpruefung.deuev.model.fehler;

import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;

/**
 * Gelistet sind alle externen Fehlernummern fuer den Baustein DBKS.
 */
public enum FehlerNummerDBKS implements FehlerNummer {
	/**
	 * Zulaessig ist "DBKS".
	 */
	DBKS001,

	/**
	 * Zulaessig ist "K" oder "S".
	 */
	DBKS010,

	/**
	 * Bei Meldungen knappschaftlicher Arbeitgeber ist im Feld KENNZ-KNV-SEE nur
	 * der Wert "K" zulaessig.
	 */
	DBKS012,

	/**
	 * Bei Meldungen seemaennischer Arbeitgeber ist im Feld KENNZ-KNV-SEE nur
	 * der Wert "S" zulaessig.
	 */
	DBKS014,

	/**
	 * Meldungen mit einem Zeitraumbeginn ab 01.01.2008 (ZRBG im DBME >
	 * 20071231) mit den Versicherungsarten a) Antragsversicherung in allen
	 * Zweigen der Sozialversicherung nach § 2 Abs. 3 Nr. 2 SGB IV fuer
	 * Seeschiffe unter auslaendischer Flagge (VA = 60) oder b)
	 * Antragsversicherung in der Kranken-, Pflege-, Renten- und
	 * Arbeitslosenversicherung nach § 2 Abs. 3 Nr. 1 SGB IV fuer See-schiffe
	 * unter auslaendischer Flagge (VA = 70) sind nur an die Krankenkasse der
	 * Deutsche Rentenversicherung Knappschaft-Bahn-See (BBNRKK im DSME =
	 * "98000006") zulaessig.
	 */
	DBKS100,

	/**
	 * Die folgenden Pruefungen gelten nur, wenn der Datenbaustein Daten fuer
	 * die See-Sozialversicherung (KENNZKS = "S") enthaelt und es sich um den
	 * Meldeweg zwischen der Knappschaft oder der See-Krankenkasse und der
	 * Rentenversicherung (VFMM im VOSZ = "KVTRV" und BBNRAB im DSME =
	 * "98000006", "98094032" oder "99086875") oder zwischen der Deutschen
	 * Rentenversicherung Bund und der Datenstelle der Rentenversicherung (VFMM
	 * im VOSZ = "DSTBF" oder "BFTDS") handelt. Zulaessig sind die VKNR'n "36",
	 * "38", "96" oder "98".
	 */
	DBKS200,

	/**
	 * Die VKNR a) "36" = Beschaeftigung in der Seefahrt (Altersteilzeit) ohne
	 * Beitraege zur Seemannskasse oder b) "38" = Beschaeftigung in der Seefahrt
	 * (Altersteilzeit) mit Beitraegen zur Seemannskasse ist nur zulaessig, wenn
	 * es sich 1) um eine Meldung fuer Seeleute in Altersteilzeit (PERSGR =
	 * "142") 2) für Zeiten ab dem 01.08.1996 (ZRBG im Datenbaustein DBME >
	 * 31.07.1996) handelt.
	 */
	DBKS210,

	/**
	 * Die VKNR a) "96" = Beschaeftigung in der Seefahrt ohne Beitraege zur
	 * Seemannskasse oder b) "98" Beschaeftigung in der Seefahrt mit Beitraegen
	 * zur Seemannskasse ist nur zulaessig, wenn es sich um eine Meldung fuer
	 * Seeleute ausserhalb der Altersteilzeit (PERSGR = "140", "141", "143",
	 * "144" oder "149") handelt.
	 */
	DBKS220,

	/**
	 * Zulaessig ist nur die Datenlaenge 220.
	 */
	DBKS910;

	@Override
	public String getNummer() {
		return name();
	}

	@Override
	public FehlerNummer getValueOf(final String fehlerNummer) {
		return valueOf(fehlerNummer);
	}
}
