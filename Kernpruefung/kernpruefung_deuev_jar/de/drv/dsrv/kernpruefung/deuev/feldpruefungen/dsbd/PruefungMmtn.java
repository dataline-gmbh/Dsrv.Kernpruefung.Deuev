package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Datensatz;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.AbstractPruefungSchalterleisteFeld;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSBD;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSBD;

/**
 * Pruefung fuer das Feld MMTN aus dem Baustein DSBD.
 */
public class PruefungMmtn extends AbstractPruefungSchalterleisteFeld<FeldNameDSBD, FehlerNummerDSBD> {

	private static final List<String> VERFAHREN_J = Arrays.asList("BTRKV", "BTRRV");
	private final Feld<FeldNameDSBD> feldVerfahren;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldVerfahren
	 *            Feld Verfahren aus DSBD.
	 * @param datensatz
	 *            Der komplette Datensatz.
	 */
	public PruefungMmtn(final Feld<FeldNameDSBD> feld, final Feld<FeldNameDSBD> feldVerfahren, final Datensatz datensatz) {
		super(feld, datensatz);

		this.feldVerfahren = feldVerfahren;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		addPruefungenOptional(FehlerNummerDSBD.DSBD700, FehlerNummerDSBD.DSBD932, BausteinName.DBTN);

		if ("J".equals(getFeld().getTrimmedValue())) {
			final PruefungInList bd702 = new PruefungInList(VERFAHREN_J, feldVerfahren);
			addPruefung(bd702, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD702));
		}
	}
}
