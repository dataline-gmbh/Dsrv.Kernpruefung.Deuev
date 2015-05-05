package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbtn;

import java.util.Date;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumLogischRichtig;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumVorDatum;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBTN;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBTN;

/**
 * Pruefung fuer das Feld Datum-Entscheidung-U1 aus dem Baustein DBTN.
 */
public class PruefungDatumEntscheidungU1 extends AbstractFeldPruefung<FeldNameDBTN, FehlerNummerDBTN> {

	private final Date verarbeitungsDatum;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param verarbeitungsDatum
	 *            Verarbeitungsdatum aus den Eingabedaten.
	 */
	public PruefungDatumEntscheidungU1(final Feld<FeldNameDBTN> feld, final Date verarbeitungsDatum) {
		super(feld);

		this.verarbeitungsDatum = verarbeitungsDatum;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch tn200 = new PruefungNumerisch(getFeld(), true);
		addPruefung(tn200, new Fehler<FehlerNummerDBTN>(FehlerNummerDBTN.DBTN200));

		final PruefungDatumLogischRichtig tn202 = new PruefungDatumLogischRichtig(getFeld());
		addPruefung(tn202, new Fehler<FehlerNummerDBTN>(FehlerNummerDBTN.DBTN202));

		final PruefungDatumVorDatum tn124 = new PruefungDatumVorDatum(getFeld(), verarbeitungsDatum);
		addPruefung(tn124, new Fehler<FehlerNummerDBTN>(FehlerNummerDBTN.DBTN204));
	}
}
