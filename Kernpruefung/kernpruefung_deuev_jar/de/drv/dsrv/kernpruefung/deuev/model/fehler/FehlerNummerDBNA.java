package de.drv.dsrv.kernpruefung.deuev.model.fehler;

import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;

/**
 * Fehlernummern der einzelnen Pruefungen des Bausteins DBNA.
 */
public enum FehlerNummerDBNA implements FehlerNummer {
	
	// Fehler der externen Pruefungen
	/**
	 * Zulaessig ist "DBNA".
	 */
	DBNA001,
	/**
	 * Der Familienname fehlt.
	 */
	DBNA005,
	/**
	 * Gleiche Sonder- und Leerzeichen duerfen nicht mehrfach aufeinander
	 * folgen.
	 */
	DBNA010,
	/**
	 * Zu Beginn des Feldes sind mehr als 2 gleiche, aufeinander folgende
	 * Buchstaben unzulaessig.
	 */
	DBNA011,
	/**
	 * Vor oder nach Bindestrichen sind keine Leerzeichen erlaubt.
	 */
	DBNA012,
	/**
	 * Zulässig sind Buchstaben, Leerzeichen, Bindestriche, Apostrophe, Ziffern,
	 * Klammern oder ein Punkt.
	 */
	DBNA014,
	/**
	 * Der Familienname enthaelt mehr als 2 Ziffern bzw. 2 Ziffern, die nicht
	 * unmittelbar aufeinander folgen.
	 */
	DBNA015,
	/**
	 * Vor einer Ziffer muss ein Leerzeichen stehen (z. B. Maier 3).
	 */
	DBNA018,
	/**
	 * Auf der ersten Stelle des Feldes ist nur ein Buchstabe ungleich "ß"
	 * zugelassen.
	 */
	DBNA020,
	/**
	 * Das Pluszeichen ist nur auf der ersten Stelle zulässig und 
	 * die restlichen Stellen müssen Grundstellung(Leerzeichen) sein.
	 */
	DBNA021,
	/**
	 * Auf der letzten Stelle ist nur ein Buchstabe, eine Ziffer, eine
	 * schliessŸende Klammer oder ein Punkt zulaessig.
	 */
	DBNA022,
	/**
	 * Der Vorname fehlt.
	 */
	DBNA028,
	/**
	 * Bei Meldungen der Krankenkassen an die Weiterleitungsstellen (Stellen 3-5
	 * des VFMM im VOSZ = "TWL") und zur Rentenversicherung (Stellen 3-5 des
	 * VFMM im VOSZ = "TRV") muss der Vorname aus mindestens zwei Buchstaben
	 * bestehen.
	 */
	DBNA029,
	/**
	 * Gleiche Sonder- und Leerzeichen duerfen nicht mehrfach aufeinander
	 * folgen.
	 */
	DBNA030,
	/**
	 * Zu Beginn des Feldes sind mehr als 2 gleiche, aufeinander folgende
	 * Buchstaben unzulaessig.
	 */
	DBNA031,
	/**
	 * Vor oder nach Bindestrichen sind keine Leerzeichen erlaubt.
	 */
	DBNA032,
	/**
	 * Zulaessig sind Buchstaben, Bindestriche, Leerzeichen, +.
	 */
	DBNA034,
	/**
	 * Ein fiktiver Inhalt wie Ohne, Unbekannt o.ae. ist angegeben.
	 */
	DBNA035,
	/**
	 * Auf erster bzw. letzter Stelle sind nur Buchstaben bzw + erlaubt.
	 */
	DBNA036,
	/**
	 * Das Pluszeichen ist nur auf der ersten Stelle zulässig und 
	 * die restlichen Stellen müssen Grundstellung(Leerzeichen) sein.
	 */
	DBNA037,
	/**
	 * Es ist eine unzulässige Kombination von Vor- und Familienname angege-ben
	 * (z. B. Storno, Storno).
	 */
	DBNA038,
	/**
	 * Das Pluszeichen ist entweder im Feld FMNA oder VONA zulässig.
	 */
	DBNA039,
	/**
	 * Gleiche Sonder- und Leerzeichen dür-fen nicht mehrfach aufeinanderfolgen.
	 */
	DBNA040,
	/**
	 * Zulässig sind Buchstaben, Leerzeichen, Apostrophe oder Punkte.
	 */
	DBNA044,
	/**
	 * Auf der ersten Stelle des Vorsatzwor-tes ist nur ein Buchstabe
	 * zugelassen.
	 */
	DBNA046,
	/**
	 * Vor einem Punkt ist mindestens ein Buchstabe erforderlich.
	 */
	DBNA048,
	/**
	 * Zulaessig sind nur die Vorsatzworte der Anlage
	 * "Tabelle der gültigen Vorsatz-worte" (Anlage 6). Der Pruefung auf
	 * Zulaessigkeit ist nur der Teil bis zum ersten Zeichen ungleich Alpha
	 * zugrunde zu legen.
	 */
	DBNA050,
	/**
	 * Gleiche Sonder- und Leerzeichen duerfen nicht mehrfach aufeinanderfolgen.
	 */
	DBNA060,
	/**
	 * Zulässig sind Buchstaben, Leerzei-chen, Apostrophe oder Punkte.
	 */
	DBNA064,
	/**
	 * Auf der ersten Stelle des Namenszu-satzes ist nur ein Buchstabe
	 * zugelas-sen.
	 */
	DBNA066,
	/**
	 * Vor einem Punkt ist mindestens ein Buchstabe erforderlich.
	 */
	DBNA068,
	/**
	 * Zulässig sind nur die Namenszusätze der Anlage
	 * "Tabelle der gültigen Na-menszusätze" (Anlage 7).
	 */
	DBNA070,
	/**
	 * Gleiche Sonder- und Leerzeichen dür-fen nicht mehrfach aufeinanderfolgen.
	 */
	DBNA080,
	/**
	 * Mindestens 3 gleiche aufeinanderfol-gende Buchstaben am Beginn des Titels
	 * sind unzulässig.
	 */
	DBNA081,
	/**
	 * Vor und nach Bindestrichen sind keine Leerzeichen erlaubt.
	 */
	DBNA082,
	/**
	 * Zulässig sind Buchstaben, Leerzei-chen, Punkte, Bindestriche oder
	 * Klam-mern.
	 */
	DBNA084,
	/**
	 * Das Feld muss mit einem Buchstaben beginnen.
	 */
	DBNA086,
	/**
	 * Vor einem Punkt ist mindestens ein Buchstabe erforderlich.
	 */
	DBNA088,
	/**
	 * Auf der letzten Stelle ist nur ein Buchstabe, ein Punkt oder eine
	 * schließende Klammer zugelassen.
	 */
	DBNA089,
	/**
	 * Zulässig ist "A", "M" oder Grundstellung (Leerzeichen).
	 */
	DBNA090,
	/**
	 * Bei Meldungen der Arbeitgeber (VFMM im VOSZ = "AGDEU") ist KENNZAB = "M"
	 * unzulaessig.
	 */
	DBNA092,
	/**
	 * Zulaessig ist nur die Datenlaenge 125.
	 */
	DBNA910;
	
	@Override
	public String getNummer() {
		return this.name();
	}
	
	@Override
	public FehlerNummer getValueOf(final String fehlerNummer) {
		return valueOf(fehlerNummer);
	}
}
