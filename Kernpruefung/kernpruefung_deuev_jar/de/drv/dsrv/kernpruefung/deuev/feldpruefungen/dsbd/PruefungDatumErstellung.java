package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd;

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
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSBD;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSBD;

/**
 * Pruefung fuer das Feld Datum Erstellung aus dem Baustein DSBD.
 */
public class PruefungDatumErstellung extends AbstractFeldPruefung<FeldNameDSBD, FehlerNummerDSBD> {

	private static final int LAENGE = 20;

	private static final int INDEX_DATUM_START = 0;
	private static final int INDEX_DATUM_ENDE = 8;

	private static final int INDEX_UHRZEIT_START = INDEX_DATUM_ENDE;
	private static final int INDEX_UHRZEIT_ENDE = 14;

	private final Date verarbeitungsDatum;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param verarbeitungsDatum
	 *            Verarbeitungsdatum aus den Aufrufparametern
	 */
	public PruefungDatumErstellung(final Feld<FeldNameDSBD> feld, final Date verarbeitungsDatum) {
		super(feld);

		this.verarbeitungsDatum = verarbeitungsDatum;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch bd080 = new PruefungNumerisch(getFeld(), true);
		addPruefung(bd080, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD080));

		// Sollte das Datum nicht komplett angegeben sein geht er spaetestens
		// bei substring kaputt
		final PruefungLaenge bd082a = new PruefungLaenge(LAENGE, getFeld());
		addPruefung(bd082a, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD082));

		if (getFeld().getTrimmedValue() != null && getFeld().getTrimmedValue().length() == LAENGE) {
			final Feld<FeldNameDSBD> datum = new Feld<FeldNameDSBD>(getFeld().getTrimmedValue().substring(
					INDEX_DATUM_START, INDEX_DATUM_ENDE));
			final PruefungDatumLogischRichtig bd082 = new PruefungDatumLogischRichtig(datum);
			addPruefung(bd082, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD082));

			final PruefungDatumVorDatum bd084 = new PruefungDatumVorDatum(datum, verarbeitungsDatum);
			addPruefung(bd084, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD084));

			final Feld<FeldNameDSBD> uhrzeit = new Feld<FeldNameDSBD>(getFeld().getTrimmedValue().substring(
					INDEX_UHRZEIT_START, INDEX_UHRZEIT_ENDE));
			final PruefungUhrzeitLogischRichtig bd086 = new PruefungUhrzeitLogischRichtig(uhrzeit);
			addPruefung(bd086, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD086));
		}
	}
}
