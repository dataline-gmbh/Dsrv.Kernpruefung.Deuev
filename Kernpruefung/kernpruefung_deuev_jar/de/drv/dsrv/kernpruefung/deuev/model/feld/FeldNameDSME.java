package de.drv.dsrv.kernpruefung.deuev.model.feld;

import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Definiert Feldnamen fuer den Baustein DSME.
 */
public enum FeldNameDSME implements FeldName {

	/**
	 * Kennung Datenbaustein DSME.
	 */
	KENNUNG("KENNUNG", "KE"),
	/**
	 * Verfahren MELD.
	 */
	VERFAHREN("VERFAHREN", "VF"),
	/**
	 * Betriebsnummer des Absenders.
	 */
	BBNR_ABSENDER("BBNR-ABSENDER", "BBNRAB"),
	/**
	 * Betriebsnummer des Empfaengers.
	 */
	BBNR_EMPFAENGER("BBNR-EMPFAENGER", "BBNREP"),
	/**
	 * Versionsnummer.
	 */
	VERSIONS_NR("VERSIONS-NR", "VERNR"),
	/**
	 * Zeitpunkt der Erstellung des Datensatzes.
	 */
	DATUM_ERSTELLUNG("DATUM-ERSTELLUNG", "ED"),
	/**
	 * Fehlerkennzeichen.
	 */
	FEHLER_KENNZ("FEHLER-KENNZ", "FEKZ"),
	/**
	 * Fehleranzahl.
	 */
	FEHLER_ANZAHL("FEHLER-ANZAHL", "FEAN"),
	/**
	 * Versicherungsnummer.
	 */
	VSNR("VSNR", "VSNR"),
	/**
	 * Versicherungstraeger, fuer den die Meldung bestimmt ist.
	 */
	VSTR("VSTR", "VSTR"),
	/**
	 * Betriebsnummer der Meldebehoerde.
	 */
	BBNR_VU("BBNR-VU", "BBNRVU"),

	/**
	 * Meldedatum der Meldebehoerde.
	 */
	MEDT("MEDT", "MEDT"),

	/**
	 * Dieses Feld steht dem Verursacher zur Verfuegung.
	 */
	AKTENZEICHEN_VERURSACHER("AKTENZEICHEN-VERURSACHER", "AZ-VU"),
	/**
	 * Betriebsnummer der fuer den Beschaeftigten zustaendigen Krankenkasse.
	 */
	BBNR_KK("BBNR-KK", "BBNRKK"),
	/**
	 * Dieses Feld steht der Krankenkasse zur Verfuegung.
	 */
	AKTENZEICHEN_KK("AKTENZEICHEN-KK", "AZ-KK"),
	/**
	 * Betriebsnummer der Abrechnungsstelle.
	 */
	BBNR_ABRECHNUNGSSTELLE("BBNR-ABRECHNUNGSSTELLE", "BBNRAS"),
	/**
	 * Personengruppe.
	 */
	PERSONENGRUPPE("PERSONENGRUPPE", "PERSGR"),
	/**
	 * Abgabegrund.
	 */
	ABGABEGRUND("ABGABEGRUND", "GD"),
	/**
	 * Staatsangehoerigkeitsschluessel.
	 */
	STAATSANGEHOERIGKEITS_SC("STAATSANGEHOERIGKEITS-SC", "SASC"),
	/**
	 * Vorhandensein Dateinbaustein DBME.
	 */
	MM_MELDEDATEN("MM-MELDEDATEN", "MMME"),
	/**
	 * Vorhandensein Dateinbaustein DBNA.
	 */
	MM_NAME("MM-NAME", "MMNA"),
	/**
	 * Vorhandensein Datenbaustein DBGB.
	 */
	MM_GEBNAME("MM-GEBNAME", "MMGB"),
	/**
	 * Vorhandensein Datenbaustein DBAN.
	 */
	MM_ANSCHRIFT("MM-ANSCHRIFT", "MMAN"),
	/**
	 * Vorhandensein Datenbaustein DBEU.
	 */
	MM_EUDATEN("MM-EUDATEN", "MMEU"),
	/**
	 * Vorhandensein Datenbaustein DBUV.
	 */
	MM_UVDATEN("MM-UVDATEN", "MMUV"),
	/**
	 * Vorhandensein Datenbaustein DBKS.
	 */
	MM_KNV_SEE("MM-KNV-SEE", "MMKS"),
	/**
	 * Vorhandensein Datenbaustein DBSV.
	 */
	MM_SVA("MM-SVA", "MMSV"),
	/**
	 * Vorhandensein Datenbaustein DBVR.
	 */
	MM_VERGABE_RUECKMELDUNG("MM-VERGABE-RUECKMELDUNG", "MMVR"),
	/**
	 * Vorhandensein Datenbaustein DBRG.
	 */
	MM_RUECKMELDUNG_GERINGFUEGIG("MM-RUECKMELDUNG-GERINGFUEGIG", "MMRG"),
	/**
	 * Meldungen der Bundesagentur der Arbeit.
	 */
	KENNZ_UEBERGANG("KENNZ-UEBERGANG", "KENNZUE"),
	/**
	 * Uebermittlungsweg der abgegebenen Meldung.
	 */
	MM_UEBERMITTLUNG("MM-UEBERMITTLUNG", "MMUEB"),
	/**
	 * Kennzeichen, dass die Anschrift nach Pruefung durch die Sachbearbeitung,
	 * zuzulassen ist.
	 */
	KENNZ_UNIPOST_GEPRUEFT("KENNZ-UNIPOST-GEPRUEFT", "KENNZUP"),
	/**
	 * Vorhandensein Datenbaustein DBSO.
	 */
	MM_SOFORT("MM-SOFORT", "MMSO"),
	/**
	 * Statuskennzeichen fuer Familienangehoerige und GmbH-Gesellschatfer.
	 */
	KENNZ_STATUS("KENNZ-STATUS", "KENNZSTA"),
	/**
	 * Vorhandensein Datenbaustein DBUE.
	 */
	MM_UEBERW_EINZUGSVG("MM-UEBERW-EINZUGSVG", "MMUE"),
	/**
	 * Versionsnummer des Kernpruefungsprogramms.
	 */
	VERSIONS_NR_KP("VERSIONS-NR-KP", "VERNRKP"),
	/**
	 * Vorhandensein Datenbaustein Krankenversicherung.
	 */
	MM_KVDATEN("MM-KVDATEN", "MMKV"),
	/**
	 * BLANK.
	 */
	RESERVE("RESERVE", "RESERVE"),

	/**
	 * Vorhandensein Datenbaustein Eheschliessung (DBEH).
	 */
	MMEH("MMEH", "MMEH"),

	/**
	 * Vorhandensein Datenbaustein DBZM.
	 */
	MMZM("MMZM", "MMZM"),

	/**
	 * Vorhandensein Datenbaustein Identifikationsdaten (DBID).
	 */
	MMID("MMID", "MMID"),

	/**
	 * Vorhandensein Datenbaustein Sterbedatum (DBST).
	 */
	MMST("MMST", "MMST"),

	/**
	 * Vorhandensein Datenbaustein DBGM.
	 */
	MMGM("MMGM", "MMGM"),

	/**
	 * Vorhandensein Datenbaustein DBAV.
	 */
	MMAV("MMAV", "MMAV"),

	/**
	 * Vorhandensein Datenbaustein DBES.
	 */
	MMES("MMES", "MMES"),

	/**
	 * Vorhandensein Datenbaustein DBAS.
	 */
	MMAS("MMAS", "MMAS");

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
	private FeldNameDSME(final String name, final String kurzName) {
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
