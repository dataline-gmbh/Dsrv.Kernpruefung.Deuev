package de.drv.dsrv.kernpruefung.basis.model;

/**
 * Bietet die Moeglichkeit statt einem Fehler ein Hinweis zurueckzugeben.
 * 
 * @param <T>
 */
public class Hinweis<T extends FehlerNummer> extends Fehler<T> {

	/**
	 * Konstruktor.
	 * 
	 * @param fehlerNummer
	 */
	public Hinweis(final T fehlerNummer) {
		super(fehlerNummer);

		setFehlerArt(FehlerArt.HINWEIS);
	}

}
