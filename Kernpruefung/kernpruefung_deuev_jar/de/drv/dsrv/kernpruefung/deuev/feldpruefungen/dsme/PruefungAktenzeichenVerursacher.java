package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsme;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBuchstabeAZazZiffernZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungGrossbuchstabe;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNotInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.utils.Sonderzeichen;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.FeldPruefungStornierungDsme;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBSO;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld AktenzeichenVerursacher aus dem Baustein DSME.
 */
public class PruefungAktenzeichenVerursacher extends AbstractFeldPruefung<FeldNameDSME, FehlerNummerDSME> {

	private static final String BATRV = "BATRV";
	private static final List<Character> ERLAUBTE_ZEICHEN = Arrays.asList(Sonderzeichen.SPACE, Sonderzeichen.FULL_STOP,
			Sonderzeichen.HYPHEN_MINUS, Sonderzeichen.SOLIDUS);
	private static final int LAENGE_BATRV = 15;
	private final String vfmm;

	private final Baustein<FeldNameDBME> bausteinDbme;
	private final Baustein<FeldNameDBSO> bausteinDbso;
	private final Baustein<FeldNameDBKV> bausteinDbkv;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param bausteinDbme
	 *            Baustein DBME
	 * @param bausteinDbso
	 *            Baustein DBSO
	 * @param bausteinDbkv
	 *            Baustein DBKV
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public PruefungAktenzeichenVerursacher(final Feld<FeldNameDSME> feld, final Baustein<FeldNameDBME> bausteinDbme,
			final Baustein<FeldNameDBSO> bausteinDbso, final Baustein<FeldNameDBKV> bausteinDbkv, final String vfmm) {
		super(feld);

		this.vfmm = vfmm;
		this.bausteinDbme = bausteinDbme;
		this.bausteinDbso = bausteinDbso;
		this.bausteinDbkv = bausteinDbkv;
	}

	@Override
	public void initialisierePruefungen() {
		if (BATRV.compareTo(vfmm) == 0) {
			if (getFeld().getTrimmedValue().length() < LAENGE_BATRV) {
				final PruefungNichtLeer me160y = new PruefungNichtLeer(getFeld());
				addPruefung(me160y, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME160));

				final PruefungLaenge me160z = new PruefungLaenge(15, getFeld());
				addPruefung(me160z, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME160));
			} else {
				final Feld<FeldNameDSME> numerisch1 = new Feld<FeldNameDSME>(getFeld().getTrimmedValue()
						.substring(0, 8));
				final PruefungNumerisch me160a = new PruefungNumerisch(numerisch1);
				addPruefung(me160a, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME160));

				final PruefungNotInList me160a2 = new PruefungNotInList(Arrays.asList("00000000"), numerisch1);
				addPruefung(me160a2, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME160));

				final Feld<FeldNameDSME> numerisch2 = new Feld<FeldNameDSME>(getFeld().getTrimmedValue().substring(9,
						15));
				final PruefungNumerisch me160b = new PruefungNumerisch(numerisch2);
				addPruefung(me160b, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME160));

				final PruefungNotInList me160b2 = new PruefungNotInList(Arrays.asList("000000"), numerisch2);
				addPruefung(me160b2, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME160));

				final Feld<FeldNameDSME> grossbuchstabe = new Feld<FeldNameDSME>(getFeld().getTrimmedValue().substring(
						8, 9));
				final PruefungGrossbuchstabe me160c = new PruefungGrossbuchstabe(grossbuchstabe);
				addPruefung(me160c, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME160));
			}
		}

		final FeldPruefungStornierungDsme stornierung = new FeldPruefungStornierungDsme(bausteinDbme, bausteinDbso,
				bausteinDbkv);
		if (!stornierung.isStornierung()) {
			if (getFeld().getTrimmedValue().length() > 0) {
				final PruefungBuchstabeAZazZiffernZeichen me161 = new PruefungBuchstabeAZazZiffernZeichen(getFeld(),
						ERLAUBTE_ZEICHEN);
				addPruefung(me161, new Fehler<FehlerNummerDSME>(FehlerNummerDSME.DSME161));
			}
		}
	}

}
