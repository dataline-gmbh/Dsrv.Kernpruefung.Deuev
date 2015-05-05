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
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBSV;

/**
 * Parser fuer den Baustein DBSV.
 */
public class BausteinParserDBSV extends AbstractBausteinParser<FeldNameDBSV> {

	private static final int INDEX_KENNUNG_BEGINN = 0;
	private static final int INDEX_KENNUNG_ENDE = 4;

	private static final int INDEX_KENNZSVA_BEGINN = INDEX_KENNUNG_ENDE;
	private static final int INDEX_KENNZSVA_ENDE = 5;

	private static final int LAENGE_DBSV = 5;

	@Override
	public Baustein<FeldNameDBSV> parse(final String datensatz) throws DatensatzAufbauException {
		final ArrayList<Feld<FeldNameDBSV>> felder = new ArrayList<Feld<FeldNameDBSV>>();
		felder.add(parseFeld(FeldNameDBSV.KENNUNG, INDEX_KENNUNG_BEGINN, INDEX_KENNUNG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBSV.KENNZ_SVA, INDEX_KENNZSVA_BEGINN, INDEX_KENNZSVA_ENDE, datensatz));
		return new Baustein<FeldNameDBSV>(BausteinName.DBSV, felder);
	}

	@Override
	public int getLaenge(final String datensatz) throws DatensatzAufbauException {
		return LAENGE_DBSV;
	}

	@Override
	public Fehler<? extends FehlerNummer> getFehlerBausteinNichtVorhanden() {
		return new Fehler<FehlerNummer>(FehlerNummerDSME.DSME937);
	}

}
