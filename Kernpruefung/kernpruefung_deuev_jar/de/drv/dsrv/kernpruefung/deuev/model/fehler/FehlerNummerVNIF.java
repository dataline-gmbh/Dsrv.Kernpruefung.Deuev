package de.drv.dsrv.kernpruefung.deuev.model.fehler;

import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;

/**
 * Fehlernummern der einzelnen Pruefungen im Baustein VNIF.
 */
public enum FehlerNummerVNIF implements FehlerNummer {

	/**
	 * Fuer VNIF gibt es keine Fehlernummern -> nur ein Dummy.
	 */
	DUMMY;

	@Override
	public String getNummer() {
		return name();
	}

	@Override
	public FehlerNummer getValueOf(final String fehlerNummer) {
		return valueOf(fehlerNummer);
	}
}
