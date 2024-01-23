/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.interfacecsi;

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
import it.csi.mdp.core.business.dto.multicarrello.RPTData;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.paymentmanager.local.AppGateway;
import it.csi.mdp.core.business.paymentmanager.local.AppGatewayInformativa;
import it.csi.mdp.core.interfacecxf.MissingParameterException;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;

public interface IMdpCoreCsi
{
	public AppGateway[] getModalitaPagamento(Transazione t,String appId) throws  DaoException,NamingException, CreateException, CSIException ;
	public AppGatewayInformativa[] getModalitaInformativePagamento(Transazione t,String appId) throws  DaoException,NamingException, CreateException, CSIException ;
	public Transazione initTransazione (String app_id, String basket_id) throws  DaoException, NamingException, CreateException, DatatypeConfigurationException, CSIException;
	public String startTransazione (Transazione t, TransazioneExtraAttribute [] tea ) throws DaoException, NamingException, CreateException, CSIException;
	public Transazione getStatoTransazione(String transaction_id) throws DaoException, NamingException, CreateException , CSIException;
	public Application getApplication(String applicationid) throws  DaoException, CSIException;
	//public boolean testResources() throws  it.csi.csi.wrapper.SystemException, CSIException;
	//public boolean isAlive() throws CSIException;
	public PartAnComune getComuneProvincia (String istatComune) throws CSIException, DaoException;
	public Vapplicationcomuni getPagonetCustomData (String applicationId, String gatewayId) throws CSIException ,DaoException;
	public RicevutaTelematicaNodo getRTperIUV (String iuv) throws CSIException ,DaoException;
	public RicevutaTelematicaNodo getRTperIUVTransazione (String iuv, String transactionId) throws CSIException ,DaoException;
	public List<RicevutaTelematicaNodo> getListaRTperIUV (String iuv) throws CSIException ,DaoException;
	public List<NodoErrore> getErroriNodo (String applicationId, String transactionId) throws DaoException, MissingParameterException;
	public String getUrlWisp(String applicationId, Transazione transaction,ParametriUrlWisp paramentriUrlWisp, List<ChiaveValore> prametriAggiuntivi) throws CSIException, DaoException;
	public InformativePSPScelto getSceltaWisp(String applicationId, String transactionId) throws CSIException, DaoException;
	public String startTransazione (Transazione t, TransazioneExtraAttribute [] tea, List<RPTData> elencoRPT) throws DaoException, NamingException, CreateException, CSIException;
	//public void setStatoTransazione(@WebParam(name = "transaction_id")String transaction_id, @WebParam(name = "stato")long stato, @WebParam(name = "gwStato") String gwStato) throws RemoteException, DaoException;
}
