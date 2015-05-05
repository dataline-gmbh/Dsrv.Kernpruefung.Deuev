package de.drv.dsrv.kernpruefung.deuev.pruefer.baustein;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractBausteinPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbez.PruefungAbgabegrund;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbez.PruefungBeitragsAnteil;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbez.PruefungEntgelt;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbez.PruefungKennzeichenRechtskreis;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbez.PruefungKennzeichenStornierung;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbez.PruefungKennzeichenWiedereingliederung;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbez.PruefungLeistungsart;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbez.PruefungWaehrungskennzeichen;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbez.PruefungZeitraumBeginn;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbez.PruefungZeitraumEnde;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBEZ;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBEZ;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;

/**
 * Registriert und liefert alle Pruefungen, die fuer die Felder des Bausteins
 * DBEZ definiert wurden.
 */
public class BausteinPrueferDBEZ extends AbstractBausteinPruefer<FeldNameDBEZ, FehlerNummerDBEZ> {

	private final Feld<FeldNameDSAE> feldBbnrVu;
	private final Baustein<FeldNameDSAE> bausteinDsae;
	private final String vfmm;
	private final Date verarbeitungsDatum;

	/**
	 * Konstruktor.
	 * 
	 * @param baustein
	 *            Baustein, fuer den die Pruefungen registriert werden
	 * @param bausteinDsae
	 *            Baustein DSAE
	 * @param verarbeitungsDatum
	 *            Verarbeitungsdatum
	 * @param vfmm
	 *            Opcode (Verfahrensmerkmale)
	 */
	public BausteinPrueferDBEZ(final Baustein<FeldNameDBEZ> baustein, final Feld<FeldNameDSAE> feldBbnrvu,
			final Baustein<FeldNameDSAE> bausteinDsae, final Date verarbeitungsDatum, final String vfmm) {
		super(baustein);

		this.feldBbnrVu = feldBbnrvu;
		this.bausteinDsae = bausteinDsae;
		this.vfmm = vfmm;
		this.verarbeitungsDatum = verarbeitungsDatum;
	}

	@Override
	public List<FeldPruefung<FeldNameDBEZ, FehlerNummerDBEZ>> getPruefungen() throws UngueltigePrueferDatenException {
		final List<FeldPruefung<FeldNameDBEZ, FehlerNummerDBEZ>> pruefList = new ArrayList<FeldPruefung<FeldNameDBEZ, FehlerNummerDBEZ>>();
		final Baustein<FeldNameDBEZ> baustein = getBaustein();

		final Feld<FeldNameDBEZ> feldKennzSt = baustein.getFeld(FeldNameDBEZ.KENNZ_STORNO);
		final Feld<FeldNameDBEZ> feldLeat = baustein.getFeld(FeldNameDBEZ.LEISTUNGSART);
		final Feld<FeldNameDBEZ> feldAbgabegrund = baustein.getFeld(FeldNameDBEZ.ABGABEGRUND);
		final Feld<FeldNameDBEZ> feldZeitraumBeginn = baustein.getFeld(FeldNameDBEZ.ZEITRAUM_BEGINN);
		final Feld<FeldNameDBEZ> feldZeitraumEnde = baustein.getFeld(FeldNameDBEZ.ZEITRAUM_ENDE);
		final Feld<FeldNameDBEZ> feldWaehrungskennz = baustein.getFeld(FeldNameDBEZ.WAEHRUNGSKENNZ);
		final Feld<FeldNameDBEZ> feldEntgelt = baustein.getFeld(FeldNameDBEZ.ENTGELT);
		final Feld<FeldNameDBEZ> feldBeitragsanteil = baustein.getFeld(FeldNameDBEZ.BEITRAGSANTEIL);
		final Feld<FeldNameDBEZ> feldKennzRechtskreis = baustein.getFeld(FeldNameDBEZ.KENNZ_RECHTSKREIS);
		final Feld<FeldNameDBEZ> feldWiedereingliederung = baustein.getFeld(FeldNameDBEZ.KENNZ_WIEDEREINGLIEDERUNG);

		if (feldKennzSt == null || feldLeat == null || feldAbgabegrund == null || feldZeitraumBeginn == null
				|| feldZeitraumEnde == null || feldWaehrungskennz == null || feldEntgelt == null
				|| feldBeitragsanteil == null || feldKennzRechtskreis == null || feldWiedereingliederung == null) {
			throw new UngueltigePrueferDatenException("Pruefung fuer den Baustein " + baustein.getName()
					+ " kann nicht initalisiert werden, einer der Felder ist nicht vorhanden: " + baustein);
		} else {
			pruefList.add(new PruefungKennzeichenStornierung(feldKennzSt));
			pruefList.add(new PruefungLeistungsart(feldLeat, vfmm));
			pruefList.add(new PruefungAbgabegrund(feldAbgabegrund));
			pruefList.add(new PruefungZeitraumBeginn(feldZeitraumBeginn, feldAbgabegrund, feldKennzSt, feldLeat,
					bausteinDsae, verarbeitungsDatum));
			pruefList.add(new PruefungZeitraumEnde(feldZeitraumEnde, feldZeitraumBeginn, feldLeat, bausteinDsae,
					verarbeitungsDatum));
			pruefList.add(new PruefungWaehrungskennzeichen(feldWaehrungskennz, feldZeitraumBeginn));
			pruefList.add(new PruefungEntgelt(feldEntgelt, feldZeitraumBeginn, feldZeitraumEnde, feldLeat,
					feldWaehrungskennz, feldKennzRechtskreis, feldKennzSt, feldBbnrVu, bausteinDsae));
			pruefList.add(new PruefungBeitragsAnteil(feldBeitragsanteil, feldLeat, feldWaehrungskennz,
					feldZeitraumBeginn, feldZeitraumEnde, feldEntgelt));
			pruefList.add(new PruefungKennzeichenRechtskreis(feldKennzRechtskreis, feldLeat));
			pruefList.add(new PruefungKennzeichenWiedereingliederung(feldWiedereingliederung));
		}

		return pruefList;
	}
}
