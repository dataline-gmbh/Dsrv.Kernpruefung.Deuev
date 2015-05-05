package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbme;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumNachDatum;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNotInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld Kennzeichen Rechtskreis aus dem Baustein DBME.
 */
public class PruefungKennzeichenRechtskreis extends AbstractFeldPruefung<FeldNameDBME, FehlerNummerDBME> {

	private static final List<String> ZULAESSIGE_WERTE = Arrays.asList("W", "O");
	private static final List<String> PERSGR_O = Arrays.asList("301", "302", "303");
	private static final List<String> PERSGR_VOR_1999 = Arrays.asList("205", "207", "208", "301", "302", "305", "303");
	private static final List<String> BBNRVU = Arrays.asList("001", "002", "003", "004", "005", "006", "007", "008",
			"009", "010", "011", "012", "013", "014", "015", "016", "017", "018", "019", "020", "021", "022", "023",
			"024", "025", "026", "027", "028", "029", "030", "031", "032", "033", "034", "035", "036", "037", "038",
			"039", "040", "041", "042", "043", "044", "045", "046", "047", "048", "049", "050", "051", "052", "053",
			"054", "055", "056", "057", "058", "059", "060", "061", "062", "063", "064", "065", "066", "067", "068",
			"069", "070", "071", "072", "073", "074", "075", "076", "077", "078", "079", "080", "081", "082", "083",
			"084", "085", "086", "087", "088", "089", "090", "091", "092", "093", "094", "095", "096", "097", "098",
			"099", "987");
	private static final int START_BBNRVU = 3;

	private final Feld<FeldNameDBME> feldZeitraumBeginn;
	private final Feld<FeldNameDBME> feldZeitraumEnde;
	private final Baustein<FeldNameDSME> bausteinDsme;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldZeitraumBeginn
	 *            Feld Zeitraumbeginn aus DBME
	 * @param feldZeitraumEnde
	 *            Feld ZeitraumEnde aus DBME
	 * @param bausteinDsme
	 *            Baustein DSME
	 */
	public PruefungKennzeichenRechtskreis(final Feld<FeldNameDBME> feld, final Feld<FeldNameDBME> feldZeitraumBeginn,
			final Feld<FeldNameDBME> feldZeitraumEnde, final Baustein<FeldNameDSME> bausteinDsme) {
		super(feld);

		this.feldZeitraumBeginn = feldZeitraumBeginn;
		this.feldZeitraumEnde = feldZeitraumEnde;
		this.bausteinDsme = bausteinDsme;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		if (getFeld().getTrimmedValue().length() > 0) {
			final PruefungInList me160 = new PruefungInList(ZULAESSIGE_WERTE, getFeld(), true);
			addPruefung(me160, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME160));
		}
	}

	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {
		final Feld<FeldNameDSME> feldDsmePersGr = bausteinDsme.getFeld(FeldNameDSME.PERSONENGRUPPE);
		final Feld<FeldNameDSME> feldDsmeBbnrvu = bausteinDsme.getFeld(FeldNameDSME.BBNR_VU);
		final Feld<FeldNameDSME> feldDsmeBbnrvuStart;

		if (feldDsmeBbnrvu.getTrimmedValue().length() > START_BBNRVU) {
			feldDsmeBbnrvuStart = new Feld<FeldNameDSME>(feldDsmeBbnrvu.getTrimmedValue().substring(0, START_BBNRVU));
		} else {
			feldDsmeBbnrvuStart = new Feld<FeldNameDSME>("invalid");
			feldDsmeBbnrvu.setFehlerfrei(false);
		}

		final List<FehlerNummerDBME> fehlernummernPersGr = new LinkedList<FehlerNummerDBME>();
		fehlernummernPersGr.add(FehlerNummerDBME.DBME163);
		fehlernummernPersGr.add(FehlerNummerDBME.DBME165);

		if (isPruefbar(fehlernummernPersGr, feldDsmePersGr)) {
			if (feldDsmePersGr.getTrimmedValue().compareTo("304") != 0) {
				final PruefungNichtLeer me163 = new PruefungNichtLeer(getFeld());
				addPruefungBausteinUebergreifend(me163, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME163));
			} else {
				final PruefungLaenge me165 = new PruefungLaenge(0, getFeld());
				addPruefungBausteinUebergreifend(me165, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME165));
			}
		}

		try {
			final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.GERMANY);
			dateFormat.setLenient(false);
			final Date datumOkt1990 = dateFormat.parse("19901003");

			if (isPruefbar(FehlerNummerDBME.DBME167, feldDsmePersGr, feldZeitraumBeginn)
					&& PERSGR_O.contains(feldDsmePersGr.getTrimmedValue())
					&& (getFeld().getTrimmedValue().compareTo("O") == 0)) {
				final PruefungDatumNachDatum me167 = new PruefungDatumNachDatum(feldZeitraumBeginn, datumOkt1990);
				addPruefungBausteinUebergreifend(me167, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME167));
			}

			final Date datumVor1999 = dateFormat.parse("19990101");
			Date datumZeitraum;
			if (feldZeitraumEnde.getTrimmedValue().compareTo("00000000") == 0) {
				datumZeitraum = dateFormat.parse(feldZeitraumBeginn.getTrimmedValue());
			} else {
				datumZeitraum = dateFormat.parse(feldZeitraumEnde.getTrimmedValue());
			}

			final List<FehlerNummerDBME> fehlernummernOW = new LinkedList<FehlerNummerDBME>();
			fehlernummernOW.add(FehlerNummerDBME.DBME162);
			fehlernummernOW.add(FehlerNummerDBME.DBME164);

			if (isPruefbar(fehlernummernOW, feldDsmePersGr, feldZeitraumBeginn, feldZeitraumEnde, feldDsmeBbnrvu)
					&& !PERSGR_VOR_1999.contains(feldDsmePersGr.getTrimmedValue())
					&& datumZeitraum.before(datumVor1999)) {
				if ("O".equals(getFeld().getTrimmedValue())) {
					final PruefungInList me164 = new PruefungInList(BBNRVU, feldDsmeBbnrvuStart);
					addPruefungBausteinUebergreifend(me164, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME164));
				}

				else if ("W".equals(getFeld().getTrimmedValue())) {
					final PruefungNotInList me162 = new PruefungNotInList(BBNRVU, feldDsmeBbnrvuStart);
					addPruefungBausteinUebergreifend(me162, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME162));
				}
			}

		} catch (final ParseException e) {
			if (LOGGER.isLoggable(Level.FINE)) {
				LOGGER.log(Level.FINE, e.toString());
			}
		}

		if (isPruefbar(FehlerNummerDBME.DBME168, feldDsmePersGr)
				&& feldDsmePersGr.getTrimmedValue().compareTo("306") == 0) {
			final PruefungInList me168 = new PruefungInList(Arrays.asList("W"), getFeld(), true);
			addPruefungBausteinUebergreifend(me168, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME168));
		}
	}
}
