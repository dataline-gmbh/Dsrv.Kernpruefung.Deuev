package de.drv.dsrv.kernpruefung.basis.wertelisten;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;

/**
 * Klasse zum Laden der Wertelisten.
 */
public final class WertelistenReader {

	private static final Logger LOGGER = Logger.getLogger(WertelistenReader.class.getName());

	private static final String DEFAULT_ENCODING = "ISO-8859-1";

	/**
	 * Konstruktor.
	 */
	private WertelistenReader() {
		// Klasse enthaelt nur statische Methoden
	}

	/**
	 * Laedt Werte aus einer properties-Datei.
	 * 
	 * @param filename
	 *            Dateiname
	 * @param properties
	 *            Daten-Objekt, in dem die geladenen Wertepaare gespeichert
	 *            werden
	 * @throws WertelistenException
	 *             wird ausgeloest, falls Daten aus der angegebenen Datei nicht
	 *             geladen werden koennen
	 */
	public static void loadProperties(final String filename, final Properties properties) throws WertelistenException {

		// Pruefen, ob Dateiname angegeben wurde
		if (StringUtils.isBlank(filename)) {
			throw new WertelistenException("Kein Dateiname angegeben");
		}

		// Ressource laden und prufen, ob sie vorhanden ist
		final InputStream inputStream = WertelistenReader.class.getResourceAsStream(filename);
		if (inputStream == null) {
			throw new WertelistenException("Datei mit dem Namen " + filename + " nicht vorhanden");
		}

		// Werte laden
		try {
			properties.load(inputStream);
		} catch (final FileNotFoundException e) {
			throw new WertelistenException("Datei der Werteliste " + filename + " nicht vorhanden", e);
		} catch (final IOException e) {
			throw new WertelistenException("Fehler beim Lesen der Datei " + filename, e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (final IOException e) {
					LOGGER.log(Level.SEVERE, e.getMessage(), e);
				}
			}
		}
	}

	/**
	 * Laedt Werte aus einer properties-Datei und gibt diese als Map zurueck.
	 * 
	 * @param filename
	 *            Dateiname
	 * @throws WertelistenException
	 *             wird ausgeloest, falls Daten aus der angegebenen Datei nicht
	 *             geladen werden koennen
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, String> loadPropertiesAsMap(final String filename) throws WertelistenException {
		final Properties prop = new Properties();
		loadProperties(filename, prop);

		// (SuppressWarnings) Alternative waere gewesen ueber jeden einzelnen
		// Wert zu iterieren. Da die Werte aber sowieso alle Strings sind
		// (werden von einer Datei gelesen) kann auch dieser Weg genommen werden
		return Collections.unmodifiableMap(new HashMap<String, String>((Map) prop));
	}

	/**
	 * Laedt eine Werteliste, die in Form einer Textdatei vorliegt, und erstellt
	 * fuer jede Zeile in der Datei einen Eintrag in der Liste, die als Ergebnis
	 * zurueckgeliefert wird.
	 * 
	 * @param filename
	 *            Dateiname der Werteliste
	 * @return Liste mit den Werten aus der Datei
	 * @throws WertelistenException
	 */
	public static List<String> loadValuesAsList(final String filename) throws WertelistenException {

		// Rueckgabewert definieren
		final ArrayList<String> valueList = new ArrayList<String>();

		// Pruefen, ob Dateiname angegeben wurde
		if (StringUtils.isBlank(filename)) {
			throw new WertelistenException("Kein Dateiname angegeben");
		}

		// Resource laden und pruefen
		final InputStream resourceStream = WertelistenReader.class.getResourceAsStream(filename);
		if (resourceStream == null) {
			throw new WertelistenException("Datei mit dem Namen " + filename + " nicht vorhanden");
		}

		// Streams definieren
		InputStreamReader streamReader = null;
		BufferedReader bufferedReader = null;
		try {
			streamReader = new InputStreamReader(resourceStream, DEFAULT_ENCODING);
			bufferedReader = new BufferedReader(streamReader);

			String readedLine = null;
			while ((readedLine = bufferedReader.readLine()) != null) {
				valueList.add(readedLine);
			}
		} catch (final FileNotFoundException e) {
			throw new WertelistenException("Datei der Werteliste " + filename + " nicht vorhanden", e);
		} catch (final IOException e) {
			throw new WertelistenException("Fehler beim Lesen der Datei " + filename, e);
		} finally {
			try {

				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (streamReader != null) {
					streamReader.close();
				}
				if (resourceStream != null) {
					resourceStream.close();
				}
			} catch (final IOException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
			}
		}

		return Collections.unmodifiableList(valueList);
	}

	/**
	 * Liefert einen {@link InputStream} zu der geforderten Datei zurueck.
	 * 
	 * @param filename
	 * @return FileInputStream
	 * @throws WertelistenException
	 */
	public static InputStream getInputStream(final String filename) throws WertelistenException {

		final InputStream ressource = WertelistenReader.class.getResourceAsStream(filename);
		if (ressource == null) {
			throw new WertelistenException("Datei mit dem Namen " + filename + " nicht vorhanden");
		}

		return ressource;
	}
}
