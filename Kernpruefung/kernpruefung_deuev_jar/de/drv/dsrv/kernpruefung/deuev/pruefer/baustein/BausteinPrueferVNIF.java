package de.drv.dsrv.kernpruefung.deuev.pruefer.baustein;

import java.util.ArrayList;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractBausteinPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerVNIF;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameVNIF;

/**
 * Der Bausteinpruefer fuer den Baustein VNIF.
 */
public class BausteinPrueferVNIF extends AbstractBausteinPruefer<FeldNameVNIF, FehlerNummerVNIF> {

	/**
	 * Konstruktor.
	 * 
	 * @param baustein
	 *            Baustein VNIF
	 */
	public BausteinPrueferVNIF(final Baustein<FeldNameVNIF> baustein) {
		super(baustein);
	}

	@Override
	public List<FeldPruefung<FeldNameVNIF, FehlerNummerVNIF>> getPruefungen() throws UngueltigePrueferDatenException {
		final List<FeldPruefung<FeldNameVNIF, FehlerNummerVNIF>> pruefList = new ArrayList<FeldPruefung<FeldNameVNIF, FehlerNummerVNIF>>();

		return pruefList;
	}
}
