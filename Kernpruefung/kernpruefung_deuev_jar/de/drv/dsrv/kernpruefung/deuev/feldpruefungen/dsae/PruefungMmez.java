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
 * Pruefung fuer das Feld MMEZ aus dem Baustein DSAE.
 */
public class PruefungMmez extends AbstractPruefungSchalterleisteFeld<FeldNameDSAE, FehlerNummerDSAE> {

	private static final List<String> MUSS_J = Arrays.asList("J");
	private final Feld<FeldNameDSAE> feldMmaz;
	private final String vfmm;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld
	 * @param feldMmaz
	 *            Feld MMAZ aus DSAE
	 * @param datensatz
	 *            Datensatz DSAE
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public PruefungMmez(final Feld<FeldNameDSAE> feld, final Feld<FeldNameDSAE> feldMmaz, final Datensatz datensatz,
			final String vfmm) {
		super(feld, datensatz);

		this.feldMmaz = feldMmaz;
		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		addPruefungenOptional(FehlerNummerDSAE.DSAE410, FehlerNummerDSAE.DSAE931, BausteinName.DBEZ);

		if (getFeld().getTrimmedValue().compareTo("N") == 0) {
			final PruefungInList ae412 = new PruefungInList(MUSS_J, feldMmaz);
			addPruefung(ae412, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE412));
		}

		if ((vfmm.compareTo("SOTBF") == 0) || (vfmm.compareTo("UETBF") == 0) || (vfmm.compareTo("PVTRV") == 0)) {
			final PruefungInList ae416 = new PruefungInList(MUSS_J, getFeld(), true);
			addPruefung(ae416, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE416));
		}
	}
}
