package de.drv.dsrv.kernpruefung.deuev.parser.baustein;

import java.util.ArrayList;

import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.parser.AbstractBausteinParser;
import de.drv.dsrv.kernpruefung.basis.parser.DatensatzAufbauException;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameVNIF;

/**
 * Bausteinparser VNIF.
 */
public class BausteinParserVNIF extends AbstractBausteinParser<FeldNameVNIF> {

	private static final int LAENGE_VNIF = 9;

	@Override
	public Baustein<FeldNameVNIF> parse(final String datensatz) throws DatensatzAufbauException {
		final ArrayList<Feld<FeldNameVNIF>> felder = new ArrayList<Feld<FeldNameVNIF>>();

		return new Baustein<FeldNameVNIF>(BausteinName.VNIF, felder);
	}

	@Override
	public int getLaenge(final String datensatz) throws DatensatzAufbauException {
		return LAENGE_VNIF;
	}

	@Override
	public Fehler<? extends FehlerNummer> getFehlerBausteinNichtVorhanden() {
		return null;
	}

}
