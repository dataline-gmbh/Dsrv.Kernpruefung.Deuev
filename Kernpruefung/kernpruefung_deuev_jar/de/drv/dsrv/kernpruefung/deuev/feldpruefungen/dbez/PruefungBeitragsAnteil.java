package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbez;

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
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.wertelisten.WertelistenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils.DateUtils;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils.EntgeltUtil;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBEZ;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBEZ;

/**
 * Pruefung fuer das Feld Beitragsanteil aus dem Baustein DBEZ.
 */
public class PruefungBeitragsAnteil extends AbstractFeldPruefung<FeldNameDBEZ, FehlerNummerDBEZ> {

	private static final List<String> LEAT_GRUNDSTELLUNG = Arrays.asList("02", "03", "06", "09", "21", "22", "23",
			"25", "26", "27", "28", "29", "30", "31", "32", "33", "40", "41", "42", "43", "44", "50");

	private static final int NACHKOMMASTELLEN = 2;

	private final Feld<FeldNameDBEZ> feldLeistungsart;
	private final Feld<FeldNameDBEZ> feldWaehrungskennzeichen;
	private final Feld<FeldNameDBEZ> feldZeitraumBeginn;
	private final Feld<FeldNameDBEZ> feldZeitraumEnde;
	private final Feld<FeldNameDBEZ> feldEntgelt;

	private static final Date ENDE_1991 = new GregorianCalendar(1991, 11, 31).getTime();
	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldLeistungsart
	 *            Feld Leistungsart aus dem Baustein DBEZ.
	 * @param feldWaehrungskennzeichen
	 *            Feld Waehrungskennzeichen aus dem Baustein DBEZ.
	 * @param feldZeitraumBeginn
	 *            Feld ZeitraumBeginn aus dem Baustein DBEZ.
	 * @param feldZeitraumEnde
	 *            Feld ZeitraumEnde aus dem Baustein DBEZ.
	 * @param feldEntgelt
	 *            Feld Entgelt aus dem Baustein DBEZ.
	 */
	public PruefungBeitragsAnteil(final Feld<FeldNameDBEZ> feld, final Feld<FeldNameDBEZ> feldLeistungsart,
			final Feld<FeldNameDBEZ> feldWaehrungskennzeichen, final Feld<FeldNameDBEZ> feldZeitraumBeginn,
			final Feld<FeldNameDBEZ> feldZeitraumEnde, final Feld<FeldNameDBEZ> feldEntgelt) {
		super(feld);

		this.feldLeistungsart = feldLeistungsart;
		this.feldWaehrungskennzeichen = feldWaehrungskennzeichen;
		this.feldZeitraumBeginn = feldZeitraumBeginn;
		this.feldZeitraumEnde = feldZeitraumEnde;
		this.feldEntgelt = feldEntgelt;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch ez100 = new PruefungNumerisch(getFeld(), true);
		addPruefung(ez100, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ100));
	}

	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {

		if (isPruefbar(FehlerNummerDBEZ.DBEZ102, feldLeistungsart)
				&& LEAT_GRUNDSTELLUNG.contains(feldLeistungsart.getTrimmedValue())) {
			final PruefungInList ez102 = new PruefungInList(Arrays.asList("0000000"), getFeld());
			addPruefungFeldUebergreifend(ez102, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ102));
		}

		final Date zeitraumBeginn = DateUtils.parseDate(feldZeitraumBeginn);
		if (isPruefbar(FehlerNummerDBEZ.DBEZ104, feldZeitraumBeginn, feldZeitraumEnde, feldWaehrungskennzeichen, feldEntgelt) && zeitraumBeginn.after(ENDE_1991)) {
			try {
				final int beitragsbemessungsgrenze = EntgeltUtil.getInstance().getBeitragsanteil(feldZeitraumBeginn, feldZeitraumEnde, feldWaehrungskennzeichen);
				
				final PruefungKleinerGleich ez104 = new PruefungKleinerGleich(getFeld(), (double) beitragsbemessungsgrenze, NACHKOMMASTELLEN);
				addPruefungFeldUebergreifend(ez104, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ104));
			} catch (final WertelistenException e) {
				final PruefungFuegeFehlerHinzu ez104 = new PruefungFuegeFehlerHinzu(getFeld());
				addPruefungFeldUebergreifend(ez104, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ104));
			}
		}

		if (!"0000000".equals(getFeld().getTrimmedValue())) {
			if (isPruefbar(FehlerNummerDBEZ.DBEZ106, feldWaehrungskennzeichen)) {
				final PruefungNichtLeer ez106 = new PruefungNichtLeer(feldWaehrungskennzeichen);
				addPruefungFeldUebergreifend(ez106, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ106));
			}
		}
	}
}
