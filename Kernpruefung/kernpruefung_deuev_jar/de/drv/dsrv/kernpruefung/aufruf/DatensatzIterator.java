package de.drv.dsrv.kernpruefung.aufruf;

import java.util.Iterator;

/**
 * Iterator fuer die {@link EingabeDaten}.
 */
public class DatensatzIterator implements Iterator<DatensatzIterator> {

	private final EingabeDaten eingabeDaten;
	private int indexDatensatz;

	/**
	 * Iterator fuer die {@link EingabeDaten}.
	 * 
	 * @param eingabeDaten
	 */
	public DatensatzIterator(final EingabeDaten eingabeDaten) {
		this.eingabeDaten = eingabeDaten;
		indexDatensatz = -1;
	}

	@Override
	public boolean hasNext() {
		return (indexDatensatz + 1) < eingabeDaten.getDatensaetze().size();
	}

	@Override
	public DatensatzIterator next() {
		++indexDatensatz;
		return this;
	}

	@Override
	public void remove() {
	}

	/**
	 * Gibt den aktuellen Datensatz zurueck.
	 * 
	 * @return {@link String} Datensatz.
	 */
	public String getDatensatz() {
		return eingabeDaten.getDatensaetze().get(indexDatensatz);
	}

	/**
	 * Gibt die {@link EingabeDaten} zurueck.
	 * 
	 * @return {@link EingabeDaten}
	 */
	public EingabeDaten getEingabeDaten() {
		return eingabeDaten;
	}
}
