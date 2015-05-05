package de.drv.dsrv.kernpruefung.deuev.fehler;

import java.util.ArrayList;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.fehler.AbstractFehlerBausteinGenerator;
import de.drv.dsrv.kernpruefung.basis.fehler.FehlerTextNichtVorhandenException;
import de.drv.dsrv.kernpruefung.basis.model.DatensatzName;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.FehlerArt;
import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;
import de.drv.dsrv.kernpruefung.basis.wertelisten.WertelistenException;
import de.drv.dsrv.kernpruefung.deuev.model.ReturnCodesDeuev;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSAE;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSBD;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSKO;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;

/**
 * Erstellt anhand der Fehlerdaten einen Fehlerbaustein. In DEUEV werden bis zu
 * 9 Fehler in 9 Fehlerbausteinen gemeldet.
 */
public class FehlerBausteinGenerator extends AbstractFehlerBausteinGenerator {

	/**
	 * Wert der Fehler-Kennzeichnung fuer "Datensatz fehlerfrei".
	 */
	public static final String FEHLER_KENNZ_FEHLERFREI = "0";

	private static final String FEHLERTEXTE_DATEINAME = "/de/drv/dsrv/kernpruefung/deuev/wertelisten/fehlertexte.properties";
	private static final int MAX_LAENGE = 76;

	private transient List<Fehler<? extends FehlerNummer>> fehlerListe;
	private transient List<Fehler<? extends FehlerNummer>> hinweisListe;
	private final transient String datensatzWert;
	private static final int ANZ_FEHLER = 9;

	/**
	 * Konstruktor.
	 * 
	 * @param datensatzWert
	 *            der urspruengliche Datensatz
	 * @param fehlerListe
	 *            Liste der Fehler, von der Pruefung erstellt wurde
	 */
	public FehlerBausteinGenerator(final String datensatzWert, final List<Fehler<? extends FehlerNummer>> fehlerListe) {
		super(fehlerListe, FEHLERTEXTE_DATEINAME, MAX_LAENGE);
		splitFehlerliste(fehlerListe);
		this.datensatzWert = datensatzWert;
	}

	/**
	 * Erstellt einen Fehlerbaustein, falls in der Pruefung Fehler aufgetreten
	 * sind, und haengt diesen Fehlerbaustein an den ursprueglichen Datensatz.
	 * Sollten Hinweise und keine Fehler auftreten, dann werden die Hinweise als
	 * Fehlerbausteine zurueckgegeben.
	 * 
	 * @param datensatzName
	 *            Der DatensatzName, zugehoerig zum entsprechenden Datensatz.
	 * 
	 * @return Datensatz mit dem erzeugten Fehlerbaustein, falls Fehler in der
	 *         Pruefung aufgetreten sind.
	 * @throws FehlerTextNichtVorhandenException
	 *             wird ausgeloest, wenn zu einem Fehler kein Fehlertext in der
	 *             Wertelistendatei hinterlegt ist
	 * @throws WertelistenException
	 *             wird ausgeloest, wenn die Datei mit den Fehlertexten nicht
	 *             geladen werden kann
	 */
	@Override
	public String generiereFehlerbausteine(final DatensatzName datensatzName) throws FehlerTextNichtVorhandenException,
			WertelistenException {
		final StringBuilder stringBuilder = new StringBuilder(datensatzWert);

		List<Fehler<? extends FehlerNummer>> tmp = null;
		boolean isFehlerListe = false;

		// Validiere, ob Fehler vorliegen. Falls ja, haenge dafuer
		// Fehlerbausteine an den Datensatz. Falls nein, validiere, ob Hinweise
		// vorliegen. Falls ja, haenge dafuer Fehlerbausteine an den Datensatz.
		// Falls nein, lass den Datensatz wie er ist.
		if (this.fehlerListe.size() > 0) {
			tmp = fehlerListe;
			stringBuilder.replace(INDEX_FEHLER_KENNZ, INDEX_FEHLER_KENNZ + 1, "1");
			isFehlerListe = true;
		} else if (this.hinweisListe.size() > 0) {
			tmp = hinweisListe;
			stringBuilder.replace(INDEX_FEHLER_KENNZ, INDEX_FEHLER_KENNZ + 1, "3");
			isFehlerListe = false;
		}

		if (tmp != null) {

			int wertAnzFehler = tmp.size();
			if (wertAnzFehler > ANZ_FEHLER) {
				wertAnzFehler = ANZ_FEHLER;
			}

			stringBuilder.replace(INDEX_ANZAHL_FEHLER, INDEX_ANZAHL_FEHLER + 1, Integer.toString(wertAnzFehler));

			int anzFehler = 0;
			for (final Fehler<? extends FehlerNummer> fehler : tmp) {
				if (anzFehler < ANZ_FEHLER - 1)
					stringBuilder.append(zuFehlerbaustein(fehler));

				// max 9 Fehlerbausteine anhaengen.
				if (++anzFehler == ANZ_FEHLER) {
					switch (datensatzName) {
					case DSKO:
						stringBuilder.append(zuFehlerbaustein(new Fehler<FehlerNummer>(FehlerNummerDSKO.DSKO920)));
						break;

					case DSBD:
						stringBuilder.append(zuFehlerbaustein(new Fehler<FehlerNummer>(FehlerNummerDSBD.DSBD920)));
						break;

					case DSME:
						if (isFehlerListe) {
							stringBuilder.append(zuFehlerbaustein(new Fehler<FehlerNummer>(FehlerNummerDSME.DSME920)));
						} else {
							stringBuilder.append(zuFehlerbaustein(new Fehler<FehlerNummer>(FehlerNummerDSME.DSME922)));
						}
						break;

					case DSAE:
						stringBuilder.append(zuFehlerbaustein(new Fehler<FehlerNummer>(FehlerNummerDSAE.DSAE920)));
						break;

					default:
						break;
					}

					break;
				}
			}
		}

		return stringBuilder.toString();
	}

	/**
	 * Ermittelt anhand der Listen, welcher Return Code zurueckgegeben werden
	 * muss.
	 * 
	 * @return 0000 (ERFOLG_OHNEFEHLER), 1000 (ERFOLG_MITFEHLER), 1200
	 *         (FEHLER_HINWEISE_DATENSATZ) oder 2000 (HINWEIS)
	 */
	public ReturnCodesDeuev getReturnCode() {
		ReturnCodesDeuev rCode = null;

		if ((fehlerListe.size() > 0) && (hinweisListe.size() > 0)) {
			rCode = ReturnCodesDeuev.FEHLER_HINWEISE_DATENSATZ;
		} else if (fehlerListe.size() > 0) {
			rCode = ReturnCodesDeuev.ERFOLG_MITFEHLER;
		} else if (hinweisListe.size() > 0) {
			rCode = ReturnCodesDeuev.HINWEIS;
		} else {
			rCode = ReturnCodesDeuev.ERFOLG_OHNEFEHLER;
		}

		return rCode;
	}

	/**
	 * Sortiert die Fehler und Hinweise in die entsprechende Liste.
	 * 
	 * @param liste
	 */
	private void splitFehlerliste(final List<Fehler<? extends FehlerNummer>> liste) {
		hinweisListe = new ArrayList<Fehler<? extends FehlerNummer>>();
		fehlerListe = new ArrayList<Fehler<? extends FehlerNummer>>();

		for (final Fehler<? extends FehlerNummer> fehler : liste) {
			if (fehler.getFehlerArt() == FehlerArt.FEHLER) {
				fehlerListe.add(fehler);
			} else if (fehler.getFehlerArt() == FehlerArt.HINWEIS) {
				hinweisListe.add(fehler);
			}
		}
	}
}
