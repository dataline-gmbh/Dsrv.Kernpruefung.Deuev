package de.drv.dsrv.kernpruefung.deuev.aufruf;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.drv.dsrv.kernpruefung.aufruf.DatensatzIterator;
import de.drv.dsrv.kernpruefung.aufruf.DatensatzPruefungErgebnis;
import de.drv.dsrv.kernpruefung.aufruf.DatensatzkennungUngueltigException;
import de.drv.dsrv.kernpruefung.basis.aufruf.Metadaten;
import de.drv.dsrv.kernpruefung.basis.fehler.FehlerTextNichtVorhandenException;
import de.drv.dsrv.kernpruefung.basis.model.Datensatz;
import de.drv.dsrv.kernpruefung.basis.model.DatensatzName;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.parser.DatensatzAufbauException;
import de.drv.dsrv.kernpruefung.basis.parser.DatensatzParser;
import de.drv.dsrv.kernpruefung.basis.pruefer.DatensatzPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.steuerung.AbstractKernpruefung;
import de.drv.dsrv.kernpruefung.basis.wertelisten.WertelistenException;
import de.drv.dsrv.kernpruefung.deuev.fehler.FehlerBausteinGenerator;
import de.drv.dsrv.kernpruefung.deuev.model.ReturnCodesDeuev;
import de.drv.dsrv.kernpruefung.deuev.parser.datensatz.DatensatzParserDSAE;
import de.drv.dsrv.kernpruefung.deuev.parser.datensatz.DatensatzParserDSBD;
import de.drv.dsrv.kernpruefung.deuev.parser.datensatz.DatensatzParserDSKO;
import de.drv.dsrv.kernpruefung.deuev.parser.datensatz.DatensatzParserDSME;
import de.drv.dsrv.kernpruefung.deuev.parser.datensatz.DatensatzParserVNIF;
import de.drv.dsrv.kernpruefung.deuev.pruefer.datensatz.DatensatzPrueferDSAE;
import de.drv.dsrv.kernpruefung.deuev.pruefer.datensatz.DatensatzPrueferDSBD;
import de.drv.dsrv.kernpruefung.deuev.pruefer.datensatz.DatensatzPrueferDSKO;
import de.drv.dsrv.kernpruefung.deuev.pruefer.datensatz.DatensatzPrueferDSME;
import de.drv.dsrv.kernpruefung.deuev.pruefer.datensatz.DatensatzPrueferVNIF;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.MapTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Startet die Kernpruefung, validiert die Aufrufparameter, ruft fuer einen
 * Datensatz den entsprechenden Parser und Pruefer auf und setzt die Antwort
 * zusammen.
 */
public class KernpruefungDeuev extends AbstractKernpruefung {

	private static final Logger LOGGER = Logger.getLogger(KernpruefungDeuev.class.getName());

	private static final int DATENSATZKENNUNG_LAENGE = 4;

	private static final int INDEX_VERFAHRENSMERKMAL_START = 4;
	private static final int INDEX_VERFAHRENSMERKMAL_ENDE = 9;

	private static final List<String> VF_DSKO = Arrays.asList("DEUEV");
	private static final List<String> VF_DSBD = Arrays.asList("BTRAG", "BTRKS", "BTRKV", "BTRRV");
	private static final List<String> VF_DSME = Arrays.asList("DEUEV", "KVNR ", "RVSNR");
	private static final List<String> VF_DSAE = Arrays.asList("DEUEV");
	private static final List<String> VF_VNIF = Arrays.asList("VERNR");

	private DatensatzName datensatzKennung;

	/**
	 * Konstruktor.
	 */
	public KernpruefungDeuev() {
		super();
	}

	/**
	 * Konstruktor.
	 * 
	 * @param logger
	 *            bietet die Moeglichkeit die Log-Ausgaben auf einen Stream zu
	 *            senden (Einstellungen siehe logging.properties
	 *            "de.drv.dsrv.kernpruefung.basis.logging.DeuevStreamHandler")
	 */
	public KernpruefungDeuev(final OutputStream logger) {
		super(logger);
	}

	/**
	 * Fuehrt die Fehlerbehandlung der Kernpruefung aus. Wann die einzelnen
	 * Exceptions auftreten ist in der Funktion
	 * {@link #start(DatensatzPruefungErgebnis, DatensatzIterator) start}
	 * beschrieben.
	 */
	@Override
	protected void startePruefung(final DatensatzPruefungErgebnis pruefungErgebnis, final DatensatzIterator iterator) {

		try {
			// vor dem Start alle Wertelisten und Properties laden.
			WertelistenVerwaltungDeuev.getInstance().ladeListen();

			start(pruefungErgebnis, iterator);
		}
		// DatensatzAufbauExceptionDEUEV wird ausgeloest, wenn eine Kennung
		// nicht vorhanden oder am falschen Platz ist.
		catch (final DatensatzAufbauException e) {
			if (e.getFehler() == null) {
				registerError(pruefungErgebnis, e, ReturnCodesDeuev.TECHNISCHER_FEHLER);
			} else {
				final List<Fehler<?>> fehlerListe = new ArrayList<Fehler<?>>();
				fehlerListe.add(e.getFehler());

				final FehlerBausteinGenerator fenaGenerator = new FehlerBausteinGenerator(iterator.getDatensatz(),
						fehlerListe);

				try {
					final String ergebnisDatensatz = fenaGenerator.generiereFehlerbausteine(datensatzKennung);
					pruefungErgebnis.setDatensatz(ergebnisDatensatz);
					// Return Code setzen
					pruefungErgebnis.setReturnCode(fenaGenerator.getReturnCode().getCode());

				} catch (final FehlerTextNichtVorhandenException e1) {
					registerError(pruefungErgebnis, e, ReturnCodesDeuev.TECHNISCHER_FEHLER);
				} catch (final WertelistenException e1) {
					registerError(pruefungErgebnis, e, ReturnCodesDeuev.TECHNISCHER_FEHLER);
				}
			}
		}

		catch (final DatensatzkennungUngueltigException e) {
			pruefungErgebnis.setReturnCode(ReturnCodesDeuev.FEHLER_DATENSATZ_KENNUNG.getCode());
		}

		catch (final WertelistenException e) {
			registerError(pruefungErgebnis, e, ReturnCodesDeuev.TECHNISCHER_FEHLER);
		}

		catch (final UngueltigeDatenException e) {
			registerError(pruefungErgebnis, e, ReturnCodesDeuev.TECHNISCHER_FEHLER);
		}

		catch (final FehlerTextNichtVorhandenException e) {
			registerError(pruefungErgebnis, e, ReturnCodesDeuev.TECHNISCHER_FEHLER);
		}

		catch (final UngueltigePrueferDatenException e) {
			registerError(pruefungErgebnis, e, ReturnCodesDeuev.TECHNISCHER_FEHLER);
		}

		catch (final Exception e) {
			LOGGER.log(Level.SEVERE, "Technischer Fehler in der DEUEV-Kernpruefung");
			registerError(pruefungErgebnis, e, ReturnCodesDeuev.TECHNISCHER_FEHLER);
		}
	}

	/**
	 * Startet die Pruefung, nachdem die eingabeDaten (Parameter) validiert
	 * wurden.
	 * 
	 * @param pruefungErgebnis
	 *            Ergebnis der Pruefung
	 * @param iterator
	 *            Aktuelles EingabeDaten-Objekt innerhalb des
	 *            {@link DatensatzIterator}
	 * @throws DatensatzAufbauException
	 *             wird ausgeloest, falls im Datensatz ein Baustein enthalten
	 *             ist, deren Kennung unbekannt ist, falls beim Einlesen der
	 *             einzelnen Bausteine bzw. Felder ein Feld mit dem
	 *             <code>null</code>-Wert erzeugt wird (ist i.d.R. auf einen
	 *             Programmierfehler zurueckzufuehren), falls der Datensatz zu
	 *             kurz ist und nicht eingelesen werden kann, falls die
	 *             Bausteinreihenfolge falsch ist (die Bausteine werden in der
	 *             von der Anlage 9 definierten Reihenfolge eingelesen)
	 * @throws UngueltigePrueferDatenException
	 *             wird ausgeloest, falls beim Initialisieren der einzelnen
	 *             Pruefer fur die Bausteine ein Fehler aufgetreten ist
	 * @throws FehlerTextNichtVorhandenException
	 *             wird ausgeloest, falls von der Kernpruefung ein Fehler
	 *             gemeldet wurde, zu dem keine Fehlerbeschreibung in der
	 *             Werteliste vorhanden ist
	 * @throws UngueltigeDatenException
	 *             wird ausgeloest, falls waehrend der Pruefung ungueltige Daten
	 *             an eine der einzelnen Pruefungen uebergeben wurden
	 * @throws DatensatzkennungUngueltigException
	 *             wird ausgeloest, wenn eine falsche Datensatzkennung
	 *             uebergeben wird.
	 */
	private void start(final DatensatzPruefungErgebnis pruefungErgebnis, final DatensatzIterator iterator)
			throws DatensatzAufbauException, UngueltigePrueferDatenException, UngueltigeDatenException,
			FehlerTextNichtVorhandenException, WertelistenException, DatensatzkennungUngueltigException {
		if (iterator.getDatensatz().length() < DATENSATZKENNUNG_LAENGE) {
			throw new DatensatzAufbauException("Der Datensatz ist zu kurz, es konnte keine Kennung erkannt werden. "
					+ iterator.getDatensatz());
		}

		final String kennung = iterator.getDatensatz().substring(0, DATENSATZKENNUNG_LAENGE);
		DatensatzParser parser = null;
		DatensatzPruefer pruefer = null;

		try {
			datensatzKennung = DatensatzName.valueOf(kennung);
		} catch (final IllegalArgumentException e) {
			LOGGER.log(Level.WARNING, "Die Datensatzkennung ist ungueltig: " + kennung);
			throw new DatensatzkennungUngueltigException("Die Datensatzkennung ist ungueltig: " + kennung);
		}

		if (kennung.equals(DatensatzName.DSKO.name())) {
			parser = new DatensatzParserDSKO();
			pruefer = new DatensatzPrueferDSKO(iterator.getEingabeDaten().getVerarbeitungsDatumAsDate(), iterator
					.getEingabeDaten().getOperationscode());
		}

		else if (kennung.equals(DatensatzName.DSBD.name())) {
			parser = new DatensatzParserDSBD();
			pruefer = new DatensatzPrueferDSBD(iterator.getEingabeDaten().getVerarbeitungsDatumAsDate(), iterator
					.getEingabeDaten().getOperationscode());
		}

		else if (kennung.equals(DatensatzName.DSME.name())) {
			parser = new DatensatzParserDSME();
			pruefer = new DatensatzPrueferDSME(iterator.getEingabeDaten().getVerarbeitungsDatumAsDate(), iterator
					.getEingabeDaten().getZeitangaben(), iterator.getEingabeDaten().getOperationscode());
		}

		else if (kennung.equals(DatensatzName.DSAE.name())) {
			parser = new DatensatzParserDSAE();
			pruefer = new DatensatzPrueferDSAE(iterator.getEingabeDaten().getVerarbeitungsDatumAsDate(), iterator
					.getEingabeDaten().getZeitangaben(), iterator.getEingabeDaten().getOperationscode());
		}

		else if (kennung.equals(DatensatzName.VNIF.name())) {
			parser = new DatensatzParserVNIF();
			pruefer = new DatensatzPrueferVNIF();
		}

		else {
			pruefungErgebnis.setReturnCode(ReturnCodesDeuev.FEHLER_DATENSATZ_KENNUNG.getCode());
		}

		if (pruefungErgebnis.getReturnCode() == null
				|| pruefungErgebnis.getReturnCode().compareTo(ReturnCodesDeuev.FEHLER_DATENSATZ_KENNUNG.getCode()) != 0) {

			if (validateVerfahrensmerkmal(pruefungErgebnis, iterator.getDatensatz())) {

				LOGGER.log(Level.INFO, "Parse Datensatz " + kennung);
				final Datensatz datensatz = parser.parse(iterator.getDatensatz());

				LOGGER.log(Level.INFO, "Pruefe Datensatz " + kennung);
				pruefer.initialisierePruefungen(datensatz);

				// Pruefungen ausfuehren
				final List<Fehler<?>> fehlerListe = pruefer.pruefen();

				// Ergebnis auswerten und Fehlerbausteine erstellen
				if (fehlerListe == null || fehlerListe.isEmpty()) {
					// Return Code setzen
					pruefungErgebnis.setReturnCode(ReturnCodesDeuev.ERFOLG_OHNEFEHLER.getCode());
				} else {
					final FehlerBausteinGenerator fenaGenerator = new FehlerBausteinGenerator(iterator.getDatensatz(),
							fehlerListe);

					final String ergbnisDatensatz = fenaGenerator.generiereFehlerbausteine(datensatzKennung);
					pruefungErgebnis.setDatensatz(ergbnisDatensatz);

					// Return Code setzen
					pruefungErgebnis.setReturnCode(fenaGenerator.getReturnCode().getCode());
				}

				if (kennung.equals(DatensatzName.VNIF.name())) {
					// Anfrage auf Metadaten
					final String dsAppend = getMetadaten().getAsDatensatz();
					pruefungErgebnis.setDatensatz(pruefungErgebnis.getDatensatz().concat(dsAppend));
					pruefungErgebnis.setReturnCode(ReturnCodesDeuev.ERFOLG_OHNEFEHLER.getCode());
				}
			}
		}
	}

	/**
	 * Prueft die uebergebenen Parameter: Operationscode, Datensatz,
	 * Verarbeitungsdatum. Geprueft wird, ob die einzelnen Parameter nicht leer
	 * und gueltig sind.
	 * 
	 * @return <code>true</code>, falls alle Uebergabeparameter gueltig sind
	 */
	@Override
	protected boolean validateEingabeDaten(final DatensatzPruefungErgebnis pruefungErgebnis,
			final DatensatzIterator iterator) {
		final boolean gueltig = super.validateEingabeDaten(pruefungErgebnis, iterator);

		if (!gueltig) {
			pruefungErgebnis.setReturnCode(ReturnCodesDeuev.TECHNISCHER_FEHLER.getCode());
		}

		return gueltig;
	}

	/**
	 * Validiert, ob es im uebergebenen Datensatz bereits ein Fehlerkennzeichen
	 * gesetzt ist.
	 * 
	 * @param datensatz
	 *            zu pruefende Datensatz
	 * @return <code>false</code>, falls im Datensatz bereits Fehlerkennzeichen
	 *         eingetragen wurde, <code>true</code> ansonsten
	 */
	@Override
	protected boolean validateFehlerkennzeichen(final DatensatzPruefungErgebnis pruefungErgebnis, final String datensatz, final String vfmm) {
		boolean result = false;
		try {
			result = super.validateFehlerkennzeichen(pruefungErgebnis, datensatz, vfmm);

			if (!result) {
				pruefungErgebnis.setReturnCode(ReturnCodesDeuev.FEHLER_FEHLERKENNZEICHEN.getCode());
			}
		} catch (DatensatzkennungUngueltigException e) {
			pruefungErgebnis.setReturnCode(ReturnCodesDeuev.FEHLER_DATENSATZ_KENNUNG.getCode());
		} catch (DatensatzAufbauException e) {
			pruefungErgebnis.setReturnCode(ReturnCodesDeuev.FEHLER_DATENSATZ_KENNUNG.getCode());
		}

		return result;
	}

	private void registerError(final DatensatzPruefungErgebnis pruefungErgebnis, final Throwable e,
			final ReturnCodesDeuev returnCode) {
		LOGGER.log(Level.WARNING, e.getMessage(), e);
		LOGGER.log(Level.WARNING, returnCode.getText());
		pruefungErgebnis.setReturnCode(returnCode.getCode());
	}

	/**
	 * Validiert, ob das Verfahrensmerkmal des Datensatzes "DEUEV" ist..
	 * 
	 * @param datensatz
	 * @return <code>true</code> falls der das Verfahrensmerkmal DEUEV ist;
	 *         sonst <code>false</code>
	 */
	private boolean validateVerfahrensmerkmal(final DatensatzPruefungErgebnis pruefungErgebnis, final String datensatz) {
		boolean erfolgreich = false;

		if (datensatz.length() >= INDEX_VERFAHRENSMERKMAL_ENDE) {
			final String verfahren = datensatz.substring(INDEX_VERFAHRENSMERKMAL_START, INDEX_VERFAHRENSMERKMAL_ENDE);

			switch (datensatzKennung) {
			case DSKO:
				erfolgreich = VF_DSKO.contains(verfahren);
				break;

			case DSBD:
				// Überprüfung des VFMM erfolgt erst später in Cobol
				erfolgreich = true;
				break;

			case DSME:
				erfolgreich = VF_DSME.contains(verfahren);
				break;

			case DSAE:
				erfolgreich = VF_DSAE.contains(verfahren);
				break;

			case VNIF:
				erfolgreich = VF_VNIF.contains(verfahren);
				break;

			default:
				break;
			}
		} else {
			erfolgreich = false;
		}

		if (!erfolgreich) {
			pruefungErgebnis.setReturnCode(ReturnCodesDeuev.FEHLER_VERFAHRENSMERKMAL.getCode());
		}

		return erfolgreich;
	}

	@Override
	protected void setzeFehlerWennEingabeDatenNull(final DatensatzPruefungErgebnis pruefungErgebnis) {
		pruefungErgebnis.setReturnCode(ReturnCodesDeuev.TECHNISCHER_FEHLER.getCode());
		LOGGER.log(Level.SEVERE, "Konnte Pruefung nicht durchfuehren, da die EingabeDaten null sind!");
	}

	private Metadaten getMetadaten() {
		final Metadaten metadaten = new Metadaten();

		final Map<String, String> propMetadaten = WertelistenVerwaltungDeuev.getInstance().getMap(
				MapTypDeuev.VERSION);

		metadaten.readVersionProperties(propMetadaten);
		metadaten.setFachverfahren("DEUEV");
		metadaten.setVersion("02.055");
		metadaten.setEinsatzTermin("20150601");
		metadaten.setBuildNummer("118");

		return metadaten;
	}
}
