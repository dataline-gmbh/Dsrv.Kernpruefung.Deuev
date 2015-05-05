package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld MMUE aus dem Baustein DSME.
 */
public class PruefungMmue extends AbstractFeldPruefung<FeldNameDSME, FehlerNummerDSME> {

	private static final List<String> ERLAUBTE_WERTE = Arrays.asList("J", "N");

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungMmue(final Feld<FeldNameDSME> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		// length == 0 ist Grundstellung (erlaubt)
		if (getFeld().getTrimmedValue().length() > 0) {
			final PruefungInList me500 = new PruefungInList(ERLAUBTE_WERTE, getFeld(), true);
			addPruefung(me500, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME500));
		}
	}

}
