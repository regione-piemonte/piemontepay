/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpmultiiuvsrv.business.mdpmultiiuv;

import it.csi.coopdiag.api.CalledResource;
import it.csi.coopdiag.api.InvocationNode;
import it.csi.csi.wrapper.CSIException;
import it.csi.mdpnew.mdpmultiiuvsrv.business.mdpmultiiuv.interfaces.MdpMultiIuvBusinessInterface;
import it.csi.mdpnew.mdpmultiiuvsrv.dto.mdpmultiiuv.IuvComplexResponse;
import it.csi.mdpnew.mdpmultiiuvsrv.exception.mdpmultiiuv.ErrorParameterException;
import it.csi.mdpnew.mdpmultiiuvsrv.exception.mdpmultiiuv.MdpMultiIuvSrvException;
import it.csi.mdpnew.mdpmultiiuvsrv.exception.mdpmultiiuv.MissingParameterException;
import it.csi.mdpnew.mdpmultiiuvsrv.util.MdpMultiIuvConstants;
import it.csi.util.beanlocatorfactory.ServiceBeanLocator;
import it.csi.util.beanlocatorfactory.ServiceBeanLocatorException;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import javax.xml.soap.SOAPException;

import org.apache.log4j.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MdpMultiIuvBean implements SessionBean, MdpMultiIuvBusinessInterface {

	private static final long serialVersionUID = 1L;

	// business delegate contenente le implementazioni del servizio
	protected MdpMultiIuvImpl delegate = null;
	public SessionContext ctx = null;

	// / Operazioni esposte dal servizio

	public IuvComplexResponse getIuvComplex(String idApplication, String codicePagamento, Integer numeroRichiesto, String timeStamp, String mac)
			throws SOAPException, MdpMultiIuvSrvException, MissingParameterException, ErrorParameterException {

		Logger logger = getLogger("ejb");
		IuvComplexResponse ret = new IuvComplexResponse();
		logger.info("[MdpMultiIuvBean::getMultiIUVComplex] - BEGIN");
		ret = delegate.getIuvComplexResponse(idApplication, codicePagamento, numeroRichiesto, timeStamp, mac);
		logger.info("[MdpMultiIuvBean::getMultiIUVComplex] - END");
		return ret;
	}

	public boolean testResources() throws CSIException {
		return delegate.testResources();
	}

	public InvocationNode selfCheck(CalledResource[] alreadyChecked) throws CSIException {
		return delegate.selfCheck(alreadyChecked);
	}

	public boolean hasSelfCheck() throws CSIException {
		return delegate.hasSelfCheck();
	}

	// / lifecycle dell EJB

	public void ejbActivate() throws EJBException, RemoteException {

	}

	public void ejbPassivate() throws EJBException, RemoteException {

	}

	public void ejbRemove() throws EJBException, RemoteException {

	}

	public void ejbCreate() throws CreateException {
		Logger logger = getLogger("ejb");
		logger.debug("[MdpMultiIuvBean::create] - BEGIN");
		logger.debug("[MdpMultiIuvBean::create] - END");
	}

	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {

		Logger logger = getLogger(null);
		logger.debug("[MdpMultiIuvBean::setSessionContext] - BEGIN");
		this.ctx = ctx;
		ApplicationContext context;
		try {
			context = new ClassPathXmlApplicationContext("beanContext.xml");
			logger.debug("[MdpMultiIuvBean::setSessionContext] caricato ClassPathXmlApplicationContext " + context.getDisplayName());
			this.ctx = ctx;
			delegate = (MdpMultiIuvImpl) ServiceBeanLocator.getBeanByName("delegate");
		}
		catch (BeansException e) {
			logger.error("[MdpMultiIuvBean::setSessionContext] - errore ",e);
		}
		catch (ServiceBeanLocatorException e) {
			logger.error("[MdpMultiIuvBean::setSessionContext] - errore ",e);
		}
		logger.debug("[MdpMultiIuvBean::setSessionContext] - END");
	}

	protected Logger getLogger(String subsystem) {
		if (subsystem != null)
			return Logger.getLogger(MdpMultiIuvConstants.LOGGER_PREFIX + "." + subsystem);
		else
			return Logger.getLogger(MdpMultiIuvConstants.LOGGER_PREFIX);
	}

}
