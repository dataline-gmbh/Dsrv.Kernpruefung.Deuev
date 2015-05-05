package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;
import de.drv.dsrv.kernpruefung.deuev.pruefungen.PruefungBetriebsnummer;

/**
 * Pruefung fuer das Feld Betriebsnummer Absender aus dem Baustein DSME.
 */
public class PruefungBetriebsnummerAbsender extends AbstractFeldPruefung<FeldNameDSME, FehlerNummerDSME> {

	private static final List<String> VFMM_BATRV = Arrays.asList("76641777");
	private static final List<String> VFMM_BWTRV = Arrays.asList("32349289");
	private static final List<String> VFMM_BZTRV = Arrays.asList("38065304");
	private static final List<String> VFMM_PVTRV = Arrays.asList("996");
	private static final List<String> VFMM_KSTRV = Arrays.asList("28180427");
	private static final List<String> VFMM_ZFTRV = Arrays.asList("02998824");

	private static final int INDEX_START = 0;
	private static final int INDEX_ENDE = 3;
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
	public PruefungBetriebsnummerAbsender(final Feld<FeldNameDSME> feld, final String vfmm) {
		super(feld);
		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungBetriebsnummer me020 = new PruefungBetriebsnummer(getFeld());
		addPruefung(me020, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME020));

		if (vfmm.compareTo("BATRV") == 0) {
			final PruefungInList me022 = new PruefungInList(VFMM_BATRV, getFeld(), true);
			addPruefung(me022, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME022));
		}

		else if (vfmm.compareTo("BWTRV") == 0) {
			final PruefungInList me022 = new PruefungInList(VFMM_BWTRV, getFeld(), true);
			addPruefung(me022, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME022));
		}

		else if (vfmm.compareTo("BZTRV") == 0) {
			final PruefungInList me022 = new PruefungInList(VFMM_BZTRV, getFeld(), true);
			addPruefung(me022, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME022));
		}

		else if (vfmm.compareTo("PVTRV") == 0) {
			final Feld<FeldNameDSME> feldErstenDrei;
			if (getFeld().getTrimmedValue().length() > INDEX_ENDE) {
				feldErstenDrei = new Feld<FeldNameDSME>(getFeld().getTrimmedValue().substring(INDEX_START, INDEX_ENDE));
			} else {
				// Der Fall wird nie eintreffen, da in Pruefung 020 bereits die
				// Laenge ueberprueft wird. Die Unterscheidung muss trotzdem
				// hier stehen, da es ansonsten zu einer
				// IndexOutOfBounds-Exception kommen kann.
				feldErstenDrei = new Feld<FeldNameDSME>("invalid");
			}
			final PruefungInList me022 = new PruefungInList(VFMM_PVTRV, feldErstenDrei, true);
			addPruefung(me022, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME022));
		}

		else if (vfmm.compareTo("KSTRV") == 0) {
			final PruefungInList me022 = new PruefungInList(VFMM_KSTRV, getFeld(), true);
			addPruefung(me022, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME022));
		}

		else if (vfmm.compareTo("ZFTRV") == 0) {
			final PruefungInList me022 = new PruefungInList(VFMM_ZFTRV, getFeld(), true);
			addPruefung(me022, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME022));
		}
	}


}
