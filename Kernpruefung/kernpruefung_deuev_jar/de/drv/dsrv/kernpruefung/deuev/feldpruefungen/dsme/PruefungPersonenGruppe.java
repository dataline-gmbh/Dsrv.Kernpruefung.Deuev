package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBeginntMitZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.FeldPruefungStornierungDsme;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.ListenTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Pruefung fuer das Feld Personengruppe aus dem Baustein DSME.
 */
public class PruefungPersonenGruppe extends AbstractFeldPruefung<FeldNameDSME, FehlerNummerDSME> {

	private static final List<Character> BEGINNT_MIT = Arrays.asList('1');
	private static final List<String> ERLAUBTE_WERTE_PERSGR_DSME208 = Arrays.asList("102", "103", "107", "111", "121", "122", "204");
	private static final List<String> ERLAUBTE_WERTE_PERSGR_DSME209 = Arrays.asList("099", "990", "991", "992");
	private static final List<String> ERLAUBTE_WERTE_PERSGR_DSME212 = Arrays.asList("203");
	private static final List<String> ERLAUBTE_WERTE_PERSGR_DSME228 = Arrays.asList("207", "208");
	private static final List<String> ERLAUBTE_WERTE_PERSGR_DSME210 = Arrays.asList("103", "107", "111", "204");
	
	private static final List<String> GD_KEINE_STORNIERUNG = Arrays.asList("10", "11", "12", "13", "40");
	private static final List<String> ERLAUBTE_WERTE_NULLSTELLUNG = Arrays.asList("000");
	private static final List<String> ERLAUBTE_WERTE_BBNRVU_WEHRDIENST = Arrays.asList("32349289");
	private static final List<String> ERLAUBTE_WERTE_BBNRVU_ZIVILDIENST = Arrays.asList("38065304");
	private static final List<String> ERLAUBTE_WERTE_BBNRVU_PFLEGEPERSON_BEGINNT = Arrays.asList("996");
	private static final List<String> BBNRVU_GRUNDSTELLUNG = Arrays.asList("985", "987");

	private static final List<String> PERSGR_BBNRVU2 = Arrays.asList("140", "141", "142", "143", "144", "149");
	private static final List<String> PERSGR_WEHRDIENST = Arrays.asList("301", "302", "305");
	private static final List<String> PERSGR_ZIVILDIENST = Arrays.asList("303", "304");
	private static final List<String> PERSGR_PFLEGEPERSON = Arrays.asList("207", "208");

	private static final int ERSTEN_DREI_ZEICHEN = 3;
	private final Feld<FeldNameDSME> feldBbnrvu;
	private final Feld<FeldNameDSME> feldGd;
	private final Baustein<FeldNameDBME> bausteinDbme;
	private final List<String> persgr;
	private final String vfmm;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldBbnrvu
	 *            Feld Betriebsnummer-Verursacher aus DSME
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public PruefungPersonenGruppe(final Feld<FeldNameDSME> feld, final Feld<FeldNameDSME> feldBbnrvu, final Feld<FeldNameDSME> feldGd, final Baustein<FeldNameDBME> bausteinDbme, final String vfmm) {
		super(feld);

		this.feldBbnrvu = feldBbnrvu;
		this.persgr = new ArrayList<String>(WertelistenVerwaltungDeuev.getInstance().getWerteliste(ListenTypDeuev.A2_SCHLUESSELZAHL_PERSONENGRUPPE));
		this.feldGd = feldGd;
		this.bausteinDbme = bausteinDbme;
		this.vfmm = vfmm;

		if (LOGGER.isLoggable(Level.FINE)) {
			LOGGER.log(Level.FINE, "[DSME Personengruppe] \"" + feld.getValue() + "\"");
		}
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch me200b = new PruefungNumerisch(getFeld(), true);
		addPruefung(me200b, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME200));

		if (vfmm.compareTo("AGDEU") == 0) {
			final PruefungBeginntMitZeichen me202 = new PruefungBeginntMitZeichen(BEGINNT_MIT, getFeld());
			addPruefung(me202, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME202));
		}

		persgr.add("000");
		final PruefungInList me204 = new PruefungInList(persgr, getFeld());
		addPruefung(me204, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME204));

		final String bbnrvuStartsWith;
		if (feldBbnrvu.getTrimmedValue().length() > ERSTEN_DREI_ZEICHEN) {
			bbnrvuStartsWith = feldBbnrvu.getTrimmedValue().substring(0, ERSTEN_DREI_ZEICHEN);
		} else {
			bbnrvuStartsWith = "";
		}

		if ((vfmm.compareTo("BATRV") == 0) || (vfmm.compareTo("KTTRV") == 0)) {
			final PruefungInList me216 = new PruefungInList(ERLAUBTE_WERTE_NULLSTELLUNG, getFeld());
			addPruefung(me216, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME216));
		}
		
		// TODO Damit Java wie Cobol läuft, dürfen nachfolgende Prüfungen nur bei Personengruppe ungleich 000 erfolgen.
		if(!getFeld().getTrimmedValue().equals("000")) {
			final Feld<FeldNameDSME> feldBbnrvuStartsWith = new Feld<FeldNameDSME>(bbnrvuStartsWith);
			if ((bbnrvuStartsWith.compareTo("985") == 0) || (bbnrvuStartsWith.compareTo("987") == 0)) {
				final PruefungInList me208 = new PruefungInList(ERLAUBTE_WERTE_PERSGR_DSME208, getFeld());
				addPruefung(me208, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME208));
			}
	
			if (PERSGR_BBNRVU2.contains(getFeld().getTrimmedValue())) {
				final PruefungInList me209 = new PruefungInList(ERLAUBTE_WERTE_PERSGR_DSME209, feldBbnrvuStartsWith);
				addPruefung(me209, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME209));
			}
			
			final FeldPruefungStornierungDsme stornierung = new FeldPruefungStornierungDsme(bausteinDbme);
			if (!stornierung.isStornierungDbme() && GD_KEINE_STORNIERUNG.contains(feldGd.getTrimmedValue()) && BBNRVU_GRUNDSTELLUNG.contains(bbnrvuStartsWith)) {
				final PruefungInList me210 = new PruefungInList(ERLAUBTE_WERTE_PERSGR_DSME210, getFeld());
				addPruefung(me210, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME210));
			}		
	
			if ((feldBbnrvu.getTrimmedValue().compareTo("01085914") == 0)
					|| (feldBbnrvu.getTrimmedValue().compareTo("28180427") == 0)) {
				final PruefungInList me212 = new PruefungInList(ERLAUBTE_WERTE_PERSGR_DSME212, getFeld());
				addPruefung(me212, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME212));
			}
	
			if (PERSGR_WEHRDIENST.contains(getFeld().getTrimmedValue())) {
				final PruefungInList me218 = new PruefungInList(ERLAUBTE_WERTE_BBNRVU_WEHRDIENST, feldBbnrvu);
				addPruefung(me218, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME218));
			}
	
			if (PERSGR_ZIVILDIENST.contains(getFeld().getTrimmedValue())) {
				final PruefungInList me222 = new PruefungInList(ERLAUBTE_WERTE_BBNRVU_ZIVILDIENST, feldBbnrvu);
				addPruefung(me222, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME222));
			}
	
			if (PERSGR_PFLEGEPERSON.contains(getFeld().getTrimmedValue())) {
				final PruefungInList me226 = new PruefungInList(ERLAUBTE_WERTE_BBNRVU_PFLEGEPERSON_BEGINNT,
						feldBbnrvuStartsWith);
				addPruefung(me226, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME226));
			}
	
			if (bbnrvuStartsWith.compareTo("996") == 0) {
				final PruefungInList me228 = new PruefungInList(ERLAUBTE_WERTE_PERSGR_DSME228, getFeld());
				addPruefung(me228, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME228));
			}
		}
	}
}
