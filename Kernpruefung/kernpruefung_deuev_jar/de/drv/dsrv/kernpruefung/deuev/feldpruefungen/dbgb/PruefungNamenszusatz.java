package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbgb;

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
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBGB;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBGB;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.ListenTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Pruefung fuer das Feld Namenszusatz.
 */
public class PruefungNamenszusatz extends AbstractFeldPruefung<FeldNameDBGB, FehlerNummerDBGB> {
	
	private static final List<Character> ERLAUBTE_ZEICHEN = Arrays.asList(' ', '\'', '.');
	private final transient List<String> zusatzliste = WertelistenVerwaltungDeuev.getInstance().getWerteliste(
			ListenTypDeuev.A7_NAMENSZUSAETZE);
	
	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem Namenszusatz
	 */
	public PruefungNamenszusatz(final Feld<FeldNameDBGB> feld) {
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
			
			final PruefungMehrfachGleichesSonderOderLeerzeichen gb060 = new PruefungMehrfachGleichesSonderOderLeerzeichen(
					this.getFeld());
			this.addPruefung(gb060, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB060));
			
			final PruefungBuchstabenZeichen gb064 = new PruefungBuchstabenZeichen(ERLAUBTE_ZEICHEN, this.getFeld());
			this.addPruefung(gb064, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB064));
			
			final PruefungBeginntMitBuchstabe gb066 = new PruefungBeginntMitBuchstabe(this.getFeld());
			this.addPruefung(gb066, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB066));
			
			final PruefungPunktOhneBuchstaben gb068 = new PruefungPunktOhneBuchstaben(this.getFeld());
			this.addPruefung(gb068, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB068));
			
			final PruefungInList gb070 = new PruefungInList(this.zusatzliste, this.getFeld());
			this.addPruefung(gb070, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB070));
		}
	}
}
