package de.drv.dsrv.kernpruefung.basis.parser;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Implementiert Methoden zum Parsen der einzelne Bausteine.
 * 
 * @param <T>
 *            Typ des Bausteins, vorgegeben durch den Namenkreis der Felder vom
 *            Typ {@link FeldName}
 */
public abstract class AbstractBausteinParser<T extends FeldName> implements BausteinParser<T> {

	/**
	 * Liest aus dem uebergebenen Datensatz den Wert fuer das Feld und liefert
	 * das zugehoerige Feld-Objekt als Ergebnis zurueck.
	 * 
	 * @param name
	 *            Name des Feldes
	 * @param start
	 *            Start-Index im Datensatz
	 * @param end
	 *            End-Index im Datensatz
	 * @param datensatz
	 *            Wert des gesamten Datensatzes
	 * @throws DatensatzAufbauException
	 *             wird ausgeloest, wenn der Datensatz leer ist, wenn das Feld
	 *             an der angegebenen Position nicht gelesen werden kann
	 */
	protected Feld<T> parseFeld(final T name, final int start, final int end, final String datensatz)
			throws DatensatzAufbauException {

		Feld<T> feld;

		if (datensatz == null || datensatz.length() == 0) {
			throw new DatensatzAufbauException("Fehler beim Lesen des Feldes " + name
					+ ", der zu lesende Datensatz ist leer");
		} else {
			if (end <= datensatz.length()) {
				feld = new Feld<T>(name, datensatz.substring(start, end));
			} else {
				throw new DatensatzAufbauException("Fehler beim Lesen des Feldes " + name + " an der Position " + start
						+ " und " + end);
			}
		}
		return feld;
	}
}
