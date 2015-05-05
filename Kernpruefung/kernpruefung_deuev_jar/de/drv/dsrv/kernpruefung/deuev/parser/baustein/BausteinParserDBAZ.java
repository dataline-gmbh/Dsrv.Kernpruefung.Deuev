package de.drv.dsrv.kernpruefung.deuev.parser.baustein;

import java.util.ArrayList;

import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.parser.AbstractBausteinParser;
import de.drv.dsrv.kernpruefung.basis.parser.DatensatzAufbauException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSAE;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBAZ;

/**
 * Parser fuer den Baustein DBAZ.
 */
public class BausteinParserDBAZ extends AbstractBausteinParser<FeldNameDBAZ> {

	private static final int LAENGE_KENNUNG = 4;
	private static final int LAENGE_KENNZST = 1;
	private static final int LAENGE_LEAT = 2;
	private static final int LAENGE_ZRBG = 8;
	private static final int LAENGE_ZREN = 8;

	private static final int LAENGE_DBAZ = 23;

	private int offset;

	/**
	 * Konstruktor. Setzt <code>offset</code> auf 0.
	 */
	public BausteinParserDBAZ() {
		offset = 0;
	}

	@Override
	public Baustein<FeldNameDBAZ> parse(final String datensatz) throws DatensatzAufbauException {

		final ArrayList<Feld<FeldNameDBAZ>> felder = new ArrayList<Feld<FeldNameDBAZ>>();

		felder.add(parseFeld(FeldNameDBAZ.KENNUNG, offset, offset + LAENGE_KENNUNG, datensatz));
		offset += LAENGE_KENNUNG;

		felder.add(parseFeld(FeldNameDBAZ.KENNZ_STORNO, offset, offset + LAENGE_KENNZST, datensatz));
		offset += LAENGE_KENNZST;

		felder.add(parseFeld(FeldNameDBAZ.ART_DER_ZEIT, offset, offset + LAENGE_LEAT, datensatz));
		offset += LAENGE_LEAT;

		felder.add(parseFeld(FeldNameDBAZ.ZEITRAUM_BEGINN, offset, offset + LAENGE_ZRBG, datensatz));
		offset += LAENGE_ZRBG;

		felder.add(parseFeld(FeldNameDBAZ.ZEITRAUM_ENDE, offset, offset + LAENGE_ZREN, datensatz));
		offset += LAENGE_ZREN;

		return new Baustein<FeldNameDBAZ>(BausteinName.DBAZ, felder);
	}

	@Override
	public int getLaenge(final String datensatz) throws DatensatzAufbauException {
		return LAENGE_DBAZ;
	}

	@Override
	public Fehler<? extends FehlerNummer> getFehlerBausteinNichtVorhanden() {
		return new Fehler<FehlerNummer>(FehlerNummerDSAE.DSAE930);
	}
}
