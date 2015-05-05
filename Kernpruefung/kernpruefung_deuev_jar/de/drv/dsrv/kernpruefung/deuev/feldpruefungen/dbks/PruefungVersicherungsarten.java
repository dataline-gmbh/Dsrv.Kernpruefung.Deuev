package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbks;

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
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKS;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKS;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld Versicherungsarten aus dem Baustein DBKS.
 */
public class PruefungVersicherungsarten extends AbstractFeldPruefung<FeldNameDBKS, FehlerNummerDBKS> {

	private static final List<String> VA_ANTRAGSVERSICHERUNG = Arrays.asList("60", "70");
	private static final List<String> BBNRKK_KNAPPSCHAFT = Arrays.asList("98000006");
	private final Feld<FeldNameDBKS> feldKennzks;
	private final Baustein<FeldNameDBME> bausteinDbme;
	private final Baustein<FeldNameDSME> bausteinDsme;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldKennzks
	 *            Feld Kennzeichen KS aus DBKS
	 * @param bausteinDbme
	 *            Baustein DBME
	 * @param bausteinDsme
	 *            Baustein DSME
	 */
	public PruefungVersicherungsarten(final Feld<FeldNameDBKS> feld, final Feld<FeldNameDBKS> feldKennzks, 
			final Baustein<FeldNameDBME> bausteinDbme, final Baustein<FeldNameDSME> bausteinDsme) {
		super(feld);

		this.feldKennzks = feldKennzks;
		this.bausteinDbme = bausteinDbme;
		this.bausteinDsme = bausteinDsme;
	}

	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {
		if ((bausteinDbme != null)) {

			if ((feldKennzks.getTrimmedValue().compareTo("S") == 0) && 
					isPruefbar(FehlerNummerDBKS.DBKS100, bausteinDbme.getFeld(FeldNameDBME.ZEITRAUM_BEGINN), bausteinDsme.getFeld(FeldNameDSME.BBNR_KK))) {

				try {
					final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.GERMANY);
					dateFormat.setLenient(false);
					final Date ende2007 = dateFormat.parse("20071231");
					final Date zeitraumBeginn = dateFormat.parse(bausteinDbme.getFeld(FeldNameDBME.ZEITRAUM_BEGINN).getTrimmedValue());

					if (zeitraumBeginn.after(ende2007) && VA_ANTRAGSVERSICHERUNG.contains(getFeld().getTrimmedValue())) {
						final Feld<FeldNameDSME> feldDsmeBbnrkk = bausteinDsme.getFeld(FeldNameDSME.BBNR_KK);

						final PruefungNichtLeer ks100a = new PruefungNichtLeer(feldDsmeBbnrkk);
						addPruefungBausteinUebergreifend(ks100a, new Fehler<FehlerNummerDBKS>(FehlerNummerDBKS.DBKS100));

						final PruefungInList ks100b = new PruefungInList(BBNRKK_KNAPPSCHAFT, feldDsmeBbnrkk);
						addPruefungBausteinUebergreifend(ks100b, new Fehler<FehlerNummerDBKS>(FehlerNummerDBKS.DBKS100));
					}
				} catch (final ParseException e) {
					if (LOGGER.isLoggable(Level.FINE)) {
						LOGGER.log(Level.FINE, e.toString());
					}
				}
			}
		}
	}
}
