package de.drv.dsrv.kernpruefung.basis.model;

import java.util.List;


/**
 * Repraesentiert Daten eines Bausteins.
 * 
 * @param <T>
 *            Konkreter Typ der Feldernamen, der fuer den konkreten Baustein
 *            definiert wurde, vom Typ {@link FeldName}.
 */
public class Baustein<T extends FeldName> {

	private final transient List<Feld<T>> felder;
	private final transient BausteinName name;

	/**
	 * Konstruktor.
	 * 
	 * @param name
	 *            Name des Bausteins
	 * @param felder
	 *            Liste der zugehoerigen Felder
	 */
	public Baustein(final BausteinName name, final List<Feld<T>> felder) {
		this.name = name;
		this.felder = felder;
	}

	/**
	 * @return List aller Felder des Bausteins.
	 */
	public List<Feld<T>> getFelder() {
		return felder;
	}

	/**
	 * Ermittelt aus allen vorhandenen Feldern eines Bausteins die zugehoerigen
	 * Werte und stellt so den Wert (Zeichenkette) des gesamten Bausteins
	 * zusammen.
	 * 
	 * @return Wert (Zeichenkette) des gesamten Bausteins
	 */
	public String getValue() {
		final StringBuilder valueBuilder = new StringBuilder();
		for (final Feld<T> feld : felder) {
			valueBuilder.append(feld.getValue());
		}
		return valueBuilder.toString();
	}

	/**
	 * Gibt den Namen des Bausteins zurueck.
	 * 
	 * @return Name des Bausteins
	 */
	public BausteinName getName() {
		return name;
	}

	/**
	 * Ermittelt anhand des Feldnamens das entsprechende Feld-Objekt.
	 * 
	 * @param feldName
	 *            Name des gesuchten Feldes
	 * @return Feld-Objekt
	 */
	public Feld<T> getFeld(final FeldName feldName) {
		Feld<T> result = null;
		if (feldName != null) {
			for (final Feld<T> feld : felder) {
				if (feldName.equals(feld.getName())) {
					result = feld;
					break;
				}
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "Baustein name=" + name + " [" + felder + "]";
	}
}
