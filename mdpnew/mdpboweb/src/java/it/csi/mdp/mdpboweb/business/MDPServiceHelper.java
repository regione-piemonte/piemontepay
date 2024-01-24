/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.business;



import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.mdpboweb.business.ws.*;
import it.csi.mdp.mdpboweb.util.Constants;
import it.csi.mdp.mdpboweb.util.StringUtil;
import it.csi.mdp.mdpboweb.util.UtilDate;
import it.csi.mdp.mdpboweb.business.ws.Config;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import it.csi.mdp.mdpboweb.business.ws.AuthException;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.Transazione;

import org.apache.axis.AxisFault;
import org.apache.log4j.Logger;

import java.io.FileInputStream;

import javax.mail.util.*;
import javax.naming.NamingException;
import javax.activation.DataSource;

public class MDPServiceHelper extends MDPBaseServiceHelper{

	public MDPServiceHelper() throws Exception {
		log.info("[MDPServiceHelper::MDPServiceHelper]  BEGIN ");
		try {
			Properties props = new Properties();
			InputStream is = getClass().getResourceAsStream(MDPBO_SERVICE_RESOURCE);
			props.load(is);
			MDPBOWS_ENDPOINT = props.getProperty("service.endpoint");
		} catch (Exception ex) {
			log.error("[MDPServiceHelper::MDPServiceHelper]  ", ex);
			ex.printStackTrace();
			throw ex;
		} finally {
			log.info("[MDPServiceHelper::MDPServiceHelper]  END ");
		}
	}

	/************************************
	 * 
	 * @param userCred
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws AuthException
	 * @throws Exception
	 */
	public Config[] getBoConfig(Credentials userCred) throws AxisFault,
			MalformedURLException, RemoteException, AuthException, Exception {
		log.debug("[MDPServiceHelper::getBoConfig]  BEGIN ");
		GetBoConfig parameters = new GetBoConfig(userCred);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		Config[] res = null;

		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		res = stub.getBoConfig(parameters);

		log.debug("[MDPServiceHelper::getBoConfig]  END ");
		return res;
	}

	/**
	 * 
	 * @param userCred
	 * @param conf
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws AuthException
	 * @throws Exception
	 */
	public DeleteBoConfigResponse deleteBoConfig(Credentials userCred,
			Config conf) throws AxisFault, MalformedURLException,
			RemoteException, AuthException, Exception {
		log.debug("[MDPServiceHelper::deleteBoConfig]  BEGIN ");
		DeleteBoConfig parameters = new DeleteBoConfig(userCred, conf);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		DeleteBoConfigResponse res = null;

		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		res = stub.deleteBoConfig(parameters);

		log.debug("[MDPServiceHelper::deleteBoConfig]  END ");
		return res;
	}

	/**
	 * 
	 * @param userCred
	 * @param key
	 * @param value
	 * @param descrizione
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws AuthException
	 * @throws Exception
	 */
	public SetBoConfigResponse setBoConfig(Credentials userCred, String key,
			String value, String descrizione) throws AxisFault,
			MalformedURLException, RemoteException, AuthException, Exception {
		log.debug("[MDPServiceHelper::setBoConfig]  BEGIN ");
		Config cnf = new Config();
		cnf.setKey(key);
		cnf.setValue(value);
		cnf.setDescrizione(descrizione);
		SetBoConfig parameters = new SetBoConfig(userCred, cnf);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		SetBoConfigResponse res = new SetBoConfigResponse();
		try {
			stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
			stub = autenticazione(stub);
			res = stub.setBoConfig(parameters);
		} catch (Exception e) {
			log.error("[MDPServiceHelper::setBoConfig] " + e);
			e.printStackTrace();
			throw e;
		}
		log.debug("[MDPServiceHelper::setBoConfig]  END ");
		return res;
	}

	/****
	 * 
	 * @param userCred
	 * @param idApplicazione
	 * @param codStato
	 * @param dInizio
	 * @param dFine
	 * @param numeroPagina
	 * @param transPerPagina
	 * @return
	 */
	public VtransazioneResult getTransazioneViewWithFiltersPaged(
			Credentials userCred, String idApplicazione, long codStato,
			Calendar dInizio, Calendar dFine, int numeroPagina,
			int transPerPagina) throws AxisFault, MalformedURLException,
			RemoteException, AuthException, Exception {
		log.debug("[MDPServiceHelper::getTransazioneViewWithFiltersPaged]  BEGIN ");
		VtransazioneResult risVtr;
		if (dFine != null) {
			dFine = new UtilDate().addDay(dFine, 1);
		}
		GetTransazioneViewWithFiltersPaged parameters = new GetTransazioneViewWithFiltersPaged(
				userCred, idApplicazione, codStato, dInizio, dFine,
				numeroPagina, transPerPagina);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		GetTransazioneViewWithFiltersPagedResponse risWS = stub
				.getTransazioneViewWithFiltersPaged(parameters);
		risVtr = risWS.getTransactionResult();
		log.debug("[MDPServiceHelper::getTransazioneViewWithFiltersPaged]  END ");
		return risVtr;
	}

	/**
	 * 
	 * @param userCred
	 * @param idApplicazione
	 * @param idTransazione
	 * @param idGateway
	 * @param dInizio
	 * @param dFine
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws AuthException
	 * @throws Exception
	 */
	public it.csi.mdp.mdpboweb.business.ws.Verrori[] getListaErroriByFiltro(
			Credentials userCred, String idApplicazione, String idTransazione,
			String idGateway, Calendar dInizio, Calendar dFine)
			throws AxisFault, MalformedURLException, RemoteException,
			AuthException, Exception {
		log.debug("[MDPServiceHelper::getGetListaErroriByFiltro]  BEGIN ");
		it.csi.mdp.mdpboweb.business.ws.Verrori[] listaVerr = null;
		if (dFine != null) {
			dFine = new UtilDate().addDay(dFine, 1);
		}

		it.csi.mdp.mdpboweb.business.ws.GetErrorList parameters = new it.csi.mdp.mdpboweb.business.ws.GetErrorList(
				userCred, idApplicazione, idTransazione, dInizio, dFine,
				idGateway);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		listaVerr = stub.getErrorList(parameters);
		log.debug("[MDPServiceHelper::getGetListaErroriByFiltro]  END ");
		return listaVerr;

	}

	/*****
	 * 
	 * @param userCred
	 * @param idTransazione
	 * @return
	 */
	public it.csi.mdp.mdpboweb.business.ws.Transazione setStatoTransazione(
			Credentials userCred, Transazione trans) throws AxisFault,
			MalformedURLException, RemoteException {
		log.debug("[MDPServiceHelper::setStatoTransazione]  BEGIN ");
		it.csi.mdp.mdpboweb.business.ws.Transazione wsTrans = new it.csi.mdp.mdpboweb.business.ws.Transazione();
		wsTrans.setTransactionId(trans.getIdTransazione());
		wsTrans.setApplicationId(trans.getIdApplicazione());
		wsTrans.setCodStato(trans.getCodNuovoStato());
		SetTransazione parameters = new SetTransazione(userCred, wsTrans);
		it.csi.mdp.mdpboweb.business.ws.Transazione ris = new it.csi.mdp.mdpboweb.business.ws.Transazione();
		MdpBoServicesCxfServiceSoapBindingStub stub;
		it.csi.mdp.mdpboweb.business.ws.SetTransazioneResponse risTran = null;

		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		risTran = stub.setTransazione(parameters);
		ris = risTran.getTransazione();

		log.debug("[MDPServiceHelper::setStatoTransazione]  END ");
		return ris;
	}

	/**
	 * // estrae la lista delle applicazioni
	 * 
	 * @param cred
	 * @return
	 */
	public Application[] getApplication(Credentials cred) throws AxisFault,
			MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::getApplication]  BEGIN ");
		GetApplication app = new GetApplication(cred);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		Application[] listaApplication = null;

		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		listaApplication = stub.getApplication(app);

		log.info("[MDPServiceHelper::getApplication]  END ");
		return listaApplication;
	}

	/**
	 * // estrae la lista delle Azioni
	 * 
	 * @param cred
	 * @return
	 */
	public Auditactions[] getAuditActionsList(Credentials cred)
			throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::getAzioni]  BEGIN ");
		GetAuditActionsList azi = new GetAuditActionsList(cred);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		Auditactions[] listaActionsList = null;

		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		listaActionsList = stub.getAuditActionsList(azi);

		log.info("[MDPServiceHelper::getAzioni]  END ");
		return listaActionsList;
	}

	/**
	 * // estrae la lista degli Utenti
	 * 
	 * @param cred
	 * @return
	 */
	public MdpBckusers[] getMdpUsers(Credentials cred) throws AxisFault,
			MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::getMdpUsers]  BEGIN ");
		GetMdpUsers app = new GetMdpUsers(cred);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		MdpBckusers[] listaMdpUsers = null;

		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		listaMdpUsers = stub.getMdpUsers(app);

		log.info("[MDPServiceHelper::getMdpUsers]  END ");
		return listaMdpUsers;
	}

	/**
	 * 
	 * @param cred
	 * @param id
	 * @return
	 */
	public MdpBckusers getMdpUsersById(Credentials cred, String id)
			throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::getMdpUsers]  BEGIN ");
		GetMdpUsersById parameters = new GetMdpUsersById(cred,
				Integer.parseInt(id));
		MdpBoServicesCxfServiceSoapBindingStub stub;
		GetMdpUsersByIdResponse res = new GetMdpUsersByIdResponse();

		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		res = stub.getMdpUsersById(parameters);

		log.info("[MDPServiceHelper::getMdpUsers]  END ");
		return res.getUser();
	}

	/**
	 * // estrae la lista degli Auditing
	 * 
	 * @param cred
	 * @param datestartFilter
	 * @param dateendFilter
	 * @param transactionidFilter
	 * @param applicationListFilter
	 * @param actionsFilter
	 * @param usersFilter
	 * @return
	 */
	public CsiLogAudit[] getAuditing(Credentials cred,
			Calendar datestartFilter, Calendar dateendFilter,
			String transactionidFilter, Application[] applicationListFilter,
			String[] actionsFilter, String[] usersFilter) throws AxisFault,
			MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::getAuditing]  BEGIN ");
		GetAuditing parameters = null;
		MdpBoServicesCxfServiceSoapBindingStub stub;
		CsiLogAudit[] listaCsiLogAudit = null;

		parameters = new GetAuditing();
		parameters.setAuth(cred);
		parameters.setDatestartFilter(datestartFilter);
		parameters.setDateendFilter(dateendFilter);
		parameters.setTransactionidFilter(transactionidFilter);
		parameters.setApplicationListFilter(applicationListFilter);
		parameters.setActionsFilter(actionsFilter);
		parameters.setUsersFilter(usersFilter);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		listaCsiLogAudit = stub.getAuditing(parameters);

		log.info("[MDPServiceHelper::getAuditing]  END ");
		return listaCsiLogAudit;
	}

	/**
	 * // estrae la lista degli stati transazione
	 * 
	 * @param cred
	 * @return
	 */
	public StatoTransazione[] getStatiTransazione(Credentials cred)
			throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::getStatiTransazione]  BEGIN ");
		GetStatiTransazione sta = new GetStatiTransazione(cred);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		StatoTransazione[] listaStati = null;

		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		sta.setAuth(cred);
		stub = autenticazione(stub);
		listaStati = stub.getStatiTransazione(sta);

		log.info("[MDPServiceHelper::getStatiTransazione]  END ");
		return listaStati;
	}

	/**
	 * 
	 * @param userCred
	 * @param appId
	 * @return
	 * @throws AxisFault
	 */
	public Application getApplicationById(Credentials userCred, String appId)
			throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::getApplicationById]  BEGIN ");
		GetApplicationById parameters = new GetApplicationById(userCred, appId);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		GetApplicationByIdResponse appRes = null;

		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		appRes = stub.getApplicationById(parameters);

		log.info("[MDPServiceHelper::getApplicationById]  END ");
		return appRes.getApplication();
	}

	/**
	 * 
	 * @param userCred
	 * @param appServizio
	 */
	public SetApplicationResponse setApplication(Credentials userCred,
			Application appServizio,boolean overwrite) throws AxisFault, MalformedURLException,
			RemoteException {
		log.info("[MDPServiceHelper::setApplication]  BEGIN ");
		SetApplication parameters = new SetApplication(userCred, appServizio,overwrite);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		SetApplicationResponse appRes = new SetApplicationResponse();

		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		appRes = stub.setApplication(parameters);

		log.info("[MDPServiceHelper::setApplication]  END ");
		return appRes;
	}

	/**
	 * 
	 * @param userCred
	 * @param idTransazione
	 * @return
	 */
	public it.csi.mdp.mdpboweb.business.ws.Vtransazione getTransazioneViewById(
			Credentials userCred, String idTransazione) throws AxisFault,
			MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::getTransazioneViewById]  BEGIN ");
		GetTransazioneViewById parameters = new GetTransazioneViewById(
				userCred, idTransazione);
		it.csi.mdp.mdpboweb.business.ws.Vtransazione ris = new it.csi.mdp.mdpboweb.business.ws.Vtransazione();
		MdpBoServicesCxfServiceSoapBindingStub stub;
		it.csi.mdp.mdpboweb.business.ws.Vtransazione[] risTran = null;

		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		risTran = stub.getTransazioneViewById(parameters);
		if (risTran != null && risTran.length > 0) {
			ris = risTran[0];
		}

		return ris;
	}

	/**
	 * 
	 * @param userCred
	 * @return
	 */
	public it.csi.mdp.mdpboweb.business.ws.Gateway[] getGateway(
			Credentials userCred) throws AxisFault, MalformedURLException,
			RemoteException {
		log.info("[MDPServiceHelper::getGateway]  BEGIN ");
		GetGateways parameters = new GetGateways(userCred);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		Gateway[] listaGateway = null;

		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		listaGateway = stub.getGateways(parameters);

		log.info("[MDPServiceHelper::getGateway]  END ");
		return listaGateway;
	}

	/**
	 * 
	 * @param userCred
	 * @return
	 */
	public it.csi.mdp.mdpboweb.business.ws.Paymentmode[] getPaymentMode(
			Credentials userCred) throws AxisFault, MalformedURLException,
			RemoteException {
		log.info("[MDPServiceHelper::getPaymentMode]  BEGIN ");
		GetPaymentMode parameters = new GetPaymentMode(userCred);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		it.csi.mdp.mdpboweb.business.ws.Paymentmode[] listaPaymentMode = null;

		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		listaPaymentMode = stub.getPaymentMode(parameters);

		log.info("[MDPServiceHelper::getPaymentMode]  END ");
		return listaPaymentMode;
	}

	/**
	 * 
	 * @param userCred
	 * @param chiave
	 * @param valore
	 */
	public SetApplicationCustomFieldsResponse setApplicationCustomFields(
			Credentials userCred, String chiave, String valore)
			throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::setApplicationCustomFields]  BEGIN ");
		Applicationcustomfields[] appcusfield = new Applicationcustomfields[0];
		appcusfield[0].setKeyid(chiave);
		appcusfield[0].setFieldvalue(valore);
		SetApplicationCustomFields parameters = new SetApplicationCustomFields(
				userCred, appcusfield);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		SetApplicationCustomFieldsResponse appRes = new SetApplicationCustomFieldsResponse();

		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		appRes = stub.setApplicationCustomFields(parameters);

		log.info("[MDPServiceHelper::setApplicationCustomFields]  END ");
		return appRes;
	}

	/**
	 * 
	 * @param userCred
	 * @param appId
	 * @return
	 */
	public Applicationcustomfields[] getApplicationCustomFields(
			Credentials userCred, String appId) throws AxisFault,
			MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::getApplicationCustomFields]  BEGIN ");
		GetApplicationCustomFields parameters = new GetApplicationCustomFields(
				userCred, appId);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		Applicationcustomfields[] appCf = null;

		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		appCf = stub.getApplicationCustomFields(parameters);

		log.info("[MDPServiceHelper::getApplicationCustomFields]  END ");
		return appCf;
	}

	/**
	 * ritorna la tabella di associazione gateway appmode tab appdetail
	 * 
	 * @param userCred
	 * @param appId
	 * @return GetApplicationConfigurationResponse
	 */
	public GetApplicationConfigurationResponse getApplicationConfiguration(
			Credentials userCred, String appId) throws AxisFault,
			MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::getApplicationConfiguration]  BEGIN ");
		GetApplicationConfiguration parameters = new GetApplicationConfiguration(
				userCred, appId);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		GetApplicationConfigurationResponse appCf = null;
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		appCf = stub.getApplicationConfiguration(parameters);
		log.info("[MDPServiceHelper::getApplicationConfiguration]  END ");
		return appCf;
	}

	/**
	 * 
	 * @param userCred
	 * @param gatewayId
	 * @return Gatewaydetail[]
	 */
	public Gatewaydetail[] getGatewayDetailById(Credentials userCred,
			String gatewayId) throws AxisFault, MalformedURLException,
			RemoteException {
		log.info("[MDPServiceHelper::getGatewayDetailById]  BEGIN ");
		GetGatewayDetailById parameters = new GetGatewayDetailById(userCred,
				gatewayId);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		Gatewaydetail[] res = null;
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		res = stub.getGatewayDetailById(parameters);

		log.info("[MDPServiceHelper::getGatewayDetailById]  END ");
		return res;
	}

	/**
	 * 
	 * @param userCred
	 * @param gatewayId
	 * @return
	 */
	public Gatewaydetail[] getGatewayDetailByIdAbilitati(Credentials userCred,
			String gatewayId) throws AxisFault, MalformedURLException,
			RemoteException {
		log.info("[MDPServiceHelper::getGatewayDetailById]  BEGIN ");
		Gatewaydetail[] lista = getGatewayDetailById(userCred, gatewayId);
		int contatore = 0;
		for (int b = 0; b < lista.length; b++) {
			if ((StringUtil.convertNull(lista[b].getEnabled(), "0").equals("1"))) {
				contatore++;
			}
		}
		Gatewaydetail[] listaAbilitati = new Gatewaydetail[contatore];
		int c = 0;
		for (int a = 0; a < lista.length; a++) {
			if (new StringUtil().convertNull(lista[a].getEnabled(), "0")
					.equals("1")) {
				listaAbilitati[c] = lista[a];
				c++;
			}
		}
		log.info("[MDPServiceHelper::getGatewayDetailById]  END ");
		return listaAbilitati;
	}

	/**
	 * 
	 * @param userCred
	 * @param applicationid
	 * @param idGateway
	 * @param idPayment
	 * @param abilitato
	 * @param idEsercente
	 * @param passwordEsercente
	 * @param mACAvvio
	 * @param tipologiaCommissione
	 * @param valCommMin
	 * @param valCommMax
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
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public SetApplicationDetailResponse setApplicationDetail(
			Credentials userCred, String applicationid, String idGateway,
			String idPayment, String abilitato, String idEsercente,
			String passwordEsercente, String mACAvvio,
			String tipologiaCommissione, String valCommMin, String valCommMax,
			String valSoglia, String mail2Buyerko, String mail2Buyerok,
			String mail2Esercko, String mail2Esercok, String mail2Sysko,
			String mail2Sysok, String applicationurlback,
			String applicationurlresponseko, String applicationurlresponseok,
			String codiceistat) throws AxisFault, MalformedURLException,
			RemoteException {
		log.info("[MDPServiceHelper::setApplicationDetail]  BEGIN ");
		ApplicationDetail appDetail = new ApplicationDetail();
		appDetail.setApplicationid(applicationid);
		appDetail.setGatewayid(idGateway);
		appDetail.setPaymentmodeid(idPayment);
		appDetail.setEnabled(abilitato);
		appDetail.setMerchantid(idEsercente);
		appDetail.setMerchantidpassword(passwordEsercente);
		appDetail.setMacAvvio(mACAvvio);
		appDetail.setTipocommissione(tipologiaCommissione);
		appDetail.setValorecommissionemin(-1);
		appDetail.setValorecommissionemax(-1);
		appDetail.setSogliaa(-1);
		if (valCommMin != null && !valCommMin.equals("")) {
			appDetail.setValorecommissionemin(new Float(valCommMin));
		}
		if (valCommMax != null && !valCommMax.equals("")) {
			appDetail.setValorecommissionemax(new Float(valCommMax));
		}
		if (valSoglia != null && !valSoglia.equals("")) {
			appDetail.setSogliaa(new Float(valSoglia));
		}
		appDetail.setMail2Buyerko(mail2Buyerko);
		appDetail.setMail2Buyerok(mail2Buyerok);
		appDetail.setMail2Esercko(mail2Esercko);
		appDetail.setMail2Esercok(mail2Esercok);
		appDetail.setMail2Sysko(mail2Sysko);
		appDetail.setMail2Sysok(mail2Sysok);
		appDetail.setApplicationurlback(applicationurlback);
		appDetail.setApplicationurlresponseko(applicationurlresponseko);
		appDetail.setApplicationurlresponseok(applicationurlresponseok);
		appDetail.setFlagreturlmdp("-1");
		appDetail.setCodiceistat(codiceistat);
		SetApplicationDetail parameters = new SetApplicationDetail(userCred,
				appDetail);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		SetApplicationDetailResponse appRes = new SetApplicationDetailResponse();
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		appRes = stub.setApplicationDetail(parameters);
		log.info("[MDPServiceHelper::setApplicationDetail]  END ");
		return appRes;
	}

	/**
	 * 
	 * @param userCred
	 * @return
	 */
	public Commission[] getTipoCommissione(Credentials userCred)
			throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::getTipoCommissione]  BEGIN ");
		GetTipoCommissione parameters = new GetTipoCommissione(userCred);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		Commission[] res = null;
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		res = stub.getTipoCommissione(parameters);

		log.info("[MDPServiceHelper::getTipoCommissione]  END ");
		return res;
	}

	/**
	 * 
	 * @param userCred
	 * @param appcustfields
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public SetApplicationCustomFieldsResponse setApplicationCustomFields(
			Credentials userCred, Applicationcustomfields[] appcustfields)
			throws AxisFault, MalformedURLException, RemoteException {

		MdpBoServicesCxfServiceSoapBindingStub stub;
		SetApplicationCustomFields parameters = new SetApplicationCustomFields();
		SetApplicationCustomFieldsResponse res = new SetApplicationCustomFieldsResponse();
		parameters.setAuth(userCred);

		parameters.setAppcustfields(appcustfields);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		res = stub.setApplicationCustomFields(parameters);
		log.info("[MDPServiceHelper::setApplicationCustomFields]  END ");
		return res;
	}

	/**
	 * 
	 * @param userCred
	 * @param idGateway
	 * @return
	 */
	public Gatewaycustomfields[] getGatewayCustomFields(Credentials userCred,
			String idGateway) throws AxisFault, MalformedURLException,
			RemoteException {
		log.debug("[MDPServiceHelper::getGatewayCustomFields]  BEGIN ");
		GetGatewayCustomFields parameters = new GetGatewayCustomFields(
				userCred, idGateway);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		Gatewaycustomfields[] res = null;
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		res = stub.getGatewayCustomFields(parameters);
		log.debug("[MDPServiceHelper::getGatewayCustomFields]  END ");
		return res;
	}

	/**
	 * 
	 * @param userCred
	 * @param idGateway
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public Gateway getGatewayById(Credentials userCred, String idGateway)
			throws AxisFault, MalformedURLException, RemoteException {
		log.debug("[MDPServiceHelper::getGatewayById]  BEGIN ");
		GetGatewayById parameters = new GetGatewayById(userCred, idGateway);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		GetGatewayByIdResponse res = null;
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		res = stub.getGatewayById(parameters);
		Gateway gat = res.getGateway();
		log.debug("[MDPServiceHelper::getGatewayById]  END ");
		return gat;
	}

	/**
	 * 
	 * @param userCred
	 * @param idApplication
	 * @param idGateway
	 * @return Applicationcustomfields[]
	 */
	public Applicationcustomfields[] getGatewayCustomFieldsByGateway(
			Credentials userCred, String idApplication, String idGateway)
			throws AxisFault, MalformedURLException, RemoteException {
		log.debug("[MDPServiceHelper::getGatewayCustomFieldsByGateway]  BEGIN ");
		GetApplicationCustomFieldsByGateway parameters = new GetApplicationCustomFieldsByGateway(
				userCred, idApplication, idGateway);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		Applicationcustomfields[] res = null;
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		res = stub.getApplicationCustomFieldsByGateway(parameters);
		log.debug("[MDPServiceHelper::getGatewayCustomFieldsByGateway]  END ");
		return res;
	}

	/**
	 * 
	 * @param userCred
	 * @param idGateway
	 * @param descrGateway
	 * @param providerGateway
	 * @param serviceJNDIName
	 * @return SetGatewayResponse
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public SetGatewayResponse setGateway(Credentials userCred,
			String idGateway, String descrGateway, String providerGateway,
			String serviceJNDIName) throws AxisFault, MalformedURLException,
			RemoteException {
		log.info("[MDPServiceHelper::setGateway]  BEGIN ");
		Gateway gateWay = new Gateway();
		gateWay.setGatewayId(idGateway);
		gateWay.setGatewayDescription(descrGateway);
		gateWay.setGatewayProvider(providerGateway);
		gateWay.setGatewayservicename(serviceJNDIName);

		SetGateway parameters = new SetGateway(userCred, gateWay);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		SetGatewayResponse appRes = new SetGatewayResponse();
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		appRes = stub.setGateway(parameters);
		log.info("[MDPServiceHelper::setGateway]  END ");
		return appRes;
	}

	/**
	 * 
	 * @param userCred
	 * @param idPaymentMod
	 * @param descrPaymentMod
	 * @return SetPaymentModeResponse
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public SetPaymentModeResponse setPaymentMode(Credentials userCred,
			String idPaymentMod, String descrPaymentMod) throws AxisFault,
			MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::setPaymentMode]  BEGIN ");
		Paymentmode paymentmode = new Paymentmode();
		paymentmode.setPaymentmodeId(idPaymentMod);
		paymentmode.setPaymentmodeDescription(descrPaymentMod);

		SetPaymentMode parameters = new SetPaymentMode(userCred, paymentmode);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		SetPaymentModeResponse res = new SetPaymentModeResponse();
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		res = stub.setPaymentMode(parameters);
		log.info("[MDPServiceHelper::setPaymentMode]  END ");
		return res;
	}

	/**
	 * 
	 * @param userCred
	 * @param idPaymentMod
	 * @return Paymentmode
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public Paymentmode getPaymentModeById(Credentials userCred,
			String idPaymentMod) throws AxisFault, MalformedURLException,
			RemoteException {
		log.info("[MDPServiceHelper::getPaymentModeById]  BEGIN ");
		GetPaymentModeById parameters = new GetPaymentModeById(userCred,
				idPaymentMod);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		GetPaymentModeByIdResponse res = null;
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		res = stub.getPaymentModeById(parameters);
		Paymentmode pm = res.getPaymentmode();
		log.info("[MDPServiceHelper::getPaymentModeById]  END ");
		return pm;
	}

	/**
	 * 
	 * @param userCred
	 * @param gatewayId
	 * @param paimentId
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public Gatewaydetail getGatewayDetailByIds(Credentials userCred,
			String gatewayId, String paimentId) throws AxisFault,
			MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::getGatewayDetailByIds]  BEGIN ");
		GetGatewayDetailByIds parameters = new GetGatewayDetailByIds(userCred,
				gatewayId, paimentId);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		GetGatewayDetailByIdsResponse res = null;
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		res = stub.getGatewayDetailByIds(parameters);
		log.info("[MDPServiceHelper::getGatewayDetailByIds]  END ");
		return res.getGatewayDetail();
	}

	/**
	 * 
	 * @param userCred
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public Gatewaydetail[] getGatewayDetail(Credentials userCred)
			throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::getGatewayDetail]  BEGIN ");
		GetGatewayDetail parameters = new GetGatewayDetail(userCred);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		Gatewaydetail[] res = stub.getGatewayDetail(parameters);
		log.info("[MDPServiceHelper::getGatewayDetail]  END ");
		return res;
	}

	/**
	 * 
	 * @param userCred
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
	 */
	public Gatewaydetail setGatewayDetail(Credentials userCred, String backofficeurl,
			String contextpg, String defaultpaymenturl, String enabled,
			String errorurl, String gatewayId, long httpport,
			boolean httpportNull, String mdpgatewaypage, String paymentmodeId,
			String receipturl, String returnurlmdp, String sendmailonresponse,
			boolean verificaesito) throws AxisFault, MalformedURLException,
			RemoteException {
		log.info("[MDPServiceHelper::setGatewayDetail] BEGIN");
		Gatewaydetail gd = new Gatewaydetail();
		gd.setGatewayId(gatewayId);
		gd.setPaymentmodeId(paymentmodeId);
		gd.setEnabled(enabled);
		gd.setDefaultpaymenturl(defaultpaymenturl);
		gd.setErrorurl(errorurl);
		gd.setReceipturl(receipturl);
		gd.setReturnurlmdp(returnurlmdp);
		gd.setHttpport(httpport);
		gd.setContextpg(contextpg);
		gd.setMdpgatewaypage(mdpgatewaypage);
		gd.setVerificaesito(verificaesito);
		
		SetGatewayDetail parameters = new SetGatewayDetail(userCred, gd);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		log.info("[MDPServiceHelper::setGatewayDetail] END");
		return stub.setGatewayDetail(parameters).getGatewayDetail();
		
	}

	/**
	 * 
	 * @param userCred
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
	public String uploadMethodForApplicationGateway(
			it.csi.mdp.mdpboweb.business.ws.Credentials userCred, File file,
			String idApplicazione, String filename, String contentType,
			String idGateway) throws AxisFault, MalformedURLException,
			RemoteException, Exception {
		log.info("[MDPServiceHelper::uploadMethodForApplicationGateway] BEGIN");
		String ris = "";
		if (file != null) {
			ResourceFile myFile = new ResourceFile();
			myFile.setFileName(filename);
			myFile.setFileType(contentType);

			FileInputStream instream = new FileInputStream(file);
			byte[] b = new byte[instream.available()];
			instream.read(b);
			DataSource source = new ByteArrayDataSource(instream, contentType);
			myFile.setFileData(b);
			UploadMethodForApplicationGateway upforapp = new UploadMethodForApplicationGateway(
					userCred, filename, b, idApplicazione, idGateway);
			MdpBoServicesCxfServiceSoapBindingStub stub;

			stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
					MDPBOWS_ENDPOINT), null);
			stub = autenticazione(stub);
			try {
				ris = stub.uploadMethodForApplicationGateway(upforapp)
						.getFilePath();
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		log.info("[MDPServiceHelper::uploadMethodForApplicationGateway] END");
		return ris;
	}

	/**
	 * 
	 * @param userCred
	 * @param idGateway
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public Language[] getLanguagesByGatewayId(Credentials userCred,
			String idGateway) throws AxisFault, MalformedURLException,
			RemoteException {
		log.info("[MDPServiceHelper::getLanguagesByGatewayId]  BEGIN ");
		GetLanguagesByGatewayId parameters = new GetLanguagesByGatewayId(
				userCred, idGateway);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		Language[] res = stub.getLanguagesByGatewayId(parameters);
		log.info("[MDPServiceHelper::getLanguagesByGatewayId]  END ");
		return res;
	}

	/**
	 * 
	 * @param userCred
	 * @param idGateway
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public MdpCurrency[] getMdpCurrencyByGatewayId(Credentials userCred,
			String idGateway) throws AxisFault, MalformedURLException,
			RemoteException {
		log.info("[MDPServiceHelper::getMdpCurrencyByGatewayId]  BEGIN ");
		GetMdpCurrencyByGatewayId parameters = new GetMdpCurrencyByGatewayId(
				userCred, idGateway);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		MdpCurrency[] res = stub.getMdpCurrencyByGatewayId(parameters);
		log.info("[MDPServiceHelper::getMdpCurrencyByGatewayId]  END ");
		return res;
	}

	/**
	 * 
	 * @param cred
	 * @param users
	 * @param usersGroupList
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public SetMdpUserResponse setMdpUsers(Credentials cred, MdpBckusers users,
			MdpBckusersgroup[] usersGroupList) throws AxisFault,
			MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::setMdpUsers]  BEGIN ");
		SetMdpUser parameters = new SetMdpUser(cred, users, usersGroupList);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		SetMdpUserResponse res = new SetMdpUserResponse();
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		res = stub.setMdpUser(parameters);
		log.info("[MDPServiceHelper::setMdpUsers]  END ");
		return res;
	}

	/**
	 * 
	 * @param userCred
	 * @param listaCustomFields
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public void setGatewayCustomFields(Credentials userCred,
			Gatewaycustomfields[] listaCustomFields) throws AxisFault,
			MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::setGatewayCustomFields]  BEGIN ");
		SetGatewayCustomFields parameters = new SetGatewayCustomFields();
		parameters.setAuth(userCred);
		parameters.setGatewayCustomFieldsList(listaCustomFields);
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(
				new URL(MDPBOWS_ENDPOINT), null);
		stub.setGatewayCustomFields(parameters);
		log.info("[MDPServiceHelper::setGatewayCustomFields]  END ");
	}


	public MdpBckofficegroups[] getMdpBckGroups(Credentials cred)
			throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::getMdpBckGroups]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(
				new URL(MDPBOWS_ENDPOINT), null);
		GetMdpBckGroups parameters = new GetMdpBckGroups(cred);
		MdpBckofficegroups[] out = stub.getMdpBckGroups(parameters);
		log.info("[MDPServiceHelper::getMdpBckGroups]  END ");
		return out;
	}

	/**
	 * 
	 * @param cred
	 * @param codfisc
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public MdpBckofficegroups[] getMdpBckGroupsByCfisc(Credentials cred,
			String codfisc) throws AxisFault, MalformedURLException,
			RemoteException {
		log.info("[MDPServiceHelper::getMdpBckGroupsByCfisc]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(
				new URL(MDPBOWS_ENDPOINT), null);
		GetMdpBckGroupsByCfisc parameters = new GetMdpBckGroupsByCfisc(cred,
				codfisc);
		MdpBckofficegroups[] out = stub.getMdpBckGroupsByCfisc(parameters);
		log.info("[MDPServiceHelper::getMdpBckGroupsByCfisc]  END ");
		return out;
	}

	/**
	 * 
	 * @param cred
	 * @param idUser
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public MdpBckusersgroup[] getMdpUsersGroup(Credentials cred, String idUser)
			throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::getMdpGroup]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(
				new URL(MDPBOWS_ENDPOINT), null);
		MdpBckusersgroup[] out = new MdpBckusersgroup[3];
		log.info("[MDPServiceHelper::getMdpGroup]  END ");
		return out;
	}

	/**
	 * 
	 * @param cred
	 * @param group
	 * @param appslist
	 * @param grouprole
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public void setMdpGroup(Credentials cred, MdpBckofficegroups group,
			MdpBckofficegroupappmapping[] appslist,
			MdpBckrolesgroupmap grouprole) throws AxisFault,
			MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::setMdpGroup]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(
				new URL(MDPBOWS_ENDPOINT), null);
		SetMdpGroup parameters = new SetMdpGroup(cred, group, appslist,
				grouprole);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		stub.setMdpGroup(parameters);
		log.info("[MDPServiceHelper::setMdpGroup]  END ");
	}

	/**
	 * 
	 * @param cred
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public MdpBckroles[] getMdpBckRoles(Credentials cred) throws AxisFault,
			MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::getMdpBckRoles]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(
				new URL(MDPBOWS_ENDPOINT), null);
		GetMdpBckRoles parameters = new GetMdpBckRoles(cred);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		MdpBckroles[] out = stub.getMdpBckRoles(parameters);
		log.info("[MDPServiceHelper::getMdpBckRoles]  END ");
		return out;
	}

	/**
	 * 
	 * @param cred
	 * @param roles
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public void setMdpRole(Credentials cred, MdpBckroles roles)
			throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::setMdpRole]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(
				new URL(MDPBOWS_ENDPOINT), null);
		SetMdpRole parameters = new SetMdpRole(cred, roles);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		stub.setMdpRole(parameters);
		log.info("[MDPServiceHelper::setMdpRole]  END ");
	}

	/**
	 * 
	 * @param cred
	 * @param mdpCurrency
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public void setMdpCurrency(Credentials cred, MdpCurrency mdpCurrency)
			throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::setMdpCurrency]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(
				new URL(MDPBOWS_ENDPOINT), null);
		SetMdpCurrency parameters = new SetMdpCurrency(cred, mdpCurrency);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		stub.setMdpCurrency(parameters);
		log.info("[MDPServiceHelper::setMdpCurrency]  END ");
	}

	/**
	 * 
	 * @param cred
	 * @param lan
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public void setLanguage(Credentials cred, Language lan) throws AxisFault,
			MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::setMdpCurrency]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(
				new URL(MDPBOWS_ENDPOINT), null);
		SetLanguage parameters = new SetLanguage(cred, lan);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		stub.setLanguage(parameters);
		log.info("[MDPServiceHelper::setMdpCurrency]  END ");
	}

	/**
	 * 
	 * @param cred
	 * @param codFisc
	 * @return
	 */
	public MdpBckusers getMdpUsersByCfisc(Credentials cred, String codFisc)
			throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::getMdpUsers]  BEGIN ");
		
		GetMdpUsersByCfisc parameters = new GetMdpUsersByCfisc(cred, codFisc);
		//log.info("[MDPServiceHelper::getMdpUsers]  definisco i parametri ");
		
		MdpBoServicesCxfServiceSoapBindingStub stub;
		GetMdpUsersByCfiscResponse res = new GetMdpUsersByCfiscResponse();
		//log.info("[MDPServiceHelper::getMdpUsers]  definisco la risposta ");

		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);		
		log.info("[MDPServiceHelper::getMdpUsers]  creo lo stub MDPBOWS_ENDPOINT "+MDPBOWS_ENDPOINT);

		log.info("[MDPServiceHelper::getMdpUsers]  prima dell'autenticazione ");
		stub = autenticazione(stub);
		log.info("[MDPServiceHelper::getMdpUsers]  dopo dell'autenticazione " + stub);

		res = stub.getMdpUsersByCfisc(parameters);

		log.info("[MDPServiceHelper::getMdpUsers]  END ");
		return res.getUser();
	}

	/**
	 * 
	 * @param cred
	 * @param applicationId
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public void deleteApplication(Credentials cred, String applicationId)
			throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::deleteApplication]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(
				new URL(MDPBOWS_ENDPOINT), null);
		DeleteApplication parameters = new DeleteApplication(cred,
				applicationId);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		stub.deleteApplication(parameters);
		log.info("[MDPServiceHelper::deleteApplication]  END ");
	}

	/**
	 * 
	 * @param cred
	 * @param applicationId
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public void deleteApplicationConfiguration(Credentials cred,
			String applicationId) throws AxisFault, MalformedURLException,
			RemoteException {
		log.info("[MDPServiceHelper::deleteApplicationConfiguration]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(
				new URL(MDPBOWS_ENDPOINT), null);
		DeleteApplicationConfiguration parameters = new DeleteApplicationConfiguration(
				cred, applicationId);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		stub.deleteApplicationConfiguration(parameters);
		log.info("[MDPServiceHelper::deleteApplicationConfiguration]  END ");
	}

	/**
	 * 
	 * @param cred
	 * @param gatewayId
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public void deleteApplicationCustomFields(Credentials cred, String gatewayId)
			throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::deleteApplicationConfiguration]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(
				new URL(MDPBOWS_ENDPOINT), null);
		DeleteApplicationCustomFields parameters = new DeleteApplicationCustomFields(
				cred, gatewayId);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		stub.deleteApplicationCustomFields(parameters);
		log.info("[MDPServiceHelper::deleteApplicationConfiguration]  END ");
	}

	/**
	 * 
	 * @param cred
	 * @param applicationId
	 * @param gatewayId
	 * @param paymodeId
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public void deleteApplicationDetails(Credentials cred,
			String applicationId, String gatewayId, String paymodeId)
			throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::deleteApplicationDetails]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(
				new URL(MDPBOWS_ENDPOINT), null);
		DeleteApplicationDetail parameters = new DeleteApplicationDetail(cred,
				applicationId, gatewayId, paymodeId);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		stub.deleteApplicationDetail(parameters);
		log.info("[MDPServiceHelper::deleteApplicationDetails]  END ");
	}

	/**
	 * 
	 * @param cred
	 * @param gatewayId
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public void deleteGateway(Credentials cred, String gatewayId)
			throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::deleteGateway]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(
				new URL(MDPBOWS_ENDPOINT), null);
		DeleteGateway parameters = new DeleteGateway(cred, gatewayId);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		stub.deleteGateway(parameters);
		log.info("[MDPServiceHelper::deleteGateway]  END ");
	}

	/**
	 * 
	 * @param cred
	 * @param gatewayId
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public void deleteGatewayConfiguration(Credentials cred, String gatewayId)
			throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::deleteGatewayConfiguration]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(
				new URL(MDPBOWS_ENDPOINT), null);
		DeleteGatewayConfiguration parameters = new DeleteGatewayConfiguration(
				cred, gatewayId);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		stub.deleteGatewayConfiguration(parameters);
		log.info("[MDPServiceHelper::deleteGatewayConfiguration]  END ");
	}

	/**
	 * 
	 * @param cred
	 * @param gatewayId
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public void deleteGatewayCustomFields(Credentials cred, String gatewayId)
			throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::deleteGatewayCustomFields]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(
				new URL(MDPBOWS_ENDPOINT), null);
		DeleteGatewayCustomFields parameters = new DeleteGatewayCustomFields(
				cred, gatewayId);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		stub.deleteGatewayCustomFields(parameters);
		log.info("[MDPServiceHelper::deleteGatewayCustomFields]  END ");
	}

	/**
	 * 
	 * @param cred
	 * @param gatewayId
	 * @param paymodeId
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public void deleteGatewayDetail(Credentials cred, String gatewayId,
			String paymodeId) throws AxisFault, MalformedURLException,
			RemoteException {
		log.info("[MDPServiceHelper::deleteGatewayCustomFields]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		DeleteGatewayDetail parameters = new DeleteGatewayDetail(cred,gatewayId, paymodeId);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		stub.deleteGatewayDetail(parameters);
		log.info("[MDPServiceHelper::deleteGatewayCustomFields]  END ");
	}

	/**
	 * @param userCred
	 * @param idGruppo
	 * @return
	 */
	public MdpBckofficegroups getMdpBckGroupsById(Credentials userCred,
			int idGruppo) throws AxisFault, MalformedURLException,
			RemoteException {
		log.info("[MDPServiceHelper::getMdpBckGroupsById]  BEGIN ");

		GetMdpBckGroupsById parameters = new GetMdpBckGroupsById(userCred,
				idGruppo);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		GetMdpBckGroupsById res = new GetMdpBckGroupsById();
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		return stub.getMdpBckGroupsById(parameters).get_return();
	}

	/**
	 * 
	 * @param cred
	 * @param userId
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public void deleteMdpBckUser(Credentials cred, int userId)
			throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::deleteMdpBckUser]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(
				new URL(MDPBOWS_ENDPOINT), null);
		DeleteMdpBckUser parameters = new DeleteMdpBckUser(cred, userId);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		stub.deleteMdpBckUser(parameters);
		log.info("[MDPServiceHelper::deleteMdpBckUser]  END ");
	}

	/**
	 * 
	 * @param cred
	 * @param groupId
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public DeleteMdpBckGroupResponse deleteMdpBckGroup(Credentials cred,
			int groupId) throws AxisFault, MalformedURLException,
			RemoteException {
		log.info("[MDPServiceHelper::deleteMdpBckGroup]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		DeleteMdpBckGroup parameters = new DeleteMdpBckGroup(cred, groupId);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		log.info("[MDPServiceHelper::deleteMdpBckGroup]  END ");
		return stub.deleteMdpBckGroup(parameters);
	}

	/**
	 * 
	 * @param cred
	 * @param transactionId
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public ConfirmPaymentResponse confirmPayment(Credentials cred,
			String transactionId) throws AxisFault, MalformedURLException,
			RemoteException {
		log.info("[MDPServiceHelper::confirmPayment]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(
				new URL(MDPBOWS_ENDPOINT), null);
		ConfirmPayment parameters = new ConfirmPayment(cred, transactionId);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(
				MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		log.info("[MDPServiceHelper::confirmPayment]  END ");
		return stub.confirmPayment(parameters);
	}

	/**
	 * 
	 * @param cred
	 * @param transactionId
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public RefundPaymentResponse refundPayment(Credentials cred,
			String transactionId) throws AxisFault, MalformedURLException,RemoteException {
		log.info("[MDPServiceHelper::refundPayment]  BEGIN ");
		
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		RefundPayment parameters = new RefundPayment(cred, transactionId);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		
		log.info("[MDPServiceHelper::refundPayment]  END ");
		return stub.refundPayment(parameters);
	}

	/**
	 * 
	 * @param userCred
	 * @param iuv
	 * @param dataOraEvento
	 * @param identificativodominio
	 * @param identificativofruitore
	 * @param codiceContesto
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public GiornaleEventoDTO[] getGiornaleEventoByFiltro(Credentials userCred, String iuv, String dataOraEvento, String identificativodominio, String identificativofruitore,String codiceContesto) throws AxisFault, MalformedURLException,RemoteException {
		log.info("[MDPServiceHelper::getGiornaleEventoByFiltro]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);

		GetGiornaleEventoByParam parameters = new GetGiornaleEventoByParam(userCred, iuv,  UtilDate.getConvertStringToCalendar(dataOraEvento),  identificativodominio,  identificativofruitore, codiceContesto);
		
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		GiornaleEventoDTO[] lista = stub.getGiornaleEventoByParam(parameters);

		log.info("[MDPServiceHelper::getGiornaleEventoByFiltro]  END ");
		return lista;
	}
	
	/**
	 * 
	 * @param userCred
	 * @param id
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public GetGiornaleEventoByIdResponse getGiornaleEventoById(Credentials userCred, Integer id) throws AxisFault, MalformedURLException,RemoteException {
		log.info("[MDPServiceHelper::getGiornaleEventoById]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);

		GetGiornaleEventoById parameters = new GetGiornaleEventoById(userCred, id);
		
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		GetGiornaleEventoByIdResponse giornaleEvento = stub.getGiornaleEventoById(parameters);

		log.info("[MDPServiceHelper::getGiornaleEventoById]  END ");
		return giornaleEvento;
	}

	/**
	 * 
	 * @param userCred
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public CodiciEsitoPagamentoDTO[] getCodiciEsitoPagamentoAll(Credentials userCred) throws AxisFault, MalformedURLException,RemoteException {
		log.info("[MDPServiceHelper::getCodiciEsitoPagamentoAll]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);

		GetCodiciEsitoPagamentoAll parameters = new GetCodiciEsitoPagamentoAll(userCred);
		
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		CodiciEsitoPagamentoDTO[] lista = stub.getCodiciEsitoPagamentoAll(parameters);

		log.info("[MDPServiceHelper::getCodiciEsitoPagamentoAll]  END ");
		return lista;
	}
	
	/**
	 * 
	 * @param userCred
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public Application[] listApplicationByFlussoApplicazione(Credentials userCred)
	throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::listApplicationByFlussoApplicazione]  BEGIN ");
		ListApplicationByFlussoApplicazione parameters = new ListApplicationByFlussoApplicazione(userCred);
		MdpBoServicesCxfServiceSoapBindingStub stub;
		
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		Application[] appRes = stub.listApplicationByFlussoApplicazione(parameters);
		
		log.info("[MDPServiceHelper::listApplicationByFlussoApplicazione]  END ");
		return appRes;
	}
	
	/**
	 * 
	 * @param userCred
	 * @param applicationId
	 * @param dataDa
	 * @param dataA
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public StatisticaApplicazioneTransazioneDTO[] getStatisticaApplicazioneTransazione(Credentials userCred,String applicationId,String dataDa,String dataA)
	throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::getStatisticaApplicazioneTransazione]  BEGIN ");
		GetStatisticaApplicazioneTransazione parameters = new GetStatisticaApplicazioneTransazione(userCred,applicationId,UtilDate.getConvertStringToCalendar(dataDa),UtilDate.getConvertStringToCalendar(dataA));
		MdpBoServicesCxfServiceSoapBindingStub stub;
		
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		StatisticaApplicazioneTransazioneDTO[] ris = stub.getStatisticaApplicazioneTransazione(parameters);
		
		log.info("[MDPServiceHelper::getStatisticaApplicazioneTransazione]  END ");
		return ris;
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
	public IbanEnteApplicationDTO[] getIbanEnteApplicationByParam(
			Credentials userCred,
			String id,
			String applicationId,
			String idEnte,
			String tipoversamento,
			String identificativopsp,
			String iban,
			String dataInizioValidita,
			String dataFineValidita,
			String attivo)
	throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::getIbanEnteApplicationByParam]  BEGIN ");
		Integer pid = null;
		if(id!=null){
			pid = Integer.parseInt(id);
		}
		
		log.info("[MDPServiceHelper::getIbanEnteApplicationByParam]  applicationId "+applicationId);
		log.info("[MDPServiceHelper::getIbanEnteApplicationByParam]  idEnte "+idEnte);
		log.info("[MDPServiceHelper::getIbanEnteApplicationByParam]  tipoversamento "+tipoversamento);
		log.info("[MDPServiceHelper::getIbanEnteApplicationByParam]  identificativopsp "+identificativopsp);
		
		GetIbanEnteApplicationByParam parameters = new GetIbanEnteApplicationByParam(
																						userCred,
																						pid,
																						applicationId,
																						idEnte,
																						tipoversamento,
																						identificativopsp,
																						iban,
																						UtilDate.getConvertStringToCalendar( dataInizioValidita),
																						UtilDate.getConvertStringToCalendar( dataFineValidita),
																						attivo
		);
		
		
		MdpBoServicesCxfServiceSoapBindingStub stub;
		
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		IbanEnteApplicationDTO[] ris = stub.getIbanEnteApplicationByParam(parameters);
		
		log.info("[MDPServiceHelper::getIbanEnteApplicationByParam]  END ");
		return ris;
	}
	
	/**
	 * 
	 * @param userCred
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
	 */
	public void insertIbanEnteApplication(
			Credentials userCred,
			String applicationId,
			String idEnte,
			String tipoversamento,
			String identificativopsp,
			String iban,
			String dataInizioValidita,
			String dataFineValidita,
			String attivo)
	throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::insertIbanEnteApplication]  BEGIN ");
		
		InsertIbanEnteApplication parameters = new InsertIbanEnteApplication(
																			userCred,
																			applicationId,
																			idEnte,
																			tipoversamento,
																			identificativopsp,
																			iban,
																			UtilDate.getConvertStringToCalendar( dataInizioValidita),
																			UtilDate.getConvertStringToCalendar( dataFineValidita),
																			attivo
		);
		
		
		MdpBoServicesCxfServiceSoapBindingStub stub;
		
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		stub.insertIbanEnteApplication(parameters);
		
		log.info("[MDPServiceHelper::insertIbanEnteApplication]  END ");
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
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public void updateIbanEnteApplicationByParam(
			Credentials userCred,
			String id,
			String applicationId,
			String idEnte,
			String tipoversamento,
			String identificativopsp,
			String iban,
			String dataInizioValidita,
			String dataFineValidita,
			String attivo)
	throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::updateIbanEnteApplicationByParam]  BEGIN ");
		Integer pid =null;
		if(id!=null && !id.trim().equals("")){
			log.info("[MDPServiceHelper::updateIbanEnteApplicationByParam]  id " + id);
			pid=Integer.parseInt(id);
		}
		
		UpdateIbanEnteApplication parameters = new UpdateIbanEnteApplication(
																			userCred,
																			pid,
																			applicationId,
																			idEnte,
																			tipoversamento,
																			identificativopsp,
																			iban,
																			UtilDate.getConvertStringToCalendar( dataInizioValidita),
																			UtilDate.getConvertStringToCalendar( dataFineValidita),
																			attivo
		);
		
		
		MdpBoServicesCxfServiceSoapBindingStub stub;
		
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		stub.updateIbanEnteApplication(parameters);
		
		log.info("[MDPServiceHelper::updateIbanEnteApplicationByParam]  END ");
		
	}
}


