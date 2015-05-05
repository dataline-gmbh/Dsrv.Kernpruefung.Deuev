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
 * Pruefung fuer das Feld Gueltigkeit-U1 aus dem Baustein DBTN.
 */
public class PruefungGueltigkeitU1 extends AbstractFeldPruefung<FeldNameDBTN, FehlerNummerDBTN> {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungGueltigkeitU1(final Feld<FeldNameDBTN> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch tn220 = new PruefungNumerisch(getFeld(), true);
		addPruefung(tn220, new Fehler<FehlerNummerDBTN>(FehlerNummerDBTN.DBTN220));

		final PruefungDatumLogischRichtig tn222 = new PruefungDatumLogischRichtig(getFeld());
		addPruefung(tn222, new Fehler<FehlerNummerDBTN>(FehlerNummerDBTN.DBTN222));
	}
}
