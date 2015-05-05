package de.drv.dsrv.kernpruefung.deuev.model.fehler;

import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;

/**
 * Fehlernummern der einzelnen Pruefungen des Bausteins DBKA.
 */
public enum FehlerNummerDBKA implements FehlerNummer {
	/**
	 * Zulaessig ist nur "DBKA".
	 */
	DBKA020,

	/**
	 * Die Firmenbezeichnung muss immer vorhanden sein.
	 */
	DBKA040,

	/**
	 * Zulaessig sind nur die gueltigen Inlandspostleitahlen im Rahmen der
	 * Ziffern "01000" bis "99999".
	 */
	DBKA100,

	/**
	 * Die Grundstellung (Leerzeichen) ist nicht zulaessig.
	 */
	DBKA120,

	/**
	 * Gleiche Sonder- und Leerzeichen duerfen nicht mehrfach aufeinanderfolgen.
	 */
	DBKA122,

	/**
	 * Mindestens 3 gleiche aufeinanderfolgende Buchstaben am Beginn des
	 * Wohnortes sind unzulaessig.
	 */
	DBKA124,

	/**
	 * Auf der ersten Stelle des Wohnortes ist nur ein Buchstabe zugelassen.
	 */
	DBKA126,

	/**
	 * Der Wohnort muss aus mindestens zwei Buchstaben bestehen.
	 */
	DBKA128,

	/**
	 * Es sind Buchstaben, Punkte, Kommata, Leerzeichen, Bindestriche,
	 * Schraegstriche oder Klammern zulaessig.
	 */
	DBKA130,

	/**
	 * Vor einem Punkt ist nur ein Buchstabe zugelassen.
	 */
	DBKA132,

	/**
	 * Auf der letzten Stelle des Wohnortes ist nur ein Buchstabe, eine
	 * schliessende Klammer oder ein Punkt zugelassen.
	 */
	DBKA134,

	/**
	 * Wenn die Hausnummer nicht separat abgelegt werden kann, ist es zulaessig,
	 * die Hausnummer in das Feld Strasse zu uebernehmen. In solchen Faellen
	 * muss dann das Feld Hausnummer auf Grundstellung (Leerzeichen) stehen.
	 * 
	 * Gleiche Sonder- und Leerzeichen duerfen nicht mehrfach aufeinanderfolgen.
	 */
	DBKA160,

	/**
	 * Mindestens 3 gleiche aufeinanderfolgende Buchstaben am Beginn der Strasse
	 * sind unzulaessig, es sei denn, - die Strasse beginnt mit "III" und an der
	 * 4. Stelle folgt ein Punkt, der nicht letztes Zeichen der Strasse ist oder
	 * - die Strasse beginnt mit der Zeichenfolge "MMM-Str".
	 */
	DBKA162,

	/**
	 * Zulaessig sind Buchstaben, Ziffern, Leerzeichen, Punkte, Kommata,
	 * Bindestriche, Schraegstriche, Apostrophe, Klammern, Hochkommata oder
	 * Anfuehrungszeichen.
	 */
	DBKA164,

	/**
	 * Soweit eine Strasse vorhanden ist, muss diese aus mindestens zwei Zeichen
	 * oder einem Grossbuchstaben bestehen.
	 */
	DBKA166,

	/**
	 * Auf der ersten Stelle der Strasse ist nur ein Buchstabe, eine Ziffer, ein
	 * Hochkomma, ein Apostroph oder ein Anfuehrungszeichen zugelassen.
	 */
	DBKA168,

	/**
	 * Eine auf Stelle 1 beginnende Ziffernfolge muss von einem Buchstaben,
	 * einem Punkt, ein Leerzeichen oder einem Bindestrich gefolgt sein.
	 */
	DBKA170,

	/**
	 * Vor einer nicht auf Stelle 1 beginnenden Ziffernfolge muss ein Buchstabe,
	 * ein Leerzeichen oder ein Punkt stehen.
	 */
	DBKA172,

	/**
	 * Vor einem Punkt muss ein Buchstabe oder eine Ziffer stehen.
	 */
	DBKA174,

	/**
	 * Auf der letzten Stelle der Strasse ist nur ein Buchstabe, eine Ziffer,
	 * ein Punkt, eine schliessende Klammer oder ein Anfuehrungszeichen
	 * zugelassen.
	 */
	DBKA176,

	/**
	 * Gleiche Sonder- und Leerzeichen duerfen nicht mehrfach aufeinanderfolgen.
	 */
	DBKA200,

	/**
	 * Zulaessig sind Buchstaben, Ziffern, Kommata, Leerzeichen, Binde- oder
	 * Schraegstriche und Punkte.
	 */
	DBKA202,

	/**
	 * Das erste und das letzte Zeichen muss ein Buchstabe oder eine Ziffer
	 * sein.
	 */
	DBKA204,

	/**
	 * Das Feld kann auch auf Grundstellung (Leerzeichen) stehen; sofern eine
	 * Postleitzahl angegeben wurde, sind nur die gueltigen Postleitzahlen im
	 * Rahmen der Ziffern "01000" bis "99999" zulaessig.
	 */
	DBKA220,

	/**
	 * Zulaessig ist nur die Grundstellung (Leerzeichen).
	 */
	DBKA260,

	/**
	 * Zulaessig ist nur die Datenlaenge 208.
	 */
	DBKA910;

	@Override
	public String getNummer() {
		return this.name();
	}

	@Override
	public FehlerNummer getValueOf(final String fehlerNummer) {
		return valueOf(fehlerNummer);
	}

}
