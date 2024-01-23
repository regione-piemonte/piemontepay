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

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.ws.soap.SOAPFaultException;


@WebService 
public interface IMdpCoreCxf
{
	
	public AppGateway[] getModalitaPagamento(@WebParam(name = "t")Transazione t,@WebParam(name = "appId")String appId) throws  DaoException,NamingException, CreateException, MissingParameterException,CSIException ;
	
	public AppGatewayInformativa[] getModalitaInformativePagamento(@WebParam(name = "t")Transazione t,@WebParam(name = "appId")String appId) throws  DaoException,NamingException, CreateException, MissingParameterException,CSIException ;
	
	
	public Transazione initTransazione (@WebParam(name = "appId")String app_id, @WebParam(name = "basketId")String basket_id) throws DaoException, NamingException, CreateException, MissingParameterException,DatatypeConfigurationException,SOAPFaultException, CSIException;
	

	public String startTransazione (@WebParam(name = "t")Transazione t, @WebParam(name = "tea")TransazioneExtraAttribute [] tea ) throws  DaoException, NamingException, CreateException, MissingParameterException, CSIException;
	
//	public String startTransazione (@WebParam(name = "t")Transazione t, @WebParam(name = "tea")TransazioneExtraAttribute [] tea ) throws  DaoException, NamingException, CreateException, MissingParameterException, CSIException;
	
	
	public Transazione getStatoTransazione(@WebParam(name = "transaction_id")String transaction_id) throws  DaoException, NamingException, CreateException, MissingParameterException, CSIException ;
	public Application getApplication(@WebParam(name = "appId")String appId) throws  DaoException, MissingParameterException, CSIException;
	public Vapplicationcomuni getPagonetCustomData (@WebParam(name = "applicationId")String applicationId, @WebParam(name = "gatewayId")String gatewayId) throws DaoException, MissingParameterException, CSIException;
	public PartAnComune getComuneProvincia (String istatComune) throws  DaoException, MissingParameterException, CSIException;
	
	public RicevutaTelematicaNodo getRTperIUV (@WebParam(name = "iuv") String iuv) throws  DaoException, MissingParameterException, CSIException;
	public RicevutaTelematicaNodo getRTperIUVTransazione (@WebParam(name = "iuv") String iuv, @WebParam(name = "transactionId") String transactionId) throws  DaoException, MissingParameterException, CSIException;
	public List<RicevutaTelematicaNodo> getListaRTperIUV (@WebParam(name = "iuv") String iuv) throws  DaoException, MissingParameterException, CSIException;
	public List<NodoErrore> getErroriNodo (@WebParam(name = "applicationId") String applicationId, @WebParam(name = "transactionId") String transactionId) throws  DaoException, MissingParameterException, CSIException;
	public String getUrlWisp(@WebParam(name = "applicationId")String applicationId, @WebParam(name = "transazione")Transazione transaction,@WebParam(name = "parametriUrlWisp")ParametriUrlWisp paramentriUrlWisp, @WebParam(name="parametriAggiuntivi")List<ChiaveValore> prametriAggiuntivi) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException, CSIException;
	public InformativePSPScelto getSceltaWisp(@WebParam(name = "applicationId")String applicationId, @WebParam(name = "transactionId")String transactionId) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException, CSIException;
	//public Transazione confirmPayment(String transactionid) throws RemoteException, DaoException,MissingParameterException, NamingException;
	//public void setStatoTransazione(@WebParam(name = "transaction_id")String transaction_id, @WebParam(name = "stato")long stato, @WebParam(name = "gwStato") String gwStato) throws RemoteException, DaoException;
	@WebMethod(operationName="startTransazioneCarrello")
	public String startTransazioneCarrello (@WebParam(name = "t")Transazione t, @WebParam(name = "tea")TransazioneExtraAttribute [] tea, @WebParam(name="elencoRPT") ElencoRPT elencoRPTE) throws  BusinessException, SystemException;
	
}
