package de.drv.dsrv.kernpruefung.deuev.model.fehler;

import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;

/**
 * Gelistet sind alle externen Fehlernummern fuer den Baustein DBVR.
 */
public enum FehlerNummerDBVR implements FehlerNummer {

	/**
	 * Zulaessig ist "DBVR".
	 */
	DBVR001,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBVR010,

	/**
	 * Zulaessig sind die Werte "01" - "05", "10" - "11", "80" - "85" oder "99".
	 */
	DBVR012,

	/**
	 * Bei Meldungen von der ZfA, der Bundesagentur fuer Arbeit, der Kommunen
	 * oder der privaten Pflegekassen zur Rentenversicherung (VFMM im VOSZ =
	 * "ZFTRV", "BATRV", "KTTRV" oder "PVTRV") ist nur "01", "04", "80" oder
	 * "99" zulaessig.
	 */
	DBVR014,

	/**
	 * Bei Meldungen von den Krankenkassen zur Rentenversicherung (VFMM im VOSZ
	 * = "KVTRV") ist nur "01", "04", "10", "80" oder "99" zulaessig.
	 */
	DBVR015,

	/**
	 * Bei Meldungen von den sonstigen Stellen zur Rentenversicherung (VFMM im
	 * VOSZ = "BWTRV", "BZTRV" oder "KSTRV" ist nur "01" oder "99" zulaessig.
	 */
	DBVR016,

	/**
	 * Die Angabe einer Interimsversicherungsnummer (Feld "VSNR" im DSME) ist
	 * nur zulaessig, wenn Feld GDMQ = "01", "02", "04", "05", "10", "11" oder
	 * "99" ist.
	 */
	DBVR020,

	/**
	 * Nur bei Meldungen zu Anfragen ob die persoenlichen Daten der/des
	 * Versicherten mit den Informationen der Rentenversicherung uebereinstimmen
	 * (GDMQ = "80" - "85") und bei Anfragen oder Rueckmeldungen nach einer VSNR
	 * (GDMQ = "04" oder "05") ist die Grundstellung (Leerzeichen) im Feld
	 * Geburtsort des Datenbausteins DBGB zulaessig.
	 */
	DBVR022,

	/**
	 * Bei Meldungen ungleich Anfragen ob die persoenlichen Daten der/des
	 * Versicherten mit den Informationen der Rentenversicherung uebereinstimmen
	 * (GDMQ != "80") sind Geburtsdaten, die mehr als 90 Jahre zurueck liegen
	 * (GBDT im Datenbaustein DBGB < Verarbeitungsdatum - 90 Kalenderjahre)
	 * unzulaessig.
	 */
	DBVR024,

	/**
	 * Meldungen der BA und der Kommunen (Stelle 1 - 2 der VSNR im DSME = 88)
	 * zur Vergabe einer VSNR (GDMQ = 01 oder 99) sind fuer Personen unter 14
	 * Jahren (GBDT im DBGB < Verarbeitungsdatum - 14 Jahre) unzulaessig.
	 */
	DBVR025,

	
	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBVR030,

	/**
	 * Zulaessig sind die Werte "00", "02" - "04", "08" - "21", "23" - "26",
	 * "28", "29", "38" - "40", "42" -
	 * "44, "48" - "61", "63" - "66", "68", "69", "78" - "82", oder "89".
	 */
	DBVR032,

	/**
	 * Die Angabe der Bereichsnummer der ZfA (= "40") ist nur zwischen der ZfA
	 * und der Rentenversicherung (VFMM im VOSZ = "ZFTRV" oder "RVTZF")
	 * zulaessig.
	 */
	DBVR034,

	/**
	 * Bei GDMQ = "01", "04", "80" oder "99" ist nur die Grundstellung
	 * (Leerzeichen) zulaessig.
	 */
	DBVR080,

	/**
	 * Bei GDMQ = "02", "03", "10" oder "11" ist die Versicherungsnummer auf
	 * Vollstaendigkeit und zulaessige Zeichen zu pruefen. Im numerischen Teil
	 * (Stellen 1 - 8 und 10 - 12) sind nur Ziffern und fuer den
	 * Anfangsbuchstaben des Namens (Stelle 9) nur ein Grossbuchstabe (ohne
	 * Umlaute) zugelassen.
	 */
	DBVR082,

	/**
	 * Bei GDMQ = "05" ist die Grundstellung (Leerzeichen) oder die Angabe einer
	 * Versicherungsnummer zulaessig. Ist keine Grundstellung (Leerzeichen)
	 * angegeben, sind im numerischen Teil (Stellen 1 - 8 und 10 - 12) nur
	 * Ziffern und fuer den Anfangsbuchstaben des Namens (Stelle 9) nur ein
	 * Grossbuchstabe (ohne Umlaute) zugelassen.
	 */
	DBVR083,

	/**
	 * Die Bereichsnummer (Stellen 1 - 2) ist auf Zulaessigkeit zu pruefen.
	 * Zulaessig sind die Nummern "02" - "04", "08" - "21", "23" - "26", "28",
	 * "29", "38" - "40", "42" - "44", "48" - "61", "63" - "66", "68", "69",
	 * "78" - "82" oder "89".
	 */
	DBVR084,

	/**
	 * Das Geburtsdatum muss grundsaetzlich logisch richtig sein. Bei Personen
	 * ohne bestimmbares Geburtsdatum bzw. bei ausgeschoepfter Seriennummer sind
	 * auch die Tagesangaben "00" oder groesser als "31" und Monatsangaben mit
	 * "00" zulaessig. Nicht zugelassen sind Tagesangaben "96", "98" und "99"
	 * sowie die Monatsangaben ungleich "00" bis "12". Die Tagesangabe "97" ist
	 * nur in Verbindung mit der Monatsangabe "01" bis "12" zulaessig. In den
	 * Faellen, in denen die Seriennummern "49" bzw. "99" ueberschritten werden,
	 * ist die Addition der Zahl 32 oder 64 (bei Personen, die am Ersten eines
	 * Monats geboren sind, auch die Zahl 96) auf die Tagesangabe vorgesehen.
	 */
	DBVR086,

	/**
	 * Die letzte Ziffer der Versicherungsnummer ist die Pruefziffer; sie ist
	 * auf Richtigkeit zu pruefen. Die Pruefziffer der Versicherungsnummer wird
	 * wie folgt gebildet: a) Der Buchstabe wird durch eine zweistellige Zahl
	 * ersetzt, die die Position des Buchstabens im Alphabet (01 bis 26)
	 * kennzeichnet. b) Die Ziffern der damit zwoelfstelligen Nummer werden - an
	 * der ersten Stelle beginnend - mit den Faktoren 2, 1, 2, 5, 7, 1, 2, 1, 2,
	 * 1, 2 und 1 multipliziert. c) Von den Produkten werden die Quersummen
	 * gebildet. Die Quersummen werden addiert. Die Summe wird durch 10
	 * dividiert. Der verbleibende Rest ist die Pruefziffer. d) Die zweistellige
	 * Verschluesselung des Buchstabens wird wieder durch den Buchstaben
	 * ersetzt; die Versicherungsnummer besteht damit aus elf
	 * Informationsstellen und einer Pruefziffer, zusammen zwoelf Stellen.
	 */
	DBVR088,

	/**
	 * Zulaessig ist nur die Datenlaenge 20.
	 */
	DBVR910;

	@Override
	public String getNummer() {
		return name();
	}

	@Override
	public FehlerNummer getValueOf(final String fehlerNummer) {
		return valueOf(fehlerNummer);
	}

}
