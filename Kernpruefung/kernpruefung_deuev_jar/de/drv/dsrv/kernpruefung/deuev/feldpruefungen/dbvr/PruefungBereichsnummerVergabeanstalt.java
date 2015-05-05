package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbvr;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBVR;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBVR;

/**
 * Pruefung fuer das Feld Bereichsnummer Vergabeanstalt aus dem Baustein DBVR.
 */
public class PruefungBereichsnummerVergabeanstalt extends AbstractFeldPruefung<FeldNameDBVR, FehlerNummerDBVR> {

	private static final List<String> ZULAESSIGE_WERTE = Arrays.asList("00", "02", "03", "04", "08", "09", "10", "11",
			"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "23", "24", "25", "26", "28", "29", "38", "39",
			"40", "42", "43", "44", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61",
			"63", "64", "65", "66", "68", "69", "78", "79", "80", "81", "82", "89");

	private static final List<String> VFMM_ZFA = Arrays.asList("ZFTRV", "RVTZF");
	private static final String BEREICHSNUMMER_ZFA = "40";

	private final String vfmm;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmal)
	 */
	public PruefungBereichsnummerVergabeanstalt(final Feld<FeldNameDBVR> feld, final String vfmm) {
		super(feld);

		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch vr030 = new PruefungNumerisch(getFeld(), true);
		addPruefung(vr030, new Fehler<FehlerNummerDBVR>(FehlerNummerDBVR.DBVR030));

		final PruefungInList vr032 = new PruefungInList(ZULAESSIGE_WERTE, getFeld());
		addPruefung(vr032, new Fehler<FehlerNummerDBVR>(FehlerNummerDBVR.DBVR032));

		if (getFeld().getTrimmedValue().compareTo(BEREICHSNUMMER_ZFA) == 0) {
			final Feld<FeldNameDBVR> feldVfmm = new Feld<FeldNameDBVR>(vfmm);
			final PruefungInList vr034 = new PruefungInList(VFMM_ZFA, feldVfmm, true);
			addPruefung(vr034, new Fehler<FehlerNummerDBVR>(FehlerNummerDBVR.DBVR034));
		}
	}

}
