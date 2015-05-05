package de.drv.dsrv.kernpruefung.deuev.pruefer.baustein;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Datensatz;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractBausteinPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungAbgabegrund;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungAnredeAnsprechpartner;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungBetriebsnummerAbrechnungsstelle;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungBetriebsnummerAbsender;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungBetriebsnummerBetriebsstaette;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungBetriebsnummerEmpfaenger;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungBetriebsnummerKk;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungDatumErstellung;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungEmailAnsprechpartner;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungFehleranzahl;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungFehlerkennzeichnung;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungHausnummer;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungKennung;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungMeldendeStelle;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungMmka;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungMmtn;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungNameBezeichnung1;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungOrt;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungPostleitzahlPostfach;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungPostleitzahlZustell;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungReserve1;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungReserve2;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungReserve3;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungRuhendKennzeichen;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungStrasse;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungVersionsnummer;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd.PruefungWirtschaftsUnterklasse;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSBD;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSBD;

/**
 * Der Bausteinpruefer fuer den Baustein DSBD. Ruft alle Feldpruefungen auf.
 */
public class BausteinPrueferDSBD extends AbstractBausteinPruefer<FeldNameDSBD, FehlerNummerDSBD> {

	private final Date verarbeitungsDatum;
	private final Datensatz datensatz;
	private final String vfmm;

	/**
	 * Konstruktor. Nimmt alle Werte entgegen, die fuer die Feld-Pruefungen in
	 * dem Baustein benoetigt werden, und speichert diese ab.
	 * 
	 * @param baustein
	 *            Baustein DSBD
	 * @param verarbeitungsDatum
	 *            Verarbeitungsdatum aus den Aufrufparametern
	 * @param datensatz
	 *            Der komplette Datensatz
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public BausteinPrueferDSBD(final Baustein<FeldNameDSBD> baustein, final Date verarbeitungsDatum,
			final Datensatz datensatz, final String vfmm) {
		super(baustein);

		this.verarbeitungsDatum = verarbeitungsDatum;
		this.datensatz = datensatz;
		this.vfmm = vfmm;
	}

	@Override
	public List<FeldPruefung<FeldNameDSBD, FehlerNummerDSBD>> getPruefungen() throws UngueltigePrueferDatenException {
		final List<FeldPruefung<FeldNameDSBD, FehlerNummerDSBD>> pruefList = new ArrayList<FeldPruefung<FeldNameDSBD, FehlerNummerDSBD>>();
		final Baustein<FeldNameDSBD> baustein = getBaustein();

		final Feld<FeldNameDSBD> feldKennung = baustein.getFeld(FeldNameDSBD.KENNUNG);
		final Feld<FeldNameDSBD> feldVerfahren = baustein.getFeld(FeldNameDSBD.VERFAHREN);
		final Feld<FeldNameDSBD> feldBbnrAbsender = baustein.getFeld(FeldNameDSBD.BBNR_ABSENDER);
		final Feld<FeldNameDSBD> feldBbnrEmpfaenger = baustein.getFeld(FeldNameDSBD.BBNR_EMPFAENGER);
		final Feld<FeldNameDSBD> feldVersion = baustein.getFeld(FeldNameDSBD.VERSIONS_NR);
		final Feld<FeldNameDSBD> feldDatumErstellung = baustein.getFeld(FeldNameDSBD.DATUM_ERSTELLUNG);
		final Feld<FeldNameDSBD> feldFehlerkennzeichnung = baustein.getFeld(FeldNameDSBD.FEHLER_KENNZ);
		final Feld<FeldNameDSBD> feldFehlerAnzahl = baustein.getFeld(FeldNameDSBD.FEHLER_ANZAHL);
		final Feld<FeldNameDSBD> feldBbnrBetriebsstaette = baustein.getFeld(FeldNameDSBD.BBNR_BETRIEBSSTAETTE);
		final Feld<FeldNameDSBD> feldReserve1 = baustein.getFeld(FeldNameDSBD.RESERVE1);
		final Feld<FeldNameDSBD> feldBbnrAbrechnungsstelle = baustein.getFeld(FeldNameDSBD.BBNR_ABRECHNUNGSSTELLE);
		final Feld<FeldNameDSBD> feldAbgabegrund = baustein.getFeld(FeldNameDSBD.ABGABEGRUND);
		final Feld<FeldNameDSBD> feldWirtschaftsUnterklasse = baustein.getFeld(FeldNameDSBD.WIRTSCHAFTSUNTERKLASSE);
		final Feld<FeldNameDSBD> feldNameBezeichnung1 = baustein.getFeld(FeldNameDSBD.NAME_BEZEICHNUNG1);
		final Feld<FeldNameDSBD> feldPostleitzahlZustell = baustein.getFeld(FeldNameDSBD.POSTLEITZAHL_ZUSTELL);
		final Feld<FeldNameDSBD> feldOrt = baustein.getFeld(FeldNameDSBD.ORT);
		final Feld<FeldNameDSBD> feldStrasse = baustein.getFeld(FeldNameDSBD.STRASSE);
		final Feld<FeldNameDSBD> feldHausNr = baustein.getFeld(FeldNameDSBD.HAUSNUMMER);
		final Feld<FeldNameDSBD> feldPostleitzahlPostfach = baustein.getFeld(FeldNameDSBD.POSTLEITZAHL_POSTFACH);
		final Feld<FeldNameDSBD> feldRuhendKennzeichen = baustein.getFeld(FeldNameDSBD.RUHEND_KENNZEICHEN);
		final Feld<FeldNameDSBD> feldMeldendeStelle = baustein.getFeld(FeldNameDSBD.MELDENDE_STELLE);
		final Feld<FeldNameDSBD> feldAnredeAnsprechpartner = baustein.getFeld(FeldNameDSBD.ANREDE_ANSPRECHPARTNER);
		final Feld<FeldNameDSBD> feldEmailAnsprechpartner = baustein.getFeld(FeldNameDSBD.EMAIL_ANSPRECHPARTNER);
		final Feld<FeldNameDSBD> feldBbnrKk = baustein.getFeld(FeldNameDSBD.BBNR_KK);
		final Feld<FeldNameDSBD> feldReserve2 = baustein.getFeld(FeldNameDSBD.RESERVE2);
		final Feld<FeldNameDSBD> feldMmka = baustein.getFeld(FeldNameDSBD.MM_ABWEICHENDE_ANSCHRIFT);
		final Feld<FeldNameDSBD> feldMmtn = baustein.getFeld(FeldNameDSBD.MM_TEILNAHME_PFLICHTEN);
		final Feld<FeldNameDSBD> feldReserve3 = baustein.getFeld(FeldNameDSBD.RESERVE3);

		if (feldKennung == null || feldBbnrAbsender == null || feldBbnrEmpfaenger == null || feldVersion == null
				|| feldDatumErstellung == null || feldFehlerkennzeichnung == null || feldFehlerAnzahl == null
				|| feldBbnrBetriebsstaette == null || feldReserve1 == null || feldBbnrAbrechnungsstelle == null
				|| feldAbgabegrund == null || feldWirtschaftsUnterklasse == null || feldNameBezeichnung1 == null
				|| feldPostleitzahlZustell == null || feldOrt == null || feldStrasse == null || feldHausNr == null
				|| feldPostleitzahlPostfach == null || feldRuhendKennzeichen == null || feldMeldendeStelle == null
				|| feldAnredeAnsprechpartner == null || feldEmailAnsprechpartner == null || feldBbnrKk == null
				|| feldReserve2 == null || feldMmka == null || feldMmtn == null || feldReserve3 == null) {
			throw new UngueltigePrueferDatenException("Pruefung fuer den Baustein " + baustein.getName()
					+ " kann nicht initalisiert werden, eines der Felder ist nicht vorhanden: " + baustein);
		}

		pruefList.add(new PruefungKennung(feldKennung, vfmm));
		pruefList.add(new PruefungBetriebsnummerAbsender(feldBbnrAbsender));
		pruefList.add(new PruefungBetriebsnummerEmpfaenger(feldBbnrEmpfaenger));
		pruefList.add(new PruefungVersionsnummer(feldVersion));
		pruefList.add(new PruefungDatumErstellung(feldDatumErstellung, verarbeitungsDatum));
		pruefList.add(new PruefungFehlerkennzeichnung(feldFehlerkennzeichnung));
		pruefList.add(new PruefungFehleranzahl(feldFehlerAnzahl, feldFehlerkennzeichnung));
		pruefList.add(new PruefungBetriebsnummerBetriebsstaette(feldBbnrBetriebsstaette));
		pruefList.add(new PruefungReserve1(feldReserve1));
		pruefList.add(new PruefungBetriebsnummerAbrechnungsstelle(feldBbnrAbrechnungsstelle));
		pruefList.add(new PruefungAbgabegrund(feldAbgabegrund, feldVerfahren));
		pruefList.add(new PruefungWirtschaftsUnterklasse(feldWirtschaftsUnterklasse, feldVerfahren, feldAbgabegrund));
		pruefList.add(new PruefungNameBezeichnung1(feldNameBezeichnung1));
		pruefList.add(new PruefungPostleitzahlZustell(feldPostleitzahlZustell));
		pruefList.add(new PruefungOrt(feldOrt));
		pruefList.add(new PruefungStrasse(feldStrasse));
		pruefList.add(new PruefungHausnummer(feldHausNr));
		pruefList.add(new PruefungPostleitzahlPostfach(feldPostleitzahlPostfach));
		pruefList.add(new PruefungRuhendKennzeichen(feldRuhendKennzeichen));
		pruefList.add(new PruefungMeldendeStelle(feldMeldendeStelle));
		pruefList.add(new PruefungAnredeAnsprechpartner(feldAnredeAnsprechpartner));
		pruefList.add(new PruefungEmailAnsprechpartner(feldEmailAnsprechpartner));
		pruefList.add(new PruefungBetriebsnummerKk(feldBbnrKk));
		pruefList.add(new PruefungReserve2(feldReserve2));
		pruefList.add(new PruefungMmka(feldMmka, datensatz));
		pruefList.add(new PruefungMmtn(feldMmtn, feldVerfahren, datensatz));
		pruefList.add(new PruefungReserve3(feldReserve3));

		return pruefList;
	}

}
