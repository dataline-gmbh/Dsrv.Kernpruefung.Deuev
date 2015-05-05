package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.logging.Logger;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Definiert Methoden fuer eine Basispruefung, die von einer Feldpruefung
 * verwendet wird.
 */
public abstract class AbstractPruefung {

	protected static final Logger LOGGER = Logger.getLogger(AbstractPruefung.class.getName());

	private final transient Feld<? extends FeldName> feld;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	protected AbstractPruefung(final Feld<? extends FeldName> feld) {
		this.feld = feld;
	}

	/**
	 * Fuehrt die Pruefung aus.
	 * 
	 * @return <code>true</code>, falls die Pruefung erfolgreich war,
	 *         <code>false</code> ansonsten
	 * @throws UngueltigeDatenException
	 */
	public abstract boolean pruefe() throws UngueltigeDatenException;

	protected Feld<?> getFeld() {
		return feld;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((feld == null) ? 0 : feld.hashCode());
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
		final AbstractPruefung other = (AbstractPruefung) obj;
		if (feld == null) {
			if (other.feld != null) {
				return false;
			}
		} else if (!feld.equals(other.feld)) {
			return false;
		}
		return true;
	}
}
