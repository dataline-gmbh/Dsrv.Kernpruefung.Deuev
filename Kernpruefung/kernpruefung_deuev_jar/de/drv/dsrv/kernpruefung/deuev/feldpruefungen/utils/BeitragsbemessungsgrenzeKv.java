package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils;

/**
 * Daten-Bean fuer die Beitragsbemessungsgrenze fuer die Kv.
 */
public class BeitragsbemessungsgrenzeKv {
	
	private final transient Integer Kalenderjahr;
	private final transient Double kvWert;
	
	BeitragsbemessungsgrenzeKv(final Integer kalenderjahr, final Double kvWert) {
		super();
		this.Kalenderjahr = kalenderjahr;
		this.kvWert = kvWert;
	}
	
	Integer getKalenderjahr() {
		return this.Kalenderjahr;
	}
	
	Double getKvWert() {
		return this.kvWert;
	}
}
