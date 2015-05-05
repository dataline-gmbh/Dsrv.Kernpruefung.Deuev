package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbuv;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInterval;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBUV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBUV;

/**
 * Pruefung fuer das Feld Anzahl UV aus dem Baustein DBUV.
 */
public class PruefungAnzahlUv extends AbstractFeldPruefung<FeldNameDBUV, FehlerNummerDBUV> {

	private static final int INTERVAL_MIN = 1;
	private static final int INTERVAL_MAX = 9;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungAnzahlUv(final Feld<FeldNameDBUV> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch uv020 = new PruefungNumerisch(getFeld(), true);
		addPruefung(uv020, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV020));

		final PruefungInterval uv022 = new PruefungInterval(INTERVAL_MIN, INTERVAL_MAX, getFeld());
		addPruefung(uv022, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV022));
	}

}
