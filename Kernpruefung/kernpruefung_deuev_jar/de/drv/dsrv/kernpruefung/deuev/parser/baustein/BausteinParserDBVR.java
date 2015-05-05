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
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBVR;

/**
 * Parser fuer den Baustein DBVR.
 */
public class BausteinParserDBVR extends AbstractBausteinParser<FeldNameDBVR> {

	private static final int INDEX_KENNUNG_BEGINN = 0;
	private static final int INDEX_KENNUNG_ENDE = 4;

	private static final int INDEX_GDMQ_BEGINN = INDEX_KENNUNG_ENDE;
	private static final int INDEX_GDMQ_ENDE = 6;

	private static final int IDNEX_BRNR_BEGINN = INDEX_GDMQ_ENDE;
	private static final int IDNEX_BRNR_ENDE = 8;

	private static final int INDEX_VSNRZH_BEGINN = IDNEX_BRNR_ENDE;
	private static final int INDEX_VSNRZH_ENDE = 20;

	private static final int LAENGE_DBVR = 20;

	@Override
	public Baustein<FeldNameDBVR> parse(final String datensatz) throws DatensatzAufbauException {

		final ArrayList<Feld<FeldNameDBVR>> felder = new ArrayList<Feld<FeldNameDBVR>>();
		felder.add(parseFeld(FeldNameDBVR.KENNUNG, INDEX_KENNUNG_BEGINN, INDEX_KENNUNG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBVR.ABGABEGRUND, INDEX_GDMQ_BEGINN, INDEX_GDMQ_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBVR.BEREICHS_NR_VA, IDNEX_BRNR_BEGINN, IDNEX_BRNR_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBVR.VSNR_VERGABE, INDEX_VSNRZH_BEGINN, INDEX_VSNRZH_ENDE, datensatz));

		return new Baustein<FeldNameDBVR>(BausteinName.DBVR, felder);
	}

	@Override
	public int getLaenge(final String datensatz) throws DatensatzAufbauException {
		return LAENGE_DBVR;
	}

	@Override
	public Fehler<? extends FehlerNummer> getFehlerBausteinNichtVorhanden() {
		return new Fehler<FehlerNummer>(FehlerNummerDSME.DSME938);
	}
}
