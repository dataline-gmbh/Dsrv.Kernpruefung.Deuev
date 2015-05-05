package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbez;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInterval;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNotInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBEZ;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBEZ;

/**
 * Pruefung fuer das Feld Leistungsart aus dem Baustein DBEZ.
 */
public class PruefungLeistungsart extends AbstractFeldPruefung<FeldNameDBEZ, FehlerNummerDBEZ> {

	private static final List<String> LEAT_UNZULAESSIGE_WERTE = Arrays.asList("05", "08", "10", "11", "14",
			"15", "16", "17", "18", "19", "20", "24", "34", "35", "36", "37", "38", "39", "47", "48", "49");
	private static final List<String> LEAT_KRANKENKASSE = Arrays.asList("00", "01", "04", "07", "12", "13");
	private static final List<String> LEAT_BA = Arrays.asList("21", "22", "23", "25", "27", "28", "29", "30", "31",
			"32", "33", "40", "41", "42", "43", "44", "45", "46", "50");
	private static final List<String> LEAT_KOMMUNE = Arrays.asList("43", "44");
	private static final List<String> LEAT_PFLEGE = Arrays.asList("12", "13");
	private static final List<String> LEAT_SOTBF = Arrays.asList("26");
	private static final List<String> LEAT_UEBERGANGSGELD = Arrays.asList("03", "06", "09");

	private static final int MIN = 0;
	private static final int MAX = 50;

	private static final List<String> VFMM_KRANKENKASSE = Arrays.asList("KVTWL", "KVTRV");

	private final String vfmm;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            das zu pruefende Feld
	 * @param vfmm
	 *            OpCode aus den Eingabedaten
	 */
	public PruefungLeistungsart(final Feld<FeldNameDBEZ> feld, final String vfmm) {
		super(feld);

		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer ez020a = new PruefungNichtLeer(getFeld());
		addPruefung(ez020a, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ020));

		final PruefungNumerisch ez020b = new PruefungNumerisch(getFeld(), true);
		addPruefung(ez020b, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ020));

		final PruefungInterval ez020c = new PruefungInterval(MIN, MAX, getFeld());
		addPruefung(ez020c, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ020));

		final PruefungNotInList ez020d = new PruefungNotInList(LEAT_UNZULAESSIGE_WERTE, getFeld());
		addPruefung(ez020d, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ020));

		if ("PVTRV".equals(vfmm)) {
			final PruefungInList ez021 = new PruefungInList(LEAT_PFLEGE, getFeld());
			addPruefung(ez021, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ021));

		} else if (VFMM_KRANKENKASSE.contains(vfmm)) {
			final PruefungInList ez022 = new PruefungInList(LEAT_KRANKENKASSE, getFeld());
			addPruefung(ez022, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ022));

		} else if ("BATRV".equals(vfmm)) {
			final PruefungInList ez024 = new PruefungInList(LEAT_BA, getFeld());
			addPruefung(ez024, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ024));

		} else if ("KTTRV".equals(vfmm)) {
			final PruefungInList ez025 = new PruefungInList(LEAT_KOMMUNE, getFeld());
			addPruefung(ez025, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ025));

		} else if ("SOTBF".equals(vfmm)) {
			final PruefungInList ez028 = new PruefungInList(LEAT_SOTBF, getFeld());
			addPruefung(ez028, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ028));

		} else if ("UETBF".equals(vfmm)) {
			final PruefungInList ez029 = new PruefungInList(LEAT_UEBERGANGSGELD, getFeld());
			addPruefung(ez029, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ029));
		}
	}
}
