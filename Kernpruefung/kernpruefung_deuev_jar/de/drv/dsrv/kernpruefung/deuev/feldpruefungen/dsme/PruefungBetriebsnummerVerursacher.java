package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNotInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;
import de.drv.dsrv.kernpruefung.deuev.pruefungen.PruefungBetriebsnummer;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.ListenTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Pruefung fuer das Feld Betriebsnummer Verursacher aus dem Baustein DSME.
 */
public class PruefungBetriebsnummerVerursacher extends AbstractFeldPruefung<FeldNameDSME, FehlerNummerDSME> {

	private static final List<String> ERLAUBTER_ANFANG = Arrays.asList("980", "098");
	private static final List<String> ERLAUBTE_BETRIEBSNR_BWTRV = Arrays.asList("32349289");
	private static final List<String> ERLAUBTE_BETRIEBSNR_BZTRV = Arrays.asList("38065304");
	private static final List<String> ERLAUBTER_ANFANG_PVTRV = Arrays.asList("996");
	private static final List<String> ERLAUBTE_BETRIEBSNR_KSTRV_KSTKV = Arrays.asList("01085914", "28180427");
	private static final List<String> ERLAUBTE_BETRIEBSNR_ZFTRV = Arrays.asList("02998824");
	private static final List<String> ERLAUBTE_BETRIEBSNR_RVTZF = Arrays.asList("90209055");

	private static final int INDEX_ERSTENDREI_START = 0;
	private static final int INDEX_ERSTENDREI_ENDE = 3;
	private final Feld<FeldNameDSME> feldPersGr;
	private final Feld<FeldNameDSME> feldVSTR;
	private final List<String> testBbnr;
	private final String vfmm;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldPersGr
	 *            Feld Personengruppe aus DSME
	 * @param feldVSTR
	 *            Feld VSTR aus DSME
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public PruefungBetriebsnummerVerursacher(final Feld<FeldNameDSME> feld, final Feld<FeldNameDSME> feldPersGr,
			final Feld<FeldNameDSME> feldVSTR, final String vfmm) {
		super(feld);
		this.feldPersGr = feldPersGr;
		this.feldVSTR = feldVSTR;
		this.testBbnr = WertelistenVerwaltungDeuev.getInstance().getWerteliste(ListenTypDeuev.TESTBETRIEBSNUMMERN);
		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		if (feldPersGr.getTrimmedValue().compareTo("205") != 0) {
			final PruefungNichtLeer me140 = new PruefungNichtLeer(getFeld());
			addPruefung(me140, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME140));
		}

		if (getFeld().getTrimmedValue().length() > 0) {
			final PruefungNotInList me141 = new PruefungNotInList(testBbnr, getFeld());
			addPruefung(me141, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME141));

			final PruefungBetriebsnummer me142 = new PruefungBetriebsnummer(getFeld());
			addPruefung(me142, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME142));

			Feld<FeldNameDSME> erstenDreiZeichen;
			if (getFeld().getTrimmedValue().length() > INDEX_ERSTENDREI_ENDE) {
				erstenDreiZeichen = new Feld<FeldNameDSME>(getFeld().getTrimmedValue().substring(
						INDEX_ERSTENDREI_START,
						INDEX_ERSTENDREI_ENDE));
			} else {
				erstenDreiZeichen = new Feld<FeldNameDSME>("invalid");
			}

			if (vfmm.compareTo("BWTRV") == 0) {
				final PruefungInList me146 = new PruefungInList(ERLAUBTE_BETRIEBSNR_BWTRV, getFeld());
				addPruefung(me146, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME146));
			}

			else if (vfmm.compareTo("BZTRV") == 0) {
				final PruefungInList me148 = new PruefungInList(ERLAUBTE_BETRIEBSNR_BZTRV, getFeld());
				addPruefung(me148, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME148));
			}

			else if (vfmm.compareTo("PVTRV") == 0) {
				final PruefungInList me150 = new PruefungInList(ERLAUBTER_ANFANG_PVTRV, erstenDreiZeichen);
				addPruefung(me150, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME150));
			}

			else if ((vfmm.compareTo("KSTKV") == 0) || (vfmm.compareTo("KSTRV") == 0)) {
				final PruefungInList me154 = new PruefungInList(ERLAUBTE_BETRIEBSNR_KSTRV_KSTKV, getFeld());
				addPruefung(me154, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME154));
			}

			else if (vfmm.compareTo("ZFTRV") == 0) {
				final PruefungInList me155 = new PruefungInList(ERLAUBTE_BETRIEBSNR_ZFTRV, getFeld());
				addPruefung(me155, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME155));
			}

			else if (vfmm.compareTo("RVTZF") == 0) {
				final PruefungInList me159 = new PruefungInList(ERLAUBTE_BETRIEBSNR_RVTZF, getFeld());
				addPruefung(me159, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME159));
			} 
			
			else {
				if ((feldVSTR.getTrimmedValue().compareTo("0C") == 0) || (feldVSTR.getTrimmedValue().compareTo("0G") == 0)) {
					final PruefungInList me143 = new PruefungInList(ERLAUBTER_ANFANG, erstenDreiZeichen);
					addPruefung(me143, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME143));
				}

				else if ((feldVSTR.getTrimmedValue().compareTo("0A") == 0) || (feldVSTR.getTrimmedValue().compareTo("0B") == 0)) {
					// selben Werte wie bei Pruefung 143, deswegen "ERLAUBTER_ANFANG" obwohl es notinlist ist.
					final PruefungNotInList me144 = new PruefungNotInList(ERLAUBTER_ANFANG, erstenDreiZeichen);
					addPruefung(me144, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME144));
				}
			}
		}
	}

}
