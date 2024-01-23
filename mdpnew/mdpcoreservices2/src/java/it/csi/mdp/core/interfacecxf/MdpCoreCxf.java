/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.interfacecxf;


import it.csi.csi.wrapper.CSIException;
import it.csi.mdp.core.business.dto.Application;
import it.csi.mdp.core.business.dto.ChiaveValore;
import it.csi.mdp.core.business.dto.ElementoMultiversamento;
import it.csi.mdp.core.business.dto.InformativePSPScelto;
import it.csi.mdp.core.business.dto.NodoErrore;
import it.csi.mdp.core.business.dto.ParametriUrlWisp;
import it.csi.mdp.core.business.dto.PartAnComune;
import it.csi.mdp.core.business.dto.RicevutaTelematicaNodo;
import it.csi.mdp.core.business.dto.Transazione;
import it.csi.mdp.core.business.dto.TransazioneExtraAttribute;
import it.csi.mdp.core.business.dto.Vapplicationcomuni;
import it.csi.mdp.core.business.dto.multicarrello.ElencoRPT;
import it.csi.mdp.core.business.exceptions.BusinessException;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.exceptions.SystemException;
import it.csi.mdp.core.business.paymentmanager.local.AppGateway;
import it.csi.mdp.core.business.paymentmanager.local.AppGatewayInformativa;
import it.csi.mdp.core.interfacecsi.IMdpCoreCsi;
import it.csi.mdp.core.util.LoggerUtil;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

@WebService(endpointInterface = "it.csi.mdp.core.interfacecxf.IMdpCoreCxf", targetNamespace = "http://interfacecxf.core.mdp.csi.it/")


public class MdpCoreCxf implements IMdpCoreCxf
{
	IMdpCoreCsi cPayment;
	protected static Logger log = Logger.getLogger(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
	
	private void init(){
		
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		cPayment = (IMdpCoreCsi) bf.getBean("paymentManagerLocal");
		
	}

	@WebMethod
	public AppGateway[] getModalitaPagamento(@WebParam(name = "t") Transazione t, @WebParam(name = "appId") String appId)
			throws  DaoException,NamingException, CreateException,MissingParameterException, CSIException
	{
		log.debug("[MdpCoreCxf:getModalitaPagamento] BEGIN");
		this.init();
		AppGateway[] arrAppGateway = cPayment.getModalitaPagamento(t, appId);
		log.debug("[MdpCoreCxf:getModalitaPagamento] END");
		return arrAppGateway;
	}
	
	/**
	 * Restituisce le modalita di pagamento aggiungendo i PSP del nodo
	 * @param Transazione
	 * @param appId
	 */
	@WebMethod
	public AppGatewayInformativa[] getModalitaInformativePagamento(@WebParam(name = "t") Transazione t, @WebParam(name = "appId") String appId)
			throws  DaoException,NamingException, CreateException,MissingParameterException, CSIException {
		log.debug("[MdpCoreCxf:getModalitaInformativePagamento] BEGIN");
		this.init();
		AppGatewayInformativa[] arrAppGateway = cPayment.getModalitaInformativePagamento(t, appId);
		log.debug("[MdpCoreCxf:getModalitaInformativePagamento] END");
		return arrAppGateway;
	}
	
	@WebMethod
	public String getUrlWisp(@WebParam(name = "applicationId")String applicationId, @WebParam(name = "transaction")Transazione transaction,
			@WebParam(name = "paramentriUrlWisp")ParametriUrlWisp paramentriUrlWisp, @WebParam(name = "prametriAggiuntivi")List<ChiaveValore> prametriAggiuntivi) 
			throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException, CSIException{ 
		LoggerUtil.begin();
		this.init();
		String url = cPayment.getUrlWisp(applicationId, transaction, paramentriUrlWisp, prametriAggiuntivi);
		LoggerUtil.end();
		return url;
	}
	
	@WebMethod
	public InformativePSPScelto getSceltaWisp(@WebParam(name = "applicationId")String applicationId, @WebParam(name = "transactionId")String transactionId) 
			throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException, CSIException{ 
		LoggerUtil.begin();
		this.init();
		InformativePSPScelto psp = cPayment.getSceltaWisp(applicationId,transactionId);
		LoggerUtil.end();
		return psp;
	}

	@WebMethod
	public Transazione initTransazione(@WebParam(name = "appId") String appId, @WebParam(name = "basketId") String basketId)
			throws DaoException, NamingException, CreateException, MissingParameterException, DatatypeConfigurationException,SOAPFaultException, CSIException
	{
		log.debug("[MdpCoreCxf:initTransazione] BEGIN");
		this.init();
		
		Transazione t;

		t = cPayment.initTransazione(appId, basketId);
		
		log.debug("[MdpCoreCxf:initTransazione] END");
			
		return t;
	}
	
	@WebMethod
	public String startTransazione(@WebParam(name = "t") Transazione t, @WebParam(name = "tea") TransazioneExtraAttribute[] tea)
			throws  DaoException, NamingException, CreateException, MissingParameterException, CSIException
	{
		log.debug("[MdpCoreCxf:startTransazione] BEGIN");
		this.init();

		String ret = cPayment.startTransazione(t, tea);
		
		log.debug("[MdpCoreCxf:startTransazione] END");
		return ret;

	}
	
	@WebMethod
	public Transazione getStatoTransazione(@WebParam(name = "transaction_id") String transaction_id) throws 
			DaoException, NamingException, CreateException, MissingParameterException, CSIException
	{
		log.debug("[MdpCoreCxf:getStatoTransazione] BEGIN");
		this.init();

		Transazione ret = cPayment.getStatoTransazione(transaction_id);
		log.debug("[MdpCoreCxf:getStatoTransazione] END");
		return ret;
	}
	
	@WebMethod
	public Vapplicationcomuni getPagonetCustomData (@WebParam(name = "applicationId")String applicationId, @WebParam(name = "gatewayId")String gatewayId) throws  MissingParameterException ,DaoException, CSIException
	{
		log.debug("[MdpCoreCxf:getPagonetCustomData] BEGIN");
		this.init();

		Vapplicationcomuni ret = cPayment.getPagonetCustomData(applicationId, gatewayId);
		log.debug("[MdpCoreCxf:getPagonetCustomData] END");
		return ret;
	}
	
	@WebMethod
	public Application getApplication(@WebParam(name = "appId") String appId) throws  DaoException, MissingParameterException, CSIException
	{
		log.debug("[MdpCoreCxf:getApplication] BEGIN");
		this.init();

		log.debug("[MdpCoreCxf:getApplication] ejb instanziato!");
		
		Application ret =  cPayment.getApplication(appId);
		
		log.debug("[MdpCoreCxf:getApplication] END");
		return ret;
		
	}
	
	@WebMethod
	public PartAnComune getComuneProvincia (String istatComune) throws  DaoException, MissingParameterException, CSIException
	{
		log.debug("[MdpCoreCxf:getComuneProvincia] BEGIN");
		this.init();

		PartAnComune ret = cPayment.getComuneProvincia(istatComune);
		
		log.debug("[MdpCoreCxf:getComuneProvincia] END");
		return ret;
	}
	/**
	 * Restituisce la ricevuta telematica del nodo selezionata per iuv
	 * @param iuv lo iuv
	 */
	@WebMethod
	public RicevutaTelematicaNodo getRTperIUV(@WebParam(name = "iuv") String iuv) throws DaoException, MissingParameterException, CSIException {
		log.debug("[MdpCoreCxf:getRTperIUV] BEGIN");
		this.init();

		RicevutaTelematicaNodo ret = cPayment.getRTperIUV(iuv);
		
		log.debug("[MdpCoreCxf:getRTperIUV] END");
		return ret;
	}
	
	
	/**
	 * Restituisce la ricevuta telematica del nodo selezionata per iuv
	 * @param iuv lo iuv
	 */
	@WebMethod
	public RicevutaTelematicaNodo getRTperIUVTransazione (@WebParam(name = "iuv") String iuv, @WebParam(name = "transactionId") String transactionId) throws  DaoException, MissingParameterException, CSIException {
		LoggerUtil.begin();
		this.init();
		
		RicevutaTelematicaNodo ret = cPayment.getRTperIUVTransazione(iuv, transactionId);
		
		LoggerUtil.end();
		return ret;
	}
	
	/**
	 * Restituisce la ricevuta telematica del nodo selezionata per iuv
	 * @param iuv lo iuv
	 */
	@WebMethod
	public List<RicevutaTelematicaNodo> getListaRTperIUV (@WebParam(name = "iuv") String iuv) throws  DaoException, MissingParameterException, CSIException {
		LoggerUtil.begin();
		this.init();
		List<RicevutaTelematicaNodo> ret = cPayment.getListaRTperIUV(iuv);
		
		LoggerUtil.end();
		return ret;
	}
	
	
	/**
	 * restituisce gli errori verificatisi sull'invio della RPT per applicazione e transazione
	 * @param applicationId
	 * @param transactionId
	 */
	@WebMethod
	public List<NodoErrore> getErroriNodo (@WebParam(name = "applicationId") String applicationId, @WebParam(name = "transactionId") String transactionId) throws  DaoException, MissingParameterException, CSIException {
		log.debug("[MdpCoreCxf:getErroriNodo] BEGIN");
		this.init();

		List<NodoErrore> ret = cPayment.getErroriNodo(applicationId, transactionId);
		
		log.debug("[MdpCoreCxf:getErroriNodo] END");
		return ret;
	}
	
	/**
	 * Operazione - SOLO WEBSERVICE - per l'inizializzazione di un carrello di RPT e RPT multiversamento 
	 * @param t
	 * @param tea
	 * @param elencoRPT
	 * @return
	 * @throws DaoException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws MissingParameterException
	 * @throws CSIException
	 */
	@WebMethod
	public String startTransazioneCarrello(@WebParam(name = "t") Transazione t, @WebParam(name = "tea") TransazioneExtraAttribute[] tea, @WebParam(name="elencoRPT") ElencoRPT elencoRPT)
			throws  BusinessException, SystemException
	{
		LoggerUtil.begin();
		
		if (elencoRPT == null)
			throw new BusinessException("Elenco delle RPT non puo' essere vuoto");
		if (CollectionUtils.isEmpty(elencoRPT.getElenco()))
			throw new BusinessException("Elenco delle RPT non puo' essere vuoto");
		if (elencoRPT.getElenco().size() > 10)
			throw new BusinessException("Impossibile inviare piu' di 10 RPT in un carrello");
		
		this.init();
		String ret = null;
		try {
			ret = cPayment.startTransazione(t, tea, elencoRPT.getElenco());
		} catch (DaoException e) {
			LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
			throw new BusinessException(e.getMessage());
		} catch (Exception e) {
			LoggerUtil.error(":ERRORE GENERICO", e);
			throw new SystemException(e.getMessage());
		}
		
		LoggerUtil.end();
		return ret;

	}
}
