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
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungAbgabegrund;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungAktenzeichenVerursacher;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungBetriebsnummerAbrechnungsstelle;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungBetriebsnummerAbsender;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungBetriebsnummerEmpfaenger;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungBetriebsnummerKrankenkasse;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungBetriebsnummerVerursacher;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungDatumErstellung;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungFehleranzahl;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungFehlerkennzeichnung;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungKennung;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungKennzStatus;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungKennzUebergang;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungKennzUnipostGeprueft;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungMmUebermittlung;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungMman;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungMmeu;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungMmgb;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungMmks;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungMmkv;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungMmme;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungMmna;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungMmrg;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungMmso;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungMmsv;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungMmue;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungMmuv;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungMmvr;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungPersonenGruppe;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungReserve;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungStaatsangehoerigkeit;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungVSNR;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungVSTR;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungVerfahren;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungVersionsnummer;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme.PruefungVersionsnummerKP;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBSO;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBVR;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Der Bausteinpruefer fuer den Baustein DSME. Ruft alle Feldpruefungen auf.
 */
public class BausteinPrueferDSME extends AbstractBausteinPruefer<FeldNameDSME, FehlerNummerDSME> {

	private final Date verarbDatum;
	private final String zeitangaben;
	private final Datensatz datensatz;
	private final String vfmm;

	private final Baustein<FeldNameDBME> bausteinDbme;
	private final Baustein<FeldNameDBSO> bausteinDbso;
	private final Baustein<FeldNameDBKV> bausteinDbkv;
	private final Baustein<FeldNameDBVR> bausteinDbvr;

	/**
	 * Konstruktor. Nimmt alle Werte entgegen, die fuer die Feld-Pruefungen in
	 * dem Baustein benoetigt werden, und speichert diese ab.
	 * 
	 * @param baustein
	 *            Baustein DSME
	 * @param bausteinDbme
	 *            Baustein DBME
	 * @param bausteinDbso
	 *            Baustein DBSO
	 * @param bausteinDbkv
	 *            Baustein DBKV
	 * @param bausteinDbvr
	 *            Baustein DBVR
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
	public BausteinPrueferDSME(final Baustein<FeldNameDSME> baustein, final Baustein<FeldNameDBME> bausteinDbme, // SUPPRESS
																													// CHECKSTYLE
																													// ParameterNumber
			final Baustein<FeldNameDBSO> bausteinDbso, final Baustein<FeldNameDBKV> bausteinDbkv, final Baustein<FeldNameDBVR> bausteinDbvr, final Date verarbDatum, final String zeitangaben, final Datensatz datensatz, final String vfmm) {
		// @formatter:on

		super(baustein);
		this.bausteinDbme = bausteinDbme;
		this.bausteinDbso = bausteinDbso;
		this.bausteinDbkv = bausteinDbkv;
		this.bausteinDbvr = bausteinDbvr;

		this.verarbDatum = verarbDatum;
		this.zeitangaben = zeitangaben;
		this.datensatz = datensatz;
		this.vfmm = vfmm;
	}

	@Override
	public List<FeldPruefung<FeldNameDSME, FehlerNummerDSME>> getPruefungen() throws UngueltigePrueferDatenException {

		final List<FeldPruefung<FeldNameDSME, FehlerNummerDSME>> pruefList = new ArrayList<FeldPruefung<FeldNameDSME, FehlerNummerDSME>>();
		final String schalterleiste = getSchalterleiste();
		final Baustein<FeldNameDSME> baustein = getBaustein();

		// Felder der Reihenfolge nach auslesen und Pruefungen definieren
		final Feld<FeldNameDSME> feldKennung = baustein.getFeld(FeldNameDSME.KENNUNG);
		final Feld<FeldNameDSME> feldVf = baustein.getFeld(FeldNameDSME.VERFAHREN);
		final Feld<FeldNameDSME> feldBbnrAbsender = baustein.getFeld(FeldNameDSME.BBNR_ABSENDER);
		final Feld<FeldNameDSME> feldBbnrEmpfaenger = baustein.getFeld(FeldNameDSME.BBNR_EMPFAENGER);
		final Feld<FeldNameDSME> feldVersionsnummer = baustein.getFeld(FeldNameDSME.VERSIONS_NR);
		final Feld<FeldNameDSME> feldDatumErstellung = baustein.getFeld(FeldNameDSME.DATUM_ERSTELLUNG);
		final Feld<FeldNameDSME> feldFehlerKennzeichen = baustein.getFeld(FeldNameDSME.FEHLER_KENNZ);
		final Feld<FeldNameDSME> feldFehlerAnzahl = baustein.getFeld(FeldNameDSME.FEHLER_ANZAHL);
		final Feld<FeldNameDSME> feldVsnr = baustein.getFeld(FeldNameDSME.VSNR);
		final Feld<FeldNameDSME> feldVstr = baustein.getFeld(FeldNameDSME.VSTR);
		final Feld<FeldNameDSME> feldBetriebsnummerVerursacher = baustein.getFeld(FeldNameDSME.BBNR_VU);
		final Feld<FeldNameDSME> feldAktenzeichenVerursacher = baustein.getFeld(FeldNameDSME.AKTENZEICHEN_VERURSACHER);
		final Feld<FeldNameDSME> feldBetriebsnummerKrankenkasse = baustein.getFeld(FeldNameDSME.BBNR_KK);
		final Feld<FeldNameDSME> feldAktenzeichenKrankenkasse = baustein.getFeld(FeldNameDSME.AKTENZEICHEN_KK);
		final Feld<FeldNameDSME> feldBetriebsnummerAbrechnung = baustein.getFeld(FeldNameDSME.BBNR_ABRECHNUNGSSTELLE);
		final Feld<FeldNameDSME> feldPersonenGruppe = baustein.getFeld(FeldNameDSME.PERSONENGRUPPE);
		final Feld<FeldNameDSME> feldAbgabegrund = baustein.getFeld(FeldNameDSME.ABGABEGRUND);
		final Feld<FeldNameDSME> feldStaatsangehoerigkeit = baustein.getFeld(FeldNameDSME.STAATSANGEHOERIGKEITS_SC);
		final Feld<FeldNameDSME> feldMmme = baustein.getFeld(FeldNameDSME.MM_MELDEDATEN);
		final Feld<FeldNameDSME> feldMmna = baustein.getFeld(FeldNameDSME.MM_NAME);
		final Feld<FeldNameDSME> feldMmgb = baustein.getFeld(FeldNameDSME.MM_GEBNAME);
		final Feld<FeldNameDSME> feldMman = baustein.getFeld(FeldNameDSME.MM_ANSCHRIFT);
		final Feld<FeldNameDSME> feldMmeu = baustein.getFeld(FeldNameDSME.MM_EUDATEN);
		final Feld<FeldNameDSME> feldMmuv = baustein.getFeld(FeldNameDSME.MM_UVDATEN);
		final Feld<FeldNameDSME> feldMmks = baustein.getFeld(FeldNameDSME.MM_KNV_SEE);
		final Feld<FeldNameDSME> feldMmsv = baustein.getFeld(FeldNameDSME.MM_SVA);
		final Feld<FeldNameDSME> feldMmvr = baustein.getFeld(FeldNameDSME.MM_VERGABE_RUECKMELDUNG);
		final Feld<FeldNameDSME> feldMmrg = baustein.getFeld(FeldNameDSME.MM_RUECKMELDUNG_GERINGFUEGIG);
		final Feld<FeldNameDSME> feldKennzUebergang = baustein.getFeld(FeldNameDSME.KENNZ_UEBERGANG);
		final Feld<FeldNameDSME> feldMmueb = baustein.getFeld(FeldNameDSME.MM_UEBERMITTLUNG);
		final Feld<FeldNameDSME> feldKennzUnipostGeprueft = baustein.getFeld(FeldNameDSME.KENNZ_UNIPOST_GEPRUEFT);
		final Feld<FeldNameDSME> feldMmso = baustein.getFeld(FeldNameDSME.MM_SOFORT);
		final Feld<FeldNameDSME> feldKennzStatus = baustein.getFeld(FeldNameDSME.KENNZ_STATUS);
		final Feld<FeldNameDSME> feldMmue = baustein.getFeld(FeldNameDSME.MM_UEBERW_EINZUGSVG);
		final Feld<FeldNameDSME> feldVersionsNrKP = baustein.getFeld(FeldNameDSME.VERSIONS_NR_KP);
		final Feld<FeldNameDSME> feldMmkv = baustein.getFeld(FeldNameDSME.MM_KVDATEN);
		final Feld<FeldNameDSME> feldReserve = baustein.getFeld(FeldNameDSME.RESERVE);

		if ((feldKennung == null) || (feldVf == null) || (feldBbnrAbsender == null) || (feldBbnrEmpfaenger == null) || (feldVersionsnummer == null) || (feldDatumErstellung == null) || (feldFehlerKennzeichen == null) || (feldFehlerAnzahl == null)
				|| (feldVsnr == null) || (feldVstr == null) || (feldBetriebsnummerVerursacher == null) || (feldAktenzeichenVerursacher == null) || (feldBetriebsnummerKrankenkasse == null) || (feldAktenzeichenKrankenkasse == null)
				|| (feldBetriebsnummerAbrechnung == null) || (feldPersonenGruppe == null) || (feldAbgabegrund == null) || (feldStaatsangehoerigkeit == null) || (feldMmme == null) || (feldMmna == null) || (feldMmgb == null) || (feldMman == null)
				|| (feldMmgb == null) || (feldMman == null) || (feldMmeu == null) || (feldMmuv == null) || (feldMmks == null) || (feldMmsv == null) || (feldMmvr == null) || (feldMmrg == null) || (feldKennzUebergang == null) || (feldMmueb == null)
				|| (feldKennzUnipostGeprueft == null) || (feldMmso == null) || (feldKennzStatus == null) || (feldMmue == null) || (feldVersionsNrKP == null) || (feldMmkv == null) || (feldReserve == null)) {
			throw new UngueltigePrueferDatenException("Pruefung fuer den Baustein " + baustein.getName() + " kann nicht initalisiert werden, eines der Felder ist nicht vorhanden: " + baustein);
		} else {
			pruefList.add(new PruefungKennung(feldKennung, vfmm));
			pruefList.add(new PruefungVerfahren(feldVf, vfmm));
			pruefList.add(new PruefungBetriebsnummerAbsender(feldBbnrAbsender, vfmm));
			pruefList.add(new PruefungBetriebsnummerEmpfaenger(feldBbnrEmpfaenger, vfmm));
			pruefList.add(new PruefungVersionsnummer(feldVersionsnummer));
			pruefList.add(new PruefungDatumErstellung(feldDatumErstellung, verarbDatum, zeitangaben, vfmm));
			pruefList.add(new PruefungFehlerkennzeichnung(feldFehlerKennzeichen));
			pruefList.add(new PruefungFehleranzahl(feldFehlerAnzahl, feldFehlerKennzeichen));
			pruefList.add(new PruefungVSNR(feldVsnr, feldAbgabegrund, feldPersonenGruppe, feldBbnrAbsender, vfmm));
			pruefList.add(new PruefungVSTR(feldVstr, vfmm));
			pruefList.add(new PruefungBetriebsnummerVerursacher(feldBetriebsnummerVerursacher, feldPersonenGruppe, feldVstr, vfmm));
			pruefList.add(new PruefungAktenzeichenVerursacher(feldAktenzeichenVerursacher, bausteinDbme, bausteinDbso, bausteinDbkv, vfmm));
			pruefList.add(new PruefungBetriebsnummerKrankenkasse(feldBetriebsnummerKrankenkasse, feldPersonenGruppe, feldAbgabegrund, feldBetriebsnummerVerursacher, feldBbnrEmpfaenger, feldVsnr, vfmm));
			pruefList.add(new PruefungBetriebsnummerAbrechnungsstelle(feldBetriebsnummerAbrechnung));
			pruefList.add(new PruefungPersonenGruppe(feldPersonenGruppe, feldBetriebsnummerVerursacher, feldAbgabegrund, bausteinDbme, vfmm));
			pruefList.add(new PruefungAbgabegrund(feldAbgabegrund, feldPersonenGruppe, feldBetriebsnummerVerursacher, feldVf, feldVstr, feldVsnr, feldMmkv, feldMmso, bausteinDbme, bausteinDbso, bausteinDbvr, schalterleiste, vfmm));
			pruefList.add(new PruefungStaatsangehoerigkeit(feldStaatsangehoerigkeit, feldAbgabegrund, feldVsnr, feldBetriebsnummerVerursacher, vfmm));
			pruefList.add(new PruefungMmme(feldMmme, datensatz));
			pruefList.add(new PruefungMmna(feldMmna, datensatz));
			pruefList.add(new PruefungMmgb(feldMmgb, datensatz));
			pruefList.add(new PruefungMman(feldMman, datensatz));
			pruefList.add(new PruefungMmeu(feldMmeu, feldStaatsangehoerigkeit, datensatz, vfmm));
			pruefList.add(new PruefungMmuv(feldMmuv, feldPersonenGruppe, bausteinDbme, datensatz, vfmm));
			pruefList.add(new PruefungMmks(feldMmks, feldBetriebsnummerVerursacher, feldMmme, feldPersonenGruppe, datensatz, vfmm));
			pruefList.add(new PruefungMmsv(feldMmsv, datensatz, vfmm));
			pruefList.add(new PruefungMmvr(feldMmvr, datensatz, vfmm));
			pruefList.add(new PruefungMmrg(feldMmrg, datensatz, vfmm));
			pruefList.add(new PruefungKennzUebergang(feldKennzUebergang, vfmm));
			pruefList.add(new PruefungMmUebermittlung(feldMmueb, bausteinDbme, bausteinDbso, bausteinDbkv, vfmm));
			pruefList.add(new PruefungKennzUnipostGeprueft(feldKennzUnipostGeprueft, feldAbgabegrund, vfmm));
			pruefList.add(new PruefungMmso(feldMmso, datensatz, vfmm));
			pruefList.add(new PruefungKennzStatus(feldKennzStatus, feldAbgabegrund, feldPersonenGruppe, bausteinDbme));
			pruefList.add(new PruefungMmue(feldMmue));
			pruefList.add(new PruefungVersionsnummerKP(feldVersionsNrKP, vfmm));
			pruefList.add(new PruefungMmkv(feldMmkv, bausteinDbme, bausteinDbso, datensatz));
			pruefList.add(new PruefungReserve(feldReserve));
		}

		return pruefList;
	}

	/**
	 * Baut die Schalttabelle nach Anlage 4 zusammen. Ist nur wegen Tests auf
	 * protected gesetzt.
	 * 
	 * @return
	 */
	protected String getSchalterleiste() {
		final Baustein<FeldNameDSME> baustein = getBaustein();

		final StringBuilder schalterleiste = new StringBuilder("J"); // DSME
		schalterleiste.append(baustein.getFeld(FeldNameDSME.MM_MELDEDATEN).getTrimmedValue());
		schalterleiste.append(baustein.getFeld(FeldNameDSME.MM_NAME).getTrimmedValue());
		schalterleiste.append(baustein.getFeld(FeldNameDSME.MM_GEBNAME).getTrimmedValue());
		schalterleiste.append(baustein.getFeld(FeldNameDSME.MM_ANSCHRIFT).getTrimmedValue());
		schalterleiste.append(baustein.getFeld(FeldNameDSME.MM_EUDATEN).getTrimmedValue());
		schalterleiste.append(baustein.getFeld(FeldNameDSME.MM_UVDATEN).getTrimmedValue());
		schalterleiste.append(baustein.getFeld(FeldNameDSME.MM_KNV_SEE).getTrimmedValue());
		schalterleiste.append(baustein.getFeld(FeldNameDSME.MM_SVA).getTrimmedValue());
		schalterleiste.append(baustein.getFeld(FeldNameDSME.MM_VERGABE_RUECKMELDUNG).getTrimmedValue());
		schalterleiste.append(baustein.getFeld(FeldNameDSME.MM_RUECKMELDUNG_GERINGFUEGIG).getTrimmedValue());
		schalterleiste.append(baustein.getFeld(FeldNameDSME.MM_SOFORT).getTrimmedValue());
		schalterleiste.append(baustein.getFeld(FeldNameDSME.MM_KVDATEN).getTrimmedValue());

		return schalterleiste.toString();
	}

}
