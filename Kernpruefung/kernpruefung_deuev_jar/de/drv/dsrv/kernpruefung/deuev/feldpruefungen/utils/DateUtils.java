package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Enthaelt Methoden fuer die Berechnung von Daten. Sie ist so konzipiert, das
 * sie keine Exceptions wirft, sondern bei Parse-Fehlern <code>null</code>
 * zurueckgibt und eine Logging-Ausgabe erstellt.
 */
public final class DateUtils {
	
	private static final int JAHRHUNDERT = 100;
	
	private static final Logger LOGGER = Logger.getLogger(DateUtils.class.getName());
	
	private static final int VSNR_LAENGE = 12;
	private static final int VSNR_START_TT = 2;
	private static final int VSNR_ENDE_JJ = 8;
	
	private static final String OHNE_TAG = "00";
	private static final String OHNE_MONAT = "00";
	private static final String OHNE_MONAT_TAG = "0000";
	
	private static final String KORREKTUR_TAG = "15";
	private static final String KORREKTUR_MONAT_TAG = "0701";
	
	private static final int INDEX_START_YYYY = 0;
	private static final int INDEX_ENDE_YYYY = 4;
	private static final int INDEX_ENDE_MM = 6;
	
	private static final int LAENGE_DATUM_YY = 6;
	private static final int INDEX_ENDE_YY = 2;
	private static final int INDEX_ENDE_MM_YY = 4;
	
	private static final int INDEX_VSNR_ENDE_TT = 2;
	private static final int INDEX_VSNR_START_MM = 2;
	private static final int INDEX_VSNR_ENDE_MM = 4;
	
	private static final int VSNR_MODULO_TAG = 32;
	private static final String VSNR_FORMAT_TAG = "00";
	
	private static final int INDEX_STUNDE_START = 0;
	private static final int INDEX_STUNDE_ENDE = 2;
	private static final int INDEX_MINUTE_START = INDEX_STUNDE_ENDE;
	private static final int INDEX_MINUTE_ENDE = 4;
	private static final int INDEX_SEKUNDE_START = INDEX_MINUTE_ENDE;
	private static final int INDEX_SEKUNDE_ENDE = 6;
	
	private static final int LAENGE_UHRZEIT = 6;
	
	/**
	 * Berechne das Datum eines bestimmten Geburtstages.
	 * 
	 * @param gebDat
	 *            das ausgehende Geburtsdatum
	 * @param geburtstag
	 *            der Geburtstag der errechnet werden soll
	 * @return das Datum des Geburtstags
	 */
	public static Date berechneDatumGeburtstag(final Date gebDat, final int geburtstag) {
		return DateUtils.berechneNeuesDatumJahr(gebDat, geburtstag, false);
	}
	
	/**
	 * Berechne ein neues Datum basierend auf einem Ausgangsdatum und einer
	 * Veraenderung des Jahres (bspw. '2', um zwei Jahre hoch zu zaehlen, oder
	 * '-4', um 4 Jahre herunter zu zaehlen).
	 * 
	 * @param datum
	 *            das Augangsdatum
	 * @param jahresAenderung
	 *            die Aenderung die am Jahr vorgenommen werden soll
	 * @param jahresende
	 *            true, wenn das Jahresende errechnet werden soll
	 * @return das errechnete Datum
	 */
	public static Date berechneNeuesDatumJahr(final Date datum, final int jahresAenderung, final boolean jahresende) {
		final Calendar cal = new GregorianCalendar();
		cal.setLenient(false);
		cal.setTime(datum);
		if (jahresende) {
			setJahresEnde(cal);
		}
		cal.add(Calendar.YEAR, jahresAenderung);
		return cal.getTime();
	}
	
	/**
	 * Berechne ein neues Datum basierend auf einem Ausgangsdatum und einer
	 * Veraenderung des Monats (bspw. '2', um zwei Monate hoch zu zaehlen, oder
	 * '-4', um 4 Monate herunter zu zaehlen).
	 * 
	 * @param datum
	 *            das Augangsdatum
	 * @param monatsAenderung
	 *            die Aenderung die am Monat vorgenommen werden soll
	 * @param monatsende
	 *            true, wenn das Monatsende errechnet werden soll
	 * @return das errechnete Datum
	 */
	public static Date berechneNeuesDatumMonat(final Date datum, final int monatsAenderung, final boolean monatsende) {
		final Calendar cal = new GregorianCalendar();
		cal.setLenient(false);
		cal.setTime(datum);
		cal.add(Calendar.MONTH, monatsAenderung);
		if (monatsende) {
			setMonatsEnde(cal);
		}
		return cal.getTime();
	}
	
	/**
	 * Berechne ein neues Datum basierend auf einem Ausgangsdatum und einer
	 * Veraenderung der Tage (bspw. '2', um zwei Tage hoch zu zaehlen, oder
	 * '-4', um 4 Tage herunter zu zaehlen).
	 * 
	 * @param datum
	 *            das Augangsdatum
	 * @param tagesAenderung
	 *            die Aenderung die an Tagen vorgenommen werden soll
	 *            
	 * @return das errechnete Datum
	 */
	public static Date berechneNeuesDatumTag(final Date datum, final int tagesAenderung) {
		final Calendar cal = new GregorianCalendar();
		cal.setLenient(false);
		cal.setTime(datum);
		cal.add(Calendar.DAY_OF_MONTH, tagesAenderung);
		
		return cal.getTime();
	}
	
	/**
	 * Berechne das korrigierte VSNR-Geburtsdatum. Der Tag wird als Integer
	 * geparst und Modulo 32 gerechnet.
	 * 
	 * @param datumVsnr
	 *            das Geburtsdatum aus der VSNR (Stellen 3 - 8 der VSNR) im
	 *            Format <code>ddMMyy</code>
	 * @return das korrigierte VSNR-Geburtsdatum
	 */
	public static String berechneVsnrDatum(final String datumVsnr) {
		final String monate = datumVsnr.substring(INDEX_VSNR_START_MM, INDEX_VSNR_ENDE_MM);
		Integer tage = Integer.valueOf(datumVsnr.substring(0, INDEX_VSNR_ENDE_TT));
		
		if (OHNE_MONAT.equals(monate)) {
			tage = 0;
		} else {
			tage = tage.intValue() % VSNR_MODULO_TAG;
		}
		
		final DecimalFormat dateFormat = new DecimalFormat(VSNR_FORMAT_TAG);
		
		return dateFormat.format(tage) + datumVsnr.substring(INDEX_VSNR_START_MM, datumVsnr.length());
	}
	
	/**
	 * Aendert das Datumsformat von <code>ddMMyy</code> nach <code>yyMMdd</code>
	 * .
	 * 
	 * @param datum
	 *            das Datum im Format <code>ddMMyy</code>
	 * @return das Datum im Format <code>yyMMdd</code>
	 */
	private static String changeDateFormat(final String datum) {
		final String day = datum.substring(0, 2);
		final String month = datum.substring(2, 4);
		final String year = datum.substring(4, 6);
		
		return year + month + day;
	}
	
	/**
	 * Fuegt einem Datum eine Uhrzeit hinzu.
	 * 
	 * @param datum
	 *            Das zu erweiternde Datum.
	 * @param uhrzeit
	 *            Die gewuenschte Uhrzeit im Format HHmmss.
	 * @return Datum mit Uhrzeit.
	 */
	public static Date fuegeDatumUhrzeitHinzu(final Date datum, final String uhrzeit) {
		boolean failure = false;
		
		// Parameter ueberpruefen.
		if ((uhrzeit == null) || (uhrzeit.length() != LAENGE_UHRZEIT)) {
			failure = true;
		}
		
		if (!failure) {
			try {
				Integer.parseInt(uhrzeit);
			} catch (final NumberFormatException e) {
				failure = true;
			}
		}
		
		if (!failure && (datum == null)) {
			failure = true;
		}
		
		if (!failure) {
			// Zeit setzen.
			final Calendar cal = Calendar.getInstance();
			cal.setTime(datum);
			cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(uhrzeit.substring(INDEX_STUNDE_START, INDEX_STUNDE_ENDE)));
			cal.set(Calendar.MINUTE, Integer.parseInt(uhrzeit.substring(INDEX_MINUTE_START, INDEX_MINUTE_ENDE)));
			cal.set(Calendar.SECOND, Integer.parseInt(uhrzeit.substring(INDEX_SEKUNDE_START, INDEX_SEKUNDE_ENDE)));
			return cal.getTime();
		} else {
			return null;
		}
	}
	
	/**
	 * Berechnet das (Geburts-)Datum aus der VSNR. Hierzu wird das Datum aus der
	 * VSNR extrahiert, ggf. zurueck gerechnet (zur Kodierung siehe Anlage
	 * "Allgemeines Rundschreiben") und ggf. mit Platzhaltern versehen (bei
	 * Auslaendern mit "00"-Datum).
	 * 
	 * @param feld
	 *            das zu pruefende Feld
	 * @param verarbeitungsdatum
	 *            Verarbeitungsdatum aus den Eingabedaten
	 * 
	 * @return das Geburtsdatum
	 */
	public static Date getDatVsnr(final Feld<?> feld, final Date verarbeitungsdatum) {
		Date gebDat = null;
		
		if (VSNR_LAENGE == feld.getTrimmedValue().length()) {
			final String rawDatVsnr = feld.getTrimmedValue().substring(VSNR_START_TT, VSNR_ENDE_JJ);
			final String istDatVsnr = DateUtils.berechneVsnrDatum(rawDatVsnr);
			final String changedDatVsnr = changeDateFormat(istDatVsnr);
			final String korrDatVsnr = DateUtils.korrigiereLeerDatum(changedDatVsnr);
			gebDat = DateUtils.parseDateVsnr(korrDatVsnr, verarbeitungsdatum, new SimpleDateFormat("yyMMdd",
					Locale.GERMANY));
		} else {
			if (LOGGER.isLoggable(Level.FINE)) {
				LOGGER.log(
						Level.FINE,
						String.format(
								"Uebergebener VSNR-Wert >%s< hat die falsche Laenge. Pruefungen mit Bezug auf das VSNR-Datum koennen nicht durchgefuehrt werden.",
								feld.getTrimmedValue()));
			}
		}
		
		return gebDat;
	}
	
	/**
	 * Validiert, ob ein Feld mit logisch korrektem Datum uebergeben wurde.
	 * 
	 * @param feld
	 *            das Feld mit dem zu pruefenden Datum
	 * @param dateFormat
	 *            das Datumsformat
	 * @return true, if is korrektes datum
	 */
	public static boolean isLogischKorrektesDatum(final Feld<?> feld, final DateFormat dateFormat) {
		boolean korrekt = false;
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(feld.getTrimmedValue());
			korrekt = true;
		} catch (final ParseException e) {
			if (LOGGER.isLoggable(Level.FINE)) {
				LOGGER.log(Level.FINE,
						String.format("Feld %s ist kein logisch korrektes Datum: %s", feld.getName(), e.toString()));
			}
		}
		
		return korrekt;
	}
	
	/**
	 * Validiert, ob ein Feld mit logisch korrektem Datum uebergeben wurde.
	 * (Format: JJJJMMTT)
	 * 
	 * @param datum
	 *            der String mit dem zu pruefenden Datum
	 * @return true, if is korrektes datum
	 */
	public static boolean isLogischKorrektesDatum(final String datum) {
		boolean korrekt = false;
		final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.GERMANY);
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(datum);
			korrekt = true;
		} catch (final ParseException e) {
			if (LOGGER.isLoggable(Level.FINE)) {
				LOGGER.log(Level.FINE, datum + " ist kein logisch korrektes Datum.");
			}
		}
		
		return korrekt;
	}
	
	/**
	 * Korrigiert das Datum (falls noetig) fuer weitere Pruefungen. Wenn Monat
	 * und Tag nicht vorhanden sind, wird das Datum auf den 01.07 gesetzt. Wenn
	 * nur der Tag fehlt, wird es auf den 15. des Monats gesetzt. Das Datum muss
	 * mit <code>MMdd</code> enden.
	 * 
	 * @param datum
	 *            der Datums-String im Format <code>{yy/yyyy}MMdd</code>
	 * @return das korrigierte Datum
	 */
	public static String korrigiereLeerDatum(final String datum) {
		
		final StringBuilder valueKorrigiert = new StringBuilder();
		
		int indexEndeYY = INDEX_ENDE_YYYY;
		int indexEndeMM = INDEX_ENDE_MM;
		
		if (datum.length() == LAENGE_DATUM_YY) {
			indexEndeYY = INDEX_ENDE_YY;
			indexEndeMM = INDEX_ENDE_MM_YY;
		}
		
		// Sind Monat und Tag nicht angegeben?
		if (datum.endsWith(OHNE_MONAT_TAG)) {
			valueKorrigiert.append(datum.substring(INDEX_START_YYYY, indexEndeYY)).append(KORREKTUR_MONAT_TAG);
		} else if (datum.endsWith(OHNE_TAG)) {
			valueKorrigiert.append(datum.substring(INDEX_START_YYYY, indexEndeMM)).append(KORREKTUR_TAG);
		} else {
			valueKorrigiert.append(datum);
		}
		
		return valueKorrigiert.toString();
	}
	
	/**
	 * Parse ein Datumsfeld. Bei Fehlern wird eine Logging-Ausgabe erstellt und
	 * <code>null</code> zurueckgegeben.
	 * 
	 * @param feld
	 *            das Feld mit dem Datum im Format <code>yyyyMMdd</code>
	 * @return das geparste Datum, oder <code>null</code>, wenn ein Fehler
	 *         aufgetreten ist
	 */
	public static Date parseDate(final Feld<?> feld) {
		final String feldValue = feld.getTrimmedValue();
		return parseDate(feldValue);
	}
	
	/**
	 * Parse einen Datums-String. Bei Fehlern wird eine Logging-Ausgabe erstellt
	 * und <code>null</code> zurueckgegeben.
	 * 
	 * @param datum
	 *            String mit dem Datum im Format <code>yyyyMMdd</code>
	 * @return das geparste Datum, oder <code>null</code>, wenn ein Fehler
	 *         aufgetreten ist
	 */
	public static Date parseDate(final String datum) {
		final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.GERMANY);
		dateFormat.setLenient(false);
		Date parsedDate = null;
		
		try {
			parsedDate = dateFormat.parse(datum);
		} catch (final ParseException e) {
			if (LOGGER.isLoggable(Level.FINE)) {
				LOGGER.log(Level.FINE,
						String.format("Uebergebener Wert >%s< ist kein gueltiges Datum: %s", datum, e.toString()));
			}
		}
		
		return parsedDate;
	}
	
	/**
	 * Parse einen Datums-String. Bei Fehlern wird eine Logging-Ausgabe erstellt
	 * und <code>null</code> zurueckgegeben.
	 * 
	 * @param datum
	 *            String mit dem Datum im uebergebenen Format
	 * @param dateFormat
	 *            das Datumsformat
	 * @return das geparste Datum, oder <code>null</code>, wenn ein Fehler
	 *         aufgetreten ist
	 */
	public static Date parseDate(final String datum, final DateFormat dateFormat) {
		dateFormat.setLenient(false);
		Date parsedDate = null;
		
		try {
			parsedDate = dateFormat.parse(datum);
		} catch (final ParseException e) {
			if (LOGGER.isLoggable(Level.FINE)) {
				LOGGER.log(Level.FINE,
						String.format("Uebergebener Wert >%s< ist kein gueltiges Datum: %s", datum, e.toString()));
			}
		}
		
		return parsedDate;
	}
	
	/**
	 * Parse einen Datums-String aus der VSNR. Bei Fehlern wird eine
	 * Logging-Ausgabe erstellt und <code>null</code> zurueckgegeben.
	 * 
	 * @param datum
	 *            String mit dem Datum im uebergebenen Format
	 * @param verarbeitungsdatum
	 *            das Verarbeitungsdatum
	 * @param dateFormat
	 *            das Datumsformat
	 * @return das geparste Datum, oder <code>null</code>, wenn ein Fehler
	 *         aufgetreten ist
	 */
	private static Date parseDateVsnr(final String vsnrDatum, final Date verarbeitungsdatum, final DateFormat dateFormat) {
		dateFormat.setLenient(false);
		Date parsedDate = null;
		
		try {
			parsedDate = dateFormat.parse(vsnrDatum);
			
			final GregorianCalendar parsedDateCal = new GregorianCalendar(Locale.GERMANY);
			parsedDateCal.setLenient(false);
			parsedDateCal.setTime(parsedDate);
			
			final GregorianCalendar verarbDatCal = new GregorianCalendar(Locale.GERMANY);
			verarbDatCal.setLenient(false);
			verarbDatCal.setTime(verarbeitungsdatum);
			
			// korrigiere zweistellige Jahresangabe
			if (parsedDateCal.get(Calendar.YEAR) > verarbDatCal.get(Calendar.YEAR)) {
				parsedDateCal.add(Calendar.YEAR, -JAHRHUNDERT);
				parsedDate = parsedDateCal.getTime();
			}
		} catch (final ParseException e) {
			if (LOGGER.isLoggable(Level.FINE)) {
				LOGGER.log(Level.FINE,
						String.format("Uebergebener Wert >%s< ist kein gueltiges Datum: %s", vsnrDatum, e.toString()));
			}
		}
		
		return parsedDate;
	}
	
	/**
	 * Setze Tag und Monat auf den Jahresanfang.
	 * 
	 * @param cal
	 *            das Calendar-Object, dass angepasst werden soll
	 */
	public static void setJahresAnfang(final Calendar cal) {
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, 0);
	}
	
	/**
	 * Setze Tag und Monat auf das Jahresende.
	 * 
	 * @param cal
	 *            das Calendar-Object, dass angepasst werden soll
	 */
	public static void setJahresEnde(final Calendar cal) {
		cal.set(Calendar.MONTH, cal.getActualMaximum(Calendar.MONTH));
		setMonatsEnde(cal);
	}
	
	/**
	 * Setze den Tag eines Monats auf das Monatsende.
	 * 
	 * @param cal
	 *            das Calendar-Object, dass angepasst werden soll
	 */
	public static void setMonatsEnde(final Calendar cal) {
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	}
	
	private DateUtils() {
	}
}