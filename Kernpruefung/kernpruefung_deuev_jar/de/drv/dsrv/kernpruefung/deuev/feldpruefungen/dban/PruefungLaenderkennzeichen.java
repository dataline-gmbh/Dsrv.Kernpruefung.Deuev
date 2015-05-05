package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dban;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNotInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBAN;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBAN;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.ListenTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Pruefung fuer das Feld Laenderkennzeichen aus dem Baustein DBAN.
 */
public class PruefungLaenderkennzeichen extends AbstractFeldPruefung<FeldNameDBAN, FehlerNummerDBAN> {

	private static final List<String> VERBOTENE_LDKZ = Arrays.asList("YU", "SCG", "SUD");
	private static final List<String> ERLAUBTE_VFMM_OFW = Arrays.asList("KVTWL", "WLTKV", "KVTRV", "RVTKV", "BATRV",
			"KTTRV", "RVTBA", "RVTKT", "DSTBF", "BFTDS");
	private static final String OFW = "OFW";
	private final List<String> laenderkennzeichen;
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
	public PruefungLaenderkennzeichen(final Feld<FeldNameDBAN> feld, final String vfmm) {
		super(feld);

		this.laenderkennzeichen = WertelistenVerwaltungDeuev.getInstance().getWerteliste(
				ListenTypDeuev.A8_LAENDERKENNZEICHEN);
		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final List<String> erweiterteLdkz = new ArrayList<String>(laenderkennzeichen);
		erweiterteLdkz.add("OFW");

		if (StringUtils.isNotBlank(getFeld().getTrimmedValue())) {
			final PruefungInList an012 = new PruefungInList(erweiterteLdkz, getFeld(), true);
			this.addPruefung(an012, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN012));
		}

		if (getFeld().getTrimmedValue().length() > 0) {
			final PruefungNotInList an013 = new PruefungNotInList(VERBOTENE_LDKZ, getFeld());
			addPruefung(an013, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN013));
		}

		if (getFeld().getTrimmedValue().compareTo(OFW) == 0) {
			final Feld<FeldNameDBAN> feldVfmm = new Feld<FeldNameDBAN>(vfmm);
			final PruefungInList an014 = new PruefungInList(ERLAUBTE_VFMM_OFW, feldVfmm, true);
			addPruefung(an014, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN014));
		}
	}

}
