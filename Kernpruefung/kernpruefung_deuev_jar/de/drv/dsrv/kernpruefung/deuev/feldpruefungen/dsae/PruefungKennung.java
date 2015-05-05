package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSAE;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;

/**
 * Pruefung fuer das Feld Kennung aus dem Baustein DSAE.
 */
public class PruefungKennung extends AbstractFeldPruefung<FeldNameDSAE, FehlerNummerDSAE> {

	private static final List<String> ERLAUBTE_VFMM = Arrays.asList("KVTWL", "KVTRV", "BATRV", "RVTBA", "KTTRV",
			"RVTKT", "BFTDS", "DSTBF", "SOTBF", "UETBF", "PVTRV", "RVTPV");
	private final String vfmm;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public PruefungKennung(final Feld<FeldNameDSAE> feld, final String vfmm) {
		super(feld);
		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer ae004a = new PruefungNichtLeer(getFeld());
		addPruefung(ae004a, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE004));

		final Feld<FeldNameDSAE> feldVfmm = new Feld<FeldNameDSAE>(vfmm);
		final PruefungInList ae004b = new PruefungInList(ERLAUBTE_VFMM, feldVfmm);
		addPruefung(ae004b, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE004));
	}
}
