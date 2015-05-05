package de.drv.dsrv.kernpruefung.deuev.model.fehler;

import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;

/**
 * Gelistet sind alle externen Fehlernummern fuer den Baustein DBSO.
 */
public enum FehlerNummerDBSO implements FehlerNummer {

	/**
	 * Zulaessig ist "DBSO".
	 */
	DBSO001,

	/**
	 * Zulaessig ist "N" oder "J".
	 */
	DBSO010,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBSO020,

	/**
	 * Zulaessig sind nur logisch richtige Datumsangaben.
	 */
	DBSO022,

	/**
	 * Der ZRBGSO darf nicht vor dem 01.01.2009 liegen.
	 */
	DBSO024,

	/**
	 * Zulaessig ist nur die Datenlaenge 13.
	 */
	DBSO910;

	@Override
	public String getNummer() {
		return name();
	}

	@Override
	public FehlerNummer getValueOf(final String fehlerNummer) {
		return valueOf(fehlerNummer);
	}

}
