package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbna;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
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
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungVorNachBindestrichenKeineLeerzeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.Sonderzeichen;
import de.drv.dsrv.kernpruefung.basis.utils.Zeichen;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBNA;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBNA;

/**
 * Pruefung fuer das Feld Familienname.
 */
public class PruefungFamilienname extends AbstractFeldPruefung<FeldNameDBNA, FehlerNummerDBNA> {
	
	private static final List<Character> ERLAUBTE_ZEICHEN_NA014 = Arrays.asList(' ', '-', '\'', '(', ')', '.');
	private static final List<Character> ERLAUBTE_ZEICHEN_NA020 = Arrays.asList(Sonderzeichen.APOSTROPHE);
	private static final List<Character> ERLAUBTE_ZEICHEN_NA022 = Arrays.asList(')', '.', Sonderzeichen.APOSTROPHE);
	private static final List<Character> VERBOTENE_ZEICHEN = Arrays.asList(Zeichen.LATIN_SMALL_LETTER_SHARP_S);
	private static final List<Character> ZURECHT_NICHT_VORHANDENER_NAME = Arrays.asList('+');
	
	/**
	 * Pruefung fuer das Feld Familienname.
	 * 
	 * @param feld
	 *            das Feld Familienname
	 * @param vfmm
	 *            das Verfahrensmerkmal
	 */
	public PruefungFamilienname(final Feld<FeldNameDBNA> feld) {
		super(feld);
	}
	
	/**
	 * Initialisiere die feldinternen Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		
		final PruefungNichtLeer na005 = new PruefungNichtLeer(this.getFeld());
		this.addPruefung(na005, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA005));
		
		if (this.getFeld().getValue().indexOf(ZURECHT_NICHT_VORHANDENER_NAME.get(0)) >= 0 && this.getFeld().getTrimmedValue().length() > 1) {
			final PruefungBeginntMitZeichenUndEndetMitLeer na021 = new PruefungBeginntMitZeichenUndEndetMitLeer(ZURECHT_NICHT_VORHANDENER_NAME, this.getFeld());
			this.addPruefung(na021, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA021));
			
		} else if(this.getFeld().getValue().indexOf(ZURECHT_NICHT_VORHANDENER_NAME.get(0)) == -1) {
			final PruefungMehrfachGleichesSonderOderLeerzeichen na010 = new PruefungMehrfachGleichesSonderOderLeerzeichen(
					this.getFeld());
			this.addPruefung(na010, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA010));
			
			final PruefungDreiGleicheAnfangsBuchstaben na011 = new PruefungDreiGleicheAnfangsBuchstaben(this.getFeld());
			this.addPruefung(na011, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA011));
			
			final PruefungVorNachBindestrichenKeineLeerzeichen na012 = new PruefungVorNachBindestrichenKeineLeerzeichen(
					this.getFeld());
			this.addPruefung(na012, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA012));
			
			final PruefungBuchstabenZiffernZeichenPunkt na014 = new PruefungBuchstabenZiffernZeichenPunkt(
					ERLAUBTE_ZEICHEN_NA014, this.getFeld());
			this.addPruefung(na014, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA014));
			
			final PruefungMehrfachZiffern na015 = new PruefungMehrfachZiffern(this.getFeld());
			this.addPruefung(na015, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA015));
			
			final PruefungLeerzeichenVorZiffer na018 = new PruefungLeerzeichenVorZiffer(this.getFeld());
			this.addPruefung(na018, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA018));
			
			final PruefungBeginntMitBuchstabeZeichen na020a = new PruefungBeginntMitBuchstabeZeichen(
					ERLAUBTE_ZEICHEN_NA020, this.getFeld());
			this.addPruefung(na020a, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA020));
			
			final PruefungBeginntNichtMitZeichen na020b = new PruefungBeginntNichtMitZeichen(VERBOTENE_ZEICHEN,
					this.getFeld());
			this.addPruefung(na020b, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA020));
			  
			
			final PruefungEndetMitBuchstabeZifferZeichen na022 = new PruefungEndetMitBuchstabeZifferZeichen(this.getFeld(),
					ERLAUBTE_ZEICHEN_NA022);
			this.addPruefung(na022, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA022));
		}
	}
	
}
