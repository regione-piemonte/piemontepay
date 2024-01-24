/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpiuvsrv.webservice;

import it.csi.csi.wrapper.CSIException;
import it.csi.csi.wrapper.SystemException;
import it.csi.csi.wrapper.UnrecoverableException;

import it.csi.mdpnew.mdpiuvsrv.business.mdpiuv.interfaces.MdpiuvBusinessInterface;
import it.csi.mdpnew.mdpiuvsrv.exception.mdpiuv.MdpIuvSrvException;
import it.csi.mdpnew.mdpiuvsrv.util.MdpiuvConstants;


import java.rmi.RemoteException;
import java.util.List;

import javax.jws.WebService;
import javax.xml.soap.SOAPException;

import org.apache.log4j.Logger;

@WebService(endpointInterface = "it.csi.mdpnew.mdpiuvsrv.webservice.MdpiuvWSInterface",
		    targetNamespace = "http://mdpnew.csi.it/mdpiuv/", 
		    portName = "MdpiuvWSPort",
		    serviceName = "MdpiuvWSInterface")
public class MdpiuvWSImpl implements MdpiuvWSInterface {
	
	//private Log log = LogFactory.getLog(MdpiuvConstants.MDPIUV_WEBSERVICES_LOG_CATEGORY);

	protected static Logger log = Logger.getLogger(MdpiuvConstants.MDPIUV_ROOT_LOG_CATEGORY+".ws");
     MdpiuvBusinessInterface mdpiuvBusinessInterface;
	
     
    
     
	//MdpiuvSrv mdpiuvBusinessInterface;
	
	
	
	
	public String getSingleIUV(String idApplication,
										   String dettaglioPagamento)throws  SOAPException, RemoteException, SystemException, UnrecoverableException, MdpIuvSrvException, CSIException {
		
		log.debug("[MdpiuvWSImpl:getSingleIUV] BEGIN");
		
		String ris = mdpiuvBusinessInterface.getSingleIUV(idApplication,dettaglioPagamento);
		
		
		//GetStringaResultWs risWs = new GetStringaResultWs();
		
		//risWs.setIuvContent(ris);
		
		log.debug("[MdpiuvWSImpl:getSingleIUV] END");
        //return risWs;
		return ris;
	}	
	
	
	public String[] getMultiIUV(String idApplication, 
									   Integer numeroRichiesto,
									   String dettaglioPagamento)throws SOAPException, RemoteException, SystemException, UnrecoverableException, MdpIuvSrvException, CSIException {
		
	    log.debug("[MdpiuvWSImpl:getMultIUV] BEGIN");
		
	    String[] array = mdpiuvBusinessInterface.getMultIUV(idApplication, numeroRichiesto, dettaglioPagamento);
	    
		
	   // GetArrayResultWS arrayWs = new GetArrayResultWS();
	    
	   // arrayWs.setIuvGroup(array);
	    
		log.debug("[MdpiuvWSImpl:getMultIUV] END");
		
		return array;
	}
	


	
	
	public MdpiuvBusinessInterface getMdpiuvBusinessInterface() {
		return mdpiuvBusinessInterface;
	}

	public void setMdpiuvBusinessInterface(
			MdpiuvBusinessInterface mdpiuvBusinessInterface) {
		this.mdpiuvBusinessInterface = mdpiuvBusinessInterface;
	}


	public IuvComplex getSingleIUVComplex(String idApplication, String codicePagamento) throws SOAPException, RemoteException, SystemException, UnrecoverableException, MdpIuvSrvException,
			CSIException {
		// TODO Auto-generated method stub
		return null;
	}


	public List<IuvComplex> getMultiIUVComplex(String idApplication, String codicePagamento, Integer numeroOccorrenzeIUV) {
		// TODO Auto-generated method stub
		return null;
	}


	

	
}
