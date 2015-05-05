package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbez;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumGleichesJahr;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumLogischRichtig;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumNachDatum;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumVorDatum;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils.DateUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBEZ;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBEZ;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;

/**
 * Pruefung fuer das Feld Zeitraum-Ende aus dem Baustein DBEZ.
 */
public class PruefungZeitraumEnde extends AbstractFeldPruefung<FeldNameDBEZ, FehlerNummerDBEZ> {

	private static final List<String> LEAT_NACH_2004 = Arrays.asList("23", "41");
	private static final List<String> LEAT_ARBEITSLOSENGELD = Arrays.asList("43", "44");
	private static final int ALTER_67 = 67;

	// @formatter:off
	private static final Date MRZ_2003 = new GregorianCalendar(2003, 2, 31).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date ENDE_2004 = new GregorianCalendar(2004, 11, 31).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date ENDE_2013 = new GregorianCalendar(2013, 11, 31).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	// @formatter:on

	private final Feld<FeldNameDBEZ> feldZeitraumBeginn;
	private final Feld<FeldNameDBEZ> feldLeistungsart;
	private final Baustein<FeldNameDSAE> bausteinDsae;
	private final Date verarbeitungsDatum;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldZeitraumBeginn
	 *            Feld Zeitraumbeginn aus dem Baustein DBEZ.
	 * @param feldLeistungsart
	 *            Feld Leistungsart aus dem Baustein DBEZ.
	 * @param bausteinDsae
	 *            Baustein DSAE.
	 * @param verarbeitungsDatum
	 *            Verarbeitungsdatum aus Eingabendaten
	 */
	public PruefungZeitraumEnde(final Feld<FeldNameDBEZ> feld, final Feld<FeldNameDBEZ> feldZeitraumBeginn,
			final Feld<FeldNameDBEZ> feldLeistungsart, final Baustein<FeldNameDSAE> bausteinDsae,
			final Date verarbeitungsDatum) {
		super(feld);

		this.feldZeitraumBeginn = feldZeitraumBeginn;
		this.feldLeistungsart = feldLeistungsart;
		this.bausteinDsae = bausteinDsae;
		this.verarbeitungsDatum = verarbeitungsDatum;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch ez050 = new PruefungNumerisch(getFeld(), true);
		addPruefung(ez050, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ050));

		final PruefungDatumLogischRichtig ez052 = new PruefungDatumLogischRichtig(getFeld());
		addPruefung(ez052, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ052));

		final Date verarbeitungsDatumPlus1 = DateUtils.berechneNeuesDatumMonat(verarbeitungsDatum, 1, true);
		final PruefungDatumVorDatum ez058 = new PruefungDatumVorDatum(this.getFeld(), verarbeitungsDatumPlus1);
		addPruefung(ez058, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ058));
	}

	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {
		if (isPruefbar(Arrays.asList(FehlerNummerDBEZ.DBEZ054, FehlerNummerDBEZ.DBEZ056), feldZeitraumBeginn)) {
			final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.GERMANY);
			dateFormat.setLenient(false);
			try {
				final Date zeitraumBeginnDatum = dateFormat.parse(feldZeitraumBeginn.getTrimmedValue());
				final PruefungDatumNachDatum ez054 = new PruefungDatumNachDatum(getFeld(), zeitraumBeginnDatum);
				addPruefungFeldUebergreifend(ez054, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ054));
			} catch (final ParseException e) {
				LOGGER.log(Level.WARNING, e.toString());
				throw new UngueltigeDatenException("Zeitraumbeginn ist kein Datum.", e);
			}

			final PruefungDatumGleichesJahr ez056 = new PruefungDatumGleichesJahr(getFeld(), feldZeitraumBeginn);
			addPruefungFeldUebergreifend(ez056, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ056));
		}

		if (isPruefbar(Arrays.asList(FehlerNummerDBEZ.DBEZ060, FehlerNummerDBEZ.DBEZ061, FehlerNummerDBEZ.DBEZ062),
				feldLeistungsart)) {
			if ("42".equals(feldLeistungsart.getTrimmedValue())) {
				final PruefungDatumVorDatum ez060 = new PruefungDatumVorDatum(getFeld(), MRZ_2003);
				addPruefungFeldUebergreifend(ez060, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ060));

			} else if (LEAT_NACH_2004.contains(feldLeistungsart.getTrimmedValue())) {
				final PruefungDatumVorDatum ez061 = new PruefungDatumVorDatum(getFeld(), ENDE_2004);
				addPruefungFeldUebergreifend(ez061, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ061));

			} else if ("50".equals(feldLeistungsart.getTrimmedValue())) {
				final PruefungDatumVorDatum ez062 = new PruefungDatumVorDatum(getFeld(), ENDE_2013);
				addPruefungFeldUebergreifend(ez062, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ062));
			}
		}
	}

	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {
		if (isPruefbar(FehlerNummerDBEZ.DBEZ064, feldLeistungsart, bausteinDsae.getFeld(FeldNameDSAE.VSNR))
				&& LEAT_ARBEITSLOSENGELD.contains(feldLeistungsart.getTrimmedValue())) {
			final Date gebDatum = DateUtils.getDatVsnr(bausteinDsae.getFeld(FeldNameDSAE.VSNR), verarbeitungsDatum);
			Date endeMonat67 = DateUtils.berechneNeuesDatumJahr(gebDatum, ALTER_67, false);
			
			final Calendar cal = new GregorianCalendar();
			cal.setLenient(false);
			cal.setTime(gebDatum);
			int datumKorrektur = (cal.get(Calendar.DAY_OF_MONTH) != 1) ? 0 : -1;
			if(datumKorrektur == 0)
				endeMonat67 = DateUtils.berechneNeuesDatumTag(endeMonat67, -1);
			else
				endeMonat67 = DateUtils.berechneNeuesDatumMonat(endeMonat67, datumKorrektur, true);
			
			final PruefungDatumVorDatum ez064 = new PruefungDatumVorDatum(getFeld(), endeMonat67);
			addPruefungBausteinUebergreifend(ez064, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ064));
		}
	}
}
