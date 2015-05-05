package de.drv.dsrv.kernpruefung.basis.utils;

public interface CSVRepresenter {

	/**
	 * Liefert die gesamte Zeile zu einem bestimmten Schluessel.
	 * 
	 * @param schluesselspalte
	 *            der Schluessel, fuer welchen nach der korrespondierenden Zeile
	 *            gesucht wird.
	 * @return <code>null</code> falls keine Zeile zum Schluessel existiert.
	 *         Sonst wird die Zeile selbst uebergeben.
	 */
	String getZeile(String schluesselspalte);

	/**
	 * Speichert eine ganze Zeile unter Verwendung der Angabe zur
	 * Schluesselspalte ab.
	 * 
	 * @param zeile
	 *            die Zeichenkette die abgespeichert werden soll.
	 * @retrun <code>null</code> falls noch keine Zeichekentte unter diesem
	 *         Schluessel gespeichert wurde, sonst die Zeichenkette die vorher
	 *         unter diesem Schluessel gespreichert war und ueberschrieben
	 *         wurde.
	 */
	String putZeile(String zeile);

	/**
	 * Liefert zu einem Schluessel und einem Spaltenindex den gesuchten Wert.
	 * 
	 * @param schluessel
	 *            der Schluessel fuer den Datensatz in welchem der
	 *            Spalteneintrag gesucht wird.
	 * @param spaltenIndex
	 *            der Spaltenindex des gesuchten Eintrags. Index startet mit
	 *            Index=0.
	 * @return der gesuchte Eintrag oder <code>null</code> wenn dieser nicht
	 *         vorhanden ist. (Entweder kein Zeile mit diesem Schluessel oder in
	 *         dieser Zeile kein Wert in dieser Spalte.)
	 */
	String getEintrag(String schluessel, int spaltenIndex);

}
