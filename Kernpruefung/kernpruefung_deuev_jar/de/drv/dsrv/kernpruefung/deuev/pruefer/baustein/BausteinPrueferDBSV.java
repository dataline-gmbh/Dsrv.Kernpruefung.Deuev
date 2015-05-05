package de.drv.dsrv.kernpruefung.deuev.pruefer.baustein;

import java.util.ArrayList;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractBausteinPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbsv.PruefungKennzeichenSvAusweis;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBSV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBSV;

/**
 * Der Bausteinpruefer fuer den Baustein DBSV. Ruft alle Feldpruefungen auf.
 */
public class BausteinPrueferDBSV extends AbstractBausteinPruefer<FeldNameDBSV, FehlerNummerDBSV> {

	/**
	 * Konstruktor. Nimmt alle Werte entgegen, die fuer die Feld-Pruefungen in
	 * dem Baustein benoetigt werden, und speichert diese ab.
	 * 
	 * @param baustein
	 *            Baustein DBSV
	 */
	public BausteinPrueferDBSV(final Baustein<FeldNameDBSV> baustein) {
		super(baustein);
	}

	@Override
	public List<FeldPruefung<FeldNameDBSV, FehlerNummerDBSV>> getPruefungen()
			throws UngueltigePrueferDatenException {

		final List<FeldPruefung<FeldNameDBSV, FehlerNummerDBSV>> pruefList = new ArrayList<FeldPruefung<FeldNameDBSV, FehlerNummerDBSV>>();
		final Baustein<FeldNameDBSV> baustein = getBaustein();

		final Feld<FeldNameDBSV> feldKennzeichenAusweis = baustein.getFeld(FeldNameDBSV.KENNZ_SVA);

		if (feldKennzeichenAusweis == null) {
			throw new UngueltigePrueferDatenException("Pruefung fuer den Baustein " + baustein.getName()
					+ " kann nicht initalisiert werden, einer der Felder ist nicht vorhanden: " + baustein);
		}

		pruefList.add(new PruefungKennzeichenSvAusweis(feldKennzeichenAusweis));

		return pruefList;
	}

}
