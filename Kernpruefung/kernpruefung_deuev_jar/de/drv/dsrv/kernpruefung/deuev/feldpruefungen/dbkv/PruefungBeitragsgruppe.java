package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNotInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;
import de.drv.dsrv.kernpruefung.deuev.pruefungen.PruefungIstBeitragsgruppe;

/**
 * Pruefung fuer das Feld Beitragsgruppe aus dem Baustein DBKV.
 */
public class PruefungBeitragsgruppe extends AbstractFeldPruefung<FeldNameDBKV, FehlerNummerDBKV> {

	// @formatter:off
	private static final Date ENDE_2012 = new GregorianCalendar(2012, 11, 31).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	// @formatter:on

	private final DateFormat dateFormat;
	private Date datumZrgbKv;

	private final Baustein<FeldNameDSME> bausteinDsme;
	private final Baustein<FeldNameDBME> bausteinDbme;

	private final Feld<FeldNameDBKV> feldZrgbKv;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldZrgbKv
	 *            Feld Zeitraumbeginn KV aus DBKV
	 * @param bausteinDsme
	 * @param bausteinDbme
	 */
	public PruefungBeitragsgruppe(final Feld<FeldNameDBKV> feld, final Feld<FeldNameDBKV> feldZrgbKv,
			final Baustein<FeldNameDSME> bausteinDsme, final Baustein<FeldNameDBME> bausteinDbme) {
		super(feld);

		this.bausteinDsme = bausteinDsme;
		this.bausteinDbme = bausteinDbme;

		dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.GERMANY);
		dateFormat.setLenient(false);

		this.feldZrgbKv = feldZrgbKv;

		try {
			datumZrgbKv = dateFormat.parse(feldZrgbKv.getTrimmedValue());
		} catch (final ParseException e) {
			if (LOGGER.isLoggable(Level.FINE)) {
				LOGGER.log(Level.FINE, e.toString());
			}
		}
	}

	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {
		final List<FehlerNummerDBKV> fehlernummern = new LinkedList<FehlerNummerDBKV>();
		fehlernummern.add(FehlerNummerDBKV.DBKV140);
		fehlernummern.add(FehlerNummerDBKV.DBKV144);

		if (isPruefbar(fehlernummern, feldZrgbKv)) {
			if (datumZrgbKv.after(ENDE_2012)) {
				final PruefungNumerisch kv140 = new PruefungNumerisch(getFeld(), true);
				addPruefungFeldUebergreifend(kv140, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV140));
			} else {
				final PruefungLaenge kv144 = new PruefungLaenge(0, getFeld());
				addPruefungFeldUebergreifend(kv144, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV144));
			}
		}
	}

	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {
		final Feld<FeldNameDSME> feldDsmePersgr = bausteinDsme.getFeld(FeldNameDSME.PERSONENGRUPPE);
		final Feld<FeldNameDSME> feldDsmeStaatsAng = bausteinDsme.getFeld(FeldNameDSME.STAATSANGEHOERIGKEITS_SC);

		Feld<FeldNameDBME> feldDbmeKennzStorno;
		if (bausteinDbme != null) {
			feldDbmeKennzStorno = bausteinDbme.getFeld(FeldNameDBME.KENNZ_STORNO);
		} else {
			feldDbmeKennzStorno = new Feld<FeldNameDBME>(" ");
		}

		if (isPruefbar(FehlerNummerDBKV.DBKV142, feldZrgbKv, feldDsmePersgr, feldDsmeStaatsAng, feldDbmeKennzStorno)
				&& datumZrgbKv.after(ENDE_2012)) {
			final PruefungIstBeitragsgruppe kv142 = new PruefungIstBeitragsgruppe(getFeld(), datumZrgbKv);
			addPruefungBausteinUebergreifend(kv142, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV142));

			final PruefungNotInList kv142b = new PruefungNotInList(Arrays.asList("0000"), getFeld());
			addPruefungBausteinUebergreifend(kv142b, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV142));
		}
	}
}
