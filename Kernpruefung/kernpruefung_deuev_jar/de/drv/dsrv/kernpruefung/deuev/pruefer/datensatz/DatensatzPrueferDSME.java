package de.drv.dsrv.kernpruefung.deuev.pruefer.datensatz;

import java.util.Date;

import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.Datensatz;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractDatensatzPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBAN;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBEU;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBGB;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKS;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBNA;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBRG;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBSO;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBSV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBUV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBVR;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;
import de.drv.dsrv.kernpruefung.deuev.pruefer.baustein.BausteinPrueferDBAN;
import de.drv.dsrv.kernpruefung.deuev.pruefer.baustein.BausteinPrueferDBEU;
import de.drv.dsrv.kernpruefung.deuev.pruefer.baustein.BausteinPrueferDBGB;
import de.drv.dsrv.kernpruefung.deuev.pruefer.baustein.BausteinPrueferDBKS;
import de.drv.dsrv.kernpruefung.deuev.pruefer.baustein.BausteinPrueferDBKV;
import de.drv.dsrv.kernpruefung.deuev.pruefer.baustein.BausteinPrueferDBME;
import de.drv.dsrv.kernpruefung.deuev.pruefer.baustein.BausteinPrueferDBNA;
import de.drv.dsrv.kernpruefung.deuev.pruefer.baustein.BausteinPrueferDBRG;
import de.drv.dsrv.kernpruefung.deuev.pruefer.baustein.BausteinPrueferDBSO;
import de.drv.dsrv.kernpruefung.deuev.pruefer.baustein.BausteinPrueferDBSV;
import de.drv.dsrv.kernpruefung.deuev.pruefer.baustein.BausteinPrueferDBUV;
import de.drv.dsrv.kernpruefung.deuev.pruefer.baustein.BausteinPrueferDBVR;
import de.drv.dsrv.kernpruefung.deuev.pruefer.baustein.BausteinPrueferDSME;

/**
 * Der Bausteinpruefer fuer den Baustein DSME. Ruft alle Feldpruefungen auf.
 */
public class DatensatzPrueferDSME extends AbstractDatensatzPruefer {

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
	public DatensatzPrueferDSME(final Date verarbeitungsDatum, final String zeitangaben, final String vfmm) {
		super();

		this.verarbeitungsDatum = verarbeitungsDatum;
		this.zeitangaben = zeitangaben;
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
		final Baustein<FeldNameDSME> bausteinDSME = (Baustein<FeldNameDSME>) datensatz.getBaustein(BausteinName.DSME);
		final Baustein<FeldNameDBME> bausteinDBME = (Baustein<FeldNameDBME>) datensatz.getBaustein(BausteinName.DBME);
		final Baustein<FeldNameDBNA> bausteinDBNA = (Baustein<FeldNameDBNA>) datensatz.getBaustein(BausteinName.DBNA);
		final Baustein<FeldNameDBGB> bausteinDBGB = (Baustein<FeldNameDBGB>) datensatz.getBaustein(BausteinName.DBGB);
		final Baustein<FeldNameDBAN> bausteinDBAN = (Baustein<FeldNameDBAN>) datensatz.getBaustein(BausteinName.DBAN);
		final Baustein<FeldNameDBEU> bausteinDBEU = (Baustein<FeldNameDBEU>) datensatz.getBaustein(BausteinName.DBEU);
		final Baustein<FeldNameDBUV> bausteinDBUV = (Baustein<FeldNameDBUV>) datensatz.getBaustein(BausteinName.DBUV);
		final Baustein<FeldNameDBKS> bausteinDBKS = (Baustein<FeldNameDBKS>) datensatz.getBaustein(BausteinName.DBKS);
		final Baustein<FeldNameDBSV> bausteinDBSV = (Baustein<FeldNameDBSV>) datensatz.getBaustein(BausteinName.DBSV);
		final Baustein<FeldNameDBVR> bausteinDBVR = (Baustein<FeldNameDBVR>) datensatz.getBaustein(BausteinName.DBVR);
		final Baustein<FeldNameDBRG> bausteinDBRG = (Baustein<FeldNameDBRG>) datensatz.getBaustein(BausteinName.DBRG);
		final Baustein<FeldNameDBSO> bausteinDBSO = (Baustein<FeldNameDBSO>) datensatz.getBaustein(BausteinName.DBSO);
		final Baustein<FeldNameDBKV> bausteinDBKV = (Baustein<FeldNameDBKV>) datensatz.getBaustein(BausteinName.DBKV);

		if (bausteinDSME == null) {
			throw new UngueltigePrueferDatenException("Pruefung kann nicht durchgefuehrt werden "
					+ ", Baustein DSME nicht vorhanden ");
		} else {
			setPrueferSteuersatz(new BausteinPrueferDSME(bausteinDSME, bausteinDBME, bausteinDBSO, bausteinDBKV,
					bausteinDBVR, this.verarbeitungsDatum, this.zeitangaben, datensatz, this.vfmm));

			if (bausteinDBME != null) {
				this.addPruefungen(new BausteinPrueferDBME(bausteinDBME, bausteinDSME, bausteinDBGB,
						this.verarbeitungsDatum, datensatz, this.vfmm));
			}

			if (bausteinDBNA != null) {
				this.addPruefungen(new BausteinPrueferDBNA(bausteinDBNA, this.vfmm));
			}

			if (bausteinDBGB != null) {
				this.addPruefungen(new BausteinPrueferDBGB(bausteinDBGB, bausteinDSME, bausteinDBVR, bausteinDBNA,
						this.verarbeitungsDatum));
			}

			if (bausteinDBAN != null) {
				this.addPruefungen(new BausteinPrueferDBAN(bausteinDBAN, this.vfmm));
			}

			if (bausteinDBEU != null) {
				this.addPruefungen(new BausteinPrueferDBEU(bausteinDBEU));
			}

			if (bausteinDBUV != null) {
				this.addPruefungen(new BausteinPrueferDBUV(bausteinDBUV, bausteinDBME, bausteinDSME, this.vfmm));
			}

			if (bausteinDBKS != null) {
				this.addPruefungen(new BausteinPrueferDBKS(bausteinDBKS, bausteinDSME, bausteinDBME, this.vfmm));
			}

			if (bausteinDBSV != null) {
				this.addPruefungen(new BausteinPrueferDBSV(bausteinDBSV));
			}

			if (bausteinDBVR != null) {
				this.addPruefungen(new BausteinPrueferDBVR(bausteinDBVR, bausteinDSME, bausteinDBGB,
						this.verarbeitungsDatum, this.vfmm));
			}

			if (bausteinDBRG != null) {
				this.addPruefungen(new BausteinPrueferDBRG(bausteinDBRG));
			}

			if (bausteinDBSO != null) {
				this.addPruefungen(new BausteinPrueferDBSO(bausteinDBSO));
			}

			if (bausteinDBKV != null) {
				this.addPruefungen(new BausteinPrueferDBKV(bausteinDBKV, bausteinDSME, bausteinDBME));
			}
		}
	}
}
