package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils;

/**
 * Daten-Bean fuer die Bezugsgroesse (eine von einem Gremium festgesetzte
 * Rentenversicherungsgroesse, aehnlich der Beitragsbemessungsgrenze).
 */
public class Bezugsgroesse {
	
	private final transient Integer Kalenderjahr;
	private final transient Double AvArvWest;
	private final transient Double AvArvOst;
	
	Bezugsgroesse(final Integer kalenderjahr, final Double avArvWest, final Double avArvOst) {
		super();
		this.Kalenderjahr = kalenderjahr;
		this.AvArvWest = avArvWest;
		this.AvArvOst = avArvOst;
	}
	
	Double getAvArvOst() {
		return this.AvArvOst;
	}
	
	Double getAvArvWest() {
		return this.AvArvWest;
	}
	
	Integer getKalenderjahr() {
		return this.Kalenderjahr;
	}
}
