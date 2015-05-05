package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSBD;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSBD;

/**
 * Pruefung fuer das Feld Kennung aus dem Baustein DSBD.
 */
public class PruefungKennung extends AbstractFeldPruefung<FeldNameDSBD, FehlerNummerDSBD> {

	private static final List<String> ZULAESSIGE_VFMM = Arrays.asList("AGDEU", "KVDEU", "KVTRV", "RVTBA", "RVTKV",
			"KVTWL");

	private final String vfmm;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public PruefungKennung(final Feld<FeldNameDSBD> feld, final String vfmm) {
		super(feld);
		
		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		if (vfmm.length() > 0) {
			final Feld<FeldNameDSBD> feldVfmm = new Feld<FeldNameDSBD>(vfmm);
			final PruefungInList bd004 = new PruefungInList(ZULAESSIGE_VFMM, feldVfmm, true);
			addPruefung(bd004, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD004));
		}
	}

}
