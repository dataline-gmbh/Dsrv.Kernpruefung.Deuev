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
 * Pruefung fuer das Feld MMVR aus dem Baustein DSME.
 */
public class PruefungMmvr extends AbstractPruefungSchalterleisteFeld<FeldNameDSME, FehlerNummerDSME> {

	private static final List<String> MUSS_N = Arrays.asList("N");
	private static final List<String> MUSS_J = Arrays.asList("J");
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
	public PruefungMmvr(final Feld<FeldNameDSME> feld, final Datensatz datensatz, final String vfmm) {
		super(feld, datensatz);

		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		addPruefungenOptional(FehlerNummerDSME.DSME340, FehlerNummerDSME.DSME938, BausteinName.DBVR);

		if ((vfmm.compareTo("AGDEU") == 0) || (vfmm.compareTo("KSTKV") == 0)) {
			final PruefungInList me342 = new PruefungInList(MUSS_N, getFeld(), true);
			addPruefung(me342, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME342));
		}

		if ((vfmm.compareTo("BATRV") == 0) || (vfmm.compareTo("KTTRV") == 0)) {
			final PruefungInList me344 = new PruefungInList(MUSS_J, getFeld(), true);
			addPruefung(me344, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME344));
		}
	}
}
