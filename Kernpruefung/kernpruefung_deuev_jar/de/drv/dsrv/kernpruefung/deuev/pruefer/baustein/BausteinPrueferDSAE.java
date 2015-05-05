package de.drv.dsrv.kernpruefung.deuev.pruefer.baustein;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Datensatz;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractBausteinPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae.PruefungAktenzeichenVerursacher;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae.PruefungBetriebsnummerAbsender;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae.PruefungBetriebsnummerEmpfaenger;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae.PruefungBetriebsnummerVerursacher;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae.PruefungDatumErstellung;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae.PruefungFehleranzahl;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae.PruefungFehlerkennzeichnung;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae.PruefungKennung;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae.PruefungKennzUebergang;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae.PruefungMmaz;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae.PruefungMmez;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae.PruefungReserve1;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae.PruefungReserve2;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae.PruefungReserve3;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae.PruefungReserve4;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae.PruefungVSNR;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae.PruefungVSTR;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae.PruefungVersionsnummer;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae.PruefungVersionsnummerKP;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSAE;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;

/**
 * Der Bausteinpruefer fuer den Baustein DSAE. Ruft alle Feldpruefungen auf.
 */
public class BausteinPrueferDSAE extends AbstractBausteinPruefer<FeldNameDSAE, FehlerNummerDSAE> {

	private final Date verarbDatum;
	private final String zeitangaben;
	private final Datensatz datensatz;
	private final String vfmm;

	/**
	 * Konstruktor. Nimmt alle Werte entgegen, die fuer die Feld-Pruefungen in
	 * dem Baustein benoetigt werden, und speichert diese ab.
	 * 
	 * @param baustein
	 *            Baustein DSAE
	 * @param verarbDatum
	 *            Verarbeitungsdatum aus den Aufrufparametern
	 * @param datensatz
	 *            Der komplette Datensatz
	 * @param zeitangaben
	 *            Zeitangaben aus den Eingabedaten
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	// @formatter:off
	public BausteinPrueferDSAE(final Baustein<FeldNameDSAE> baustein, final Date verarbDatum, final String zeitangaben,
			final Datensatz datensatz, final String vfmm) {
		// @formatter:on

		super(baustein);

		this.verarbDatum = verarbDatum;
		this.zeitangaben = zeitangaben;
		this.datensatz = datensatz;
		this.vfmm = vfmm;
	}

	@Override
	public List<FeldPruefung<FeldNameDSAE, FehlerNummerDSAE>> getPruefungen() throws UngueltigePrueferDatenException {

		final List<FeldPruefung<FeldNameDSAE, FehlerNummerDSAE>> pruefList = new ArrayList<FeldPruefung<FeldNameDSAE, FehlerNummerDSAE>>();
		final Baustein<FeldNameDSAE> baustein = getBaustein();

		// Felder der Reihenfolge nach auslesen und Pruefungen definieren
		final Feld<FeldNameDSAE> feldKennung = baustein.getFeld(FeldNameDSAE.KENNUNG);
		final Feld<FeldNameDSAE> feldBbnrAbsender = baustein.getFeld(FeldNameDSAE.BBNR_ABSENDER);
		final Feld<FeldNameDSAE> feldBbnrEmpfaenger = baustein.getFeld(FeldNameDSAE.BBNR_EMPFAENGER);
		final Feld<FeldNameDSAE> feldVersionsnummer = baustein.getFeld(FeldNameDSAE.VERSIONS_NR);
		final Feld<FeldNameDSAE> feldDatumErstellung = baustein.getFeld(FeldNameDSAE.DATUM_ERSTELLUNG);
		final Feld<FeldNameDSAE> feldFehlerKennzeichen = baustein.getFeld(FeldNameDSAE.FEHLER_KENNZ);
		final Feld<FeldNameDSAE> feldFehlerAnzahl = baustein.getFeld(FeldNameDSAE.FEHLER_ANZAHL);
		final Feld<FeldNameDSAE> feldVsnr = baustein.getFeld(FeldNameDSAE.VSNR);
		final Feld<FeldNameDSAE> feldVstr = baustein.getFeld(FeldNameDSAE.VSTR);
		final Feld<FeldNameDSAE> feldBetriebsnummerVerursacher = baustein.getFeld(FeldNameDSAE.BBNR_VU);
		final Feld<FeldNameDSAE> feldAktenzeichenVerursacher = baustein.getFeld(FeldNameDSAE.AKTENZEICHEN_VERURSACHER);
		final Feld<FeldNameDSAE> feldReserve1 = baustein.getFeld(FeldNameDSAE.RESERVE1);
		final Feld<FeldNameDSAE> feldMmaz = baustein.getFeld(FeldNameDSAE.MM_ANRECHNUNGSZEITEN);
		final Feld<FeldNameDSAE> feldMmez = baustein.getFeld(FeldNameDSAE.MM_ENTGELT_ERSATZLEISTUNGSZEITEN);
		final Feld<FeldNameDSAE> feldReserve2 = baustein.getFeld(FeldNameDSAE.RESERVE2);
		final Feld<FeldNameDSAE> feldKennzUebergang = baustein.getFeld(FeldNameDSAE.KENNZ_UEBERGANG);
		final Feld<FeldNameDSAE> feldReserve3 = baustein.getFeld(FeldNameDSAE.RESERVE3);
		final Feld<FeldNameDSAE> feldVersionsNrKP = baustein.getFeld(FeldNameDSAE.VERSIONS_NR_KP);
		final Feld<FeldNameDSAE> feldReserve4 = baustein.getFeld(FeldNameDSAE.RESERVE4);

		if ((feldKennung == null) || (feldBbnrAbsender == null) || (feldBbnrEmpfaenger == null)
				|| (feldVersionsnummer == null) || (feldDatumErstellung == null) || (feldFehlerKennzeichen == null)
				|| (feldFehlerAnzahl == null) || (feldVsnr == null) || (feldVstr == null)
				|| (feldBetriebsnummerVerursacher == null) || (feldAktenzeichenVerursacher == null)
				|| (feldReserve1 == null) || (feldMmaz == null) || (feldMmez == null) || (feldReserve2 == null)
				|| (feldKennzUebergang == null) || (feldReserve3 == null) || (feldVersionsNrKP == null)
				|| (feldReserve4 == null)) {
			throw new UngueltigePrueferDatenException("Pruefung fuer den Baustein " + baustein.getName()
					+ " kann nicht initalisiert werden, eines der Felder ist nicht vorhanden: " + baustein);
		} else {
			pruefList.add(new PruefungKennung(feldKennung, vfmm));
			pruefList.add(new PruefungBetriebsnummerAbsender(feldBbnrAbsender, vfmm));
			pruefList.add(new PruefungBetriebsnummerEmpfaenger(feldBbnrEmpfaenger, vfmm));
			pruefList.add(new PruefungVersionsnummer(feldVersionsnummer));
			pruefList.add(new PruefungDatumErstellung(feldDatumErstellung, verarbDatum, zeitangaben));
			pruefList.add(new PruefungFehlerkennzeichnung(feldFehlerKennzeichen));
			pruefList.add(new PruefungFehleranzahl(feldFehlerAnzahl, feldFehlerKennzeichen));
			pruefList.add(new PruefungVSNR(feldVsnr));
			pruefList.add(new PruefungVSTR(feldVstr, vfmm));
			pruefList.add(new PruefungBetriebsnummerVerursacher(feldBetriebsnummerVerursacher, vfmm));
			pruefList.add(new PruefungAktenzeichenVerursacher(feldAktenzeichenVerursacher, vfmm));
			pruefList.add(new PruefungReserve1(feldReserve1));
			pruefList.add(new PruefungMmaz(feldMmaz, feldMmez, datensatz, vfmm));
			pruefList.add(new PruefungMmez(feldMmez, feldMmaz, datensatz, vfmm));
			pruefList.add(new PruefungReserve2(feldReserve2));
			pruefList.add(new PruefungKennzUebergang(feldKennzUebergang, vfmm));
			pruefList.add(new PruefungReserve3(feldReserve3));
			pruefList.add(new PruefungVersionsnummerKP(feldVersionsNrKP, vfmm));
			pruefList.add(new PruefungReserve4(feldReserve4));
		}

		return pruefList;
	}
}
