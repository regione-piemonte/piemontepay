/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpiuvsrv.business.mdpiuv;

import it.csi.csi.wrapper.CSIException;
import it.csi.csi.wrapper.SystemException;
import it.csi.csi.wrapper.UnrecoverableException;
import it.csi.mdpnew.mdpiuvsrv.business.mdpiuv.interfaces.MdpiuvBusinessInterface;
import it.csi.mdpnew.mdpiuvsrv.dto.mdpiuv.*;
import it.csi.mdpnew.mdpiuvsrv.interfacecsi.mdpiuv.*;
import it.csi.mdpnew.mdpiuvsrv.util.MdpiuvConstants;
import it.csi.mdpnew.mdpiuvsrv.util.Utility;
import it.csi.mdpnew.mdpiuvsrv.exception.mdpiuv.*;
import it.csi.util.beanlocatorfactory.ServiceBeanLocator;

import java.rmi.RemoteException;
import java.util.HashMap;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.xml.soap.SOAPException;

import org.apache.log4j.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.remoting.soap.SoapFaultException;


public class MdpiuvBean implements SessionBean, MdpiuvBusinessInterface{

	// business delegate contenente le implementazioni del servizio
	protected MdpiuvImpl delegate = null;
	
	
	public SessionContext ctx=null;
	/// Operazioni esposte dal servizio
	

  public String getDataSistema (it.csi.mdpnew.mdpiuvsrv.dto.mdpiuv.ProvaEntita p1)
					  throws it.csi.csi.wrapper.CSIException, it.csi.csi.wrapper.SystemException, 
					  		 it.csi.csi.wrapper.UnrecoverableException,
					  		 it.csi.mdpnew.mdpiuvsrv.exception.mdpiuv.MdpIuvSrvException

  {
	Logger logger = getLogger("ejb");
	logger.debug("[MdpiuvBean::getDataSistema] - START");
	
	logger.debug("[MdpiuvBean::getDataSistema] - END");
	
	
	
    return delegate.getDataSistema(p1);
  }

	
  
  public String getSingleIUV(String id, String dettaglioPagamento)  throws SOAPException, it.csi.csi.wrapper.CSIException, it.csi.csi.wrapper.SystemException, 
	 it.csi.csi.wrapper.UnrecoverableException,
		 it.csi.mdpnew.mdpiuvsrv.exception.mdpiuv.MdpIuvSrvException{
	  
	  Logger logger = getLogger("ejb");
	  logger.info("[MdpiuvBean::getSingleIUV] - START");
	  String ris ="";
		
		try {
			ris = delegate.getSingleIUV(id, dettaglioPagamento);
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new EJBException(e.getMessage());
		}
		
			
	  logger.info("[MdpiuvBean::getSingleIUV] - END");	
	  return ris;
  }
	
	public String[] getMultIUV(String id, Integer numeroRichiesto, String dettaglioPagamento) throws SOAPException, it.csi.csi.wrapper.CSIException, it.csi.csi.wrapper.SystemException, 
																			 it.csi.csi.wrapper.UnrecoverableException,
																			 it.csi.mdpnew.mdpiuvsrv.exception.mdpiuv.MdpIuvSrvException{
		  
		Logger logger = getLogger("ejb"); 
		String[] ris =null;
		logger.info("[MdpiuvBean::getMultIUV] - START");
		
		
		try {
			ris = delegate.getMultIUV(id, numeroRichiesto, dettaglioPagamento);
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new EJBException(e.getMessage());
		}
		
		logger.info("[MdpiuvBean::getMultIUV] - END");	
		return ris;
		
		
}
	
	
	/*
	public String getSingleIUV(String id, String dettaglioPagamento)  throws SOAPException, it.csi.csi.wrapper.CSIException, it.csi.csi.wrapper.SystemException, 
	 it.csi.csi.wrapper.UnrecoverableException,
		 it.csi.mdpnew.mdpiuvsrv.exception.mdpiuv.MdpIuvSrvException{
	  
	  Logger logger = getLogger("ejb");
	  logger.info("[MdpiuvBean::getSingleIUV] - START");
	  String ris ="";
		
	    
		try {
			ris = delegate.getSingleIUV(id);
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new EJBException(e.getMessage());
		}
		
			
	  logger.info("[MdpiuvBean::getSingleIUV] - END");	
	  return ris;
 }
	
	public String[] getMultIUV(String id, 
							   Integer numeroRichiesto,
							   String dettaglioPagamento) throws SOAPException, it.csi.csi.wrapper.CSIException, it.csi.csi.wrapper.SystemException, 
																			 it.csi.csi.wrapper.UnrecoverableException,
																			 it.csi.mdpnew.mdpiuvsrv.exception.mdpiuv.MdpIuvSrvException{
		  
		Logger logger = getLogger("ejb"); 
		String[] ris =null;
		logger.info("[MdpiuvBean::getMultIUV] - START");
		
		
		try {
			ris = delegate.getMultIUV(id, numeroRichiesto);
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new EJBException(e.getMessage());
		}
		
		logger.info("[MdpiuvBean::getMultIUV] - END");	
		return ris;
		
		
}
	*/
	
	
	public boolean testResources() throws  it.csi.csi.wrapper.CSIException{
		return delegate.testResources();
	}
	
	
	public it.csi.coopdiag.api.InvocationNode selfCheck( it.csi.coopdiag.api.CalledResource[] alreadyChecked ) throws it.csi.csi.wrapper.CSIException{
		return delegate.selfCheck(alreadyChecked);
	}
	
	public boolean hasSelfCheck() throws it.csi.csi.wrapper.CSIException{
		return delegate.hasSelfCheck();
	}
	
	
	/// lifecycle dell EJB
	
	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void ejbRemove() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	public void ejbCreate() {
		Logger logger = getLogger("ejb");
		logger.debug("[MdpiuvBean::create] - BEGIN");
		
		
		logger.debug("[MdpiuvBean::create] - END");
		
    }
	  

	
	
	/*
	public void createImpl(Object initOptions) throws EJBException{
		Logger logger = getLogger(null);
	    logger.debug("[MdpiuvBean::createImpl] - START");
		try{
			delegate = new MdpiuvImpl();
			delegate.init(initOptions);
		}
		catch(Exception ie){
			logger.debug("[MdpiuvBean::createImpl] - ERROR", ie);
			throw new EJBException("Errore nella inizializzazione dell'implementazione del servizio mdpiuv:"+ie.getMessage(),ie);
		}
		finally{
			logger.debug("[MdpiuvBean::createImpl] - END");
		}
	}
	*/
	
	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {
	  
	    /// META-TODO: forse sarebbe opportuno accodare il nome del servizio al logger...
	    Logger logger = getLogger(null);
	    logger.debug("[MdpiuvBean::setSessionContext] - START");
		this.ctx = ctx;
		// contiene eventuali oggetti inizializzati nella sezione seguente e che
		// devono essere passati all'oggetto delegate
		Object implInitOptions = null;
		
		ApplicationContext context;
		try {
			context = new ClassPathXmlApplicationContext("beanContext.xml");
		} catch (BeansException e) {
			
			logger.error("[MdpiuvBean::setSessionContext] - errore : "+e.getMessage());
		}
		
		logger.debug("[MdpiuvBean::setSessionContext] caricato ClassPathXmlApplicationContext ");
		
		this.ctx = ctx;
		
		delegate = (MdpiuvImpl)ServiceBeanLocator.getBeanByName("delegate");
		
		//createImpl(implInitOptions);	
		logger.debug("[MdpiuvBean::setSessionContext] - END");
	}
	
	protected Logger getLogger(String subsystem){
	  if (subsystem!=null)
	  	return Logger.getLogger(MdpiuvConstants.LOGGER_PREFIX+"."+subsystem);
	  else
	    return Logger.getLogger(MdpiuvConstants.LOGGER_PREFIX);
	}



	
}

	
