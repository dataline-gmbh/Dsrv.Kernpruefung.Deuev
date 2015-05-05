package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSBD;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSBD;

/**
 * Pruefung fuer das Feld ruhend Kennzeichen aus dem Baustein DSBD.
 */
public class PruefungRuhendKennzeichen extends AbstractFeldPruefung<FeldNameDSBD, FehlerNummerDSBD> {

	private static final List<String> ERLAUBTE_WERTE = Arrays.asList("A", "R");

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungRuhendKennzeichen(final Feld<FeldNameDSBD> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer bd460a = new PruefungNichtLeer(getFeld());
		addPruefung(bd460a, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD460));

		final PruefungInList bd460b = new PruefungInList(ERLAUBTE_WERTE, getFeld(), true);
		addPruefung(bd460b, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD460));
	}

}
