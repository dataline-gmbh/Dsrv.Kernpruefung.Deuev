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
import de.drv.dsrv.kernpruefung.deuev.pruefungen.PruefungBetriebsnummer;

/**
 * Pruefung fuer das Feld Betriebsnummer Empfaenger aus dem Baustein DSME.
 */
public class PruefungBetriebsnummerEmpfaenger extends AbstractFeldPruefung<FeldNameDSME, FehlerNummerDSME> {

	private static final List<String> VFMM_66667777 = Arrays.asList("KVTWL", "KVTRV", "KTTRV", "BATRV", "BWTRV", "BZTRV",
			"AGTRV");

	private static final List<String> BBNR_EMPF_66667777 = Arrays.asList("66667777");
	private static final List<String> BBNR_EMPF_90209055 = Arrays.asList("90209055");
	private static final List<String> BBNR_EMPF_76641777 = Arrays.asList("76641777");

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
	public PruefungBetriebsnummerEmpfaenger(final Feld<FeldNameDSME> feld, final String vfmm) {
		super(feld);

		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungBetriebsnummer me030 = new PruefungBetriebsnummer(getFeld());
		addPruefung(me030, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME030));

		if (VFMM_66667777.contains(vfmm)) {
			final PruefungInList me032 = new PruefungInList(BBNR_EMPF_66667777, getFeld());
			addPruefung(me032, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME032));
		}

		else if (vfmm.compareTo("ZFTRV") == 0) {
			final PruefungInList me032 = new PruefungInList(BBNR_EMPF_90209055, getFeld());
			addPruefung(me032, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME032));
		}

		else if (vfmm.compareTo("RVTBA") == 0) {
			final PruefungInList me032 = new PruefungInList(BBNR_EMPF_76641777, getFeld());
			addPruefung(me032, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME032));
		}
	}
}
