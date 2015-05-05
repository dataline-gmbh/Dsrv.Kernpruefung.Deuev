package de.drv.dsrv.kernpruefung.deuev.pruefer.datensatz;

import java.util.Date;

import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Datensatz;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractDatensatzPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKA;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBTN;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSBD;
import de.drv.dsrv.kernpruefung.deuev.pruefer.baustein.BausteinPrueferDBKA;
import de.drv.dsrv.kernpruefung.deuev.pruefer.baustein.BausteinPrueferDBTN;
import de.drv.dsrv.kernpruefung.deuev.pruefer.baustein.BausteinPrueferDSBD;

/**
 * Der Bausteinpruefer fuer den Baustein DSBD. Ruft alle Feldpruefungen auf.
 */
public class DatensatzPrueferDSBD extends AbstractDatensatzPruefer {

	private final Date verarbeitungsDatum;
	private final String vfmm;

	/**
	 * Konstruktor.
	 * 
	 * @param verarbeitungsDatum
	 *            Verarbeitungsdatum aus den Eingabedaten.
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale).
	 */
	public DatensatzPrueferDSBD(final Date verarbeitungsDatum, final String vfmm) {
		this.verarbeitungsDatum = verarbeitungsDatum;
		this.vfmm = vfmm;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialisierePruefungen(final Datensatz datensatz) throws UngueltigePrueferDatenException {
		final Baustein<FeldNameDSBD> bausteinDSBD = (Baustein<FeldNameDSBD>) datensatz.getBaustein(BausteinName.DSBD);
		final Baustein<FeldNameDBKA> bausteinDBKA = (Baustein<FeldNameDBKA>) datensatz.getBaustein(BausteinName.DBKA);
		final Baustein<FeldNameDBTN> bausteinDBTN = (Baustein<FeldNameDBTN>) datensatz.getBaustein(BausteinName.DBTN);

		if (bausteinDSBD == null) {
			throw new UngueltigePrueferDatenException("Pruefung kann nicht durchgefuehrt werden "
					+ ", Baustein DSBD nicht vorhanden ");
		} else {
			setPrueferSteuersatz(new BausteinPrueferDSBD(bausteinDSBD, verarbeitungsDatum, datensatz, vfmm));

			if (bausteinDBKA != null) {
				addPruefungen(new BausteinPrueferDBKA(bausteinDBKA));
			}

			if (bausteinDBTN != null) {
				addPruefungen(new BausteinPrueferDBTN(bausteinDBTN, verarbeitungsDatum));
			}
		}
	}
}
