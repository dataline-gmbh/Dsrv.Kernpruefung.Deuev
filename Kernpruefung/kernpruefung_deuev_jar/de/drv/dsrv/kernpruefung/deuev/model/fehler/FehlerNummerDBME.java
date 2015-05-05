package de.drv.dsrv.kernpruefung.deuev.model.fehler;

import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;

/**
 * Fehlernummern der einzelnen Pruefungen des Bausteins (DB)GB.
 */
public enum FehlerNummerDBME implements FehlerNummer {

	/**
	 * Zulaessig ist "DBME".
	 */
	DBME001,
	/**
	 * Zulaessig ist "N" oder "J".
	 */
	DBME010,
	/**
	 * Listenmeldungen für kurzfristig Beschaeftigte ungleich Stornierungen duerfen nur in Verbindung mit gleichzeitigen An- und Abmeldungen wegen
	 * Ende der Beschaeftigung abgegeben werden.
	 */
	DBME012,
	/**
	 * Meldungen ungleich Stornierungen der Krankenkassen für unstaendig Beschaeftigte sind nur fuer unstaendig Beschaeftigte zulaessig.
	 */
	DBME013,
	/**
	 * Bei Anmeldungen ungleich Stornierungen sind im Feld Staatsangehoerigkeit die Angaben Jugoslawien, Serbien-Montenegro, Serbien oder abhaengige
	 * Gebiete unzulaessig.
	 */
	DBME018,
	/**
	 * Zulaessig ist sind Leerzeichen, "N", "J", "0", "1" oder "2".
	 */
	DBME020,
	/**
	 * Die Grundstellung (Leerzeichen) ist nur bei Anmeldungen und bei Stornierungsmeldungen zulaessig.
	 */
	DBME021,
	/**
	 * Bei Meldungen ungleich Stornierungen zwischen den Arbeitgebern und den Krankenkassen sowie den Krankenkassen und der Rentenversicherung sind
	 * uebergangsweise bis zum Verarbeitungsdatum 31.12.2006 die Werte "N" und "J" im Feld Kennzeichen Gleitzone zulaessig und werden wie "0"
	 * behandelt.
	 */
	DBME022,
	/**
	 * Bei Meldungen mit einem Erstellungsdatum nach dem 31.12.2008 und Meldezeiten mit einem Zeitraumbeginn nach dem 31.12.2008, muss der
	 * Datenbaustein DBUV immer vorhanden sein.
	 */
	DBME023,
	/**
	 * Bei bestimmten Meldungen "0" oder "1" unzulaessig.
	 */
	DBME024,
	/**
	 * Bei Meldungen (PERSGR im DSME = "301" - "306") ist nur "0" zulaassig.
	 */
	DBME025,
	/**
	 * Bei Meldungen fur Heimarbeiter darf der ZRBG nicht vor dem 01.12.2011 liegen.
	 */
	DBME026,
	/**
	 * Bei Gesonderten Meldungen darf der ZRBG nicht vor dem 01.01.2007 liegen.
	 */
	DBME027,
	/**
	 * Bei Anmeldungen ungleich Stornierun-gen mit angegebenen Statuskennzeichen darf der ZRBG nicht vor dem 01.01.2005 liegen.
	 */
	DBME028,
	/**
	 * Bei Meldungen fur geringfugig Beschaeftigte mit Zeiten ab 01.04.2003 darf der Datenbaustein DBKS - Deutsche Renten-versicherung
	 * Knappschaft-Bahn-See /See-Krankenkasse nicht vorhanden sein (MM-KNV-SEE = "N").
	 */
	DBME029,
	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBME030,
	/**
	 * Meldungen ungleich Stornierungen und dem VSTR = "0B" sind mit einem Zeitraumbeginn nach dem 31.12.2004 unzulassig.
	 */
	DBME031,
	/**
	 * Bei Meldungen eines Stoerfalls oder des Unterschiedbetrags bei Entgeltersatzleistungen wahrend Altersteilzeitdarf der ZRBG nicht vor dem
	 * 01.01.1999 sein.
	 */
	DBME032,
	/**
	 * Bei Meldungen fur geringfugig Beschaeftigte mit Zeiten ab 01.04.2003 darf der Datenbaustein DBKS - Deutsche Renten-versicherung
	 * Knappschaft-Bahn-See /See-Krankenkasse nicht vorhanden sein (MM-KNV-SEE = "N").
	 */
	DBME033,
	/**
	 * Zulaessig sind logisch richtige Datumsangaben.
	 */
	DBME034,
	/**
	 * Bei Meldungen fur Zivildienstleistende oder fur Personen, die ein freiwilliges soziales oder okologisches Jahr leisten, muss der Zeitraumbeginn
	 * (ZRBG) nach der Vollendung des 16. Lebensjahres liegen.
	 */
	DBME035,
	/**
	 * Der ZRBG darf nicht vor dem 01.01.1973 liegen.
	 */
	DBME036,
	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST = "N") fur Beschaeftigte im Haushaltsscheckverfahren (PERSGR im DSME = "201") darf das ZREN nicht
	 * nach dem 31.03.2003 liegen.
	 */
	DBME037,
	/**
	 * Bei Anmeldungen muss der ZRBG kleiner als das Ende des Monats des Verarbeitungsdatums + 2 Kalendermonate sein.
	 */
	DBME038,
	/**
	 * Die Kennung, ob der Beschaeftigte Entgelte unter Anwendung der Gleitzonenregelung erhalten hat, darf bei Meldungen ungleich Stornierungen erst
	 * mit einem ZRBG ab dem 01.01.2003 verwendet werden.
	 */
	DBME039,
	/**
	 * Bei bestimmten Meldungen muss der ZRBG kleiner als das Ende des Monats des Verarbeitungsdatums + 1 Kalendermonat sein.
	 */
	DBME040,
	/**
	 * Bei Meldungen fur geringfugig Beschafaetigte darf der ZRBG nicht vor dem 01.04.1999 liegen.
	 */
	DBME041,
	/**
	 * Bei Meldungen in Insolvenzfaallen der ZRBG kleiner als das Ende des Jahres des Verarbeitungsdatums + 2 Kalenderjahre sein.
	 */
	DBME042,
	/**
	 * Bei Meldungen fur Auszubildende oder bei freiwilligem okologischen Jahr oder einen Bundesfreiwilligendienst leisten darf der ZRBG nicht vor dem
	 * 01.01.2012 liegen.
	 */
	DBME043,
	/**
	 * Bei Meldungen fur Einmalzahlungen oder Stoerfall muss der ZRBG immer der erste Tag eines Monats sein.
	 */
	DBME044,
	/**
	 * Bei Meldungen ungleich Stornierungen für Personen, bei denen eine Beschaeftigung vermutet wird, darf der ZRBG nicht vor dem 01.01.1999 und
	 * nicht nach dem 31.12.2002 liegen.
	 */
	DBME045,
	/**
	 * Bei Meldungen fur Pflegepersonen darf der ZRBG nicht vor dem 01.04.1995 liegen.
	 */
	DBME046,
	/**
	 * Bei Meldungen fur Wehrubungsleistende oder fur Wehrdienstverhaltnisse besonderer Art muss der Zeitraumbeginn nach der Vollendung des 17.
	 * Lebensjahres liegen.
	 */
	DBME047,
	/**
	 * Bei Meldungen ungleich Stornierungen fuer Beschaeftigte im Haushaltsscheckverfahren darf der ZRBG nicht vor dem 01.01.1997 und nach dem
	 * 31.03.2003 liegen.
	 */
	DBME048,
	/**
	 * Bei gleichzeitigen An- und Abmeldungen ist die Grundstellung in der Versicherungsnummer nur fur Meldungen mit einem ZRBG ab dem 01.04.2003
	 * zulaessig.
	 */
	DBME049,
	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBME050,
	/**
	 * Bei Meldungen fur Personen, die ein freiwilliges soziales oder oekologisches Jahr leisten , muss der Zeitraumbeginn nach dem 31.07.2002 liegen.
	 */
	DBME051,
	/**
	 * Bei den anderen Meldungen muss ein logisch richtiges Datum vorhanden sein.
	 */
	DBME052,
	/**
	 * Meldungen ungleich Stornierungen fuer Beschaeftigte oder Seeleute in Altersteilzeit sind erst fur Zeiten ab dem 01.01.1989 zulaessig.
	 */
	DBME053,
	/**
	 * Das ZREN muss fur Anmeldungen (GD im DSME = "10" bis "13") Grundstellung (Nullen) sein.
	 */
	DBME054,
	/**
	 * Meldungen ungleich Stornierungen fuer Beschaeftigte oder Seeleute in Altersteilzeit sind erst fur Zeiten nach Vollendung des 55. Lebensjahres
	 * zulaessig.
	 */
	DBME055,
	/**
	 * Das ZREN muss groesser oder gleich dem ZRBG sein.
	 */
	DBME056,
	/**
	 * Das Jahr des ZREN muss gleich dem Jahr des ZRBG sein.
	 */
	DBME057,
	/**
	 * Bei Jahresmeldungen fur freigestellte Arbeitnehmer und bei Meldungen in Insolvenzfallen zum rechtlichen Ende der Beschaftigung (GD im DSME =
	 * "70" oder "72") muss das ZREN kleiner oder gleich dem Ende des Jahres des Verarbeitungsdatums + 2 Kalenderjahre sein.
	 */
	DBME058,
	/**
	 * Bei Meldungen ungleich Jahresmeldungen fuer freigestellte Arbeitnehmer und ungleich Meldungen in Insolvenzfaellen zum rechtlichen Ende der
	 * Beschaeftigung (GD im DSME ungleich "70" und "72") muss das ZREN kleiner oder gleich dem Ende des Monats des Verarbeitungsdatums + 1
	 * Kalendermonat sein.
	 */
	DBME059,
	/**
	 * Bei Abmeldungen wegen Tod (GD im DSME = "49") muss das ZREN kleiner oder gleich dem Verarbeitungsdatum sein.
	 */
	DBME060,
	/**
	 * Bei Jahresmeldungen (GD im DSME = "50" oder "70") ungleich von der Kuenstlersozialkasse (BBNRVU ungleich "01085914" und "28180427") muss das
	 * ZREN immer der 31.12. eines Jahres sein.
	 */
	DBME061,
	/**
	 * Bei Meldungen fuer Einmalzahlungen (GD im DSME = "54" oder "91") oder von nicht vereinbarungsgemaess verwendetem Wertguthaben (Stoerfall) (GD
	 * im DSME = "55") muss der Monat ZREN gleich dem Monat ZRBG sein.
	 */
	DBME062,
	/**
	 * Bei Meldungen fuer Einmalzahlungen (GD im DSME = "54" oder "91") oder von nicht vereinbarungsgemaess verwendetem Wertguthaben (Storfall) (GD im
	 * DSME = "55") muss das ZREN immer den letzten Tag des Monats beinhalten.
	 */
	DBME063,
	/**
	 * Bei Meldungen fur Zivildienstpflichtige, die ein freiwilliges soziales oder oekologisches Jahr leisten (PERSGR im DSME = "304"), darf das ZREN
	 * nicht nach dem 31.12.2004 liegen.
	 */
	DBME064,
	/**
	 * Bei Meldungen fur kurzfristig Beschaeftigte im Haushaltsscheckverfahren (PERSGR = "210") mit Zeiten ab 01.04.1999 sind die Abgabegruende "50" -
	 * "54" im GD im DSME unzulaessig.
	 */
	DBME065,
	/**
	 * Bei Meldungen ungleich Stornierungen fuer in der Seefahrt beschaeftigte Personen fuer Meldezeiten mit einem Zeitraumbeginn vor dem 01.01.2008
	 * ist nur die Krankenkassenbetriebsnummern der See-Krankenkasse zulaessig.
	 */
	DBME066,
	/**
	 * Bei Meldungen von Wehrdienstverhaeltnissen besonderer Art darf der Zeitraumbeginn nicht vor dem 18.12.2007 liegen.
	 */
	DBME067,
	/**
	 * Bei Meldungen fur Zeiten der besonde-ren Auslandsverwendung darf der Zeitraumbeginn nicht vor dem 13.12.2011 liegen.
	 */
	DBME068,
	/**
	 * Die Kennung, dass der Beschaeftigte Arbeitsentgelt in Zusammenhang mit der Gleitzonenregelung erhalten hat (KENNZGLE = "1" oder "2"), darf bei
	 * Meldungen ungleich Stornierungen (KENNZST = "N") mit einem ZREN vor dem 01.04.2003 nicht verwendet werden.
	 */
	DBME069,
	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBME070,
	/**
	 * Bei Meldungen fur Beschaeftigte, die ausschliesslich in der gesetzlichen Unfallversicherung versichert sind, darf der Zeitraumbeginn nicht vor
	 * dem 01.01.2010 liegen.
	 */
	DBME071,
	/**
	 * Bei Meldungen fur ungleich kurzfristig Beschaeftigte ist nur die Grundstellung (Nullen) zulaessig.
	 */
	DBME072,
	/**
	 * Bei kurzfristig Beschaeftigten ist nur "01" bis "06" zulaessig.
	 */
	DBME074,
	/**
	 * Zulaessig ist die Grundstellung (Leerzeichen), "D" oder "E".
	 */
	DBME082,
	/**
	 * Die Angabe des Waehrungskennzeichens "E" ist erst fuer Zeiten ab dem 01.01.1999 zulaessig.
	 */
	DBME084,
	/**
	 * Die Angabe des Waehrungskennzeichens "D" ist fur Zeiten ab dem 01.01.2002 unzulaessig.
	 */
	DBME086,
	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBME090,
	/**
	 * Bei Meldungen fur Wehruebungsleistende (PERSGR im DSME = "302") fur Zeiten vor dem 01.01.1990 ist nur die Grundstellung (Nullen) zulaessig.
	 */
	DBME091,
	/**
	 * Bei den definierten Meldungen ist nur die Grundstellung (Nullen) zulaessig.
	 */
	DBME092,
	/**
	 * Bei den hier nicht definierten Meldungen mit den Abgabegrunden "51" bis "53" ist das Entgelt = Grundstellung (Nullen) nur zulaessig, wenn der
	 * Monat des ZRBG gleich dem Monat des ZREN ist.
	 */
	DBME093,
	/**
	 * Bei den hier nicht definierten Meldungen mit den Abgabegrunden "50" - "54", "59" oder "70" ist die Grundstellung (Nullen) unzulaessig.
	 */
	DBME094,
	/**
	 * Bei Entgelt ungleich Grundstellung (Nullen) ist die Grundstellung (Leerzeichen) im Feld WG unzulaessig.
	 */
	DBME095,
	/**
	 * Pruefung auf Einhaltung der Beitragsbemessungsgrenze.
	 */
	DBME096,
	/**
	 * Nur bei Meldungen der Arbeitgeber an die Krankenkassen (VFMM = "AGDEU", "WLTKV", "KVTWL") oder bei Stornierungsmeldungen (KENNZST = "J") ist
	 * die Angabe von 1 DM/Euro zulaessig.
	 */
	DBME097,
	/**
	 * Fur Pflegepersonen (PERSGR im DSME = "207" oder "208") darf das ENTGELT 80 % der Bezugsgroesse nicht uebersteigen.
	 */
	DBME098,
	/**
	 * Fur im Haushaltsscheckverfahren gemeldete versicherungspflichtig oder geringfuegig entlohnte Beschaeftigte (PERSGR im DSME = "201" oder "209")
	 * ist fuer Zeiten bis 31.03.2003 (ZRBG kleiner 01.04.2003) hoechstens ein monatliches Entgelt von 1.500 DM bzw. 767 Euro zulaessig.
	 */
	DBME100,
	/**
	 * Meldungen ungleich Stornierungen mit Abgabegrund "34" (GD im DSME) ohne Entgelt (EG gleich Nullen) und einem Meldezeitraum groesser als 2
	 * Monate sind unzulaessig, ausgenommen sind Meldungen für die Personengruppen 110 oder 190.
	 */
	DBME101,
	/**
	 * Bei Meldungen von Wehrdienstverhaeltnissen besonderer Art (PERSGR im DSME = "305") ist die Grundstellung (Nullen) unzulaessig.
	 */
	DBME102,

	/**
	 * Für geringfügig beschäftigte Personen (PERSGR im DSME = „109“ oder „209“) gilt für Meldezeiträume ab dem 01.01.2015 bei einem Meldezeitraum von
	 * bis zu drei Monaten eine maximale Entgelthöhe bis zum dreifachen Betrag der monatlichen Beitragsbemessungsgrenze der Rentenversicherung des
	 * jeweiligen Rechtskreises. Für jeden weiteren Tag ist die Grenze in Höhe von 15 Euro zu beachten.
	 */
	DBME103,

	/**
	 * Für geringfügig beschäftigte Personen (PERSGR im DSME = „109“ oder „209“) gilt für Meldezeiträume bis 31.12.2014 bei einem Meldezeitraum von
	 * bis zu zwei Monaten eine maximale Entgelthöhe bis zum doppelten Betrag der monatlichen Beitragsbemessungsgrenze der Rentenversicherung des
	 * jeweiligen Rechtskreises. Für jeden weiteren Tag ist für Meldezeiträume bis 31.12.2002 die Grenze in Höhe von 21 DM bzw. 11 Euro, ab 01.01.2003
	 * die Grenze in Höhe von 14 Euro und ab 01.01.2013 die Grenze in Höhe von 15 Euro zu beachten.
	 */
	DBME105,
	/**
	 * Die BYGR (RV) = "2", "4" oder "6" ist bei Meldungen fur Zeiten ab 01.01.2005 (ZRBG > 31.12.2004) unzulaessig.
	 */
	DBME106,
	/**
	 * Die BYGR = "0000" ist nur bei bestimmten Meldungen zulaessig.
	 */
	DBME107,
	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST = "N") mit den Personengruppen 1xx sind nur die in der Anlage 16 angegebenen Beitragsgruppen
	 * zulaessig.
	 */
	DBME108,
	/**
	 * Die BYGR (RV) = "5" oder "6" ist bei Meldungen mit Gleitzonenanwendung (KENNZGLE = "1" oder "2") unzulaessig.
	 */
	DBME109,
	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBME110,
	/**
	 * Zulaessig sind die Beitragsgruppen nach der Anlage "Schluesselzahlen fuer Beitragsgruppen in den Meldungen nach der DEUV" (Anlage 1) sowie der
	 * Wert "9" in jeder Stelle.
	 */
	DBME111,
	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST = "N") mit der Kombination der BYGR (KV) = "6" und der BYGR (RV) = "1" ist nur die Personengruppe
	 * (PERSGR im DSME) = "109" zulaessig.
	 */
	DBME113,
	/**
	 * Bei Meldungen fuer bestimmte Personengruppen ("110", "202", "210", "304", "306") ist nur die BYGR = "0000" zulassig.
	 */
	DBME114,
	/**
	 * Die BYGR (RV) = "5" oder "6" ist nur bei Meldungen fur geringfuegig Beschaeftigte (PERSGR im DSME = "109" oder "209") zulaessig.
	 */
	DBME115,
	/**
	 * Bei Meldungen fur Bezieher von Vorruhestandsgeld (PERSGR im DSME = "108") sind nur folgende BYGR zulaessig: - BYGR (KV) = "0", "3", "4" oder
	 * "9" - BYGR (RV) = "0", "1", "2" oder "9" - BYGR (ALV) = "0" oder "9" - BYGR (PV) = "0", "1", "2" oder "9".
	 */
	DBME116,
	/**
	 * Bei Meldungen fur Wehrdienst-, Wehruebungs-, Zivildienstleistende oder Wehrdienstverhaeltnisse besonderer Art (PERSGR im DSME = "301" - "303"
	 * oder "305") ist die BYGR = "0100" fuer Zeiten ab 01.01.2007 (ZRBG > 31.12.2006) unzulaessig.
	 */
	DBME117,
	/**
	 * Bei Meldungen fur Bezieher von Ausgleichsgeld nach dem FELEG (PERSGR im DSME = "116") sind nur folgende BYGR zulaessig: - BYGR (KV) = "0" oder
	 * "3" - BYGR (RV) = "0", "1", "2" oder "9" - BYGR (ALV) = "0" oder "9" - BYGR (PV) = "0", "1", "2" oder "9".
	 */
	DBME118,
	/**
	 * Bei Meldungen fur geringfuegig Beschaeftigte (PERSGR im DSME = "109" oder "209") ist als BYGR (ALV) nur "0", "1" oder "2" zulaessig.
	 */
	DBME119,
	/**
	 * Bei Meldungen fur Beschaeftigte, fur die nur der Arbeitgeberanteil zur Rentenversicherung zu zaehlen ist (PERSGR im DSME = "119"), ist nur die
	 * BYGR (RV) = "3", "4" oder "9" zulaessig.
	 */
	DBME120,
	/**
	 * Bei Meldungen fur Wehrdienst-, Wehruebungs-, Zivildienstleistende oder Wehrdienstverhaeltnisse besonderer Art (PERSGR im DSME = "301" - "303"
	 * oder "305") ist die BYGR = "0110" ist bei Meldungen fur Zeiten vor dem 01.02.2006 (ZRBG < 01.02.2006) unzulaessig.
	 */
	DBME121,
	/**
	 * Die BYGR (KV) = "5" ist nur zulaessig fur Zeiten ab 01.01.1995 (ZRBG > 31.12.1994).
	 */
	DBME122,
	/**
	 * Die BYGR (PV) = "1" oder "2" ist nur zulaessig fur Zeiten ab 01.01.1995 (ZRBG > 31.12.1994).
	 */
	DBME124,
	/**
	 * Die BYGR (KV) = "2" ist fur Zeiten ab 01.01.2009 (ZRBG > 31.12.2008) unzulaessig.
	 */
	DBME125,
	/**
	 * Die BYGR (ALV) = "1" ist nur zulaessig fur Zeiten bis zum Ablauf des Monats der Vollendung des 67.Lebensjahres.
	 */
	DBME126,
	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST = "N") ist die BYGR (ALV) = "2" nur zulaessig fur Zeiten nach Ablauf der Vollendung des
	 * 55.Lebensjahres.
	 */
	DBME128,
	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST = "N") knappschaftlicher Arbeitgeber (Stellen 1 bis 3 der BBNR-VU = "980" oder "098") fuer
	 * Meldezeiten mit einem Zeitraumbeginn vor dem 01.04.2007 (ZRBG kleiner 01.04.2007) mit BYGR (RV) ungleich "0" sind nur die
	 * Krankenkassenbetriebsnummern der Knappschaft (BBNRKK im DSME = "98094032" oder "98094037" zulaessig.
	 */
	DBME129,
	/**
	 * Bei Versicherungszweig = ArV oder KnV-ArV (VSTR im DSME = "0A", "0C", "AC", "BA" oder "BC") ist in der BYGR (RV) nur "0", "1", "3", "5" oder
	 * "9" zulaessig.
	 */
	DBME130,
	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST = "N") fuer nicht deutsche Seeleute (PERSGR im DSME = "140" und SASC im DSME ? "000") ohne Angabe
	 * einer Beitragsgruppe (BYGR = "0000") sind nur die Betriebsnummern der Knappschaft (BBNRKK im DSME) "99086875" oder "98000006" zulaessig.
	 */
	DBME131,
	/**
	 * Bei Versicherungszweig = AV oder KnV-AV (VSTR im DSME = "0B", "0G", "AB",
	 * "AG, "BB" oder "BG") ist in der BYGR (RV) nur "0", "2", "4", "6" oder "9" zulaessig.
	 */
	DBME132,
	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST = "N") fuer bestimmte Personengruppen, (BYGR (KV) = "6") oder (BYGR (RV) = "5" oder "6") fur
	 * Zeiten mit einem Zeitraumbeginn oder Zeitraumende ab 01.04.2003 ist als Betriebsnummer der Krankenkasse (BBNRKK im DSME) nur die
	 * Minijob-Zentrale zulaessig.
	 */
	DBME133,
	/**
	 * Bei Meldungen fur unstandig Beschaeftigte (PERSGR im DSME = "205") ist nur die BYGR (RV) = "0", "1", "2", "3", "4" oder "9" zulaessig.
	 */
	DBME134,
	/**
	 * Bei Meldungen fuer Wehrdienst-, Wehr-uebungs-, Zivildienstleistende oder Wehrdienstverhaeltnisse besonderer Art (PERSGR im DSME = "301" - "303"
	 * oder "305") ist nur die BYGR "0100", "0110" (bis 31.12.2004 fuer Arbeiter/ ab 01.01.2005 fuer die allgemeine Renten-versicherung) oder "0200"
	 * (bis 31.12.2004 fuer Angestellte) zulaessig.
	 */
	DBME135,
	/**
	 * Bei Meldungen fur Kuenstler und Publizisten (PERSGR im DSME = "203") an die Rentenversicherung (VFMM = "KSTRV") fur Zeiten bis 31.12.2004 (ZRBG
	 * kleiner 01.01.2005) ist in der BYGR nur "0200" zulaessig.
	 */
	DBME136,
	/**
	 * Bei Meldungen fur Kuenstler und Publizisten (PERSGR im DSME = "203") sind in den Stellen 1 - 3 der BYGR nur "100", "200" oder "300" bei
	 * Meldungen an die Krankenkasse (VFMM im VOSZ = "KSTKV) zulaessig.
	 */
	DBME137,
	/**
	 * Bei Meldungen fur Pflegepersonen (PERSGR im DSME = "207" oder "208") ist nur die BYGR "0100" oder "0200" zulaessig.
	 */
	DBME138,
	/**
	 * Bei Meldungen fur Kuenstler und Publizisten (PERSGR im DSME = "203") an die Rentenversicherung (VFMM = "KSTRV") fur Zeiten ab 01.01.2005 (ZRBG
	 * grosser 31.12.2004) ist in der BYGR nur "0100" zulaessig.
	 */
	DBME139,
	/**
	 * Bei Meldungen für Meldezeiträume bis 30.11.2011 sind nur Ziffern oder Leerzeichen zulässig.
	 */
	DBME149,

	/**
	 * Für alle Meldungen mit Angabe eines Tätigkeitsschlüssels für Meldezeiträume ab 01.12.2011 sind die in der Anlage 5 Teil B aufgeführten
	 * Schlüssel zulässig.
	 */
	DBME150,
	/**
	 * Bei Meldungen ungleich Stornierungen für Meldezeitraeume ab 01.12.2011 mit a) den Personengruppen (PERSGR im DSME) "102", "121" oder "122" und
	 * einer Betriebsnummer (BBNRVU im DSME) beginnend mit "985" oder "987" oder b) den Personengruppen (PERSGR im DSME) "107", "108", "111", "116",
	 * "203", "204", "207", "208", "209", "210", "301", "302", "303", "304", "305" oder "306" ist nur die Grundstellung (Leerzeichen) zulaessig.
	 */
	DBME151,
	/**
	 * Bei Meldungen ungleich Stornierungen für Meldezeiträume ab 01.12.2011 ungleich - den Personengruppen (PERSGR im DSME) "102", "121" oder "122"
	 * und einer Betriebsnummer (BBNRVU im DSME) beginnend mit "985" oder "987" oder - den Personengruppen (PERSGR im DSME) "107", "108", "111",
	 * "116", "203", "204", "207", "208", "209", "210", "301", "302", "303", "304", "305" oder "306" ist die Grundstellung (Leerzeichen) unzulässig.
	 */
	DBME153,
	/**
	 * Bei Meldungen ungleich Stornierungen für Meldezeiträume ab 01.12.2014 mit den Personengruppen (PERSGR im DSME) "108", "116", "203", "207",
	 * "208", "209", "210", "301", "302", "303", "304", "305" oder "306" ist nur die Grundstellung (Leerzeichen) zulässig.
	 */
	DBME154,
	/**
	 * Bei Meldungen für Meldezeiträume ab 01.12.2014 mit den Personengruppen (PERSGR im DSME) "107", "111" oder "204" ist an den ersten fünf Stellen
	 * für die Angaben zur ausgeübten Tätigkeit (Feld AT) sowohl ein gültiger Schlüssel gemäß Anlage 5 Teil B1 als auch die Grundstellung
	 * (Leerzeichen) zulässig. In den Stellen 6-9 (Felder AS, BA, AÜ VF) sind ausschließlich gültige Schlüssel gemäß Anlage 5 Teil B1 zulässig.
	 */
	DBME156,
	/**
	 * Zulaessig ist "W", "O" oder die Grundstellung (Leerzeichen).
	 */
	DBME160,
	/**
	 * Bei Meldungen fuer a) ungleich unstaendig Beschaeftigte (PERSGR im DSME ungleich "205"), b) ungleich Meldungen für Pflegepersonen (PERSGR im
	 * DSME ungleich "207" und "208") und c) ungleich von der Bundeswehr (PERSGR im DSME ungleich "301", "302" und "305") und d) ungleich vom
	 * Bundesamt fuer den Zivildienst (PERSGR im DSME ungleich "303") und e) fuer Zeiten vor 1999 (ZREN kleiner 01.01.1999 oder, wenn ZREN =
	 * "00000000", dann ZRBG kleiner 01.01.1999) ist *) "W" nur zulaessig bei einer BBNRVU im DSME in den ersten drei Stellen ungleich "001" - "099"
	 * und "987".
	 */
	DBME162,
	/**
	 * Die Grundstellung (Leerzeichen) ist nur bei Meldungen fuer Zivildienstpflichtige, die ein freiwilliges soziales oder oekologisches Jahr leisten
	 * (PERSGR = "304"), zulaessig.
	 */
	DBME163,
	/**
	 * *) (ME162) und "O" nur zulaessig bei einer BBNRVU im DSME in den ersten drei Stellen = "001" - "099" oder "987".
	 */
	DBME164,
	/**
	 * Bei Meldungen fuer Zivildienstpflichtige, die ein freiwilliges soziales oder oekologisches Jahr leisten (PERSGR = "304"), ist nur die
	 * Grundstellung (Leerzeichen) zulaessig.
	 */
	DBME165,
	/**
	 * Meldungen von Wehrdienst-, Wehruebungs- oder Zivildienstzeiten (PERSGR = "301", "302" oder "303") fuer Beitrittsgebietszeiten (KENNZRK = "O")
	 * sind erst fuer Zeiten ab dem 03.10.1990 zulaessig.
	 */
	DBME167,
	/**
	 * Bei Meldungen fuer Zeiten der besonderen Auslandsverwendung (PERSGR im DSME = "306") ist nur "W" zulaessig.
	 */
	DBME168,
	/**
	 * Zulaessig ist "N" oder "J".
	 */
	DBME170,
	/**
	 * Bei Meldungen der Bundeswehr (VFMM im VOSZ = "BWTRV") oder des Bundesamtes fuer den Zivildienst (VFMM im VOSZ = "BZTRV") ist nur "N" zulaessig.
	 */
	DBME172,
	/**
	 * Zulaessig ist nur die Datenlaenge 46.
	 */
	DBME910,
	/**
	 * Bei Meldungen der Arbeitgeber mit einem ZRBG Ende des Jahres des Verarbeitungsdatums minus 5 Kalenderjahre ist ein Hinweis auszugeben.
	 */
	DBMEH10;

	@Override
	public String getNummer() {
		return this.name();
	}

	@Override
	public FehlerNummer getValueOf(final String fehlerNummer) {
		return valueOf(fehlerNummer);
	}
}