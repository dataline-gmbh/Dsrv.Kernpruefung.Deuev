package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae;

import java.util.Arrays;
import java.util.List;

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
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSAE;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;
import de.drv.dsrv.kernpruefung.deuev.pruefungen.PruefungVersicherungsnummerGeburtsdatum;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.ListenTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Validiert das Feld VSNR im Baustein DSAE.
 */
public class PruefungVSNR extends AbstractFeldPruefung<FeldNameDSAE, FehlerNummerDSAE> {

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
	private final List<String> testVsnr;
	private static final List<String> BEREICHSNUMMER = Arrays.asList("02", "03", "04", "08", "09", "10", "11", "12",
			"13", "14", "15", "16", "17", "18", "19", "20", "21", "23", "24", "25", "26", "28", "29", "38", "39", "42",
			"43", "44", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "63", "64",
			"65", "66", "68", "69", "78", "79", "80", "81", "82", "89");

	private Feld<FeldNameDSAE> feldGeburtsdatum;
	private Feld<FeldNameDSAE> bereichsnummer;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld
	 */
	public PruefungVSNR(final Feld<FeldNameDSAE> feld) {
		super(feld);

		testVsnr = WertelistenVerwaltungDeuev.getInstance().getWerteliste(ListenTypDeuev.TESTVERSICHERUNGSNUMMERN);
	}

	@Override
	// SUPPRESS CHECKSTYLE MethodLengthCheck
	public void initialisierePruefungen() throws UngueltigeDatenException {

		// Sollte es eine Grundstellung geben, wird ueberprueft, ob diese
		// zulaessig ist
		if (getFeld().getTrimmedValue().length() != LAENGE_VSNR) {
			final PruefungNichtLeer ae082Leer = new PruefungNichtLeer(getFeld());
			addPruefung(ae082Leer, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE082));

			final PruefungLaenge ae082Laenge = new PruefungLaenge(LAENGE_VSNR, getFeld());
			addPruefung(ae082Laenge, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE082));
		} else {
			feldGeburtsdatum = new Feld<FeldNameDSAE>(getFeld().getTrimmedValue().substring(INDEX_GEBURTSDATUM_BEGINN,
					INDEX_GEBURTSDATUM_ENDE));

			final Feld<FeldNameDSAE> numerisch18 = new Feld<FeldNameDSAE>(getFeld().getTrimmedValue().substring(
					INDEX_NUMMER_START_A, INDEX_NUMMER_ENDE_A));
			final PruefungNumerisch ae082a = new PruefungNumerisch(numerisch18);
			addPruefung(ae082a, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE082));

			final Feld<FeldNameDSAE> numerisch1012 = new Feld<FeldNameDSAE>(getFeld().getTrimmedValue().substring(
					INDEX_NUMMER_START_B, INDEX_NUMMER_ENDE_B));
			final PruefungNumerisch ae082b = new PruefungNumerisch(numerisch1012);
			addPruefung(ae082b, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE082));

			final Feld<FeldNameDSAE> grossbuchstabe = new Feld<FeldNameDSAE>(getFeld().getTrimmedValue().substring(
					INDEX_BUCHSTABE_START, INDEX_BUCHSTABE_ENDE));
			final PruefungPattern ae082Grossbuchstabe = new PruefungPattern(grossbuchstabe, "[A-Z]");
			addPruefung(ae082Grossbuchstabe, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE082));

			bereichsnummer = new Feld<FeldNameDSAE>(getFeld().getTrimmedValue().substring(INDEX_BEREICHSNUMMER_START,
					INDEX_BEREICHSNUMMER_ENDE));

			// Pruefung, ob es eine VSNR ist
			if (BEREICHSNUMMER.contains(bereichsnummer.getTrimmedValue())) {
				pruefungenVsnr();
			} else {
				final PruefungInList ae084 = new PruefungInList(BEREICHSNUMMER, bereichsnummer);
				addPruefung(ae084, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE084));
			}
		}
	}

	/**
	 * Pruefungen, falls eine Versicherungsnummer vorliegt.
	 */
	private void pruefungenVsnr() {
		final PruefungNotInList ae089 = new PruefungNotInList(testVsnr, getFeld());
		addPruefung(ae089, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE089));

		final PruefungVersicherungsnummerGeburtsdatum ae086 = new PruefungVersicherungsnummerGeburtsdatum(
				feldGeburtsdatum);
		addPruefung(ae086, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE086));

		final int errechnetePruefziffer = Pruefziffer.berechnePruefzifferDerVersicherungsnummer(getFeld()
				.getTrimmedValue());
		final Feld<FeldNameDSAE> pruefziffer = new Feld<FeldNameDSAE>(getFeld().getTrimmedValue().substring(
				INDEX_PRUEFZIFFER_START, INDEX_PRUEFZIFFER_ENDE));
		final PruefungInList ae088 = new PruefungInList(Arrays.asList("" + errechnetePruefziffer), pruefziffer);
		addPruefung(ae088, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE088));
	}
}
