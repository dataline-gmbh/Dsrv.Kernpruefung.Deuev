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
 * Pruefung fuer das Feld Kennung aus dem Baustein DSME.
 */
public class PruefungKennung extends AbstractFeldPruefung<FeldNameDSME, FehlerNummerDSME> {

	private static final List<String> ERLAUBTE_VFMM = Arrays.asList("AGDEU", "KVDEU", "AGTRV", "RVTAG", "WLTKV",
			"KVTWL", "KVTRV", "RVTKV", "BATRV", "RVTBA", "KTTRV", "RVTKT", "BWTRV", "RVTBW", "BZTRV", "RVTBZ", "PVTRV",
			"RVTPV", "KSTRV", "RVTKS", "KSTKV", "KVTKS", "BFTDS", "DSTBF", "ZFTRV", "RVTZF", "BDTKV", "KVTBD");
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
	public PruefungKennung(final Feld<FeldNameDSME> feld, final String vfmm) {
		super(feld);
		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final Feld<FeldNameDSME> feldVfmm = new Feld<FeldNameDSME>(vfmm);
		final PruefungInList me004 = new PruefungInList(ERLAUBTE_VFMM, feldVfmm, true);
		addPruefung(me004, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME004));
	}
}
