package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNotInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSBD;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSBD;

/**
 * Pruefung fuer das Feld Abgabegrund aus dem Baustein DSBD.
 */
public class PruefungAbgabegrund extends AbstractFeldPruefung<FeldNameDSBD, FehlerNummerDSBD> {

	private static final List<String> ZULAESSIGE_WERTE = Arrays.asList("01", "02", "03", "11", "12", "13", "14", "15",
			"16", "17", "18");
	private static final List<String> WERTE_01_02 = Arrays.asList("01", "02");
	private static final List<String> VF_0102_UNZULAESSIG = Arrays.asList("BTRAG", "BTRKV", "BTRRV");

	private final Feld<FeldNameDSBD> feldVerfahren;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldVerfahren
	 *            Feld Verfahren aus DBVR
	 */
	public PruefungAbgabegrund(final Feld<FeldNameDSBD> feld, final Feld<FeldNameDSBD> feldVerfahren) {
		super(feld);

		this.feldVerfahren = feldVerfahren;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch bd200 = new PruefungNumerisch(getFeld(), true);
		addPruefung(bd200, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD200));

		final PruefungInList bd202 = new PruefungInList(ZULAESSIGE_WERTE, getFeld());
		addPruefung(bd202, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD202));

		if (feldVerfahren.getTrimmedValue().compareTo("BTRKS") == 0) {
			final PruefungInList bd204 = new PruefungInList(WERTE_01_02, getFeld());
			addPruefung(bd204, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD204));
		}

		else if (VF_0102_UNZULAESSIG.contains(feldVerfahren.getTrimmedValue())) {
			final PruefungNotInList bd206 = new PruefungNotInList(WERTE_01_02, getFeld());
			addPruefung(bd206, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD206));
		}

		if (getFeld().getTrimmedValue().compareTo("03") == 0) {
			final PruefungInList bd208 = new PruefungInList(Arrays.asList("BTRKV"), feldVerfahren, true);
			addPruefung(bd208, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD208));
		}
	}
}
