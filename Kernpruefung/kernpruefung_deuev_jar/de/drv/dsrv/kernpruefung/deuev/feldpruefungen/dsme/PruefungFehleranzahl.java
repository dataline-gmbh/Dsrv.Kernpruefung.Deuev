package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld Fehleranzahl aus dem Baustein DSME.
 */
public class PruefungFehleranzahl extends AbstractFeldPruefung<FeldNameDSME, FehlerNummerDSME> {

	private static final List<String> ERLAUBTE_WERTE = Arrays.asList("0");
	private final Feld<FeldNameDSME> feldFEKZ;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldFEKZ
	 *            Feld Fehlerkennzeichnung aus DSME
	 */
	public PruefungFehleranzahl(final Feld<FeldNameDSME> feld, final Feld<FeldNameDSME> feldFEKZ) {
		super(feld);

		this.feldFEKZ = feldFEKZ;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch me070 = new PruefungNumerisch(getFeld(), true);
		addPruefung(me070, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME070));

		if (feldFEKZ.getTrimmedValue().compareTo("0") == 0) {
			final PruefungInList me072 = new PruefungInList(ERLAUBTE_WERTE, getFeld());
			addPruefung(me072, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME072));
		}
	}
}
