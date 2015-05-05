package de.drv.dsrv.kernpruefung.deuev.wertelisten;

import de.drv.dsrv.kernpruefung.basis.wertelisten.ListenTyp;

/**
 * Speichert alle DEUEV-Listen und deren Speicherorte als Enum ab.
 */
public enum ListenTypDeuev implements ListenTyp {

	//@formatter:off
	/** */
	A1_SCHLUESSELZAHL_ABGABEGRUND("Anlage1_Schluesselzahl_Abgabegrund.txt"),
	/** */
	A16_BEITRAGSGRUPPEN_KOMBINATIONEN("Anlage16_Beitragsgruppen_Kombinationen.csv"),
	/** */
	A19A_UNFALLVERSICHERUNGSTRAEGER("Anlage19_a_UVG_A08_Unfallversicherungstraeger.txt"),
	/** */
	A19B_UNFALLVERSICHERUNGSTRAEGER("Anlage19_b_UVG_A09_Unfallversicherungstraeger.txt"),
	/** */
	A19C_UNFALLVERSICHERUNGSTRAEGER("Anlage19_c_UVG_A07_Unfallversicherungstraeger.txt"),
	/** */
	A2_SCHLUESSELZAHL_PERSONENGRUPPE("Anlage2_Schluesselzahl_Personengruppe.txt"),
	/** */
	A20_BETRIEBSNUMMER_UVTRAEGER("Anlage20_Betriebsnummer_UVTraeger.txt"),
	/** */
	A5_TAETIGKEITSSCHLUESSEL_FELDA("Anlage5_Taetigkeitsschluessel_FeldA.txt"),
	/** */
	A5_TAETIGKEITSSCHLUESSEL_FELDAT("Anlage5_Taetigkeitsschluessel_FeldAT.txt"),
	/** */
	A6_VORSATZWORTE("Anlage6_Vorsatzworte.txt"),
	/** */
	A6_VORSATZWORTE_Cobol("Anlage6_Vorsatzworte_Cobol.txt"),
	/** */
	A7_NAMENSZUSAETZE("Anlage7_Namenszusaetze.txt"),
	/** */
	A8_LAENDERKENNZEICHEN("Anlage8_Laenderkennzeichen.txt"),
	/** */
	A8_STAATSSCHLUESSEL_EU("Anlage8_Staatsschluessel_EU.txt"),
	/** */
	A8_STAATSSCHLUESSEL("Anlage8_Staatsschluessel.txt"),
	/** */
	BBG_DM("Beitragsbemessungsgrenze_DM.csv"),
	/** */
	BBG_EURO("Beitragsbemessungsgrenze_Euro.csv"),
	/** */
	BBG_KV_EURO("BeitragsbemessungsgrenzeKV_Euro.csv"),
	/** */
	BEZUGSGROESSE_DM("Bezugsgroesse_DM.csv"),
	/** */
	BEZUGSGROESSE_EURO("Bezugsgroesse_Euro.csv"),
	/** */
	INTERIMSVERSICHERUNGSNUMMER("interimsversicherungsnummer.txt"),
	/** */
	TESTBETRIEBSNUMMERN("testBetriebsnummern.txt"),
	/** */
	TESTVERSICHERUNGSNUMMERN("testVersicherungsnummern.txt"),
	/** */
	VERSICHERUNGSNUMMER("versicherungsnummer.txt");
	//@formatter:on

	private final String speicherOrt;
	private static final String PFAD = "/de/drv/dsrv/kernpruefung/deuev/wertelisten/";

	@Override
	public String getSpeicherort() {
		return speicherOrt;
	}

	ListenTypDeuev(final String speicherOrt) {
		this.speicherOrt = PFAD + speicherOrt;
	}

	@Override
	public String getName() {
		return name();
	}

}
