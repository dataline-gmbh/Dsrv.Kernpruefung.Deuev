package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefungErgebnis;
import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Datensatz;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.ListenTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Pruefung fuer das Feld MMEU aus dem Baustein DSME.
 */
public class PruefungMmeu extends AbstractPruefungSchalterleisteFeld<FeldNameDSME, FehlerNummerDSME> {

	private static final List<String> MUSS_N = Arrays.asList("N");
	private FeldPruefungErgebnis<FehlerNummerDSME> pruefungErgebnis;
	private final Feld<FeldNameDSME> feldSasc;
	private final List<String> ldkzEZ;
	private final String vfmm;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldSasc
	 *            Feld Staatsangehoerigkeit aus DSME
	 * @param datensatz
	 *            Datensatz DSME
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public PruefungMmeu(final Feld<FeldNameDSME> feld, final Feld<FeldNameDSME> feldSasc, final Datensatz datensatz,
			final String vfmm) {
		super(feld, datensatz);

		this.feldSasc = feldSasc;
		this.ldkzEZ = WertelistenVerwaltungDeuev.getInstance().getWerteliste(ListenTypDeuev.A8_STAATSSCHLUESSEL_EU);
		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		pruefungErgebnis = new FeldPruefungErgebnis<FehlerNummerDSME>();
		addPruefungenOptional(FehlerNummerDSME.DSME300, FehlerNummerDSME.DSME934, BausteinName.DBEU);

		if (getFeld().getTrimmedValue().compareTo("J") == 0) {
			if (feldSasc.getTrimmedValue().length() == 0) {
				pruefungErgebnis.addFehler(new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME302));
			} else {
				final PruefungInList me302 = new PruefungInList(ldkzEZ, feldSasc, true);
				addPruefung(me302, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME302));
			}
		}

		if ((vfmm.compareTo("BWTRV") == 0) || (vfmm.compareTo("BZTRV") == 0)) {
			final PruefungInList me304 = new PruefungInList(MUSS_N, getFeld(), true);
			addPruefung(me304, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME304));
		}
	}

	@Override
	public FeldPruefungErgebnis<FehlerNummerDSME> pruefe() throws UngueltigeDatenException {
		if (!pruefungErgebnis.getFehlerListe().isEmpty()) {
			return pruefungErgebnis;
		}

		return pruefeMitAbbruch();
	}

}
