package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsko;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSKO;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSKO;

/**
 * Pruefung fuer das Feld Ort Betrieb aus dem Baustein DSKO.
 */
public class PruefungOrtBetrieb extends AbstractFeldPruefung<FeldNameDSKO, FehlerNummerDSKO> {

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungOrtBetrieb(final Feld<FeldNameDSKO> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer ko540 = new PruefungNichtLeer(getFeld());
		addPruefung(ko540, new Fehler<FehlerNummerDSKO>(FehlerNummerDSKO.DSKO540));
	}

}
