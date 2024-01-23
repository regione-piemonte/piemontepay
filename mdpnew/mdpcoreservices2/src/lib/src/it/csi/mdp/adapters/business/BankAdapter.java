/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.adapters.business;

import java.rmi.RemoteException;
import java.util.List;

import it.csi.mdp.core.business.dto.*;
import it.csi.mdp.core.business.dto.multicarrello.RPTData;

import javax.ejb.EJBObject;
public interface BankAdapter extends EJBObject
{
	/**
	 * Inizializza la transazione
	 * @param t
	 * @return
	 * @throws RemoteException
	 */
	public Transazione initTransazione (Transazione t) throws RemoteException;
	/**
	 * Start della transazione
	 * @param t
	 * @param gd
	 * @param acf
	 * @return
	 * @throws RemoteException
	 * @deprecated al posto di questa usare startTransazione(Transazione ,Gatewaydetail ,Applicationcustomfields[], TransazioneExtraAttribute[])
	 */
	public String startTransazione (Transazione t,Gatewaydetail gd,Applicationcustomfields [] acf) throws RemoteException;
	/**
	 * Start della transazione che prevede il passaggio dei TransazioneExtraAttribute, da preferire per completezza al vecchio metodo
	 * @param t
	 * @param gd
	 * @param acf
	 * @param tea
	 * @return
	 * @throws RemoteException
	 */
	public Transazione startTransazione (Transazione t,Gatewaydetail gd,Applicationcustomfields [] acf, ApplicationDetail ad, TransazioneExtraAttribute[] tea) throws RemoteException, BusinessException, SystemException;
	/**
	 * Start della transazione che prevede il passaggio dell'elenco RPT: specifica nodo SPC
	 * @param t
	 * @param gd
	 * @param acf
	 * @param tea
	 * @return
	 * @throws RemoteException
	 */
	public Transazione startTransazione (Transazione t,Gatewaydetail gd,Applicationcustomfields [] acf, ApplicationDetail ad, TransazioneExtraAttribute[] tea, List<RPTData> elencoRPT) throws RemoteException, BusinessException, SystemException;
	/**
	 * Start della transazione
	 * @param t
	 * @param gd
	 * @param acf
	 * @return
	 * @throws RemoteException
	 * @deprecated al posto di questa usare startTransazione(Transazione ,Gatewaydetail ,Applicationcustomfields[], TransazioneExtraAttribute[])
	 */
	public Transazione startTransazione (Transazione t,Gatewaydetail gd,Applicationcustomfields [] acf, ApplicationDetail ad) throws RemoteException;
	public Transazione getCommissioni(Transazione t, ApplicationDetail ad) throws RemoteException;
	public Transazione getStatoTransazione (Transazione t, Gatewaydetail gd, Applicationcustomfields [] acf,long oldState) throws RemoteException;
	public boolean isAlive () throws RemoteException;
	public Transazione confirmPayment(Transazione t,Applicationcustomfields [] acf, Gatewaydetail gd) throws RemoteException;
	public Transazione refundPayment (Transazione t,Applicationcustomfields [] acf, Gatewaydetail gd) throws RemoteException;
	
}
