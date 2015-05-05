package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbvr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBuchstabeAZazZiffernZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungFuegeFehlerHinzu;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungGrossbuchstabe;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInterval;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.Pruefziffer;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBVR;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBVR;
import de.drv.dsrv.kernpruefung.deuev.pruefungen.PruefungVersicherungsnummerGeburtsdatum;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.ListenTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Pruefung fuer das Feld Versicherungsnummer Vergabe aus dem Baustein DBVR.
 * 
 * ACHTUNG: IN DIESER FELDPRUEFUNG GIBT ES KEINE FELDINTERNEN PRUEFUNGEN. ES
 * KANN ALSO NICHT DAVON AUSGEGANGEN WERDEN, DASS DER AUFBAU KORREKT IST, WENN
 * EIN ANDERES FELD FELDUEBERGREIFEND PRUEFT!
 */
public class PruefungVersicherungsnummerVergabe extends AbstractFeldPruefung<FeldNameDBVR, FehlerNummerDBVR> {

	private static final List<String> GD_GRUNDSTELLUNG = Arrays.asList("01", "04", "80", "99");
	private static final List<String> GD_VSNR = Arrays.asList("02", "03", "10", "11");

	private static final int LAENGE_VSNR = 12;
	private static final int INDEX_NUMERISCH1_BEGINN = 0;
	private static final int INDEX_NUMERISCH1_ENDE = 8;

	private static final int INDEX_BUCHSTABE_BEGINN = INDEX_NUMERISCH1_ENDE;
	private static final int INDEX_BUCHSTABE_ENDE = 9;

	private static final int INDEX_NUMERISCH2_BEGINN = INDEX_BUCHSTABE_ENDE;
	private static final int INDEX_NUMERISCH2_ENDE = LAENGE_VSNR;

	private static final int INDEX_GEBURTSDATUM_BEGINN = 2;
	private static final int INDEX_GEBURTSDATUM_ENDE = 8;
	private final Feld<FeldNameDBVR> feldAbgabegrund;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldAbgabegrund
	 *            Feld Abgabegrund aus DBVR
	 */
	public PruefungVersicherungsnummerVergabe(final Feld<FeldNameDBVR> feld, final Feld<FeldNameDBVR> feldAbgabegrund) {
		super(feld);

		this.feldAbgabegrund = feldAbgabegrund;
	}

	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {

		final List<FehlerNummerDBVR> fehlernummern = new LinkedList<FehlerNummerDBVR>();
		fehlernummern.add(FehlerNummerDBVR.DBVR080);
		fehlernummern.add(FehlerNummerDBVR.DBVR082);
		fehlernummern.add(FehlerNummerDBVR.DBVR083);

		if (isPruefbar(fehlernummern, feldAbgabegrund)) {
			if (GD_GRUNDSTELLUNG.contains(feldAbgabegrund.getTrimmedValue())) {
				final PruefungLaenge vr080 = new PruefungLaenge(0, getFeld());
				addPruefungFeldUebergreifend(vr080, new Fehler<FehlerNummerDBVR>(FehlerNummerDBVR.DBVR080));
			}

			else if (GD_VSNR.contains(feldAbgabegrund.getTrimmedValue())) {
				pruefeKorrekterAufbauUndInhalt(new Fehler<FehlerNummerDBVR>(FehlerNummerDBVR.DBVR082));
			}

			else if ("05".equals(feldAbgabegrund.getTrimmedValue()) && StringUtils.isNotBlank(getFeld().getTrimmedValue())) {
				pruefeKorrekterAufbauUndInhalt(new Fehler<FehlerNummerDBVR>(FehlerNummerDBVR.DBVR083));
			}
		}
	}

	/**
	 * Pruefungen fuer die Fehlernummern DBVR082, DBVR083, DBVR084, DBVR086,
	 * DBVR088.
	 * 
	 * @param fehlerFalscherAufbau
	 */
	private void pruefeKorrekterAufbauUndInhalt(final Fehler<FehlerNummerDBVR> fehlerFalscherAufbau) {

		// Aufbau testen: Stellen 1-8 und 10-12 Ziffern, Stelle 9 Grossbuchstabe
		// ohne Umlaut
		if (LAENGE_VSNR != getFeld().getTrimmedValue().length()) {
			final PruefungFuegeFehlerHinzu pruefung = new PruefungFuegeFehlerHinzu(getFeld());
			addPruefungFeldUebergreifend(pruefung, fehlerFalscherAufbau);
		}

		else {
			final Feld<FeldNameDBVR> feldVsnrNumerisch1 = new Feld<FeldNameDBVR>(getFeld().getTrimmedValue().substring(INDEX_NUMERISCH1_BEGINN, INDEX_NUMERISCH1_ENDE));
			final PruefungNumerisch vr082b = new PruefungNumerisch(feldVsnrNumerisch1, true);
			addPruefungFeldUebergreifend(vr082b, fehlerFalscherAufbau);

			final Feld<FeldNameDBVR> feldVsnrBuchstabe = new Feld<FeldNameDBVR>(getFeld().getTrimmedValue().substring(INDEX_BUCHSTABE_BEGINN, INDEX_BUCHSTABE_ENDE));
			final PruefungGrossbuchstabe vr082c = new PruefungGrossbuchstabe(feldVsnrBuchstabe);
			addPruefungFeldUebergreifend(vr082c, fehlerFalscherAufbau);
			final PruefungBuchstabeAZazZiffernZeichen vr082d = new PruefungBuchstabeAZazZiffernZeichen(feldVsnrBuchstabe, new ArrayList<Character>());
			addPruefungFeldUebergreifend(vr082d, fehlerFalscherAufbau);

			final Feld<FeldNameDBVR> feldVsnrNumerisch2 = new Feld<FeldNameDBVR>(getFeld().getTrimmedValue().substring(INDEX_NUMERISCH2_BEGINN, INDEX_NUMERISCH2_ENDE));
			final PruefungNumerisch vr082e = new PruefungNumerisch(feldVsnrNumerisch2, true);
			addPruefungFeldUebergreifend(vr082e, fehlerFalscherAufbau);

			// Bereichsnummer
			final List<String> bereichsnummern = WertelistenVerwaltungDeuev.getInstance().getWerteliste(ListenTypDeuev.VERSICHERUNGSNUMMER);
			final Feld<FeldNameDBVR> feldBereichsnummer = new Feld<FeldNameDBVR>(getFeld().getTrimmedValue().substring(0, 2));
			final PruefungInList vr084 = new PruefungInList(bereichsnummern, feldBereichsnummer);
			addPruefungFeldUebergreifend(vr084, new Fehler<FehlerNummerDBVR>(FehlerNummerDBVR.DBVR084));

			// Geburtsdatum Versicherungsnummer
			final Feld<FeldNameDBVR> feldGeburtsdatum = new Feld<FeldNameDBVR>(getFeld().getTrimmedValue().substring(INDEX_GEBURTSDATUM_BEGINN, INDEX_GEBURTSDATUM_ENDE));
			final PruefungVersicherungsnummerGeburtsdatum vr086 = new PruefungVersicherungsnummerGeburtsdatum(feldGeburtsdatum);
			addPruefungFeldUebergreifend(vr086, new Fehler<FehlerNummerDBVR>(FehlerNummerDBVR.DBVR086));

			// Pruefziffer
			final int pruefziffer = Pruefziffer.berechnePruefzifferDerVersicherungsnummer(getFeld().getTrimmedValue());
			final Feld<FeldNameDBVR> feldPruefzifferErrechnet = new Feld<FeldNameDBVR>("" + pruefziffer);
			final PruefungInterval vr088a = new PruefungInterval(0, 9, feldPruefzifferErrechnet);
			addPruefungFeldUebergreifend(vr088a, new Fehler<FehlerNummerDBVR>(FehlerNummerDBVR.DBVR088));

			final List<String> sollPruefziffer = new ArrayList<String>();
			sollPruefziffer.add(getFeld().getTrimmedValue().substring(INDEX_NUMERISCH2_ENDE - 1, INDEX_NUMERISCH2_ENDE));
			final PruefungInList vr088b = new PruefungInList(sollPruefziffer, feldPruefzifferErrechnet);
			addPruefungFeldUebergreifend(vr088b, new Fehler<FehlerNummerDBVR>(FehlerNummerDBVR.DBVR088));
		}
	}
}
