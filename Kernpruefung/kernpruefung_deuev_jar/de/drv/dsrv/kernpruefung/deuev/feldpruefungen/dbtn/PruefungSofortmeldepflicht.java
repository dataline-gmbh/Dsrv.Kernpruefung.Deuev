package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbtn;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBTN;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBTN;

/**
 * Pruefung fuer das Feld Softortmeldepflicht aus dem Baustein DBTN.
 */
public class PruefungSofortmeldepflicht extends AbstractFeldPruefung<FeldNameDBTN, FehlerNummerDBTN> {

	private static final List<String> ZULAESSIGE_ZEICHEN = Arrays.asList("J", "N");

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungSofortmeldepflicht(final Feld<FeldNameDBTN> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		if (StringUtils.isNotBlank(getFeld().getTrimmedValue())) {
			final PruefungInList tn030 = new PruefungInList(ZULAESSIGE_ZEICHEN, getFeld(), true);
			addPruefung(tn030, new Fehler<FehlerNummerDBTN>(FehlerNummerDBTN.DBTN030));
		}
	}
}