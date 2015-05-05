package de.drv.dsrv.kernpruefung.basis.aufruf;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.drv.dsrv.kernpruefung.basis.utils.StrBuilder;

/**
 * Datenklasse fuer die Metadaten zu jedem Pruefdurchlauf.
 */
public class Metadaten {
	private static final Logger LOGGER = Logger.getLogger(Metadaten.class.getName());

	private static final int LAENGE_VERSION = 10;
	private static final int LAENGE_BUILDNR = 6;
	private static final int LAENGE_BUILDDATUM = 14;
	private static final int LAENGE_EINSATZTERMIN = 8;
	private static final int LAENGE_FACHVERFAHREN = 5;
	private static final int LAENGE_RESERVE = 138;

	private transient String buildNummer;
	private transient Date buildDatum;
	private transient String version;
	private transient Date einsatzTermin;
	private transient String fachverfahren;

	/**
	 * @return Build-Nummer
	 */
	public String getBuildNummer() {
		return buildNummer;
	}

	/**
	 * @param buildNummer
	 */
	public void setBuildNummer(final String buildNummer) {
		this.buildNummer = buildNummer;
	}

	/**
	 * @return Build-Datum
	 */
	public Date getBuildDatum() {
		return buildDatum;
	}

	/**
	 * @param buildDatum
	 */
	public void setBuildDatum(final Date buildDatum) {
		this.buildDatum = buildDatum;
	}

	/**
	 * @return Version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 */
	public void setVersion(final String version) {
		this.version = version;
	}

	/**
	 * @return Einsatztermin
	 */
	public Date getEinsatzTermin() {
		return einsatzTermin;
	}

	/**
	 * @param einsatzTermin
	 */
	public void setEinsatzTermin(final Date einsatzTermin) {
		this.einsatzTermin = einsatzTermin;
	}

	/**
	 * Nimmt einen String mit dem Format "dd.MM.yyyy" entgegen, parst diesen und
	 * setzt den Einsatztermin auf das entsprechende Datum.
	 * 
	 * @param einsatzTermin
	 */
	public void setEinsatzTermin(final String einsatzTermin) {
		final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		dateFormat.setLenient(false);
		try {
			this.einsatzTermin = dateFormat.parse(einsatzTermin);
		} catch (final ParseException e) {
			LOGGER.log(Level.WARNING, "Konnte den Einsatztermin nicht parsen. " + e.getMessage());
		} catch (final NullPointerException e) {
			LOGGER.log(Level.WARNING, "Einsatztermin ist null.");
		}
	}

	/**
	 * @return Fachverfahren
	 */
	public String getFachverfahren() {
		return fachverfahren;
	}

	/**
	 * @param fachverfahren
	 */
	public void setFachverfahren(final String fachverfahren) {
		this.fachverfahren = fachverfahren;
	}

	/**
	 * Liest die Build-Nummer und das Build-Datum aus der von Jenkins erstellte
	 * Properties-Datei.
	 * 
	 * @param metadaten
	 *            Die von Jenkins erstellte version.properties
	 */
	public void readVersionProperties(final Map<String, String> metadaten) {
		if (metadaten != null) {
			final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.GERMANY);
			dateFormat.setLenient(false);
			try {
				buildDatum = dateFormat.parse(metadaten.get("build.date") + " " + metadaten.get("build.time"));
			} catch (final ParseException e) {
				LOGGER.log(Level.WARNING, "Konnte das Datum aus version.properties nicht parsen. " + e.getMessage());
			}
		} else {
			LOGGER.log(Level.WARNING, "version.properties sind nicht vorhanden!");
		}
	}

	/**
	 * Gibt die Metadaten passend fuer den Datensatzstring zurueck.
	 * 
	 * @return Metadaten als Datensatz
	 */
	public String getAsDatensatz() {
		final StrBuilder builder = new StrBuilder();

		final SimpleDateFormat formatBuildDatum = new SimpleDateFormat("yyyyMMddhhmmss");
		final SimpleDateFormat formatEinsatzDatum = new SimpleDateFormat("yyyyMMdd");

		builder.appendFixedWidthPadRight(version, LAENGE_VERSION, ' ');
		builder.appendFixedWidthPadRight(buildNummer, LAENGE_BUILDNR, ' ');
		if (buildDatum != null) {
			builder.appendFixedWidthPadRight(formatBuildDatum.format(buildDatum), LAENGE_BUILDDATUM, ' ');
		} else {
			LOGGER.log(Level.WARNING, "Build-Datum ist null!");
			builder.appendFixedWidthPadRight(" ", LAENGE_BUILDDATUM, ' ');
		}

		if (einsatzTermin != null) {
			builder.appendFixedWidthPadRight(formatEinsatzDatum.format(einsatzTermin), LAENGE_EINSATZTERMIN, ' ');
		} else {
			LOGGER.log(Level.WARNING, "Einsatztermin ist null!");
			builder.appendFixedWidthPadRight(" ", LAENGE_EINSATZTERMIN, ' ');
		}
		builder.appendFixedWidthPadRight(fachverfahren, LAENGE_FACHVERFAHREN, ' ');
		builder.appendFixedWidthPadRight(" ", LAENGE_RESERVE, ' ');

		return builder.toString();
	}

	@Override
	public String toString() {
		return "Metadaten [buildNummer=" + buildNummer + ", buildDatum=" + buildDatum + ", version=" + version
				+ ", einsatzTermin=" + einsatzTermin + ", fachverfahren=" + fachverfahren + "]";
	}
}
