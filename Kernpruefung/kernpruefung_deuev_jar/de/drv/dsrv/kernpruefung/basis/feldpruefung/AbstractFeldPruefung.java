package de.drv.dsrv.kernpruefung.basis.feldpruefung;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.FehlerArt;
import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;
import de.drv.dsrv.kernpruefung.basis.pruefungen.AbstractPruefung;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;

/**
 * 
 * Definiert Methoden einer abstrakten Pruefung fuer ein Feld.
 * 
 * @param <T>
 *            Typ des Feldes, vorgegeben durch die Namen der Felder eines
 *            Bausteins (vom {@link FeldName})
 * @param <E>
 *            Typ der Fehlernummern, vorgegeben durch den Nummernkreis (von
 *            {@link FehlerNummer}
 */
public abstract class AbstractFeldPruefung<T extends FeldName, E extends FehlerNummer> implements FeldPruefung<T, E> {

	protected static final Logger LOGGER = Logger.getLogger(AbstractFeldPruefung.class.getName());

	private final transient List<PruefungBean<E>> pruefungListe;
	private final transient List<PruefungBean<E>> pruefungListeFeldUebergreifend;
	private final transient List<PruefungBean<E>> pruefungListeBausteinUebergreifend;

	private final transient Feld<T> feld;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            das zu pruefende Feld
	 */
	public AbstractFeldPruefung(final Feld<T> feld) {
		this.feld = feld;
		this.pruefungListe = new ArrayList<PruefungBean<E>>();
		this.pruefungListeFeldUebergreifend = new ArrayList<PruefungBean<E>>();
		this.pruefungListeBausteinUebergreifend = new ArrayList<PruefungBean<E>>();
	}

	/**
	 * Fuegt die einzelnen Pruefungen fuer das zu pruefende Feld hinzu. Diese
	 * Pruefungen werden dann nach einander in der Reihenfolge ausgefuehrt, wie
	 * sie in die Liste eingefuegt wurden. Die Pruefungen sind Basispruefungen,
	 * die nur das zu pruefende Feld benoetigt und nicht von anderen Feldern
	 * abhaengt.
	 * 
	 * @param pruefung
	 *            Pruefung, die in der Liste der Pruefungen eingefuegt werden
	 *            soll
	 * @param fehler
	 *            Fehler, falls die eingefuegte Pruefung fehlschlaegt
	 */
	protected final void addPruefung(final AbstractPruefung pruefung, final Fehler<E> fehler) {
		final PruefungBean<E> pruefungBean = new PruefungBean<E>(pruefung, fehler);
		pruefungListe.add(pruefungBean);
	}

	/**
	 * Fuegt die einzelnen Pruefungen fuer das zu pruefende Feld hinzu. Diese
	 * Pruefungen werden dann nach einander in der Reihenfolge ausgefuehrt, wie
	 * sie in die Liste eingefuegt wurden. Die Pruefungen sind
	 * felduebergreifende Pruefungen, die auf ein weiteres Feld aus demselben
	 * Baustein zugreift.
	 * 
	 * @param pruefung
	 *            Pruefung, die in der Liste der Pruefungen eingefuegt werden
	 *            soll
	 * @param fehler
	 *            Fehler, falls die eingefuegte Pruefung fehlschlaegt
	 */
	protected final void addPruefungFeldUebergreifend(final AbstractPruefung pruefung, final Fehler<E> fehler) {
		final PruefungBean<E> pruefungBean = new PruefungBean<E>(pruefung, fehler);
		pruefungListeFeldUebergreifend.add(pruefungBean);
	}

	/**
	 * Fuegt die einzelnen Pruefungen fuer das zu pruefende Feld hinzu. Diese
	 * Pruefungen werden dann nach einander in der Reihenfolge ausgefuehrt, wie
	 * sie in die Liste eingefuegt wurden. Die Pruefungen sind
	 * bausteinuebergreifende Pruefungen, die auf ein weiteres Feld aus einem
	 * anderen Baustein zugreift.
	 * 
	 * @param pruefung
	 *            Pruefung, die in der Liste der Pruefungen eingefuegt werden
	 *            soll
	 * @param fehler
	 *            Fehler, falls die eingefuegte Pruefung fehlschlaegt
	 */
	protected final void addPruefungBausteinUebergreifend(final AbstractPruefung pruefung, final Fehler<E> fehler) {
		final PruefungBean<E> pruefungBean = new PruefungBean<E>(pruefung, fehler);
		pruefungListeBausteinUebergreifend.add(pruefungBean);
	}

	/**
	 * Fuehrt die Pruefungen durch, die in die Liste mit der Methode
	 * <code>addPruefung()</code> hinzugefuegt wurden. Sollte ein Fehler
	 * auftreten, wird das {@link Feld} markiert.
	 * 
	 * @param mitAbbruch
	 *            bei <code>true</code> werden die Pruefungen nach dem ersten
	 *            Fehler abgebrochen, bei <code>false</code> werden alle
	 *            Pruefungen durchgefuehrt
	 * @return Liste mit den Fehlern der einzelnen Pruefungen
	 * @throws UngueltigeDatenException
	 */
	protected List<Fehler<E>> pruefeListe(final boolean mitAbbruch, final List<PruefungBean<E>> pruefungListe)
			throws UngueltigeDatenException {

		// Ergebnis der Pruefung
		final List<Fehler<E>> fehlerListe = new ArrayList<Fehler<E>>();
		for (final PruefungBean<E> pruefung : pruefungListe) {
			if (!pruefung.getPruefung().pruefe()) {

				final Fehler<E> fehler = pruefung.getFehler();
				if (fehler != null) {
					fehlerListe.add(fehler);
				}

				// Sollen die Pruefungen abgebrochen werden?
				// Pruefung nur abbrechen, wenn es sich um einen Fehler handelt
				// (bei Hinweisen wird noch weiter geprueft)
				if (mitAbbruch && fehler.getFehlerArt() == FehlerArt.FEHLER) {
					getFeld().setFehlerfrei(false);
					break;
				}
			}
		}
		return fehlerListe;
	}

	protected List<Fehler<E>> pruefeListe(final boolean mitAbbruch) throws UngueltigeDatenException {
		return pruefeListe(mitAbbruch, pruefungListe);
	}

	/**
	 * Fuehrt alle Pruefungen in der Liste (hinzugefuegt durch die
	 * {@link AbstractFeldPruefung#addPruefung(AbstractPruefung, Fehler)} aus
	 * und bricht beim ersten Fehler ab.
	 * 
	 * @return Datenobjekt mit dem Ergebnis der Pruefung
	 * @throws UngueltigeDatenException
	 */
	private FeldPruefungErgebnis<E> pruefeMitAbbruch(final List<PruefungBean<E>> pruefungListe)
			throws UngueltigeDatenException {
		// Ergebnis der Pruefung
		final FeldPruefungErgebnis<E> pruefungErgebnis = new FeldPruefungErgebnis<E>();

		// Weitere Pruefungen ausfuehren
		final List<Fehler<E>> fehlerListe = pruefeListe(true, pruefungListe);
		pruefungErgebnis.setFehlerListe(fehlerListe);

		return pruefungErgebnis;
	}

	/**
	 * Fuehrt alle Pruefungen in der Liste (hinzugefuegt durch die
	 * {@link AbstractFeldPruefung#addPruefung(AbstractPruefung, Fehler)} aus
	 * und bricht beim ersten Fehler ab. Es wird die Pruefungliste fuer die
	 * Basispruefungen verwendet.
	 * 
	 * @return Datenobjekt mit dem Ergebnis der Pruefung
	 * @throws UngueltigeDatenException
	 */
	protected FeldPruefungErgebnis<E> pruefeMitAbbruch() throws UngueltigeDatenException {
		return pruefeMitAbbruch(pruefungListe);
	}

	@Override
	public FeldPruefungErgebnis<E> pruefe() throws UngueltigeDatenException {
		return pruefeMitAbbruch(pruefungListe);
	}

	@Override
	public FeldPruefungErgebnis<E> pruefeFeldUebergreifend() throws UngueltigeDatenException {
		FeldPruefungErgebnis<E> pruefungErgebnis = new FeldPruefungErgebnis<E>();

		if (feld != null && feld.isFehlerfrei()) {
			pruefungErgebnis = pruefeMitAbbruch(pruefungListeFeldUebergreifend);
		} else if (feld != null) {
			if (LOGGER.isLoggable(Level.INFO)) {
				LOGGER.info("Die felduebergreifenden Pruefungen fuer das Feld " + feld.getName()
						+ " wurden nicht ausgefuehrt, da das Feld nicht fehlerfrei ist.");
			}
		} else {
			if (LOGGER.isLoggable(Level.INFO)) {
				LOGGER.info("Die felduebergreifenden Pruefungen"
						+ " wurden nicht ausgefuehrt, da das Feld nicht fehlerfrei ist.");
			}
		}

		return pruefungErgebnis;
	}

	@Override
	public FeldPruefungErgebnis<E> pruefeBausteinUebergreifend() throws UngueltigeDatenException {
		FeldPruefungErgebnis<E> pruefungErgebnis = new FeldPruefungErgebnis<E>();

		if (feld != null && feld.isFehlerfrei()) {
			pruefungErgebnis = pruefeMitAbbruch(pruefungListeBausteinUebergreifend);
		} else if (feld != null) {
			if (LOGGER.isLoggable(Level.INFO)) {
				LOGGER.info("Die bausteinuebergreifenden Pruefungen fuer das Feld " + feld.getName()
						+ " wurden nicht ausgefuehrt, da das Feld nicht fehlerfrei ist.");
			}
		} else {
			if (LOGGER.isLoggable(Level.INFO)) {
				LOGGER.info("Die bausteinuebergreifenden Pruefungen"
						+ " wurden nicht ausgefuehrt, da das Feld nicht fehlerfrei ist.");
			}
		}

		return pruefungErgebnis;
	}

	@Override
	public Feld<T> getFeld() {
		return feld;
	}

	/**
	 * Initialisiert alle feldinternen Pruefungen. <br>
	 * Per Default werden keine feldinternen Pruefungen initialisiert, sodass
	 * diese bei Nichtvorhandensein nicht ueberschrieben werden muessen.
	 */
	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {

	}

	/**
	 * Initialisiert alle felduebergreifenden Pruefungen. <br>
	 * Per Default werden keine felduebergreifenden Pruefungen initialisiert,
	 * sodass diese bei Nichtvorhandensein nicht ueberschrieben werden muessen.
	 */
	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {

	}

	/**
	 * Initialisiert alle bausteinuebergreifenden Pruefungen. <br>
	 * Per Default werden keine bausteinuebergreifenden Pruefungen
	 * initialisiert, sodass diese bei Nichtvorhandensein nicht ueberschrieben
	 * werden muessen.
	 */
	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {

	}

	/**
	 * Validiert, ob die uebergebenen <code>felder</code> fehlerfrei und damit
	 * pruefbar sind.
	 * 
	 * @param felder
	 * @return <code>true</code>, wenn alle Felder fehlerfrei sind,
	 *         <code>false</code> sonst.
	 */
	// SuppressWarnings sind hier absichtlich, da ansonsten bei jeder Klasse,
	// die diese Methode aufruft, SuppressWarnings kommen muesste. Siehe mehr
	// u.a. hier:
	// http://stackoverflow.com/questions/3096708/java-generics-and-varargs
	protected boolean isPruefbar(final E fehlernummer, @SuppressWarnings("rawtypes") final Feld... felder) {
		final boolean erfolgreich = isPruefbar(felder);

		if (!erfolgreich) {
			final StringBuilder builder = new StringBuilder();
			builder.append("Die Fehlernummer ");
			builder.append(fehlernummer);

			if (feld != null) {
				builder.append(" aus dem Feld ");
				builder.append(feld.getName());
			}

			builder.append(" wurde nicht geprueft, da eines der externen Felder fehlerhaft ist.");

			if (LOGGER.isLoggable(Level.INFO)) {
				LOGGER.info(builder.toString());
			}
		}

		return erfolgreich;
	}

	protected boolean isPruefbar(final List<E> fehlernummern, @SuppressWarnings("rawtypes") final Feld... felder) {
		final boolean erfolgreich = isPruefbar(felder);

		if (!erfolgreich) {
			final StringBuilder builder = new StringBuilder();
			builder.append("Die Fehlernummern ");

			for (FehlerNummer fehlernummer : fehlernummern) {
				builder.append(fehlernummer);
				builder.append(", ");
			}
			// letzte , loeschen
			builder.delete(builder.length() - 2, builder.length());

			if (feld != null) {
				builder.append(" aus dem Feld ");
				builder.append(feld.getName());
			}
			builder.append(" wurde nicht geprueft, da eines der externen Felder fehlerhaft ist.");

			if (LOGGER.isLoggable(Level.INFO)) {
				LOGGER.info(builder.toString());
			}
		}

		return erfolgreich;
	}


	private boolean isPruefbar(@SuppressWarnings("rawtypes") final Feld... felder) {
		boolean erfolgreich = true;

		if (felder == null) {
			erfolgreich = false;
		} else {
			for (final Feld<T> feld : felder) {
				if (feld == null) {
					erfolgreich = false;
					break;
				} else {
					erfolgreich = erfolgreich && feld.isFehlerfrei();
				}
			}
		}

		return erfolgreich;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((feld == null) ? 0 : feld.hashCode());
		result = prime * result + ((pruefungListe == null) ? 0 : pruefungListe.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final AbstractFeldPruefung<?, ?> other = (AbstractFeldPruefung<?, ?>) obj;
		if (feld == null) {
			if (other.feld != null) {
				return false;
			}
		} else if (!feld.equals(other.feld)) {
			return false;
		}
		if (pruefungListe == null) {
			if (other.pruefungListe != null) {
				return false;
			}
		} else if (!pruefungListe.equals(other.pruefungListe)) {
			return false;
		}
		return true;
	}
}
