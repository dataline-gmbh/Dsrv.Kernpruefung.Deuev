package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd;

import java.util.Arrays;
import java.util.Map;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSBD;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSBD;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.MapTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Pruefung fuer das Feld Versicherungsnummer aus dem Baustein DSBD.
 */
public class PruefungVersionsnummer extends AbstractFeldPruefung<FeldNameDSBD, FehlerNummerDSBD> {

	private final String version;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungVersionsnummer(final Feld<FeldNameDSBD> feld) {
		super(feld);

		final Map<String, String> konstanten = WertelistenVerwaltungDeuev.getInstance().getMap(
				MapTypDeuev.DEUEV);
		version = konstanten.get("dsbd.version");
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch bd060 = new PruefungNumerisch(getFeld(), true);
		addPruefung(bd060, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD060));

		final PruefungInList bd062 = new PruefungInList(Arrays.asList(version), getFeld());
		addPruefung(bd062, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD062));
	}

}
