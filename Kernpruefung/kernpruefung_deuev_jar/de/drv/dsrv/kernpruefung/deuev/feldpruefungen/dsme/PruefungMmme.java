package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Datensatz;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld MMME aus dem Baustein DSME.
 */
public class PruefungMmme extends AbstractPruefungSchalterleisteFeld<FeldNameDSME, FehlerNummerDSME> {

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param datensatz
	 *            Datensatz DSME
	 */
	public PruefungMmme(final Feld<FeldNameDSME> feld, final Datensatz datensatz) {
		super(feld, datensatz);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		addPruefungenOptional(FehlerNummerDSME.DSME260, FehlerNummerDSME.DSME930, BausteinName.DBME);

		// Die Prüfung ist nicht im Programm realisiert, da aufgrund der Prüfungen DSME236 (nur GD 99) und DSME248 nur die Kombination der
		// Datenbausteine gem. Anlage 4 zulässig sind. Es wäre daher ein fehlerhafter Sachverhalt mit zwei gleich
		// lautenden Meldungen abzuweisen.

		// if ((vfmm.compareTo("BATRV") == 0) || (vfmm.compareTo("KTTRV") == 0)) {
		// final PruefungInList me264 = new PruefungInList(MUSS_N, getFeld(), true);
		// addPruefung(me264, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME264));
		// }
	}
}
