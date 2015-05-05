package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbna;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBeginntMitBuchstabe;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBuchstabenZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungMehrfachGleichesSonderOderLeerzeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungPunktOhneBuchstaben;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBNA;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBNA;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.ListenTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Pruefung fuer das Feld Namenszusatz.
 */
public class PruefungNamenszusatz extends AbstractFeldPruefung<FeldNameDBNA, FehlerNummerDBNA> {
	
	private final transient List<Character> erlaubteZeichen = Arrays.asList(' ', '\'', '.');
	private static final List<String> ZUSATZLISTE = WertelistenVerwaltungDeuev.getInstance().getWerteliste(
			ListenTypDeuev.A7_NAMENSZUSAETZE);
	
	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem Namenszusatz
	 */
	public PruefungNamenszusatz(final Feld<FeldNameDBNA> feld) {
		super(feld);
	}
	
	/**
	 * Initialisiere die feldinternen Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		if (this.getFeld() != null && StringUtils.isNotEmpty(this.getFeld().getTrimmedValue())) {
			
			final PruefungMehrfachGleichesSonderOderLeerzeichen na060 = new PruefungMehrfachGleichesSonderOderLeerzeichen(
					this.getFeld());
			this.addPruefung(na060, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA060));
			
			final PruefungBuchstabenZeichen na064 = new PruefungBuchstabenZeichen(this.erlaubteZeichen, this.getFeld());
			this.addPruefung(na064, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA064));
			
			final PruefungBeginntMitBuchstabe na066 = new PruefungBeginntMitBuchstabe(this.getFeld());
			this.addPruefung(na066, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA066));
			
			final PruefungPunktOhneBuchstaben na068 = new PruefungPunktOhneBuchstaben(this.getFeld());
			this.addPruefung(na068, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA068));
			
			final PruefungInList na070 = new PruefungInList(ZUSATZLISTE, this.getFeld());
			this.addPruefung(na070, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA070));
		}
	}
}
