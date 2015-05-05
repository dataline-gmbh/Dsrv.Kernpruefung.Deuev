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
 * Pruefung fuer das Feld Entscheidung-SO aus dem Baustein DBTN.
 */
public class PruefungEntscheidungSo extends AbstractFeldPruefung<FeldNameDBTN, FehlerNummerDBTN> {

	private final Date verarbeitungsDatum;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param verarbeitungsDatum
	 *            Verarbeitungsdatum aus den Eingabedaten.
	 */
	public PruefungEntscheidungSo(final Feld<FeldNameDBTN> feld, final Date verarbeitungsDatum) {
		super(feld);

		this.verarbeitungsDatum = verarbeitungsDatum;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch tn040 = new PruefungNumerisch(getFeld(), true);
		addPruefung(tn040, new Fehler<FehlerNummerDBTN>(FehlerNummerDBTN.DBTN040));

		final PruefungDatumLogischRichtig tn042 = new PruefungDatumLogischRichtig(getFeld());
		addPruefung(tn042, new Fehler<FehlerNummerDBTN>(FehlerNummerDBTN.DBTN042));

		final PruefungDatumVorDatum tn044 = new PruefungDatumVorDatum(getFeld(), verarbeitungsDatum);
		addPruefung(tn044, new Fehler<FehlerNummerDBTN>(FehlerNummerDBTN.DBTN044));
	}
}
