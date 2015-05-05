package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Ermittlung der Anzahl der Tage zur Berechnung der Beitragsbemessungsgrenze
 * und Bezugsgroesse nach Anlage ab700000_PY-BBG-BG.doc.
 */
public class EntgeltTage {

	private static final int TAGE_VOLLER_MONAT = 30;
	private static final List<String> NICHT_BESCH = Arrays.asList("109", "201", "207", "208", "209", "210", "301",
			"302", "303");
	private static final List<String> UNST_BESCH = Arrays.asList("118", "205");

	EntgeltTage() {
		super();
	}

	/**
	 * Berechnet die zu zaehlenden Tage.
	 * 
	 * @param calZrbg
	 *            das Calendar-Object des Feldes ZeitraumBeginn
	 * @param calZren
	 *            das Calendar-Object des Feldes ZeitraumEnde
	 * @return Anzahl Tage aus angebrochenen Monaten
	 */
	private int berechneTage(final Calendar calZrbg, final Calendar calZren) {
		int tage = 0;

		if (this.isGleicherMonat(calZrbg, calZren)) {
			tage = this.berechneTageSelberMonat(calZrbg, calZren);
		} else {
			tage = this.berechneTageMonatsuebergreifend(calZrbg, calZren);
		}

		return tage;
	}

	/**
	 * Berechnet die Entgelt-Tage bei monatsuebergreifenden Zeitraeumen.
	 * 
	 * @param calZrbg
	 *            das Calendar-Object des Feldes ZeitraumBeginn
	 * @param calZren
	 *            das Calendar-Object des Feldes ZeitraumEnde
	 * @return die Anzahl Entgelt-Tage
	 */
	private int berechneTageMonatsuebergreifend(final Calendar calZrbg, final Calendar calZren) {
		int tage = 0;
		for (int currentMonth = calZrbg.get(Calendar.MONTH); currentMonth <= calZren.get(Calendar.MONTH); currentMonth++) {
			// beim Monat von ZRBG und ZREN muessen die angebrochenen tage
			// gezaehlt werden, sofern es sich bei diesen nicht um einen vollen
			// Monat handelt
			if (currentMonth == calZrbg.get(Calendar.MONTH)) {
				tage = this.getTageZrbgMonatsuebergreifend(calZrbg, calZren);
			} else if (currentMonth == calZren.get(Calendar.MONTH)) {
				tage = tage + this.getTageZrenMonatsuebergreifend(calZrbg, calZren);
			} else {
				tage = tage + TAGE_VOLLER_MONAT;
			}
		}

		return tage;
	}

	/**
	 * Berechnet die Entgelt-Tage bei Zeitraeumen im selben Monat.
	 * 
	 * @param calZrbg
	 *            das Calendar-Object des Feldes ZeitraumBeginn
	 * @param calZren
	 *            das Calendar-Object des Feldes ZeitraumEnde
	 * @return die Anzahl Entgelt-Tage
	 */
	private int berechneTageSelberMonat(final Calendar calZrbg, final Calendar calZren) {
		int tage = 0;

		if (this.isErsterDesMonats(calZrbg) && this.isLetzterDesMonats(calZren)) {
			tage = TAGE_VOLLER_MONAT;
		} else {
			final int tageZren = calZren.get(Calendar.DAY_OF_MONTH);
			final int tageZrbg = calZrbg.get(Calendar.DAY_OF_MONTH);
			tage = (tageZren - tageZrbg) + 1;
		}

		return tage;
	}

	/**
	 * Berechnet die Anzahl der Tage unter Beruecksichtigung der jeweils
	 * zutreffenden Ausnahmen und Bedingungen gemaess der jeweiligen
	 * Entscheidungstabellen.
	 * 
	 * @param feldZrbg
	 *            das Feld ZeitraumBeginn (zrbg)
	 * @param feldZren
	 *            das Feld ZeitraumEnde (zren)
	 * @return the anzahl tage
	 */
	public int getAnzahlTage(final Feld<? extends FeldName> feldZrbg, final Feld<? extends FeldName> feldZren) {
		return getAnzahlTage(null, feldZrbg, feldZren);
	}

	/**
	 * Berechnet die Anzahl der Tage unter Beruecksichtigung der jeweils
	 * zutreffenden Ausnahmen und Bedingungen gemaess der jeweiligen
	 * Entscheidungstabellen.
	 * 
	 * @param feldZrbg
	 *            das Feld ZeitraumBeginn (zrbg)
	 * @param feldZren
	 *            das Feld ZeitraumEnde (zren)
	 * @return the anzahl tage
	 */
	public int getAnzahlTageDbez(final Feld<? extends FeldName> feldZrbg, final Feld<? extends FeldName> feldZren) {
		return getAnzahlTage(null, feldZrbg, feldZren, true);
	}

	/**
	 * Berechnet die Anzahl der Tage unter Beruecksichtigung der jeweils
	 * zutreffenden Ausnahmen und Bedingungen gemaess der jeweiligen
	 * Entscheidungstabellen.
	 * 
	 * @param feldPersgr
	 *            das Feld Personengruppe; darf <code>null</code> sein
	 * @param feldZrbg
	 *            das Feld ZeitraumBeginn (zrbg)
	 * @param feldZren
	 *            das Feld ZeitraumEnde (zren)
	 * @return the anzahl tage
	 */
	public int getAnzahlTage(final Feld<? extends FeldName> feldPersgr, final Feld<? extends FeldName> feldZrbg,
			final Feld<? extends FeldName> feldZren) {
		return getAnzahlTage(feldPersgr, feldZrbg, feldZren, false);
	}

	/**
	 * Berechnet die Anzahl der Tage unter Beruecksichtigung der jeweils
	 * zutreffenden Ausnahmen und Bedingungen gemaess der jeweiligen
	 * Entscheidungstabellen.
	 * 
	 * @param feldPersgr
	 *            das Feld Personengruppe; darf <code>null</code> sein
	 * @param feldZrbg
	 *            das Feld ZeitraumBeginn (zrbg)
	 * @param feldZren
	 *            das Feld ZeitraumEnde (zren)
	 * @param dbez
	 *            Handelt es sich um die Entgelt-Pruefung aus dem Datensatz DSAE
	 *            / DBEZ? Sonderfall Februar wird beruecksichtigt
	 * @return the anzahl tage
	 */
	public int getAnzahlTage(final Feld<? extends FeldName> feldPersgr, final Feld<? extends FeldName> feldZrbg,
			final Feld<? extends FeldName> feldZren, final boolean dbez) {

		final String zrbgValue = feldZrbg.getTrimmedValue();
		final String zrenValue = feldZren.getTrimmedValue();

		final Date zrbg = DateUtils.parseDate(zrbgValue);
		final Date zren = DateUtils.parseDate(zrenValue);

		final Calendar calZrbg = Calendar.getInstance();
		calZrbg.setLenient(false);
		calZrbg.setTime(zrbg);

		final Calendar calZren = Calendar.getInstance();
		calZren.setLenient(false);
		calZren.setTime(zren);

		if (feldPersgr != null) {
			final String persgrValue = feldPersgr.getTrimmedValue();

			if (this.isBeschaeftigte(persgrValue)) {
				DateUtils.setJahresAnfang(calZrbg);
			}

			if (this.isUnstBeschaeftigte(persgrValue)) {
				DateUtils.setMonatsEnde(calZren);
			}
		}

		int tage = this.berechneTage(calZrbg, calZren);

		if (dbez) {
			tage += getZuschlagFebruar(calZrbg, calZren);
		}

		return tage;
	}

	/**
	 * Berechnet die Tage des zrbg bei monatsuebergreifenden Entgelt-Tagen.
	 * 
	 * @param calZrbg
	 *            das Calendar-Object des Feldes ZeitraumBeginn
	 * @param calZren
	 *            das Calendar-Object des Feldes ZeitraumEnde
	 * @return the tage zrbg monatsuebergreifend
	 */
	private int getTageZrbgMonatsuebergreifend(final Calendar calZrbg, final Calendar calZren) {
		int tage = 0;
		// handelt es sich um den 1. des ZRBG-Monats, ist dieser als
		// voller Monat anzusehen, ansonsten wird berechnet
		if (this.isErsterDesMonats(calZrbg)) {
			tage = TAGE_VOLLER_MONAT;
		} else {
			tage = (calZrbg.getActualMaximum(Calendar.DAY_OF_MONTH) - calZrbg.get(Calendar.DAY_OF_MONTH)) + 1;
		}

		return tage;
	}

	/**
	 * Berechnet die Tage zren bei monatsuebergreifenden Entgelt-Tagen.
	 * 
	 * @param calZrbg
	 *            das Calendar-Object des Feldes ZeitraumBeginn
	 * @param calZren
	 *            das Calendar-Object des Feldes ZeitraumEnde
	 * @return the tage zren monatsuebergreifend
	 */
	private int getTageZrenMonatsuebergreifend(final Calendar calZrbg, final Calendar calZren) {
		int tage = 0;
		// handelt es sich um den letzten des ZREN-Monats, ist dieser
		// als voller Monat anzusehen, ansonsten wird berechnet
		if (this.isLetzterDesMonats(calZren)) {
			tage = TAGE_VOLLER_MONAT;
		} else {
			tage = calZren.get(Calendar.DAY_OF_MONTH);
		}
		return tage;
	}

	/**
	 * Prueft, ob es sich um einen Beschaeftigten handelt.
	 * 
	 * @param persgrValue
	 *            der Feld-Wert Personengruppe
	 * @return true, if is Beschaeftigte
	 */
	private boolean isBeschaeftigte(final String persgrValue) {
		return !NICHT_BESCH.contains(persgrValue);
	}

	/**
	 * Pruefung auf ersten des Monats.
	 * 
	 * @param cal
	 *            das zu pruefende Calendar-Object
	 * @return true, if is erster des Monats
	 */
	private boolean isErsterDesMonats(final Calendar cal) {
		return cal.get(Calendar.DAY_OF_MONTH) == cal.getActualMinimum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Prueft, ob zwei Calendar-Objekte den gleichen Monat haben.
	 * 
	 * @param calZrbg
	 *            das Calendar-Object des Feldes ZeitraumBeginn
	 * @param calZren
	 *            das Calendar-Object des Feldes ZeitraumEnde
	 * @return true, if is gleicher Monat
	 */
	private boolean isGleicherMonat(final Calendar calZrbg, final Calendar calZren) {
		return calZrbg.get(Calendar.MONTH) == calZren.get(Calendar.MONTH);
	}

	/**
	 * Pruefung auf letzten des Monats.
	 * 
	 * @param cal
	 *            das zu pruefende Calendar-Object
	 * @return true, if is letzter des Monats
	 */
	private boolean isLetzterDesMonats(final Calendar cal) {
		return cal.get(Calendar.DAY_OF_MONTH) == cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Prueft, ob es sich um einen unstaendig Beschaeftigten handelt.
	 * 
	 * @param persgrValue
	 *            der Feld-Wert Personengruppe
	 * @return true, if is unstaendig Beschaeftigte
	 */
	private boolean isUnstBeschaeftigte(final String persgrValue) {
		return UNST_BESCH.contains(persgrValue);
	}

	/**
	 * Berechnet fuer Zeitraume, die im Februar (aber nich am 1.1.) begonnen
	 * haben und ueber diesen hinaus gehen "Bonustage". Im Schaltjahr gibt es 1,
	 * sonst 2. (siehe T74-1 aus ab740000_PY-BBG-Entgeltersatz.doc).
	 * 
	 * @param zrbgCal
	 * @param zrenCal
	 * @return Bonustage Februar (0, 1 oder 2)
	 */
	private int getZuschlagFebruar(final Calendar zrbgCal, final Calendar zrenCal) {
		int zuschlag = 0;

		if (zrbgCal.get(Calendar.MONTH) == Calendar.FEBRUARY && zrbgCal.get(Calendar.DAY_OF_MONTH) > 1) {
			final int tageFebruar = zrbgCal.getActualMaximum(Calendar.DAY_OF_MONTH);

			final Calendar zrenMin = (Calendar) zrbgCal.clone();
			zrenMin.set(Calendar.DAY_OF_MONTH, (tageFebruar - 1));

			if (zrenCal.after(zrenMin)) {
				zuschlag = TAGE_VOLLER_MONAT - tageFebruar;
			}
		}

		return zuschlag;
	}
}
