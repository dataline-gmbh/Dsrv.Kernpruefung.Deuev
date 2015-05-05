package de.drv.dsrv.kernpruefung.deuev.model.fehler;

import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;

/**
 * Gelistet sind alle externen Fehlernummern fuer den Baustein DSKO.
 */
public enum FehlerNummerDSKO implements FehlerNummer {

	/**
	 * Zulaessig sind im Feld VFMM im VOSZ nur die Werte "AGDEU", "KVDEU",
	 * "AGTRV", "RVTAG", "WLTKV", "KVTWL", "KVTRV" oder "RVTKV".
	 */
	DSKO004,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DSKO040,

	/**
	 * Gueltig ist die Version "02" bis zur Bekanntgabe einer neuen
	 * Versionsnummer.
	 */
	DSKO042,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DSKO050,

	/**
	 * Das Erstellungsdatum muss logisch richtig sein.
	 */
	DSKO052,

	/**
	 * Das Erstellungsdatum darf nicht groesser als das Verarbeitungsdatum sein.
	 */
	DSKO054,

	/**
	 * Die Uhrzeit muss logisch richtig sein.
	 */
	DSKO056,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DSKO060,

	/**
	 * Zulaessig ist "0" oder "1".
	 */
	DSKO062,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DSKO070,

	/**
	 * Ist im Feld FEKZ der Wert "0" angegeben, ist hier nur der Wert "0"
	 * zulaessig.
	 */
	DSKO072,

	/**
	 * Feldinhalt ist leer.
	 */
	DSKO500,

	/**
	 * Feldinhalt ist leer.
	 */
	DSKO530,

	/**
	 * Feldinhalt ist leer.
	 */
	DSKO540,

	/**
	 * Zulaessig sind nur M oder W.
	 */
	DSKO570,

	/**
	 * Feldinhalt ist leer.
	 */
	DSKO580,

	/**
	 * Feldinhalt ist leer.
	 */
	DSKO590,

	/**
	 * Die E-Mail-Adresse des DEUEVAnsprechpartners muss immer vorhanden sein.
	 */
	DSKO605,

	/**
	 * Zulaessig sind Ausrufungszeichen, Anfuehrungszeichen, Nummernzeichen,
	 * Dollar, Prozent, kommerzielles Und, Apostroph, runde Klammer auf, runde
	 * Klammer zu, Stern, plus, Komma, Bindestrich, Punkt, Schraegstrich,
	 * Ziffern 0 - 9, Doppelpunkt, Semikolon, kleiner als, gleich, größer als,
	 * Fragezeichen, Paragraph (§), AT-Zeichen (@), Grossbuchstaben (A - Z, AE,
	 * OE, UE), Zirkumflex, Unterstreichung, Gravis, Kleinbuchstaben (a - z, ae,
	 * oe, ue).
	 */
	DSKO610,

	/**
	 * Das Zeichen "@" oder "§" muss einmal vorhanden sein. Das Zeichen "@" oder
	 * "§" darf nur einmal vorhanden sein. Das Zeichen "@" oder "§" darf nicht
	 * am Anfang oder am Ende des Feldes vorhanden sein.
	 */
	DSKO612,

	/**
	 * Zulaessig ist nur "J" oder "N".
	 */
	DSKO620,

	/**
	 * Zulaessig ist nur "J" oder "K".
	 */
	DSKO630,

	/**
	 * Zulaessig ist nur die Grundstellung (Leerzeichen).
	 */
	DSKO900,

	/**
	 * Zulaessig ist nur die Datenlaenge 415.
	 */
	DSKO910,

	/**
	 * Datensatz enthaelt mehr als 9 Fehler, Pruefung abgebrochen.
	 */
	DSKO920;

	@Override
	public String getNummer() {
		return this.name();
	}

	@Override
	public FehlerNummer getValueOf(final String fehlerNummer) {
		return valueOf(fehlerNummer);
	}
}
