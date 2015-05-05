package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;

/**
 * Validiert, ob das letzte Zeichen in dem uebergebenen Wert eine Ziffer ist.
 * Fehlernummer: AN176.
 */
public class PruefungEndetMitZiffer extends AbstractPruefung {

	protected PruefungEndetMitZiffer(final Feld<?> feld) {
		super(feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			if (StringUtils.isNotEmpty(feldValue)) {
				final Character letztesZeichen = feldValue.charAt(feldValue.length() - 1);
				if (Character.isDigit(letztesZeichen)) {
					erfolgreich = true;
				}
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Letztes Zeichen im Wert ist eine Ziffer< "
					+ "Wert des Feldes fehlt.");
		}

		return erfolgreich;
	}

}
