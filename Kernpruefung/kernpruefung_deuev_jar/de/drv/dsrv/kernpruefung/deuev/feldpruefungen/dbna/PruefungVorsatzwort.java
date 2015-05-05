package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbna;

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
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBNA;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBNA;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.ListenTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Pruefung fuer das Feld Vorsatzwort.
 */
public class PruefungVorsatzwort extends AbstractFeldPruefung<FeldNameDBNA, FehlerNummerDBNA> {
	
	private final transient List<Character> erlaubteZeichen = Arrays.asList(' ', '\'', '.');
	private final transient List<String> vorsatzworte = WertelistenVerwaltungDeuev.getInstance().getWerteliste(
			ListenTypDeuev.A6_VORSATZWORTE_Cobol);
	
	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem Vorsatzwort
	 */
	public PruefungVorsatzwort(final Feld<FeldNameDBNA> feld) {
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
			
			// Pruefungen definieren, die nacheinander ausgefuehrt werden
			final PruefungMehrfachGleichesSonderOderLeerzeichen na040 = new PruefungMehrfachGleichesSonderOderLeerzeichen(this.getFeld());
			this.addPruefung(na040, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA040));
			
			final PruefungBuchstabenZeichen na044 = new PruefungBuchstabenZeichen(this.erlaubteZeichen, this.getFeld());
			this.addPruefung(na044, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA044));
			
			final PruefungBeginntMitBuchstabe na046 = new PruefungBeginntMitBuchstabe(this.getFeld());
			this.addPruefung(na046, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA046));
			
			final PruefungPunktOhneBuchstaben na048 = new PruefungPunktOhneBuchstaben(this.getFeld());
			this.addPruefung(na048, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA048));
			
			// TODO Damit Java wie Cobol läuft, darf nur das erste Vorsatzwort in der Zeichenkette gegen die VOSA-Liste geprüft werden
			final PruefungInListVosa na050 = new PruefungInListVosa(this.vorsatzworte, this.getFeld());
			this.addPruefung(na050, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA050));
		}
	}
}
