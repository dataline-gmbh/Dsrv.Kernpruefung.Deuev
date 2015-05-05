package de.drv.dsrv.kernpruefung.deuev.model.fehler;

import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;

/**
 * Fehlernummern der einzelnen Pruefungen des Bausteins (DB)EU.
 */
public enum FehlerNummerDBEU implements FehlerNummer {

	/**
	 * Zulaessig ist "DBEU".
	 */
	DBEU001,
	/**
	 * Im Feld sind nur numerische Zeichen zulaessig.
	 */
	DBEU010,
	/**
	 * Zulaessig sind nur die vom statistischen Bundesamt festgelegten
	 * Schluesselzahlen (s. Anlage 8).
	 */
	DBEU012,
	/**
	 * Zulaessig ist nur die Datenlaenge 27.
	 */
	DBEU910;

	@Override
	public String getNummer() {
		return name();
	}

	@Override
	public FehlerNummer getValueOf(final String fehlerNummer) {
		return valueOf(fehlerNummer);
	}
}
