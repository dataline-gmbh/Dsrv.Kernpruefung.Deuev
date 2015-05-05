package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSAE;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;
import de.drv.dsrv.kernpruefung.deuev.pruefungen.PruefungBetriebsnummer;

/**
 * Pruefung fuer das Feld Betriebsnummer Verursacher aus dem Baustein DSAE.
 */
public class PruefungBetriebsnummerVerursacher extends AbstractFeldPruefung<FeldNameDSAE, FehlerNummerDSAE> {

	private static final List<String> ERLAUBTE_BETRIEBSNR_UETBF = Arrays.asList("98503184", "98702232");
	private static final List<String> VFMM_PVTRV = Arrays.asList("996");

	private static final int INDEX_START = 0;
	private static final int INDEX_ENDE = 3;
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
	public PruefungBetriebsnummerVerursacher(final Feld<FeldNameDSAE> feld, final String vfmm) {
		super(feld);

		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungBetriebsnummer ae142 = new PruefungBetriebsnummer(getFeld());
		addPruefung(ae142, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE142));

		if (vfmm.compareTo("UETBF") == 0) {
			final PruefungInList ae158 = new PruefungInList(ERLAUBTE_BETRIEBSNR_UETBF, getFeld());
			addPruefung(ae158, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE158));
			
		} else if (vfmm.compareTo("PVTRV") == 0) {
			final Feld<FeldNameDSAE> feldErstenDrei;
			if (getFeld().getTrimmedValue().length() > INDEX_ENDE) {
				feldErstenDrei = new Feld<FeldNameDSAE>(getFeld().getTrimmedValue().substring(INDEX_START, INDEX_ENDE));
			} else {
				// Der Fall wird nie eintreffen, da in Pruefung 020 bereits die
				// Laenge ueberprueft wird. Die Unterscheidung muss trotzdem
				// hier stehen, da es ansonsten zu einer
				// IndexOutOfBounds-Exception kommen kann.
				feldErstenDrei = new Feld<FeldNameDSAE>("invalid");
			}
			final PruefungInList ae022 = new PruefungInList(VFMM_PVTRV, feldErstenDrei, true);
			addPruefung(ae022, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE159));
		}
	}
}
