package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils;

/**
 * Daten-Bean fuer die Beitragsbemessungsgrenze.
 */
public class Beitragsbemessungsgrenze {
	
	private final transient Integer Kalenderjahr;
	private final transient Double AvArvWest;
	private final transient Double AvArvOst;
	private final transient Double KnvWest;
	private final transient Double KnvOst;
	
	Beitragsbemessungsgrenze(final Integer kalenderjahr, final Double avArvWest, final Double avArvOst,
			final Double knvWest, final Double knvOst) {
		super();
		this.Kalenderjahr = kalenderjahr;
		this.AvArvWest = avArvWest;
		this.AvArvOst = avArvOst;
		this.KnvWest = knvWest;
		this.KnvOst = knvOst;
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
	
	Double getKnvOst() {
		return this.KnvOst;
	}
	
	Double getKnvWest() {
		return this.KnvWest;
	}
}
