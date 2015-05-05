package de.drv.dsrv.kernpruefung.deuev.pruefungen;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;
import de.drv.dsrv.kernpruefung.basis.pruefungen.AbstractPruefung;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;

/**
 * Validiert die Beitragsgruppe nach Anlage 1. Sollte kein Datum angegeben sein,
 * dann sind grundsaetzlich die Ziffern aller moeglichen Datumsangaben erlaubt
 * (z.B. ist 2 bei der Rentenversicherung ohne Datum erlaubt). Ist ein Datum
 * angegeben wird dieses beruecksichtigt. Fehlernummer: KV142.
 */
public class PruefungIstBeitragsgruppe extends AbstractPruefung {
	
	private static final int INDEX_0 = 0;
	private static final int INDEX_1 = 1;
	private static final int INDEX_2 = 2;
	private static final int INDEX_3 = 3;
	private static final int INDEX_4 = 4;
	
	private static final int LAENGE_BEITRAGSGRUPPE = 4;
	
	private static final List<Integer> KRANK_NICHT_ERLAUBT = Arrays.asList(7, 8);
	private static final List<Integer> RENTE_ERLAUBT = Arrays.asList(0, 1, 3, 5, 9);
	private static final List<Integer> RENTE_ERLAUBT_BIS_2004 = Arrays.asList(2, 4, 6);
	private static final List<Integer> ARBEITSLOS_ERLAUBT = Arrays.asList(0, 1, 2, 9);
	private static final List<Integer> PFLEGE_ERLAUBT = Arrays.asList(0, 1, 2, 9);
	
	// @formatter:off
	private final Date dez2008 = new GregorianCalendar(2008, 11, 31).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private final Date dez2004 = new GregorianCalendar(2004, 11, 31).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	// @formatter:on
	
	private final Date zeitraum;
	
	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungIstBeitragsgruppe(final Feld<? extends FeldName> feld) {
		super(feld);
		this.zeitraum = null;
	}
	
	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 * @param zeitraum
	 *            Gibt an in welchem Zeitraum die Meldung war
	 */
	public PruefungIstBeitragsgruppe(final Feld<? extends FeldName> feld, final Date zeitraum) {
		super(feld);
		this.zeitraum = zeitraum;
	}
	
	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = true;
		
		if (this.getFeld().getTrimmedValue().length() != LAENGE_BEITRAGSGRUPPE) {
			erfolgreich = false;
		}
		
		else {
			try {
				
				// Krankenversicherung
				final int ersteZiffer = Integer.parseInt(this.getFeld().getTrimmedValue().substring(INDEX_0, INDEX_1));
				if (ersteZiffer == 2 && (this.zeitraum != null && this.zeitraum.after(this.dez2008))) {
					erfolgreich = false;
				} else if (KRANK_NICHT_ERLAUBT.contains(ersteZiffer)) {
					erfolgreich = false;
				}
				
				// Rentenversicherung
				final int zweiteZiffer = Integer.parseInt(this.getFeld().getTrimmedValue().substring(INDEX_1, INDEX_2));
				if (RENTE_ERLAUBT_BIS_2004.contains(zweiteZiffer)) {
					if ((this.zeitraum != null && this.zeitraum.after(this.dez2004))) {
						erfolgreich = false;
					}
				} else if (!RENTE_ERLAUBT.contains(zweiteZiffer)) {
					erfolgreich = false;
				}
				
				// Arbeitslosenversicherung
				final int dritteZiffer = Integer.parseInt(this.getFeld().getTrimmedValue().substring(INDEX_2, INDEX_3));
				if (!ARBEITSLOS_ERLAUBT.contains(dritteZiffer)) {
					erfolgreich = false;
				}
				
				// Pflegeversicherung
				final int vierteZiffer = Integer.parseInt(this.getFeld().getTrimmedValue().substring(INDEX_3, INDEX_4));
				if (!PFLEGE_ERLAUBT.contains(vierteZiffer)) {
					erfolgreich = false;
				}
			} catch (final NumberFormatException e) {
				erfolgreich = false;
			}
		}
		
		return erfolgreich;
	}
}
