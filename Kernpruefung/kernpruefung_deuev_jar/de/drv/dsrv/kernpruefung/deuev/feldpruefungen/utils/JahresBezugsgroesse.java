package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;
import de.drv.dsrv.kernpruefung.basis.wertelisten.AbstractWertelistenVerwaltung;
import de.drv.dsrv.kernpruefung.basis.wertelisten.WertelistenException;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.ListenTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Klasse zur Ermittlung der Jahres-Bezugsgroesse.
 */
public class JahresBezugsgroesse {
	
	private static final Logger LOGGER = Logger.getLogger(JahresBeitragsbemessungsgrenze.class.getName());
	
	private static final int INDEX_KJ = 0;
	private static final String DMARK = "D";
	
	private static final int INDEX_AVARV_WEST = 1;
	private static final int INDEX_AVARV_OST = 2;
	
	private static final List<String> ALTE_BL = Arrays.asList("9", "W");
	
	private static Map<Integer, Bezugsgroesse> bzgDmMap = new HashMap<Integer, Bezugsgroesse>();
	private static Map<Integer, Bezugsgroesse> bzgEuroMap = new HashMap<Integer, Bezugsgroesse>();
	
	/**
	 * Konstruktor.
	 */
	public JahresBezugsgroesse() {
		super();
		this.initBzgMaps();
	}
	
	/**
	 * Errechnet den betreffenden JahresBezugsgroesse aus den uebergebenen
	 * Werten.
	 * 
	 * @param zrbg
	 *            das Feld Zeitraum Beginn (zrbg)
	 * @param waehrungsKennz
	 *            das Feld Waehrungskennzeichen
	 * @param kennzrk
	 *            das Feld Kennz. Rechnungskreis
	 * @return the Jahres-Bezugsgroesse
	 * @throws WertelistenException
	 */
	Double getJahresBzg(final Feld<? extends FeldName> zrbg, final Feld<? extends FeldName> waehrungsKennz,
			final Feld<? extends FeldName> kennzrk) throws WertelistenException {
		Double result = null;
		
		final Date zrgbDate = DateUtils.parseDate(zrbg);
		final Calendar zrbgCal = Calendar.getInstance();
		zrbgCal.setTime(zrgbDate);
		final int zrbgJahr = zrbgCal.get(Calendar.YEAR);
		
		final Bezugsgroesse jahresBzg = this.getJahresBzgBean(zrbgJahr, waehrungsKennz);
		
		if (this.isAlteBL(kennzrk)) {
			result = jahresBzg.getAvArvWest();
		} else {
			result = jahresBzg.getAvArvOst();
		}
		
		return result;
	}
	
	/**
	 * Erstelle eine Bezugsgroesse-Bean aus einer Liste von geparsten
	 * Bezugsgroessen-CSV-Werten.
	 * 
	 * @param werte
	 *            die Werteliste
	 * @return die errechnete Bezugsgroesse
	 */
	private Bezugsgroesse createBean(final List<String> werte) {
		Integer kalenderjahr;
		Double avArvWest;
		Double avArvOst;
		kalenderjahr = Integer.valueOf(werte.get(INDEX_KJ));
		avArvWest = Double.valueOf(werte.get(INDEX_AVARV_WEST));
		avArvOst = Double.valueOf(werte.get(INDEX_AVARV_OST));
		
		return new Bezugsgroesse(kalenderjahr, avArvWest, avArvOst);
	}
	
	/**
	 * Hole eine Bezugsgroesse-Bean fuer das zutreffende Jahr aus der
	 * Bezugsgroesse-DM-Werteliste.
	 * 
	 * @param kalenderJahr
	 *            das Kalenderjahr
	 * @return die Bezugsgroesse-Bean
	 * @throws WertelistenException
	 */
	private Bezugsgroesse getBzgDmBean(final Integer kalenderJahr) throws WertelistenException {
		if (!bzgDmMap.containsKey(kalenderJahr)) {
			LOGGER.severe(String
					.format("Bezugsgroesse in der Waehrung 'DM' fuer das Jahr %s in der zugehoerigen Werteliste nicht vorhanden!",
							kalenderJahr));
			throw new WertelistenException(
					String.format(
							"Bezugsgroesse in der Waehrung 'DM' fuer das Jahr %s in der zugehoerigen Werteliste nicht vorhanden!",
							kalenderJahr));
		}
		return bzgDmMap.get(kalenderJahr);
	}
	
	/**
	 * Hole eine Bezugsgroesse-Bean fuer das zutreffende Jahr aus der
	 * Bezugsgroesse-Euro-Werteliste.
	 * 
	 * @param kalenderJahr
	 *            das Kalenderjahr
	 * @return die Bezugsgroesse-Bean
	 * @throws WertelistenException
	 */
	private Bezugsgroesse getBzgEuroBean(final Integer kalenderJahr) throws WertelistenException {
		if (!bzgEuroMap.containsKey(kalenderJahr)) {
			LOGGER.severe(String
					.format("Bezugsgroesse in der Waehrung 'Euro' fuer das Jahr %s in der zugehoerigen Werteliste nicht vorhanden!",
							kalenderJahr));
			throw new WertelistenException(
					String.format(
							"Bezugsgroesse in der Waehrung 'Euro' fuer das Jahr %s in der zugehoerigen Werteliste nicht vorhanden!",
							kalenderJahr));
		}
		return bzgEuroMap.get(kalenderJahr);
	}
	
	/**
	 * Gebe eine Bezugsgroesse-Bean zurueck, die alle Werte (Ost/West) des
	 * jeweiligen Jahres in der angegebenen Waehrung haelt.
	 * 
	 * @param zrbg
	 *            das Feld Zeitraum Beginn (zrbg)
	 * @param waehrungsKennz
	 *            das Feld Waehrungskennzeichen
	 * @return die Bezugsgroesse-Bean des jeweiligen Jahres
	 * @throws WertelistenException
	 */
	private Bezugsgroesse getJahresBzgBean(final int zrbgJahr, final Feld<? extends FeldName> waehrungsKennz)
			throws WertelistenException {
		Bezugsgroesse bzg = null;
		
		if (DMARK.equals(waehrungsKennz.getTrimmedValue())) {
			// bei DM gebe DM-Werte zurueck
			bzg = this.getBzgDmBean(zrbgJahr);
		} else {
			// sonst gebe Euro-Werte zurueck
			bzg = this.getBzgEuroBean(zrbgJahr);
		}
		
		return bzg;
	}
	
	/**
	 * Initialisiert die benoetigten Wertelisten und fuellt die
	 * Bezugsgroesse-Maps.
	 * 
	 */
	private void initBzgMaps() {
		if (bzgDmMap.isEmpty() && bzgEuroMap.isEmpty()) {
			final AbstractWertelistenVerwaltung verwaltung = WertelistenVerwaltungDeuev.getInstance();
			final List<String> bzgDM = verwaltung.getWerteliste(ListenTypDeuev.BEZUGSGROESSE_DM);
			final List<String> bzgEuro = verwaltung.getWerteliste(ListenTypDeuev.BEZUGSGROESSE_EURO);
			
			// fuelle die Maps, die die Werte-Beans halten
			for (final String zeile : bzgDM) {
				if (!zeile.startsWith("//")) {
					final Bezugsgroesse dmBean = this.parseBzgBean(zeile);
					bzgDmMap.put(dmBean.getKalenderjahr(), dmBean);
				}
			}
			
			for (final String zeile : bzgEuro) {
				if (!zeile.startsWith("//")) {
					final Bezugsgroesse euroBean = this.parseBzgBean(zeile);
					bzgEuroMap.put(euroBean.getKalenderjahr(), euroBean);
				}
			}
		}
	}
	
	/**
	 * Prueft, ob es sich um eines der alten Bundeslaender handelt.
	 * 
	 * @param kennzrk
	 *            das Feld Kennz. Rechungskreis
	 * @return true, if is alte Bundeslaender
	 */
	private boolean isAlteBL(final Feld<? extends FeldName> kennzrk) {
		return ALTE_BL.contains(kennzrk.getTrimmedValue());
	}
	
	/**
	 * Erstelle eine Bezugsgroesse-Bean aus einem Bezugsgroesse-CSV-String.
	 * 
	 * @param csvString
	 *            ein csv-String
	 * @return die Bezugsgroesse-Bean
	 */
	private Bezugsgroesse parseBzgBean(final String csvString) {
		final String[] werte = csvString.split(";");
		
		return this.createBean(Arrays.asList(werte));
	}
}
