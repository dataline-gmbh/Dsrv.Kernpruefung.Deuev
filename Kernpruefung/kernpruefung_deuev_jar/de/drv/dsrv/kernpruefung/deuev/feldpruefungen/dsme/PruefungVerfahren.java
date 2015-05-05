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
 * Pruefung fuer das Verfahren.
 */
public class PruefungVerfahren extends AbstractFeldPruefung<FeldNameDSME, FehlerNummerDSME> {

	private static final List<String> KVNR_VFMM_ZULAESSIG = Arrays.asList("KVTRV", "RVTKV", "KVTWL", "WLTKV");
	private static final String KVNR = "KVNR";
	private final String vfmm;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            das zu pruefende Feld
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmal)
	 */
	public PruefungVerfahren(final Feld<FeldNameDSME> feld, final String vfmm) {
		super(feld);

		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final Feld<FeldNameDSME> feldVfmm = new Feld<FeldNameDSME>(vfmm);
		if (getFeld().getTrimmedValue().compareTo(KVNR) == 0) {
			final PruefungInList pruefungKvnr = new PruefungInList(KVNR_VFMM_ZULAESSIG, feldVfmm, true);
			addPruefung(pruefungKvnr, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME010));
		}
	}
}
