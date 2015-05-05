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
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBSO;

/**
 * Parser fuer den Baustein DBSO.
 */
public class BausteinParserDBSO extends AbstractBausteinParser<FeldNameDBSO> {

	private static final int INDEX_KENNUNG_BEGINN = 0;
	private static final int INDEX_KENNUNG_ENDE = 4;

	private static final int INDEX_KENNZSTSO_BEGINN = INDEX_KENNUNG_ENDE;
	private static final int INDEX_KENNZSTSO_ENDE = 5;

	private static final int INDEX_ZRGBSO_BEGINN = INDEX_KENNZSTSO_ENDE;
	private static final int INDEX_ZRGBSO_ENDE = 13;

	private static final int LAENGE_DBSO = 13;

	@Override
	public Baustein<FeldNameDBSO> parse(final String datensatz) throws DatensatzAufbauException {
		final ArrayList<Feld<FeldNameDBSO>> felder = new ArrayList<Feld<FeldNameDBSO>>();
		felder.add(parseFeld(FeldNameDBSO.KENNUNG, INDEX_KENNUNG_BEGINN, INDEX_KENNUNG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBSO.KENNZ_STORNO_SOFORT, INDEX_KENNZSTSO_BEGINN, INDEX_KENNZSTSO_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBSO.ZEITRAUM_BEGINN_SOFORT, INDEX_ZRGBSO_BEGINN, INDEX_ZRGBSO_ENDE, datensatz));

		return new Baustein<FeldNameDBSO>(BausteinName.DBSO, felder);
	}

	@Override
	public int getLaenge(final String datensatz) throws DatensatzAufbauException {
		return LAENGE_DBSO;
	}

	@Override
	public Fehler<? extends FehlerNummer> getFehlerBausteinNichtVorhanden() {
		return new Fehler<FehlerNummer>(FehlerNummerDSME.DSME940);
	}

}
