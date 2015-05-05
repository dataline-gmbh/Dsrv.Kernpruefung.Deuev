package de.drv.dsrv.kernpruefung.basis.utils;

/**
 * Util-Klasse, um Pruefziffern fuer Betriebsnummer und Versicherungsnummer.
 */
public final class Pruefziffer {

	private static final int BETRIEBSNUMMER_LAENGE = 8;
	private static final int ZEHNERSTELLE = 10;

	private static final int VERSICHERUNGSNUMMER_INDEX_NUMMER_START_A = 0;
	private static final int VERSICHERUNGSNUMMER_INDEX_NUMMER_ENDE_A = 8;

	private static final int VERSICHERUNGSNUMMER_INDEX_BUCHSTABE_START = VERSICHERUNGSNUMMER_INDEX_NUMMER_ENDE_A;
	private static final int VERSICHERUNGSNUMMER_INDEX_BUCHSTABE_ENDE = 9;

	private static final int VERSICHERUNGSNUMMER_INDEX_NUMMER_START_B = VERSICHERUNGSNUMMER_INDEX_BUCHSTABE_ENDE;
	private static final int VERSICHERUNGSNUMMER_INDEX_NUMMER_ENDE_B = 12;

	private static final int VERSICHERUNGSNUMMER_LENGTH = 12;

	private static final int VERSICHERUNGSNUMMER_ASCII_DIFFERENZ_ZU_NULL = 64;

	private static final int VERSICHERUNGSNUMMER_MAX = 26;

	/**
	 * Berechnet die Pruefziffer der Betriebsnummer analog zu Ziffer 1.3.2.2
	 * 
	 * 1.3.2.2 Betriebsnummer [...] Die letzte Ziffer der Betriebsnummer ist die
	 * Prüfziffer; sie ist auf Richtigkeit zu prüfen. Die Prüfziffer der
	 * Betriebsnummer wird wie folgt gebildet: a) Die Ziffern der Betriebsnummer
	 * (Stellen 1 bis 7) werden - an der ersten Stelle beginnend b) mit den
	 * Faktoren 1, 2, 1, 2, 1, 2, 1 multipliziert. c) Von den einzelnen
	 * Produkten werden die Quersummen gebildet. d) Die Quersummen werden
	 * addiert. e) Die Summe wird durch 10 dividiert. f) Der verbleibende Rest
	 * ist die Prüfziffer. Als letzte Ziffer der Betriebsnummer ist sowohl die
	 * errechnete Prüfziffer als auch die letzte Stelle aus der Summe von
	 * Prüfziffer und der Konstanten 5 zulässig.
	 * 
	 * @param feld
	 *            Die Betriebsnummer
	 * @return -1 sollte feld keine Betriebsnummer sein - oder 0..9 die
	 *         errechnete Pruefziffer
	 */
	public static int berechnePruefzifferDerBetriebsnummer(final String feld) {

		int pruefziffer = 0;

		if (feld.length() != BETRIEBSNUMMER_LAENGE) {
			pruefziffer = -1;
		}

		else {
			try {
				final int[] ziffern = new int[BETRIEBSNUMMER_LAENGE - 1];
				final int[] multiplikator = { 1, 2, 1, 2, 1, 2, 1 };

				for (int i = 0; i < BETRIEBSNUMMER_LAENGE - 1; ++i) {
					ziffern[i] = Integer.parseInt(feld.substring(i, (i + 1)));
					multiplikator[i] = ziffern[i] * multiplikator[i];
					pruefziffer += berechneQuersumme(multiplikator[i]);
				}

				pruefziffer = pruefziffer % ZEHNERSTELLE;

			} catch (NumberFormatException e) {
				pruefziffer = -1;
			}

		}

		return pruefziffer;
	}

	/**
	 * Die letzte Ziffer der Versicherungsnummer ist die Pruefziffer; sie ist
	 * auf Richtigkeit zu pruefen. Die Pruefziffer der Versicherungsnummer wird
	 * wie folgt gebildet: a) Der Buchstabe wird durch eine zweistellige Zahl
	 * ersetzt, die die Position des Buchstabens im Alphabet (01 bis 26)
	 * kennzeichnet. b) Die Ziffern der damit zwoelfstelligen Nummer werden - an
	 * der ersten Stelle beginnend - mit den Faktoren 2, 1, 2, 5, 7, 1, 2, 1, 2,
	 * 1, 2 und 1 multipliziert. c) Von den Produkten werden die Quersummen
	 * gebildet. Die Quersummen werden addiert. Die Summe wird durch 10
	 * dividiert. Der verbleibende Rest ist die Pruefziffer. d) Die zweistellige
	 * Verschluesselung des Buchstabens wird wieder durch den Buchstaben
	 * ersetzt; die Versicherungsnummer besteht damit aus elf
	 * Informationsstellen und einer Pruefziffer, zusammen zwoelf Stellen.
	 * 
	 * @param feld
	 *            - Versicherungsnummer
	 * @return Pruefziffer der Versicherungsnummer
	 */
	public static int berechnePruefzifferDerVersicherungsnummer(final String feld) {
		int pruefziffer = 0;

		if (feld.length() != VERSICHERUNGSNUMMER_LENGTH) {
			pruefziffer = -1;
		}

		else {
			final char grossbuchstabe = feld.charAt(VERSICHERUNGSNUMMER_INDEX_BUCHSTABE_START);
			final int gbZahl = grossbuchstabe - VERSICHERUNGSNUMMER_ASCII_DIFFERENZ_ZU_NULL;

			if (gbZahl < 1 || gbZahl > VERSICHERUNGSNUMMER_MAX) {
				pruefziffer = -1;
			}

			else {
				// bbttmmjjassp
				// Fuelle das Ziffern-Array mit den einzelnen Ziffern aus dem
				// Feld
				final int[] ziffern = new int[VERSICHERUNGSNUMMER_LENGTH];
				final int[] multiplikator = { 2, 1, 2, 5, 7, 1, 2, 1, 2, 1, 2, 1 };
				// int[] ergebnisMultiplikation = new int[12];

				try {

					for (int i = VERSICHERUNGSNUMMER_INDEX_NUMMER_START_A; i < VERSICHERUNGSNUMMER_INDEX_NUMMER_ENDE_A; ++i) {
						ziffern[i] = Integer.parseInt(feld.substring(i, (i + 1)));
					}
					ziffern[VERSICHERUNGSNUMMER_INDEX_BUCHSTABE_START] = gbZahl / ZEHNERSTELLE;
					ziffern[VERSICHERUNGSNUMMER_INDEX_BUCHSTABE_START + 1] = gbZahl % ZEHNERSTELLE;

					for (int i = VERSICHERUNGSNUMMER_INDEX_NUMMER_START_B; i < VERSICHERUNGSNUMMER_INDEX_NUMMER_ENDE_B - 1; ++i) {
						ziffern[i + 1] = Integer.parseInt(feld.substring(i, (i + 1)));
					}

					// Berechne die ziffern * multiplikanten
					// Berechne Quersumme
					for (int i = 0; i < ziffern.length; ++i) {
						multiplikator[i] = ziffern[i] * multiplikator[i];
						pruefziffer += berechneQuersumme(multiplikator[i]);
					}

					pruefziffer = pruefziffer % ZEHNERSTELLE;
				} catch (NumberFormatException e) {
					pruefziffer = -1;
				}
			}
		}

		return pruefziffer;
	}

	private static int berechneQuersumme(final int zahl) {
		int quersumme = 0;
		int tmpZahl = zahl;
		while (tmpZahl > 0) {
			quersumme = quersumme + tmpZahl % ZEHNERSTELLE;
			tmpZahl = tmpZahl / ZEHNERSTELLE;
		}
		return quersumme;
	}

	private Pruefziffer() {
	}

}
