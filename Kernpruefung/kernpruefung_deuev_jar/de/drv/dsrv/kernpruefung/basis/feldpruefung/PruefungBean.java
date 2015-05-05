package de.drv.dsrv.kernpruefung.basis.feldpruefung;

import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.AbstractPruefung;


/**
 * Haelt Pruefung und zugehoerige Fehlernummer.
 * 
 * @param <E>
 *            FehlerNummer
 */
class PruefungBean<E extends FehlerNummer> {

	private final AbstractPruefung pruefung;
	private final Fehler<E> fehler;

	/**
	 * Konstruktor.
	 * 
	 * @param pruefung
	 *            die Pruefung
	 * @param fehler
	 *            der Fehler
	 */
	public PruefungBean(AbstractPruefung pruefung, Fehler<E> fehler) {
		this.pruefung = pruefung;
		this.fehler = fehler;
	}

	public AbstractPruefung getPruefung() {
		return pruefung;
	}

	public Fehler<E> getFehler() {
		return fehler;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fehler == null) ? 0 : fehler.hashCode());
		result = prime * result + ((pruefung == null) ? 0 : pruefung.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final PruefungBean<?> other = (PruefungBean<?>) obj;
		if (fehler == null) {
			if (other.fehler != null) {
				return false;
			}
		} else if (!fehler.equals(other.fehler)) {
			return false;
		}
		if (pruefung == null) {
			if (other.pruefung != null) {
				return false;
			}
		} else if (!pruefung.equals(other.pruefung)) {
			return false;
		}
		return true;
	}

}
