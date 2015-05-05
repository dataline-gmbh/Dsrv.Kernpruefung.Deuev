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
 * Klasse zur Ermittlung der Jahres-Beitragsbemessungsgrenze.
 */
public class JahresBeitragsbemessungsgrenze {

	private static final Logger LOGGER = Logger.getLogger(JahresBeitragsbemessungsgrenze.class.getName());

	private static final int INDEX_KJ = 0;
	private static final String DMARK = "D";

	private static final int INDEX_AVARV_WEST = 1;
	private static final int INDEX_KV_WERT = 1;
	private static final int INDEX_AVARV_OST = 2;
	private static final int INDEX_KNV_WEST = 3;
	private static final int INDEX_KNV_OST = 4;

	private static final List<String> AVARV = Arrays.asList("0A", "0B", "AB", "BA", "BB", "");
	private static final List<String> ALTE_BL = Arrays.asList("9", "W");

	private static final List<String> BBNRVU_KS = Arrays.asList("098", "980");

	private static Map<Integer, Beitragsbemessungsgrenze> bbgDmMap = new HashMap<Integer, Beitragsbemessungsgrenze>();
	private static Map<Integer, Beitragsbemessungsgrenze> bbgEuroMap = new HashMap<Integer, Beitragsbemessungsgrenze>();
	private static Map<Integer, BeitragsbemessungsgrenzeKv> bbgKvEuroMap = new HashMap<Integer, BeitragsbemessungsgrenzeKv>();

	JahresBeitragsbemessungsgrenze() {
		super();
		this.initBeitrBemGrMaps();
	}

	/**
	 * Errechnet den betreffenden JahresBeitragsbemessungsgrenze aus den
	 * uebergebenen Werten.
	 * 
	 * @param zrbg
	 *            das Feld Zeitraum Beginn (zrbg)
	 * @param waehrungsKennz
	 *            das Feld Waehrungskennzeichen
	 * @param kennzrk
	 *            das Feld Kennz. Rechnungskreis
	 * @param bbnrvu
	 *            das Feld BBNRVU (Betriebsnummer des Verursachers)
	 * @param vstr
	 *            das Feld VSTR (Versicherungstraeger)
	 * @return die Jahresbeitragsbemessungsgrenze
	 * @throws WertelistenException
	 *             wenn kein Eintrag in der Werteliste ist
	 */
	Double getJahresBeitrBemGr(final Feld<? extends FeldName> zrbg, final Feld<? extends FeldName> waehrungsKennz,
			final Feld<? extends FeldName> kennzrk, final Feld<? extends FeldName> bbnrvu,
			final Feld<? extends FeldName> vstr) throws WertelistenException {
		Double result = null;

		final int zrbgJahr = getJahr(zrbg);
		final Beitragsbemessungsgrenze jahresBBG = this.getJahresBbgBean(zrbgJahr, waehrungsKennz);
		
		String bbnrvuStart = "";
		if(bbnrvu != null && bbnrvu.getTrimmedValue().length() >= 3)
			bbnrvuStart = bbnrvu.getTrimmedValue().substring(0, 3);
		
		if (AVARV.contains(vstr.getTrimmedValue())) {
			if (this.isAlteBL(kennzrk)) {
				if (BBNRVU_KS.contains(bbnrvuStart)) {
					result = jahresBBG.getKnvWest();
				} else {
					result = jahresBBG.getAvArvWest();
				}
			} else {
				if (BBNRVU_KS.contains(bbnrvuStart)) {
					result = jahresBBG.getKnvOst();
				} else {
					result = jahresBBG.getAvArvOst();
				}
			}
		} else {
			if (bbnrvu == null || this.isAlteBL(kennzrk)) {
				result = jahresBBG.getKnvWest();
			} else {
				result = jahresBBG.getKnvOst();
			}
		}

		return result;
	}

	/**
	 * Erstelle eine BBG-Bean aus einer Liste von geparsten BBG-CSV-Werten.
	 * 
	 * @param werte
	 *            die Werteliste
	 * @return die errechnete Beitragsbemessungsgrenze
	 */
	private Beitragsbemessungsgrenze createBean(final List<String> werte) {
		Integer kalenderjahr;
		Double avArvWest;
		Double avArvOst;
		Double knvWest;
		Double knvOst;

		kalenderjahr = Integer.valueOf(werte.get(INDEX_KJ));
		avArvWest = Double.valueOf(werte.get(INDEX_AVARV_WEST));
		avArvOst = Double.valueOf(werte.get(INDEX_AVARV_OST));
		knvWest = Double.valueOf(werte.get(INDEX_KNV_WEST));
		knvOst = Double.valueOf(werte.get(INDEX_KNV_OST));

		return new Beitragsbemessungsgrenze(kalenderjahr, avArvWest, avArvOst, knvWest, knvOst);
	}
	
	/**
	 * Erstelle eine BBG-Bean fuer die Kv aus einer Liste von geparsten BBG-CSV-Werten.
	 * 
	 * @param werte
	 *            die Werteliste
	 * @return die errechnete Beitragsbemessungsgrenze
	 */
	private BeitragsbemessungsgrenzeKv createKvBean(final List<String> werte) {
		Integer kalenderjahr;
		Double kvWert;

		kalenderjahr = Integer.valueOf(werte.get(INDEX_KJ));
		kvWert = Double.valueOf(werte.get(INDEX_KV_WERT));
		return new BeitragsbemessungsgrenzeKv(kalenderjahr, kvWert);
	}

	/**
	 * Gebe eine Beitragsbemessungsgrenze-Bean zurueck, die alle Werte des
	 * jeweiligen Jahres in der angegebenen Waehrung haelt.
	 * 
	 * @param zrbgJahr
	 *            das Jahr des Zeitraum-Beginns
	 * @param waehrungsKennz
	 *            das Feld Waehrungskennzeichen
	 * @return die Beitragsbemessungsgrenzen-Bean des jeweiligen Jahres
	 * @throws WertelistenException
	 *             wenn kein Eintrag in der Werteliste ist
	 */
	Beitragsbemessungsgrenze getJahresBbgBean(final int zrbgJahr, final Feld<? extends FeldName> waehrungsKennz)
			throws WertelistenException {
		Beitragsbemessungsgrenze bbg = null;

		if (DMARK.equals(waehrungsKennz.getTrimmedValue())) {
			// bei DM gebe DM-Werte zurueck
			bbg = this.getBeitrBemGrDmBean(zrbgJahr);
		} else {
			// sonst gebe Euro-Werte zurueck
			bbg = this.getBeitrBemGrEuroBean(zrbgJahr);
		}

		return bbg;
	}
	
	/**
	 * Gebe eine Beitragsbemessungsgrenze-Bean fuer die KV zurueck, die alle Werte in Euro haelt.
	 * 
	 * @param zrbgJahr
	 *            das Jahr des Zeitraum-Beginns
	 * @return die Beitragsbemessungsgrenzen-Bean des jeweiligen Jahres
	 * @throws WertelistenException
	 *             wenn kein Eintrag in der Werteliste ist
	 */
	double getJahresBbgKvBean(final Feld<? extends FeldName> zrbg) throws WertelistenException {
		final int zrbgJahr = getJahr(zrbg);
		
		return this.getBeitrBemGrKvEuroBean(zrbgJahr).getKvWert();
	}

	/**
	 * Hole eine Beitragsbemessungsgrenze-Bean fuer das zutreffende Jahr aus der
	 * BBG-DM-Werteliste.
	 * 
	 * @param kalenderJahr
	 *            das Kalenderjahr
	 * @return die BBG-Bean
	 * @throws WertelistenException
	 *             wenn kein Eintrag in der Werteliste ist
	 */
	private Beitragsbemessungsgrenze getBeitrBemGrDmBean(final Integer kalenderJahr) throws WertelistenException {
		if (!bbgDmMap.containsKey(kalenderJahr)) {
			LOGGER.severe(String
					.format("Beitragsbemessungsgrenze in der Waehrung 'DM' fuer das Jahr %s in der zugehoerigen Werteliste nicht vorhanden!",
							kalenderJahr));
			throw new WertelistenException(
					String.format(
							"Beitragsbemessungsgrenze in der Waehrung 'DM' fuer das Jahr %s in der zugehoerigen Werteliste nicht vorhanden!",
							kalenderJahr));
		}
		return bbgDmMap.get(kalenderJahr);
	}

	/**
	 * Hole eine Beitragsbemessungsgrenze-Bean fuer das zutreffende Jahr aus der
	 * BBG-Euro-Werteliste.
	 * 
	 * @param kalenderJahr
	 *            das Kalenderjahr
	 * @return die BBG-Bean
	 * @throws WertelistenException
	 *             wenn kein Eintrag in der Werteliste ist
	 */
	private Beitragsbemessungsgrenze getBeitrBemGrEuroBean(final Integer kalenderJahr) throws WertelistenException {
		if (!bbgEuroMap.containsKey(kalenderJahr)) {
			LOGGER.severe(String
					.format("Beitragsbemessungsgrenze in der Waehrung 'Euro' fuer das Jahr %s in der zugehoerigen Werteliste nicht vorhanden!",
							kalenderJahr));
			throw new WertelistenException(
					String.format(
							"Beitragsbemessungsgrenze in der Waehrung 'Euro' fuer das Jahr %s in der zugehoerigen Werteliste nicht vorhanden!",
							kalenderJahr));

		}
		return bbgEuroMap.get(kalenderJahr);
	}
	
	/**
	 * Hole eine Beitragsbemessungsgrenze-Bean fuer die KV fuer das zutreffende Jahr aus der
	 * BBG-Euro-Werteliste.
	 * 
	 * @param kalenderJahr
	 *            das Kalenderjahr
	 * @return die BBG-Bean
	 * @throws WertelistenException
	 *             wenn kein Eintrag in der Werteliste ist
	 */
	private BeitragsbemessungsgrenzeKv getBeitrBemGrKvEuroBean(final Integer kalenderJahr) throws WertelistenException {
		if (!bbgKvEuroMap.containsKey(kalenderJahr)) {
			LOGGER.severe(String
					.format("Beitragsbemessungsgrenze fuer KV in der Waehrung 'Euro' fuer das Jahr %s in der zugehoerigen Werteliste nicht vorhanden!",
							kalenderJahr));
			throw new WertelistenException(
					String.format(
							"Beitragsbemessungsgrenze fuer KV in der Waehrung 'Euro' fuer das Jahr %s in der zugehoerigen Werteliste nicht vorhanden!",
							kalenderJahr));

		}
		return bbgKvEuroMap.get(kalenderJahr);
	}

	/**
	 * Initialisiert die benoetigten Wertelisten und fuellt die
	 * Beitragsbemessungsgrenzen-Maps.
	 * 
	 */
	private void initBeitrBemGrMaps() {
		if (bbgDmMap.isEmpty() && bbgEuroMap.isEmpty() && bbgKvEuroMap.isEmpty()) {
			final AbstractWertelistenVerwaltung verwaltung = WertelistenVerwaltungDeuev.getInstance();
			final List<String> bbgDM = verwaltung.getWerteliste(ListenTypDeuev.BBG_DM);
			final List<String> bbgEuro = verwaltung.getWerteliste(ListenTypDeuev.BBG_EURO);
			final List<String> bbgKvEuro = verwaltung.getWerteliste(ListenTypDeuev.BBG_KV_EURO);

			// fuelle die Maps, die die Werte-Beans halten
			for (final String zeile : bbgDM) {
				if (!zeile.startsWith("//")) {
					final Beitragsbemessungsgrenze dmBean = this.parseBeitrBemGrBean(zeile);
					bbgDmMap.put(dmBean.getKalenderjahr(), dmBean);
				}
			}

			for (final String zeile : bbgEuro) {
				if (!zeile.startsWith("//")) {
					final Beitragsbemessungsgrenze euroBean = this.parseBeitrBemGrBean(zeile);
					bbgEuroMap.put(euroBean.getKalenderjahr(), euroBean);
				}
			}
			
			for (final String zeile : bbgKvEuro) {
				if (!zeile.startsWith("//")) {
					final BeitragsbemessungsgrenzeKv euroKvBean = this.parseBeitrBemGrKvBean(zeile);
					bbgKvEuroMap.put(euroKvBean.getKalenderjahr(), euroKvBean);
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
	 * Erstelle eine Beitragsbemessungsgrenzen-Bean aus einem CSV-String.
	 * 
	 * @param csvString
	 *            ein csv-String mit Beitragsbemessungsgrenzen
	 * @return die entsprechende Beitragsbemessungsgrenze-Bean
	 */
	private Beitragsbemessungsgrenze parseBeitrBemGrBean(final String csvString) {
		final String[] werte = csvString.split(";");

		return this.createBean(Arrays.asList(werte));
	}
	
	/**
	 * Erstelle eine Beitragsbemessungsgrenzen-Bean aus einem CSV-String.
	 * 
	 * @param csvString
	 *            ein csv-String mit Beitragsbemessungsgrenzen
	 * @return die entsprechende Beitragsbemessungsgrenze-Bean
	 */
	private BeitragsbemessungsgrenzeKv parseBeitrBemGrKvBean(final String csvString) {
		final String[] werte = csvString.split(";");

		return this.createKvBean(Arrays.asList(werte));
	}

	/**
	 * Holt aus dem Feld ZeitraumBeginn das Jahr
	 * 
	 * @param zrbg
	 * @return Jahr
	 */
	int getJahr(final Feld<? extends FeldName> zrbg) {
		final Date zrgbDate = DateUtils.parseDate(zrbg);
		final Calendar zrbgCal = Calendar.getInstance();
		zrbgCal.setTime(zrgbDate);
		return zrbgCal.get(Calendar.YEAR);
	}
}
