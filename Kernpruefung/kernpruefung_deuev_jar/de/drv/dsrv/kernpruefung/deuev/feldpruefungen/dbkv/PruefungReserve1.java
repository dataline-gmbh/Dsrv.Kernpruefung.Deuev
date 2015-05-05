package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKV;

/**
 * Pruefung fuer das Feld Reserve1 aus dem Baustein DBKV.
 */
public class PruefungReserve1 extends AbstractFeldPruefung<FeldNameDBKV, FehlerNummerDBKV> {

	private final Feld<FeldNameDBKV> feldKennzst;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldKennzst
	 *            Feld Kennzeichen Storni aus DBKV
	 */
	public PruefungReserve1(final Feld<FeldNameDBKV> feld, final Feld<FeldNameDBKV> feldKennzst) {
		super(feld);

		this.feldKennzst = feldKennzst;
	}

	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {
		if (isPruefbar(FehlerNummerDBKV.DBKV102, feldKennzst) && feldKennzst.getTrimmedValue().compareTo("N") == 0) {
			final PruefungLaenge kv102 = new PruefungLaenge(0, getFeld());
			addPruefungFeldUebergreifend(kv102, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV102));
		}
	}

}
