package it.csi.mdp.boservices.business.manager.bean;

import it.csi.mdp.adapters.business.BankAdapter;
import it.csi.mdp.adapters.business.BankAdapterHome;
import it.csi.mdp.boservices.business.manager.services.ConnectionFactory;
import it.csi.mdp.boservices.dto.IbanEnteApplicationDTO;
import it.csi.mdp.boservices.dto.CodiciEsitoPagamentoDTO;
import it.csi.mdp.boservices.dto.EntiDTO;
import it.csi.mdp.boservices.dto.FlussoRiversamentoDTO;
import it.csi.mdp.boservices.dto.FlussoSingoloPagamentoDTO;
import it.csi.mdp.boservices.dto.GiornaleEventoDTO;
import it.csi.mdp.boservices.dto.InformativePSPDTO;
import it.csi.mdp.boservices.dto.ParametroNodoDTO;
import it.csi.mdp.boservices.dto.RPTDTO;
import it.csi.mdp.boservices.dto.RTDTO;
import it.csi.mdp.boservices.dto.StatiRptDTO;
import it.csi.mdp.boservices.dto.StatisticaApplicazioneTransazioneDTO;
import it.csi.mdp.boservices.dto.TipoVersamentoDTO;
import it.csi.mdp.boservices.exception.AuthException;
import it.csi.mdp.boservices.exception.MissingParameterException;
import it.csi.mdp.boservices.util.Constants;
import it.csi.mdp.boservices.util.Credentials;
import it.csi.mdp.boservices.util.InvocaServizioFlussiRendicontazione;
import it.csi.mdp.boservices.util.StringUtils;
import it.csi.mdp.boservices.util.VtransazioneResult;
import it.csi.mdp.core.business.dao.ApplicationDao;
import it.csi.mdp.core.business.dao.ApplicationDetailDao;
import it.csi.mdp.core.business.dao.ApplicationcustomfieldsDao;
import it.csi.mdp.core.business.dao.AuditactionsDao;
import it.csi.mdp.core.business.dao.AuthorizationsDao;
import it.csi.mdp.core.business.dao.CodiciEsitoPagamentoDao;
import it.csi.mdp.core.business.dao.CommissionDao;
import it.csi.mdp.core.business.dao.ConfigDao;
import it.csi.mdp.core.business.dao.CsiLogAuditDao;
import it.csi.mdp.core.business.dao.EntiDao;
import it.csi.mdp.core.business.dao.FlussoRiversamentoDao;
import it.csi.mdp.core.business.dao.FlussoSingoloPagamentoDao;
import it.csi.mdp.core.business.dao.GatewayDao;
import it.csi.mdp.core.business.dao.GatewaycustomfieldsDao;
import it.csi.mdp.core.business.dao.GatewaydetailDao;
import it.csi.mdp.core.business.dao.GiornaleEventoDao;
import it.csi.mdp.core.business.dao.IbanEnteApplicationDao;
import it.csi.mdp.core.business.dao.InformativePSPDao;
import it.csi.mdp.core.business.dao.LanguageDao;
import it.csi.mdp.core.business.dao.MdpBckofficegroupappmappingDao;
import it.csi.mdp.core.business.dao.MdpBckofficegroupsDao;
import it.csi.mdp.core.business.dao.MdpBckrolesDao;
import it.csi.mdp.core.business.dao.MdpBckrolesgroupmapDao;
import it.csi.mdp.core.business.dao.MdpBckusersDao;
import it.csi.mdp.core.business.dao.MdpBckusersgroupDao;
import it.csi.mdp.core.business.dao.MdpCurrencyDao;
import it.csi.mdp.core.business.dao.PaymentmodeDao;
import it.csi.mdp.core.business.dao.RPTDao;
import it.csi.mdp.core.business.dao.RTDao;
import it.csi.mdp.core.business.dao.StatiRptDao;
import it.csi.mdp.core.business.dao.StatisticaApplicazioneTransazioneDao;
import it.csi.mdp.core.business.dao.StatoTransazioneDao;
import it.csi.mdp.core.business.dao.TransazioneDao;
import it.csi.mdp.core.business.dao.VerroriDao;
import it.csi.mdp.core.business.dao.VtransazioneDao;
import it.csi.mdp.core.business.dto.Application;
import it.csi.mdp.core.business.dto.ApplicationConfiguration;
import it.csi.mdp.core.business.dto.ApplicationDetail;
import it.csi.mdp.core.business.dto.ApplicationDetailPk;
import it.csi.mdp.core.business.dto.ApplicationGatewayConfiguration;
import it.csi.mdp.core.business.dto.ApplicationPk;
import it.csi.mdp.core.business.dto.Applicationcustomfields;
import it.csi.mdp.core.business.dto.Auditactions;
import it.csi.mdp.core.business.dto.Authorizations;
import it.csi.mdp.core.business.dto.CodiciEsitoPagamento;
import it.csi.mdp.core.business.dto.Commission;
import it.csi.mdp.core.business.dto.Config;
import it.csi.mdp.core.business.dto.ConfigPk;
import it.csi.mdp.core.business.dto.CsiLogAudit;
import it.csi.mdp.core.business.dto.Enti;
import it.csi.mdp.core.business.dto.FlussoRiversamento;
import it.csi.mdp.core.business.dto.FlussoSingoloPagamento;
import it.csi.mdp.core.business.dto.Gateway;
import it.csi.mdp.core.business.dto.GatewayPk;
import it.csi.mdp.core.business.dto.Gatewaycustomfields;
import it.csi.mdp.core.business.dto.Gatewaydetail;
import it.csi.mdp.core.business.dto.GatewaydetailPk;
import it.csi.mdp.core.business.dto.GiornaleEvento;
import it.csi.mdp.core.business.dto.IbanEnteApplication;
import it.csi.mdp.core.business.dto.InformativePSP;
import it.csi.mdp.core.business.dto.Language;
import it.csi.mdp.core.business.dto.MdpBckofficegroupappmapping;
import it.csi.mdp.core.business.dto.MdpBckofficegroupappmappingPk;
import it.csi.mdp.core.business.dto.MdpBckofficegroups;
import it.csi.mdp.core.business.dto.MdpBckofficegroupsPk;
import it.csi.mdp.core.business.dto.MdpBckroles;
import it.csi.mdp.core.business.dto.MdpBckrolesPk;
import it.csi.mdp.core.business.dto.MdpBckrolesgroupmap;
import it.csi.mdp.core.business.dto.MdpBckrolesgroupmapPk;
import it.csi.mdp.core.business.dto.MdpBckusers;
import it.csi.mdp.core.business.dto.MdpBckusersPk;
import it.csi.mdp.core.business.dto.MdpBckusersgroup;
import it.csi.mdp.core.business.dto.MdpBckusersgroupPk;
import it.csi.mdp.core.business.dto.MdpCurrency;
import it.csi.mdp.core.business.dto.MdpCurrencyPk;
import it.csi.mdp.core.business.dto.Paymentmode;
import it.csi.mdp.core.business.dto.PaymentmodePk;
import it.csi.mdp.core.business.dto.RPT;
import it.csi.mdp.core.business.dto.RT;
import it.csi.mdp.core.business.dto.StatiRpt;
import it.csi.mdp.core.business.dto.StatisticaApplicazioneTransazione;
import it.csi.mdp.core.business.dto.StatoTransazione;
import it.csi.mdp.core.business.dto.TipoVersamento;
import it.csi.mdp.core.business.dto.Transazione;
import it.csi.mdp.core.business.dto.TransazionePk;
import it.csi.mdp.core.business.dto.Verrori;
import it.csi.mdp.core.business.dto.Vtransazione;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.factory.DaoFactory;
import it.csi.mdp.core.business.util.MailServletAuthenticator;
import it.csi.mdp.generatedvo.flussoriversamento.CtFlussoRiversamento;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.ws.security.util.Base64;
import org.springframework.core.io.ClassPathResource;

public class BoServicesBeanImpl {
	private static final Logger log = Logger.getLogger(Constants.APPLICATION_CODE);
	private Properties envProps = new Properties();
	private StringWriter sw = new StringWriter();
	private StringBuffer sb = sw.getBuffer();
	private PrintWriter pw = new PrintWriter(sw);
	private String output = "";
	private int superadminRoleId = 25;
	private Credentials userAuth = null;

	public BoServicesBeanImpl() {
		try {
			// log.debug("[CorePaymentBean::CorePaymentBean] BEGIN");
			ClassPathResource cp = new ClassPathResource("/env.properties");
			envProps.load(cp.getInputStream());
			ConfigDao confdao = DaoFactory.createConfigDao();
			List<Config> lconf = confdao.findAll();
			for (int i = 0; i < lconf.size(); i++) {
				Config c = lconf.get(i);
				log.debug("[CorePaymentBean::CorePaymentBean] config:" + c.toString());
				envProps.put(c.getKey(), c.getValue());
			}
			superadminRoleId = Integer.parseInt(envProps.getProperty("superadminRoleId"));
		} catch (Exception e) {

			e.printStackTrace(pw);
			sb = sw.getBuffer();
			output = sb.toString();

			log.error("[BoServicesBeanImpl::BoServicesBeanImpl] ERROR:" + output);
			this.errorMail(null);
			// e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param auth
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public List<Application> listApplicationByFlussoApplicazione(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException{
		
		log.debug("[" + this.getClass().getName() + "::listApplicationByFlussoApplicazione] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}

		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		ApplicationDao appDao = DaoFactory.createApplicationDao();

		List<Application> appList = null;

		appList = appDao.listApplicationByFlussoApplicazione();

		log.debug("[" + this.getClass().getName() + "::listApplicationByFlussoApplicazione] END");
		return appList;
		
	}

	/**
	 * 
	 * @param auth
	 * @param app
	 * @param overwrite
	 * @param ipclient
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public void setApplication(Credentials auth, Application app, boolean overwrite, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::setApplication] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		ApplicationDao appDao = DaoFactory.createApplicationDao();
		if (app.getId() == null || app.getId().trim().equals("")) {
			throw new MissingParameterException("Id applicazione obbligatorio");
		}
		if (app.getApplicationname() == null || app.getApplicationname().trim().equals("")) {
			throw new MissingParameterException("Nome applicazione obbligatorio");
		}
		String idgroup = null;
		if (this.userAuth.getRole() != this.superadminRoleId) {
			idgroup = Integer.toString(this.userAuth.getGroup());
		}
		List<Application> appList = appDao.findWhereIdEquals(app.getId(), idgroup);
		if (appList != null && appList.size() > 0 && !overwrite) {
			throw new DaoException("Applicazione con id:" + app.getId() + " gia' presente in archivio.");
		}
		ApplicationPk apk = new ApplicationPk();
		apk.setId(app.getId());
		if (appList.size() > 0) {

			appDao.update(apk, app);
		} else {
			apk = appDao.insert(app);
		}

		this.setAudit(auth, Constants.INSERITOAPPLICATION, app.getId(), "", "APPLICATION", apk.toString(), ipclient);
		log.debug("[" + this.getClass().getName() + "::setApplication] END");
	}

	/**
	 * 
	 * @param auth
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public List<Application> getApplication(Credentials auth, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getApplication] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		ApplicationDao appDao = DaoFactory.createApplicationDao();

		List<Application> appList = null;
		log.debug("[" + this.getClass().getName() + "::getApplication] idRole:" + this.userAuth.getRole());
		log.debug("[" + this.getClass().getName() + "::getApplication] superadminRoleId:" + this.superadminRoleId);

		if (this.userAuth.getRole() == this.superadminRoleId) {
			appList = appDao.findAll(null);
		} else {
			appList = appDao.findAll(Integer.toString(this.userAuth.getGroup()));
		}

		log.debug("[" + this.getClass().getName() + "::getApplication] END");
		return appList;

	}

	/**
	 * 
	 * @param auth
	 * @param appId
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public Application getApplicationById(Credentials auth, String appId, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getApplication] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		ApplicationDao appDao = DaoFactory.createApplicationDao();

		List<Application> appList = null;
		if (this.userAuth.getRole() == this.superadminRoleId) {
			appList = appDao.findWhereIdEquals(appId, null);
		} else {
			appList = appDao.findWhereIdEquals(appId, Integer.toString(this.userAuth.getGroup()));
		}
		log.debug("[" + this.getClass().getName() + "::getApplication] END");
		return appList.get(0);

	}

	/**
	 * 
	 * @param auth
	 * @param app
	 * @param ipclient
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public void setApplicationDetail(Credentials auth, ApplicationDetail app, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::setApplicationDetail] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		ApplicationDetailDao appDao = DaoFactory.createApplicationDetailDao();
		String sKey = null;
		try {

			FileInputStream stream = new FileInputStream(envProps.getProperty("sKeyDb"));
			int len = stream.available();
			byte[] b = new byte[len];
			stream.read(b);
			b = Base64.decode(new String(b));
			sKey = new String(b);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());

		}
		appDao.setsKey(sKey);
		ApplicationDetailPk apk = new ApplicationDetailPk();
		apk.setApplicationid(app.getApplicationid());
		apk.setGatewayid(app.getGatewayid());
		apk.setPaymentmodeid(app.getPaymentmodeid());
		String idgroup = null;
		if (this.userAuth.getRole() != this.superadminRoleId) {
			idgroup = Integer.toString(this.userAuth.getGroup());
		}
		ApplicationDetail appList = appDao.findByPrimaryKey(apk, idgroup);
		if (app.getApplicationurlback() != null) {
			if (app.getApplicationurlback().trim().equals("")) {
				app.setApplicationurlback(null);
			}
		}

		if (app.getApplicationurlcancel() != null) {
			if (app.getApplicationurlcancel().trim().equals("")) {
				app.setApplicationurlcancel(null);
			}
		}

		if (app.getApplicationurlerror() != null) {
			if (app.getApplicationurlerror().trim().equals("")) {
				app.setApplicationurlerror(null);
			}
		}
		if (app.getApplicationurlresponseko() != null) {
			if (app.getApplicationurlresponseko().trim().equals("")) {
				app.setApplicationurlresponseko(null);
			}
		}
		if (app.getApplicationurlresponseok() != null) {
			if (app.getApplicationurlresponseok().trim().equals("")) {
				app.setApplicationurlresponseok(null);
			}
		}

		if (appList != null) {

			appDao.update(apk, app);
		} else {
			apk = appDao.insert(app);
		}
		this.setAudit(auth, Constants.INSERITOAPPLICATIONDETAIL, app.getApplicationid(), "", "APPLICATIONDETAIL", apk.toString(), ipclient);
		log.debug("[" + this.getClass().getName() + "::setApplicationDetail] END");
	}

	/**
	 * 
	 * @param auth
	 * @param appid
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public List<ApplicationDetail> getApplicationDetail(Credentials auth, String appid, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getApplicationDetail] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		ApplicationDetailDao appDao = DaoFactory.createApplicationDetailDao();
		String sKey = null;
		try {

			FileInputStream stream = new FileInputStream(envProps.getProperty("sKeyDb"));
			int len = stream.available();
			byte[] b = new byte[len];
			stream.read(b);
			b = Base64.decode(new String(b));
			sKey = new String(b);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());

		}
		appDao.setsKey(sKey);
		List<ApplicationDetail> appList = null;
		if (this.userAuth.getRole() == this.superadminRoleId) {
			appList = appDao.findWhereApplicationidEquals(appid, null);
		} else {
			appList = appDao.findWhereApplicationidEquals(appid, Integer.toString(this.userAuth.getGroup()));
		}

		log.debug("[" + this.getClass().getName() + "::getApplicationDetail] END");
		return appList;
	}

	public List<ApplicationDetail> getApplicationDetail(Credentials auth, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getApplicationDetail] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		ApplicationDetailDao appDao = DaoFactory.createApplicationDetailDao();
		String sKey = null;
		try {

			FileInputStream stream = new FileInputStream(envProps.getProperty("sKeyDb"));
			int len = stream.available();
			byte[] b = new byte[len];
			stream.read(b);
			b = Base64.decode(new String(b));
			sKey = new String(b);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());

		}
		appDao.setsKey(sKey);
		List<ApplicationDetail> appList = null;
		if (this.userAuth.getRole() == this.superadminRoleId) {
			appList = appDao.findAll(null);
		} else {
			appList = appDao.findAll(Integer.toString(this.userAuth.getGroup()));
		}
		log.debug("[" + this.getClass().getName() + "::getApplicationDetail] END");
		return appList;
	}

	/**
	 * 
	 * @param auth
	 * @param appId
	 * @param gatewayId
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws AuthException
	 */
	public List<Applicationcustomfields> getApplicationCustomFieldsByGateway(Credentials auth, String appId, String gatewayId, String ipclient) throws RemoteException, DaoException,
			MissingParameterException, AuthException {
		log.debug("[" + this.getClass().getName() + "::getApplicationCustomFieldsByGateway] BEGIN");
		String sKey = null;
		try {

			FileInputStream stream = new FileInputStream(envProps.getProperty("sKeyDb"));
			int len = stream.available();
			byte[] b = new byte[len];
			stream.read(b);
			b = Base64.decode(new String(b));
			sKey = new String(b);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());

		}

		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		ApplicationcustomfieldsDao appDao = DaoFactory.createApplicationcustomfieldsDao();
		appDao.setsKey(sKey);
		log.debug("[" + this.getClass().getName() + "::getApplicationCustomFieldsByGateway] skeydb=" + sKey);
		appDao.setsKey(sKey);

		List<Applicationcustomfields> appList = appDao.findWhereApplicationidAndGatewayIdEquals(appId, gatewayId);

		log.debug("[" + this.getClass().getName() + "::getApplicationCustomFieldsByGateway] END");
		return appList;
	}

	/**
	 * 
	 * @param auth
	 * @param appId
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws AuthException
	 */
	public List<Applicationcustomfields> getApplicationCustomFields(Credentials auth, String appId, String ipclient) throws RemoteException, DaoException, MissingParameterException, AuthException {
		log.debug("[" + this.getClass().getName() + "::getApplicationCustomFieldsByGateway] BEGIN");
		String sKey = null;
		try {

			FileInputStream stream = new FileInputStream(envProps.getProperty("sKeyDb"));
			int len = stream.available();
			byte[] b = new byte[len];
			stream.read(b);
			b = Base64.decode(new String(b));
			sKey = new String(b);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());

		}

		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		ApplicationcustomfieldsDao appDao = DaoFactory.createApplicationcustomfieldsDao();
		appDao.setsKey(sKey);
		log.debug("[" + this.getClass().getName() + "::getApplicationCustomFields] skeydb=" + sKey);
		appDao.setsKey(sKey);
		List<Applicationcustomfields> appList = appDao.findWhereApplicationidEquals(appId);

		log.debug("[" + this.getClass().getName() + "::getApplicationCustomFields] END");
		return appList;
	}

	/**
	 * 
	 * @param auth
	 * @param app
	 * @param ipclient
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws AuthException
	 */
	public void setApplicationCustomFields(Credentials auth, Applicationcustomfields[] app, String ipclient) throws RemoteException, DaoException, MissingParameterException, AuthException {
		log.debug("[" + this.getClass().getName() + "::setApplicationCustomFields] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		ApplicationcustomfieldsDao appDao = DaoFactory.createApplicationcustomfieldsDao();
		String sKey = null;
		try {

			FileInputStream stream = new FileInputStream(envProps.getProperty("sKeyDb"));
			int len = stream.available();
			byte[] b = new byte[len];
			stream.read(b);
			b = Base64.decode(new String(b));
			sKey = new String(b);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());

		}
		appDao.setsKey(sKey);
		for (int i = 0; i < app.length; i++) {
			List<Applicationcustomfields> appList = appDao.findWhereApplicationidAndGatewayIdAndFieldNameEquals(app[i].getApplicationid(), app[i].getGatewayId(), app[i].getFieldname());
			if (appList != null && appList.size() > 0) {
				log.debug("[" + this.getClass().getName() + "::setApplicationCustomFields] updated appid=" + app[i].getApplicationid() + " gatewayid=" + app[i].getGatewayId() + " fieldname="
						+ app[i].getFieldname());
				appDao.update(app[i]);
			} else {
				log.debug("[" + this.getClass().getName() + "::setApplicationCustomFields] added appid=" + app[i].getApplicationid() + " gatewayid=" + app[i].getGatewayId() + " fieldname="
						+ app[i].getFieldname());
				appDao.insert(app[i]);
			}
		}
		this.setAudit(auth, Constants.INSERITOAPPLICATIONCUSTOMFIELD, app[0].getApplicationid(), "", "APPLICATIONCUSTOMFIELDS", app[0].getApplicationid(), ipclient);
		log.debug("[" + this.getClass().getName() + "::setApplicationCustomFields] END");
		return;
	}

	/**
	 * 
	 * @param auth
	 * @param gwId
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws AuthException
	 */
	public List<Gatewaycustomfields> getGatewayCustomFields(Credentials auth, String gwId, String ipclient) throws RemoteException, DaoException, MissingParameterException, AuthException {
		log.debug("[" + this.getClass().getName() + "::getGatewayCustomFields] BEGIN");

		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		GatewaycustomfieldsDao appDao = DaoFactory.createGatewaycustomfieldsDao();

		List<Gatewaycustomfields> appList = appDao.findWhereGatewayidEquals(gwId);

		log.debug("[" + this.getClass().getName() + "::getGatewayCustomFields] END");
		return appList;
	}

	/**
	 * 
	 * @param auth
	 * @param app
	 * @param ipclient
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws AuthException
	 */
	public void setGatewayCustomFields(Credentials auth, Gatewaycustomfields[] app, String ipclient) throws RemoteException, DaoException, MissingParameterException, AuthException {
		log.debug("[" + this.getClass().getName() + "::setGatewayCustomFields] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		if (app == null || app.length == 0) {
			throw new MissingParameterException("Elenco gateway custom fields obbligatorio.");
		}

		GatewaycustomfieldsDao appDao = DaoFactory.createGatewaycustomfieldsDao();

		appDao.delete(app[0].getGatewayId());
		for (int i = 0; i < app.length; i++) {
			// List<Gatewaycustomfields> appList =
			// appDao.findWhereGatewayidFieldnameEquals(app[i].getGatewayId(),
			// app[i]
			// .getFieldname());
			// if (appList != null && appList.size() > 0)
			// {
			// log.debug("[" + this.getClass().getName() +
			// "::setGatewayCustomFields] updated gwid=" + app[i].getGatewayId()
			// + "fieldname=" + app[i].getFieldname());
			// appDao.update(app[i]);
			// } else
			// {
			log.debug("[" + this.getClass().getName() + "::setGatewayCustomFields] added gwid=" + app[i].getGatewayId() + "fieldname=" + app[i].getFieldname());
			appDao.insert(app[i]);
			// }
		}
		this.setAudit(auth, Constants.MODIFICATOGATEWAYCUSTOMFIELD, "", "", "GATEWAYCUSTOMFIELDS", app[0].getGatewayId(), ipclient);
		log.debug("[" + this.getClass().getName() + "::setGatewayCustomFields] END");
		return;
	}

	/**
	 * 
	 * @param auth
	 * @param app
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public Gateway setGateway(Credentials auth, Gateway app, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::setGateway] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		GatewayDao gd = DaoFactory.createGatewayDao();
		GatewayPk gpk = new GatewayPk();
		Gateway gtw = null;
		if (app.getGatewayId() != null && !app.getGatewayId().trim().equals("")) {
			gpk.setGatewayId(app.getGatewayId().toUpperCase());
			gtw = gd.findByPrimaryKey(gpk);
		}
		if (gtw == null) {
			app.setGatewayId(java.util.UUID.randomUUID().toString().toUpperCase());
			gpk = gd.insert(app);
		} else {
			gd.update(gpk, app);
		}
		this.setAudit(auth, Constants.INSERITOGATEWAY, "", "", "GATEWAY", gpk.toString(), ipclient);
		log.debug("[" + this.getClass().getName() + "::setGateway] END");
		return app;
	}

	/**
	 * 
	 * @param auth
	 * @param gwId
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws AuthException
	 */
	public Gateway getGateway(Credentials auth, String gwId, String ipclient) throws RemoteException, DaoException, MissingParameterException, AuthException {
		log.debug("[" + this.getClass().getName() + "::getGateway byid] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		GatewayDao gd = DaoFactory.createGatewayDao();
		Gateway gw = gd.findByPrimaryKey(gwId);

		log.debug("[" + this.getClass().getName() + "::getGateway byid] END");
		return gw;
	}

	/**
	 * 
	 * @param auth
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 */
	public List<Gateway> getGateway(Credentials auth, String ipclient) throws RemoteException, DaoException, AuthException {
		log.debug("[" + this.getClass().getName() + "::getGateway byid] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		GatewayDao gd = DaoFactory.createGatewayDao();
		List<Gateway> gw = gd.findAll();

		log.debug("[" + this.getClass().getName() + "::getGateway byid] END");
		return gw;
	}

	/**
	 * 
	 * @param auth
	 * @param paymode
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws AuthException
	 */
	public Paymentmode setPaymentMode(Credentials auth, Paymentmode paymode, String ipclient) throws RemoteException, DaoException, MissingParameterException, AuthException {
		log.debug("[" + this.getClass().getName() + "::setPaymentMode] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		PaymentmodeDao pd = DaoFactory.createPaymentmodeDao();
		PaymentmodePk ppk = new PaymentmodePk();
		Paymentmode pm = null;
		if (paymode.getPaymentmodeId() != null) {
			ppk.setPaymentmodeId(paymode.getPaymentmodeId().toUpperCase());
			pm = pd.findByPrimaryKey(ppk);
		}

		if (pm == null) {
			paymode.setPaymentmodeId(java.util.UUID.randomUUID().toString().toUpperCase());
			pd.insert(paymode);
		} else {
			pd.update(ppk, paymode);
		}
		this.setAudit(auth, Constants.INSERITOMODALITAPAGAMENTO, "", "", "PAYMENTMODE", ppk.toString(), ipclient);
		log.debug("[" + this.getClass().getName() + "::setPaymentMode] END");
		return paymode;
	}

	/**
	 * 
	 * @param auth
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws AuthException
	 */
	public List<Paymentmode> getPaymentMode(Credentials auth, String ipclient) throws RemoteException, DaoException, MissingParameterException, AuthException {
		log.debug("[" + this.getClass().getName() + "::getPaymentMode ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		PaymentmodeDao gd = DaoFactory.createPaymentmodeDao();
		List<Paymentmode> gw = gd.findAll();

		log.debug("[" + this.getClass().getName() + "::getPaymentMode ] END");
		return gw;

	}

	/**
	 * 
	 * @param auth
	 * @param payModeId
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws AuthException
	 */
	public Paymentmode getPaymentMode(Credentials auth, String payModeId, String ipclient) throws RemoteException, DaoException, MissingParameterException, AuthException {

		log.debug("[" + this.getClass().getName() + "::getPaymentMode ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		PaymentmodeDao pd = DaoFactory.createPaymentmodeDao();
		PaymentmodePk ppk = new PaymentmodePk();
		ppk.setPaymentmodeId(payModeId);
		Paymentmode pm = pd.findByPrimaryKey(ppk);

		log.debug("[" + this.getClass().getName() + "::getPaymentMode ] END");
		return pm;
	}

	/**
	 * 
	 * @param auth
	 * @param app
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws AuthException
	 */
	public Gatewaydetail setGatewayDetail(Credentials auth, Gatewaydetail app, String ipclient) throws RemoteException, DaoException, MissingParameterException, AuthException {
		log.debug("[" + this.getClass().getName() + "::setGatewayDetail] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		GatewaydetailDao pd = DaoFactory.createGatewaydetailDao();
		GatewaydetailPk ppk = new GatewaydetailPk();
		ppk.setPaymentmodeId(app.getPaymentmodeId().toUpperCase());
		ppk.setGatewayId(app.getGatewayId().toUpperCase());
		Gatewaydetail pm = pd.findByPrimaryKey(ppk);
		if (pm == null) {
			app.setPaymentmodeId(app.getPaymentmodeId().toUpperCase());
			app.setGatewayId(app.getGatewayId().toUpperCase());
			ppk = pd.insert(app);
		} else {
			pd.update(ppk, app);
		}
		this.setAudit(auth, Constants.INSERITOGATEWAYDETAIL, "", "", "GATEWAYDETAIL", ppk.toString(), ipclient);
		log.debug("[" + this.getClass().getName() + "::setGatewayDetail] END");
		return app;
	}
	/**
	 * 
	 * @param auth
	 * @param gwId
	 * @param payId
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws AuthException
	 */
	public Gatewaydetail getGatewayDetail(Credentials auth, String gwId, String payId, String ipclient) throws RemoteException, DaoException, MissingParameterException, AuthException {
		log.debug("[" + this.getClass().getName() + "::getGatewayDetail ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		GatewaydetailDao pd = DaoFactory.createGatewaydetailDao();
		GatewaydetailPk ppk = new GatewaydetailPk();
		ppk.setGatewayId(gwId);
		ppk.setPaymentmodeId(payId);
		Gatewaydetail pm = pd.findByPrimaryKey(ppk);

		log.debug("[" + this.getClass().getName() + "::getGatewayDetail ] END");
		return pm;
	}
	/**
	 * 
	 * @param auth
	 * @param gatewayid
	 * @param clientip
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public List<Language> getLanguagesByGatewayId(Credentials auth, String gatewayid, String clientip) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getLanguagesByGatewayId ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		LanguageDao pd = DaoFactory.createLanguageDao();

		List<Language> pm = pd.findWhereGatewayidEquals(gatewayid);

		log.debug("[" + this.getClass().getName() + "::getLanguagesByGatewayId ] END");
		return pm;
	}
	/**
	 * 
	 * @param auth
	 * @param clientip
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public List<Language> getLanguages(Credentials auth, String clientip) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getLanguages ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		LanguageDao pd = DaoFactory.createLanguageDao();

		List<Language> pm = pd.findAll();

		log.debug("[" + this.getClass().getName() + "::getLanguages ] END");
		return pm;
	}
	/**
	 * 
	 * @param auth
	 * @param lang
	 * @param clientip
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public void setLanguage(Credentials auth, Language lang, String clientip) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::setLanguage ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		LanguageDao pd = DaoFactory.createLanguageDao();

		pd.insert(lang);

		log.debug("[" + this.getClass().getName() + "::setLanguage ] END");
		return;
	}
	/**
	 * 
	 * @param auth
	 * @param gwId
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws AuthException
	 */
	public List<Gatewaydetail> getGatewayDetail(Credentials auth, String gwId, String ipclient) throws RemoteException, DaoException, MissingParameterException, AuthException {
		log.debug("[" + this.getClass().getName() + "::getGatewayDetail ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		GatewaydetailDao pd = DaoFactory.createGatewaydetailDao();

		List<Gatewaydetail> lgd = pd.findWhereGatewayIdEquals(gwId);
		log.debug("[" + this.getClass().getName() + "::getGatewayDetail ] END");
		return lgd;
	}
	/**
	 * 
	 * @param auth
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws AuthException
	 */
	public List<Gatewaydetail> getGatewayDetail(Credentials auth, String ipclient) throws RemoteException, DaoException, MissingParameterException, AuthException {
		log.debug("[" + this.getClass().getName() + "::getGatewayDetail ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		GatewaydetailDao pd = DaoFactory.createGatewaydetailDao();
		List<Gatewaydetail> gw = pd.findAll();

		log.debug("[" + this.getClass().getName() + "::getGatewayDetail ] END");
		return gw;
	}
	/**
	 * 
	 * @param auth
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public List<MdpBckusers> getMdpUsers(Credentials auth, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getMdpUsers ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		log.debug("[" + this.getClass().getName() + "::getMdpUsers ] controllo autorizzazione avvenuto");
		MdpBckusersDao pd = DaoFactory.createMdpBckusersDao();
		String sKey = null;
		try {

			FileInputStream stream = new FileInputStream(envProps.getProperty("sKeyDb"));
			int len = stream.available();
			byte[] b = new byte[len];
			stream.read(b);
			b = Base64.decode(new String(b));
			sKey = new String(b);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());

		}
		pd.setsKey(sKey);
		List<MdpBckusers> gw = pd.findAll();

		log.debug("[" + this.getClass().getName() + "::getMdpUsers ] END");
		return gw;
	}
	/**
	 * 
	 * @param auth
	 * @param userid
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public MdpBckusers getMdpUsersById(Credentials auth, int userid, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getMdpUsers ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		MdpBckusersDao pd = DaoFactory.createMdpBckusersDao();
		String sKey = null;
		try {

			FileInputStream stream = new FileInputStream(envProps.getProperty("sKeyDb"));
			int len = stream.available();
			byte[] b = new byte[len];
			stream.read(b);
			b = Base64.decode(new String(b));
			sKey = new String(b);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());

		}
		pd.setsKey(sKey);
		MdpBckusers gw = pd.findByPrimaryKey(userid);

		MdpBckusersgroupDao grpdao = DaoFactory.createMdpBckusersgroupDao();
		List<MdpBckusersgroup> lgrp = grpdao.findByMdpBckusers(userid);
		gw.setUsergrp(lgrp);

		log.debug("[" + this.getClass().getName() + "::getMdpUsers ] END");
		return gw;
	}
	/**
	 * 
	 * @param auth
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public List<MdpBckroles> getMdpBckRoles(Credentials auth, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getMdpBckRoles ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		MdpBckrolesDao pd = DaoFactory.createMdpBckrolesDao();
		List<MdpBckroles> gw = pd.findAll();

		log.debug("[" + this.getClass().getName() + "::getMdpBckRoles ] END");
		return gw;
	}
	/**
	 * 
	 * @param auth
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public List<MdpBckofficegroups> getMdpBckGroups(Credentials auth, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getMdpBckGroups ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		MdpBckofficegroupsDao groupsdao = DaoFactory.createMdpBckofficegroupsDao();
		List<MdpBckofficegroups> gw = groupsdao.findAll();
		MdpBckofficegroupappmappingDao gappdao = DaoFactory.createMdpBckofficegroupappmappingDao();
		ApplicationDao adao = DaoFactory.createApplicationDao();
		for (int i = 0; i < gw.size(); i++) {

			MdpBckofficegroups grp = gw.get(i);
			List<MdpBckofficegroupappmapping> gappmap = gappdao.findByMdpBckofficegroups(grp.getIdgroup());
			ArrayList<Application> lapp = new ArrayList<Application>();
			for (int j = 0; j < gappmap.size(); j++) {
				List<Application> app = adao.findWhereIdEquals(gappmap.get(j).getIdapp(), null);
				lapp.add(app.get(0));
			}
			grp.setAppgrp(lapp);
			gw.set(i, grp);
		}

		log.debug("[" + this.getClass().getName() + "::getMdpBckGroups ] END");
		return gw;
	}
	/**
	 * 
	 * @param auth
	 * @param groupid
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public MdpBckofficegroups getMdpBckGroupsById(Credentials auth, int groupid, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getMdpBckGroupsById ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		MdpBckofficegroupsDao groupsdao = DaoFactory.createMdpBckofficegroupsDao();
		List<MdpBckofficegroups> gw = groupsdao.findWhereIdgroupEquals(groupid);

		if (gw == null || gw.size() == 0) {
			throw new DaoException("Nessuno gruppo trovato per id:" + groupid);
		}
		MdpBckofficegroupappmappingDao gappdao = DaoFactory.createMdpBckofficegroupappmappingDao();
		ApplicationDao adao = DaoFactory.createApplicationDao();
		MdpBckrolesgroupmapDao grdao = DaoFactory.createMdpBckrolesgroupmapDao();
		MdpBckrolesDao rdao = DaoFactory.createMdpBckrolesDao();
		MdpBckusersDao udao = DaoFactory.createMdpBckusersDao();
		MdpBckusersgroupDao ugdao = DaoFactory.createMdpBckusersgroupDao();

		for (int i = 0; i < gw.size(); i++) {

			MdpBckofficegroups grp = gw.get(i);
			List<MdpBckofficegroupappmapping> gappmap = gappdao.findByMdpBckofficegroups(grp.getIdgroup());
			ArrayList<Application> lapp = new ArrayList<Application>();
			for (int j = 0; j < gappmap.size(); j++) {
				List<Application> app = adao.findWhereIdEquals(gappmap.get(j).getIdapp(), null);
				lapp.add(app.get(0));
			}
			grp.setAppgrp(lapp);

			ArrayList<MdpBckroles> roles = new ArrayList<MdpBckroles>();
			List<MdpBckrolesgroupmap> lrgmap = grdao.findByMdpBckofficegroups(groupid);
			if (lrgmap != null) {
				for (int j = 0; j < lrgmap.size(); j++) {
					MdpBckroles role = rdao.findByPrimaryKey(lrgmap.get(j).getIdrole());
					roles.add(role);
				}
			}
			grp.setRoles(roles);

			List<MdpBckusers> users = new ArrayList<MdpBckusers>();
			List<MdpBckusersgroup> lusersgrpmap = ugdao.findByMdpBckofficegroups(gw.get(i).getIdgroup());
			if (lusersgrpmap != null) {
				for (int j = 0; j < lusersgrpmap.size(); j++) {
					MdpBckusers user = udao.findByPrimaryKey(lusersgrpmap.get(j).getIduser());
					users.add(user);
				}
			}
			grp.setUsers(users);
			gw.set(i, grp);
		}

		log.debug("[" + this.getClass().getName() + "::getMdpBckGroupsById ] END");
		return gw.get(0);
	}
	/**
	 * 
	 * @param auth
	 * @param cfisc
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public List<MdpBckofficegroups> getMdpBckGroupsByCfisc(Credentials auth, String cfisc, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getMdpBckGroupsByCfisc ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		MdpBckusers user = this.getMdpUsersByCfisc(auth, cfisc, ipclient);

		MdpBckusersgroupDao groupsdao = DaoFactory.createMdpBckusersgroupDao();
		List<MdpBckusersgroup> usergroups = groupsdao.findWhereIduserEquals(user.getIduser());

		log.debug("[" + this.getClass().getName() + "::getMdpBckGroupsByCfisc usergroup:" + usergroups.get(0));

		MdpBckofficegroupsDao grpdao = DaoFactory.createMdpBckofficegroupsDao();

		List<MdpBckofficegroups> groups = new ArrayList<MdpBckofficegroups>();
		for (int i = 0; i < usergroups.size(); i++) {
			MdpBckofficegroups gruppo = grpdao.findWhereIdgroupEquals(usergroups.get(i).getIdgroup()).get(0);
			groups.add(gruppo);

		}

		MdpBckofficegroupappmappingDao gappdao = DaoFactory.createMdpBckofficegroupappmappingDao();
		ApplicationDao adao = DaoFactory.createApplicationDao();
		for (int i = 0; i < groups.size(); i++) {

			MdpBckofficegroups grp = groups.get(i);
			List<MdpBckofficegroupappmapping> gappmap = gappdao.findByMdpBckofficegroups(grp.getIdgroup());
			ArrayList<Application> lapp = new ArrayList<Application>();
			for (int j = 0; j < gappmap.size(); j++) {
				List<Application> app = adao.findWhereIdEquals(gappmap.get(j).getIdapp(), null);
				lapp.add(app.get(0));
			}
			grp.setAppgrp(lapp);
			groups.set(i, grp);
		}

		log.debug("[" + this.getClass().getName() + "::getMdpBckGroupsByCfisc ] END");
		return groups;
	}
	/**
	 * 
	 * @param auth
	 * @param role
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public MdpBckroles setMdpRole(Credentials auth, MdpBckroles role, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::setMdpRole ] BEGIN");
		if (!this.checkAuth(auth)) {
			throw new AuthException("user:" + auth.getUserAuth() + " not allowed for this operation");
		}
		MdpBckrolesDao pd = DaoFactory.createMdpBckrolesDao();
		MdpBckrolesPk gw = pd.insert(role);
		role.setIdrole(gw.getIdrole());
		this.setAudit(auth, Constants.INSERITORUOLO, "", "", "MDPBCKROLES", gw.toString(), ipclient);
		log.debug("[" + this.getClass().getName() + "::setMdpRole ] END");
		return role;
	}
	/**
	 * 
	 * @param auth
	 * @param user
	 * @param usergroup
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	@SuppressWarnings("unused")
	public MdpBckusers setMdpUser(Credentials auth, MdpBckusers user, List<MdpBckusersgroup> usergroup, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::setMdpUser ] BEGIN");
		log.debug("[" + this.getClass().getName() + "::setMdpUser ] user:" + user.toString());
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		if (user == null) {
			throw new MissingParameterException("Parametro user obbligatorio");
		}
		if (user.getCodfisc() == null || user.getCodfisc().trim().equals("")) {
			throw new MissingParameterException("Codice Fiscale user obbligatorio");
		}
		MdpBckusersDao pd = DaoFactory.createMdpBckusersDao();

		MdpBckusersPk upk = new MdpBckusersPk(user.getIduser());
		String sKey = null;
		try {

			FileInputStream stream = new FileInputStream(envProps.getProperty("sKeyDb"));
			int len = stream.available();
			byte[] b = new byte[len];
			stream.read(b);
			b = Base64.decode(new String(b));
			sKey = new String(b);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());

		}
		pd.setsKey(sKey);
		String azione = "";

		if (pd.findByPrimaryKey(upk) != null) {
			pd.update(upk, user);
			azione = Constants.MODIFICATOUTENTE;
		} else {
			List<MdpBckusers> l = pd.findWhereCodfiscEquals(user.getCodfisc());
			if (l != null && l.size() > 0) {
				throw new DaoException("Il codice fiscale dell'utente e' gia' presente in archivio.");
			}
			upk = pd.insert(user);
			azione = Constants.INSERITOUTENTE;
		}
		user.setIduser(upk.getIduser());
		if (usergroup != null && usergroup.size() > 0) {
			MdpBckusersgroupDao ugdao = DaoFactory.createMdpBckusersgroupDao();

			List<MdpBckusersgroup> lgrp = ugdao.findByMdpBckusers(user.getIduser());
			if (lgrp != null) {
				for (int j = 0; j < lgrp.size(); j++) {
					MdpBckusersgroupPk ugpk = new MdpBckusersgroupPk();
					ugpk.setIdgroup(lgrp.get(j).getIdgroup());
					ugpk.setIduser(lgrp.get(j).getIduser());
					ugdao.delete(ugpk);
				}
			}

			MdpBckusersgroup ug = usergroup.get(0);
			MdpBckusersgroupPk ugpk = new MdpBckusersgroupPk();
			ugpk.setIdgroup(ug.getIdgroup());
			ugpk.setIduser(user.getIduser());

			ug.setIduser(user.getIduser());
			// ugdao.delete(ugpk);
			ugdao.insert(ug);
			user = pd.findByPrimaryKey(user.getIduser());
		}
		this.setAudit(auth, azione, "", "", "MdpBckusers", upk.toString(), ipclient);
		log.debug("[" + this.getClass().getName() + "::setMdpUser ] END");
		return user;
	}
	/**
	 * 
	 * @param auth
	 * @param group
	 * @param groupapp
	 * @param grouprole
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public MdpBckofficegroups setMdpGroup(Credentials auth, MdpBckofficegroups group, List<MdpBckofficegroupappmapping> groupapp, MdpBckrolesgroupmap grouprole, String ipclient)
			throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::setMdpGroup ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		log.debug("[" + this.getClass().getName() + "::setMdpGroup ] group:" + group.toString());
		if (groupapp != null && groupapp.get(0) != null)
			log.debug("[" + this.getClass().getName() + "::setMdpGroup ] groupapp:" + groupapp.get(0).toString());
		if (grouprole != null)
			log.debug("[" + this.getClass().getName() + "::setMdpGroup ] grouprole:" + grouprole.toString());
		MdpBckofficegroupsDao pd = DaoFactory.createMdpBckofficegroupsDao();
		MdpBckofficegroupsPk gpk = new MdpBckofficegroupsPk();
		gpk.setIdgroup(group.getIdgroup());
		String azione = "";
		MdpBckofficegroups gr = pd.findByPrimaryKey(gpk);
		if (gr == null) {
			gpk = pd.insert(group);
			azione = Constants.INSERITOGRUPPO;
		} else {
			pd.update(gpk, group);
			azione = Constants.MODIFICATOGRUPPO;
		}

		group.setIdgroup(gpk.getIdgroup());

		if (grouprole != null) {
			MdpBckrolesgroupmapDao rgdao = DaoFactory.createMdpBckrolesgroupmapDao();

			List<MdpBckrolesgroupmap> lrolegrp = rgdao.findByMdpBckofficegroups(group.getIdgroup());
			if (lrolegrp != null && lrolegrp.size() > 0) {
				for (int j = 0; j < lrolegrp.size(); j++) {
					MdpBckrolesgroupmapPk rgpk = new MdpBckrolesgroupmapPk();
					rgpk.setIdgroup(lrolegrp.get(j).getIdgroup());
					rgpk.setIdrole(lrolegrp.get(j).getIdrole());
					rgdao.delete(rgpk);
				}
			}

			grouprole.setIdgroup(group.getIdgroup());
			MdpBckrolesgroupmapPk rgpk = new MdpBckrolesgroupmapPk();
			rgpk.setIdgroup(group.getIdgroup());
			rgpk.setIdrole(grouprole.getIdrole());
			if (rgdao.findByPrimaryKey(rgpk) != null) {
				rgdao.update(rgpk, grouprole);
			} else {
				rgdao.insert(grouprole);
			}
		}

		if (groupapp != null) {
			MdpBckofficegroupappmappingDao grpappdao = DaoFactory.createMdpBckofficegroupappmappingDao();
			List<MdpBckofficegroupappmapping> lgrpappmap = grpappdao.findByMdpBckofficegroups(group.getIdgroup());

			for (int i = 0; i < lgrpappmap.size(); i++) {
				MdpBckofficegroupappmappingPk gapppk = new MdpBckofficegroupappmappingPk();
				gapppk.setIdapp(lgrpappmap.get(i).getIdapp());
				gapppk.setIdgroup(lgrpappmap.get(i).getIdgroup());
				grpappdao.delete(gapppk);
			}

			for (int i = 0; i < groupapp.size(); i++) {
				groupapp.get(i).setIdgroup(group.getIdgroup());
				MdpBckofficegroupappmappingPk gapppk = new MdpBckofficegroupappmappingPk();
				gapppk.setIdapp(groupapp.get(i).getIdapp());
				gapppk.setIdgroup(groupapp.get(i).getIdgroup());

				grpappdao.insert(groupapp.get(i));
			}
		}
		this.setAudit(auth, azione, "", "", "MdpBckofficegroupappmapping", gpk.toString(), ipclient);
		log.debug("[" + this.getClass().getName() + "::setMdpGroup ] END");
		return group;
	}
	/**
	 * 
	 * @param auth
	 * @param apps
	 * @param datestart
	 * @param dateend
	 * @param transactionid
	 * @param actions
	 * @param user
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public List<CsiLogAudit> getAuditing(Credentials auth, List<Application> apps, Date datestart, Date dateend, String transactionid, List<String> actions, List<String> user, String ipclient)
			throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getAuditing ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		CsiLogAuditDao adao = DaoFactory.createCsiLogAuditDao();

		List<CsiLogAudit> la = null;
		try {
			la = adao.findWithFilters(apps, datestart, dateend, transactionid, actions, user);
		} catch (Exception e) {

			log.debug("[" + this.getClass().getName() + "::getAuditing ] error:" + e.getMessage() + " cause:" + e.getCause().getMessage());
		}

		log.debug("[" + this.getClass().getName() + "::getAuditing ] END");
		return la;
	}


	private void sendMail(String receiver, String bodyText, String subject) throws Exception {
		log.debug("[" + this.getClass().getName() + "::sendMail] BEGIN");
		log.debug("[" + this.getClass().getName() + "::sendMail] receiver:" + receiver);
		log.debug("[" + this.getClass().getName() + "::sendMail] bodyText:" + bodyText);
		Session sessionMail = null;
		Properties props = new Properties();
		props.put(Constants.SERVER_SMTP, (String) envProps.get(Constants.SERVER_SMTP));
		props.put(Constants.SERVER_SMTP_PORT, (String) envProps.get(Constants.SERVER_SMTP_PORT));
		log.debug("[" + this.getClass().getName() + "::sendMail] mailserver:" + (String) envProps.get(Constants.SERVER_SMTP) + ":" + (String) envProps.get(Constants.SERVER_SMTP_PORT));
		MailServletAuthenticator authenticator = new MailServletAuthenticator((String) envProps.get("userSMTP"), (String) envProps.get("userPasswdSMTP"));
		if (((String) envProps.get(Constants.SERVER_SMTP_AUTH)).equalsIgnoreCase("true")) {
			sessionMail = Session.getInstance(props, authenticator);
		} else {
			sessionMail = Session.getInstance(props);
		}
		sessionMail = Session.getInstance(props, authenticator);
		MimeMessage message = new MimeMessage(sessionMail);

		message.setSubject(subject);
		message.setText(bodyText);
		if (envProps.get("MDPSender") == null) {
			throw new DaoException("Errore di sistema. Manca parametro MDPSender in file env.properties.");
		}
		InternetAddress from = new InternetAddress((String) envProps.get("MDPSender"));
		InternetAddress to = new InternetAddress(receiver);
		message.setSender(from);
		message.setFrom(from);
		message.setRecipient(Message.RecipientType.TO, to);

		Transport.send(message);
		log.debug("[" + this.getClass().getName() + "::sendMail] END");

	}

	private void errorMail(Transazione transaction) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StringBuffer bodyText = new StringBuffer("Errore di sistema\r\n");
		GregorianCalendar gc = new GregorianCalendar();
		bodyText.append("Data e ora:" + sdf.format(gc.getTime()) + "\r\n");
		if (transaction != null) {
			bodyText.append("Id Applicazione:" + transaction.getApplicationId() + "\r\n");
			bodyText.append("Id Transazione:" + transaction.getTransactionId() + "\r\n");
		}

		bodyText.append("Dettagli errore:\r\n");
		bodyText.append(this.output);
		try {
			this.sendMail(envProps.getProperty("MDPMailbox"), bodyText.toString(), envProps.getProperty("SubjectSystemError"));
		} catch (Exception e2) {

			e2.printStackTrace();
		}

	}
	/**
	 * 
	 * @param auth
	 * @param cfisc
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public MdpBckusers getMdpUsersByCfisc(Credentials auth, String cfisc, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getMdpUsers ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		MdpBckusersDao pd = DaoFactory.createMdpBckusersDao();
		String sKey = null;
		try {

			FileInputStream stream = new FileInputStream(envProps.getProperty("sKeyDb"));
			int len = stream.available();
			byte[] b = new byte[len];
			stream.read(b);
			b = Base64.decode(new String(b));
			sKey = new String(b);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());

		}
		pd.setsKey(sKey);
		List<MdpBckusers> gw = pd.findWhereCodfiscEquals(cfisc);
		if (gw == null) {
			return null;
		}
		if (gw.size() == 0) {
			throw new DaoException("Nessun utente presente con codice fiscale: " + cfisc);
		}

		MdpBckusers user = gw.get(0);
		MdpBckusersgroupDao grpdao = DaoFactory.createMdpBckusersgroupDao();
		List<MdpBckusersgroup> lgrp = grpdao.findByMdpBckusers(user.getIduser());
		user.setUsergrp(lgrp);
		log.debug("[" + this.getClass().getName() + "::getMdpUsers ] END");
		return user;
	}
	/**
	 * 
	 * @param auth
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 * @throws AuthException
	 */
	public List<MdpCurrency> getMdpCurrency(Credentials auth, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException, AuthException {
		log.debug("[" + this.getClass().getName() + "::getMdpCurrency ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		MdpCurrencyDao ccydao = DaoFactory.createMdpCurrencyDao();

		log.debug("[" + this.getClass().getName() + "::getMdpCurrency ] END");
		return ccydao.findAll();

	}
	/**
	 * 
	 * @param auth
	 * @param gatewayid
	 * @param mdpcurrencycode
	 * @param gtwcurrencycode
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public MdpCurrency getMdpCurrencyByKey(Credentials auth, String gatewayid, String mdpcurrencycode, String gtwcurrencycode, String ipclient) throws RemoteException, DaoException, AuthException,
			MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getMdpCurrency ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		MdpCurrencyDao ccydao = DaoFactory.createMdpCurrencyDao();

		log.debug("[" + this.getClass().getName() + "::getMdpCurrency ] END");
		MdpCurrencyPk pk = new MdpCurrencyPk();
		pk.setGatewayid(gatewayid);
		pk.setGtwcurrencycode(gtwcurrencycode);
		pk.setMdpcurrencycode(mdpcurrencycode);
		return ccydao.findByPrimaryKey(pk);
	}
	/**
	 * 
	 * @param auth
	 * @param gatewayid
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public List<MdpCurrency> getMdpCurrencyByGatewayId(Credentials auth, String gatewayid, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getMdpCurrency ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		MdpCurrencyDao ccydao = DaoFactory.createMdpCurrencyDao();

		log.debug("[" + this.getClass().getName() + "::getMdpCurrency ] END");

		return ccydao.findWhereGatewayidEquals(gatewayid);
	}
	/**
	 * 
	 * @param auth
	 * @param ccy
	 * @param ipclient
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public void setMdpCurrency(Credentials auth, MdpCurrency ccy, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getMdpCurrency ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		MdpCurrencyDao ccydao = DaoFactory.createMdpCurrencyDao();
		ccydao.insert(ccy);
		log.debug("[" + this.getClass().getName() + "::getMdpCurrency ] END");

	}
	/**
	 * 
	 * @param auth
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public List<Transazione> getTransazione(Credentials auth, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getTransazione ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		TransazioneDao tdao = DaoFactory.createTransazioneDao();
		log.debug("[" + this.getClass().getName() + "::getTransazione ] END");
		List<Transazione> tall = null;
		if (this.userAuth.getRole() == this.superadminRoleId) {
			tall = tdao.findAll(null);
		} else {
			tall = tdao.findAll(Integer.toString(this.userAuth.getGroup()));
		}
		return tall;

	}
	/**
	 * 
	 * @param auth
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public List<Vtransazione> getTransazioneView(Credentials auth, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getTransazioneView ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		VtransazioneDao tdao = DaoFactory.createVtransazioneDao();
		log.debug("[" + this.getClass().getName() + "::getTransazioneView ] END");
		List<Vtransazione> tall = null;
		if (this.userAuth.getRole() == this.superadminRoleId) {
			tall = tdao.findAll(null);
		} else {
			tall = tdao.findAll(Integer.toString(this.userAuth.getGroup()));
		}
		return tall;

	}
	/**
	 * 
	 * @param auth
	 * @param pagina
	 * @param transazioniPerPagina
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public VtransazioneResult getTransazioneViewPaged(Credentials auth, int pagina, int transazioniPerPagina, String ipclient) throws RemoteException, DaoException, AuthException,
			MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getTransazioneView ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		VtransazioneDao tdao = DaoFactory.createVtransazioneDao();

		List<Vtransazione> tall = null;
		if (this.userAuth.getRole() == this.superadminRoleId) {
			tall = tdao.findAll(null);
		} else {
			tall = tdao.findAll(Integer.toString(this.userAuth.getGroup()));
		}
		List<Vtransazione> tselected = new ArrayList<Vtransazione>();
		int start = (pagina - 1) * transazioniPerPagina;
		int end = start + transazioniPerPagina;
		int maxTransazioni = (tall.size() < 1000 ? tall.size() : 1000);

		if (end > maxTransazioni) {
			end = maxTransazioni;
		}
		for (int i = start; i < end; i++) {
			tselected.add(tall.get(i));
		}
		VtransazioneResult vr = new VtransazioneResult();
		vr.setListTransazioni(tselected);
		vr.setTotTransactionsFound(tall.size());

		log.debug("[" + this.getClass().getName() + "::getTransazioneView ] END");
		return vr;

	}
	/**
	 * 
	 * @param auth
	 * @param appid
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public List<Transazione> getTransazioneByApp(Credentials auth, String appid, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getTransazioneByApp ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		TransazioneDao tdao = DaoFactory.createTransazioneDao();
		log.debug("[" + this.getClass().getName() + "::getTransazioneByApp ] END");
		List<Transazione> tall = null;
		if (this.userAuth.getRole() == this.superadminRoleId) {
			tall = tdao.findWhereApplicationIdEquals(appid, null);
		} else {
			tall = tdao.findWhereApplicationIdEquals(appid, Integer.toString(this.userAuth.getGroup()));
		}
		return tall;
	}
	/**
	 * 
	 * @param auth
	 * @param appid
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public List<Vtransazione> getTransazioneViewByApp(Credentials auth, String appid, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getTransazioneViewByApp ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		VtransazioneDao tdao = DaoFactory.createVtransazioneDao();
		List<Vtransazione> tall = null;
		if (this.userAuth.getRole() == this.superadminRoleId) {
			tdao.findWhereApplicationIdEquals(appid, null);
		} else {
			tdao.findWhereApplicationIdEquals(appid, Integer.toString(this.userAuth.getGroup()));
		}

		log.debug("[" + this.getClass().getName() + "::getTransazioneViewByApp ] END");
		return tall;
	}
	/**
	 * 
	 * @param auth
	 * @param idTransazione
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public List<Transazione> getTransazioneById(Credentials auth, String idTransazione, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getTransazioneById ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		TransazioneDao tdao = DaoFactory.createTransazioneDao();
		log.debug("[" + this.getClass().getName() + "::getTransazioneById ] END");
		return tdao.findWhereTransactionIdEquals(idTransazione, Integer.toString(this.userAuth.getGroup()));
	}
	/**
	 * 
	 * @param auth
	 * @param idTransazione
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public List<Vtransazione> getTransazioneViewById(Credentials auth, String idTransazione, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getTransazioneViewById ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		VtransazioneDao tdao = DaoFactory.createVtransazioneDao();
		List<Vtransazione> tall = null;
		if (this.userAuth.getRole() == this.superadminRoleId) {
			tall = tdao.findWhereTransactionIdEquals(idTransazione, null);
		} else {
			tall = tdao.findWhereTransactionIdEquals(idTransazione, Integer.toString(this.userAuth.getGroup()));
		}

		log.debug("[" + this.getClass().getName() + "::getTransazioneViewById ] END");
		return tall;
	}
	/**
	 * 
	 * @param auth
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public List<StatoTransazione> getStatiTransazione(Credentials auth, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getStatiTransazione ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		StatoTransazioneDao tdao = DaoFactory.createStatoTransazioneDao();
		log.debug("[" + this.getClass().getName() + "::getStatiTransazione ] END");
		return tdao.findAll();
	}
	/**
	 * 
	 * @param auth
	 * @param appId
	 * @param codstato
	 * @param datastart
	 * @param dataend
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public List<Vtransazione> getTransazioneWithFilters(Credentials auth, String appId, long codstato, Date datastart, Date dataend, String ipclient) throws RemoteException, DaoException,
			AuthException, MissingParameterException {

		log.debug("[" + this.getClass().getName() + "::getTransazioneWithFilters ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		VtransazioneDao tdao = DaoFactory.createVtransazioneDao();
		List<Vtransazione> tall = null;
		if (this.userAuth.getRole() == this.superadminRoleId) {

			tall = tdao.findWithFilters(appId, codstato, datastart, dataend, null);
		} else {
			tall = tdao.findWithFilters(appId, codstato, datastart, dataend, Integer.toString(this.userAuth.getGroup()));
		}

		log.debug("[" + this.getClass().getName() + "::getTransazioneWithFilters ] END");
		return tall;

	}
	/**
	 * 
	 * @param auth
	 * @param appId
	 * @param codstato
	 * @param datastart
	 * @param dataend
	 * @param pagina
	 * @param transazioniPerPagina
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public VtransazioneResult getTransazioneViewWithFiltersPaged(Credentials auth, String appId, long codstato, Date datastart, Date dataend, int pagina, int transazioniPerPagina, String ipclient)
			throws RemoteException, DaoException, AuthException, MissingParameterException {

		log.debug("[" + this.getClass().getName() + "::getTransazioneViewWithFiltersPaged ] BEGIN");

		log.debug("[" + this.getClass().getName() + "::getTransazioneViewWithFiltersPaged ] Datastart:" + datastart);
		log.debug("[" + this.getClass().getName() + "::getTransazioneViewWithFiltersPaged ] Datastart:" + dataend);

		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		VtransazioneDao tdao = DaoFactory.createVtransazioneDao();
		List<Vtransazione> tall = null;
		if (this.userAuth.getRole() == this.superadminRoleId) {
			tall = tdao.findWithFilters(appId, codstato, datastart, dataend, null);
		} else {
			tall = tdao.findWithFilters(appId, codstato, datastart, dataend, Integer.toString(this.userAuth.getGroup()));
		}
		List<Vtransazione> tselected = new ArrayList<Vtransazione>();
		int start = (pagina - 1) * transazioniPerPagina;
		int end = start + transazioniPerPagina;
		int maxTransazioni = (tall.size() < 1000 ? tall.size() : 1000);

		if (end > maxTransazioni) {
			end = maxTransazioni;
		}
		for (int i = start; i < end; i++) {
			tselected.add(tall.get(i));
		}
		VtransazioneResult vr = new VtransazioneResult();
		vr.setListTransazioni(tselected);
		vr.setTotTransactionsFound(tall.size());
		int tot = tdao.findWithFiltersCount(appId, codstato, datastart, dataend, Integer.toString(this.userAuth.getGroup()));
		vr.setTotTransactions(tot);

		log.debug("[" + this.getClass().getName() + "::getTransazioneViewWithFiltersPaged ] END");
		return vr;

	}
	/**
	 * 
	 * @param auth
	 * @param transazione
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws AuthException
	 * @throws MissingParameterException
	 */
	public Transazione setTransazione(Credentials auth, Transazione transazione, String ipclient) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::setTransazione ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		TransazioneDao tdao = DaoFactory.createTransazioneDao();
		if (transazione == null) {
			throw new MissingParameterException("Oggetto transazione obbligatorio");
		}
		if (transazione.getTransactionId() == null || transazione.getTransactionId().trim().equals("")) {
			throw new MissingParameterException("Oggetto transazione obbligatorio");
		}

		TransazionePk tpk = new TransazionePk(transazione.getTransactionId());
		Transazione t = tdao.findByPrimaryKey(tpk, Integer.toString(this.userAuth.getGroup()));
		if (t == null) {
			throw new MissingParameterException("Transazione con id:" + tpk.getTransactionId() + " inesistente.");
		}
		t.setOldstate(transazione.getOldstate());
		t.setProvidertimestamp(transazione.getProvidertimestamp());
		t.setPgresultcode(transazione.getPgresultcode());
		t.setCodStato(transazione.getCodStato());
		t.setChangestatedate(new Date());
		t.setFinishDate(new Date());
		t.setPaymentid(transazione.getPaymentid());

		tdao.update(tpk, t);
		this.setAudit(auth, Constants.MODIFICATRANSAZIONE, t.getApplicationId(), t.getTransactionId(), "TRANSAZIONE", tpk.toString(), ipclient);
		log.debug("[" + this.getClass().getName() + "::setTransazione ] END");
		return transazione;
	}
	/**
	 * 
	 * @param auth
	 * @param appId
	 * @param ipclient
	 * @return
	 * @throws DaoException
	 * @throws AuthException
	 * @throws RemoteException
	 * @throws MissingParameterException
	 */
	public ApplicationConfiguration getApplicationConfiguration(Credentials auth, String appId, String ipclient) throws DaoException, AuthException, RemoteException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getApplicationConfiguration ] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		Application app = this.getApplicationById(auth, appId, ipclient);
		List<ApplicationDetail> lad = this.getApplicationDetail(auth, appId, ipclient);
		List<ApplicationGatewayConfiguration> applicationGatewayConfigurationsList = new ArrayList<ApplicationGatewayConfiguration>();

		for (int i = 0; i < lad.size(); i++) {

			List<Applicationcustomfields> lappcust = this.getApplicationCustomFieldsByGateway(auth, appId, lad.get(i).getGatewayid(), ipclient);

			ApplicationGatewayConfiguration agc = new ApplicationGatewayConfiguration(lad.get(i));
			Gateway gateway = this.getGateway(auth, lad.get(i).getGatewayid(), ipclient);
			Paymentmode paymode = this.getPaymentMode(auth, lad.get(i).getPaymentmodeid(), ipclient);

			agc.setAppcustfields(lappcust);
			agc.setGatewaydescription(gateway.getGatewayDescription());
			agc.setPaymodedescription(paymode.getPaymentmodeDescription());
			applicationGatewayConfigurationsList.add(agc);

		}
		ApplicationConfiguration ac = new ApplicationConfiguration(app);
		ac.setApplicationGatewayConfigurationsList(applicationGatewayConfigurationsList);

		log.debug("[" + this.getClass().getName() + "::getApplicationConfiguration ] END");
		return ac;
	}
	/**
	 * 
	 * @param auth
	 * @param appId
	 * @param transactionId
	 * @param datastart
	 * @param dataend
	 * @param gatewayId
	 * @param ipclient
	 * @return
	 * @throws DaoException
	 * @throws AuthException
	 * @throws RemoteException
	 * @throws MissingParameterException
	 */
	public List<Verrori> getErrorList(Credentials auth, String appId, String transactionId, Date datastart, Date dataend, String gatewayId, String ipclient) throws DaoException, AuthException,
			RemoteException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getErrorList ] START");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		VerroriDao errdao = DaoFactory.createVErroriDao();
		List<Verrori> errlist = errdao.findWithFilters(appId, transactionId, datastart, dataend, gatewayId, Integer.toString(auth.getGroup()));
		log.debug("[" + this.getClass().getName() + "::getErrorList ] END");

		return errlist;
	}
	/**
	 * 
	 * @param auth
	 * @param clientip
	 * @return
	 * @throws DaoException
	 * @throws AuthException
	 * @throws RemoteException
	 * @throws MissingParameterException
	 */
	public List<Config> getBoConfig(Credentials auth, String clientip) throws DaoException, AuthException, RemoteException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getBoConfig ] START");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		ConfigDao configdao = DaoFactory.createConfigDao();
		List<Config> lconf = configdao.findAll();
		log.debug("[" + this.getClass().getName() + "::getBoConfig ] END");

		return lconf;
	}
	/**
	 * 
	 * @param auth
	 * @param conf
	 * @param clientip
	 * @throws DaoException
	 * @throws AuthException
	 * @throws RemoteException
	 * @throws MissingParameterException
	 */
	public void setBoConfig(Credentials auth, Config conf, String clientip) throws DaoException, AuthException, RemoteException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getBoConfig ] START");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		ConfigDao configdao = DaoFactory.createConfigDao();
		Config vconf = configdao.findByPrimaryKey(conf.getKey());
		ConfigPk cpk = new ConfigPk();
		cpk.setKey(conf.getKey());
		if (vconf != null) {
			configdao.update(cpk, conf);
		} else {
			configdao.insert(conf);
		}
		this.setAudit(auth, Constants.MODIICATACONFIGURAZIONEPIATTAFORMA, "MDPNEW", null, "CONFIG", cpk.toString(), clientip);

		log.debug("[" + this.getClass().getName() + "::getBoConfig ] END");

		return;
	}
	/**
	 * 
	 * @param auth
	 * @param conf
	 * @param clientip
	 * @throws DaoException
	 * @throws AuthException
	 * @throws RemoteException
	 * @throws MissingParameterException
	 */
	public void deleteBoConfig(Credentials auth, Config conf, String clientip) throws DaoException, AuthException, RemoteException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getBoConfig ] START");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		ConfigDao configdao = DaoFactory.createConfigDao();
		ConfigPk cpk = new ConfigPk();
		cpk.setKey(conf.getKey());
		configdao.delete(cpk);
		this.setAudit(auth, Constants.MODIICATACONFIGURAZIONEPIATTAFORMA, "MDPNEW", null, "CONFIG", cpk.toString(), clientip);
		log.debug("[" + this.getClass().getName() + "::getBoConfig ] END");

		return;
	}
	/**
	 * 
	 * @param auth
	 * @param ipclient
	 * @return
	 * @throws DaoException
	 * @throws AuthException
	 * @throws RemoteException
	 * @throws MissingParameterException
	 */
	public List<Auditactions> getAuditActionsList(Credentials auth, String ipclient) throws DaoException, AuthException, RemoteException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getAuditActionsList ] START");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		AuditactionsDao actionsdao = DaoFactory.createAuditactionsDao();
		List<Auditactions> actionslist = actionsdao.findAll();
		log.debug("[" + this.getClass().getName() + "::getAuditActionsList ] END");

		return actionslist;
	}
	/**
	 * 
	 * @param auth
	 * @param ipclient
	 * @return
	 * @throws DaoException
	 * @throws AuthException
	 * @throws RemoteException
	 * @throws MissingParameterException
	 */
	public List<Commission> getTipoCommissione(Credentials auth, String ipclient) throws DaoException, AuthException, RemoteException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getTipoCommissione ] START");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		CommissionDao commdao = DaoFactory.createCommissionDao();
		List<Commission> commlist = commdao.findAll();
		log.debug("[" + this.getClass().getName() + "::getTipoCommissione ] END");

		return commlist;
	}
	/**
	 * 
	 * @param auth
	 * @param fileName
	 * @param fileContent
	 * @param clientip
	 * @throws IOException
	 * @throws AuthException
	 * @throws DaoException
	 */
	public void uploadMethod(Credentials auth, String fileName, byte[] fileContent, String clientip) throws IOException, AuthException, DaoException {

		log.debug("[" + this.getClass().getName() + "::uploadMethod ] START");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		// destination location
		String destinationPathUL = envProps.getProperty("destinationPathUL");

		// get uploaded file
		// DataHandler handler = myFile.getFileData();

		// String fileNameUL = myFile.getFileName() + "." +
		// myFile.getFileType();
		// String fileUL = destinationPathUL + fileNameUL;
		String fileUL = destinationPathUL + fileName;
		InputStream is = null;
		OutputStream os = null;
		try {
			// open the INPUT streaming channel to READ uploaded file
			// is = handler.getInputStream();

			// open the OUTPUT streaming channel to WRITE uploaded file to local
			// filesystem
			os = new FileOutputStream(new File(fileUL));

			// // READ uploaded file and WRITE it to local filesystem
			// byte[] buf = new byte[1024];
			// int bytesRead = 0;
			// while ((bytesRead = is.read(buf)) != -1)
			// {
			os.write(fileContent, 0, fileContent.length);
			// }
			os.flush();

		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (os != null)
				os.close();
			if (is != null)
				is.close();
		}
		this.setAudit(auth, Constants.UPLOADEDFILE, "MDPNEW", null, "filesystem", fileUL, clientip);
		log.debug("[" + this.getClass().getName() + "::uploadMethod ] END");

	}
	/**
	 * 
	 * @param auth
	 * @param fileName
	 * @param fileContent
	 * @param appId
	 * @param clientip
	 * @throws IOException
	 * @throws AuthException
	 * @throws DaoException
	 */
	public void uploadMethodForApplication(Credentials auth, String fileName, byte[] fileContent, String appId, String clientip) throws IOException, AuthException, DaoException {
		log.debug("[" + this.getClass().getName() + "::uploadMethodForApplication ] START");

		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		// destination location
		String destinationPathUL = envProps.getProperty("destinationPathUL");

		File f = new File(destinationPathUL + (destinationPathUL.endsWith(File.separator) ? "" : File.separator) + appId);
		if (!f.exists()) {
			f.mkdir();
		}
		// get uploaded file
		// DataHandler handler = myFile.getFileData();
		InputStream is = null;
		OutputStream os = null;
		// String fileNameUL = myFile.getFileName() + "." +
		// myFile.getFileType();
		String fileUL = f.getAbsolutePath() + File.separator + fileName;

		try {
			// open the INPUT streaming channel to READ uploaded file
			// is = handler.getInputStream();

			// open the OUTPUT streaming channel to WRITE uploaded file to local
			// filesystem
			os = new FileOutputStream(new File(fileUL));

			// READ uploaded file and WRITE it to local filesystem
			// byte[] buf = new byte[1024];
			// int bytesRead = 0;
			// while ((bytesRead = is.read(buf)) != -1)
			// {
			os.write(fileContent, 0, fileContent.length);
			// }
			os.flush();

			log.debug("[" + this.getClass().getName() + "::uploadMethodForApplication ] content:" + new String(fileContent));
			log.info("-----> [SERVER][ServiceImpl::uploadMethod] Upload del file [" + fileName + "]" + " completato: salvato nella cartella {" + destinationPathUL + "}");

		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (os != null)
				os.close();
			if (is != null)
				is.close();
		}
		this.setAudit(auth, Constants.UPLOADEDFILE, appId, null, "filesystem", fileUL, clientip);
		log.debug("[" + this.getClass().getName() + "::uploadMethodForApplication ] START");

	}
	/**
	 * 
	 * @param auth
	 * @param fileName
	 * @param fileContent
	 * @param appId
	 * @param gatewayId
	 * @param clientip
	 * @return
	 * @throws IOException
	 * @throws AuthException
	 * @throws DaoException
	 */
	public String uploadMethodForApplicationGateway(Credentials auth, String fileName, byte[] fileContent, String appId, String gatewayId, String clientip) throws IOException, AuthException,
			DaoException {

		// destination location

		log.debug("[" + this.getClass().getName() + "::uploadMethodForApplication ] START");
		gatewayId = gatewayId.replaceAll("\\{", "");
		gatewayId = gatewayId.replaceAll("\\}", "");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		// destination location
		String destinationPathUL = envProps.getProperty("destinationPathUL");

		File f = new File(destinationPathUL + (destinationPathUL.endsWith(File.separator) ? "" : File.separator) + appId + File.separator + gatewayId);
		if (!f.exists()) {
			File f1 = new File(destinationPathUL + (destinationPathUL.endsWith(File.separator) ? "" : File.separator) + appId);
			f1.mkdir();
			log.debug("[" + this.getClass().getName() + "::uploadMethodForApplication ] creato folder:" + f1.getAbsolutePath());
			File f2 = new File(destinationPathUL + (destinationPathUL.endsWith(File.separator) ? "" : File.separator) + appId + File.separator + gatewayId);
			f2.mkdir();
			log.debug("[" + this.getClass().getName() + "::uploadMethodForApplication ] creato folder:" + f2.getAbsolutePath());

		}
		// get uploaded file
		// DataHandler handler = myFile.getFileData();
		InputStream is = null;
		OutputStream os = null;
		// String fileNameUL = myFile.getFileName() + "." +
		// myFile.getFileType();
		String fileUL = f.getAbsolutePath() + File.separator + fileName;

		try {
			// open the INPUT streaming channel to READ uploaded file
			// is = handler.getInputStream();

			// open the OUTPUT streaming channel to WRITE uploaded file to local
			// filesystem
			os = new FileOutputStream(new File(fileUL));

			// READ uploaded file and WRITE it to local filesystem
			// byte[] buf = new byte[1024];
			// int bytesRead = 0;
			// while ((bytesRead = is.read(buf)) != -1)
			// {
			os.write(fileContent, 0, fileContent.length);
			// }
			os.flush();

			log.debug("[" + this.getClass().getName() + "::uploadMethodForApplication ] content:" + new String(fileContent));
			log.info("-----> [SERVER][ServiceImpl::uploadMethod] Upload del file [" + fileName + "]" + " completato: salvato nella cartella {" + destinationPathUL + "}");

		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (os != null)
				os.close();
			if (is != null)
				is.close();
		}
		this.setAudit(auth, Constants.UPLOADEDFILE, appId, null, "filesystem", fileUL, clientip);
		log.debug("[" + this.getClass().getName() + "::uploadMethodForApplication ] START");
		return f.getPath() + File.separator;
	}
	/**
	 * 
	 * @return
	 * @throws it.csi.csi.wrapper.SystemException
	 */
	public boolean testResources() throws it.csi.csi.wrapper.SystemException {
		log.info("[" + this.getClass().getName() + ": BEGIN");
		boolean stato = true;

		try {
			LanguageDao daotest = DaoFactory.createLanguageDao();
			@SuppressWarnings("unused")
			List<Language> ll = daotest.findAll();
		} catch (java.lang.Exception ex) {
			stato = false;
			ex.printStackTrace(pw);
			sb = sw.getBuffer();
			output = sb.toString();
			log.error("[" + this.getClass().getName() + "::testResources] error: " + output);
			this.errorMail(null);
			throw new it.csi.csi.wrapper.SystemException("IL DATABASE DEL SERVIZIO 'MDPBOSERVICESSRV' E' INATTIVO: ", ex);
		} finally {
			log.info("[" + this.getClass().getName() + ": END");
		}
		return stato;
	}
	/**
	 * 
	 * @param auth
	 * @param azione
	 * @param appid
	 * @param transactionid
	 * @param table
	 * @param keyOper
	 * @param clientip
	 * @throws DaoException
	 */
	private void setAudit(Credentials auth, String azione, String appid, String transactionid, String table, String keyOper, String clientip) throws DaoException {
		CsiLogAudit audit = new CsiLogAudit();
		audit.setDataOra(new Date());
		audit.setIdaction(azione);
		audit.setApplicationid(appid);
		audit.setUtente(auth.getUserAuth());
		audit.setTransactionid(transactionid);
		audit.setIdApp("MDPNEW_INT01_PROD_MDPBOSERVICES");
		audit.setCodappmodify("B");
		audit.setIpAddress(clientip);
		if (azione.startsWith("M")) {
			audit.setOperazione("update");
		} else if (azione.startsWith("D")) {
			audit.setOperazione("delete");
		} else if (azione.startsWith("C")) {
			audit.setOperazione("select");
		} else{
			audit.setOperazione("insert");
		}
		audit.setOggOper(table);
		audit.setKeyOper(keyOper);
		audit.setCodfisc(auth.getCodfisc());
		CsiLogAuditDao auditdao = DaoFactory.createCsiLogAuditDao();
		auditdao.insert(audit);
	}

	public boolean isAlive() {

		return true;
	}

	private boolean checkAuth(Credentials auth) throws DaoException {
		log.debug("calling method:" + Thread.currentThread().getStackTrace()[2].getMethodName());
		if (auth == null) {
			log.debug("checkAuth auth=null");
			return false;
		}
		MdpBckusersDao udao = DaoFactory.createMdpBckusersDao();
		int idGroup = -1;
		if (auth.getCodfisc() != null && !auth.getCodfisc().trim().equals("")) {

			String sKey = null;
			try {

				FileInputStream stream = new FileInputStream(envProps.getProperty("sKeyDb"));
				int len = stream.available();
				byte[] b = new byte[len];
				stream.read(b);
				b = Base64.decode(new String(b));
				sKey = new String(b);
			} catch (Exception e) {
				log.error("Exception FileInputStream" + e.getMessage());
				throw new DaoException(e.getMessage());

			}

			udao.setsKey(sKey);
			List<MdpBckusers> lu = udao.findWhereCodfiscEquals(auth.getCodfisc());
			if (lu == null || lu.size() == 0) {
				log.warn("checkAuth lu == null || lu.size() == 0  " + auth.getCodfisc());
				return false;
			}

			String pwd = lu.get(0).getPwdservizio();

			String pwdAuthTrimmed = StringUtils.trimToEmpty(auth.getPwdAuth());
			String pwdTrimmed = StringUtils.trimToEmpty(pwd);

			if (auth.getPwdAuth() != null && !pwdAuthTrimmed.equals(pwdTrimmed)) {
				log.warn("checkAuth pw non corrette ");
				log.warn("checkAuth pw non corrette*" + pwdAuthTrimmed + "*");
				log.warn("checkAuth pw non corrette*" + pwdTrimmed + "*");
				return false;
			}

			MdpBckusersgroupDao ugdao = DaoFactory.createMdpBckusersgroupDao();
			if (ugdao == null) {
				log.warn("checkauth: ugdao == null");
				return false;
			}
			List<MdpBckusersgroup> l = ugdao.findByMdpBckusers(lu.get(0).getIduser());

			if (l == null) {
				log.warn("checkauth: l == null " + lu.get(0).getIduser());
				return false;
			}
			if (l.size() == 0) {
				log.warn("checkauth len :" + l.size());
				return false;
			}
			idGroup = l.get(0).getIdgroup();
			auth.setUserAuth(lu.get(0).getIduser());

		}

		MdpBckrolesgroupmapDao rgdao = DaoFactory.createMdpBckrolesgroupmapDao();

		List<MdpBckrolesgroupmap> lrg = rgdao.findByMdpBckofficegroups(idGroup);
		if (lrg == null || lrg.size() == 0) {
			log.warn("checkauth len :lrg == null || lrg.size() == 0 ");
			return false;
		}

		AuthorizationsDao ad = DaoFactory.createAuthorizationsDao();
		Authorizations at = ad.findByPrimaryKey(lrg.get(0).getIdrole(), Thread.currentThread().getStackTrace()[2].getMethodName());
		log.debug("id Ruolo  " + lrg.get(0).getIdrole());
		log.debug("nome Metodo " + Thread.currentThread().getStackTrace()[2].getMethodName());
		log.debug("idGroup " + idGroup);
		log.debug("lrg.get(0).getIdrole() " + lrg.get(0).getIdrole());

		if (at == null) {
			log.warn("Authorizations  null controllare la tabella autorization ");
			return false;
		} else {
			log.info("Authorizations  avvenuta");
		}

		auth.setGroup(idGroup);
		auth.setRole(lrg.get(0).getIdrole());

		this.userAuth = auth;

		return true;
	}

	String getAppWsAuth(String appid) throws RemoteException, DaoException, AuthException, MissingParameterException {
		log.debug("[" + this.getClass().getName() + "::getAppWsAuth ] BEGIN");
		ApplicationcustomfieldsDao pd = DaoFactory.createApplicationcustomfieldsDao();
		List<Applicationcustomfields> gw = pd.findWhereApplicationidEquals(appid);
		String pwd = null;
		if (gw == null) {
			return null;
		}
		Statement st = null;
		Connection conn = null;
		try {
			conn = ConnectionFactory.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT applicationid, wspwd  FROM mdp_appwsauth WHERE applicationid = '" + appid + "'");
			if (!rs.next()) {
				throw new AuthException("Applicazione:" + appid + " non configurata all'uso dei servizi bo.");
			}
			pwd = rs.getString("wspwd");
		} catch (SQLException e) {

			throw new RemoteException(e.getMessage());
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {

					throw new RemoteException(e.getMessage());
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {

					throw new RemoteException(e.getMessage());
				}
			}
		}
		log.debug("[" + this.getClass().getName() + "::getAppWsAuth ] END");
		return pwd;
	}
	/**
	 * 
	 * @param auth
	 * @param transactionid
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 */
	@SuppressWarnings("unused")
	public Transazione confirmPayment(Credentials auth, String transactionid, String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException {
		log.debug("[" + this.getClass().getName() + "::confirmPayment] BEGIN");
		if (auth == null) {
			throw new MissingParameterException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			throw new MissingParameterException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}
		if (transactionid != null) {
			log.debug("[" + this.getClass().getName() + "::confirmPayment] id transazione:" + transactionid);
		} else {
			log.error("[" + this.getClass().getName() + "::confirmPayment] Parametro transazione obbligatorio");
			output = "[" + this.getClass().getName() + "::confirmPayment] Parametro transazione obbligatorio";

			throw new MissingParameterException("Parametro transazione obbligatorio");
		}

		if (transactionid == null) {
			log.error("[" + this.getClass().getName() + "::confirmPayment] Parametro transazione transactionId  obbligatorio");
			output = "[" + this.getClass().getName() + "::confirmPayment] Parametro transactionId obbligatorio";

			throw new MissingParameterException("Parametro transactionId obbligatorio");
		}
		Context initCtx = new InitialContext();
		Transazione transaction = null;
		Gateway gt = null;
		try {
			TransazioneDao daoTransaction = DaoFactory.createTransazioneDao();
			transaction = daoTransaction.findByPrimaryKey(transactionid, Integer.toString(this.userAuth.getGroup()));
			if (transaction == null) {
				throw new DaoException("Transazione con id:" + transactionid + " non trovata in archivio.");
			}
			if (transaction.getCodStato() != 8) {

				output = "Transazione con id:" + transaction.getTransactionId() + " in stato incoerente con la richiesta";

				throw new DaoException("Transazione con id:" + transaction.getTransactionId() + " in stato incoerente con la richiesta");
			}

			GatewayDao gtdao = DaoFactory.createGatewayDao();
			gt = gtdao.findByPrimaryKey(transaction.getGatewayId());
			if (gt == null) {
				log.error("[" + this.getClass().getName() + "::confirmPayment] gateway id:" + transaction.getGatewayId() + " non trovato");
				output = "[" + this.getClass().getName() + "::confirmPayment] gateway id:" + transaction.getGatewayId() + " non trovato";

				throw new DaoException("Parametro gateway id:" + transaction.getGatewayId() + " non trovato");

			}


			if (gt.getGatewayservicename() == null) {
				log.error("[" + this.getClass().getName() + "::confirmPayment] gateway service name per gateway id:" + transaction.getGatewayId() + " non trovato");
				output = ("[" + this.getClass().getName() + "::confirmPayment] gateway service name per gateway id:" + transaction.getGatewayId() + " non trovato");

				throw new DaoException("Parametro gateway id:" + transaction.getGatewayId() + " non trovato");

			}
			Object bankAdapterBean = initCtx.lookup(gt.getGatewayservicename());
			if (bankAdapterBean != null) {
				BankAdapterHome home = (BankAdapterHome) bankAdapterBean;
				BankAdapter bankAdapter = home.create();
				if (bankAdapter != null) {
					ApplicationcustomfieldsDao acfdao = DaoFactory.createApplicationcustomfieldsDao();
					List<Applicationcustomfields> lacf = acfdao.findWhereApplicationidEquals(transaction.getApplicationId());
					Applicationcustomfields[] acf = null;
					if (lacf != null && lacf.size() > 0) {

						acf = new Applicationcustomfields[lacf.size()];
						for (int i = 0; i < lacf.size(); i++) {
							acf[i] = lacf.get(i);
						}
					}
					GatewaydetailDao gddao = DaoFactory.createGatewaydetailDao();
					Gatewaydetail gd = gddao.findByPrimaryKey(transaction.getGatewayId(), transaction.getGatewaypaymodeid());
					transaction = bankAdapter.confirmPayment(transaction, acf, gd);
					Calendar objCal = Calendar.getInstance();
					transaction.setChangestatedate(new java.util.Date(objCal.getTimeInMillis()));
					transaction.setFinishDate(new java.util.Date(objCal.getTimeInMillis()));
					this.setTransazione(auth, transaction, ipclient);
				}
			}
		} catch (Exception e) {
			e.printStackTrace(pw);
			sb = sw.getBuffer();
			output = sb.toString();
			log.error("[" + this.getClass().getName() + "::confirmPayment] " + output);
			throw new DaoException(e.getMessage());
		}
		this.setAudit(auth, Constants.CONFIRMPAYMENT, "MDPNEW", transactionid, "TRANSAZIONE", transactionid, ipclient);
		log.debug("[" + this.getClass().getName() + "::confirmPayment] END");
		return transaction;
	}
	/**
	 * 
	 * @param auth
	 * @param transactionid
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public Transazione refundPayment(Credentials auth, String transactionid, String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[" + this.getClass().getName() + "::refundPayment] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		if (transactionid != null) {
			log.debug("[" + this.getClass().getName() + "::refundPayment] id transazione:" + transactionid);
		} else {
			log.error("[" + this.getClass().getName() + "::refundPayment] Parametro transazione obbligatorio");
			output = "[" + this.getClass().getName() + "::refundPayment] Parametro transazione obbligatorio";

			throw new MissingParameterException("Parametro transazione obbligatorio");
		}

		if (transactionid == null) {
			log.error("[" + this.getClass().getName() + "::refundPayment] Parametro transazione transactionId  obbligatorio");
			output = "[" + this.getClass().getName() + "::refundPayment] Parametro transactionId obbligatorio";

			throw new MissingParameterException("Parametro transactionId obbligatorio");
		}
		Context initCtx = new InitialContext();
		Transazione transaction = null;
		Gateway gt = null;

		String sKey = null;
		try {

			FileInputStream stream = new FileInputStream(envProps.getProperty("sKeyDb"));
			int len = stream.available();
			byte[] b = new byte[len];
			stream.read(b);
			b = Base64.decode(new String(b));
			sKey = new String(b);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());

		}
		try {
			TransazioneDao daoTransaction = DaoFactory.createTransazioneDao();
			transaction = daoTransaction.findByPrimaryKey(transactionid, Integer.toString(this.userAuth.getGroup()));
			log.debug("[" + this.getClass().getName() + "::refundPayment] Transazione:" + transaction);
			if (transaction == null) {
				throw new DaoException("Transazione codice:" + transactionid + " non trovata o utente non abilitato ad operare sulla medesima.");
			}
			if (transaction.getCodStato() != 4 && transaction.getCodStato() != 3) {

				output = "Transazione con id:" + transaction.getTransactionId() + " in stato incoerente con la richiesta";

				throw new DaoException("Transazione con id:" + transaction.getTransactionId() + " in stato incoerente con la richiesta");
			}

			GatewayDao gtdao = DaoFactory.createGatewayDao();

			gt = gtdao.findByPrimaryKey(transaction.getGatewayId());
			if (gt == null) {
				log.error("[" + this.getClass().getName() + "::refundPayment] transazione:" + transaction.getTransactionId() + "  gateway id:" + transaction.getGatewayId() + " non trovato");
				output = ("[" + this.getClass().getName() + "::refundPayment] transazione:" + transaction.getTransactionId() + "  gateway id:" + transaction.getGatewayId() + " non trovato");

				throw new DaoException("Transazione:" + transaction.getTransactionId() + "  gateway id:" + transaction.getGatewayId() + " non trovato");

			}

			/*
			 * lookup
			 */
			if (gt.getGatewayservicename() == null) {
				log.error("[" + this.getClass().getName() + "::refundPayment] Gateway service name per gateway id:" + transaction.getGatewayId() + " non trovato");
				output = ("[" + this.getClass().getName() + "::refundPayment] Gateway service name per gateway id:" + transaction.getGatewayId() + " non trovato");

				throw new DaoException("Transazione:" + transaction.getTransactionId() + transaction.getGatewayId() + " non trovato");

			}
			Object bankAdapterBean = initCtx.lookup(gt.getGatewayservicename());
			if (bankAdapterBean != null) {
				BankAdapterHome home = (BankAdapterHome) bankAdapterBean;
				BankAdapter bankAdapter = home.create();
				if (bankAdapter != null) {
					ApplicationcustomfieldsDao acfdao = DaoFactory.createApplicationcustomfieldsDao();
					acfdao.setsKey(sKey);
					List<Applicationcustomfields> lacf = acfdao.findWhereApplicationidEquals(transaction.getApplicationId());
					Applicationcustomfields[] acf = null;
					if (lacf != null && lacf.size() > 0) {

						acf = new Applicationcustomfields[lacf.size()];
						for (int i = 0; i < lacf.size(); i++) {
							acf[i] = lacf.get(i);
						}
					}
					GatewaydetailDao gddao = DaoFactory.createGatewaydetailDao();
					Gatewaydetail gd = gddao.findByPrimaryKey(transaction.getGatewayId(), transaction.getGatewaypaymodeid());

					transaction = bankAdapter.refundPayment(transaction, acf, gd);
					Calendar objCal = Calendar.getInstance();
					transaction.setChangestatedate(new java.util.Date(objCal.getTimeInMillis()));
					transaction.setFinishDate(new java.util.Date(objCal.getTimeInMillis()));
					this.setTransazione(auth, transaction, ipclient);
				}
			}
		} catch (Exception e) {
			log.error("[" + this.getClass().getName() + "::refundPayment] " + e.getMessage());
			throw new DaoException(e.getMessage());
		}
		this.setAudit(auth, Constants.REFUNDPAYMENT, "MDPNEW", transactionid, "TRANSAZIONE", transactionid, ipclient);
		log.debug("[" + this.getClass().getName() + "::refundPayment] END");
		return transaction;
	}
	/**
	 * 
	 * @param auth
	 * @param gatewayid
	 * @param ipclient
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public void deleteGateway(Credentials auth, String gatewayid, String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[" + this.getClass().getName() + "::deleteGateway] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		if (gatewayid != null) {
			log.debug("[" + this.getClass().getName() + "::deleteGateway] id gateway:" + gatewayid);
		} else {
			log.error("[" + this.getClass().getName() + "::deleteGateway] Parametro gatewayid obbligatorio");
			output = "[" + this.getClass().getName() + "::deleteGateway] Parametro gatewayid obbligatorio";

			throw new MissingParameterException("Parametro gatewayid obbligatorio");
		}

		TransazioneDao tdao = DaoFactory.createTransazioneDao();
		List<Transazione> lt = tdao.findWhereGatewayIdEquals(gatewayid, null);
		if (lt != null && lt.size() > 0) {
			throw new DaoException("REFINT-002: Esistono transazioni per il gateway:" + gatewayid);
		}

		GatewayDao gdao = DaoFactory.createGatewayDao();
		List<Gateway> lg = gdao.findWhereGatewayIdEquals(gatewayid);
		if (lg == null || lg.size() == 0) {
			throw new DaoException("Gateway inesistente:" + gatewayid);
		}

		GatewayPk gpk = new GatewayPk();
		gpk.setGatewayId(gatewayid);
		gdao.delete(gpk);
		this.setAudit(auth, Constants.ELIMINATOGATEWAY, "MDPNEW", null, "GATEWAY", gatewayid, ipclient);
		log.debug("[" + this.getClass().getName() + "::deleteGateway] END");

	}
	/**
	 * 
	 * @param auth
	 * @param gatewayid
	 * @param gatewaypaymodeid
	 * @param ipclient
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public void deleteGatewayDetail(Credentials auth, String gatewayid, String gatewaypaymodeid, String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException,
			AuthException {
		log.debug("[" + this.getClass().getName() + "::deleteGatewayDetail] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		if (gatewayid != null && gatewaypaymodeid != null) {
			log.debug("[" + this.getClass().getName() + "::deleteGatewayDetail] id gateway:" + gatewayid + " idpaymode:" + gatewaypaymodeid);
		} else {
			log.error("[" + this.getClass().getName() + "::deleteGatewayDetail] Parametro gatewayid e gatewaypaymodeid obbligatori");
			output = "[" + this.getClass().getName() + "::deleteGatewayDetail] Parametro gatewayid e gatewaypaymodeid obbligatori";

			throw new MissingParameterException("Parametro gatewayid e gatewaypaymodeid obbligatori");
		}

		TransazioneDao tdao = DaoFactory.createTransazioneDao();
		List<Transazione> lt = tdao.findWhereGatewayidAndPaymodeidEquals(gatewayid, gatewaypaymodeid, null);
		if (lt != null && lt.size() > 0) {
			throw new DaoException("REFINT-003: Esistono transazioni per il gateway/paymode:" + gatewayid + "/" + gatewaypaymodeid);
		}

		GatewaydetailDao gdao = DaoFactory.createGatewaydetailDao();
		GatewaydetailPk gpk = new GatewaydetailPk();
		gpk.setGatewayId(gatewayid);
		gpk.setPaymentmodeId(gatewaypaymodeid);
		gdao.delete(gpk);
		this.setAudit(auth, Constants.ELIMINATODETTAGLIOGATEWAY, "MDPNEW", null, "GATEWAYDETAIL", gpk.toString(), ipclient);
		log.debug("[" + this.getClass().getName() + "::deleteGatewayDetail] END");

	}
	/**
	 * 
	 * @param auth
	 * @param gatewayid
	 * @param ipclient
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public void deleteGatewayCustomFields(Credentials auth, String gatewayid, String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[" + this.getClass().getName() + "::deleteGatewayCustomFields] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		if (gatewayid != null) {
			log.debug("[" + this.getClass().getName() + "::deleteGatewayCustomFields] id gateway:" + gatewayid);
		} else {
			log.error("[" + this.getClass().getName() + "::deleteGatewayCustomFields] Parametro gatewayid  obbligatorio");
			output = "[" + this.getClass().getName() + "::deleteGatewayCustomFields] Parametro gatewayid obbligatorio";

			throw new MissingParameterException("Parametro gatewayid obbligatorio");
		}
		GatewayDao gdao = DaoFactory.createGatewayDao();
		List<Gateway> lg = gdao.findWhereGatewayIdEquals(gatewayid);
		if (lg == null || lg.size() == 0) {
			throw new DaoException("Gateway inesistente:" + gatewayid);
		}

		GatewaycustomfieldsDao gcfdao = DaoFactory.createGatewaycustomfieldsDao();

		gcfdao.delete(gatewayid);
		this.setAudit(auth, Constants.ELIMINATIGATEWAYCUSTOMFIELDS, "MDPNEW", null, "GATEWAYCUSTOMFIELDS", gatewayid, ipclient);
		log.debug("[" + this.getClass().getName() + "::deleteGatewayDetail] END");

	}
	/**
	 * 
	 * @param auth
	 * @param applicationId
	 * @param gatewayid
	 * @param ipclient
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public void deleteApplicationCustomFields(Credentials auth, String applicationId, String gatewayid, String ipclient) throws RemoteException, DaoException, MissingParameterException,
			NamingException, AuthException {
		log.debug("[" + this.getClass().getName() + "::deleteApplicationCustomFields] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		if (gatewayid != null && applicationId != null) {
			log.debug("[" + this.getClass().getName() + "::deleteApplicationCustomFields] id applicazione:" + applicationId + " id gateway:" + gatewayid);
		} else {
			log.error("[" + this.getClass().getName() + "::deleteApplicationCustomFields] Parametri gatewayid e applicationid obbligatori");
			output = "[" + this.getClass().getName() + "::deleteApplicationCustomFields] Parametri gatewayid e applicationid obbligator";

			throw new MissingParameterException("Parametri gatewayid e applicationid obbligator");
		}

		TransazioneDao tdao = DaoFactory.createTransazioneDao();
		List<Transazione> lt = tdao.findWhereApplicationIdEquals(applicationId, null);
		if (lt != null && lt.size() > 0) {
			throw new DaoException("REFINT-002: Esistono transazioni per l'applicazione:" + applicationId);
		}

		ApplicationDao adao = DaoFactory.createApplicationDao();
		List<Application> la = adao.findWhereIdEquals(applicationId, Integer.toString(this.userAuth.getGroup()));
		if (la == null || la.size() == 0) {
			throw new DaoException("Applicazione con id:" + applicationId + " inesistente o non di competenza.");
		}

		ApplicationcustomfieldsDao gdao = DaoFactory.createApplicationcustomfieldsDao();

		gdao.delete(applicationId, gatewayid);
		this.setAudit(auth, Constants.ELIMINATIAPPLICATIONCUSTOMFIELDS, "MDPNEW", null, "APPLICATIONCUSTOMFIELDS", applicationId + "," + gatewayid, ipclient);
		log.debug("[" + this.getClass().getName() + "::deleteApplicationCustomFields] END");

	}
	/**
	 * 
	 * @param auth
	 * @param applicationId
	 * @param ipclient
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public void deleteApplication(Credentials auth, String applicationId, String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[" + this.getClass().getName() + "::deleteApplication] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		if (applicationId != null) {
			log.debug("[" + this.getClass().getName() + "::deleteApplication] id applicationId:" + applicationId);
		} else {
			log.error("[" + this.getClass().getName() + "::deleteApplication] Parametro applicationId  obbligatorio");
			output = "[" + this.getClass().getName() + "::deleteApplication] Parametro applicationId obbligatorio";

			throw new MissingParameterException("Parametro applicationId obbligatorio");
		}
		ApplicationDao gdao = DaoFactory.createApplicationDao();
		List<Application> la = gdao.findWhereIdEquals(applicationId, Integer.toString(this.userAuth.getGroup()));
		if (la == null || la.size() == 0) {
			throw new DaoException("Applicazione con id:" + applicationId + " inesistente o non di competenza.");
		}

		TransazioneDao tdao = DaoFactory.createTransazioneDao();
		List<Transazione> lt = tdao.findWhereApplicationIdEquals(applicationId, null);
		if (lt != null && lt.size() > 0) {
			throw new DaoException("REFINT-002: Esistono transazioni per l'applicazione:" + applicationId);
		}
		MdpBckofficegroupappmappingDao appgrpdao = DaoFactory.createMdpBckofficegroupappmappingDao();

		List<MdpBckofficegroupappmapping> lappgrp = appgrpdao.findWhereIdappEquals(applicationId);
		if (lappgrp != null) {
			for (int i = 0; i < lappgrp.size(); i++) {
				MdpBckofficegroupappmappingPk appgrppk = new MdpBckofficegroupappmappingPk(applicationId, lappgrp.get(i).getIdgroup());
				appgrpdao.delete(appgrppk);
			}
		}

		ApplicationPk pk = new ApplicationPk(applicationId);
		gdao.delete(pk);
		this.setAudit(auth, Constants.ELIMINATAAPPLICAZIONE, "MDPNEW", null, "APPLICATION", pk.toString(), ipclient);
		log.debug("[" + this.getClass().getName() + "::deleteApplication] END");

	}
	/**
	 * 
	 * @param auth
	 * @param applicationId
	 * @param gatewayId
	 * @param paymodeid
	 * @param ipclient
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public void deleteApplicationDetail(Credentials auth, String applicationId, String gatewayId, String paymodeid, String ipclient) throws RemoteException, DaoException, MissingParameterException,
			NamingException, AuthException {
		log.debug("[" + this.getClass().getName() + "::deleteApplicationDetail] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		if (applicationId != null && gatewayId != null && paymodeid != null) {
			log.debug("[" + this.getClass().getName() + "::deleteApplicationDetail] id applicationId:" + applicationId + " gatwwayId:" + gatewayId + " paymodeid:" + paymodeid);
		} else {
			log.error("[" + this.getClass().getName() + "::deleteApplicationDetail] Parametro applicationId  obbligatorio");
			output = "[" + this.getClass().getName() + "::deleteApplicationDetail] Parametro applicationId obbligatorio";

			throw new MissingParameterException("Parametro applicationId obbligatorio");
		}

		ApplicationDao adao = DaoFactory.createApplicationDao();
		List<Application> la = adao.findWhereIdEquals(applicationId, Integer.toString(this.userAuth.getGroup()));
		if (la == null || la.size() == 0) {
			throw new DaoException("Applicazione con id:" + applicationId + " inesistente o non di competenza.");
		}

		TransazioneDao tdao = DaoFactory.createTransazioneDao();
		List<Transazione> lt = tdao.findWhereApplicationAndGatewayAndPaymodeEquals(applicationId, gatewayId, paymodeid, null);
		if (lt != null && lt.size() > 0) {
			throw new DaoException("REFINT-003: Esistono transazioni per l'applicazione:" + applicationId + ", gateway:" + gatewayId + ", paymode:" + paymodeid);
		}

		ApplicationDetailDao gdao = DaoFactory.createApplicationDetailDao();
		ApplicationDetailPk pk = new ApplicationDetailPk(applicationId, gatewayId, paymodeid);
		gdao.delete(pk);
		this.setAudit(auth, Constants.ELIMINATODETTAGLIOAPPLICAZIONE, "MDPNEW", null, "APPLICATIONDETAIL", pk.toString(), ipclient);
		log.debug("[" + this.getClass().getName() + "::deleteApplicationDetail] END");

	}
	/**
	 * 
	 * @param auth
	 * @param gatewayId
	 * @param ipclient
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public void deleteGatewayConfiguration(Credentials auth, String gatewayId, String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {

		log.debug("[" + this.getClass().getName() + "::deleteGatewayConfiguration] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		if (gatewayId != null) {
			log.debug("[" + this.getClass().getName() + "::deleteGatewayConfiguration] id gatewayId:" + gatewayId);
		} else {
			log.error("[" + this.getClass().getName() + "::deleteGatewayConfiguration] Parametro gatewayId  obbligatorio");
			output = "[" + this.getClass().getName() + "::deleteGatewayConfiguration] Parametro gatewayId obbligatorio";

			throw new MissingParameterException("Parametro gatewayId obbligatorio");
		}

		TransazioneDao tdao = DaoFactory.createTransazioneDao();
		List<Transazione> lt = tdao.findWhereGatewayIdEquals(gatewayId, null);
		if (lt != null && lt.size() > 0) {
			throw new DaoException("REFINT-002: Esistono transazioni per il gateway:" + gatewayId);
		}

		GatewayDao gdao = DaoFactory.createGatewayDao();
		List<Gateway> lg = gdao.findWhereGatewayIdEquals(gatewayId);
		if (lg == null || lg.size() == 0) {
			throw new DaoException("Gateway inesistente:" + gatewayId);
		}

		ApplicationDetailDao adao = DaoFactory.createApplicationDetailDao();
		List<ApplicationDetail> lad = adao.findWhereGatewayidEquals(gatewayId);
		if (lad != null && lad.size() > 0) {
			throw new DaoException("REFINT-005: il gateway " + lg.get(0) + " e' utilizzato dall'applicazione con id:" + lad.get(0).getApplicationid());
		}

		GatewayPk gpk = new GatewayPk(gatewayId);
		gdao.deleteGatewayConfiguration(gpk);

		this.setAudit(auth, Constants.ELIMINATAGATEWAYCCONFIGURATION, "MDPNEW", null, "GATEWAY", gpk.toString(), ipclient);
		log.debug("[" + this.getClass().getName() + "::deleteGatewayConfiguration] END");
	}
	/**
	 * 
	 * @param auth
	 * @param applicationId
	 * @param ipclient
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public void deleteApplicationConfiguration(Credentials auth, String applicationId, String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[" + this.getClass().getName() + "::deleteApplicationConfiguration] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		if (applicationId != null) {
			log.debug("[" + this.getClass().getName() + "::deleteApplicationConfiguration] id applicationId:" + applicationId);
		} else {
			log.error("[" + this.getClass().getName() + "::deleteApplicationConfiguration] Parametro applicationId  obbligatorio");
			output = "[" + this.getClass().getName() + "::deleteApplicationConfiguration] Parametro applicationId obbligatorio";

			throw new MissingParameterException("Parametro applicationId obbligatorio");
		}

		TransazioneDao tdao = DaoFactory.createTransazioneDao();
		List<Transazione> lt = tdao.findWhereApplicationIdEquals(applicationId, null);
		if (lt != null && lt.size() > 0) {
			throw new DaoException("REFINT-002: Esistono transazioni per l'applicazione:" + applicationId);
		}

		ApplicationDao adao = DaoFactory.createApplicationDao();
		List<Application> la = adao.findWhereIdEquals(applicationId, Integer.toString(this.userAuth.getGroup()));
		if (la == null || la.size() == 0) {
			throw new DaoException("Applicazione con id:" + applicationId + " inesistente o non di competenza.");
		}

		adao.deleteApplicationConfiguration(new ApplicationPk(applicationId));
		this.setAudit(auth, Constants.ELIMINATAAPPLICATIONCCONFIGURATION, "MDPNEW", null, "APPLICATION", applicationId, ipclient);
	}
	/**
	 * 
	 * @param auth
	 * @param userid
	 * @param ipclient
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public void deleteMdpBckUser(Credentials auth, int userid, String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[" + this.getClass().getName() + "::deleteMdpBckUser BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		if (userid > 0) {
			log.info("[" + this.getClass().getName() + "::deleteMdpBckUser userid:" + userid);
		} else {
			log.error("[" + this.getClass().getName() + "::deleteMdpBckUser] Parametro deleteMdpBckUser  obbligatorio");
			output = "[" + this.getClass().getName() + "::deleteMdpBckUser] Parametro deleteMdpBckUser obbligatorio";

			throw new MissingParameterException("Parametro userId obbligatorio");
		}

		MdpBckusersgroupDao ugdao = DaoFactory.createMdpBckusersgroupDao();
		List<MdpBckusersgroup> lug = ugdao.findByMdpBckusers(userid);
		if (lug != null) {
			for (int i = 0; i < lug.size(); i++) {
				MdpBckusersgroupPk mpk = new MdpBckusersgroupPk(userid, lug.get(i).getIdgroup());
				ugdao.delete(mpk);
			}

		}
		MdpBckusersDao adao = DaoFactory.createMdpBckusersDao();
		MdpBckusersPk upk = new MdpBckusersPk();
		upk.setIduser(userid);
		adao.delete(upk);
		this.setAudit(auth, Constants.ELIMINATOUTENTE, "MDPNEW", null, "mdp_bckusers", Integer.toString(userid), ipclient);
		log.debug("[" + this.getClass().getName() + "::deleteMdpBckUser] END");
	}
	/**
	 * 
	 * @param auth
	 * @param idgroup
	 * @param ipclient
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public void deleteMdpBckGroup(Credentials auth, int idgroup, String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[" + this.getClass().getName() + "::deleteMdpBckGroup] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		if (idgroup > 0) {
			log.debug("[" + this.getClass().getName() + "::deleteMdpBckGroup] idgroup:" + idgroup);
		} else {
			log.error("[" + this.getClass().getName() + "::deleteMdpBckGroup] Parametro idgroup  obbligatorio");
			output = "[" + this.getClass().getName() + "::deleteMdpBckGroup] Parametro idgroup obbligatorio";

			throw new MissingParameterException("Parametro idgroup obbligatorio");
		}

		MdpBckusersgroupDao ugdao = DaoFactory.createMdpBckusersgroupDao();
		List<MdpBckusersgroup> l = ugdao.findByMdpBckofficegroups(idgroup);
		if (l != null && l.size() > 0) {
			throw new DaoException("REFINT-001: Il gruppo con id:" + idgroup + " risulta associato ad utenti attivi.");
		}

		MdpBckofficegroupappmappingDao uadao = DaoFactory.createMdpBckofficegroupappmappingDao();
		List<MdpBckofficegroupappmapping> lua = uadao.findByMdpBckofficegroups(idgroup);
		if (lua != null) {
			for (int i = 0; i < lua.size(); i++) {
				MdpBckofficegroupappmappingPk muapk = new MdpBckofficegroupappmappingPk(lua.get(i).getIdapp(), lua.get(i).getIdgroup());
				uadao.delete(muapk);
			}
		}

		MdpBckrolesgroupmapDao rgdao = DaoFactory.createMdpBckrolesgroupmapDao();
		List<MdpBckrolesgroupmap> lrg = rgdao.findByMdpBckofficegroups(idgroup);
		if (lrg != null) {
			for (int i = 0; i < lrg.size(); i++) {
				MdpBckrolesgroupmapPk muapk = new MdpBckrolesgroupmapPk(lrg.get(i).getIdrole(), lrg.get(i).getIdgroup());
				rgdao.delete(muapk);
			}
		}

		MdpBckofficegroupsDao adao = DaoFactory.createMdpBckofficegroupsDao();
		MdpBckofficegroupsPk upk = new MdpBckofficegroupsPk();
		upk.setIdgroup(idgroup);
		adao.delete(upk);
		this.setAudit(auth, Constants.ELIMINATOGRUPPO, "MDPNEW", null, "mdp_bckofficegroups", Integer.toString(idgroup), ipclient);
		log.debug("[" + this.getClass().getName() + "::deleteMdpBckGroup] END");
	}

//////////////////////////////////
	
	/**
	 * 
	 * @param auth
	 * @param iuv
	 * @param dataOraEvento
	 * @param identificativodominio
	 * @param identificativofruitore
	 * @param codiceContesto
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public List<GiornaleEventoDTO> getGiornaleEventoByParam(Credentials auth, String iuv, Date dataOraEvento, String identificativodominio, String identificativofruitore, String codiceContesto,String ipclient)
			throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[" + this.getClass().getName() + "::getGiornaleEventoByParam ] BEGIN");

		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		GiornaleEventoDao dao = DaoFactory.createGiornaleEventoDao();
		List<GiornaleEvento> lista = dao.getGiornaleEventoByParam(iuv, dataOraEvento, identificativodominio, identificativofruitore, codiceContesto);
		List<GiornaleEventoDTO> listaDto = new ArrayList<GiornaleEventoDTO>();

		log.debug("[" + this.getClass().getName() + "::getGiornaleEventoByParam ] lista.size " + lista.size());
		for (GiornaleEvento ge : lista) {
			GiornaleEventoDTO geDto = new GiornaleEventoDTO();

			try {
				BeanUtils.copyProperties(geDto, ge);
			} catch (IllegalAccessException e) {
				log.error("[" + this.getClass().getName() + "::getGiornaleEventoByParam ] ", e);
			} catch (InvocationTargetException e) {
				log.error("[" + this.getClass().getName() + "::getGiornaleEventoByParam ] ", e);
			}
			// log.debug("[" + this.getClass().getName() +
			// "::getGiornaleEventoByParam ] "+new
			// Date(ge.getDataAggiornamento().getTime()));
			geDto.setLastUpdate(new Date(ge.getLastUpdate().getTime()));
			// log.debug("[" + this.getClass().getName() +
			// "::getGiornaleEventoByParam ] "+new
			// Date(ge.getDataInserimento().getTime()));
			geDto.setInsertDate(new Date(ge.getInsertDate().getTime()));
			// log.debug("[" + this.getClass().getName() +
			// "::getGiornaleEventoByParam ] "+new
			// Date(ge.getDataoraevento().getTime()));
			geDto.setDataoraevento(new Date(ge.getDataoraevento().getTime()));

			listaDto.add(geDto);
		}
		this.setAudit(auth, Constants.CONSULTAZIONE_GIORNALE_EVENTI, "", "", "GE", "", ipclient);
		log.debug("[" + this.getClass().getName() + "::getGiornaleEventoByParam ] END");
		return listaDto;
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
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public GiornaleEventoDTO getGiornaleEventoById(Credentials auth, Integer id,String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[" + this.getClass().getName() + "::getGiornaleEventoById ] BEGIN");

		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		GiornaleEventoDao dao = DaoFactory.createGiornaleEventoDao();
		GiornaleEvento ge = dao.getGiornaleEventoById(id);
		GiornaleEventoDTO geDto = new GiornaleEventoDTO();
		try {
			BeanUtils.copyProperties(geDto, ge);
		} catch (IllegalAccessException e) {
			log.error("[" + this.getClass().getName() + "::getGiornaleEventoById ] ", e);
		} catch (InvocationTargetException e) {
			log.error("[" + this.getClass().getName() + "::getGiornaleEventoById ] ", e);
		}
		geDto.setLastUpdate(new Date(ge.getLastUpdate().getTime()));
		geDto.setInsertDate(new Date(ge.getInsertDate().getTime()));
		geDto.setDataoraevento(new Date(ge.getDataoraevento().getTime()));

		this.setAudit(auth, Constants.CONSULTAZIONE_GIORNALE_EVENTI, "", "", "GE", "", ipclient);

		log.debug("[" + this.getClass().getName() + "::getGiornaleEventoById ] END");
		return geDto;
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
	public List<CodiciEsitoPagamentoDTO> getCodiciEsitoPagamentoAll(Credentials auth,String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[" + this.getClass().getName() + "::getCodiciEsitoPagamentoAll ] BEGIN");

		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		CodiciEsitoPagamentoDao dao = DaoFactory.createCodiciEsitoPagamentoDao();
		List<CodiciEsitoPagamento> lista = dao.getCodiciEsitoPagamentoAll();
		List<CodiciEsitoPagamentoDTO> listaDto = new ArrayList<CodiciEsitoPagamentoDTO>();

		log.debug("[" + this.getClass().getName() + "::getCodiciEsitoPagamentoAll ] lista.size " + lista.size());
		for (CodiciEsitoPagamento ge : lista) {
			CodiciEsitoPagamentoDTO dto = new CodiciEsitoPagamentoDTO();

			try {
				BeanUtils.copyProperties(dto, ge);
			} catch (IllegalAccessException e) {
				log.error("[" + this.getClass().getName() + "::getCodiciEsitoPagamentoAll ] ", e);
			} catch (InvocationTargetException e) {
				log.error("[" + this.getClass().getName() + "::getCodiciEsitoPagamentoAll ] ", e);
			}

			listaDto.add(dto);
		}

		log.debug("[" + this.getClass().getName() + "::getCodiciEsitoPagamentoAll ] END");
		return listaDto;
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
	public List<StatiRptDTO> getStatiRptAll(Credentials auth,String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[" + this.getClass().getName() + "::getStatiRptAll ] BEGIN");

		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		StatiRptDao dao = DaoFactory.createStatiRptDao();
		List<StatiRpt> lista = dao.getStatiRptAll();
		List<StatiRptDTO> listaDto = new ArrayList<StatiRptDTO>();

		log.debug("[" + this.getClass().getName() + "::getStatiRptAll ] lista.size " + lista.size());
		for (StatiRpt ge : lista) {
			StatiRptDTO dto = new StatiRptDTO();

			try {
				BeanUtils.copyProperties(dto, ge);
			} catch (IllegalAccessException e) {
				log.error("[" + this.getClass().getName() + "::getStatiRptAll ] ", e);
			} catch (InvocationTargetException e) {
				log.error("[" + this.getClass().getName() + "::getStatiRptAll ] ", e);
			}

			listaDto.add(dto);
		}

		log.debug("[" + this.getClass().getName() + "::getStatiRptAll ] END");
		return listaDto;
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
			String idEsitoPagamento, String idMsgRichiesta,String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[" + this.getClass().getName() + "::getRTByParam ] BEGIN");

		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		RTDao dao = DaoFactory.createRTDao();
		List<RT> lista = dao.getRTByParam(id, applicationId, transactionId, lastUpdateDa, lastUpdateA, insertDateDa, insertDateA, iuv, idEsitoPagamento, idMsgRichiesta);
		List<RTDTO> listaDto = new ArrayList<RTDTO>();

		log.debug("[" + this.getClass().getName() + "::getRTByParam ] lista.size " + lista.size());
		for (RT ge : lista) {
			RTDTO dto = new RTDTO();

			try {
				BeanUtils.copyProperties(dto, ge);
			} catch (IllegalAccessException e) {
				log.error("[" + this.getClass().getName() + "::getRTByParam ] ", e);
			} catch (InvocationTargetException e) {
				log.error("[" + this.getClass().getName() + "::getRTByParam ] ", e);
			}

			listaDto.add(dto);
		}
		this.setAudit(auth, Constants.CONSULTAZIONE_RT, "", "", "RT", "", ipclient);

		log.debug("[" + this.getClass().getName() + "::getRTByParam ] END");
		return listaDto;
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
			String idStatiRpt, String idMsgRichiesta,String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[" + this.getClass().getName() + "::getRPTByParam ] BEGIN");

		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		RPTDao dao = DaoFactory.createRPTDao();
		List<RPT> lista = dao.getRPTByParam(id, applicationId, transactionId, lastUpdateDa, lastUpdateA, insertDateDa, insertDateA, iuv, idStatiRpt, idMsgRichiesta);
		List<RPTDTO> listaDto = new ArrayList<RPTDTO>();

		log.debug("[" + this.getClass().getName() + "::getRPTByParam ] lista.size " + lista.size());
		for (RPT ge : lista) {
			RPTDTO dto = new RPTDTO();

			try {
				BeanUtils.copyProperties(dto, ge);
			} catch (IllegalAccessException e) {
				log.error("[" + this.getClass().getName() + "::getRPTByParam ] ", e);
			} catch (InvocationTargetException e) {
				log.error("[" + this.getClass().getName() + "::getRPTByParam ] ", e);
			}

			listaDto.add(dto);
		}
		this.setAudit(auth, Constants.CONSULTAZIONE_RPT, "", "", "RPT", "", ipclient);
		log.debug("[" + this.getClass().getName() + "::getRPTByParam ] END");
		return listaDto;
	}

	/**
	 * 
	 * @param auth
	 * @param enteId
	 * @param partitaIva
	 * @param descrizione
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public void insertEnte(Credentials auth, String enteId, String partitaIva, String descrizione, String attivo,String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException,
			AuthException {
		log.debug("[BoServicesBean::insertEnte] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		EntiDao dao = DaoFactory.createEntiDao();
		dao.insertEnte(enteId, partitaIva, descrizione, attivo);
		this.setAudit(auth, Constants.INSERT_ENTE, "", "", "ENTI", "", ipclient);

		log.debug("[" + this.getClass().getName() + "::insertEnte ] END");
	}

	/**
	 * 
	 * @param auth
	 * @param enteId
	 * @param partitaIva
	 * @param descrizione
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public void updateEnte(Credentials auth, String enteId, String partitaIva, String descrizione, String attivo,String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException,
			AuthException {
		log.debug("[BoServicesBean::updateEnte] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		EntiDao dao = DaoFactory.createEntiDao();
		dao.updateEnte(enteId, partitaIva, descrizione, attivo);
		this.setAudit(auth, Constants.MODIFICA_ENTE, "", "", "ENTI", "", ipclient);
		
		log.debug("[" + this.getClass().getName() + "::updateEnte ] END");
	}

	/**
	 * 
	 * @param auth
	 * @param enteId
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public void deleteEnte(Credentials auth, String enteId,String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[BoServicesBean::deleteEnte] BEGIN");
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		EntiDao dao = DaoFactory.createEntiDao();
		dao.deleteEnte(enteId);
		this.setAudit(auth, Constants.ELIMINA_ENTE, "", "", "ENTI", "", ipclient);

		log.debug("[" + this.getClass().getName() + "::deleteEnte ] END");

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
	public List<EntiDTO> findEntiAll(Credentials auth,String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		EntiDao dao = DaoFactory.createEntiDao();
		List<Enti> lista = dao.findEntiAll();
		List<EntiDTO> listaDto = new ArrayList<EntiDTO>();

		log.debug("[" + this.getClass().getName() + "::findEntiAll ] lista.size " + lista.size());
		for (Enti ge : lista) {
			EntiDTO dto = new EntiDTO();

			try {
				BeanUtils.copyProperties(dto, ge);
			} catch (IllegalAccessException e) {
				log.error("[" + this.getClass().getName() + "::findEntiAll ] ", e);
			} catch (InvocationTargetException e) {
				log.error("[" + this.getClass().getName() + "::findEntiAll ] ", e);
			}

			listaDto.add(dto);
		}
		this.setAudit(auth, Constants.CONSULTAZIONE_ENTI, "", "", "ENTI", "", ipclient);

		log.debug("[" + this.getClass().getName() + "::findEntiAll ] END");
		return listaDto;
	}

	/**
	 * 
	 * @param auth
	 * @param enteId
	 * @param partitaIva
	 * @param descrizione
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public List<EntiDTO> getEntiByParam(Credentials auth, String enteId, String partitaIva, String descrizione, String attivo,String ipclient) throws RemoteException, DaoException, MissingParameterException,
			NamingException, AuthException {
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		EntiDao dao = DaoFactory.createEntiDao();
		List<Enti> lista = dao.getEntiByParam(enteId, partitaIva, descrizione, attivo);
		List<EntiDTO> listaDto = new ArrayList<EntiDTO>();

		log.debug("[" + this.getClass().getName() + "::getEntiByParam ] lista.size " + lista.size());
		for (Enti ge : lista) {
			EntiDTO dto = new EntiDTO();

			try {
				BeanUtils.copyProperties(dto, ge);
				dto.setAttivo(ge.getAttivo());
				
			} catch (IllegalAccessException e) {
				log.error("[" + this.getClass().getName() + "::getEntiByParam ] ", e);
			} catch (InvocationTargetException e) {
				log.error("[" + this.getClass().getName() + "::getEntiByParam ] ", e);
			}

			listaDto.add(dto);
		}
		this.setAudit(auth, Constants.CONSULTAZIONE_ENTI, "", "", "ENTI", "", ipclient);

		log.debug("[" + this.getClass().getName() + "::getEntiByParam ] END");
		return listaDto;
	}

	/**
	 * 
	 * @param auth
	 * @param idApplicazione
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public List<EntiDTO> getEntiByApplicationId(Credentials auth, String idApplicazione,String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		EntiDao dao = DaoFactory.createEntiDao();
		List<Enti> lista = dao.getEntiByApplicationId(idApplicazione);
		List<EntiDTO> listaDto = new ArrayList<EntiDTO>();

		log.debug("[" + this.getClass().getName() + "::getEntiByParam ] lista.size " + lista.size());
		for (Enti ge : lista) {
			EntiDTO dto = new EntiDTO();

			try {
				BeanUtils.copyProperties(dto, ge);
			} catch (IllegalAccessException e) {
				log.error("[" + this.getClass().getName() + "::getEntiByParam ] ", e);
			} catch (InvocationTargetException e) {
				log.error("[" + this.getClass().getName() + "::getEntiByParam ] ", e);
			}
			listaDto.add(dto);
		}
		this.setAudit(auth, Constants.CONSULTAZIONE_ENTI, "", "", "ENTI", "", ipclient);

		log.debug("[" + this.getClass().getName() + "::getEntiByParam ] END");
		return listaDto;
	}

	/**
	 * 
	 * @param auth
	 * @param idApplicazione
	 * @param enteId
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public Integer insRelEnteApplication(Credentials auth, String idApplicazione, String enteId,String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		EntiDao dao = DaoFactory.createEntiDao();
		Integer ris = dao.insRelEnteApplication(idApplicazione, enteId);
		this.setAudit(auth, Constants.MODIFICA_REL_ENTE_APP, "", "", "R_APPLICATION_ENTI", "", ipclient);

		log.debug("[" + this.getClass().getName() + "::getEntiByParam ] END");
		return ris;
	}

	/**
	 * 
	 * @param auth
	 * @param idApplicazione
	 * @param enteId
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public Integer delRelEnteApplication(Credentials auth, String idApplicazione, String enteId,String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		EntiDao dao = DaoFactory.createEntiDao();
		Integer ris = dao.delRelEnteApplication(idApplicazione, enteId);

		log.debug("[" + this.getClass().getName() + "::getEntiByParam ] ris " + ris);

		this.setAudit(auth, Constants.MODIFICA_REL_ENTE_APP, "", "", "R_APPLICATION_ENTI", "", ipclient);
		
		log.debug("[" + this.getClass().getName() + "::getEntiByParam ] END");
		return ris;
	}

	/**
	 * 
	 * @param auth
	 * @param idinformativapsp
	 * @param identificativoFlusso
	 * @param identificativoPSP
	 * @param ragioneSociale
	 * @param dataPubblicazione
	 * @param dataInizioValidita
	 * @param urlInformazioniPSP
	 * @param stornoPagamento
	 * @param identificativoIntermediario
	 * @param identificativoCanale
	 * @param tipoVersamento
	 * @param modelloPagamento
	 * @param priorita
	 * @param disponibilitaServizio
	 * @param descrizioneServizio
	 * @param condizioniEconomicheMassime
	 * @param urlInformazioniCanale
	 * @param datainserimento
	 * @param statoinserimento
	 * @param ordinamento
	 * @param origine
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public List<InformativePSPDTO> getInformativePSPByParam(Credentials auth, Integer idinformativapsp, String identificativoFlusso, String identificativoPSP, String ragioneSociale,
			Date dataPubblicazione, Date dataInizioValidita, String urlInformazioniPSP, Integer stornoPagamento, String identificativoIntermediario, String identificativoCanale,
			String tipoVersamento, Integer modelloPagamento, Integer priorita, String disponibilitaServizio, String descrizioneServizio, String condizioniEconomicheMassime,
			String urlInformazioniCanale, Date datainserimento, String statoinserimento, Integer ordinamento, String origine,String ipclient) throws RemoteException, DaoException, MissingParameterException,
			NamingException, AuthException {

		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		InformativePSPDao dao = DaoFactory.createInformativePSPDao();
		List<InformativePSP> lista = dao.getInformativePSPByParam(idinformativapsp, identificativoFlusso, identificativoPSP, ragioneSociale, dataPubblicazione, dataInizioValidita, urlInformazioniPSP,
				stornoPagamento, identificativoIntermediario, identificativoCanale, tipoVersamento, modelloPagamento, priorita, disponibilitaServizio, descrizioneServizio,
				condizioniEconomicheMassime, urlInformazioniCanale, datainserimento, statoinserimento, ordinamento, origine);

		List<InformativePSPDTO> listaDto = new ArrayList<InformativePSPDTO>();

		//log.debug("[" + this.getClass().getName() + "::getInformativePSPByParam ] lista.size " + lista.size());
		for (InformativePSP ge : lista) {
			InformativePSPDTO dto = new InformativePSPDTO();

			try {
				BeanUtils.copyProperties(dto, ge);
			} catch (IllegalAccessException e) {
				log.error("[" + this.getClass().getName() + "::getInformativePSPByParam ] ", e);
			} catch (InvocationTargetException e) {
				log.error("[" + this.getClass().getName() + "::getInformativePSPByParam ] ", e);
			}

			dto.setDataInizioValidita(new Date(ge.getDataInizioValidita().getTime()));
			dto.setDatainserimento(new Date(ge.getDatainserimento().getTime()));
			dto.setDataPubblicazione(new Date(ge.getDataPubblicazione().getTime()));

			listaDto.add(dto);
		}

		this.setAudit(auth, Constants.CONSULTAZIONE_PSP, "", "", "INFORMATIVA_PSP", "", ipclient);
		
		log.debug("[" + this.getClass().getName() + "::getInformativePSPByParam ] END");

		return listaDto;

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
			String codiceesitosingolopagamento, Date dataesitosingolopagamento, Date datainserimento, Date datamodifica, String applicationId, Date dataregolamentoDa, Date dataregolamentoA,String ipclient)
			throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {

		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		FlussoSingoloPagamentoDao dao = DaoFactory.createFlussoSingoloPagamentoDao();
		List<FlussoSingoloPagamento> lista = dao.getFlussoSingoloPagamentoByParam(id,idFlusso, iuv, identificativounivocoriscossione, singoloimportopagato, codiceesitosingolopagamento,
				dataesitosingolopagamento, datainserimento, datamodifica, applicationId, dataregolamentoDa, dataregolamentoA);

		List<FlussoSingoloPagamentoDTO> listaDto = new ArrayList<FlussoSingoloPagamentoDTO>();

		log.debug("[" + this.getClass().getName() + "::getFlussoSingoloPagamentoByParam ] lista.size " + lista.size());
		for (FlussoSingoloPagamento ge : lista) {
			FlussoSingoloPagamentoDTO dto = new FlussoSingoloPagamentoDTO();

			try {
				BeanUtils.copyProperties(dto, ge);
			} catch (IllegalAccessException e) {
				log.error("[" + this.getClass().getName() + "::getFlussoSingoloPagamentoByParam ] ", e);
			} catch (InvocationTargetException e) {
				log.error("[" + this.getClass().getName() + "::getFlussoSingoloPagamentoByParam ] ", e);
			}

			dto.setDataesitosingolopagamento(new Date(ge.getDataesitosingolopagamento().getTime()));
			dto.setDatainserimento(new Date(ge.getDatainserimento().getTime()));
			dto.setDatamodifica(new Date(ge.getDatamodifica().getTime()));
			dto.setDataoraflusso(new Date(ge.getDataoraflusso().getTime()));
			dto.setDataregolamento(new Date(ge.getDataregolamento().getTime()));

			listaDto.add(dto);
		}

		this.setAudit(auth, Constants.CONSULTAZIONE_SINGOLO_PAG, "", "", "FLUSSO_SINGOLO_PAGAMENTO", "", ipclient);

		log.debug("[" + this.getClass().getName() + "::getFlussoSingoloPagamentoByParam ] END");
		return listaDto;
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
			Date dataoraflusso, Date dataregolamentoDa, Date dataregolamentoA, Date datainserimento, Date datamodifica, String xmlflusso, String denominazionemittente, String denominazionericevente,String ipclient)
			throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {

		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		FlussoRiversamentoDao dao = DaoFactory.createFlussoRiversamentoDao();
		List<FlussoRiversamento> lista = dao.getFlussoRiversamentoByParam(id, identificativopsp, identificativoflusso, versioneoggetto, identificativounivocoregolamento,
				identificativoistitutomittente, identificativoistitutoricevente, numerototalepagamenti, importototalepagamenti, dataoraflusso, dataregolamentoDa, dataregolamentoA, datainserimento,
				datamodifica, xmlflusso, denominazionemittente, denominazionericevente);

		List<FlussoRiversamentoDTO> listaDto = new ArrayList<FlussoRiversamentoDTO>();

		log.debug("[" + this.getClass().getName() + "::getFlussoSingoloPagamentoByParam ] lista.size " + lista.size());
		for (FlussoRiversamento ge : lista) {
			FlussoRiversamentoDTO dto = new FlussoRiversamentoDTO();

			try {
				BeanUtils.copyProperties(dto, ge);
			} catch (IllegalAccessException e) {
				log.error("[" + this.getClass().getName() + "::getFlussoSingoloPagamentoByParam ] ", e);
			} catch (InvocationTargetException e) {
				log.error("[" + this.getClass().getName() + "::getFlussoSingoloPagamentoByParam ] ", e);
			}

			dto.setDatainserimento(new Date(ge.getDatainserimento().getTime()));
			dto.setDatamodifica(new Date(ge.getDatamodifica().getTime()));
			dto.setDataoraflusso(new Date(ge.getDataoraflusso().getTime()));
			dto.setDataregolamento(new Date(ge.getDataregolamento().getTime()));

			listaDto.add(dto);
		}
		this.setAudit(auth, Constants.CONSULTAZIONE_FLUSSO, "", "", "FLUSSO_RIVERSAMENTO", "", ipclient);

		log.debug("[" + this.getClass().getName() + "::getFlussoSingoloPagamentoByParam ] END");
		return listaDto;
	}

	/**
	 * 
	 * @param auth
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public List<TipoVersamentoDTO> getListaTipoversamento(Credentials auth,String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {

		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		InformativePSPDao dao = DaoFactory.createInformativePSPDao();
		List<TipoVersamento> lista = dao.getListaTipoversamento();
		
		List<TipoVersamentoDTO> listaDto = new ArrayList<TipoVersamentoDTO>();

		log.debug("[" + this.getClass().getName() + "::getListaTipoversamento ] lista.size " + lista.size());
		for (TipoVersamento ge : lista) {
			TipoVersamentoDTO dto = new TipoVersamentoDTO();

			try {
				BeanUtils.copyProperties(dto, ge);
			} catch (IllegalAccessException e) {
				log.error("[" + this.getClass().getName() + "::getListaTipoversamento ] ", e);
			} catch (InvocationTargetException e) {
				log.error("[" + this.getClass().getName() + "::getListaTipoversamento ] ", e);
			}

			listaDto.add(dto);
		}

		log.debug("[" + this.getClass().getName() + "::getFlussoSingoloPagamentoByParam ] END");
		return listaDto;
	}
	
	/**
	 * 
	 * @param auth
	 * @param applicationId
	 * @param dateDa
	 * @param dateA
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public List<StatisticaApplicazioneTransazioneDTO> getStatisticaApplicazioneTransazione(Credentials auth,String applicationId, Date dateDa,Date dateA,String ipclient)throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {

		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		StatisticaApplicazioneTransazioneDao dao = DaoFactory.createStatisticaApplicazioneTransazioneDao();
		List<StatisticaApplicazioneTransazione> lista = dao.getStatisticaApplicazioneTransazione(applicationId,  dateDa, dateA);
		
		List<StatisticaApplicazioneTransazioneDTO> listaDto = new ArrayList<StatisticaApplicazioneTransazioneDTO>();

		log.debug("[" + this.getClass().getName() + "::getStatisticaApplicazioneTransazione ] lista.size " + lista.size());
		String appId ="";
		String OLDappId ="";	
		StatisticaApplicazioneTransazioneDTO dto = null;

		int indice = -1;
		for (StatisticaApplicazioneTransazione sat : lista) {
			
			appId = sat.getApplicationId();

			if(!appId.equalsIgnoreCase(OLDappId)){
				OLDappId = appId;
				dto = new StatisticaApplicazioneTransazioneDTO();
				dto.setApplicationId(appId);
				indice ++;
				listaDto.add(indice,dto);	
			}
	
			
			
			switch (sat.getCodStato()){
			case 0:
				listaDto.get(indice).setNotInitialized(sat.getNumXstato());
				break;	
			case 1:
				listaDto.get(indice).setInitialized(sat.getNumXstato());
				break;	
			case 2:
				listaDto.get(indice).setConfigured(sat.getNumXstato());
				break;	
			case 3:
				listaDto.get(indice).setStarted(sat.getNumXstato());
				break;	
			case 4:
				listaDto.get(indice).setSuccessful(sat.getNumXstato());
				break;	
			case 5:
				listaDto.get(indice).setUnsuccessful(sat.getNumXstato());
				break;	
			case 6:
				listaDto.get(indice).setCanceled(sat.getNumXstato());
				break;	
			case 7:
				listaDto.get(indice).setRefunded(sat.getNumXstato());
				break;	
			case 8:
				listaDto.get(indice).setToBeConfirmed(sat.getNumXstato());
				break;	
			case 9:
				listaDto.get(indice).setAttesaRT(sat.getNumXstato());
				break;	
			}
		}

		log.debug("[" + this.getClass().getName() + "::getStatisticaApplicazioneTransazione ] END");
		return listaDto;
	}
	
	
	/**
	 * 
	 * @param auth
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public  List<FlussoRiversamentoDTO> estraiFlussiDaServizio(Credentials auth,String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		log.debug("[" + this.getClass().getName() + "::estraiFlussiDaServizio] BEGIN");
		String sKey = null;
		List<FlussoRiversamentoDTO> listaFlussi = new ArrayList<FlussoRiversamentoDTO>();
		
		try {

			FileInputStream stream = new FileInputStream(envProps.getProperty("sKeyDb"));
			int len = stream.available();
			byte[] b = new byte[len];
			stream.read(b);
			b = Base64.decode(new String(b));
			sKey = new String(b);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());

		}

		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		ApplicationcustomfieldsDao appDao = DaoFactory.createApplicationcustomfieldsDao();
		appDao.setsKey(sKey);
		log.debug("[" + this.getClass().getName() + "::estraiFlussiDaServizio] skeydb=" + sKey);
		appDao.setsKey(sKey);

		List<Applicationcustomfields> lista       = appDao.findDistinctDomini();
		//List<ParametroNodoDTO> listaParametroNodo = new ArrayList<ParametroNodoDTO>();
		List<ParametroNodoDTO> listaParametroNodo = new ArrayList<ParametroNodoDTO>();
		ParametroNodoDTO parametroNodo = new ParametroNodoDTO();			
		String oldAppId                           = "";
		
		for( Applicationcustomfields acf : lista){
			
			String appId  = acf.getApplicationid();
			if (!appId.equals(oldAppId)) {
				if(!oldAppId.equals("")){
					listaParametroNodo.add(parametroNodo);
				}
				oldAppId = appId;
				parametroNodo = new ParametroNodoDTO();
			}


			String fieldName  = acf.getFieldname();
			String fieldvalue = acf.getFieldvalue();
			
			log.info("fieldName " + fieldName);
			log.info("fieldvalue " + fieldvalue);
			
			if("identificativoDominio".equals(fieldName.trim())){
				parametroNodo.setIdentificativoDominio(fieldvalue);
			}else if("identificativointermediarioPA".equals(fieldName.trim())){
				parametroNodo.setIdentificativointermediarioPA(fieldvalue);
			}else if("identificativoStazioneIntermediarioPA".equals(fieldName.trim())){
				parametroNodo.setIdentificativoStazioneIntermediarioPA(fieldvalue);
			}else if ("passwordDominioNodoSpc".equals(fieldName.trim())){
				parametroNodo.setPasswordDominioNodoSpc(fieldvalue);
			}else if ("portaDiDominio".equals(fieldName.trim())){
				parametroNodo.setPortaDiDominio(fieldvalue);
			}
			
		}
		//add ultimo record
		listaParametroNodo.add(parametroNodo);
		log.info("estraggo lista listaParametroNodoProvvisorio " +listaParametroNodo.size());

		InformativePSPDao dao = DaoFactory.createInformativePSPDao();
		//List<String> listaPsp = dao.getListaIdentificativopsp();

		//log.info("estraggo lista listaPsp " + listaPsp.size() );
		
		List<FlussoRiversamentoDTO> listaFlussiTotali = new ArrayList<FlussoRiversamentoDTO>();
		for (ParametroNodoDTO elem : listaParametroNodo) {
			if (elem != null && elem.getIdentificativoDominio()!=null && !elem.getIdentificativoDominio().equals("")){
				//listaParametroNodo.add(elem);
				listaFlussi = elaborateFlussiAllPsp( auth,elem);
				//listaFlussiTotali = add(listaFlussiTotali,listaFlussi);
				listaFlussiTotali.addAll(listaFlussi);
			}else{
				log.info("elem " + elem.getIdentificativoDominio() );
			}
		}
		// fine lista parametri del nodo

		log.debug("[" + this.getClass().getName() + "::estraiFlussiDaServizio] END");

		this.setAudit(auth, Constants.CONSULTAZIONE_FLUSSO, "", "", "FLUSSO_RIVERSAMENTO", "", ipclient);
		
		return listaFlussi;
	}
	
	private List<FlussoRiversamentoDTO> elaborateFlussiAllPsp(Credentials auth,ParametroNodoDTO pm) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {

		log.info("elaborateFlussiXPsp Inizio .");

		String endPointUrl 								= pm.getPortaDiDominio();
		String azione 									= "nodoChiediElencoFlussiRendicontazione";
		String identificativoDominio 					= pm.getIdentificativoDominio();
		String identificativoIntermediarioPA 			= pm.getIdentificativointermediarioPA();
		String identificativoStazioneIntermediarioPA 	= pm.getIdentificativoStazioneIntermediarioPA();
		String password 								= pm.getPasswordDominioNodoSpc();
		List<FlussoRiversamentoDTO> listaFlussoRiversamentoDTO = new ArrayList<FlussoRiversamentoDTO>();
		
		try {
				List<CtFlussoRiversamento> listaCtFlussoRiversamento = new InvocaServizioFlussiRendicontazione().elaborazioneFlussiPerPSP(endPointUrl, azione, identificativoDominio, identificativoIntermediarioPA,identificativoStazioneIntermediarioPA, password, null);				
				for (CtFlussoRiversamento fr : listaCtFlussoRiversamento) {
					FlussoRiversamentoDTO elem =new FlussoRiversamentoDTO();
					elem.setIdentificativoflusso(fr.getIdentificativoFlusso());
					elem.setDataoraflusso(fr.getDataOraFlusso().toGregorianCalendar().getTime());
					listaFlussoRiversamentoDTO.add(elem);
					log.info("getDataOraFlusso ." + elem.getDataoraflusso());
				}
		} catch (Exception e) {
			log.error("elaborateFlussiAllPsp", e);
		}
		log.info("elaborateFlussiAllPsp Fine ." + listaFlussoRiversamentoDTO.size());
		return listaFlussoRiversamentoDTO;
	}
	

	private List<FlussoRiversamentoDTO> elaborateFlussiXPsp(Credentials auth,List<String> listaPsp,ParametroNodoDTO pm) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {

		log.info("elaborateFlussiXPsp Inizio .");

		String endPointUrl 								= pm.getPortaDiDominio();
		String azione 									= "nodoChiediElencoFlussiRendicontazione";
		String identificativoDominio 					= pm.getIdentificativoDominio();
		String identificativoIntermediarioPA 			= pm.getIdentificativointermediarioPA();
		String identificativoStazioneIntermediarioPA 	= pm.getIdentificativoStazioneIntermediarioPA();
		String password 								= pm.getPasswordDominioNodoSpc();
		List<FlussoRiversamentoDTO> listaFlussoRiversamentoDTO = new ArrayList<FlussoRiversamentoDTO>();
		
		try {
			for (String idPSP : listaPsp) {
				List<CtFlussoRiversamento> listaCtFlussoRiversamento = new InvocaServizioFlussiRendicontazione().elaborazioneFlussiPerPSP(endPointUrl, azione, identificativoDominio, identificativoIntermediarioPA,identificativoStazioneIntermediarioPA, password, idPSP);				
				for (CtFlussoRiversamento fr : listaCtFlussoRiversamento) {
					FlussoRiversamentoDTO elem =new FlussoRiversamentoDTO();
					elem.setIdentificativoflusso(fr.getIdentificativoFlusso());
					elem.setIdentificativopsp(idPSP);
					elem.setVersioneoggetto(fr.getVersioneOggetto());
					elem.setIdentificativounivocoregolamento(fr.getIdentificativoUnivocoRegolamento());
					elem.setIdentificativoistitutomittente(fr.getIstitutoMittente().getIdentificativoUnivocoMittente().getCodiceIdentificativoUnivoco());
					elem.setIdentificativoistitutoricevente(fr.getIstitutoRicevente().getIdentificativoUnivocoRicevente().getCodiceIdentificativoUnivoco());
					elem.setNumerototalepagamenti(fr.getNumeroTotalePagamenti().intValue());
					elem.setDataoraflusso(fr.getDataOraFlusso().toGregorianCalendar().getTime());
					
					elem.setDataregolamento(fr.getDataRegolamento().toGregorianCalendar().getTime());
					elem.setDataoraflusso(fr.getDataOraFlusso().toGregorianCalendar().getTime());

					elem.setImportototalepagamenti(fr.getImportoTotalePagamenti().intValue());
					elem.setDenominazionemittente(fr.getIstitutoMittente().getDenominazioneMittente());
					elem.setDenominazionericevente(fr.getIstitutoRicevente().getDenominazioneRicevente());
					
					listaFlussoRiversamentoDTO.add(elem);
				}
			}
		} catch (Exception e) {
			log.error("elaborateFlussiXPsp", e);
		}

		log.info("elaborateFlussiXPsp Fine ." + listaFlussoRiversamentoDTO.size());
		return listaFlussoRiversamentoDTO;
	}
	
	/**
	 * 
	 * @param auth
	 * @param id
	 * @param applicationId
	 * @param idEnte
	 * @param tipoversamento
	 * @param identificativopsp
	 * @param iban
	 * @param dataInizioValidita
	 * @param dataFineValidita
	 * @param attivo
	 * @param ipclient
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
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
																		  String attivo,
																		  String ipclient)
			throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {

		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		IbanEnteApplicationDao dao = DaoFactory.createIbanEnteApplicationDao();

		log.info("[BoServicesBeanImpl::getIbanEnteApplicationByParam]  applicationId "+applicationId);
		log.info("[BoServicesBeanImpl::getIbanEnteApplicationByParam]  idEnte "+idEnte);
		log.info("[BoServicesBeanImpl::getIbanEnteApplicationByParam]  tipoversamento "+tipoversamento);
		log.info("[BoServicesBeanImpl::getIbanEnteApplicationByParam]  identificativopsp "+identificativopsp);
		
		List<IbanEnteApplication> lista = dao.getIbanEnteApplicationByParam(
				   id,
				   applicationId,
				   idEnte,
				   tipoversamento,
				   identificativopsp,
				   iban,
				   dataInizioValidita,
				   dataFineValidita,
				   attivo);

		List<IbanEnteApplicationDTO> listaDto = new ArrayList<IbanEnteApplicationDTO>();

		log.debug("[" + this.getClass().getName() + "::getIbanEnteApplicationByParam ] lista.size " + lista.size());
		
		for (IbanEnteApplication ge : lista) {
			IbanEnteApplicationDTO dto = new IbanEnteApplicationDTO();

			try {
				BeanUtils.copyProperties(dto, ge);
			} catch (IllegalAccessException e) {
				log.error("[" + this.getClass().getName() + "::getIbanEnteApplicationByParam ] ", e);
			} catch (InvocationTargetException e) {
				log.error("[" + this.getClass().getName() + "::getIbanEnteApplicationByParam ] ", e);
			}

			dto.setDataInizioValidita(new Date(ge.getDataInizioValidita().getTime()));
			
			if(ge.getDataFineValidita()!=null){
				dto.setDataFineValidita(new Date(ge.getDataFineValidita().getTime()));
			}
			
			listaDto.add(dto);
		}
		this.setAudit(auth, Constants.CONSULTAZIONE_IBAN, "", "", "IbanEnteApplication", "", ipclient);

		log.debug("[" + this.getClass().getName() + "::getIbanEnteApplicationByParam ] END");
		return (List<IbanEnteApplicationDTO>) listaDto;
	}
	
	/**
	 * 
	 * @param auth
	 * @param applicationId
	 * @param idEnte
	 * @param tipoversamento
	 * @param identificativopsp
	 * @param iban
	 * @param dataInizioValidita
	 * @param dataFineValidita
	 * @param attivo
	 * @param ipclient
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public void insertIbanEnteApplication(Credentials auth,
										  String applicationId,
										  String idEnte,
										  String tipoversamento,
										  String identificativopsp,
										  String iban,
										  Date dataInizioValidita,
										  Date dataFineValidita,
										  String attivo,
									  	  String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		IbanEnteApplicationDao dao = DaoFactory.createIbanEnteApplicationDao();
		dao.insertIbanEnteApplication(
				   applicationId,
				   idEnte,
				   tipoversamento,
				   identificativopsp,
				   iban,
				   dataInizioValidita,
				   dataFineValidita,
				   attivo
				   );


		this.setAudit(auth, Constants.INSERIMENTO_IBAN, "", "", "IbanEnteApplication", "", ipclient);
		
		log.debug("[" + this.getClass().getName() + "::insertIbanEnteApplication ] END");
	}

	/**
	 * 
	 * @param auth
	 * @param id
	 * @param applicationId
	 * @param idEnte
	 * @param tipoversamento
	 * @param identificativopsp
	 * @param iban
	 * @param dataInizioValidita
	 * @param dataFineValidita
	 * @param attivo
	 * @param ipclient
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public void updateIbanEnteApplication(Credentials auth,
										Integer id,
										String applicationId,
										String idEnte,
										String tipoversamento,
										String identificativopsp,
										String iban,
										Date dataInizioValidita,
										Date dataFineValidita,
										String attivo,
										String ipclient) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException {
		if (auth == null) {
			log.error("Credenziali obbligatorie.");
			throw new AuthException("Credenziali obbligatorie.");
		}
		if (!this.checkAuth(auth)) {
			log.error("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
			throw new AuthException("user id:" + auth.getUserAuth() + " - cod.fisc.: " + auth.getCodfisc() + " o gruppo id:" + auth.getGroup() + " non autorizzati per questa operazione");
		}

		IbanEnteApplicationDao dao = DaoFactory.createIbanEnteApplicationDao();
		dao.updateIbanEnteApplication(
				id,
				applicationId,
				idEnte,
				tipoversamento,
				identificativopsp,
				iban,
				dataInizioValidita,
				dataFineValidita,
				attivo
		);
		this.setAudit(auth, Constants.MODIFICA_IBAN, "", "", "IbanEnteApplication", "", ipclient);
		log.debug("[" + this.getClass().getName() + "::updateIbanEnteApplication ] END");
	}
	
}
