package de.drv.dsrv.kernpruefung.deuev.pruefer.datensatz;

import java.util.Date;

import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Datensatz;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractDatensatzPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSKO;
import de.drv.dsrv.kernpruefung.deuev.pruefer.baustein.BausteinPrueferDSKO;

/**
 * Der Bausteinpruefer fuer den Baustein DSKO. Ruft alle Feldpruefungen auf.
 */
public class DatensatzPrueferDSKO extends AbstractDatensatzPruefer {

	private final Date verarbeitungsDatum;
	private final String vfmm;

	/**
	 * Konstruktor.
	 * 
	 * @param verarbeitungsDatum
	 *            Verarbeitungsdatum
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public DatensatzPrueferDSKO(final Date verarbeitungsDatum, final String vfmm) {
		super();

		this.verarbeitungsDatum = verarbeitungsDatum;
		this.vfmm = vfmm;
	}

	/**
	 * Ruft die entsprechenden Bausteinpruefer auf.
	 * 
	 * @param datensatz
	 *            der zu pruefende Datensatz
	 * 
	 * @throws UngueltigePrueferDatenException
	 *             wird ausgeloest, wenn beim Initialisieren des Pruefers ein
	 *             Fehler augetreten ist, z.B. da erforderliche Bausteine nicht
	 *             vorhanden sind
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void initialisierePruefungen(final Datensatz datensatz) throws UngueltigePrueferDatenException {
		final Baustein<FeldNameDSKO> bausteinDSKO = (Baustein<FeldNameDSKO>) datensatz.getBaustein(BausteinName.DSKO);

		if (bausteinDSKO == null) {
			throw new UngueltigePrueferDatenException("Pruefung kann nicht durchgefuehrt werden "
					+ ", Baustein DSKO nicht vorhanden ");
		} else {
			setPrueferSteuersatz(new BausteinPrueferDSKO(bausteinDSKO, verarbeitungsDatum, vfmm));
		}
	}

}
