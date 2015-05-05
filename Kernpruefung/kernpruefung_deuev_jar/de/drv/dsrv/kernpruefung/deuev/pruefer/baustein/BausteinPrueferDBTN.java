package de.drv.dsrv.kernpruefung.deuev.pruefer.baustein;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractBausteinPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbtn.PruefungDatumEntscheidungU1;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbtn.PruefungEntscheidungIu;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbtn.PruefungEntscheidungSo;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbtn.PruefungGueltigkeitIu;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbtn.PruefungGueltigkeitSo;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbtn.PruefungGueltigkeitU1;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbtn.PruefungInsolvenzGeld;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbtn.PruefungReserve;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbtn.PruefungSofortmeldepflicht;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbtn.PruefungUmlagepflichtU1;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBTN;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBTN;

/**
 * Der Bausteinpruefer fuer den Baustein DBTN. Ruft alle Feldpruefungen auf.
 */
public class BausteinPrueferDBTN extends AbstractBausteinPruefer<FeldNameDBTN, FehlerNummerDBTN> {

	private final Date verarbeitungsDatum;

	/**
	 * Konstruktor. Nimmt alle Werte entgegen, die fuer die Feld-Pruefungen in
	 * dem Baustein benoetigt werden, und speichert diese ab.
	 * 
	 * @param baustein
	 *            Baustein DBTN
	 * @param verarbeitungsDatum
	 *            Verarbeitungsdatum aus den Eingabedaten
	 */
	public BausteinPrueferDBTN(final Baustein<FeldNameDBTN> baustein, final Date verarbeitungsDatum) {
		super(baustein);

		this.verarbeitungsDatum = verarbeitungsDatum;
	}

	@Override
	public List<FeldPruefung<FeldNameDBTN, FehlerNummerDBTN>> getPruefungen() throws UngueltigePrueferDatenException {
		final List<FeldPruefung<FeldNameDBTN, FehlerNummerDBTN>> pruefList = new ArrayList<FeldPruefung<FeldNameDBTN, FehlerNummerDBTN>>();
		final Baustein<FeldNameDBTN> baustein = getBaustein();

		final Feld<FeldNameDBTN> feldSofortmeldePflicht = baustein.getFeld(FeldNameDBTN.SOFORTMELDEPFLICHT);
		final Feld<FeldNameDBTN> feldEntscheidungSo = baustein.getFeld(FeldNameDBTN.ENTSCHEIDUNG_SO);
		final Feld<FeldNameDBTN> feldGueltigkeitSo = baustein.getFeld(FeldNameDBTN.GUELTIGKEIT_SO);
		final Feld<FeldNameDBTN> feldInsolvenzGeld = baustein.getFeld(FeldNameDBTN.INSOLVENZGELD);
		final Feld<FeldNameDBTN> feldEntscheidungIu = baustein.getFeld(FeldNameDBTN.ENTSCHEIDUNG_IU);
		final Feld<FeldNameDBTN> feldGueltigkeitIu = baustein.getFeld(FeldNameDBTN.GUELTIGKEIT_IU);
		final Feld<FeldNameDBTN> feldUmlagepflichtU1 = baustein.getFeld(FeldNameDBTN.UMLAGEPFLICHT_U1);
		final Feld<FeldNameDBTN> feldDatumEntscheidungU1 = baustein.getFeld(FeldNameDBTN.DATUM_ENTSCHEIDUNG_U1);
		final Feld<FeldNameDBTN> feldGueltigkeitU1 = baustein.getFeld(FeldNameDBTN.GUELTIGKEIT_U1);
		final Feld<FeldNameDBTN> feldReserve = baustein.getFeld(FeldNameDBTN.RESERVE);

		if (feldSofortmeldePflicht == null || feldEntscheidungSo == null || feldGueltigkeitSo == null
				|| feldInsolvenzGeld == null || feldEntscheidungIu == null || feldGueltigkeitIu == null
				|| feldUmlagepflichtU1 == null || feldDatumEntscheidungU1 == null || feldGueltigkeitU1 == null
				|| feldReserve == null) {
			throw new UngueltigePrueferDatenException("Pruefung fuer den Baustein " + baustein.getName()
					+ " kann nicht initalisiert werden, einer der Felder ist nicht vorhanden: " + baustein);
		}

		pruefList.add(new PruefungSofortmeldepflicht(feldSofortmeldePflicht));
		pruefList.add(new PruefungEntscheidungSo(feldEntscheidungSo, verarbeitungsDatum));
		pruefList.add(new PruefungGueltigkeitSo(feldGueltigkeitSo));
		pruefList.add(new PruefungInsolvenzGeld(feldInsolvenzGeld));
		pruefList.add(new PruefungEntscheidungIu(feldEntscheidungIu, verarbeitungsDatum));
		pruefList.add(new PruefungGueltigkeitIu(feldGueltigkeitIu));
		pruefList.add(new PruefungUmlagepflichtU1(feldUmlagepflichtU1));
		pruefList.add(new PruefungDatumEntscheidungU1(feldDatumEntscheidungU1, verarbeitungsDatum));
		pruefList.add(new PruefungGueltigkeitU1(feldGueltigkeitU1));
		pruefList.add(new PruefungReserve(feldReserve));

		return pruefList;
	}

}
