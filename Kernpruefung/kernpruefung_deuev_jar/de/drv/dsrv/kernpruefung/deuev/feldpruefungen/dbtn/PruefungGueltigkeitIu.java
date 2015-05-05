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
 * Pruefung fuer das Feld Gueltigkeit-IU aus dem Baustein DBTN.
 */
public class PruefungGueltigkeitIu extends AbstractFeldPruefung<FeldNameDBTN, FehlerNummerDBTN> {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungGueltigkeitIu(final Feld<FeldNameDBTN> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch tn140 = new PruefungNumerisch(getFeld(), true);
		addPruefung(tn140, new Fehler<FehlerNummerDBTN>(FehlerNummerDBTN.DBTN140));

		final PruefungDatumLogischRichtig tn142 = new PruefungDatumLogischRichtig(getFeld());
		addPruefung(tn142, new Fehler<FehlerNummerDBTN>(FehlerNummerDBTN.DBTN142));
	}

}
