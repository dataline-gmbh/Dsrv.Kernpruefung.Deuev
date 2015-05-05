package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSAE;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;
import de.drv.dsrv.kernpruefung.deuev.pruefungen.PruefungBetriebsnummer;

/**
 * Pruefung fuer das Feld Betriebsnummer Empfaenger aus dem Baustein DSAE.
 */
public class PruefungBetriebsnummerEmpfaenger extends AbstractFeldPruefung<FeldNameDSAE, FehlerNummerDSAE> {

	private static final List<String> BBNR_EMPF_66667777 = Arrays.asList("66667777");
	private static final List<String> BBNR_EMPF_66667777_98094032 = Arrays.asList("66667777", "98094032");
	private static final List<String> BBNR_EMPF_76641777 = Arrays.asList("76641777");
	private static final List<String> BBNR_EMPF_996 = Arrays.asList("996");

	private static final int INDEX_START = 0;
	private static final int INDEX_ENDE = 3;

	private final String vfmm;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public PruefungBetriebsnummerEmpfaenger(final Feld<FeldNameDSAE> feld, final String vfmm) {
		super(feld);

		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungBetriebsnummer ae030 = new PruefungBetriebsnummer(getFeld());
		addPruefung(ae030, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE030));

		if (vfmm.compareTo("RVTBA") == 0) {
			final PruefungInList ae032 = new PruefungInList(BBNR_EMPF_76641777, getFeld());
			addPruefung(ae032, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE032));

		} else if (vfmm.compareTo("BATRV") == 0 || vfmm.compareTo("KTTRV") == 0) {
			final PruefungInList ae032 = new PruefungInList(BBNR_EMPF_66667777, getFeld());
			addPruefung(ae032, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE032));
		}

		else if (vfmm.compareTo("KVTWL") == 0 || vfmm.compareTo("KVTRV") == 0) {
			final PruefungInList ae032 = new PruefungInList(BBNR_EMPF_66667777_98094032, getFeld());
			addPruefung(ae032, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE032));
		}
		
		else if (vfmm.compareTo("PVTRV") == 0) {
			final PruefungInList ae032 = new PruefungInList(BBNR_EMPF_66667777, getFeld());
			addPruefung(ae032, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE032));
		}
		
		else if (vfmm.compareTo("RVTPV") == 0) {
			final Feld<FeldNameDSAE> feldErstenDrei;
			if (getFeld().getTrimmedValue().length() > INDEX_ENDE) {
				feldErstenDrei = new Feld<FeldNameDSAE>(getFeld().getTrimmedValue().substring(INDEX_START, INDEX_ENDE));
			} else {
				// Der Fall wird nie eintreffen, da in Pruefung 020 bereits die
				// Laenge ueberprueft wird. Die Unterscheidung muss trotzdem
				// hier stehen, da es ansonsten zu einer
				// IndexOutOfBounds-Exception kommen kann.
				feldErstenDrei = new Feld<FeldNameDSAE>("invalid");
			}
			final PruefungInList ae022 = new PruefungInList(BBNR_EMPF_996, feldErstenDrei, true);
			addPruefung(ae022, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE032));
		}

	}
}
