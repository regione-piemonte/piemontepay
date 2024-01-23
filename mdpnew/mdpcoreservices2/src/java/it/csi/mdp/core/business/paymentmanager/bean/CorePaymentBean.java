/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.paymentmanager.bean;

import it.csi.mdp.adapters.business.BankAdapter;
import it.csi.mdp.adapters.business.BankAdapterHome;
import it.csi.mdp.adapters.business.BusinessException;
import it.csi.mdp.core.business.dao.ApplicationDao;
import it.csi.mdp.core.business.dao.ApplicationDetailDao;
import it.csi.mdp.core.business.dao.ApplicationcustomfieldsDao;
import it.csi.mdp.core.business.dao.ConfigDao;
import it.csi.mdp.core.business.dao.ElementoMultiVersamentoDao;
import it.csi.mdp.core.business.dao.GatewayDao;
import it.csi.mdp.core.business.dao.GatewaydetailDao;
import it.csi.mdp.core.business.dao.LanguageDao;
import it.csi.mdp.core.business.dao.MdpCurrencyDao;
import it.csi.mdp.core.business.dao.MdpErroriDao;
import it.csi.mdp.core.business.dao.PartAnComuneDao;
import it.csi.mdp.core.business.dao.PaymentmodeDao;
import it.csi.mdp.core.business.dao.TransazioneDao;
import it.csi.mdp.core.business.dao.TransazioneExtraAttributeDao;
import it.csi.mdp.core.business.dao.VapplicationcomuniDao;
import it.csi.mdp.core.business.dto.Application;
import it.csi.mdp.core.business.dto.ApplicationDetail;
import it.csi.mdp.core.business.dto.ApplicationDetailPk;
import it.csi.mdp.core.business.dto.Applicationcustomfields;
import it.csi.mdp.core.business.dto.Config;
import it.csi.mdp.core.business.dto.ElementoMultiversamento;
import it.csi.mdp.core.business.dto.Gateway;
import it.csi.mdp.core.business.dto.Gatewaydetail;
import it.csi.mdp.core.business.dto.Language;
import it.csi.mdp.core.business.dto.MdpCurrency;
import it.csi.mdp.core.business.dto.MdpErrori;
import it.csi.mdp.core.business.dto.PartAnComune;
import it.csi.mdp.core.business.dto.Paymentmode;
import it.csi.mdp.core.business.dto.Transazione;
import it.csi.mdp.core.business.dto.TransazioneExtraAttribute;
import it.csi.mdp.core.business.dto.TransazionePk;
import it.csi.mdp.core.business.dto.Vapplicationcomuni;
import it.csi.mdp.core.business.dto.multicarrello.RPTData;
import it.csi.mdp.core.business.exceptions.ApplicationDetailDaoException;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.exceptions.GatewayDaoException;
import it.csi.mdp.core.business.exceptions.GatewaydetailDaoException;
import it.csi.mdp.core.business.exceptions.TransazioneDaoException;
import it.csi.mdp.core.business.factory.DaoFactory;
import it.csi.mdp.core.business.paymentmanager.local.AppGateway;
import it.csi.mdp.core.business.util.MailBody;
import it.csi.mdp.core.business.util.MailServletAuthenticator;
import it.csi.mdp.core.business.util.PlaceHolder;
import it.csi.mdp.core.business.util.StringUtil;
import it.csi.mdp.core.interfacecxf.CheckFaultBean;
import it.csi.mdp.core.interfacecxf.MissingParameterException;
import it.csi.mdp.core.util.ConnectionFactory;
import it.csi.mdp.core.util.Constants;
import it.csi.mdp.core.util.LoggerUtil;
import it.csi.mdp.utility.CostantiNodoSpc;
import it.csi.peas.ejb.PEAS;
import it.csi.peas.ejb.PEASHome;
import it.csi.peas.entity.Basket;
import it.csi.peas.entity.ExtraAttribute;
import it.csi.peas.entity.Item;
import it.csi.util.performance.StopWatch;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.ws.security.util.Base64;
import org.springframework.core.io.ClassPathResource;

/**
 * <p>
 * Implementazione bean logica core
 * </p>
 * 
 * 
 * @version 1.0, &nbsp; 15-FEB-2011
 * @since SDK1.5
 */

public class CorePaymentBean

{
	protected static Logger log = Logger.getLogger(Constants.APPLICATION_CODE);
	private Properties envProps = new Properties();
	private StringWriter sw = new StringWriter();
	private StringBuffer sb = sw.getBuffer();
	private PrintWriter pw = new PrintWriter(sw);
	private String output = "";

	public CorePaymentBean()
	{
		try
		{
			// log.debug("[CorePaymentBean::CorePaymentBean] BEGIN");
			ClassPathResource cp = new ClassPathResource("/env.properties");
			// log.debug("[CorePaymentBean::CorePaymentBean] cp:" + cp.getPath()
			// + cp.getFilename());
			envProps.load(cp.getInputStream());
			// log.debug("[CorePaymentBean::CorePaymentBean] envProps:"+envProps.toString());
			// log.debug("[CorePaymentBean::CorePaymentBean] END");
			ConfigDao confdao = DaoFactory.createConfigDao();
			List<Config> lconf = confdao.findAll();
			for (int i = 0; i < lconf.size(); i++)
			{
				Config c = lconf.get(i);
				log.debug("[CorePaymentBean::CorePaymentBean] config:" + c.toString());
				envProps.put(StringUtils.trimToEmpty(c.getKey()), StringUtils.trimToEmpty(c.getValue()));
			}

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace(pw);
			sb = sw.getBuffer();
			output = sb.toString();

			log.error("[CorePaymentBean::CorePaymentBean] ERROR:" + output);
			this.errorMail(null, null);
			// e.printStackTrace();
		}
	}

	/* metodi pagamento */

	public AppGateway[] getModalitaPagamento(Transazione t, String appId) throws DaoException, NamingException, CreateException,
			MissingParameterException
	{
		// TODO testare!
		log.debug("[CorePaymentBean::getModalitaPagamento] BEGIN");
		if (appId == null)
		{

			output = "Parametro appId (application id) obbligatorio.";
			this.errorMail(appId, t);
			throw new MissingParameterException("Parametro appId (application id) obbligatorio.");

		}
		if (appId.trim().equals(""))
		{
			output = "Parametro appId (application id) obbligatorio.";
			this.errorMail(appId, t);
			throw new MissingParameterException("Parametro appId (application id) obbligatorio.");

		}
		if (t == null)
		{
			output = "Parametro t (transazione) obbligatorio.";
			this.errorMail(appId, t);
			throw new MissingParameterException("Parametro t (transazione) obbligatorio.");

		}

		
		
		log.debug("[CorePaymentBean::getModalitaPagamento] param appId:" + appId);
		log.debug("[CorePaymentBean::getModalitaPagamento] param t:" + t.toString());
		
		TransazioneDao daoTransaction = DaoFactory.createTransazioneDao();
		
		Transazione transazioneiInizializzata = daoTransaction.findByPrimaryKey(t.getTransactionId(), "");
		if (transazioneiInizializzata.getCodStato() >= 3)
		{

			output = "Transazione con id:" + transazioneiInizializzata.getTransactionId()
					+ " gia' elaborata dal Motore dei Pagamenti";
			this.errorMail(transazioneiInizializzata.getApplicationId(), transazioneiInizializzata);
			throw new DaoException("Transazione con id:" + transazioneiInizializzata.getTransactionId()
					+ " gia' elaborata dal Motore dei Pagamenti");
		}
		ApplicationDetailDao daoAppDetail = DaoFactory.createApplicationDetailDao();
		String sKey = null;
		try
		{

			FileInputStream stream = new FileInputStream(envProps.getProperty("sKeyDb"));
			int len = stream.available();
			byte[] b = new byte[len];
			stream.read(b);
			//b = Base64.decode(new String(b));
			sKey = new String(b);
		} catch (Exception e)
		{
			e.printStackTrace(pw);
			sb = sw.getBuffer();
			output = sb.toString();
			this.errorMail(t.getApplicationId(), t);
			throw new DaoException(e.getMessage());

		}
		daoAppDetail.setsKey(sKey);
		log.debug("[CorePaymentBean::getModalitaPagamento] daoAppDetail:" + daoAppDetail.toString());
		List<ApplicationDetail> elencoAppDetail = daoAppDetail.findWhereApplicationidEqualsAndNotNodo(appId, "");
		log.debug("[CorePaymentBean::getModalitaPagamento] _result:" + elencoAppDetail.size());
		AppGateway[] arrAppGateway = new AppGateway[elencoAppDetail.size()];

		if (!elencoAppDetail.isEmpty())
		{
			int i = 0;

			for (ApplicationDetail objApp : elencoAppDetail)
			{
				if (objApp.getEnabled().equals("1"))
				{
					arrAppGateway[i] = new AppGateway();
				
					log.debug("[CorePaymentBean::getModalitaPagamento]objApp:" + objApp.getApplicationid());
					arrAppGateway[i].setApplicationId(objApp.getApplicationid());

					arrAppGateway[i].setGatewayId(objApp.getGatewayid());
					GatewayDao daoGateway = DaoFactory.createGatewayDao();
					log.debug("[CorePaymentBean::getModalitaPagamento]getGatewayId:" + objApp.getGatewayid());
					List<Gateway> resGate = daoGateway.findWhereGatewayIdEquals(objApp.getGatewayid());
					if (resGate != null)
					{
						if (resGate.size() >= 1)
						{
							arrAppGateway[i].setGatewayDescription(resGate.get(0).getGatewayDescription());
							arrAppGateway[i].setGatewayServiceName(resGate.get(0).getGatewayservicename());
						}

						arrAppGateway[i].setMerchantId(objApp.getMerchantid());
						arrAppGateway[i].setPaymentmodeId(objApp.getPaymentmodeid());
						arrAppGateway[i].setTipoCommissione(objApp.getTipocommissione());
						log.debug("[CorePaymentBean::getModalitaPagamento]Il valore di  min: " + objApp.getValorecommissionemin());
						arrAppGateway[i].setValoreMin(new BigDecimal(objApp.getValorecommissionemin(), new MathContext(4, RoundingMode.HALF_UP)).doubleValue());
						log.debug("[CorePaymentBean::getModalitaPagamento]Il valore di  min DOPO: " + arrAppGateway[i].getValoreMin());
						arrAppGateway[i].setValoreMax(objApp.getValorecommissionemax());
						arrAppGateway[i].setSogliaMax(objApp.getSogliaa());
						arrAppGateway[i].setSogliaMin(objApp.getSogliada());
						PaymentmodeDao daoPayment = DaoFactory.createPaymentmodeDao();
						List<Paymentmode> resPay = daoPayment.findWherePaymentmodeIdEquals(objApp.getPaymentmodeid());
						if (resPay != null)
						{
							if (resPay.size() >= 1)
							{

								arrAppGateway[i].setPaymentmodeDescription(resPay.get(0).getPaymentmodeDescription());
								log.debug("[CorePaymentBean::getModalitaPagamento]PaymentDescription:"
										+ resPay.get(0).getPaymentmodeDescription());
							} else
							{
								output = "Nessuna modalita pagamento trovata per id:" + objApp.getPaymentmodeid()
										+ ". Verificare tabella PaymentMode.";
								log.error("[" + this.getClass().getName() + "::startTransazione] " + output);
								this.errorMail(appId, null);
								throw new DaoException(output);

							}

						} else
						{
							output = "Nessuna modalita pagamento trovata per id:" + objApp.getPaymentmodeid()
									+ ". Verificare tabella PaymentMode.";
							log.error("[" + this.getClass().getName() + "::startTransazione] " + output);
							this.errorMail(appId, null);
							throw new DaoException(output);

						}

						// arrAppGateway[i].setPaymentDescription(objApp.get));

						log.debug("[CorePaymentBean::getModalitaPagamento]lookup jndi name del gatewayadapter:"
								+ resGate.get(0).getGatewayservicename());
						Context initCtx = null;
						Object bankAdapterBean = null;
						try
						{
							initCtx = new InitialContext();
							bankAdapterBean = initCtx.lookup(resGate.get(0).getGatewayservicename().trim());
						} catch (Exception e)
						{
							output = "Errore in lookup del servizio gateway " + resGate.get(0).getGatewayDescription()
									+ " con jndi name:" + resGate.get(0).getGatewayservicename().trim()
									+ ". Verificare tabella Gateway.";
							log.error("[" + this.getClass().getName() + "::startTransazione] " + output);
							this.errorMail(appId, null);
							throw new DaoException(output);
						}
						// "mdppaymentsrv/ejb/PagOnlineAdapter");
						log.debug("[CorePaymentBean::getModalitaPagamento] lookup ok");
						if (bankAdapterBean != null)
						{
							BankAdapterHome home = (BankAdapterHome) bankAdapterBean;

							BankAdapter bankAdapter = null;
							try
							{
								bankAdapter = home.create();
								log.debug("[CorePaymentBean::getModalitaPagamento] home interface create ok");
							} catch (RemoteException e)
							{
								e.printStackTrace(pw);
								sb = sw.getBuffer();
								output = sb.toString();
								log.error("[" + this.getClass().getName() + "::startTransazione] " + output);
								this.errorMail(appId, t);
								throw new DaoException("Ejb corrispondente a jndi name " + resGate.get(0).getGatewayservicename()
										+ " errore in creazione home interface. Verificare tabella gateway.");
							}

							if (bankAdapter != null)
							{
								try
								{
									log.debug("[CorePaymentBean::getModalitaPagamento] ricerca application detail gwid:"
											+ resGate.get(0).getGatewayId() + " payid:" + resPay.get(0).getPaymentmodeId());
									t.setGatewayId(resGate.get(0).getGatewayId());
									t.setPaymentid(resPay.get(0).getPaymentmodeId());
									ApplicationDetailDao appDet = DaoFactory.createApplicationDetailDao();
									sKey = null;
									try
									{

										FileInputStream stream = new FileInputStream(envProps.getProperty("sKeyDb"));
										int len = stream.available();
										byte[] b = new byte[len];
										stream.read(b);
										b = Base64.decode(new String(b));
										sKey = new String(b);
									} catch (Exception e)
									{
										e.printStackTrace(pw);
										sb = sw.getBuffer();
										output = sb.toString();
										this.errorMail(t.getApplicationId(), t);
										throw new DaoException(e.getMessage());

									}
									appDet.setsKey(sKey);
									ApplicationDetail ad = appDet.findByPrimaryKey(t.getApplicationId(), t.getGatewayId(), t
											.getPaymentid(), "");
									if (ad != null)
									{
										log.debug("[CorePaymentBean::getModalitaPagamento]ad:" + ad.toString());
										Transazione t1 = null;
										try
										{
											t1 = bankAdapter.getCommissioni(t, ad);
										} catch (RemoteException e)
										{
											e.printStackTrace(pw);
											sb = sw.getBuffer();
											output = sb.toString();
											log.error("[" + this.getClass().getName() + "::startTransazione] " + output);
											this.errorMail(appId, null);
											throw new DaoException("Errore in calcolo commissioni per gateway:" + t.getGatewayId());
										}
										arrAppGateway[i].setValoreComm(t1.getCommissioniApplicate());

									}
								} catch (Exception ex)
								{
									ex.printStackTrace(pw);
									sb = sw.getBuffer();
									output = sb.toString();
									log.error("[" + this.getClass().getName() + "::startTransazione] " + output);
									this.errorMail(appId, t);
									throw new DaoException("Errore in calcolo commissioni per gateway:" + output);
								}
							}
						} else
						{

							log.error("[" + this.getClass().getName() + "::startTransazione] errore in ricerca bean "
									+ resGate.get(0).getGatewayservicename());
							output = "[" + this.getClass().getName() + "::startTransazione] errore in ricerca bean "
									+ resGate.get(0).getGatewayservicename();
							this.errorMail(t.getApplicationId(), t);
							throw new DaoException("Ejb corrispondente a jndi name " + resGate.get(0).getGatewayservicename()
									+ " non trovato. Verificare tabella gateway.");
						}

						i++;

					}

				}
			}
		}

		t.setCodStato(2);
		TransazioneDao tdao = DaoFactory.createTransazioneDao();
		TransazionePk pk = new TransazionePk(t.getTransactionId());
		tdao.update(pk, t);
		log.debug("[CorePaymentBean::getModalitaPagamento] END");
		LoggerUtil.dumpObject("Sto array Com'e' qui?", arrAppGateway);
		return arrAppGateway;
	}

	public Transazione initTransazione(String appId, String basketId) throws DaoException, NamingException, CreateException,
			DatatypeConfigurationException, MissingParameterException, SOAPFaultException
	{
		log.debug("[CorePaymentBean::initTransazione] BEGIN");
		StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
		stopWatch.start();
		log.debug("[CorePaymentBean::initTransazione] appId:" + appId);

		if (appId == null)
		{
			this.output = "[" + this.getClass().getName() + "::initTransazione] Parametro appId obbligatorio";
			log.error("[" + this.getClass().getName() + "::initTransazione] Parametro appId obbligatorio");
			this.errorMail(null, null);
			// CheckFaultBean faultInfo = new CheckFaultBean();
			// faultInfo.setCode("MissingParameterException");
			// faultInfo.setFaultCode("MissingParameterException");
			// faultInfo.setMessage("Parametro appId obbligatorio faultinfo message");
			// faultInfo.setFaultCode("Parametro appId obbligatorio faultinfo code");
			// faultInfo.setDetail("faultinfo error detail");
			// faultInfo.setFaultDetail("fualtinfo detail");
			throw new MissingParameterException("Parametro appId obbligatorio.");// ,faultInfo);

		}
		this.getApplication(appId);
		String newPrimaryKey = (envProps.getProperty("environment") == null ? "PS" : envProps.getProperty("environment"));
		// PeasTableId newId = new PeasTableId();
		// newId.setTablePk(Constants.TRANSACTION_TABLE_NAME);
		// PeasTableIdDao tableId = DaoFactory.createPeasTableIdDao();
		// List<PeasTableId> idList =
		// tableId.findWhereTablePkEquals(Constants.TRANSACTION_TABLE_NAME);
		// if (idList != null && !idList.isEmpty())
		// {
		// PeasTableId tId = idList.get(0);
		// String oldPrimaryKey = tId.getTableId();
		// if (oldPrimaryKey.indexOf("PS") == 0 && !oldPrimaryKey.equals("PS"))
		// oldPrimaryKey = oldPrimaryKey.substring(2);
		// aggiungo alla chiave generata il prefisso "PS"
		// newPrimaryKey = "PS" + PrimaryKeyGenerator.getNextPK(oldPrimaryKey);
		// newId.setTableId(newPrimaryKey);
		// tableId.update(newId);
		// }
		DecimalFormat df = new DecimalFormat("000000000000000");
		TransazioneDao t = DaoFactory.createTransazioneDao();
		newPrimaryKey = newPrimaryKey + df.format(t.getNextTransazioneId());
		log.debug("[CorePaymentBean::initTransazione] newPrimaryKey:" + newPrimaryKey);

		Transazione voidTransaction = new Transazione();
		voidTransaction.setTransactionId(newPrimaryKey);
		voidTransaction.setApplicationId(appId);

		voidTransaction.setLanguage("ITA");
		voidTransaction.setBasketId(basketId);
		Calendar objCal = Calendar.getInstance();
		voidTransaction.setInitDate(new java.sql.Date(objCal.getTimeInMillis()));
		voidTransaction.setCodStato(1);

		TransazionePk tPk = t.insert(voidTransaction);
		log.debug("[CorePaymentBean::initTransazione] tPk:" + tPk.getTransactionId());
		log.debug("[CorePaymentBean::initTransazione] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("CorePaymentBean", "initTransazione()", "chiamata initTransazione", "initTransazione");

		return voidTransaction;
	}

	public String startTransazione(Transazione transaction, TransazioneExtraAttribute[] tea, List<RPTData> elencoRPT) throws DaoException, NamingException,
			CreateException, MissingParameterException
	{
		log.debug("[CorePaymentBean::startTransazione] BEGIN");
		
		if (transaction != null)
		{
			log.debug("[CorePaymentBean::startTransazione] id transazione:" + transaction.getTransactionId());
		} else
		{
			log.error("[" + this.getClass().getName() + "::startTransazione] Parametro transazione obbligatorio");
			output = "[" + this.getClass().getName() + "::startTransazione] Parametro transazione obbligatorio";
			this.errorMail(null, null);

			throw new MissingParameterException("Parametro transazione obbligatorio");
		}
		
		if (transaction.getTransactionId() == null)
		{
			log.error("[" + this.getClass().getName()
					+ "::startTransazione] Parametro transazione transactionId (currency code) obbligatorio");
			output = "[" + this.getClass().getName()
					+ "::startTransazione] Parametro transazione transactionId (currency code) obbligatorio";
			this.errorMail(transaction.getApplicationId(), transaction);

			throw new MissingParameterException("Parametro transazione transactionId obbligatorio");
		}
		if (transaction.getCcy() == null)
		{
			log.error("[" + this.getClass().getName()
					+ "::startTransazione] Parametro transazione ccy (currency code) obbligatorio");
			output = "[" + this.getClass().getName() + "::startTransazione] Parametro transazione ccy (currency code) obbligatorio";
			this.errorMail(transaction.getApplicationId(), transaction);

			throw new MissingParameterException("Parametro transazione ccy (currency code) obbligatorio");
		}
		if (transaction.getGatewayId() == null)
		{
			log.error("[" + this.getClass().getName() + "::startTransazione] Parametro transazione gatewayid) obbligatorio");
			output = ("[" + this.getClass().getName() + "::startTransazione] Parametro transazione gatewayid) obbligatorio");
			this.errorMail(transaction.getApplicationId(), transaction);

			throw new MissingParameterException("Parametro transazione gatewayid obbligatorio");
		}
		if (transaction.getGatewaypaymodeid() == null)
		{
			log.error("[" + this.getClass().getName() + "::startTransazione] Parametro transazione gatewaypaymodeid) obbligatorio");
			output = ("[" + this.getClass().getName() + "::startTransazione] Parametro transazione gatewaypaymodeid) obbligatorio");
			this.errorMail(transaction.getApplicationId(), transaction);

			throw new MissingParameterException("Parametro transazione gatewaypaymodeid obbligatorio");
		}
		if (transaction.getLanguage() == null)
		{
			log.error("[" + this.getClass().getName() + "::startTransazione] Parametro transazione language obbligatorio");
			output = ("[" + this.getClass().getName() + "::startTransazione] Parametro transazione language obbligatorio");
			this.errorMail(transaction.getApplicationId(), transaction);

			throw new MissingParameterException("Parametro transazione language obbligatorio");
		}
		if (transaction.getAmount() == 0)
		{
			log.error("[" + this.getClass().getName() + "::startTransazione] Parametro transazione amount maggiore di zero");
			output = ("[" + this.getClass().getName() + "::startTransazione] Parametro transazione amount maggiore di zero");
			this.errorMail(transaction.getApplicationId(), transaction);

			throw new MissingParameterException("Parametro transazione amount maggiore di zero");
		}
			
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
		stopWatch.start();

		String urlToCall = null; // indirizzo da richiamare per raggiungere il
		// gateway
		Context initCtx = new InitialContext();

		Gateway gt = null;

		try
		{
			TransazioneDao daoTransaction = DaoFactory.createTransazioneDao();
			TransazionePk pk = new TransazionePk(transaction.getTransactionId());
			Transazione transazioneiInizializzata = daoTransaction.findByPrimaryKey(transaction.getTransactionId(), null);
			if (transazioneiInizializzata == null)
				throw new DaoException("Transazione con id:" + transaction.getTransactionId()
					+ " Non trovata");
			GatewayDao gtdao = DaoFactory.createGatewayDao();
			gt = gtdao.findByPrimaryKey(transaction.getGatewayId());
			if (gt == null)
			{
				log.error("[" + this.getClass().getName() + "::startTransazione] gateway id:" + transaction.getGatewayId()
						+ " non trovato");
				output = "[" + this.getClass().getName() + "::startTransazione] gateway id:" + transaction.getGatewayId()
						+ " non trovato";
				this.errorMail(transaction.getApplicationId(), transaction);

				throw new DaoException("Parametro gateway id:" + transaction.getGatewayId() + " non trovato");

			}
			/*
			 * lookup
			 */
			if (gt.getGatewayservicename() == null)
			{
				log.error("[" + this.getClass().getName() + "::startTransazione] gateway service name per gateway id:"
						+ transaction.getGatewayId() + " non trovato");
				output = ("[" + this.getClass().getName() + "::startTransazione] gateway service name per gateway id:"
						+ transaction.getGatewayId() + " non trovato");

				this.errorMail(transaction.getApplicationId(), transaction);

				throw new DaoException("Parametro gateway id:" + transaction.getGatewayId() + " non trovato");

			}
			LoggerUtil.debug("GATEWAY CHE STO CARICANDO " +gt.getGatewayservicename());
			Object bankAdapterBean = initCtx.lookup(gt.getGatewayservicename());
			// "mdppaymentsrv/ejb/PagOnlineAdapter");
			ApplicationDetail ad = null;
			if (bankAdapterBean != null)
			{
				BankAdapterHome home = (BankAdapterHome) bankAdapterBean;

				BankAdapter bankAdapter = home.create();

				if (bankAdapter != null)
				{
					try
					{
						ApplicationDetailDao appDet = DaoFactory.createApplicationDetailDao();
						String sKey = null;
						try
						{

							FileInputStream stream = new FileInputStream(envProps.getProperty("sKeyDb"));
							int len = stream.available();
							byte[] b = new byte[len];
							stream.read(b);
							//b = Base64.decode(new String(b));
							sKey = new String(b);
						} catch (Exception e)
						{
							e.printStackTrace(pw);
							sb = sw.getBuffer();
							output = sb.toString();
							this.errorMail(transaction.getApplicationId(), transaction);
							throw new DaoException(e.getMessage());

						}
						
						appDet.setsKey(sKey);
						ad = appDet.findByPrimaryKey(transaction.getApplicationId(), transaction.getGatewayId(), transaction
								.getGatewaypaymodeid(), "");
						
						if (ad != null && ad.getEnabled().equals("1"))
						{

							if (transazioneiInizializzata.getCodStato() >= 3)
							{

								output = "Transazione con id:" + transaction.getTransactionId()
										+ " gia' elaborata dal Motore dei Pagamenti";
								this.errorMail(transaction.getApplicationId(), transaction);
								throw new DaoException("Transazione con id:" + transaction.getTransactionId()
										+ " gia' elaborata dal Motore dei Pagamenti");
							}

							LanguageDao lang = DaoFactory.createLanguageDao();
							List<Language> gwlangList = lang.findWhereGatewayidandMdpLanguageidEquals(transaction.getGatewayId(),
									"ITA");
							log.debug("[" + this.getClass().getName() + "::startTransazione trascodifica lingua gwid:"
									+ transaction.getGatewayId());
							log.debug("[" + this.getClass().getName() + "::startTransazione trascodifica lingua gwlanglist:"
									+ gwlangList);
							if (gwlangList != null && gwlangList.size() > 0)
							{
								log.debug("[" + this.getClass().getName() + "::startTransazione trascodifica lingua lang gw code:"
										+ gwlangList.get(0).getGtwlanguagecode());
								transaction.setLanguage(gwlangList.get(0).getGtwlanguagecode());
							}
							
							transaction.setMerchantId(ad.getMerchantid());
							MdpCurrencyDao curr = DaoFactory.createMdpCurrencyDao();
							List<MdpCurrency> lCurr = curr.findWhereMdpccyAndGatewayIdEquals(transaction.getCcy(), transaction
									.getGatewayId());
							if (lCurr == null || lCurr.size() == 0)
							{
								output = "no currency translation found for appid=" + transaction.getApplicationId()
										+ " gatewayid=" + transaction.getGatewayId() + " ccy=" + transaction.getCcy()
										+ " e paymentmodeid=" + transaction.getGatewaypaymodeid();
								this.errorMail(transaction.getApplicationId(), transaction);
								throw new DaoException("no currency translation found for appid=" + transaction.getApplicationId()
										+ " gatewayid=" + transaction.getGatewayId() + " ccy=" + transaction.getCcy()
										+ " e paymentmodeid=" + transaction.getGatewaypaymodeid());

							}
							MdpCurrency ccy = lCurr.get(0);
							
							TransazioneExtraAttributeDao daoTransExtraAttr = DaoFactory.createTransazioneExtraAttributeDao();
							if (tea != null)
							{
								for (int i = 0; i < tea.length; i++)
								{
									if (tea[i] != null)
									 daoTransExtraAttr.insert(tea[i]);
								}

							} else
							{

								TransazioneExtraAttribute tea0 = new TransazioneExtraAttribute();
								tea0.setTransactionId(transaction.getTransactionId());
								tea0.setName("portal");
								tea0.setValue("Default");
								daoTransExtraAttr.insert(tea0);
							}

							daoTransaction.update(pk, transaction);
							transaction.setCcy(ccy.getGtwcurrencycode());
						} 
						else
						{
							output = "Nessun dettaglio applicazione valido esistente o abilitato per appid=" + transaction.getApplicationId() + " gatewayid="
									+ transaction.getGatewayId() + " e paymentmodeid=" + transaction.getGatewaypaymodeid();
							this.errorMail(transaction.getApplicationId(), transaction);
							throw new DaoException("Nessun dettaglio applicazione valido esistente o abilitato per appid=" + transaction.getApplicationId()
									+ " gatewayid=" + transaction.getGatewayId() + " e paymentmodeid="
									+ transaction.getGatewaypaymodeid());
						}
					} 
					catch (ApplicationDetailDaoException ex)
					{

						ex.printStackTrace(pw);
						sb = sw.getBuffer();
						output = sb.toString();
						log.error("[" + this.getClass().getName() + "::startTransazione] " + output);
						this.errorMail(transaction.getApplicationId(), transaction);
						throw new DaoException("[" + this.getClass().getName() + "::startTransazione] " + ex.getMessage());
					}

					Gatewaydetail gd = null;

					try
					{
						GatewaydetailDao gateDet = DaoFactory.createGatewaydetailDao();
						gd = gateDet.findByPrimaryKey(transaction.getGatewayId(), transaction.getGatewaypaymodeid());
						if (gd == null || !gd.getEnabled().equals("1"))
						{
							log.error("[" + this.getClass().getName() + "::startTransazione] Nessun gateway o strumento di pagamento valido e abilitato esistente per appid="
									+ transaction.getApplicationId() + " gatewayid=" + transaction.getGatewayId()
									+ " e paymentmodeid=" + transaction.getGatewaypaymodeid());
							this.output = "[" + this.getClass().getName()
									+ "::startTransazione] Nessun gateway o strumento di pagamento valido e abilitato esistente per appid=" + transaction.getApplicationId()
									+ " gatewayid=" + transaction.getGatewayId() + " e paymentmodeid="
									+ transaction.getGatewaypaymodeid();
							this.errorMail(transaction.getApplicationId(), transaction);
							throw new DaoException("[" + this.getClass().getName()
									+ "::startTransazione] Nessun gateway o strumento di pagamento valido e abilitato esistente per appid=" + transaction.getApplicationId()
									+ " gatewayid=" + transaction.getGatewayId() + " e paymentmodeid="
									+ transaction.getGatewaypaymodeid());
						}
					} catch (GatewaydetailDaoException ex)
					{
						ex.printStackTrace(pw);
						sb = sw.getBuffer();
						output = sb.toString();
						log.error("[" + this.getClass().getName() + "::startTransazione] " + output);
						this.errorMail(transaction.getApplicationId(), transaction);
						throw new DaoException("[" + this.getClass().getName() + "::startTransazione] " + ex.getMessage());
					}

					try
					{
						transaction = bankAdapter.initTransazione(transaction);
						transaction.setOldstate(1);
						transaction.setCodStato(2);
						daoTransaction.update(pk, transaction);
						log.debug("[" + this.getClass().getName() + "::startTransazione] invoking bankadapter:"
								+ gt.getGatewayservicename() + " " + bankAdapter.getClass().getName());
						
						  
						
						//transaction.setAmount(transaction.getAmount() + (transaction.isCommissioniApplicateNull()?0:transaction.getCommissioniApplicate()));
						
						Transazione transazioneconcommissioni = bankAdapter.getCommissioni(transaction, ad);
						transaction.setAmount(transaction.getAmount()+transazioneconcommissioni.getCommissioniApplicate());
						LoggerUtil.dumpObject("ELENCO RPT ", elencoRPT);
						
						log.debug ( "Adapter " + bankAdapter);
						log.debug ( "Transaction " + transaction);
						log.debug ( "GD " + gd);
						log.debug ( "AD " + ad);
						log.debug ( "TEA " + tea);
						log.debug ( "Elenco RPT " + elencoRPT);
						
						transaction = bankAdapter.startTransazione(transaction, gd, this.getCustomFields(transaction
								.getApplicationId(),gt.getGatewayId()), ad, tea, elencoRPT);
						transaction.setOldstate(2);
						Calendar objCal = Calendar.getInstance();
						transaction.setStartDate(new java.util.Date(objCal.getTimeInMillis()));
						urlToCall = transaction.getPayurl();
						transaction.setCodStato(3); // STARTED
						log.debug("[CorePaymentBean::startTransazione] transazione prima di update:" + transaction);
						daoTransaction.update(pk, transaction);
						log.debug("[" + this.getClass().getName() + "::startTransazione] Url:" + urlToCall);
					} catch (BusinessException e) {
						LoggerUtil.error("BUSINESS EXCEPTION", e);
						StringBuilder sb = new StringBuilder("ERRORE DI VALIDAZIONE DA PARTE DELL'ADAPTER, SEGUONO DETTAGLI ");
						for (String s : e.getDettagli()) {
							sb.append(" ");
							sb.append(s);
						}
						transaction.setCodStato(5);
						daoTransaction.update(pk, transaction);
						throw new DaoException("[" + this.getClass().getName() + "::startTransazione] " + sb);
					} 
					
                catch ( it.csi.mdp.adapters.business.SystemException e ) {
                    LoggerUtil.error ( "SYSTEM EXCEPTION", e );
                    StringBuilder sb = new StringBuilder ( "ERRORE DI VALIDAZIONE DA PARTE DELL'ADAPTER, SEGUONO DETTAGLI " );
                    sb.append (e.getMessage ());
//                    for ( String s: e.getDettagli () ) {
//                        sb.append ( " " );
//                        sb.append ( s );
//                    }
                    transaction.setCodStato ( 5 );
                    daoTransaction.update ( pk, transaction );
                    throw new DaoException ( "[" + this.getClass ().getName () + "::startTransazione] " + sb );
            
            } 
					
					
//					catch (Exception e)
//					{
//						LoggerUtil.error("BUSINESS EXCEPTION", e);
//						e.printStackTrace();
//						e.printStackTrace(pw);
//						sb = sw.getBuffer();
//						output = sb.toString();
//						log.error("[" + this.getClass().getName() + "::startTransazione] " + output, e);
//						this.errorMail(transaction.getApplicationId(), transaction);
//						String errmessage = "NULL MESSAGE";
//						if(e.getMessage() != null) {
//						    errmessage = (e.getMessage().length()>2000?e.getMessage().substring(0,1999):e.getMessage());
//						}
//						transaction.setPgresultcode(errmessage);
//						transaction.setCodStato(5);
//						daoTransaction.update(pk, transaction);
//						if(e.getMessage () != null) {
//						    throw new DaoException("[" + this.getClass().getName() + "::startTransazione] " + e.getMessage());
//						} else 
//                            throw new DaoException("[" + this.getClass().getName() + "::startTransazione]");
//					}
				}
			} else
			{

				log.error("[" + this.getClass().getName() + "::startTransazione] lookupFallita jndi name:"
						+ gt.getGatewayservicename());
				this.output = "[" + this.getClass().getName() + "::startTransazione] lookupFallita jndi name:"
						+ gt.getGatewayservicename();
				this.errorMail(transaction.getApplicationId(), transaction);
				throw new DaoException("[" + this.getClass().getName() + "::startTransazione] lookupFallita jndi name:"
						+ gt.getGatewayservicename());
			}
		} catch (Exception e1)
		{
		    LoggerUtil.error ( "GENERIC EXCEPTION - M", e1 );
			e1.printStackTrace(pw);
			sb = sw.getBuffer();
			output = sb.toString();
			log.error("[" + this.getClass().getName() + "::startTransazione] error: " + output);
			StringBuffer bodyText = new StringBuffer("Errore di sistema\r\n");
			GregorianCalendar gc = new GregorianCalendar();
			bodyText.append("Data e ora:" + sdf.format(gc.getTime()) + "\r\n");
			if (transaction != null)
			{
				bodyText.append("Id Applicazione:" + transaction.getApplicationId() + "\r\n");
				bodyText.append("Id Transazione:" + transaction.getTransactionId() + "\r\n");
			}

			bodyText.append("Dettagli errore:\r\n");
			bodyText.append(output);
			try
			{
				this.sendMail(envProps.getProperty("MDPMailbox"), bodyText.toString(), envProps.getProperty("SubjectSystemError"));
			} catch (Exception e2)
			{
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			throw new DaoException(output);
		}

		log.debug("[CorePaymentBean::startTransazione] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("CorePaymentBean", "startTransazione()", "chiamata startTransazione", "startTransazione");

		return urlToCall;
	}

	public Transazione getStatoTransazione(String transactionId) throws DaoException, NamingException, CreateException,
			MissingParameterException
	{
		log.debug("[CorePaymentBean::getStatoTransazione] BEGIN");
		StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
		stopWatch.start();

		TransazioneDao daoTransaction = DaoFactory.createTransazioneDao();
		TransazionePk pk = new TransazionePk(transactionId);
		Transazione transaction = daoTransaction.findByPrimaryKey(transactionId, "");
		if (transaction == null)
		{
			output = "[" + this.getClass().getName() + "::getStatoTransazione] Transazione codice:" + transactionId
					+ " inesistente";
			this.errorMail(null, null);
			throw new MissingParameterException("[" + this.getClass().getName() + "::getStatoTransazione] Transazione codice:"
					+ transactionId + " inesistente");
		}
		log.debug("[CorePaymentBean::getStatoTransazione] GATEWAY");
		Context initCtx = new InitialContext();

		Gateway gt = null;

		try
		{
			GatewayDao gtdao = DaoFactory.createGatewayDao();
			gt = gtdao.findByPrimaryKey(transaction.getGatewayId());
		} catch (GatewayDaoException ex)
		{
			ex.printStackTrace(pw);
			sb = sw.getBuffer();
			output = sb.toString();
			log.error("[" + this.getClass().getName() + "::getStatoTransazione] " + output);
			this.errorMail(transaction.getApplicationId(), transaction);
			throw new MissingParameterException("[" + this.getClass().getName() + "::getStatoTransazione] " + ex.getMessage());
		}

		/*
		 * lookup
		 */
		log.debug("[CorePaymentBean::getStatoTransazione] ENTRO ? " + transaction.getCodStato());
		if (transaction.getCodStato() >= 3 ) // STARTED
		{
			log.debug("[CorePaymentBean::getStatoTransazione] ENTRATO!!!! " + transaction.getCodStato());
			Object bankAdapterBean = initCtx.lookup(gt.getGatewayservicename());
			
			log.debug("[CorePaymentBean::getStatoTransazione] BANK ADAPTER " + bankAdapterBean);
			// "mdppaymentsrv/ejb/PagOnlineAdapter");

			if (bankAdapterBean != null)
			{
				BankAdapterHome home = (BankAdapterHome) bankAdapterBean;

				BankAdapter bankAdapter = null;
				try
				{
					bankAdapter = home.create();
				} catch (RemoteException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				log.debug("[CorePaymentBean::getStatoTransazione] BANK ADAPTER 2" + bankAdapter);

				if (bankAdapter != null)
				{
					Gatewaydetail gd = null;

					try
					{
						GatewaydetailDao gateDet = DaoFactory.createGatewaydetailDao();
						gd = gateDet.findByPrimaryKey(transaction.getGatewayId(), transaction.getGatewaypaymodeid());
					} catch (GatewaydetailDaoException ex)
					{
						ex.printStackTrace(pw);
						sb = sw.getBuffer();
						output = sb.toString();
						log.error("[" + this.getClass().getName() + "::getStatoTransazione] " + output);
						this.errorMail(transaction.getApplicationId(), transaction);
						throw new MissingParameterException("[" + this.getClass().getName() + "::getStatoTransazione] "
								+ ex.getMessage());
					}
					Calendar objCal = Calendar.getInstance();
					try
					{
						log.debug("[CorePaymentBean::getStatoTransazione] VERIFICA ESITO? " + gd.isVerificaesito());
						if (gd.isVerificaesito())
						{
							transaction = bankAdapter.getStatoTransazione(transaction, gd, this.getCustomFields(transaction
									.getApplicationId(),gd.getGatewayId()), transaction.getCodStato());
	
							// imposto campo cod_stato su transazione
	
							transaction.setFinishDate(new java.sql.Date(objCal.getTimeInMillis()));
							// transaction.setCodStato(stato);
							daoTransaction.update(pk, transaction);
	
							log.debug("[" + this.getClass().getName() + "::getStatoTransazione] Stato:" + transaction.getCodStato());
						}
					} catch (Exception e)
					{
						// transaction.setFinishDate(new
						// java.sql.Date(objCal.getTimeInMillis()));
						// transaction.setCodStato(5);
						// transaction.setPgresultcode(e.getMessage());
						// daoTransaction.update(pk, transaction);

						e.printStackTrace(pw);
						sb = sw.getBuffer();
						output = sb.toString();
						log.error("[" + this.getClass().getName() + "::getStatoTransazione] " + output);
						this.errorMail(transaction.getApplicationId(), transaction);
						throw new MissingParameterException("[" + this.getClass().getName() + "::getStatoTransazione] "
								+ e.getMessage());
					}
				}
			}
		}
		log.debug("[CorePaymentBean::getStatoTransazione] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("CorePaymentBean", "getStatoTransazione()", "chiamata getStatoTransazione", "getStatoTransazione");

		return transaction;
	}

	public Transazione getTransazione(String transactionId) throws DaoException, NamingException, CreateException
	{
		log.debug("[CorePaymentBean::getTransazione] BEGIN");
		StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
		stopWatch.start();

		TransazioneDao daoTransaction = DaoFactory.createTransazioneDao();
		// TransazionePk pk = new TransazionePk(transactionId);
		Transazione transaction = daoTransaction.findByPrimaryKey(transactionId, "");

		// Context initCtx = new InitialContext();

		// Gateway gt = null;
		//
		// try
		// {
		// GatewayDao gtdao = DaoFactory.createGatewayDao();
		// gt = gtdao.findByPrimaryKey(transaction.getGatewayId());
		// } catch (GatewayDaoException ex)
		// {
		// log.error("[" + this.getClass().getName() + "::getTransazione] " +
		// ex.getMessage());
		// throw new RemoteException("[" + this.getClass().getName() +
		// "::getTransazione] " + ex.getMessage());
		// }

		log.debug("[CorePaymentBean::getTransazione] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("CorePaymentBean", "getTransazione()", "chiamata getTransazione", "getTransazione");

		return transaction;
	}
	
	public void setStatoTransazione(String transactionId, long stato, String gwCodStato) throws MissingParameterException,
	DaoException
	{
		this.setStatoTransazione(transactionId, stato, gwCodStato, null);
	}

	public void setStatoTransazione(String transactionId, long stato, String gwCodStato, String iuv) throws MissingParameterException,
			DaoException
	{
		/* aggiornamento db mdp */
		log.debug("[CorePaymentBean::setStatoTransazione] BEGIN");
		log.debug("[CorePaymentBean::setStatoTransazione] transaction_id:" + transactionId);
		Transazione transaction = null;
		try
		{
			StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
			stopWatch.start();

			TransazioneDao daoTransaction = DaoFactory.createTransazioneDao();
			TransazionePk pk = new TransazionePk(transactionId);
			transaction = daoTransaction.findByPrimaryKey(transactionId, "");
			Calendar objCal = Calendar.getInstance();
			transaction.setOldstate(transaction.getCodStato());
			transaction.setCodStato(stato);
			transaction.setChangestatedate(new java.util.Date(objCal.getTimeInMillis()));
			transaction.setPgresultcode(gwCodStato);
			if (stato == 5 || stato == 4 || stato == 7 || stato == 6)
			{
				transaction.setFinishDate(new java.util.Date(objCal.getTimeInMillis()));

			}
			MdpCurrencyDao curr = DaoFactory.createMdpCurrencyDao();

			List<MdpCurrency> lCurr = curr.findWhereGtwccyAndGatewayIdEquals(transaction.getCcy(), transaction.getGatewayId());
			if (lCurr == null || lCurr.size() == 0)
			{
				output = "no currency translation found for appid=" + transaction.getApplicationId() + " gatewayid="
						+ transaction.getGatewayId() + " ccy=" + transaction.getCcy() + " e paymentmodeid="
						+ transaction.getGatewaypaymodeid();
				this.errorMail(transaction.getApplicationId(), transaction);
				throw new DaoException("no currency translation found for appid=" + transaction.getApplicationId() + " gatewayid="
						+ transaction.getGatewayId() + " ccy=" + transaction.getCcy() + " e paymentmodeid="
						+ transaction.getGatewaypaymodeid());

			}
			MdpCurrency ccy = lCurr.get(0);

			transaction.setCcy(ccy.getMdpcurrencycode());
			daoTransaction.update(pk, transaction);
			log.debug("[CorePaymentBean::setStatoTransazione] aggiornata transazione:" + transactionId + " a stato:" + stato);
			if (stato == 5 || stato == 4 || stato == 6)
			{
				GatewayDao gwdao = DaoFactory.createGatewayDao();
				Gateway gw = gwdao.findByPrimaryKey(transaction.getGatewayId());
				log.debug("[CorePaymentBean::setStatoTransazione] found gateway:" + gw.getGatewayId());
				GatewaydetailDao gdao = DaoFactory.createGatewaydetailDao();
				log.debug("[CorePaymentBean::setStatoTransazione] finding gatewaydetail:" + transaction.getGatewayId() + "-"
						+ transaction.getGatewaypaymodeid());
				Gatewaydetail gd = gdao.findByPrimaryKey(transaction.getGatewayId(), transaction.getGatewaypaymodeid());
				log.debug("[CorePaymentBean::setStatoTransazione] found gatewaydetail:" + gd.getGatewayId());
				PaymentmodeDao pdao = DaoFactory.createPaymentmodeDao();
				Paymentmode pmode = pdao.findByPrimaryKey(transaction.getGatewaypaymodeid());
				log.debug("[CorePaymentBean::setStatoTransazione] found paymentmode:" + pmode.getPaymentmodeId());
				ApplicationDao apdao = DaoFactory.createApplicationDao();
				List<Application> ap = apdao.findWhereIdEquals(transaction.getApplicationId(), "");
				log.debug("[CorePaymentBean::setStatoTransazione] found application:" + ap.get(0).getId() + "-"
						+ ap.get(0).getApplicationname());

				ApplicationDetailPk pkad = new ApplicationDetailPk(transaction.getApplicationId(), transaction.getGatewayId(),
						transaction.getGatewaypaymodeid());
				ApplicationDetailDao addao = DaoFactory.createApplicationDetailDao();
				String sKey = null;
				try
				{

					FileInputStream stream = new FileInputStream(envProps.getProperty("sKeyDb"));
					int len = stream.available();
					byte[] b = new byte[len];
					stream.read(b);
					//b = Base64.decode(new String(b));
					sKey = new String(b);
				} catch (Exception e)
				{
					e.printStackTrace(pw);
					sb = sw.getBuffer();
					output = sb.toString();
					this.errorMail(transaction.getApplicationId(), transaction);
					throw new DaoException(e.getMessage());

				}
				addao.setsKey(sKey);
				ApplicationDetail ad = addao.findByPrimaryKey(pkad, "");

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				DecimalFormat df = new DecimalFormat("###,###,###,##0.00", new DecimalFormatSymbols(Locale.ITALY));
				PlaceHolder ph = new PlaceHolder(gwCodStato, transaction.getTransactionId(), transaction.getMerchantId(), gw
						.getGatewayProvider(), pmode.getPaymentmodeDescription(), (transaction.getFinishDate() == null ? "" : sdf
						.format(transaction.getFinishDate())), df.format(transaction.getAmount()), transaction.getCcy(), "", "",
						transaction.getBuyerEmail(), transaction.getBuyername(), ap.get(0).getNumeroverde());
				ph.setNumAutor(transaction.getAuthornumber());
				ph.setNumOper(transaction.getOpernumber());
				
				if (iuv!=null && !iuv.trim().isEmpty())
                	ph.setIuv(iuv);
				
				log.debug("[CorePaymentBean::setStatoTransazione] numautor=" + transaction.getAuthornumber() + " numoper="
						+ transaction.getOpernumber());
				// MdpCurrencyDao curr = DaoFactory.createMdpCurrencyDao();
				// List<MdpCurrency> lCurr =
				// curr.findWhereGtwccyAndGatewayIdEquals(transaction.getCcy(),
				// transaction.getGatewayId());
				// MdpCurrency ccy = lCurr.get(0);
				// transaction.setCcy(ccy.getMdpcurrencycode());

				try
				{

					if (stato == 5 || stato == 6)
					{
						if (ad.getMail2buyerko().equals("1") && transaction.getBuyerEmail() != null
								&& !transaction.getBuyerEmail().trim().equals(""))
						{
							String bodyText = MailBody.processBodyMail(ph, this.envProps, stato);
							this.sendMail(transaction.getBuyerEmail(), bodyText, this.envProps
									.getProperty("SubjectNotifyResponseMail"));
						}
						if (ad.getMail2esercko().equals("1"))
						{
							String bodyText = MailBody.processBodyMail(ph, this.envProps, stato);
							this.sendMail(ap.get(0).getEsercemail(), bodyText, this.envProps
									.getProperty("SubjectNotifyResponseMail"));
						}
						if (ad.getMail2sysko().equals("1"))
						{
							String bodyText = MailBody.processBodyMail(ph, this.envProps, stato);
							this.sendMail(envProps.getProperty("MDPMailbox"), bodyText, this.envProps
									.getProperty("SubjectNotifyResponseMail"));
						}

					}
					if (stato == 4)
					{
						if (ad.getMail2buyerok().equals("1") && transaction.getBuyerEmail() != null
								&& !transaction.getBuyerEmail().trim().equals(""))
						{
							String bodyText = MailBody.processBodyMail(ph, this.envProps, stato);
							this.sendMail(transaction.getBuyerEmail(), bodyText, this.envProps
									.getProperty("SubjectNotifyResponseMail"));
						}
						if (ad.getMail2esercok().equals("1"))
						{
							String bodyText = MailBody.processBodyMail(ph, this.envProps, stato);
							this.sendMail(ap.get(0).getEsercemail(), bodyText, this.envProps
									.getProperty("SubjectNotifyResponseMail"));
						}
						if (ad.getMail2sysok().equals("1"))
						{
							String bodyText = MailBody.processBodyMail(ph, this.envProps, stato);
							this.sendMail(envProps.getProperty("MDPMailbox"), bodyText, this.envProps
									.getProperty("SubjectNotifyResponseMail"));
						}

					}
				} catch (Exception e)
				{
					e.printStackTrace(pw);
					sb = sw.getBuffer();
					output = sb.toString();
					log.error("[" + this.getClass().getName() + "::startTransazione] error: " + output);
					StringBuffer bodyText = new StringBuffer("Errore di sistema\r\n");
					bodyText.append("Id Transazione:" + transaction.getTransactionId() + "\r\n");
					bodyText.append("Dettagli errore:\r\n");
					bodyText.append(output);
					// try
					// {
					// this.sendMail(envProps.getProperty("MDPMailbox"),
					// bodyText.toString(), envProps
					// .getProperty("SubjectSystemError"));
					// } catch (Exception e1)
					// {
					// // TODO Auto-generated catch block
					// e1.printStackTrace();
					// }
					this.errorMail(transaction.getApplicationId(), transaction);
					// throw new RemoteException(output);
				}
			}
			log.debug("[CorePaymentBean::setStatoTransazione] END");

			stopWatch.stop();
			stopWatch
					.dumpElapsed("CorePaymentBean", "setStatoTransazione()", "chiamata setStatoTransazione", "setStatoTransazione");
		} catch (Exception e1)
		{
			e1.printStackTrace(pw);
			sb = sw.getBuffer();
			output = sb.toString();
			log.error("[" + this.getClass().getName() + "::startTransazione] error: " + output);
			StringBuffer bodyText = new StringBuffer("Errore di sistema\r\n");
			if (transaction != null)
			{
				bodyText.append("Id Transazione:" + transaction.getTransactionId() + "\r\n");
			}

			bodyText.append("Dettagli errore:\r\n");
			bodyText.append(output);
			try
			{
				this.sendMail(envProps.getProperty("MDPMailbox"), bodyText.toString(), envProps.getProperty("SubjectSystemError"));
			} catch (Exception e2)
			{
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			throw new MissingParameterException(output);
		}

	}

	public Applicationcustomfields[] getCustomFields(String applicationid, String gatewayid) throws DaoException
	{
		log.debug("[" + this.getClass().getName() + "::getCustomFields] BEGIN");
		Applicationcustomfields[] acf;
		ApplicationcustomfieldsDao daoCustomFields = DaoFactory.createApplicationcustomfieldsDao();
		String sKey = null;
		try
		{

			FileInputStream stream = new FileInputStream(envProps.getProperty("sKeyDb"));
			int len = stream.available();
			byte[] b = new byte[len];
			stream.read(b);
			//b = Base64.decode(new String(b));
			sKey = new String(b);
			
			
		} catch (Exception e)
		{
			e.printStackTrace(pw);
			sb = sw.getBuffer();
			output = sb.toString();
			this.errorMail(applicationid, null);
			throw new DaoException(e.getMessage());

		}
		daoCustomFields.setsKey(sKey);
		List<Applicationcustomfields> lstCustomFields = daoCustomFields.findWhereApplicationidAndGatewayIdEquals(applicationid, gatewayid);

		if (!lstCustomFields.isEmpty())
		{
			acf = new Applicationcustomfields[lstCustomFields.size()];
			int i = 0;

			for (Applicationcustomfields customfield : lstCustomFields)
			{
				acf[i] = customfield;
				log.debug("[" + this.getClass().getName() + "::getCustomFields] " + acf[i].toString());
				i++;
			}
		} else
		{
			// log.error("[" + this.getClass().getName() +
			// "::getCustomFields] no applicationdetal found for appid:" +
			// applicationid);
			// this.errorMail(null);
			// throw new DaoException("[" + this.getClass().getName() +
			// "::getCustomFields] no applicationdetal found for appid:"
			// + applicationid);
			acf = new Applicationcustomfields[1];
			acf[0] = new Applicationcustomfields();
		}
		log.debug("[" + this.getClass().getName() + "::getCustomFields] END");
		return acf;
	}

	public ApplicationDetail getApplicationDetail(String applicationid, String gatewayid, String paymentmodeid) throws DaoException
	{
		log.debug("[" + this.getClass().getName() + "::getApplicationDetail] BEGIN");
		ApplicationDetailDao daoAppDetails = DaoFactory.createApplicationDetailDao();
		String sKey = null;
		try
		{

			FileInputStream stream = new FileInputStream(envProps.getProperty("sKeyDb"));
			int len = stream.available();
			byte[] b = new byte[len];
			stream.read(b);
			//b = Base64.decode(new String(b));
			sKey = new String(b);
		} catch (Exception e)
		{
			e.printStackTrace(pw);
			sb = sw.getBuffer();
			output = sb.toString();
			this.errorMail(applicationid, null);
			throw new DaoException(e.getMessage());

		}
		daoAppDetails.setsKey(sKey);
		ApplicationDetail appDetails = daoAppDetails.findByPrimaryKey(applicationid, gatewayid, paymentmodeid, "");

		if (appDetails == null)
		{
			log.error("[" + this.getClass().getName() + "::getApplicationDetail] no applicationdetal found for appid:"
					+ applicationid + " gatewayid:" + gatewayid + " paymentmodeid:" + paymentmodeid);
			output = "[" + this.getClass().getName() + "::getApplicationDetail] no applicationdetal found for appid:"
					+ applicationid + " gatewayid:" + gatewayid + " paymentmodeid:" + paymentmodeid;
			this.errorMail(applicationid, null);
			throw new DaoException("[" + this.getClass().getName() + "::getApplicationDetail] no applicationdetal found for appid:"
					+ applicationid + " gatewayid:" + gatewayid + " paymentmodeid:" + paymentmodeid);

		}
		log.debug("[" + this.getClass().getName() + "::getApplicationDetail] END");
		return appDetails;
	}

	public Application getApplication(String applicationid) throws DaoException, MissingParameterException
	{
		log.debug("[" + this.getClass().getName() + "::getApplication] BEGIN");
		if (applicationid == null || applicationid.trim().equals(""))
		{
			this.output = "[" + this.getClass().getName() + "::getApplication] Parametro applicationid obbligatorio";
			log.error("[" + this.getClass().getName() + "::getApplication] Parametro applicationid obbligatorio");
			this.errorMail(null, null);
			CheckFaultBean faultInfo = new CheckFaultBean();
			// faultInfo.setCode("MissingParameterException");
			// faultInfo.setFaultCode("MissingParameterException");
			faultInfo.setMessage("Parametro appId obbligatorio faultinfo message");
			// faultInfo.setFaultCode("Parametro appId obbligatorio faultinfo code");
			// faultInfo.setDetail("faultinfo error detail");
			// faultInfo.setFaultDetail("fualtinfo detail");
			MissingParameterException mex = new MissingParameterException("Parametro applicationid obbligatorio.");// ,faultInfo);
			// log.debug("[" + this.getClass().getName() +
			// "::getApplication] mex "+mex.getCause());
			// log.debug("[" + this.getClass().getName() +
			// "::getApplication] mex "+mex.getFaultInfo());

			throw mex;
		}
		log.debug("[" + this.getClass().getName() + "::getApplication] appid:" + applicationid);
		ApplicationDao daoAppDetails = DaoFactory.createApplicationDao();
		List<Application> appDetails = daoAppDetails.findWhereIdEquals(applicationid, "");

		if (appDetails == null || appDetails.size() == 0)
		{
			log.error("[" + this.getClass().getName() + "::getApplication] no application found for appid:" + applicationid);
			this.output = "[" + this.getClass().getName() + "::getApplication] no application found for appid:" + applicationid;
			this.errorMail(null, null);
			throw new DaoException("[" + this.getClass().getName() + "::getApplicationDetail] no applicationfound for appid:"
					+ applicationid);

		}
		log.debug("[" + this.getClass().getName() + "::getApplication] END");
		return appDetails.get(0);
	}

	public TransazioneExtraAttribute[] getTransazioneExtraAttributes(String transaction_id) throws DaoException, NamingException,
			CreateException
	{
		log.debug("[" + this.getClass().getName() + "::getTransazioneExtraAttributes] BEGIN");
		TransazioneExtraAttributeDao daoTransazioneExtraAttributes = DaoFactory.createTransazioneExtraAttributeDao();
		List<TransazioneExtraAttribute> transEaList = daoTransazioneExtraAttributes
				.findWhereTransactionIdEquals(transaction_id, "");

		/*
		 * if (appDetails == null) { log.error("[" + this.getClass().getName() +
		 * "::getTransazioneExtraAttributes] no applicationdetal found for appid:"
		 * + applicationid + " gatewayid:" + gatewayid + " paymentmodeid:" +
		 * paymentmodeid); throw new RemoteException("[" +
		 * this.getClass().getName() +
		 * "::getApplicationDetail] no applicationdetal found for appid:" +
		 * applicationid + " gatewayid:" + gatewayid + " paymentmodeid:" +
		 * paymentmodeid);
		 * 
		 * }
		 */
		
		ElementoMultiVersamentoDao daoMulti = DaoFactory.createElementoMultiversamentoDao();
		ElementoMultiversamento filtro = new ElementoMultiversamento();
		filtro.setTransactionId(transaction_id);
		List<ElementoMultiversamento> elenco = daoMulti.find(filtro);
		if (CollectionUtils.isNotEmpty(elenco) && elenco.size() ==1) {
			TransazioneExtraAttribute multivers = new TransazioneExtraAttribute(-1, CostantiNodoSpc.TEA_MULTIVERSAMENTO, transaction_id, elenco.get(0).getXmlElemento());
			transEaList.add(multivers);
		}
		
		TransazioneExtraAttribute[] arTea = new TransazioneExtraAttribute[0];
		if (transEaList != null && transEaList.size() > 0)
		{
			arTea = new TransazioneExtraAttribute[transEaList.size()];
			for (int i = 0; i < arTea.length; i++)
			{
				arTea[i] = transEaList.get(i);
			}
		}
		
		
		
		log.debug("[" + this.getClass().getName() + "::getTransazioneExtraAttributes] END");

		return arTea;
	}

	private void sendMail(String receiver, String bodyText, String subject) throws Exception
	{
		log.debug("[" + this.getClass().getName() + "::sendMail] BEGIN");
		log.debug("[" + this.getClass().getName() + "::sendMail] receiver:" + receiver);
		log.debug("[" + this.getClass().getName() + "::sendMail] bodyText:" + bodyText);
		Session sessionMail = null;
		Properties props = new Properties();
		props.put(Constants.SERVER_SMTP, (String) envProps.get(Constants.SERVER_SMTP));
		props.put(Constants.SERVER_SMTP_PORT, (String) envProps.get(Constants.SERVER_SMTP_PORT));
		log.debug("[" + this.getClass().getName() + "::sendMail] mailserver:" + (String) envProps.get(Constants.SERVER_SMTP) + ":"
				+ (String) envProps.get(Constants.SERVER_SMTP_PORT));
		MailServletAuthenticator authenticator = new MailServletAuthenticator((String) envProps.get("userSMTP"), (String) envProps
				.get("userPasswdSMTP"));
		if (((String) envProps.get(Constants.SERVER_SMTP_AUTH)).equalsIgnoreCase("true"))
		{
			sessionMail = Session.getInstance(props, authenticator);
		} else
		{
			sessionMail = Session.getInstance(props);
		}
		sessionMail = Session.getInstance(props, authenticator);
		MimeMessage message = new MimeMessage(sessionMail);
		message.setSentDate(new Date());
		message.setSubject(subject);
		message.setText(bodyText);
		if (envProps.get("MDPSender") == null)
		{
			output = "Errore di sistema. Manca parametro MDPSender in file env.properties.";
			this.errorMail(null, null);
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

	/*public boolean testResources() throws it.csi.csi.wrapper.SystemException
	{
		log.info("[" + this.getClass().getName() + ": BEGIN");
		boolean stato = true;

		try
		{
			LanguageDao daotest = DaoFactory.createLanguageDao();
			@SuppressWarnings("unused")
			List<Language> ll = daotest.findAll();
		} catch (java.lang.Exception ex)
		{
			stato = false;
			ex.printStackTrace(pw);
			sb = sw.getBuffer();
			output = sb.toString();
			log.error("[" + this.getClass().getName() + "::testResources] error: " + output);
			this.errorMail(null, null);
			throw new it.csi.csi.wrapper.SystemException("IL DATABASE DEL SERVIZIO 'MDPCORESRV' E' INATTIVO: ", ex);
		} finally
		{
			log.info("[" + this.getClass().getName() + ": END");
		}
		return stato;
	}

	public boolean isAlive()
	{
		return true;
	}
*/
	private void errorMail(String appId, Transazione transaction)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StringBuffer bodyText = new StringBuffer("Errore di sistema\r\n");
		GregorianCalendar gc = new GregorianCalendar();
		bodyText.append("Data e ora:" + sdf.format(gc.getTime()) + "\r\n");

		

		if (transaction != null)
		{
			MdpErroriDao errdao = DaoFactory.createMdpErroriDao();
			MdpErrori errdto = new MdpErrori();
			errdto.setApplicationId(appId);
			errdto.setDescrizione(StringUtils.substring(this.output, 0, 100000));// Evitiamo eccezioni per sforamento DB
			Calendar objCal = Calendar.getInstance();
			errdto.setData(new java.sql.Date(objCal.getTimeInMillis()));
			bodyText.append("Id Applicazione:" + transaction.getApplicationId() + "\r\n");
			bodyText.append("Id Transazione:" + transaction.getTransactionId() + "\r\n");
			errdto.setTransactionId(transaction.getTransactionId());
			try {
				errdao.insert(errdto);
			} catch (Exception e) {
				LoggerUtil.error("IMPOSSIBILE REGISTRARE ERRORE: ", e);
				LoggerUtil.dumpObject("ERRORE DA REGISTRARE: ", errdto);
			}
		}
		
		bodyText.append("Dettagli errore:\r\n");
		bodyText.append(this.output);
		try
		{
			this.sendMail(envProps.getProperty("MDPMailbox"), bodyText.toString(), envProps.getProperty("SubjectSystemError"));
		} catch (Exception e2)
		{
			LoggerUtil.error("ERRORE IN INVIO EMAIL", e2);
		}

	}
	
	public void errorMail(String appId, Transazione transaction, String body)
	{
		this.output = body;
		errorMail(appId, transaction);
	}

	public void setTransazione(Transazione t) throws DaoException
	{
		log.debug("[CorePaymentBean::setTransazione] start");

		try
		{
			StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
			stopWatch.start();

			TransazioneDao daoTransaction = DaoFactory.createTransazioneDao();
			TransazionePk pk = new TransazionePk(t.getTransactionId());
			daoTransaction.update(pk, t);
		} catch (Exception e1)
		{
			e1.printStackTrace(pw);
			sb = sw.getBuffer();
			output = sb.toString();
			log.error("[" + this.getClass().getName() + "::startTransazione] error: " + output);
			// StringBuffer bodyText = new
			// StringBuffer("Errore di sistema\r\n");
			// if (t != null)
			// {
			// bodyText.append("Id Transazione:" + t.getTransactionId() +
			// "\r\n");
			// }
			//
			// bodyText.append("Dettagli errore:\r\n");
			// bodyText.append(output);
			// try
			// {
			// this.sendMail(envProps.getProperty("MDPMailbox"),
			// bodyText.toString(), envProps.getProperty("SubjectSystemError"));
			// } catch (Exception e2)
			// {
			// // TODO Auto-generated catch block
			// e2.printStackTrace();
			// }
			this.errorMail(t.getApplicationId(), t);
			throw new DaoException(output);
		}

	}

	public Transazione getTransazioneByPaymentId(String paymentid) throws DaoException
	{
		log.debug("[CorePaymentBean::getTransazioneByPaymentId] BEGIN");
		StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
		stopWatch.start();

		TransazioneDao daoTransaction = DaoFactory.createTransazioneDao();
		// TransazionePk pk = new TransazionePk(transactionId);
		List<Transazione> ltransaction;
		try
		{
			log.debug("[CorePaymentBean::getTransazioneByPaymentId] paymentid:" + paymentid);
			ltransaction = daoTransaction.findWherePaymentidEquals(paymentid, "");
		} catch (TransazioneDaoException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace(pw);
			sb = sw.getBuffer();
			output = sb.toString();
			log.error("[" + this.getClass().getName() + "::startTransazione] " + output);
			this.errorMail(null, null);
			throw new DaoException(e.getMessage());
		}
		if (ltransaction == null || ltransaction.size() == 0)
		{
			output = "Nessuna transazione presente con paymentid=" + paymentid;
			this.errorMail(null, null);
			throw new DaoException("Nessuna transazione presente con paymentid=" + paymentid);

		}
		Transazione transaction = ltransaction.get(0);
		// Context initCtx = new InitialContext();

		// Gateway gt = null;
		//
		// try
		// {
		// GatewayDao gtdao = DaoFactory.createGatewayDao();
		// gt = gtdao.findByPrimaryKey(transaction.getGatewayId());
		// } catch (GatewayDaoException ex)
		// {
		// log.error("[" + this.getClass().getName() + "::getTransazione] " +
		// ex.getMessage());
		// throw new RemoteException("[" + this.getClass().getName() +
		// "::getTransazione] " + ex.getMessage());
		// }

		log.debug("[CorePaymentBean::getTransazioneByPaymentId] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("CorePaymentBean", "getTransazioneByPaymentId()", "chiamata getTransazioneByPaymentId",
				"getTransazioneByPaymentId");

		return transaction;
	}

	public Transazione getTransazioneByAuthorNumber(String authorNumber) throws RemoteException,DaoException
	{
		log.debug("[CorePaymentBean::getTransazioneByAuthorNumber] BEGIN");
		StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
		stopWatch.start();

		TransazioneDao daoTransaction = DaoFactory.createTransazioneDao();
		// TransazionePk pk = new TransazionePk(transactionId);
		List<Transazione> ltransaction;
		try
		{
			log.debug("[CorePaymentBean::getTransazioneByAuthorNumber] authorNumber:" + authorNumber);
			ltransaction = daoTransaction.findWhereAuthorNumberEquals(authorNumber, null);
		} catch (TransazioneDaoException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace(pw);
			sb = sw.getBuffer();
			output = sb.toString();
			log.error("[" + this.getClass().getName() + "::startTransazione] " + output);
			this.errorMail(null, null);
			throw new DaoException(e.getMessage());
		}
		if (ltransaction == null || ltransaction.size() == 0)
		{
			output = "Nessuna transazione presente con authorNumber=" + authorNumber;
			this.errorMail(null, null);
			throw new DaoException("Nessuna transazione presente con authorNumber=" + authorNumber);

		}
		Transazione transaction = ltransaction.get(0);
		// Context initCtx = new InitialContext();

		// Gateway gt = null;
		//
		// try
		// {
		// GatewayDao gtdao = DaoFactory.createGatewayDao();
		// gt = gtdao.findByPrimaryKey(transaction.getGatewayId());
		// } catch (GatewayDaoException ex)
		// {
		// log.error("[" + this.getClass().getName() + "::getTransazione] " +
		// ex.getMessage());
		// throw new RemoteException("[" + this.getClass().getName() +
		// "::getTransazione] " + ex.getMessage());
		// }

		log.debug("[CorePaymentBean::getTransazioneByAuthorNumber] END");

		stopWatch.stop();
		stopWatch.dumpElapsed("CorePaymentBean", "getTransazioneByAuthorNumber()", "chiamata getTransazioneByAuthorNumber",
				"getTransazioneByAuthorNumber");

		return transaction;
	}
	
	public PartAnComune getComuneProvincia(String istatComune) throws DaoException
	{
		PartAnComuneDao pdao = DaoFactory.createPartAnComuneDao();

		List<PartAnComune> lcomune = pdao.findWhereIstatComuneEquals(istatComune);
		if (lcomune == null || lcomune.size() == 0)
		{
			output = "Nessun comune trovato con codice istat:" + istatComune;
			this.errorMail(null, null);
			throw new DaoException("Nessun comune trovato con codice istat:" + istatComune);
		}
		PartAnComune pcomune = lcomune.get(0);
		return pcomune;

	}

	public Vapplicationcomuni getPagonetCustomData(String applicationId, String gatewayId) throws DaoException
	{
		VapplicationcomuniDao pdao = DaoFactory.createVapplicationcomuniDao();

		List<Vapplicationcomuni> lcomune = pdao.findWhereAppIdandGwIdEquals(applicationId, gatewayId);
		if (lcomune == null || lcomune.size() == 0)
		{
			output = "Nessun comune trovato con applicationID:" + applicationId + " e gatewayId:" + gatewayId;
			this.errorMail(null, null);
			throw new DaoException("Nessun comune trovato con applicationID:" + applicationId + " e gatewayId:" + gatewayId);
		}
		Vapplicationcomuni pcomune = lcomune.get(0);
		return pcomune;
	}

	public long getIdOpXpay() throws DaoException
	{
		long l = -1;
		ResultSet rs = null;
		Statement st = null;
		Connection c = null;
		try
		{
			c = ConnectionFactory.getConnection();
			st = c.createStatement();
			rs = st.executeQuery("select nextval('seq_idopxpay')");
			rs.next();
			l = rs.getLong(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LoggerUtil.error("ERRORE DURANTE LA GENERAZIONE DELL'ID TRANSAZIONE PER XPAY", e);
			e.printStackTrace(pw);
			sb = sw.getBuffer();
			output = "Errore in generazione id per transazione xpay:" + sb.toString();
			this.errorMail(null, null);
			throw new DaoException(e.getMessage());
		} finally {
			DbUtils.closeQuietly(c, st, rs);
		}

		return l;
	}


	public Hashtable<String, String> getDatiPagonet(String tranId) throws RemoteException, DaoException
	{
		log.debug("[CorePaymentBean::getDatiPagonet] BEGIN");
		Hashtable<String, String> htpagonet = new Hashtable<String, String>();

		//HashMap<String, String> ht = new HashMap<String, String>();
		Transazione t = null;
		try
		{
			t = this.getTransazione(tranId);
		} catch (Exception e)
		{
			
			e.printStackTrace(pw);
			sb = sw.getBuffer();
			output = "get dati pagonet errore in ricerca transazione " + tranId + ":" + sb.toString();
			this.errorMail(null, null);
			throw new RemoteException(e.getMessage());
		}
		ApplicationDetail ad = this.getApplicationDetail(t.getApplicationId(), t.getGatewayId(), t.getGatewaypaymodeid());
		Gatewaydetail gd = null;

		try
		{
			GatewaydetailDao gateDet = DaoFactory.createGatewaydetailDao();
			gd = gateDet.findByPrimaryKey(t.getGatewayId(), t.getGatewaypaymodeid());
			if (gd == null)
			{
				log.error("[" + this.getClass().getName() + "::getDatiPagonet] no GatewaydetailDao found per appid="
						+ t.getApplicationId() + " gatewayid=" + t.getGatewayId() + " e paymentmodeid=" + t.getGatewaypaymodeid());
				this.output = "[" + this.getClass().getName() + "::getDatiPagonet] no GatewaydetailDao found per appid="
						+ t.getApplicationId() + " gatewayid=" + t.getGatewayId() + " e paymentmodeid=" + t.getGatewaypaymodeid();
				this.errorMail(t.getApplicationId(), t);
				throw new DaoException("[" + this.getClass().getName() + "::getDatiPagonet] no GatewaydetailDao found per appid="
						+ t.getApplicationId() + " gatewayid=" + t.getGatewayId() + " e paymentmodeid=" + t.getGatewaypaymodeid());
			}
		} catch (GatewaydetailDaoException ex)
		{
			ex.printStackTrace(pw);
			sb = sw.getBuffer();
			output = sb.toString();
			log.error("[" + this.getClass().getName() + "::getDatiPagonet] " + output);
			this.errorMail(t.getApplicationId(), t);
			throw new DaoException("[" + this.getClass().getName() + "::getDatiPagonet] " + ex.getMessage());
		}

		DecimalFormat df = new DecimalFormat("#################0");

		InitialContext ctx = null;
		try
		{
			ctx = new InitialContext();
		} catch (NamingException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace(pw);
			sb = sw.getBuffer();
			output = sb.toString();
			log.error("[" + this.getClass().getName() + "::getDatiPagonet] " + output);
			this.errorMail(t.getApplicationId(), t);

			throw new RemoteException(e1.getMessage());
		}

		// ctx = (Context)ctx.lookup("java:comp/env");
		PEAS peas = null;
		//Payment p = null;
		Basket basket = null;
		
		try
		{
			Object o = ctx.lookup("peas/PEAS");
			PEASHome peasHome = (PEASHome) PortableRemoteObject.narrow(o, PEASHome.class);
			peas = peasHome.create();

			basket = peas.getBasket(t.getBasketId());
			log.debug("[CorePaymentBean::getDatiPagonet] basket:" + basket.toString());
		} catch (Exception e1)
		{
			e1.printStackTrace(pw);
			sb = sw.getBuffer();
			output = sb.toString();
			this.errorMail(t.getApplicationId(), t);
			throw new RemoteException(e1.getMessage());
		}

		if (basket == null)
		{
			output = "getDatiPagonet: Basket peas non valorizzato.";
			this.errorMail(t.getApplicationId(), t);
			throw new RemoteException(output);
		}
		Item[] itemsPeas = basket.getItems();
		if (itemsPeas == null)
		{
			output = "getDatiPagonet: Basket peas vuoto.";
			this.errorMail(t.getApplicationId(), t);
			throw new RemoteException(output);

		}
		ExtraAttribute[] eatts = itemsPeas[0].getExtraAttributes();
		Hashtable<String, String> htextraatts = new Hashtable<String, String>();

		for (int i = 0; i < eatts.length; i++)
		{
			ExtraAttribute ea = eatts[i];
			htextraatts.put(ea.getName(), ea.getValue().trim());
		}
		log.debug("[CorePaymentBean::getDatiPagonet] htextraatts:" + htextraatts.toString());
		Hashtable<String, String> htoptatts = new Hashtable<String, String>();
		it.csi.peas.entity.OptAttr optattr = null;
		try
		{
			optattr = peas.getOptionalAttributes(t.getTransactionId());
			log.debug("[CorePaymentBean::getDatiPagonet] optattr (da peas) per transid=:" + t.getTransactionId() + " :"
					+ optattr.toString());
		} catch (Exception e1)
		{
			e1.printStackTrace(pw);
			sb = sw.getBuffer();
			output = sb.toString();
			this.errorMail(t.getApplicationId(), t);
			throw new RemoteException(e1.getMessage());
		}
		ExtraAttribute[] eopt = null;
		if (optattr != null)
		{
			eopt = optattr.getExtraAttributes();
			for (int i = 0; i < eopt.length; i++)
			{
				ExtraAttribute ea = eopt[i];
				log.debug("[CorePaymentBean::getDatiPagonet] ea:" + ea.getName() + "->" + ea.getValue());
				htoptatts.put(ea.getName(), ea.getValue());
			}
		}
		log.debug("[CorePaymentBean::getDatiPagonet] htoptatts:" + htoptatts.toString());
		StringBuffer url = new StringBuffer(gd.getDefaultpaymenturl());
		url.append("?");
		// String basketId = t.getBasketId();

		// String tipoBollettino = ad.getTipobollettinoposte();

		// //Response.Write("<input type=\"hidden\" name=\"cboComune\" value=\""+codiceImm+"|"+comune+"|"+codCliente+"\">\r\t\t\t");
		String istat = htextraatts.get("codicecomuneistat");
		log.debug("[CorePaymentBean::getDatiPagonet] codicecomuneistat:" + istat);
		log.debug("[CorePaymentBean::getDatiPagonet] appid :" + t.getApplicationId() + " gwid:" + t.getGatewayId());
		PartAnComune pcom = null;
		Vapplicationcomuni vappcomuni = null;
		try
		{
			pcom = this.getComuneProvincia(istat);
			vappcomuni = this.getPagonetCustomData(t.getApplicationId(), t.getGatewayId());
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace(pw);
			sb = sw.getBuffer();
			output = sb.toString();
			this.errorMail(t.getApplicationId(), t);
			throw new RemoteException(e.getMessage());
		}

		String cboComune = vappcomuni.getCodiceimm() + "|" + vappcomuni.getDescComune() + "|" + t.getMerchantId();
		log.debug("[CorePaymentBean::getDatiPagonet] cboCOmune:" + cboComune);

		htpagonet.put("Esercente", t.getMerchantId());
		htpagonet.put("prezzo", df.format(t.getAmount() * 100));
		htpagonet.put("cboProvincia", pcom.getSiglaProv());
		htpagonet.put("cboComune", cboComune);
		htpagonet.put("txtemail", htextraatts.get("emailutente") != null ? htextraatts.get("emailutente") : "");
		htpagonet.put("cboTipoBollettino", (ad.getTipobollettinoposte() != null ? ad.getTipobollettinoposte().trim() : ""));
		htpagonet.put("txtCF", htextraatts.get("codfiscont") != null ? htextraatts.get("codfiscont") : "");
		htpagonet.put("txtCognome", htextraatts.get("cognomecont") != null ? htextraatts.get("cognomecont") : "");
		htpagonet.put("txtNome", htextraatts.get("nomecont") != null ? htextraatts.get("nomecont") : "");
		htpagonet.put("txtDomicilio", htextraatts.get("comunecont") != null ? htextraatts.get("comunecont") : "");
		htpagonet.put("txtCAP", htoptatts.get("capreg") != null ? htoptatts.get("capreg") : "");
		htpagonet.put("txtIndirizzo", htextraatts.get("indirizzocont"));
		htpagonet.put("txtNroFabbricati", htextraatts.get("numfabb") != null ? htextraatts.get("numfabb") : "");
		htpagonet.put("txtImpTerreni", htextraatts.get("terreni") != null ? df.format(Double
				.parseDouble(htextraatts.get("terreni")) * 100) : "000");
		htpagonet.put("txtImpAree", htextraatts.get("areefab") != null ? df
				.format(Double.parseDouble(htextraatts.get("areefab")) * 100) : "000");
		htpagonet.put("txtImpAbitazione", htextraatts.get("abitprinc") != null ? df.format(Double.parseDouble(htextraatts
				.get("abitprinc")) * 100) : "000");
		htpagonet.put("txtImpAltri", htextraatts.get("altri") != null ? df
				.format(Double.parseDouble(htextraatts.get("altri")) * 100) : "000");
		htpagonet.put("txtImpDetrazione", htextraatts.get("detrazione") != null ? df.format(Double.parseDouble(htextraatts
				.get("detrazione")) * 100) : "");
		htpagonet.put("txtImpDetrazionestatale", htextraatts.get("detrazionestatale") != null ? df.format(Double
				.parseDouble(htextraatts.get("detrazionestatale")) * 100) : "000");
		String tipoPag = (htextraatts.get("cboTipoPagamento") != null) ? htextraatts.get("cboTipoPagamento").toLowerCase() : "";
		String valoreTipoPag = "";
		switch (tipoPag.charAt(0))
		{
		case 'a':
			valoreTipoPag = "acconto";
			break;
		case 's':
			valoreTipoPag = "saldo";
			break;
		case 't':
			valoreTipoPag = "acconto,saldo";
			break;

		}
		htpagonet.put("cboTipoPagamento", valoreTipoPag.trim());
		htpagonet.put("txtAnno", htextraatts.get("anno") != null ? htextraatts.get("anno") : "");

		htpagonet.put("cboRavvedimento", this.getRavvedimento(htextraatts.get("sanzioni"), htextraatts.get("interessi")));

		log.debug("[CorePaymentBean::getDatiPagonet] htpagonet:" + htpagonet.toString());

		log.debug("[CorePaymentBean::getDatiPagonet] END");
		return htpagonet;
	}

	private String getRavvedimento(String _sanzioni, String _interessi)
	{
		if (_sanzioni == null || _sanzioni.trim().equals(""))
			_sanzioni = "0";
		if (_interessi == null || _interessi.trim().equals(""))
			_interessi = "0";

		if (Double.parseDouble(_sanzioni) > 0 || Double.parseDouble(_interessi) > 0)
			return "true";
		else
			return "false";
	}
	
	public Properties getConfig ()
	{
		return this.envProps;
	}
}
