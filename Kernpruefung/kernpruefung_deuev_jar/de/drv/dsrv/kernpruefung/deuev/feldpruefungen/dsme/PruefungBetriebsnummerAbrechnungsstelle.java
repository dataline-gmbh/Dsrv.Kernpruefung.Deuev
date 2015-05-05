package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNotInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;
import de.drv.dsrv.kernpruefung.deuev.pruefungen.PruefungBetriebsnummer;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.ListenTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Pruefung fuer das Feld Betriebsnummer Abrechnungsstelle aus dem Baustein
 * DSME.
 */
public class PruefungBetriebsnummerAbrechnungsstelle extends AbstractFeldPruefung<FeldNameDSME, FehlerNummerDSME> {

	private final List<String> testBbnr;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungBetriebsnummerAbrechnungsstelle(final Feld<FeldNameDSME> feld) {
		super(feld);

		this.testBbnr = WertelistenVerwaltungDeuev.getInstance().getWerteliste(ListenTypDeuev.TESTBETRIEBSNUMMERN);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		if (getFeld().getTrimmedValue().length() > 0) {
			final PruefungBetriebsnummer me190 = new PruefungBetriebsnummer(getFeld());
			addPruefung(me190, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME190));

			final PruefungNotInList me195 = new PruefungNotInList(testBbnr, getFeld());
			addPruefung(me195, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME195));
		}
	}

}
