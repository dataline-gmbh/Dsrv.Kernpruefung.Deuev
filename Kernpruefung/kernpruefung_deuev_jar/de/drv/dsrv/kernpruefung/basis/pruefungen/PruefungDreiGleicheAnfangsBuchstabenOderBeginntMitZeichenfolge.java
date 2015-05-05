package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert, ob der uebergebene Wert mit drei gleichen Buchstaben oder "III."
 * beginnt. Fehlernummer: AN181.
 */
public class PruefungDreiGleicheAnfangsBuchstabenOderBeginntMitZeichenfolge extends AbstractPruefung {

	private transient String zulaessigeBuchstabenKombination;

	/**
	 * Konstruktor.
	 * 
	 * @param zulaessigeBuchstabenKombination
	 *            zulaessige Kombination aus Buchstaben
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungDreiGleicheAnfangsBuchstabenOderBeginntMitZeichenfolge(final String zulaessigeBuchstabenKombination,
			final Feld<?> feld) {
		super(feld);
		this.zulaessigeBuchstabenKombination = zulaessigeBuchstabenKombination;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null && zulaessigeBuchstabenKombination != null) {
			final String feldValue = getFeld().getTrimmedValue();

			final PruefungDreiGleicheAnfangsBuchstaben gleicheBuchstaben = new PruefungDreiGleicheAnfangsBuchstaben(
					getFeld());
			if (gleicheBuchstaben.pruefe()) {

				// Pruefung, das keine 3 gleichen Buchstaben am Anfang, ...
				erfolgreich = true;
			} else {

				// ... ausgenommen III. und Punkt ist nicht das letzte Zeichen
				if (feldValue.length() > zulaessigeBuchstabenKombination.length()
						&& feldValue.startsWith(zulaessigeBuchstabenKombination)) {
					erfolgreich = true;
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