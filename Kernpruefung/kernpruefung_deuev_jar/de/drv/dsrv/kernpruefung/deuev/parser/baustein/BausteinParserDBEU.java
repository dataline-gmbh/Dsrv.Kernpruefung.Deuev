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
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBEU;

/**
 * Parser fuer den Baustein DBEU.
 */
public class BausteinParserDBEU extends AbstractBausteinParser<FeldNameDBEU> {

	private static final int INDEX_KENNUNG_BEGINN = 0;
	private static final int INDEX_KENNUNG_ENDE = 4;

	private static final int INDEX_GBLAND_BEGINN = INDEX_KENNUNG_ENDE;
	private static final int INDEX_GBLAND_ENDE = 7;

	private static final int INDEX_EUVSNR_BEGINN = INDEX_GBLAND_ENDE;
	private static final int INDEX_EUVSNR_ENDE = 27;

	private static final int LAENGE_DBEU = 27;

	@Override
	public Baustein<FeldNameDBEU> parse(final String datensatz) throws DatensatzAufbauException {

		final ArrayList<Feld<FeldNameDBEU>> felder = new ArrayList<Feld<FeldNameDBEU>>();
		felder.add(parseFeld(FeldNameDBEU.KENNUNG, INDEX_KENNUNG_BEGINN, INDEX_KENNUNG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBEU.GB_LAND, INDEX_GBLAND_BEGINN, INDEX_GBLAND_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBEU.EUVSNR, INDEX_EUVSNR_BEGINN, INDEX_EUVSNR_ENDE, datensatz));

		return new Baustein<FeldNameDBEU>(BausteinName.DBEU, felder);
	}

	@Override
	public int getLaenge(final String datensatz) throws DatensatzAufbauException {
		return LAENGE_DBEU;
	}

	@Override
	public Fehler<? extends FehlerNummer> getFehlerBausteinNichtVorhanden() {
		return new Fehler<FehlerNummer>(FehlerNummerDSME.DSME934);
	}

}
