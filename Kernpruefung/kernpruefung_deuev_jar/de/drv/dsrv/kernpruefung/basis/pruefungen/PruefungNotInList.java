package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert, ob der uebergebene Wert mit einem aus der Liste unerlaubteWerte
 * uebereinstimmt.
 */
public class PruefungNotInList extends AbstractPruefung {

	private final List<String> unerlaubteWerte;
	private transient boolean caseSensitive;

	/**
	 * Konstruktor.
	 * 
	 * @param unerlaubteWerte
	 *            Liste mit unzulaessigen Werten
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungNotInList(final List<String> unerlaubteWerte, final Feld<?> feld) {
		super(feld);
		this.unerlaubteWerte = unerlaubteWerte;
		this.caseSensitive = false;
	}

	/**
	 * Konstruktor.
	 * 
	 * @param unerlaubteWerte
	 *            Liste mit unzulaessigen Werten
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 * @param caseSensitive
	 *            Pruefung case sensitive
	 */
	public PruefungNotInList(final List<String> unerlaubteWerte, final Feld<?> feld, final boolean caseSensitive) {
		super(feld);
		this.unerlaubteWerte = unerlaubteWerte;
		this.caseSensitive = caseSensitive;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		final PruefungInList pruefungInList = new PruefungInList(unerlaubteWerte, getFeld(), caseSensitive);
		return !pruefungInList.pruefe();
	}

}
