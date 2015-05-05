package de.drv.dsrv.kernpruefung.deuev.model.fehler;

import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;

/**
 * Gelistet sind alle externen Fehlernummern fuer den Baustein DSBD.
 */
public enum FehlerNummerDSBD implements FehlerNummer {

	/**
	 * Zulaessig sind im Feld VFMM im VOSZ nur die Werte "AGDEU", "KVDEU",
	 * "KVTRV", "RVTBA" "RVTKV" oder "KVTWL".
	 */
	DSBD004,

	/**
	 * Die Betriebsnummer ist gemaess Ziffer 1.3.2.2 zu pruefen.
	 */
	DSBD020,

	/**
	 * Die Betriebsnummer ist gemaess Ziffer 1.3.2.2 zu pruefen.
	 */
	DSBD040,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DSBD060,

	/**
	 * Gueltig ist die Version "01" bis zur Bekanntgabe einer neuen
	 * Versionsnummer.
	 */
	DSBD062,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DSBD080,

	/**
	 * Das Erstelldatum muss logisch richtig sein.
	 */
	DSBD082,

	/**
	 * Das Erstelldatum darf nicht groesser als das Verarbeitungsdatum sein.
	 */
	DSBD084,

	/**
	 * Die Uhrzeit muss logisch richtig sein.
	 */
	DSBD086,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DSBD100,

	/**
	 * Zulaessig ist "0" oder "1".
	 */
	DSBD102,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DSBD120,

	/**
	 * Ist im Feld FEKZ der Wert "0" angegeben, ist hier nur der Wert "0"
	 * zulaessig.
	 */
	DSBD122,

	/**
	 * Die Betriebsnummer ist gemaess Ziffer 1.3.2.2 zu pruefen.
	 */
	DSBD140,

	/**
	 * Zulaessig ist nur die Grundstellung (Leerzeichen).
	 */
	DSBD160,

	/**
	 * Das Feld kann auch auf Grundstellung (Leerzeichen) stehen, sofern eine
	 * Betriebsnummer angegeben wurde, ist gemaess Ziffer 1.3.2.2 zu pruefen.
	 */
	DSBD180,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DSBD200,

	/**
	 * Zulaessig ist "01", "02", "03", "11", "12", "13", "14", "15", "16", "17"
	 * oder "18".
	 */
	DSBD202,

	/**
	 * Bei der Betriebsdatenpflege durch die Deutsche Rentenversicherung
	 * Knappschaft-Bahn-See im selbst verwalteten Betriebsnummernbereich (VF =
	 * "BTRKS") sind nur die Werte "01" oder "02" zulaessig.
	 */
	DSBD204,

	/**
	 * Bei der Betriebsdatenpflege durch Arbeitgeber (VF = "BTRAG"),
	 * Krankenkassen (VF = "BTRKV") und Rentenversicherung (VF = "BTRRV") sind
	 * die Werte "01" und "02" unzulaessig.
	 */
	DSBD206,

	/**
	 * Der Wert "03" ist nur bei der Betriebsdatenpflege durch die Krankenkassen
	 * (VF = "BTRKV") zulaessig.
	 */
	DSBD208,

	/**
	 * Bei Meldungen der Arbeitgeber (VF = "BTRAG") ist nur Grundstellung
	 * (Leerzeichen) zulaessig.
	 */
	DSBD220,

	/**
	 * Bei Meldungen fuer die Betriebsdatenpflege durch die Deutsche
	 * Rentenversicherung Knappschaft-Bahn-See im selbst verwalteten
	 * Betriebsnummernbereich (VF = "BTRKS") fuer ungleich Neuvergaben (GD
	 * ungleich "02") ist nur Grundstellung (Leerzeichen) zulaessig.
	 */
	DSBD222,

	/**
	 * Bei Meldungen fuer die Betriebsdatenpflege durch die Deutsche
	 * Rentenversicherung Knappschaft-Bahn-See im selbst verwalteten
	 * Betriebsnummernbereich (VF = "BTRKS") fuer Neuvergaben (GD = "02") ist
	 * die Grundstellung (Leerzeichen) unzulaessig.
	 */
	DSBD224,

	/**
	 * Die Firmenbezeichnung muss immer vorhanden sein.
	 */
	DSBD240,

	/**
	 * Zulaessig sind nur die gueltigen Inlandspostleitzahlen im Rahmen der
	 * Ziffern "01000" bis "99999".
	 */
	DSBD300,

	/**
	 * Die Grundstellung (Leerzeichen) ist nicht zulaessig.
	 */
	DSBD320,

	/**
	 * Gleiche Sonder- und Leerzeichen duerfen nicht mehrfach aufeinanderfolgen.
	 */
	DSBD322,

	/**
	 * Mindestens 3 gleiche aufeinanderfolgende Buchstaben am Beginn des
	 * Wohnortes sind unzulaessig.
	 */
	DSBD324,

	/**
	 * Auf der ersten Stelle des Wohnortes ist nur ein Buchstabe zugelassen.
	 */
	DSBD326,

	/**
	 * Der Wohnort muss aus mindestens zwei Buchstaben bestehen.
	 */
	DSBD328,

	/**
	 * Zulaessig sind Buchstaben, Punkte, Kommata, Leerzeichen, Bindestriche,
	 * Schraegstriche oder Klammern.
	 */
	DSBD330,

	/**
	 * Vor einem Punkt ist nur ein Buchstabe zugelassen.
	 */
	DSBD332,

	/**
	 * Auf der letzten Stelle des Wohnortes ist nur ein Buchstabe, eine
	 * schliessende Klammer oder ein Punkt zugelassen.
	 */
	DSBD334,

	/**
	 * Wenn die Hausnummer nicht separat abgelegt werden kann, ist es zulaessig,
	 * die Hausnummer in das Feld Strasse zu uebernehmen. In solchen Faellen
	 * muss dann das Feld Hausnummer auf Grundstellung (Leerzeichen) stehen.
	 * 
	 * Gleiche Sonder- und Leerzeichen duerfen nicht mehrfach aufeinanderfolgen.
	 */
	DSBD360,

	/**
	 * Mindestens 3 gleiche aufeinanderfolgende Buchstaben am Beginn der Strasse
	 * sind unzulaessig, es sei denn, - die Strasse beginnt mit "III" und an der
	 * 4. Stelle folgt ein Punkt, der nicht letztes Zeichen der Strasse ist oder
	 * - die Strasse beginnt mit der Zeichenfolge "MMM-Str".
	 */
	DSBD362,

	/**
	 * Zulaessig sind Buchstaben, Ziffern, Leerzeichen, Punkte, Kommata,
	 * Bindestriche, Schraegstriche, Apostrophe, Klammern, Hochkommata oder
	 * Anfuehrungszeichen.
	 */
	DSBD364,

	/**
	 * Soweit eine Strasse vorhanden ist, muss diese aus mindestens zwei Zeichen
	 * oder einem Grossbuchstaben bestehen.
	 */
	DSBD366,

	/**
	 * Auf der ersten Stelle der Strasse ist nur ein Buchstabe, eine Ziffer, ein
	 * Hochkomma, ein Apostroph oder ein Anfuehrungszeichen zugelassen.
	 */
	DSBD368,

	/**
	 * Eine auf Stelle 1 beginnende Ziffernfolge muss von einem Buchstaben,
	 * einem Punkt, ein Leerzeichen oder einem Bindestrich gefolgt sein.
	 */
	DSBD370,

	/**
	 * Vor einer nicht auf Stelle 1 beginnenden Ziffernfolge muss ein Buchstabe,
	 * ein Leerzeichen oder ein Punkt stehen.
	 */
	DSBD372,

	/**
	 * Vor einem Punkt muss ein Buchstabe oder eine Ziffer stehen.
	 */
	DSBD374,

	/**
	 * Auf der letzten Stelle der Strasse ist nur ein Buchstabe, eine Ziffer,
	 * ein Punkt, eine schliessende Klammer oder ein Anfuehrungszeichen
	 * zugelassen.
	 */
	DSBD376,

	/**
	 * Gleiche Sonder- und Leerzeichen duerfen nicht mehrfach aufeinanderfolgen.
	 */
	DSBD400,

	/**
	 * Zulaessig sind Buchstaben, Ziffern, Kommata, Leerzeichen, Binde- oder
	 * Schraegstriche und Punkte.
	 */
	DSBD402,

	/**
	 * Das erste und das letzte Zeichen muss ein Buchstabe oder eine Ziffer
	 * sein.
	 */
	DSBD404,

	/**
	 * Das Feld kann auch auf Grundstellung (Leerzeichen) stehen; sofern eine
	 * Postleitzahl angegeben wurde, sind nur die gueltigen Postleitzahlen im
	 * Rahmen der Ziffern "01000" bis "99999" zulaessig.
	 */
	DSBD420,

	/**
	 * Zulaessig ist "A" oder "R".
	 */
	DSBD460,

	/**
	 * Das Feld kann auch auf Grundstellung (Leerzeichen) stehen; sofern eine
	 * Betriebsnummer angegeben wurde, ist sie gemaess Ziffer 1.3.2.2 zu
	 * pruefen.
	 */
	DSBD480,

	/**
	 * Zulaessig sind nur "M", "W", "N" oder Grundstellung (Leerzeichen).
	 */
	DSBD500,

	/**
	 * Zulaessig sind Ausrufungszeichen, Anfuehrungszeichen, Nummernzeichen,
	 * Dollar, Prozent, kommerzielles Und, Apostroph, runde Klammer auf, runde
	 * Klammer zu, Stern, plus, Komma, Bindestrich, Punkt, Schraegstrich,
	 * Ziffern 0 - 9, Doppelpunkt, Semikolon, kleiner als, gleich, groesser als,
	 * Fragezeichen, Paragraph (§), AT-Zeichen (@), Grossbuchstaben (A - Z, Ae,
	 * Oe, Ue), Zirkumflex, Unterstreichung, Gravis, Kleinbuchstaben (a - z, ae,
	 * oe, ue).
	 */
	DSBD580,

	/**
	 * Das Zeichen "@" oder "§" muss einmal vorhanden sein. Das Zeichen "@" oder
	 * "§" darf nur einmal vorhanden sein. Das Zeichen "@" oder "§" darf nicht
	 * am Anfang oder am Ende des Feldes vorhanden sein.
	 */
	DSBD582,

	/**
	 * Das Feld kann auch auf Grundstellung (Leerzeichen) stehen; sofern eine
	 * Betriebsnummer angegeben wurde, ist sie gemaess Ziffer 1.3.2.2 zu
	 * pruefen.
	 */
	DSBD640,

	/**
	 * Zulaessig ist nur die Grundstellung (Leerzeichen).
	 */
	DSBD660,

	/**
	 * Zulaessig sind nur "J" oder "N".
	 */
	DSBD680,

	/**
	 * Zulaessig sind nur "J" oder "N".
	 */
	DSBD700,

	/**
	 * Die Angabe MMTN = "J" ist nur bei Meldungen der Krankenkassen oder der
	 * Rentenversicherung (VF = "BTRKV" oder "BTRRV") zulaessig.
	 */
	DSBD702,

	/**
	 * Zulaessig ist nur die Grundstellung (Leerzeichen).
	 */
	DSBD720,

	/**
	 * Die Fehlerpruefung wird nach mehr als 8 erkannten Fehlern abgebrochen.
	 * Auf diesen Sachverhalt wird mit der neunten Fehlernummer DSBD920
	 * hingewiesen.
	 */
	DSBD920,

	/**
	 * Bei MMKA = "J" muss der Datenbaustein DBKA - Abweichende
	 * Korrespondenzanschrift vorhanden sein.
	 */
	DSBD930,

	/**
	 * Bei MMTN = "J" muss der Datenbaustein DBTN - Teilnahmepflichten vorhanden
	 * sein.
	 */
	DSBD932,

	/**
	 * Ist der eingehende Datensatz fehlerhaft (FEKZ im DSME = "1"), wird keine
	 * Laengen- und Fehlerpruefung durchgefuehrt. Die Laenge des festen Teils
	 * von dem Datensatz DSBD (541 Stellen) und die Laenge der im Datensatz
	 * vorkommenden Datenbausteine (entsprechend "J" in den Merkmalfeldern von
	 * Stelle 535 bis 536 ist zu errechnen und mit der Laenge des gemeldeten
	 * Datensatzes abzugleichen.
	 */
	DSBD910;

	@Override
	public String getNummer() {
		return name();
	}

	@Override
	public FehlerNummer getValueOf(final String fehlerNummer) {
		return valueOf(fehlerNummer);
	}
}
