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
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBAN;

/**
 * Parser fuer den Baustein DBAN.
 */
public class BausteinParserDBAN extends AbstractBausteinParser<FeldNameDBAN> {

	private static final int INDEX_KENNUNG_BEGINN = 0;
	private static final int INDEX_KENNUNG_ENDE = 4;

	private static final int INDEX_LDKZ_BEGINN = INDEX_KENNUNG_ENDE;
	private static final int INDEX_LDKZ_ENDE = 7;

	private static final int INDEX_PLZ_BEGINN = INDEX_LDKZ_ENDE;
	private static final int INDEX_PLZ_ENDE = 17;

	private static final int INDEX_WOHNORT_BEGINN = INDEX_PLZ_ENDE;
	private static final int INDEX_WOHNORT_ENDE = 51;

	private static final int INDEX_STRASSE_BEGINN = INDEX_WOHNORT_ENDE;
	private static final int INDEX_STRASSE_ENDE = 84;

	private static final int INDEX_HAUSNR_BEGINN = INDEX_STRASSE_ENDE;
	private static final int INDEX_HAUSNR_ENDE = 93;

	private static final int INDEX_ADRZUSATZ_BEGINN = INDEX_HAUSNR_ENDE;
	private static final int INDEX_ADRZUSATZ_ENDE = 133;

	private static final int LAENGE_DBAN = 133;

	@Override
	public Baustein<FeldNameDBAN> parse(final String bausteinWert) throws DatensatzAufbauException {

		final ArrayList<Feld<FeldNameDBAN>> felder = new ArrayList<Feld<FeldNameDBAN>>();
		felder.add(parseFeld(FeldNameDBAN.KENNUNG, INDEX_KENNUNG_BEGINN, INDEX_KENNUNG_ENDE, bausteinWert));
		felder.add(parseFeld(FeldNameDBAN.LAENDER_KENNZ, INDEX_LDKZ_BEGINN, INDEX_LDKZ_ENDE, bausteinWert));
		felder.add(parseFeld(FeldNameDBAN.PLZ, INDEX_PLZ_BEGINN, INDEX_PLZ_ENDE, bausteinWert));
		felder.add(parseFeld(FeldNameDBAN.WOHNORT, INDEX_WOHNORT_BEGINN, INDEX_WOHNORT_ENDE, bausteinWert));
		felder.add(parseFeld(FeldNameDBAN.STRASSE, INDEX_STRASSE_BEGINN, INDEX_STRASSE_ENDE, bausteinWert));
		felder.add(parseFeld(FeldNameDBAN.HAUS_NR, INDEX_HAUSNR_BEGINN, INDEX_HAUSNR_ENDE, bausteinWert));
		felder.add(parseFeld(FeldNameDBAN.ADR_ZUSATZ, INDEX_ADRZUSATZ_BEGINN, INDEX_ADRZUSATZ_ENDE, bausteinWert));

		return new Baustein<FeldNameDBAN>(BausteinName.DBAN, felder);
	}

	@Override
	public int getLaenge(final String datensatz) {
		return LAENGE_DBAN;
	}

	@Override
	public Fehler<? extends FehlerNummer> getFehlerBausteinNichtVorhanden() {
		return new Fehler<FehlerNummer>(FehlerNummerDSME.DSME933);
	}
}
