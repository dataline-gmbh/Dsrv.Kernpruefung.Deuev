package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsko;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSKO;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSKO;

/**
 * Pruefung fuer das Feld Fehleranzahl aus dem Baustein DSKO.
 */
public class PruefungFehleranzahl extends AbstractFeldPruefung<FeldNameDSKO, FehlerNummerDSKO> {

	private static final List<String> ERLAUBTE_WERTE = Arrays.asList("0");
	private final Feld<FeldNameDSKO> feldFEKZ;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldFEKZ
	 *            - Feld Fehlerkennzeichnung aus DSKO.
	 */
	public PruefungFehleranzahl(final Feld<FeldNameDSKO> feld, final Feld<FeldNameDSKO> feldFEKZ) {
		super(feld);
		
		this.feldFEKZ = feldFEKZ;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch ko070 = new PruefungNumerisch(getFeld(), true);
		addPruefung(ko070, new Fehler<FehlerNummerDSKO>(FehlerNummerDSKO.DSKO070));
		
		if (feldFEKZ.getTrimmedValue().compareTo("0") == 0) {
			final PruefungInList ko072 = new PruefungInList(ERLAUBTE_WERTE, getFeld());
			addPruefung(ko072, new Fehler<FehlerNummerDSKO>(FehlerNummerDSKO.DSKO072));
		}
	}
}
