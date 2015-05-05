package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.wertelisten.WertelistenException;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.MapTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Utility-Klasse zur Ermittlung des Entgelts. Abstrahiert die Komplexitaet der
 * Berechnung der zu beruecksichtigenden Tage ({@link EntgeltTage}) sowie das
 * Auslesen der jeweiligen JahresBBG aus den Wertelisten (
 * {@link JahresBeitragsbemessungsgrenze}) in weiteren Klassen. Implementiert
 * als Singleton, um die aufwendige Dateninitialisierung zu minimieren.
 */
public final class EntgeltUtil {

	private static final Logger LOGGER = Logger.getLogger(EntgeltUtil.class.getName());

	private static final EntgeltUtil INSTANCE = new EntgeltUtil();

	private static final int TAGE_IM_JAHR = 360;
	private static final int PROZENT = 100;

	private static final double ZUSCHLAG_3_PROZENT = 1.033333;
	private static final double ZUSCHLAG_6_PROZENT = 1.06;

	private static final List<Integer> LEISTUNGSART_ARBEITSLOS = Arrays.asList(43, 44);
	private static final int JAHR_ARBEITSLOS_BBG = 2006;
	private static final int BBG_NACH_2006 = 2460;
	private static final int BBG_VOR_2006 = 4800;
	private static final int LEISTUNGSART_ZUSCHLAG_6_MIN = 20;
	private static final int LEISTUNGSART_ZUSCHLAG_6_MAX = 33;

	/**
	 * Gibt eine Instanz zurueck. Singleton-Pattern.
	 * 
	 * @return {@link EntgeltUtil}
	 */
	public static EntgeltUtil getInstance() {
		return INSTANCE;
	}

	private final transient EntgeltTage entgeltTage = new EntgeltTage();
	private final transient JahresBeitragsbemessungsgrenze bbg = new JahresBeitragsbemessungsgrenze();
	private final transient JahresBezugsgroesse bez = new JahresBezugsgroesse();

	/**
	 * Singleton-Klasse.
	 */
	private EntgeltUtil() {
	}

	/**
	 * Berechnet die anteilige Beitragsbemessungsgrenze. Hierzu werden die
	 * zutreffende Jahresbeitragsbemessungsgrundlage und die zu
	 * beruecksichtigenden Tage in Abhaengigkeit zu den uebergebenen Parameter
	 * ermittelt und dann umgerechnet.
	 * 
	 * @param feldPersgr
	 *            das Feld Personengruppe
	 * @param feldZrbg
	 *            das Feld Zeitraum Beginn (zrbg)
	 * @param feldZren
	 *            das Feld Zeitraum Ende (zen)
	 * @param waehrungsKennz
	 *            das Feld Waehrungskennzeichen
	 * @param kennzrk
	 *            das Feld Kennz. Rechnungskreis
	 * @param bbnrvu
	 *            das Feld BBNRVU (Betriebsnummer des Verursachers)
	 * @param vstr
	 *            das Feld VSTR (Versicherungstraeger)
	 * @return die anteilige Beitragsbemessungsgrenze
	 * @throws WertelistenException
	 *             wenn kein Eintrag in der Werteliste ist
	 */
	public Double getAnteiligeBeitrBemGr(final Feld<? extends FeldName> feldPersgr,
			final Feld<? extends FeldName> feldZrbg, final Feld<? extends FeldName> feldZren,
			final Feld<? extends FeldName> waehrungsKennz, final Feld<? extends FeldName> kennzrk,
			final Feld<? extends FeldName> bbnrvu, final Feld<? extends FeldName> vstr) throws WertelistenException {

		final double jahresBBG = this.bbg.getJahresBeitrBemGr(feldZrbg, waehrungsKennz, kennzrk, bbnrvu, vstr);
		final double anzahlTage = this.entgeltTage.getAnzahlTage(feldPersgr, feldZrbg, feldZren);

		return anzahlTage * jahresBBG / TAGE_IM_JAHR;
	}
	
	/**
	 * Berechnet die anteilige Beitragsbemessungsgrenze. Hierzu werden die
	 * zutreffende Jahresbeitragsbemessungsgrundlage und die zu
	 * beruecksichtigenden Tage in Abhaengigkeit zu den uebergebenen Parameter
	 * ermittelt und dann umgerechnet.
	 * 
	 * @param feldPersgr
	 *            das Feld Personengruppe
	 * @param feldZrbg
	 *            das Feld Zeitraum Beginn (zrbg)
	 * @param feldZren
	 *            das Feld Zeitraum Ende (zen)
	 * @param waehrungsKennz
	 *            das Feld Waehrungskennzeichen
	 * @param kennzrk
	 *            das Feld Kennz. Rechnungskreis
	 * @param bbnrvu
	 *            das Feld BBNRVU (Betriebsnummer des Verursachers)
	 * @param vstr
	 *            das Feld VSTR (Versicherungstraeger)
	 * @return die anteilige Beitragsbemessungsgrenze
	 * @throws WertelistenException
	 *             wenn kein Eintrag in der Werteliste ist
	 */
	public Double getAnteiligeBeitrBemGrKv(final Feld<? extends FeldName> feldPersgr,
			final Feld<? extends FeldName> feldZrbg, final Feld<? extends FeldName> feldZren) throws WertelistenException {

		final double jahresBBG = this.bbg.getJahresBbgKvBean(feldZrbg);
		final double anzahlTage = this.entgeltTage.getAnzahlTage(feldPersgr, feldZrbg, feldZren);

		return anzahlTage * jahresBBG / TAGE_IM_JAHR;
	}

	/**
	 * Berechnet die anteilige Beitragsbemessungsgrenze fuer DSAE / DBEZ inkl.
	 * Zuschlaege und aufrunden. Hierzu werden die zutreffende
	 * Jahresbeitragsbemessungsgrundlage und die zu beruecksichtigenden Tage in
	 * Abhaengigkeit zu den uebergebenen Parameter ermittelt und dann
	 * umgerechnet.
	 * 
	 * @param feldZrbg
	 *            ZeitraumBeginn
	 * @param feldZren
	 *            ZeitraumEnde
	 * @param feldWaehrungsKennz
	 *            Waehrungskennzeichen
	 * @param feldKennzrk
	 *            Kennzeichen RK
	 * @param feldVstr
	 *            VSTR aus DSAE
	 * @param feldLeistungsArt
	 *            Leistungsart
	 * @return maximale Entgelt
	 * @throws WertelistenException
	 *             wenn kein Eintrag in der Werteliste ist
	 */
	public int getMaxEntgeltDbez(final Feld<? extends FeldName> feldZrbg, final Feld<? extends FeldName> feldZren,
			final Feld<? extends FeldName> feldWaehrungsKennz, final Feld<? extends FeldName> feldKennzrk,
			final Feld<? extends FeldName> feldVstr, final Feld<? extends FeldName> bbnrvu,
			final Feld<? extends FeldName> feldLeistungsArt)
			throws WertelistenException {

		double zuschlag = ZUSCHLAG_3_PROZENT;

		int leistungsArt = -1;
		if (feldLeistungsArt != null) {
			leistungsArt = Integer.parseInt(feldLeistungsArt.getTrimmedValue());
		}

		double jahresBBG;
		if (LEISTUNGSART_ARBEITSLOS.contains(leistungsArt)) {
			zuschlag = 1.0;

			if (this.bbg.getJahr(feldZrbg) <= JAHR_ARBEITSLOS_BBG) {
				jahresBBG = BBG_VOR_2006;
			} else {
				jahresBBG = BBG_NACH_2006;
			}
		} else {
			jahresBBG = this.bbg.getJahresBeitrBemGr(feldZrbg, feldWaehrungsKennz, feldKennzrk, bbnrvu, feldVstr);
		}

		final double anzahlTage = this.entgeltTage.getAnzahlTageDbez(feldZrbg, feldZren);
		double maxEntgelt = jahresBBG * anzahlTage / TAGE_IM_JAHR;

		if (leistungsArt >= LEISTUNGSART_ZUSCHLAG_6_MIN && leistungsArt <= LEISTUNGSART_ZUSCHLAG_6_MAX) {
			zuschlag = ZUSCHLAG_6_PROZENT;
		}

		maxEntgelt = maxEntgelt * zuschlag;
		return (int) Math.ceil(maxEntgelt);
	}

	/**
	 * Berechnet die anteilige Bezugsgroesse. Hierzu werden die zutreffende
	 * Jahresbezugsgroesse und die zu beruecksichtigenden Tage in Abhaengigkeit
	 * zu den uebergebenen Parameter ermittelt und dann umgerechnet.
	 * 
	 * @param feldPersgr
	 *            das Feld Personengruppe
	 * @param feldZrbg
	 *            das Feld Zeitraum Beginn (zrbg)
	 * @param feldZren
	 *            das Feld Zeitraum Ende (zen)
	 * @param waehrungsKennz
	 *            das Feld Waehrungskennzeichen
	 * @param kennzrk
	 *            das Feld Kennz. Rechnungskreis
	 * @param vstr
	 *            das Feld VSTR (Versicherungstraeger)
	 * @return die anteilige Bezugsgroesse
	 * @throws WertelistenException
	 */
	public Double getAnteiligeBezGr(final Feld<? extends FeldName> feldPersgr, final Feld<? extends FeldName> feldZrbg,
			final Feld<? extends FeldName> feldZren, final Feld<? extends FeldName> waehrungsKennz,
			final Feld<? extends FeldName> kennzrk, final Feld<? extends FeldName> vstr) throws WertelistenException {

		final double jahresBez = this.bez.getJahresBzg(feldZrbg, waehrungsKennz, kennzrk);
		final double anzahlTage = this.entgeltTage.getAnzahlTage(feldPersgr, feldZrbg, feldZren);

		return anzahlTage * jahresBez / TAGE_IM_JAHR;
	}

	/**
	 * Ermittelt die zu beruecksichtigenden Tage in Abhaengigkeit zu den
	 * uebergebenen Parametern.
	 * 
	 * @param feldPersgr
	 *            das Feld Personengruppe
	 * @param feldZrbg
	 *            das Feld Zeitraum Beginn (zrbg)
	 * @param feldZren
	 *            das Feld Zeitraum Ende (zen)
	 * @return die Anzahl der zu beruecksichtigenden Tage
	 */
	public int getAnzahlTage(final Feld<? extends FeldName> feldPersgr, final Feld<? extends FeldName> feldZrbg,
			final Feld<? extends FeldName> feldZren) {
		return this.entgeltTage.getAnzahlTage(feldPersgr, feldZrbg, feldZren);
	}
	
	/**
	 * Ermittelt die zu beruecksichtigenden Tage in Abhaengigkeit zu den
	 * uebergebenen Parametern speziell für den DBEZ.
	 * 
	 * @param feldZrbg
	 *            das Feld Zeitraum Beginn (zrbg)
	 * @param feldZren
	 *            das Feld Zeitraum Ende (zen)
	 * @return die Anzahl der zu beruecksichtigenden Tage
	 */
	public int getAnzahlTageDbez(final Feld<? extends FeldName> feldZrbg, final Feld<? extends FeldName> feldZren) {
		return this.entgeltTage.getAnzahlTage(null, feldZrbg, feldZren, true);
	}

	/**
	 * Ermittelt die Beitragsbemessungsgrenze eines Jahres in Abhaengigkeit zu
	 * den uebergebenen Parametern.
	 * 
	 * @param feldZrbg
	 *            das Feld Zeitraum Beginn (zrbg)
	 * @param waehrungsKennz
	 *            das Feld Waehrungskennzeichen
	 * @param kennzrk
	 *            das Feld Kennz. Rechnungskreis
	 * @param bbnrvu
	 *            das Feld BBNRVU (Betriebsnummer des Verursachers)
	 * @param vstr
	 *            das Feld VSTR (Versicherungstraeger)
	 * @return die Beitragsbemessungsgrenze des uebergebenen Jahres
	 * @throws WertelistenException
	 *             wenn kein Eintrag in der Werteliste ist
	 */
	public Double getJahresBeitrBemGr(final Feld<? extends FeldName> feldZrbg,
			final Feld<? extends FeldName> waehrungsKennz, final Feld<? extends FeldName> kennzrk,
			final Feld<? extends FeldName> bbnrvu, final Feld<? extends FeldName> vstr) throws WertelistenException {
		return this.bbg.getJahresBeitrBemGr(feldZrbg, waehrungsKennz, kennzrk, bbnrvu, vstr);
	}

	/**
	 * Berechnet den Beitragsanteil nach ab760000_PY-BY-Anteil.doc.
	 * 
	 * @param feldZrbg
	 *            das Feld Zeitraum Beginn
	 * @param feldZren
	 *            das Feld Zeitraum Ende
	 * @param feldWaehrungskennzeichen
	 *            das Feld Waehrungskennzeichen
	 * @return die Beitragsmessungsgrenze des uebergebenen Jahres
	 * @throws UngueltigeDatenException
	 * @throws WertelistenException
	 *             wenn kein Eintrag in der Werteliste ist
	 */
	public int getBeitragsanteil(final Feld<? extends FeldName> feldZrbg, final Feld<? extends FeldName> feldZren,
			final Feld<? extends FeldName> feldWaehrungskennzeichen) throws UngueltigeDatenException,
			WertelistenException {
		final int jahr = this.bbg.getJahr(feldZrbg);
		final Beitragsbemessungsgrenze b = this.bbg.getJahresBbgBean(jahr, feldWaehrungskennzeichen);

		final Map<String, String> beitragssaetze = WertelistenVerwaltungDeuev.getInstance().getMap(
				MapTypDeuev.BEITRAGSSATZ_DEUEV);
		final String beitragssatz = beitragssaetze.get("" + jahr);

		if (beitragssatz == null) {
			LOGGER.log(Level.SEVERE, "Fuer das Jahr " + jahr + ", generiert aus dem Feld " + feldZrbg
					+ " gibt es keinen Beitragssatz.");
			throw new WertelistenException("Fuer das Jahr " + jahr + ", generiert aus dem Feld " + feldZrbg
					+ " gibt es keinen Beitragssatz.");
		}

		final double bs = Double.parseDouble(beitragssatz);
		final double tage = this.entgeltTage.getAnzahlTage(feldZrbg, feldZren);

		final double ergebnis = b.getKnvWest() * ((bs / PROZENT) / 2) * tage / TAGE_IM_JAHR;

		return (int) Math.ceil(ergebnis);
	}
}
