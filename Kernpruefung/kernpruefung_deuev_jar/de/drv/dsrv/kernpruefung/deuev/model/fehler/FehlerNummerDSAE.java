package de.drv.dsrv.kernpruefung.deuev.model.fehler;

import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;

/**
 * Fehlernummern der einzelnen Pruefungen im Baustein DSAE.
 */
public enum FehlerNummerDSAE implements FehlerNummer {

	/**
	 * Zulaessig ist "DSAE".
	 */
	DSAEV01,

	/**
	 * Zulaessig sind im Feld VFMM im VOSZ nur die Werte "KVTWL", "KVTRV",
	 * "BATRV", "RVTBA", "KTTRV", "RVTKT", "BFTDS", "DSTBF", "SOTBF" oder
	 * "UETBF".
	 */
	DSAE004,

	/**
	 * Zulaessig ist "DEUEV".
	 */
	DSAEV05,

	/**
	 * Die Betriebsnummer ist gemaess Ziffer 1.3.2.2 zu pruefen.
	 */
	DSAE020,

	/**
	 * Bei Meldungen der Krankenkassen (VFMM im VOSZ = "KVTWL" oder "KVTRV")
	 * muss es sich um eine gueltige Krankenkassen-Betriebsnummer, bei Meldungen
	 * der Kommunen um die Betriebsnummer einer zugelassenen Kommune und bei
	 * Meldungen der Sonderversorgungstraeger an die Deutsche Rentenversicherung
	 * Bund (VFMM im VOSZ = "SOTBF") um eine gueltige
	 * Sonderversorgungs-Betriebsnummer handeln.
	 */
	DSAEV10,

	/**
	 * Bei Meldungen a) der Bundesagentur fuer Arbeit (VFMM im VOSZ = "BATRV")
	 * muss die Betriebsnummer "76641777" oder "12621621" und b) von
	 * Uebergangsgeld an die Deutsche Rentenversicherung Bund (VFMM im VOSZ =
	 * "UETBF") "98503184" oder "98702232" lauten.
	 */
	DSAE022,

	/**
	 * Die Betriebsnummer ist gemaess Ziffer 1.3.2.2 zu pruefen.
	 */
	DSAE030,

	/**
	 * Bei Meldungen a) der Krankenkassen an die Weiterleitungsstellen (VFMM im
	 * VOSZ = "KVTWL") oder der Krankenkassen an die Rentenversicherung (VFMM im
	 * VOSZ = "KVTRV") ist nur "66667777" oder "98094032", b) der Bundesagentur
	 * fuer Arbeit an die Datenstelle der Rentenversicherung (VFMM im VOSZ =
	 * "BATRV") nur "66667777", c) der Kommunen an die Rentenversicherung (VFMM
	 * im VOSZ = "KTTRV") "66667777 und d) der Datenstelle der
	 * Rentenversicherung an die Bundesagentur fuer Arbeit (VFMM im VOSZ =
	 * "RVTBA") nur "76641777" zulaessig.
	 */
	DSAE032,

	/**
	 * Pruefung, ob es sich um eine zulaessige Betriebsnummer handelt.
	 */
	DSAEV20,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DSAE040,

	/**
	 * Zulaessig ist nur der Wert "01" bis zur Bekanntgabe einer neuen
	 * Versionsnummer.
	 */
	DSAE042,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DSAE050,

	/**
	 * Das Erstellungsdatum muss logisch richtig sein.
	 */
	DSAE052,

	/**
	 * Das Erstellungsdatum darf nicht groesser als das Verarbeitungsdatum sein.
	 */
	DSAE054,

	/**
	 * Die Uhrzeit muss logisch richtig sein.
	 */
	DSAE056,

	/**
	 * Die Uhrzeit darf bei Erstellungsdatum = Verarbeitungsdatum nicht groesser
	 * oder gleich dem Verarbeitungszeitpunkt sein.
	 */
	DSAE058,

	/**
	 * Die Mikrosekunden duerfen nicht generell auf Null stehen.
	 */
	DSAEV30,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DSAE060,

	/**
	 * Zulaessig ist "0", "1" oder "2".
	 */
	DSAE062,

	/**
	 * Bei Meldungen von den Krankenkassen (VFMM im VOSZ = "KVTWL" oder
	 * "KVTRV"), der Bundesagentur fuer Arbeit oder der Kommunen (VFMM im VOSZ =
	 * "BATRV" oder "KTTRV") sowie der Sonderversorgungstraeger (VFMM im VOSZ =
	 * "SOTBF") und bei Meldungen von Uebergangsgeld (VFMM im VOSZ = "UETBF") an
	 * die Deutsche Rentenversicherung Bund ist nur der Wert "0" zulaessig.
	 */
	DSAEV35,

	/**
	 * Der Wert "2" darf nur bei Meldungen von der Datenstelle zu den
	 * Landesversicherungsanstalten verwendet werden.
	 */
	DSAEV42,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DSAE070,

	/**
	 * Ist im Feld FEKZ der Wert "0" angegeben, ist hier nur der Wert "0"
	 * zulaessig.
	 */
	DSAE072,

	/**
	 * Ist im Feld FEKZ der Wert > "0" angegeben, ist hier nur der Wert von "1"
	 * bis "9" zulaessig.
	 */
	DSAEV50,

	/**
	 * Zulaessig ist nur die Zahl, die mit der Anzahl der gezaehlten Fehler im
	 * Datensatz uebereinstimmt (maximal "9").
	 */
	DSAEV52,

	/**
	 * Pruefung auf Vollstaendigkeit und zulaessige Zeichen. Im numerischen Teil
	 * (Stellen 1-8 und 10-12) sind nur Ziffern und fuer den Anfangsbuchstaben
	 * des Namens (Stelle 9) nur ein Grossbuchstabe (ohne Umlaute) zugelassen.
	 */
	DSAE082,

	/**
	 * Die Bereichsnummer (Stellen 1-2) ist auf Zulaessigkeit zu pruefen.
	 * Zulaessig sind die Nummern "02" - "04", "08" - "21", "23" - "26", "28",
	 * "29", "38", "39", "42" - "44", "48" - "61", "63" - "66", "68", "69", "78"
	 * - "82" oder "89".
	 */
	DSAE084,

	/**
	 * Im Bestand der Rentenversicherung sind zu Qualitaetssicherungszwecken
	 * Versicherungsnummern enthalten, die nicht mit Aussenwirkung vergeben
	 * wurden. Die Verwendung dieser Versicherungsnummern ist im Meldeverfahren
	 * unzulaessig.
	 */
	DSAE089,

	/**
	 * Das Geburtsdatum muss grundsaetzlich logisch richtig sein. Naehere
	 * Beschreibung des Aufbaus des Geburtsdatums siehe Ziffer 3.1.1.2.
	 */
	DSAE086,

	/**
	 * Die letzte Ziffer der Versicherungsnummer ist die Pruefziffer; sie ist
	 * auf Richtigkeit zu pruefen. Die Pruefziffer der Versicherungsnummer wird
	 * wie folgt gebildet:
	 * 
	 * Der Buchstabe wird durch eine zweistellige Zahl ersetzt, welche die
	 * Position des Buchstabens im Alphabet (01 bis 26) kennzeichnet.
	 * 
	 * Die Ziffern der damit zwoelfstelligen Nummer werden - an der ersten
	 * Stelle beginnend - mit den Faktoren 2, 1, 2, 5, 7, 1, 2, 1, 2, 1, 2, 1
	 * multipliziert.
	 * 
	 * Von den Produkten werden die Quersummen gebildet. Die Quersummen werden
	 * addiert. Die Summe wird durch 10 dividiert. Der verbleibende Rest ist die
	 * Pruefziffer.
	 * 
	 * Die zweistellige Verschluesselung des Buchstabens wird wieder durch den
	 * Buchstaben ersetzt; die Versicherungsnummer besteht damit aus elf
	 * Informationsstellen und einer Pruefziffer, zusammen zwoelf Stellen.
	 */
	DSAE088,

	/**
	 * Es sind nur die im Feld "Inhalt/ Erlaeuterung" angegebenen Werte
	 * zulaessig.
	 */
	DSAE120,

	/**
	 * Bei Meldungen von der Krankenkasse (VFMM im VOSZ = "KVTWL" oder "KVTRV"),
	 * von der Bundesagentur fuer Arbeit oder den Kommunen (VFMM im VOSZ =
	 * "BATRV" oder "KTTRV") zur Rentenversicherung sind nur "0A", "0B", "0C"
	 * oder "0G" zulaessig.
	 */
	DSAE124,

	/**
	 * Bei Meldungen von der Datenstelle der Rentenversicherung zur Deutschen
	 * Rentenversicherung Bund (VFMM im VOSZ = "DSTBF") sind nur "0B", "BA",
	 * "BB", "BC" oder "BG" zulaessig.
	 */
	DSAE132,

	/**
	 * Die Betriebsnummer ist gemaess Ziffer 1.3.2.2 zu pruefen.
	 */
	DSAE142,

	/**
	 * Bei Meldungen von der Bundesagentur fuer Arbeit oder den Kommunen (VFMM
	 * im VOSZ = "BATRV" oder "KTTRV") zur Rentenversicherung muss die
	 * Betriebsnummer in der Betriebsdatei der Bundesagentur fuer Arbeit
	 * enthalten sein.
	 */
	DSAEE58,

	/**
	 * Bei Meldungen von Uebergangsgeld an die Deutsche Rentenversicherung Bund
	 * (VFMM im VOSZ = "UETBF") muss die Betriebsnummer "98503184" oder
	 * "98702232" lauten.
	 */
	DSAE158,
	
	/**
	 * Bei Meldungen von den privaten Pflegekassen(VFMM im VOSZ = "PVTRV") muss die 
	 * Betriebsnummer mit 996 beginnen.
	 */
	DSAE159,

	/**
	 * Bei Meldungen a) von den Krankenkassen (VFMM im VOSZ = "KVTWL" oder
	 * "KVTRV") muss es sich um eine gueltige Krankenkassen-Betriebsnummer, b)
	 * von den Sonderversorgungstraegern an die Deutsche Rentenversicherung Bund
	 * (VFMM im VOSZ = "SOTBF") um eine gueltige
	 * Sonderversorgungs-Betriebsnummer c) von der Bundesagentur fuer Arbeit
	 * (VFMM im VOSZ = "BATRV") muss es sich um eine zum Meldeverfahren
	 * zugelassene Betriebsnummer einer Arbeitsagentur handeln.
	 */
	DSAEV70,

	/**
	 * Bei Meldungen von der Bundesagentur fuer Arbeit (VFMM im VOSZ = "BATRV")
	 * zur Rentenversicherung muessen a) die Stellen 93 - 100 und 102 - 107
	 * numerisch und ungleich Nullen und b) in Stelle 101 ein Grossbuchstabe
	 * angegeben sein. Die Stellen 108 - 112 werden von der Bundesagentur fuer
	 * Arbeit intern verwendet.
	 */
	DSAE160,

	/**
	 * Zulaessig ist nur die Grundstellung (Leerzeichen).
	 */
	DSAE390,

	/**
	 * Zulaessig ist "N" oder "J".
	 */
	DSAE400,

	/**
	 * Wenn Inhalt = "J", dann muss Feld MMEZ = "N" sein.
	 */
	DSAE402,

	/**
	 * Bei MMAZ = "J" muss Datenbaustein-DBAZ - Anrechnungszeiten vorhanden
	 * sein.
	 */
	DSAE930,

	/**
	 * Bei Meldungen der Sonderversorgungstraeger an die Deutsche
	 * Rentenversicherung Bund (VFMM im VOSZ = "SOTBF") und von Uebergangsgeld
	 * an die Deutsche Rentenversicherung Bund (VFMM im VOSZ = "UETBF") ist nur
	 * "N" zulaessig.
	 */
	DSAE406,

	/**
	 * Zulaessig ist "N" oder "J".
	 */
	DSAE410,

	/**
	 * Wenn Inhalt = "N", dann muss Feld MMAZ = "J" sein.
	 */
	DSAE412,

	/**
	 * Bei MMEZ = "J" muss der Datenbaustein-DBEZ - Entgeltersatzleistungszeiten
	 * vorhanden sein.
	 */
	DSAE931,

	/**
	 * Bei Meldungen der Sonderversorgungstraeger an die Deutsche
	 * Rentenversicherung Bund (VFMM im VOSZ = "SOTBF") und von Uebergangsgeld
	 * an die Deutsche Rentenversicherung Bund (VFMM im VOSZ = "UETBF") ist nur
	 * "J" zulaessig.
	 */
	DSAE416,

	/**
	 * Zulaessig ist nur die Grundstellung (Leerzeichen).
	 */
	DSAE420,

	/**
	 * Zulaessig ist nur die Grundstellung (Leerzeichen), "1" - "9" oder "A".
	 */
	DSAE360,

	/**
	 * Die Werte "1" bis "7", "9" oder "A" sind nur bei Meldungen der
	 * Bundesagentur fuer Arbeit an die Rentenversicherung und zwischen der
	 * Datenstelle der Rentenversicherung und der Deutschen Rentenversicherung
	 * Bund (VFMM im VOSZ = "BATRV", "DSTBF" oder "BFTDS") zulaessig.
	 */
	DSAE362,

	/**
	 * Der Wert "8" ist nur bei Meldungen zwischen den Kommunen und der
	 * Datenstelle und zwischen der Datenstelle Rentenversicherung und der
	 * Deutschen Rentenversicherung Bund (VFMM im VOSZ = "KTTRV", "RVTKT",
	 * "DSTBF" oder "BFTDS") zulaessig.
	 */
	DSAE365,

	/**
	 * Zulaessig ist nur die Grundstellung (Leerzeichen).
	 */
	DSAE430,

	/**
	 * Zulaessig sind die Grundstellung (Leerzeichen) und Ziffern.
	 */
	DSAE550,

	/**
	 * Nur bei Meldungen an die Rentenversicherung (Stellen 3 - 5 des VFMM im
	 * VOSZ = "TRV"), ist die Angabe einer Versionsnummer zulaessig.
	 */
	DSAE555,

	/**
	 * Zulaessig ist nur die Grundstellung (Leerzeichen).
	 */
	DSAE440,

	/**
	 * Ist der eingehende Datensatz fehlerhaft (FEKZ im DSAE = "1"), wird keine
	 * Laengen- und Fehlerpruefung durchgefuehrt.
	 * 
	 * Die Laenge des festen Teils von dem Datensatz DSAE (190 Stellen) und die
	 * Laenge der im Datensatz vorkommenden Datenbausteine (entsprechend "J" in
	 * den Merkmalfeldern von Stelle 171 bis 172) ist zu errechnen und mit der
	 * Laenge des gemeldeten Datensatzes abzugleichen.
	 */
	DSAE910,

	/**
	 * Mehr als 8 Fehler.
	 */
	DSAE920;

	@Override
	public String getNummer() {
		return name();
	}

	@Override
	public FehlerNummer getValueOf(final String fehlerNummer) {
		return valueOf(fehlerNummer);
	}

}
