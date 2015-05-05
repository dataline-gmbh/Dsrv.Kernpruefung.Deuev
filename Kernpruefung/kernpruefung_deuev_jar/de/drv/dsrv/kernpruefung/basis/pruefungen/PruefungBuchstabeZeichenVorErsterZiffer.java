package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;
import de.drv.dsrv.kernpruefung.basis.utils.Zeichen;

/**
 * Validiert, ob vor einer Ziffernfolge, die nicht an erster Stelle steht, ein
 * Buchstabe oder ein Zeichen aus erlaubteZeichen vorkommt. Es wird nur bis
 * Fehlernummer: AN164
 */
public class PruefungBuchstabeZeichenVorErsterZiffer extends AbstractPruefung {

	private final List<Character> erlaubteZeichen;

	/**
	 * Konstruktor.
	 * 
	 * @param erlaubteZeichen
	 *            Liste der erlaubten Zeichen
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungBuchstabeZeichenVorErsterZiffer(final List<Character> erlaubteZeichen, Feld<? extends FeldName> feld) {
		super(feld);
		this.erlaubteZeichen = erlaubteZeichen;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = true;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();
			Character vorgaenger = null;

			if (feldValue.length() >= 2) {
//				TODO Damit Java wie Cobol läuft, wurde die Bezugstelle der Prüfung geändert und die Strasse mit Zifferfolge nur auf der ersten Stelle zulässt.
				final int ersteStelle = getErsteStelleZiffer(feldValue, 0);

//				TODO Damit Java wie Cobol läuft, wurde die Bezugstelle der Prüfung geändert und die Strasse mit Zifferfolge nur auf der ersten Stelle zulässt.
				if (ersteStelle > 0) {
					vorgaenger = feldValue.charAt(ersteStelle - 1);
					if (!(Zeichen.ALL_ALPHABETIC_CHARACTERS.contains(vorgaenger.toString()) || erlaubteZeichen
							.contains(vorgaenger))) {
						erfolgreich = false;
					}
				}
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >vor Ziffer steht ein Buchstabe oder ein Zeichen< "
							+ "Wert des Feldes fehlt.");
		}

		return erfolgreich;
	}
	
	/**
	 * Sucht die erste Ziffernfolge des Strings, die an erster Stelle
	 * beginnt.
	 * 
	 * @param feldValue
	 * @return
	 */
	private int getErsteStelleZiffer(final String feldValue, int start) {

		for (int i = start; i < feldValue.length(); ++i) {
			if (Character.isDigit(feldValue.charAt(i))) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Sucht die erste Ziffernfolge des Strings, die nicht an erster Stelle
	 * beginnt.
	 * 
	 * @param feldValue
	 * @return
	 */
	private int getNaechsteStelleZiffer(final String feldValue, int start) {
		boolean ersteStelle = true;

		for (int i = start; i < feldValue.length(); ++i) {
			if (Character.isDigit(feldValue.charAt(i)) && !ersteStelle) {
				return i;
			} else if (!Character.isDigit(feldValue.charAt(i)) && ersteStelle) {
				ersteStelle = false;
			}
		}

		return -1;
	}
}
