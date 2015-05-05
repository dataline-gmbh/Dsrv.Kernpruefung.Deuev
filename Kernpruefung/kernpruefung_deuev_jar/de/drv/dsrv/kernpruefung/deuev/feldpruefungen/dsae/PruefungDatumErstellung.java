package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae;

import java.util.Date;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefungErgebnis;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumLogischRichtig;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumVorDatum;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungUhrzeitLogischRichtig;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungUhrzeitNachUhrzeit;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils.DateUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSAE;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;

/**
 * Pruefung DatumErstellung fuer den Baustein DSAE.
 */
public class PruefungDatumErstellung extends AbstractFeldPruefung<FeldNameDSAE, FehlerNummerDSAE> {

	private static final int LAENGE = 20;

	private static final int INDEX_DATUM_START = 0;
	private static final int INDEX_DATUM_ENDE = 8;

	private static final int INDEX_UHRZEIT_START = INDEX_DATUM_ENDE;
	private static final int INDEX_UHRZEIT_ENDE = 14;

	private static final int INDEX_ZEITANGABEN_START = 0;
	private static final int INDEX_ZEITANGABEN_ENDE = 6;

	private final Date verarbeitungsDatum;
	private final String zeitangaben;

	private final FeldPruefungErgebnis<FehlerNummerDSAE> ergebnis;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld
	 * @param verarbeitungsDatum
	 *            Verarbeitungsdatum aus den Aufrufparametern
	 * @param zeitangaben
	 *            Zeitangaben aus den Uebergabeparametern
	 */
	public PruefungDatumErstellung(final Feld<FeldNameDSAE> feld, final Date verarbeitungsDatum,
			final String zeitangaben) {
		super(feld);

		this.verarbeitungsDatum = verarbeitungsDatum;
		this.zeitangaben = zeitangaben;

		ergebnis = new FeldPruefungErgebnis<FehlerNummerDSAE>();
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch ae050Numerisch = new PruefungNumerisch(getFeld(), true);
		addPruefung(ae050Numerisch, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE050));

		// Sollte das Datum nicht komplett angegeben sein geht er spaetestens
		// bei substring kaputt
		final PruefungLaenge ae052Laenge = new PruefungLaenge(LAENGE, getFeld());
		addPruefung(ae052Laenge, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE052));

		if ((getFeld().getTrimmedValue() != null) && (getFeld().getTrimmedValue().length() == LAENGE)) {
			final Feld<FeldNameDSAE> datum = new Feld<FeldNameDSAE>(getFeld().getTrimmedValue().substring(
					INDEX_DATUM_START, INDEX_DATUM_ENDE));
			final PruefungDatumLogischRichtig ae052Logisch = new PruefungDatumLogischRichtig(datum);
			addPruefung(ae052Logisch, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE052));

			final PruefungDatumVorDatum ae054 = new PruefungDatumVorDatum(datum, verarbeitungsDatum);
			addPruefung(ae054, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE054));

			final Feld<FeldNameDSAE> uhrzeit = new Feld<FeldNameDSAE>(getFeld().getTrimmedValue().substring(
					INDEX_UHRZEIT_START, INDEX_UHRZEIT_ENDE));
			final PruefungUhrzeitLogischRichtig ae056 = new PruefungUhrzeitLogischRichtig(uhrzeit);
			addPruefung(ae056, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE056));

			final Feld<FeldNameDSAE> datumUhrzeit = new Feld<FeldNameDSAE>(getFeld().getTrimmedValue().substring(
					INDEX_DATUM_START, INDEX_UHRZEIT_ENDE));

			final Date datumInklUhrzeit = DateUtils.fuegeDatumUhrzeitHinzu(verarbeitungsDatum,
					zeitangaben.substring(INDEX_ZEITANGABEN_START, INDEX_ZEITANGABEN_ENDE));

			if (datumInklUhrzeit != null) {
				final PruefungUhrzeitNachUhrzeit ae058 = new PruefungUhrzeitNachUhrzeit(datumUhrzeit, datumInklUhrzeit);
				addPruefung(ae058, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE058));
			} else {
				ergebnis.addFehler(new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE058));
			}
		}
	}

	@Override
	public FeldPruefungErgebnis<FehlerNummerDSAE> pruefe() throws UngueltigeDatenException {

		FeldPruefungErgebnis<FehlerNummerDSAE> ergebnis = super.pruefe();
		if (ergebnis == null || ergebnis.isErfolgreichInklHinweis()) {
			ergebnis = this.ergebnis;
		}

		return ergebnis;
	}
}
