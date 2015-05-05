package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSAE;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;

/**
 * Pruefung fuer das Feld Versionsnummer des Kernpruefungsprogramm aus dem
 * Baustein DSAE.
 */
public class PruefungVersionsnummerKP extends AbstractFeldPruefung<FeldNameDSAE, FehlerNummerDSAE> {

	private static final int VFMM_TO_START = 2;
	private static final int VFMM_TO_ENDE = 5;
	private static final int VFMM_LAENGE = 5;
	private final String vfmm;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmal)
	 */
	public PruefungVersionsnummerKP(final Feld<FeldNameDSAE> feld, final String vfmm) {
		super(feld);

		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		// length == 0 ist Grundstellung (erlaubt)
		if (getFeld().getTrimmedValue().length() > 0) {
			final PruefungNumerisch ae550 = new PruefungNumerisch(getFeld(), true);
			addPruefung(ae550, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE550));
		}

		String vfmmTmp;
		if (vfmm.length() == VFMM_LAENGE) {
			vfmmTmp = vfmm;
		} else {
			vfmmTmp = "XXXXX";
		}

		if (vfmmTmp.substring(VFMM_TO_START, VFMM_TO_ENDE).compareTo("TRV") != 0) {
			final PruefungLaenge ae555 = new PruefungLaenge(0, getFeld());
			addPruefung(ae555, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE555));
		}
	}
}
