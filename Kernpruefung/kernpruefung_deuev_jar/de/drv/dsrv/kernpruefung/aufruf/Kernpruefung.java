package de.drv.dsrv.kernpruefung.aufruf;

import java.util.List;


/**
 * Startet die Kernpruefung, validiert die EingabeDaten, parst den Datensatz und
 * prueft diesen.
 */
public interface Kernpruefung {

	/**
	 * Hier wird die Kernpruefung gestartet und das Ergebnis dieser
	 * zurueckgegeben. Dazu wird eine Sendung mit einen oder mehreren
	 * Datensaetzen uebergeben werden
	 * 
	 * @param eingabeDaten
	 *            EingabeDaten fuer die Kernpruefung
	 * @return List<DatensatzPruefungErgebnis> Fuer jeden Datensatz wird das
	 *         Ergebnis zurueckgegeben.
	 */
	List<DatensatzPruefungErgebnis> pruefe(final EingabeDaten eingabeDaten);
}
