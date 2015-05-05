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
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBNA;

/**
 * Parser fuer den Baustein DBNA.
 */
public class BausteinParserDBNA extends AbstractBausteinParser<FeldNameDBNA> {

	private static final int INDEX_KENNUNG_BEGINN = 0;
	private static final int INDEX_KENNUNG_ENDE = 4;

	private static final int INDEX_FMNA_BEGINN = INDEX_KENNUNG_ENDE;
	private static final int INDEX_FMNA_ENDE = 34;

	private static final int INDEX_VONA_BEGINN = INDEX_FMNA_ENDE;
	private static final int INDEX_VONA_ENDE = 64;

	private static final int INDEX_VOSA_BEGINN = INDEX_VONA_ENDE;
	private static final int INDEX_VOSA_ENDE = 84;

	private static final int IDNEX_NAZU_BEGINN = INDEX_VOSA_ENDE;
	private static final int INDEX_NAZU_ENDE = 104;

	private static final int INDEX_TITEL_BEGINN = INDEX_NAZU_ENDE;
	private static final int INDEX_TITEL_ENDE = 124;

	private static final int INDEX_KENNZAB_BEGINN = INDEX_TITEL_ENDE;
	private static final int INDEX_KENNZAB_ENDE = 125;

	private static final int LAENGE_DBNA = 125;

	@Override
	public Baustein<FeldNameDBNA> parse(final String datensatz) throws DatensatzAufbauException {

		final ArrayList<Feld<FeldNameDBNA>> felder = new ArrayList<Feld<FeldNameDBNA>>();
		felder.add(parseFeld(FeldNameDBNA.KENNUNG, INDEX_KENNUNG_BEGINN, INDEX_KENNUNG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBNA.FAMILIENNAME, INDEX_FMNA_BEGINN, INDEX_FMNA_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBNA.VORNAME, INDEX_VONA_BEGINN, INDEX_VONA_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBNA.VORSATZWORT, INDEX_VOSA_BEGINN, INDEX_VOSA_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBNA.NAMENSZUSATZ, IDNEX_NAZU_BEGINN, INDEX_NAZU_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBNA.TITEL, INDEX_TITEL_BEGINN, INDEX_TITEL_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBNA.KENNZ_AEND_BER, INDEX_KENNZAB_BEGINN, INDEX_KENNZAB_ENDE, datensatz));

		return new Baustein<FeldNameDBNA>(BausteinName.DBNA, felder);
	}

	@Override
	public int getLaenge(final String datensatz) throws DatensatzAufbauException {
		return LAENGE_DBNA;
	}

	@Override
	public Fehler<? extends FehlerNummer> getFehlerBausteinNichtVorhanden() {
		return new Fehler<FehlerNummer>(FehlerNummerDSME.DSME931);
	}
}
