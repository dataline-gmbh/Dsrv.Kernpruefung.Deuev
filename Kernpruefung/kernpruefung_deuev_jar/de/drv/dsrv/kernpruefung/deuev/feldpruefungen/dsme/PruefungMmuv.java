package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Datensatz;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld MMUV aus dem Baustein DSME.
 */
public class PruefungMmuv extends AbstractPruefungSchalterleisteFeld<FeldNameDSME, FehlerNummerDSME> {

	static final List<String> PERS_GR_N = Arrays.asList("108", "111", "143", "203", "204", "205", "207", "208", "209",
			"210", "301", "302", "303", "304", "305", "306");
	static final List<String> MUSS_N = Arrays.asList("N");
	static final List<String> VFMM_N = Arrays.asList("PVTRV", "BWTRV", "BZTRV", "KSTRV", "SOTBF", "UETBF", "DSTBF",	"ZFTRV");
	private final Feld<FeldNameDSME> feldPersgr;
	private final Feld<FeldNameDBME> feldKennzSt;
	private final String vfmm;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldPersgr
	 *            Feld Personengruppe DSME
	 * @param datensatz
	 *            Datensatz DSME
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public PruefungMmuv(final Feld<FeldNameDSME> feld, final Feld<FeldNameDSME> feldPersgr, final Baustein<FeldNameDBME> bausteinDbme, final Datensatz datensatz, final String vfmm) {
		super(feld, datensatz);

		this.feldPersgr = feldPersgr;
		if(bausteinDbme != null)
			this.feldKennzSt = bausteinDbme.getFeld(FeldNameDBME.KENNZ_STORNO);
		else 
			this.feldKennzSt = null;
		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		addPruefungenOptional(FehlerNummerDSME.DSME316, FehlerNummerDSME.DSME935, BausteinName.DBUV);

		if (PERS_GR_N.contains(feldPersgr.getTrimmedValue()) && !(feldPersgr.getTrimmedValue().equals("111") && feldKennzSt != null && feldKennzSt.getTrimmedValue().equals("J"))) {
			final PruefungInList me317 = new PruefungInList(MUSS_N, getFeld(), true);
			addPruefung(me317, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME317));
		}

		if (VFMM_N.contains(vfmm)) {
			final PruefungInList me318 = new PruefungInList(MUSS_N, getFeld(), true);
			addPruefung(me318, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME318));
		}
	}

}
