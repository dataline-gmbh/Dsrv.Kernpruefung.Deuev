package de.drv.dsrv.kernpruefung.deuev.parser.baustein;

import java.util.ArrayList;

import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.parser.AbstractBausteinParser;
import de.drv.dsrv.kernpruefung.basis.parser.DatensatzAufbauException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSBD;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBTN;

/**
 * Parser fuer den Baustein DBTN.
 */
public class BausteinParserDBTN extends AbstractBausteinParser<FeldNameDBTN> {

	private static final int INDEX_KENNUNG_BEGINN = 0;
	private static final int INDEX_KENNUNG_ENDE = 4;

	private static final int INDEX_SOFORTMELDEPFLICHT_BEGINN = INDEX_KENNUNG_ENDE;
	private static final int INDEX_SOFORTMELDEPFLICHT_ENDE = 5;

	private static final int INDEX_ENTSCHEIDUNG_SO_BEGINN = INDEX_SOFORTMELDEPFLICHT_ENDE;
	private static final int INDEX_ENTSCHEIDUNG_SO_ENDE = 13;

	private static final int INDEX_GUELTIGKEIT_SO_BEGINN = INDEX_ENTSCHEIDUNG_SO_ENDE;
	private static final int INDEX_GUELTIGKEIT_SO_ENDE = 21;

	private static final int INDEX_INSOLVENZ_GELD_BEGINN = 36;
	private static final int INDEX_INSOLVENZ_GELD_ENDE = 37;

	private static final int INDEX_ENTSCHEIDUNG_IU_BEGINN = INDEX_INSOLVENZ_GELD_ENDE;
	private static final int INDEX_ENTSCHEIDUNG_IU_ENDE = 45;

	private static final int INDEX_GUELTIGKEIT_IU_BEGINN = INDEX_ENTSCHEIDUNG_IU_ENDE;
	private static final int INDEX_GUELTIGKEIT_IU_ENDE = 53;

	private static final int INDEX_UMLAGEPFLICHT_U1_BEGINN = 68;
	private static final int INDEX_UMLAGEPFLICHT_U1_ENDE = 69;

	private static final int INDEX_DATUM_ENTSCHEIDUNG_U1_BEGINN = INDEX_UMLAGEPFLICHT_U1_ENDE;
	private static final int INDEX_DATUM_ENTSCHEIDUNG_U1_ENDE = 77;

	private static final int INDEX_GUELTIGKEIT_U1_BEGINN = INDEX_DATUM_ENTSCHEIDUNG_U1_ENDE;
	private static final int INDEX_GUELTIGKEIT_U1_ENDE = 85;

	private static final int INDEX_RESERVE_BEGINN = 100;
	private static final int INDEX_RESERVE_ENDE = 108;

	private static final int LAENGE = 108;

	@Override
	public Baustein<FeldNameDBTN> parse(final String datensatz) throws DatensatzAufbauException {
		final ArrayList<Feld<FeldNameDBTN>> felder = new ArrayList<Feld<FeldNameDBTN>>();
		felder.add(parseFeld(FeldNameDBTN.KENNUNG, INDEX_KENNUNG_BEGINN, INDEX_KENNUNG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBTN.SOFORTMELDEPFLICHT, INDEX_SOFORTMELDEPFLICHT_BEGINN,
				INDEX_SOFORTMELDEPFLICHT_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBTN.ENTSCHEIDUNG_SO, INDEX_ENTSCHEIDUNG_SO_BEGINN, INDEX_ENTSCHEIDUNG_SO_ENDE,
				datensatz));
		felder.add(parseFeld(FeldNameDBTN.GUELTIGKEIT_SO, INDEX_GUELTIGKEIT_SO_BEGINN, INDEX_GUELTIGKEIT_SO_ENDE,
				datensatz));
		felder.add(parseFeld(FeldNameDBTN.INSOLVENZGELD, INDEX_INSOLVENZ_GELD_BEGINN, INDEX_INSOLVENZ_GELD_ENDE,
				datensatz));
		felder.add(parseFeld(FeldNameDBTN.ENTSCHEIDUNG_IU, INDEX_ENTSCHEIDUNG_IU_BEGINN, INDEX_ENTSCHEIDUNG_IU_ENDE,
				datensatz));
		felder.add(parseFeld(FeldNameDBTN.GUELTIGKEIT_IU, INDEX_GUELTIGKEIT_IU_BEGINN, INDEX_GUELTIGKEIT_IU_ENDE,
				datensatz));
		felder.add(parseFeld(FeldNameDBTN.UMLAGEPFLICHT_U1, INDEX_UMLAGEPFLICHT_U1_BEGINN, INDEX_UMLAGEPFLICHT_U1_ENDE,
				datensatz));
		felder.add(parseFeld(FeldNameDBTN.DATUM_ENTSCHEIDUNG_U1, INDEX_DATUM_ENTSCHEIDUNG_U1_BEGINN,
				INDEX_DATUM_ENTSCHEIDUNG_U1_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBTN.GUELTIGKEIT_U1, INDEX_GUELTIGKEIT_U1_BEGINN, INDEX_GUELTIGKEIT_U1_ENDE,
				datensatz));
		felder.add(parseFeld(FeldNameDBTN.RESERVE, INDEX_RESERVE_BEGINN, INDEX_RESERVE_ENDE, datensatz));

		return new Baustein<FeldNameDBTN>(BausteinName.DBTN, felder);
	}

	@Override
	public int getLaenge(final String datensatz) throws DatensatzAufbauException {
		return LAENGE;
	}

	@Override
	public Fehler<? extends FehlerNummer> getFehlerBausteinNichtVorhanden() {
		return new Fehler<FehlerNummer>(FehlerNummerDSBD.DSBD932);
	}

	
	
}
