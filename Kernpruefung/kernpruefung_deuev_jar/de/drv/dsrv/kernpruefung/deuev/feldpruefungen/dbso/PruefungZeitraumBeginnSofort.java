package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbso;

import java.util.Date;
import java.util.GregorianCalendar;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumLogischRichtig;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumNachDatum;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBSO;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBSO;

/**
 * Pruefung fuer das Feld Zeitraum-Beginn-Sofort aus dem Baustein DBSO.
 */
public class PruefungZeitraumBeginnSofort extends AbstractFeldPruefung<FeldNameDBSO, FehlerNummerDBSO> {

	// @formatter:off
	private static final Date ANFANG_2009 = new GregorianCalendar(2009, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	// @formatter:on

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungZeitraumBeginnSofort(final Feld<FeldNameDBSO> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch so020 = new PruefungNumerisch(getFeld(), true);
		addPruefung(so020, new Fehler<FehlerNummerDBSO>(FehlerNummerDBSO.DBSO020));

		final PruefungDatumLogischRichtig so022 = new PruefungDatumLogischRichtig(getFeld());
		addPruefung(so022, new Fehler<FehlerNummerDBSO>(FehlerNummerDBSO.DBSO022));

		final PruefungDatumNachDatum so024 = new PruefungDatumNachDatum(getFeld(), ANFANG_2009);
		addPruefung(so024, new Fehler<FehlerNummerDBSO>(FehlerNummerDBSO.DBSO024));
	}

}
