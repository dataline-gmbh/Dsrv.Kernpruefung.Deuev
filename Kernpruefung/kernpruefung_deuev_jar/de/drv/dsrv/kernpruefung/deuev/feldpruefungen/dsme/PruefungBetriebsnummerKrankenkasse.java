package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNotInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;
import de.drv.dsrv.kernpruefung.deuev.pruefungen.PruefungBetriebsnummer;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.ListenTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Pruefung fuer das Feld Betriebsnummer Krankenkasse aus dem Baustein DSME.
 */
public class PruefungBetriebsnummerKrankenkasse extends AbstractFeldPruefung<FeldNameDSME, FehlerNummerDSME> {

	private static final List<String> UNZULAESSIGE_BETRIEBSNR_AGDEU = Arrays.asList("32023311", "35382142", "37912580",
			"47056789", "15451439");
	private static final String FR_SOZIALES_OEKOLOGISCHES_JAHR = "304";

	private static final String GD_SOFORTMELDUNG = "20";
	private static final List<String> PERSGR_GRUNDSTELLUNG = Arrays.asList("301", "302", "303", "305", "306");
	private final Feld<FeldNameDSME> feldPersGr;
	private final Feld<FeldNameDSME> feldGD;
	private final Feld<FeldNameDSME> feldBbnrvu;
	private final Feld<FeldNameDSME> feldBbnrep;
	private final Feld<FeldNameDSME> feldVsnr;
	private final List<String> testBetriebsnummer;
	private final String vfmm;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldPersGr
	 *            Feld Personengruppe aus DSME
	 * @param feldGD
	 *            Feld Abgabegrund aus DSME
	 * @param feldBbnrvu
	 *            Feld Betriebsnummer-Versacher aus DSME
	 * @param feldBbnrep
	 *            Feld Betriebsnummer-Empfaenger aus DSME
	 * @param feldVsnr
	 *            Feld VSNR (Versicherungsnummer) aus DSME
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public PruefungBetriebsnummerKrankenkasse(final Feld<FeldNameDSME> feld, final Feld<FeldNameDSME> feldPersGr,
			final Feld<FeldNameDSME> feldGD, final Feld<FeldNameDSME> feldBbnrvu, final Feld<FeldNameDSME> feldBbnrep,
			final Feld<FeldNameDSME> feldVsnr, final String vfmm) {
		super(feld);

		this.feldPersGr = feldPersGr;
		this.feldGD = feldGD;
		this.feldBbnrvu = feldBbnrvu;
		this.feldBbnrep = feldBbnrep;
		this.feldVsnr = feldVsnr;
		this.testBetriebsnummer = WertelistenVerwaltungDeuev.getInstance().getWerteliste(
				ListenTypDeuev.TESTBETRIEBSNUMMERN);
		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		if (vfmm.compareTo("ZFTRV") == 0) {
			final PruefungLaenge me168 = new PruefungLaenge(0, getFeld());
			addPruefung(me168, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME168));
		}

		if (feldPersGr.getTrimmedValue().compareTo(FR_SOZIALES_OEKOLOGISCHES_JAHR) == 0) {
			final PruefungNichtLeer me169 = new PruefungNichtLeer(getFeld());
			addPruefung(me169, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME169));
		}

		String vsnrBeginn;
		if (feldVsnr.getTrimmedValue().length() > 1) {
			vsnrBeginn = feldVsnr.getTrimmedValue().substring(0, 2);
		} else {
			vsnrBeginn = "";
		}
		if (!((feldGD.getTrimmedValue().compareTo(GD_SOFORTMELDUNG) == 0)
				|| PERSGR_GRUNDSTELLUNG.contains(feldPersGr.getTrimmedValue()) || (vsnrBeginn.compareTo("88") == 0) || (vfmm
				.compareTo("ZFTRV") == 0))) {
			final PruefungBetriebsnummer me170 = new PruefungBetriebsnummer(getFeld());
			addPruefung(me170, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME170));
		}

		if (getFeld().getTrimmedValue().length() > 0) {
			final PruefungNotInList me171 = new PruefungNotInList(testBetriebsnummer, getFeld());
			addPruefung(me171, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME171));
		}

		if ((vfmm.compareTo("PVTRV") == 0) || (vfmm.compareTo("KSTRV") == 0)) {
			if (feldBbnrvu.getTrimmedValue().length() == 0) {
				final PruefungLaenge me172a = new PruefungLaenge(0, getFeld());
				addPruefung(me172a, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME172));
			} else {
				final PruefungInList me172b = new PruefungInList(Arrays.asList(feldBbnrvu.getTrimmedValue()), getFeld());
				addPruefung(me172b, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME172));
			}
		}

		else if (vfmm.compareTo("AGDEU") == 0) {
			if (getFeld().getTrimmedValue().length() > 0) {
				final PruefungNotInList me174 = new PruefungNotInList(UNZULAESSIGE_BETRIEBSNR_AGDEU, getFeld());
				addPruefung(me174, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME174));
			}

			if (feldBbnrep.getTrimmedValue().length() == 0) {
				final PruefungLaenge me176a = new PruefungLaenge(0, getFeld());
				addPruefung(me176a, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME176));
			} else {
				final PruefungInList me176b = new PruefungInList(Arrays.asList(feldBbnrep.getTrimmedValue()), getFeld());
				addPruefung(me176b, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME176));
			}
		}
	}

}
