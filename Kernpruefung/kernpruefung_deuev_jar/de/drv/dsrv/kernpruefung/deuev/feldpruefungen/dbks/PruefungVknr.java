package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbks;

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
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKS;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKS;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld VKNR aus dem Baustein DBKS.
 */
public class PruefungVknr extends AbstractFeldPruefung<FeldNameDBKS, FehlerNummerDBKS> {

	private static final List<String> BBNRAB = Arrays.asList("98000006", "98094032", "99086875");
	private static final List<String> VKNR_ZULAESSIGE_WERTE = Arrays.asList("36", "38", "96", "98");
	private static final List<String> VKNR_ALTERSTEILZEIT = Arrays.asList("36", "38");
	private static final List<String> VKNR_AUSSERHALB_ALTERSTEILZEIT = Arrays.asList("96", "98");
	private static final List<String> PERSGR_ALTERSTEILZEIT = Arrays.asList("142");
	private static final List<String> PERSGR_AUSSERHALB_ALTERSTEILZEIT = Arrays.asList("140", "141", "143", "144",
			"149");
	private static final List<String> VFMM_GRUNDSTELLUNG = Arrays.asList("DSTBF", "BFTDS");
	private final Feld<FeldNameDBKS> feldKennzks;
	private final Baustein<FeldNameDSME> bausteinDsme;
	private final Baustein<FeldNameDBME> bausteinDbme;
	private final String vfmm;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldKennzks
	 *            Feld Kennzeichen KS aus DBKS
	 * @param bausteinDsme
	 *            Baustein DSME
	 * @param bausteinDbme
	 *            Baustein DBME
	 * @param vfmm
	 */
	public PruefungVknr(final Feld<FeldNameDBKS> feld, final Feld<FeldNameDBKS> feldKennzks,
			final Baustein<FeldNameDSME> bausteinDsme, final Baustein<FeldNameDBME> bausteinDbme, final String vfmm) {
		super(feld);

		this.feldKennzks = feldKennzks;
		this.bausteinDsme = bausteinDsme;
		this.bausteinDbme = bausteinDbme;
		this.vfmm = vfmm;
	}

	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {

		final List<FehlerNummerDBKS> fehlernummern = new LinkedList<FehlerNummerDBKS>();
		fehlernummern.add(FehlerNummerDBKS.DBKS200);
		fehlernummern.add(FehlerNummerDBKS.DBKS210);
		fehlernummern.add(FehlerNummerDBKS.DBKS220);

		if (((feldKennzks.getTrimmedValue().compareTo("S") == 0) 
				&& (((vfmm.compareTo("KVTRV") == 0) && (BBNRAB.contains(bausteinDsme.getFeld(FeldNameDSME.BBNR_ABSENDER).getTrimmedValue()))) || (VFMM_GRUNDSTELLUNG.contains(vfmm))))
				&& (isPruefbar(fehlernummern, bausteinDsme.getFeld(FeldNameDSME.BBNR_ABSENDER)))) {

			final PruefungNichtLeer ks200a = new PruefungNichtLeer(getFeld());
			addPruefungBausteinUebergreifend(ks200a, new Fehler<FehlerNummerDBKS>(FehlerNummerDBKS.DBKS200));

			final PruefungInList ks200 = new PruefungInList(VKNR_ZULAESSIGE_WERTE, getFeld());
			addPruefungBausteinUebergreifend(ks200, new Fehler<FehlerNummerDBKS>(FehlerNummerDBKS.DBKS200));

			final Feld<FeldNameDSME> feldDsmePersGr = bausteinDsme.getFeld(FeldNameDSME.PERSONENGRUPPE);

			if (VKNR_ALTERSTEILZEIT.contains(getFeld().getTrimmedValue())) {
				Feld<FeldNameDBME> feldDbmeZrbg;
				if (bausteinDbme != null) {
					feldDbmeZrbg = bausteinDbme.getFeld(FeldNameDBME.ZEITRAUM_BEGINN);
				} else {
					feldDbmeZrbg = new Feld<FeldNameDBME>("");
					feldDbmeZrbg.setFehlerfrei(false);
				}

				if (isPruefbar(FehlerNummerDBKS.DBKS210, feldDsmePersGr, feldDbmeZrbg)) {
					final PruefungNichtLeer ks210pers = new PruefungNichtLeer(feldDsmePersGr);
					addPruefungBausteinUebergreifend(ks210pers, new Fehler<FehlerNummerDBKS>(FehlerNummerDBKS.DBKS210));

					final PruefungNichtLeer ks210datum = new PruefungNichtLeer(feldDbmeZrbg);
					addPruefungBausteinUebergreifend(ks210datum, new Fehler<FehlerNummerDBKS>(FehlerNummerDBKS.DBKS210));

					final PruefungInList ks210a = new PruefungInList(PERSGR_ALTERSTEILZEIT, feldDsmePersGr);
					addPruefungBausteinUebergreifend(ks210a, new Fehler<FehlerNummerDBKS>(FehlerNummerDBKS.DBKS210));

					try {
						final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.GERMANY);
						dateFormat.setLenient(false);
						final Date august1996 = dateFormat.parse("19960801");
						// parse Zrgb, damit falls ein falsches Datumformat
						// angegeben wurde, das hier gefangen wird und keine
						// Exception ausgeloest wird. Das falsche Datum wird
						// dann in
						// der Pruefung fuer das Feld selbst abgefangen und als
						// Fehlermeldung zurueckgegeben.
						dateFormat.parse(feldDbmeZrbg.getTrimmedValue());

						final PruefungDatumNachDatum ks210b = new PruefungDatumNachDatum(feldDbmeZrbg, august1996);
						addPruefungBausteinUebergreifend(ks210b, new Fehler<FehlerNummerDBKS>(FehlerNummerDBKS.DBKS210));

					} catch (final ParseException e) {
						if (LOGGER.isLoggable(Level.FINE)) {
							LOGGER.log(Level.FINE, e.toString());
						}
					}
				}
			}

			else if (VKNR_AUSSERHALB_ALTERSTEILZEIT.contains(getFeld().getTrimmedValue())
					&& isPruefbar(FehlerNummerDBKS.DBKS220, feldDsmePersGr)) {
				final PruefungNichtLeer ks220pers = new PruefungNichtLeer(feldDsmePersGr);
				addPruefungBausteinUebergreifend(ks220pers, new Fehler<FehlerNummerDBKS>(FehlerNummerDBKS.DBKS220));

				final PruefungInList ks220 = new PruefungInList(PERSGR_AUSSERHALB_ALTERSTEILZEIT, feldDsmePersGr);
				addPruefungBausteinUebergreifend(ks220, new Fehler<FehlerNummerDBKS>(FehlerNummerDBKS.DBKS220));
			}
		}
	}
}
