package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbgb;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBeginntMitBuchstabe;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBuchstabenZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInListVosa;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungMehrfachGleichesSonderOderLeerzeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungPunktOhneBuchstaben;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBGB;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBGB;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.ListenTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Pruefung fuer das Feld Vorsatzwort.
 */
public class PruefungVorsatzwort extends AbstractFeldPruefung<FeldNameDBGB, FehlerNummerDBGB> {
	
	private static final List<Character> ERLAUBTE_ZEICHEN = Arrays.asList(' ', '\'', '.');
	private final transient List<String> vorsatzworte = WertelistenVerwaltungDeuev.getInstance().getWerteliste(
			ListenTypDeuev.A6_VORSATZWORTE_Cobol);
	
	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem Vorsatzwort
	 */
	public PruefungVorsatzwort(final Feld<FeldNameDBGB> feld) {
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
			
			final PruefungMehrfachGleichesSonderOderLeerzeichen gb040 = new PruefungMehrfachGleichesSonderOderLeerzeichen(
					this.getFeld());
			this.addPruefung(gb040, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB040));
			
			final PruefungBuchstabenZeichen gb044 = new PruefungBuchstabenZeichen(ERLAUBTE_ZEICHEN, this.getFeld());
			this.addPruefung(gb044, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB044));
			
			final PruefungBeginntMitBuchstabe gb046 = new PruefungBeginntMitBuchstabe(this.getFeld());
			this.addPruefung(gb046, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB046));
			
			final PruefungPunktOhneBuchstaben gb048 = new PruefungPunktOhneBuchstaben(this.getFeld());
			this.addPruefung(gb048, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB048));
			
			final PruefungInListVosa gb050 = new PruefungInListVosa(this.vorsatzworte, this.getFeld());
			this.addPruefung(gb050, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB050));
		}
	}
}
