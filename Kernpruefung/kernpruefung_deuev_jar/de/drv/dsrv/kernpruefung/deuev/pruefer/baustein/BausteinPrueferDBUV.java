package de.drv.dsrv.kernpruefung.deuev.pruefer.baustein;

import java.util.ArrayList;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractBausteinPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbuv.PruefungAnzahlUv;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbuv.PruefungArbeitsstunden;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbuv.PruefungBetriebsnummerGefahrtarif;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbuv.PruefungBetriebsnummerUvTraeger;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbuv.PruefungGefahrtarifstelle;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbuv.PruefungMitgliedsnummer;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbuv.PruefungReserve;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbuv.PruefungUvEntgelt;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbuv.PruefungUvGrund;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBUV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBUV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Der Bausteinpruefer fuer den Baustein DBUV. Ruft alle Feldpruefungen auf.
 */
public class BausteinPrueferDBUV extends AbstractBausteinPruefer<FeldNameDBUV, FehlerNummerDBUV> {

	private final Baustein<FeldNameDBME> bausteinDBME;
	private final Baustein<FeldNameDSME> bausteinDSME;
	private final String vfmm;

	/**
	 * Konstruktor. Nimmt alle Werte entgegen, die fuer die Feld-Pruefungen in
	 * dem Baustein benoetigt werden, und speichert diese ab.
	 * 
	 * @param baustein
	 *            Baustein DBUV
	 * @param bausteinDBME
	 *            Baustein DBME
	 * @param bausteinDSME
	 *            Baustein DSME
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public BausteinPrueferDBUV(final Baustein<FeldNameDBUV> baustein, final Baustein<FeldNameDBME> bausteinDBME,
			final Baustein<FeldNameDSME> bausteinDSME, final String vfmm) {
		super(baustein);

		this.bausteinDBME = bausteinDBME;
		this.bausteinDSME = bausteinDSME;
		this.vfmm = vfmm;
	}

	@Override
	public List<FeldPruefung<FeldNameDBUV, FehlerNummerDBUV>> getPruefungen() throws UngueltigePrueferDatenException {
		final List<FeldPruefung<FeldNameDBUV, FehlerNummerDBUV>> pruefList = new ArrayList<FeldPruefung<FeldNameDBUV, FehlerNummerDBUV>>();
		final Baustein<FeldNameDBUV> baustein = this.getBaustein();

		// Felder der Reihenfolge nach auslesen und Pruefungen definieren
		final Feld<FeldNameDBUV> feldAnzahlUv = baustein.getFeld(FeldNameDBUV.ANZAHL_UV);
		final Feld<FeldNameDBUV> feldReserve = baustein.getFeld(FeldNameDBUV.RESERVE);

		if (feldAnzahlUv == null || feldReserve == null) {
			throw new UngueltigePrueferDatenException("Pruefung fuer den Baustein " + baustein.getName()
					+ " kann nicht initalisiert werden, eines der Felder ist nicht vorhanden: " + baustein);
		}
		pruefList.add(new PruefungAnzahlUv(feldAnzahlUv));
		pruefList.add(new PruefungReserve(feldReserve));

		// An dieser Stelle kann davon ausgegangen werden, dass der Wert
		// AnzahlUv korrekt gesetzt ist, da die Ueberpruefung bereits im Parser
		// statt findet.
		final int anzahl = Integer.parseInt(feldAnzahlUv.getTrimmedValue());
		int indexEnum = FeldNameDBUV.UV_GRUND_1.ordinal();

		for (int i = 0; i < anzahl; ++i) {
			final Feld<FeldNameDBUV> feldUvGrund = baustein.getFeld(FeldNameDBUV.values()[indexEnum]);
			++indexEnum;

			final Feld<FeldNameDBUV> feldBetriebsnummerUv = baustein.getFeld(FeldNameDBUV.values()[indexEnum]);
			++indexEnum;

			final Feld<FeldNameDBUV> feldMitgliedsnummer = baustein.getFeld(FeldNameDBUV.values()[indexEnum]);
			++indexEnum;

			final Feld<FeldNameDBUV> feldBetriebsnummerGts = baustein.getFeld(FeldNameDBUV.values()[indexEnum]);
			++indexEnum;

			final Feld<FeldNameDBUV> feldGefahrtarifstelle = baustein.getFeld(FeldNameDBUV.values()[indexEnum]);
			++indexEnum;

			final Feld<FeldNameDBUV> feldEntgeld = baustein.getFeld(FeldNameDBUV.values()[indexEnum]);
			++indexEnum;

			final Feld<FeldNameDBUV> feldArbeitsstunden = baustein.getFeld(FeldNameDBUV.values()[indexEnum]);
			++indexEnum;

			if (feldUvGrund == null || feldBetriebsnummerUv == null || feldMitgliedsnummer == null
					|| feldBetriebsnummerGts == null || feldGefahrtarifstelle == null || feldEntgeld == null
					|| feldArbeitsstunden == null) {
				throw new UngueltigePrueferDatenException("Pruefung fuer den Baustein " + baustein.getName()
						+ " kann nicht initalisiert werden, einer der Felder ist nicht vorhanden: " + baustein);
			}

			pruefList.add(new PruefungUvGrund(feldUvGrund, bausteinDSME, bausteinDBME, vfmm));
			pruefList.add(new PruefungBetriebsnummerUvTraeger(feldBetriebsnummerUv, feldUvGrund, bausteinDBME));
			pruefList.add(new PruefungMitgliedsnummer(feldMitgliedsnummer, feldUvGrund, feldBetriebsnummerUv,
					bausteinDBME));
			pruefList.add(new PruefungBetriebsnummerGefahrtarif(feldBetriebsnummerGts, feldUvGrund,
					feldBetriebsnummerUv, bausteinDBME));
			pruefList.add(new PruefungGefahrtarifstelle(feldGefahrtarifstelle, feldBetriebsnummerGts, bausteinDBME));
			pruefList.add(new PruefungUvEntgelt(feldEntgeld, feldUvGrund, bausteinDBME, bausteinDSME));
			pruefList.add(new PruefungArbeitsstunden(feldArbeitsstunden, feldUvGrund, bausteinDBME));
		}

		return pruefList;
	}

}
