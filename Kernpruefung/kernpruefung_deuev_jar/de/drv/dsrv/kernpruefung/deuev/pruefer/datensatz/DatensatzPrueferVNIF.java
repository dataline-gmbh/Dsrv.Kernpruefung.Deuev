package de.drv.dsrv.kernpruefung.deuev.pruefer.datensatz;

import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Datensatz;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractDatensatzPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameVNIF;
import de.drv.dsrv.kernpruefung.deuev.pruefer.baustein.BausteinPrueferVNIF;

/**
 * Der Bausteinpruefer fuer den Baustein VNIF. Ruft alle Feldpruefungen auf.
 */
public class DatensatzPrueferVNIF extends AbstractDatensatzPruefer {

	@SuppressWarnings("unchecked")
	@Override
	public void initialisierePruefungen(final Datensatz datensatz) throws UngueltigePrueferDatenException {
		final Baustein<FeldNameVNIF> bausteinVNIF = (Baustein<FeldNameVNIF>) datensatz.getBaustein(BausteinName.VNIF);

		if (bausteinVNIF == null) {
			throw new UngueltigePrueferDatenException("Pruefung kann nicht durchgefuehrt werden "
					+ ", Baustein VNIF nicht vorhanden ");
		} else {
			setPrueferSteuersatz(new BausteinPrueferVNIF(bausteinVNIF));
		}
	}

}
