package de.drv.dsrv.kernpruefung.deuev.pruefer.baustein;

import java.util.ArrayList;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractBausteinPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbso.PruefungKennzStorno;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbso.PruefungZeitraumBeginnSofort;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBSO;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBSO;

/**
 * Der Bausteinpruefer fuer den Baustein DBSO. Ruft alle Feldpruefungen auf.
 */
public class BausteinPrueferDBSO extends AbstractBausteinPruefer<FeldNameDBSO, FehlerNummerDBSO> {

	/**
	 * Konstruktor. Nimmt alle Werte entgegen, die fuer die Feld-Pruefungen in
	 * dem Baustein benoetigt werden, und speichert diese ab.
	 * 
	 * @param baustein
	 *            Baustein DBSO
	 */
	public BausteinPrueferDBSO(final Baustein<FeldNameDBSO> baustein) {
		super(baustein);
	}

	@Override
	public List<FeldPruefung<FeldNameDBSO, FehlerNummerDBSO>> getPruefungen()
			throws UngueltigePrueferDatenException {

		final List<FeldPruefung<FeldNameDBSO, FehlerNummerDBSO>> pruefList = new ArrayList<FeldPruefung<FeldNameDBSO, FehlerNummerDBSO>>();
		final Baustein<FeldNameDBSO> baustein = getBaustein();

		final Feld<FeldNameDBSO> feldKennzstso = baustein.getFeld(FeldNameDBSO.KENNZ_STORNO_SOFORT);
		final Feld<FeldNameDBSO> feldZeitraum = baustein.getFeld(FeldNameDBSO.ZEITRAUM_BEGINN_SOFORT);

		if ((feldKennzstso == null) || (feldZeitraum == null)) {
			throw new UngueltigePrueferDatenException("Pruefung fuer den Baustein " + baustein.getName()
					+ " kann nicht initalisiert werden, einer der Felder ist nicht vorhanden: " + baustein);
		}

		pruefList.add(new PruefungKennzStorno(feldKennzstso));
		pruefList.add(new PruefungZeitraumBeginnSofort(feldZeitraum));

		return pruefList;
	}

}
