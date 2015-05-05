package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbez;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungFuegeFehlerHinzu;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInterval;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungKleinerGleich;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNotInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.wertelisten.WertelistenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils.EntgeltUtil;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBEZ;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBEZ;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;

/**
 * Pruefung fuer das Feld Entgelt aus dem Baustein DBEZ.
 */
public class PruefungEntgelt extends AbstractFeldPruefung<FeldNameDBEZ, FehlerNummerDBEZ> {

	private static final List<String> LEAT_ARBEITSLOSENGELD = Arrays.asList("43", "44");
	private static final String KEIN_STORNO = "N";
	private static final double MAX_ENTGELT_BIS_2006 = 4800;
	private static final double MAX_ENTGELT_AB_2007 = 2460;

	private final Feld<FeldNameDBEZ> feldZeitraumBeginn;
	private final Feld<FeldNameDBEZ> feldZeitraumEnde;
	private final Feld<FeldNameDBEZ> feldLeistungsart;
	private final Feld<FeldNameDBEZ> feldWaehrungskennzeichen;
	private final Feld<FeldNameDBEZ> feldKennzRk;
	private final Feld<FeldNameDBEZ> feldKennzSt;
	
	private final Feld<FeldNameDSAE> feldBbnrvu;
	private final Baustein<FeldNameDSAE> bausteinDsae;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            das zu pruefende Feld
	 * @param feldLeistungsart
	 *            Feld Leistungsart aus dem Baustein DBEZ
	 * @param feldWaehrungskennzeichen
	 *            Feld Waehrungskennzeichen aus dem Baustein DBEZ
	 * @param feldZeitraumBeginn
	 *            Feld ZeitraumBeginn aus dem Baustein DBEZ
	 * @param feldZeitraumEnde
	 *            Feld ZeitraumEnde aus dem Baustein DBEZ
	 * @param feldKennzRk
	 *            das Feld Kennzeichen RK aus dem Baustein DBEZ
	 * @param bausteinDsae
	 *            Baustein DSAE
	 */
	public PruefungEntgelt(final Feld<FeldNameDBEZ> feld, final Feld<FeldNameDBEZ> feldZeitraumBeginn,
			final Feld<FeldNameDBEZ> feldZeitraumEnde, final Feld<FeldNameDBEZ> feldLeistungsart,
			final Feld<FeldNameDBEZ> feldWaehrungskennzeichen, final Feld<FeldNameDBEZ> feldKennzRk,
			final Feld<FeldNameDBEZ> feldKennzSt, final Feld<FeldNameDSAE> feldBbnrvu,
			final Baustein<FeldNameDSAE> bausteinDsae) {
		super(feld);

		this.feldZeitraumBeginn = feldZeitraumBeginn;
		this.feldZeitraumEnde = feldZeitraumEnde;
		this.feldLeistungsart = feldLeistungsart;
		this.feldWaehrungskennzeichen = feldWaehrungskennzeichen;
		this.feldKennzRk = feldKennzRk;
		this.feldBbnrvu = feldBbnrvu;
		this.feldKennzSt = feldKennzSt;
		this.bausteinDsae = bausteinDsae;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch ez090 = new PruefungNumerisch(getFeld(), true);
		addPruefung(ez090, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ090));
	}

	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {
		// 095 vor 094, da hier definiert wird, dass ggf. Waehrungskennzeichen
		// nicht leer sein darf, was in 094 verwendet wird.
		if (isPruefbar(FehlerNummerDBEZ.DBEZ095, feldWaehrungskennzeichen)
				&& !"000000".equals(getFeld().getTrimmedValue())) {
			final PruefungNichtLeer ez095 = new PruefungNichtLeer(feldWaehrungskennzeichen);
			addPruefungFeldUebergreifend(ez095, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ095));
		}

		if (isPruefbar(FehlerNummerDBEZ.DBEZ094, feldZeitraumBeginn, feldLeistungsart)
				&& "19911231".compareTo(feldZeitraumBeginn.getTrimmedValue()) < 0
				&& !LEAT_ARBEITSLOSENGELD.contains(feldLeistungsart.getTrimmedValue())) {
			final PruefungNotInList ez094 = new PruefungNotInList(Arrays.asList("000000"), getFeld());
			addPruefungFeldUebergreifend(ez094, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ094));
		}

		if (isPruefbar(Arrays.asList(FehlerNummerDBEZ.DBEZ097, FehlerNummerDBEZ.DBEZ098), feldZeitraumBeginn,
				feldLeistungsart) && feldKennzSt.getTrimmedValue().equals(KEIN_STORNO) && LEAT_ARBEITSLOSENGELD.contains(feldLeistungsart.getTrimmedValue())) {
			
			try {
				final int anzahlBerechnugsTage = EntgeltUtil.getInstance().getAnzahlTageDbez(feldZeitraumBeginn, feldZeitraumEnde);
				
				final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.GERMANY);
				dateFormat.setLenient(false);
				final Date datumJan2007 = dateFormat.parse("20070101");
				final Date datumZeitraum = dateFormat.parse(feldZeitraumBeginn.getTrimmedValue());
					
				if (datumZeitraum != null && datumZeitraum.before(datumJan2007)) {
					final double maxBBGZeitraum = (anzahlBerechnugsTage * MAX_ENTGELT_BIS_2006) / 360;
					final int maxBBGFuerZeitraum = ((Double)Math.ceil(maxBBGZeitraum)).intValue();
					final PruefungInterval ez097 = new PruefungInterval(0, maxBBGFuerZeitraum, getFeld());
					addPruefungFeldUebergreifend(ez097, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ097));
				} else {
					final double maxBBGZeitraum = (anzahlBerechnugsTage * MAX_ENTGELT_AB_2007) / 360.0;
					final int maxBBGFuerZeitraum = ((Double)Math.ceil(maxBBGZeitraum)).intValue();
					final PruefungInterval ez098 = new PruefungInterval(0, maxBBGFuerZeitraum, getFeld());
					addPruefungFeldUebergreifend(ez098, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ098));
				}
			} catch (final ParseException e) {
				if (LOGGER.isLoggable(Level.FINE)) {
					LOGGER.log(Level.FINE, e.toString());
				}
			}
		}
	}

	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {
		final Feld<FeldNameDSAE> feldDsaeVstr = bausteinDsae.getFeld(FeldNameDSAE.VSTR);

		if (isPruefbar(FehlerNummerDBEZ.DBEZ096, feldDsaeVstr, feldZeitraumBeginn, feldZeitraumEnde,
				feldWaehrungskennzeichen, feldKennzRk, feldLeistungsart) && feldKennzSt.getTrimmedValue().equals(KEIN_STORNO)) {

			try {
				final int geltendeBezugsgr = EntgeltUtil.getInstance().getMaxEntgeltDbez(feldZeitraumBeginn,
						feldZeitraumEnde, feldWaehrungskennzeichen, feldKennzRk, feldDsaeVstr, feldBbnrvu, feldLeistungsart);
				final PruefungKleinerGleich ez096 = new PruefungKleinerGleich(getFeld(), (double) geltendeBezugsgr);
				addPruefungBausteinUebergreifend(ez096, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ096));
			} catch (final WertelistenException e) {
				final PruefungFuegeFehlerHinzu ez096 = new PruefungFuegeFehlerHinzu(getFeld());
				addPruefungBausteinUebergreifend(ez096, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ096));
			}
		}
	}
}
