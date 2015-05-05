package de.drv.dsrv.kernpruefung.basis.feldpruefung;

import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;

/**
 * Schnittstellenbeschreibung fuer die Feldpruefung.
 * 
 * @param <T>
 *            FeldName<Baustein>
 * @param <E>
 *            FehlerNummer<Baustein>
 */
public interface FeldPruefung<T extends FeldName, E extends FehlerNummer> {

	/**
	 * Fuehrt alle Pruefungen durch, die fuer ein Feld definiert wurden aus und
	 * liefert ein Ergebnis. Es werden alle Basispruefungen, die nur das zu
	 * pruefende Feld benoetigen und nicht von anderen Feldern abhaengen,
	 * ausgefuerht.
	 * 
	 * @return Erebnis-Objekt mit den Daten zum Fehler
	 * @throws UngueltigeDatenException
	 *             wurd ausgeloest, falls eine der Pruefungen nicht
	 *             durchgefuehrt werden konnte
	 */
	FeldPruefungErgebnis<E> pruefe() throws UngueltigeDatenException;

	/**
	 * Fuehrt alle Pruefungen durch, die fuer ein Feld definiert wurden aus und
	 * liefert ein Ergebnis. Es werden alle felduebergreifenden Pruefungen, die
	 * auf ein weiteres Feld aus demselben Baustein zugreifen, ausgefuehrt.
	 * 
	 * @return Erebnis-Objekt mit den Daten zum Fehler
	 * @throws UngueltigeDatenException
	 *             wurd ausgeloest, falls eine der Pruefungen nicht
	 *             durchgefuehrt werden konnte
	 */
	FeldPruefungErgebnis<E> pruefeFeldUebergreifend() throws UngueltigeDatenException;

	/**
	 * Fuehrt alle Pruefungen durch, die fuer ein Feld definiert wurden aus und
	 * liefert ein Ergebnis. Es werden alle bausteinuebergreifenden Pruefungen,
	 * die auf ein weiteres Feld aus einem anderen Baustein zugreifen,
	 * ausgefuehrt.
	 * 
	 * @return Erebnis-Objekt mit den Daten zum Fehler
	 * @throws UngueltigeDatenException
	 *             wurd ausgeloest, falls eine der Pruefungen nicht
	 *             durchgefuehrt werden konnte
	 */
	FeldPruefungErgebnis<E> pruefeBausteinUebergreifend() throws UngueltigeDatenException;

	/**
	 * Initialisiert alle feldinternen Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	void initialisierePruefungen() throws UngueltigeDatenException;

	/**
	 * Initialisiert alle felduebergreifenden Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException;

	/**
	 * Initialisiert alle bausteinuebergreifenden Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException;

	/**
	 * Gibt das aktuelle {@link Feld} zurueck.
	 * 
	 * @return {@link Feld}
	 */
	Feld<T> getFeld();
}