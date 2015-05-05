package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbme;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungZiffernZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.ListenTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Fuer diese Pruefung wurde neben der Anlage 9.4 auch teilweise die
 * Entscheidungstabelle ab530309_Logik_PY-Taetigkeitsschluessel.doc zu Rate
 * gezogen.
 */
public class PruefungTaetigkeitsSchluessel extends AbstractFeldPruefung<FeldNameDBME, FehlerNummerDBME> {
	
	private final transient List<String> lsttaetigkeitsschluessel = WertelistenVerwaltungDeuev.getInstance().getWerteliste(ListenTypDeuev.A5_TAETIGKEITSSCHLUESSEL_FELDAT);

	private static final List<String> GD_ANMELDUNG = Arrays.asList("10", "11", "12", "13");
	private static final List<String> GD_ENTGELTMELDUNG = Arrays.asList("30", "31", "32", "33", "34", "35", "36", "40",	"49", "50", "51", "52", "53", "54", "55", "56", "57", "59", "70", "71", "72", "94", "95");
	private static final List<String> PERSGR_GRUNDSTELLUNG_BBNRVU = Arrays.asList("102", "121", "122");
	private static final List<String> PERSGR_GRUNDSTELLUNG_DBME151U153 = Arrays.asList("107", "108", "111", "116", "203", "204", "207", "208", "209", "210", "301", "302", "303", "304", "305", "306");
	private static final List<String> PERSGR_GRUNDSTELLUNG_DBME154 = Arrays.asList("108", "116", "203", "207", "208", "209", "210", "301", "302", "303", "304", "305", "306");
	private static final List<String> PERSGR_GRUNDSTELLUNG_DBME156 = Arrays.asList("107", "111", "204");
	private static final List<String> BBNRVU_GRUNDSTELLUNG = Arrays.asList("985", "987");

	private static final int INDEX_FELDAT_START = 0;
	private static final int INDEX_FELDAT_ENDE = 5;

	private static final int INDEX_FELDAS_START = INDEX_FELDAT_ENDE;
	private static final int INDEX_FELDAS_ENDE = 6;

	private static final int INDEX_FELDBA_START = INDEX_FELDAS_ENDE;
	private static final int INDEX_FELDBA_ENDE = 7;

	private static final int INDEX_FELDAUE_START = INDEX_FELDBA_ENDE;
	private static final int INDEX_FELDAUE_ENDE = 8;

	private static final int INDEX_FELDVF_START = INDEX_FELDAUE_ENDE;
	private static final int INDEX_FELDVF_ENDE = 9;

	private static final List<Character> WERTE_FELD_TTSC__SIGNS_2003 = Arrays.asList(' ');
	private static final List<String> WERTE_FELD_AS = Arrays.asList("1", "2", "3", "4", "9");
	private static final List<String> WERTE_FELD_BA = Arrays.asList("1", "2", "3", "4", "5", "6", "9");
	private static final List<String> WERTE_FELD_AUE = Arrays.asList("1", "2");
	private static final List<String> WERTE_FELD_VF = Arrays.asList("1", "2", "3", "4");

	private static final int START_BBNR = 3;

	private final Feld<FeldNameDBME> feldZeitraumBeginn;
	private final Feld<FeldNameDBME> feldZeitraumEnde;
	private final Feld<FeldNameDBME> feldKennzst;
	private final Baustein<FeldNameDSME> bausteinDSME;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldZeitraumBeginn
	 *            Feld Zeitraumbeginn aus DBME
	 * @param feldZeitraumEnde
	 *            Feld Zeitraumende aus DBME
	 * @param feldKennzst
	 *            Feld Kennzeichen Storno aus DBME
	 * @param bausteinDSME
	 *            Baustein DSME
	 */
	public PruefungTaetigkeitsSchluessel(final Feld<FeldNameDBME> feld, final Feld<FeldNameDBME> feldZeitraumBeginn,
			final Feld<FeldNameDBME> feldZeitraumEnde, final Feld<FeldNameDBME> feldKennzst,
			final Baustein<FeldNameDSME> bausteinDSME) {
		super(feld);

		this.feldZeitraumBeginn = feldZeitraumBeginn;
		this.feldZeitraumEnde = feldZeitraumEnde;
		this.feldKennzst = feldKennzst;
		this.bausteinDSME = bausteinDSME;

		if (LOGGER.isLoggable(Level.FINE)) {
			LOGGER.log(Level.FINE, "[DBME Tätigkeitsschlüssel] \"" + feld.getValue() + "\"");
		}
	}

	
	
	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {
		// Baustein DSME kann nicht null sein.
		final Feld<FeldNameDSME> feldDsmeGd = bausteinDSME.getFeld(FeldNameDSME.ABGABEGRUND);
		final Feld<FeldNameDSME> feldDsmePersGr = bausteinDSME.getFeld(FeldNameDSME.PERSONENGRUPPE);
		final Feld<FeldNameDSME> feldBbnrvu = bausteinDSME.getFeld(FeldNameDSME.BBNR_VU);
		
		final String bbnrvuStartsWith;
		if (feldBbnrvu.getTrimmedValue().length() > START_BBNR) {
			bbnrvuStartsWith = feldBbnrvu.getTrimmedValue().substring(0, START_BBNR);
		} else {
			bbnrvuStartsWith = "invalid";
		}

		if ( !bbnrvuStartsWith.equals("098") && !bbnrvuStartsWith.equals("980") ) {
			try {
				final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.GERMANY);
				dateFormat.setLenient(false);
				final Date datumDez2011 = dateFormat.parse("20111201");
				final Date datumNov2011 = dateFormat.parse("20111130");
				final Date datumDez2014 = dateFormat.parse("20141201");
				final Date datumNov2014 = dateFormat.parse("20141130");
			
				Date datumZeitraum;
				if (GD_ANMELDUNG.contains(feldDsmeGd.getTrimmedValue())) {
					datumZeitraum = dateFormat.parse(feldZeitraumBeginn.getTrimmedValue());
				} else if (GD_ENTGELTMELDUNG.contains(feldDsmeGd.getTrimmedValue())) {
					datumZeitraum = dateFormat.parse(feldZeitraumEnde.getTrimmedValue());
				} else {
					datumZeitraum = null;
				}
				
				// Pruefung Taetigkeitsschluessel bis 1.12.2011
				if (datumZeitraum != null && datumZeitraum.before(datumDez2011)) {
					final PruefungZiffernZeichen me149 = new PruefungZiffernZeichen(WERTE_FELD_TTSC__SIGNS_2003, getFeld());
					addPruefungBausteinUebergreifend(me149, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME149));

					// Pruefung Taetigkeitsschluessel ab 1.12.2011
				} else if (datumZeitraum != null && datumZeitraum.after(datumNov2011)) {
					final String ttsc = getFeld().getTrimmedValue();
					if (ttsc.length() > 0) {
						String feldAt = null;
						String feldAs = null;
						String feldBa = null;
						String feldAue = null;
						String feldVf = null;
						try {
							feldAt = ttsc.substring(INDEX_FELDAT_START, INDEX_FELDAT_ENDE);
							feldAs = ttsc.substring(INDEX_FELDAS_START, INDEX_FELDAS_ENDE);
							feldBa = ttsc.substring(INDEX_FELDBA_START, INDEX_FELDBA_ENDE);
							feldAue = ttsc.substring(INDEX_FELDAUE_START, INDEX_FELDAUE_ENDE);
							feldVf = ttsc.substring(INDEX_FELDVF_START, INDEX_FELDVF_ENDE);	
						} catch (StringIndexOutOfBoundsException e) {
							final PruefungFuegeFehlerHinzu me150 = new PruefungFuegeFehlerHinzu(getFeld());
							addPruefungBausteinUebergreifend(me150, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME150));
						}
						
						if ( feldKennzst.getTrimmedValue().equals("N") && datumZeitraum.before(datumDez2014) && ((BBNRVU_GRUNDSTELLUNG.contains(bbnrvuStartsWith) && PERSGR_GRUNDSTELLUNG_BBNRVU.contains(feldDsmePersGr.getTrimmedValue())) || PERSGR_GRUNDSTELLUNG_DBME151U153.contains(feldDsmePersGr.getTrimmedValue()))) {
							final PruefungLaenge me151 = new PruefungLaenge(0, getFeld());
							addPruefungBausteinUebergreifend(me151, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME151));
						}
						
						// Pruefung Taetigkeitsschluessel ab 1.12.2014
						boolean dbme156Nichtgeprueft = true;
						if (datumZeitraum != null && datumZeitraum.after(datumNov2014)) {
								
							if	(feldKennzst.getTrimmedValue().equals("N") && PERSGR_GRUNDSTELLUNG_DBME154.contains(feldDsmePersGr.getTrimmedValue())) {
								final PruefungLaenge me154 = new PruefungLaenge(0, getFeld());
								addPruefungBausteinUebergreifend(me154, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME154));
							}
							
							// TODO Damit Java wie Cobol läuft, muss das KennzeichenStorno mitbetrachtet werden.
							if	(feldKennzst.getTrimmedValue().equals("N") && PERSGR_GRUNDSTELLUNG_DBME156.contains(feldDsmePersGr.getTrimmedValue())) {
								dbme156Nichtgeprueft = false;
								List<String> tmpListTTSC = new ArrayList<String>(this.lsttaetigkeitsschluessel);
								tmpListTTSC.add("     ");
								
								if (!(tmpListTTSC.contains(feldAt) && WERTE_FELD_AS.contains(feldAs) && WERTE_FELD_BA.contains(feldBa) && WERTE_FELD_AUE.contains(feldAue) && WERTE_FELD_VF.contains(feldVf))) {
									final PruefungFuegeFehlerHinzu me156 = new PruefungFuegeFehlerHinzu(new Feld<FeldNameDBME>(getFeld().getValue()));
									addPruefungBausteinUebergreifend(me156, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME156));
								}
							}
						}
						 
						if (dbme156Nichtgeprueft && !(this.lsttaetigkeitsschluessel.contains(feldAt) && WERTE_FELD_AS.contains(feldAs) && WERTE_FELD_BA.contains(feldBa) && WERTE_FELD_AUE.contains(feldAue) && WERTE_FELD_VF.contains(feldVf)) ) {
							final PruefungFuegeFehlerHinzu me150 = new PruefungFuegeFehlerHinzu(new Feld<FeldNameDBME>(ttsc));
							addPruefungBausteinUebergreifend(me150, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME150));
						}
					} else {
						if ( feldKennzst.getTrimmedValue().equals("N") && !( (BBNRVU_GRUNDSTELLUNG.contains(bbnrvuStartsWith) && PERSGR_GRUNDSTELLUNG_BBNRVU.contains(feldDsmePersGr.getTrimmedValue())) || PERSGR_GRUNDSTELLUNG_DBME151U153.contains(feldDsmePersGr.getTrimmedValue()) )) {
							final PruefungFuegeFehlerHinzu me153 = new PruefungFuegeFehlerHinzu(new Feld<FeldNameDBME>(ttsc));
							addPruefungBausteinUebergreifend(me153, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME153));
							
						} else if( feldKennzst.getTrimmedValue().equals("N") && datumZeitraum.before(datumDez2014) && !((BBNRVU_GRUNDSTELLUNG.contains(bbnrvuStartsWith) && PERSGR_GRUNDSTELLUNG_BBNRVU.contains(feldDsmePersGr.getTrimmedValue())) || PERSGR_GRUNDSTELLUNG_DBME151U153.contains(feldDsmePersGr.getTrimmedValue()))) {
							final PruefungFuegeFehlerHinzu me151 = new PruefungFuegeFehlerHinzu(new Feld<FeldNameDBME>(ttsc));
							addPruefungBausteinUebergreifend(me151, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME151));
							
						} else if (feldKennzst.getTrimmedValue().equals("N") && datumZeitraum.after(datumNov2014) && PERSGR_GRUNDSTELLUNG_DBME154.contains(feldDsmePersGr.getTrimmedValue())) {
							final PruefungLaenge me154 = new PruefungLaenge(0, getFeld());
							addPruefungBausteinUebergreifend(me154, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME154));
							
						} else if (feldKennzst.getTrimmedValue().equals("N") && datumZeitraum.after(datumNov2014) && PERSGR_GRUNDSTELLUNG_DBME156.contains(feldDsmePersGr.getTrimmedValue())) {
							final PruefungFuegeFehlerHinzu me156 = new PruefungFuegeFehlerHinzu(new Feld<FeldNameDBME>(ttsc));
							addPruefungBausteinUebergreifend(me156, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME156));
						}
					}
				}
			} catch (final ParseException e) {
				if (LOGGER.isLoggable(Level.FINE)) {
					LOGGER.log(Level.FINE, e.toString());
				}
			}
		}
	}	
}
