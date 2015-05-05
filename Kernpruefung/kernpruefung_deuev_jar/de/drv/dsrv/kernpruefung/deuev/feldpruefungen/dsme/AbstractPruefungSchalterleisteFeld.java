package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Datensatz;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBausteinNichtVorhanden;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBausteinVorhanden;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;

/**
 * Definiert Methoden, die fuer die Pruefung eines Felds in der Schalterleiste
 * verwendet werden.
 * 
 * @param <T>
 *            {@link FeldName}
 * @param <E>
 *            {@link FehlerNummer}
 */
public abstract class AbstractPruefungSchalterleisteFeld<T extends FeldName, E extends FehlerNummer> extends
		AbstractFeldPruefung<T, E> {

	static final String WERT_N = "N";
	static final String WERT_J = "J";
	static final List<String> ZULAESSIGE_WERTE_J_N = Arrays.asList(WERT_J, WERT_N);
	static final List<String> ZULAESSIGE_WERTE_N = Arrays.asList(WERT_N);
	static final List<String> ZULAESSIGE_WERTE_J = Arrays.asList(WERT_J);

	private final Datensatz datensatz;

	/**
	 * Konstrukor.
	 * 
	 * @param feld
	 *            das zu pruefende Feld
	 * @param datensatz
	 *            komplette Datensatz
	 */
	public AbstractPruefungSchalterleisteFeld(final Feld<T> feld, final Datensatz datensatz) {
		super(feld);
		this.datensatz = datensatz;
	}

	/**
	 * Fuegt der Liste der Pruefungen weitere Pruefung hinzu: Pruefung, ob der
	 * Wert des Feldes den Wert <code>N</code> besitzt.
	 * 
	 * @param unzulaessigerWert
	 *            Fehlernummer fuer den Fall, dass der Wert des Felds
	 *            unzulaessig ist (d.h. nicht <code>N</code>)
	 */
	protected void addPruefungenOptional(final E unzulaessigerWert) {
		final PruefungInList pruefungInList = new PruefungInList(ZULAESSIGE_WERTE_N, this.getFeld(), true);
		this.addPruefung(pruefungInList, new Fehler<E>(unzulaessigerWert));
	}

	/**
	 * Fuegt der Liste der Pruefungen weitere Pruefungen hinzu: Pruefung, ob der
	 * Wert des Feldes die Werte <code>J</code> oder <code>N</code> besitzt, und
	 * Pruefung auf Vorhandensein des zugehoerigen Bausteins, falls der Wert des
	 * Feldes <code>J</code> ist.
	 * 
	 * @param unzulaessigerWert
	 *            Fehlernummer fuer den Fall, dass der Wert des Felds
	 *            unzulaessig ist (d.h. nicht <code>J</code> oder <code>N</code>
	 *            )
	 * @param nichtVorhanden
	 *            Fehlernummer fuer den Fall, dass der Wert des Fehldes
	 *            <code>J</code> ist und der zugehoerige Baustein nicht
	 *            vorhanden ist
	 * @param bausteinName
	 *            Name des Bausteins
	 */
	protected void addPruefungenOptional(final E unzulaessigerWert, final E nichtVorhanden, final BausteinName bausteinName) {

		final PruefungNichtLeer pruefungNichtLeer = new PruefungNichtLeer(this.getFeld());
		this.addPruefung(pruefungNichtLeer, new Fehler<E>(unzulaessigerWert));

		final PruefungInList pruefungInList = new PruefungInList(ZULAESSIGE_WERTE_J_N, this.getFeld(), true);
		this.addPruefung(pruefungInList, new Fehler<E>(unzulaessigerWert));

		if (this.getFeld().getTrimmedValue().compareTo("J") == 0) {
			final PruefungBausteinVorhanden pruefungBausteinVorhanden = new PruefungBausteinVorhanden(this.getFeld(),
					this.datensatz, bausteinName);
			this.addPruefung(pruefungBausteinVorhanden, new Fehler<E>(nichtVorhanden));
		} else {
			final PruefungBausteinNichtVorhanden pruefungBausteinNichtVorhanden = new PruefungBausteinNichtVorhanden(
					this.getFeld(), this.datensatz, bausteinName);
			this.addPruefung(pruefungBausteinNichtVorhanden, new Fehler<E>(nichtVorhanden));
		}
	}

	/**
	 * Fuegt der Liste der Pruefungen weitere Pruefungen hinzu: Pruefung, ob der
	 * Wert des Feldes den Wert <code>J</code> besitzt, und Pruefung auf
	 * Vorhandensein des zugehoerigen Bausteins.
	 * 
	 * @param unzulaessigerWert
	 *            Fehlernummer fuer den Fall, dass der Wert des Felds
	 *            unzulaessig ist (d.h. nicht <code>J</code>) und/oder der
	 *            zugehoerige Baustein nicht vorhanden ist
	 * @param bausteinName
	 *            Name des Bausteins
	 */
	protected void addPruefungenZwingend(final E unzulaessigerWert, final BausteinName bausteinName) {

		final PruefungInList pruefungInList = new PruefungInList(ZULAESSIGE_WERTE_J, this.getFeld(), true);
		this.addPruefung(pruefungInList, new Fehler<E>(unzulaessigerWert));

		final PruefungBausteinVorhanden pruefungBausteinVorhanden = new PruefungBausteinVorhanden(this.getFeld(),
				this.datensatz, bausteinName);
		this.addPruefung(pruefungBausteinVorhanden, new Fehler<E>(unzulaessigerWert));
	}
}
