package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbna;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBeginntMitBuchstabe;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBuchstabenZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDreiGleicheAnfangsBuchstaben;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungEndetMitBuchstabeZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungMehrfachGleichesSonderOderLeerzeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungPunktOhneBuchstaben;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungVorNachBindestrichenKeineLeerzeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.Sonderzeichen;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBNA;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBNA;

/**
 * Pruefung fuer das Feld Titel.
 */
public class PruefungTitel extends AbstractFeldPruefung<FeldNameDBNA, FehlerNummerDBNA> {
	
	private static final List<Character> ERLAUBTE_ZEICHEN_NA084 = Arrays.asList(' ', '.', Sonderzeichen.HYPHEN_MINUS,
			'(', ')');
	private static final List<Character> ERLAUBTE_ZEICHEN_NA089 = Arrays.asList('.', ')');;
	
	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem Titel
	 */
	public PruefungTitel(final Feld<FeldNameDBNA> feld) {
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
			final PruefungMehrfachGleichesSonderOderLeerzeichen na080 = new PruefungMehrfachGleichesSonderOderLeerzeichen(
					this.getFeld());
			this.addPruefung(na080, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA080));
			
			final PruefungDreiGleicheAnfangsBuchstaben na081 = new PruefungDreiGleicheAnfangsBuchstaben(this.getFeld());
			this.addPruefung(na081, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA081));
			
			final PruefungVorNachBindestrichenKeineLeerzeichen na082 = new PruefungVorNachBindestrichenKeineLeerzeichen(
					this.getFeld());
			this.addPruefung(na082, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA082));
			
			final PruefungBuchstabenZeichen na084 = new PruefungBuchstabenZeichen(ERLAUBTE_ZEICHEN_NA084,
					this.getFeld());
			this.addPruefung(na084, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA084));
			
			final PruefungBeginntMitBuchstabe na086 = new PruefungBeginntMitBuchstabe(this.getFeld());
			this.addPruefung(na086, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA086));
			
			final PruefungPunktOhneBuchstaben na088 = new PruefungPunktOhneBuchstaben(this.getFeld());
			this.addPruefung(na088, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA088));
			
			final PruefungEndetMitBuchstabeZeichen na089 = new PruefungEndetMitBuchstabeZeichen(this.getFeld(),
					ERLAUBTE_ZEICHEN_NA089);
			this.addPruefung(na089, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA089));
		}
	}
}
