package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbka;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInterval;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKA;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKA;

/**
 * Pruefung fuer das Feld Postleitzahl-Postfach aus dem Baustein DBKA.
 */
public class PruefungPostleitzahlPostfach extends AbstractFeldPruefung<FeldNameDBKA, FehlerNummerDBKA> {

	private static final int PLZ_MIN = 1000;
	private static final int PLZ_MAX = 99999;
	private static final int LAENGE_PLZ = 5;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungPostleitzahlPostfach(final Feld<FeldNameDBKA> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		if (StringUtils.isNotBlank(getFeld().getTrimmedValue())) {
			final PruefungLaenge bd420a = new PruefungLaenge(LAENGE_PLZ, getFeld());
			addPruefung(bd420a, new Fehler<FehlerNummerDBKA>(FehlerNummerDBKA.DBKA220));

			final PruefungInterval bd420b = new PruefungInterval(PLZ_MIN, PLZ_MAX, getFeld());
			addPruefung(bd420b, new Fehler<FehlerNummerDBKA>(FehlerNummerDBKA.DBKA220));
		}
	}
}
