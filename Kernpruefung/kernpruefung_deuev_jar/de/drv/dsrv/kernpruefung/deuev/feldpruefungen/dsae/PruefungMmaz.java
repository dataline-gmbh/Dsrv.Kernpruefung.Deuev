package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Datensatz;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.AbstractPruefungSchalterleisteFeld;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSAE;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;

/**
 * Pruefung fuer das Feld MMAZ aus dem Baustein DSAE.
 */
public class PruefungMmaz extends AbstractPruefungSchalterleisteFeld<FeldNameDSAE, FehlerNummerDSAE> {

	private static final List<String> MUSS_N = Arrays.asList("N");
	private final Feld<FeldNameDSAE> feldMmez;
	private final String vfmm;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld
	 * @param feldMmez
	 *            Feld MMEZ aus DSAE
	 * @param datensatz
	 *            Datensatz DSAE
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public PruefungMmaz(final Feld<FeldNameDSAE> feld, final Feld<FeldNameDSAE> feldMmez, final Datensatz datensatz,
			final String vfmm) {
		super(feld, datensatz);

		this.feldMmez = feldMmez;
		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		addPruefungenOptional(FehlerNummerDSAE.DSAE400, FehlerNummerDSAE.DSAE930, BausteinName.DBAZ);

		if (getFeld().getTrimmedValue().compareTo("J") == 0) {
			final PruefungInList ae402 = new PruefungInList(MUSS_N, feldMmez);
			addPruefung(ae402, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE402));
		}

		if ((vfmm.compareTo("SOTBF") == 0) || (vfmm.compareTo("UETBF") == 0) || (vfmm.compareTo("PVTRV") == 0)) {
			final PruefungInList ae406 = new PruefungInList(MUSS_N, getFeld(), true);
			addPruefung(ae406, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE406));
		}
	}
}
