package de.drv.dsrv.kernpruefung.deuev.pruefer.baustein;

import java.util.ArrayList;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractBausteinPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbeu.PruefungGeburtsland;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBEU;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBEU;

/**
 * Registriert und liefert alle Pruefungen, die fuer die Felder des Bausteins
 * DBEU definiert wurden.
 */
public class BausteinPrueferDBEU extends AbstractBausteinPruefer<FeldNameDBEU, FehlerNummerDBEU> {
	
	/**
	 * Konstruktor.
	 * 
	 * @param baustein
	 *            Baustein, fuer den die Pruefungen registriert werden
	 */
	public BausteinPrueferDBEU(final Baustein<FeldNameDBEU> baustein) {
		super(baustein);
	}
	
	@Override
	public List<FeldPruefung<FeldNameDBEU, FehlerNummerDBEU>> getPruefungen() throws UngueltigePrueferDatenException {
		
		final List<FeldPruefung<FeldNameDBEU, FehlerNummerDBEU>> pruefList = new ArrayList<FeldPruefung<FeldNameDBEU, FehlerNummerDBEU>>();
		final Baustein<FeldNameDBEU> baustein = this.getBaustein();
		
		final Feld<FeldNameDBEU> feldGbLand = baustein.getFeld(FeldNameDBEU.GB_LAND);
		
		if (feldGbLand == null) {
			throw new UngueltigePrueferDatenException("Pruefung fuer den Baustein " + baustein.getName()
					+ " kann nicht initalisiert werden, einer der Felder ist nicht vorhanden: " + baustein);
		} else {
			pruefList.add(new PruefungGeburtsland(feldGbLand));
		}
		
		return pruefList;
	}
	
}
