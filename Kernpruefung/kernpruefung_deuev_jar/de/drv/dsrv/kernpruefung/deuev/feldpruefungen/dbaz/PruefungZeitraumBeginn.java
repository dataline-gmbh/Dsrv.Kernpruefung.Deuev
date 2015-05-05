package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbaz;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
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
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBAZ;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBAZ;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;

/**
 * Pruefung fuer das Feld Zeitraum-Beginn aus dem Baustein DBAZ.
 */
public class PruefungZeitraumBeginn extends AbstractFeldPruefung<FeldNameDBAZ, FehlerNummerDBAZ> {

	// @formatter:off
	private static final Date SEPTEMBER_2000 = new GregorianCalendar(2000, 9, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date MAI_2003 = new GregorianCalendar(2003, 4, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date ANFANG_2009 = new GregorianCalendar(2009, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date ANFANG_2011 = new GregorianCalendar(2011, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	// @formatter:on

	private static final List<String> VSTR_KEINE_STORNIERUNG = Arrays.asList("0A", "0C");

	private final Feld<FeldNameDBAZ> feldKennzSt;
	private final Feld<FeldNameDBAZ> feldLeistungsart;

	private final Baustein<FeldNameDSAE> bausteinDsae;
	private final Date verarbeitungsDatum;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld
	 * @param feldKennzSt
	 *            Feld Kennzeichen-Storno aus dem Baustein DBAZ
	 * @param feldLeistungsart
	 *            Feld Leistungsart aus dem Baustein DBAZ
	 * @param bausteinDsae
	 *            Baustein DSAE
	 * @param verarbeitungsDatum
	 *            Verarbeitungsdatum
	 */
	public PruefungZeitraumBeginn(final Feld<FeldNameDBAZ> feld, final Feld<FeldNameDBAZ> feldKennzSt,
			final Feld<FeldNameDBAZ> feldLeistungsart, final Baustein<FeldNameDSAE> bausteinDsae,
			final Date verarbeitungsDatum) {
		super(feld);

		this.feldKennzSt = feldKennzSt;
		this.feldLeistungsart = feldLeistungsart;

		this.bausteinDsae = bausteinDsae;
		this.verarbeitungsDatum = verarbeitungsDatum;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch az030 = new PruefungNumerisch(getFeld(), true);
		addPruefung(az030, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ030));

		final PruefungDatumLogischRichtig az032 = new PruefungDatumLogischRichtig(getFeld());
		addPruefung(az032, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ032));
	}

	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {
		if (isPruefbar(FehlerNummerDBAZ.DBAZ033, feldLeistungsart) && "42".equals(feldLeistungsart.getTrimmedValue())) {
			final PruefungDatumNachDatum az033 = new PruefungDatumNachDatum(getFeld(), MAI_2003);
			addPruefungFeldUebergreifend(az033, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ033));
		}

		else if (isPruefbar(FehlerNummerDBAZ.DBAZ031, feldLeistungsart)
				&& "45".equals(feldLeistungsart.getTrimmedValue())) {
			final PruefungDatumNachDatum az031 = new PruefungDatumNachDatum(getFeld(), ANFANG_2009);
			addPruefungFeldUebergreifend(az031, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ031));
		}

		else if (isPruefbar(FehlerNummerDBAZ.DBAZ035, feldLeistungsart)
				&& "43".equals(feldLeistungsart.getTrimmedValue())) {
			final PruefungDatumNachDatum az035 = new PruefungDatumNachDatum(getFeld(), SEPTEMBER_2000);
			addPruefungFeldUebergreifend(az035, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ035));
		}

		else if (isPruefbar(FehlerNummerDBAZ.DBAZ041, feldLeistungsart)
				&& "46".equals(feldLeistungsart.getTrimmedValue())) {
			final PruefungDatumNachDatum az041 = new PruefungDatumNachDatum(getFeld(), ANFANG_2011);
			addPruefungFeldUebergreifend(az041, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ041));
		}
	}

	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {
		final List<FehlerNummerDBAZ> fehlernummern = new LinkedList<FehlerNummerDBAZ>();
		fehlernummern.add(FehlerNummerDBAZ.DBAZ034);
		fehlernummern.add(FehlerNummerDBAZ.DBAZ036);
		fehlernummern.add(FehlerNummerDBAZ.DBAZ037);
		fehlernummern.add(FehlerNummerDBAZ.DBAZ038);
		fehlernummern.add(FehlerNummerDBAZ.DBAZ039);

		if (isPruefbar(fehlernummern, bausteinDsae.getFeld(FeldNameDSAE.VSNR))) {
			final Date gebDatum = DateUtils.getDatVsnr(bausteinDsae.getFeld(FeldNameDSAE.VSNR), verarbeitungsDatum);

			if (isPruefbar(FehlerNummerDBAZ.DBAZ034, feldKennzSt)
					&& isPruefbar(FehlerNummerDBAZ.DBAZ034, feldLeistungsart)
					&& isPruefbar(FehlerNummerDBAZ.DBAZ034, bausteinDsae.getFeld(FeldNameDSAE.VSNR))
					&& "N".equals(feldKennzSt.getTrimmedValue())
					&& feldLeistungsart.getTrimmedValue().compareTo("44") == 0) {
				final Date siebzehnJahre = DateUtils.berechneNeuesDatumJahr(gebDatum, 17, false);
				final PruefungDatumNachDatum az034 = new PruefungDatumNachDatum(getFeld(), siebzehnJahre);
				addPruefungBausteinUebergreifend(az034, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ034));
			}

			if (isPruefbar(FehlerNummerDBAZ.DBAZ036, feldLeistungsart)
					&& isPruefbar(FehlerNummerDBAZ.DBAZ036, bausteinDsae.getFeld(FeldNameDSAE.VSNR))
					&& "42".equals(feldLeistungsart.getTrimmedValue())) {
				final Date achtUndFuenfzigJahre = DateUtils.berechneNeuesDatumJahr(gebDatum, 58, false);
				final PruefungDatumNachDatum az036 = new PruefungDatumNachDatum(getFeld(), achtUndFuenfzigJahre);
				addPruefungBausteinUebergreifend(az036, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ036));

			} else if (isPruefbar(FehlerNummerDBAZ.DBAZ037, feldLeistungsart)
					&& isPruefbar(FehlerNummerDBAZ.DBAZ037, bausteinDsae.getFeld(FeldNameDSAE.VSNR))
					&& "43".equals(feldLeistungsart.getTrimmedValue())) {
				final Date vierzehnJahre = DateUtils.berechneNeuesDatumJahr(gebDatum, 14, false);
				final PruefungDatumNachDatum az037 = new PruefungDatumNachDatum(getFeld(), vierzehnJahre);
				addPruefungBausteinUebergreifend(az037, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ037));
			}

			if (isPruefbar(FehlerNummerDBAZ.DBAZ038, feldKennzSt)
					&& isPruefbar(FehlerNummerDBAZ.DBAZ038, bausteinDsae.getFeld(FeldNameDSAE.VSTR))
					&& "N".equals(feldKennzSt.getTrimmedValue())
					&& getFeld().getTrimmedValue().compareTo("20041231") > 0) {
				final Feld<FeldNameDSAE> feldDsaeVstr = bausteinDsae.getFeld(FeldNameDSAE.VSTR);
				final PruefungInList az038 = new PruefungInList(VSTR_KEINE_STORNIERUNG, feldDsaeVstr);
				addPruefungBausteinUebergreifend(az038, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ038));
			}

			if (isPruefbar(FehlerNummerDBAZ.DBAZ039, feldKennzSt)
					&& isPruefbar(FehlerNummerDBAZ.DBAZ039, bausteinDsae.getFeld(FeldNameDSAE.VSNR))
					&& isPruefbar(FehlerNummerDBAZ.DBAZ039, feldLeistungsart)
					&& "N".equals(feldKennzSt.getTrimmedValue()) && "54".equals(feldLeistungsart.getTrimmedValue())) {
				final Date siebzehnJahre = DateUtils.berechneNeuesDatumJahr(gebDatum, 17, false);
				final PruefungDatumNachDatum az039 = new PruefungDatumNachDatum(getFeld(), siebzehnJahre);
				addPruefungBausteinUebergreifend(az039, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ039));
			}
		}
	}
}
