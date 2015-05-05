package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbez;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBEZ;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBEZ;

/**
 * Pruefung fuer das Feld Abgabegrund aus dem Baustein DBEZ.
 */
public class PruefungAbgabegrund extends AbstractFeldPruefung<FeldNameDBEZ, FehlerNummerDBEZ> {

	private static final List<String> ZULAESSIGE_WERTE = Arrays.asList("02", "03", "04");

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungAbgabegrund(final Feld<FeldNameDBEZ> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch ez030 = new PruefungNumerisch(getFeld(), true);
		addPruefung(ez030, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ030));

		final PruefungInList ez032 = new PruefungInList(ZULAESSIGE_WERTE, getFeld());
		addPruefung(ez032, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ032));
	}

}
