package de.drv.dsrv.kernpruefung.basis.pruefer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefung;
import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefungErgebnis;
import de.drv.dsrv.kernpruefung.basis.model.Datensatz;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;

/**
 * Die abstrakte Klasse fuer den Datensatzpruefer. Neben ein paar
 * vorimplementierten Pruefungen werden hier auch individuelle definiert.
 */
public abstract class AbstractDatensatzPruefer implements DatensatzPruefer {

	protected static final Logger LOGGER = Logger.getLogger(AbstractDatensatzPruefer.class.getName());
	private final transient List<FeldPruefung<? extends FeldName, ? extends FehlerNummer>> pruefungsListe;
	private transient BausteinPruefer<? extends FeldName, ? extends FehlerNummer> prueferSteuersatz;

	/**
	 * Konstruktor.
	 */
	public AbstractDatensatzPruefer() {
		pruefungsListe = new ArrayList<FeldPruefung<? extends FeldName, ? extends FehlerNummer>>();
	}

	/**
	 * Fuegt der Liste der Pruefungen fuer einen Datensatz alle beim
	 * Baustein-Pruefer registrierten Pruefungen hinzu.
	 * 
	 * @param pruefer
	 *            Baustein-Pruefer mit den Pruefungen fuer die zugehoerigen
	 *            Felder
	 * @throws UngueltigePrueferDatenException
	 *             wird ausgeloest, falls die Pruefungen des Baustein-Pruefers
	 *             nicht initialisiert werden koennen
	 */
	protected void addPruefungen(final BausteinPruefer<? extends FeldName, ? extends FehlerNummer> pruefer)
			throws UngueltigePrueferDatenException {
		pruefungsListe.addAll(pruefer.getPruefungen());
	}

	/**
	 * Ruft alle Feldpruefungen auf und ermittelt die Fehlerliste. Dabei gibt es
	 * die 3 "Level": feldinterne, felduebergreifende und bausteinuebergreifende
	 * Pruefungen.
	 * 
	 * @return Liste an Fehlern
	 * @throws UngueltigeDatenException
	 * @throws UngueltigePrueferDatenException
	 */
	@Override
	public List<Fehler<? extends FehlerNummer>> pruefen() throws UngueltigeDatenException,
			UngueltigePrueferDatenException {
		final List<Fehler<? extends FehlerNummer>> fehlerListe = new ArrayList<Fehler<? extends FehlerNummer>>();

		if (LOGGER.isLoggable(Level.INFO)) {
			LOGGER.info("Fuehre Pruefungen des Steuerbausteins aus.");
		}

		// Pruefungen des Steuerteils
		if (prueferSteuersatz != null) {
			final List<FeldPruefung<? extends FeldName, ? extends FehlerNummer>> pruefListDsme = new LinkedList<FeldPruefung<? extends FeldName, ? extends FehlerNummer>>();
			pruefListDsme.addAll(prueferSteuersatz.getPruefungen());

			// Pruefungen Steuersatz
			for (final FeldPruefung<? extends FeldName, ? extends FehlerNummer> pruefung : pruefListDsme) {
				pruefung.initialisierePruefungen();
				final FeldPruefungErgebnis<? extends FehlerNummer> pruefungErgebnis = pruefung.pruefe();
				if (!pruefungErgebnis.isErfolgreichInklHinweis()) {
					fehlerListe.addAll(pruefungErgebnis.getFehlerListe());
				}
			}
		}

		// Alle anderen Pruefungen starten
		if (LOGGER.isLoggable(Level.INFO)) {
			LOGGER.info("Fuehre Basispruefungen aus.");
		}

		// Basispruefungen
		for (final FeldPruefung<? extends FeldName, ? extends FehlerNummer> pruefung : pruefungsListe) {
			pruefung.initialisierePruefungen();
			final FeldPruefungErgebnis<? extends FehlerNummer> pruefungErgebnis = pruefung.pruefe();
			if (!pruefungErgebnis.isErfolgreichInklHinweis()) {
				fehlerListe.addAll(pruefungErgebnis.getFehlerListe());
			}
		}

		if (LOGGER.isLoggable(Level.INFO)) {
			LOGGER.info("Fuehre felduebergreifende Pruefungen aus.");
		}

		// Felduebergreifende Pruefungen
		for (final FeldPruefung<? extends FeldName, ? extends FehlerNummer> pruefung : pruefungsListe) {
			if (pruefung.getFeld().isFehlerfrei()) {
				pruefung.initialisiereFeldUebergreifendePruefungen();
				final FeldPruefungErgebnis<? extends FehlerNummer> pruefungErgebnis = pruefung
						.pruefeFeldUebergreifend();
				if (!pruefungErgebnis.isErfolgreichInklHinweis()) {
					fehlerListe.addAll(pruefungErgebnis.getFehlerListe());
				}
			}
		}

		if (LOGGER.isLoggable(Level.INFO)) {
			LOGGER.info("Fuehre bausteinuebergreifende Pruefungen aus.");
		}

		// Bausteinuebergreifende Pruefungen
		for (final FeldPruefung<? extends FeldName, ? extends FehlerNummer> pruefung : pruefungsListe) {
			if (pruefung.getFeld().isFehlerfrei()) {
				pruefung.initialisiereBausteinUebergreifendePruefungen();
				final FeldPruefungErgebnis<? extends FehlerNummer> pruefungErgebnis = pruefung
						.pruefeBausteinUebergreifend();
				if (!pruefungErgebnis.isErfolgreichInklHinweis()) {
					fehlerListe.addAll(pruefungErgebnis.getFehlerListe());
				}
			}
		}

		return fehlerListe;
	}

	/**
	 * Liefert die vollstaendige Liste aller Pruefungen, die fuer einen
	 * Datensatz registriert sind.
	 * 
	 * @return vollstaendige Liste aller Pruefungen fuer den Datensatz
	 */
	public List<FeldPruefung<? extends FeldName, ? extends FehlerNummer>> getPruefungen() {
		return pruefungsListe;
	}

	/**
	 * Alle Pruefungen werden initialisiert.
	 * 
	 * @param datensatz
	 * @throws UngueltigePrueferDatenException
	 */
	@Override
	public abstract void initialisierePruefungen(Datensatz datensatz) throws UngueltigePrueferDatenException;

	/**
	 * Setzt den Pruefer des Steuerteils (DSME, DSAE, DSKO, DSBD...), sodass
	 * diese Pruefungen vor den anderen ausgefuehrt werden koennen.
	 * 
	 * @param prueferSteuersatz
	 */
	protected void setPrueferSteuersatz(BausteinPruefer<? extends FeldName, ? extends FehlerNummer> prueferSteuersatz) {
		this.prueferSteuersatz = prueferSteuersatz;
	}
}
