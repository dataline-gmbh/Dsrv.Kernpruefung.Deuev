package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.utils.Sonderzeichen;
import de.drv.dsrv.kernpruefung.basis.utils.Zeichen;

/**
 * Validiert, ob der uebergebene Wert nur aus Buchstaben, Ziffern, Sonderzeichen
 * oder einem Punkt besteht. Fehlernummer: NA/GB014.
 */
public class PruefungBuchstabenZiffernZeichenPunkt extends AbstractPruefung {

	private final transient List<Character> erlaubteZeichen;
	private final boolean zweiPunkteErlaubt;

	public PruefungBuchstabenZiffernZeichenPunkt(final List<Character> erlaubteZeichen, final Feld<?> feld) {
		this(erlaubteZeichen, feld, true);
	}

	/**
	 * Konstruktor.
	 * 
	 * @param erlaubteZeichen
	 *            Liste mit zulaessigen Zeichen
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 * @param zweiPunkteErlaubt
	 *            <code>true</code>, falls zwei und mehr Punkte erlaubt sind,
	 *            <code>false</code> wenn max. ein Punkt erlaubt ist
	 */
	public PruefungBuchstabenZiffernZeichenPunkt(final List<Character> erlaubteZeichen, final Feld<?> feld,
			final boolean zweiPunkteErlaubt) {
		super(feld);
		this.erlaubteZeichen = erlaubteZeichen;
		this.zweiPunkteErlaubt = zweiPunkteErlaubt;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = true;

		if (getFeld() != null && getFeld().getTrimmedValue() != null && erlaubteZeichen != null) {
			final String feldValue = getFeld().getTrimmedValue();

			Character zeichen = null;
			int j = 0;
			for (int i = 0; i < feldValue.length(); i++) {
				zeichen = feldValue.charAt(i);
				if (!(Character.isDigit(zeichen) || Zeichen.ALL_ALPHABETIC_CHARACTERS.contains(zeichen.toString()) || erlaubteZeichen
						.contains(zeichen))) {
					erfolgreich = false;
					break;
				}

				if (!zweiPunkteErlaubt && zeichen == Sonderzeichen.FULL_STOP) {
					j = j + 1;
				}
				if (!zweiPunkteErlaubt && j >= 2) {
					erfolgreich = false;
					break;
				}
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Wert besteht nur aus Buchstaben, Ziffern, Sonderzeichen oder einem Punkt< "
							+ "Wert des Feldes fehlt oder nicht initialisierte Liste der erlaubten Werte.");
		}
		return erfolgreich;
	}
}
