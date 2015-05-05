package de.drv.dsrv.kernpruefung.deuev.model.fehler;

import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;

/**
 * Fehlernummern der einzelnen Pruefungen des Bausteins (DB)GB.
 */
public enum FehlerNummerDBGB implements FehlerNummer {

	/**
	 * Zulaessig ist "DBGB".
	 */
	DBGB001,
	/**
	 * Gleiche Sonder- und Leerzeichen nicht mehrfach aufeinander folgen.
	 */
	DBGB010,
	/**
	 * Zu Beginn des Feldes sind mehr als 2 gleiche aufeinander folgende
	 * Buchstaben unzulaessig.
	 */
	DBGB011,
	/**
	 * Vor oder nach Bindestrichen sind keine Leerzeichen erlaubt.
	 */
	DBGB012,
	/**
	 * Zulaessig sind Buchstaben, Leerzeichen, Bindestriche, Apostrophe,
	 * Klammern oder ein Punkt.
	 */
	DBGB014,
	/**
	 * Das Feld enthaelt mehr als 2 Ziffern oder 2 Ziffern, die nicht
	 * unmittelbar hintereinander stehen.
	 */
	DBGB015,
	/**
	 * Im Feld muss vor einer Ziffer ein Leerzeichen stehen.
	 */
	DBGB018,
	/**
	 * Auf der ersten Stelle des Geburtsnamens ist nur ein Buchstabe ungleich
	 * Eszett zugelassen.
	 */
	DBGB020,
	/**
	 * Das Pluszeichen ist nur auf der ersten Stelle zulässig und 
	 * die restlichen Stellen müssen Grundstellung(Leerzeichen) sein.
	 */
	DBGB021,
	/**
	 * Auf der letzten Stelle des Feldes ist nur ein Buchstabe, eine
	 * schliessende Klammer oder ein Punkt zulaessig.
	 */
	DBGB022,
	/**
	 * Das Pluszeichen ist entweder im Feld GBNA oder VONA zulässig.
	 */
	DBGB024,
	/**
	 * Gleiche Sonder- und Leerzeichen duerfen nicht mehrfach aufeinanderfolgen.
	 */
	DBGB040,
	/**
	 * Zulaessig sind Buchstaben, Leerzeichen, Apostrophe oder Punkte.
	 */
	DBGB044,
	/**
	 * Auf der ersten Stelle des Geburts-Vorsatzwortes ist nur ein Buchstabe
	 * zugelassen.
	 */
	DBGB046,
	/**
	 * Vor einem Punkt ist mindestens ein Buchstabe erforderlich.
	 */
	DBGB048,
	/**
	 * Zulaessig sind nur die Vorsatzworte der Anlage 6.
	 */
	DBGB050,
	/**
	 * Gleiche Sonder- und Leerzeichen duerfen nicht mehrfach aufeinanderfolgen.
	 */
	DBGB060,
	/**
	 * Zulaessig sind Buchstaben, Leerzeichen, Apostrophe oder Punkte.
	 */
	DBGB064,
	/**
	 * Auf der ersten Stelle des Geburts-Namenszusatzes ist nur ein Buchstabe
	 * zugelassen.
	 */
	DBGB066,
	/**
	 * Vor einem Punkt ist mindestens ein Buchstabe erforderlich.
	 */
	DBGB068,
	/**
	 * Zulaessig sind nur die Namenszusaetze der Anlage
	 * "Tabelle der gültigen Namenszusaetze" (Anlage 7).
	 */
	DBGB070,
	/**
	 * Im Feld sind nur numerische Werte zulaessig.
	 */
	DBGB100,
	/**
	 * Wenn der Geburtsmonat 00 ist, muss auch der Geburtstag 00 sein.
	 */
	DBGB102,
	/**
	 * Im Feld ist nur ein logisch richtiges Datum zulaessig.
	 */
	DBGB104,
	/**
	 * Ein Datum, das mehr als 150 Jahre vor dem Verarbeitungsdatum liegt, ist
	 * unzulaessig.
	 */
	DBGB106,
	/**
	 * Ein Datum, das nach dem Verarbeitungsdatum liegt, ist nicht zulaessig.
	 */
	DBGB107,
	/**
	 * Das Geburtsdatum muss gleich dem Geburtsdatum in der
	 * Interimsversicherungsnummer sein.
	 */
	DBGB110,
	/**
	 * Im Feld Geschlecht ist der Wert m oder w zulaessig.
	 */
	DBGB120,
	/**
	 * Bei GE = "M" muss die Seriennummer der Interimsversicherungsnummer
	 * (Stellen 10 - 11 der VSNR im DSME) = 00 - 49 sein.
	 */
	DBGB122,
	/**
	 * Bei GE = "W" muss die Seriennummer der Interimsversicherungsnummer
	 * (Stellen 10 - 11 der VSNR im DSME) = 50 - 99 sein.
	 */
	DBGB124,
	/**
	 * Der Geburtsort muss immer gemeldet werden.
	 */
	DBGB128,
	/**
	 * Gleiche Sonder- und Leerzeichen duerfen nicht mehrfach aufeinander
	 * folgen.
	 */
	DBGB130,
	/**
	 * Mindestens 3 gleiche aufeinanderfolgende Buchstaben am Beginn des
	 * Geburtsortes sind unzulaessig.
	 */
	DBGB131,
	/**
	 * Zulaessig sind Buchstaben, Ziffern, Leerzeichen, Punkte, Kommata,
	 * Bindestriche, Schrägstriche, Apostrophe oder Klammern.
	 */
	DBGB134,
	/**
	 * Auf der ersten Stelle des Geburtsortes ist nur ein Buchstabe zugelassen.
	 */
	DBGB136,
	/**
	 * Der Geburtsort muss aus mindestens zwei Buchstaben bestehen.
	 */
	DBGB138,
	/**
	 * Die Angabe fiktiver Werte im Feld Geburtsort ist unzulaessig. Ist der
	 * Geburtsort nicht bekannt, ist "unbekannt" einzutragen.
	 */
	DBGB140,
	/**
	 * Auf der letzten Stelle des Geburtsortes ist nur ein Buchstabe, ein Punkt
	 * oder eine schließende Klammer zugelassen.
	 */
	DBGB142,
	
	/**
	 * Zulaessig ist nur die Datenlänge 117.
	 */
	DBGB910;
	
	@Override
	public String getNummer() {
		return this.name();
	}

	@Override
	public FehlerNummer getValueOf(final String fehlerNummer) {
		return valueOf(fehlerNummer);
	}
}
