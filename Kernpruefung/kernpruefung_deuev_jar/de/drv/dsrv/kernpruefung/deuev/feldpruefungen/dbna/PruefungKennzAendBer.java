package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbna;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtGleicherString;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBNA;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBNA;

/**
 * Pruefung fuer das Feld KENNZ-AEND-BER (KENNZAB).
 */
public class PruefungKennzAendBer extends AbstractFeldPruefung<FeldNameDBNA, FehlerNummerDBNA> {
	
	private static final List<String> ERLAUBTE_ZEICHEN_NA090 = Arrays.asList("A", "M");
	
	private final transient String vfmm;
	private static final String VFMM = "AGDEU";
	
	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld KENNZAB
	 * @param vfmm
	 *            Verfahrensmerkmale
	 */
	public PruefungKennzAendBer(final Feld<FeldNameDBNA> feld, final String vfmm) {
		super(feld);
		this.vfmm = vfmm;
	}
	
	/**
	 * Initialisiere die feldinternen Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		// keine Pruefung bei ausschliesslichen Leerzeichen (Grundeinstellung)
		if ((this.getFeld() != null) && StringUtils.isNotEmpty(this.getFeld().getTrimmedValue())) {
			final PruefungInList na090 = new PruefungInList(ERLAUBTE_ZEICHEN_NA090, this.getFeld(), true);
			this.addPruefung(na090, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA090));
			
			if (this.vfmm.equals(VFMM)) {
				final PruefungNichtGleicherString na092 = new PruefungNichtGleicherString(this.getFeld(), "M");
				this.addPruefung(na092, new Fehler<FehlerNummerDBNA>(FehlerNummerDBNA.DBNA092));
			}
			
		}
	}
}
