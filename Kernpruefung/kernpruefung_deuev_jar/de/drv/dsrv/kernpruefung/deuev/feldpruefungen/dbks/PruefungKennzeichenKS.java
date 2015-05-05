package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbks;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKS;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKS;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld KENNZ-KNV-SEE aus dem Baustein DBKS.
 */
public class PruefungKennzeichenKS extends AbstractFeldPruefung<FeldNameDBKS, FehlerNummerDBKS> {

	private static final List<String> ZULAESSIGE_WERTE = Arrays.asList("K", "S");
	private static final List<String> ZULAESSIGE_WERTE_K = Arrays.asList("K");
	private static final List<String> ZULAESSIGE_WERTE_S = Arrays.asList("S");
	private static final List<String> BBNRVU_K = Arrays.asList("098", "980");
	private static final List<String> BBNRVU_S = Arrays.asList("099", "990", "991", "992");
	private static final int ERSTEN_DREI_ZEICHEN = 3;
	private final Baustein<FeldNameDSME> bausteinDsme;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param bausteinDsme
	 *            Baustein DSME
	 */
	public PruefungKennzeichenKS(final Feld<FeldNameDBKS> feld, final Baustein<FeldNameDSME> bausteinDsme) {
		super(feld);

		this.bausteinDsme = bausteinDsme;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer ks010a = new PruefungNichtLeer(getFeld());
		addPruefung(ks010a, new Fehler<FehlerNummerDBKS>(FehlerNummerDBKS.DBKS010));

		final PruefungInList ks010b = new PruefungInList(ZULAESSIGE_WERTE, getFeld(), true);
		addPruefung(ks010b, new Fehler<FehlerNummerDBKS>(FehlerNummerDBKS.DBKS010));
	}

	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {
		final Feld<FeldNameDSME> feldBbnrvuStart;

		final List<FehlerNummerDBKS> fehlernummern = new LinkedList<FehlerNummerDBKS>();
		fehlernummern.add(FehlerNummerDBKS.DBKS012);
		fehlernummern.add(FehlerNummerDBKS.DBKS014);

		if (isPruefbar(fehlernummern, bausteinDsme.getFeld(FeldNameDSME.BBNR_VU))) {
			if ((bausteinDsme.getFeld(FeldNameDSME.BBNR_VU).getTrimmedValue().length() > ERSTEN_DREI_ZEICHEN)) {
				final String bbnrvuStart = bausteinDsme.getFeld(FeldNameDSME.BBNR_VU).getTrimmedValue().substring(0, ERSTEN_DREI_ZEICHEN);
				feldBbnrvuStart = new Feld<FeldNameDSME>(bbnrvuStart);
			} else {
				feldBbnrvuStart = new Feld<FeldNameDSME>("");
			}

			if (ZULAESSIGE_WERTE_K.contains(getFeld().getTrimmedValue())) {
				final PruefungInList ks012 = new PruefungInList(BBNRVU_K, feldBbnrvuStart, true);
				addPruefungBausteinUebergreifend(ks012, new Fehler<FehlerNummerDBKS>(FehlerNummerDBKS.DBKS012));
			}
			
			if (ZULAESSIGE_WERTE_S.contains(getFeld().getTrimmedValue())) {
				final PruefungInList ks014 = new PruefungInList(BBNRVU_S, feldBbnrvuStart, true);
				addPruefungBausteinUebergreifend(ks014, new Fehler<FehlerNummerDBKS>(FehlerNummerDBKS.DBKS014));
			}
			
//			if (BBNRVU_K.contains(bbnrvuStart)) {
//				final PruefungInList ks012 = new PruefungInList(ZULAESSIGE_WERTE_K, getFeld(), true);
//				addPruefungBausteinUebergreifend(ks012, new Fehler<FehlerNummerDBKS>(FehlerNummerDBKS.DBKS012));
//			}

//			if (BBNRVU_S.contains(bbnrvuStart)) {
//				final PruefungInList ks014 = new PruefungInList(ZULAESSIGE_WERTE_S, getFeld(), true);
//				addPruefungBausteinUebergreifend(ks014, new Fehler<FehlerNummerDBKS>(FehlerNummerDBKS.DBKS014));
//			}
		}
	}
}
