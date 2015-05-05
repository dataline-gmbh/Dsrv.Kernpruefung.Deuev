package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSAE;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;

/**
 * Pruefung fuer das Feld VSTR aus dem Baustein DSAE.
 */
public class PruefungVSTR extends AbstractFeldPruefung<FeldNameDSAE, FehlerNummerDSAE> {

	private static final List<String> ERLAUBTE_WERTE = Arrays.asList("0A", "0B", "0C", "0G", "AB", "AC", "AG", "BA",
			"BB", "BC", "BG");

	private static final List<String> VFMM_SONDERFALL = Arrays.asList("KVTWL", "KVTRV", "BATRV", "KTTRV", "PVTRV");
	private static final List<String> VFMM_SONDERFALL_WERTE = Arrays.asList("0A", "0B", "0C", "0G");

	private static final List<String> VFMM_SONDERFALL2 = Arrays.asList("DSTBF");
	private static final List<String> VFMM_SONDERFALL_WERTE2 = Arrays.asList("0B", "BA", "BB", "BC", "BG");

	private final String vfmm;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmal)
	 */
	public PruefungVSTR(final Feld<FeldNameDSAE> feld, final String vfmm) {
		super(feld);

		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		if (StringUtils.isBlank(getFeld().getTrimmedValue())) {
			final PruefungNichtLeer ae120Leer = new PruefungNichtLeer(getFeld());
			addPruefung(ae120Leer, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE120));
		} else {
			final PruefungInList ae120 = new PruefungInList(ERLAUBTE_WERTE, getFeld(), true);
			addPruefung(ae120, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE120));
		}

		if (VFMM_SONDERFALL.contains(vfmm)) {
			final PruefungInList ae124 = new PruefungInList(VFMM_SONDERFALL_WERTE, getFeld(), true);
			addPruefung(ae124, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE124));
		}

		else if (VFMM_SONDERFALL2.contains(vfmm)) {
			final PruefungInList ae132 = new PruefungInList(VFMM_SONDERFALL_WERTE2, getFeld(), true);
			addPruefung(ae132, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE132));
		}
	}
}
