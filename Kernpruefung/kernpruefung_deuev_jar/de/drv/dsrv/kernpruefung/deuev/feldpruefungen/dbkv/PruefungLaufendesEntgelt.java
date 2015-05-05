package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungFuegeFehlerHinzu;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungKleinerGleich;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.wertelisten.WertelistenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils.DateUtils;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils.EntgeltUtil;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld laufendes Entgelt aus dem Baustein DBKV.
 */
public class PruefungLaufendesEntgelt extends AbstractFeldPruefung<FeldNameDBKV, FehlerNummerDBKV> {

	private static final Date ENDE_2014 = new GregorianCalendar(2014, 11, 31).getTime();

	private static final List<String> GRUNDSTELLUNG = Arrays.asList("00000000");

	private final Feld<FeldNameDBKV> feldZeitraumBeginn;
	private final Feld<FeldNameDBKV> feldZeitraumEnde;
	private final Feld<FeldNameDBKV> feldKennzRk;
	private final Feld<FeldNameDBKV> feldKennzSt;
	private final Baustein<FeldNameDSME> bausteinDsme;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungLaufendesEntgelt(final Feld<FeldNameDBKV> feld, final Feld<FeldNameDBKV> feldZeitraumBeginn, final Feld<FeldNameDBKV> feldZeitraumEnde, final Feld<FeldNameDBKV> feldKennzRk, final Feld<FeldNameDBKV> feldKennzSt,
			final Baustein<FeldNameDSME> bausteinDsme) {
		super(feld);

		this.feldZeitraumBeginn = feldZeitraumBeginn;
		this.feldZeitraumEnde = feldZeitraumEnde;
		this.feldKennzRk = feldKennzRk;
		this.feldKennzSt = feldKennzSt;
		this.bausteinDsme = bausteinDsme;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch kv060 = new PruefungNumerisch(getFeld(), true);
		addPruefung(kv060, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV060));
	}

	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {
		final Date zeitraumBeginn = DateUtils.parseDate(feldZeitraumBeginn);
		if (isPruefbar(FehlerNummerDBKV.DBKV064, feldZeitraumBeginn) && zeitraumBeginn.after(ENDE_2014)) {
			final PruefungInList kv064 = new PruefungInList(GRUNDSTELLUNG, getFeld());
			addPruefungFeldUebergreifend(kv064, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV064));
		}
	}

	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {

		final Feld<FeldNameDSME> feldDsmeBbnrvu = bausteinDsme.getFeld(FeldNameDSME.BBNR_VU);
		final Feld<FeldNameDSME> feldDsmeVstr = bausteinDsme.getFeld(FeldNameDSME.VSTR);

		// Bei DBKV handelt es sich um Euro
		final Feld<FeldNameDBKV> feldWaehrungskennzeichen = new Feld<FeldNameDBKV>("E");

		if (isPruefbar(FehlerNummerDBKV.DBKV062, feldZeitraumBeginn, feldZeitraumEnde, feldKennzRk, feldKennzSt, feldDsmeBbnrvu, feldDsmeVstr) && "N".equals(feldKennzSt.getTrimmedValue())) {
			// Personengruppe "normale Beschaeftigte" 000
			final Feld<FeldNameDSME> feldDsmePersgr = new Feld<FeldNameDSME>("000");

			try {
				final Double anteiligeBBG = EntgeltUtil.getInstance().getAnteiligeBeitrBemGr(feldDsmePersgr, feldZeitraumBeginn, feldZeitraumEnde, feldWaehrungskennzeichen, feldKennzRk, feldDsmeBbnrvu, feldDsmeVstr);

				final Double zuschlag = Double.valueOf(1.033333);
				final Integer geltendeBezugsgr = (int) Math.ceil(anteiligeBBG * zuschlag);

				final PruefungKleinerGleich kv062 = new PruefungKleinerGleich(getFeld(), geltendeBezugsgr.doubleValue(), 2);
				addPruefungBausteinUebergreifend(kv062, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV062));

			} catch (final WertelistenException e) {
				final PruefungFuegeFehlerHinzu kv062 = new PruefungFuegeFehlerHinzu(getFeld());
				this.addPruefungBausteinUebergreifend(kv062, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV062));
			}
		}
	}
}
