package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae;

import java.util.Arrays;
import java.util.Map;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSAE;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.MapTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Pruefung fuer das Feld Versionsnummer aus dem Baustein DSAE.
 */
public class PruefungVersionsnummer extends AbstractFeldPruefung<FeldNameDSAE, FehlerNummerDSAE> {

	private final String version;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld
	 */
	public PruefungVersionsnummer(final Feld<FeldNameDSAE> feld) {
		super(feld);

		final Map<String, String> konstanten = WertelistenVerwaltungDeuev.getInstance().getMap(
				MapTypDeuev.DEUEV);
		version = konstanten.get("dsae.version");
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch ae040 = new PruefungNumerisch(getFeld(), true);
		addPruefung(ae040, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE040));

		final PruefungInList ae042 = new PruefungInList(Arrays.asList(version), getFeld());
		addPruefung(ae042, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE042));
	}
}
