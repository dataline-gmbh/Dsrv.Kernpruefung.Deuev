package de.drv.dsrv.kernpruefung.aufruf;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Daten, welche an die Kernpruefung uebergeben werden. Bevor die Kernpruefung
 * gestartet wird, werden diese Eingaben validiert.
 */
public class EingabeDaten implements Iterable<DatensatzIterator> {

	private String operationscode;
	private String verarbeitungsDatum;
	private String zeitangaben;
	private String version;
	private List<String> datensatzListe;
	private Date verarbeitungsDatumDate;

	/**
	 * @return Operationscode.
	 */
	public String getOperationscode() {
		return operationscode;
	}

	/**
	 * @param operationscode
	 */
	public void setOperationscode(final String operationscode) {
		this.operationscode = operationscode;
	}

	/**
	 * @return Datensatz.
	 */
	public List<String> getDatensaetze() {
		return datensatzListe;
	}

	/**
	 * @param datensatz
	 */
	public void setDatensaetze(final List<String> datensatz) {
		this.datensatzListe = datensatz;
	}

	/**
	 * Gewaehrleistet, dass die Eingabedaten wie bisher gesetzt werden koennen
	 * (nur 1 Datensatz pro "Lieferung").
	 * 
	 * @param datensatz
	 *            Setzt den Datensatz
	 */
	public void setDatensatz(final String datensatz) {
		if (datensatzListe == null) {
			datensatzListe = new ArrayList<String>();
		} else {
			datensatzListe.clear();
		}
		datensatzListe.add(datensatz);
		setDatensaetze(datensatzListe);
	}

	/**
	 * @return Verarbeitungsdatum (JJJJMMTT).
	 */
	public String getVerarbeitungsDatum() {
		return verarbeitungsDatum;
	}

	/**
	 * @param datum
	 *            (JJJJMMTT).
	 */
	public void setVerarbeitungsDatum(final String datum) {
		this.verarbeitungsDatum = datum;
	}

	/**
	 * @return Zeitangaben (HHMMSSmmmmmm).
	 */
	public String getZeitangaben() {
		return zeitangaben;
	}

	/**
	 * @param zeitangaben
	 *            (HHMMSSmmmmmm).
	 */
	public void setZeitangaben(final String zeitangaben) {
		this.zeitangaben = zeitangaben;
	}

	/**
	 * @return Version.
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 */
	public void setVersion(final String version) {
		this.version = version;
	}

	/**
	 * @return VerarbeitungsDatum als <code>Date</code>.
	 */
	public Date getVerarbeitungsDatumAsDate() {
		return verarbeitungsDatumDate;
	}

	/**
	 * @param verarbDatum
	 */
	public void setVerarbeitungsDatumAsDate(final Date verarbDatum) {
		this.verarbeitungsDatumDate = verarbDatum;
	}

	@Override
	public String toString() {
		return "[Version=" + version + ", VFMM=" + operationscode + ", Verarbeitungsdatum=" + verarbeitungsDatum
				+ ", Zeitangaben=" + zeitangaben + "]";
	}

	@Override
	public Iterator<DatensatzIterator> iterator() {
		return new DatensatzIterator(this);
	}
}
