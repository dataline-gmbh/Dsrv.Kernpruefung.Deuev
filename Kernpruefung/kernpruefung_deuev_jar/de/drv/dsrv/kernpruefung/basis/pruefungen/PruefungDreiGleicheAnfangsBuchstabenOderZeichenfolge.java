package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert, ob der uebergebene Wert mit drei gleichen Buchstaben oder "III."
 * oder "MMM-Str" beginnt. Fehlernummer: AN151.
 */
public class PruefungDreiGleicheAnfangsBuchstabenOderZeichenfolge extends AbstractPruefung {

	private transient List<String> zulaessigeBuchstabenKombination;

	/**
	 * Konstruktor.
	 * 
	 * @param zulaessigeBuchstabenKombination
	 *            zulaessige Kombination aus Buchstaben
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungDreiGleicheAnfangsBuchstabenOderZeichenfolge(final List<String> zulaessigeBuchstabenKombination,
			final Feld<?> feld) {
		super(feld);
		this.zulaessigeBuchstabenKombination = zulaessigeBuchstabenKombination;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null && zulaessigeBuchstabenKombination != null) {
			final String feldValue = getFeld().getTrimmedValue();

			if (zulaessigeBuchstabenKombination.size() == 2) {
				final PruefungDreiGleicheAnfangsBuchstabenOderBeginntMitZeichenfolge gleicheBuchstaben = new PruefungDreiGleicheAnfangsBuchstabenOderBeginntMitZeichenfolge(
						zulaessigeBuchstabenKombination.get(0), getFeld());
				if (gleicheBuchstaben.pruefe()) {

					// Pruefung, das keine 3 gleichen Buchstaben am Anfang, oder
					// III. (und Punkt ist nicht das letzte Zeichen) ...
					erfolgreich = true;
				} else {
					// ... ausgenommen MMM-Str
					if (feldValue.startsWith(zulaessigeBuchstabenKombination.get(1))) {
						erfolgreich = true;
					}

				}
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Wert beginnt mit drei gleichen Buchstaben oder III.< "
							+ "Wert des Feldes fehlt oder nicht initialisierte Liste der zulaessigen Anfangsbuchstaben.");
		}
		return erfolgreich;
	}
}