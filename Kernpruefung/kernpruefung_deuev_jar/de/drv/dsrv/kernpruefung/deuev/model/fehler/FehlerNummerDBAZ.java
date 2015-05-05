package de.drv.dsrv.kernpruefung.deuev.model.fehler;

import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;

/**
 * Fehlernummern der einzelnen Pruefungen des Bausteins DBAZ.
 */
public enum FehlerNummerDBAZ implements FehlerNummer {

	/**
	 * Zulaessig ist "DBAZ".
	 */
	DBAZ001,

	/**
	 * Zulaessig ist nur die Datenlaenge 23.
	 */
	DBAZ910,

	/**
	 * Zulaessig ist "N" oder "J".
	 */
	DBAZ010,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBAZ020,

	/**
	 * Zulaessig sind die Ziffern "40" - "46", "51", "52" oder "54".
	 */
	DBAZ022,

	/**
	 * Meldungen von Schwangerschaftszeiten (LEAT = "52") sind nur fuer
	 * weibliche Personen (Seriennummer in der VSNR im DSAE = "50" - "99")
	 * zulaessig.
	 */
	DBAZ024,

	/**
	 * Bei Meldungen der Bundesagentur fuer Arbeit (VFMM im VOSZ = "BATRV" sind
	 * nur die Ziffern "40" - "46" zulaessig).
	 */
	DBAZ026,

	/**
	 * Bei Meldungen der Kommunen (VFMM im VOSZ = "KTTRV") sind nur die Ziffern
	 * "41" oder "46" zulaessig.
	 */
	DBAZ027,

	/**
	 * Bei Meldungen von den Krankenkassen (VFMM im VOSZ = "KVTWL" oder "KVTRV")
	 * sind nur die Ziffern "51", "52" oder "54" zulaessig.
	 */
	DBAZ028,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBAZ030,

	/**
	 * Pruefung auf logische Richtigkeit.
	 */
	DBAZ032,

	/**
	 * Meldungen von Sperrzeiten nach § 159 SGB III (LEAT = "40") sind erst fuer
	 * Zeiten ab dem 01.01.1992 zulaessig.
	 */
	DBAZe10,

	/**
	 * Meldungen von Arbeitslosigkeit ohne Vermittlungsbereitschaft nach §252
	 * Abs. 8 SGB VI (LEAT = "42") sind erst fuer Zeiten ab dem 01.05.2003
	 * zulaessig.
	 */
	DBAZ033,

	/**
	 * Bei Meldungen mit einem ZRBG bis 31.12.1991 erfolgt bei Meldungen
	 * ungleich Schulausbildung (LEAT != "54") nur eine Pruefung auf logische
	 * Richtigkeit. Faelle dieser Art werden von der der Sachbearbeitung der
	 * Rentenversicherung mit der folgenden Hinweisnummer zur manuellen
	 * UEberpruefung angezeigt.
	 */
	DBAZv20,

	/**
	 * Bei Meldungen ungleich Stornierung (KENNZST = N) von berufsvorbereitenden
	 * Bildungsmassnahmen (LEAT = "44") muss der Zeitraumbeginn (ZRBG) nach der
	 * Vollendung des 17. Lenbensjahres liegen.
	 */
	DBAZ034,

	/**
	 * Meldungen von Vermittlungssperren nach § 38 Absatz 3 SGB III (LEAT =
	 * "45") sind nur fuer Zeiten ab 01.01.2009 (ZRBG>20081231) zulaessig.
	 */
	DBAZ031,

	/**
	 * Bei Meldungen von Zeiten der Ausbildungssuche (LEAT = "43") muss der
	 * Zeitraumbeginn (ZRBG) nach dem 30.09.2000 liegen.
	 */
	DBAZ035,

	/**
	 * Bei Meldungen von Arbeitslosigkeit ohne Vermittlungsberetschaft nach §
	 * 252 Abs. 8 SGB VI (LEAT = "42") muss der Zeitraumbeginn (ZRBG) nach der
	 * Vollendung des 58. Labensjahres liegen.
	 */
	DBAZ036,

	/**
	 * Bei Meldungen von Zeiten der Ausbildungssuche (LEAT = "43") muss der
	 * Zeitraumbeginn (ZRBG) nach der Vollendung des 14. Lebensjahres liegen.
	 */
	DBAZ037,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST = N) fuer Zeiten ab
	 * 01.01.2005 (ZRBG > 20041231) ist im Feld Versicherungstraeger (VSTR im
	 * DSAE) nur "0A" oder "0C" zulaessig.
	 */
	DBAZ038,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST = N) von Schulausbildung
	 * (LEAT = "54") muss der Zeitraumbeginn (ZRBG) nach der Vollendung des 17.
	 * Lebensjahres liegen.
	 */
	DBAZ039,

	/**
	 * Bei Meldungen von Zeiten des Bezuges von Arbeitslosengeld II (LEAT =
	 * "46") muss der Zeitraumbeginn (ZRBG) nach dem 31.12.2010 liegen.
	 */
	DBAZ041,

	/**
	 * Zulaessig sind numerische Zeichen.
	 */
	DBAZ040,

	/**
	 * Pruefung auf logische Richtigkeit.
	 */
	DBAZ042,

	/**
	 * Das Zeitraumende (ZREN) muss gleich oder groesser dem Zeitraumbeginn
	 * (ZRBG) sein.
	 */
	DBAZ044,

	/**
	 * Das Jahr des Zeitraumende (ZREN) muss bei Meldungen a) ungleich
	 * Schulausbildung (LEAT != "54") und b) ungleich Stornierungen fuer Zeiten
	 * vor dem 01.01.1999 (KENNZST = "N" und ZREN < 19990101) gleich dem Jahr
	 * des Zeitraumbeginn (ZRBG) sein.
	 */
	DBAZ046,

	/**
	 * Das Zeitraumende (ZREN) muss kleiner oder gleich dem Ende des Monats der
	 * Verarbeitung + 3 Kalendermonate sein.
	 */
	DBAZ048,

	/**
	 * Bei Meldungen fuer Zeiten der Schwangerschaft oder Mutterschaft waehrend
	 * der Schutzfrist (LEAT = 52) muss das Zeitraumende (ZREN) kleiner oder
	 * gleich dem Ende des Monats der Verarbeitung + 5 Kalendermonate sein.
	 */
	DBAZ050;

	@Override
	public String getNummer() {
		return name();
	}

	@Override
	public FehlerNummer getValueOf(final String fehlerNummer) {
		return valueOf(fehlerNummer);
	}

}
