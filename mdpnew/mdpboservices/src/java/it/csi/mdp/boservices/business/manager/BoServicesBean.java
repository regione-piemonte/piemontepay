/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.business.manager;

import it.csi.mdp.boservices.business.manager.bean.BoServicesBeanImpl;
import it.csi.mdp.boservices.dto.CodiciEsitoPagamentoDTO;
import it.csi.mdp.boservices.dto.EntiDTO;
import it.csi.mdp.boservices.dto.FlussoRiversamentoDTO;
import it.csi.mdp.boservices.dto.IbanEnteApplicationDTO;
import it.csi.mdp.boservices.dto.StatisticaApplicazioneTransazioneDTO;
import it.csi.mdp.boservices.dto.ParametroNodoDTO;
import it.csi.mdp.boservices.dto.TipoVersamentoDTO;
import it.csi.mdp.boservices.dto.FlussoSingoloPagamentoDTO;
import it.csi.mdp.boservices.dto.GiornaleEventoDTO;
import it.csi.mdp.boservices.dto.InformativePSPDTO;
import it.csi.mdp.boservices.dto.RPTDTO;
import it.csi.mdp.boservices.dto.RTDTO;
import it.csi.mdp.boservices.dto.StatiRptDTO;
import it.csi.mdp.boservices.exception.AuthException;
import it.csi.mdp.boservices.exception.MissingParameterException;
import it.csi.mdp.boservices.util.Credentials;
import it.csi.mdp.boservices.util.VtransazioneResult;
import it.csi.mdp.core.business.dao.ApplicationDao;
import it.csi.mdp.core.business.dto.Application;
import it.csi.mdp.core.business.dto.ApplicationDetail;
import it.csi.mdp.core.business.dto.Applicationcustomfields;

import it.csi.mdp.core.business.dto.ApplicationConfiguration;
import it.csi.mdp.core.business.dto.Auditactions;
import it.csi.mdp.core.business.dto.Commission;
import it.csi.mdp.core.business.dto.Config;
import it.csi.mdp.core.business.dto.CsiLogAudit;
import it.csi.mdp.core.business.dto.Gateway;
import it.csi.mdp.core.business.dto.Gatewaycustomfields;
import it.csi.mdp.core.business.dto.Gatewaydetail;
import it.csi.mdp.core.business.dto.GiornaleEvento;
import it.csi.mdp.core.business.dto.IbanEnteApplication;
import it.csi.mdp.core.business.dto.Language;
import it.csi.mdp.core.business.dto.MdpBckofficegroupappmapping;
import it.csi.mdp.core.business.dto.MdpBckofficegroups;
import it.csi.mdp.core.business.dto.MdpBckroles;
import it.csi.mdp.core.business.dto.MdpBckrolesgroupmap;
import it.csi.mdp.core.business.dto.MdpBckusers;
import it.csi.mdp.core.business.dto.MdpBckusersgroup;
import it.csi.mdp.core.business.dto.MdpCurrency;
import it.csi.mdp.core.business.dto.Paymentmode;
import it.csi.mdp.core.business.dto.StatoTransazione;
import it.csi.mdp.core.business.dto.Transazione;
import it.csi.mdp.core.business.dto.Verrori;
import it.csi.mdp.core.business.dto.Vtransazione;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.factory.DaoFactory;
import it.csi.util.performance.StopWatch;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.jws.WebMethod;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class BoServicesBean implements SessionBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8550628316866107727L;
	protected static Logger log = Logger.getLogger(it.csi.mdp.boservices.util.Constants.APPLICATION_CODE);

	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbRemove() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void setSessionContext(SessionContext arg0) throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public List<Application> listApplicationByFlussoApplicazione(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException{
		
		log.debug("[BoServicesBean::listApplicationByFlussoApplicazione] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<Application> appList = bsbi.listApplicationByFlussoApplicazione(auth);

		log.debug("[BoServicesBean::listApplicationByFlussoApplicazione] END");

		stopWatch.stop();
		return appList;
		
	}
		
	public void setApplication(Credentials auth, Application app, boolean overwrite) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::setApplication] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.setApplication(auth, app, overwrite, this.getClientIp());

		log.debug("[BoServicesBean::setApplication] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "setApplication()", "chiamata setApplication", "setApplication");

	}

	public List<Application> getApplication(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getApplication] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<Application> appList = bsbi.getApplication(auth, this.getClientIp());

		log.debug("[BoServicesBean::getApplication] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getApplication()", "chiamata setApplication", "setApplication");
		return appList;

	}

	public Application getApplicationById(Credentials auth, String appId) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getApplicationById] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		Application appList = bsbi.getApplicationById(auth, appId, this.getClientIp());

		log.debug("[BoServicesBean::getApplication] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "setApplication()", "chiamata setApplication", "setApplication");
		return appList;

	}

	public void setApplicationDetail(Credentials auth, ApplicationDetail app) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::setApplicationDetail] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.setApplicationDetail(auth, app, this.getClientIp());

		log.debug("[BoServicesBean::setApplicationDetail] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "setApplicationDetail()", "chiamata setApplicationDetail", "setApplicationDetail");
		return;
	}

	public List<ApplicationDetail> getApplicationDetail(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getApplicationDetail] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<ApplicationDetail> appList = bsbi.getApplicationDetail(auth, this.getClientIp());

		log.debug("[BoServicesBean::getApplication] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getApplicationDetail()", "chiamata getApplicationDetail", "getApplicationDetail");
		return appList;
	}

	public List<ApplicationDetail> getApplicationDetailById(Credentials auth, String appid) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getApplicationDetail] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<ApplicationDetail> appList = bsbi.getApplicationDetail(auth, appid, this.getClientIp());

		log.debug("[BoServicesBean::getApplication] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getApplicationDetail()", "chiamata getApplicationDetail", "getApplicationDetail");
		return appList;
	}

	public List<Applicationcustomfields> getApplicationCustomFields(Credentials auth, String appId) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getApplicationCustomFields] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<Applicationcustomfields> appList = bsbi.getApplicationCustomFields(auth, appId, this.getClientIp());

		log.debug("[BoServicesBean::getApplicationCustomFields] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getApplicationCustomFields()", "chiamata getApplicationCustomFields", "getApplicationCustomFields");
		return appList;
	}

	public void setApplicationCustomFields(Credentials auth, Applicationcustomfields[] app) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::setApplicationCustomFields] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.setApplicationCustomFields(auth, app, this.getClientIp());

		log.debug("[BoServicesBean::setApplicationCustomFields] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "setApplicationCustomFields()", "chiamata setApplicationCustomFields", "setApplicationCustomFields");
		return;
	}

	public Gateway setGateway(Credentials auth, Gateway app) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::setGateway] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		app = bsbi.setGateway(auth, app, this.getClientIp());

		log.debug("[BoServicesBean::setGateway] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "setApplicationCustomFields()", "chiamata setApplicationCustomFields", "setApplicationCustomFields");
		return app;
	}

	public Gateway getGatewayById(Credentials auth, String gwId) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getGateway byid] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		Gateway gw = bsbi.getGateway(auth, gwId, this.getClientIp());

		log.debug("[BoServicesBean::getGateway byid] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getGateway()", "chiamata getGateway", "getGateway");
		return gw;
	}

	public List<Gateway> getGateway(Credentials auth) throws RemoteException, DaoException, AuthException {
		log.debug("[BoServicesBean::getGateway byid] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<Gateway> gw = bsbi.getGateway(auth, this.getClientIp());

		log.debug("[BoServicesBean::getGateway byid] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getGateway()", "chiamata getGateway", "getGateway");
		return gw;
	}

	public Paymentmode setPaymentMode(Credentials auth, Paymentmode paymode) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::setPaymentMode] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		paymode = bsbi.setPaymentMode(auth, paymode, this.getClientIp());

		log.debug("[BoServicesBean::setGateway] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "setPaymentMode()", "chiamata setPaymentMode", "setPaymentMode");
		return paymode;
	}

	public List<Paymentmode> getPaymentMode(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getPaymentMode ] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<Paymentmode> gw = bsbi.getPaymentMode(auth, this.getClientIp());

		log.debug("[BoServicesBean::getGateway byid] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getPaymentMode()", "chiamata getPaymentMode", "getPaymentMode");
		return gw;

	}

	public Paymentmode getPaymentModeById(Credentials auth, String payModeId) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getGateway byid] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		Paymentmode gw = bsbi.getPaymentMode(auth, payModeId, this.getClientIp());

		log.debug("[BoServicesBean::getGateway byid] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getGateway()", "chiamata getGateway", "getGateway");
		return gw;
	}

	public Gatewaydetail setGatewayDetail(Credentials auth, Gatewaydetail app) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::setGatewayDetail] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		app = bsbi.setGatewayDetail(auth, app, this.getClientIp());

		log.debug("[BoServicesBean::setGatewayDetail] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "setGatewayDetail()", "chiamata setGatewayDetail", "setGatewayDetail");
		return app;
	}

	public Gatewaydetail getGatewayDetailByIds(Credentials auth, String gwId, String payId) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getGatewayDetail] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		Gatewaydetail app = bsbi.getGatewayDetail(auth, gwId, payId, this.getClientIp());

		log.debug("[BoServicesBean::getGatewayDetail] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getGatewayDetail()", "chiamata getGatewayDetail", "getGatewayDetail");
		return app;
	}

	public List<Gatewaydetail> getGatewayDetailById(Credentials auth, String gwId) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getGatewayDetail] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<Gatewaydetail> lapp = bsbi.getGatewayDetail(auth, gwId, this.getClientIp());

		log.debug("[BoServicesBean::getGatewayDetail] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getGatewayDetail()", "chiamata getGatewayDetail", "getGatewayDetail");
		return lapp;
	}

	public List<Gatewaydetail> getGatewayDetail(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getGatewayDetail] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<Gatewaydetail> lapp = bsbi.getGatewayDetail(auth, this.getClientIp());

		log.debug("[BoServicesBean::getGatewayDetail] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getGatewayDetail()", "chiamata getGatewayDetail", "getGatewayDetail");
		return lapp;
	}

	public List<MdpCurrency> getMdpCurrency(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getMdpCurrency] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<MdpCurrency> lapp = bsbi.getMdpCurrency(auth, this.getClientIp());

		log.debug("[BoServicesBean::getMdpCurrency] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getMdpCurrency()", "chiamata getMdpCurrency", "getMdpCurrency");
		return lapp;
	}

	/*
	 * public List<Auditing> getAuditing(Credentials auth ,String app, Date
	 * datestart, Date dateend, String transactionid, String user)throws
	 * RemoteException , DaoException, AuthException,MissingParameterException;
	 * 
	 * public void setAuditing (Credentials auth, Auditing audit) throws
	 * RemoteException , DaoException, AuthException,MissingParameterException;
	 * 
	 * 
	 * public void setMdpCurrency (Credentials auth , MdpCurrency ccy) throws
	 * RemoteException , DaoException, AuthException,MissingParameterException;
	 */

	public List<MdpBckusers> getMdpUsers(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getMdpUsers ] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<MdpBckusers> lapp = bsbi.getMdpUsers(auth, this.getClientIp());

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getMdpUsers()", "chiamata getMdpUsers", "getMdpUsers");
		log.debug("[" + this.getClass().getName() + "::getMdpUsers ] END");
		return lapp;
	}

	public MdpBckusers getMdpUsersByCfisc(Credentials auth, String cfisc) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getMdpUsersByCfisc ] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		MdpBckusers lapp = bsbi.getMdpUsersByCfisc(auth, cfisc, this.getClientIp());

		log.debug("[BoServicesBean::getMdpUsersByCfisc] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getMdpUsersByCfisc()", "chiamata getMdpUsersByCfisc", "getMdpUsersByCfisc");
		log.debug("[" + this.getClass().getName() + "::getMdpUsersByCfisc ] END");
		return lapp;
	}

	public List<MdpBckofficegroups> getMdpBckGroupsByCfisc(Credentials auth, String cfisc) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getMdpBckGroupsByCfisc ] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<MdpBckofficegroups> lapp = bsbi.getMdpBckGroupsByCfisc(auth, cfisc, this.getClientIp());

		log.debug("[BoServicesBean::getMdpUsersByCfisc] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getMdpBckGroupsByCfisc()", "chiamata getMdpBckGroupsByCfisc", "getMdpBckGroupsByCfisc");
		log.debug("[" + this.getClass().getName() + "::getMdpBckGroupsByCfisc ] END");
		return lapp;
	}

	public List<MdpBckofficegroups> getMdpBckGroups(Credentials auth, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getMdpBckGroups ] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<MdpBckofficegroups> lapp = bsbi.getMdpBckGroups(auth, this.getClientIp());

		log.debug("[BoServicesBean::getMdpBckGroups] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getMdpBckGroups()", "chiamata getMdpBckGroups", "getMdpBckGroups");
		log.debug("[" + this.getClass().getName() + "::getMdpBckGroups ] END");
		return lapp;
	}

	public List<MdpBckroles> getMdpBckRoles(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getMdpBckRoles] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<MdpBckroles> lapp = bsbi.getMdpBckRoles(auth, this.getClientIp());

		log.debug("[BoServicesBean::getMdpCurrency] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getMdpBckRoles()", "chiamata getMdpBckRoles", "getMdpBckRoles");
		return lapp;
	}

	public MdpBckroles setMdpRole(Credentials auth, MdpBckroles role) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::setMdpRole] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		MdpBckroles lapp = bsbi.setMdpRole(auth, role, this.getClientIp());

		log.debug("[BoServicesBean::getMdpCurrency] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "setMdpRole()", "chiamata setMdpRole", "setMdpRole");
		return lapp;
	}

	public MdpBckusers setMdpUser(Credentials auth, MdpBckusers user, List<MdpBckusersgroup> usergroup) throws RemoteException, MissingParameterException, DaoException, AuthException {
		log.debug("[BoServicesBean::setMdpUser] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		MdpBckusers lapp = bsbi.setMdpUser(auth, user, usergroup, this.getClientIp());

		log.debug("[BoServicesBean::setMdpUser] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "setMdpUser()", "chiamata setMdpUser", "setMdpUser");
		return lapp;
	}

	public MdpBckofficegroups setMdpGroup(Credentials auth, MdpBckofficegroups group, List<MdpBckofficegroupappmapping> groupapp, MdpBckrolesgroupmap grouprole) throws RemoteException, DaoException,
			AuthException, MissingParameterException {
		log.debug("[BoServicesBean::setMdpGroup] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		MdpBckofficegroups lapp = bsbi.setMdpGroup(auth, group, groupapp, grouprole, this.getClientIp());
		log.debug("[BoServicesBean::setMdpGroup] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "setMdpGroup()", "chiamata setMdpGroup", "setMdpGroup");
		return lapp;
	}

	public MdpCurrency getMdpCurrencyByKey(Credentials auth, String gatewayid, String mdpcurrencycode, String gtwcurrencycode) throws RemoteException, DaoException, AuthException,
			MissingParameterException {
		log.debug("[BoServicesBean::getMdpCurrency] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		MdpCurrency lapp = bsbi.getMdpCurrencyByKey(auth, gatewayid, mdpcurrencycode, gtwcurrencycode, this.getClientIp());
		log.debug("[BoServicesBean::getMdpCurrency] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getMdpCurrency()", "chiamata getMdpCurrency", "getMdpCurrency");
		return lapp;
	}

	public void setMdpCurrency(Credentials auth, MdpCurrency ccy) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::setMdpGroup] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.setMdpCurrency(auth, ccy, this.getClientIp());
		log.debug("[BoServicesBean::setMdpGroup] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "setMdpGroup()", "chiamata setMdpGroup", "setMdpGroup");
		return;
	}

	// public List<CsiLogAudit> getAuditing(Credentials auth, String app, Date
	// datestart, Date dateend, String transactionid, String user)
	// throws RemoteException, DaoException, AuthException,
	// MissingParameterException
	// {
	// log.debug("[BoServicesBean::getAuditing] BEGIN");
	// StopWatch stopWatch = new
	// StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
	// stopWatch.start();
	//
	// BeanFactory bf = new XmlBeanFactory(new
	// ClassPathResource("beanContext.xml"));
	// BoServicesBeanImpl bsbi= (BoServicesBeanImpl)
	// bf.getBean("boServicesBeanImpl");
	//
	// List<CsiLogAudit> lapp = bsbi.getAuditing(auth, app, datestart,
	// dateend, transactionid, user,this.getClientIp());
	// log.debug("[BoServicesBean::getAuditing] END");
	//
	// stopWatch.stop();
	// stopWatch.dumpElapsed("BoServicesBean", "getAuditing()",
	// "chiamata getAuditing", "getAuditing");
	// return lapp;
	// }

	public List<Transazione> getTransazione(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getTransazione] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<Transazione> lapp = bsbi.getTransazione(auth, this.getClientIp());
		log.debug("[BoServicesBean::getTransazione] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getTransazione()", "chiamata getTransazione", "getTransazione");
		return lapp;
	}

	public List<Transazione> getTransazioneByApp(Credentials auth, String appid) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getTransazione] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<Transazione> lapp = bsbi.getTransazioneByApp(auth, appid, this.getClientIp());
		log.debug("[BoServicesBean::getTransazione] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getTransazione()", "chiamata getTransazione", "getTransazione");
		return lapp;
	}

	public List<Transazione> getTransazioneById(Credentials auth, String idTransazione) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getTransazione] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<Transazione> lapp = bsbi.getTransazioneById(auth, idTransazione, this.getClientIp());
		log.debug("[BoServicesBean::getTransazione] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getTransazione()", "chiamata getTransazione", "getTransazione");
		return lapp;
	}

	public List<Vtransazione> getTransazioneView(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getTransazioneView] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<Vtransazione> lapp = bsbi.getTransazioneView(auth, this.getClientIp());
		log.debug("[BoServicesBean::getTransazioneView] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getTransazioneView()", "chiamata getTransazioneView", "getTransazioneView");
		return lapp;
	}

	public List<Vtransazione> getTransazioneViewByApp(Credentials auth, String appId) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getTransazioneViewByApp] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<Vtransazione> lapp = bsbi.getTransazioneViewByApp(auth, appId, this.getClientIp());
		log.debug("[BoServicesBean::getTransazioneView] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getTransazioneViewByApp()", "chiamata getTransazioneViewByApp", "getTransazioneViewByApp");
		return lapp;

	}

	public List<Vtransazione> getTransazioneViewById(Credentials auth, String idTransazione) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getTransazioneViewById] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<Vtransazione> lapp = bsbi.getTransazioneViewById(auth, idTransazione, this.getClientIp());
		log.debug("[BoServicesBean::getTransazioneViewById] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getTransazioneViewById()", "chiamata getTransazioneViewById", "getTransazioneViewById");
		return lapp;
	}

	public Transazione setTransazione(Credentials auth, Transazione transazione) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getTransazione] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		Transazione lapp = bsbi.setTransazione(auth, transazione, this.getClientIp());
		log.debug("[BoServicesBean::getTransazione] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getTransazione()", "chiamata getTransazione", "getTransazione");
		return lapp;
	}

	public ApplicationConfiguration getApplicationConfiguration(Credentials auth, String appId) throws DaoException, AuthException, RemoteException, MissingParameterException {
		log.debug("[BoServicesBean::getApplicationConfiguration] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		ApplicationConfiguration lapp = bsbi.getApplicationConfiguration(auth, appId, this.getClientIp());
		log.debug("[BoServicesBean::getApplicationConfiguration] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getApplicationConfiguration()", "chiamata getApplicationConfiguration", "getApplicationConfiguration");
		return lapp;
	}

	public List<Applicationcustomfields> getApplicationCustomFieldsByGateway(Credentials auth, String appId, String gatewayId) throws RemoteException, DaoException, MissingParameterException,
			AuthException {
		log.debug("[BoServicesBean::getApplicationCustomFieldsByGateway] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<Applicationcustomfields> lapp = bsbi.getApplicationCustomFieldsByGateway(auth, appId, gatewayId, this.getClientIp());
		log.debug("[BoServicesBean::getApplicationCustomFieldsByGateway] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getApplicationConfiguration()", "chiamata getApplicationConfiguration", "getApplicationConfiguration");
		return lapp;
	}

	public VtransazioneResult getTransazioneViewPaged(Credentials auth, int pagina, int transazioniPerPagina) throws RemoteException, DaoException, AuthException, MissingParameterException {

		log.debug("[BoServicesBean::getTransazioneViewPaged] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		VtransazioneResult lapp = bsbi.getTransazioneViewPaged(auth, pagina, transazioniPerPagina, this.getClientIp());
		log.debug("[BoServicesBean::getTransazioneViewPaged] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getTransazioneViewPaged()", "chiamata getTransazioneViewPaged", "getTransazioneViewPaged");
		return lapp;

	}

	public VtransazioneResult getTransazioneViewWithFiltersPaged(Credentials auth, String appId, long codstato, Date datastart, Date dataend, int pagina, int transazioniPerPagina)
			throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getTransazioneViewWithFiltersPaged] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		// bsbi.getTransazioneViewWithFiltersPaged(auth, appId, codstato,
		// datastart, dataend, pagina, transazioniPerPagina,
		// this.getClientIp());
		VtransazioneResult lapp = bsbi.getTransazioneViewWithFiltersPaged(auth, appId, codstato, datastart, dataend, pagina, transazioniPerPagina, this.getClientIp());
		log.debug("[BoServicesBean::getTransazioneViewWithFiltersPaged] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getTransazioneViewWithFiltersPaged()", "chiamata getTransazioneViewWithFiltersPaged", "getTransazioneViewWithFiltersPaged");
		return lapp;
	}

	public List<Config> getBoConfig(Credentials auth) throws DaoException, AuthException, RemoteException, MissingParameterException {
		log.debug("[BoServicesBean::getBoConfig] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		List<Config> lapp = bsbi.getBoConfig(auth, this.getClientIp());

		log.debug("[BoServicesBean::getBoConfig] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getBoConfig()", "chiamata getBoConfig", "getBoConfig");
		return lapp;
	}

	public void setBoConfig(Credentials auth, Config conf) throws DaoException, AuthException, RemoteException, MissingParameterException {
		log.debug("[BoServicesBean::setBoConfig] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		bsbi.setBoConfig(auth, conf, this.getClientIp());

		log.debug("[BoServicesBean::setBoConfig] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "setBoConfig()", "chiamata setBoConfig", "setBoConfig");
	}

	public void deleteBoConfig(Credentials auth, Config conf) throws DaoException, AuthException, RemoteException, MissingParameterException {
		log.debug("[BoServicesBean::deleteBoConfig] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		bsbi.deleteBoConfig(auth, conf, this.getClientIp());

		log.debug("[BoServicesBean::deleteBoConfig] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "deleteBoConfig()", "chiamata deleteBoConfig", "deleteBoConfig");
	}

	public List<Gatewaycustomfields> getGatewayCustomFields(Credentials auth, String gwId) throws RemoteException, DaoException, MissingParameterException, AuthException {
		log.debug("[BoServicesBean::getGatewayCustomFields] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		List<Gatewaycustomfields> l = bsbi.getGatewayCustomFields(auth, gwId, this.getClientIp());

		log.debug("[BoServicesBean::getGatewayCustomFields] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getGatewayCustomFields()", "chiamata getGatewayCustomFields", "getGatewayCustomFields");
		return l;
	}

	public void setGatewayCustomFields(Credentials auth, Gatewaycustomfields[] app) throws RemoteException, DaoException, MissingParameterException, AuthException {
		log.debug("[BoServicesBean::setGatewayCustomFields] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		bsbi.setGatewayCustomFields(auth, app, this.getClientIp());

		log.debug("[BoServicesBean::setGatewayCustomFields] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "setGatewayCustomFields()", "chiamata setGatewayCustomFields", "setGatewayCustomFields");
	}

	public List<Commission> getTipoCommissione(Credentials auth) throws DaoException, AuthException, RemoteException, MissingParameterException {
		log.debug("[BoServicesBean::getTipoCommissione] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		List<Commission> l = bsbi.getTipoCommissione(auth, this.getClientIp());

		log.debug("[BoServicesBean::getTipoCommissione] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getTipoCommissione()", "chiamata getTipoCommissione", "getTipoCommissione");
		return l;
	}

	public List<Vtransazione> getTransazioneWithFilters(Credentials auth, String appId, long codstato, Date datastart, Date dataend) throws RemoteException, DaoException, AuthException,
			MissingParameterException {
		log.debug("[BoServicesBean::getTipoCommissione] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		List<Vtransazione> l = bsbi.getTransazioneWithFilters(auth, appId, codstato, datastart, dataend, this.getClientIp());

		log.debug("[BoServicesBean::getTipoCommissione] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getTipoCommissione()", "chiamata getTipoCommissione", "getTipoCommissione");
		return l;
	}

	public boolean testResources() throws RemoteException, it.csi.csi.wrapper.SystemException {
		log.debug("[BoServicesBean::testResources] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		boolean ret = bsbi.testResources();

		log.debug("[BoServicesBean::testResources] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "testResources()", "chiamata testResources", "testResources");

		return ret;

	}

	public boolean isAlive() throws RemoteException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		return bsbi.isAlive();
	}

	private String getClientIp() {
		String currentThreadName = Thread.currentThread().getName();
		log.debug("Threadname: " + currentThreadName);
		// int begin = currentThreadName.indexOf('[') +1;
		// int end = currentThreadName.indexOf(']')-1;
		String remoteClient = currentThreadName;// .substring(begin, end);
		log.debug("remoteClient: " + remoteClient);
		return remoteClient;
	}

	public void uploadMethod(Credentials auth, String fileName, byte[] fileContent) throws IOException, DaoException, AuthException, RemoteException {
		log.debug("[BoServicesBean::uploadMethod] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.uploadMethod(auth, fileName, fileContent, this.getClientIp());

		log.debug("[BoServicesBean::uploadMethod] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "uploadMethod()", "chiamata uploadMethod", "uploadMethod");

		return;
	}

	public void uploadMethodForApplication(Credentials auth, String fileName, byte[] fileContent, String appId) throws IOException, DaoException, AuthException, RemoteException {
		log.debug("[BoServicesBean::uploadMethodForApplication] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.uploadMethodForApplication(auth, fileName, fileContent, appId, this.getClientIp());

		log.debug("[BoServicesBean::uploadMethodForApplication] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "uploadMethodForApplication()", "chiamata uploadMethodForApplication", "uploadMethodForApplication");

		return;
	}

	public List<Language> getLanguages(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getLanguages] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<Language> ret = bsbi.getLanguages(auth, this.getClientIp());

		log.debug("[BoServicesBean::getLanguages] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getLanguages()", "chiamata getLanguages", "getLanguages");

		return ret;
	}

	public List<Language> getLanguagesByGatewayId(Credentials auth, String gatewayid) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getLanguagesByGatewayId] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<Language> ret = bsbi.getLanguagesByGatewayId(auth, gatewayid, this.getClientIp());

		log.debug("[BoServicesBean::getLanguagesByGatewayId] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getLanguagesByGatewayId()", "chiamata getLanguagesByGatewayId", "getLanguagesByGatewayId");

		return ret;
	}

	public List<Auditactions> getAuditActionsList(Credentials auth) throws DaoException, AuthException, RemoteException, MissingParameterException {
		log.debug("[BoServicesBean::getAuditActionsList] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<Auditactions> ret = bsbi.getAuditActionsList(auth, this.getClientIp());

		log.debug("[BoServicesBean::getAuditActionsList] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getAuditActionsList()", "chiamata getAuditActionsList", "getAuditActionsList");

		return ret;
	}

	public List<StatoTransazione> getStatiTransazione(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getStatiTransazione] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<StatoTransazione> ret = bsbi.getStatiTransazione(auth, this.getClientIp());

		log.debug("[BoServicesBean::getStatiTransazione] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getStatiTransazione()", "chiamata getStatiTransazione", "getStatiTransazione");

		return ret;
	}

	public List<Verrori> getErrorList(Credentials auth, String appId, String transactionId, Date datastart, Date dataend, String gatewayId) throws DaoException, AuthException, RemoteException,
			MissingParameterException {
		log.debug("[BoServicesBean::getErrorList] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<Verrori> ret = bsbi.getErrorList(auth, appId, transactionId, datastart, dataend, gatewayId, this.getClientIp());

		log.debug("[BoServicesBean::getErrorList] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getErrorList()", "chiamata getErrorList", "getErrorList");

		return ret;
	}

	public List<MdpCurrency> getMdpCurrencyByGatewayId(Credentials auth, String gatewayid) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getMdpCurrencyByGatewayId] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<MdpCurrency> ret = bsbi.getMdpCurrencyByGatewayId(auth, gatewayid, this.getClientIp());

		log.debug("[BoServicesBean::getMdpCurrencyByGatewayId] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getMdpCurrencyByGatewayId()", "chiamata getMdpCurrencyByGatewayId", "getMdpCurrencyByGatewayId");

		return ret;
	}

	public List<CsiLogAudit> getAuditing(Credentials auth, List<Application> apps, Date datestart, Date dateend, String transactionid, List<String> actions, List<String> users)
			throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getAuditing] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<CsiLogAudit> ret = bsbi.getAuditing(auth, apps, datestart, dateend, transactionid, actions, users, this.getClientIp());

		log.debug("[BoServicesBean::getAuditing] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getAuditing()", "chiamata getAuditing", "getAuditing");

		return ret;
	}

	public MdpBckusers getMdpUsersById(Credentials auth, int userid) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::getMdpUsersById] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		MdpBckusers ret = bsbi.getMdpUsersById(auth, userid, this.getClientIp());

		log.debug("[BoServicesBean::getMdpUsersById] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getMdpUsersById()", "chiamata getMdpUsersById", "getMdpUsersById");

		return ret;
	}

	public String uploadMethodForApplicationGateway(Credentials auth, String fileName, byte[] fileContent, String appId, String gatewayId) throws IOException, AuthException, DaoException,
			RemoteException {
		log.debug("[BoServicesBean::uploadMethodForApplicationGateway] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		String path = bsbi.uploadMethodForApplicationGateway(auth, fileName, fileContent, appId, gatewayId, this.getClientIp());

		log.debug("[BoServicesBean::getMdpUsersById] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "uploadMethodForApplicationGateway()", "chiamata uploadMethodForApplicationGateway", "uploadMethodForApplicationGateway");

		return path;
	}

	public void setLanguage(Credentials auth, Language lang) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[BoServicesBean::setLanguage] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.setLanguage(auth, lang, this.getClientIp());

		log.debug("[BoServicesBean::setLanguage] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("setLanguage", "setLanguage()", "chiamata setLanguage", "setLanguage");

	}

	public Transazione confirmPayment(Credentials auth, String transactionid) throws RemoteException, DaoException, MissingParameterException, NamingException {
		log.debug("[BoServicesBean::confirmPayment] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		Transazione ret = bsbi.confirmPayment(auth, transactionid, this.getClientIp());

		log.debug("[BoServicesBean::confirmPayment] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "confirmPayment()", "chiamata confirmPayment", "confirmPayment");

		return ret;
	}

	public Transazione refundPayment(Credentials auth, String transactionid) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[BoServicesBean::refundPayment] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		Transazione ret = bsbi.refundPayment(auth, transactionid, this.getClientIp());

		log.debug("[BoServicesBean::refundPayment] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "refundPayment()", "chiamata refundPayment", "refundPayment");

		return ret;
	}

	public void deleteGatewayConfiguration(Credentials auth, String gatewayId) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[BoServicesBean::deleteGatewayConfiguration] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.deleteGatewayConfiguration(auth, gatewayId, this.getClientIp());

		log.debug("[BoServicesBean::deleteGatewayConfiguration] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "deleteGatewayConfiguration()", "chiamata deleteGatewayConfiguration", "deleteGatewayConfiguration");
	}

	public void deleteApplicationDetail(Credentials auth, String applicationId, String gatewayId, String paymodeid) throws RemoteException, DaoException, MissingParameterException, NamingException,
			AuthException {
		log.debug("[BoServicesBean::deleteApplicationDetail] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.deleteApplicationDetail(auth, applicationId, gatewayId, paymodeid, this.getClientIp());

		log.debug("[BoServicesBean::deleteApplicationDetail] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "deleteApplicationDetail()", "chiamata deleteApplicationDetail", "deleteApplicationDetail");
	}

	public void deleteApplication(Credentials auth, String applicationId) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[BoServicesBean::deleteApplication] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.deleteApplication(auth, applicationId, this.getClientIp());

		log.debug("[BoServicesBean::deleteApplication] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "deleteApplication()", "chiamata deleteApplication", "deleteApplication");

	}

	public void deleteApplicationCustomFields(Credentials auth, String applicationId, String gatewayid) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[BoServicesBean::deleteApplicationCustomFields] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.deleteApplicationCustomFields(auth, applicationId, gatewayid, this.getClientIp());

		log.debug("[BoServicesBean::deleteApplicationCustomFields] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "deleteApplicationCustomFields()", "chiamata deleteApplicationCustomFields", "deleteApplicationCustomFields");

	}

	public void deleteGatewayCustomFields(Credentials auth, String gatewayid) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[BoServicesBean::deleteGatewayCustomFields] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.deleteGatewayCustomFields(auth, gatewayid, this.getClientIp());

		log.debug("[BoServicesBean::deleteGatewayCustomFields] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "deleteGatewayCustomFields()", "chiamata deleteGatewayCustomFields", "deleteGatewayCustomFields");

	}

	public void deleteGatewayDetail(Credentials auth, String gatewayid, String gatewaypaymodeid) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[BoServicesBean::deleteGatewayDetail] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.deleteGatewayDetail(auth, gatewayid, gatewaypaymodeid, this.getClientIp());

		log.debug("[BoServicesBean::deleteGatewayDetail] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "deleteGatewayDetail()", "chiamata deleteGatewayDetail", "deleteGatewayDetail");

	}

	public void deleteGateway(Credentials auth, String gatewayid) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[BoServicesBean::deleteGateway] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.deleteGateway(auth, gatewayid, this.getClientIp());

		log.debug("[BoServicesBean::deleteGateway] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "deleteGateway()", "chiamata deleteGateway", "deleteGateway");
	}

	public void deleteMdpBckGroup(Credentials auth, int idgroup) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[BoServicesBean::deleteMdpBckGroup] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.deleteMdpBckGroup(auth, idgroup, this.getClientIp());

		log.debug("[BoServicesBean::deleteMdpBckGroup] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "deleteMdpBckGroup()", "chiamata deleteMdpBckGroup", "deleteMdpBckGroup");

	}

	public void deleteMdpBckUser(Credentials auth, int userid) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[BoServicesBean::deleteMdpBckUser] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.deleteMdpBckGroup(auth, userid, this.getClientIp());

		log.debug("[BoServicesBean::deleteMdpBckUser] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "deleteMdpBckUser()", "chiamata deleteMdpBckUser", "deleteMdpBckUser");

	}

	public MdpBckofficegroups getMdpBckGroupsById(Credentials auth, int groupid) throws RemoteException, DaoException, AuthException, MissingParameterException {

		log.debug("[BoServicesBean::getMdpBckGroupsById] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		MdpBckofficegroups group = bsbi.getMdpBckGroupsById(auth, groupid, this.getClientIp());

		log.debug("[BoServicesBean::getMdpBckGroupsById] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getMdpBckGroupsById()", "chiamata getMdpBckGroupsById", "getMdpBckGroupsById");
		return group;
	}

	////////////////////////////////////////////////////////////  NUOVI sviluppi per il nodo dei pagamenti
	
	/**
	 * 
	 * @param auth
	 * @param id
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public List<GiornaleEventoDTO> getGiornaleEventoByParam(Credentials auth, String iuv, Date dataOraEvento, String identificativodominio, String identificativofruitore, String codiceContesto)
			throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {

		log.debug("[BoServicesBean::getGiornaleEventoByParam] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<GiornaleEventoDTO> listGdeDto = bsbi.getGiornaleEventoByParam(auth, iuv, dataOraEvento, identificativodominio, identificativofruitore, codiceContesto,this.getClientIp());

		log.debug("[BoServicesBean::getGiornaleEventoByParam] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getGiornaleEventoByParam()", "chiamata getGiornaleEventoByParam", "getGiornaleEventoByParam");

		return listGdeDto;

	}

	/**
	 * 
	 * @param auth
	 * @param id
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public GiornaleEventoDTO getGiornaleEventoById(Credentials auth, Integer id) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {

		log.debug("[BoServicesBean::getGiornaleEventoById] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		GiornaleEventoDTO gdeDto = bsbi.getGiornaleEventoById(auth, id,this.getClientIp());

		log.debug("[BoServicesBean::getGiornaleEventoById] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getGiornaleEventoByParam()", "chiamata getGiornaleEventoByParam", "getGiornaleEventoByParam");
		return gdeDto;

	}

	/**
	 * 
	 * @param auth
	 * @param id
	 * @param applicationId
	 * @param transactionId
	 * @param lastUpdate
	 * @param iuv
	 * @param idStatiRpt
	 * @param idMsgRichiesta
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public List<RPTDTO> getRPTByParam(Credentials auth, Integer id, String applicationId, String transactionId, Date lastUpdateDa, Date lastUpdateA, Date insertDateDa, Date insertDateA, String iuv,
			String idStatiRpt, String idMsgRichiesta) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {

		log.debug("[BoServicesBean::getRPTByParam] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<RPTDTO> listDto = bsbi.getRPTByParam(auth, id, applicationId, transactionId, lastUpdateDa, lastUpdateA, insertDateDa, insertDateA, iuv, idStatiRpt, idMsgRichiesta,this.getClientIp());

		log.debug("[BoServicesBean::getRPTByParam] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getRPTByParam()", "chiamata getRPTByParam", "getRPTByParam");

		return listDto;

	}

	/**
	 * 
	 * @param auth
	 * @param id
	 * @param applicationId
	 * @param transactionId
	 * @param lastUpdate
	 * @param iuv
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public List<RTDTO> getRTByParam(Credentials auth, Integer id, String applicationId, String transactionId, Date lastUpdateDa, Date lastUpdateA, Date insertDateDa, Date insertDateA, String iuv,
			String idEsitoPagamento, String idMsgRichiesta) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {

		log.debug("[BoServicesBean::getRTByParam] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<RTDTO> listDto = bsbi.getRTByParam(auth, id, applicationId, transactionId, lastUpdateDa, lastUpdateA, insertDateDa, insertDateA, iuv, idEsitoPagamento, idMsgRichiesta,this.getClientIp());

		log.debug("[BoServicesBean::getGiornaleEventoByParam] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getRTByParam()", "chiamata getRTByParam", "getRTByParam");

		return listDto;

	}

	/**
	 * 
	 * @param auth
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public List<CodiciEsitoPagamentoDTO> getCodiciEsitoPagamentoAll(Credentials auth) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {

		log.debug("[BoServicesBean::getCodiciEsitoPagamentoAll] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<CodiciEsitoPagamentoDTO> listDto = bsbi.getCodiciEsitoPagamentoAll(auth,this.getClientIp());

		log.debug("[BoServicesBean::getCodiciEsitoPagamentoAll] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getCodiciEsitoPagamentoAll()", "chiamata getCodiciEsitoPagamentoAll", "getCodiciEsitoPagamentoAll");

		return listDto;

	}

	/**
	 * 
	 * @param auth
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public List<StatiRptDTO> getStatiRptAll(Credentials auth) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {

		log.debug("[BoServicesBean::getStatiRptAll] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<StatiRptDTO> listDto = bsbi.getStatiRptAll(auth,this.getClientIp());

		log.debug("[BoServicesBean::getStatiRptAll] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "getStatiRptAll()", "chiamata getStatiRptAll", "getStatiRptAll");

		return listDto;

	}

	public void insertEnte(Credentials auth, String enteId, String partitaIva, String descrizione, String attivo) throws RemoteException, DaoException, MissingParameterException, NamingException,
			AuthException {
		log.debug("[BoServicesBean::insertEnte] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.insertEnte(auth, enteId, partitaIva, descrizione, attivo,this.getClientIp());

		log.debug("[BoServicesBean::insertEnte] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "insertEnte()", "chiamata insertEnte", "insertEnte");
	}

	public void updateEnte(Credentials auth, String enteId, String partitaIva, String descrizione, String attivo) throws RemoteException, DaoException, MissingParameterException, NamingException,
			AuthException {
		log.debug("[BoServicesBean::updateEnte] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.updateEnte(auth, enteId, partitaIva, descrizione, attivo,this.getClientIp());

		log.debug("[BoServicesBean::updateEnte] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "updateEnte()", "chiamata updateEnte", "updateEnte");
	}

	public void deleteEnte(Credentials auth, String enteId) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[BoServicesBean::deleteEnte] BEGIN");
		StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
		stopWatch.start();

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.deleteEnte(auth, enteId,this.getClientIp());

		log.debug("[BoServicesBean::deleteEnte] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("BoServicesBean", "deleteEnte()", "chiamata deleteEnte", "deleteEnte");

	}

	public List<EntiDTO> findEntiAll(Credentials auth) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<EntiDTO> listaRis = bsbi.findEntiAll(auth,this.getClientIp());
		return listaRis;
	}

	public List<EntiDTO> getEntiByParam(Credentials auth, String enteId, String partitaIva, String descrizione, String attivo) throws RemoteException, DaoException, MissingParameterException,
			NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<EntiDTO> listaRis = bsbi.getEntiByParam(auth, enteId, partitaIva, descrizione, attivo,this.getClientIp());
		return listaRis;
	}

	public List<EntiDTO> getEntiByApplicationId(Credentials auth, String idApplicazione) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<EntiDTO> listaRis = bsbi.getEntiByApplicationId(auth, idApplicazione,this.getClientIp());
		return listaRis;
	}

	public Integer insRelEnteApplication(Credentials auth, String idApplicazione, String enteId) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		Integer ris = bsbi.insRelEnteApplication(auth, idApplicazione, enteId,this.getClientIp());
		return ris;
	}

	public Integer delRelEnteApplication(Credentials auth, String idApplicazione, String enteId) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		Integer ris = bsbi.delRelEnteApplication(auth, idApplicazione, enteId,this.getClientIp());
		return ris;
	}

	public List<InformativePSPDTO> getInformativePSPByParam(Credentials auth, Integer idinformativapsp, String identificativoFlusso, String identificativoPSP, String ragioneSociale,
			Date dataPubblicazione, Date dataInizioValidita, String urlInformazioniPSP, Integer stornoPagamento, String identificativoIntermediario, String identificativoCanale,
			String tipoVersamento, Integer modelloPagamento, Integer priorita, String disponibilitaServizio, String descrizioneServizio, String condizioniEconomicheMassime,
			String urlInformazioniCanale, Date datainserimento, String statoinserimento, Integer ordinamento, String origine) throws RemoteException, DaoException, MissingParameterException,
			NamingException, AuthException {

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<InformativePSPDTO> listaRis = bsbi.getInformativePSPByParam(auth, idinformativapsp, identificativoFlusso, identificativoPSP, ragioneSociale, dataPubblicazione, dataInizioValidita,
				urlInformazioniPSP, stornoPagamento, identificativoIntermediario, identificativoCanale, tipoVersamento, modelloPagamento, priorita, disponibilitaServizio, descrizioneServizio,
				condizioniEconomicheMassime, urlInformazioniCanale, datainserimento, statoinserimento, ordinamento, origine,this.getClientIp());
		return listaRis;

	}


	
	
	/**
	 * 
	 * @param auth
	 * @param id
	 * @param identificativopsp
	 * @param identificativoflusso
	 * @param versioneoggetto
	 * @param identificativounivocoregolamento
	 * @param identificativoistitutomittente
	 * @param identificativoistitutoricevente
	 * @param numerototalepagamenti
	 * @param importototalepagamenti
	 * @param dataoraflusso
	 * @param dataregolamentoDa
	 * @param dataregolamentoA
	 * @param datainserimento
	 * @param datamodifica
	 * @param xmlflusso
	 * @param denominazionemittente
	 * @param denominazionericevente
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public List<FlussoRiversamentoDTO> getFlussoRiversamentoByParam(Credentials auth, Integer id, String identificativopsp, String identificativoflusso, String versioneoggetto,
			String identificativounivocoregolamento, String identificativoistitutomittente, String identificativoistitutoricevente, Integer numerototalepagamenti, Double importototalepagamenti,
			Date dataoraflusso, Date dataregolamentoDa, Date dataregolamentoA, Date datainserimento, Date datamodifica, String xmlflusso, String denominazionemittente, String denominazionericevente)
			throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<FlussoRiversamentoDTO> listaRis = bsbi.getFlussoRiversamentoByParam(auth, id, identificativopsp, identificativoflusso, versioneoggetto, identificativounivocoregolamento,
				identificativoistitutomittente, identificativoistitutoricevente, numerototalepagamenti, importototalepagamenti, dataoraflusso, dataregolamentoDa, dataregolamentoA, datainserimento,
				datamodifica, xmlflusso, denominazionemittente, denominazionericevente,this.getClientIp());
		return listaRis;
	}

	/**
	 * 
	 * @param auth
	 * @param idFlusso
	 * @param iuv
	 * @param identificativounivocoriscossione
	 * @param singoloimportopagato
	 * @param codiceesitosingolopagamento
	 * @param dataesitosingolopagamento
	 * @param datainserimento
	 * @param datamodifica
	 * @param applicationId
	 * @param dataregolamentoDa
	 * @param dataregolamentoA
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public List<FlussoSingoloPagamentoDTO> getFlussoSingoloPagamentoByParam(Credentials auth, Integer id,Integer idFlusso, String iuv, String identificativounivocoriscossione, Double singoloimportopagato,
			String codiceesitosingolopagamento, Date dataesitosingolopagamento, Date datainserimento, Date datamodifica, String applicationId, Date dataregolamentoDa, Date dataregolamentoA)
			throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<FlussoSingoloPagamentoDTO> listaRis = bsbi.getFlussoSingoloPagamentoByParam(auth,id, idFlusso, iuv, identificativounivocoriscossione, singoloimportopagato, codiceesitosingolopagamento,
				dataesitosingolopagamento, datainserimento, datamodifica, applicationId, dataregolamentoDa, dataregolamentoA,this.getClientIp());
		return listaRis;
	}

	public List<TipoVersamentoDTO> getListaTipoversamento(Credentials auth) throws RemoteException, DaoException, MissingParameterException,
	NamingException, AuthException {

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		
		List<TipoVersamentoDTO> listaRis = bsbi.getListaTipoversamento(auth,this.getClientIp());
		return listaRis;
		
	}
	
	public List<StatisticaApplicazioneTransazioneDTO> getStatisticaApplicazioneTransazione(Credentials auth,String applicationId, Date dateDa,Date dateA)throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		List<StatisticaApplicazioneTransazioneDTO> listaRis = bsbi.getStatisticaApplicazioneTransazione(auth, applicationId,  dateDa, dateA,this.getClientIp());
		return listaRis;
	}
	
	public List<FlussoRiversamentoDTO> estraiFlussiDaServizio(Credentials auth)throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.info("[BoServicesBean::estraiFlussiDaServizio] START");
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		List<FlussoRiversamentoDTO> listaRis = bsbi.estraiFlussiDaServizio(auth,this.getClientIp());
		log.info("[BoServicesBean::estraiFlussiDaServizio] listaRis size " + listaRis.size());
		log.info("[BoServicesBean::estraiFlussiDaServizio] END");
		return listaRis;
	}
	
	
	public List<IbanEnteApplicationDTO> getIbanEnteApplicationByParam(
			Credentials auth,
			Integer id,
			String applicationId,
			String idEnte,
			String tipoversamento,
			String identificativopsp,
			String iban,
			Date dataInizioValidita,
			Date dataFineValidita,
			String attivo) throws RemoteException, DaoException, MissingParameterException,
			NamingException, AuthException{
				log.info("[BoServicesBean::getIbanEnteApplicationByParam] START");
				BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
				BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
				List<IbanEnteApplicationDTO> listaRis = bsbi.getIbanEnteApplicationByParam(auth,
						 id,
						 applicationId,
						 idEnte,
						 tipoversamento,
						 identificativopsp,
						 iban,
						 dataInizioValidita,
						 dataFineValidita,
						 attivo,
						this.getClientIp());
				log.info("[BoServicesBean::getIbanEnteApplicationByParam] listaRis size " + listaRis.size());
				log.info("[BoServicesBean::getIbanEnteApplicationByParam] END");
				return listaRis;
			}

	
	public void insertIbanEnteApplication(
			Credentials auth,
			String applicationId,
			String idEnte,
			String tipoversamento,
			String identificativopsp,
			String iban,
			Date dataInizioValidita,
			Date dataFineValidita,
			String attivo) throws RemoteException, DaoException, MissingParameterException,
			NamingException, AuthException{
				log.info("[BoServicesBean::insertIbanEnteApplication] START");
				BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
				BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
				bsbi.insertIbanEnteApplication(auth,
						 applicationId,
						 idEnte,
						 tipoversamento,
						 identificativopsp,
						 iban,
						 dataInizioValidita,
						 dataFineValidita,
						 attivo,
						 this.getClientIp());
				log.info("[BoServicesBean::insertIbanEnteApplication] END");
			}


	public void updateIbanEnteApplication(
			Credentials auth,
			Integer id,
			String applicationId,
			String idEnte,
			String tipoversamento,
			String identificativopsp,
			String iban,
			Date dataInizioValidita,
			Date dataFineValidita,
			String attivo) throws RemoteException, DaoException, MissingParameterException,
			NamingException, AuthException{
				log.info("[BoServicesBean::updateIbanEnteApplication] START");
				BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
				BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
				bsbi.updateIbanEnteApplication(auth,
						id,
						 applicationId,
						 idEnte,
						 tipoversamento,
						 identificativopsp,
						 iban,
						 dataInizioValidita,
						 dataFineValidita,
						 attivo,
						 this.getClientIp());
				log.info("[BoServicesBean::updateIbanEnteApplication] END");
			}
	
	
	
	
	
	
	

}
