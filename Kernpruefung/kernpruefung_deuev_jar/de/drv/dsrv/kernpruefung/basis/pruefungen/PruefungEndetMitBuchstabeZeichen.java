package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert, ob auf der letzten Stelle des uebergebenen Wertes ein Buchstabe
 * oder ein Zeichen aus der Liste erlaubteZeichen steht. Fehlernummer: NA089.
 */
public class PruefungEndetMitBuchstabeZeichen extends AbstractPruefung {

	private final transient PruefungEndetMitBuchstabe pruefungEndetMitBuchstabe;
	private final transient PruefungEndetMitZeichen pruefungEndetMitZeichen;

	/**
	 * Konstruktor.
	 * 
	 * @param erlaubteZeichen
	 *            Liste mit zulaessigen Zeichen
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungEndetMitBuchstabeZeichen(final Feld<?> feld, final List<Character> erlaubteZeichen) {
		super(feld);
		pruefungEndetMitBuchstabe = new PruefungEndetMitBuchstabe(feld);
		pruefungEndetMitZeichen = new PruefungEndetMitZeichen(erlaubteZeichen, feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		return pruefungEndetMitBuchstabe.pruefe() || pruefungEndetMitZeichen.pruefe();
	}
}
