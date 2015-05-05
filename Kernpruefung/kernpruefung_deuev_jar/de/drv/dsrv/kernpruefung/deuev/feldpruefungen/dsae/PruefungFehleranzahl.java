package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSAE;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;

/**
 * Pruefung fuer das Feld Fehleranzahl aus dem Baustein DSAE.
 */
public class PruefungFehleranzahl extends AbstractFeldPruefung<FeldNameDSAE, FehlerNummerDSAE> {

	private static final List<String> ERLAUBTE_WERTE = Arrays.asList("0");
	private final Feld<FeldNameDSAE> feldFEKZ;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld
	 * @param feldFEKZ
	 *            Feld Fehlerkennzeichnung aus DSME
	 */
	public PruefungFehleranzahl(final Feld<FeldNameDSAE> feld, final Feld<FeldNameDSAE> feldFEKZ) {
		super(feld);

		this.feldFEKZ = feldFEKZ;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch ae070 = new PruefungNumerisch(getFeld(), true);
		addPruefung(ae070, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE070));

		if (feldFEKZ.getTrimmedValue().compareTo("0") == 0) {
			final PruefungInList ae072 = new PruefungInList(ERLAUBTE_WERTE, getFeld());
			addPruefung(ae072, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE072));
		}
	}
}
