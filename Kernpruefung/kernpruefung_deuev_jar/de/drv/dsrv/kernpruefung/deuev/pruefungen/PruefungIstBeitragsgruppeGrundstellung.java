package de.drv.dsrv.kernpruefung.deuev.pruefungen;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;
import de.drv.dsrv.kernpruefung.basis.pruefungen.AbstractPruefung;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;

/**
 * Fuer die Beitragsgruppe, die ueber die Anlage 1 (vgl.
 * {@link PruefungIstBeitragsgruppe} definiert ist, wird hier ermittelt, wann
 * der Ausnahmefall "0000" gesetzt sein muss. Fehlernummer: DBME107.
 */
public class PruefungIstBeitragsgruppeGrundstellung extends AbstractPruefung {
	
	private static final String GRUNDSTELLUNG = "0000";
	private static final String STORNIERUNG = "J";
	private static final String SEELEUTE = "140";
	private static final String SC_DEU = "000";
	private static final String UNST_BESCH = "205";
	private static final List<String> MELD_107 = Arrays.asList("110", "190", "202", "210", "304", "306");
	
	private final String persGr;
	private final String staatsang;
	private final String kennzStorno;
	
	/**
	 * 
	 * @param feld
	 * @param persGr
	 *            , z.B. aus dem Baustein DSME
	 * @param staatsang
	 *            , z.B. aus dem Baustein DSME
	 * @param kennzStorno
	 *            , z.B. aus dem Baustein DBME
	 */
	public PruefungIstBeitragsgruppeGrundstellung(final Feld<? extends FeldName> feld, final String persGr,
			final String staatsang, final String kennzStorno) {
		super(feld);
		
		this.persGr = persGr;
		this.staatsang = staatsang;
		this.kennzStorno = kennzStorno;
	}
	
	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = true;
		
		if (this.isNichtDeuSeeleute() || MELD_107.contains(this.persGr) || this.isStorUnstBesch()) {
			erfolgreich = GRUNDSTELLUNG.equals(this.getFeld().getTrimmedValue());
		}
		
		return erfolgreich;
	}
	
	/**
	 * Prueft, ob eine Meldung fuer nicht-deutsche Seeleute vorliegt.
	 * 
	 * @return true, if is nicht-deutsche Seeleute
	 */
	private boolean isNichtDeuSeeleute() {
		return SEELEUTE.equals(this.persGr) && !SC_DEU.equals(this.staatsang);
	}
	
	/**
	 * Prueft, ob eine Stornierung fuer unstaendig Beschaeftigte vorliegt.
	 * 
	 * @return true, if is Stornierung fuer unstaendig Beschaeftigte
	 */
	private boolean isStorUnstBesch() {
		return STORNIERUNG.equals(this.kennzStorno) && UNST_BESCH.equals(this.persGr);
	}
	
}
