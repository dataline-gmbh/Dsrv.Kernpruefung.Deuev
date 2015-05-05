package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInterval;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld Fehlerkennzeichnung aus dem Baustein DSME.
 */
public class PruefungFehlerkennzeichnung extends AbstractFeldPruefung<FeldNameDSME, FehlerNummerDSME> {

	private static final int MIN_WERT = 0;
	private static final int MAX_WERT = 4;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungFehlerkennzeichnung(final Feld<FeldNameDSME> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch me060 = new PruefungNumerisch(getFeld(), true);
		addPruefung(me060, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME060));

		final PruefungInterval me062 = new PruefungInterval(MIN_WERT, MAX_WERT, getFeld());
		addPruefung(me062, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME062));
	}
	
}
