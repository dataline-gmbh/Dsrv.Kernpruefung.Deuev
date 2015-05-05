package de.drv.dsrv.kernpruefung.basis.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Repraesentiert eine simple Umsetzung zur internen Verwaltung einer CSV-Datei.
 */
public class CSVRepresenterSimple implements CSVRepresenter {

	private final Map<String, ArrayList<String>> csvTabelle;
	private final int schluesselSpalte;

	/**
	 * Schluessel fuer die interne Verwaltung der Datei ist die erste Spalte der
	 * Datei.
	 */
	public CSVRepresenterSimple() {
		this(0);
	}

	public CSVRepresenterSimple(int schluesselSpalte) {
		this.csvTabelle = new HashMap<String, ArrayList<String>>();
		this.schluesselSpalte = schluesselSpalte;
	}


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
	@Override
	public String getEintrag(String schluessel, int spaltenIndex) {
		String ergebnis = null;

		final ArrayList<String> zeile = this.csvTabelle.get(schluessel);

		final int tatsaechlicherIndex = spaltenIndex + 1;
		if (zeile != null && 0 <= tatsaechlicherIndex && tatsaechlicherIndex < zeile.size()) {
			ergebnis = zeile.get(spaltenIndex + 1);
		}

		return ergebnis;
	}

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
	@Override
	public synchronized String putZeile(String zeile) {
		String ergebnis = null;

		final String[] spaltenFeld = zeile.split(";");

		if (schluesselSpalte < spaltenFeld.length) {

			final ArrayList<String> spaltenListe = new ArrayList<String>();
			
			// Erster Eintrag des Feldes ist immer der Orginalstring
			spaltenListe.add(zeile);
			spaltenListe.addAll(Arrays.asList(spaltenFeld));

			final ArrayList<String> rueckgabe = this.csvTabelle.put(spaltenFeld[schluesselSpalte], spaltenListe);
			if (rueckgabe != null) {
				ergebnis = rueckgabe.get(0); // Liefert den Orginalstring zum
												// Vergleich, ob wir etwas
												// ueberschrieben haben
			}
		}

		return ergebnis;
	}

	/**
	 * Liefert die gesamte Zeichenkette einer Zeile zu einem bestimmten
	 * Schluessel.
	 * 
	 * @param schluessel
	 *            der Schluessel fuer welchen nach einer Zeile gesucht wird.
	 * @return <code>null</code> falls zu dem Schluessel keine entsprechende
	 *         Zeile vorliegt, sonst die Zeichenkette der Zeile, welche zum
	 *         Schluessel korrespondiert.
	 */
	@Override
	public String getZeile(String schluessel) {
		String ergebnis = null;
		final ArrayList<String> zeile = this.csvTabelle.get(schluessel);

		if (zeile != null) {
			ergebnis = zeile.get(0); // Liefert den Orginalstring
			// einschliesslich
			// Trennzeichen.
		}

		return ergebnis;
	}

}
