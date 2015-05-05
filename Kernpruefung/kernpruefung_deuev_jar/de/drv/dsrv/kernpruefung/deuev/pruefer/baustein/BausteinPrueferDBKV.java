package de.drv.dsrv.kernpruefung.deuev.pruefer.baustein;

import java.util.ArrayList;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractBausteinPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv.PruefungBeitragsBemGrundlageEntgeltAltersTeilzeit;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv.PruefungBeitragsBemGrundlageKurzArbeitgergeld;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv.PruefungBeitragsgruppe;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv.PruefungEinmaligesEntgelt;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv.PruefungKennzStorno;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv.PruefungKennzeichenGleitzone;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv.PruefungKennzeichenRechtskreis;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv.PruefungKvGrund;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv.PruefungLaufendesEntgelt;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv.PruefungLaufendesEntgeltAv;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv.PruefungLaufendesEntgeltKv;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv.PruefungLaufendesEntgeltRv;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv.PruefungRegelmaessigesJahresentgelt;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv.PruefungReserve1;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv.PruefungReserve2;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv.PruefungReserve3;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv.PruefungSvTage;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv.PruefungZeitraumBeginn;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv.PruefungZeitraumEnde;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Der Bausteinpruefer fuer den Baustein DBKV. Ruft alle Feldpruefungen auf.
 */
public class BausteinPrueferDBKV extends AbstractBausteinPruefer<FeldNameDBKV, FehlerNummerDBKV> {

	private final Baustein<FeldNameDSME> bausteinDsme;
	private final Baustein<FeldNameDBME> bausteinDbme;

	/**
	 * Konstruktor. Nimmt alle Werte entgegen, die fuer die Feld-Pruefungen in
	 * dem Baustein benoetigt werden, und speichert diese ab.
	 * 
	 * @param baustein
	 *            - Baustein DBKV
	 * @param bausteinDsme
	 *            - Baustein DSME
	 * @param bausteinDbme
	 *            - Baustein DBME
	 */
	public BausteinPrueferDBKV(final Baustein<FeldNameDBKV> baustein, final Baustein<FeldNameDSME> bausteinDsme, final Baustein<FeldNameDBME> bausteinDbme) {
		super(baustein);

		this.bausteinDsme = bausteinDsme;
		this.bausteinDbme = bausteinDbme;
	}

	@Override
	public List<FeldPruefung<FeldNameDBKV, FehlerNummerDBKV>> getPruefungen() throws UngueltigePrueferDatenException {

		final List<FeldPruefung<FeldNameDBKV, FehlerNummerDBKV>> pruefList = new ArrayList<FeldPruefung<FeldNameDBKV, FehlerNummerDBKV>>();
		final Baustein<FeldNameDBKV> baustein = getBaustein();

		final Feld<FeldNameDBKV> feldKennzst = baustein.getFeld(FeldNameDBKV.KENNZ_STORNO);
		final Feld<FeldNameDBKV> feldKvgrund = baustein.getFeld(FeldNameDBKV.KV_GRUND);
		final Feld<FeldNameDBKV> feldSvtage = baustein.getFeld(FeldNameDBKV.SV_TAGE);
		final Feld<FeldNameDBKV> feldZeitraumBeginn = baustein.getFeld(FeldNameDBKV.ZEITRAUM_BEGINN);
		final Feld<FeldNameDBKV> feldZeitraumEnde = baustein.getFeld(FeldNameDBKV.ZEITRAUM_ENDE);
		final Feld<FeldNameDBKV> feldLaufendesEntgelt = baustein.getFeld(FeldNameDBKV.LAUFENDES_ENTGELT);
		final Feld<FeldNameDBKV> feldEinmaligesEntgelt = baustein.getFeld(FeldNameDBKV.EINMALIGES_ENTGELT);
		final Feld<FeldNameDBKV> feldBbgruKug = baustein.getFeld(FeldNameDBKV.BEITRAGS_BEMMESUNGSGRUNDLAGE_KURZ_ARBEITERGELD);
		final Feld<FeldNameDBKV> feldKennzgle = baustein.getFeld(FeldNameDBKV.KENNZ_GLEITZONE);
		final Feld<FeldNameDBKV> feldReserve1 = baustein.getFeld(FeldNameDBKV.RESERVE1);
		final Feld<FeldNameDBKV> feldReserve2 = baustein.getFeld(FeldNameDBKV.RESERVE2);
		final Feld<FeldNameDBKV> feldRjeg = baustein.getFeld(FeldNameDBKV.REGELMAESSIGES_JAHRESENTGELT);
		final Feld<FeldNameDBKV> feldBbgruAtg = baustein.getFeld(FeldNameDBKV.BEITRAGSBEMESSUNGSGRUNDLAGE_ENTGELT_ALTERSTEILZEIT);
		final Feld<FeldNameDBKV> feldBeitragsgruppe = baustein.getFeld(FeldNameDBKV.BEITRAGS_GRUPPE);
		final Feld<FeldNameDBKV> feldKennzrk = baustein.getFeld(FeldNameDBKV.KENNZ_RECHTSKREIS);
		final Feld<FeldNameDBKV> feldLaufendesEntgeltKv = baustein.getFeld(FeldNameDBKV.LAUFENDES_ENTGELT_KV);
		final Feld<FeldNameDBKV> feldLaufendesEntgeltRv = baustein.getFeld(FeldNameDBKV.LAUFENDES_ENTGELT_RV);
		final Feld<FeldNameDBKV> feldLaufendesEntgeltAv = baustein.getFeld(FeldNameDBKV.LAUFENDES_ENTGELT_AV);
		final Feld<FeldNameDBKV> feldReserve3 = baustein.getFeld(FeldNameDBKV.RESERVE3);

		if (feldKennzst == null || feldKvgrund == null || feldSvtage == null || feldZeitraumBeginn == null || feldZeitraumEnde == null || feldLaufendesEntgelt == null || feldEinmaligesEntgelt == null || feldBbgruKug == null || feldKennzgle == null
				|| feldReserve1 == null || feldReserve2 == null || feldRjeg == null || feldBbgruAtg == null || feldBeitragsgruppe == null || feldKennzrk == null || feldReserve3 == null) {
			throw new UngueltigePrueferDatenException("Pruefung fuer den Baustein " + baustein.getName() + " kann nicht initalisiert werden, einer der Felder ist nicht vorhanden: " + baustein);
		}

		pruefList.add(new PruefungKennzStorno(feldKennzst));
		pruefList.add(new PruefungKvGrund(feldKvgrund, feldZeitraumBeginn));
		pruefList.add(new PruefungSvTage(feldSvtage, feldKennzst, feldLaufendesEntgelt, feldLaufendesEntgeltKv, feldLaufendesEntgeltRv, feldLaufendesEntgeltAv));
		pruefList.add(new PruefungZeitraumBeginn(feldZeitraumBeginn, bausteinDsme));
		pruefList.add(new PruefungZeitraumEnde(feldZeitraumEnde, feldZeitraumBeginn));
		pruefList.add(new PruefungLaufendesEntgelt(feldLaufendesEntgelt, feldZeitraumBeginn, feldZeitraumEnde, feldKennzrk, feldKennzst, bausteinDsme));
		pruefList.add(new PruefungEinmaligesEntgelt(feldEinmaligesEntgelt));
		pruefList.add(new PruefungBeitragsBemGrundlageKurzArbeitgergeld(feldBbgruKug, feldZeitraumBeginn));
		pruefList.add(new PruefungKennzeichenGleitzone(feldKennzgle, feldKennzst, feldZeitraumBeginn, bausteinDsme));
		pruefList.add(new PruefungReserve1(feldReserve1, feldKennzst));
		pruefList.add(new PruefungReserve2(feldReserve2, feldKennzst));
		pruefList.add(new PruefungRegelmaessigesJahresentgelt(feldRjeg, feldZeitraumBeginn, feldKennzgle));
		pruefList.add(new PruefungBeitragsBemGrundlageEntgeltAltersTeilzeit(feldBbgruAtg, feldZeitraumBeginn));
		pruefList.add(new PruefungBeitragsgruppe(feldBeitragsgruppe, feldZeitraumBeginn, bausteinDsme, bausteinDbme));
		pruefList.add(new PruefungKennzeichenRechtskreis(feldKennzrk, feldZeitraumBeginn));
		pruefList.add(new PruefungLaufendesEntgeltKv(feldLaufendesEntgeltKv, feldZeitraumBeginn, feldZeitraumEnde));
		pruefList.add(new PruefungLaufendesEntgeltRv(feldLaufendesEntgeltRv, feldZeitraumBeginn, feldZeitraumEnde, feldKennzrk, bausteinDsme));
		pruefList.add(new PruefungLaufendesEntgeltAv(feldLaufendesEntgeltAv, feldZeitraumBeginn, feldZeitraumEnde, feldKennzrk, bausteinDsme));
		pruefList.add(new PruefungReserve3(feldReserve3));

		return pruefList;
	}

}
