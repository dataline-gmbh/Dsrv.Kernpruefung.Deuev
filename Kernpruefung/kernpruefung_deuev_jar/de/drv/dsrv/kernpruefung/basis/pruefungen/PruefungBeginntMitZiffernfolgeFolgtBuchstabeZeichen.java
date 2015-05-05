package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.basis.utils.Zeichen;

/**
 * Ueberprueft, ob der uebergebene Wert mit einer Ziffernfolge beginnt. Sollte
 * dies zutreffen wird validiert, ob ein Buchstabe oder ein Zeichen aus der
 * Liste erlaubteZeichen darauf folgt. Fehlernummer: AN162.
 */
public class PruefungBeginntMitZiffernfolgeFolgtBuchstabeZeichen extends AbstractPruefung {

	private final List<Character> erlaubteZeichen;
	private final transient PruefungBeginntMitZiffer pruefungBeginntZiffer;

	/**
	 * Konstruktor.
	 * 
	 * @param erlaubteZeichen
	 *            Liste der erlaubten Zeichen
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungBeginntMitZiffernfolgeFolgtBuchstabeZeichen(final List<Character> erlaubteZeichen,
			Feld<? extends FeldName> feld) {
		super(feld);

		this.erlaubteZeichen = erlaubteZeichen;
		pruefungBeginntZiffer = new PruefungBeginntMitZiffer(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			if (StringUtils.isNotEmpty(getFeld().getTrimmedValue())) {
				// Sollte der Wert nicht mit einer Ziffer beginnen ist ein
				// weiteres
				// Ueberpruefen hinfaellig.
				try {
					if (!pruefungBeginntZiffer.pruefe()) {
						erfolgreich = true;
					} else {
						final String feldValue = getFeld().getTrimmedValue();
						final Character zeichen = getErstesZeichenNachZiffernfolge(feldValue);
						if (zeichen != null
								&& (Zeichen.ALL_ALPHABETIC_CHARACTERS.contains(zeichen.toString()) || erlaubteZeichen
										.contains(zeichen))) {
							erfolgreich = true;
						}
					}

				} catch (final UngueltigeDatenException e) {
					// Richtige Nachricht einfuegen.
					throw new UngueltigeDatenException(
							"Fehler in der Pruefung >Wert beginnt mit Ziffernfolge, dem ein Buchstabe oder ein erlaubtes Zeichen folgt< "
									+ "Wert des Feldes fehlt.");
				}
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Wert beginnt mit Ziffernfolge, dem ein Buchstabe oder ein erlaubtes Zeichen folgt< "
							+ "Wert des Feldes fehlt.");
		}

		return erfolgreich;
	}

	/**
	 * Sucht das erste Zeichen nach der Ziffernfolge.
	 * 
	 * @param feldValue
	 *            der String mit startender Ziffernfolge
	 * @return Character das erste Zeichen, das keine Ziffer mehr ist.
	 */
	private Character getErstesZeichenNachZiffernfolge(final String feldValue) {
		final int laenge = feldValue.length();
		Character zeichenReturn = null;

		for (int i = 0; i < laenge; ++i) {
			final Character zeichen = feldValue.charAt(i);
			if (!Character.isDigit(zeichen)) {
				zeichenReturn = zeichen;
				break;
			}
		}

		return zeichenReturn;
	}

}
