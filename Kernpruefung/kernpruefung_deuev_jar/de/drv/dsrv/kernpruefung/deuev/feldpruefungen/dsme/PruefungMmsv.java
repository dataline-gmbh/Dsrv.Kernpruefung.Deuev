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
 * Pruefung fuer das Feld MMSV aus dem Baustein DSME.
 */
public class PruefungMmsv extends AbstractPruefungSchalterleisteFeld<FeldNameDSME, FehlerNummerDSME> {

	private static final List<String> VFMM_N = Arrays.asList("AGDEU", "WLTKV", "BATRV", "KTTRV", "BWTRV", "BZTRV",
			"PVTRV", "KSTRV", "KSTKV");
	private static final List<String> MUSS_N = Arrays.asList("N");
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
	 *            OpCode (Verfahrensmerkmale)
	 */
	public PruefungMmsv(final Feld<FeldNameDSME> feld, final Datensatz datensatz, final String vfmm) {
		super(feld, datensatz);
		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		addPruefungenOptional(FehlerNummerDSME.DSME330, FehlerNummerDSME.DSME937, BausteinName.DBSV);

		if (VFMM_N.contains(vfmm)) {
			final PruefungInList me332 = new PruefungInList(MUSS_N, getFeld(), true);
			addPruefung(me332, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME332));
		}
	}

}
