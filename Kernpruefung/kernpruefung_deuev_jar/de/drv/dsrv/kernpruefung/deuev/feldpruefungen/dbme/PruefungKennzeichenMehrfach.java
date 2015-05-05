package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbme;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;

/**
 * Pruefung fuer das Feld Kennzeichen Mehrfact aus dem Baustein DBME.
 */
public class PruefungKennzeichenMehrfach extends AbstractFeldPruefung<FeldNameDBME, FehlerNummerDBME> {

	private static final List<String> ZULAESSIGE_WERTE = Arrays.asList("J", "N");
	private static final List<String> VFMM_N = Arrays.asList("BWTRV", "BZTRV");

	private final String vfmm;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            das zu pruefende Feld
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public PruefungKennzeichenMehrfach(final Feld<FeldNameDBME> feld, final String vfmm) {
		super(feld);

		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer me170a = new PruefungNichtLeer(getFeld());
		addPruefung(me170a, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME170));

		final PruefungInList me170b = new PruefungInList(ZULAESSIGE_WERTE, getFeld(), true);
		addPruefung(me170b, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME170));

		if (VFMM_N.contains(vfmm)) {
			final PruefungInList me172 = new PruefungInList(Arrays.asList("N"), getFeld(), true);
			addPruefung(me172, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME172));
		}
	}
}
