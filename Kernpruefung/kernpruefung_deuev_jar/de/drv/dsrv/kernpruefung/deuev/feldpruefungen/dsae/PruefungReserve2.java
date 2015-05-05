package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSAE;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;

/**
 * Pruefung fuer das Feld Reserve2 aus dem Baustein DSAE.
 */
public class PruefungReserve2 extends AbstractFeldPruefung<FeldNameDSAE, FehlerNummerDSAE> {

	private static final List<String> ERLAUBTE_ZEICHEN = Arrays	.asList("W");
	
	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungReserve2(Feld<FeldNameDSAE> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		if (getFeld().getTrimmedValue().length() == 1) {
			final PruefungInList ae420 = new PruefungInList(ERLAUBTE_ZEICHEN, getFeld(), true);
			addPruefung(ae420, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE420));
		} else {
			final PruefungLaenge ae420 = new PruefungLaenge(0, getFeld());
			addPruefung(ae420, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE420));
		}
		
		
	}
}
