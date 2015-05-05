package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.ArrayList;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;

/**
 * Validiert, ob der uebergebene Wert mit einem aus der Liste der erlaubten
 * Werte uebereinstimmt.<br/>
 * Zudem kann auch validiert werden, ob der uebergebene Wert keinem Wert der
 * Liste entspricht. (Negativtest) <br/>
 * Fehlernummer: NA050, NA070, GB120, NA035.
 */
public class PruefungInList extends AbstractPruefung {

	private final transient List<String> erlaubteWerte;
	private final transient boolean caseSensitive;
	private final transient boolean wirdVerneint;

	/**
	 * Konstruktor.
	 * 
	 * @param erlaubteWerte
	 *            Liste mit zulaessigen Werten
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungInList(final List<String> erlaubteWerte, final Feld<?> feld) {
		super(feld);
		this.erlaubteWerte = erlaubteWerte;
		this.caseSensitive = false;
		this.wirdVerneint = false;
	}

	/**
	 * Konstruktor.
	 * 
	 * @param erlaubteWerte
	 *            Liste mit zulaessigen Werten
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 * @param caseSensitive
	 *            Pruefung case sensitive
	 */
	public PruefungInList(final List<String> erlaubteWerte, final Feld<?> feld, final boolean caseSensitive) {
		super(feld);
		this.erlaubteWerte = erlaubteWerte;
		this.caseSensitive = caseSensitive;
		this.wirdVerneint = false;
	}

	/**
	 * Konstruktor.
	 * 
	 * @param erlaubteWerte
	 *            Liste mit zulaessigen Werten
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 * @param caseSensitive
	 *            Pruefung case sensitive
	 * @param wirdVerneint
	 *            <code>true</code> bedeutet, dass der Feldwert nicht in der
	 *            Liste enthalten sein darf. <code>false</code> bedeutet, dass
	 *            der Feldwert in der Liste vorhanden sein darf.<br/>
	 *            Wird hier <code>true</code> gesetzt verhaelt sich die Pruefung
	 *            als waere diese mit einem <i>logischen NICHT/NOT</i>
	 *            verknuepft.
	 */
	public PruefungInList(final List<String> erlaubteWerte, final Feld<?> feld, final boolean caseSensitive,
			final boolean wirdVerneint) {
		super(feld);
		this.erlaubteWerte = erlaubteWerte;
		this.caseSensitive = caseSensitive;
		this.wirdVerneint = wirdVerneint;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null && erlaubteWerte != null) {
			final String feldValue = getFeld().getTrimmedValue();

			if (feldValue != null && !caseSensitive) {
				final List<String> erlaubteWerteUpperCase = new ArrayList<String>();

				for (final String wert : erlaubteWerte) {
					erlaubteWerteUpperCase.add(wert.toUpperCase());
				}

				if (erlaubteWerteUpperCase.contains(feldValue.toUpperCase())) {
					erfolgreich = true;
				}
			} else {
				if (erlaubteWerte.contains(feldValue)) {
					erfolgreich = true;
				}
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Wert stimmt mit einem aus der Liste erlaubte Werte ueberein< "
							+ "Wert des Feldes fehlt oder nicht initialisierte Liste der erlaubten Werte.");
		}

		return erfolgreich ^ wirdVerneint;
	}
}
