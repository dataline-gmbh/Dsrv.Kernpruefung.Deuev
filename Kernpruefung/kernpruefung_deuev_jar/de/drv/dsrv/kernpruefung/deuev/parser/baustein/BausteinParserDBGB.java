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
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBGB;

/**
 * Parser fuer den Baustein DBGB.
 */
public class BausteinParserDBGB extends AbstractBausteinParser<FeldNameDBGB> {

	private static final int INDEX_KENNUNG_BEGINN = 0;
	private static final int INDEX_KENNUNG_ENDE = 4;

	private static final int INDEX_GBNAME_BEGINN = INDEX_KENNUNG_ENDE;
	private static final int INDEX_GBNAME_ENDE = 34;

	private static final int INDEX_GBVOSA_BEGINN = INDEX_GBNAME_ENDE;
	private static final int INDEX_GBVOSA_ENDE = 54;

	private static final int INDEX_NAZU_BEGINN = INDEX_GBVOSA_ENDE;
	private static final int INDEX_NAZU_ENDE = 74;

	private static final int INDEX_GBDT_BEGINN = INDEX_NAZU_ENDE;
	private static final int INDEX_GBDT_ENDE = 82;

	private static final int INDEX_GE_BEGINN = INDEX_GBDT_ENDE;
	private static final int INDEX_GE_ENDE = 83;

	private static final int INDEX_GBORT_BEGINN = INDEX_GE_ENDE;
	private static final int IDNEX_GBORT_ENDE = 117;

	private static final int LAENGE_DBGB = 117;

	@Override
	public Baustein<FeldNameDBGB> parse(final String datensatz) throws DatensatzAufbauException {

		final ArrayList<Feld<FeldNameDBGB>> felder = new ArrayList<Feld<FeldNameDBGB>>();
		felder.add(parseFeld(FeldNameDBGB.KENNUNG, INDEX_KENNUNG_BEGINN, INDEX_KENNUNG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBGB.GB_NAME, INDEX_GBNAME_BEGINN, INDEX_GBNAME_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBGB.GB_VORSATZWORT, INDEX_GBVOSA_BEGINN, INDEX_GBVOSA_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBGB.GB_NAMENSZUSATZ, INDEX_NAZU_BEGINN, INDEX_NAZU_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBGB.GEBURTSDATUM, INDEX_GBDT_BEGINN, INDEX_GBDT_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBGB.GESCHLECHT, INDEX_GE_BEGINN, INDEX_GE_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBGB.GB_ORT, INDEX_GBORT_BEGINN, IDNEX_GBORT_ENDE, datensatz));

		return new Baustein<FeldNameDBGB>(BausteinName.DBGB, felder);
	}

	@Override
	public int getLaenge(final String datensatz) throws DatensatzAufbauException {
		return LAENGE_DBGB;
	}

	@Override
	public Fehler<? extends FehlerNummer> getFehlerBausteinNichtVorhanden() {
		return new Fehler<FehlerNummer>(FehlerNummerDSME.DSME932);
	}
}
