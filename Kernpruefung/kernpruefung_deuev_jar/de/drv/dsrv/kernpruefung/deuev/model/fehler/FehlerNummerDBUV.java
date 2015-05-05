package de.drv.dsrv.kernpruefung.deuev.model.fehler;

import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;

/**
 * Fehlernummern der einzelnen Pruefungen im Baustein (DB)UV.
 */
public enum FehlerNummerDBUV implements FehlerNummer {

	/**
	 * Zulaessig ist "DBUV".
	 */
	DBUV001,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBUV020,

	/**
	 * Zulaessig ist nur "1" - "9".
	 */
	DBUV022,

	/**
	 * Zulaessig ist nur die Grundstellung (Leerzeichen).
	 */
	DBUV040,

	/**
	 * Zulaessig sind nur die Werte "A07", "A08", "A09", "B01", "B02", "B03",
	 * "B04", "B05", "B06", "B09" "C01", "C06" und die Grundstellung
	 * (Leerzeichen).
	 */
	DBUV080,

	/**
	 * Meldungen zur Entsparung von an die DRV Bund uebertragenem Wertguthaben
	 * (UVGD = "C01") sind nur durch DRV Bund - Wertguthaben - (BBNRVU im DSME =
	 * "18663937") zulaessig.
	 */
	DBUV082,

	/**
	 * Bei Meldungen der Arbeitgeber an die Krankenkassen (VFMM im VOSZ =
	 * "AGDEU") ist der Wert "C06" unzulaessig.
	 */
	DBUV084,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N") sind nur die
	 * Betriebsnummern der Anlage 20 oder die Grundstellung (Leerzeichen)
	 * zulaessig.
	 */
	DBUV100,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N") ist die
	 * Grundstellung (Leerzeichen) nur a) bei Entsparung von uebertragenem
	 * Wertguthaben durch die DRV Bund (UVGD = "C01") oder b) bei Meldungen
	 * durch die Krankenkassen (UVGD = "C06") zulaessig.
	 */
	DBUV102,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N") mit der
	 * Betriebsnummer eines UV-Traegers gemaess Anlage 19 Teil a ist nur der
	 * UV-GRUND "A08" zulaessig.
	 */
	DBUV103,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N") fuer
	 * Arbeitnehmer, deren Unternehmen Mitglied bei einer landwirtschaftlichen
	 * Berufsgenossenschaft (UVGD = "A08") ist, ist nur eine Betriebsnummer der
	 * Anlage 19 Teil a zulaessig.
	 */
	DBUV104,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N") mit der
	 * Betriebsnummer eines UV-Traegers "01627953", "03701377", "09322747",
	 * "13385729", "18626026", "18645029", "21204943", "26125562", "28143238",
	 * "29086457", "29214533", "34239086", "44861264" oder "98705576" ist nur
	 * der UV-GRUND "A07" oder "A09" zulaessig.
	 */
	DBUV105,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N"), bei denen
	 * die Beitraege zur Unfallversicherung nicht nach dem Arbeitsentgelt
	 * bemessen (UVGD = "A09") werden, ist nur eine Betriebsnummer der Anlage 19
	 * Teil b zulaessig.
	 */
	DBUV106,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N") ist die
	 * Grundstellung (Leerzeichen) nur a) bei Meldungen fuer Arbeitnehmer der
	 * UV-Traeger (UVGD = "A07"), b) bei Meldungen fuer Arbeitnehmer, deren
	 * Unternehmen Mitglied bei einer landwirtschaftlichen Berufsgenossenschaft
	 * (UVGD = "A08") sind, c) bei Meldungen, bei denen die Beitraege zur
	 * Unfallversicherung nicht nach dem Arbeitsentgelt bemessen werden (UVGD =
	 * "A09"), d) bei Entsparung von uebertragenem Wertguthaben durch die
	 * Deutsche Rentenversicherung Bund (UVGD = "C01") oder e) bei Meldungen
	 * durch die Krankenkassen (UVGD = "C06") zulaessig.
	 */
	DBUV120,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N") und ungleich
	 * Grundstellung (Leerzeichen) ist nur die Laenge der Mitgliedsnummer des
	 * jeweiligen Unfallversicherungstraegers gemaess der Anlage 20 zulaessig.
	 */
	DBUV122,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N") und ungleich
	 * Grundstellung (Leerzeichen) sind bei der Mitgliedsnummer nur die fuer den
	 * jeweiligen Unfallversicherungstraeger gemaess der Anlage 20 aufgefuehrten
	 * Zeichen zulaessig.
	 */
	DBUV124,

	/**
	 * Zulaessig sind nur die Betriebsnummern der Anlage 20 oder die
	 * Grundstellung (Leerzeichen).
	 */
	DBUV140,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N") ist die
	 * Grundstellung (Leerzeichen) nur a) bei Meldungen fuer Arbeitnehmer der
	 * UV-Traeger (UVGD = "A07"), b) bei Meldungen fuer Arbeitnehmer, deren
	 * Unternehmen Mitglied bei einer landwirtschaftlichen Berufsgenossenschaft
	 * (UVGD = "A08") sind, c) bei Meldungen, bei denen die Beitraege zur
	 * Unfallversicherung nicht nach dem Arbeitsentgelt bemessen werden (UVGD =
	 * "A09"), d) bei Meldungen fuer die Entsparung von ausschliesslich
	 * sozialversicherungspflichtigem Wertguthaben (UVGD = "B01"), e) bei
	 * Meldungen, bei denen keine UV-Pflicht wegen Auslandsbeschaeftigung
	 * vorliegt (UVGD = "B02"), f) bei Meldungen fuer Arbeitnehmer, welche in
	 * der UV gemaess SGB VII versicherungsfrei sind (UVGD = "B03"), g) bei
	 * Meldungen fuer die Entsparung von uebertragenem Wertguthaben durch die
	 * DRV Bund (UVGD = "C01") oder h) bei Meldungen durch die Krankenkassen
	 * (UVGD = "C06") zulaessig.
	 */
	DBUV142,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N") a) fuer
	 * Arbeitnehmer der UV-Traeger (UVGD = "A07"), b) fuer Arbeitnehmer, deren
	 * Unternehmen Mitglied bei einer landwirtschaftlichen Berufsgenossenschaft
	 * (UVGD = "A08") sind oder c) bei denen die Beitraege zur
	 * Unfallversicherung nicht nach dem Arbeitsentgelt bemessen werden (UVGD =
	 * "A09") ist nur die Grundstellung (Leerzeichen) zulaessig.
	 */
	DBUV144,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N") fuer
	 * Meldezeitraeume ab 01.01.2014 (ZRBG im DBME > 31.12.2013) und einer
	 * Betriebsnummer des zustaendigen UVTraegers (BBNR-UV) ungleich "14066582",
	 * "15087927", "29036720", "42884688", "44888436", "62279404", "67350937",
	 * "87661138", "87661183" oder "63800761" muessen die BBNRUV und die
	 * Betriebsnummer des UVTraegers, dessen Gefahrtarif angewendet wird
	 * (BBNR-GTS) identisch sein.
	 */
	DBUV146,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N") mit Angabe
	 * einer BBNR-GTS (BBNR-GTS ungleich Grundstellung) ist die Grundstellung
	 * (Leerzeichen) unzulaessig.
	 */
	DBUV160,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N") mit Angabe
	 * einer GT-STELLE (GTST ungleich Grundstellung) ist im Feld BBNR-GTST die
	 * Grundstellung (Leerzeichen) unzulaessig.
	 */
	DBUV161,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBUV180,

	/**
	 * Bei Sondermeldungen UV (GD im DSME = "91") ungleich Stornierungen
	 * (KENNZST im DBME = "N") ist die Grundstellung (Nullen) unzulaessig.
	 */
	DBUV182,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N") ist bei den
	 * UV-Gruenden (UVGD) "A07", "A08", "A09", "B01", "B02", "B03", "B04",
	 * "B05", "B06" oder "B09" nur die Grundstellung zulaessig.
	 */
	DBUV183,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N") ohne
	 * beitragspflichtiges Arbeitsentgelt zur Unfallversicherung (UVEG = Nullen)
	 * sind nur die UV-Gruende (UVGD) "A07", "A08", "A09", "B01", "B02", "B03",
	 * "B04", "B05", "B06" oder "B09" zulaessig.
	 */
	DBUV184,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N") mit
	 * beitragspflichtigem Arbeitsentgelt zur Unfallversicherung (UVEG ungleich
	 * Nullen) sind die UV-Gruende (UVGD) "A07", "A08", "A09", "B01", "B02",
	 * "B03", "B04", "B05", "B06" oder "B09" unzulaessig.
	 */
	DBUV185,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N") sind nur
	 * numerische Zeichen zulaessig.
	 */
	DBUV200,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N") ist bei den
	 * UV-Gruenden (UVGD) "A07", "A08", "A09", "B01", "B02", "B03", "B05" oder
	 * "B09" nur die Grundstellung zulaessig.
	 */
	DBUV202,

	/**
	 * Zulaessig ist nur die Datenlaenge 20 + (ANUV * 71).
	 */
	DBUV910,

	/**
	 * Bei Meldungen ungleich Stornierungen (KENNZST im DBME = "N") ist der UVGD
	 * = "A07" nur bei Arbeitnehmern der UV-Traeger (BBNRVU im DSME gemaess
	 * Anlage 19 Teil c) zulaessig.
	 */
	DBUVW01;

	@Override
	public String getNummer() {
		return name();
	}

	@Override
	public FehlerNummer getValueOf(final String fehlerNummer) {
		return valueOf(fehlerNummer);
	}

}
