package de.drv.dsrv.kernpruefung.basis.model;

import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;

/**
 * Repraesentiert Daten eines Feldes im Baustein.
 * 
 * @param <T>
 *            Konkreter Typ der Feldernamen, der fuer den konkreten Baustein
 *            definiert wurde, vom Typ {@link FeldName}.
 */
public class Feld<T extends FeldName> {

	private transient String value;
	private transient String trimmedValue;
	private transient T name;
	private boolean fehlerfrei;

	/**
	 * Konstruktor.
	 * 
	 * @param value
	 *            Wert des Feldes
	 */
	public Feld(final String value) {
		super();
		this.value = value;
		this.fehlerfrei = true;
	}

	/**
	 * Konstruktor.
	 * 
	 * @param name
	 *            Name des Feldes
	 * @param value
	 *            Wert des Feldes
	 */
	public Feld(T name, String value) {
		super();
		this.value = value;
		this.name = name;
		this.fehlerfrei = true;
	}

	/**
	 * Gibt den Originalwert (nicht bereinigt) des Feldes zurueck.
	 * 
	 * @return Orginalwert des Feldes
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Traegt den Wert des Feldes ein und berechnet den bereinigten Wert fuer
	 * das Feld, der ueber die Methode {@link Feld#getTrimmedValue()} ermittelt
	 * werden kann.
	 * 
	 * @param value
	 */
	public void setValue(final String value) {
		this.value = value;
		this.trimmedValue = StringUtils.stripEnd(value, null);
	}

	/**
	 * Gibt den bereinigten Wert des Feldes zurueck, d.h. die Leerzeichen am
	 * Ende des Wertes werden entfernt.
	 * 
	 * @return bereinigter Wert des Feldes
	 */
	public String getTrimmedValue() {
		if (trimmedValue == null && value != null) {
			trimmedValue = StringUtils.stripEnd(value, null);
		}
		return trimmedValue;
	}

	/**
	 * Gibt den Namen des Feldes zurueck.
	 * 
	 * @return Name des Feldes
	 */
	public FeldName getName() {
		return name;
	}

	/**
	 * Gibt an, ob ein Feld fehlerfrei und damit auch in uebergreifenden
	 * Pruefungen verwendbar ist. Setzt den Wert.
	 * 
	 * @param fehlerfrei
	 */
	public void setFehlerfrei(boolean fehlerfrei) {
		this.fehlerfrei = fehlerfrei;
	}

	/**
	 * Gibt an, ob ein Feld fehlerfrei und damit auch in uebergreifenden
	 * Pruefungen verwendbar ist.
	 * 
	 * @return <code>true</code>, falls das Feld fehlerfrei ist,
	 *         <code>false</code> sonst
	 */
	public boolean isFehlerfrei() {
		return fehlerfrei;
	}

	@Override
	public String toString() {
		return "Feld name=" + name + "[" + value + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Feld<?> other = (Feld<?>) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}
}
