package de.drv.dsrv.kernpruefung.basis.parser;

import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Definiert Methoden, die von einem konkreten Baustein-Parser implementiert
 * werden.
 * 
 * @param <T>
 *            Typ des Bausteins, vorgegeben durch den Namenkreis der Felder vom
 *            Typ {@link FeldName}
 */
public interface BausteinParser<T extends FeldName> {

	/**
	 * Liest aus dem uebergebenen Datensatz die einzelnen Felder, die zu dem
	 * jeweiligen Baustein gehoeren und gibt sie in dem Datenobjekt des
	 * Bausteins zurueck.
	 * 
	 * @param datensatz
	 *            Datensatz als Zeichenkette
	 * @return Datenobjekt eines Bausteins vom Typ {@link Baustein} mit den
	 *         zugehoerigen Feldern
	 * @throws DatensatzAufbauException
	 *             wird ausgeloest, wenn der Datensatz leer oder null ist und
	 *             deswegen kein Baustein gelesen werden kann, wenn aus dem
	 *             Datensatz der gewuenschte Datensatz nicht gelesen werden
	 *             kann, weil der Datensatz nicht genuegend Zeichen enthaelt,
	 *             d.h. zu kurz ist
	 */
	Baustein<T> parse(String datensatz) throws DatensatzAufbauException;

	/**
	 * Gibt die Laenge des Bausteins zurueck.
	 * 
	 * @param datensatz
	 *            - Wird fuer Berechnungen, wie z.B. im Baustein DBUV benoetigt.
	 *            Bei dem Datensatz werden alle vorherigen Bausteine
	 *            abgeschnitten, sodass der aktuelle Baustein an 1. Stelle
	 *            steht.
	 * @return positiven Werte, falls die Laenge errechnet werden konnte, -1
	 *         sonst.
	 * @throws DatensatzAufbauException
	 */
	int getLaenge(final String datensatz) throws DatensatzAufbauException;

	/**
	 * Gibt die Fehlernummer zurueck, der angezeigt werden soll, wenn der
	 * Baustein nicht vorhanden ist (z.B. in DEUEV, DSME: DSME930 und folgende).
	 * 
	 * @return Fehler
	 */
	Fehler<? extends FehlerNummer> getFehlerBausteinNichtVorhanden();
}
