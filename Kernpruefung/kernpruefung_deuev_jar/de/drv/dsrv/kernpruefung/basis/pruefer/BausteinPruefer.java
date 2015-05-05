package de.drv.dsrv.kernpruefung.basis.pruefer;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Schnittstellenbeschreibung fuer den BausteinPruefer.
 * 
 * @param <T>
 *            FeldName<Baustein>
 * @param <E>
 *            FehlerNummer<Baustein>
 */
public interface BausteinPruefer<T extends FeldName, E extends FehlerNummer> {

	/**
	 * Liefert eine Liste von Feldpruefungen, die zu dem angegebenen Baustein
	 * gehoeren.
	 * 
	 * @return Liste von Feldpruefungen eines Bausteins
	 * @throws UngueltigePrueferDatenException
	 *             wird ausgeloest, falls beim Initialisieren der Feldpruefungen
	 *             ein Fehler aufgetreten ist
	 */
	List<FeldPruefung<T, E>> getPruefungen() throws UngueltigePrueferDatenException;

}