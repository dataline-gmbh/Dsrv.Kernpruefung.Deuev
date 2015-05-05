package de.drv.dsrv.kernpruefung.deuev.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;
import de.drv.dsrv.kernpruefung.basis.pruefungen.AbstractPruefung;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.Pruefziffer;

/**
 * Validiert, ob eine Betriebsnummer nach Ziffer 1.3.2.2 vorliegt. Fehlernummer:
 * ME142
 */
public class PruefungBetriebsnummer extends AbstractPruefung {

	private static final int LAENGE = 8;
	private static final int ZEHNERSTELLE = 10;
	private static final int ERSTEN_DREI_ZIFFERN = 100000;
	private static final int KLEINER_99 = 99;
	private static final int GROESSER_110 = 110;
	private static final int PRUEFZIFFER_KONSTANTE = 5;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungBetriebsnummer(final Feld<? extends FeldName> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;

		final String feldValue = getFeld().getTrimmedValue();

		if (feldValue.length() == LAENGE) {

			try {
				// ueberpruefen, ob es eine Zahl ist.
				final int kompletteZahl = Integer.parseInt(feldValue);
				final int erstenDreiZiffern = kompletteZahl / ERSTEN_DREI_ZIFFERN;

				if ((erstenDreiZiffern >= 1 && erstenDreiZiffern <= KLEINER_99) || erstenDreiZiffern > GROESSER_110) {
					final int pruefziffer = kompletteZahl % ZEHNERSTELLE;
					int errechnetePruefziffer = Pruefziffer.berechnePruefzifferDerBetriebsnummer(getFeld()
							.getTrimmedValue());

					if (errechnetePruefziffer == pruefziffer) {
						erfolgreich = true;
					}

					else {
						errechnetePruefziffer += PRUEFZIFFER_KONSTANTE;
						errechnetePruefziffer = errechnetePruefziffer % ZEHNERSTELLE;

						if (errechnetePruefziffer == pruefziffer) {
							erfolgreich = true;
						}
					}
				}

			} catch (NumberFormatException e) {
				erfolgreich = false;
			}
//		TODO Damit Java wie Cobol läuft, muss jede BBNR größer als 8 Stellen abgeschnitten werden und die Folgezeichen ignoriert werden
		} else if (feldValue.length() > LAENGE) {

			try {
				// ueberpruefen, ob es eine Zahl ist.
				final String feldBbnr = feldValue.substring(0, LAENGE);
				final int kompletteZahl = Integer.parseInt(feldBbnr);
				final int erstenDreiZiffern = kompletteZahl / ERSTEN_DREI_ZIFFERN;

				if ((erstenDreiZiffern >= 1 && erstenDreiZiffern <= KLEINER_99) || erstenDreiZiffern > GROESSER_110) {
					final int pruefziffer = kompletteZahl % ZEHNERSTELLE;
					int errechnetePruefziffer = Pruefziffer.berechnePruefzifferDerBetriebsnummer(feldBbnr);

					if (errechnetePruefziffer == pruefziffer) {
						erfolgreich = true;
					}

					else {
						errechnetePruefziffer += PRUEFZIFFER_KONSTANTE;
						errechnetePruefziffer = errechnetePruefziffer % ZEHNERSTELLE;

						if (errechnetePruefziffer == pruefziffer) {
							erfolgreich = true;
						}
					}
				}

			} catch (NumberFormatException e) {
				erfolgreich = false;
			}
		}

		return erfolgreich;
	}

	

}
