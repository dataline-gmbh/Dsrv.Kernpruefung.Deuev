package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbeu;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBEU;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBEU;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.ListenTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Pruefung fuer das Feld mit Geburtsland.
 */
public class PruefungGeburtsland extends AbstractFeldPruefung<FeldNameDBEU, FehlerNummerDBEU> {
	
	private final transient List<String> schlsslgueltig = WertelistenVerwaltungDeuev.getInstance().getWerteliste(
			ListenTypDeuev.A8_STAATSSCHLUESSEL);
	
	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            das zu pruefende Feld
	 */
	public PruefungGeburtsland(final Feld<FeldNameDBEU> feld) {
		super(feld);
	}
	
	/**
	 * Initialisiere die feldinternen Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer eu010a = new PruefungNichtLeer(this.getFeld());
		this.addPruefung(eu010a, new Fehler<FehlerNummerDBEU>(FehlerNummerDBEU.DBEU010));
		
		final PruefungNumerisch eu010b = new PruefungNumerisch(this.getFeld(), true);
		this.addPruefung(eu010b, new Fehler<FehlerNummerDBEU>(FehlerNummerDBEU.DBEU010));
		
		final PruefungInList eu012 = new PruefungInList(this.schlsslgueltig, this.getFeld());
		this.addPruefung(eu012, new Fehler<FehlerNummerDBEU>(FehlerNummerDBEU.DBEU012));
	}
}
