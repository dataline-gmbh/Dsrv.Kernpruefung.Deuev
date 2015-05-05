package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInterval;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSBD;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSBD;

/**
 * Pruefung fuer das Feld Postleitzahl-Zustell aus dem Baustein DSBD.
 */
public class PruefungPostleitzahlZustell extends AbstractFeldPruefung<FeldNameDSBD, FehlerNummerDSBD> {

	private static final int PLZ_MIN = 1000;
	private static final int PLZ_MAX = 99999;
	private static final int LAENGE_PLZ = 5;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungPostleitzahlZustell(final Feld<FeldNameDSBD> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer bd300a = new PruefungNichtLeer(getFeld());
		addPruefung(bd300a, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD300));

		final PruefungLaenge bd300b = new PruefungLaenge(LAENGE_PLZ, getFeld());
		addPruefung(bd300b, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD300));

		final PruefungInterval bd300c = new PruefungInterval(PLZ_MIN, PLZ_MAX, getFeld());
		addPruefung(bd300c, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD300));
	}

}
