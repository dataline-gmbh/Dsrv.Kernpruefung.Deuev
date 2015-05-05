package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbka;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKA;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKA;

/**
 * Pruefung fuer das Feld Name-Bezeichnung-1 aus dem Baustein DBKA.
 */
public class PruefungNameBezeichnung1 extends AbstractFeldPruefung<FeldNameDBKA, FehlerNummerDBKA> {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungNameBezeichnung1(final Feld<FeldNameDBKA> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer ka040 = new PruefungNichtLeer(getFeld());
		addPruefung(ka040, new Fehler<FehlerNummerDBKA>(FehlerNummerDBKA.DBKA040));
	}

}
