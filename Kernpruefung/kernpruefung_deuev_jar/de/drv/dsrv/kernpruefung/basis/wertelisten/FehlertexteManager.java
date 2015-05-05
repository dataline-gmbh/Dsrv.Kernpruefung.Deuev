package de.drv.dsrv.kernpruefung.basis.wertelisten;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Ermittelt Fehlertexte fuer die Pruefungen aus einer Datei.
 */
public final class FehlertexteManager {

	// key: dateiname, value: fehlertextemanager instanz
	private static Map<String, FehlertexteManager> instances;

	private transient Properties fehlertexte;
	private final transient String dateiname;


	/**
	 * Konstruktor.
	 * 
	 * @param dateiname
	 *            Dateiname mit den Fehlertexten
	 */
	private FehlertexteManager(final String dateiname) {
		this.dateiname = dateiname;
	}

	/**
	 * Ermittelt eine Instanz der Klasse und gibt diese zurueck.
	 * 
	 * @param dateiname
	 *            Datenname mit den Fehlertexten, die geladen werden sollen
	 * @return Instanz der Klasse
	 */
	public static synchronized FehlertexteManager getInstance(final String dateiname) {
		FehlertexteManager fManager;

		if (instances == null) {
			instances = new HashMap<String, FehlertexteManager>();
		}

		fManager = instances.get(dateiname);
		if (fManager == null) {
			fManager = new FehlertexteManager(dateiname);
			instances.put(dateiname, fManager);
		}

		return fManager;
	}

	/**
	 * Liefert zu der angegebenen Fehlernummer den zugehoerigen Fehlertext.
	 * 
	 * @param fehlernummer
	 *            Fehlernummer
	 * @return Fehlertext zu der angegebenen Fehlernummer
	 * @throws WertelistenException
	 *             wird ausgeloest, falls die Fehlertexte aus einer Werteliste
	 *             nicht ermittelt werden konnten
	 */
	public synchronized String getFehlertext(final String fehlernummer) throws WertelistenException {

		if (fehlertexte == null) {
			fehlertexte = new Properties();
			WertelistenReader.loadProperties(dateiname, fehlertexte);
		}

		return fehlertexte.getProperty(fehlernummer);
	}
}
