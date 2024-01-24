/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.business.mgr;

import it.csi.mdp.mdpboweb.business.MDPCoreHelper;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoTransazione;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt;
import it.csi.mdp.mdpboweb.util.Constants;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.apache.axis.AxisFault;
import org.apache.log4j.Logger;

public class GestoreCoreMdpBackOffice {
	// ///////////////////////////////////////////////////////////////////////
	// GESTORE_MDP_BACKOFFICE
	//
	// ///////////////////////////////////////////////////////////////////////
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	/**
	 * 
	 * @param user
	 * @param transactionId
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String getStatoTransazione(UserInfoExt user,String transactionId) throws AxisFault,
			MalformedURLException, RemoteException, Exception {
		log.info("[GestoreCoreMdpBackOffice::getStatoTransazione]  BEGIN ");
		MDPCoreHelper MDPHelper = new MDPCoreHelper();
		String ris = "";
		long codStato = MDPHelper.getStatoTransazione(transactionId).getCodStato();
		ArrayList<StatoTransazione> listaStati = new GestoreMDPBackOffice().getListaStatiByUser( user);
		for(int a=0;a <listaStati.size();a++){
			StatoTransazione statosel = listaStati.get(a);
			if(statosel.getCodStato()==codStato){
				ris = 	"codice stato: " + codStato +"   descrizione stato "+  statosel.getDescrStato();
				break;
			}
		}
		log.info("[GestoreCoreMdpBackOffice::getStatoTransazione]  END ");
		return ris;
	}
}
