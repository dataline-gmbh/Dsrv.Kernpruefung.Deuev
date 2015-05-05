package de.drv.dsrv.kernpruefung.deuev.pruefer.datensatz;

import java.util.Date;

import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Datensatz;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractDatensatzPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBAZ;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBEZ;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;
import de.drv.dsrv.kernpruefung.deuev.pruefer.baustein.BausteinPrueferDBAZ;
import de.drv.dsrv.kernpruefung.deuev.pruefer.baustein.BausteinPrueferDBEZ;
import de.drv.dsrv.kernpruefung.deuev.pruefer.baustein.BausteinPrueferDSAE;

/**
 * Der Bausteinpruefer fuer den Baustein DSAE. Ruft alle Feldpruefungen auf.
 */
public class DatensatzPrueferDSAE extends AbstractDatensatzPruefer {

	private final Date verarbeitungsDatum;
	private final String zeitangaben;
	private final String vfmm;

	/**
	 * Konstruktor.
	 * 
	 * @param verarbeitungsDatum
	 *            Verarbeitungsdatum
	 * @param zeitangaben
	 *            Zeitangaben aus den Eingabedaten
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public DatensatzPrueferDSAE(final Date verarbeitungsDatum, final String zeitangaben, final String vfmm) {
		this.verarbeitungsDatum = verarbeitungsDatum;
		this.zeitangaben = zeitangaben;
		this.vfmm = vfmm;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialisierePruefungen(final Datensatz datensatz) throws UngueltigePrueferDatenException {
		
		final Baustein<FeldNameDSAE> bausteinDSAE = (Baustein<FeldNameDSAE>) datensatz.getBaustein(BausteinName.DSAE);
		final Baustein<FeldNameDBAZ> bausteinDBAZ = (Baustein<FeldNameDBAZ>) datensatz.getBaustein(BausteinName.DBAZ);
		final Baustein<FeldNameDBEZ> bausteinDBEZ = (Baustein<FeldNameDBEZ>) datensatz.getBaustein(BausteinName.DBEZ);

		if (bausteinDSAE == null) {
			throw new UngueltigePrueferDatenException("Pruefung kann nicht durchgefuehrt werden "
					+ ", Baustein DSAE nicht vorhanden ");
		} else {
			
			setPrueferSteuersatz(new BausteinPrueferDSAE(bausteinDSAE, verarbeitungsDatum, zeitangaben, datensatz, vfmm));

			if (bausteinDBAZ != null) {
				addPruefungen(new BausteinPrueferDBAZ(bausteinDBAZ, bausteinDSAE, verarbeitungsDatum, vfmm));
			}
			
			if (bausteinDBEZ != null) {
				final Feld<FeldNameDSAE> feldBbnrvu = bausteinDSAE.getFeld(FeldNameDSAE.BBNR_VU);
				addPruefungen(new BausteinPrueferDBEZ(bausteinDBEZ, feldBbnrvu, bausteinDSAE, verarbeitungsDatum, vfmm));
			}
		}
	}
}
