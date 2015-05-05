package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert, ob der uebergebene Wert eine Mindestlaenge besitzt und aus Zeichen
 * (Buchstaben, Ziffern und Sonderzeichen) besteht. Besteht der Wert nur aus
 * einem Zeichen, darf es nur der Grossbuchstabe sein. Fehlernummer: AN158.
 */
public class PruefungGrossbuchstabeOderMinZweiBuchstabenZiffernZeichen extends AbstractPruefung {

	private final transient PruefungMinLaenge pruefungLaenge;
	private final transient PruefungGrossbuchstabe pruefungGross;
	private final PruefungBuchstabenZiffernZeichen pruefungBuchstabenZiffernZeichen;

	/**
	 * Konstruktor.
	 * 
	 * @param erlaubteZeichen
	 *            Liste mit zulaessigen Zeichen
	 * @param minLaenge
	 *            Mindestlaenge
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungGrossbuchstabeOderMinZweiBuchstabenZiffernZeichen(final List<Character> erlaubteZeichen,
			final int minLaenge, final Feld<?> feld) {
		super(feld);
		pruefungLaenge = new PruefungMinLaenge(minLaenge, feld);
		pruefungGross = new PruefungGrossbuchstabe(feld);
		pruefungBuchstabenZiffernZeichen = new PruefungBuchstabenZiffernZeichen(erlaubteZeichen, feld);
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = true;
		if (!((pruefungLaenge.pruefe() && pruefungBuchstabenZiffernZeichen.pruefe()) || pruefungGross.pruefe())) {
			erfolgreich = false;
		}

		return erfolgreich;
	}
}
