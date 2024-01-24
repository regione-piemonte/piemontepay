/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.business.mgr;

import it.csi.mdp.mdpboweb.business.MDPNODONAZIONALEServiceHelper;
import it.csi.mdp.mdpboweb.business.MDPServiceHelper;
import it.csi.mdp.mdpboweb.business.ws.Application;
import it.csi.mdp.mdpboweb.business.ws.ApplicationGatewayConfiguration;
import it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields;
import it.csi.mdp.mdpboweb.business.ws.Auditactions;
import it.csi.mdp.mdpboweb.business.ws.CodiciEsitoPagamentoDTO;
import it.csi.mdp.mdpboweb.business.ws.Commission;
import it.csi.mdp.mdpboweb.business.ws.Config;
import it.csi.mdp.mdpboweb.business.ws.Credentials;
import it.csi.mdp.mdpboweb.business.ws.CsiLogAudit;
import it.csi.mdp.mdpboweb.business.ws.Gatewaycustomfields;
import it.csi.mdp.mdpboweb.business.ws.Gatewaydetail;
import it.csi.mdp.mdpboweb.business.ws.GetApplicationConfigurationResponse;
import it.csi.mdp.mdpboweb.business.ws.GetCodiciEsitoPagamentoAll;
import it.csi.mdp.mdpboweb.business.ws.GetGiornaleEventoByIdResponse;
import it.csi.mdp.mdpboweb.business.ws.GetIbanEnteApplicationByParam;
import it.csi.mdp.mdpboweb.business.ws.GetRPTByParam;
import it.csi.mdp.mdpboweb.business.ws.GetRTByParam;
import it.csi.mdp.mdpboweb.business.ws.GetStatiRptAll;
import it.csi.mdp.mdpboweb.business.ws.GetStatisticaApplicazioneTransazione;
import it.csi.mdp.mdpboweb.business.ws.GiornaleEventoDTO;
import it.csi.mdp.mdpboweb.business.ws.IbanEnteApplicationDTO;
import it.csi.mdp.mdpboweb.business.ws.Language;
import it.csi.mdp.mdpboweb.business.ws.ListApplicationByFlussoApplicazione;
import it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroupappmapping;
import it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroups;
import it.csi.mdp.mdpboweb.business.ws.MdpBckroles;
import it.csi.mdp.mdpboweb.business.ws.MdpBckrolesgroupmap;
import it.csi.mdp.mdpboweb.business.ws.MdpBckusers;
import it.csi.mdp.mdpboweb.business.ws.MdpBckusersgroup;
import it.csi.mdp.mdpboweb.business.ws.MdpBoServicesCxfServiceSoapBindingStub;
import it.csi.mdp.mdpboweb.business.ws.MdpCurrency;
import it.csi.mdp.mdpboweb.business.ws.Paymentmode;
import it.csi.mdp.mdpboweb.business.ws.Rptdto;
import it.csi.mdp.mdpboweb.business.ws.Rtdto;
import it.csi.mdp.mdpboweb.business.ws.SetApplicationCustomFieldsResponse;
import it.csi.mdp.mdpboweb.business.ws.SetApplicationResponse;
import it.csi.mdp.mdpboweb.business.ws.StatiRptDTO;
import it.csi.mdp.mdpboweb.business.ws.StatisticaApplicazioneTransazioneDTO;
import it.csi.mdp.mdpboweb.business.ws.VtransazioneResult;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.AssociazioneGW_PM;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.Audit;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.Azione;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.CodiciEsitoPagamento;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.Divisa;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.Enti;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.Errore;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoRiversamento;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.Gateway;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.GiornaleEventi;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.Gruppo;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.GruppoApplicazione;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.GruppoRuolo;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.IbanEnteApplication;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.Lingua;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.ParametroConfigurazioneBO;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.PaymentMode;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.RicevuteTelematiche;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.RichiestaPagamentoTelematico;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.Ruolo;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.StatiRpt;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.StatisticaApplicazioneTransazione;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoTransazione;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.TipologiaCommissione;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.Transazione;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.UtenteGruppo;
import it.csi.mdp.mdpboweb.util.Constants;
import it.csi.mdp.mdpboweb.util.MdpboProperties;
import it.csi.mdp.mdpboweb.util.NumberUtil;
import it.csi.mdp.mdpboweb.util.StringUtil;
import it.csi.mdp.mdpboweb.util.UtilDate;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.axis.AxisFault;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

public class GestoreMDPBackOffice {
	// ///////////////////////////////////////////////////////////////////////
	// GESTORE_MDP_BACKOFFICE
	//
	// ///////////////////////////////////////////////////////////////////////
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	/**
	 * 
	 * @param user
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */

	public ArrayList<ParametroConfigurazioneBO> getListaParametriConfigurazione(
			UserInfoExt user) throws AxisFault, MalformedURLException,
			RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getListaParametriConfigurazione]  BEGIN ");
		ArrayList<ParametroConfigurazioneBO> listaRes = new ArrayList<ParametroConfigurazioneBO>();

		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		it.csi.mdp.mdpboweb.business.ws.Config[] listaConfig = MDPHelper
				.getBoConfig(userCred);
		if (listaConfig.length > 0) {
			for (int k = 0; k < listaConfig.length; k++) {
				Config conf = listaConfig[k];
				ParametroConfigurazioneBO newPar = new ParametroConfigurazioneBO();
				newPar.setIdParametro(conf.getKey());
				newPar.setDescrParametro(conf.getDescrizione());
				newPar.setValore(conf.getValue());
				listaRes.add(newPar);
			}
		}

		log.info("[GestoreMDPBackOffice::getListaParametriConfigurazione]  END ");
		return listaRes;
	}

	/**
	 * 
	 * @param user
	 * @param inpPC
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String salvaParametroConfigurazioneBO(UserInfoExt user,
			ParametroConfigurazioneBO inpPC) throws AxisFault,
			MalformedURLException, RemoteException, Exception {
		String ris = "";
		log.info("[GestoreMDPBackOffice::salvaParametroConfigurazioneBO]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MDPHelper.setBoConfig(userCred, inpPC.getIdParametro(),
				inpPC.getValore(), inpPC.getDescrParametro());

		log.info("[GestoreMDPBackOffice::salvaParametroConfigurazioneBO]  END ");
		return ris;
	}

	/**
	 * 
	 * @param user
	 * @param inpPC
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String eliminaParametroConfigurazioneBO(UserInfoExt user,
			ParametroConfigurazioneBO inpPC) throws AxisFault,
			MalformedURLException, RemoteException, Exception {
		String ris = "";
		log.info("[GestoreMDPBackOffice::salvaParametroConfigurazioneBO]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		it.csi.mdp.mdpboweb.business.ws.Config cnf = new it.csi.mdp.mdpboweb.business.ws.Config();
		cnf.setKey(inpPC.getIdParametro());
		cnf.setValue(inpPC.getValore());
		cnf.setDescrizione(inpPC.getDescrParametro());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MDPHelper.deleteBoConfig(userCred, cnf);

		log.info("[GestoreMDPBackOffice::salvaParametroConfigurazioneBO]  END ");
		return ris;
	}

	/**
	 * 
	 * @param user
	 * @param trans
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void aggiornaStatoTransazione(UserInfoExt user, Transazione trans)
			throws AxisFault, MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::aggiornaStatoTransazione]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MDPHelper.setStatoTransazione(userCred, trans);
		log.info("[GestoreMDPBackOffice::aggiornaStatoTransazione]  END ");
	}

	/**
	 * 
	 * @param user
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public ArrayList<Utente> getListaUtentiByUser(UserInfoExt user)
			throws AxisFault, MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getListaUtentiByUser]  BEGIN ");
		ArrayList<Utente> listaRes = new ArrayList<Utente>();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MdpBckusers[] listaUsers = MDPHelper.getMdpUsers(userCred);
		if (listaUsers == null) {
			log.info("[GestoreMDPBackOffice::getListaUtentiByUser]  lista utenti null ");
		} else {
			for (int k = 0; k < listaUsers.length; k++) {
				MdpBckusers wsus = listaUsers[k];
				Utente us1 = new Utente();
				us1.setIdUtente(String.valueOf(wsus.getIduser()));
				us1.setDescrUtente(wsus.getFirstname() + " "
						+ StringUtil.convertNull(wsus.getLastname(), ""));
				us1.setCodFisc(wsus.getCodfisc());
				us1.setEmail(wsus.getEmail());
				us1.setPwdservizio(wsus.getPwdservizio());
				listaRes.add(us1);
			}
		}
		log.info("[GestoreMDPBackOffice::getListaUtentiByUser]  END ");
		return listaRes;
	}

	/**
	 * 
	 * @param user
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public ArrayList<Azione> getListaAzioniByUser(UserInfoExt user)
			throws AxisFault, MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getListaAzioniByUser]  BEGIN ");
		ArrayList<Azione> listaRes = new ArrayList<Azione>();

		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		Auditactions[] listaAzioni = MDPHelper.getAuditActionsList(userCred);
		if (listaAzioni == null) {
			log.info("[GestoreMDPBackOffice::getListaAzioniByUser]  lista azioni null ");
		} else {
			for (int k = 0; k < listaAzioni.length; k++) {
				Auditactions wsus = listaAzioni[k];
				Azione az1 = new Azione();
				az1.setIdAzione(wsus.getIdaction());
				az1.setDescrAzione(wsus.getDescription());
				listaRes.add(az1);
			}
		}

		log.info("[GestoreMDPBackOffice::getListaAzioniByUser]  END ");
		return listaRes;
	}

	/**
	 * 
	 * @param user
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public ArrayList<Applicazione> getListaApplicazioniByUser(UserInfoExt user)
			throws AxisFault, MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getListaApplicazioniByUser]  BEGIN ");
		ArrayList<Applicazione> listaRes = new ArrayList<Applicazione>();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		Application[] listaApplication = MDPHelper.getApplication(userCred);
		if (listaApplication == null) {
			log.info("[GestoreMDPBackOffice::getListaApplicazioniByUser]  lista applicazioni null ");
		} else {
			for (int k = 0; k < listaApplication.length; k++) {
				Application wsApp = listaApplication[k];
				Applicazione app1 = new Applicazione();
				app1.setIdApplicazione(wsApp.getId());
				app1.setCliente(wsApp.getCliente());
				app1.setNomeApplicazione(wsApp.getId() + " - "
						+ wsApp.getApplicationname());
				app1.setReferenteCSI(wsApp.getReferentecsi());
				listaRes.add(app1);
			}
		}
		log.info("[GestoreMDPBackOffice::getListaApplicazioniByUser]  END ");
		return listaRes;
	}

	/**
	 * 
	 * @param user
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public ArrayList<StatoTransazione> getListaStatiByUser(UserInfoExt user)
			throws AxisFault, MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getListaStatiByUser]  BEGIN ");
		ArrayList<StatoTransazione> listaRes = new ArrayList<StatoTransazione>();

		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		it.csi.mdp.mdpboweb.business.ws.StatoTransazione[] listaStati = MDPHelper
				.getStatiTransazione(userCred);
		if (listaStati == null) {
			log.info("[GestoreMDPBackOffice::getListaStatiByUser]  lista applicazioni null ");
		} else {
			for (int k = 0; k < listaStati.length; k++) {
				it.csi.mdp.mdpboweb.business.ws.StatoTransazione wsSta = listaStati[k];
				StatoTransazione sta1 = new StatoTransazione();
				sta1.setCodStato(wsSta.getCodStato());
				sta1.setDescrStato(wsSta.getDescrizione());
				listaRes.add(sta1);
			}
		}

		log.info("[GestoreMDPBackOffice::getListaStatiByUser]  END ");
		return listaRes;
	}

	/**
	 * 
	 * @param user
	 * @param filtro
	 * @param numeroPagina
	 * @param transPerPagina
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public VtransazioneResult getListaTransazioniByFiltro(UserInfoExt user,
			Transazione filtro, int numeroPagina, int transPerPagina)
			throws AxisFault, MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getListaTransazioniByFiltro]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		Calendar dInizio = null;
		if (filtro.getCercaDataInizio() != null
				&& !filtro.getCercaDataInizio().equals("")) {
			dInizio = UtilDate.getConvertStringToCalendar(filtro
					.getCercaDataInizio());
		}

		Calendar dFine = null;
		if (filtro.getCercaDataFine() != null
				&& !filtro.getCercaDataFine().equals("")) {
			dFine = UtilDate.getConvertStringToCalendar(filtro
					.getCercaDataFine());
		}

		String pIdApplicazione = null;
		if (!filtro.getIdApplicazione().equals("SCEGLI_APPLICAZIONE")) {
			pIdApplicazione = filtro.getIdApplicazione();
		}

		VtransazioneResult result = MDPHelper
				.getTransazioneViewWithFiltersPaged(userCred, pIdApplicazione,
						filtro.getCodStato(), dInizio, dFine, numeroPagina,
						transPerPagina);
		log.info("[GestoreMDPBackOffice::getListaTransazioniByFiltro]  END ");

		return result;

	}

	/**
	 * 
	 * @param user
	 * @param idTransazione
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public Transazione getTransazioneById(UserInfoExt user, String idTransazione)
			throws AxisFault, MalformedURLException, RemoteException, Exception {
		// Chiama i servizi MDP
		log.info("[GestoreMDPBackOffice::getTransazioneById]  BEGIN ");
		Transazione trn = null;
		it.csi.mdp.mdpboweb.business.ws.Vtransazione trnWs = null;

		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		trnWs = MDPHelper
				.getTransazioneViewById(userCred, idTransazione.trim());
		trn = new Transazione();
		trn.setIdApplicazione(trnWs.getApplicationId());
		trn.setNomeApplicazione(trnWs.getApplicationname());
		trn.setIdTransazione(trnWs.getTransactionId());
		trn.setDataTransazione(UtilDate.getConvertCalendarToString(trnWs
				.getFinishDate(),"dd/MM/yyyy HH:mm:ss"));
		trn.setDataFine(UtilDate.getConvertCalendarToString(trnWs
				.getFinishDate(),"dd/MM/yyyy HH:mm:ss"));
		trn.setDataInizio(UtilDate.getConvertCalendarToString(trnWs
				.getInitDate(),"dd/MM/yyyy HH:mm:ss"));
		trn.setDataUltimaOperazione(UtilDate.getConvertCalendarToString(trnWs
				.getChangestatedate(),"dd/MM/yyyy HH:mm:ss"));
		trn.setDescrStato(trnWs.getDescrizione());
		trn.setCodStato(trnWs.getCodStato());
		trn.setOldstate(trnWs.getOldstate());
		trn.setNomeApplicazione(trnWs.getApplicationname());
		trn.setDescrGateway(trnWs.getGatewayDescription());
		trn.setDescrPayment(trnWs.getPaymentmodeDescription());
		trn.setImportoTransazione(trnWs.getAmount());
		trn.setBasketId(trnWs.getBasketId());
		trn.setMerchantId(trnWs.getMerchantId());
		trn.setPaymentid(trnWs.getPaymentid());
		trn.setPayurl(trnWs.getPayurl());
		trn.setPgresultcode(trnWs.getPgresultcode());
		trn.setStartDate(UtilDate.getConvertCalendarToString(trnWs
				.getStartDate(),"dd/MM/yyyy HH:mm:ss"));


		log.info("[GestoreMDPBackOffice::getTransazioneById]  END ");
		return trn;
	}

	/**
	 * 
	 * @param user
	 * @param filtro
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public ArrayList<Errore> getListaErroriByFiltro(UserInfoExt user,
			Errore filtro) throws AxisFault, MalformedURLException,
			RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getListaErroriByFiltro]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		Calendar dInizio = null;
		if (filtro.getCercaDataInizio() != null
				&& !filtro.getCercaDataInizio().equals("")) {
			dInizio = UtilDate.getConvertStringToCalendar(filtro
					.getCercaDataInizio());
		}
		Calendar dFine = null;
		if (filtro.getCercaDataFine() != null
				&& !filtro.getCercaDataFine().equals("")) {
			dFine = UtilDate.getConvertStringToCalendar(filtro
					.getCercaDataFine());
		}
		String pIdApplicazione = null;
		if (!filtro.getIdApplicazione().equals("SCEGLI_APPLICAZIONE")) {
			pIdApplicazione = filtro.getIdApplicazione();
		}

		String pIdTransazione = null;
		if (filtro.getIdTransazione() != null
				&& !filtro.getIdTransazione().equals("")) {
			pIdTransazione = filtro.getIdTransazione();
		}

		String pIdGateway = null;
		if (!filtro.getIdGateway().equals("SCEGLI_GATEWAY")) {
			pIdGateway = filtro.getIdGateway();
		}

		it.csi.mdp.mdpboweb.business.ws.Verrori[] listaErrori = MDPHelper
				.getListaErroriByFiltro(userCred, pIdApplicazione,
						pIdTransazione, pIdGateway, dInizio, dFine);

		ArrayList<Errore> listaRes = new ArrayList<Errore>();

		if (listaErrori == null) {
			log.info("[GestoreMDPBackOffice::getListaErroriByFiltro]  lista null ");
		} else {
			for (int k = 0; k < listaErrori.length; k++) {
				it.csi.mdp.mdpboweb.business.ws.Verrori ws = listaErrori[k];
				Errore err = new Errore();
				err.setBuyerName("");
				err.setBuyerNameCF("");
				err.setDataTransazione(UtilDate.getConvertCalendarToString(ws
						.getInitDate(),"dd/MM/yyyy HH:mm:ss"));
				err.setDescrGateway(ws.getGatewayDescription());
				err.setTestoErroreCompleto(ws.getDescrizione());
				if (ws.getDescrizione().length() > 70) {
					err.setDettaglioErrore(ws.getDescrizione().substring(0, 70));
				} else {
					err.setDettaglioErrore(ws.getDescrizione());
				}
				err.setIdApplicazione(ws.getApplicationId());
				err.setIdGateway(ws.getGatewayId());
				err.setIdTransazione(ws.getTransactionId());
				err.setImportoTransazione(ws.getAmount());
				err.setNomeApplicazione(ws.getApplicationId() + " - "
						+ ws.getApplicationname());
				err.setIdErrore(String.valueOf(k + 1));
				listaRes.add(err);
			}
		}
		log.info("[GestoreMDPBackOffice::getListaErroriByFiltro]  END ");
		return listaRes;

	}

	/**
	 * 
	 * @param user
	 * @param datestartFilter
	 * @param dateendFilter
	 * @param transactionidFilter
	 * @param applicationListFilter
	 * @param actionsFilter
	 * @param usersFilter
	 * @return
	 */
	public ArrayList<Audit> getListaAuditByFiltro(UserInfoExt user,
			String datestartFilter, String dateendFilter,
			String transactionidFilter,
			ArrayList<Applicazione> applicationListFilter,
			ArrayList<Azione> actionsFilter, ArrayList<Utente> usersFilter)
			throws AxisFault, MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getListaAuditByFiltro]  BEGIN ");
		ArrayList<Audit> listaRes = new ArrayList<Audit>();

		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		Application[] appList = new Application[applicationListFilter.size()];
		String[] aziList = new String[actionsFilter.size()];
		String[] userList = new String[usersFilter.size()];
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		for (int s = 0; s < applicationListFilter.size(); s++) {
			Applicazione app = applicationListFilter.get(s);
			Application appListSel = new Application();
			if (!app.getIdApplicazione().equals("@@@@@@NONE@@@@@@")) {
				appListSel.setId(app.getIdApplicazione());
				appList[s] = appListSel;
			} else {
				appList = new Application[0];
			}
		}

		for (int f = 0; f < actionsFilter.size(); f++) {
			if (!actionsFilter.get(f).getIdAzione().equals("@@@@@@NONE@@@@@@")) {
				aziList[f] = actionsFilter.get(f).getIdAzione();
			} else {
				aziList = new String[0];
			}
		}

		for (int g = 0; g < usersFilter.size(); g++) {
			if (!usersFilter.get(g).getIdUtente().equals("@@@@@@NONE@@@@@@")) {
				userList[g] = usersFilter.get(g).getIdUtente();
			} else {
				userList = new String[0];
			}
		}

		CsiLogAudit[] listaAuditing = MDPHelper.getAuditing(
				userCred,
				UtilDate.getConvertStringToCalendar(datestartFilter),
				new UtilDate().addDay(
						UtilDate.getConvertStringToCalendar(dateendFilter), 1),
				transactionidFilter, appList, aziList, userList);
		if (listaAuditing == null) {
			log.info("[GestoreMDPBackOffice::getListaAuditByFiltro]  lista null ");
		} else {

			MdpBckusers[] us = MDPHelper.getMdpUsers(userCred);
			//HashMap map = new HashMap();
			HashMap<Integer, String> map = new HashMap<Integer, String>();
			for (int k = 0; k < us.length; k++) {
				map.put(us[k].getIduser(), us[k].getFirstname());
			}
			
			for (int k = 0; k < listaAuditing.length; k++) {
				CsiLogAudit ws = listaAuditing[k];
				Audit au = new Audit();
				au.setDataAudit(UtilDate.getConvertCalendarToString(ws.getDataOra(),"dd/MM/yyyy HH:mm:ss"));
				au.setDescrApplicazione(ws.getIdApp());
				au.setDescrOperazione(ws.getOperazione());
				au.setIdTransazione(ws.getTransactionid());
				au.setCodfisc(ws.getCodfisc());
				au.setIdaction(ws.getIdaction());
				au.setDescrUser("" + map.get(ws.getUtente()));
				listaRes.add(au);
			}
		}

		log.info("[GestoreMDPBackOffice::getListaAuditByFiltro]  END ");
		return listaRes;
	}

	/**
	 * getApplicazioneById()
	 * 
	 * @param user
	 * @param idApplicazione
	 * @return Applicazione
	 */
	public Applicazione getApplicazioneById(UserInfoExt user,
			String idApplicazione) throws AxisFault, MalformedURLException,
			RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getApplicazioneById]  BEGIN ");
		Application app = null;
		Applicazione app1 = null;

		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		app = MDPHelper.getApplicationById(userCred, idApplicazione);
		app1 = new Applicazione();
		app1.setCliente(app.getCliente());
		app1.setDataFineValidita(UtilDate.getConvertCalendarToString(app
				.getValidoAl()));
		app1.setDataInizioValidita(UtilDate.getConvertCalendarToString(app
				.getValidoDal()));
		app1.setEmailEsercente(app.getEsercemail());
		app1.setIdApplicazione(app.getId());
		app1.setNomeApplicazione(app.getApplicationname());
		app1.setNote(app.getNote());
		app1.setProgetto(app.getProgetto());
		app1.setReferenteCSI(app.getReferentecsi());
		app1.setNumeroVerde(app.getNumeroverde());

		log.info("[GestoreMDPBackOffice::getApplicazioneById]  END ");
		return app1;
	}

	/**
	 * 
	 * @param user
	 * @param appa
	 */
	public String setApplicazione(UserInfoExt user,
			Applicazione app,boolean overwrite) throws  MalformedURLException,
			RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::setApplicazione]  BEGIN ");
		SetApplicationResponse appres = null;
		String out = "";
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		Application appServizio = new Application();
		appServizio.setApplicationname(app.getNomeApplicazione());
		appServizio.setCliente(app.getCliente());
		appServizio.setValidoAl(UtilDate.getConvertStringToCalendar(app
				.getDataFineValidita()));
		appServizio.setValidoDal(UtilDate.getConvertStringToCalendar(app
				.getDataInizioValidita()));
		appServizio.setEsercemail(app.getEmailEsercente());
		appServizio.setId(app.getIdApplicazione());
		appServizio.setApplicationname(app.getNomeApplicazione());
		appServizio.setNote(app.getNote());
		appServizio.setProgetto(app.getProgetto());
		appServizio.setReferentecsi(app.getReferenteCSI());
		appServizio.setNumeroverde(app.getNumeroVerde());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		try{
			appres = MDPHelper.setApplication(userCred, appServizio, overwrite);
		} catch (AxisFault e) {
			out = e.getFaultString();
		}
		log.info("[GestoreMDPBackOffice::setApplicazione]  END ");
		return out;
	}

	/**
	 * 
	 * @param user
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public ArrayList<StatoTransazione> getStatiTransazione(UserInfoExt user)
			throws AxisFault, MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getStatiTransazione]  BEGIN ");
		ArrayList<StatoTransazione> listaRes = new ArrayList<StatoTransazione>();

		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		it.csi.mdp.mdpboweb.business.ws.StatoTransazione[] listaStati = MDPHelper
				.getStatiTransazione(userCred);
		if (listaStati == null) {
			log.info("[GestoreMDPBackOffice::getStatiTransazione]  lista applicazioni null ");
		} else {
			for (int k = 0; k < listaStati.length; k++) {
				it.csi.mdp.mdpboweb.business.ws.StatoTransazione wsSta = listaStati[k];
				StatoTransazione sta1 = new StatoTransazione();
				sta1.setCodStato(wsSta.getCodStato());
				sta1.setDescrStato(wsSta.getDescrizione());
				listaRes.add(sta1);
			}
		}

		log.info("[GestoreMDPBackOffice::getStatiTransazione]  END ");
		return listaRes;
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	public ArrayList<Gateway> getListaGatewayByUser(UserInfoExt user)
			throws AxisFault, MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getListaGatewayByUser]  BEGIN ");
		ArrayList<Gateway> listaRes = new ArrayList<Gateway>();

		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		it.csi.mdp.mdpboweb.business.ws.Gateway[] listaGateway = MDPHelper
				.getGateway(userCred);
		if (listaGateway == null) {
			log.info("[GestoreMDPBackOffice::getListaGatewayByUser]  lista  null ");
		} else {
			for (int k = 0; k < listaGateway.length; k++) {
				it.csi.mdp.mdpboweb.business.ws.Gateway wsGat = listaGateway[k];
				Gateway gat = new Gateway();
				gat.setDataFineValidita(UtilDate
						.getConvertCalendarToString(wsGat.getValidoAl()));
				gat.setDataInizioValidita(UtilDate
						.getConvertCalendarToString(wsGat.getValidoDal()));
				
				log.info("[GestoreMDPBackOffice::getListaGatewayByUser]  descr " + wsGat.getGatewayDescription());
				gat.setDescrGateway(wsGat.getGatewayDescription());
				gat.setIdGateway(wsGat.getGatewayId());
				gat.setProviderGateway(wsGat.getGatewayProvider());
				gat.setServiceJNDIName(wsGat.getGatewayservicename());
				listaRes.add(gat);
			}
		}

		log.info("[GestoreMDPBackOffice::getListaGatewayByUser]  END ");
		return listaRes;
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	public ArrayList<PaymentMode> getListaPaymentModeByUser(UserInfoExt user)
			throws AxisFault, MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getListaPaymentModeByUser]  BEGIN ");
		ArrayList<PaymentMode> listaRes = new ArrayList<PaymentMode>();

		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		it.csi.mdp.mdpboweb.business.ws.Paymentmode[] listaPaymentMode = MDPHelper
				.getPaymentMode(userCred);
		if (listaPaymentMode == null) {
			log.info("[GestoreMDPBackOffice::getListaPaymentModeByUser]  lista  null ");
		} else {
			for (int k = 0; k < listaPaymentMode.length; k++) {
				it.csi.mdp.mdpboweb.business.ws.Paymentmode wsPayMws = listaPaymentMode[k];
				PaymentMode PayM = new PaymentMode();
				PayM.setDataFineValidita(UtilDate
						.getConvertCalendarToString(wsPayMws.getValidoAl()));
				PayM.setDataInizioValidita(UtilDate
						.getConvertCalendarToString(wsPayMws.getValidoAl()));
				PayM.setDescrPayment(wsPayMws.getPaymentmodeDescription());
				PayM.setIdPayment(wsPayMws.getPaymentmodeId());
				listaRes.add(PayM);
			}
		}

		log.info("[GestoreMDPBackOffice::getListaPaymentModeByUser]  END ");
		return listaRes;
	}

	/**
	 * 
	 * @param idApplicazione
	 * @param chiave
	 * @param valore
	 */
	public void insExtraAttribute(UserInfoExt user, String idApplicazione,
			String chiave, String valore) throws AxisFault,
			MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::insExtraAttribute]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MDPHelper.setApplicationCustomFields(userCred, chiave, valore);
		log.info("[GestoreMDPBackOffice::insExtraAttribute]  END ");
	}

	/**
	 * 
	 * @param user
	 * @param idApplicazione
	 * @return
	 */
	public ArrayList<ExtraAttribute> getListaExtraAttributeByUser(
			UserInfoExt user, String idApplicazione) throws AxisFault,
			MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getListaExtraAttributeByUser]  BEGIN ");
		ArrayList<ExtraAttribute> listaRes = new ArrayList<ExtraAttribute>();
	
			Credentials userCred = new Credentials();
			userCred.setCodfisc(user.getCodFisc());
			userCred.setPwdAuth(user.getPwBck());
			MDPServiceHelper MDPHelper = new MDPServiceHelper();
			it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[] listaExtraAttribute = MDPHelper
					.getApplicationCustomFields(userCred, idApplicazione);
			if (listaExtraAttribute == null) {
				log.info("[GestoreMDPBackOffice::getListaExtraAttributeByUser]  lista  null ");
			} else {
				for (int k = 0; k < listaExtraAttribute.length; k++) {
					it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields wsAcf = listaExtraAttribute[k];
					ExtraAttribute acf = new ExtraAttribute();
					acf.setIdApplicazione(wsAcf.getApplicationid());
					acf.setChiave(wsAcf.getKeyid());
					acf.setValore(wsAcf.getFieldvalue());
					listaRes.add(acf);
				}
			}
		
		log.info("[GestoreMDPBackOffice::getListaExtraAttributeByUser]  END ");
		return listaRes;
	}

	/**
	 * 
	 * @param user
	 * @param idApplicazione
	 * @return
	 */
	public ArrayList<AssociazioneGW_PM> getApplicationConfiguration(
			UserInfoExt user, String idApplicazione) throws AxisFault,
			MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getApplicationConfiguration]  BEGIN ");
		ArrayList<AssociazioneGW_PM> listaRes = new ArrayList<AssociazioneGW_PM>();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		GetApplicationConfigurationResponse appconfres = MDPHelper
				.getApplicationConfiguration(userCred, idApplicazione);
		ApplicationGatewayConfiguration[] appconf = appconfres
				.getApplicationConfiguration()
				.getApplicationGatewayConfigurationsList();
		if (appconf == null) {
			log.info("[GestoreMDPBackOffice::getApplicationConfiguration]  lista  null ");
		} else {
			for (int k = 0; k < appconf.length; k++) {
				ApplicationGatewayConfiguration ws = appconf[k];
				AssociazioneGW_PM assgwpm = new AssociazioneGW_PM();
				assgwpm.setIdGateway(ws.getGatewayid());
				assgwpm.setCodIstat(ws.getCodiceistat());
				assgwpm.setDescrGateway(ws.getGatewaydescription());
				assgwpm.setIdPayment(ws.getPaymentmodeid());
				assgwpm.setDescrPayment(ws.getPaymodedescription());
				assgwpm.setIdEsercente(ws.getMerchantid());
				assgwpm.setPasswordEsercente(ws.getMerchantidpassword());
				assgwpm.setMACAvvio(ws.getMacAvvio());
				assgwpm.setIdTipologiaComm(ws.getTipocommissione());
				assgwpm.setCodIstat(ws.getCodiceistat());

				assgwpm.setAbilitazione(new NumberUtil()
						.convertiStringToBoolean(ws.getEnabled(), "1"));
				assgwpm.setMail2Buyerko(new NumberUtil()
						.convertiStringToBoolean(ws.getMail2Buyerko(), "1"));
				assgwpm.setMail2Buyerok(new NumberUtil()
						.convertiStringToBoolean(ws.getMail2Buyerok(), "1"));
				assgwpm.setMail2Esercko(new NumberUtil()
						.convertiStringToBoolean(ws.getMail2Esercko(), "1"));
				assgwpm.setMail2Esercok(new NumberUtil()
						.convertiStringToBoolean(ws.getMail2Esercok(), "1"));
				assgwpm.setMail2Sysko(new NumberUtil().convertiStringToBoolean(
						ws.getMail2Sysko(), "1"));
				assgwpm.setMail2Sysok(new NumberUtil().convertiStringToBoolean(
						ws.getMail2Sysok(), "1"));
				assgwpm.setApplicationurlback(ws.getApplicationurlback());
				assgwpm.setApplicationurlresponseko(ws
						.getApplicationurlresponseko());
				assgwpm.setApplicationurlresponseok(ws
						.getApplicationurlresponseok());
				assgwpm.setChiave(idApplicazione + ws.getGatewayid()
						+ ws.getPaymentmodeid());
				if (ws.getValorecommissionemin() >= 0) {
					assgwpm.setImpMin(NumberUtil.floatToPercentuale(ws
							.getValorecommissionemin()));
				}

				if (ws.getValorecommissionemax() >= 0) {
					assgwpm.setImpMax(NumberUtil.floatToPercentuale(ws
							.getValorecommissionemax()));
				}

				if (ws.getSogliaa() >= 0) {
					
					assgwpm.setSogliaa(NumberUtil.floatToImporto(ws
							.getSogliaa()));
				}
				listaRes.add(assgwpm);
			}
		}
		log.info("[GestoreMDPBackOffice::getApplicationConfiguration]  END ");
		return listaRes;
	}

	/**
	 * 
	 * @param user
	 * @param idgate
	 * @return ArrayList<PaymentMode>
	 */
	public ArrayList<PaymentMode> getPaymentModeByIdGateway(UserInfoExt user,
			String idgate) throws AxisFault, MalformedURLException,
			RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getPaymentModeByIdGateway]  BEGIN ");
		ArrayList<PaymentMode> listaRes = new ArrayList<PaymentMode>();

		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		Gatewaydetail[] listGatewaydetail = MDPHelper
				.getGatewayDetailByIdAbilitati(userCred, idgate);
		ArrayList<PaymentMode> pm = getListaPaymentModeByUser(user);
		if (listGatewaydetail == null) {
			log.info("[GestoreMDPBackOffice::getPaymentModeByIdGateway]  lista  null ");
		} else {
			for (int k = 0; k < listGatewaydetail.length; k++) {
				Gatewaydetail ws = listGatewaydetail[k];
				PaymentMode PayM = new PaymentMode();
				PayM.setIdPayment(ws.getPaymentmodeId());
				// ciclo per estrarre la descrizione
				for (int p = 0; p < pm.size(); p++) {
					PaymentMode pmsel = pm.get(p);
					if (pmsel.getIdPayment().equals(ws.getPaymentmodeId())) {
						PayM.setDescrPayment(pmsel.getDescrPayment());
						break;
					}
				}
				String abilitato = ws.getEnabled();
				abilitato = StringUtil.convertNull(abilitato, "0");
				listaRes.add(PayM);
			}
		}

		log.info("[GestoreMDPBackOffice::getPaymentModeByIdGateway]  END ");
		return listaRes;
	}

	/**
	 * 
	 * @param user
	 * @param applicationid
	 * @param idGateway
	 * @param idPayment
	 * @param abilitazione
	 * @param idEsercente
	 * @param passwordEsercente
	 * @param mACAvvio
	 * @param tipologiaCommissione
	 * @param svalCommMin
	 * @param svalCommMax
	 * @param valSoglia
	 * @param mail2Buyerko
	 * @param mail2Buyerok
	 * @param mail2Esercko
	 * @param mail2Esercok
	 * @param mail2Sysko
	 * @param mail2Sysok
	 * @param applicationurlback
	 * @param applicationurlresponseko
	 * @param applicationurlresponseok
	 * @param codiceistat
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 * serve per l'inserimento delle associazioni gatewai e paymentmode
	 * 
	 */
	 
	public void setApplicationDetail(UserInfoExt user, String applicationid,
			String idGateway, String idPayment, boolean abilitazione,
			String idEsercente, String passwordEsercente, String mACAvvio,
			String tipologiaCommissione, String svalCommMin,
			String svalCommMax, String valSoglia, boolean mail2Buyerko,
			boolean mail2Buyerok, boolean mail2Esercko, boolean mail2Esercok,
			boolean mail2Sysko, boolean mail2Sysok, String applicationurlback,
			String applicationurlresponseko, String applicationurlresponseok,
			String codiceistat) throws AxisFault, MalformedURLException,
			RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::setApplicationDetail]  BEGIN ");
		String sabilitato = NumberUtil.convertiBooleanToString(abilitazione,"1");
		String smail2Buyerko = NumberUtil.convertiBooleanToString(mail2Buyerko,"1");
		String smail2Buyerok = NumberUtil.convertiBooleanToString(mail2Buyerok,"1");
		String smail2Esercko = NumberUtil.convertiBooleanToString(mail2Esercko,"1");
		String smail2Esercok = NumberUtil.convertiBooleanToString(mail2Esercok,"1");
		String smail2Sysko = NumberUtil.convertiBooleanToString(mail2Sysko, "1");
		String smail2Sysok = NumberUtil.convertiBooleanToString(mail2Sysok, "1");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MDPHelper
				.setApplicationDetail(userCred, applicationid, idGateway,
						idPayment, sabilitato, idEsercente, passwordEsercente,
						mACAvvio, tipologiaCommissione, svalCommMin,
						svalCommMax, valSoglia, smail2Buyerko, smail2Buyerok,
						smail2Esercko, smail2Esercok, smail2Sysko, smail2Sysok,
						applicationurlback, applicationurlresponseko,
						applicationurlresponseok, codiceistat);
		log.info("[GestoreMDPBackOffice::setApplicationDetail]  END ");
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	public ArrayList<TipologiaCommissione> getTipologiaCommissione(
			UserInfoExt user) throws AxisFault, MalformedURLException,
			RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getTipologiaCommissione]  BEGIN ");
		ArrayList<TipologiaCommissione> listaRes = new ArrayList<TipologiaCommissione>();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		Commission[] commission = MDPHelper.getTipoCommissione(userCred);
		for (int k = 0; k < commission.length; k++) {
			Commission ws = commission[k];
			TipologiaCommissione tc = new TipologiaCommissione();
			tc.setCommissionId(ws.getCommissionId());
			tc.setCommissionDescription(ws.getCommissionDescription());
			listaRes.add(tc);
		}

		log.info("[GestoreMDPBackOffice::getTipologiaCommissione]  END ");
		return listaRes;
	}

	/**
	 * 
	 * @param user
	 * @param applicationid
	 * @param idGateway
	 * @param listaExtraAtt
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setApplicationCustomFields(UserInfoExt user,
			String applicationid, String idGateway,
			ArrayList<ExtraAttribute> listaExtraAtt) throws AxisFault,
			MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::setApplicationCustomFields]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		if (listaExtraAtt != null && listaExtraAtt.size() > 0) {
			Applicationcustomfields[] appcustfields = new Applicationcustomfields[listaExtraAtt
					.size()];
			for (int k = 0; k < listaExtraAtt.size(); k++) {
				ExtraAttribute easel = listaExtraAtt.get(k);
				Applicationcustomfields appcustfield = new Applicationcustomfields();
				appcustfield.setApplicationid(applicationid);
				appcustfield.setGatewayId(idGateway);
				appcustfield.setFieldvalue(easel.getValore());
				appcustfield.setFielddescription(easel.getDescrizione());
				appcustfield.setFieldname(easel.getNome());
				appcustfields[k] = appcustfield;
			}
			MDPHelper.setApplicationCustomFields(userCred, appcustfields);
		}
		log.info("[GestoreMDPBackOffice::setApplicationCustomFields]  END ");
	}

	/**
	 * 
	 * @param user
	 * @param idGateway
	 * @param idApplication
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public ArrayList<ExtraAttribute> getGatewayCustomFields(UserInfoExt user,
			String idGateway, String idApplication) throws AxisFault,
			MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getGatewayCustomFields]  BEGIN ");
		ArrayList<ExtraAttribute> listaResDefault = new ArrayList<ExtraAttribute>();
		ArrayList<ExtraAttribute> listaResApp = new ArrayList<ExtraAttribute>();
		ArrayList<ExtraAttribute> listaRes = new ArrayList<ExtraAttribute>();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		Gatewaycustomfields[] gatewaycustomfieldsDefault = MDPHelper
				.getGatewayCustomFields(userCred, idGateway);
		Applicationcustomfields[] gatewaycustomfields = MDPHelper
				.getGatewayCustomFieldsByGateway(userCred, idApplication,
						idGateway);
		for (int k = 0; k < gatewaycustomfieldsDefault.length; k++) {
			Gatewaycustomfields val = gatewaycustomfieldsDefault[k];
			ExtraAttribute tc = new ExtraAttribute();
			tc.setChiave(val.getKeyid());
			tc.setDescrizione(val.getFielddescription());
			tc.setIdApplicazione(idApplication);
			tc.setValore(val.getFieldvalue());
			tc.setNome(val.getFieldname());
			tc.setGatewayId(val.getGatewayId());
			listaResDefault.add(tc);
		}

		for (int k = 0; k < gatewaycustomfields.length; k++) {
			Applicationcustomfields val = gatewaycustomfields[k];
			ExtraAttribute tc = new ExtraAttribute();
			tc.setChiave(val.getKeyid());
			tc.setDescrizione(val.getFielddescription());
			tc.setIdApplicazione(idApplication);
			tc.setValore(val.getFieldvalue());
			tc.setNome(val.getFieldname());
			tc.setGatewayId(val.getGatewayId());
			listaResApp.add(tc);
		}

		if (listaResDefault.size() > listaResApp.size()) {
			for (int k = 0; k < listaResDefault.size(); k++) {
				ExtraAttribute defsel = listaResDefault.get(k);
				ExtraAttribute appSel = null;
				boolean semaforo = false;
				for (int a = 0; k < listaResApp.size(); a++) {
					semaforo = false;
					appSel = listaResDefault.get(a);
					if (defsel.getGatewayId() == appSel.getGatewayId()
							&& defsel.getNome() == appSel.getNome()) {
						semaforo = true;
						break;
					}
				}
				if (semaforo) {
					listaRes.add(appSel);
				} else {
					listaRes.add(defsel);
				}
			}
		} else {
			listaRes = listaResApp;
		}
		log.info("[GestoreMDPBackOffice::getGatewayCustomFields]  END ");
		return listaRes;
	}

	/**
	 * 
	 * @param user
	 * @param idGateway
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public ArrayList<ExtraAttribute> getGatewayCustomFields(UserInfoExt user,
			String idGateway) throws AxisFault, MalformedURLException,
			RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getGatewayCustomFields]  BEGIN ");
		ArrayList<ExtraAttribute> listaResDefault = new ArrayList<ExtraAttribute>();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		Gatewaycustomfields[] gatewaycustomfieldsDefault = MDPHelper
				.getGatewayCustomFields(userCred, idGateway);
		for (int k = 0; k < gatewaycustomfieldsDefault.length; k++) {
			Gatewaycustomfields val = gatewaycustomfieldsDefault[k];
			ExtraAttribute tc = new ExtraAttribute();
			tc.setChiave(String.valueOf(k + 1));
			tc.setDescrizione(val.getFielddescription());
			tc.setValore(val.getFieldvalue());
			tc.setNome(val.getFieldname());
			tc.setGatewayId(val.getGatewayId());
			listaResDefault.add(tc);
		}

		log.info("[GestoreMDPBackOffice::getGatewayCustomFields]  END ");
		return listaResDefault;
	}

	/**
	 * 
	 * @param user
	 * @param idGateway
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public Gateway getGatewayById(UserInfoExt user, String idGateway)
			throws AxisFault, MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getGatewayById]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		it.csi.mdp.mdpboweb.business.ws.Gateway wsGateway = MDPHelper.getGatewayById(userCred, idGateway);
		Gateway gateway = new Gateway();
		gateway.setIdGateway(wsGateway.getGatewayId());
		gateway.setDescrGateway(wsGateway.getGatewayDescription());
		gateway.setProviderGateway(wsGateway.getGatewayProvider());
		gateway.setServiceJNDIName(wsGateway.getGatewayservicename());
		log.info("[GestoreMDPBackOffice::getGatewayById]  END ");
		return gateway;
	}

	/**
	 * 
	 * @param user
	 * @param idGateway
	 * @param descrGateway
	 * @param providerGateway
	 * @param serviceJNDIName
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String setGateway(UserInfoExt user, String idGateway,
			String descrGateway, String providerGateway, String serviceJNDIName)
			throws AxisFault, MalformedURLException, RemoteException, Exception {

		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		return MDPHelper
				.setGateway(userCred, idGateway, descrGateway, providerGateway,
						serviceJNDIName).getGateway().getGatewayId();
	}

	/**
	 * 
	 * @param user
	 * @param idPaymentMod
	 * @param descrPaymentMode
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setPaymentMode(UserInfoExt user, String idPaymentMod,
			String descrPaymentMode) throws AxisFault, MalformedURLException,
			RemoteException, Exception {
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MDPHelper.setPaymentMode(userCred, idPaymentMod, descrPaymentMode);
	}

	/**
	 * 
	 * @param user
	 * @param idPaymentMod
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public PaymentMode getPaymentModeById(UserInfoExt user, String idPaymentMod)
			throws AxisFault, MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getPaymentModeById]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		it.csi.mdp.mdpboweb.business.ws.Paymentmode wsPaymentMode = MDPHelper
				.getPaymentModeById(userCred, idPaymentMod);
		PaymentMode payMod = new PaymentMode();
		payMod.setIdPayment(wsPaymentMode.getPaymentmodeId());
		payMod.setDescrPayment(wsPaymentMode.getPaymentmodeDescription());
		log.info("[GestoreMDPBackOffice::getPaymentModeById]  END ");
		return payMod;
	}

	/**
	 * 
	 * @param user
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public ArrayList<AssociazioneGW_PM> getGatewayDetail(UserInfoExt user)
			throws AxisFault, MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getGatewayDetail]  BEGIN ");

		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		ArrayList<AssociazioneGW_PM> listaRet = new ArrayList<AssociazioneGW_PM>();

		Gatewaydetail[] wsGatewaydetail = MDPHelper.getGatewayDetail(userCred);

		HashMap<String, String> map = new HashMap<String, String>();
		
		Paymentmode[] listaPm = MDPHelper.getPaymentMode(userCred);
		
		log.info("estraggo la mappa");
	    for(int i=0;i<listaPm.length;i++){
	    	it.csi.mdp.mdpboweb.business.ws.Paymentmode wsPayMws = listaPm[i];
	    	 map.put(wsPayMws.getPaymentmodeId(), wsPayMws.getPaymentmodeDescription() );
	    }
		
		
		for (int a = 0; a < wsGatewaydetail.length; a++) {
			Gatewaydetail wsGatewaydetailSel = wsGatewaydetail.clone()[a];
			AssociazioneGW_PM associazione = new AssociazioneGW_PM();

			associazione.setChiave(wsGatewaydetailSel.getGatewayId()
					+ wsGatewaydetailSel.getPaymentmodeId());
			associazione.setIdGateway(wsGatewaydetailSel.getGatewayId());

			Gateway gateway = getGatewayById(user, associazione.getIdGateway());
			associazione.setDescrGateway(gateway.getDescrGateway());
			associazione.setIdPayment(wsGatewaydetailSel.getPaymentmodeId());
			associazione.setDescrPayment(map.get(wsGatewaydetailSel.getPaymentmodeId()));
			
			
			associazione.setVerificaesito(wsGatewaydetailSel.isVerificaesito());
			
			associazione.setDefaultPaymentUrl(wsGatewaydetailSel.getDefaultpaymenturl());
			associazione.setReturnUrl(wsGatewaydetailSel.getReturnurlmdp());
			associazione.setReceiptUrl(wsGatewaydetailSel.getReceipturl());
			associazione.setCancelUrl(wsGatewaydetailSel.getErrorurl());
			associazione.setAbilitazione(new NumberUtil().convertiStringToBoolean(wsGatewaydetailSel.getEnabled(),"1"));

			if (wsGatewaydetailSel.getHttpport() >= 0) {
				associazione.setHttpport(String.valueOf(wsGatewaydetailSel.getHttpport()));
			}
			associazione.setContextpg(wsGatewaydetailSel.getContextpg());
			associazione.setMdpgatewaypage(wsGatewaydetailSel.getMdpgatewaypage());
			associazione.setCodIstat("");

			listaRet.add(associazione);
		}
		log.info("[GestoreMDPBackOffice::getGatewayDetail]  END ");
		return listaRet;
	}

	/**
	 * 
	 * @param user
	 * @param Chiave
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public AssociazioneGW_PM getGatewayDetailSelezionato(UserInfoExt user,
			String Chiave) throws AxisFault, MalformedURLException,
			RemoteException, Exception {
		ArrayList<AssociazioneGW_PM> listaAssociazioneGW_PM = getGatewayDetail(user);
		AssociazioneGW_PM ris = new AssociazioneGW_PM();
		for (int a = 0; a < listaAssociazioneGW_PM.size(); a++) {
			AssociazioneGW_PM sel = listaAssociazioneGW_PM.get(a);
			if (sel.getChiave().equals(Chiave)) {
				ris = listaAssociazioneGW_PM.get(a);
			}
		}
		return ris;
	}

	/**
	 * 
	 * @param user
	 * @param backofficeurl
	 * @param contextpg
	 * @param defaultpaymenturl
	 * @param enabled
	 * @param errorurl
	 * @param gatewayId
	 * @param httpport
	 * @param httpportNull
	 * @param mdpgatewaypage
	 * @param paymentmodeId
	 * @param receipturl
	 * @param returnurlmdp
	 * @param sendmailonresponse
	 * @param verificaesito
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setGatewayDetail(UserInfoExt user, String backofficeurl,
			String contextpg, String defaultpaymenturl, boolean enabled,
			String errorurl, String gatewayId, String httpport,
			boolean httpportNull, String mdpgatewaypage, String paymentmodeId,
			String receipturl, String returnurlmdp, String sendmailonresponse,
			boolean verificaesito) throws AxisFault, MalformedURLException,
			RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::setGatewayDetail]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());

		String abilitazione = NumberUtil.convertiBooleanToString(enabled, "1");

		httpport = StringUtil.convertNull(httpport, "-1");
		MDPServiceHelper MDPHelper = new MDPServiceHelper();

		MDPHelper.setGatewayDetail(userCred, backofficeurl, contextpg,
				defaultpaymenturl, abilitazione, errorurl, gatewayId,
				Long.valueOf(httpport), httpportNull, mdpgatewaypage,
				paymentmodeId, receipturl, returnurlmdp, sendmailonresponse,
				verificaesito);
		log.info("[GestoreMDPBackOffice::setGatewayDetail]  END ");
	}

	/**
	 * 
	 * @param user
	 * @param file
	 * @param idApplicazione
	 * @param filename
	 * @param contentType
	 * @param idGateway
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String uploadMethodForApplicationGateway(UserInfoExt user,
			File file, String idApplicazione, String filename,
			String contentType, String idGateway) throws AxisFault,
			MalformedURLException, RemoteException, Exception {
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());

		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		return MDPHelper.uploadMethodForApplicationGateway(userCred, file,
				idApplicazione, filename, contentType, idGateway);
	}

	/**
	 * 
	 * @param user
	 * @param idGateway
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public ArrayList<Lingua> getLanguagesByGatewayId(UserInfoExt user,
			String idGateway) throws AxisFault, MalformedURLException,
			RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getLanguagesByGatewayId]  BEGIN ");
		ArrayList<Lingua> listaRes = new ArrayList<Lingua>();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());

		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		Language[] language = MDPHelper.getLanguagesByGatewayId(userCred,
				idGateway);

		for (int k = 0; k < language.length; k++) {
			Language ws = language[k];
			Lingua li = new Lingua();
			li.setChiave(ws.getGatewayid() + ws.getGtwlanguagecode()
					+ ws.getMdplanguagecode());
			li.setIdLinguaGateway(ws.getGtwlanguagecode());
			li.setIdLinguaMdp(ws.getMdplanguagecode());
			li.setIdGateway(ws.getGatewayid());
			li.setDescLingua(ws.getLanguagedescr());
			listaRes.add(li);
		}
		log.info("[GestoreMDPBackOffice::getLanguagesByGatewayId]  END ");
		return listaRes;
	}

	/**
	 * 
	 * @param user
	 * @param idGateway
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public ArrayList<Divisa> getMdpCurrencyByGatewayId(UserInfoExt user,
			String idGateway) throws AxisFault, MalformedURLException,
			RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getMdpCurrencyByGatewayId]  BEGIN ");
		ArrayList<Divisa> listaRes = new ArrayList<Divisa>();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());

		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MdpCurrency[] currency = MDPHelper.getMdpCurrencyByGatewayId(userCred,
				idGateway);
		for (int k = 0; k < currency.length; k++) {
			MdpCurrency ws = currency[k];
			Divisa li = new Divisa();
			li.setChiave(ws.getGatewayid() + ws.getGtwcurrencycode()
					+ ws.getMdpcurrencycode());
			li.setIdDivisaGateway(ws.getGtwcurrencycode());
			li.setIdDivisaMdp(ws.getMdpcurrencycode());
			li.setIdGateway(ws.getGatewayid());
			li.setDescDivisa(ws.getCurrencydescr());
			listaRes.add(li);
		}
		log.info("[MDPServiceHelper::getMdpCurrencyByGatewayId]  END ");
		return listaRes;
	}

	/**
	 * 
	 * @param user
	 * @param utente
	 * @param ug
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String setMdpUsers(UserInfoExt user, String iduser, String codfisc,
			String email, String firstname, String lastname,
			String pwdservizio, ArrayList<UtenteGruppo> ug) throws 
			MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::setMdpUsers]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MdpBckusers users = new MdpBckusers();
		String out ="";
		// da gestire l'inserimento
		if (iduser != null && !iduser.trim().equals("")) {
			users.setIduser(Integer.parseInt(iduser));
		} else {
			users.setIduser(-1);
		}
		users.setCodfisc(codfisc);
		users.setEmail(email);
		users.setFirstname(firstname);
		users.setLastname(lastname);
		users.setPwdservizio(pwdservizio);
		
		MdpBckusersgroup[] usersGroupList = new MdpBckusersgroup[ug.size()];
		for (int r = 0; r < ug.size(); r++) {
			MdpBckusersgroup usersGroupsel = new MdpBckusersgroup();
			usersGroupsel.setIdgroup(Integer.parseInt(ug.get(r).getIdgroup()));

			if (ug.get(r).getIdUtente() != null) {
				usersGroupsel.setIduser(Integer.parseInt(ug.get(r).getIdUtente()));
			}
			usersGroupList[r] = usersGroupsel;
		}
		try{
			MDPHelper.setMdpUsers(userCred, users, usersGroupList);
		} catch (AxisFault e) {
			out = e.getFaultString();
		}
		log.info("[GestoreMDPBackOffice::setMdpUsers]  END ");
		return out;
	}

	/**
	 * 
	 * @param user
	 * @param listEA
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setGatewayCustomFields(UserInfoExt user,
			ArrayList<ExtraAttribute> listEA) throws AxisFault,
			MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::setGatewayCustomFields]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());

		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		Gatewaycustomfields[] listGatewayCF = new Gatewaycustomfields[listEA
				.size()];
		for (int i = 0; i < listEA.size(); i++) {
			ExtraAttribute easel = listEA.get(i);
			Gatewaycustomfields rec = new Gatewaycustomfields();
			rec.setGatewayId(easel.getGatewayId());
			rec.setFielddescription(easel.getDescrizione());
			rec.setFieldname(easel.getNome());
			listGatewayCF[i] = rec;
		}
		MDPHelper.setGatewayCustomFields(userCred, listGatewayCF);
		log.info("[GestoreMDPBackOffice::setGatewayCustomFields]  END ");
	}

	public void setGatewayCustomFields(UserInfoExt user,
			ArrayList<ExtraAttribute> listEA, String idGateway)
			throws AxisFault, MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::setGatewayCustomFields]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());

		MDPServiceHelper MDPHelper = new MDPServiceHelper();

		Gatewaycustomfields[] listGatewayCF = new Gatewaycustomfields[listEA.size()];
		for (int i = 0; i < listEA.size(); i++) {
			ExtraAttribute easel = listEA.get(i);
			Gatewaycustomfields rec = new Gatewaycustomfields();
			rec.setGatewayId(idGateway);
			rec.setFielddescription(easel.getDescrizione());
			rec.setFieldname(easel.getNome());
			listGatewayCF[i] = rec;
		}
		
		MDPHelper.setGatewayCustomFields(userCred, listGatewayCF);
		log.info("[GestoreMDPBackOffice::setGatewayCustomFields]  END ");
	}

	/**
	 * 
	 * @param user
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public ArrayList<Gruppo> getMdpBckGroups(UserInfoExt user) throws AxisFault, MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getMdpGroup]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());

		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MdpBckofficegroups[] listgru = MDPHelper.getMdpBckGroups(userCred);

		ArrayList<Gruppo> listGruppi = new ArrayList<Gruppo>();

		for (int n = 0; n < listgru.length; n++) {
			MdpBckofficegroups gru = listgru[n];
			Gruppo gruppo = new Gruppo();
			gruppo.setIdgroup(String.valueOf(gru.getIdgroup()));
			gruppo.setDescription(gru.getDescription());
			listGruppi.add(gruppo);
		}
		log.info("[GestoreMDPBackOffice::getMdpGroup]  END ");
		return listGruppi;
	}

	/**
	 * 
	 * @param user
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public ArrayList<Ruolo> getMdpBckRoles(UserInfoExt user) throws AxisFault,MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getMdpBckRoles]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MdpBckroles[] ruoli = MDPHelper.getMdpBckRoles(userCred);
		ArrayList<Ruolo> listaRuoli = new ArrayList<Ruolo>();
		for (int a = 0; a < ruoli.length; a++) {
			MdpBckroles ruolows = ruoli[a];
			Ruolo ruolo = new Ruolo();
			ruolo.setIdRuolo(String.valueOf(ruolows.getIdrole()));
			ruolo.setDescrRuolo(ruolows.getRoledescr());
			listaRuoli.add(ruolo);
		}
		log.info("[GestoreMDPBackOffice::getMdpBckRoles]  END ");
		return listaRuoli;
	}

	/**
	 * 
	 * @param user
	 * @param ruolo
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setMdpRole(UserInfoExt user, Ruolo ruolo) throws AxisFault,MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::setMdpRole]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MdpBckroles roles = new MdpBckroles();
		roles.setIdrole(Integer.parseInt(ruolo.getIdRuolo()));
		roles.setRoledescr(ruolo.getDescrRuolo());
		MDPHelper.setMdpRole(userCred, roles);
		log.info("[GestoreMDPBackOffice::setMdpRole]  END ");
	}

	/**
	 * 
	 * @param user
	 * @param divisa
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setMdpCurrency(UserInfoExt user, Divisa divisa) throws AxisFault, MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::setMdpCurrency]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MdpCurrency mdpCurrency = new MdpCurrency();
		mdpCurrency.setEnabled("1");
		mdpCurrency.setGtwcurrencycode(divisa.getIdDivisaGateway());
		mdpCurrency.setMdpcurrencycode(divisa.getIdDivisaMdp());
		mdpCurrency.setGatewayid(divisa.getIdGateway());
		mdpCurrency.setCurrencydescr(divisa.getDescDivisa());
		MDPHelper.setMdpCurrency(userCred, mdpCurrency);
		log.info("[GestoreMDPBackOffice::setMdpCurrency]  END ");
	}

	/**
	 * 
	 * @param user
	 * @param gruppo
	 * @param listGruppoApp
	 * @param gruppoRuolo
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setMdpGroup(UserInfoExt user, Gruppo gruppo,
			ArrayList<GruppoApplicazione> listGruppoApp, GruppoRuolo gruppoRuolo)
			throws AxisFault, MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::setMdpGroup]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MdpBckofficegroupappmapping[] appslist = new MdpBckofficegroupappmapping[listGruppoApp.size()];

		for (int s = 0; s < listGruppoApp.size(); s++) {
			GruppoApplicazione sel = listGruppoApp.get(s);
			MdpBckofficegroupappmapping appslistSel = new MdpBckofficegroupappmapping();
			appslistSel.setIdapp(sel.getIdapp());
			if (gruppo.getIdgroup() != null && !gruppo.getIdgroup().equals("")) {
				appslistSel.setIdgroup(Integer.parseInt(sel.getIdgroup()));
			}
			appslist[s] = appslistSel;
		}

		MdpBckofficegroups group = new MdpBckofficegroups();
		if (gruppo.getIdgroup() != null && !gruppo.getIdgroup().equals("")) {
			group.setIdgroup(Integer.parseInt(gruppo.getIdgroup()));
		}
		group.setDescription(gruppo.getDescription());
		MdpBckrolesgroupmap grouprole = new MdpBckrolesgroupmap();
		if (gruppoRuolo.getIdgroup() != null
				&& !gruppoRuolo.getIdgroup().equals("")) {
			grouprole.setIdgroup(Integer.parseInt(gruppoRuolo.getIdgroup()));
		}
		if (gruppoRuolo.getIdruolo() != null
				&& !gruppoRuolo.getIdruolo().equals("")) {
			grouprole.setIdrole(Integer.parseInt(gruppoRuolo.getIdruolo()));
		}
		MDPHelper.setMdpGroup(userCred, group, appslist, grouprole);
		log.info("[GestoreMDPBackOffice::setMdpGroup]  END ");
	}

	/**
	 * 
	 * @param user
	 * @param id
	 * @return Utente
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public Utente getMdpUsersById(UserInfoExt user, String id) throws AxisFault, MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getMdpUsersById]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MdpBckusers us = MDPHelper.getMdpUsersById(userCred, id);
		Utente ute = new Utente();
		ute.setIdUtente(String.valueOf(us.getIduser()));
		ute.setDescrUtente(us.getFirstname() + " " + us.getLastname());
		ute.setCodFisc(us.getCodfisc());
		ute.setEmail(us.getEmail());
		ute.setPwdservizio(us.getPwdservizio());
		if (us.getUsergrp() != null && us.getUsergrp().length > 0) {
			ute.setIdGruppo(String.valueOf(us.getUsergrp(0).getIdgroup()));
		}
		log.info("[GestoreMDPBackOffice::getMdpUsersById]  END ");
		return ute;
	}

	/**
	 * 
	 * @param user
	 * @param lin
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setLanguage(UserInfoExt user, Lingua lin) throws AxisFault,
			MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::setLanguage]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		Language language = new Language();
		language.setEnabled("1");
		language.setGatewayid(lin.getIdGateway());
		language.setGtwlanguagecode(lin.getIdLinguaGateway());
		language.setLanguagedescr(lin.getDescLingua());
		language.setMdplanguagecode(lin.getIdLinguaMdp());
		MDPHelper.setLanguage(userCred, language);
		log.info("[GestoreMDPBackOffice::setLanguage]  END ");
	}

	/**
	 * 
	 * @param user
	 * @param codFisc
	 * @return ArrayList<Gruppo>
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public ArrayList<Gruppo> getMdpBckGroupsByCfisc(UserInfoExt user,
			String codFisc) throws AxisFault, MalformedURLException,
			RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getMdpBckGroupsByCfisc]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());

		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MdpBckofficegroups[] listgru = MDPHelper.getMdpBckGroupsByCfisc(userCred, codFisc);

		ArrayList<Gruppo> listGruppi = new ArrayList<Gruppo>();

		for (int n = 0; n < listgru.length; n++) {
			MdpBckofficegroups gru = listgru[n];
			Gruppo gruppo = new Gruppo();
			gruppo.setIdgroup(String.valueOf(gru.getIdgroup()));
			gruppo.setDescription(gru.getDescription());
			listGruppi.add(gruppo);
		}
		log.info("[GestoreMDPBackOffice::getMdpBckGroupsByCfisc]  END ");
		return listGruppi;
	}

	/**
	 * @param user
	 * @param applicationId
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String deleteApplicationConfiguration(UserInfoExt user,
			String applicationId) throws MalformedURLException,
			RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::deleteApplicationConfiguration]  BEGIN ");
		String out = "";
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper;
		try {
			MDPHelper = new MDPServiceHelper();
			MDPHelper.deleteApplicationConfiguration(userCred, applicationId);
		} catch (AxisFault e) {
			out = e.getFaultString();
		}
		log.info("[GestoreMDPBackOffice::deleteApplicationConfiguration]  END ");
		return out;
	}

	/**
	 * @param user
	 * @param applicationId
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void deleteApplication(UserInfoExt user, String applicationId)
			throws AxisFault, MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::deleteApplication]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MDPHelper.deleteApplication(userCred, applicationId);
		log.info("[GestoreMDPBackOffice::deleteApplication]  END ");
	}

	/**
	 * @param user
	 * @param gatewayId
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void deleteApplicationCustomFields(UserInfoExt user, String gatewayId)
			throws AxisFault, MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::deleteApplicationCustomFields]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MDPHelper.deleteApplicationCustomFields(userCred, gatewayId);
		log.info("[GestoreMDPBackOffice::deleteApplicationCustomFields]  END ");
	}

	/** 
	 * @param user
	 * @param applicationId
	 * @param gatewayId
	 * @param paymodeId
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void deleteApplicationDetail(UserInfoExt user, String applicationId,
			String gatewayId, String paymodeId) throws AxisFault,
			MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::deleteApplicationDetail]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MDPHelper.deleteApplicationDetails(userCred, applicationId, gatewayId,
				paymodeId);
		log.info("[GestoreMDPBackOffice::deleteApplicationDetail]  END ");
	}

	/**
	 * 
	 * @param user
	 * @param applicationId
	 * @param gatewayId
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void deleteGateway(UserInfoExt user, String applicationId,
			String gatewayId) throws AxisFault, MalformedURLException,
			RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::deleteGateway]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MDPHelper.deleteGateway(userCred, gatewayId);
		log.info("[GestoreMDPBackOffice::deleteGateway]  END ");
	}

	/**
	 * 
	 * @param user
	 * @param applicationId
	 * @param gatewayId
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String deleteGatewayConfiguration(UserInfoExt user, String gatewayId)
			throws MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::deleteGatewayConfiguration]  BEGIN ");
		String out = "";
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		try {
			MDPServiceHelper MDPHelper = new MDPServiceHelper();
			MDPHelper.deleteGatewayConfiguration(userCred, gatewayId);
		} catch (AxisFault e) {
			out = e.getFaultString();
		}
		log.info("[GestoreMDPBackOffice::deleteGatewayConfiguration]  END ");
		return out;

	}
	/**
	 * 
	 * @param user
	 * @param applicationId
	 * @param gatewayId
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void deleteGatewayCustomFields(UserInfoExt user,
			String applicationId, String gatewayId) throws AxisFault,
			MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::deleteGatewayCustomFields]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MDPHelper.deleteGatewayCustomFields(userCred, gatewayId);
		log.info("[GestoreMDPBackOffice::deleteGatewayCustomFields]  END ");
	}

	/**
	 * 
	 * @param user
	 * @param applicationId
	 * @param gatewayId
	 * @param paymodeId
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void deleteGatewayDetail(UserInfoExt user, String applicationId,
			String gatewayId, String paymodeId) throws AxisFault,
			MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::deleteGatewayDetail]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MDPHelper.deleteGatewayDetail(userCred, gatewayId, paymodeId);
		log.info("[GestoreMDPBackOffice::deleteGatewayDetail]  END ");
	}

	/**
	 * 
	 * @param user
	 * @param applicationId
	 * @param userId
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String deleteMdpBckUser(UserInfoExt user, String userId)
			throws MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::deleteMdpBckUser]  BEGIN ");
		String out = "";
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		try {
			MDPHelper.deleteMdpBckUser(userCred, Integer.parseInt(userId));
		} catch (AxisFault e) {
			out = e.getFaultString();
		}
		log.info("[GestoreMDPBackOffice::deleteMdpBckUser]  END ");
		return out;
	}

	/**
	 * 
	 * @param user
	 * @param applicationId
	 * @param groupId
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String deleteMdpBckGroup(UserInfoExt user, String groupId)
			throws MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::deleteMdpBckGroup]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		String out = "";
		try {
			MDPServiceHelper MDPHelper = new MDPServiceHelper();
			MDPHelper.deleteMdpBckGroup(userCred, Integer.parseInt(groupId));
		} catch (AxisFault e) {
			out = e.getFaultString();
		}
		log.info("[GestoreMDPBackOffice::deleteMdpBckGroup]  END ");
		return out;

	}

	/**
	 * 
	 * @param user
	 * @param idGruppo
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public Gruppo getMdpBckGroupsById(UserInfoExt user, String idGruppo)
			throws AxisFault, MalformedURLException, RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getMdpBckGroupsById]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MdpBckofficegroups ris = MDPHelper.getMdpBckGroupsById(userCred,Integer.parseInt(idGruppo));
		Gruppo out = new Gruppo();
		out.setDescription(ris.getDescription());
		out.setIdgroup(String.valueOf(ris.getIdgroup()));

		if (ris.getRoles() != null && ris.getRoles().length > 0) {
			out.setIdRuolo(String.valueOf(ris.getRoles(0).getIdrole()));
		}
		Application[] listapp = ris.getAppgrp();
		ArrayList<Applicazione> listaapp1 = new ArrayList<Applicazione>();
		if (ris.getAppgrp() != null) {
			for (int i = 0; i < listapp.length; i++) {
				Application listappSel = listapp[i];
				Applicazione app1sel = new Applicazione();
				app1sel.setIdApplicazione(listappSel.getId());
				app1sel.setNomeApplicazione(listappSel.getId() + " - "
						+ listappSel.getApplicationname());
				listaapp1.add(app1sel);
			}
			out.setApplicazioni(listaapp1);
		}

		MdpBckusers[] listUsr = ris.getUsers();
		ArrayList<Utente> listaUsr1 = new ArrayList<Utente>();
		if (ris.getUsers() != null) {
			for (int i = 0; i < listUsr.length; i++) {
				MdpBckusers listUteSel = listUsr[i];
				Utente ute1sel = new Utente();
				ute1sel.setIdUtente(String.valueOf(listUteSel.getIduser()));
				ute1sel.setDescrUtente(listUteSel.getFirstname());
				listaUsr1.add(ute1sel);
			}
			log.info("[GestoreMDPBackOffice::getMdpBckGroupsById]  utenti.si " + listaUsr1.size());
			out.setUtenti(listaUsr1);
		}
		log.info("[GestoreMDPBackOffice::getMdpBckGroupsById]  END ");
		return out;
	}

	/**
	 * 
	 * @param codFisc
	 * @return
	 * @throws Exception
	 */
	public String getPwUtente(String codFisc) throws AxisFault,
			MalformedURLException, RemoteException, Exception {
		// log.info("[GestoreMDPBackOffice::getPwUtente]   BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(new MdpboProperties()
				.getDefwsMdpboProperties("super.user.cod.fisc"));
		userCred.setPwdAuth(new MdpboProperties()
				.getDefwsMdpboProperties("service.pw"));
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MdpBckusers users = MDPHelper.getMdpUsersByCfisc(userCred, codFisc);
		// log.info("[GestoreMDPBackOffice::getPwUtente]   END ");
		return users.getPwdservizio();
	}

	/**
	 * 
	 * @param user
	 * @param trans
	 * @return String
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public Transazione confirmPayment(
			UserInfoExt user, String idtrans) throws 
			MalformedURLException, RemoteException, Exception {		
		log.info("[GestoreMDPBackOffice::confirmPayment]  BEGIN ");
		it.csi.mdp.mdpboweb.business.ws.Transazione risws = new it.csi.mdp.mdpboweb.business.ws.Transazione();
		Transazione ris = new Transazione();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		try{
			risws = MDPHelper.confirmPayment(userCred, idtrans).getTransazione();
			ris.setErrore("");
			ris.setCodStato(risws.getCodStato());

		}catch(AxisFault e){
			ris.setErrore(e.getFaultString());
		}
		log.info("[GestoreMDPBackOffice::confirmPayment]  END ");
		return ris;
	}

	/**
	 * 
	 * @param user
	 * @param trans
	 * @return String
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	
	public Transazione refundPayment(
			UserInfoExt user, String idtrans) throws 
			MalformedURLException, RemoteException, Exception {
		it.csi.mdp.mdpboweb.business.ws.Transazione risws = new it.csi.mdp.mdpboweb.business.ws.Transazione();
		log.info("[GestoreMDPBackOffice::refundPayment]  BEGIN ");
		Transazione ris = new Transazione();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		try{
			risws = MDPHelper.refundPayment(userCred, idtrans).getTransazione();
			ris.setErrore("");
			ris.setCodStato(risws.getCodStato());
		}catch(AxisFault e){
			ris.setErrore(e.getFaultString());
		}
		
		log.info("[GestoreMDPBackOffice::refundPayment]  END ");
		return ris;
	}

	public ArrayList<AssociazioneGW_PM> getGatewayDetailById(UserInfoExt user,
			String idgate) throws AxisFault, MalformedURLException,
			RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getGatewayDetailById]  BEGIN ");
		//ArrayList<AssociazioneGW_PM> listaRes = new ArrayList<AssociazioneGW_PM>();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		ArrayList<AssociazioneGW_PM> listaRet = new ArrayList<AssociazioneGW_PM>();
		Gatewaydetail[] listGatewaydetail = MDPHelper.getGatewayDetailById(
				userCred, idgate);
		if (listGatewaydetail == null) {
			log.info("[GestoreMDPBackOffice::getGatewayDetailById]  lista  null ");
		} else {
			for (int a = 0; a < listGatewaydetail.length; a++) {
				Gatewaydetail wsGatewaydetailSel = listGatewaydetail[a];
				AssociazioneGW_PM associazione = new AssociazioneGW_PM();
				associazione.setChiave(wsGatewaydetailSel.getGatewayId()+ wsGatewaydetailSel.getPaymentmodeId());
				associazione.setIdGateway(wsGatewaydetailSel.getGatewayId());
				Gateway gateway = getGatewayById(user,associazione.getIdGateway());
				associazione.setDescrGateway(gateway.getDescrGateway());
				associazione.setIdPayment(wsGatewaydetailSel.getPaymentmodeId());
				PaymentMode pmsel = getPaymentModeById(user,associazione.getIdPayment());
				associazione.setDescrPayment(pmsel.getDescrPayment());
				associazione.setDefaultPaymentUrl(wsGatewaydetailSel.getDefaultpaymenturl());
				associazione.setReturnUrl(wsGatewaydetailSel.getReturnurlmdp());
				associazione.setReceiptUrl(wsGatewaydetailSel.getReceipturl());
				associazione.setCancelUrl(wsGatewaydetailSel.getErrorurl());
				associazione.setAbilitazione(new NumberUtil().convertiStringToBoolean(wsGatewaydetailSel.getEnabled(), "1"));
				if (wsGatewaydetailSel.getHttpport() >= 0) {
					associazione.setHttpport(String.valueOf(wsGatewaydetailSel.getHttpport()));
				}
				associazione.setContextpg(wsGatewaydetailSel.getContextpg());
				associazione.setMdpgatewaypage(wsGatewaydetailSel.getMdpgatewaypage());
				associazione.setCodIstat("");
				listaRet.add(associazione);
			}
		}
		log.info("[GestoreMDPBackOffice::getGatewayDetailById]  END ");
		return listaRet;
	}

/**
 * 
 * @param user
 * @return
 * @throws AxisFault
 * @throws MalformedURLException
 * @throws RemoteException
 * @throws Exception
 */
	public ArrayList<Applicazione> listApplicationByFlussoApplicazione(UserInfoExt user)throws AxisFault, MalformedURLException,
	RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getListaApplicazioniByUser]  BEGIN ");
		ArrayList<Applicazione> listaRes = new ArrayList<Applicazione>();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		Application[] listaApplication = MDPHelper.listApplicationByFlussoApplicazione(userCred);
		if (listaApplication == null) {
			log.info("[GestoreMDPBackOffice::getListaApplicazioniByUser]  lista applicazioni null ");
		} else {
			for (int k = 0; k < listaApplication.length; k++) {
				Application wsApp = listaApplication[k];
				Applicazione app1 = new Applicazione();
				app1.setIdApplicazione(wsApp.getId());
				app1.setCliente(wsApp.getCliente());
				app1.setNomeApplicazione(wsApp.getId() + " - "
						+ wsApp.getApplicationname());
				app1.setReferenteCSI(wsApp.getReferentecsi());
				listaRes.add(app1);
			}
		}
		log.info("[GestoreMDPBackOffice::getListaApplicazioniByUser]  END ");
		return listaRes;
	}

	
	public ArrayList<StatisticaApplicazioneTransazione> getStatisticaApplicazioneTransazione(UserInfoExt user,String applicationId,String dataDa,String dataA)throws AxisFault, MalformedURLException,
	RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getStatisticaApplicazioneTransazione]  BEGIN ");
		ArrayList<StatisticaApplicazioneTransazione> listaRes = new ArrayList<StatisticaApplicazioneTransazione>();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		StatisticaApplicazioneTransazioneDTO[] list = MDPHelper.getStatisticaApplicazioneTransazione(userCred,applicationId, dataDa, dataA);
		
		
		if (list == null) {
			log.info("[GestoreMDPBackOffice::getListaApplicazioniByUser]  lista applicazioni null ");
		} else {
			for (int k = 0; k < list.length; k++) {
				StatisticaApplicazioneTransazioneDTO satDto = list[k];
				StatisticaApplicazioneTransazione sat = new StatisticaApplicazioneTransazione();
				sat.setApplicationId(satDto.getApplicationId());
				sat.setAttesaRt(satDto.getAttesaRT());
				sat.setCanceled(satDto.getCanceled());
				sat.setConfigured(satDto.getConfigured());
				sat.setInitialized(satDto.getInitialized());
				sat.setNotInitialized(satDto.getNotInitialized());
				sat.setRefunded(satDto.getRefunded());
				sat.setStarted(satDto.getStarted());
				sat.setSuccessful(satDto.getSuccessful());
				sat.setToBeConfirmed(satDto.getToBeConfirmed());
				sat.setUnsuccessful(satDto.getUnsuccessful());
			    
				int totForAppId = satDto.getAttesaRT() 
									+ satDto.getCanceled() 
									+satDto.getConfigured()
									+satDto.getConfigured()
									+satDto.getInitialized()
									+satDto.getNotInitialized()
									+satDto.getRefunded()
									+satDto.getStarted()
									+satDto.getSuccessful()
									+satDto.getToBeConfirmed()
									+satDto.getUnsuccessful();
				
				sat.setTotForAppId(totForAppId);
				listaRes.add(sat);
			}
		}
		log.info("[GestoreMDPBackOffice::getListaApplicazioniByUser]  END ");
		return listaRes;
	}
	
	
	/**
	 * 
	 * @param user
	 * @param id
	 * @param applicationId
	 * @param idEnte
	 * @param tipoversamento
	 * @param identificativopsp
	 * @param iban
	 * @param dataInizioValidita
	 * @param dataFineValidita
	 * @param attivo
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void updateIbanEnteApplication(
													UserInfoExt user,
													String id,
													String applicationId,
													String idEnte,
													String tipoversamento,
													String identificativopsp,
													String iban,
													String dataInizioValidita,
													String dataFineValidita,
													String attivo)throws AxisFault, MalformedURLException,RemoteException, Exception {
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		MDPHelper.updateIbanEnteApplicationByParam(
													userCred,
													id,
													applicationId,
													idEnte,
													tipoversamento,
													identificativopsp,
													iban,
													dataInizioValidita,
													dataFineValidita,
													attivo);
	}
	
	/**
	 * 
	 * @param user
	 * @param applicationId
	 * @param idEnte
	 * @param tipoversamento
	 * @param identificativopsp
	 * @param iban
	 * @param dataInizioValidita
	 * @param dataFineValidita
	 * @param attivo
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void insertIbanEnteApplication(
										UserInfoExt user,
										String applicationId,
										String idEnte,
										String tipoversamento,
										String identificativopsp,
										String iban,
										String dataInizioValidita,
										String dataFineValidita,
										String attivo)throws AxisFault, MalformedURLException,RemoteException, Exception {
		
			Credentials userCred = new Credentials();
			userCred.setCodfisc(user.getCodFisc());
			userCred.setPwdAuth(user.getPwBck());
			MDPServiceHelper MDPHelper = new MDPServiceHelper();
			MDPHelper.insertIbanEnteApplication(
						userCred,
						applicationId,
						idEnte,
						tipoversamento,
						identificativopsp,
						iban,
						dataInizioValidita,
						dataFineValidita,
						attivo);
	}
	
	/**
	 * 
	 * @param userCred
	 * @param id
	 * @param applicationId
	 * @param idEnte
	 * @param tipoversamento
	 * @param identificativopsp
	 * @param iban
	 * @param dataInizioValidita
	 * @param dataFineValidita
	 * @param attivo
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public ArrayList<IbanEnteApplication> getIbanEnteApplicationByParam(
			UserInfoExt user,
			String id,
			String applicationId,
			String idEnte,
			String tipoversamento,
			String identificativopsp,
			String iban,
			String dataInizioValidita,
			String dataFineValidita,
			String attivo)
	throws AxisFault, MalformedURLException, RemoteException,Exception {
		
		ArrayList<IbanEnteApplication> listaRes = new ArrayList<IbanEnteApplication>();
		
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		
		log.info("[GestoreMDPBackOffice::getIbanEnteApplicationByParam]  applicationId "+applicationId);
		log.info("[GestoreMDPBackOffice::getIbanEnteApplicationByParam]  idEnte "+idEnte);
		log.info("[GestoreMDPBackOffice::getIbanEnteApplicationByParam]  tipoversamento "+tipoversamento);
		log.info("[GestoreMDPBackOffice::getIbanEnteApplicationByParam]  identificativopsp "+identificativopsp);
		
		IbanEnteApplicationDTO[] listaIban = MDPHelper.getIbanEnteApplicationByParam(
													userCred,
													id,
													applicationId,
													idEnte,
													tipoversamento,
													identificativopsp,
													iban,
													dataInizioValidita,
													dataFineValidita,
													attivo
		);
		
		if (listaIban == null) {
			log.info("[GestoreMDPBackOffice::getIbanEnteApplicationByParam]  lista Iban null ");
		} else {
			for (int k = 0; k < listaIban.length; k++) {
				IbanEnteApplicationDTO dto = listaIban[k];
				IbanEnteApplication iea = new IbanEnteApplication();
				iea.setAttivo(dto.getAttivo());
				iea.setIban(dto.getIban());
				
				iea.setDataFineValidita(UtilDate.getConvertCalendarToString(dto.getDataFineValidita(),"dd/MM/yyyy HH:mm:ss"));
				iea.setDataInizioValidita(UtilDate.getConvertCalendarToString(dto.getDataInizioValidita(),"dd/MM/yyyy HH:mm:ss"));
				iea.setId(String.valueOf(dto.getId()));
				iea.setIdEnte(dto.getIdEnte());
				iea.setIdentificativopsp(dto.getIdentificativopsp());
				iea.setTipoversamento(dto.getTipoversamento());
				iea.setApplicationId(dto.getApplicationId());

				listaRes.add(iea);
			}
		}
		
		log.info("[MDPServiceHelper::getIbanEnteApplicationByParam]  END ");
		return listaRes;
	}
	
	
	
//	public StatisticaApplicazioneTransazioneDTO[] getStatisticaApplicazioneTransazione(Credentials userCred,String applicationId,String dataDa,String dataA)
//	throws AxisFault, MalformedURLException, RemoteException {
//		log.info("[MDPServiceHelper::getStatisticaApplicazioneTransazione]  BEGIN ");
//		GetStatisticaApplicazioneTransazione parameters = new GetStatisticaApplicazioneTransazione(userCred,applicationId,UtilDate.getConvertStringToCalendar(dataDa),UtilDate.getConvertStringToCalendar(dataA));
//		MdpBoServicesCxfServiceSoapBindingStub stub;
//		
//		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
//		stub = autenticazione(stub);
//		StatisticaApplicazioneTransazioneDTO[] ris = stub.getStatisticaApplicazioneTransazione(parameters);
//		
//		log.info("[MDPServiceHelper::getStatisticaApplicazioneTransazione]  END ");
//		return ris;
//	}
	
}
