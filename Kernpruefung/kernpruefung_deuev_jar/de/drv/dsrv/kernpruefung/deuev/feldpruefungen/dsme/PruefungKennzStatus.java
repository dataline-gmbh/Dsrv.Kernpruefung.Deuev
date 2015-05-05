package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.FeldPruefungStornierungDsme;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld Kennzeichen Status aus dem Baustein DSME.
 */
public class PruefungKennzStatus extends AbstractFeldPruefung<FeldNameDSME, FehlerNummerDSME> {

	private static final List<String> ERLAUBTE_WERTE = Arrays.asList("1", "2");
	private static final List<String> GD_KEINE_STORNIERUNG_1_2 = Arrays.asList("10", "40");
	private final Feld<FeldNameDSME> feldGd;
	private final Feld<FeldNameDSME> feldPersgr;
	private final Baustein<FeldNameDBME> bausteinDbme;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldGd
	 *            Feld Abgabegrund aus DSME
	 * @param feldPersgr
	 *            Feld Personengruppe aus DSME
	 * @param bausteinDbme
	 *            Baustein DBME
	 */
	public PruefungKennzStatus(final Feld<FeldNameDSME> feld, final Feld<FeldNameDSME> feldGd,
			final Feld<FeldNameDSME> feldPersgr, final Baustein<FeldNameDBME> bausteinDbme) {
		super(feld);

		this.feldGd = feldGd;
		this.feldPersgr = feldPersgr;
		this.bausteinDbme = bausteinDbme;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		// length == 0 ist Grundstellung (erlaubt)
		if (getFeld().getTrimmedValue().length() > 0) {
			final PruefungInList me400 = new PruefungInList(ERLAUBTE_WERTE, getFeld());
			addPruefung(me400, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME400));
		}

		final FeldPruefungStornierungDsme stornierung = new FeldPruefungStornierungDsme(bausteinDbme);
		if (!stornierung.isStornierungDbme()
				&& ((getFeld().getTrimmedValue().compareTo("1") == 0) || (getFeld().getTrimmedValue().compareTo("2") == 0))) {
			final PruefungNichtLeer me401a = new PruefungNichtLeer(feldGd);
			addPruefung(me401a, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME401));

			final PruefungInList me401 = new PruefungInList(GD_KEINE_STORNIERUNG_1_2, feldGd);
			addPruefung(me401, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME401));
		}

		if (!stornierung.isStornierungDbme() && (feldPersgr.getValue().charAt(0) != '1')) {
			final PruefungLaenge me402 = new PruefungLaenge(0, getFeld());
			addPruefung(me402, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME402));
		}
	}

}
