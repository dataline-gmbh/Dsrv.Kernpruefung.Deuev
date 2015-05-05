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
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBRG;

/**
 * Parser fuer den Baustein DBRG.
 */
public class BausteinParserDBRG extends AbstractBausteinParser<FeldNameDBRG> {

	private static final Logger LOGGER = Logger.getLogger(BausteinParserDBRG.class.getName());

	private static final int INDEX_KENNUNG_BEGINN = 0;
	private static final int INDEX_KENNUNG_ENDE = 4;

	private static final int INDEX_ZAEHLER_BEGINN = 206;
	private static final int INDEX_ZAEHLER_ENDE = 208;

	private static final int LAENGE_EINMALIGER_TEIL = 208;
	private static final int LAENGE_VARIABLER_TEIL = 206;

	@Override
	public Baustein<FeldNameDBRG> parse(final String datensatz) throws DatensatzAufbauException {
		if (datensatz == null || berechneLaenge(datensatz) != datensatz.length()) {
			throw new DatensatzAufbauException("Fehler beim Lesen des Bausteins DBRG. Die Laenge ist falsch oder null.");
		}

		final ArrayList<Feld<FeldNameDBRG>> felder = new ArrayList<Feld<FeldNameDBRG>>();
		felder.add(parseFeld(FeldNameDBRG.KENNUNG, INDEX_KENNUNG_BEGINN, INDEX_KENNUNG_ENDE, datensatz));
		felder.add(parseFeld(FeldNameDBRG.ZAEHLER, INDEX_ZAEHLER_BEGINN, INDEX_ZAEHLER_ENDE, datensatz));

		return new Baustein<FeldNameDBRG>(BausteinName.DBRG, felder);
	}

	/**
	 * Berechnet die Laenge des Datensatzes.
	 * 
	 * @param offset
	 * @param datensatz
	 * 
	 * @return
	 * @throws DatensatzAufbauException
	 */
	private int berechneLaenge(final String datensatz) throws DatensatzAufbauException {
		int laenge = -1;

		if (datensatz == null) {
			LOGGER.log(Level.WARNING, "Fehler beim Lesen des Bausteins DBRG. Der Datensatz ist null.");
			throw new DatensatzAufbauException("Fehler beim Lesen des Bausteins DBRG. Der Datensatz ist null.");
		}

		// Laenge berechnen und ueberpruefen, ob der String diese hat.
		try {
			final int wiederholungen = Integer.parseInt(datensatz.substring(INDEX_ZAEHLER_BEGINN, INDEX_ZAEHLER_ENDE));
			laenge = LAENGE_EINMALIGER_TEIL + wiederholungen * LAENGE_VARIABLER_TEIL;

		} catch (final NumberFormatException e) {
			LOGGER.log(Level.WARNING, "Das Feld ZAEHLER aus dem Baustein DBRG ist nicht numerisch.");
			throw new DatensatzAufbauException("Das Feld ZAEHLER aus dem Baustein DBRG ist nicht numerisch.");
		} catch (final IndexOutOfBoundsException e) {
			LOGGER.log(Level.WARNING, "Fehler beim Lesen des Bausteins DBRG. Die Laenge ist falsch. Datensatz: >"
					+ datensatz + "<");
			throw new DatensatzAufbauException("Fehler beim Lesen des Bausteins DBRG. Die Laenge ist falsch.");
		}

		return laenge;
	}

	@Override
	public int getLaenge(final String datensatz) throws DatensatzAufbauException {
		return berechneLaenge(datensatz);
	}

	@Override
	public Fehler<? extends FehlerNummer> getFehlerBausteinNichtVorhanden() {
		return new Fehler<FehlerNummer>(FehlerNummerDSME.DSME939);
	}

}
