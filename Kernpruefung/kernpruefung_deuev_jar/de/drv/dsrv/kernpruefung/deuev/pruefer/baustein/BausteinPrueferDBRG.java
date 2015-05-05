package de.drv.dsrv.kernpruefung.deuev.pruefer.baustein;

import java.util.ArrayList;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractBausteinPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbrg.PruefungZaehler;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBRG;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBRG;

/**
 * Der Bausteinpruefer fuer den Baustein DBRG. Ruft alle Feldpruefungen auf.
 */
public class BausteinPrueferDBRG extends AbstractBausteinPruefer<FeldNameDBRG, FehlerNummerDBRG> {

	/**
	 * Konstruktor. Nimmt alle Werte entgegen, die fuer die Feld-Pruefungen in
	 * dem Baustein benoetigt werden, und speichert diese ab.
	 * 
	 * @param baustein
	 *            Baustein DBRG
	 */
	public BausteinPrueferDBRG(final Baustein<FeldNameDBRG> baustein) {
		super(baustein);
	}

	@Override
	public List<FeldPruefung<FeldNameDBRG, FehlerNummerDBRG>> getPruefungen()
			throws UngueltigePrueferDatenException {

		final List<FeldPruefung<FeldNameDBRG, FehlerNummerDBRG>> pruefList = new ArrayList<FeldPruefung<FeldNameDBRG, FehlerNummerDBRG>>();
		final Baustein<FeldNameDBRG> baustein = getBaustein();

		final Feld<FeldNameDBRG> feldZaehler = baustein.getFeld(FeldNameDBRG.ZAEHLER);

		if (feldZaehler == null) {
			throw new UngueltigePrueferDatenException("Pruefung fuer den Baustein " + baustein.getName()
					+ " kann nicht initalisiert werden, einer der Felder ist nicht vorhanden: " + baustein);
		}

		pruefList.add(new PruefungZaehler(feldZaehler));

		return pruefList;
	}

}
