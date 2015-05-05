package de.drv.dsrv.kernpruefung.basis.pruefer;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Datensatz;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;

/**
 * Definiert Methoden fuer einen Datensatzprufer.
 */
public interface DatensatzPruefer {

	/**
	 * Fuehrt die Pruefung des Datensatzes durch und verwendet dabei alle
	 * registrierten Baustein-Pruefungen.
	 * 
	 * @return Liste der Fehler nach den Pruefungen
	 * @throws UngueltigeDatenException
	 *             wird ausgeloest, falls fuer eine der Feldpruefungen
	 *             ungueltige Daten (z.B. Wert nicht vorhanden) vorliegen
	 * @throws UngueltigePrueferDatenException
	 */
	List<Fehler<? extends FehlerNummer>> pruefen() throws UngueltigeDatenException, UngueltigePrueferDatenException;

	/**
	 * Initialisiert die Bausteinpruefungen.
	 * 
	 * @param datensatz
	 * @throws UngueltigePrueferDatenException
	 */
	void initialisierePruefungen(final Datensatz datensatz) throws UngueltigePrueferDatenException;
}