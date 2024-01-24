/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.interfacecxf;

import it.csi.csi.wrapper.SystemException;
import it.csi.mdp.boservices.business.manager.bean.BoServicesBeanImpl;
import it.csi.mdp.boservices.dto.CodiciEsitoPagamentoDTO;
import it.csi.mdp.boservices.dto.EntiDTO;
import it.csi.mdp.boservices.dto.FlussoRiversamentoDTO;
import it.csi.mdp.boservices.dto.FlussoSingoloPagamentoDTO;
import it.csi.mdp.boservices.dto.GiornaleEventoDTO;
import it.csi.mdp.boservices.dto.IbanEnteApplicationDTO;
import it.csi.mdp.boservices.dto.InformativePSPDTO;
import it.csi.mdp.boservices.dto.ParametroNodoDTO;
import it.csi.mdp.boservices.dto.RPTDTO;
import it.csi.mdp.boservices.dto.RTDTO;
import it.csi.mdp.boservices.dto.StatiRptDTO;
import it.csi.mdp.boservices.dto.StatisticaApplicazioneTransazioneDTO;
import it.csi.mdp.boservices.dto.TipoVersamentoDTO;
import it.csi.mdp.boservices.exception.AuthException;
import it.csi.mdp.boservices.util.Credentials;
import it.csi.mdp.boservices.util.VtransazioneResult;
import it.csi.mdp.core.business.dto.Application;
import it.csi.mdp.core.business.dto.ApplicationConfiguration;
import it.csi.mdp.core.business.dto.ApplicationDetail;
import it.csi.mdp.core.business.dto.Applicationcustomfields;
import it.csi.mdp.core.business.dto.Auditactions;
import it.csi.mdp.core.business.dto.Commission;
import it.csi.mdp.core.business.dto.Config;
import it.csi.mdp.core.business.dto.CsiLogAudit;
import it.csi.mdp.core.business.dto.Enti;
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
import it.csi.mdp.core.util.Constants;
import it.csi.mdp.boservices.exception.MissingParameterException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

@WebService(name = "BoServices", endpointInterface = "it.csi.mdp.boservices.interfacecxf.IMdpBoServicesCxf", targetNamespace = "http://interfacecxf.boservices.mdp.csi.it/")
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
public class MdpBoServicesCxf implements IMdpBoServicesCxf {
	@Resource
	WebServiceContext wsContext;

	protected static Logger log = Logger.getLogger(Constants.APPLICATION_CODE);

	@WebMethod
	public List<Application> listApplicationByFlussoApplicazione(@WebParam(name = "auth")Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException{

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<Application> lapp = bsbi.listApplicationByFlussoApplicazione(auth);
		return lapp;
	}

	
	@WebMethod
	public List<MdpBckusers> getMdpUsers(@WebParam(name = "auth") Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException {

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<MdpBckusers> lapp = bsbi.getMdpUsers(auth, this.getClientIp());
		return lapp;
	}

	@WebMethod
	public void setApplication(@WebParam(name = "auth") Credentials auth, Application app, boolean overwrite) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.setApplication(auth, app, overwrite, this.getClientIp());
		return;
	}

	@WebMethod
	public List<Application> getApplication(@WebParam(name = "auth") Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<Application> l = bsbi.getApplication(auth, this.getClientIp());
		return l;
	}

	@WebMethod
	public Application getApplicationById(@WebParam(name = "auth") Credentials auth, String appId) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		Application l = bsbi.getApplicationById(auth, appId, this.getClientIp());
		return l;
	}

	@WebMethod
	public void setApplicationDetail(Credentials auth, ApplicationDetail app) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.setApplicationDetail(auth, app, this.getClientIp());
		return;
	}

	@WebMethod
	public List<ApplicationDetail> getApplicationDetailById(Credentials auth, String appid) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getApplicationDetail(auth, appid, this.getClientIp());

	}

	@WebMethod
	public List<ApplicationDetail> getApplicationDetail(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getApplicationDetail(auth, this.getClientIp());
	}

	@WebMethod
	public List<Applicationcustomfields> getApplicationCustomFields(Credentials auth, String appId) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getApplicationCustomFields(auth, appId, this.getClientIp());
	}

	@WebMethod
	public void setApplicationCustomFields(Credentials auth, Applicationcustomfields[] app) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		bsbi.setApplicationCustomFields(auth, app, this.getClientIp());
	}

	@WebMethod
	public Gateway setGateway(Credentials auth, Gateway app) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.setGateway(auth, app, this.getClientIp());
	}

	@WebMethod
	public Gateway getGatewayById(Credentials auth, String gwId) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getGateway(auth, gwId, this.getClientIp());
	}

	@WebMethod
	public List<Gateway> getGateway(Credentials auth) throws RemoteException, DaoException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getGateway(auth, this.getClientIp());
	}

	@WebMethod
	public Paymentmode setPaymentMode(Credentials auth, Paymentmode paymode) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.setPaymentMode(auth, paymode, this.getClientIp());
	}

	@WebMethod
	public List<Paymentmode> getPaymentMode(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getPaymentMode(auth, this.getClientIp());
	}

	@WebMethod
	public Paymentmode getPaymentModeById(Credentials auth, String payModeId) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getPaymentMode(auth, payModeId, this.getClientIp());
	}

	@WebMethod
	public Gatewaydetail setGatewayDetail(Credentials auth, Gatewaydetail app) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.setGatewayDetail(auth, app, this.getClientIp());
	}

	@WebMethod
	public Gatewaydetail getGatewayDetailByIds(Credentials auth, String gwId, String payId) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getGatewayDetail(auth, gwId, payId, this.getClientIp());
	}

	@WebMethod
	public List<Gatewaydetail> getGatewayDetailById(Credentials auth, String gwId) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getGatewayDetail(auth, gwId, this.getClientIp());
	}

	@WebMethod
	public List<Gatewaydetail> getGatewayDetail(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getGatewayDetail(auth, this.getClientIp());
	}

	@WebMethod
	public List<MdpCurrency> getMdpCurrency(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getMdpCurrency(auth, this.getClientIp());
	}

	@WebMethod
	public List<MdpBckroles> getMdpBckRoles(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getMdpBckRoles(auth, this.getClientIp());
	}

	@WebMethod
	public MdpBckroles setMdpRole(Credentials auth, MdpBckroles role) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.setMdpRole(auth, role, this.getClientIp());
	}

	@WebMethod
	public MdpBckusers setMdpUser(Credentials auth, MdpBckusers user, List<MdpBckusersgroup> usergroup) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.setMdpUser(auth, user, usergroup, this.getClientIp());
	}

	@WebMethod
	public MdpBckofficegroups setMdpGroup(Credentials auth, MdpBckofficegroups group, List<MdpBckofficegroupappmapping> groupapp, MdpBckrolesgroupmap grouprole) throws RemoteException, DaoException,
			AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.setMdpGroup(auth, group, groupapp, grouprole, this.getClientIp());
	}

	@WebMethod
	public MdpBckusers getMdpUsersById(Credentials auth, int userid) throws RemoteException, DaoException, AuthException, MissingParameterException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getMdpUsersById(auth, userid, this.getClientIp());
	}

	@WebMethod
	public MdpBckusers getMdpUsersByCfisc(Credentials auth, String cfisc) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getMdpUsersByCfisc(auth, cfisc, this.getClientIp());
	}

	@WebMethod
	public List<MdpBckofficegroups> getMdpBckGroups(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getMdpBckGroups(auth, this.getClientIp());
	}

	@WebMethod
	public List<MdpBckofficegroups> getMdpBckGroupsByCfisc(Credentials auth, String cfisc) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getMdpBckGroupsByCfisc(auth, cfisc, this.getClientIp());
	}

	@WebMethod
	public List<CsiLogAudit> getAuditing(Credentials auth, List<Application> apps, Date datestart, Date dateend, String transactionid, List<String> actions, List<String> users)
			throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getAuditing(auth, apps, datestart, dateend, transactionid, actions, users, this.getClientIp());

	}

	@WebMethod
	public List<Transazione> getTransazione(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getTransazione(auth, this.getClientIp());
	}

	@WebMethod
	public List<Transazione> getTransazioneByApp(Credentials auth, String appid) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getTransazioneByApp(auth, appid, this.getClientIp());
	}

	@WebMethod
	public List<Transazione> getTransazioneById(Credentials auth, String idTransazione) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getTransazioneById(auth, idTransazione, this.getClientIp());
	}

	@WebMethod
	public List<Vtransazione> getTransazioneViewById(Credentials auth, String idTransazione) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getTransazioneViewById(auth, idTransazione, this.getClientIp());
	}

	@WebMethod
	public Transazione setTransazione(Credentials auth, Transazione transazione) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.setTransazione(auth, transazione, this.getClientIp());
	}

	@WebMethod
	public List<StatoTransazione> getStatiTransazione(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getStatiTransazione(auth, this.getClientIp());
	}

	@WebMethod
	public List<Vtransazione> getTransazioneWithFilters(Credentials auth, String appId, long codstato, Date datastart, Date dataend) throws RemoteException, DaoException, AuthException,
			MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getTransazioneWithFilters(auth, appId, codstato, datastart, dataend, this.getClientIp());
	}

	@WebMethod
	public MdpCurrency getMdpCurrencyByKey(Credentials auth, String gatewayid, String mdpcurrencycode, String gtwcurrencycode) throws RemoteException, DaoException, AuthException,
			MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getMdpCurrencyByKey(auth, gatewayid, mdpcurrencycode, gtwcurrencycode, this.getClientIp());
	}

	@WebMethod
	public List<MdpCurrency> getMdpCurrencyByGatewayId(Credentials auth, String gatewayid) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getMdpCurrencyByGatewayId(auth, gatewayid, this.getClientIp());
	}

	@WebMethod
	public List<Language> getLanguagesByGatewayId(@WebParam(name = "auth") Credentials auth, @WebParam(name = "gatewayid") String gatewayid) throws RemoteException, DaoException, AuthException,
			MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getLanguagesByGatewayId(auth, gatewayid, this.getClientIp());
	}

	@WebMethod
	public List<Language> getLanguages(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getLanguages(auth, this.getClientIp());
	}

	@WebMethod
	public void setLanguage(Credentials auth, Language lang) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		bsbi.setLanguage(auth, lang, this.getClientIp());
	}

	@WebMethod
	public void setMdpCurrency(Credentials auth, MdpCurrency ccy) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		bsbi.setMdpCurrency(auth, ccy, this.getClientIp());
	}

	@WebMethod
	public ApplicationConfiguration getApplicationConfiguration(Credentials auth, String appId) throws DaoException, AuthException, RemoteException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getApplicationConfiguration(auth, appId, this.getClientIp());

	}

	@WebMethod
	public List<Applicationcustomfields> getApplicationCustomFieldsByGateway(Credentials auth, String appId, String gatewayId) throws RemoteException, DaoException, MissingParameterException,
			AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getApplicationCustomFieldsByGateway(auth, appId, gatewayId, this.getClientIp());

	}

	@WebMethod
	public List<Verrori> getErrorList(Credentials auth, String appId, String transactionId, Date datastart, Date dataend, String gatewayId) throws DaoException, AuthException, RemoteException,
			MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getErrorList(auth, appId, transactionId, datastart, dataend, gatewayId, this.getClientIp());
	}

	@WebMethod
	public List<Auditactions> getAuditActionsList(Credentials auth) throws DaoException, AuthException, RemoteException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getAuditActionsList(auth, this.getClientIp());
	}

	@WebMethod
	public VtransazioneResult getTransazioneViewPaged(Credentials auth, int pagina, int transazioniPerPagina) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getTransazioneViewPaged(auth, pagina, transazioniPerPagina, this.getClientIp());
	}

	@WebMethod
	public VtransazioneResult getTransazioneViewWithFiltersPaged(Credentials auth, String appId, long codstato, Date datastart, Date dataend, int pagina, int transazioniPerPagina)
			throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getTransazioneViewWithFiltersPaged(auth, appId, codstato, datastart, dataend, pagina, transazioniPerPagina, this.getClientIp());
	}

	@WebMethod
	public List<Config> getBoConfig(Credentials auth) throws DaoException, AuthException, RemoteException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getBoConfig(auth, this.getClientIp());
	}

	@WebMethod
	public void setBoConfig(Credentials auth, Config conf) throws DaoException, AuthException, RemoteException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		bsbi.setBoConfig(auth, conf, this.getClientIp());

	}

	@WebMethod
	public void deleteBoConfig(Credentials auth, Config conf) throws DaoException, AuthException, RemoteException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		bsbi.deleteBoConfig(auth, conf, this.getClientIp());
	}

	@WebMethod
	public List<Gatewaycustomfields> getGatewayCustomFields(Credentials auth, String gwId) throws RemoteException, DaoException, MissingParameterException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getGatewayCustomFields(auth, gwId, this.getClientIp());
	}

	@WebMethod
	public void setGatewayCustomFields(Credentials auth, Gatewaycustomfields[] app) throws RemoteException, DaoException, MissingParameterException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		bsbi.setGatewayCustomFields(auth, app, this.getClientIp());
	}

	@WebMethod
	public List<Commission> getTipoCommissione(Credentials auth) throws DaoException, AuthException, RemoteException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.getTipoCommissione(auth, this.getClientIp());
	}

	@WebMethod
	public boolean testResources() throws RemoteException, SystemException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		return bsbi.testResources();
	}

	@WebMethod
	public boolean isAlive() throws RemoteException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		return bsbi.isAlive();
	}

	/**
	 * File upload method: enables file transfer from client to server
	 * 
	 * @param myFile
	 * @param usr
	 * @param pwd
	 * @param repo
	 * @throws IOException
	 */

	@WebMethod
	public void uploadMethod(Credentials auth, String fileName, byte[] fileContent) throws IOException, DaoException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.uploadMethod(auth, fileName, fileContent, this.getClientIp());

	}

	@WebMethod
	public void uploadMethodForApplication(Credentials auth, String fileName, byte[] fileContent, String appId) throws IOException, DaoException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.uploadMethodForApplication(auth, fileName, fileContent, appId, this.getClientIp());

	}

	@WebMethod
	public String uploadMethodForApplicationGateway(Credentials auth, String fileName, byte[] fileContent, String appId, String gatewayId) throws IOException, AuthException, DaoException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		return bsbi.uploadMethodForApplicationGateway(auth, fileName, fileContent, appId, gatewayId, this.getClientIp());
	}

	@WebMethod
	public Transazione confirmPayment(Credentials auth, String transactionid) throws RemoteException, DaoException, MissingParameterException, NamingException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		return bsbi.confirmPayment(auth, transactionid, this.getClientIp());
	}

	@WebMethod
	public Transazione refundPayment(Credentials auth, String transactionid) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		return bsbi.refundPayment(auth, transactionid, this.getClientIp());
	}

	@WebMethod
	public void deleteApplicationConfiguration(Credentials auth, String applicationId) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.deleteApplicationConfiguration(auth, applicationId, this.getClientIp());
	}

	@WebMethod
	public void deleteGatewayConfiguration(Credentials auth, String gatewayId) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.deleteGatewayConfiguration(auth, gatewayId, this.getClientIp());
	}

	@WebMethod
	public void deleteApplicationDetail(Credentials auth, String applicationId, String gatewayId, String paymodeid) throws RemoteException, DaoException, MissingParameterException, NamingException,
			AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.deleteApplicationDetail(auth, applicationId, gatewayId, paymodeid, this.getClientIp());
	}

	@WebMethod
	public void deleteApplication(Credentials auth, String applicationId) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.deleteApplication(auth, applicationId, this.getClientIp());
	}

	@WebMethod
	public void deleteApplicationCustomFields(Credentials auth, String applicationId, String gatewayid) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.deleteApplicationCustomFields(auth, applicationId, gatewayid, this.getClientIp());
	}

	@WebMethod
	public void deleteGatewayCustomFields(Credentials auth, String gatewayid) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.deleteGatewayCustomFields(auth, gatewayid, this.getClientIp());
	}

	@WebMethod
	public void deleteGatewayDetail(Credentials auth, String gatewayid, String gatewaypaymodeid) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.deleteGatewayDetail(auth, gatewayid, gatewaypaymodeid, this.getClientIp());
	}

	@WebMethod
	public void deleteGateway(Credentials auth, String gatewayid) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.deleteGateway(auth, gatewayid, this.getClientIp());
	}

	@WebMethod
	public void deleteMdpBckGroup(Credentials auth, int idgroup) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.deleteMdpBckGroup(auth, idgroup, this.getClientIp());
	}

	@WebMethod
	public void deleteMdpBckUser(Credentials auth, int userid) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.deleteMdpBckUser(auth, userid, this.getClientIp());
	}

	public MdpBckofficegroups getMdpBckGroupsById(Credentials auth, int groupid) throws RemoteException, DaoException, AuthException, MissingParameterException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		return bsbi.getMdpBckGroupsById(auth, groupid, this.getClientIp());
	}

	private String getClientIp() {
		MessageContext mc = wsContext.getMessageContext();
		HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
		log.debug("isAlive: Client IP = " + req.getRemoteAddr());
		return req.getRemoteAddr();
	}

	
	
	
	
	
	
	
	
	@WebMethod
	public List<GiornaleEventoDTO> getGiornaleEventoByParam(Credentials auth, String iuv, Date dataOraEvento, String identificativodominio, String identificativofruitore, String codiceContesto)
			throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<GiornaleEventoDTO> lista = bsbi.getGiornaleEventoByParam(auth, iuv, dataOraEvento, identificativodominio, identificativofruitore, codiceContesto,this.getClientIp());

		return lista;
	}

	@WebMethod
	public GiornaleEventoDTO getGiornaleEventoById(Credentials auth, Integer id) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		GiornaleEventoDTO geDTO = bsbi.getGiornaleEventoById(auth, id,this.getClientIp());
		return geDTO;
	}

	@WebMethod
	public List<RPTDTO> getRPTByParam(Credentials auth, Integer id, String applicationId, String transactionId, Date lastUpdateDa, Date lastUpdateA, Date insertDateDa, Date insertDateA, String iuv,
			String idStatiRpt, String idMsgRichiesta) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<RPTDTO> listaRis = bsbi.getRPTByParam(auth, id, applicationId, transactionId, lastUpdateDa, lastUpdateA, insertDateDa, insertDateA, iuv, idStatiRpt, idMsgRichiesta,this.getClientIp());
		return listaRis;
	}

	@WebMethod
	public List<RTDTO> getRTByParam(Credentials auth, Integer id, String applicationId, String transactionId, Date lastUpdateDa, Date lastUpdateA, Date insertDateDa, Date insertDateA, String iuv,
			String idEsitoPagamento, String idMsgRichiesta) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<RTDTO> listaRis = bsbi.getRTByParam(auth, id, applicationId, transactionId, lastUpdateDa, lastUpdateA, insertDateDa, insertDateA, iuv, idEsitoPagamento, idMsgRichiesta,this.getClientIp());
		return listaRis;
	}

	@WebMethod
	public List<StatiRptDTO> getStatiRptAll(Credentials auth) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<StatiRptDTO> listaRis = bsbi.getStatiRptAll(auth,this.getClientIp());
		return listaRis;
	}

	@WebMethod
	public List<CodiciEsitoPagamentoDTO> getCodiciEsitoPagamentoAll(Credentials auth) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<CodiciEsitoPagamentoDTO> listaRis = bsbi.getCodiciEsitoPagamentoAll(auth,this.getClientIp());
		return listaRis;
	}

	@WebMethod
	public void insertEnte(Credentials auth, String enteId, String partitaIva, String descrizione, String attivo) throws RemoteException, DaoException, MissingParameterException, NamingException,
			AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		bsbi.insertEnte(auth, enteId, partitaIva, descrizione, attivo,this.getClientIp());

	}

	@WebMethod
	public void updateEnte(Credentials auth, String enteId, String partitaIva, String descrizione, String attivo) throws RemoteException, DaoException, MissingParameterException, NamingException,
			AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.updateEnte(auth, enteId, partitaIva, descrizione, attivo,this.getClientIp());
	}

	@WebMethod
	public void deleteEnte(Credentials auth, String enteId) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		bsbi.deleteEnte(auth, enteId,this.getClientIp());
	}

	@WebMethod
	public List<EntiDTO> findEntiAll(Credentials auth) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<EntiDTO> listaRis = bsbi.findEntiAll(auth,this.getClientIp());
		return listaRis;
	}

	@WebMethod
	public List<EntiDTO> getEntiByParam(Credentials auth, String enteId, String partitaIva, String descrizione, String attivo) throws RemoteException, DaoException, MissingParameterException,
			NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		List<EntiDTO> listaRis = bsbi.getEntiByParam(auth, enteId, partitaIva, descrizione, attivo,this.getClientIp());
		return listaRis;
	}

	@WebMethod
	public List<EntiDTO> getEntiByApplicationId(Credentials auth, String idApplicazione) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		List<EntiDTO> listaRis = bsbi.getEntiByApplicationId(auth, idApplicazione,this.getClientIp());
		return listaRis;
	}

	@WebMethod
	public Integer insRelEnteApplication(Credentials auth, String idApplicazione, String enteId) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		Integer ris = bsbi.insRelEnteApplication(auth, idApplicazione, enteId,this.getClientIp());
		return ris;
	}

	@WebMethod
	public Integer delRelEnteApplication(Credentials auth, String idApplicazione, String enteId) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		Integer ris = bsbi.delRelEnteApplication(auth, idApplicazione, enteId,this.getClientIp());
		return ris;
	}

	@WebMethod
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

	@WebMethod
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

	@WebMethod
	public List<FlussoSingoloPagamentoDTO> getFlussoSingoloPagamentoByParam(Credentials auth,Integer id, Integer idFlusso, String iuv, String identificativounivocoriscossione, Double singoloimportopagato,
			String codiceesitosingolopagamento, Date dataesitosingolopagamento, Date datainserimento, Date datamodifica, String applicationId, Date dataregolamentoDa, Date dataregolamentoA)
			throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");

		List<FlussoSingoloPagamentoDTO> listaRis = bsbi.getFlussoSingoloPagamentoByParam(auth,id, idFlusso, iuv, identificativounivocoriscossione, singoloimportopagato, codiceesitosingolopagamento,
				dataesitosingolopagamento, datainserimento, datamodifica, applicationId, dataregolamentoDa, dataregolamentoA,this.getClientIp());
		return listaRis;
	}

	@WebMethod
	public List<TipoVersamentoDTO> getListaTipoversamento(Credentials auth) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		List<TipoVersamentoDTO> listaRis = bsbi.getListaTipoversamento(auth,this.getClientIp());
		return listaRis;
	}
	
	@WebMethod
	public List<StatisticaApplicazioneTransazioneDTO> getStatisticaApplicazioneTransazione(Credentials auth,String applicationId, Date dateDa,Date dateA)throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		List<StatisticaApplicazioneTransazioneDTO> listaRis = bsbi.getStatisticaApplicazioneTransazione(auth, applicationId,  dateDa, dateA,this.getClientIp());
		return listaRis;
	}
	
	@WebMethod
	public List<FlussoRiversamentoDTO> estraiFlussiDaServizio(Credentials auth) throws RemoteException, DaoException, MissingParameterException,
	NamingException, AuthException{
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl bsbi = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		List<FlussoRiversamentoDTO> listaRis = bsbi.estraiFlussiDaServizio(auth,this.getClientIp());
		return listaRis;
	}
	
	@WebMethod
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
				return listaRis;
			}

	@WebMethod
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
						 attivo,this.getClientIp());
			}

	@WebMethod
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
			}
	
}



