package de.drv.dsrv.kernpruefung.deuev.model.fehler;

import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;

/**
 * Fehlernummern der einzelnen Pruefungen im Baustein DSME.
 */
public enum FehlerNummerDSME implements FehlerNummer {

	/**
	 * Unzulaessige Kennung.
	 */
	DSMEV01,

	/**
	 * Zulaessig sind im Feld VFMM im VOSZ nur die Werte "AGDEU", "KVDEU",
	 * "AGTRV", "RVTAG", "WLTKV", "KVTWL", "KVTRV", "RVTKV", "BATRV", "RVTBA",
	 * "KTTRV", "RVTKT", "BWTRV", "RVTBW", "BZTRV", "RVTBZ", "PVTRV", "RVTPV",
	 * "KSTRV", "RVTKS", "KSTKV", "KVTKS", "BFTDS", "DSTBF", "ZFTRV", "RVTZF",
	 * "BDTKV" oder "KVTBD".
	 */
	DSME004,

	/**
	 * Das Verfahren "Vergabe Krankenversichertennummer" (VF = "KVNR") ist nur
	 * bei den Verfahrensmerkmalen (VFMM im VOSZ) "KVTRV", "RVTKV", "KVTWL"
	 * und" "WLTKV" zulaessig.
	 */
	DSME010,

	/**
	 * Die Betriebsnummer ist gemaess Ziffer 1.3.2.2 zu pruefen.
	 */
	DSME020,

	/**
	 * Bei Meldungen a) der Bundesagentur fuer Arbeit (VFMM im VOSZ = "BATRV")
	 * muss die Betriebsnummer "76641777", b) der Bundeswehr (VFMM im VOSZ =
	 * "BWTRV") "32349289", c) des Bundesamtes fuer den Zivildienst (VFMM im
	 * VOSZ = "BZTRV") "38065304", d) der privaten Pflegekassen (VFMM im VOSZ =
	 * "PVTRV") in den ersten 3 Stellen "996", e) bei der Kuenstlersozialkasse
	 * (VFMM im VOSZ = KSTRV") "28180427" und f) der ZfA an die RV (VFMM im VOSZ
	 * = ZFTRV") "02998824" lauten.
	 */
	DSME022,

	/**
	 * Die Betriebsnummer ist gemaess Ziffer 1.3.2.2 zu pruefen.
	 */
	DSME030,

	/**
	 * Bei Meldungen a) der Krankenkassen an die Weiterleitungsstellen (VFMM im
	 * VOSZ = "KVTWL") oder der Krankenkassen an die Rentenversicherung (VFMM im
	 * VOSZ = "KVTRV") ist nur "66667777", b) der ZfA an die Rentenversicherung
	 * (VFMM im VOSZ = "ZFTRV") ist nur "90209055", c) der Bundesagentur fuer
	 * Arbeit oder der Kommunen an die Rentenversicherung (VFMM im VOSZ =
	 * "BATRV" oder "KTTRV") ist nur "66667777", d) der Rentenversicherung an
	 * die Bundesagentur fuer Arbeit (VFMM im VOSZ = "RVTBA") ist nur "76641777,
	 * e) der Bundeswehr (VFMM im VOSZ = "BWTRV") oder des Bundesamtes fuer den
	 * Zivildienst an die Rentenversicherung (VFMM im VOSZ = "BZTRV") ist nur
	 * "66667777" und f) der Arbeitgeber an die Rentenversicherung (VFMM im VOSZ
	 * = "AGTRV") ist nur "66667777" zulaessig.
	 */
	DSME032,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DSME040,

	/**
	 * Zulaessig ist nur der Wert "02" bis zur Bekanntgabe einer neuen
	 * Versionsnummer.
	 */
	DSME042,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DSME050,

	/**
	 * Das Erstellungsdatum muss logisch richtig sein.
	 */
	DSME052,

	/**
	 * Das Erstellungsdatum darf nicht groesser als das Verarbeitungsdatum sein.
	 */
	DSME054,

	/**
	 * Die Uhrzeit muss logisch richtig sein.
	 */
	DSME056,

	/**
	 * Bei Meldungen ungleich a) von den Arbeitgebern zu den Krankenkassen (VFMM
	 * im VOSZ ungleich "AGDEU"), b) von den Arbeitgebern zur Rentenversicherung
	 * (VFMM im VOSZ ungleich AGTRV) und c) den Weiterleitungsstellen zu den
	 * Krankenkassen (VFMM im VOSZ ungleich "WLTKV") darf die Uhrzeit bei
	 * Erstellungsdatum = Verarbeitungsdatum nicht groesser oder gleich dem
	 * Verarbeitungszeitpunkt sein.
	 */
	DSME058,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DSME060,

	/**
	 * Zulaessig ist 0, 1, 2, 3 oder 4.
	 */
	DSME062,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DSME070,

	/**
	 * Ist im Feld FEKZ der Wert "0" angegeben, ist hier nur der Wert "0"
	 * zulaessig.
	 */
	DSME072,

	/**
	 * Bei Anmeldungen (GD = "10" - "13") oder GKV-Monatsmeldungen (GD = "58")
	 * zwischen a1) Arbeitgeber und Krankenkasse (VFMM im VOSZ = "AGDEU" oder
	 * "KVDEU"), a2) der Krankenkassen intern (VFMM im VOSZ = "WLTKV") sowie a3)
	 * der Kuenstlersozialkasse und der Krankenkasse (VFMM im VOSZ = "KSTKV")
	 * oder b) bei Sofortmeldungen (GD = "20") der Arbeitgeber an die
	 * Rentenversicherung (VFMM im VOSZ = "AGTRV") oder c) bei gleichzeitigen
	 * An- und Abmeldungen (GD = "40") für kurzfristig Beschaeftigte (PERSGR =
	 * "110" oder "210") zwischen c1) Arbeitgeber und Krankenkasse (VFMM im VOSZ
	 * = "AGDEU" oder "KVDEU") sowie c2) der Krankenkassen intern (VFMM im VOSZ
	 * = ("WLTKV") ist auch die Grundstellung (Leerzeichen) zulaessig.
	 */
	DSME080,

	/**
	 * Pruefung auf Vollstaendigkeit und zulaessige Zeichen. Im numerischen Teil
	 * (Stellen 1-8 und 10-12) sind nur Ziffern und fuer den Anfangsbuchstaben
	 * des Namens (Stelle 9) nur ein Grossbuchstabe (ohne Umlaute) zugelassen.
	 */
	DSME082,

	/**
	 * Die Bereichsnummer (Stellen 1-2) ist auf Zulaessigkeit zu pruefen.
	 * Zulaessig sind die Nummern "02" - "04", "08" - "21", "23" - "26", "28",
	 * "29", "38", "39", "40", "42" - "44", "48" - "61", "63" - "66", "68",
	 * "69", "78" - "82" oder "89".
	 */
	DSME084,

	/**
	 * Die Bereichsnummer (Stellen 1-2) "40" darf nur in Meldungen zwischen der
	 * ZfA und der RV (VFMM im VOSZ = "ZFTRV" oder "RVTZF") angegeben sein.
	 */
	DSME085,

	/**
	 * Das Geburtsdatum muss grundsaetzlich logisch richtig sein. Naehere
	 * Beschreibung des Aufbaus des Geburtsdatums siehe Ziffer 3.1.1.2.
	 */
	DSME086,

	/**
	 * Die letzte Ziffer der Versicherungsnummer ist die Pruefziffer; sie ist
	 * auf Richtigkeit zu pruefen. Die Pruefziffer der Versicherungsnummer wird
	 * wie folgt gebildet:
	 * 
	 * Der Buchstabe wird durch eine zweistellige Zahl ersetzt, welche die
	 * Position des Buchstabens im Alphabet (01 bis 26) kennzeichnet.
	 * 
	 * Die Ziffern der damit zwoelfstelligen Nummer werden - an der ersten
	 * Stelle beginnend - mit den Faktoren 2, 1, 2, 5, 7, 1, 2, 1, 2, 1, 2 und 1
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
	DSME088,

	/**
	 * Im Bestand der Rentenversicherung sind zu Qualitaetssicherungszwecken
	 * Versicherungsnummern enthalten, die nicht mit Aussenwirkung vergeben
	 * wurden. Die Verwendung dieser Versicherungsnummern ist im Meldeverfahren
	 * unzulaessig.
	 */
	DSME089,

	/**
	 * Bei Meldungen a) zwischen Arbeitgeber und Krankenkasse (VFMM im VOSZ =
	 * "AGDEU"), b) zwischen Arbeitgeber und Rentenversicherung (VFMM im VOSZ =
	 * "AGTRV" und c) der Kuenstlersozialkasse an die Krankenkasse (VFMM im VOSZ
	 * = "KSTKV") ist die Angabe einer Interimsversicherungsnummer (ITVSNR)
	 * unzulaessig.
	 */
	DSME090,

	/**
	 * Bei Meldungen von der Bundesagentur fuer Arbeit oder den Kommunen an die
	 * Rentenversicherung (VFMM im VOSZ = "BATRV" oder "KTTRV") ist nur die
	 * Angabe einer Interimsversicherungs-nummer (ITVSNR) zulaessig.
	 */
	DSME092,

	/**
	 * Die ITVSNR hat grundsaetzlich den gleichen Aufbau wie die VSNR und
	 * unterliegt den gleichen Pruefungen. Ausnahmen: Bei Personen ohne
	 * bestimmbares Ge-burtsdatum sind auch die Tagesangaben "00" und
	 * Monatsangaben mit "00" zulaessig.
	 */
	DSME096,

	/**
	 * Bei Meldungen zwischen der ZfA und der RV (VFMM im VOSZ = "ZFTRV" oder
	 * "RVTZF") ist als Bereichsnummer nur "41" zulaessig.
	 */
	DSME099,

	/**
	 * Bei Meldungen der Deutsche Renten-versicherung Knappschaft-Bahn-See
	 * (BBNRAB = "98000001" oder "98000006") und der See-Kranken-kasse (BBNRAB =
	 * "99086875") ist als Bereichsnummer nur "00" zulaessig.
	 */
	DSME100,

	/**
	 * Nur bei Meldungen zwischen der ZfA und der RV (VFMM im VOSZ = "ZFTRV"
	 * oder "RVTZF") ist als Bereichsnummer "41" zulaessig.
	 */
	DSME101,

	/**
	 * Bei Meldungen der Künstlersozialkasse (VFMM im VOSZ = "KSTRV") ist als
	 * Bereichsnummer nur "77" zulaessig.
	 */
	DSME102,

	/**
	 * Bei Meldungen der Krankenkassen (VFMM im VOSZ = "KVTRV") sind als
	 * Bereichsnummer "83" bis "87" zulaessig.
	 */
	DSME104,

	/**
	 * Bei Meldungen der Bundesagentur fuer Arbeit oder der Kommunen (VFMM im
	 * VOSZ = "BATRV" oder "KTTRV"), ist als Bereichsnummer nur "88" zulaessig.
	 */
	DSME106,

	/**
	 * Bei Meldungen der Bundeswehr (VFMM im VOSZ = "BWTRV"), ist als
	 * Bereichsnummer nur "91" zulaessig.
	 */
	DSME108,

	/**
	 * Bei Meldungen vom Bundesamt für den Zivildienst (VFMM im VOSZ = "BZTRV")
	 * ist als Bereichsnummer nur "92" zulaessig.
	 */
	DSME110,

	/**
	 * Bei Meldungen von privaten Pflegekassen (VFMM im VOSZ = "PVTRV") ist als
	 * Bereichsnummer nur "94" zulaessig.
	 */
	DSME112,

	/**
	 * Es sind nur die im Feld "Inhalt/ Erlaeuterung" angegebenen Werte sowie
	 * die Grundstellung (Leerzeichen) zulaessig.
	 */
	DSME120,

	/**
	 * Bei Meldungen der Arbeitgeber (VFMM im VOSZ = "AGDEU") und der
	 * Krankenkassen intern (VFMM im VOSZ = "WLTKV") sind nur Grundstellung
	 * (Leerzeichen), "0A", "0B", "0C" oder "0G" zulaessig.
	 */
	DSME122,

	/**
	 * Bei Meldungen von den Krankenkassen (VFMM im VOSZ = "KVTWL" oder
	 * "KVTRV"), den privaten Pflegekassen (VFMM im VOSZ = "PVTRV"), der
	 * Bundesagentur fuer Arbeit oder den Kommunen (VFMM im VOSZ = "BATRV" oder
	 * "KTTRV"), der Bundeswehr (VFMM im VOSZ = "BWTRV") oder dem Bundesamt fuer
	 * den Zivildienst (VFMM im VOSZ = "BZTRV") zur Rentenversicherung sind nur
	 * "0A", "0B", "0C" oder "0G" zulaessig.
	 */
	DSME124,

	/**
	 * 4 Bei Meldungen von der Datenstelle der Rentenversicherung zur Deutschen
	 * Rentenversicherung Bund (VFMM im VOSZ = "DSTBF") sind nur "BA", "BB",
	 * "BC" oder "BG" zulaessig.
	 */
	DSME132,

	/**
	 * Nur bei zusammengefassten Meldungen fuer unstaendig Beschaeftigte (PERSGR
	 * = "205") durch die Krankenkassen ist die Grundstellung (Leerzeichen)
	 * zulaessig.
	 */
	DSME140,

	/**
	 * Die Rentenversicherung hat zu Qualitaetssicherungszwecken Betriebsnummern
	 * vergeben lassen, die nicht mit Aussenwirkung vergeben wurden. Die
	 * Verwendung dieser Betriebsnummern ist im Meldeverfahren unzulaessig.
	 */
	DSME141,

	/**
	 * Bei allen anderen Meldungen ist die Betriebsnummer gemaess Ziffer 1.3.2.2
	 * zu pruefen.
	 */
	DSME142,

	/**
	 * Bei Meldungen fuer eine knappschaftliche Beschaeftigung ist VSTR = "0C"
	 * oder "0G" nur zulaessig, wenn die Betriebsnummer in den ersten drei
	 * Stellen "980" oder "098" lautet.
	 */
	DSME143,

	/**
	 * Bei Meldungen von einem Knappschaftsbetrieb (BBNRVU in den ersten drei
	 * Stellen "980" oder "098") sind im Feld Versicherungstraeger (VSTR) die
	 * Werte "0A" oder "0B" unzulaessig.
	 */
	DSME144,

	/**
	 * Bei Meldungen der Bundeswehr (VFMM im VOSZ = "BWTRV") muss die
	 * Betriebsnummer = "32349289" sein.
	 */
	DSME146,

	/**
	 * Bei Meldungen vom Bundesamt für den Zivildienst (VFMM im VOSZ = "BZTRV")
	 * muss die Betriebsnummer = "38065304" sein.
	 */
	DSME148,

	/**
	 * Bei Meldungen von den privaten Pflegekassen (VFMM im VOSZ = "PVTRV") muss
	 * die Betriebsnummer in den ersten 3 Stellen "996" sein.
	 */
	DSME150,

	/**
	 * Bei Meldungen von der Kuenstlersozialkasse (VFMM im VOSZ = "KSTRV" oder
	 * "KSTKV") muss die Betriebsnummer = "01085914" oder "28180427" sein.
	 */
	DSME154,

	/**
	 * Bei Meldungen von der ZfA an die RV (VFMM im VOSZ = "ZFTRV") muss die
	 * Betriebsnummer = "02998824" sein.
	 */
	DSME155,

	/**
	 * Bei Meldungen von der RV an die ZfA (VFMM im VOSZ = "RVTZF") muss die
	 * Betriebsnummer = "90209055" sein.
	 */
	DSME159,

	/**
	 * Bei Meldungen von der Bundesagentur fuer Arbeit (VFMM im VOSZ = "BATRV")
	 * zur Rentenversicherung muessen a) die Stellen 93 - 100 und 102 - 107
	 * numerisch und ungleich Nullen und b) in Stelle 101 ein Grossbuchstabe
	 * angegeben sein. Die Stellen 108 - 112 werden von der Bundesagentur fuer
	 * Arbeit intern verwendet.
	 */
	DSME160,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N"), KENNZSTSO
	 * im DBSO = "N" oder KENNZST im DBKV = "N") sind nur Buchstaben ohne
	 * Umlaute, Ziffern, Leerzeichen, Punkte, Bindestriche oder Schraegstriche
	 * zulaessig.
	 */
	DSME161,

	/**
	 * Bei Meldungen der ZfA an die Rentenversicherung (VFMM im VOSZ = "ZFTRV")
	 * ist nur die Grundstellung (Leerzeichen) zulaessig.
	 */
	DSME168,

	/**
	 * Bei Meldungen für Zivildienstpflichtige, die ein freiwilliges soziales
	 * oder oekologisches Jahr leisten (PERSGR = "304"), ist die Betriebsnummer
	 * des Traegers des freiwilligen sozialen oder oekologischen Jahres
	 * anzugeben.
	 */
	DSME169,

	/**
	 * Bei a) Sofortmeldungen (GD = "20") der Arbeitgeber b) Meldungen fuer
	 * Grundwehrdienst-, Wehruebungs-, Zivildienstleistende,
	 * Wehrdienstverhaeltnisse besonderer Art und Zeiten der besonderen
	 * Auslandsverwendung (PERSGR = numerisch und "301" - "303", "305" oder
	 * "306"), c) Meldungen von der Bundesagentur fuer Arbeit oder den Kommunen
	 * (Stellen 1 - 2 der VSNR = "88") zur Rentenversicherung ist das Feld ohne
	 * Bedeutung und kann auf Grundstellung (Leerzeichen) sein. Sofern bei den
	 * vorstehenden Meldungen das Feld nicht auf Grundstellung (Leerzeichen)
	 * steht und bei allen anderen Meldungen wird die Betriebsnummer gemaess
	 * Ziffer 1.3.2.2 geprueft.
	 */
	DSME170,

	/**
	 * Die Rentenversicherung hat zu Qualitaetssicherungszwecken Betriebsnummern
	 * vergeben lassen, die nicht mit Aussenwirkung vergeben wurden. Die
	 * Verwendung dieser Betriebsnummern ist im Meldeverfahren unzulaessig.
	 */
	DSME171,

	/**
	 * Bei Meldungen der privaten Pflegekassen (VFMM im VOSZ = "PVTRV") und der
	 * Kuenstlersozialkasse an die Rentenversicherung (VFMM im VOSZ = "KSTRV")
	 * ist nur der gleiche Inhalt wie im Feld BBNRVU zulaessig.
	 */
	DSME172,

	/**
	 * Bei Meldungen der Arbeitgeber an die Krankenkassen (VFMM im VOSZ =
	 * "AGDEU") sind die Betriebsnummern "32023311", "35382142", "37912580",
	 * "47056789" und "15451439" unzulaessig.
	 */
	DSME174,

	/**
	 * Bei Meldungen der Arbeitgeber an die Krankenkassen (VFMM im VOSZ =
	 * "AGDEU") muss die BBNRKK gleich der BBNREP sein.
	 */
	DSME176,

	/**
	 * Das Feld kann auch auf Grundstellung (Leerzeichen) stehen; sofern eine
	 * Betriebsnummer angegeben wurde, ist sie gemaess Ziffer 1.3.2.2 zu
	 * pruefen.
	 */
	DSME190,

	/**
	 * Die Rentenversicherung hat zu Qualitaetssicherungszwecken Betriebsnummern
	 * vergeben lassen, die nicht mit Aussenwirkung vergeben wurden. Die
	 * Verwendung dieser Betriebsnummern ist im Meldeverfahren unzulaessig.
	 */
	DSME195,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DSME200,

	/**
	 * Bei Meldungen zwischen dem Arbeitgeber und der Krankenkasse (VFMM im VOSZ
	 * = "AGDEU") in der Stelle 1 nur "1" zulaessig.
	 */
	DSME202,

	/**
	 * Zulaessig sind nur die Grundstellung (Nullen) oder die Personengruppen
	 * der Anlage
	 * "Schluesselzahlen für Personengruppen in den Meldungen nach der DEÜV"
	 * (Anlage 2).
	 */
	DSME204,

	/**
	 * Bei Meldungen mit der Betriebsnum-mer des Verursachers (BBNRVU) in den
	 * ersten 3 Stellen = "985" oder "987" darf die PERSGR nur "102", "103",
	 * "107", "111", "121", "122" oder "204" sein.
	 */
	DSME208,

	/**
	 * Bei Meldungen fuer in der Seefahrt beschaeftigte Personen (PERSGR = "140"
	 * - "144" oder "149") muss die Betriebsnummer des Verursachers (BBNRVU) in
	 * den ersten 3 Stellen = "099" oder "990" - "992" sein.
	 */
	DSME209,
	
	/**
	 * Bei Meldungen ungleich Stornierungen mit den Abgabegründen (GD) „10“ –
	 * „13“ oder „40“ und der Betriebsnummer des Verursachers (BBNRVU) in den
	 * ersten drei Stellen = „985“ oder „987“ darf die PERSGR nur „103“, „107“,
	 * „111“ oder „204“ sein.
	 */
	DSME210,

	/**
	 * Bei Meldungen mit der Betriebsnummer (BBNRVU) = "01085914" oder
	 * "28180427" darf die PERSGR nur "203" sein.
	 */
	DSME212,

	/**
	 * Bei Meldungen der Bundesagentur fuer Arbeit oder der Kommunen an die
	 * Rentenversicherung (VFMM im VOSZ = "BATRV" oder "KTTRV") darf die PERSGR
	 * nur Grundstellung (Nullen) sein.
	 */
	DSME216,

	/**
	 * Meldungen für a) Grundwehrdienstleistende (PERSGR = "301"), b)
	 * Wehruebungsleistende (PERSGR = "302") oder c) Personen, die sich in einem
	 * Wehrdienstverhaeltnis besonderer Art befinden (PERSGR = "305") sind nur
	 * unter der Betriebsnummer der Bundeswehr (BBNRVU) = "32349289" zulaessig.
	 */
	DSME218,

	/**
	 * Meldungen fuer a) Zivildienstleistende (PERSGR = "303") oder b) fuer
	 * Zivildienstpflichtige, die ein freiwilliges soziales oder oekologisches
	 * Jahr leisten (PERSGR = "304") sind nur unter der Betriebnummer des
	 * Bundesamtes fuer den Zivildienst (BBNRVU) = "38065304" zulaessig.
	 */
	DSME222,

	/**
	 * Bei Meldungen für Pflegepersonen (PERSGR = "207" oder "208") muss die
	 * Betriebsnummer (BBNRVU) in den ersten 3 Stellen "996" sein.
	 */
	DSME226,

	/**
	 * Bei Meldungen mit der Betriebsnummer (BBNRVU) in den ersten 3 Stellen
	 * "996" darf die PERSGR nur "207" oder "208" lauten.
	 */
	DSME228,

	/**
	 * Nur bei Meldungen auf dem Meldeweg zwischen a) dem Arbeitgeber und der
	 * Krankenkasse (VFMM = "AGDEU"), b) den Krankenkassen (VFMM = "WLTKV") oder
	 * c) der Kuenstlersozialkasse und der Krankenkasse (VFMM = "KSTKV") ist der
	 * Abgabegrund für GKV-Monatsmeldungen (GD = "58") zulaessig.
	 */
	DSME229,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DSME230,

	/**
	 * Bei Meldungen fuer a) geringfuegig Beschaeftigte (PERSGR = "109"), b)
	 * kurzfristig Beschaeftigte (PERSGR = "110") oder c) für ausschliesslich in
	 * der UV versicherte Beschaeftigte (PERSGR 190) ist der Abgabegrund für
	 * GKV-Monatsmeldungen (GD = "58") unzulaessig.
	 */
	DSME231,

	/**
	 * Zulaessig sind nur die Gruende der Anlage
	 * "Schluesselzahlen für Abgabegruende in den Meldungen nach der DEUEV"
	 * (Anlage 1).
	 */
	DSME232,

	/**
	 * Sofortmeldungen (GD = "20") sind nur auf dem Meldeweg von den
	 * Arbeitgebern zur Rentenversicherung (VFMM im VOSZ = "AGTRV") zulaessig.
	 */
	DSME233,

	/**
	 * Nur bei Anmeldungen (GD = "10" - "13") oder GKV-Monatsmeldungen (GD =
	 * "58") zwischen a) dem Arbeitgeber und der Krankenkasse (VFMM im VOSZ =
	 * "AGDEU" oder "KVDEU"), b) den Krankenkassen intern (VFMM im VOSZ =
	 * "WLTKV") sowie c) der Kuenstlersozialkasse und der Krankenkasse (VFMM im
	 * VOSZ = "KSTKV") oder bei Sofortmeldungen (GD = "20") der Arbeitgeber an
	 * die Rentenversicherung (VFMM im VOSZ = "AGTRV") oder bei gleichzeitigen
	 * An- und Abmeldungen (GD = "40") für kurzfristig Beschaeftigte (PERSGR =
	 * "110" oder "210") zwischen a) Arbeitgeber und Krankenkasse (VFMM im VOSZ
	 * = "AGDEU" oder "KVDEU") sowie b) der Krankenkassen intern (VFMM im VOSZ =
	 * ("WLTKV") ist die Grundstellung (Leerzeichen) im Feld VSNR zulaessig.
	 */
	DSME234,

	/**
	 * Bei Meldungen für Kuenstler und Publizisten (PERSGR = "203") ungleich a)
	 * Namens- und Anschriftenberichtigungen (GD = "60" oder "61"), b)
	 * SVA-Anforderungen (GD = "90") und c) Vergabe/Rueckmeldungen VSNR (GD =
	 * "99") muss die Betriebsnummer (BBNRVU) = "01085914" oder "28180427" sein.
	 */
	DSME235,

	/**
	 * Bei Meldungen der Bundesagentur fuer Arbeit oder der Kommunen an die
	 * Rentenversicherung (VFMM im VOSZ = "BATRV" oder "KTTRV"), der ZfA an die
	 * Rentenversicherung (VFMM im VOSZ = "ZFTRV") oder mit Verfahren
	 * "Vergabe Krankenversichertennummer" (VF = "KVNR") darf GD nur "99" sein.
	 */
	DSME236,

	/**
	 * Meldungen ungleich Sofortmeldungen (GD != "20") sind auf dem Meldeweg von
	 * den Arbeitgebern zur Rentenversicherung (VFMM im VOSZ = "AGTRV")
	 * unzulaessig.
	 */
	DSME237,

	/**
	 * Bei Meldungen der Bundeswehr (VFMM im VOSZ = "BWTRV") und des Bundesamtes
	 * fuer den Zivildienst (VFMM im VOSZ = "BZTRV") darf GD nur "30", "49" oder
	 * "99" sein.
	 */
	DSME238,

	/**
	 * Meldungen der Krankenkassen für unstaendig Beschaeftigte (GD = "59") sind
	 * nur zwischen Krankenkasse und Rentenversicherung (VFMM im VOSZ = "KVTRV",
	 * "KVTWL" oder "WLTKV") oder zwischen der Datenstelle Rentenversicherung
	 * und der Deutschen Rentenversicherung Bund (VFMM im VOSZ = "DSTBF" oder
	 * "BFTDS") zulaessig.
	 */
	DSME239,

	/**
	 * Bei Meldungen der privaten Pflegekassen (VFMM im VOSZ = "PVTRV") darf GD
	 * nur "30", "50", "57", "60", "61" oder "99" sein.
	 */
	DSME240,

	/**
	 * Bei Meldungen von a) Namens- oder Anschriftsaenderungen (GD = "60" oder
	 * "61"), b) Aenderungen des Aktenzeichens/ der Personalnummer (GD = "62"),
	 * c) Aenderungen der Staatsangehoerigkeit (GD = "63"), oder d)
	 * Anforderungen eines SV-Ausweises (GD = "90") oder e) Meldungen zur
	 * Vergabe einer VSNR (GD = "99") und bei Rueckmeldungen f) im Rahmen der
	 * Meldungen fuer geringfuegig Beschaeftigte (GD = "80") ist im Feld
	 * Versicherungstraeger (VSTR) nur die Grundstellung (Leerzeichen), 0A oder
	 * 0C zulaessig.
	 */
	DSME241,

	/**
	 * Bei Angabe einer ITVSNR muss der Grund der Abgabe gleich Vergabe /
	 * Rueckmeldung VSNR (GD = "99") sein.
	 */
	DSME242,

	/**
	 * Bei Meldungen des Unterschiedsbetrages bei Entgeltersatzleistungen
	 * waehrend Altersteilzeit (GD = 56) ist im Feld PERSGR nur die Angabe "103"
	 * oder "142" zulaessig.
	 */
	DSME243,

	/**
	 * Nur bei Sofortmeldungen (GD = "20") der Arbeitgeber, bei Namens- oder
	 * Anschriftsaenderungen (GD = "60" oder "61"), bei Anforderungen eines
	 * SV-Ausweises (GD = "90") oder bei Meldungen zur Vergabe einer VSNR (GD =
	 * "99") ist im Feld PERSGR die Grundstellung (Nullen) zulaessig.
	 */
	DSME244,

	/**
	 * Bei Meldungen für Behinderte (PERSGR = "107") oder Rehabilitanden (PERSGR
	 * = "204") muss bei Meldungen ungleich a) Namens- oder
	 * Anschriftsaenderungen (GD = "60" oder "61"), b) Rueckmeldungen im Rahmen
	 * der Meldungen für geringfuegig Beschaeftigte (GD = "80"), c)
	 * Anforderungen eines SV-Ausweises (GD = "90") oder d) Meldungen zur
	 * Vergabe einer VSNR (GD = "99") die Betriebsnummer des Verursachers
	 * (BBNRVU) in den ersten 3 Stellen = "985" oder "987" sein.
	 */
	DSME245,

	/**
	 * Bei Grund der Abgabe ungleich Anmeldung (GD ungleich "10" - "13"),
	 * GKV-Monatsmeldung (GD ungleich "58"), Sofortmeldung (GD ungleich "20")
	 * und ungleich Vergabe VSNR (GD ungleich "99") ist im Feld VSNR nur die
	 * Angabe einer VSNR zulaessig.
	 */
	DSME246,

	/**
	 * Listenmeldungen fuer kurzfristig Beschaeftigte (PERSGR = "202") sind in
	 * Verbindung mit a) Meldungen zur Aenderung der Staatsangehoerigkeit (GD =
	 * "63") oder b) Anforderungen eines SV-Ausweises (GD = "90") unzulaessig.
	 */
	DSME247,

	/**
	 * Zulaessig sind nur die Kombinationen gemaess Anlage
	 * "Uebersicht moeglicher Kombinationen des Abgabegrundes mit den Datenbausteinen"
	 * (Anlage 4).
	 */
	DSME248,

	/**
	 * Jahresmeldungen oder Abmeldungen wegen Schliessung der Mitgliedschaft
	 * durch die Krankenkasse (GD = "94" oder "95") sind nur zwischen
	 * Krankenkasse und Rentenversicherung (VFMM im VOSZ = "KVTRV", "KVTWL" oder
	 * "WLTKV") oder zwischen der Datenstelle Rentenversicherung und der
	 * Deutschen Rentenversicherung Bund (VFMM im VOSZ = "DSTBF" oder "BFTDS")
	 * zulaessig.
	 */
	DSME249,

	/**
	 * Bei a) Sofortmeldungen (GD = "20") der Arbeitgeber mit Angabe einer
	 * Versicherungsnummer (VSNR ungleich Grundstellung), b) Meldungen von
	 * Namens- oder Anschriftsaenderungen (GD = "60" oder "61") oder c)
	 * Meldungen der privaten Pflegekassen (BBNRVU in den ersten 3 Stellen
	 * "996") ungleich Antraege auf Vergabe einer VSNR (GD ungleich "99") ist
	 * die Grundstellung (Leerzeichen) zulaessig.
	 */
	DSME250,

	/**
	 * Bei Meldungen für Beschaeftigte, die ausschliesslich in der gesetzlichen
	 * Unfallversicherung versichert sind (PERSGR im DSME = 190), sind nur die
	 * Abgabegruende "10" - "13", "20", "30" - "49", "50" - "53", "55", "60" -
	 * "63", "71", "91", "94", "95" und "99" zulaessig.
	 */
	DSME251,

	/**
	 * Fuer alle anderen Meldungen sind nur die vom Statistischen Bundesamt
	 * festgelegten Schluessel (Anlage 8) zulaessig.
	 */
	DSME252,

	/**
	 * Bei Meldungen von a) Aenderungen der Staatsangehoerigkeit (GD = "63")
	 * oder b) Meldungen zur Vergabe einer VSNR (GD = "99") sind im Feld
	 * Staatsangehoerigkeit die Angaben Jugoslawien (SASC = "138"),
	 * Serbien-Montenegro (SASC = "132"), Serbien (SASC = "133"), Sudan (SASC =
	 * "276") oder abhaengige Gebiete (SASC = "195", "199", "295", "299", "395",
	 * "399", "495", "499", "595" oder "599") unzulaessig.
	 */
	DSME253,

	/**
	 * Bei Meldungen a) der Bundeswehr (VFMM im VOSZ = "BWTRV") oder b) des
	 * Bundesamtes fuer den Zivildienst (VFMM im VOSZ = "BZTRV") an die
	 * Rentenversicherung darf nur "000" angegeben sein.
	 */
	DSME254,

	/**
	 * Zulaessig ist "N" oder "J".
	 */
	DSME260,

	/**
	 * Bei Meldungen der Bundesagentur fuer Arbeit oder der Kommunen (VFMM im
	 * VOSZ = "BATRV" oder "KTTRV") darf nur "N" angegeben sein.
	 */
	DSME264,

	/**
	 * Zulaessig ist "N" oder "J".
	 */
	DSME270,

	/**
	 * Bei Meldungen der Bundesagentur fuer Arbeit oder der Kommunen (VFMM im
	 * VOSZ = "BATRV" oder "KTTRV") darf nur "J" angegeben sein.
	 */
	DSME274,

	/**
	 * Zulaessig ist "N" oder "J".
	 */
	DSME280,

	/**
	 * Bei Meldungen der Bundesagentur fuer Arbeit oder der Kommunen (VFMM im
	 * VOSZ = "BATRV" oder "KTTRV") darf nur "J" angegeben sein.
	 */
	DSME284,

	/**
	 * Zulaessig ist "N" oder "J".
	 */
	DSME290,

	/**
	 * Bei Meldungen der Bundesagentur fuer Arbeit oder der Kommunen (VFMM im
	 * VOSZ = "BATRV" oder "KTTRV") darf nur "J" angegeben sein.
	 */
	DSME294,

	/**
	 * Zulaessig ist "N" oder "J".
	 */
	DSME300,

	/**
	 * MMEU = "J" ist nur zulaessig, wenn die Staatsangehoerigkeit eines
	 * Mitgliedslandes der Europaeischen Union oder eines Landes, fuer den das
	 * Abkommen über den Europaeischen Wirtschafts-raum gilt, angegeben ist
	 * (SASC = "124" - "129", "131", "134" - "137", "139", "141" - "143", "145",
	 * "148", "149", "151" - "155", "157", "161", "164", "165", "168" oder
	 * "181").
	 */
	DSME302,

	/**
	 * Bei Meldungen der Bundeswehr (VFMM im VOSZ = "BWTRV") und des Bundesamtes
	 * fuer den Zivildienst (VFMM im VOSZ = "BZTRV") darf nur "N" angegeben
	 * sein.
	 */
	DSME304,

	/**
	 * Zulaessig ist "N" oder "J".
	 */
	DSME316,

	/**
	 * Bei Meldungen mit den Personengruppen (PERSGR) "108", "111", "143",
	 * "203", "204", "205", "207" bis "210" oder "301" bis "306" ist nur "N"
	 * zulaessig.
	 */
	DSME317,

	/**
	 * Bei Meldungen a) der privaten Pflegekassen (VFMM im VOSZ = "PVTRV"), b)
	 * der Bundeswehr und des Bundesamtes für den Zivildienst (VFMM im VOSZ =
	 * "BWTRV" oder "BZTRV" ), c) der Kuenstlersozialkasse (VFMM im VOSZ =
	 * "KSTRV"), d) der Sonderversorgungstraeger an die Deutsche
	 * Rentenversicherung Bund (VFMM im VOSZ = "SOTBF"), e) von Uebergangsgeld
	 * an die Deutsche Rentenversicherung Bund (VFMM im VOSZ = "UETBF"), f) der
	 * Datenstelle der Traeger der Rentenversicherung an die Deutsche
	 * Rentenversicherung Bund (VFMM = "DSTBF") und g) der Zentralen
	 * Zulagenstelle für Altersvermoegen an die Rentenversicherung (VFMM im VOSZ
	 * = "ZFTRV") ist nur "N" zulaessig.
	 */
	DSME318,

	/**
	 * Zulaessig ist "N" oder "J".
	 */
	DSME320,

	/**
	 * Bei Meldungen a) der Bundesagentur für Arbeit oder der Kommunen (VFMM im
	 * VOSZ = "BATRV" oder "KTTRV"), b) der Bundeswehr (VFMM im VOSZ = "BWTRV"),
	 * c) des Bundesamtes für den Zivildienst (VFMM im VOSZ = "BZTRV"), d) der
	 * privaten Pflegekassen (VFMM im VOSZ = "PVTRV"), e) der
	 * Kuenstlersozialkasse (VFMM im VOSZ = "KSTRV" oder "KSTKV") ist nur "N"
	 * zulaessig.
	 */
	DSME322,

	/**
	 * Bei Meldungen zwischen dem Arbeitgeber und den Krankenkassen (VFMM im
	 * VOSZ = "AGDEU") ist "J" nur zulaessig, wenn die Stellen 1 bis 3 der
	 * BBNR-VU gleich "098", "099", "990", "980", "991" oder "992" lauten.
	 */
	DSME324,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N") mit einem
	 * Datenbaustein Meldungen (MMME = "J") ist fuer die Personengruppen "140",
	 * "141", "142", "143", "144" oder "149" nur "J" zulaessig.
	 */
	DSME325,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N") mit den
	 * Personengruppen (PERSGR) "109", "110" oder "190" ist "J" unzulaessig.
	 */
	DSME326,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N") mit "099",
	 * "990", "991" oder "992" in den Stellen 1 bis 3 der BBNRVU und MMKS gleich
	 * "J" muss die Personengruppe "140", "141", "142", "143", "144" oder "149"
	 * sein.
	 */
	DSME327,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N") mit einem
	 * Datenbaustein Meldungen (MMME = "J") und "098" oder "980" in den Stellen
	 * 1 bis 3 der BBNRVU und einer Personengruppe ungleich "109", "110" oder
	 * "190", ist nur "J" zulaessig.
	 */
	DSME328,

	/**
	 * Zulaessig ist "N" oder "J".
	 */
	DSME330,

	/**
	 * Bei Meldungen der Arbeitgeber (VFMM im VOSZ = "AGDEU"), der Krankenkassen
	 * intern (VFMM = "WLTKV"), der Bundesagentur für Arbeit oder der Kommunen
	 * (VFMM im VOSZ = "BATRV" oder "KTTRV"), der Bundeswehr (VFMM im VOSZ =
	 * "BWTRV"), des Bundesamtes für den Zivildienst (VFMM im VOSZ = "BZTRV"),
	 * der privaten Pflegekassen (VFMM im VOSZ = "PVTRV") und der
	 * Kuenstlersozialkasse (VFMM im VOSZ = "KSTRV" oder "KSTKV") ist nur "N"
	 * zulaessig.
	 */
	DSME332,

	/**
	 * Zulaessig ist "N" oder "J".
	 */
	DSME340,

	/**
	 * Bei Meldungen der Arbeitgeber (VFMM im VOSZ = "AGDEU") sowie der
	 * Kuenstlersozialkasse an die Krankenkasse (VFMM im VOSZ = "KSTKV") ist nur
	 * "N" zulaessig.
	 */
	DSME342,

	/**
	 * Bei Meldungen der Bundesagentur fuer Arbeit und der Kommunen (VFMM im
	 * VOSZ = "BATRV" oder "KTTRV") darf nur "J" angegeben sein.
	 */
	DSME344,

	/**
	 * Zulaessig ist "N" oder "J".
	 */
	DSME350,

	/**
	 * "J" ist ausschliesslich bei Meldungen von der Rentenversicherung zur
	 * Krankenkasse (VFMM im VOSZ = "RVTKV" oder "WLTKV" zulaessig.
	 */
	DSME352,

	/**
	 * Zulaessig ist die Grundstellung (Leerzeichen), "1" - "8" oder "A".
	 */
	DSME360,

	/**
	 * Bei Meldungen a) der Bundeswehr (VFMM im VOSZ = "BWTRV"), b) des
	 * Bundesamtes fuer den Zivildienst (VFMM im VOSZ = "BZTRV"), c) der ZfA an
	 * die RV (VFMM im VOSZ = "ZFTRV") oder d) der privaten Pflegekassen (VFMM
	 * im VOSZ = "PVTRV") ist nur die Grundstellung (Leerzeichen) zulaessig.
	 */
	DSME361,

	/**
	 * Die Werte "1" - "7" oder "A" sind nur bei Meldungen zwischen der
	 * Bundesagentur fuer Arbeit und der Rentenversicherung (VFMM im VOSZ =
	 * "BATRV" oder "RVTBA") sowie zwischen der DSRV und der Deutschen
	 * Rentenversicherung Bund (VFMM im VOSZ = "DSTBF" oder "BFTDS") zulaessig.
	 */
	DSME362,

	/**
	 * Der Wert "8" ist nur bei Meldungen zwischen den Kommunen und der
	 * Datenstelle (VFMM im VOSZ = "KTTRV" oder "RVTKT") sowie zwischen der
	 * Datenstelle Rentenversicherung und der Deutschen Rentenversicherung Bund
	 * (VFMM im VOSZ = "DSTBF" oder "BFTDS") zulaessig.
	 */
	DSME365,

	/**
	 * Zulaessig ist die Grundstellung (Leerzeichen), "1", "2", "4", "5" oder
	 * "9".
	 */
	DSME380,

	/**
	 * Bei Meldungen a) Bundesagentur fuer Arbeit (VFMM im VOSZ = "BATRV"), b)
	 * der Kommunen (VFMM im VOSZ = "KTTRV"), c) der Bundeswehr (VFMM im VOSZ =
	 * "BWTRV"), d) des Bundesamtes fuer den Zivildienst (VFMM im VOSZ =
	 * "BZTRV"), e) der ZfA an die RV (VFMM im VOSZ = "ZFTRV") oder f) der
	 * privaten Pflegekassen (VFMM im VOSZ = "PVTRV") ist nur die Grundstellung
	 * (Leerzeichen) zulaessig.
	 */
	DSME381,

	/**
	 * Der Wert "4" ist nur bei Meldungen zwischen den Krankenkassen und der
	 * Rentenversicherung (VFMM im VOSZ = "KVTRV", "KVTWL", "WLTKV" oder
	 * "RVTKV") zulaessig.
	 */
	DSME382,

	/**
	 * Zulaessig ist die Grundstellung (Leerzeichen) oder "D".
	 */
	DSME383,

	/**
	 * Der Wert "2" ist nur bei Stornierungen (KENNZST im DBME = "J", KENNZSTSO
	 * im DBSO = "J" oder KENNZST im DBKV = "J") zulaessig.
	 */
	DSME384,

	/**
	 * Die Angabe "D" ist nur bei Antraegen auf Vergabe von VSNR'n (GD = "99")
	 * zulaessig.
	 */
	DSME385,

	/**
	 * Bei Meldungen a) Bundesagentur fuer Arbeit (VFMM im VOSZ = "BATRV"), b)
	 * der Kommunen (VFMM im VOSZ = "KTTRV"), c) der Bundeswehr (VFMM im VOSZ =
	 * "BWTRV"), d) des Bundesamtes fuer den Zivildienst (VFMM im VOSZ =
	 * "BZTRV"), e) der privaten Pflegekassen (VFMM im VOSZ = "PVTRV") oder f)
	 * der ZfA an die RV (VFMM im VOSZ = "ZFTRV" ist nur die Grundstellung
	 * (Leerzeichen) zulaessig.
	 */
	DSME386,

	/**
	 * Zulaessig ist "N" oder "J".
	 */
	DSME387,

	/**
	 * "J" ist nur bei Meldungen der Arbeitgeber an die Rentenversicherung (VFMM
	 * im VOSZ = "AGTRV") zulaessig.
	 */
	DSME388,

	/**
	 * Zulaessig ist nur die Grundstellung (Leerzeichen), "1" oder "2".
	 */
	DSME400,

	/**
	 * Bei Meldungen ungleich Stornierungen ist der Wert "1" oder "2" nur bei
	 * Anmeldung wegen Beginn einer Beschaeftigung (GD = "10") oder bei
	 * gleichzeitiger An- und Abmeldung wegen Ende der Beschaeftigung (GD =
	 * "40") zulaessig.
	 */
	DSME401,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N") mit den
	 * Personengruppen ungleich "1xx" ist nur die Grundstellung (Leerzeichen)
	 * zulaessig.
	 */
	DSME402,

	/**
	 * Zulaessig ist die Grundstellung (Leerzeichen), "N" oder "J".
	 */
	DSME500,

	/**
	 * Der Wert "J" ist nur bei Meldungen der Deutschen Rentenversicherung Bund
	 * an die Datenstelle Rentenversicherung (VFMM im VOSZ = "DSTBF") zulaessig.
	 */
	DSME542,

	/**
	 * Zulaessig sind die Grundstellung (Leerzeichen) und Ziffern.
	 */
	DSME550,

	/**
	 * Nur bei Meldungen an die Rentenversicherung (Stellen 3 - 5 des VFMM im
	 * VOSZ = "TRV"), ist die Angabe einer Versionsnummer zulaessig.
	 */
	DSME555,

	/**
	 * Zulaessig ist nur "N" oder "J".
	 */
	DSME560,

	/**
	 * Zulaessig ist nur die Grundstellung (Leerzeichen).
	 */
	DSME610,

	/**
	 * Ist der eingehende Datensatz fehlerhaft (FEKZ im DSME = "1"), wird keine
	 * Laengen- und Fehlerpruefung durchgefuehrt. Die Laenge des festen Teils
	 * von dem Datensatz DSME (190 Stellen) und die Laenge der im Datensatz
	 * vorkommenden Datenbausteine (entsprechend "J" in den Merkmalfeldern von
	 * Stelle 171 bis 180, 184 und 189) ist zu errechnen und mit der Laenge des
	 * gemeldeten Datensatzes abzugleichen. Die Laenge des variablen
	 * Datenbausteins DBUV - Unfallversicherung ergibt sich aus Addition der
	 * Laenge des festen Teils des DBUV (020) mit dem Ergebnis aus der
	 * Multiplikation des Feldes "ANZAHL-UV" im DBUV mit der Laenge des
	 * Wiederholteils im DBUV (071). Die Laenge des variablen Datenbausteins
	 * DBRG - Rueckmeldung geringfuegig Beschaeftigte ergibt sich aus der Laenge
	 * des festen Teils des DBRG (208) plus dem Ergebnis der Multiplikation des
	 * Feldes "ZAEHLER" im DBRG mit der Laenge der
	 * "Informationen aus der Sonderdatei" im DBRG (206).
	 */
	DSME910,

	/**
	 * Datensatz enthaelt mehr als 9 Fehler, Pruefung abgebrochen.
	 */
	DSME920,

	/**
	 * Datensatz enthaelt mehr als 9 Hinweise, Pruefung abgebrochen.
	 */
	DSME922,

	/**
	 * Bei MMME = "J" muss der Datenbaustein-DBME - Meldesachverhalt vorhanden
	 * sein.
	 */
	DSME930,

	/**
	 * Bei MMNA = "J" muss der Datenbaustein-DBNA - Name vorhanden sein.
	 */
	DSME931,

	/**
	 * Bei MMGB = "J" muss der Datenbaustein-DBGB - Geburtsangaben vorhanden
	 * sein.
	 */
	DSME932,

	/**
	 * Bei MMAN = "J" muss der Datenbaustein-DBAN - Anschrift vorhanden sein.
	 */
	DSME933,

	/**
	 * Bei MMEU = "J" muss der Datenbaustein-DBEU - Europaeische VSNR vorhanden
	 * sein.
	 */
	DSME934,

	/**
	 * Bei MMUV = "J" muss der Datenbaustein-DBUV - Unfallversicherung vorhanden
	 * sein.
	 */
	DSME935,

	/**
	 * Bei MMKS = "J" muss der Datenbaustein- DBKS - Daten der Deutschen
	 * Rentenversicherung Knappschaft-Bahn-See/See-Krankenkassen-Daten vorhanden
	 * sein.
	 */
	DSME936,

	/**
	 * Bei MMSV = "J" muss der Datenbaustein- DBSV - Sozialversicherungs-ausweis
	 * vorhanden sein.
	 */
	DSME937,

	/**
	 * Bei MMVR = "J" muss der Datenbau-stein- DBVR - Vergabe/Rückmeldung
	 * vorhanden sein.
	 */
	DSME938,

	/**
	 * Bei MMRG = "J" muss der Datenbaustein- DBRG - Rueckmeldung geringfuegig
	 * Beschaeftigte vorhanden sein.
	 */
	DSME939,

	/**
	 * Bei MMSO = "J" muss der Datenbaustein DBSO - Sofortmeldung vorhanden
	 * sein.
	 */
	DSME940,

	/**
	 * Bei MMKV = "J" muss der Datenbaustein DBKV - Datenbaustein
	 * Krankenversicherung vorhanden sein.
	 */
	DSME941;

	@Override
	public String getNummer() {
		return name();
	}

	@Override
	public FehlerNummer getValueOf(final String fehlerNummer) {
		return valueOf(fehlerNummer);
	}
}
