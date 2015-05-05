package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbuv;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungMaxLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungMinLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungPattern;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.FeldPruefungStornierungDsme;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBUV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBUV;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.MapTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Pruefung fuer das Feld Mitgliedsnummer aus dem Baustein DBUV.
 */
public class PruefungMitgliedsnummer extends AbstractFeldPruefung<FeldNameDBUV, FehlerNummerDBUV> {

	private static final List<String> UVGD_GRUNDSTELLUNG = Arrays.asList("A07", "A08", "A09", "C01", "C06");
	private final Feld<FeldNameDBUV> feldUvgd;
	private final Feld<FeldNameDBUV> feldBbnrUv;
	private final Map<String, String> mitgliedsnrLaenge;
	private final Map<String, String> mitgliedsnrZeichen;
	private final Baustein<FeldNameDBME> bausteinDbme;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldUvgd
	 *            Feld UV-Grund DBUV
	 * @param feldBbnrUv
	 *            Feld Betriebsnummer-UV aus DBUV
	 * @param bausteinDbme
	 *            Baustein DBME
	 */
	public PruefungMitgliedsnummer(final Feld<FeldNameDBUV> feld, final Feld<FeldNameDBUV> feldUvgd,
			final Feld<FeldNameDBUV> feldBbnrUv, final Baustein<FeldNameDBME> bausteinDbme) {
		super(feld);

		this.feldUvgd = feldUvgd;
		this.feldBbnrUv = feldBbnrUv;
		this.mitgliedsnrLaenge = WertelistenVerwaltungDeuev.getInstance().getMap(
				MapTypDeuev.A20_MITGLIEDSNUMMER_LAENGE);
		this.mitgliedsnrZeichen = WertelistenVerwaltungDeuev.getInstance().getMap(
				MapTypDeuev.A20_MITGLIEDSNUMMER_ZEICHEN);
		this.bausteinDbme = bausteinDbme;
	}

	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {

		final List<FehlerNummerDBUV> fehlernummern = new LinkedList<FehlerNummerDBUV>();
		fehlernummern.add(FehlerNummerDBUV.DBUV120);
		fehlernummern.add(FehlerNummerDBUV.DBUV122);
		fehlernummern.add(FehlerNummerDBUV.DBUV124);

		if (bausteinDbme == null || isPruefbar(fehlernummern, bausteinDbme.getFeld(FeldNameDBME.KENNZ_STORNO))) {

			final FeldPruefungStornierungDsme stornierung = new FeldPruefungStornierungDsme(bausteinDbme);
			if (!stornierung.isStornierungDbme()) {
				if (getFeld().getTrimmedValue().length() == 0) {
					if (isPruefbar(FehlerNummerDBUV.DBUV120, feldUvgd)) {
						final PruefungNichtLeer uv120a = new PruefungNichtLeer(feldUvgd);
						addPruefungBausteinUebergreifend(uv120a, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV120));

						final PruefungInList uv120 = new PruefungInList(UVGD_GRUNDSTELLUNG, feldUvgd, true);
						addPruefungBausteinUebergreifend(uv120, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV120));
					}
				}

				else {
					final String laenge = mitgliedsnrLaenge.get(feldBbnrUv.getTrimmedValue());
					if (isPruefbar(FehlerNummerDBUV.DBUV122, feldBbnrUv) && (laenge != null) && (laenge.length() > 1)) {
						final String[] bereich = laenge.split(",");
						final int min = Integer.parseInt(bereich[0]);
						final int max = Integer.parseInt(bereich[1]);

						final PruefungMinLaenge uv122a = new PruefungMinLaenge(min, getFeld());
						addPruefungBausteinUebergreifend(uv122a, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV122));

						final PruefungMaxLaenge uv122b = new PruefungMaxLaenge(max, getFeld());
						addPruefungBausteinUebergreifend(uv122b, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV122));
					}

					final String zeichen = mitgliedsnrZeichen.get(feldBbnrUv.getTrimmedValue());
					if (isPruefbar(FehlerNummerDBUV.DBUV124, feldBbnrUv) && (zeichen != null) && (zeichen.length() > 1)) {
						// +, da sich die erlaubten Zeichen wiederholen duerfen/muessen.
						getFeld().setValue(getFeld().getValue().toUpperCase());
						final PruefungPattern uv124 = new PruefungPattern(getFeld(), zeichen + "+");
						addPruefungBausteinUebergreifend(uv124, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV124));
					}
				}
			}
		}
	}
}
