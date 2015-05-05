package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbme;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLeerNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld Anzahl Tage.
 */
public class PruefungZahlTage extends AbstractFeldPruefung<FeldNameDBME, FehlerNummerDBME> {
	
	private static final List<String> MELD_074 = Arrays.asList("01", "02", "03", "04", "05", "06");
	
	private static final String KRZBESCH = "202";
	
	private transient Feld<FeldNameDSME> feldDSMEPersgr;
	
	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            das Feld Anzahl Tage
	 * @param bausteinDSME
	 *            der Baustein DSME
	 */
	public PruefungZahlTage(final Feld<FeldNameDBME> feld, final Baustein<FeldNameDSME> bausteinDSME) {
		super(feld);
		if (bausteinDSME != null) {
			this.feldDSMEPersgr = bausteinDSME.getFeld(FeldNameDSME.PERSONENGRUPPE);
		}
	}
	
	/**
	 * Initialisiere die bausteinuebergreifenden Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {
		if (this.isPruefbar(Arrays.asList(FehlerNummerDBME.DBME072, FehlerNummerDBME.DBME074), this.feldDSMEPersgr)) {
			if (KRZBESCH.equals(this.feldDSMEPersgr.getTrimmedValue())) {
				final PruefungInList me074 = new PruefungInList(MELD_074, this.getFeld());
				this.addPruefungBausteinUebergreifend(me074, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME074));
			} else {
				final PruefungLeerNumerisch me072 = new PruefungLeerNumerisch(this.getFeld());
				this.addPruefungBausteinUebergreifend(me072, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME072));
			}
		}
	}
	
	/**
	 * Initialisiere die feldinternen Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer me070a = new PruefungNichtLeer(this.getFeld());
		this.addPruefung(me070a, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME070));
		
		final PruefungNumerisch me070b = new PruefungNumerisch(this.getFeld(), true);
		this.addPruefung(me070b, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME070));
	}
}
