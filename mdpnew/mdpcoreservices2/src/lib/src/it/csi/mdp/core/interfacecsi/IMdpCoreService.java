/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.interfacecsi;

import it.csi.csi.wrapper.WrapperControl;
import it.csi.mdp.core.business.dto.Application;
import it.csi.mdp.core.business.dto.PartAnComune;
import it.csi.mdp.core.business.dto.Transazione;
import it.csi.mdp.core.business.dto.TransazioneExtraAttribute;
import it.csi.mdp.core.business.dto.Vapplicationcomuni;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.paymentmanager.local.AppGateway;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;

public interface IMdpCoreService extends WrapperControl
{
	public AppGateway[] getModalitaPagamento(Transazione t,String appId) throws RemoteException, DaoException,NamingException, CreateException ;
	public Transazione initTransazione (String app_id, String basket_id) throws RemoteException, DaoException, NamingException, CreateException, DatatypeConfigurationException;
	public String startTransazione (Transazione t, TransazioneExtraAttribute [] tea ) throws RemoteException, DaoException, NamingException, CreateException;
	public Transazione getStatoTransazione(String transaction_id) throws RemoteException, DaoException, NamingException, CreateException ;
	public Application getApplication(String applicationid) throws RemoteException, DaoException;
	//public boolean testResources() throws it.csi.csi.wrapper.SystemException;
	//public boolean isAlive() throws RemoteException;
	public PartAnComune getComuneProvincia (String istatComune) throws RemoteException, DaoException;
	public Vapplicationcomuni getPagonetCustomData (String applicationId, String gatewayId) throws RemoteException ,DaoException;

	//public void setStatoTransazione(@WebParam(name = "transaction_id")String transaction_id, @WebParam(name = "stato")long stato, @WebParam(name = "gwStato") String gwStato) throws RemoteException, DaoException;
}
