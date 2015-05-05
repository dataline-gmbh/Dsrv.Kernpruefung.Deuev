package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Datensatz;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld MMAN aus dem Baustein DSME.
 */
public class PruefungMman extends AbstractPruefungSchalterleisteFeld<FeldNameDSME, FehlerNummerDSME> {

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param datensatz
	 *            Datensatz DSME
	 */
	public PruefungMman(final Feld<FeldNameDSME> feld, final Datensatz datensatz) {
		super(feld, datensatz);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		addPruefungenOptional(FehlerNummerDSME.DSME290, FehlerNummerDSME.DSME933, BausteinName.DBAN);

		// Die Pr�fung ist nicht im Programm realisiert, da aufgrund der Pr�fungen DSME236 (nur GD 99) und DSME248 nur die Kombination der
		// Datenbausteine gem. Anlage 4 zul�ssig sind. Es w�re daher ein fehlerhafter Sachverhalt mit zwei gleich
		// lautenden Meldungen abzuweisen.

		// if ((vfmm.compareTo("BATRV") == 0) || (vfmm.compareTo("KTTRV") == 0)) {
		// final PruefungInList me294 = new PruefungInList(MUSS_J, getFeld(), true);
		// addPruefung(me294, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME294));
		// }
	}

}
