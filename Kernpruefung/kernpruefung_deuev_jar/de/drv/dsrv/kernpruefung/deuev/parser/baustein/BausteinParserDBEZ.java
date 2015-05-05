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
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBEZ;

/**
 * Parser fuer den Baustein DBEZ.
 */
public class BausteinParserDBEZ extends AbstractBausteinParser<FeldNameDBEZ> {

	private static final int INDEX_KENNUNG_BEGINN = 0;
	private static final int INDEX_KENNUNG_ENDE = 4;

	private static final int INDEX_KENNZ_STORNO_BEGINN = INDEX_KENNUNG_ENDE;
	private static final int INDEX_KENNZ_STORNO_ENDE = 5;

	private static final int INDEX_LEAT_BEGINN = INDEX_KENNZ_STORNO_ENDE;
	private static final int INDEX_LEAT_ENDE = 7;

	private static final int INDEX_ABGABEGRUND_BEGINN = INDEX_LEAT_ENDE;
	private static final int INDEX_ABGABEGRUND_ENDE = 9;

	private static final int INDEX_ZEITRAUM_BEGINN_BEGINN = INDEX_ABGABEGRUND_ENDE;
	private static final int INDEX_ZEITRAUM_BEGINN_ENDE = 17;

	private static final int INDEX_ZEITRAUM_ENDE_BEGINN = INDEX_ZEITRAUM_BEGINN_ENDE;
	private static final int INDEX_ZEITRAUM_ENDE_ENDE = 25;

	private static final int INDEX_WAEHRUNGSKENNZ_BEGINN = INDEX_ZEITRAUM_ENDE_ENDE;
	private static final int INDEX_WAEHRUNGSKENNZ_ENDE = 26;

	private static final int INDEX_ENTGELT_BEGINN = INDEX_WAEHRUNGSKENNZ_ENDE;
	private static final int INDEX_ENTGELT_ENDE = 32;

	private static final int INDEX_BEITRAGSANTEIL_BEGINN = INDEX_ENTGELT_ENDE;
	private static final int INDEX_BEITRAGSANTEIL_ENDE = 39;

	private static final int INDEX_KENNZRK_BEGINN = INDEX_BEITRAGSANTEIL_ENDE;
	private static final int INDEX_KENNZRK_ENDE = 40;

	private static final int INDEX_KENNZWG_BEGINN = INDEX_KENNZRK_ENDE;
	private static final int INDEX_KENNZWG_ENDE = 41;

	private static final int LAENGE_DBEZ = 41;

	@Override
	public Baustein<FeldNameDBEZ> parse(final String datensatz) throws DatensatzAufbauException {

		final ArrayList<Feld<FeldNameDBEZ>> felder = new ArrayList<Feld<FeldNameDBEZ>>();
		felder.add(parseFeld(FeldNameDBEZ.KENNUNG, INDEX_KENNUNG_BEGINN, INDEX_KENNUNG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBEZ.KENNZ_STORNO, INDEX_KENNZ_STORNO_BEGINN, INDEX_KENNZ_STORNO_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBEZ.LEISTUNGSART, INDEX_LEAT_BEGINN, INDEX_LEAT_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBEZ.ABGABEGRUND, INDEX_ABGABEGRUND_BEGINN, INDEX_ABGABEGRUND_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBEZ.ZEITRAUM_BEGINN, INDEX_ZEITRAUM_BEGINN_BEGINN, INDEX_ZEITRAUM_BEGINN_ENDE,
				datensatz));
		felder.add(parseFeld(FeldNameDBEZ.ZEITRAUM_ENDE, INDEX_ZEITRAUM_ENDE_BEGINN, INDEX_ZEITRAUM_ENDE_ENDE,
				datensatz));
		felder.add(parseFeld(FeldNameDBEZ.WAEHRUNGSKENNZ, INDEX_WAEHRUNGSKENNZ_BEGINN, INDEX_WAEHRUNGSKENNZ_ENDE,
				datensatz));
		felder.add(parseFeld(FeldNameDBEZ.ENTGELT, INDEX_ENTGELT_BEGINN, INDEX_ENTGELT_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBEZ.BEITRAGSANTEIL, INDEX_BEITRAGSANTEIL_BEGINN, INDEX_BEITRAGSANTEIL_ENDE,
				datensatz));
		felder.add(parseFeld(FeldNameDBEZ.KENNZ_RECHTSKREIS, INDEX_KENNZRK_BEGINN, INDEX_KENNZRK_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBEZ.KENNZ_WIEDEREINGLIEDERUNG, INDEX_KENNZWG_BEGINN, INDEX_KENNZWG_ENDE,
				datensatz));

		return new Baustein<FeldNameDBEZ>(BausteinName.DBEZ, felder);
	}

	@Override
	public int getLaenge(final String datensatz) throws DatensatzAufbauException {
		return LAENGE_DBEZ;
	}

	@Override
	public Fehler<? extends FehlerNummer> getFehlerBausteinNichtVorhanden() {
		return new Fehler<FehlerNummer>(FehlerNummerDSAE.DSAE931);
	}

}
