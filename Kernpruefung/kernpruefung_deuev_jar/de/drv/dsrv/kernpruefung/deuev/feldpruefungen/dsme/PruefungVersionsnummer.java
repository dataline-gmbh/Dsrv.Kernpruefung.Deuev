package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import java.util.Arrays;
import java.util.Map;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.MapTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Pruefung fuer das Feld Versionsnummer aus dem Baustein DSME.
 */
public class PruefungVersionsnummer extends AbstractFeldPruefung<FeldNameDSME, FehlerNummerDSME> {

	private final String version;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungVersionsnummer(final Feld<FeldNameDSME> feld) {
		super(feld);

		final Map<String, String> konstanten = WertelistenVerwaltungDeuev.getInstance().getMap(
				MapTypDeuev.DEUEV);
		version = konstanten.get("dsme.version");
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch me040b = new PruefungNumerisch(getFeld(), true);
		addPruefung(me040b, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME040));

		final PruefungInList me042 = new PruefungInList(Arrays.asList(version), getFeld());
		addPruefung(me042, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME042));
	}

}
