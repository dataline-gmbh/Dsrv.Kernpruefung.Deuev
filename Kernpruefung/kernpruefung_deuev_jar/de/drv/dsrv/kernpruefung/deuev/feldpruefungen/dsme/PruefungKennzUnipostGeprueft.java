package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld Kennzeichen Unipost Geprueft aus dem Baustein DSME.
 */
public class PruefungKennzUnipostGeprueft extends AbstractFeldPruefung<FeldNameDSME, FehlerNummerDSME> {

	private static final List<String> ERLAUBTE_WERTE = Arrays.asList("D");
	private static final List<String> GD_D = Arrays.asList("99");
	private static final List<String> VFMM_GRUNDSTELLUNG = Arrays.asList("BATRV", "KTTRV", "BWTRV", "BZTRV", "PVTRV",
			"ZFTRV");
	private final Feld<FeldNameDSME> feldGd;
	private final String vfmm;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldGd
	 *            Feld Abgabegrund aus DSME
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public PruefungKennzUnipostGeprueft(final Feld<FeldNameDSME> feld, final Feld<FeldNameDSME> feldGd,
			final String vfmm) {
		super(feld);

		this.feldGd = feldGd;
		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		// length == 0 ist Grundstellung (erlaubt)
		if (getFeld().getTrimmedValue().length() > 0) {
			final PruefungInList me383 = new PruefungInList(ERLAUBTE_WERTE, getFeld(), true);
			addPruefung(me383, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME383));
		}

		if (getFeld().getTrimmedValue().compareTo("D") == 0) {
			final PruefungNichtLeer me385a = new PruefungNichtLeer(feldGd);
			addPruefung(me385a, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME385));

			final PruefungInList me385 = new PruefungInList(GD_D, feldGd);
			addPruefung(me385, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME385));
		}

		if (VFMM_GRUNDSTELLUNG.contains(vfmm)) {
			final PruefungLaenge me386 = new PruefungLaenge(0, getFeld());
			addPruefung(me386, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME386));
		}
	}

}
