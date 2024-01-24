/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.business.mdpbackoffice;

import java.util.*;

import it.csi.mdp.mdpboweb.dto.*;
import it.csi.mdp.mdpboweb.dto.common.*;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.*;

import org.apache.log4j.*;
import it.csi.mdp.mdpboweb.util.*;
import it.csi.mdp.mdpboweb.business.*;

/*PROTECTED REGION ID(R-284023790) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPBackOffice;
import it.csi.mdp.mdpboweb.business.ws.Vtransazione;
import it.csi.mdp.mdpboweb.business.ws.VtransazioneResult;

/*PROTECTED REGION END*/

public class CPBECpGestioneTransazioni {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_APPLICAZIONI_CODE = "appDataapplicazioni";

	public final static String APPDATA_TRANSAZIONI_CODE = "appDatatransazioni";

	public final static String APPDATA_RICERCATRANSAZIONE_CODE = "appDataricercaTransazione";

	public final static String APPDATA_LASTWHERECLAUSE_CODE = "appDatalastWhereClause";

	public final static String APPDATA_SELETTOREIDTRANSAZIONE_CODE = "appDataselettoreIdTransazione";

	public final static String APPDATA_SELETTOREOPERAZIONE_CODE = "appDataselettoreOperazione";

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_CURRENTUSER_CODE = "appDatacurrentUser";

	public final static String APPDATA_STATITRANSAZIONE_CODE = "appDatastatiTransazione";

	public final static String APPDATA_TRANSAZIONE_CODE = "appDatatransazione";

	public final static String APPDATA_SELETTOREIDTIPOLOGIACOMMISSIONE_CODE = "appDataselettoreIdTipologiaCommissione";

	public final static String APPDATA_TIPOLOGIACOMMISSIONI_CODE = "appDatatipologiaCommissioni";

	public final static String APPDATA_PAGINAZIONETRANS_CODE = "appDatapaginazioneTrans";

	public final static String APPDATA_REPORTCERCATRANSAZIONI_CODE = "appDatareportCercaTransazioni";

	public final static String APPDATA_SIZEBLOCCO_CODE = "appDatasizeBlocco";

	public final static String APPDATA_STATITRANSAZIONEXCAMBIO_CODE = "appDatastatiTransazionexCambio";

	public final static String APPDATA_CERCAERRORE_CODE = "appDatacercaErrore";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpGestioneTransazioni";

	/** 
	 * Restituisce il tab correntemente visibile in un determinato tab set
	 */
	private String getCurrentTab(Map session, String tabSetName) {
		String value = (String) session.get(CPNAME + "_" + tabSetName
				+ "_selectedMultiPanel");
		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults cerca(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneTransazioniModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String CERCA_OUTCOME_CODE__OK_NO_RESULTS = "Ok_No_Results";
		final String CERCA_OUTCOME_CODE__OK_SOME_RESULTS = "Ok_Some_Results";
		final String CERCA_OUTCOME_CODE__OK_PREV_NEXT = "Ok_Prev_Next";
		final String CERCA_OUTCOME_CODE__OK_PREV = "Ok_Prev";
		final String CERCA_OUTCOME_CODE__OK_NEXT = "Ok_Next";
		final String CERCA_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1504640671) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String cercaDataInizio = theModel.getAppDataricercaTransazione()
					.getCercaDataInizio();
			String cercaDataFine = theModel.getAppDataricercaTransazione()
					.getCercaDataFine();
			String cercaIdTransazione = theModel.getAppDataricercaTransazione()
					.getIdTransazione().trim();
			String cercaIdApplicazione = theModel
					.getAppDataricercaTransazione().getIdApplicazione();
			long cercaCodStato = theModel.getAppDataricercaTransazione()
					.getCodStato();
			ArrayList<Transazione> aTrans = new ArrayList<Transazione>();
			Map sess = theModel.getSession();
			resetClearStatus_widg_tRicerca(sess);
			theModel.setAppDataselettoreIdTransazione("");

			if ((cercaIdTransazione == null || cercaIdTransazione.trim()
					.equals(""))
					&& (cercaDataInizio == null || cercaDataInizio.trim()
							.equals(""))
					&& (cercaDataFine == null || cercaDataFine.trim()
							.equals(""))
					&& cercaIdApplicazione.equals("SCEGLI_APPLICAZIONE")
					&& cercaCodStato == -1) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0115"));
				result.setResultCode(CERCA_OUTCOME_CODE__KO);

				result.setModel(theModel);
				return result;
			}
			Transazione ts = new Transazione();
			if (cercaIdTransazione != null && !cercaIdTransazione.equals("")) {
				// Ricerca secca per id transazione
				ts = getGestoreMDPBackOffice().getTransazioneById(user,
						cercaIdTransazione);
				if (ts == null) {
					result.setResultCode(CERCA_OUTCOME_CODE__OK_NO_RESULTS);
				} else {
					result.setResultCode(CERCA_OUTCOME_CODE__OK_SOME_RESULTS);
				}
				aTrans.add(ts);

				theModel.setAppDatatransazioni(aTrans);
				result.setModel(theModel);
				return result;
			}
			// paginazione
			PaginazioneTrans Pt = theModel.getAppDatapaginazioneTrans();
			VtransazioneResult ris;
			try {
				ris = getGestoreMDPBackOffice().getListaTransazioniByFiltro(
						user, theModel.getAppDataricercaTransazione(),
						Pt.getNumeroPagina(), Pt.getDimensionePagina());
			} catch (Exception e) {
				String faultString = e.getMessage();

				String message = pu.getMessage("TR-E-0101") + " Exception"
						+ pu.getMessage("TR-E-0102")
						+ " getListaTransazioniByFiltro()"
						+ pu.getMessage("TR-E-0103") + faultString
						+ pu.getMessage("TR-E-0104");
				result.setResultCode(CERCA_OUTCOME_CODE__KO);
				result.getGlobalErrors().add(message);
				result.setModel(theModel);
				return result;
			}
			Vtransazione[] listaTrans = ris.getListTransazioni();
			int totTransazioniFound = ris.getTotTransactionsFound();
			Pt.setNumeroPagina(1);
			Pt.setTotale(totTransazioniFound);
			theModel.setAppDatapaginazioneTrans(Pt);
			if (totTransazioniFound == 0) {
				log.info("[GestoreMDPBackOffice::getListaTransazioniByFiltro]  lista null ");
			} else {
				for (int k = 0; k < listaTrans.length; k++) {
					Vtransazione trnWs = listaTrans[k];
					Transazione trn = new Transazione();
					trn.setIdApplicazione(trnWs.getApplicationId());
					trn.setNomeApplicazione(trnWs.getApplicationname());
					trn.setCodStato(trnWs.getCodStato());
					trn.setOldstate(trnWs.getOldstate());
					trn.setIdTransazione(trnWs.getTransactionId());
					trn.setDataTransazione(UtilDate.getConvertCalendarToString(
							trnWs.getInitDate(), "dd/MM/yyyy HH:mm:ss"));
					trn.setDataInizio(UtilDate.getConvertCalendarToString(
							trnWs.getInitDate(), "dd/MM/yyyy HH:mm:ss"));
					trn.setFinishDate(UtilDate.getConvertCalendarToString(
							trnWs.getFinishDate(), "dd/MM/yyyy HH:mm:ss"));
					trn.setDescrStato(trnWs.getDescrizione());
					trn.setNomeApplicazione(trnWs.getApplicationname());
					trn.setDescrGateway(trnWs.getGatewayDescription());
					trn.setDescrPayment(trnWs.getPaymentmodeDescription());
					trn.setImportoTransazione(trnWs.getAmount());
					trn.setNumeroPagina(Pt.getNumeroPagina());
					trn.setTransPerPagina(Pt.getNumeroPagina());
					trn.setBasketId(trnWs.getBasketId());
					trn.setMerchantId(trnWs.getMerchantId());
					trn.setPaymentid(trnWs.getPaymentid());
					trn.setPayurl(trnWs.getPayurl());
					trn.setPgresultcode(trnWs.getPgresultcode());
					trn.setStartDate(UtilDate.getConvertCalendarToString(
							trnWs.getStartDate(), "dd/MM/yyyy HH:mm:ss"));
					aTrans.add(trn);
				}
			}

			theModel.setAppDatatransazioni(aTrans);
			String resultCode = "";
			resultCode = (CERCA_OUTCOME_CODE__OK_SOME_RESULTS);
			if (aTrans != null) {
				if (aTrans.size() == 0) {
					resultCode = (CERCA_OUTCOME_CODE__OK_NO_RESULTS);
				} else {
					PaginazioneTrans pag = theModel
							.getAppDatapaginazioneTrans();
					int nPage = pag.getNumeroPagina();
					int sizePage = pag.getDimensionePagina();
					int totRecord = pag.getTotale();
					if (totRecord <= sizePage) {
						resultCode = CERCA_OUTCOME_CODE__OK_SOME_RESULTS;
					} else {
						resultCode = CERCA_OUTCOME_CODE__OK_NEXT;
					}
					if (totRecord > 100) {
						String sOverflow = "ATTENZIONE : Il numero di transazioni che soddisfano i criteri di ricerca e' maggiore di 100."
								+ " Vengono recuperate solo le prime 100. "
								+ "Si consiglia di affinare i criteri di ricerca.";
						theModel.setAppDatareportCercaTransazioni(sOverflow);
					}
					String rptRicerca = "Totale transazioni trovate: "
							+ totRecord + " - Visualizzato Blocco " + nPage
							+ " ( " + ((nPage - 1) * sizePage + 1) + "..."
							+ (nPage * sizePage) + ")";

					theModel.setAppDatareportCercaTransazioni(rptRicerca);
					result.getGlobalMessages().add(rptRicerca);
					theModel.setAppDatapaginazioneTrans(pag);
				}
			}
			result.setResultCode(resultCode);
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::cerca] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults previousPage(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneTransazioniModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String PREVIOUSPAGE_OUTCOME_CODE__OK = "Ok";
		final String PREVIOUSPAGE_OUTCOME_CODE__OK_PREV_NEXT = "Ok_Prev_Next";
		final String PREVIOUSPAGE_OUTCOME_CODE__OK_PREV = "Ok_Prev";
		final String PREVIOUSPAGE_OUTCOME_CODE__OK_NEXT = "Ok_Next";
		final String PREVIOUSPAGE_OUTCOME_CODE__OK_NO_RESULT = "Ok_No_Result";
		final String PREVIOUSPAGE_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-933482365) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			PaginazioneTrans pt = theModel.getAppDatapaginazioneTrans();
			int nPage = pt.getNumeroPagina();
			nPage--;
			pt.setNumeroPagina(nPage);
			//Transazione tr0 = theModel.getAppDatatransazioni().get(0);
			VtransazioneResult ris;
			try {
				ris = getGestoreMDPBackOffice().getListaTransazioniByFiltro(
						user, theModel.getAppDataricercaTransazione(),
						pt.getNumeroPagina(), pt.getDimensionePagina());
			} catch (Exception e) {
				String faultString = e.getMessage();
				String message = pu.getMessage("TR-E-0101") + " Exception"
						+ pu.getMessage("TR-E-0102")
						+ " getListaTransazioniByFiltro()"
						+ pu.getMessage("TR-E-0103") + faultString
						+ pu.getMessage("TR-E-0104");
				result.setResultCode(PREVIOUSPAGE_OUTCOME_CODE__KO);
				result.getGlobalErrors().add(message);
				result.setModel(theModel);
				return result;
			}
			Vtransazione[] listaTrans = ris.getListTransazioni();
			int totTransazioniFound = ris.getTotTransactionsFound();
			pt.setTotale(totTransazioniFound);
			theModel.setAppDatapaginazioneTrans(pt);
			ArrayList<Transazione> aTrans = new ArrayList<Transazione>();
			if (listaTrans.length == 0) {
				log.info("[GestoreMDPBackOffice::getListaTransazioniByFiltro]  lista null ");
			} else {
				for (int k = 0; k < listaTrans.length; k++) {
					Vtransazione trnWs = listaTrans[k];
					Transazione trn = new Transazione();
					trn.setIdApplicazione(trnWs.getApplicationId());
					trn.setNomeApplicazione(trnWs.getApplicationname());
					trn.setCodStato(trnWs.getCodStato());
					trn.setOldstate(trnWs.getOldstate());
					trn.setIdTransazione(trnWs.getTransactionId());
					trn.setDataTransazione(UtilDate.getConvertCalendarToString(
							trnWs.getFinishDate(), "dd/MM/yyyy HH:mm:ss"));
					trn.setDataInizio(UtilDate.getConvertCalendarToString(
							trnWs.getInitDate(), "dd/MM/yyyy HH:mm:ss"));
					trn.setDescrStato(trnWs.getDescrizione());
					trn.setNomeApplicazione(trnWs.getApplicationname());
					trn.setDescrGateway(trnWs.getGatewayDescription());
					trn.setDescrPayment(trnWs.getPaymentmodeDescription());
					trn.setImportoTransazione(trnWs.getAmount());
					trn.setNumeroPagina(pt.getNumeroPagina());
					trn.setTransPerPagina(pt.getNumeroPagina());
					trn.setBasketId(trnWs.getBasketId());
					trn.setMerchantId(trnWs.getMerchantId());
					trn.setPaymentid(trnWs.getPaymentid());
					trn.setPayurl(trnWs.getPayurl());
					trn.setPgresultcode(trnWs.getPgresultcode());
					trn.setStartDate(UtilDate.getConvertCalendarToString(
							trnWs.getStartDate(), "dd/MM/yyyy HH:mm:ss"));
					aTrans.add(trn);
				}

				theModel.setAppDatatransazioni(aTrans);

				String resultCode = "";
				resultCode = (PREVIOUSPAGE_OUTCOME_CODE__OK_NO_RESULT);
				if (aTrans != null) {
					if (aTrans.size() == 0) {
						if (totTransazioniFound == 0) {
							resultCode = (PREVIOUSPAGE_OUTCOME_CODE__OK_NO_RESULT);
						} else {
							resultCode = PREVIOUSPAGE_OUTCOME_CODE__OK_PREV;
						}
					} else {
						int sizePage = pt.getDimensionePagina();
						if (totTransazioniFound <= sizePage) {
							resultCode = PREVIOUSPAGE_OUTCOME_CODE__OK;
							pt.setNumeroPagina(1);
							nPage = 1;
						} else if (nPage == 1) {
							resultCode = PREVIOUSPAGE_OUTCOME_CODE__OK_NEXT;
						} else if ((nPage + 1) * sizePage > totTransazioniFound) {
							resultCode = PREVIOUSPAGE_OUTCOME_CODE__OK_PREV;
						} else {
							resultCode = PREVIOUSPAGE_OUTCOME_CODE__OK_PREV_NEXT;
						}
						theModel.setAppDatapaginazioneTrans(pt);
						result.setResultCode(resultCode);
					}
					if (pt.getTotale() > 100) {
						String sOverflow = "ATTENZIONE : Il numero di transazioni che soddisfano i criteri di ricerca e' maggiore di 100."
								+ " Vengono recuperate solo le prime 100. "
								+ "Si consiglia di affinare i criteri di ricerca.";
						theModel.setAppDatareportCercaTransazioni(sOverflow);
					}
					String rptRicerca = "Totale transazioni trovate: "
							+ pt.getTotale()
							+ " - Visualizzato Blocco "
							+ nPage
							+ " ("
							+ ((pt.getNumeroPagina() - 1)
									* pt.getDimensionePagina() + 1) + "..."
							+ (pt.getNumeroPagina() * pt.getDimensionePagina())
							+ ")";
					theModel.setAppDatareportCercaTransazioni(rptRicerca);
					result.getGlobalMessages().add(rptRicerca);
					result.setModel(theModel);
				}
				return result;
			}
			result.setResultCode(PREVIOUSPAGE_OUTCOME_CODE__KO);
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::previousPage] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults nextPage(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneTransazioniModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String NEXTPAGE_OUTCOME_CODE__OK = "Ok";
		final String NEXTPAGE_OUTCOME_CODE__OK_PREV_NEXT = "Ok_Prev_Next";
		final String NEXTPAGE_OUTCOME_CODE__OK_PREV = "Ok_Prev";
		final String NEXTPAGE_OUTCOME_CODE__OK_NEXT = "Ok_Next";
		final String NEXTPAGE_OUTCOME_CODE__OK_NO_RESULT = "Ok_No_Result";
		final String NEXTPAGE_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R470233735) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			PaginazioneTrans pt = theModel.getAppDatapaginazioneTrans();
			int nPage = pt.getNumeroPagina();
			if ((nPage * pt.getDimensionePagina()) == 1) {
				result.setResultCode("Ok");
				result.setModel(theModel);
				return result;
			} else {
				nPage++;
				pt.setNumeroPagina(nPage);
				//Transazione tr0 = theModel.getAppDatatransazioni().get(0);
				VtransazioneResult ris = null;
				try {
					ris = getGestoreMDPBackOffice()
							.getListaTransazioniByFiltro(user,
									theModel.getAppDataricercaTransazione(),
									pt.getNumeroPagina(),
									pt.getDimensionePagina());
				} catch (Exception e) {
					String faultString = e.getMessage();
					String message = pu.getMessage("TR-E-0101") + " Exception"
							+ pu.getMessage("TR-E-0102")
							+ " getAppDataricercaTransazione()"
							+ pu.getMessage("TR-E-0103") + faultString
							+ pu.getMessage("TR-E-0104");
					result.setResultCode(NEXTPAGE_OUTCOME_CODE__KO);
					result.getGlobalErrors().add(message);
					result.setModel(theModel);
					return result;
				}

				Vtransazione[] listaTrans = ris.getListTransazioni();
				int totTransazioniFound = ris.getTotTransactionsFound();
				pt.setTotale(totTransazioniFound);
				theModel.setAppDatapaginazioneTrans(pt);
				ArrayList<Transazione> aTrans = new ArrayList<Transazione>();
				if (listaTrans.length == 0) {
					log.info("[GestoreMDPBackOffice::getListaTransazioniByFiltro]  lista null ");
				} else {
					for (int k = 0; k < listaTrans.length; k++) {
						Vtransazione trnWs = listaTrans[k];
						Transazione trn = new Transazione();
						trn.setIdApplicazione(trnWs.getApplicationId());
						trn.setNomeApplicazione(trnWs.getApplicationname());
						trn.setCodStato(trnWs.getCodStato());
						trn.setOldstate(trnWs.getOldstate());
						trn.setIdTransazione(trnWs.getTransactionId());
						trn.setDataTransazione(UtilDate
								.getConvertCalendarToString(
										trnWs.getFinishDate(),
										"dd/MM/yyyy HH:mm:ss"));
						trn.setDataInizio(UtilDate.getConvertCalendarToString(
								trnWs.getInitDate(), "dd/MM/yyyy HH:mm:ss"));
						trn.setDescrStato(trnWs.getDescrizione());
						trn.setNomeApplicazione(trnWs.getApplicationname());
						trn.setDescrGateway(trnWs.getGatewayDescription());
						trn.setDescrPayment(trnWs.getPaymentmodeDescription());
						trn.setImportoTransazione(trnWs.getAmount());
						trn.setNumeroPagina(pt.getNumeroPagina());
						trn.setTransPerPagina(pt.getNumeroPagina());
						trn.setBasketId(trnWs.getBasketId());
						trn.setMerchantId(trnWs.getMerchantId());
						trn.setPaymentid(trnWs.getPaymentid());
						trn.setPayurl(trnWs.getPayurl());
						trn.setPgresultcode(trnWs.getPgresultcode());
						trn.setStartDate(UtilDate.getConvertCalendarToString(
								trnWs.getStartDate(), "dd/MM/yyyy HH:mm:ss"));
						aTrans.add(trn);
					}
				}
				theModel.setAppDatatransazioni(aTrans);
				String resultCode = "";
				resultCode = (NEXTPAGE_OUTCOME_CODE__OK_NO_RESULT);
				if (aTrans != null) {
					if (aTrans.size() == 0) {
						if (totTransazioniFound == 0) {
							resultCode = (NEXTPAGE_OUTCOME_CODE__OK_NO_RESULT);
						} else {
							resultCode = NEXTPAGE_OUTCOME_CODE__OK_PREV;
						}
					} else {
						int sizePage = pt.getDimensionePagina();
						if (totTransazioniFound <= sizePage) {
							resultCode = NEXTPAGE_OUTCOME_CODE__OK;
							pt.setNumeroPagina(1);
							nPage = 1;
						} else if (nPage == 1) {
							resultCode = NEXTPAGE_OUTCOME_CODE__OK_NEXT;
						} else if ((nPage + 1) * sizePage > totTransazioniFound) {
							resultCode = NEXTPAGE_OUTCOME_CODE__OK_PREV;
						} else {
							resultCode = NEXTPAGE_OUTCOME_CODE__OK_PREV_NEXT;
						}
						theModel.setAppDatapaginazioneTrans(pt);
						result.setResultCode(resultCode);
					}
					if (pt.getTotale() > 100) {
						String sOverflow = "ATTENZIONE : Il numero di transazioni che soddisfano i criteri di ricerca e' maggiore di 100."
								+ " Vengono recuperate solo le prime 100. "
								+ "Si consiglia di affinare i criteri di ricerca.";
						theModel.setAppDatareportCercaTransazioni(sOverflow);
					}
					String rptRicerca = "Totale transazioni trovate: "
							+ pt.getTotale()
							+ " - Visualizzato Blocco "
							+ nPage
							+ " ("
							+ ((pt.getNumeroPagina() - 1)
									* pt.getDimensionePagina() + 1) + "..."
							+ (pt.getNumeroPagina() * pt.getDimensionePagina())
							+ ")";
					theModel.setAppDatareportCercaTransazioni(rptRicerca);
					result.getGlobalMessages().add(rptRicerca);
					result.setModel(theModel);
				}
				result.setModel(theModel);
				return result;
			}
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::nextPage] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults dettaglioTransazione(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneTransazioniModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String DETTAGLIOTRANSAZIONE_OUTCOME_CODE__OK = "Ok";
		final String DETTAGLIOTRANSAZIONE_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-548849770) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();

			String idTransazione = theModel.getAppDataselettoreIdTransazione();

			if (idTransazione == null || idTransazione.equals("")) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0001"));
				result.setResultCode(DETTAGLIOTRANSAZIONE_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			} else {
				Transazione trans = null;
				try {
					trans = getGestoreMDPBackOffice().getTransazioneById(user,
							idTransazione);
				} catch (Exception e) {
					String faultString = e.getMessage();
					String message = pu.getMessage("TR-E-0101") + " Exception"
							+ pu.getMessage("TR-E-0102")
							+ " getTransazioneById()"
							+ pu.getMessage("TR-E-0103") + faultString
							+ pu.getMessage("TR-E-0104");
					result.setResultCode(DETTAGLIOTRANSAZIONE_OUTCOME_CODE__KO);
					result.getGlobalErrors().add(message);
					result.setModel(theModel);
					return result;
				}
				if (trans == null) {
					result.getGlobalErrors().add(pu.getMessage("TR-E-0002"));;
					result.setResultCode(DETTAGLIOTRANSAZIONE_OUTCOME_CODE__KO);
					result.setModel(theModel);
					return result;
				}
				trans.setCodNuovoStato(trans.getCodStato());
				theModel.setAppDatatransazione(trans);
				result.setResultCode(DETTAGLIOTRANSAZIONE_OUTCOME_CODE__OK);
			}
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::dettaglioTransazione] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneTransazioniModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__OK_INIZIALE = "Ok_Iniziale";
		final String REFRESHPANEL_OUTCOME_CODE__OK_NO_RESULTS = "Ok_No_Results";
		final String REFRESHPANEL_OUTCOME_CODE__OK_SOME_RESULTS = "Ok_Some_Results";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-136382304) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			// impostazione del result code
			result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK_INIZIALE);
			String isPostBack = theModel.getAppDataisPostBack();
			if (isPostBack == null || isPostBack.equals("")
					|| isPostBack.equals("N")) {
				ArrayList<Applicazione> aApp = new ArrayList<Applicazione>();
				ArrayList<Applicazione> aAppFromWS = new ArrayList<Applicazione>();
				// INIZIALIZZA DIMENSIONE BLOCCHI 500
				theModel.setAppDatasizeBlocco(500);
				// Inizializza paginazione
				PaginazioneTrans Pt = new PaginazioneTrans();
				Pt.setDimensionePagina(theModel.getAppDatasizeBlocco());
				Pt.setNumeroPagina(1);
				Pt.setTotale(0);
				theModel.setAppDatapaginazioneTrans(Pt);
				aAppFromWS = getGestoreMDPBackOffice()
						.getListaApplicazioniByUser(user);
				Collections.sort(aAppFromWS,
						Comparators.APPLICAZIONE_COMPARATOR);

				Applicazione fakeApp = new Applicazione();
				fakeApp.setIdApplicazione("SCEGLI_APPLICAZIONE");
				fakeApp.setNomeApplicazione("Nessun filtro sulla applicazione");
				aApp.add(fakeApp);
				for (int y = 0; y < aAppFromWS.size(); y++) {
					Applicazione app = aAppFromWS.get(y);
					aApp.add(app);
				}
				theModel.setAppDataapplicazioni(aApp);
				Transazione trans = theModel.getAppDataricercaTransazione();
				if (trans == null) {
					trans = new Transazione();
				}
				trans.setCodStato(-1);
				trans.setIdApplicazione("SCEGLI_APPLICAZIONE");
				ArrayList<StatoTransazione> aStatiTransFromWS = new ArrayList<StatoTransazione>();
				aStatiTransFromWS = getGestoreMDPBackOffice()
						.getStatiTransazione(user);
				ArrayList<StatoTransazione> aStatiTrans = new ArrayList<StatoTransazione>();
				ArrayList<StatoTransazione> aStatiTrans2 = new ArrayList<StatoTransazione>();
				StatoTransazione fakeTrans = new StatoTransazione();
				fakeTrans.setCodStato(-1);
				fakeTrans.setDescrStato("Nessun filtro sullo stato");
				aStatiTrans.add(fakeTrans);
				for (int y = 0; y < aStatiTransFromWS.size(); y++) {
					StatoTransazione staTrans = aStatiTransFromWS.get(y);
					aStatiTrans.add(staTrans);
					if (staTrans.getCodStato() > 3) {
						StatoTransazione staTrans2 = aStatiTransFromWS.get(y);
						aStatiTrans2.add(staTrans2);
					}
				}
				theModel.setAppDatastatiTransazione(aStatiTrans);
				theModel.setAppDatastatiTransazionexCambio(aStatiTrans2);
				theModel.setAppDataricercaTransazione(trans);
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK_INIZIALE);
				theModel.setAppDataisPostBack("S");
			} else {
				if (theModel.getAppDatatransazioni() == null
						|| theModel.getAppDatatransazioni().size() == 0) {
					result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK_NO_RESULTS);
				} else {
					result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK_SOME_RESULTS);
				}
			}

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::refreshPanel] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi statici per il reset delle tabelle
	//////////////////////////////////////////////////////////////////////////////

	public static void resetClearStatus_widg_tRicerca(java.util.Map session) {
		session.put("cpGestioneTransazioni_tRicerca_clearStatus", Boolean.TRUE);
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R853618972) ENABLED START*/
	//// inserire qui le property che si vogliono iniettare in questo bean (es. dao, proxy di pd, ...) 
	GestoreMDPBackOffice gestoreMDPBackOffice = null;

	public GestoreMDPBackOffice getGestoreMDPBackOffice() {
		return gestoreMDPBackOffice;
	}

	public void setGestoreMDPBackOffice(
			GestoreMDPBackOffice gestoreMDPBackOffice) {
		this.gestoreMDPBackOffice = gestoreMDPBackOffice;
	}

	/*PROTECTED REGION END*/
}
