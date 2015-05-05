package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSBD;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSBD;

/**
 * Pruefung fuer das Feld Fehlerkennzeichnung aus dem Baustein DSBD.
 */
public class PruefungFehlerkennzeichnung extends AbstractFeldPruefung<FeldNameDSBD, FehlerNummerDSBD> {

	private static final List<String> ZULAESSIGE_WERTE = Arrays.asList("0", "1");

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungFehlerkennzeichnung(final Feld<FeldNameDSBD> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch bd100 = new PruefungNumerisch(getFeld(), true);
		addPruefung(bd100, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD100));

		final PruefungInList bd102 = new PruefungInList(ZULAESSIGE_WERTE, getFeld());
		addPruefung(bd102, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD102));
	}
}
