package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Datensatz;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld MMRG aus dem Baustein DSME.
 */
public class PruefungMmrg extends AbstractPruefungSchalterleisteFeld<FeldNameDSME, FehlerNummerDSME> {

	private static final List<String> VFMM_J = Arrays.asList("RVTKV", "WLTKV");
	private final String vfmm;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param datensatz
	 *            Datensatz DSME
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmal)
	 */
	public PruefungMmrg(final Feld<FeldNameDSME> feld, final Datensatz datensatz, final String vfmm) {
		super(feld, datensatz);

		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		addPruefungenOptional(FehlerNummerDSME.DSME350, FehlerNummerDSME.DSME939, BausteinName.DBRG);

		if (getFeld().getTrimmedValue().compareTo("J") == 0) {
			final Feld<FeldNameDSME> feldVfmm = new Feld<FeldNameDSME>(vfmm);
			final PruefungInList me352 = new PruefungInList(VFMM_J, feldVfmm, true);
			addPruefung(me352, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME352));
		}
	}
}