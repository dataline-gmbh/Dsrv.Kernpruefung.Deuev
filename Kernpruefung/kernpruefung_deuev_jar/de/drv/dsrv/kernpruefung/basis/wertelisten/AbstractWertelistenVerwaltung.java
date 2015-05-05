package de.drv.dsrv.kernpruefung.basis.wertelisten;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Initialisiert Wertelisten mit Namenszusaetzen und Vorsatzworten, falls diese
 * nicht leer sind.
 */
public abstract class AbstractWertelistenVerwaltung {

	private List<ListenTyp> speicherort;
	private final Map<ListenTyp, List<String>> lists;

	private List<MapTyp> speicherortProperties;
	private final Map<MapTyp, Map<String, String>> maps;

	/**
	 * Vor dem Anfordern der Wertelisten muessen diese vorher geladen werden.
	 * Dadurch werden alle moeglichen Fehler bereits beim Starten der Anwendung
	 * ausgeloest. In den Feldpruefungen kann folglich davon ausgegangen werden,
	 * dass die Listen geladen und vorhanden sind.
	 * 
	 * @param speicherortList
	 * @param speicherortProp
	 * @throws WertelistenException
	 */
	protected void ladeListen(final List<ListenTyp> speicherortList, final List<MapTyp> speicherortProp)
			throws WertelistenException {
		this.speicherort = speicherortList;
		this.speicherortProperties = speicherortProp;

		for (final ListenTyp lTyp : speicherort) {
			ladeWerteliste(lTyp);
		}

		for (final MapTyp pTyp : speicherortProperties) {
			ladeProperties(pTyp);
		}
	}

	protected AbstractWertelistenVerwaltung() {
		this.lists = new HashMap<ListenTyp, List<String>>();
		this.maps = new HashMap<MapTyp, Map<String, String>>();
	}

	/**
	 * Gibt die entsprechende Properties als Map zurueck.
	 * 
	 * @param pTyp
	 * @return Gibt die gewuenschte Map zurueck. Wurde davor nicht ladeListen()
	 *         ausgefuehrt wird <code>null</code> zurueckgegeben!
	 */
	public synchronized Map<String, String> getMap(final MapTyp pTyp) {
		return maps.get(pTyp);
	}

	/**
	 * Beim ersten Zugriff auf eine Property-Datei wird diese neu erstellt und
	 * die Werte werde in diese eingelesen. Bei jeden weiteren Aufruf werden die
	 * properties als <code>Map</code> zurueckgegeben. Die Map darf NICHT
	 * GEAENDERT WERDEN! Sollte ein Wert hinzugefuegt, entfernt oder
	 * ueberschrieben werden muss eine neue Map erstellt werden!
	 * 
	 * @param pTyp
	 *            Wert aus <code>PropertiesTyp</code> zu dem die Properties
	 *            angefordert werden.
	 */
	private synchronized void ladeProperties(final MapTyp pTyp) throws WertelistenException {
		if (maps.get(pTyp) == null) {
			final String sOrt = pTyp.getSpeicherOrt();
			if (sOrt == null) {
				throw new WertelistenException("Fehler beim Initialisieren der Properties " + pTyp.getName()
						+ ", Speicherort nicht verhanden.");
			}

			final Map<String, String> map = WertelistenReader.loadPropertiesAsMap(sOrt);

			if ((map == null) || map.isEmpty()) {
				throw new WertelistenException(
						"Fehler beim Initialisieren der Properties, keine Properties vorhanden. " + pTyp.getName());
			}

			maps.put(pTyp, map);
		}
	}

	/**
	 * Gibt die entsprechende Werteliste zurueck. Die Werteliste darf NICHT
	 * GEAENDERT WERDEN! Sollte ein Wert hinzugefuegt, entfernt oder
	 * ueberschrieben werden muss eine neue Liste erstellt werden!
	 * 
	 * @param lTyp
	 * @return Gibt die gewuenschte Werteliste zurueck. Wurde davor nicht
	 *         ladeListen() ausgefuehrt wird <code>null</code> zurueckgegeben!
	 */
	public synchronized List<String> getWerteliste(final ListenTyp lTyp) {
		return this.lists.get(lTyp);
	}

	/**
	 * Beim ersten Zugriff auf eine Liste wird diese neu erstellt und die Werte
	 * werde in diese eingelesen. Bei jeden weiteren Aufruf wird die Liste
	 * zurueckgegeben.
	 * 
	 * @param lTyp
	 *            Wert aus <code>ListenTyp</code> zu dem die Liste angefordert
	 *            wird.
	 */
	private synchronized void ladeWerteliste(final ListenTyp lTyp) throws WertelistenException {
		List<String> list = this.lists.get(lTyp);
		if (list == null) {
			final String sOrt = lTyp.getSpeicherort();
			if (sOrt == null) {
				throw new WertelistenException("Fehler beim Initialisieren der Liste " + lTyp.getName()
						+ ", Speicherort nicht verhanden.");
			}

			list = WertelistenReader.loadValuesAsList(sOrt);

			if ((list == null) || list.isEmpty()) {
				throw new WertelistenException("Fehler beim Initialisieren der Liste " + lTyp.getName()
						+ ", Liste ist leer");
			}

			lists.put(lTyp, list);
		}
	}

	/**
	 * Zu Beginn alle Wertelisten und Properties laden. Die Methode muss am
	 * Anfang 1x aufgerufen werden, bevor die get-Methoden verwendet werden!
	 * 
	 * @throws WertelistenException
	 */
	public abstract void ladeListen() throws WertelistenException;
}
