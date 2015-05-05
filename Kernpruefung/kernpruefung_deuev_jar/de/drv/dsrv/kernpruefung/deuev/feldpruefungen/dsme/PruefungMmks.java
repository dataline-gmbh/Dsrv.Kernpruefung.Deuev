package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Datensatz;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNotInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.FeldPruefungStornierungDsme;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld MMKS aus dem Baustein DSME.
 */
public class PruefungMmks extends AbstractPruefungSchalterleisteFeld<FeldNameDSME, FehlerNummerDSME> {

	private static final List<String> MUSS_N = Arrays.asList("N");
	private static final List<String> MUSS_J = Arrays.asList("J");
	private static final List<String> VFMM_N = Arrays.asList("BATRV", "KTTRV", "BWTRV", "BZTRV", "PVTRV", "KSTRV",
			"KSTKV");
	private static final List<String> BBNRVU_J = Arrays.asList("098", "099", "990", "980", "991", "992");
	private static final List<String> BBNRVU_MMME = Arrays.asList("099", "990", "991", "992");
	private static final int BBNRVU_ERSTEN_DREI_ZEICHEN = 3;

	private static final List<String> PERSGR_J_MMME = Arrays.asList("140", "141", "142", "143", "144", "149");
	private static final List<String> PERSGR_J = Arrays.asList("109", "110", "190");
	private final Feld<FeldNameDSME> feldBbnrvu;
	private final Feld<FeldNameDSME> feldMmme;
	private final Feld<FeldNameDSME> feldPersgr;
	private final String vfmm;
	private final Baustein<FeldNameDBME> bausteinDbme;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldBbnrvu
	 *            Feld Betriebsnummer-Verursacher aus DSME
	 * @param feldMmme
	 *            Feld MMME aus DSME
	 * @param feldPersgr
	 *            Feld Personengruppe aus DSME
	 * @param datensatz
	 *            Datensatz DSME
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	@SuppressWarnings("unchecked")
	public PruefungMmks(final Feld<FeldNameDSME> feld, final Feld<FeldNameDSME> feldBbnrvu,
			final Feld<FeldNameDSME> feldMmme, final Feld<FeldNameDSME> feldPersgr, final Datensatz datensatz,
			final String vfmm) {
		super(feld, datensatz);

		this.feldBbnrvu = feldBbnrvu;
		this.feldMmme = feldMmme;
		this.feldPersgr = feldPersgr;
		this.vfmm = vfmm;
		bausteinDbme = (Baustein<FeldNameDBME>) datensatz.getBaustein(BausteinName.DBME);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		addPruefungenOptional(FehlerNummerDSME.DSME320, FehlerNummerDSME.DSME936, BausteinName.DBKS);

		final String bbnrvuStart;
		if (feldBbnrvu.getTrimmedValue().length() >= BBNRVU_ERSTEN_DREI_ZEICHEN) {
			bbnrvuStart = feldBbnrvu.getTrimmedValue().substring(0, BBNRVU_ERSTEN_DREI_ZEICHEN);
		} else {
			bbnrvuStart = "";
		}

		final Feld<FeldNameDSME> feldBbnrvuStart = new Feld<FeldNameDSME>(bbnrvuStart);

		if (VFMM_N.contains(vfmm)) {
			final PruefungInList me322 = new PruefungInList(MUSS_N, getFeld(), true);
			addPruefung(me322, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME322));
		}

		if ((vfmm.compareTo("AGDEU") == 0) && (getFeld().getTrimmedValue().compareTo("J") == 0)) {
			final PruefungNichtLeer me324a = new PruefungNichtLeer(feldBbnrvuStart);
			addPruefung(me324a, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME324));

			final PruefungInList me324b = new PruefungInList(BBNRVU_J, feldBbnrvuStart);
			addPruefung(me324b, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME324));
		}

		final FeldPruefungStornierungDsme stornierung = new FeldPruefungStornierungDsme(bausteinDbme);
		if (!stornierung.isStornierungDbme()) {
			if ((feldMmme.getTrimmedValue().compareTo("J") == 0)
					&& PERSGR_J_MMME.contains(feldPersgr.getTrimmedValue())) {
				final PruefungInList me325 = new PruefungInList(MUSS_J, getFeld(), true);
				addPruefung(me325, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME325));
			}

			if (PERSGR_J.contains(feldPersgr.getTrimmedValue())) {
				final PruefungNotInList me326 = new PruefungNotInList(MUSS_J, getFeld(), true);
				addPruefung(me326, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME326));
			}

			if (BBNRVU_MMME.contains(bbnrvuStart) && (getFeld().getTrimmedValue().compareTo("J") == 0)) {
				final PruefungInList me327 = new PruefungInList(PERSGR_J_MMME, feldPersgr);
				addPruefung(me327, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME327));
			}

			if ((feldMmme.getTrimmedValue().compareTo("J") == 0)
					&& ((bbnrvuStart.compareTo("098") == 0) || (bbnrvuStart.compareTo("980") == 0))
					&& !PERSGR_J.contains(feldPersgr.getTrimmedValue())) {
				final PruefungInList me328 = new PruefungInList(MUSS_J, getFeld(), true);
				addPruefung(me328, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME328));
			}
		}
	}
}
