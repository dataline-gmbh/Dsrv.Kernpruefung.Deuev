package de.drv.dsrv.kernpruefung.deuev.model.fehler;

import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;

/**
 * Gelistet sind alle externen Fehlernummern fuer den Baustein DBRG.
 */
public enum FehlerNummerDBRG implements FehlerNummer {

	/**
	 * Zulaessig ist nur "DBRG".
	 */
	DBRG001,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBRG300,

	/**
	 * Zulaessig ist nur 01-46.
	 */
	DBRG310;
	
	@Override
	public String getNummer() {
		return name();
	}

	@Override
	public FehlerNummer getValueOf(final String fehlerNummer) {
		return valueOf(fehlerNummer);
	}
}
