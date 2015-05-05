package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefungErgebnis;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBSO;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld MM-Uebermittlung aus dem Baustein DSME.
 */
public class PruefungMmUebermittlung extends AbstractFeldPruefung<FeldNameDSME, FehlerNummerDSME> {

	private static final List<String> ERLAUBTE_WERTE = Arrays.asList("1", "2", "4", "5", "9");
	private static final String STORNIERUNG = "2";
	private static final List<String> VFMM_4 = Arrays.asList("KVTRV", "KVTWL", "WLTKV", "RVTKV", "DSTBF");
	private static final List<String> VFMM_GRUNDSTELLUNG = Arrays.asList("BATRV", "KTTRV", "BWTRV", "BZTRV", "ZFTRV",
			"PVTRV");

	private FeldPruefungErgebnis<FehlerNummerDSME> pruefungErgebnis;
	private final Baustein<FeldNameDBME> bausteinDbme;
	private final Baustein<FeldNameDBSO> bausteinDbso;
	private final Baustein<FeldNameDBKV> bausteinDbkv;
	private final String vfmm;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param bausteinDbme
	 *            Baustein DBME
	 * @param bausteinDbso
	 *            Baustein DBSO
	 * @param bausteinDbkv
	 *            Baustein DBKV
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmal)
	 */
	public PruefungMmUebermittlung(final Feld<FeldNameDSME> feld, final Baustein<FeldNameDBME> bausteinDbme,
			final Baustein<FeldNameDBSO> bausteinDbso, final Baustein<FeldNameDBKV> bausteinDbkv, final String vfmm) {
		super(feld);

		this.bausteinDbme = bausteinDbme;
		this.bausteinDbso = bausteinDbso;
		this.bausteinDbkv = bausteinDbkv;
		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		pruefungErgebnis = new FeldPruefungErgebnis<FehlerNummerDSME>();
		final Feld<FeldNameDSME> feldVfmm = new Feld<FeldNameDSME>(vfmm);

		// length == 0 waere die Grundstellung, die hier auch erlaubt ist.
		if (getFeld().getTrimmedValue().length() > 0) {
			final PruefungInList me380 = new PruefungInList(ERLAUBTE_WERTE, getFeld());
			addPruefung(me380, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME380));
		}

		if (getFeld().getTrimmedValue().compareTo(STORNIERUNG) == 0) {
			if (!(bausteinDbme != null && bausteinDbme.getFeld(FeldNameDBME.KENNZ_STORNO).getTrimmedValue()
					.compareTo("J") == 0
					|| bausteinDbso != null && bausteinDbso.getFeld(FeldNameDBSO.KENNZ_STORNO_SOFORT)
							.getTrimmedValue().compareTo("J") == 0 || bausteinDbkv != null && bausteinDbkv
					.getFeld(FeldNameDBKV.KENNZ_STORNO).getTrimmedValue().compareTo("J") == 0)) {
				pruefungErgebnis.addFehler(new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME384));
			}
		}

		if (getFeld().getTrimmedValue().compareTo("4") == 0) {
			final PruefungInList me382 = new PruefungInList(VFMM_4, feldVfmm, true);
			addPruefung(me382, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME382));
		}

		if (VFMM_GRUNDSTELLUNG.contains(vfmm)) {
			final PruefungLaenge me381 = new PruefungLaenge(0, getFeld());
			addPruefung(me381, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME381));
		}
	}


	@Override
	public FeldPruefungErgebnis<FehlerNummerDSME> pruefe() throws UngueltigeDatenException {
		if (!pruefungErgebnis.getFehlerListe().isEmpty()) {
			return pruefungErgebnis;
		}

		return pruefeMitAbbruch();
	}

}
