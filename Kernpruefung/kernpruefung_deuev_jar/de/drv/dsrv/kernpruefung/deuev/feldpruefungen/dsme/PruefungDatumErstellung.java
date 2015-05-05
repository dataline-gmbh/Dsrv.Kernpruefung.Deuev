package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld Datum Erstellung aus dem Baustein DSME.
 */
public class PruefungDatumErstellung extends AbstractFeldPruefung<FeldNameDSME, FehlerNummerDSME> {

	private static final int LAENGE = 20;

	private static final int INDEX_DATUM_START = 0;
	private static final int INDEX_DATUM_ENDE = 8;

	private static final int INDEX_UHRZEIT_START = INDEX_DATUM_ENDE;
	private static final int INDEX_UHRZEIT_ENDE = 14;

	private static final int INDEX_ZEITANGABEN_START = 0;
	private static final int INDEX_ZEITANGABEN_ENDE = 6;

	private static final List<String> VFMM_UNGLEICH = Arrays.asList("AGDEU", "AGTRV", "WLTKV");

	private final Date verarbeitungsDatum;
	private final String zeitangaben;
	private final String vfmm;

	private final FeldPruefungErgebnis<FehlerNummerDSME> ergebnis;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param verarbeitungsDatum
	 *            Verarbeitungsdatum aus den Aufrufparametern
	 * @param zeitangaben
	 *            Zeitangaben aus den Uebergabeparametern.
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public PruefungDatumErstellung(final Feld<FeldNameDSME> feld, final Date verarbeitungsDatum,
			final String zeitangaben, final String vfmm) {
		super(feld);

		this.verarbeitungsDatum = verarbeitungsDatum;
		this.zeitangaben = zeitangaben;
		this.vfmm = vfmm;

		ergebnis = new FeldPruefungErgebnis<FehlerNummerDSME>();
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch me050 = new PruefungNumerisch(getFeld(), true);
		addPruefung(me050, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME050));

		// Sollte das Datum nicht komplett angegeben sein geht er spaetestens
		// bei substring kaputt
		final PruefungLaenge me052a = new PruefungLaenge(LAENGE, getFeld());
		addPruefung(me052a, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME052));

		if ((getFeld().getTrimmedValue() != null) && (getFeld().getTrimmedValue().length() == LAENGE)) {
			final Feld<FeldNameDSME> datum = new Feld<FeldNameDSME>(getFeld().getTrimmedValue().substring(
					INDEX_DATUM_START, INDEX_DATUM_ENDE));
			final PruefungDatumLogischRichtig me052 = new PruefungDatumLogischRichtig(datum);
			addPruefung(me052, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME052));

			final PruefungDatumVorDatum me054 = new PruefungDatumVorDatum(datum, verarbeitungsDatum);
			addPruefung(me054, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME054));

			final Feld<FeldNameDSME> uhrzeit = new Feld<FeldNameDSME>(getFeld().getTrimmedValue().substring(
					INDEX_UHRZEIT_START, INDEX_UHRZEIT_ENDE));
			final PruefungUhrzeitLogischRichtig me056 = new PruefungUhrzeitLogischRichtig(uhrzeit);
			addPruefung(me056, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME056));

			if (!VFMM_UNGLEICH.contains(vfmm)) {
				final Feld<FeldNameDSME> datumUhrzeit = new Feld<FeldNameDSME>(getFeld().getTrimmedValue().substring(
						INDEX_DATUM_START, INDEX_UHRZEIT_ENDE));

				final Date datumInklUhrzeit = DateUtils.fuegeDatumUhrzeitHinzu(verarbeitungsDatum,
						zeitangaben.substring(INDEX_ZEITANGABEN_START, INDEX_ZEITANGABEN_ENDE));

				if (datumInklUhrzeit != null) {
					final PruefungUhrzeitNachUhrzeit me058 = new PruefungUhrzeitNachUhrzeit(datumUhrzeit, datumInklUhrzeit);
					addPruefung(me058, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME058));
				} else {
					ergebnis.addFehler(new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME058));
				}
			}
		}
	}

	@Override
	public FeldPruefungErgebnis<FehlerNummerDSME> pruefe() throws UngueltigeDatenException {

		FeldPruefungErgebnis<FehlerNummerDSME> ergebnis = super.pruefe();
		if (ergebnis == null || ergebnis.isErfolgreichInklHinweis()) {
			ergebnis = this.ergebnis;
		}

		return ergebnis;
	}
}
