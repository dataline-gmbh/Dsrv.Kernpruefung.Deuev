package de.drv.dsrv.kernpruefung.deuev.model.feld;

import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Definiert Feldnamen fuer den Baustein DBGB.
 */
public enum FeldNameDBGB implements FeldName {
	/**
	 * Kennung Datenbaustein DBGB.
	 */
	KENNUNG("KENNUNG", "KE"),
	/**
	 * Geburtsname.
	 */
	GB_NAME("GB-NAME", "GBNA"),
	/**
	 * Vorsatzwort zum Geburtsnamen.
	 */
	GB_VORSATZWORT("GB-VORSATZWORT", "GBVOSA"),
	/**
	 * Namenszusatz zum Geburtsnamen.
	 */
	GB_NAMENSZUSATZ("GB-NAMENSZUSATZ", "GBNAZU"),
	/**
	 * Geburtsdatum im Format JJJJMMTT.
	 */
	GEBURTSDATUM("GEBURTSDATUM", "GBDT"),
	/**
	 * Geschlecht.
	 */
	GESCHLECHT("GESCHLECHT", "GE"),
	/**
	 * Geburtsort.
	 */
	GB_ORT("GB-ORT", "GBOT");

	private String name;
	private String kurzName;

	private FeldNameDBGB(final String name, final String kurzName) {
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
