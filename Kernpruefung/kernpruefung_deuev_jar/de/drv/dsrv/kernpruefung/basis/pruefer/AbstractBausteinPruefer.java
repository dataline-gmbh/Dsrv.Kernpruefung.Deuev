package de.drv.dsrv.kernpruefung.basis.pruefer;

import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Definiert allgemeine Methoden fuer die Pruefer der konkreten Bausteinen.
 * 
 * @param <T>
 *            Typ des Bausteins, vorgegeben durch den Namenkreis der Felder vom
 *            Typ {@link FeldName}
 * @param <E>
 */
public abstract class AbstractBausteinPruefer<T extends FeldName, E extends FehlerNummer> implements
		BausteinPruefer<T, E> {

	private final transient Baustein<T> baustein;

	/**
	 * Konstruktor.
	 * 
	 * @param baustein
	 *            Baustein, fuer den die Pruefungen definiert werden
	 */
	protected AbstractBausteinPruefer(final Baustein<T> baustein) {
		this.baustein = baustein;
	}

	/**
	 * Liefert den Baustein, fuer den die Pruefungen definiert werden.
	 * 
	 * @return Baustein
	 */
	protected Baustein<T> getBaustein() {
		return baustein;
	}
}
