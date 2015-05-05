package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.HashMap;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Die Komponente ermoeglicht es zu ueberpruefen, ob in einem Feldwert die
 * Anzahl eines bestimmten Zeichens kleiner/gleich/groesser als der
 * Vergleichswert ist.
 */
public class PruefungAnzahlVorkommenZeichen extends AbstractPruefung {
	/**
	 * Definiert den Modus des Verlgeichs bei der Pruefung.
	 */
	public enum Modus {
		/**
		 * Hierbei wird darauf geprueft, ob die Anzhal der Vorkommen des
		 * Zeichens geringer ist als die uebergebene Anzahl:<br/>
		 * <br/>
		 * <b>tatsaechlicheAnzahl < erlaubteAnzahl</b>.
		 */
		WENIGER_VORKOMMEN,
		/**
		 * Hierbei wird darauf geprueft, ob die Anzahl der Vorkommen des
		 * Zeichens groesser ist als die uebergebene Anzahl:<br/>
		 * <br/>
		 * <b>tatsaechlicheAnzahl > erlaubteAnzahl</b>.
		 */
		MEHR_VORKOMMEN,
		/**
		 * Hierbei wird darauf geprueft, ob die Anzahl der Vorkommen des
		 * Zeichens gleich der uebergebenen Anzahl ist:<br/>
		 * <br/>
		 * <b>tatsaechlicheAnzahl = erlaubteAnzahl</b>.
		 */
		GLEICHVIELE_VORKOMMEN
	}

	private final Character zeichen;
	private final int erlaubteAnzahl;
	private final Modus modus;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            das Feld dessen Feldwert geprueft wird.
	 * @param zeichen
	 *            das Zeichen welches im Feldwert gezaehlt werden soll.
	 * @param modus
	 *            die Art des Vergleichs (Vergleichsoperand), die den Erfolg der
	 *            Pruefung beschreibt. (gezaehlteAnzahl Vergleichsoperand
	 *            erlaubteAnzahl, z.B. 2 < 3)
	 * @param erlaubteAnzahl
	 *            der Vergleichswert fuer die Zaehlung.
	 * @see Modus
	 */
	public PruefungAnzahlVorkommenZeichen(Feld<? extends FeldName> feld, Character zeichen, Modus modus,
			int erlaubteAnzahl) {
		super(feld);

		this.zeichen = zeichen;
		this.modus = modus;
		this.erlaubteAnzahl = erlaubteAnzahl;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean ergebnis = true;


		if (getFeld() != null && getFeld().getValue() != null) {
			final String feldWert = getFeld().getValue();

			final HashMap<Character, Integer> zaehlTabelle = new HashMap<Character, Integer>();

			for (int i = 0; i < feldWert.length(); i++) {
				final char aktuellesZeichen = feldWert.charAt(i);

				Integer zaehlStand = zaehlTabelle.get(aktuellesZeichen);
				if (zaehlStand == null) {
					zaehlStand = 0;
				}
				zaehlTabelle.put(aktuellesZeichen, ++zaehlStand);

			}

			Integer gesuchterWert = zaehlTabelle.get(zeichen);
			if (gesuchterWert == null) {
				gesuchterWert = 0;
			}

			if ((gesuchterWert > erlaubteAnzahl && modus == Modus.MEHR_VORKOMMEN)
					|| (gesuchterWert < erlaubteAnzahl && modus == Modus.WENIGER_VORKOMMEN)
					|| (gesuchterWert == erlaubteAnzahl && modus == Modus.GLEICHVIELE_VORKOMMEN)) {
				ergebnis = true;
			} else {
				ergebnis = false;
			}

		} else {
			ergebnis = false;
		}

		return ergebnis;
	}

}
