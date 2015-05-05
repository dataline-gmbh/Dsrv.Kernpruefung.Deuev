package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNotInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.ListenTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Pruefung fuer das Feld Staatsangehoerigkeit aus dem Baustein DSME.
 */
public class PruefungStaatsangehoerigkeit extends AbstractFeldPruefung<FeldNameDSME, FehlerNummerDSME> {

	private static final String GD_SOFORTMELDUNG = "20";
	private static final String GD_AENDERUNG_STAAT = "63";
	private static final String GD_VERGABE_VSNR = "99";

	private static final List<String> GD_AENDERUNGEN = Arrays.asList("60", "61");

	private static final String BBNRVU_PRIV_PFLEGEKASSE = "996";
	private static final int BBNRVU_ERSTEN_DREI_ZEICHEN = 3;

	private static final List<String> SASC_UNZULAESSIG_NEU = Arrays.asList("138", "132", "133", "276", "527",
			"533", "195", "199", "295", "299", "395", "399", "495", "499", "595", "599");
	private static final List<String> SASC_BUNDESWEHR_ZIVIL = Arrays.asList("000");
	private final Feld<FeldNameDSME> feldGd;
	private final Feld<FeldNameDSME> feldVsnr;
	private final Feld<FeldNameDSME> feldBbnrvu;
	private final List<String> ldkz;
	private final String vfmm;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldGd
	 *            Feld Abgabegrund aus DSME
	 * @param feldVsnr
	 *            Feld VSNR (Versicherungsnummer) aus DSME
	 * @param feldBbnrvu
	 *            Feld Betriebsnummer-Verursacher aus DSME
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public PruefungStaatsangehoerigkeit(final Feld<FeldNameDSME> feld, final Feld<FeldNameDSME> feldGd,
			final Feld<FeldNameDSME> feldVsnr, final Feld<FeldNameDSME> feldBbnrvu, final String vfmm) {
		super(feld);

		this.feldGd = feldGd;
		this.feldVsnr = feldVsnr;
		this.feldBbnrvu = feldBbnrvu;
		this.ldkz = WertelistenVerwaltungDeuev.getInstance().getWerteliste(ListenTypDeuev.A8_STAATSSCHLUESSEL);
		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		if (StringUtils.isNotBlank(getFeld().getTrimmedValue())) {
			final PruefungInList me252 = new PruefungInList(ldkz, getFeld(), true);
			addPruefung(me252, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME252));
			
			if ((feldGd.getTrimmedValue().compareTo(GD_AENDERUNG_STAAT) == 0)
					|| (feldGd.getTrimmedValue().compareTo(GD_VERGABE_VSNR) == 0)) {
				final PruefungNotInList me253 = new PruefungNotInList(SASC_UNZULAESSIG_NEU, getFeld());
				addPruefung(me253, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME253));
			}

			if ((vfmm.compareTo("BWTRV") == 0) || (vfmm.compareTo("BZTRV") == 0)) {
				final PruefungInList me254 = new PruefungInList(SASC_BUNDESWEHR_ZIVIL, getFeld());
				addPruefung(me254, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME254));
			}
		} else {
			boolean grundstellung = false;
			final String bbnrvuStart;
			if (feldBbnrvu.getTrimmedValue().length() >= BBNRVU_ERSTEN_DREI_ZEICHEN) {
				bbnrvuStart = feldBbnrvu.getTrimmedValue().substring(0, BBNRVU_ERSTEN_DREI_ZEICHEN);
			} else {
				bbnrvuStart = "";
			}

			if ((feldGd.getTrimmedValue().compareTo(GD_SOFORTMELDUNG) == 0)
					&& StringUtils.isNotBlank(feldVsnr.getTrimmedValue())) {
				grundstellung = true;
				
			} else if (GD_AENDERUNGEN.contains(feldGd.getTrimmedValue())) {
				grundstellung = true;
				
			} else if ((bbnrvuStart.compareTo(BBNRVU_PRIV_PFLEGEKASSE) == 0)
					&& (feldGd.getTrimmedValue().compareTo(GD_VERGABE_VSNR) != 0)) {
				grundstellung = true;
			}

			if (!grundstellung) {
				final PruefungNichtLeer me250 = new PruefungNichtLeer(getFeld());
				addPruefung(me250, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME250));
			}
		}
	}
}
