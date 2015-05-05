package de.drv.dsrv.kernpruefung.basis.steuerung;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.drv.dsrv.kernpruefung.aufruf.DatensatzIterator;
import de.drv.dsrv.kernpruefung.aufruf.DatensatzPruefungErgebnis;
import de.drv.dsrv.kernpruefung.aufruf.DatensatzkennungUngueltigException;
import de.drv.dsrv.kernpruefung.aufruf.EingabeDaten;
import de.drv.dsrv.kernpruefung.aufruf.Kernpruefung;
import de.drv.dsrv.kernpruefung.basis.fehler.AbstractFehlerBausteinGenerator;
import de.drv.dsrv.kernpruefung.basis.logging.DeuevStreamHandler;
import de.drv.dsrv.kernpruefung.basis.model.DatensatzName;
import de.drv.dsrv.kernpruefung.basis.parser.DatensatzAufbauException;
import de.drv.dsrv.kernpruefung.basis.wertelisten.WertelistenException;
import de.drv.dsrv.kernpruefung.basis.wertelisten.WertelistenReader;

/**
 * Abstrakte Klasse fuer den Aufruf der Kernpruefung. Implementiert die nach
 * aussen zugaengige Schnittstelle {@link Kernpruefung}.
 */
public abstract class AbstractKernpruefung implements Kernpruefung {

	protected static final String OPCODE_METADATEN = "VERNR";

	private static final Logger LOGGER = Logger.getLogger(AbstractKernpruefung.class.getName());
	private static final int DATENSATZKENNUNG_LAENGE = 4;
	private static final int OPCODE_LAENGE = 5;
	private static final int ZEITANGABE_LAENGE = 12;

	private static final String LOGGER_INTERN = "/de/drv/dsrv/kernpruefung/basis/logging/logging.properties";
	private static final String LOGGER_EXTERN = "/logging.properties";
	
	private static final List<String> AUSNAHMEN_VFMM_DSME_FEAN_3 = Arrays.asList("KVDEU", "WLTKV");
	private static final List<String> AUSNAHMEN_VFMM_DSME_FEAN_4 = Arrays.asList("RVTKV", "WLTKV");
	
	private static final List<String> NICHT_ERLAUBTE_ZEICHEN_DSME = Arrays.asList("1", "2");
	private static final List<String> NICHT_ERLAUBTE_ZEICHEN_DSAE = Arrays.asList("1", "2");
	private static final List<String> NICHT_ERLAUBTE_ZEICHEN_DSBD = Arrays.asList("1");
	private static final List<String> NICHT_ERLAUBTE_ZEICHEN_DSKO = Arrays.asList("1");

	/**
	 * Wert der Fehler-Kennzeichnung fuer "Datensatz fehlerfrei".
	 */
	public static final String FEHLER_KENNZ_FEHLERFREI = "0";

	protected abstract void startePruefung(final DatensatzPruefungErgebnis pruefungErgebnis,
			final DatensatzIterator iterator);

	/**
	 * Fehler erzeugen, wenn die EingabeDaten <code>null</code> sind.
	 * 
	 * @param pruefungErgebnis
	 */
	protected abstract void setzeFehlerWennEingabeDatenNull(final DatensatzPruefungErgebnis pruefungErgebnis);

	/**
	 * Konstruktor. Setzt die Einstellungen fuer den Logger.
	 */
	public AbstractKernpruefung() {
		ladeLoggerEinstellungen(null);
	}

	/**
	 * Konstruktor. Setzt die Einstellungen fuer den Logger.
	 * 
	 * @param logging
	 *            Wenn fuer die Log-Ausgaben ein Stream verwendet werden soll.
	 */
	public AbstractKernpruefung(final OutputStream logging) {
		ladeLoggerEinstellungen(logging);
	}

	/**
	 * Startet die Pruefung.
	 * 
	 * @param eingabeDaten
	 * 
	 * @return Das Ergebnis der Pruefung, inkl. Datensatz und
	 *         <code>ReturnCode</code>.
	 */
	@Override
	public List<DatensatzPruefungErgebnis> pruefe(final EingabeDaten eingabeDaten) {
		final List<DatensatzPruefungErgebnis> pruefungErgebnisse = new ArrayList<DatensatzPruefungErgebnis>();

		if (LOGGER.isLoggable(Level.INFO)) {
			LOGGER.info("Aufruf Kernpruefung mit Eingabedaten: " + eingabeDaten);
		}

		if (eingabeDaten != null) {
			for (DatensatzIterator iterator : eingabeDaten) {

				if (LOGGER.isLoggable(Level.INFO)) {
					LOGGER.info("Prüfung mit Datensatz: " + iterator.getDatensatz());
				}

				// Ergebnis der Pruefung
				final DatensatzPruefungErgebnis pruefungErgebnis = new DatensatzPruefungErgebnis();

				// Muss bei Fehlerfall mit Fehlerbausteinen ueberschrieben
				// werden!
				pruefungErgebnis.setDatensatz(iterator.getDatensatz());

				// Eingabedaten pruefen
				final boolean datenGueltig = validateEingabeDaten(pruefungErgebnis, iterator);

				if (datenGueltig) {
					// Pruefen, ob der Datensatz ein Fehlerkennzeichen
					// enthaelt (kann uebersprungen werden, wenn es eine Anfrage
					// auf Metadaten gibt)
					try {
						boolean fehlerkennzeichen = true;
						if (!OPCODE_METADATEN.equals(iterator.getEingabeDaten().getOperationscode())) {
							fehlerkennzeichen = validateFehlerkennzeichen(pruefungErgebnis, iterator.getDatensatz(), iterator.getEingabeDaten().getOperationscode());
						}
	
						if (fehlerkennzeichen) {
							// Pruefung starten
							startePruefung(pruefungErgebnis, iterator);
						}
					} catch (DatensatzkennungUngueltigException e) {
						pruefungErgebnis.setReturnCode("1002");
					} catch (DatensatzAufbauException e) {
						pruefungErgebnis.setReturnCode("1002");
					}
				}

				if (LOGGER.isLoggable(Level.INFO)) {
					LOGGER.info("Ergebnis der Prüfung: " + pruefungErgebnis);
				}

				pruefungErgebnisse.add(pruefungErgebnis);
			}
		} else {
			final DatensatzPruefungErgebnis pruefungErgebnis = new DatensatzPruefungErgebnis();
			setzeFehlerWennEingabeDatenNull(pruefungErgebnis);
			pruefungErgebnisse.add(pruefungErgebnis);
		}

		return pruefungErgebnisse;
	}

	/**
	 * Prueft die uebergebenen Parameter: Operationscode, Datensatz,
	 * Verarbeitungsdatum. Geprueft wird, ob die einzelnen Parameter nicht leer
	 * und gueltig sind.
	 * 
	 * @return <code>true</code>, falls alle Uebergabeparameter gueltig sind
	 */
	protected boolean validateEingabeDaten(final DatensatzPruefungErgebnis pruefungErgebnis,
			final DatensatzIterator iterator) {
		// Operationscode pruefen
		boolean gueltig = validateOperationscode(iterator.getEingabeDaten());

		// Uebergebenen Datensatz pruefen
		gueltig = gueltig && validateDatensatz(iterator);

		// Verarbeitungsdatum pruefen
		gueltig = gueltig && validateVerarbeitungsdatum(iterator.getEingabeDaten());

		// Zeitangaben pruefen
		gueltig = gueltig && validateZeitangaben(iterator.getEingabeDaten());

		return gueltig;
	}

	/**
	 * Validiert die Zeitangaben.
	 * 
	 * @param eingabeDaten
	 * @return <code>true</code>, falls die Zeitangaben valide sind,
	 *         <code>false</code> sonst.
	 */
	protected boolean validateZeitangaben(final EingabeDaten eingabeDaten) {
		final String zeitWert = eingabeDaten.getZeitangaben();
		boolean gueltig = false;
		if (zeitWert == null) {
			LOGGER.log(Level.SEVERE, "Keine Zeitangaben uebergeben");
		} else {

			final Pattern p = Pattern.compile("[0-9]{" + ZEITANGABE_LAENGE + "}");
			final Matcher m = p.matcher(zeitWert);

			if (!m.matches()) {
				LOGGER.log(Level.SEVERE, "Zeitangabe ist nicht numerisch oder hat die falsche Laenge ("
						+ ZEITANGABE_LAENGE + "): " + zeitWert);
			} else {
				gueltig = true;
			}

		}

		return gueltig;
	}

	/**
	 * Validiert das Verarbeitungsdatum.
	 * 
	 * @return <code>true</code>, falls das Verarbeitungsdatum nicht
	 *         <code>null</code> ist und in ein Datum umgewandelt werden kann
	 */
	protected boolean validateVerarbeitungsdatum(final EingabeDaten eingabeDaten) {
		boolean gueltig = true;

		final String datum = eingabeDaten.getVerarbeitungsDatum();

		if (datum != null) {
			final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.GERMANY);
			dateFormat.setLenient(false);

			try {
				eingabeDaten.setVerarbeitungsDatumAsDate(dateFormat.parse(datum));
			} catch (final ParseException e) {
				LOGGER.log(Level.SEVERE,
						String.format("Uebergebener Wert >%s< ist kein gueltiges Datum: %s", datum, e.toString()));
				gueltig = false;
				eingabeDaten.setVerarbeitungsDatumAsDate(null);
			}
		} else {
			LOGGER.log(Level.SEVERE, "Uebergebenes Datum ist null.");
			gueltig = false;
			eingabeDaten.setVerarbeitungsDatumAsDate(null);
		}

		return gueltig;
	}

	/**
	 * Validiert den Datensatz.
	 * 
	 * @return <code>true</code>, falls der Datensatz nicht <code>null</code>
	 *         und nicht leer ist.
	 */
	protected boolean validateDatensatz(final DatensatzIterator iterator) {
		final String datensatz = iterator.getDatensatz();
		final boolean gueltig = ((datensatz != null) && (datensatz.trim().length() > 0));

		if (!gueltig) {
			LOGGER.log(Level.SEVERE, "Ungueltiger Datensatz: Wert leer oder null.");
		}
		return gueltig;
	}

	/**
	 * Validiert den uebergebenen Operationscode.
	 * 
	 * @return <code>true</code>, falls Operationscode nicht leer ist und die
	 *         Laenge 5 hat
	 */
	protected boolean validateOperationscode(final EingabeDaten eingabeDaten) {
		final String operationscode = eingabeDaten.getOperationscode();
		final boolean gueltig = ((operationscode != null) && (operationscode.trim().length() == OPCODE_LAENGE));

		if (!gueltig) {
			LOGGER.log(Level.SEVERE, "Ungueltiger Operationscode: Wert ist nicht 5 Zeichen lang oder ist null.");
		}
		return gueltig;
	}

	/**
	 * Validiert, ob es im uebergebenen Datensatz bereits ein Fehlerkennzeichen
	 * gesetzt ist.
	 * 
	 * @param datensatz
	 *            zu pruefende Datensatz
	 * @return <code>false</code>, falls im Datensatz bereits Fehlerkennzeichen
	 *         eingetragen wurde, <code>true</code> ansonsten
	 */
	protected boolean validateFehlerkennzeichen(final DatensatzPruefungErgebnis pruefungErgebnis, final String datensatz, final String vfmm) throws DatensatzkennungUngueltigException, DatensatzAufbauException {
		boolean result = true;

		if(datensatz.length() >= DATENSATZKENNUNG_LAENGE) {
			final String kennung = datensatz.substring(0, DATENSATZKENNUNG_LAENGE);
			try {
				DatensatzName.valueOf(kennung);
				
				if (datensatz.length() > (AbstractFehlerBausteinGenerator.INDEX_FEHLER_KENNZ + 1)) {
					final CharSequence fehlerKennzeichen = datensatz.subSequence(
							AbstractFehlerBausteinGenerator.INDEX_FEHLER_KENNZ,
							AbstractFehlerBausteinGenerator.INDEX_FEHLER_KENNZ + 1);
		
					if (kennung.equals(DatensatzName.DSME.name()) && (NICHT_ERLAUBTE_ZEICHEN_DSME.contains(fehlerKennzeichen) 
							|| ("4".equals(fehlerKennzeichen) && !AUSNAHMEN_VFMM_DSME_FEAN_4.contains(vfmm))
							|| ("3".equals(fehlerKennzeichen) && !AUSNAHMEN_VFMM_DSME_FEAN_3.contains(vfmm))))
						return false;
					
					else if (kennung.equals(DatensatzName.DSAE.name()) && NICHT_ERLAUBTE_ZEICHEN_DSAE.contains(fehlerKennzeichen))
						return false;
					
					else if (kennung.equals(DatensatzName.DSBD.name()) && NICHT_ERLAUBTE_ZEICHEN_DSBD.contains(fehlerKennzeichen))
						return false;
					
					else if (kennung.equals(DatensatzName.DSKO.name()) && NICHT_ERLAUBTE_ZEICHEN_DSKO.contains(fehlerKennzeichen))
						return false;
					
				} else {
					LOGGER.severe("Fehlerkennzeichen kann nicht geprueft werden, da der Datensatz zu kurz ist: >" + datensatz
							+ "<");
					result = false;
				}
				
			} catch (final IllegalArgumentException e) {
				LOGGER.log(Level.WARNING, "Die Datensatzkennung ist ungueltig: " + kennung);
				throw new DatensatzkennungUngueltigException("Die Datensatzkennung ist ungueltig: " + kennung);
			}
		} else 
			throw new DatensatzAufbauException("Der Datensatz ist zu kurz um eine Kennung zu beinhalten:");

		return result;
	}

	/**
	 * Laedt die logging.properties Datei und uebergibt sie den Loggern.
	 */
	private void ladeLoggerEinstellungen(final OutputStream loggerOutputStream) {
		try {
			InputStream is = null;

			boolean internerLogger = false;

			try {
				is = WertelistenReader.getInputStream(LOGGER_EXTERN);
			} catch (final WertelistenException e) {
				try {
					is = WertelistenReader.getInputStream(LOGGER_INTERN);
					internerLogger = true;
				} catch (final WertelistenException e1) {
					LOGGER.log(Level.WARNING, "Konnte Standard-Logger-Settings nicht laden.");
				}
			}

			if (is != null) {
				LogManager.getLogManager().readConfiguration(is);
				if (internerLogger) {
					LOGGER.log(Level.INFO,
							"Konnte Logger-Settings nicht laden. Es werden die Standard-Werte verwendet.");
				}
			}
		} catch (final IOException e) {
			LOGGER.log(Level.WARNING, "Konnte Logger-Settings nicht laden. " + e.getMessage());
		}

		if (loggerOutputStream != null) {
			LOGGER.getParent().addHandler(new DeuevStreamHandler(loggerOutputStream));
		}
	}
}
