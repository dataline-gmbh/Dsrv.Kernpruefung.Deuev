package de.drv.dsrv.kernpruefung.deuev.parser.baustein;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.parser.AbstractBausteinParser;
import de.drv.dsrv.kernpruefung.basis.parser.DatensatzAufbauException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBUV;

/**
 * Parser fuer den Baustein DBUV.
 */
public class BausteinParserDBUV extends AbstractBausteinParser<FeldNameDBUV> {

	private static final Logger LOGGER = Logger.getLogger(BausteinParserDBUV.class.getName());

	private static final int LAENGE_KENNUNG = 4;
	private static final int LAENGE_ANZAHL_UV = 1;
	private static final int LAENGE_RESERVE = 15;
	private static final int LAENGE_UV_GRUND = 3;
	private static final int LAENGE_BBNR_UV = 15;
	private static final int LAENGE_MITGLIEDS_NR = 20;
	private static final int LAENGE_BBNR_GTS = 15;
	private static final int LAENGE_GT_STELLE = 8;
	private static final int LAENGE_UV_EG = 6;
	private static final int LAENGE_ARBSTD = 4;

	private static final int LAENGE_VARIABLER_TEIL = 71;
	private static final int LAENGE_FESTER_TEIL = 20;

	private int offset;
	private static final int INDEX_ANZAHL = 4;

	@Override
	public Baustein<FeldNameDBUV> parse(final String datensatz) throws DatensatzAufbauException {

		if ((datensatz == null) || (datensatz.length() < LAENGE_KENNUNG + LAENGE_ANZAHL_UV)) {
			throw new DatensatzAufbauException(
					"Fehler beim Einlesen des Datensatzes, Baustein DBUV. Datensatz nicht vollstaendig.");
		}

		offset = LAENGE_KENNUNG;

		final ArrayList<Feld<FeldNameDBUV>> felder = new ArrayList<Feld<FeldNameDBUV>>();

		final int anzahl = getAnzahlWiederholung(datensatz.substring(offset, offset + LAENGE_ANZAHL_UV));
		felder.add(parseFeld(FeldNameDBUV.ANZAHL_UV, offset, offset + LAENGE_ANZAHL_UV, datensatz));
		offset += LAENGE_ANZAHL_UV;

		felder.add(parseFeld(FeldNameDBUV.RESERVE, offset, offset + LAENGE_RESERVE, datensatz));
		offset += LAENGE_RESERVE;

		int indexEnum = FeldNameDBUV.UV_GRUND_1.ordinal();

		for (int i = 0; i < anzahl; ++i) {
			felder.add(parseFeld(FeldNameDBUV.values()[indexEnum], offset, offset + LAENGE_UV_GRUND, datensatz));
			offset += LAENGE_UV_GRUND;
			++indexEnum;

			felder.add(parseFeld(FeldNameDBUV.values()[indexEnum], offset, offset + LAENGE_BBNR_UV, datensatz));
			offset += LAENGE_BBNR_UV;
			++indexEnum;

			felder.add(parseFeld(FeldNameDBUV.values()[indexEnum], offset, offset + LAENGE_MITGLIEDS_NR, datensatz));
			offset += LAENGE_MITGLIEDS_NR;
			++indexEnum;

			felder.add(parseFeld(FeldNameDBUV.values()[indexEnum], offset, offset + LAENGE_BBNR_GTS, datensatz));
			offset += LAENGE_BBNR_GTS;
			++indexEnum;

			felder.add(parseFeld(FeldNameDBUV.values()[indexEnum], offset, offset + LAENGE_GT_STELLE, datensatz));
			offset += LAENGE_GT_STELLE;
			++indexEnum;

			felder.add(parseFeld(FeldNameDBUV.values()[indexEnum], offset, offset + LAENGE_UV_EG, datensatz));
			offset += LAENGE_UV_EG;
			++indexEnum;

			felder.add(parseFeld(FeldNameDBUV.values()[indexEnum], offset, offset + LAENGE_ARBSTD, datensatz));
			offset += LAENGE_ARBSTD;
			++indexEnum;
		}

		return new Baustein<FeldNameDBUV>(BausteinName.DBUV, felder);
	}

	private int getAnzahlWiederholung(final String feldAnzahlUv) throws DatensatzAufbauException {
		int anzahl = 0;

		try {
			anzahl = Integer.parseInt(feldAnzahlUv);
		} catch (final NumberFormatException e) {
			LOGGER.log(Level.WARNING, "Das Feld ANZAHL-UV aus dem Baustein DBUV ist nicht numerisch: " + feldAnzahlUv
					+ ".");
			anzahl = -1;
			throw new DatensatzAufbauException("Das Feld ANZAHL-UV aus dem Baustein DBUV ist nicht numerisch: "
					+ feldAnzahlUv + ".");
		}

		return anzahl;
	}

	@Override
	public int getLaenge(final String datensatz) throws DatensatzAufbauException {
		int laenge = 0;

		if (datensatz == null) {
			LOGGER.log(Level.WARNING, "Fehler beim Lesen des Bausteins DBUV (getLaenge). Der Datensatz ist null.");
			throw new DatensatzAufbauException("Fehler beim Lesen des Bausteins DBUV. Der Datensatz ist null.");
		}

		if (datensatz.length() < INDEX_ANZAHL + LAENGE_ANZAHL_UV) {
			LOGGER.log(Level.WARNING, "Der Datensatz ist zu kurz. Das Feld DBUV ANZAHL-UV ist nicht vorhanden.");
			throw new DatensatzAufbauException(
					"Der Datensatz ist zu kurz. Das Feld DBUV ANZAHL-UV ist nicht vorhanden.");
		}
		laenge = LAENGE_FESTER_TEIL
				+ getAnzahlWiederholung(datensatz.substring(INDEX_ANZAHL, INDEX_ANZAHL + LAENGE_ANZAHL_UV))
				* LAENGE_VARIABLER_TEIL;

		return laenge;
	}

	@Override
	public Fehler<? extends FehlerNummer> getFehlerBausteinNichtVorhanden() {
		return new Fehler<FehlerNummer>(FehlerNummerDSME.DSME935);
	}

}
