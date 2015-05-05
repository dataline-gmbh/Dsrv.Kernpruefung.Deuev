package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsko;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSKO;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSKO;

/**
 * Pruefung fuer das Feld Anrede Ansprechpartner aus dem Baustein DSKO.
 */
public class PruefungAnredeAnsprechpartner extends AbstractFeldPruefung<FeldNameDSKO, FehlerNummerDSKO> {

	private static final List<String> ZULAESSIGE_WERTE = Arrays.asList("M", "W");

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungAnredeAnsprechpartner(final Feld<FeldNameDSKO> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer ko570a = new PruefungNichtLeer(getFeld());
		addPruefung(ko570a, new Fehler<FehlerNummerDSKO>(FehlerNummerDSKO.DSKO570));

		final PruefungInList ko570b = new PruefungInList(ZULAESSIGE_WERTE, getFeld(), true);
		addPruefung(ko570b, new Fehler<FehlerNummerDSKO>(FehlerNummerDSKO.DSKO570));
	}

}
