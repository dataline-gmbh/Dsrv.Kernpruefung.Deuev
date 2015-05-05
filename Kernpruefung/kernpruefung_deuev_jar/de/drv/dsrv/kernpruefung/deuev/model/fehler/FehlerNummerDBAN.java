package de.drv.dsrv.kernpruefung.deuev.model.fehler;

import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;

/**
 * Fehlernummern der einzelnen Pruefungen im Baustein (DB)AN.
 */
public enum FehlerNummerDBAN implements FehlerNummer {

	// Fehler der externen Pruefungen
	/**
	 * Zulaessig ist "DBAN".
	 */
	DBAN001,
	/**
	 * Bei Auslandsanschriften (LDKZ un-gleich Leerzeichen und "OFW") ist das
	 * LDKZ gemaess Anlage 8 anzugeben.
	 */
	DBAN012,
	/**
	 * Bei Meldungen von Auslandsanschriften ist die Angabe des
	 * Laenderkennzeichens für Jugoslawien, Serbien-Montenegro oder Sudan (LDKZ
	 * = "YU", "SCG" oder "SUD") unzulaessig.
	 */
	DBAN013,
	/**
	 * Meldungen von Anschriften für Perso-nen ohne festen Wohnsitz (LDKZ =
	 * "OFW") sind nur auf dem Meldeweg zwischen a) den Krankenkassen intern
	 * (VFMM im VOSZ = KVTWL oder WLTKV) b) den Krankenkassen und der
	 * Rentenversicherung (VFMM im VOSZ = KVTRV oder RVTKV) c) der Bundesagentur
	 * für Arbeit oder den Kommunen und der Rentenversicherung (VFMM im VOSZ =
	 * BATRV, KTTRV, RVTBA oder RVTKT) und d) innerhalb der Rentenversicherung
	 * (VFMM im VOSZ = DSTBF oder BFTDS) zulaessig.
	 */
	DBAN014,
	/**
	 * Im Feld PLZ sind Leerzeichen nur zulaessig fuer Personen ohne festen
	 * Wohnsitz und Auslandsanschriften.
	 */
	DBAN018,
	/**
	 * Gueltige Postleitzahlen bei Inlandsanschriften sind im Rahmen "01000" bis
	 * "99999".
	 */
	DBAN020,
	/**
	 * Bei Auslandsanschriften (LDKZ != Leerzeichen, "D" und "OFW") sind
	 * Buchstaben, Ziffern, Bindestrich oder Leerzeichen zulaessig.
	 */
	DBAN022,
	/**
	 * Bindestriche duerfen nicht mehrfach aufeinanderfolgen.
	 */
	DBAN024,
	/**
	 * Bei den in der Anlage 18 aufgefuehrten Auslandsanschriften sind nur die
	 * beschriebenen Formate der Postleitzahl zulaessig.
	 */
	DBAN026,
	/**
	 * Im Feld Ort sind Leerzeichen nur zulaessig fuer Personen ohne festen
	 * Wohnsitz.
	 */
	DBAN118,
	/**
	 * Gleiche Sonder- und Leerzeichen duerfen nicht mehrfach aufeinanderfolgen.
	 */
	DBAN120,
	/**
	 * Mindestens drei gleiche aufeianderfolgende Buchstaben sind unzulaessig.
	 */
	DBAN121,
	/**
	 * Auf der ersten Stelle ist nur ein Buchstabe zugelassen.
	 */
	DBAN124,
	/**
	 * Zulaessig sind Buchstaben, Punkte, Kommata, Leerzeichen, Bindestriche,
	 * Schraegstriche, Klammern.
	 */
	DBAN126,
	/**
	 * Vor einem Punkt ist nur ein Buchstabe zugelassen.
	 */
	DBAN128,
	/**
	 * Der Wohnort besteht aus mindestens zwei Buchstaben.
	 */
	DBAN130,
	/**
	 * Auf der letzten Stelle des Wohnortes ist nur ein Buchstabe, eine
	 * schliessende Klammer oder ein Punkt zugelassen.
	 */
	DBAN132,
	/**
	 * Zulaessig sind Buchstaben, Ziffern, Leerzeichen, Punkte, Bindestriche,
	 * Kommata, Schraegstriche, Apostrophe oder Klammern.
	 */
	DBAN140,
	/**
	 * Auf der letzten Stelle des Wohnortes ist nur eine Ziffer, ein Buchstabe,
	 * ein Punkt oder eine schliessende Klammer zugelassen.
	 */
	DBAN144,
	/**
	 * Gleiche Sonder- und Leerzeichen duerfen nicht mehrfach aufeinanderfolgen.
	 */
	DBAN150,
	/**
	 * Mindestens 3 gleich aufeinanderfolgende Buchstaben am Beginn der Strasse
	 * sind unzulaessig, es sei denn a) die Strasse beginnt mit "III" und an der
	 * 4. Stelle folgt ein Punkt, der nicht letztes Zeichen der Strasse ist,
	 * oder b) die Strasse beginnt mit der Zeichenfolge "MMM-Str"
	 */
	DBAN151,
	/**
	 * Bei Auslandsanschriften (LDKZ != Leerzeichen, "D" und "OFW") muss immer
	 * eine Strasse vorhanden sein.
	 */
	DBAN154,
	/**
	 * Zulaessig sind Buchstaben, Ziffern, Leerzeichen, Punkte, Kommata,
	 * Bindestriche, Schraegstriche, Apostrophe, Klammern, Hochkommata oder
	 * Anfuehrungszeichen.
	 */
	DBAN156,
	/**
	 * Soweit eine Strasse vorhanden ist, muss diese aus mindestens zwei Zeichen
	 * oder einem Grossbuchstaben bestehen.
	 */
	DBAN158,
	/**
	 * Auf der ersten Stelle der Strasse ist nur ein Buchstabe, eine Ziffer, ein
	 * Hochkomma, ein Apostroph oder ein Anfuehrungszeichen zugelassen.
	 */
	DBAN160,
	/**
	 * Eine auf Stelle 1 beginnende Ziffernfolge muss von einem Buchstaben,
	 * einem Punkt, ein Leerzeichen oder einem Bindestrich gefolgt sein. Bei
	 * Auslandsanschriften ist auch ein Komma als Folgezeichen zulaessig.
	 */
	DBAN162,
	/**
	 * Vor einer nicht auf Stelle 1 beginnenden Ziffernfolge muss ein Buchstabe,
	 * ein Leerzeichen oder ein Punkt stehen.
	 */
	DBAN164,
	/**
	 * Vor einem Punkt muss ein Buchstabe oder eine Ziffer stehen.
	 */
	DBAN166,
	/**
	 * Auf der letzten Stelle der Straße ist nur ein Buchstabe, eine Ziffer, ein
	 * Punkt, eine schliessende Klammer oder ein Anfuehrungszeichen zugelassen.
	 */
	DBAN168,
	/**
	 * Gleiche Sonder- und Leerzeichen duerfen nicht mehrfach aufeinanderfolgen.
	 */
	DBAN170,
	/**
	 * Zulaessig sind Buchstaben, Ziffern, Kommata, Leerzeichen, Bindestriche,
	 * Schraegstriche, Punkte.
	 */
	DBAN174,
	/**
	 * Das erste und das letzte Zeichen muss ein Buchstabe oder eine Ziffer
	 * sein.
	 */
	DBAN176,
	/**
	 * Zulaessig ist nur die Datenlaenge 133.
	 */
	DBAN910;

	@Override
	public String getNummer() {
		return name();
	}

	@Override
	public FehlerNummer getValueOf(final String fehlerNummer) {
		return valueOf(fehlerNummer);
	}
}
