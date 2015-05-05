package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;
import de.drv.dsrv.kernpruefung.basis.utils.Sonderzeichen;

/**
 * Validiert, ob ein @ oder § (commercial at || section sign) Zeichen genau einmal vorkommt und dieses
 * weder am Anfang noch am Ende steht. DIES IST NICHT EINE VOLLSTAENDIGE EMAIL
 * PRUEFUNG. Fehlenummer: KO612.
 */
public class PruefungEmail extends AbstractPruefung {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungEmail(final Feld<? extends FeldName> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreichAt = false;
		boolean erfolgreichPara = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			int countAt = 0;
			int countPara = 0;

			for (int i = 0; i < feldValue.length(); ++i) {
				if (feldValue.charAt(i) == Sonderzeichen.COMMERCIAL_AT) {
					++countAt;
				}
				
				if (feldValue.charAt(i) == Sonderzeichen.SECTION_SIGN) {
					++countPara;
				}
			}

			if(countAt + countPara == 1) {
				if (countAt == 1) {
					if (feldValue.charAt(0) != Sonderzeichen.COMMERCIAL_AT) {
						if (feldValue.charAt(feldValue.length() - 1) != Sonderzeichen.COMMERCIAL_AT) {
							erfolgreichAt = true;
						}
					}
				} else if (countAt == 0) {
					erfolgreichAt = true;
				}
				
				if (countPara == 1) {
					if (feldValue.charAt(0) != Sonderzeichen.SECTION_SIGN) {
						if (feldValue.charAt(feldValue.length() - 1) != Sonderzeichen.SECTION_SIGN) {
							erfolgreichPara = true;
						}
					}
				} else if (countPara == 0 && countAt > 0) {
					erfolgreichPara = true;
				}
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Email< Wert des Feldes fehlt.");
		}

		return erfolgreichAt && erfolgreichPara;
	}

}
