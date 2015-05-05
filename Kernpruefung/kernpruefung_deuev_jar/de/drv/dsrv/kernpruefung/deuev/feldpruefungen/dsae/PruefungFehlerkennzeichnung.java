package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInterval;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSAE;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;

/**
 * Pruefung fuer das Feld Fehlerkennzeichnung aus dem Baustein DSAE.
 */
public class PruefungFehlerkennzeichnung extends AbstractFeldPruefung<FeldNameDSAE, FehlerNummerDSAE> {

	private static final int MIN_WERT = 0;
	private static final int MAX_WERT = 2;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld
	 */
	public PruefungFehlerkennzeichnung(final Feld<FeldNameDSAE> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch ae060 = new PruefungNumerisch(getFeld(), true);
		addPruefung(ae060, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE060));

		final PruefungInterval ae062 = new PruefungInterval(MIN_WERT, MAX_WERT, getFeld());
		addPruefung(ae062, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE062));
	}
}
