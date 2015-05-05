package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Validiert, ob eine als String (fuehrende 0) uebergebene Zahl eine feste
 * Laenge hat und die Zahl innerhalb eines Bereiches liegt. Fehlernummer: AN020
 */
public class PruefungZahlBereichUndLaenge extends AbstractPruefung {

	private final int laenge;
	private final int minBereich;
	private final int maxBereich;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 * @param laenge
	 *            Laenge des Feldes
	 * @param minBereich
	 *            Mindeswert des Feldes
	 * @param maxBereich
	 *            Maximalwert des Feldes
	 */
	public PruefungZahlBereichUndLaenge(Feld<? extends FeldName> feld, final int laenge, final int minBereich,
			final int maxBereich) {
		super(feld);

		this.laenge = laenge;
		this.minBereich = minBereich;
		this.maxBereich = maxBereich;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();
			if (feldValue.length() != laenge) {
				return false;
			}

			try {
				final int intZahl = Integer.parseInt(feldValue);
				if (intZahl > maxBereich || intZahl < minBereich) {
					return false;
				}
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Bereich und Laenge einer Zahl< "
					+ "Wert des Feldes fehlt.");
		}

		return true;

	}

}
