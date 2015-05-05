package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbuv;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.FeldPruefungStornierungDsme;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBUV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBUV;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.ListenTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Pruefung fuer das Feld Betriebsnummer UV-Traeger aus dem Baustein DBUV.
 */
public class PruefungBetriebsnummerUvTraeger extends AbstractFeldPruefung<FeldNameDBUV, FehlerNummerDBUV> {

	private static final List<String> UVGD_GRUNDSTELLUNG = Arrays.asList("C01", "C06");
	private static final List<String> UVGD_ANLAGE19A = Arrays.asList("A08");
	private static final List<String> UVGD_BBNR = Arrays.asList("A07", "A09");

	private static final List<String> BBNR = Arrays.asList("01627953", "03701377", "09322747", "13385729", "18626026",
			"18645029", "21204943", "26125562", "28143238", "29086457", "29214533", "34239086", "44861264", "98705576");
	private final Feld<FeldNameDBUV> feldUvgd;
	private final List<String> bbnrAnlage20;
	private final List<String> uvTraegerAnlage19a;
	private final List<String> uvTraegerAnlage19b;
	private final Baustein<FeldNameDBME> bausteinDbme;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldUvgd
	 *            Feld UV-Grund aus DBUV
	 * @param bausteinDbme
	 *            Baustein DBME
	 */
	public PruefungBetriebsnummerUvTraeger(final Feld<FeldNameDBUV> feld, final Feld<FeldNameDBUV> feldUvgd,
			final Baustein<FeldNameDBME> bausteinDbme) {
		super(feld);

		this.feldUvgd = feldUvgd;
		this.bbnrAnlage20 = WertelistenVerwaltungDeuev.getInstance().getWerteliste(
				ListenTypDeuev.A20_BETRIEBSNUMMER_UVTRAEGER);
		this.uvTraegerAnlage19a = WertelistenVerwaltungDeuev.getInstance().getWerteliste(
				ListenTypDeuev.A19A_UNFALLVERSICHERUNGSTRAEGER);
		this.uvTraegerAnlage19b = WertelistenVerwaltungDeuev.getInstance().getWerteliste(
				ListenTypDeuev.A19B_UNFALLVERSICHERUNGSTRAEGER);
		this.bausteinDbme = bausteinDbme;
	}

	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {
		final List<FehlerNummerDBUV> fehlernummern = new LinkedList<FehlerNummerDBUV>();
		fehlernummern.add(FehlerNummerDBUV.DBUV100);
		fehlernummern.add(FehlerNummerDBUV.DBUV102);
		fehlernummern.add(FehlerNummerDBUV.DBUV104);
		fehlernummern.add(FehlerNummerDBUV.DBUV106);
		fehlernummern.add(FehlerNummerDBUV.DBUV103);
		fehlernummern.add(FehlerNummerDBUV.DBUV105);

		if (bausteinDbme == null || isPruefbar(fehlernummern, bausteinDbme.getFeld(FeldNameDBME.KENNZ_STORNO))) {

			final FeldPruefungStornierungDsme stornierung = new FeldPruefungStornierungDsme(bausteinDbme);
			if (!stornierung.isStornierungDbme()) {
				if (getFeld().getTrimmedValue().length() > 0) {
					final PruefungInList uv100 = new PruefungInList(bbnrAnlage20, getFeld());
					addPruefungBausteinUebergreifend(uv100, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV100));
				}

				else {
					if (isPruefbar(FehlerNummerDBUV.DBUV102, feldUvgd)) {
						final PruefungNichtLeer uv102a = new PruefungNichtLeer(feldUvgd);
						addPruefungBausteinUebergreifend(uv102a, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV102));

						final PruefungInList uv102 = new PruefungInList(UVGD_GRUNDSTELLUNG, feldUvgd, true);
						addPruefungBausteinUebergreifend(uv102, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV102));
					}
				}

				final List<FehlerNummer> fehlernummernUvgd = new LinkedList<FehlerNummer>();
				fehlernummernUvgd.add(FehlerNummerDBUV.DBUV104);
				fehlernummernUvgd.add(FehlerNummerDBUV.DBUV106);
				fehlernummernUvgd.add(FehlerNummerDBUV.DBUV103);
				fehlernummernUvgd.add(FehlerNummerDBUV.DBUV105);

				if (isPruefbar(FehlerNummerDBUV.DBUV102, feldUvgd)) {
					if (feldUvgd.getTrimmedValue().compareTo("A08") == 0) {
						final PruefungNichtLeer uv104a = new PruefungNichtLeer(getFeld());
						addPruefungBausteinUebergreifend(uv104a, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV104));

						final PruefungInList uv104 = new PruefungInList(uvTraegerAnlage19a, getFeld());
						addPruefungBausteinUebergreifend(uv104, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV104));
					} else if (feldUvgd.getTrimmedValue().compareTo("A09") == 0) {
						final PruefungNichtLeer uv106a = new PruefungNichtLeer(getFeld());
						addPruefungBausteinUebergreifend(uv106a, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV106));

						final PruefungInList uv106 = new PruefungInList(uvTraegerAnlage19b, getFeld());
						addPruefungBausteinUebergreifend(uv106, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV106));
					}

					if (uvTraegerAnlage19a.contains(getFeld().getTrimmedValue())) {
						final PruefungNichtLeer uv103a = new PruefungNichtLeer(feldUvgd);
						addPruefungBausteinUebergreifend(uv103a, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV103));

						final PruefungInList uv103 = new PruefungInList(UVGD_ANLAGE19A, feldUvgd);
						addPruefungBausteinUebergreifend(uv103, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV103));
					}

					if (BBNR.contains(getFeld().getTrimmedValue())) {
						final PruefungNichtLeer uv105a = new PruefungNichtLeer(feldUvgd);
						addPruefungBausteinUebergreifend(uv105a, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV105));

						final PruefungInList uv105 = new PruefungInList(UVGD_BBNR, feldUvgd);
						addPruefungBausteinUebergreifend(uv105, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV105));
					}
				}
			}
		}
	}
}
