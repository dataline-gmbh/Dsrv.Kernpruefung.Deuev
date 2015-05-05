package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbaz;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBAZ;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBAZ;

/**
 * Pruefung fuer das Feld Kennzeichen Stornierung aus dem Baustein DBAZ.
 */
public class PruefungKennzStorno extends AbstractFeldPruefung<FeldNameDBAZ, FehlerNummerDBAZ> {

	private static final List<String> ZULAESSIGE_WERTE = Arrays.asList("N", "J");

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld
	 */
	public PruefungKennzStorno(final Feld<FeldNameDBAZ> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer az010a = new PruefungNichtLeer(getFeld());
		addPruefung(az010a, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ010));

		final PruefungInList az010b = new PruefungInList(ZULAESSIGE_WERTE, getFeld(), true);
		addPruefung(az010b, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ010));
	}
}
