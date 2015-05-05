package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
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
public class PruefungLaufendesEntgeltKv extends AbstractFeldPruefung<FeldNameDBKV, FehlerNummerDBKV> {

	private static final Date Anfang_2015 = new GregorianCalendar(2015, 0, 1).getTime();

	private static final List<String> GRUNDSTELLUNG = Arrays.asList("00000000");

	private final Feld<FeldNameDBKV> feldZeitraumBeginn;
	private final Feld<FeldNameDBKV> feldZeitraumEnde;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungLaufendesEntgeltKv(final Feld<FeldNameDBKV> feld, final Feld<FeldNameDBKV> feldZeitraumBeginn, final Feld<FeldNameDBKV> feldZeitraumEnde) {
		super(feld);

		this.feldZeitraumBeginn = feldZeitraumBeginn;
		this.feldZeitraumEnde = feldZeitraumEnde;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch kv160 = new PruefungNumerisch(getFeld(), true);
		addPruefung(kv160, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV160));
	}

	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {
		final Date zeitraumBeginn = DateUtils.parseDate(feldZeitraumBeginn);
		if (isPruefbar(FehlerNummerDBKV.DBKV164, feldZeitraumBeginn) && zeitraumBeginn.before(Anfang_2015)) {
			final PruefungInList kv164 = new PruefungInList(GRUNDSTELLUNG, getFeld());
			addPruefungFeldUebergreifend(kv164, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV164));
		} else if (isPruefbar(FehlerNummerDBKV.DBKV162, feldZeitraumBeginn, feldZeitraumEnde)) {
			// Personengruppe "normale Beschaeftigte" 000
			final Feld<FeldNameDSME> feldDsmePersgr = new Feld<FeldNameDSME>("000");

			try {
				final Double anteiligeBBG = EntgeltUtil.getInstance().getAnteiligeBeitrBemGrKv(feldDsmePersgr, feldZeitraumBeginn, feldZeitraumEnde);

				final Double zuschlag = Double.valueOf(1.033333);
				final Integer geltendeBezugsgr = (int) Math.ceil(anteiligeBBG * zuschlag);

				final PruefungKleinerGleich kv162 = new PruefungKleinerGleich(getFeld(), geltendeBezugsgr.doubleValue(), 2);
				addPruefungBausteinUebergreifend(kv162, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV162));

			} catch (final WertelistenException e) {
				final PruefungFuegeFehlerHinzu kv162 = new PruefungFuegeFehlerHinzu(getFeld());
				this.addPruefungBausteinUebergreifend(kv162, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV162));
			}
		}
	}
}
