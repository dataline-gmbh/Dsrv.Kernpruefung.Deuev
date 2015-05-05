package de.drv.dsrv.kernpruefung.deuev.pruefer.baustein;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractBausteinPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbvr.PruefungAbgabegrund;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbvr.PruefungBereichsnummerVergabeanstalt;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbvr.PruefungVersicherungsnummerVergabe;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBVR;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBGB;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBVR;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Der Bausteinpruefer fuer den Baustein DBVR. Ruft alle Feldpruefungen auf.
 */
public class BausteinPrueferDBVR extends AbstractBausteinPruefer<FeldNameDBVR, FehlerNummerDBVR> {

	private final Baustein<FeldNameDSME> bausteinDsme;
	private final Baustein<FeldNameDBGB> bausteinDbgb;
	private final Date verarbeitungsdatum;
	private final String vfmm;

	/**
	 * Konstruktor. Nimmt alle Werte entgegen, die fuer die Feld-Pruefungen in
	 * dem Baustein benoetigt werden, und speichert diese ab.
	 * 
	 * @param baustein
	 *            Baustein DBVR
	 * @param bausteinDsme
	 *            Baustein DSME
	 * @param bausteinDbgb
	 *            Baustein DBGB
	 * @param verarbeitungsdatum
	 *            Verarbeitungsdatum aus den Aufrufparamtern
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public BausteinPrueferDBVR(final Baustein<FeldNameDBVR> baustein, final Baustein<FeldNameDSME> bausteinDsme,
			final Baustein<FeldNameDBGB> bausteinDbgb, final Date verarbeitungsdatum, final String vfmm) {
		super(baustein);

		this.bausteinDsme = bausteinDsme;
		this.bausteinDbgb = bausteinDbgb;
		this.verarbeitungsdatum = verarbeitungsdatum;
		this.vfmm = vfmm;
	}

	@Override
	public List<FeldPruefung<FeldNameDBVR, FehlerNummerDBVR>> getPruefungen()
			throws UngueltigePrueferDatenException {

		final List<FeldPruefung<FeldNameDBVR, FehlerNummerDBVR>> pruefList = new ArrayList<FeldPruefung<FeldNameDBVR, FehlerNummerDBVR>>();
		final Baustein<FeldNameDBVR> baustein = getBaustein();

		final Feld<FeldNameDBVR> feldAbgabegrund = baustein.getFeld(FeldNameDBVR.ABGABEGRUND);
		final Feld<FeldNameDBVR> feldBereichsnummer = baustein.getFeld(FeldNameDBVR.BEREICHS_NR_VA);
		final Feld<FeldNameDBVR> feldVsnrVergabe = baustein.getFeld(FeldNameDBVR.VSNR_VERGABE);

		if ((feldAbgabegrund == null) || (feldBereichsnummer == null) || (feldVsnrVergabe == null)) {
			throw new UngueltigePrueferDatenException("Pruefung fuer den Baustein " + baustein.getName()
					+ " kann nicht initalisiert werden, einer der Felder ist nicht vorhanden: " + baustein);
		}

		pruefList.add(new PruefungAbgabegrund(feldAbgabegrund, bausteinDsme, bausteinDbgb, verarbeitungsdatum, vfmm));
		pruefList.add(new PruefungBereichsnummerVergabeanstalt(feldBereichsnummer, vfmm));
		pruefList.add(new PruefungVersicherungsnummerVergabe(feldVsnrVergabe, feldAbgabegrund));

		return pruefList;
	}

}
