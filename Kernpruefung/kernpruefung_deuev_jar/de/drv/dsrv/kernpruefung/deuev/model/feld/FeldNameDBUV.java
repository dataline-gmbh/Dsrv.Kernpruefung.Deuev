package de.drv.dsrv.kernpruefung.deuev.model.feld;

import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Gelistet sind alle Feldnamen, die fuer eine Pruefung benoetigt werden, aus
 * dem Baustein DBUV.
 */
public enum FeldNameDBUV implements FeldName {
	/**
	 * Kennung Datenbaustein DBUV.
	 */
	KENNUNG("KENNUNG", "KE"),

	/**
	 * Anzahl der angehaengten UV-Daten.
	 */
	ANZAHL_UV("ANZAHL-UV", "ANUV"),

	/**
	 * Reservefelder.
	 */
	RESERVE("RESERVE", "RESERVE"),

	// Ab hier wiederholende Werte (Siehe ANZAHL-UV)

	/**
	 * Grund fuer die Besonderheiten der Abgabe der UV-Daten.
	 */
	UV_GRUND_1("UV-GRUND_1", "UVGD_1"),

	/**
	 * Betriebsnummer des zustaendigen UV-Traegers.
	 */
	BBNR_UV_1("BBNR-UV_1", "BBNRUV_1"),

	/**
	 * Mitgliedsnummer des Unternehmens.
	 */
	MITGLIEDS_NR_1("MITGLIEDS-NR_1", "MNR_1"),

	/**
	 * Betriebsnummer des UV-Traegers, dessen Gefahrtarif angewendet wird.
	 */
	BBNR_GTS_1("BBNR-GTS_1", "BBNRGT_1"),

	/**
	 * Gefahrtarifstelle.
	 */
	GT_STELLE_1("GT-STELLE_1", "GTST_1"),

	/**
	 * Beitragspflichtiges Arbeitsentgelt zur Unfallversicherung in vollen Euro.
	 */
	UV_EG_1("UV-EG_1", "UVEG_1"),

	/**
	 * Geleistete Arbeitsstunden.
	 */
	ARBSTD_1("ARBSTD_1", "ARBSTD_1"),

	/**
	 * Grund fuer die Besonderheiten der Abgabe der UV-Daten.
	 */
	UV_GRUND_2("UV-GRUND_2", "UVGD_2"),

	/**
	 * Betriebsnummer des zustaendigen UV-Traegers.
	 */
	BBNR_UV_2("BBNR-UV_2", "BBNRUV_2"),

	/**
	 * Mitgliedsnummer des Unternehmens.
	 */
	MITGLIEDS_NR_2("MITGLIEDS-NR_2", "MNR_2"),

	/**
	 * Betriebsnummer des UV-Traegers, dessen Gefahrtarif angewendet wird.
	 */
	BBNR_GTS_2("BBNR-GTS_2", "BBNRGT_2"),

	/**
	 * Gefahrtarifstelle.
	 */
	GT_STELLE_2("GT-STELLE_2", "GTST_2"),

	/**
	 * Beitragspflichtiges Arbeitsentgelt zur Unfallversicherung in vollen Euro.
	 */
	UV_EG_2("UV-EG_2", "UVEG_2"),

	/**
	 * Geleistete Arbeitsstunden.
	 */
	ARBSTD_2("ARBSTD_2", "ARBSTD_2"),

	/**
	 * Grund fuer die Besonderheiten der Abgabe der UV-Daten.
	 */
	UV_GRUND_3("UV-GRUND_3", "UVGD_3"),

	/**
	 * Betriebsnummer des zustaendigen UV-Traegers.
	 */
	BBNR_UV_3("BBNR-UV_3", "BBNRUV_3"),

	/**
	 * Mitgliedsnummer des Unternehmens.
	 */
	MITGLIEDS_NR_3("MITGLIEDS-NR_3", "MNR_3"),

	/**
	 * Betriebsnummer des UV-Traegers, dessen Gefahrtarif angewendet wird.
	 */
	BBNR_GTS_3("BBNR-GTS_3", "BBNRGT_3"),

	/**
	 * Gefahrtarifstelle.
	 */
	GT_STELLE_3("GT-STELLE_3", "GTST_3"),

	/**
	 * Beitragspflichtiges Arbeitsentgelt zur Unfallversicherung in vollen Euro.
	 */
	UV_EG_3("UV-EG_3", "UVEG_3"),

	/**
	 * Geleistete Arbeitsstunden.
	 */
	ARBSTD_3("ARBSTD_3", "ARBSTD_3"),

	/**
	 * Grund fuer die Besonderheiten der Abgabe der UV-Daten.
	 */
	UV_GRUND_4("UV-GRUND_4", "UVGD_4"),

	/**
	 * Betriebsnummer des zustaendigen UV-Traegers.
	 */
	BBNR_UV_4("BBNR-UV_4", "BBNRUV_4"),

	/**
	 * Mitgliedsnummer des Unternehmens.
	 */
	MITGLIEDS_NR_4("MITGLIEDS-NR_4", "MNR_4"),

	/**
	 * Betriebsnummer des UV-Traegers, dessen Gefahrtarif angewendet wird.
	 */
	BBNR_GTS_4("BBNR-GTS_4", "BBNRGT_4"),

	/**
	 * Gefahrtarifstelle.
	 */
	GT_STELLE_4("GT-STELLE_4", "GTST_4"),

	/**
	 * Beitragspflichtiges Arbeitsentgelt zur Unfallversicherung in vollen Euro.
	 */
	UV_EG_4("UV-EG_4", "UVEG_4"),

	/**
	 * Geleistete Arbeitsstunden.
	 */
	ARBSTD_4("ARBSTD_4", "ARBSTD_4"),

	/**
	 * Grund fuer die Besonderheiten der Abgabe der UV-Daten.
	 */
	UV_GRUND_5("UV-GRUND_5", "UVGD_5"),

	/**
	 * Betriebsnummer des zustaendigen UV-Traegers.
	 */
	BBNR_UV_5("BBNR-UV_5", "BBNRUV_5"),

	/**
	 * Mitgliedsnummer des Unternehmens.
	 */
	MITGLIEDS_NR_5("MITGLIEDS-NR_5", "MNR_5"),

	/**
	 * Betriebsnummer des UV-Traegers, dessen Gefahrtarif angewendet wird.
	 */
	BBNR_GTS_5("BBNR-GTS_5", "BBNRGT_5"),

	/**
	 * Gefahrtarifstelle.
	 */
	GT_STELLE_5("GT-STELLE_5", "GTST_5"),

	/**
	 * Beitragspflichtiges Arbeitsentgelt zur Unfallversicherung in vollen Euro.
	 */
	UV_EG_5("UV-EG_5", "UVEG_5"),

	/**
	 * Geleistete Arbeitsstunden.
	 */
	ARBSTD_5("ARBSTD_5", "ARBSTD_5"),

	/**
	 * Grund fuer die Besonderheiten der Abgabe der UV-Daten.
	 */
	UV_GRUND_6("UV-GRUND_6", "UVGD_6"),

	/**
	 * Betriebsnummer des zustaendigen UV-Traegers.
	 */
	BBNR_UV_6("BBNR-UV_6", "BBNRUV_6"),

	/**
	 * Mitgliedsnummer des Unternehmens.
	 */
	MITGLIEDS_NR_6("MITGLIEDS-NR_6", "MNR_6"),

	/**
	 * Betriebsnummer des UV-Traegers, dessen Gefahrtarif angewendet wird.
	 */
	BBNR_GTS_6("BBNR-GTS_6", "BBNRGT_6"),

	/**
	 * Gefahrtarifstelle.
	 */
	GT_STELLE_6("GT-STELLE_6", "GTST_6"),

	/**
	 * Beitragspflichtiges Arbeitsentgelt zur Unfallversicherung in vollen Euro.
	 */
	UV_EG_6("UV-EG_6", "UVEG_6"),

	/**
	 * Geleistete Arbeitsstunden.
	 */
	ARBSTD_6("ARBSTD_6", "ARBSTD_6"),

	/**
	 * Grund fuer die Besonderheiten der Abgabe der UV-Daten.
	 */
	UV_GRUND_7("UV-GRUND_7", "UVGD_7"),

	/**
	 * Betriebsnummer des zustaendigen UV-Traegers.
	 */
	BBNR_UV_7("BBNR-UV_7", "BBNRUV_7"),

	/**
	 * Mitgliedsnummer des Unternehmens.
	 */
	MITGLIEDS_NR_7("MITGLIEDS-NR_7", "MNR_7"),

	/**
	 * Betriebsnummer des UV-Traegers, dessen Gefahrtarif angewendet wird.
	 */
	BBNR_GTS_7("BBNR-GTS_7", "BBNRGT_7"),

	/**
	 * Gefahrtarifstelle.
	 */
	GT_STELLE_7("GT-STELLE_7", "GTST_7"),

	/**
	 * Beitragspflichtiges Arbeitsentgelt zur Unfallversicherung in vollen Euro.
	 */
	UV_EG_7("UV-EG_7", "UVEG_7"),

	/**
	 * Geleistete Arbeitsstunden.
	 */
	ARBSTD_7("ARBSTD_7", "ARBSTD_7"),

	/**
	 * Grund fuer die Besonderheiten der Abgabe der UV-Daten.
	 */
	UV_GRUND_8("UV-GRUND_8", "UVGD_8"),

	/**
	 * Betriebsnummer des zustaendigen UV-Traegers.
	 */
	BBNR_UV_8("BBNR-UV_8", "BBNRUV_8"),

	/**
	 * Mitgliedsnummer des Unternehmens.
	 */
	MITGLIEDS_NR_8("MITGLIEDS-NR_8", "MNR_8"),

	/**
	 * Betriebsnummer des UV-Traegers, dessen Gefahrtarif angewendet wird.
	 */
	BBNR_GTS_8("BBNR-GTS_8", "BBNRGT_8"),

	/**
	 * Gefahrtarifstelle.
	 */
	GT_STELLE_8("GT-STELLE_8", "GTST_8"),

	/**
	 * Beitragspflichtiges Arbeitsentgelt zur Unfallversicherung in vollen Euro.
	 */
	UV_EG_8("UV-EG_8", "UVEG_8"),

	/**
	 * Geleistete Arbeitsstunden.
	 */
	ARBSTD_8("ARBSTD_8", "ARBSTD_8"),

	/**
	 * Grund fuer die Besonderheiten der Abgabe der UV-Daten.
	 */
	UV_GRUND_9("UV-GRUND_9", "UVGD_9"),

	/**
	 * Betriebsnummer des zustaendigen UV-Traegers.
	 */
	BBNR_UV_9("BBNR-UV_9", "BBNRUV_9"),

	/**
	 * Mitgliedsnummer des Unternehmens.
	 */
	MITGLIEDS_NR_9("MITGLIEDS-NR_9", "MNR_9"),

	/**
	 * Betriebsnummer des UV-Traegers, dessen Gefahrtarif angewendet wird.
	 */
	BBNR_GTS_9("BBNR-GTS_9", "BBNRGT_9"),

	/**
	 * Gefahrtarifstelle.
	 */
	GT_STELLE_9("GT-STELLE_9", "GTST_9"),

	/**
	 * Beitragspflichtiges Arbeitsentgelt zur Unfallversicherung in vollen Euro.
	 */
	UV_EG_9("UV-EG_9", "UVEG_9"),

	/**
	 * Geleistete Arbeitsstunden.
	 */
	ARBSTD_9("ARBSTD_9", "ARBSTD_9");

	private String name;
	private String kurzName;

	/**
	 * Konstruktor.
	 * 
	 * @param name
	 *            Name des Feldes
	 * @param kurzName
	 *            Kurzbezeichnung des Feldes
	 */
	private FeldNameDBUV(final String name, final String kurzName) {
		this.name = name;
		this.kurzName = kurzName;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getKurzName() {
		return this.kurzName;
	}

}
