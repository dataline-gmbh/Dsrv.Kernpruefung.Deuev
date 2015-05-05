package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSBD;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSBD;

/**
 * Pruefung fuer das Feld Wirtschafts-Unterklasse aus dem Baustein DSBD.
 */
public class PruefungWirtschaftsUnterklasse extends AbstractFeldPruefung<FeldNameDSBD, FehlerNummerDSBD> {

	private final Feld<FeldNameDSBD> feldVerfahren;
	private final Feld<FeldNameDSBD> feldAbgabegrund;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldVerfahren
	 *            Das Feld Verfahren aus DSBD.
	 * @param feldAbgabegrund
	 *            Das Feld Abgabegrund aus DSBD.
	 */
	public PruefungWirtschaftsUnterklasse(final Feld<FeldNameDSBD> feld, final Feld<FeldNameDSBD> feldVerfahren,
			final Feld<FeldNameDSBD> feldAbgabegrund) {
		super(feld);

		this.feldVerfahren = feldVerfahren;
		this.feldAbgabegrund = feldAbgabegrund;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		if ("BTRAG".equals(feldVerfahren.getTrimmedValue())) {
			final PruefungLaenge bd220 = new PruefungLaenge(0, getFeld());
			addPruefung(bd220, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD220));
		}

		if ("BTRKS".equals(feldVerfahren.getTrimmedValue())) {
			if (!"02".equals(feldAbgabegrund.getTrimmedValue())) {
				final PruefungLaenge bd222 = new PruefungLaenge(0, getFeld());
				addPruefung(bd222, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD222));
			} else {
				final PruefungNichtLeer bd224 = new PruefungNichtLeer(getFeld());
				addPruefung(bd224, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD224));
			}
		}

	}

}
