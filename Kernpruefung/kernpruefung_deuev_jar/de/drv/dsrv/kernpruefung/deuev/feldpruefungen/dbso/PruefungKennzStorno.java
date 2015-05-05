package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbso;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBSO;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBSO;

/**
 * Pruefung fuer das Feld Kennzeichen Storno aus dem Baustein DBSO.
 */
public class PruefungKennzStorno extends AbstractFeldPruefung<FeldNameDBSO, FehlerNummerDBSO> {

	private static final List<String> ERLAUBTE_WERTE = Arrays.asList("N", "J");

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungKennzStorno(final Feld<FeldNameDBSO> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer so010a = new PruefungNichtLeer(getFeld());
		addPruefung(so010a, new Fehler<FehlerNummerDBSO>(FehlerNummerDBSO.DBSO010));

		final PruefungInList so010 = new PruefungInList(ERLAUBTE_WERTE, getFeld(), true);
		addPruefung(so010, new Fehler<FehlerNummerDBSO>(FehlerNummerDBSO.DBSO010));
	}

}
