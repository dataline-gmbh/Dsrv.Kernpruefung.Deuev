package de.drv.dsrv.kernpruefung.deuev.pruefer.baustein;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractBausteinPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsko.PruefungAnredeAnsprechpartner;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsko.PruefungDatumErstellung;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsko.PruefungEmailEmpfaengerProtokolle;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsko.PruefungFehleranzahl;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsko.PruefungFehlerfreieVerarbeitungBestaetigung;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsko.PruefungFehlerkennzeichnung;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsko.PruefungKennung;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsko.PruefungKennzFehlrueck;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsko.PruefungNameAbsender1;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsko.PruefungNameAnsprechpartner;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsko.PruefungOrtBetrieb;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsko.PruefungPlzBetrieb;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsko.PruefungReserve;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsko.PruefungTelefonAnsprechpartner;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsko.PruefungVersionsnummer;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSKO;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSKO;

/**
 * Der Bausteinpruefer fuer den Baustein DSKO. Ruft alle Feldpruefungen auf.
 */
public class BausteinPrueferDSKO extends AbstractBausteinPruefer<FeldNameDSKO, FehlerNummerDSKO> {

	private final Date verarbeitungsDatum;
	private final String vfmm;

	/**
	 * Konstruktor. Nimmt alle Werte entgegen, die fuer die Feld-Pruefungen in
	 * dem Baustein benoetigt werden, und speichert diese ab.
	 * 
	 * @param baustein
	 *            Baustein DSKO
	 * @param verarbeitungsDatum
	 *            Verarbeitungsdatum aus den Aufrufparametern
	 * @param vfmm
	 *            OpCode (Verfahrensmerkamel)
	 */
	public BausteinPrueferDSKO(final Baustein<FeldNameDSKO> baustein, final Date verarbeitungsDatum, final String vfmm) {
		super(baustein);

		this.verarbeitungsDatum = verarbeitungsDatum;
		this.vfmm = vfmm;
	}

	@Override
	public List<FeldPruefung<FeldNameDSKO, FehlerNummerDSKO>> getPruefungen()
			throws UngueltigePrueferDatenException {
		final List<FeldPruefung<FeldNameDSKO, FehlerNummerDSKO>> pruefList = new ArrayList<FeldPruefung<FeldNameDSKO, FehlerNummerDSKO>>();
		final Baustein<FeldNameDSKO> baustein = getBaustein();

		final Feld<FeldNameDSKO> feldKennung = baustein.getFeld(FeldNameDSKO.KENNUNG);
		final Feld<FeldNameDSKO> feldVersion = baustein.getFeld(FeldNameDSKO.VERSIONS_NR);
		final Feld<FeldNameDSKO> feldDatumErstellung = baustein.getFeld(FeldNameDSKO.DATUM_ERSTELLUNG);
		final Feld<FeldNameDSKO> feldFehlerkennzeichnung = baustein.getFeld(FeldNameDSKO.FEHLER_KENNZ);
		final Feld<FeldNameDSKO> feldFehleranzahl = baustein.getFeld(FeldNameDSKO.FEHLER_ANZAHL);
		final Feld<FeldNameDSKO> feldName1Absender = baustein.getFeld(FeldNameDSKO.NAME1_ABSENDER);
		final Feld<FeldNameDSKO> feldPlzBetrieb = baustein.getFeld(FeldNameDSKO.PLZ_BETRIEB);
		final Feld<FeldNameDSKO> feldOrtBetrieb = baustein.getFeld(FeldNameDSKO.ORT_BETRIEB);
		final Feld<FeldNameDSKO> feldAnredeAnsprechpartner = baustein.getFeld(FeldNameDSKO.ANREDE_ANSPRECHPARTNER);
		final Feld<FeldNameDSKO> feldNameAnsprechpartner = baustein.getFeld(FeldNameDSKO.NAME_ANSPRECHPARTNER);
		final Feld<FeldNameDSKO> feldTelefonAnsprechpartner = baustein.getFeld(FeldNameDSKO.TELEFON_ANSPRECHPARTNER);
		final Feld<FeldNameDSKO> feldEmailProtokolle = baustein.getFeld(FeldNameDSKO.EMAIL_EMPFAENGER_PROTOKOLLE);
		final Feld<FeldNameDSKO> feldVerBestaetigung = baustein.getFeld(FeldNameDSKO.VER_BESTAETIGUNG);
		final Feld<FeldNameDSKO> feldKennzFehlrueck = baustein.getFeld(FeldNameDSKO.KENNZ_FEHLRUECK);
		final Feld<FeldNameDSKO> feldReserve = baustein.getFeld(FeldNameDSKO.RESERVE);

		if ((feldKennung == null) || (feldVersion == null) || (feldDatumErstellung == null)
				|| (feldFehlerkennzeichnung == null) || (feldFehleranzahl == null) || (feldName1Absender == null)
				|| (feldPlzBetrieb == null) || (feldOrtBetrieb == null) || (feldAnredeAnsprechpartner == null)
				|| (feldNameAnsprechpartner == null) || (feldTelefonAnsprechpartner == null) || (feldEmailProtokolle == null)
				|| (feldVerBestaetigung == null) || (feldKennzFehlrueck == null) || (feldReserve == null)) {
			throw new UngueltigePrueferDatenException("Pruefung fuer den Baustein " + baustein.getName()
					+ " kann nicht initalisiert werden, eines der Felder ist nicht vorhanden: " + baustein);
		}

		pruefList.add(new PruefungKennung(feldKennung, vfmm));
		pruefList.add(new PruefungVersionsnummer(feldVersion));
		pruefList.add(new PruefungDatumErstellung(feldDatumErstellung, verarbeitungsDatum));
		pruefList.add(new PruefungFehlerkennzeichnung(feldFehlerkennzeichnung));
		pruefList.add(new PruefungFehleranzahl(feldFehleranzahl, feldFehlerkennzeichnung));
		pruefList.add(new PruefungNameAbsender1(feldName1Absender));
		pruefList.add(new PruefungPlzBetrieb(feldPlzBetrieb));
		pruefList.add(new PruefungOrtBetrieb(feldOrtBetrieb));
		pruefList.add(new PruefungAnredeAnsprechpartner(feldAnredeAnsprechpartner));
		pruefList.add(new PruefungNameAnsprechpartner(feldNameAnsprechpartner));
		pruefList.add(new PruefungTelefonAnsprechpartner(feldTelefonAnsprechpartner));
		pruefList.add(new PruefungEmailEmpfaengerProtokolle(feldEmailProtokolle));
		pruefList.add(new PruefungFehlerfreieVerarbeitungBestaetigung(feldVerBestaetigung));
		pruefList.add(new PruefungKennzFehlrueck(feldKennzFehlrueck));
		pruefList.add(new PruefungReserve(feldReserve));

		return pruefList;
	}

}
