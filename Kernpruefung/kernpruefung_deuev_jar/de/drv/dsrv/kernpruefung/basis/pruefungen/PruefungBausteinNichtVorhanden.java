package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Datensatz;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Validiert, ob der Baustein mit dem angegebenen Namen im Datensatz nicht
 * vorhanden und dies in Verbindung mit dem angegebenen Feld korrekt ist. <br>
 * <br>
 * Die Pruefung ist nur erfolgreich, wenn der Wert des Feldes den Wert
 * <code>N</code> besitzt und der angegebene Baustein nicht im Datensatz
 * vorhanden ist. <br>
 * Die Pruefung schlaegt fehl, bei Feldwerten ungleich <code>N</code> oder wenn
 * der Feldwert gleich <code>N</code> ist, der Baustein aber trotzdem vorhanden
 * ist.
 */
public class PruefungBausteinNichtVorhanden extends AbstractPruefung {

	private static final String ZULAESSIGER_WERT_N = "N";

	private final BausteinName bausteinName;
	private final Datensatz datensatz;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            das zu pruefende Feld
	 * @param datensatz
	 * @param bausteinName
	 */
	public PruefungBausteinNichtVorhanden(final Feld<? extends FeldName> feld, final Datensatz datensatz,
			final BausteinName bausteinName) {
		super(feld);

		this.datensatz = datensatz;
		this.bausteinName = bausteinName;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = false;

		if (this.getFeld() != null && this.getFeld().getTrimmedValue() != null) {

			// Geprueft wird nur dann, wenn der Wert J ist
			if (ZULAESSIGER_WERT_N.equals(this.getFeld().getTrimmedValue())
					&& !datensatz.containsBaustein(bausteinName)) {
				erfolgreich = true;
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Baustein vorhanden< "
					+ "Wert des Feldes fehlt.");
		}

		return erfolgreich;
	}
}
