package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbme;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungGleicherString;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNotInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld Kennzeichen Storno.
 */
public class PruefungKennzStorno extends AbstractFeldPruefung<FeldNameDBME, FehlerNummerDBME> {

	private static final String KEINE_STORNIERUNG = "N";
	private static final String MELD_ENDBESCH = "40";
	private static final String MELD_UNSTAENDBESCH = "59";
	private static final String KURZFR_BESCH = "202";
	private static final String UNSTAEND_BESCH = "205";

	private static final List<String> ERLBT_ZCHN_010 = Arrays.asList("J", "N");
	private static final List<String> UNZLG_LAENDER_018 = Arrays.asList("138", "132", "133", "276", "527",
			"533", "195", "199", "295", "299", "395", "399", "495", "499", "595", "599");
	private static final List<String> MELD_N_STORNIERUNG = Arrays.asList("10", "11", "12", "13");

	private transient Feld<FeldNameDSME> feldDSMEPersgr;
	private transient Feld<FeldNameDSME> feldDSMEGd;
	private transient Feld<FeldNameDSME> feldDSMEStaatsAng;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem Namenszusatz
	 * @param bausteinDSME
	 *            der Baustein DSME
	 */
	public PruefungKennzStorno(final Feld<FeldNameDBME> feld, final Baustein<FeldNameDSME> bausteinDSME) {
		super(feld);
		if (bausteinDSME != null) {
			this.feldDSMEPersgr = bausteinDSME.getFeld(FeldNameDSME.PERSONENGRUPPE);
			this.feldDSMEGd = bausteinDSME.getFeld(FeldNameDSME.ABGABEGRUND);
			this.feldDSMEStaatsAng = bausteinDSME.getFeld(FeldNameDSME.STAATSANGEHOERIGKEITS_SC);
		}
	}

	/**
	 * Initialisiere die bausteinuebergreifenden Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {
		final String kennzStoValue = this.getFeld().getTrimmedValue().toUpperCase();
		if (KEINE_STORNIERUNG.equals(kennzStoValue)) {

			final String gdValue = StringUtils.stripToEmpty(this.feldDSMEGd.getTrimmedValue());

			if (this.isPruefbar(FehlerNummerDBME.DBME012, this.feldDSMEPersgr)
					&& KURZFR_BESCH.equals(this.feldDSMEPersgr.getTrimmedValue())) {
				final PruefungGleicherString me012 = new PruefungGleicherString(this.feldDSMEGd, MELD_ENDBESCH);
				this.addPruefungBausteinUebergreifend(me012, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME012));
			}

			if (this.isPruefbar(Arrays.asList(FehlerNummerDBME.DBME013, FehlerNummerDBME.DBME018), this.feldDSMEPersgr)) {
				if (MELD_UNSTAENDBESCH.equals(gdValue)) {
					final PruefungGleicherString me013 = new PruefungGleicherString(this.feldDSMEPersgr, UNSTAEND_BESCH);
					this.addPruefungBausteinUebergreifend(me013, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME013));
				}

				if (MELD_N_STORNIERUNG.contains(gdValue)) {
					final PruefungNotInList me018 = new PruefungNotInList(UNZLG_LAENDER_018, this.feldDSMEStaatsAng);
					this.addPruefungBausteinUebergreifend(me018, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME018));
				}
			}
		}
	}

	/**
	 * Initialisiere die feldinternen Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungInList me010 = new PruefungInList(ERLBT_ZCHN_010, this.getFeld(), true);
		this.addPruefung(me010, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME010));
	}

}
