package de.drv.dsrv.kernpruefung.deuev.model.fehler;

import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;

/**
 * Gelistet sind alle externen Fehlernummern fuer den Baustein DBSV.
 */
public enum FehlerNummerDBSV implements FehlerNummer {

	/**
	 * Zulaessig ist "DBSV".
	 */
	DBSV001,

	/**
	 * Zulaessig ist "J".
	 */
	DBSV010,

	/**
	 * Zulaessig ist nur die Datenlaenge 5.
	 */
	DBSV910;

	@Override
	public String getNummer() {
		return this.name();
	}

	@Override
	public FehlerNummer getValueOf(String fehlerNummer) {
		return valueOf(fehlerNummer);
	}

}
