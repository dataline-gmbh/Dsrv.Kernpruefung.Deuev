package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbez;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBEZ;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBEZ;

/**
 * Pruefung fuer das Feld Kennzeichen Stornierung aus dem Baustein DBEZ.
 */
public class PruefungKennzeichenStornierung extends AbstractFeldPruefung<FeldNameDBEZ, FehlerNummerDBEZ> {

	private static final List<String> ZULAESSIGE_WERTE = Arrays.asList("N", "J");

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungKennzeichenStornierung(final Feld<FeldNameDBEZ> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer ez010a = new PruefungNichtLeer(getFeld());
		addPruefung(ez010a, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ010));

		final PruefungInList ez010b = new PruefungInList(ZULAESSIGE_WERTE, getFeld(), true);
		addPruefung(ez010b, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ010));
	}
}
