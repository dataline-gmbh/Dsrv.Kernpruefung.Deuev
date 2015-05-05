package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbrg;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInterval;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBRG;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBRG;

/**
 * Pruefung fuer das Feld Zaehler aus dem Baustein DBRG.
 */
public class PruefungZaehler extends AbstractFeldPruefung<FeldNameDBRG, FehlerNummerDBRG> {

	private static final int LAENGE = 2;
	private static final int INTERVAL_MIN = 1;
	private static final int INTERVAL_MAX = 46;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungZaehler(final Feld<FeldNameDBRG> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch rg300 = new PruefungNumerisch(getFeld(), true);
		addPruefung(rg300, new Fehler<FehlerNummerDBRG>(FehlerNummerDBRG.DBRG300));

		final PruefungLaenge rg310a = new PruefungLaenge(LAENGE, getFeld());
		addPruefung(rg310a, new Fehler<FehlerNummerDBRG>(FehlerNummerDBRG.DBRG310));

		final PruefungInterval rg310b = new PruefungInterval(INTERVAL_MIN, INTERVAL_MAX, getFeld());
		addPruefung(rg310b, new Fehler<FehlerNummerDBRG>(FehlerNummerDBRG.DBRG310));
	}

}
