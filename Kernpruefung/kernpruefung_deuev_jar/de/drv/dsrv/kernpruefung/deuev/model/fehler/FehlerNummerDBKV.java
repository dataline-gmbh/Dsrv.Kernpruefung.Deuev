package de.drv.dsrv.kernpruefung.deuev.model.fehler;

import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;

/**
 * Gelistet sind alle externen Fehlernummern fuer den Baustein DBKV.
 */
public enum FehlerNummerDBKV implements FehlerNummer {

	/**
	 * Zulaessig ist "DBKV".
	 */
	DBKV001,

	/**
	 * Zulaessig ist "N" oder "J".
	 */
	DBKV010,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBKV020,

	/**
	 * Zulaessig sind die Werte "00" (Grundstellung), "01" oder "02".
	 */
	DBKV022,

	/**
	 * Bei Meldungen mit einem Zeitraum- Beginn (ZRBG-KV) nach dem 31.12.2014
	 * ist nur die Grundstellung („00“) zulässig.
	 */
	DBKV024,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBKV030,

	/**
	 * Zulaessig sind nur Werte kleiner 31.
	 */
	DBKV032,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBKV = „N“) und einem
	 * laufenden Entgelt (LFDEG) größer „0“ ist die Grundstellung („00“)
	 * unzulässig.
	 */
	DBKV034,

	/**
	 * Bei Meldungen mit einem: laufenden Entgelt zur KV/PV (LFDKV), laufenden
	 * Entgelt zur RV (LFDRV) oder laufenden Entgelt zur ALV (LFDAV) größer „0“
	 * ist die Grundstellung („00“) unzulässig.
	 */
	DBKV036,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBKV040,

	/**
	 * Zulaessig sind logisch richtige Datumsangaben.
	 */
	DBKV042,

	/**
	 * Der Zeitraum-Beginn darf nicht vor dem 01.01.2012 liegen.
	 */
	DBKV044,

	/**
	 * Bei Meldungen mit einem Erstellungsdatum (ED im DSME) nach dem 31.12.2014
	 * ist nur ein Zeitraum-Beginn nach dem 31.12.2014 zulässig.
	 */
	DBKV046,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBKV050,

	/**
	 * Bei den Meldungen muss ein logisch richtiges Datum vorhanden sein.
	 */
	DBKV052,

	/**
	 * Zeitraum-Ende muss groesser oder gleich dem Zeitraum-Beginn sein.
	 */
	DBKV054,

	/**
	 * Zeitraum-Beginn und Zeitraum-Ende muessen im selben Kalendermonat liegen.
	 */
	DBKV056,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBKV060,

	/**
	 * Entgelt-Pruefung.
	 */
	DBKV062,

	/**
	 * Bei Meldungen mit einem Zeitraum- Beginn (ZRBG-KV) nach dem 31.12.2014
	 * ist nur die Grundstellung (Nullen) zulässig.
	 */
	DBKV064,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBKV070,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBKV080,

	/**
	 * Bei Meldungen mit einem Zeitraum- Beginn (ZRBG-KV) nach dem 31.12.2014
	 * ist nur die Grundstellung (Nullen) zulässig.
	 */
	DBKV082,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBKV090,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBKV) sind nur die Werte
	 * "0" oder "1" zulaessig.
	 */
	DBKV092,

	/**
	 * Bei Meldungen fuer - Auszubildende (PERSGR im DSME = "102", "121" oder
	 * "122"), - Personen, die ein freiwilliges soziales, ein freiwilliges
	 * oekologisches Jahr oder einen Bundesfreiwilligendienst leisten (PERSGR im
	 * DSME = "123") - Beschaeftigte in Altersteilzeit (PERSGR im DSME = "103"),
	 * - Praktikanten (PERSGR im DSME = "105"), - behinderte Menschen in
	 * anerkannten Werkstaetten oder gleichartigen Einrichtungen (PERSGR im DSME
	 * = "107"), - Personen in Einrichtungen der Jugendhilfe,
	 * Berufsbildungswerken oder aehnlichen Einrichtungen fuer behinderte
	 * Menschen (PERSGR im DSME = "111"), - behinderte Menschen, die im
	 * Anschluss an eine Beschaeftigung in einer anerkannten Werkstatt in einem
	 * Integrationsprojekt beschaeftigt sind (PERSGR im DSME = "127"), -
	 * Auszubildende in der Seefahrt (PERSGR im DSME = "141" oder "144"), ist
	 * "1" unzulaessig.
	 */
	DBKV094,

	/**
	 * Bei Meldungen mit einem Zeitraum- Beginn (ZRBG-KV) nach dem 31.12.2014
	 * ist nur der Wert „0“ zulässig.
	 */
	DBKV096,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBKV) ist nur die
	 * Grundstellung zulaessig.
	 */
	DBKV102,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBKV) ist nur die
	 * Grundstellung zulaessig.
	 */
	DBKV112,

	/**
	 * Bei Meldungen mit einem ZRBG-KV zwischen dem 01.01.2013 und 31.12.2014 sind nur numerische
	 * Zeichen zulaessig.
	 */
	DBKV120,

	/**
	 * Bei Meldungen mit einem ZRBG-KV nach dem 31.12.2012 und dem Wert "1" im
	 * Feld KENNZGLE-SV ist nur ein Wert groesser Null zulaessig.
	 */
	DBKV122,

	/**
	 * Bei Meldungen mit einem ZRBG-KV vor dem 01.01.2013 ist nur die
	 * Grundstellung (Leerzeichen) zulaessig.
	 */
	DBKV124,

	/**
	 * Bei Meldungen mit einem Zeitraum- Beginn (ZRBG-KV) nach dem 31.12.2014
	 * ist nur die Grundstellung (Leerzeichen) zulässig.
	 */
	DBKV126,

	/**
	 * Bei Meldungen mit einem ZRBG-KV zwischen dem 01.01.2013 und 31.12.2014 sind nur numerische
	 * Zeichen zulaessig.
	 */
	DBKV130,

	/**
	 * Bei Meldungen mit einem ZRBG-KV vor dem 01.01.2013 ist nur die
	 * Grundstellung (Leerzeichen) zulaessig.
	 */
	DBKV132,

	/**
	 * Bei Meldungen mit einem Zeitraum- Beginn (ZRBG-KV) nach dem 31.12.2014
	 * ist nur die Grundstellung (Leerzeichen) zulässig.
	 */
	DBKV134,

	/**
	 * Bei Meldungen mit einem ZRBG-KV nach dem 31.12.2012 sind nur numeri-sche
	 * Zeichen zulaessig.
	 */
	DBKV140,

	/**
	 * Bei Meldungen mit einem ZRBG-KV nach dem 31.12.2012 sind die
	 * Beitragsgruppen nach der Anlage
	 * "Schluesselzahlen fuer Beitragsgruppen in den Meldungen nach der DEUEV"
	 * (Anlage 1) sowie der Wert "9" in jeder Stelle zulaessig.
	 */
	DBKV142,

	/**
	 * Bei Meldungen mit einem ZRBG-KV vor dem 01.01.2013 ist nur die
	 * Grundstellung (Leerzeichen) zulaessig.
	 */
	DBKV144,

	/**
	 * Bei Meldungen mit einem ZRBG-KV nach dem 31.12.2012 ist nur "W" oder "O"
	 * zulaessig.
	 */
	DBKV150,

	/**
	 * Bei Meldungen mit einem ZRBG-KV vor dem 01.01.2013 ist nur die
	 * Grundstellung (Leerzeichen) zulaessig.
	 */
	DBKV152,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBKV160,

	/**
	 * Entgelt-Pruefung KV/PV.
	 */
	DBKV162,

	/**
	 * Bei Meldungen mit einem Zeitraum- Beginn (ZRBG-KV) vor dem 01.01.2015 ist
	 * nur die Grundstellung (Nullen) zulässig.
	 */
	DBKV164,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBKV170,

	/**
	 * Entgelt-Pruefung RV.
	 */
	DBKV172,

	/**
	 * Bei Meldungen mit einem Zeitraum- Beginn (ZRBG-KV) vor dem 01.01.2015 ist
	 * nur die Grundstellung (Nullen) zulässig.
	 */
	DBKV174,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBKV180,

	/**
	 * Entgelt-Pruefung AV.
	 */
	DBKV182,

	/**
	 * Bei Meldungen mit einem Zeitraum- Beginn (ZRBG-KV) vor dem 01.01.2015 ist
	 * nur die Grundstellung (Nullen) zulässig.
	 */
	DBKV184,

	/**
	 * Zulaessig ist nur die Grundstellung (Leerzeichen).
	 */
	DBKV290,

	/**
	 * Zulaessig ist nur die Datenlaenge 150.
	 */
	DBKV910;

	@Override
	public String getNummer() {
		return name();
	}

	@Override
	public FehlerNummer getValueOf(final String fehlerNummer) {
		return valueOf(fehlerNummer);
	}
}
