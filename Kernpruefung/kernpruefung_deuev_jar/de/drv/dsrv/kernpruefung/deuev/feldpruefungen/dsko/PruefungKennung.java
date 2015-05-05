package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsko;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSKO;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSKO;

/**
 * Pruefung fuer das Feld Kennung aus dem Baustein DSKO.
 */
public class PruefungKennung extends AbstractFeldPruefung<FeldNameDSKO, FehlerNummerDSKO> {

	private static final List<String> ZULAESSIGE_VFMM = Arrays.asList("AGDEU", "KVDEU", "AGTRV", "RVTAG", "WLTKV",
			"KVTWL", "KVTRV", "RVTKV");
	private static final int LAENGE_VFMM = 5;
	private final String vfmm;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public PruefungKennung(final Feld<FeldNameDSKO> feld, final String vfmm) {
		super(feld);

		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		if (vfmm.length() == LAENGE_VFMM) {
			final Feld<FeldNameDSKO> feldVfmm = new Feld<FeldNameDSKO>(vfmm);
			final PruefungInList ko004 = new PruefungInList(ZULAESSIGE_VFMM, feldVfmm, true);
			addPruefung(ko004, new Fehler<FehlerNummerDSKO>(FehlerNummerDSKO.DSKO004));
		}
	}

}
