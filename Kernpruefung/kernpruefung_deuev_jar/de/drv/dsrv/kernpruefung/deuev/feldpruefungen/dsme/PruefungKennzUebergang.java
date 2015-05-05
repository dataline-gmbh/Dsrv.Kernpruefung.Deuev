package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld Kenzeichen Uebergang aus dem Baustein DSME.
 */
public class PruefungKennzUebergang extends AbstractFeldPruefung<FeldNameDSME, FehlerNummerDSME> {

	private static final List<String> ERLAUBTE_ZEICHEN = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "A");
	private static final List<String> ERLAUBTE_ZEICHEN_OHNE_KOMMUNGEN = Arrays.asList("1", "2", "3", "4", "5", "6",
			"7", "A");

	private static final List<String> VFMM_GRUNDSTELLUNG = Arrays.asList("BWTRV", "BZTRV", "ZFTRV", "PVTRV");
	private static final List<String> VFMM_GRUNDSTELLUNG_OHNE_KOMMUNEN = Arrays.asList("BATRV", "RVTBA", "DSTBF",
			"BFTDS");
	private static final List<String> VFMM_GRUNDSTELLUNG_KOMMUNE = Arrays.asList("KTTRV", "RVTKT", "DSTBF", "BFTDS");

	private static final String KOMMUNE = "8";
	private final String vfmm;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public PruefungKennzUebergang(final Feld<FeldNameDSME> feld, final String vfmm) {
		super(feld);

		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final Feld<FeldNameDSME> feldVfmm = new Feld<FeldNameDSME>(vfmm);

		// Grundstellung ist erlaubt, wuerde in der Ueberpruefung aber eine
		// UngueltigeDatenException werfen, da das Leerzeichen weggetrimmt wird.
		if (getFeld().getTrimmedValue().length() > 0) {
			final PruefungInList me360 = new PruefungInList(ERLAUBTE_ZEICHEN, getFeld(), true);
			addPruefung(me360, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME360));
		}

		if (VFMM_GRUNDSTELLUNG.contains(vfmm)) {
			final PruefungLaenge me361 = new PruefungLaenge(0, getFeld());
			addPruefung(me361, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME361));
		}

		if (ERLAUBTE_ZEICHEN_OHNE_KOMMUNGEN.contains(getFeld().getTrimmedValue())) {
			final PruefungInList me362 = new PruefungInList(VFMM_GRUNDSTELLUNG_OHNE_KOMMUNEN, feldVfmm, true);
			addPruefung(me362, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME362));
		}

		if (getFeld().getTrimmedValue().compareTo(KOMMUNE) == 0) {
			final PruefungInList me365 = new PruefungInList(VFMM_GRUNDSTELLUNG_KOMMUNE, feldVfmm, true);
			addPruefung(me365, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME365));
		}
	}
}
