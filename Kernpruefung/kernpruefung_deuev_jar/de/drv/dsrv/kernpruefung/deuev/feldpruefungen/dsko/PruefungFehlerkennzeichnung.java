package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsko;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSKO;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSKO;

/**
 * Pruefung fuer das Feld Fehlerkennzeichnung aus dem Baustein DSKO.
 */
public class PruefungFehlerkennzeichnung extends AbstractFeldPruefung<FeldNameDSKO, FehlerNummerDSKO> {

	private static final List<String> ZULAESSIGE_WERTE = Arrays.asList("0", "1");

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungFehlerkennzeichnung(final Feld<FeldNameDSKO> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch ko060 = new PruefungNumerisch(getFeld(), true);
		addPruefung(ko060, new Fehler<FehlerNummerDSKO>(FehlerNummerDSKO.DSKO060));

		final PruefungInList ko062 = new PruefungInList(ZULAESSIGE_WERTE, getFeld());
		addPruefung(ko062, new Fehler<FehlerNummerDSKO>(FehlerNummerDSKO.DSKO062));
	}

}
