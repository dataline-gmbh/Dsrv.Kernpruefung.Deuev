package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;

/**
 * Validiert ob der uebergebene Wert mit dem String identisch ist, wobei die in
 * der Liste uebergebene Character (in dem String) ignoriert werden.
 * Fehlernummer DSME248 (Schalttabelle)
 */
public class PruefungAehnlicherString extends AbstractPruefung {

	private final String vergleich;
	private final List<Character> ueberspringen;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 * @param vergleich
	 *            String, mit dem der Wert verglichen werden soll
	 * @param ueberspringen
	 *            <code>Character</code>, die im Vergleichsstring vorkommen und
	 *            ueberpsringen werden.
	 */
	public PruefungAehnlicherString(final Feld<? extends FeldName> feld, final String vergleich,
			final List<Character> ueberspringen) {
		super(feld);

		this.vergleich = vergleich;
		this.ueberspringen = ueberspringen;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = true;
		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			if (feldValue.length() == vergleich.length()) {
				if (StringUtils.isNotEmpty(feldValue)) {
					for (int i = 0; i < feldValue.length(); ++i) {
						if (!ueberspringen.contains(vergleich.charAt(i)) && vergleich.charAt(i) != feldValue.charAt(i)) {
							erfolgreich = false;
						}
					}
				}
			} else {
				erfolgreich = false;
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >aehnlicher String< " + "Wert des Feldes fehlt.");
		}

		return erfolgreich;
	}

}
