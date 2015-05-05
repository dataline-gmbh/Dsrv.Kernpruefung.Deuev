package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbtn;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumLogischRichtig;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBTN;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBTN;

/**
 * Pruefung fuer das Feld Gueltigkeit-SO aus dem Baustein DBTN.
 */
public class PruefungGueltigkeitSo extends AbstractFeldPruefung<FeldNameDBTN, FehlerNummerDBTN> {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungGueltigkeitSo(final Feld<FeldNameDBTN> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch tn060 = new PruefungNumerisch(getFeld(), true);
		addPruefung(tn060, new Fehler<FehlerNummerDBTN>(FehlerNummerDBTN.DBTN060));

		final PruefungDatumLogischRichtig tn062 = new PruefungDatumLogischRichtig(getFeld());
		addPruefung(tn062, new Fehler<FehlerNummerDBTN>(FehlerNummerDBTN.DBTN062));
	}
}
