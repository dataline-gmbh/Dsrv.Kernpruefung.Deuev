package de.drv.dsrv.kernpruefung.deuev.model.fehler;

import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;

/**
 * Fehlernummern der einzelnen Pruefungen im Baustein DBTN.
 */
public enum FehlerNummerDBTN implements FehlerNummer {

	/**
	 * Zulaessig ist nur "DBTN".
	 */
	DBTN020,

	/**
	 * Zulaessig sind nur "J", "N" oder Grundstellung (Leerzeichen).
	 */
	DBTN030,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBTN040,

	/**
	 * Das Datum der Entscheidung zur Sofortmeldepflicht muss logisch richtig
	 * sein.
	 */
	DBTN042,

	/**
	 * Das Datum der Entscheidung zur Sofortmeldepflicht darf nicht groesser als
	 * das Verarbeitungsdatum sein.
	 */
	DBTN044,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBTN060,

	/**
	 * Das Datum, ab wann die Verpflichtung zur Abgabe einer Sofortmeldung
	 * besteht bzw. nicht besteht, muss logisch richtig sein.
	 */
	DBTN062,

	/**
	 * Zulaessig sind nur "J", "N" oder Grundstellung (Leerzeichen).
	 */
	DBTN100,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBTN120,

	/**
	 * Das Datum der Entscheidung zur Insolvenzgeldumlagepflicht muss logisch
	 * richtig sein.
	 */
	DBTN122,

	/**
	 * Das Datum der Entscheidung zur Insolvenzgeldumlagepflicht darf nicht
	 * groesser als das Verarbeitungsdatum sein.
	 */
	DBTN124,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBTN140,

	/**
	 * Das Datum, ab wann die Teilnahme an der Insolvenzgeldumlagepflicht
	 * besteht oder nicht, muss logisch richtig sein.
	 */
	DBTN142,

	/**
	 * Zulaessig sind nur "J", "N" oder Grundstellung (Leerzeichen).
	 */
	DBTN180,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBTN200,

	/**
	 * Das Datum der Entscheidung, ob der Betrieb der Umlagepflicht U1 muss
	 * logisch richtig sein.
	 */
	DBTN202,

	/**
	 * Das Datum der Entscheidung, ob der Betrieb der Umlagepflicht U1 darf
	 * nicht groesser als das Verarbeitungsdatum sein.
	 */
	DBTN204,

	/**
	 * Zulaessig sind nur numerische Zeichen.
	 */
	DBTN220,

	/**
	 * Das Datum, ab wann die Teilnahme an der Umlage 1 besteht oder nicht, muss
	 * logisch richtig sein.
	 */
	DBTN222,

	/**
	 * Zulaessig ist nur die Grundstellung (Leerzeichen).
	 */
	DBTN260,

	/**
	 * Zulaessig ist nur die Datenlaenge 108.
	 */
	DBTN910;

	@Override
	public String getNummer() {
		return name();
	}

	@Override
	public FehlerNummer getValueOf(final String fehlerNummer) {
		return valueOf(fehlerNummer);
	}
}
