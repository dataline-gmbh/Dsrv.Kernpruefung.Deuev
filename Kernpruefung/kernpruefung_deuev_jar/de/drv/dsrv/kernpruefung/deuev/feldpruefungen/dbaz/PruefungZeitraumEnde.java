package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbaz;

import java.util.Date;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumGleichesJahr;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumLogischRichtig;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumNachDatum;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumVorDatum;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils.DateUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBAZ;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBAZ;

/**
 * Pruefung fuer das Feld Zeitraum-Ende aus dem Baustein DBAZ.
 */
public class PruefungZeitraumEnde extends AbstractFeldPruefung<FeldNameDBAZ, FehlerNummerDBAZ> {

	private static final String LEAT_MUTTERSCHAFT = "52";

	private final Feld<FeldNameDBAZ> feldKennzSt;
	private final Feld<FeldNameDBAZ> feldLeistungsart;
	private final Feld<FeldNameDBAZ> feldZrbg;

	private final Date verarbeitungsDatum;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld
	 * @param feldZrbg
	 *            Feld Zeitraum-Beginn aus dem Baustein DBAZ
	 * @param feldKennzSt
	 *            Feld Kennzeichen-Storno aus dem Baustein DBAZ
	 * @param feldLeistungsart
	 *            Feld Leistungsart aus dem Baustein DBAZ
	 * @param verarbeitungsDatum
	 *            Verarbeitungsdatum
	 */
	public PruefungZeitraumEnde(final Feld<FeldNameDBAZ> feld, final Feld<FeldNameDBAZ> feldZrbg,
			final Feld<FeldNameDBAZ> feldKennzSt, final Feld<FeldNameDBAZ> feldLeistungsart,
			final Date verarbeitungsDatum) {
		super(feld);

		this.feldZrbg = feldZrbg;
		this.feldKennzSt = feldKennzSt;
		this.feldLeistungsart = feldLeistungsart;
		this.verarbeitungsDatum = verarbeitungsDatum;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch az040 = new PruefungNumerisch(getFeld(), true);
		addPruefung(az040, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ040));

		final PruefungDatumLogischRichtig az042 = new PruefungDatumLogischRichtig(getFeld());
		addPruefung(az042, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ042));
	}

	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {
		if (isPruefbar(FehlerNummerDBAZ.DBAZ044, feldZrbg)) {
			final PruefungDatumNachDatum az044 = new PruefungDatumNachDatum(getFeld(), feldZrbg);
			addPruefungFeldUebergreifend(az044, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ044));
		}

		if (isPruefbar(FehlerNummerDBAZ.DBAZ046, feldZrbg) && isPruefbar(FehlerNummerDBAZ.DBAZ046, feldLeistungsart)
				&& isPruefbar(FehlerNummerDBAZ.DBAZ046, feldKennzSt)
				&& !"54".equals(feldLeistungsart.getTrimmedValue())) {
			if ("J".equals(feldKennzSt.getTrimmedValue()) && !(getFeld().getTrimmedValue().compareTo("19990101") < 0)) {
				final PruefungDatumGleichesJahr az046 = new PruefungDatumGleichesJahr(getFeld(), feldZrbg);
				addPruefungFeldUebergreifend(az046, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ046));
			} else if ("N".equals(feldKennzSt.getTrimmedValue())) {
				final PruefungDatumGleichesJahr az046 = new PruefungDatumGleichesJahr(getFeld(), feldZrbg);
				addPruefungFeldUebergreifend(az046, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ046));
			}
		}

		if (isPruefbar(FehlerNummerDBAZ.DBAZ046, feldLeistungsart)) {
			if (!LEAT_MUTTERSCHAFT.equals(feldLeistungsart.getTrimmedValue())) {
				final Date verarbeitungsDatumPlus3 = DateUtils.berechneNeuesDatumMonat(verarbeitungsDatum, 3, true);
				final PruefungDatumVorDatum az048 = new PruefungDatumVorDatum(this.getFeld(), verarbeitungsDatumPlus3);
				addPruefungFeldUebergreifend(az048, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ048));
			} else {
				final Date verarbeitungsDatumPlus5 = DateUtils.berechneNeuesDatumMonat(verarbeitungsDatum, 5, true);
				final PruefungDatumVorDatum az050 = new PruefungDatumVorDatum(this.getFeld(), verarbeitungsDatumPlus5);
				addPruefungFeldUebergreifend(az050, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ050));
			}
		}
	}
}
