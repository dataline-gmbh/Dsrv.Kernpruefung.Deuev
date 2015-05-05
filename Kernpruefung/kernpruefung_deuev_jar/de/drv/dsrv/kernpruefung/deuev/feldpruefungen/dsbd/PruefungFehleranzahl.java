package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd;

import java.util.Arrays;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSBD;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSBD;

/**
 * Pruefung fuer das Feld Fehleranzahl aus dem Baustein DSBD.
 */
public class PruefungFehleranzahl extends AbstractFeldPruefung<FeldNameDSBD, FehlerNummerDSBD> {

	private final Feld<FeldNameDSBD> feldFehlerkennzeichnung;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldFehlerkennzeichnung
	 *            Feld Fehlerkennzeichnung aus DSBD
	 */
	public PruefungFehleranzahl(final Feld<FeldNameDSBD> feld, final Feld<FeldNameDSBD> feldFehlerkennzeichnung) {
		super(feld);

		this.feldFehlerkennzeichnung = feldFehlerkennzeichnung;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch bd120 = new PruefungNumerisch(getFeld(), true);
		addPruefung(bd120, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD120));

		if (feldFehlerkennzeichnung.getTrimmedValue().compareTo("0") == 0) {
			final PruefungInList bd122 = new PruefungInList(Arrays.asList("0"), getFeld());
			addPruefung(bd122, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD122));
		}
	}
}