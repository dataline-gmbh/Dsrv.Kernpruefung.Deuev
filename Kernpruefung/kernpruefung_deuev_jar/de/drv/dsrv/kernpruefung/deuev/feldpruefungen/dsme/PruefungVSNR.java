package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNotInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungPattern;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.Pruefziffer;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;
import de.drv.dsrv.kernpruefung.deuev.pruefungen.PruefungVersicherungsnummerGeburtsdatum;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.ListenTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Validiert das Feld VSNR im Baustein DSME. Dabei wird neben der Fehlernummern
 * aus Anlage 9.4 auch die Entscheidungstabelle
 * ab522000_Logik_PY-DT-Identifikation.doc hergenommen.
 */
public class PruefungVSNR extends AbstractFeldPruefung<FeldNameDSME, FehlerNummerDSME> {

	private static final List<String> GRUNDST_FALL1 = Arrays.asList("AGDEU", "WLTKV", "KSTKV", "KVDEU");
	private static final List<String> GRUNDST_FALL3 = Arrays.asList("AGDEU", "KVDEU", "WLTKV");
	private static final List<String> VFMM_BN_40 = Arrays.asList("ZFTRV", "RVTZF");
	private static final List<String> VFMM_BN_42 = Arrays.asList("ZFTRV", "RVTZF");
	private static final List<String> VFMM_BN_88 = Arrays.asList("BATRV", "KTTRV");
	private static final List<String> VFMM_KEINE_INTERIM = Arrays.asList("AGDEU", "AGTRV", "KSTKV");
	private static final List<String> VFMM_INTERIM = Arrays.asList("BATRV", "KTTRV");

	private static final List<String> BBNRAB_BN_00 = Arrays.asList("98000001", "98000006", "99086875");
	private static final List<String> BN_KVTRV = Arrays.asList("83", "84", "85", "86", "87");

	private static final int ANMELDUNG_GD_MIN = 10;
	private static final int ANMELDUNG_GD_MAX = 13;
	private static final int GKV_MONATSMELDUNG = 58;
	private static final int SOFORTMELDUNG = 20;
	private static final int AN_UND_ABMELDUNG = 40;

	private static final int INDEX_NUMMER_START_A = 0;
	private static final int INDEX_NUMMER_ENDE_A = 8;

	private static final int INDEX_BUCHSTABE_START = INDEX_NUMMER_ENDE_A;
	private static final int INDEX_BUCHSTABE_ENDE = 9;

	private static final int INDEX_NUMMER_START_B = INDEX_BUCHSTABE_ENDE;
	private static final int INDEX_NUMMER_ENDE_B = 12;

	private static final int INDEX_BEREICHSNUMMER_START = 0;
	private static final int INDEX_BEREICHSNUMMER_ENDE = 2;

	private static final int INDEX_GEBURTSDATUM_BEGINN = INDEX_BEREICHSNUMMER_ENDE;
	private static final int INDEX_GEBURTSDATUM_ENDE = 8;

	private static final int INDEX_PRUEFZIFFER_START = 11;
	private static final int INDEX_PRUEFZIFFER_ENDE = 12;

	private static final int LAENGE_VSNR = 12;
	private final Feld<FeldNameDSME> feldGD;
	private final Feld<FeldNameDSME> feldBbnrab;
	private final Feld<FeldNameDSME> feldPersGr;
	private final List<String> testVsnr;
	private final List<String> interimsVsnr;
	private final List<String> versicherungsnummer;
	private final String vfmm;

	private Feld<FeldNameDSME> feldGeburtsdatum;
	private Feld<FeldNameDSME> bereichsnummer;
	private Feld<FeldNameDSME> feldVfmm;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldGD
	 *            Feld Abgabegrund aus DSME
	 * @param feldPersGr
	 *            Feld Personengruppe aus DSME
	 * @param feldBbnrab
	 *            Feld Betriebsnummer-Absender aus DSME
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmal)
	 */
	public PruefungVSNR(final Feld<FeldNameDSME> feld, final Feld<FeldNameDSME> feldGD,
			final Feld<FeldNameDSME> feldPersGr, final Feld<FeldNameDSME> feldBbnrab, final String vfmm) {
		super(feld);

		this.feldGD = feldGD;
		this.feldPersGr = feldPersGr;
		this.feldBbnrab = feldBbnrab;
		this.vfmm = vfmm;

		testVsnr = WertelistenVerwaltungDeuev.getInstance().getWerteliste(ListenTypDeuev.TESTVERSICHERUNGSNUMMERN);
		interimsVsnr = WertelistenVerwaltungDeuev.getInstance().getWerteliste(
				ListenTypDeuev.INTERIMSVERSICHERUNGSNUMMER);
		versicherungsnummer = WertelistenVerwaltungDeuev.getInstance()
				.getWerteliste(ListenTypDeuev.VERSICHERUNGSNUMMER);
	}

	@Override
	// SUPPRESS CHECKSTYLE MethodLengthCheck
	public void initialisierePruefungen() throws UngueltigeDatenException {
		feldVfmm = new Feld<FeldNameDSME>(vfmm);

		// Sollte es eine Grundstellung geben, wird ueberprueft, ob diese
		// zulaessig ist
		if (StringUtils.isBlank(getFeld().getTrimmedValue())) {
			grundstellung();
		} else {
			if (getFeld().getTrimmedValue().length() != LAENGE_VSNR) {
				final PruefungNichtLeer me082y = new PruefungNichtLeer(getFeld());
				addPruefung(me082y, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME082));

				final PruefungLaenge me082z = new PruefungLaenge(LAENGE_VSNR, getFeld());
				addPruefung(me082z, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME082));
			} else {
				feldGeburtsdatum = new Feld<FeldNameDSME>(getFeld().getTrimmedValue().substring(
						INDEX_GEBURTSDATUM_BEGINN, INDEX_GEBURTSDATUM_ENDE));

				final Feld<FeldNameDSME> numerisch18 = new Feld<FeldNameDSME>(getFeld().getTrimmedValue().substring(
						INDEX_NUMMER_START_A, INDEX_NUMMER_ENDE_A));
				final PruefungNumerisch me082a = new PruefungNumerisch(numerisch18);
				addPruefung(me082a, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME082));

				final Feld<FeldNameDSME> numerisch1012 = new Feld<FeldNameDSME>(getFeld().getTrimmedValue().substring(
						INDEX_NUMMER_START_B, INDEX_NUMMER_ENDE_B));
				final PruefungNumerisch me082b = new PruefungNumerisch(numerisch1012);
				addPruefung(me082b, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME082));

				final Feld<FeldNameDSME> grossbuchstabe = new Feld<FeldNameDSME>(getFeld().getTrimmedValue().substring(
						INDEX_BUCHSTABE_START, INDEX_BUCHSTABE_ENDE));
				final PruefungPattern me082c = new PruefungPattern(grossbuchstabe, "[A-Z]");
				addPruefung(me082c, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME082));

				bereichsnummer = new Feld<FeldNameDSME>(getFeld().getTrimmedValue().substring(
						INDEX_BEREICHSNUMMER_START, INDEX_BEREICHSNUMMER_ENDE));

				// Pruefung, ob es eine VSNR ist
				if (versicherungsnummer.contains(bereichsnummer.getTrimmedValue())) {
					pruefungenVsnr();

					// Pruefung, ob es eine IT-VSNR ist
				} else if (interimsVsnr.contains(bereichsnummer.getTrimmedValue())) {
					pruefungenItvsnr();

				} else {
					final PruefungInList me084 = new PruefungInList(versicherungsnummer, bereichsnummer);
					addPruefung(me084, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME084));
				}
			}
		}
	}

	/**
	 * Pruefung, ob Grundstellung erlaubt ist.
	 */
	private void grundstellung() {
		boolean grundstellungErlaubt = false;

		try {
			final int iGD = Integer.parseInt(feldGD.getTrimmedValue());

			if ((((iGD >= ANMELDUNG_GD_MIN) && (iGD <= ANMELDUNG_GD_MAX)) || (iGD == GKV_MONATSMELDUNG))
					&& GRUNDST_FALL1.contains(vfmm)) {
				grundstellungErlaubt = true;
			}

			else if ((iGD == SOFORTMELDUNG) && (vfmm.compareTo("AGTRV") == 0)) {
				grundstellungErlaubt = true;
			}

			else if ((iGD == AN_UND_ABMELDUNG)
					&& ((feldPersGr.getTrimmedValue().compareTo("110") == 0) || (feldPersGr.getTrimmedValue()
							.compareTo("210") == 0))) {
				if (GRUNDST_FALL3.contains(vfmm)) {
					grundstellungErlaubt = true;
				}
			}
		} catch (final NumberFormatException e) {
			grundstellungErlaubt = false;
			if (LOGGER.isLoggable(Level.FINE)) {
				LOGGER.log(Level.FINE, e.toString());
			}
		}

		if (!grundstellungErlaubt) {
			final PruefungNichtLeer me080 = new PruefungNichtLeer(getFeld());
			addPruefung(me080, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME080));
		}
	}

	/**
	 * Pruefungen, falls eine Versicherungsnummer vorliegt.
	 */
	private void pruefungenVsnr() {
		if (Integer.parseInt(bereichsnummer.getTrimmedValue()) == AN_UND_ABMELDUNG) {
			final PruefungNichtLeer me085a = new PruefungNichtLeer(feldVfmm);
			addPruefung(me085a, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME085));

			final PruefungInList me085 = new PruefungInList(VFMM_BN_40, feldVfmm, true);
			addPruefung(me085, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME085));
		}

		final PruefungVersicherungsnummerGeburtsdatum me086 = new PruefungVersicherungsnummerGeburtsdatum(
				feldGeburtsdatum);
		addPruefung(me086, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME086));

		final PruefungNotInList me089 = new PruefungNotInList(testVsnr, getFeld());
		addPruefung(me089, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME089));

		final int errechnetePruefziffer = Pruefziffer.berechnePruefzifferDerVersicherungsnummer(getFeld()
				.getTrimmedValue());
		final Feld<FeldNameDSME> pruefziffer = new Feld<FeldNameDSME>(getFeld().getTrimmedValue().substring(
				INDEX_PRUEFZIFFER_START, INDEX_PRUEFZIFFER_ENDE));
		final PruefungInList me088 = new PruefungInList(Arrays.asList("" + errechnetePruefziffer), pruefziffer);
		addPruefung(me088, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME088));

		if (VFMM_INTERIM.contains(vfmm)) {
			final PruefungInList me092 = new PruefungInList(interimsVsnr, getFeld(), true);
			addPruefung(me092, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME092));
		}
	}

	/**
	 * Pruefungen, falls eine Interimsversicherungsnummer vorliegt.
	 */
	private void pruefungenItvsnr() {
		if (VFMM_KEINE_INTERIM.contains(vfmm)) {
			final PruefungNotInList me090 = new PruefungNotInList(interimsVsnr, bereichsnummer);
			addPruefung(me090, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME090));
		} else {
			final PruefungVersicherungsnummerGeburtsdatum me096a = new PruefungVersicherungsnummerGeburtsdatum(feldGeburtsdatum);
			addPruefung(me096a, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME096));

			if (vfmm.compareTo("PVTRV") == 0) {
				final PruefungInList me112 = new PruefungInList(Arrays.asList("94"), bereichsnummer);
				addPruefung(me112, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME112));
			}
			
			final int errechnetePruefziffer = Pruefziffer.berechnePruefzifferDerVersicherungsnummer(getFeld().getTrimmedValue());
			final Feld<FeldNameDSME> pruefziffer = new Feld<FeldNameDSME>(getFeld().getTrimmedValue().substring(INDEX_PRUEFZIFFER_START, INDEX_PRUEFZIFFER_ENDE));
			final PruefungInList me088 = new PruefungInList(Arrays.asList("" + errechnetePruefziffer), pruefziffer);
			addPruefung(me088, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME088));
			
			if (BBNRAB_BN_00.contains(feldBbnrab.getTrimmedValue())) {
				final PruefungInList me100 = new PruefungInList(Arrays.asList("00"), bereichsnummer);
				addPruefung(me100, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME100));
			} else {
				if (vfmm.compareTo("KSTRV") == 0) {
					final PruefungInList me102 = new PruefungInList(Arrays.asList("77"), bereichsnummer);
					addPruefung(me102, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME102));
				}

				else if (vfmm.compareTo("KVTRV") == 0) {
					final PruefungInList me104 = new PruefungInList(BN_KVTRV, bereichsnummer);
					addPruefung(me104, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME104));
				}

				else if (VFMM_BN_88.contains(vfmm)) {
					final PruefungInList me106 = new PruefungInList(Arrays.asList("88"), bereichsnummer);
					addPruefung(me106, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME106));
				}

				else if (vfmm.compareTo("BWTRV") == 0) {
					final PruefungInList me108 = new PruefungInList(Arrays.asList("91"), bereichsnummer);
					addPruefung(me108, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME108));
				}

				else if (vfmm.compareTo("BZTRV") == 0) {
					final PruefungInList me110 = new PruefungInList(Arrays.asList("92"), bereichsnummer);
					addPruefung(me110, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME110));
				} 
				
				else if (VFMM_BN_42.contains(vfmm)) {
					final PruefungInList me099 = new PruefungInList(Arrays.asList("41"), bereichsnummer);
					addPruefung(me099, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME099));
				} 
				
				else {
					if (bereichsnummer.getTrimmedValue().compareTo("41") == 0) {
						final PruefungNichtLeer me101a = new PruefungNichtLeer(feldVfmm);
						addPruefung(me101a, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME101));

						final PruefungInList me101b = new PruefungInList(VFMM_BN_42, feldVfmm, true);
						addPruefung(me101b, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME101));
					}
				}
			}
		}

		
		

		

		

		

		
	}
}
