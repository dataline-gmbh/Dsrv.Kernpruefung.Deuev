package de.drv.dsrv.kernpruefung.deuev.wertelisten;

import de.drv.dsrv.kernpruefung.basis.wertelisten.MapTyp;

/**
 * Speichert alle DEUEV-Properties und deren Speicherorte als Enum ab.
 */
public enum MapTypDeuev implements MapTyp {

	//@formatter:off
	/** */
	DEUEV("deuev.properties"),
	/** */
	A20_MITGLIEDSNUMMER_LAENGE("Anlage20_Mitgliedsnummer_Laenge.properties"),
	/** */
	A20_MITGLIEDSNUMMER_ZEICHEN("Anlage20_Mitgliedsnummer_Zeichen.properties"),
	/** */
	BEITRAGSSATZ_DEUEV("BeitragssatzDeuev.properties"),
	/** */
	VERSION("version.properties", "/de/drv/dsrv/kernpruefung/deuev/");
	//@formatter:on

	private final String speicherOrt;
	private static final String PFAD = "/de/drv/dsrv/kernpruefung/deuev/wertelisten/";

	MapTypDeuev(final String speicherOrt) {
		this(speicherOrt, PFAD);
	}

	MapTypDeuev(final String speicherOrt, final String pfad) {
		this.speicherOrt = pfad + speicherOrt;
	}
	
	@Override
	public String getSpeicherOrt() {
		return speicherOrt;
	}

	@Override
	public String getName() {
		return name();
	}

}
