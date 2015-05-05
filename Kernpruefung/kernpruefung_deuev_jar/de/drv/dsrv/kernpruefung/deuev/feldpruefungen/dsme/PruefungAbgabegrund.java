package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefungErgebnis;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungAehnlicherString;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNotInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBSO;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBVR;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.ListenTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Pruefung fuer das Feld Abgabegrund aus dem Baustein DSME.
 */
public class PruefungAbgabegrund extends AbstractFeldPruefung<FeldNameDSME, FehlerNummerDSME> {

	private static final String SOFORTMELDUNG = "20";

	private static final List<String> VFMM_AGTRV = Arrays.asList("AGTRV");
	private static final List<String> VFMM_GRUNDSTELLUNG1 = Arrays.asList("AGDEU", "KVDEU", "WLTKV", "KSTKV");
	private static final List<String> VFMM_GRUNDSTELLUNG2 = Arrays.asList("AGDEU", "KVDEU", "WLTKV");
	private static final List<String> VFMM_99 = Arrays.asList("BATRV", "KTTRV", "ZFTRV");
	private static final List<String> VFMM_BUNDESWEHRZIVIL = Arrays.asList("BWTRV", "BZTRV");
	private static final List<String> VFMM_UNSTAENDIG_BESCH = Arrays.asList("KVTRV", "KVTWL", "WLTKV", "DSTBF", "BFTDS");
	private static final List<String> VFMM_ABMELDUNG_KRANKENKASSE = Arrays.asList("KVTRV", "KVTWL", "WLTKV", "DSTBF", "BFTDS");
	private static final List<String> VFMM_MONATSMELDUNG = Arrays.asList("AGDEU", "WLTKV", "KSTKV");

	private static final List<String> PERSGR_KURZBESCH = Arrays.asList("110", "210");
	private static final List<String> PERSGR_ENTGELT_ALTER = Arrays.asList("103", "142");
	private static final List<String> PERSGR_BEHIN_REHAB = Arrays.asList("107", "204");

	private static final List<String> GD_KUENSTLER_UNGLEICH = Arrays.asList("60", "61", "90", "99");
	private static final List<String> GD_KUENSTLER_BBNRVU = Arrays.asList("01085914", "28180427");
	private static final List<String> GD_99 = Arrays.asList("99");
	private static final List<String> GD_BUNDESWEHRZIVIL = Arrays.asList("30", "49", "99");
	private static final List<String> GD_PRIVATE_PK = Arrays.asList("30", "50", "57", "60", "61", "99");
	private static final List<String> GD_VSTR = Arrays.asList("60", "61", "62", "63", "90", "99", "80");
	private static final List<String> GD_BEHIN_REHAB = Arrays.asList("60", "61", "80", "90", "99");
	private static final List<String> GD_VSNR_VERLANGT = Arrays.asList("10", "11", "12", "13", "58", "20", "99");
	private static final List<String> GD_KURZFRISTIG_BESCH = Arrays.asList("63", "90");
	private static final List<String> GD_ABMELDUNG_KRANKENKASSE = Arrays.asList("94", "95");
	private static final List<String> GD_GESETZL_UNFALLVERS = Arrays.asList("10", "11", "12", "13", "20", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53",
			"55", "60", "61", "62", "63", "71", "91", "94", "95", "99");
	private static final List<String> GD_MONATSMELD_UNZUL = Arrays.asList("58");
	private static final List<String> GD_PERSGR_GRUNDSTELLUNG = Arrays.asList("20", "60", "61", "90", "99");

	private static final List<String> VSTR_GD = Arrays.asList("0A", "0C");

	private static final List<String> BETRIEBSNUMMER_BEHIN_REHAB = Arrays.asList("985", "987");
	private static final List<String> BETRIEBSNUMMER_KNAPPSCHAFT = Arrays.asList("980", "098");

	private static final int ANMELDUNG_MIN = 10;
	private static final int ANMELDUNG_MAX = 13;
	private static final int INT_SOFORTMELDUNG = 20;
	private static final int AN_ABMELDUNG = 40;
	private static final int ENTGELTERSATZLEISTUNG_ALTER = 56;
	private static final int GKV_MONATSMELDUNG = 58;
	private static final int UNSTAENDIG_BESCHAEFTIGTE = 59;

	private static final String PERS_KUENSTLER = "203";
	private static final String PERS_KURZFRISTIG_BESCH = "202";
	private static final String PERS_GESETZL_UNFALLVERS = "190";
	private static final List<String> PERS_MONATSMELD_UNZUL = Arrays.asList("109", "110", "190");

	private static final String VFMM_RUECKMELDUNG = "RVTAG";
	private static final String VF_RUECKMELDUNG = "RVSNR";

	// Bei diesen Werten kann der Baustein sowohl vorkommen (J) als auch nicht
	// vorkommen (N) und kann deswegen nicht abgeglichen werden.
	private static final List<Character> SCHALTTABELLE_UEBERSPRINGEN = Arrays.asList('K', 'k', 'm');

	private static final List<String> DBVR_GD_1 = Arrays.asList("01", "10", "99");
	private static final List<String> DBVR_GD_2 = Arrays.asList("02", "03", "11");
	private static final List<String> DBVR_GD_3 = Arrays.asList("04", "05");
	private static final List<String> DBVR_GD_4 = Arrays.asList("80", "81", "82", "83", "84", "85");

	private static final int ABGABEGRUND_10 = 10;
	private static final int ABGABEGRUND_11 = 11;
	private static final int ABGABEGRUND_12 = 12;
	private static final int ABGABEGRUND_13 = 13;
	private static final int ABGABEGRUND_20 = 20;
	private static final int ABGABEGRUND_30 = 30;
	private static final int ABGABEGRUND_31 = 31;
	private static final int ABGABEGRUND_32 = 32;
	private static final int ABGABEGRUND_33 = 33;
	private static final int ABGABEGRUND_34 = 34;
	private static final int ABGABEGRUND_35 = 35;
	private static final int ABGABEGRUND_36 = 36;
	private static final int ABGABEGRUND_40 = 40;
	private static final int ABGABEGRUND_49 = 49;
	private static final int ABGABEGRUND_50 = 50;
	private static final int ABGABEGRUND_51 = 51;
	private static final int ABGABEGRUND_52 = 52;
	private static final int ABGABEGRUND_53 = 53;
	private static final int ABGABEGRUND_54 = 54;
	private static final int ABGABEGRUND_55 = 55;
	private static final int ABGABEGRUND_56 = 56;
	private static final int ABGABEGRUND_57 = 57;
	private static final int ABGABEGRUND_58 = 58;
	private static final int ABGABEGRUND_59 = 59;
	private static final int ABGABEGRUND_60 = 60;
	private static final int ABGABEGRUND_61 = 61;
	private static final int ABGABEGRUND_62 = 62;
	private static final int ABGABEGRUND_63 = 63;
	private static final int ABGABEGRUND_70 = 70;
	private static final int ABGABEGRUND_71 = 71;
	private static final int ABGABEGRUND_72 = 72;
	private static final int ABGABEGRUND_80 = 80;
	private static final int ABGABEGRUND_90 = 90;
	private static final int ABGABEGRUND_91 = 91;
	private static final int ABGABEGRUND_94 = 94;
	private static final int ABGABEGRUND_95 = 95;
	private static final int ABGABEGRUND_99 = 99;

	private static final int LAENGE_BBNR_START = 3;

	private static final String VF_DEUEV = "DEUEV";
	private static final String VF_KVNR = "KVNR ";

	private final Feld<FeldNameDSME> feldPersGr;
	private final Feld<FeldNameDSME> feldBbnrvu;
	private final Feld<FeldNameDSME> feldVF;
	private final Feld<FeldNameDSME> feldVstr;
	private final Feld<FeldNameDSME> feldVsnr;
	private final Feld<FeldNameDSME> feldMmkv;
	private final Feld<FeldNameDSME> feldMmso;

	private final Baustein<FeldNameDBME> bausteinDbme;
	private final Baustein<FeldNameDBSO> bausteinDbso;
	private final Baustein<FeldNameDBVR> bausteinDbvr;

	private final String vfmm;
	private final String schalttabelle;

	private final List<String> abgabegd;
	private final List<String> interimsVsnr;
	private final List<String> versicherungsnummer;

	private FeldPruefungErgebnis<FehlerNummerDSME> pruefungFehler;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldPersGr
	 *            Feld Personengruppe aus DSME
	 * @param feldBbnrvu
	 *            Feld Betriebsnummer-Verursacher aus DSME
	 * @param feldVF
	 *            Feld Verfahren aus DSME
	 * @param feldVstr
	 *            Feld VSTR aus DSME
	 * @param feldVsnr
	 *            Feld VSNR (Versicherungsnummer) aus DSME
	 * @param bausteinDbvr
	 *            Baustein DBVR
	 * @param schalttabelle
	 *            Schalterleiste als <code>String</code>
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	// @formatter:off
	public PruefungAbgabegrund(final Feld<FeldNameDSME> feld,
			final Feld<FeldNameDSME> feldPersGr, // SUPPRESS CHECKSTYLE ParameterNumber
			final Feld<FeldNameDSME> feldBbnrvu, final Feld<FeldNameDSME> feldVF, final Feld<FeldNameDSME> feldVstr, final Feld<FeldNameDSME> feldVsnr, final Feld<FeldNameDSME> feldMmkv, final Feld<FeldNameDSME> feldMmso,
			final Baustein<FeldNameDBME> bausteinDbme, final Baustein<FeldNameDBSO> bausteinDbso, final Baustein<FeldNameDBVR> bausteinDbvr, final String schalttabelle, final String vfmm) {
		// @formatter:on
		super(feld);

		this.vfmm = vfmm;
		this.versicherungsnummer = WertelistenVerwaltungDeuev.getInstance().getWerteliste(ListenTypDeuev.VERSICHERUNGSNUMMER);
		this.interimsVsnr = WertelistenVerwaltungDeuev.getInstance().getWerteliste(ListenTypDeuev.INTERIMSVERSICHERUNGSNUMMER);
		this.abgabegd = WertelistenVerwaltungDeuev.getInstance().getWerteliste(ListenTypDeuev.A1_SCHLUESSELZAHL_ABGABEGRUND);
		this.schalttabelle = schalttabelle;
		this.bausteinDbme = bausteinDbme;
		this.bausteinDbso = bausteinDbso;
		this.bausteinDbvr = bausteinDbvr;
		this.feldVsnr = feldVsnr;
		this.feldVstr = feldVstr;
		this.feldVF = feldVF;
		this.feldBbnrvu = feldBbnrvu;
		this.feldPersGr = feldPersGr;
		this.feldMmkv = feldMmkv;
		this.feldMmso = feldMmso;

		if (LOGGER.isLoggable(Level.FINE)) {
			LOGGER.log(Level.FINE, "[DSME Abgabegrund] \"" + feld.getValue() + "\"");
		}
	}

	// @formatter:off
	@Override
	// SUPPRESS CHECKSTYLE MethodLengthCheck
	// @formatter:on
	public void initialisierePruefungen() {
		boolean grundstellungVsnr = false;
		pruefungFehler = new FeldPruefungErgebnis<FehlerNummerDSME>();

		Feld<FeldNameDSME> feldVfmm;
		if (vfmm.length() > 0) {
			feldVfmm = new Feld<FeldNameDSME>(vfmm);
		} else {
			feldVfmm = new Feld<FeldNameDSME>("invalidVfmm");
		}

		String bereichsnummer;
		if (feldVsnr.getTrimmedValue().length() > 2) {
			bereichsnummer = feldVsnr.getTrimmedValue().substring(0, 2);
		} else {
			bereichsnummer = "invalidBereichsnummer";
		}
		final Feld<FeldNameDSME> feldVsnrBereichsnummer = new Feld<FeldNameDSME>(bereichsnummer);

		// Erste Pruefung, da es ansonsten Exceptions schmeisst.
		final PruefungNichtLeer me230a = new PruefungNichtLeer(getFeld());
		addPruefung(me230a, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME230));

		if (getFeld().getTrimmedValue().length() > 0) {
			// Pruefung auf Grundstellung vorgezogen (etwas komplizierter in
			// mehrere
			// if / else if Abfragen geschrieben, damit es leserlich bleibt)
			int intFeld = 0;
			try {
				intFeld = Integer.parseInt(getFeld().getTrimmedValue());

				if ((((intFeld >= ANMELDUNG_MIN) && (intFeld <= ANMELDUNG_MAX)) || (intFeld == GKV_MONATSMELDUNG)) && VFMM_GRUNDSTELLUNG1.contains(vfmm)) {
					grundstellungVsnr = true;
				} else if ((intFeld == INT_SOFORTMELDUNG) && (vfmm.compareTo("AGTRV") == 0)) {
					grundstellungVsnr = true;
				} else if ((intFeld == AN_ABMELDUNG) && PERSGR_KURZBESCH.contains(feldPersGr.getTrimmedValue()) && VFMM_GRUNDSTELLUNG2.contains(vfmm)) {
					grundstellungVsnr = true;
				}
			} catch (final NumberFormatException e) {
				if (LOGGER.isLoggable(Level.FINE)) {
					LOGGER.log(Level.FINE, e.toString());
				}
				grundstellungVsnr = false;
			}

			final PruefungNumerisch me230 = new PruefungNumerisch(getFeld(), true);
			addPruefung(me230, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME230));

			final PruefungInList me232 = new PruefungInList(abgabegd, getFeld());
			addPruefung(me232, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME232));

			if (getFeld().getTrimmedValue().compareTo(SOFORTMELDUNG) == 0) {
				final PruefungInList me233 = new PruefungInList(VFMM_AGTRV, feldVfmm, true);
				addPruefung(me233, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME233));
			}

			if (getFeld().getTrimmedValue().compareTo(SOFORTMELDUNG) != 0) {
				final PruefungNotInList me237 = new PruefungNotInList(VFMM_AGTRV, feldVfmm);
				addPruefung(me237, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME237));
			}

			if (!grundstellungVsnr) {
				final PruefungNichtLeer me234 = new PruefungNichtLeer(feldVsnr);
				addPruefung(me234, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME234));
			}

			if ((feldPersGr.getTrimmedValue().compareTo(PERS_KUENSTLER) == 0) && !GD_KUENSTLER_UNGLEICH.contains(getFeld().getTrimmedValue())) {

				if (StringUtils.isNotBlank(feldBbnrvu.getTrimmedValue())) {
					final PruefungInList me235 = new PruefungInList(GD_KUENSTLER_BBNRVU, feldBbnrvu);
					addPruefung(me235, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME235));
				} else {
					pruefungFehler.addFehler(new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME235));
				}
			}

			if (VFMM_99.contains(vfmm) || (feldVF.getTrimmedValue().compareTo("KVNR") == 0)) {
				final PruefungInList me236 = new PruefungInList(GD_99, getFeld());
				addPruefung(me236, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME236));
			}

			if (VFMM_BUNDESWEHRZIVIL.contains(vfmm)) {
				final PruefungInList me238 = new PruefungInList(GD_BUNDESWEHRZIVIL, getFeld());
				addPruefung(me238, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME238));
			}

			if (intFeld == UNSTAENDIG_BESCHAEFTIGTE) {
				final PruefungInList me239 = new PruefungInList(VFMM_UNSTAENDIG_BESCH, feldVfmm);
				addPruefung(me239, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME239));
			}

			if (vfmm.compareTo("PVTRV") == 0) {
				final PruefungInList me240 = new PruefungInList(GD_PRIVATE_PK, getFeld());
				addPruefung(me240, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME240));
			}

			if (GD_VSTR.contains(getFeld().getTrimmedValue())) {
				if (feldVstr.getTrimmedValue().length() > 0) {
					final PruefungInList me241 = new PruefungInList(VSTR_GD, feldVstr, true);
					addPruefung(me241, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME241));
				}
			}

			if (interimsVsnr.contains(bereichsnummer)) {
				final PruefungInList me242 = new PruefungInList(GD_99, getFeld());
				addPruefung(me242, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME242));
			}

			if (intFeld == ENTGELTERSATZLEISTUNG_ALTER) {
				final PruefungInList me243 = new PruefungInList(PERSGR_ENTGELT_ALTER, feldPersGr);
				addPruefung(me243, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME243));
			}

			if (feldPersGr.getTrimmedValue().compareTo("000") == 0) {
				final PruefungInList me244 = new PruefungInList(GD_PERSGR_GRUNDSTELLUNG, getFeld());
				addPruefung(me244, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME244));
			}

			if (feldBbnrvu.getTrimmedValue().length() > 0 && PERSGR_BEHIN_REHAB.contains(feldPersGr.getTrimmedValue()) && !GD_BEHIN_REHAB.contains(getFeld().getTrimmedValue())) {
				final Feld<FeldNameDSME> feldBbnrvuBeginn = new Feld<FeldNameDSME>(feldBbnrvu.getTrimmedValue().substring(0, 3));
				final PruefungInList me245 = new PruefungInList(BETRIEBSNUMMER_BEHIN_REHAB, feldBbnrvuBeginn);
				addPruefung(me245, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME245));
			}

			if (feldVsnrBereichsnummer.getTrimmedValue().length() == 2 && !GD_VSNR_VERLANGT.contains(getFeld().getTrimmedValue())) {
				final PruefungInList me246 = new PruefungInList(versicherungsnummer, feldVsnrBereichsnummer, true);
				addPruefung(me246, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME246));
			}

			if (feldPersGr.getTrimmedValue().compareTo(PERS_KURZFRISTIG_BESCH) == 0) {
				final PruefungNotInList me247 = new PruefungNotInList(GD_KURZFRISTIG_BESCH, getFeld());
				addPruefung(me247, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME247));
			}
			fehlernummerME248(feldVsnr, feldBbnrvu, feldVF, bausteinDbvr, schalttabelle);
			if (GD_ABMELDUNG_KRANKENKASSE.contains(getFeld().getTrimmedValue())) {
				final PruefungInList me249 = new PruefungInList(VFMM_ABMELDUNG_KRANKENKASSE, feldVfmm, true);
				addPruefung(me249, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME249));
			}
			if (feldPersGr.getTrimmedValue().compareTo(PERS_GESETZL_UNFALLVERS) == 0) {
				final PruefungInList me251 = new PruefungInList(GD_GESETZL_UNFALLVERS, getFeld());
				addPruefung(me251, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME251));
			}
			if (PERS_MONATSMELD_UNZUL.contains(feldPersGr.getTrimmedValue())) {
				final PruefungNotInList me231 = new PruefungNotInList(GD_MONATSMELD_UNZUL, getFeld());
				addPruefung(me231, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME231));
			}
			if (intFeld == GKV_MONATSMELDUNG) {
				final PruefungInList me229 = new PruefungInList(VFMM_MONATSMELDUNG, feldVfmm, true);
				addPruefung(me229, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME229));
			}
		}
	}

	/**
	 * Ueberpruefung gemaess Anlage 4.
	 * 
	 * @param feldVsnr
	 * @param feldBbnrvu
	 * @param feldVF
	 * @param bausteinDbvr
	 * @param schalttabelle
	 */
	// @formatter:off
	private void fehlernummerME248(final Feld<FeldNameDSME> feldVsnr, final Feld<FeldNameDSME> feldBbnrvu, // SUPPRESS CHECKSTYLE MethodLengthCheck
			final Feld<FeldNameDSME> feldVF, final Baustein<FeldNameDBVR> bausteinDbvr, final String schalttabelle) {
		// @formatter:on

		final Feld<FeldNameDSME> feldSchalttabelle = new Feld<FeldNameDSME>(schalttabelle);

		try {
			final int intGD = Integer.parseInt(getFeld().getTrimmedValue());
			String bbnrvuStart;
			if (feldBbnrvu.getTrimmedValue().length() > LAENGE_BBNR_START) {
				bbnrvuStart = feldBbnrvu.getTrimmedValue().substring(0, LAENGE_BBNR_START);
			} else {
				bbnrvuStart = "";
			}

			if (this.feldMmkv.getTrimmedValue().length() == 0
					&& ((bausteinDbme != null && bausteinDbme.getFeld(FeldNameDBME.KENNZ_STORNO).getTrimmedValue().compareTo("J") == 0) || (bausteinDbso != null && bausteinDbso.getFeld(FeldNameDBSO.KENNZ_STORNO_SOFORT).getTrimmedValue()
							.compareTo("J") == 0)))
				feldSchalttabelle.setValue(feldSchalttabelle.getTrimmedValue() + "N");

			if (this.feldMmso.getTrimmedValue().length() == 0)
				feldSchalttabelle.setValue(feldSchalttabelle.getTrimmedValue() + "N");

			switch (intGD) {
			case ABGABEGRUND_10:
			case ABGABEGRUND_11:
			case ABGABEGRUND_12:
			case ABGABEGRUND_13:

				if (StringUtils.isNotBlank(feldVsnr.getTrimmedValue()) && !BETRIEBSNUMMER_KNAPPSCHAFT.contains(bbnrvuStart)) {
					final PruefungAehnlicherString me248 = new PruefungAehnlicherString(feldSchalttabelle, "JJJNJNNmNNNNN", SCHALTTABELLE_UEBERSPRINGEN);
					addPruefung(me248, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
				}

				else if (StringUtils.isNotBlank(feldVsnr.getTrimmedValue()) && BETRIEBSNUMMER_KNAPPSCHAFT.contains(bbnrvuStart)) {
					final PruefungAehnlicherString me248 = new PruefungAehnlicherString(feldSchalttabelle, "JJJJJNNmNNNNN", SCHALTTABELLE_UEBERSPRINGEN);
					addPruefung(me248, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
				}

				else if (StringUtils.isBlank(feldVsnr.getTrimmedValue())) {
					final PruefungAehnlicherString me248 = new PruefungAehnlicherString(feldSchalttabelle, "JJJJJKNmNNNNN", SCHALTTABELLE_UEBERSPRINGEN);
					addPruefung(me248, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
				}

				break;

			case ABGABEGRUND_20:
				if (VF_RUECKMELDUNG.equals(feldVF.getTrimmedValue()) && VFMM_RUECKMELDUNG.equals(vfmm)) {
					final PruefungAehnlicherString me248 = new PruefungAehnlicherString(feldSchalttabelle, "JNNNNNNNNJNNN", SCHALTTABELLE_UEBERSPRINGEN);
					addPruefung(me248, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
				}

				else {
					if (StringUtils.isNotBlank(feldVsnr.getTrimmedValue())) {
						final PruefungAehnlicherString me248 = new PruefungAehnlicherString(feldSchalttabelle, "JNJNNNNNNNNJN", SCHALTTABELLE_UEBERSPRINGEN);
						addPruefung(me248, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
					}

					else {
						final PruefungAehnlicherString me248 = new PruefungAehnlicherString(feldSchalttabelle, "JNJJJKNNNNNJN", SCHALTTABELLE_UEBERSPRINGEN);
						addPruefung(me248, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
					}
				}

				break;

			case ABGABEGRUND_30:
			case ABGABEGRUND_31:
			case ABGABEGRUND_32:
			case ABGABEGRUND_33:
			case ABGABEGRUND_34:
			case ABGABEGRUND_35:
			case ABGABEGRUND_36:
				final PruefungAehnlicherString me248 = new PruefungAehnlicherString(feldSchalttabelle, "JJkNkNmmNNNNN", SCHALTTABELLE_UEBERSPRINGEN);
				addPruefung(me248, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
				break;

			case ABGABEGRUND_40:

				if (StringUtils.isNotBlank(feldVsnr.getTrimmedValue()) && !BETRIEBSNUMMER_KNAPPSCHAFT.contains(bbnrvuStart)) {
					final PruefungAehnlicherString me248b = new PruefungAehnlicherString(feldSchalttabelle, "JJJNJNmmNNNNN", SCHALTTABELLE_UEBERSPRINGEN);
					addPruefung(me248b, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
				}

				else if (StringUtils.isNotBlank(feldVsnr.getTrimmedValue()) && BETRIEBSNUMMER_KNAPPSCHAFT.contains(bbnrvuStart)) {
					final PruefungAehnlicherString me248b = new PruefungAehnlicherString(feldSchalttabelle, "JJJJJNmmNNNNN", SCHALTTABELLE_UEBERSPRINGEN);
					addPruefung(me248b, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
				}

				else if (StringUtils.isBlank(feldVsnr.getTrimmedValue())) {
					final PruefungAehnlicherString me248b = new PruefungAehnlicherString(feldSchalttabelle, "JJJJJKmmNNNNN", SCHALTTABELLE_UEBERSPRINGEN);
					addPruefung(me248b, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
				}

				break;

			case ABGABEGRUND_49:
			case ABGABEGRUND_50:
			case ABGABEGRUND_51:
			case ABGABEGRUND_52:
			case ABGABEGRUND_53:
			case ABGABEGRUND_54:
			case ABGABEGRUND_55:
			case ABGABEGRUND_57:
				final PruefungAehnlicherString me248c = new PruefungAehnlicherString(feldSchalttabelle, "JJkNkNmmNNNNN", SCHALTTABELLE_UEBERSPRINGEN);
				addPruefung(me248c, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
				break;

			case ABGABEGRUND_56:
				final PruefungAehnlicherString me248d = new PruefungAehnlicherString(feldSchalttabelle, "JJkNkNNmNNNNN", SCHALTTABELLE_UEBERSPRINGEN);
				addPruefung(me248d, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
				break;

			case ABGABEGRUND_58:
				final PruefungAehnlicherString me248e = new PruefungAehnlicherString(feldSchalttabelle, "JNJNNNNNNNNNJ", SCHALTTABELLE_UEBERSPRINGEN);
				addPruefung(me248e, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
				break;

			case ABGABEGRUND_59:
				final PruefungAehnlicherString me248z = new PruefungAehnlicherString(feldSchalttabelle, "JJNNNNNNNNNNN", SCHALTTABELLE_UEBERSPRINGEN);
				addPruefung(me248z, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
				break;

			case ABGABEGRUND_60:
				final PruefungAehnlicherString me248f = new PruefungAehnlicherString(feldSchalttabelle, "JNJNkNNNNNNNN", SCHALTTABELLE_UEBERSPRINGEN);
				addPruefung(me248f, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
				break;

			case ABGABEGRUND_61:
				final PruefungAehnlicherString me248g = new PruefungAehnlicherString(feldSchalttabelle, "JNNNJNNNNNNNN", SCHALTTABELLE_UEBERSPRINGEN);
				addPruefung(me248g, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
				break;

			case ABGABEGRUND_62:
			case ABGABEGRUND_63:
				final PruefungAehnlicherString me248h = new PruefungAehnlicherString(feldSchalttabelle, "JNNNNNNNNNNNN", SCHALTTABELLE_UEBERSPRINGEN);
				addPruefung(me248h, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
				break;

			case ABGABEGRUND_70:
			case ABGABEGRUND_72:
				final PruefungAehnlicherString me248i = new PruefungAehnlicherString(feldSchalttabelle, "JJkNkNNmNNNNN", SCHALTTABELLE_UEBERSPRINGEN);
				addPruefung(me248i, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
				break;

			case ABGABEGRUND_71:
				final PruefungAehnlicherString me248j = new PruefungAehnlicherString(feldSchalttabelle, "JJkNkNmmNNNNN", SCHALTTABELLE_UEBERSPRINGEN);
				addPruefung(me248j, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
				break;

			case ABGABEGRUND_80:
				final PruefungAehnlicherString me248k = new PruefungAehnlicherString(feldSchalttabelle, "JNNNNNNNNNJNN", SCHALTTABELLE_UEBERSPRINGEN);
				addPruefung(me248k, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
				break;

			case ABGABEGRUND_90:
				final PruefungAehnlicherString me248l = new PruefungAehnlicherString(feldSchalttabelle, "JNJNJNNNJNNNN", SCHALTTABELLE_UEBERSPRINGEN);
				addPruefung(me248l, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
				break;

			case ABGABEGRUND_91:
				final PruefungAehnlicherString me248m = new PruefungAehnlicherString(feldSchalttabelle, "JJkNkNJmNNNNN", SCHALTTABELLE_UEBERSPRINGEN);
				addPruefung(me248m, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
				break;

			case ABGABEGRUND_94:
			case ABGABEGRUND_95:
				final PruefungAehnlicherString me248n = new PruefungAehnlicherString(feldSchalttabelle, "JJkNkNNmNNNNN", SCHALTTABELLE_UEBERSPRINGEN);
				addPruefung(me248n, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
				break;

			case ABGABEGRUND_99:
				if (bausteinDbvr == null) {
					// erzeugt Fehler
					final PruefungAehnlicherString me248o = new PruefungAehnlicherString(feldSchalttabelle, "", SCHALTTABELLE_UEBERSPRINGEN);
					addPruefung(me248o, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
				} else {
					final Feld<FeldNameDBVR> feldDbvrAbgabegrund = bausteinDbvr.getFeld(FeldNameDBVR.ABGABEGRUND);

					if (DBVR_GD_1.contains(feldDbvrAbgabegrund.getTrimmedValue())) {
						final PruefungAehnlicherString me248o = new PruefungAehnlicherString(feldSchalttabelle, "JNJJJKNNNJNNN", SCHALTTABELLE_UEBERSPRINGEN);
						addPruefung(me248o, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
						break;
					}

					else if (DBVR_GD_2.contains(feldDbvrAbgabegrund.getTrimmedValue())) {
						final PruefungAehnlicherString me248p = new PruefungAehnlicherString(feldSchalttabelle, "JNkNkNNNNJNNN", SCHALTTABELLE_UEBERSPRINGEN);
						addPruefung(me248p, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
						break;
					}

					else if (DBVR_GD_3.contains(feldDbvrAbgabegrund.getTrimmedValue()) && feldVF.getValue().equals(VF_DEUEV)) {
						final PruefungAehnlicherString me248q = new PruefungAehnlicherString(feldSchalttabelle, "JNJKJKNNNJNNN", SCHALTTABELLE_UEBERSPRINGEN);
						addPruefung(me248q, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
						break;
					}

					else if (DBVR_GD_3.contains(feldDbvrAbgabegrund.getTrimmedValue()) && feldVF.getValue().equals(VF_KVNR)) {
						final PruefungAehnlicherString me248q = new PruefungAehnlicherString(feldSchalttabelle, "JNJJJKNNNJNNN", SCHALTTABELLE_UEBERSPRINGEN);
						addPruefung(me248q, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
						break;
					}

					else if (DBVR_GD_4.contains(feldDbvrAbgabegrund.getTrimmedValue())) {
						final PruefungAehnlicherString me248q = new PruefungAehnlicherString(feldSchalttabelle, "JNJJJKNNNJNNN", SCHALTTABELLE_UEBERSPRINGEN);
						addPruefung(me248q, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
						break;
					}

					else {
						// erzeugt Fehler
						final PruefungAehnlicherString me248o = new PruefungAehnlicherString(feldSchalttabelle, "", SCHALTTABELLE_UEBERSPRINGEN);
						addPruefung(me248o, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
					}

				}

				break;

			default:

				// erzeugt Fehler
				final PruefungAehnlicherString me248o = new PruefungAehnlicherString(feldSchalttabelle, "", SCHALTTABELLE_UEBERSPRINGEN);
				addPruefung(me248o, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
				break;
			}

		} catch (final NumberFormatException e) {
			// erzeugt Fehler
			final PruefungAehnlicherString me248o = new PruefungAehnlicherString(feldSchalttabelle, "", SCHALTTABELLE_UEBERSPRINGEN);
			addPruefung(me248o, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME248));
		}
	}

	@Override
	public FeldPruefungErgebnis<FehlerNummerDSME> pruefe() throws UngueltigeDatenException {
		FeldPruefungErgebnis<FehlerNummerDSME> pruefungErgebnisSuper = super.pruefe();
		if (pruefungErgebnisSuper.isErfolgreichInklHinweis()) {
			pruefungErgebnisSuper = pruefungFehler;
		}
		return pruefungErgebnisSuper;
	}
}
