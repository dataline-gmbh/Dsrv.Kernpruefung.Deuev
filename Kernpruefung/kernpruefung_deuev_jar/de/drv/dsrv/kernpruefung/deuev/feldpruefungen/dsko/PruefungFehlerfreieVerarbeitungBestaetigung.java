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
 * Pruefung fuer das Feld fehlerfreie Verarbeitungsbestaetigung aus dem Baustein
 * DSKO.
 */
public class PruefungFehlerfreieVerarbeitungBestaetigung extends AbstractFeldPruefung<FeldNameDSKO, FehlerNummerDSKO> {

	private static final List<String> ZULAESSIGE_WERTE = Arrays.asList("J", "N");

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungFehlerfreieVerarbeitungBestaetigung(final Feld<FeldNameDSKO> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer ko620a = new PruefungNichtLeer(getFeld());
		addPruefung(ko620a, new Fehler<FehlerNummerDSKO>(FehlerNummerDSKO.DSKO620));

		final PruefungInList ko620b = new PruefungInList(ZULAESSIGE_WERTE, getFeld(), true);
		addPruefung(ko620b, new Fehler<FehlerNummerDSKO>(FehlerNummerDSKO.DSKO620));
	}

}
