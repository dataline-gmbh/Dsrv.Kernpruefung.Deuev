package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbvr;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumVorDatum;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungFuegeFehlerHinzu;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils.DateUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBVR;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBGB;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBVR;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld Abgabegrund aus dem Baustein DBVR.
 */
public class PruefungAbgabegrund extends AbstractFeldPruefung<FeldNameDBVR, FehlerNummerDBVR> {

	private static final List<String> ZULAESSIGE_WERTE = Arrays.asList("01", "02", "03", "04", "05", "10", "11", "80",
			"81", "82", "83", "84", "85", "99");
	private static final List<String> VFMM_ARBEIT = Arrays.asList("ZFTRV", "BATRV", "KTTRV", "PVTRV");
	private static final List<String> VFMM_SONSTIGE_STELLEN = Arrays.asList("BWTRV", "BZTRV", "KSTRV");

	private static final List<String> GD_ARBEIT = Arrays.asList("01", "04", "80", "99");
	private static final List<String> GD_KRANKENKASSE = Arrays.asList("01", "04", "10", "80", "99");
	private static final List<String> GD_SONSTIGE_STELLEN = Arrays.asList("01", "99");
	private static final List<String> GD_INTERIMSVSNR = Arrays.asList("01", "02", "04", "05", "10", "11", "99");
	private static final List<String> GD_GRUNDSTELLUNG_GEBURTSORT = Arrays.asList("04", "05", "80", "81", "82", "83",
			"84", "85");

	private static final List<String> VSNR_INTERIMSNUMMER = Arrays.asList("00", "41", "77", "83", "84", "85", "86",
			"87", "88", "91", "92", "94");
	private final Baustein<FeldNameDSME> bausteinDsme;
	private final Baustein<FeldNameDBGB> bausteinDbgb;
	private final Date verarbeitungsdatum;
	private final String vfmm;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param bausteinDsme
	 *            Baustein DSME
	 * @param bausteinDbgb
	 *            Baustein DBGB
	 * @param verarbeitungsdatum
	 *            Verarbeitungsdatum aus den Aufrufparametern
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmal)
	 */
	public PruefungAbgabegrund(final Feld<FeldNameDBVR> feld, final Baustein<FeldNameDSME> bausteinDsme,
			final Baustein<FeldNameDBGB> bausteinDbgb, final Date verarbeitungsdatum, final String vfmm) {
		super(feld);

		this.bausteinDsme = bausteinDsme;
		this.bausteinDbgb = bausteinDbgb;
		this.verarbeitungsdatum = verarbeitungsdatum;
		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch vr010 = new PruefungNumerisch(getFeld(), true);
		addPruefung(vr010, new Fehler<FehlerNummerDBVR>(FehlerNummerDBVR.DBVR010));

		final PruefungInList vr012 = new PruefungInList(ZULAESSIGE_WERTE, getFeld());
		addPruefung(vr012, new Fehler<FehlerNummerDBVR>(FehlerNummerDBVR.DBVR012));

		if (VFMM_ARBEIT.contains(vfmm)) {
			final PruefungInList vr014 = new PruefungInList(GD_ARBEIT, getFeld());
			addPruefung(vr014, new Fehler<FehlerNummerDBVR>(FehlerNummerDBVR.DBVR014));
		}

		else if (vfmm.compareTo("KVTRV") == 0) {
			final PruefungInList vr015 = new PruefungInList(GD_KRANKENKASSE, getFeld());
			addPruefung(vr015, new Fehler<FehlerNummerDBVR>(FehlerNummerDBVR.DBVR015));
		}

		else if (VFMM_SONSTIGE_STELLEN.contains(vfmm)) {
			final PruefungInList vr016 = new PruefungInList(GD_SONSTIGE_STELLEN, getFeld());
			addPruefung(vr016, new Fehler<FehlerNummerDBVR>(FehlerNummerDBVR.DBVR016));
		}
	}

	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {
		final Feld<FeldNameDBGB> feldDbgbGebDat = new Feld<FeldNameDBGB>("");
		if (bausteinDbgb != null) {
			feldDbgbGebDat.setValue(DateUtils.korrigiereLeerDatum(bausteinDbgb.getFeld(FeldNameDBGB.GEBURTSDATUM)
					.getTrimmedValue()));
		}

		String vsnrStart;
		if (bausteinDsme.getFeld(FeldNameDSME.VSNR).getTrimmedValue().length() > 1) {
			vsnrStart = bausteinDsme.getFeld(FeldNameDSME.VSNR).getTrimmedValue().substring(0, 2);
		} else {
			vsnrStart = "";
		}
		if (isPruefbar(FehlerNummerDBVR.DBVR020, bausteinDsme.getFeld(FeldNameDSME.VSNR))) {
			if( vsnrStart.length() == 2 && VSNR_INTERIMSNUMMER.contains(vsnrStart) && !GD_INTERIMSVSNR.contains(getFeld().getTrimmedValue())) {
				final PruefungFuegeFehlerHinzu vr020 = new PruefungFuegeFehlerHinzu(getFeld());
				addPruefungBausteinUebergreifend(vr020, new Fehler<FehlerNummerDBVR>(FehlerNummerDBVR.DBVR020));
			}
			
			if( vsnrStart.length() == 2 && !VSNR_INTERIMSNUMMER.contains(vsnrStart) && GD_INTERIMSVSNR.contains(getFeld().getTrimmedValue())) {
				final PruefungFuegeFehlerHinzu vr020 = new PruefungFuegeFehlerHinzu(getFeld());
				addPruefungBausteinUebergreifend(vr020, new Fehler<FehlerNummerDBVR>(FehlerNummerDBVR.DBVR020));
			}
		}

		if ((bausteinDbgb != null) && isPruefbar(FehlerNummerDBVR.DBVR022, bausteinDbgb.getFeld(FeldNameDBGB.GB_ORT))
				&& (bausteinDbgb.getFeld(FeldNameDBGB.GB_ORT).getTrimmedValue().length() == 0)) {
			final PruefungInList vr022 = new PruefungInList(GD_GRUNDSTELLUNG_GEBURTSORT, getFeld());
			addPruefungBausteinUebergreifend(vr022, new Fehler<FehlerNummerDBVR>(FehlerNummerDBVR.DBVR022));
		}

		// TODO Damit Java wie Cobol läuft, muss diese Prüfung auskommentiert werden, da sie nicht in Cobol implementiert ist.
//		if (getFeld().getTrimmedValue().compareTo("80") != 0) {
//			if (bausteinDbgb != null
//					&& isPruefbar(FehlerNummerDBVR.DBVR024, bausteinDbgb.getFeld(FeldNameDBGB.GEBURTSDATUM))) {
//				final Date verarbeitungsDatum90 = DateUtils.berechneNeuesDatumJahr(verarbeitungsdatum, -90, false);
//				final PruefungDatumNachDatum vr024 = new PruefungDatumNachDatum(feldDbgbGebDat, verarbeitungsDatum90);
//				addPruefungBausteinUebergreifend(vr024, new Fehler<FehlerNummerDBVR>(FehlerNummerDBVR.DBVR024));
//			}
//		}

		if ((vsnrStart.compareTo("88") == 0)
				&& ((getFeld().getTrimmedValue().compareTo("01") == 0) || (getFeld().getTrimmedValue().compareTo("99") == 0))) {
			if (bausteinDbgb != null
					&& isPruefbar(FehlerNummerDBVR.DBVR024, bausteinDbgb.getFeld(FeldNameDBGB.GEBURTSDATUM))) {
				final Date verarbeitungsDatum14 = DateUtils.berechneNeuesDatumJahr(verarbeitungsdatum, -14, false);
				final PruefungDatumVorDatum vr025 = new PruefungDatumVorDatum(feldDbgbGebDat, verarbeitungsDatum14);
				addPruefungBausteinUebergreifend(vr025, new Fehler<FehlerNummerDBVR>(FehlerNummerDBVR.DBVR025));
			}
		}
	}
}
