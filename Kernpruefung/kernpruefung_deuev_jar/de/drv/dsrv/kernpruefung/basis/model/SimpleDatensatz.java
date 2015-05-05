package de.drv.dsrv.kernpruefung.basis.model;

import java.util.List;


/**
 * Repraesentiert Daten eines Datensatzes.
 */
public class SimpleDatensatz implements Datensatz {

	private final transient List<Baustein<? extends FeldName>> bausteine;

	/**
	 * Konstruktor.
	 * 
	 * @param bausteine
	 *            Liste der Bausteine, die den Datensatz bilden
	 */
	public SimpleDatensatz(final List<Baustein<? extends FeldName>> bausteine) {
		this.bausteine = bausteine;
	}

	@Override
	public Baustein<? extends FeldName> getBaustein(final BausteinName name) {
		Baustein<? extends FeldName> result = null;
		if (name != null) {
			for (final Baustein<? extends FeldName> baustein : bausteine) {
				if (name.equals(baustein.getName())) {
					result = baustein;
					break;
				}
			}
		}
		return result;
	}

	@Override
	public boolean containsBaustein(final BausteinName name) {
		boolean result = false;
		if (name != null) {
			for (final Baustein<? extends FeldName> baustein : bausteine) {
				if (name.equals(baustein.getName())) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

	@Override
	public List<Baustein<? extends FeldName>> getBausteine() {
		return bausteine;
	}

	@Override
	public String toString() {
		return "SimpleDatensatz [bausteine=" + bausteine + "]";
	}
}
