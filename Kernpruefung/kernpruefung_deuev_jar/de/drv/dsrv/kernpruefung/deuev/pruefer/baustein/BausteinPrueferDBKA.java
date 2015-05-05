package de.drv.dsrv.kernpruefung.deuev.pruefer.baustein;

import java.util.ArrayList;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractBausteinPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbka.PruefungHausnummer;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbka.PruefungNameBezeichnung1;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbka.PruefungOrt;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbka.PruefungPostleitzahlPostfach;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbka.PruefungPostleitzahlZustell;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbka.PruefungReserve;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbka.PruefungStrasse;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKA;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKA;

/**
 * Der Bausteinpruefer fuer den Baustein DBKA. Ruft alle Feldpruefungen auf.
 */
public class BausteinPrueferDBKA extends AbstractBausteinPruefer<FeldNameDBKA, FehlerNummerDBKA> {

	/**
	 * Konstruktor. Nimmt alle Werte entgegen, die fuer die Feld-Pruefungen in
	 * dem Baustein benoetigt werden, und speichert diese ab.
	 * 
	 * @param baustein
	 *            Baustein DBKA
	 */
	public BausteinPrueferDBKA(final Baustein<FeldNameDBKA> baustein) {
		super(baustein);
	}

	@Override
	public List<FeldPruefung<FeldNameDBKA, FehlerNummerDBKA>> getPruefungen() throws UngueltigePrueferDatenException {
		final List<FeldPruefung<FeldNameDBKA, FehlerNummerDBKA>> pruefList = new ArrayList<FeldPruefung<FeldNameDBKA, FehlerNummerDBKA>>();
		final Baustein<FeldNameDBKA> baustein = getBaustein();

		final Feld<FeldNameDBKA> feldNameBezeichnung1 = baustein.getFeld(FeldNameDBKA.NAME_BEZEICHNUNG1);
		final Feld<FeldNameDBKA> feldPostleitzahlZustell = baustein.getFeld(FeldNameDBKA.POSTLEITZAHL_ZUSTELL);
		final Feld<FeldNameDBKA> feldOrt = baustein.getFeld(FeldNameDBKA.ORT);
		final Feld<FeldNameDBKA> feldStrasse = baustein.getFeld(FeldNameDBKA.STRASSE);
		final Feld<FeldNameDBKA> feldHausNr = baustein.getFeld(FeldNameDBKA.HAUSNUMMER);
		final Feld<FeldNameDBKA> feldPostleitzahlPostfach = baustein.getFeld(FeldNameDBKA.POSTLEITZAHL_POSTFACH);
		final Feld<FeldNameDBKA> feldReserve = baustein.getFeld(FeldNameDBKA.RESERVE);

		if (feldNameBezeichnung1 == null || feldPostleitzahlZustell == null || feldOrt == null || feldStrasse == null
				|| feldHausNr == null || feldPostleitzahlPostfach == null) {
			throw new UngueltigePrueferDatenException("Pruefung fuer den Baustein " + baustein.getName()
					+ " kann nicht initalisiert werden, eines der Felder ist nicht vorhanden: " + baustein);
		}

		pruefList.add(new PruefungNameBezeichnung1(feldNameBezeichnung1));
		pruefList.add(new PruefungPostleitzahlZustell(feldPostleitzahlZustell));
		pruefList.add(new PruefungOrt(feldOrt));
		pruefList.add(new PruefungStrasse(feldStrasse));
		pruefList.add(new PruefungHausnummer(feldHausNr));
		pruefList.add(new PruefungPostleitzahlPostfach(feldPostleitzahlPostfach));
		pruefList.add(new PruefungReserve(feldReserve));

		return pruefList;
	}

}
