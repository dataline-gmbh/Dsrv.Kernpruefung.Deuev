package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsko;

import java.util.Date;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumLogischRichtig;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumVorDatum;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungUhrzeitLogischRichtig;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSKO;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSKO;

/**
 * Pruefung fuer das Feld Datum Erstellung aus dem Baustein DSKO.
 */
public class PruefungDatumErstellung extends AbstractFeldPruefung<FeldNameDSKO, FehlerNummerDSKO> {

	private static final int LAENGE = 20;

	private static final int INDEX_DATUM_START = 0;
	private static final int INDEX_DATUM_ENDE = 8;

	private static final int INDEX_UHRZEIT_START = INDEX_DATUM_ENDE;
	private static final int INDEX_UHRZEIT_ENDE = 14;

	private final Date verarbeitungsDatum;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param verarbeitungsDatum
	 *            Verarbeitungsdatum aus den Aufrufparametern
	 */
	public PruefungDatumErstellung(final Feld<FeldNameDSKO> feld, final Date verarbeitungsDatum) {
		super(feld);

		this.verarbeitungsDatum = verarbeitungsDatum;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch ko050 = new PruefungNumerisch(getFeld(), true);
		addPruefung(ko050, new Fehler<FehlerNummerDSKO>(FehlerNummerDSKO.DSKO050));

		// Sollte das Datum nicht komplett angegeben sein geht er spaetestens
		// bei substring kaputt
		final PruefungLaenge me052a = new PruefungLaenge(LAENGE, getFeld());
		addPruefung(me052a, new Fehler<FehlerNummerDSKO>(FehlerNummerDSKO.DSKO052));

		if ((getFeld().getTrimmedValue() != null) && (getFeld().getTrimmedValue().length() == LAENGE)) {
			final Feld<FeldNameDSKO> datum = new Feld<FeldNameDSKO>(getFeld().getTrimmedValue().substring(
					INDEX_DATUM_START,
					INDEX_DATUM_ENDE));
			final PruefungDatumLogischRichtig me052 = new PruefungDatumLogischRichtig(datum);
			addPruefung(me052, new Fehler<FehlerNummerDSKO>(FehlerNummerDSKO.DSKO052));

			final PruefungDatumVorDatum me054 = new PruefungDatumVorDatum(datum, verarbeitungsDatum);
			addPruefung(me054, new Fehler<FehlerNummerDSKO>(FehlerNummerDSKO.DSKO054));

			final Feld<FeldNameDSKO> uhrzeit = new Feld<FeldNameDSKO>(getFeld().getTrimmedValue().substring(
					INDEX_UHRZEIT_START, INDEX_UHRZEIT_ENDE));
			final PruefungUhrzeitLogischRichtig me056 = new PruefungUhrzeitLogischRichtig(uhrzeit);
			addPruefung(me056, new Fehler<FehlerNummerDSKO>(FehlerNummerDSKO.DSKO056));
		}
	}

}
