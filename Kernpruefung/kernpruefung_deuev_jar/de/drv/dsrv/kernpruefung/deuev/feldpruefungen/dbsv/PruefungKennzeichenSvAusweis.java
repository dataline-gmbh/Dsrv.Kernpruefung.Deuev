package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbsv;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBSV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBSV;

/**
 * Pruefung fuer das Feld Kennzeichen-SV-Ausweis aus dem Baustein DBSV.
 */
public class PruefungKennzeichenSvAusweis extends AbstractFeldPruefung<FeldNameDBSV, FehlerNummerDBSV> {

	private static final List<String> ZULAESSIGER_WERT = Arrays.asList("J");

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungKennzeichenSvAusweis(final Feld<FeldNameDBSV> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer sv010a = new PruefungNichtLeer(getFeld());
		addPruefung(sv010a, new Fehler<FehlerNummerDBSV>(FehlerNummerDBSV.DBSV010));

		final PruefungInList sv010b = new PruefungInList(ZULAESSIGER_WERT, getFeld(), true);
		addPruefung(sv010b, new Fehler<FehlerNummerDBSV>(FehlerNummerDBSV.DBSV010));
	}

}
