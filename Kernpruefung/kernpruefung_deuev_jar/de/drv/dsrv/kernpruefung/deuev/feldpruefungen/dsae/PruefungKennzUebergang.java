package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungFuegeFehlerHinzu;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSAE;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;

/**
 * Pruefung fuer das Feld Kenzeichen Uebergang aus dem Baustein DSAE.
 */
public class PruefungKennzUebergang extends AbstractFeldPruefung<FeldNameDSAE, FehlerNummerDSAE> {

	private static final List<String> ERLAUBTE_ZEICHEN = Arrays
			.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "A");
	private static final List<String> ERLAUBTE_ZEICHEN_OHNE_KOMMUNEN = Arrays.asList("1", "2", "3", "4", "5", "6", "7",
			"9", "A");

	private static final List<String> VFMM_GRUNDSTELLUNG_OHNE_KOMMUNEN = Arrays.asList("BATRV", "DSTBF", "BFTDS");
	private static final List<String> VFMM_GRUNDSTELLUNG_KOMMUNE = Arrays.asList("KTTRV", "RVTKT", "DSTBF", "BFTDS");

	private static final String KOMMUNE = "8";
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
	public PruefungKennzUebergang(final Feld<FeldNameDSAE> feld, final String vfmm) {
		super(feld);

		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final Feld<FeldNameDSAE> feldVfmm = new Feld<FeldNameDSAE>(vfmm);

		// Grundstellung ist erlaubt, wuerde in der Ueberpruefung aber eine
		// UngueltigeDatenException werfen, da das Leerzeichen weggetrimmt wird.
		if (getFeld().getTrimmedValue().length() > 0) {
			final PruefungInList ae360 = new PruefungInList(ERLAUBTE_ZEICHEN, getFeld(), true);
			addPruefung(ae360, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE360));
		}
		
		// TODO Damit Java wie Cobol läuft, muss der Fehler bei allen ungültigen Werten geworfen werden, während das VFMM nur die Ausprägungen in der Liste hat.
		if (ERLAUBTE_ZEICHEN_OHNE_KOMMUNEN.contains(getFeld().getTrimmedValue())) {
			final PruefungInList ae362a = new PruefungInList(VFMM_GRUNDSTELLUNG_OHNE_KOMMUNEN, feldVfmm, true);
			addPruefung(ae362a, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE362));
		} else if (getFeld().getValue().equals(" ") && vfmm.equals(VFMM_GRUNDSTELLUNG_OHNE_KOMMUNEN.get(0))) {
			final PruefungFuegeFehlerHinzu ae362b = new PruefungFuegeFehlerHinzu(getFeld());
			addPruefung(ae362b, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE362));
		}

		// TODO Damit Java wie Cobol läuft, muss der Fehler bei allen ungültigen Werten geworfen werden, während das VFMM nur die Ausprägungen in der Liste hat.
		if (getFeld().getTrimmedValue().compareTo(KOMMUNE) == 0) {
			final PruefungInList ae365a = new PruefungInList(VFMM_GRUNDSTELLUNG_KOMMUNE, feldVfmm, true);
			addPruefung(ae365a, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE365));
		} else if (getFeld().getValue().equals(" ") && vfmm.equals(VFMM_GRUNDSTELLUNG_KOMMUNE.get(0))) {
			final PruefungFuegeFehlerHinzu ae365b = new PruefungFuegeFehlerHinzu(getFeld());
			addPruefung(ae365b, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE365));
		}
	}
}
