package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSBD;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSBD;
import de.drv.dsrv.kernpruefung.deuev.pruefungen.PruefungBetriebsnummer;

/**
 * Pruefung fuer das Feld Betriebsnummer Abrechnungsstelle aus dem Baustein
 * DSBD.
 */
public class PruefungBetriebsnummerAbrechnungsstelle extends AbstractFeldPruefung<FeldNameDSBD, FehlerNummerDSBD> {

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungBetriebsnummerAbrechnungsstelle(final Feld<FeldNameDSBD> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		if (getFeld().getTrimmedValue().length() > 0) {
			final PruefungBetriebsnummer bd180 = new PruefungBetriebsnummer(getFeld());
			addPruefung(bd180, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD180));
		}
	}
}
