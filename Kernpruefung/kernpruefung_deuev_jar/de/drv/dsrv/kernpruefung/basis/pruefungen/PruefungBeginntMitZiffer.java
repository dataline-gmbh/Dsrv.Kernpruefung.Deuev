package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;

/**
 * Validiert, ob das erste Zeichen in dem uebergebenen Wert eine Ziffer ist.
 * Fehlernummer: AN185, AN176.
 */
public class PruefungBeginntMitZiffer extends AbstractPruefung {

	public PruefungBeginntMitZiffer(final Feld<?> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			if (StringUtils.isNotEmpty(feldValue)) {
				final Character erstesZeichen = feldValue.charAt(0);
				if (Character.isDigit(erstesZeichen)) {
					erfolgreich = true;
				}
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Wert beginnt mit Ziffer< "
					+ "Wert des Feldes fehlt.");
		}

		return erfolgreich;
	}

}
