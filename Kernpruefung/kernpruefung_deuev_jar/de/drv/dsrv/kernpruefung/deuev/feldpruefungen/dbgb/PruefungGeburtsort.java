package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbgb;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBeginntMitBuchstabe;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBuchstabenZiffernZeichenPunkt;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDreiGleicheAnfangsBuchstaben;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungEndetMitBuchstabeZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungMehrfachGleichesSonderOderLeerzeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungMindestensZweiBuchstaben;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNotInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBGB;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBGB;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBVR;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld mit dem Geburtsort.
 */
public class PruefungGeburtsort extends AbstractFeldPruefung<FeldNameDBGB, FehlerNummerDBGB> {

	private static final String AUSNAHME_GD_128 = "99";
	private static final List<String> AUSNAHME_MELD_GB128 = Arrays.asList("04", "05", "80", "81", "82", "83", "84", "85");
	private static final List<Character> ERLAUBT_GB134 = Arrays.asList(' ', '.', ',', '-', '/', '\\', '\'', '(', ')');
	private static final List<String> FIKTIV_GB140 = Arrays.asList("OHNE ANGABE", "OHNE ANGABEN", "RENTE", "BEITRAG", "GESUNDHEIT", "HANDWERKER", "RTZE-VSNR", "FEHLT", "XXX", "DDD");
	private static final List<String> FIKTIV_GB140_000 = Arrays.asList("OHNE ANGABE", "OHNE ANGABEN", "RENTE", "BEITRAG", "GESUNDHEIT", "HANDWERKER", "RTZE-VSNR", "FEHLT", "XXX", "DDD", "DEUTSCHLAND");
	private static final List<Character> ERLAUBT_GB142 = Arrays.asList('.', ')');

	private transient Feld<FeldNameDSME> feldDSMEGd;
	private transient Feld<FeldNameDBVR> feldDBVRGdmq;
	private transient Feld<FeldNameDSME> feldDSMESc;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            das zu pruefende Feld Geburtsort
	 * @param bausteinDSME
	 *            der Baustein DSME
	 * @param bausteinDBVR
	 *            der Baustein DBVR
	 */
	public PruefungGeburtsort(final Feld<FeldNameDBGB> feld, final Baustein<FeldNameDSME> bausteinDSME, final Baustein<FeldNameDBVR> bausteinDBVR) {
		super(feld);

		if (bausteinDSME != null) {
			this.feldDSMEGd = bausteinDSME.getFeld(FeldNameDSME.ABGABEGRUND);
			this.feldDSMESc = bausteinDSME.getFeld(FeldNameDSME.STAATSANGEHOERIGKEITS_SC);
		}

		if (bausteinDBVR != null) {
			this.feldDBVRGdmq = bausteinDBVR.getFeld(FeldNameDBVR.ABGABEGRUND);
		}
	}

	/**
	 * Initialisiere die bausteinuebergreifenden Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {

		// Pruefe, ob Ausnahme zutrifft, falls ja, muss nur geprueft werden,
		// falls hier keine Grundstellung angegeben
		if (!this.isAusnahme() || (this.isAusnahme() && StringUtils.isNotEmpty(this.getFeld().getTrimmedValue()))) {
			final PruefungNichtLeer gb128 = new PruefungNichtLeer(this.getFeld());
			this.addPruefungBausteinUebergreifend(gb128, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB128));

			final PruefungMehrfachGleichesSonderOderLeerzeichen gb130 = new PruefungMehrfachGleichesSonderOderLeerzeichen(this.getFeld());
			this.addPruefungBausteinUebergreifend(gb130, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB130));

			final PruefungDreiGleicheAnfangsBuchstaben gb131 = new PruefungDreiGleicheAnfangsBuchstaben(this.getFeld());
			this.addPruefungBausteinUebergreifend(gb131, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB131));

			final PruefungBuchstabenZiffernZeichenPunkt gb134 = new PruefungBuchstabenZiffernZeichenPunkt(ERLAUBT_GB134, this.getFeld());
			this.addPruefungBausteinUebergreifend(gb134, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB134));

			final PruefungBeginntMitBuchstabe gb136 = new PruefungBeginntMitBuchstabe(this.getFeld());
			this.addPruefungBausteinUebergreifend(gb136, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB136));

			final PruefungMindestensZweiBuchstaben gb138 = new PruefungMindestensZweiBuchstaben(this.getFeld());
			this.addPruefungBausteinUebergreifend(gb138, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB138));

			if (isPruefbar(FehlerNummerDBGB.DBGB140, feldDSMESc)) {
				PruefungNotInList gb140 = null;
				if ("000".equals(feldDSMESc.getTrimmedValue()))
					gb140 = new PruefungNotInList(FIKTIV_GB140_000, this.getFeld());
				else
					gb140 = new PruefungNotInList(FIKTIV_GB140, this.getFeld());
				this.addPruefungBausteinUebergreifend(gb140, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB140));
			}

			final PruefungEndetMitBuchstabeZeichen gb142 = new PruefungEndetMitBuchstabeZeichen(this.getFeld(), ERLAUBT_GB142);
			this.addPruefungBausteinUebergreifend(gb142, new Fehler<FehlerNummerDBGB>(FehlerNummerDBGB.DBGB142));
		}
	}

	/**
	 * Prueft, ob die Ausnahme fuer Fehlernummer DBGB128 zutrifft.
	 * 
	 * @return true, if is Ausnahme
	 * @throws UngueltigeDatenException
	 */
	private boolean isAusnahme() throws UngueltigeDatenException {
		return (this.feldDSMEGd != null && AUSNAHME_GD_128.equals(this.feldDSMEGd.getTrimmedValue())) && (this.feldDBVRGdmq != null && AUSNAHME_MELD_GB128.contains(this.feldDBVRGdmq.getTrimmedValue()));
	}
}
