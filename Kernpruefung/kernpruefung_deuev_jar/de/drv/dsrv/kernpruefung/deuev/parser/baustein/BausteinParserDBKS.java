package de.drv.dsrv.kernpruefung.deuev.parser.baustein;

import java.util.ArrayList;

import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.parser.AbstractBausteinParser;
import de.drv.dsrv.kernpruefung.basis.parser.DatensatzAufbauException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKS;

/**
 * Parser fuer den Baustein DBKS.
 */
public class BausteinParserDBKS extends AbstractBausteinParser<FeldNameDBKS> {

	private static final int INDEX_KENNUNG_BEGINN = 0;
	private static final int INDEX_KENNUNG_ENDE = 4;

	private static final int INDEX_KENNZKS_BEGINN = INDEX_KENNUNG_ENDE;
	private static final int INDEX_KENNZKS_ENDE = 5;

	private static final int INDEX_VERSICHERUNGSARTEN_BEGINN = 7;
	private static final int INDEX_VERSICHERUNGSARTEN_ENDE = 9;

	private static final int INDEX_VKNR_BEGINN = 50;
	private static final int INDEX_VKNR_ENDE = 52;

	private static final int LAENGE_DBKS = 220;

	@Override
	public Baustein<FeldNameDBKS> parse(final String datensatz) throws DatensatzAufbauException {

		final ArrayList<Feld<FeldNameDBKS>> felder = new ArrayList<Feld<FeldNameDBKS>>();
		felder.add(parseFeld(FeldNameDBKS.KENNUNG, INDEX_KENNUNG_BEGINN, INDEX_KENNUNG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKS.KENNZ_KNV_SEE, INDEX_KENNZKS_BEGINN, INDEX_KENNZKS_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKS.VERSICHERUNGSARTEN, INDEX_VERSICHERUNGSARTEN_BEGINN,
				INDEX_VERSICHERUNGSARTEN_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBKS.VKNR, INDEX_VKNR_BEGINN, INDEX_VKNR_ENDE, datensatz));

		return new Baustein<FeldNameDBKS>(BausteinName.DBKS, felder);
	}

	@Override
	public int getLaenge(final String datensatz) throws DatensatzAufbauException {
		return LAENGE_DBKS;
	}

	@Override
	public Fehler<? extends FehlerNummer> getFehlerBausteinNichtVorhanden() {
		return new Fehler<FehlerNummer>(FehlerNummerDSME.DSME936);
	}

}
