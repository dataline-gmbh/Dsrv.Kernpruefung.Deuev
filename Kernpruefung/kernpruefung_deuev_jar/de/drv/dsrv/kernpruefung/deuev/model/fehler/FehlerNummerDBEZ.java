package de.drv.dsrv.kernpruefung.deuev.model.fehler;

import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;

/**
 * Fehlernummern der einzelnen Pruefungen des Bausteins DBEZ.
 */
public enum FehlerNummerDBEZ implements FehlerNummer {

	/**
	 * Zulaessig ist "DBEZ".
	 */
	DBEZ001,

	/**
	 * Zulaessig ist "N" oder "J".
	 */
	DBEZ010,

	/**
	 * Die Ziffern "05", "08", "10" und "11" sind zurzeit nicht zugelassen, weil
	 * die Leis-tungstraeger nicht am maschinellen Meldeverfahren teilnehmen.
	 * Zulaessig sind die Ziffern "00" - "04", "06", "07", "09", "21" - "23",
	 * "25" - "33", "40" - "46" oder "50".
	 */
	DBEZ020,

	/**
	 * Die privaten Pflegekassen dürfen ausschließlich Meldungen mit den 
	 * Leistungsarten 12 oder 13 abgeben.
	 */
	DBEZ021,
	
	/**
	 * Bei Meldungen von den Krankenkas-sen (VFMM im VOSZ = "KVTWL" oder
	 * "KVTRV") sind nur "00", "01, "04" oder "07" zulaessig.
	 */
	DBEZ022,

	/**
	 * Bei Meldungen von der Bundesagentur fuer Arbeit (VFMM im VOSZ = "BATRV")
	 * ist nur "21" - "23", "25", "27" - "33", "40" - "46" oder "50" zulaessig.
	 */
	DBEZ024,

	/**
	 * Bei Meldungen der Kommunen (VFMM im VOSZ = "KTTRV") ist nur "43" oder
	 * "44" zulaessig.
	 */
	DBEZ025,

	/**
	 * Bei Meldungen von den Sonderversor-gungstraegern an die Deutsche
	 * Renten-versicherung Bund (VFMM im VOSZ = "SOTBF") ist nur "26" zulaessig.
	 */
	DBEZ028,

	/**
	 * Bei Meldungen von UEbergangsgeld an die Deutsche Rentenversicherung Bund
	 * (VFMM im VOSZ = "UETBF") ist nur "03", "06" oder "09" zulaessig.
	 */
	DBEZ029,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBEZ030,

	/**
	 * Zulaessig sind "02", "03" oder "04".
	 */
	DBEZ032,
	
	/**
	 * Bei Meldungen von Pflegeunterstützungsgeld (LEAT = "12" oder "13") darf der Zeitraumbeginn
	 * (ZRBG) nicht vor dem 01.01.2015 liegen.
	 */
	DBEZ037,

	/**
	 * Bei Meldungen von Ausbildungsgeld (LEAT = "46") darf der Zeitraumbeginn
	 * (ZRBG) nicht vor dem 01.01.2009 liegen.
	 */
	DBEZ038,

	/**
	 * Bei Meldungen von Arbeitslosengeld waehrend des Auslandsaufenthalts (LEAT
	 * = "45") darf der Zeitraumbeginn (ZRBG) nicht vor dem 01.05.2010 lie-gen.
	 */
	DBEZ039,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBEZ040,

	/**
	 * Bei Gesonderten Meldungen nach § 194 Abs. 2 SGB VI (GDMQ = "04") darf der
	 * ZRBG nicht vor dem 01.01.2007 liegen.
	 */
	DBEZ041,

	/**
	 * Pruefung auf logische Richtigkeit.
	 */
	DBEZ042,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST = N) fuer Zeiten ab
	 * 01.01.2005 (ZRBG > 20041231) ist im Feld Versicherungstraeger (VSTR im
	 * DSAE) nur "0A" oder "0C" zulaessig.
	 */
	DBEZ043,

	/**
	 * Bei Meldungen von Arbeitslosengeld nach Altersteilzeit (LEAT = "27") oder
	 * fuer Meldungen mit einem Zuschuss-betrag nach dem ATG (LEAT = "28") darf
	 * der Zeitraumbeginn (ZRBG) nicht vor dem 01.05.1996 liegen.
	 */
	DBEZ044,

	/**
	 * Bei Meldungen von Arbeitslosengeld II (LEAT = "43" oder "44") darf der
	 * Zeit-raumbeginn (ZRBG) nicht vor der Vollendung des 15. Lebensjahres
	 * lie-gen.
	 */
	DBEZ045,

	/**
	 * Bei Meldungen fuer Teilarbeitslosengeld (LEAT ="30"), Teilunterhaltsgeld
	 * (LEAT = "31"), Teiluebergangsgeld (LEAT = "32"), Teiluebergangsgeld
	 * waehrend Be-rufsausbildung (LEAT = "33") und An-schlussunterhaltsgeld
	 * (LEAT = "42") darf der Zeitraumbeginn (ZRBG) nicht vor dem 01.01.1998
	 * liegen.
	 */
	DBEZ046,

	/**
	 * Bei Meldungen von Arbeitslosengeld II (LEAT = "43" oder "44") darf der
	 * Zeit-raumbeginn (ZRBG) nicht vor dem 01.01.2005 liegen.
	 */
	DBEZ047,

	/**
	 * Bei Meldungen von Leistungen zur Entgeltsicherung fuer aeltere
	 * Arbeitneh-mer gemaess § 417 SGB III (LEAT = "50") darf der Zeitraumbeginn
	 * (ZRBG) nicht vor dem 01.01.2003 liegen.
	 */
	DBEZ048,

	/**
	 * Bei Meldungen von Leistungen zur Entgeltsicherung fuer aeltere
	 * Arbeitneh-mer gemaess § 417 SGB III (LEAT = "50") muss der Zeitraumbeginn
	 * (ZRBG) nach der Vollendung des 50. Lebens-jahres liegen.
	 */
	DBEZ049,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBEZ050,

	/**
	 * Pruefung auf logische Richtigkeit.
	 */
	DBEZ052,

	/**
	 * Das Zeitraumende (ZREN) muss gleich oder groesser dem Zeitraumbeginn
	 * (ZRBG) sein.
	 */
	DBEZ054,

	/**
	 * Das Jahr des Zeitraumende (ZREN) muss gleich dem Jahr des Zeitraumbe-ginn
	 * (ZRBG) sein.
	 */
	DBEZ056,

	/**
	 * Das Zeitraumende (ZREN) muss klei-ner oder gleich dem Ende des Monats der
	 * Verarbeitung + 1 Kalendermonat sein.
	 */
	DBEZ058,

	/**
	 * Bei Meldungen von Anschlussunter-haltsgeld nach § 156 SGB III (LEAT =
	 * "42") darf das Zeitraumende (ZREN) nicht nach dem 31.03.2003 liegen.
	 */
	DBEZ060,

	/**
	 * Bei Meldungen von Eingliederungs-geld/-hilfe (LEAT = "23") und
	 * Arbeitslo-senhilfe (LEAT = "41") darf das Zeit-raumende (ZREN) nicht nach
	 * dem 31.12.2004 liegen.
	 */
	DBEZ061,

	/**
	 * Bei Meldungen von Leistungen zur Entgeltsicherung fuer aeltere
	 * Arbeitneh-mer gemaess § 417 SGB III (LEAT = "50") darf das Zeitraumende
	 * (ZREN) nicht nach dem 31.12.2013 liegen.
	 */
	DBEZ062,

	/**
	 * Bei Meldungen von Arbeitslosengeld II (LEAT = "43" oder "44") darf das
	 * Zeit-raumende (ZREN) nicht nach dem Ende des Kalendermonats liegen, in
	 * dem das 67. Lebensjahr vollendet wur-de.
	 */
	DBEZ064,

	/**
	 * Zulaessig ist die Grundstellung (Leerzei-chen), "D" oder "E".
	 */
	DBEZ082,

	/**
	 * Die Angabe des Waehrungskennzei-chens "E" ist erst fuer Zeiten ab dem
	 * 01.01.2002 zulaessig.
	 */
	DBEZ084,

	/**
	 * Die Angabe des Waehrungskennzei-chens "D" ist fuer Zeiten ab dem
	 * 01.01.2002 unzulaessig.
	 */
	DBEZ086,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBEZ090,

	/**
	 * Die Grundstellung (Nullen) ist fuer Zeiten ab 1992 (ZRBG > 19911231) mit
	 * Aus-nahme der Meldungen von Arbeitslo-sengeld II LEAT = "43" oder "44")
	 * unzu-laessig.
	 */
	DBEZ094,

	/**
	 * Bei Entgelt ungleich Grundstellung (Nullen) ist die Grundstellung
	 * (Leerzei-chen) im Feld WG unzulaessig.
	 */
	DBEZ095,

	/**
	 * Es gelten die Beitragsbemessungs-grenzen und Bezugsgroessen der
	 * Ren-tenversicherung der Arbeiter / Ange-stellten bzw. der
	 * knappschaftlichen Rentenversicherung. Die Pruefung der
	 * Beitragsbemessungs-grenze erfolgt unter Beruecksichtigung des Zuschlags
	 * zur Beitragsbemes-sungsgrenze (siehe Ziffer 2.2.3).
	 */
	DBEZ096,

	/**
	 * Bei Meldungen von Arbeitslosengeld II (LEAT = "43" oder "44") darf das
	 * mo-natliche Entgelt fuer Zeiten bis 31.12.2006 nicht ueber 400 Euro
	 * liegen.
	 */
	DBEZ097,

	/**
	 * Bei Meldungen von Arbeitslosengeld II (LEAT = "43" oder "44") darf das
	 * mo-natliche Entgelt fuer Zeiten ab 01.01.2007 nicht ueber 205 Euro
	 * liegen.
	 */
	DBEZ098,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBEZ100,

	/**
	 * Der Beitragsanteil (BY) darf fuer Meldungen mit den Leistungsarten (Feld
	 * LEAT) = "02", "03", "06", "09", "21" -
	 * "23, "25" - "33", "40" - "44" oder "50" nur auf Grundstellung (Nullen)
	 * stehen.
	 */
	DBEZ102,

	/**
	 * Der Beitragsanteil (BY) darf nicht groesser sein, als der Betrag, der
	 * sich aus der Multiplikation der Beitragsbemes-sungsgrenze der
	 * knappschaftlichen Rentenversicherung mit der Haelfte des Beitragssatzes
	 * der Arbeiter- und Ange-stelltenversicherung bzw. ab 01.01.2005 der
	 * allgemeinen Renten-versicherung ergibt.
	 */
	DBEZ104,

	/**
	 * Bei Beitragsanteil (BY) ungleich Grund-stellung (Nullen) ist die
	 * Grundstellung (Leerzeichen) im Feld WG unzulaessig.
	 */
	DBEZ106,

	/**
	 * Zulaessig ist "W" oder "O".
	 */
	DBEZ160,

	/**
	 * Meldungen von Altersuebergangsgeld oder Versorgungsleistungen nach § 9
	 * Abs. 1 Nr 1b-d AAUEG (LEAT = "25" oder "26") sind nur mit KENNZRK = "O"
	 * zulaessig.
	 */
	DBEZ164,

	/**
	 * Meldungen von Eingliederungsgeld oder Eingliederungshilfe (LEAT = "23")
	 * und Arbeitslosengeld II (LEAT = "43" oder "44") sind nur mit KENNZRK =
	 * "W" zulaessig.
	 */
	DBEZ166,

	/**
	 * Zulaessig sind "N" oder "J".
	 */
	DBEZ180,

	/**
	 * Zulaessig ist nur die Datenlaenge 41.
	 */
	DBEZ910;

	@Override
	public String getNummer() {
		return this.name();
	}

	@Override
	public FehlerNummer getValueOf(final String fehlerNummer) {
		return valueOf(fehlerNummer);
	}
}
