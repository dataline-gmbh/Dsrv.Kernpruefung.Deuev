package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;

/**
 * Validiert, ob der uebergebene Wert mit einem Zeichen beginnt aus der Liste
 * erlaubteZeichen. Fehlernummer: AN036.
 */
public class PruefungBeginntMitZeichenUndEndetMitLeer extends AbstractPruefung {

	private final transient List<Character> erlaubteZeichen;

	/**
	 * Konstruktor.
	 * 
	 * @param erlaubteZeichen
	 *            Liste mit zulaessigen Zeichen
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungBeginntMitZeichenUndEndetMitLeer(final List<Character> erlaubteZeichen, final Feld<?> feld) {
		super(feld);
		this.erlaubteZeichen = erlaubteZeichen;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			if (StringUtils.isNotEmpty(feldValue) && erlaubteZeichen != null && feldValue.length() == 1) {
				final Character erstesZeichen = feldValue.charAt(0);
				if (erlaubteZeichen.contains(erstesZeichen)) {
					erfolgreich = true;
				}
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Wert beginnt mit einem Zeichen aus der Liste mit erlaubten Werten< "
							+ "Wert des Feldes fehlt oder nicht initialisierte Liste der erlaubten Werte.");
		}
		return erfolgreich;
	}
}
