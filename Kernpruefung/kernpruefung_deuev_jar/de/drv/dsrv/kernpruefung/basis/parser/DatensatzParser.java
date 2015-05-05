package de.drv.dsrv.kernpruefung.basis.parser;

import de.drv.dsrv.kernpruefung.basis.model.Datensatz;

/**
 * Definiert Methoden fuer einen Datensatzparser.
 */
public interface DatensatzParser {

	/**
	 * Liest den Datensatz ein und erstellt ein Datenobjekt mit den zugehoerigen
	 * Bausteinen und einzelnen Feldern.
	 * 
	 * @param datensatzWert
	 *            Datensatz als Zeichenkette
	 * @return Datenobjekt für den eingelesenen Datensatz
	 * @throws DatensatzAufbauException
	 *             wird ausgeloest, falls ein Baustein, der eingelesen wird,
	 *             eine unbekannte Kennung enthaelt, falls ein leerer Datensatz
	 *             uebergeben wurde, falls ein Datensatz nicht ausreichende
	 *             Laenge hat, falls die Reihenfolge der Bausteine von der
	 *             vorgegebenen Reihenfolge abweicht
	 */
	Datensatz parse(final String datensatzWert) throws DatensatzAufbauException;

}