package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbuv;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNotInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBUV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBUV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.ListenTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Pruefung fuer das Feld UV-Grund aus dem Baustein DBUV.
 */
public class PruefungUvGrund extends AbstractFeldPruefung<FeldNameDBUV, FehlerNummerDBUV> {

	private static final List<String> ZULAESSIGE_WERTE = Arrays.asList("A07", "A08", "A09", "B01", "B02", "B03", "B04",
			"B05", "B06", "B09", "C01", "C06");
	private static final List<String> ZULAESSIGE_WERTE_AGDEU = Arrays.asList("C06");
	private static final String KEINE_STORNIERUNG = "N";
	private static final String UVGD_W01 = "A07";

	private static final List<String> BBNRVU_C01 = Arrays.asList("18663937");
	private final Baustein<FeldNameDSME> bausteinDsme;
	private final Baustein<FeldNameDBME> bausteinDbme;
	private final String vfmm;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param bausteinDsme
	 *            Baustein DSME
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmal)
	 */
	public PruefungUvGrund(final Feld<FeldNameDBUV> feld, final Baustein<FeldNameDSME> bausteinDsme,
			final Baustein<FeldNameDBME> bausteinDbme, final String vfmm) {
		super(feld);

		this.bausteinDsme = bausteinDsme;
		this.bausteinDbme = bausteinDbme;
		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		// length == 0 ist Grundstellung (erlaubt)
		if (getFeld().getTrimmedValue().length() > 0) {
			final PruefungInList uv080 = new PruefungInList(ZULAESSIGE_WERTE, getFeld(), true);
			addPruefung(uv080, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV080));

		}

		if ((vfmm.compareTo("AGDEU") == 0) && (getFeld().getTrimmedValue().length() > 0)) {
			final PruefungNotInList uv084 = new PruefungNotInList(ZULAESSIGE_WERTE_AGDEU, getFeld());
			addPruefung(uv084, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV084));
		}
	}

	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {
		final Feld<FeldNameDSME> feldDSMEBbnrvu = bausteinDsme.getFeld(FeldNameDSME.BBNR_VU);

		if (StringUtils.isNotBlank(getFeld().getTrimmedValue())) {
			if (getFeld().getTrimmedValue().compareTo("C01") == 0) {
				if (isPruefbar(FehlerNummerDBUV.DBUV082, feldDSMEBbnrvu)) {
					final PruefungNichtLeer uv082a = new PruefungNichtLeer(feldDSMEBbnrvu);
					addPruefungBausteinUebergreifend(uv082a, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV082));

					final PruefungInList uv082 = new PruefungInList(BBNRVU_C01, feldDSMEBbnrvu);
					addPruefungBausteinUebergreifend(uv082, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV082));
				}
			}
		}

		if (bausteinDbme != null) {
			final Feld<FeldNameDBME> feldDbmeKennzst = bausteinDbme.getFeld(FeldNameDBME.KENNZ_STORNO);
			if (isPruefbar(FehlerNummerDBUV.DBUVW01, feldDSMEBbnrvu, feldDbmeKennzst)) {
				if (KEINE_STORNIERUNG.equals(feldDbmeKennzst.getTrimmedValue())
						&& UVGD_W01.equals(getFeld().getTrimmedValue())) {
					final PruefungInList uvw01 = new PruefungInList(WertelistenVerwaltungDeuev.getInstance()
							.getWerteliste(ListenTypDeuev.A19C_UNFALLVERSICHERUNGSTRAEGER), feldDSMEBbnrvu);
					addPruefungBausteinUebergreifend(uvw01, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUVW01));
				}
			}
		}
	}
}
