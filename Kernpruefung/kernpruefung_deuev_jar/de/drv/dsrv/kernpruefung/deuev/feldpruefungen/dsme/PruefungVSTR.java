package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld VSTR aus dem Baustein DSME.
 */
public class PruefungVSTR extends AbstractFeldPruefung<FeldNameDSME, FehlerNummerDSME> {

	private static final List<String> ERLAUBTE_WERTE = Arrays.asList("0A", "0B", "0C", "0G", "AB", "AC", "AG", "BA",
			"BB", "BC", "BG", "IL", "PA", "PB", "PC", "PG", "");

	private static final List<String> VFMM_SONDERFALL = Arrays.asList("AGDEU", "WLTKV");
	private static final List<String> VFMM_SONDERFALL_WERTE = Arrays.asList("0A", "0B", "0C", "0G");

	private static final List<String> VFMM_SONDERFALL2 = Arrays.asList("KVTWL", "KVTRV", "PVTRV", "BATRV", "KTTRV",
			"BWTRV", "BZTRV");
	private static final List<String> VFMM_SONDERFALL_WERTE2 = Arrays.asList("0A", "0B", "0C", "0G");

	private static final String DSTBF = "DSTBF";
	private static final List<String> VFMM_SONDERFALL_WERTE3 = Arrays.asList("BA", "BB", "BC", "BG");

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
	public PruefungVSTR(final Feld<FeldNameDSME> feld, final String vfmm) {
		super(feld);

		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		if (StringUtils.isNotBlank(getFeld().getTrimmedValue())) {
			final PruefungInList me120 = new PruefungInList(ERLAUBTE_WERTE, getFeld(), true);
			addPruefung(me120, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME120));
		}

		if (VFMM_SONDERFALL.contains(vfmm)) {
			if (StringUtils.isNotBlank(getFeld().getTrimmedValue())) {
				final PruefungInList me122 = new PruefungInList(VFMM_SONDERFALL_WERTE, getFeld(), true);
				addPruefung(me122, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME122));
			}
		}

		else if (VFMM_SONDERFALL2.contains(vfmm)) {
			if (StringUtils.isBlank(getFeld().getTrimmedValue())) {
				final PruefungNichtLeer me124a = new PruefungNichtLeer(getFeld());
				addPruefung(me124a, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME124));
			} else {
				final PruefungInList me124 = new PruefungInList(VFMM_SONDERFALL_WERTE2, getFeld(), true);
				addPruefung(me124, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME124));
			}
		}

		else if (DSTBF.compareTo(vfmm) == 0) {
			if (StringUtils.isBlank(getFeld().getTrimmedValue())) {
				final PruefungNichtLeer me132a = new PruefungNichtLeer(getFeld());
				addPruefung(me132a, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME132));
			} else {
				final PruefungInList me132 = new PruefungInList(VFMM_SONDERFALL_WERTE3, getFeld(), true);
				addPruefung(me132, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME132));
			}
		}
	}

}
