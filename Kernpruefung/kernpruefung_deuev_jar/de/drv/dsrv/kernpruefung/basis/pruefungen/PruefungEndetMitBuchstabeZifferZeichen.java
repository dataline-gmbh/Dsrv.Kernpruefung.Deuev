package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert, ob der uebergebene Wert mit einem Buchstaben, Ziffer oder einem
 * Zeichen aus der Liste der erlaubten Zeichen endet.
 */
public class PruefungEndetMitBuchstabeZifferZeichen extends AbstractPruefung {

	private final transient PruefungEndetMitBuchstabe pruefungEndetMitBuchstabe;
	private final transient PruefungEndetMitZiffer pruefungEndetMitZiffer;
	private final transient PruefungEndetMitZeichen pruefungEndetMitZeichen;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            das zu pruefende Feld eines Bausteins
	 * @param erlaubteZeichen
	 *            Liste der erlaubten Zeichen
	 */
	public PruefungEndetMitBuchstabeZifferZeichen(final Feld<?> feld, final List<Character> erlaubteZeichen) {
		super(feld);
		pruefungEndetMitBuchstabe = new PruefungEndetMitBuchstabe(feld);
		pruefungEndetMitZiffer = new PruefungEndetMitZiffer(feld);
		pruefungEndetMitZeichen = new PruefungEndetMitZeichen(erlaubteZeichen, feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		return pruefungEndetMitBuchstabe.pruefe() || pruefungEndetMitZiffer.pruefe()
				|| pruefungEndetMitZeichen.pruefe();
	}
}
