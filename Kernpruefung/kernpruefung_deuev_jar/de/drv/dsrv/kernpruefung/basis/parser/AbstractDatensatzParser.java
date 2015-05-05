package de.drv.dsrv.kernpruefung.basis.parser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Datensatz;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;
import de.drv.dsrv.kernpruefung.basis.model.SimpleDatensatz;

/**
 * Vaterklasse fuer DatensatzParser. Ausserdem bietet die Klasse eine Methode
 * an, mit der der Datensatz in Bausteine geparst wird.
 */
public abstract class AbstractDatensatzParser implements DatensatzParser {
	protected static final Logger LOGGER = Logger.getLogger(AbstractFeldPruefung.class.getName());

	private transient String datensatzWert;
	private transient int offset;
	private static final int LAENGE_KENNUNG = 4;
	private transient BausteinReihenfolge reihenfolge;
	private final transient Queue<Entry<BausteinReihenfolge, Boolean>> schalterleiste;
	private final transient Fehler<? extends FehlerNummer> fehlerGesamtLaenge;

	protected AbstractDatensatzParser(Fehler<? extends FehlerNummer> fehlerGesamtLaenge, BausteinReihenfolge reihenfolge) {
		schalterleiste = new LinkedList<Map.Entry<BausteinReihenfolge, Boolean>>();

		this.fehlerGesamtLaenge = fehlerGesamtLaenge;
		this.reihenfolge = reihenfolge;
	}

	/**
	 * Parst den Datensatz in Bausteine und ruft dafuer z.B. die Methode
	 * <code>getBaustein</code> aus dieser Klasse auf.
	 * 
	 * @param datensatzWert
	 * @return Gibt den mit Bausteinen gefuellten Datensatz zurueck.
	 * @throws DatensatzAufbauException
	 */
	@Override
	public Datensatz parse(String datensatzWert) throws DatensatzAufbauException {
		if ((datensatzWert == null) || (datensatzWert.length() == 0)) {
			throw new DatensatzAufbauException("Fehler beim Lesen der Bausteine, Datensatz ist leer.");
		}

		this.datensatzWert = datensatzWert;
		parseSchalterleiste();
		ueberpruefeGesamtlaenge();

		final ArrayList<Baustein<? extends FeldName>> bausteine = new ArrayList<Baustein<? extends FeldName>>();
		final Datensatz datensatz = new SimpleDatensatz(bausteine);

		for (final Entry<BausteinReihenfolge, Boolean> aktuellerBaustein : schalterleiste) {
			if (aktuellerBaustein.getValue()) {
				final Baustein<?> baustein = parseBaustein(aktuellerBaustein.getKey());
				bausteine.add(baustein);
			}
		}

		return datensatz;
	}

	/**
	 * Schalterleiste wird ausgelesen und in einer Map gespeichert, wobei der
	 * Wert zeigt, ob der entsprechende Baustein vorhanden sein muss.
	 * 
	 * @throws DatensatzAufbauExceptionDEUEV
	 *             - Falls der Datensatz zu kurz ist wird mit der Fehlernummer
	 *             <Datensatz>910 abgebrochen.
	 */
	protected abstract void parseSchalterleiste() throws DatensatzAufbauException;

	/**
	 * Da die Laenge immer vom Vorgaenger ueberpruft wird, muss die Laenge am
	 * Ende nochmals explizit ueberprueft werden, falls das von der Kernpruefung
	 * so verlangt ist.
	 * 
	 * @param fehlerLaenge
	 *            - Die Fehlernummer, die im Fehlerfall geworfen werden soll,
	 *            sonst null.
	 * @throws DatensatzAufbauExceptionDEUEV
	 */
	protected void ueberpruefeLaenge(final Fehler<?> fehlerLaenge) throws DatensatzAufbauException {
		if (fehlerLaenge != null) {
			if (datensatzWert.length() != offset) {
				throw new DatensatzAufbauException(
						"Fehler beim Einlesen des Datensatzes. Der Datensatz hat die falsche Laenge.", fehlerLaenge);
			}
		}
	}

	protected Queue<Entry<BausteinReihenfolge, Boolean>> getSchalterleiste() {
		return schalterleiste;
	}

	protected String getDatensatzWert() {
		return datensatzWert;
	}

	/**
	 * Schneidet aus dem datensatzWert den Teil heraus, der zu dem Baustein
	 * gehoert. Dabei wird darauf geachtet, dass die Bausteine in der richtigen
	 * Reihenfolge abgearbeitet werden. Ausserdem wird ueberprueft, ob die
	 * Bausteine die richtige Laenge haben und ob die Kennung vorhanden ist.
	 * Sollte alles okay sein, wird <code>parse</code> auf dem Baustein
	 * aufgerufen.
	 * 
	 * @param laenge
	 *            - Die Laenge des Bausteins, falls bekannt. Sonst -1.
	 * @param fehlerKennung
	 *            - Die Fehlernummer, die zurueckgegeben werden soll, wenn die
	 *            Kennung nicht vorhanden ist.
	 * @param fehlerLaenge
	 *            - Die Fehlernummer, die zurueckgegeben werden soll, wenn die
	 *            Kennung an der falschen Position ist und somit die Laenge des
	 *            Vorgaengers (!!) falsch ist.
	 * @param parser
	 *            - Der Bausteinparser, auf den nachher die Methode parse
	 *            aufgerufen wird.
	 * @param aktuellerBaustein
	 *            - Wird fuer die Reihenfolge benoetigt.
	 * @return
	 * @throws DatensatzAufbauExceptionDEUEV
	 * @throws DatensatzAufbauException
	 */
	private Baustein<?> parseBaustein(final BausteinReihenfolge aktuellerBaustein) throws DatensatzAufbauException {
		final BausteinParser<?> parser = getBausteinParser(aktuellerBaustein);

		int laenge;
		if (offset > datensatzWert.length()) {
			throw new DatensatzAufbauException("Fehler beim Einlesen des Datensatzes, Baustein " + aktuellerBaustein
					+ " ist nicht an der richtigen Position oder fehlt.", parser.getFehlerBausteinNichtVorhanden());
		}
		if (offset > 0) {
			laenge = parser.getLaenge(datensatzWert.substring(offset));
		} else {
			laenge = parser.getLaenge(datensatzWert);
		}

		final String datensatz;
		final String kennung = aktuellerBaustein.getName();

		if (datensatzWert.length() >= offset + laenge) {
			datensatz = datensatzWert.substring(offset, (offset + laenge));
		} else {
			throw new DatensatzAufbauException("Fehler beim Einlesen des Datensatzes, Baustein " + aktuellerBaustein
					+ " ist nicht an der richtigen Position oder fehlt.", parser.getFehlerBausteinNichtVorhanden());
		}

		Baustein<?> baustein = null;

		if ((datensatz.length() > LAENGE_KENNUNG) && ((datensatz.substring(0, LAENGE_KENNUNG)).compareTo(kennung) == 0)) {
			// Reihenfolge pruefen.
			if (reihenfolge.getOrdinal() > aktuellerBaustein.getOrdinal()) {
				throw new DatensatzAufbauException("Fehler beim Einlesen des Datensatzes, Baustein "
						+ aktuellerBaustein + " erscheint nicht in der erwarteten Reihenfolge");
			}
			reihenfolge = aktuellerBaustein;

			baustein = parser.parse(datensatz);

		} else {
			// Es ist ein Fehler aufgetreten, der aktuelle Datensatz faengt
			// nicht mit der erwarteten Kennung an.
			LOGGER.log(Level.SEVERE, "Fehler beim Einlesen des Datensatzes, Baustein " + aktuellerBaustein
					+ " ist nicht an der richtigen Position oder fehlt. Datensatz: >" + datensatzWert + "<");
			throw new DatensatzAufbauException("Fehler beim Einlesen des Datensatzes, Baustein " + aktuellerBaustein
					+ " ist nicht an der richtigen Position oder fehlt.", parser.getFehlerBausteinNichtVorhanden());
		}

		offset += laenge;

		return baustein;
	}

	/**
	 * Erstellt den zum Baustein zugehoerigen Parser.
	 * 
	 * @param baustein
	 * @return
	 * @throws DatensatzAufbauException
	 *             - Wird geworfen, wenn kein der BausteinParser nicht erstellt
	 *             werden konnte.
	 */
	private BausteinParser<?> getBausteinParser(final BausteinReihenfolge baustein) throws DatensatzAufbauException {
		BausteinParser<?> parser = null;
		Class<?> clazz;
		try {
			clazz = Class.forName("de.drv.dsrv.kernpruefung.deuev.parser.baustein.BausteinParser" + baustein);
			parser = (BausteinParser<?>) clazz.newInstance();
		} catch (final Exception e) {
			LOGGER.log(Level.SEVERE, "BausteinParser konnte nicht instantiiert werden. " + e.toString());
			throw new DatensatzAufbauException("[DatensatzParser] Fehler beim Instantiieren der BausteinParser Klasse.");
		}

		return parser;
	}

	/**
	 * Errechnet die Gesamtlaenge des Datensatzes anhand der getSchalterleiste()
	 * und vergleicht diese mit der tatsaechlichen Laenge. Sollte diese nicht
	 * uebereinstimmen wird ein Fehler geworfen.
	 * 
	 * @throws DatensatzAufbauException
	 *             - Wird mit der Fehlernummer <Datensatz>910 geworfen, falls
	 *             die Laenge des Datensatzes falsch ist.
	 */
	private void ueberpruefeGesamtlaenge() throws DatensatzAufbauException {
		int laenge = 0;

		try {
			for (final Entry<BausteinReihenfolge, Boolean> aktuellerBaustein : schalterleiste) {
				if (aktuellerBaustein.getValue()) {
					final BausteinParser<?> parser = getBausteinParser(aktuellerBaustein.getKey());
					if (datensatzWert.length() > laenge) {
						laenge += parser.getLaenge(datensatzWert.substring(laenge));
					} else {
						laenge = -1;
						break;
					}
				}
			}
		} catch (final DatensatzAufbauException e) {
			// Laenge konnte nicht berechnet werden
			laenge = -1;
		}

		if (laenge != datensatzWert.length()) {
			if (LOGGER.isLoggable(Level.INFO)) {
				LOGGER.info("Fehler beim Einlesen des Datensatzes, die Laenge ist falsch. Erwartete Laenge: " + laenge
						+ ", tatsaechliche Laenge: " + datensatzWert.length());
			}
			throw new DatensatzAufbauException("Fehler beim Einlesen des Datensatzes, die Laenge ist falsch.",
					fehlerGesamtLaenge);
		}
	}
}
