package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbez;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumLogischRichtig;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumNachDatum;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils.DateUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBEZ;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBEZ;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;

/**
 * Pruefung fuer das Feld Zeitraum-Beginn aus dem Baustein DBEZ.
 */
public class PruefungZeitraumBeginn extends AbstractFeldPruefung<FeldNameDBEZ, FehlerNummerDBEZ> {

	// @formatter:off
	private static final Date MAI_1996 = new GregorianCalendar(1996, 4, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date ANFANG_1998 = new GregorianCalendar(1998, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date ANFANG_2003 = new GregorianCalendar(2003, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date ANFANG_2005 = new GregorianCalendar(2005, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date ANFANG_2007 = new GregorianCalendar(2007, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date ANFANG_2009 = new GregorianCalendar(2009, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date ANFANG_2015 = new GregorianCalendar(2015, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date MAI_2010 = new GregorianCalendar(2010, 4, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	// @formatter:on

	private static final List<String> VSTR_KEINE_STORNIERUNG = Arrays.asList("0A", "0C");

	private static final List<String> LEAT_NACH_1996 = Arrays.asList("27", "28");
	private static final List<String> LEAT_AB_15_JAHRE = Arrays.asList("43", "44");
	private static final List<String> LEAT_NACH_1998 = Arrays.asList("30", "31", "32", "33", "42");
	private static final List<String> LEAT_NACH_2005 = Arrays.asList("43", "44");

	private final Feld<FeldNameDBEZ> feldAbgabegrund;
	private final Feld<FeldNameDBEZ> feldKennzSt;
	private final Feld<FeldNameDBEZ> feldLeistungsart;

	private final Baustein<FeldNameDSAE> bausteinDsae;
	private final Date verarbeitungsDatum;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldAbgabegrund
	 *            Feld Abgabegrund aus dem Baustein DBEZ.
	 * @param feldKennzSt
	 *            Feld Kennzeichen-Storno aus dem Baustein DBEZ.
	 * @param feldLeistungsart
	 *            Feld Leistungsart aus dem Baustein DBEZ.
	 * @param bausteinDsae
	 *            Baustein DSAE.
	 * @param verarbeitungsDatum
	 *            Verarbeitungsdatum aus Eingabendaten
	 */
	public PruefungZeitraumBeginn(final Feld<FeldNameDBEZ> feld, final Feld<FeldNameDBEZ> feldAbgabegrund,
			final Feld<FeldNameDBEZ> feldKennzSt, final Feld<FeldNameDBEZ> feldLeistungsart,
			final Baustein<FeldNameDSAE> bausteinDsae, final Date verarbeitungsDatum) {
		super(feld);

		this.feldAbgabegrund = feldAbgabegrund;
		this.feldKennzSt = feldKennzSt;
		this.feldLeistungsart = feldLeistungsart;

		this.bausteinDsae = bausteinDsae;
		this.verarbeitungsDatum = verarbeitungsDatum;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch ez040 = new PruefungNumerisch(getFeld(), true);
		addPruefung(ez040, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ040));

		final PruefungDatumLogischRichtig ez042 = new PruefungDatumLogischRichtig(getFeld());
		addPruefung(ez042, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ042));
	}

	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {
		if (isPruefbar(FehlerNummerDBEZ.DBEZ041, feldAbgabegrund) && "04".equals(feldAbgabegrund.getTrimmedValue())) {
			final PruefungDatumNachDatum ez041 = new PruefungDatumNachDatum(getFeld(), ANFANG_2007);
			addPruefungFeldUebergreifend(ez041, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ041));
		}

		if (isPruefbar(Arrays.asList(FehlerNummerDBEZ.DBEZ044, FehlerNummerDBEZ.DBEZ046, FehlerNummerDBEZ.DBEZ047,
				FehlerNummerDBEZ.DBEZ048, FehlerNummerDBEZ.DBEZ038, FehlerNummerDBEZ.DBEZ039), feldLeistungsart)) {

			if (LEAT_NACH_1996.contains(feldLeistungsart.getTrimmedValue())) {
				final PruefungDatumNachDatum ez044 = new PruefungDatumNachDatum(getFeld(), MAI_1996);
				addPruefungFeldUebergreifend(ez044, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ044));
			}

			else if (LEAT_NACH_1998.contains(feldLeistungsart.getTrimmedValue())) {
				final PruefungDatumNachDatum ez046 = new PruefungDatumNachDatum(getFeld(), ANFANG_1998);
				addPruefungFeldUebergreifend(ez046, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ046));
			}

			else if (LEAT_NACH_2005.contains(feldLeistungsart.getTrimmedValue())) {
				final PruefungDatumNachDatum ez047 = new PruefungDatumNachDatum(getFeld(), ANFANG_2005);
				addPruefungFeldUebergreifend(ez047, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ047));
			}

			else if ("50".equals(feldLeistungsart.getTrimmedValue())) {
				final PruefungDatumNachDatum ez048 = new PruefungDatumNachDatum(getFeld(), ANFANG_2003);
				addPruefungFeldUebergreifend(ez048, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ048));
			}
			
			else if ("12".equals(feldLeistungsart.getTrimmedValue()) || "13".equals(feldLeistungsart.getTrimmedValue())) {
				final PruefungDatumNachDatum ez037 = new PruefungDatumNachDatum(getFeld(), ANFANG_2015);
				addPruefungFeldUebergreifend(ez037, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ037));
			}

			else if ("46".equals(feldLeistungsart.getTrimmedValue())) {
				final PruefungDatumNachDatum ez038 = new PruefungDatumNachDatum(getFeld(), ANFANG_2009);
				addPruefungFeldUebergreifend(ez038, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ038));
			}

			else if ("45".equals(feldLeistungsart.getTrimmedValue())) {
				final PruefungDatumNachDatum ez039 = new PruefungDatumNachDatum(getFeld(), MAI_2010);
				addPruefungFeldUebergreifend(ez039, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ039));
			}
		}
	}

	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {
		if ("N".equals(feldKennzSt.getTrimmedValue()) && getFeld().getTrimmedValue().compareTo("20041231") > 0) {
			final Feld<FeldNameDSAE> feldDsaeVstr = bausteinDsae.getFeld(FeldNameDSAE.VSTR);

			if (isPruefbar(FehlerNummerDBEZ.DBEZ043, feldKennzSt, feldDsaeVstr)) {
				final PruefungInList ez043 = new PruefungInList(VSTR_KEINE_STORNIERUNG, feldDsaeVstr);
				addPruefungBausteinUebergreifend(ez043, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ043));
			}
		}

		if (isPruefbar(Arrays.asList(FehlerNummerDBEZ.DBEZ045, FehlerNummerDBEZ.DBEZ049),
				bausteinDsae.getFeld(FeldNameDSAE.VSNR), feldLeistungsart)) {
			final Date gebDatum = DateUtils.getDatVsnr(bausteinDsae.getFeld(FeldNameDSAE.VSNR), verarbeitungsDatum);
			if (LEAT_AB_15_JAHRE.contains(feldLeistungsart.getTrimmedValue())) {
				final Date fuenfzehnJahre = DateUtils.berechneNeuesDatumJahr(gebDatum, 15, false);
				final PruefungDatumNachDatum ez045 = new PruefungDatumNachDatum(getFeld(), fuenfzehnJahre);
				addPruefungBausteinUebergreifend(ez045, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ045));
			}

			else if ("50".equals(feldLeistungsart.getTrimmedValue())) {
				final Date fuenfzigJahre = DateUtils.berechneNeuesDatumJahr(gebDatum, 50, false);
				final PruefungDatumNachDatum ez049 = new PruefungDatumNachDatum(getFeld(), fuenfzigJahre);
				addPruefungBausteinUebergreifend(ez049, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ049));
			}
		}
	}
}
