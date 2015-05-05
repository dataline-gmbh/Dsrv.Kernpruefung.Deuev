package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKV;

/**
 * Pruefung fuer das Feld KENNZ-STORNO.
 */
public class PruefungKennzStorno extends AbstractFeldPruefung<FeldNameDBKV, FehlerNummerDBKV> {
	
	private static final List<String> ERLAUBT = Arrays.asList("J", "N");
	
	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            das Feld KENNZ-STORNO
	 */
	public PruefungKennzStorno(final Feld<FeldNameDBKV> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer kv010a = new PruefungNichtLeer(getFeld());
		addPruefung(kv010a, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV010));

		final PruefungInList kv010 = new PruefungInList(ERLAUBT, getFeld(), true);
		addPruefung(kv010, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV010));
	}
	
}
