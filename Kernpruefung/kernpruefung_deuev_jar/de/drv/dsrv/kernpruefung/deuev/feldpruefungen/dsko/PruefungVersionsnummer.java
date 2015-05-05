package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsko;

import java.util.Arrays;
import java.util.Map;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSKO;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSKO;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.MapTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Pruefung fuer das Feld Versionsnummer aus dem Baustein DSKO.
 */
public class PruefungVersionsnummer extends AbstractFeldPruefung<FeldNameDSKO, FehlerNummerDSKO> {

	private final String version;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungVersionsnummer(final Feld<FeldNameDSKO> feld) {
		super(feld);

		final Map<String, String> konstanten = WertelistenVerwaltungDeuev.getInstance().getMap(
				MapTypDeuev.DEUEV);
		version = konstanten.get("dsko.version");
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch ks040b = new PruefungNumerisch(getFeld(), true);
		addPruefung(ks040b, new Fehler<FehlerNummerDSKO>(FehlerNummerDSKO.DSKO040));

		final PruefungInList me042 = new PruefungInList(Arrays.asList(version), getFeld());
		addPruefung(me042, new Fehler<FehlerNummerDSKO>(FehlerNummerDSKO.DSKO042));
	}

}
