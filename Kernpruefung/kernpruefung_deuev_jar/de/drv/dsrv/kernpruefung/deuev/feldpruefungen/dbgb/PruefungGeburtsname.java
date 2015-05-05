package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbgb;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBeginntMitBuchstabeZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBeginntMitZeichenUndEndetMitLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBeginntNichtMitZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBuchstabenZiffernZeichenPunkt;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDreiGleicheAnfangsBuchstaben;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungEndetMitBuchstabeZifferZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLeerzeichenVorZiffer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungMehrfachGleichesSonderOderLeerzeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungMehrfachZiffern;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungVorNachBindestrichenKeineLeerzeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.Sonderzeichen;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.basis.utils.Zeichen;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBGB;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBGB;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBNA;

/**
 * Pruefung fuer das Feld mit dem Geburtsnamen.
 */
public class PruefungGeburtsname extends AbstractFeldPruefung<FeldNameDBGB, FehlerNummerDBGB> {
	
	private static final List<Character> ERLAUBT_GB014 = Arrays.asList(' ', '-', '\'', '(', ')', '.');
	private static final List<Character> ERLAUBT_GB020 = Arrays.asList(Sonderzeichen.APOSTROPHE);
	private static final List<Character> ERLAUBT_GB022 = Arrays.asList(')', '.', Sonderzeichen.APOSTROPHE);
	private static final List<Character> VERBOTEN_GB020 = Arrays.asList(Zeichen.LATIN_SMALL_LETTER_SHARP_S);
	private static final List<Character> ZURECHT_NICHT_VORHANDENER_NAME = Arrays.asList('+');
//	feld im konstruktor noch mappen
	private final transient Baustein<FeldNameDBNA> bausteinDbna;
	
	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            das Feld Geburtsname
	 * @param vfmm
	 *            der OPCODE VFMM
	 */
	public PruefungGeburtsname(final Feld<FeldNameDBGB> feld, final Baustein<FeldNameDBNA> bausteinDBNA) {
		super(feld);
		
		this.bausteinDbna = bausteinDBNA;
	}
	
	/**
	 * Initialisiere die feldinternen Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		
		
		if (this.getFeld() != null && StringUtils.isNotBlank(this.getFeld().getValue())) {
			
			if (this.getFeld().getValue().indexOf(ZURECHT_NICHT_VORHANDENER_NAME.get(0)) >= 0 && this.getFeld().getTrimmedValue().length() >= 1) {
				//eigene Prüfung erstellen für beginnt mit zeichen und rest muss leer sein
				final PruefungBeginntMitZeichenUndEndetMitLeer gb021 = new PruefungBeginntMitZeichenUndEndetMitLeer(ZURECHT_NICHT_VORHANDENER_NAME, this.getFeld());
				this.addPruefung(gb021, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB021));
				
				final Feld<FeldNameDBNA> feldVorname = bausteinDbna.getFeld(FeldNameDBNA.VORNAME);
				if(isPruefbar(FehlerNummerDBGB.DBGB024, feldVorname)) {
					final PruefungBeginntNichtMitZeichen gb024 = new PruefungBeginntNichtMitZeichen(ZURECHT_NICHT_VORHANDENER_NAME, feldVorname);
					this.addPruefung(gb024, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB024));
				}
			} else if (this.getFeld().getValue().indexOf(ZURECHT_NICHT_VORHANDENER_NAME.get(0)) == -1) {
				final PruefungMehrfachGleichesSonderOderLeerzeichen gb010 = new PruefungMehrfachGleichesSonderOderLeerzeichen(
						this.getFeld());
				this.addPruefung(gb010, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB010));
				
				final PruefungDreiGleicheAnfangsBuchstaben gb011 = new PruefungDreiGleicheAnfangsBuchstaben(this.getFeld());
				this.addPruefung(gb011, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB011));
				
				final PruefungVorNachBindestrichenKeineLeerzeichen gb012 = new PruefungVorNachBindestrichenKeineLeerzeichen(
						this.getFeld());
				this.addPruefung(gb012, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB012));
				
				final PruefungBuchstabenZiffernZeichenPunkt gb014 = new PruefungBuchstabenZiffernZeichenPunkt(
						ERLAUBT_GB014, this.getFeld());
				this.addPruefung(gb014, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB014));
				
				final PruefungMehrfachZiffern gb015 = new PruefungMehrfachZiffern(this.getFeld());
				this.addPruefung(gb015, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB015));
				
				final PruefungLeerzeichenVorZiffer gb018 = new PruefungLeerzeichenVorZiffer(this.getFeld());
				this.addPruefung(gb018, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB018));
				
				final PruefungBeginntMitBuchstabeZeichen gb020bst = new PruefungBeginntMitBuchstabeZeichen(ERLAUBT_GB020,
						this.getFeld());
				this.addPruefung(gb020bst, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB020));
				
				final PruefungBeginntNichtMitZeichen gb020szett = new PruefungBeginntNichtMitZeichen(VERBOTEN_GB020,
						this.getFeld());
				this.addPruefung(gb020szett, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB020));
				
				final PruefungEndetMitBuchstabeZifferZeichen gb022 = new PruefungEndetMitBuchstabeZifferZeichen(
						this.getFeld(), ERLAUBT_GB022);
				this.addPruefung(gb022, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB022));
			}
		}
	}
}
