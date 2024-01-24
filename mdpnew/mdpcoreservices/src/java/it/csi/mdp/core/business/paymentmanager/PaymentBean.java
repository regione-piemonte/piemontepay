/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.paymentmanager;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EJBHome;
import javax.ejb.EJBObject;
import javax.ejb.Handle;
import javax.ejb.RemoveException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import it.csi.mdp.core.business.dto.Application;
import it.csi.mdp.core.business.dto.ApplicationDetail;
import it.csi.mdp.core.business.dto.Applicationcustomfields;
import it.csi.mdp.core.business.dto.ChiaveValore;
import it.csi.mdp.core.business.dto.DettaglioFruitore;
import it.csi.mdp.core.business.dto.ER;
import it.csi.mdp.core.business.dto.ElementoMultiversamento;
import it.csi.mdp.core.business.dto.GiornaleEvento;
import it.csi.mdp.core.business.dto.IbanEnteApplication;
import it.csi.mdp.core.business.dto.InformativePSP;
import it.csi.mdp.core.business.dto.InformativePSPScelto;
import it.csi.mdp.core.business.dto.IuvOtticoFruitore;
import it.csi.mdp.core.business.dto.NodoErrore;
import it.csi.mdp.core.business.dto.ParametriUrlWisp;
import it.csi.mdp.core.business.dto.ParametroWisp;
import it.csi.mdp.core.business.dto.PartAnComune;
import it.csi.mdp.core.business.dto.RPT;
import it.csi.mdp.core.business.dto.RR;
import it.csi.mdp.core.business.dto.RT;
import it.csi.mdp.core.business.dto.RicevutaTelematicaNodo;
import it.csi.mdp.core.business.dto.Transazione;
import it.csi.mdp.core.business.dto.TransazioneExtraAttribute;
import it.csi.mdp.core.business.dto.Vapplicationcomuni;
import it.csi.mdp.core.business.dto.multicarrello.DatiMarcaBolloDigitale;
import it.csi.mdp.core.business.dto.multicarrello.DatiSingoloVersamentoRPT;
import it.csi.mdp.core.business.dto.multicarrello.RPTData;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.paymentmanager.bean.CorePaymentBean;
import it.csi.mdp.core.business.paymentmanager.bean.NodoSpcBean;
import it.csi.mdp.core.business.paymentmanager.local.AppGateway;
import it.csi.mdp.core.business.paymentmanager.local.AppGatewayInformativa;
import it.csi.mdp.core.business.util.StringUtil;
import it.csi.mdp.core.interfacecxf.MissingParameterException;
import it.csi.mdp.core.util.Constants;
import it.csi.mdp.core.util.LoggerUtil;
import it.csi.peas.interf.IllegalArgumentException;
import it.csi.util.performance.StopWatch;

/**
 * <p>
 * Implementazione dei servizi esposti dall'EJB Test e dell'interfaccia
 * SessioneBean.
 * </p>
 *
 * <p>
 * BEST PRACTICE: sarebbe pi&ugrave; indicato delegare l'implementazione della
 * logica di business a un livello successivo del BackEnd, in modo da separare
 * nettamente la logica di accesso (EJB) da quella di business. Questa
 * operazione pu&ograve; essere compiuta definendo un'interfaccia di logica e la
 * implemtazione, inizializzando quest'ultima come bean di Spring e richimandola
 * nel metodo (servizio) per mezzo del ServiceBeanLocator.
 * </p>
 *
 * @version 1.0, &nbsp; 15-APR-2008
 * @since SDK1.5
 */

public class PaymentBean implements SessionBean
{
    /**
     *
     */
    private static final long serialVersionUID = 3145996062384750276L;

    protected static Logger log = Logger.getLogger(it.csi.mdp.core.util.Constants.APPLICATION_CODE);

    @SuppressWarnings("unused")
    private SessionContext context;

    // private BeanFactoryReference beanContext= null;

    public PaymentBean()
    {
        log.debug("[PaymentBean::PaymentBean] istanziato PaymentBean");
    }

    @Override
    public void ejbActivate() throws EJBException, RemoteException
    {
    }

    @Override
    public void ejbPassivate() throws EJBException, RemoteException
    {
    }

    @Override
    public void ejbRemove() throws EJBException, RemoteException
    {
    }

    /**
     * Set the associated session context. The container calls this method after
     * the instance creation.
     *
     * The enterprise bean instance should store the reference to the context
     * object in an instance variable.
     *
     * This method is called with no transaction context.
     *
     * @throws EJBException
     *             Thrown if method fails due to system-level error.
     */
    @Override
    public void setSessionContext(SessionContext newContext) throws EJBException
    {
        context = newContext;
    }

    /** Persist the newInstance object into database */
    /*
     * public Integer createNew(ApplicationDetail newPojoTest) throws
     * RemoteException { log.info("esecuione TestBean: init bean testDAO");
     *
     * ApplicationDetailDaoImpl
     * testDAO=(ApplicationDetailDaoImpl)ServiceBeanLocator
     * .getBeanByName("transazioneDAO"); //PojoTest p = new PojoTest("Per", 90);
     * //return testDAO.create(p); return null; }
     */

    /**
     * Retrieve an object that was previously persisted to the database using
     * the indicated id as primary key
     *
     * @throws ApplicationDetailDaoException
     */
    /*
     * public Application readOne(String id) throws RemoteException,
     * ApplicationDaoException { log.debug("[PaymentBean::readOne] BEGIN");
     * StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
     * stopWatch.start();
     *
     * ApplicationDao dao = DaoFactory.createApplicationDao(); List<Application>
     * _result = dao.findWhereIdEquals(id);
     *
     * log.debug("dao OK");
     *
     * stopWatch.stop(); stopWatch.dumpElapsed("PaymentBean", "readOne()",
     * "chiamata DAO", "ricerca");
     *
     * return _result.get(0);
     *
     * }
     */
    /** Retrieve all objects that was previously persisted to the database */
    /*
     * public List readAll() throws RemoteException { return null; }
     */

    /** Save changes made to a persistent object. */
    /*
     * public void updateOne(Application transientObject) throws RemoteException
     * {}
     */

    /** Remove an object from persistent storage in the database */
    /*
     * public void deleteOne(Application persistentObject)
     *
     * throws RemoteException {}
     */
    public EJBHome getEJBHome() throws RemoteException
    {
        return null;
    }

    public Handle getHandle() throws RemoteException
    {
        return null;
    }

    public Object getPrimaryKey() throws RemoteException
    {
        return null;
    }

    public boolean isIdentical(EJBObject arg0) throws RemoteException
    {
        return false;
    }

    public void remove() throws RemoteException, RemoveException
    {
    }

    public String recuperaApplicationIdFromIuv(String iuv) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException{
        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        String appid = nodoBean.recuperaApplicationIdFromIuv ( iuv );

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "recuperaApplicationCustomFields()", "chiamata recuperaApplicationCustomFields", "recuperaApplicationCustomFields");

        return appid;
    }

    /************
     * metodi pagamento esposti
     *
     * @throws CreateException
     * @throws NamingException
     **********/

    public List<Applicationcustomfields> recuperaApplicationCustomFieldDecoded(String applicationId, String fieldName) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException{
        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        List<Applicationcustomfields> customFields = null;

        try {
            customFields = nodoBean.recuperaApplicationCustomFieldDecoded ( applicationId, fieldName );
        } catch ( Exception e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "recuperaApplicationCustomFieldDecoded()", "chiamata recuperaApplicationCustomFields", "recuperaApplicationCustomFields");

        return customFields;
    }

    public List<Applicationcustomfields> recuperaApplicationCustomFields(String applicationId, String fieldName) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException{
        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        List<Applicationcustomfields> customFields = nodoBean.recuperaApplicationCustomFields ( applicationId, fieldName );

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "recuperaApplicationCustomFields()", "chiamata recuperaApplicationCustomFields", "recuperaApplicationCustomFields");

        return customFields;
    }

    public AppGateway[] getModalitaPagamento(Transazione t, String appId) throws RemoteException, DaoException, NamingException,
    CreateException, MissingParameterException
    {
        log.debug("[PaymentBean::getModalitaPagamento] BEGIN");
        if ( !StringUtil.isValidApplicationId ( appId ) ) {
            throw new DaoException ( "Parametro appId non valido" );
        }
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        CorePaymentBean cPayment = (CorePaymentBean) bf.getBean("corePaymentBean");

        AppGateway[] arrAppGateway = cPayment.getModalitaPagamento(t, appId);

        log.debug("[PaymentBean::getModalitaPagamento] END");
        return arrAppGateway;
    }

    /**
     * Informative
     * @param t
     * @param appId
     * @return
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public AppGatewayInformativa[] getModalitaInformativePagamento(Transazione t, String appId) throws RemoteException, DaoException, NamingException,
    CreateException, MissingParameterException, IllegalAccessException, InvocationTargetException
    {
        log.debug("[PaymentBean::getModalitaInformativePagamento] BEGIN");
        if ( !StringUtil.isValidApplicationId ( appId ) ) {
            throw new DaoException ( "Parametro appId non valido" );
        }
        List<AppGatewayInformativa> listAppGwInformativa = new ArrayList<AppGatewayInformativa>();
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        CorePaymentBean cPayment = (CorePaymentBean) bf.getBean("corePaymentBean");

        AppGateway[] arrAppGateway = cPayment.getModalitaPagamento(t, appId);

        if (arrAppGateway != null) {
            for (AppGateway el : arrAppGateway) {
                AppGatewayInformativa info = new AppGatewayInformativa();
                LoggerUtil.dump(info);
                if (el != null) {
                    BeanUtils.copyProperties(info, el);
                    listAppGwInformativa.add(info);
                }
            }
        }
        log.debug("[PaymentBean::getModalitaInformativePagamento] finito reperimento delle vecchie modalita di pagamento, reperisco quelle del nodo");
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");
        List<AppGatewayInformativa> info = nodoBean.getElencoPSPNodo(t, appId);
        if (info != null && info.size() > 0)
            listAppGwInformativa.addAll(info);
        log.debug("[PaymentBean::getModalitaInformativePagamento] Reperite modalita di pagamento del nodo, stampo il complessivo");

        LoggerUtil.dump(listAppGwInformativa);

        log.debug("[PaymentBean::getModalitaPagamento] END");
        return listAppGwInformativa.toArray(new AppGatewayInformativa[0]);
    }

    public Transazione initTransazione(String appId, String basketId) throws RemoteException, DaoException, NamingException,
    CreateException, DatatypeConfigurationException, MissingParameterException
    {
        log.debug("[PaymentBean::initTransazione] BEGIN");
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();
        Transazione t = null;
        if ( !StringUtil.isValidApplicationId ( appId ) ) {
            throw new DaoException ( "Parametro appId non valido" );
        }
        if ( StringUtils.isNotBlank ( basketId ) && !StringUtils.isAlphanumeric ( basketId ) ) {
            throw new DaoException ( "Parametro basketId non valido" );
        }
        try {
            BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
            CorePaymentBean cPayment = (CorePaymentBean) bf.getBean("corePaymentBean");

            t = cPayment.initTransazione(appId, basketId);
        } catch (Exception e) {
            LoggerUtil.error("ERRORE GENERICO", e);
            throw new DaoException(e.getMessage(), e);
        }

        log.debug("[PaymentBean::initTransazione] END");

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "initTransazione()", "chiamata initTransazione", "initTransazione");

        return t;
    }

    public String startTransazione(Transazione transaction, TransazioneExtraAttribute[] tea, List<RPTData> elencoRPT, boolean multiBeneficiario) throws RemoteException, DaoException,
    NamingException, CreateException, MissingParameterException
    {
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();
        log.debug("[PaymentBean::startTransazione] BEGIN");
        log.debug("[PaymentBean::startTransazione] multiBeneficiario: " + multiBeneficiario);
        String urlToCall = null;
        try {

            BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
            CorePaymentBean cPayment = (CorePaymentBean) bf.getBean("corePaymentBean");
            Set<String> elencoIuvTrovati = new HashSet<String>();
            BigDecimal importoTotale = BigDecimal.ZERO;

            //LF 3/4/2019 - MDPNEW-76 Controllo cifre dopo la virgola - inizio
            String appId = "ERR_IMPORTO_2_CIFRE";
            String errorString = "";
            String cfUltimoPagatore = "";
            if (elencoRPT != null) {
                for (RPTData elemento : elencoRPT) {
                    if (elemento.getDatiVersamento() != null) {

                        BigDecimal importoTotaleDaVersare = elemento.getDatiVersamento().getImportoTotaleDaVersare();

                        if (null != importoTotaleDaVersare && importoTotaleDaVersare.scale() > 2) {
                            errorString = String.format("Importo totale da versare [%s] relativo allo IUV %s non valido. Ricevute %d cifre dopo la virgola (massimo = 2)", importoTotaleDaVersare.toString(), elemento.getDatiVersamento().getIdentificativoUnivocoVersamento(), importoTotaleDaVersare.scale());

                            if (null != transaction) {
                                appId = transaction.getApplicationId();
                            }

                            cPayment.errorMail(appId, transaction, errorString);
                            throw new IllegalArgumentException(errorString);
                        }

                        if (importoTotaleDaVersare != null && importoTotaleDaVersare.scale() < 2) {
                            elemento.getDatiVersamento().setImportoTotaleDaVersare(importoTotaleDaVersare.setScale(2));
                            log.warn(String.format("Importo totale da versare [%s] relativo allo IUV %s non valido. Ricevute %d cifre dopo la virgola (minimo = 2). Impostato scale a 2 cifre", importoTotaleDaVersare.toString(), elemento.getDatiVersamento().getIdentificativoUnivocoVersamento(), importoTotaleDaVersare.scale()));
                        }

                        for (DatiSingoloVersamentoRPT datiSingoloVersamentoRPT : elemento.getDatiVersamento().getDatiSingoloVersamento()) {

                            BigDecimal importoSingoloVersamento = datiSingoloVersamentoRPT.getImportoSingoloVersamento();

                            if (null != importoSingoloVersamento && importoSingoloVersamento.scale() > 2) {

                                if (null != transaction) {
                                    appId = transaction.getApplicationId();
                                }

                                cPayment.errorMail(appId, transaction, errorString);

                                throw new IllegalArgumentException(String.format("Importo singolo versamento [%s] relativo alla causale %s non valido. Ricevute %d cifre dopo la virgola (massimo = 2)", importoSingoloVersamento.toString(), datiSingoloVersamentoRPT.getCausaleVersamento(), importoSingoloVersamento.scale()));
                            }

                            if (importoSingoloVersamento != null && importoSingoloVersamento.scale() < 2) {
                                datiSingoloVersamentoRPT.setImportoSingoloVersamento(importoSingoloVersamento.setScale(2));
                                log.warn(String.format("Importo totale da versare [%s] relativo alla causale %s non valido. Ricevute %d cifre dopo la virgola (minimo = 2). Impostato scale a 2 cifre", importoSingoloVersamento.toString(), datiSingoloVersamentoRPT.getCausaleVersamento(), importoSingoloVersamento.scale()));
                            }

                        }

                        importoTotale = importoTotale.add(importoTotaleDaVersare);
                        //RDI-45 MULTI BENEFICIARIO. Controllo sugli applicationId
                        if(multiBeneficiario) {
                            if (null != transaction) {
                                appId = transaction.getApplicationId();
                            }
                            if(elencoIuvTrovati.isEmpty()) {
                                //Siamo nel primo elemento della lista
                                if(!elemento.getApplicationId().equalsIgnoreCase(appId)) {
                                    throw new MissingParameterException("StartTransazioneCarrello con pagamento multi-beneficiario: elenco RPT non conforme. applicationId testata non coincidente con applicationId prima RPT");
                                }
                            } else {
                                //Siamo nell'elemento successivo
                                if(elemento.getApplicationId().equalsIgnoreCase(appId)) {
                                    throw new MissingParameterException("StartTransazioneCarrello con pagamento multi-beneficiario: elenco RPT non conforme. RPT con stesso applicationId");
                                }
                            }
                        }
                        //RDI-45 MULTI BENEFICIARIO. Se multibeneficiario gli iuv devono essere gli stessi.
                        if(!multiBeneficiario) {
                            if (elencoIuvTrovati.contains(elemento.getDatiVersamento().getIdentificativoUnivocoVersamento()))
                                throw new MissingParameterException("Lo iuv " + elemento.getDatiVersamento().getIdentificativoUnivocoVersamento() + "e' presente in piu' di una RPT");
                            else
                                elencoIuvTrovati.add(elemento.getDatiVersamento().getIdentificativoUnivocoVersamento());
                        } else {
                            if(elencoIuvTrovati.isEmpty()) {
                                elencoIuvTrovati.add(elemento.getDatiVersamento().getIdentificativoUnivocoVersamento());
                            } else {
                                if (!elencoIuvTrovati.contains(elemento.getDatiVersamento().getIdentificativoUnivocoVersamento())) {
                                    throw new MissingParameterException("StartTransazioneCarrello con pagamento multi-beneficiario: elenco RPT non conforme. RPT con differenti IUV");
                                }

                            }
                        }
                        //RDI-45 MULTI BENEFICIARIO. un solo soggetto pagatore. presenza della mail del soggetto medesimo, assenza marca da bollo.
                        if(multiBeneficiario) {
                            if(elemento.getSoggettoPagatore() != null) {
                                if (StringUtils.isBlank ( cfUltimoPagatore)){
                                    cfUltimoPagatore = elemento.getSoggettoPagatore().getIdentificativoUnivocoPagatore ();
                                } else if ( !cfUltimoPagatore.equals ( elemento.getSoggettoPagatore ().getIdentificativoUnivocoPagatore () ) ) {
                                    throw new MissingParameterException("StartTransazioneCarrello con pagamento multi-beneficiario: elenco RPT non conforme. Presenti piu' soggetti pagatori.");
                                }
                                if ( StringUtils.isBlank ( elemento.getSoggettoPagatore ().geteMailPagatore () ) ) {
                                    throw new MissingParameterException (
                                        "StartTransazioneCarrello con pagamento multi-beneficiario: elenco RPT non conforme. E-mail soggetto pagatore non indicata." );
                                }
                            }
                            for(DatiSingoloVersamentoRPT singoloVersamento : elemento.getDatiVersamento().getDatiSingoloVersamento()) {
                                DatiMarcaBolloDigitale marcaBollo = singoloVersamento.getDatiMarcaBolloDigitale();
                                if(marcaBollo != null) {
                                    if((marcaBollo.getHashDocumento() != null && marcaBollo.getHashDocumento().trim().length() > 0) ||
                                                    (marcaBollo.getProvinciaResidenza() != null && marcaBollo.getProvinciaResidenza().trim().length() > 0) ||
                                                    (marcaBollo.getTipoBollo() != null && marcaBollo.getTipoBollo().trim().length() > 0)) {
                                        throw new MissingParameterException("StartTransazioneCarrello con pagamento multi-beneficiario: elenco RPT non conforme. Indicati dati marca da bollo.");
                                    }
                                }
                            }
                        }
                    }
                }

                if (transaction.getAmount() != importoTotale.doubleValue()) {
                    throw new MissingParameterException("L'importo specificato nella transazione: " +transaction.getAmount() + " e' diverso dalla somma degli importi delle singole RPT (pari a " + importoTotale + ")");
                }
            }
            urlToCall = cPayment.startTransazione ( transaction, tea, elencoRPT, multiBeneficiario );
        } catch (Exception e) {
            LoggerUtil.error("ERRORE GENERICO", e);
            throw new DaoException(e.getMessage(), e);
        }
        log.debug("[PaymentBean::startTransazione] END");

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "startTransazione()", "chiamata startTransazione", "startTransazione");

        //LF 3/4/2019 - MDPNEW-76 Controllo cifre dopo la virgola - fine

        return urlToCall;
    }

    public Transazione getStatoTransazione(String transactionId) throws RemoteException, DaoException, NamingException,
    CreateException, MissingParameterException
    {
        log.debug("[PaymentBean::getStatoTransazione] BEGIN");
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();
        if ( !StringUtils.isAlphanumeric ( transactionId ) ) {
            throw new DaoException ( "Parametro transactionId non valido" );
        }
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        CorePaymentBean cPayment = (CorePaymentBean) bf.getBean("corePaymentBean");

        Transazione transaction = cPayment.getStatoTransazione(transactionId);

        log.debug("[PaymentBean::getStatoTransazione] END");

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "getStatoTransazione()", "chiamata getStatoTransazione", "getStatoTransazione");

        return transaction;
    }

    public void setStatoTransazione(String transactionId, long stato, String gwStato) throws RemoteException, DaoException,
    MissingParameterException
    {
        /* aggiornamento db mdp */
        log.debug("[PaymentBean::setStatoTransazione] BEGIN");
        StopWatch stopWatch;
        try
        {
            stopWatch = new StopWatch(Constants.APPLICATION_CODE);
            stopWatch.start();

            BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
            CorePaymentBean cPayment = (CorePaymentBean) bf.getBean("corePaymentBean");

            cPayment.setStatoTransazione(transactionId, stato, gwStato);
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RemoteException(e.getMessage());

        }

        log.debug("[PaymentBean::setStatoTransazione] END");

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "setStatoTransazione()", "chiamata setStatoTransazione", "setStatoTransazione");
    }

    public Applicationcustomfields[] getCustomFields(String applicationid,String gatewayid) throws RemoteException, DaoException,
    MissingParameterException
    {
        log.debug("[PaymentBean::getCustomField] BEGIN");
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        Applicationcustomfields[] acf;
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        CorePaymentBean cPayment = (CorePaymentBean) bf.getBean("corePaymentBean");
        if (StringUtils.isEmpty(gatewayid)) {
            NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");
            gatewayid = nodoBean.getGatewayNodo(applicationid);
        }
        acf = cPayment.getCustomFields(applicationid, gatewayid);

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "getCustomFields()", "chiamata getCustomFields", "getCustomFields");
        log.debug("[PaymentBean::getCustomField] END");
        return acf;
    }

    public ApplicationDetail getApplicationDetail(String applicationid, String gatewayid, String paymentmodeid)
                    throws RemoteException, DaoException, MissingParameterException
    {
        log.debug("[PaymentBean::getApplicationDetail] BEGIN");
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        CorePaymentBean cPayment = (CorePaymentBean) bf.getBean("corePaymentBean");

        ApplicationDetail acf = cPayment.getApplicationDetail(applicationid, gatewayid, paymentmodeid);

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "getApplicationDetail()", "chiamata getApplicationDetail", "getApplicationDetail");
        log.debug("[PaymentBean::getApplicationDetail] END");
        return acf;

    }

    public Application getApplication(String applicationid) throws RemoteException, DaoException, MissingParameterException
    {
        log.debug("[PaymentBean::getApplication] BEGIN");
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();
        if ( !StringUtil.isValidApplicationId ( applicationid ) ) {
            throw new DaoException ( "Parametro appId non valido" );
        }
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        CorePaymentBean cPayment = (CorePaymentBean) bf.getBean("corePaymentBean");

        Application acf = cPayment.getApplication(applicationid);

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "getApplication()", "chiamata getApplicationDetail", "getApplicationDetail");
        log.debug("[PaymentBean::getApplication] END");
        return acf;

    }

    public Transazione getTransazione(String transaction_id) throws RemoteException, DaoException, NamingException,
    CreateException, MissingParameterException
    {
        log.debug("[PaymentBean::getTransazione] BEGIN");
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        CorePaymentBean cPayment = (CorePaymentBean) bf.getBean("corePaymentBean");

        Transazione transaction = cPayment.getTransazione(transaction_id);

        log.debug("[PaymentBean::getTransazione] END");

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "getTransazione()", "chiamata getTransazione", "getTransazione");

        return transaction;
    }

    public void setTransazione(Transazione t) throws RemoteException, DaoException, NamingException, CreateException,
    MissingParameterException
    {
        log.debug("[PaymentBean::setTransazione] BEGIN");
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        CorePaymentBean cPayment = (CorePaymentBean) bf.getBean("corePaymentBean");

        cPayment.setTransazione(t);

        log.debug("[PaymentBean::setTransazione] END");

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "setTransazione()", "chiamata setTransazione", "setTransazione");

        return;
    }

    public TransazioneExtraAttribute[] getTransazioneExtraAttributes(String transaction_id) throws RemoteException, DaoException,
    NamingException, CreateException, MissingParameterException
    {
        log.debug("[PaymentBean::getTransazioneExtraAttributes] BEGIN");
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        CorePaymentBean cPayment = (CorePaymentBean) bf.getBean("corePaymentBean");

        TransazioneExtraAttribute[] transactionea = cPayment.getTransazioneExtraAttributes(transaction_id);

        log.debug("[PaymentBean::getTransazioneExtraAttributes] END");

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "getTransazioneExtraAttributes()", "chiamata getTransazioneExtraAttributes",
                        "getTransazioneExtraAttributes");

        return transactionea;

    }

    public Transazione getTransazioneByPaymentId(String paymentid) throws RemoteException, DaoException, MissingParameterException
    {
        log.debug("[PaymentBean::getTransazioneByPaymentId] BEGIN");
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        CorePaymentBean cPayment = (CorePaymentBean) bf.getBean("corePaymentBean");

        Transazione transaction = cPayment.getTransazioneByPaymentId(paymentid);

        log.debug("[PaymentBean::getTransazioneByPaymentId] END");

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "getTransazioneByPaymentId()", "chiamata getTransazioneByPaymentId",
                        "getTransazioneByPaymentId");

        return transaction;
    }

    public PartAnComune getComuneProvincia(String istatComune) throws RemoteException, DaoException, MissingParameterException
    {
        log.debug("[PaymentBean::getComuneProvincia] BEGIN");
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();
        if ( !StringUtils.isAlphanumeric ( istatComune ) ) {
            throw new DaoException ( "Parametro istatComune non valido" );
        }
        
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        CorePaymentBean cPayment = (CorePaymentBean) bf.getBean("corePaymentBean");

        PartAnComune pcomune = cPayment.getComuneProvincia(istatComune);

        log.debug("[PaymentBean::getComuneProvincia] END");

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "getComuneProvincia()", "chiamata getComuneProvincia", "getComuneProvincia");

        return pcomune;
    }

    public Vapplicationcomuni getPagonetCustomData(String applicationId, String gatewayId) throws RemoteException, DaoException,
    MissingParameterException
    {
        log.debug("[PaymentBean::getPagonetCustomData] BEGIN");
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();
        if ( !StringUtil.isValidApplicationId ( applicationId ) ) {
            throw new DaoException ( "Parametro appId non valido" );
        }
        if ( !StringUtils.isAlphanumeric ( gatewayId ) ) {
            throw new DaoException ( "Parametro gatewayId non valido" );
        }
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        CorePaymentBean cPayment = (CorePaymentBean) bf.getBean("corePaymentBean");

        Vapplicationcomuni pagonetcustomdata = cPayment.getPagonetCustomData(applicationId, gatewayId);

        log.debug("[PaymentBean::getPagonetCustomData] END");

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "getPagonetCustomData()", "chiamata getPagonetCustomData", "getPagonetCustomData");

        return pagonetcustomdata;
    }

    public long getIdOpXpay() throws RemoteException, DaoException
    {
        log.debug("[PaymentBean::getIdOpXpay] BEGIN");
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        CorePaymentBean cPayment = (CorePaymentBean) bf.getBean("corePaymentBean");

        long idop = cPayment.getIdOpXpay();

        log.debug("[PaymentBean::getIdOpXpay] END");

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "getIdOpXpay()", "chiamata getIdOpXpay", "getIdOpXpay");

        return idop;
    }

    public Hashtable<String, String> getDatiPagonet(String tranId) throws RemoteException, DaoException
    {
        log.debug("[PaymentBean::getDatiPagonet] BEGIN");
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        CorePaymentBean cPayment = (CorePaymentBean) bf.getBean("corePaymentBean");

        Hashtable<String, String> ht = cPayment.getDatiPagonet(tranId);

        log.debug("[PaymentBean::getDatiPagonet] END");

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "getDatiPagonet()", "chiamata getDatiPagonet", "getDatiPagonet");

        return ht;
    }

    public Transazione getTransazioneByAuthorNumber(String authorNumber) throws RemoteException,DaoException
    {
        log.debug("[PaymentBean::getTransazioneByAuthorNumber] BEGIN");
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        CorePaymentBean cPayment = (CorePaymentBean) bf.getBean("corePaymentBean");

        Transazione t = cPayment.getTransazioneByAuthorNumber(authorNumber);

        log.debug("[PaymentBean::getTransazioneByAuthorNumber] END");

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "getTransazioneByAuthorNumber()", "chiamata getTransazioneByAuthorNumber", "getTransazioneByAuthorNumber");

        return t;
    }
    public boolean testResources() throws RemoteException, it.csi.csi.wrapper.SystemException
    {
        log.debug("[PaymentBean::testResources] BEGIN");
        StopWatch stopWatch = new StopWatch(it.csi.mdp.core.util.Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        CorePaymentBean cPayment = (CorePaymentBean) bf.getBean("corePaymentBean");

        boolean ret = cPayment.testResources();

        log.debug("[PaymentBean::testResources] END");

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "testResources()", "chiamata testResources", "testResources");

        return ret;

    }

    public boolean isAlive() throws RemoteException
    {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        CorePaymentBean cPayment = (CorePaymentBean) bf.getBean("corePaymentBean");
        return cPayment.isAlive();
    }
    public Properties getConfig () throws RemoteException
    {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        CorePaymentBean cPayment = (CorePaymentBean) bf.getBean("corePaymentBean");
        return cPayment.getConfig();
    }

    /* INIZIO OPERAZIONI PER INTEGRAZIONE CON NODO SPC */

    /**
     * Registra un evento nel giornale eventi
     * @param gde l'evento da registrare
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public void registraEventoGiornale(GiornaleEvento gde) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {

        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        nodoBean.registraEventoGiornale(gde);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "registraEventoGiornale()", "chiamata registraEventoGiornale", "registraEventoGiornale");
    }

    /**
     * Registra un esito di revoca
     * @param er
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public void registraEsitoRevoca(ER er) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException{
        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        LoggerUtil.dump(er);

        nodoBean.registraEsitoRevoca ( er );

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "registraEsitoRevoca()", "chiamata registraEsitoRevoca", "registraEsitoRevoca");
    }

    /**
     * Registra id_RR su RT
     * @param iuv
     * @param idRR
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public void updateIdRrByIuv(String iuv, Integer idRR)  throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException{
        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        nodoBean.updateIdRrByIuv ( iuv, idRR );

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "updateIdRrByIuv()", "chiamata updateIdRrByIuv", "updateIdRrByIuv");
    }

    /**
     * Registra una richiesta di revoca da parte del nodo
     * @param rr
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public Integer registraRichiestaRevoca(RR rr) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {

        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        LoggerUtil.dump(rr);

        Integer lastKey = nodoBean.registraRichiestaRevoca ( rr );

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "registraRichiestaDiRevoca()", "chiamata registraRichiestaDiRevoca", "registraRichiestaDiRevoca");

        return lastKey;
    }

    /**
     * Registra una richiesta di pagamento telematica, quando essa viene inviata al Nodo SPC tramite lo startTrnsazione
     * @param rpt
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public void registraRichiestaTelematicaPagamento(RPT rpt) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {

        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        LoggerUtil.dump(rpt);

        nodoBean.registraRichiestaPagamentoTelematico(rpt);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "registraRichiestaTelematicaPagamento()", "chiamata registraRichiestaTelematicaPagamento", "registraRichiestaTelematicaPagamento");
    }

    /**
     * Aggiorna una richiesta di pagamento telematica, quando essa viene inviata al Nodo SPC tramite lo startTrnsazione
     * @param rpt
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public void aggiornaRichiestaTelematicaPagamento(RPT rpt) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {

        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        LoggerUtil.dump(rpt);

        nodoBean.aggiornaRichiestaPagamentoTelematico(rpt);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "aggiornaRichiestaTelematicaPagamento()", "chiamata aggiornaRichiestaTelematicaPagamento", "aggiornaRichiestaTelematicaPagamento");
    }

    /**
     * Aggiorna una Ricevuta Telematica quando il nodo la restituisce oppure viene chiamato getStatoTransazione sull'adapter
     * @param rt
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public void aggiornaRicevutaTelematica(RT rt) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {

        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        LoggerUtil.info ( "AGGIORNAMENTO RICEVUTA TELEMATICA MDPCORE - PAYMENT BEAN" );

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        nodoBean.aggiornaRicevutaTelematica(rt);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "aggiornaRicevutaTelematica()", "chiamata aggiornaRicevutaTelematica", "aggiornaRicevutaTelematica");
    }

    /**
     * Registra una Ricevuta Telematica quando il nodo la restituisce oppure viene chiamato getStatoTransazione sull'adapter
     * @param rt
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public void registraRicevutaTelematica(RT rt) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {

        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        nodoBean.registraRicevutaTelematica(rt);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "registraRicevutaTelematica()", "chiamata registraRicevutaTelematica", "registraRicevutaTelematica");
    }

    /**
     *
     * @param idTransazione
     * @return
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public RT recuperaRichiestaPerIdTransazione(String idTransazione) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {

        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        RT rt = nodoBean.recuperaRichiestaPerIdTransazione(idTransazione);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "recuperaRichiestaPerIdTransazione()", "chiamata recuperaRichiestaPerIdTransazione", "recuperaRichiestaPerIdTransazione");

        return rt;

    }

    /**
     *
     * @param filtro
     * @return
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public List<RPT> recuperaRichiestaPagamentoConFiltro(RPT filtro) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {

        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        List<RPT> elenco = nodoBean.recuperaRichiestaPagamentoConFiltro(filtro);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "recuperaRichiestaPagamentoConFiltro()", "chiamata recuperaRichiestaPagamentoConFiltro", "recuperaRichiestaPagamentoConFiltro");

        return elenco;
    }

    /**
     *
     * @param datiAggiornati
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public void aggiornaRPT(RPT datiAggiornati) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {

        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        nodoBean.aggiornaRPT(datiAggiornati);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "aggiornaRPT()", "chiamata aggiornaRPT", "aggiornaRPT");
    }

    /**
     *
     * @param iuv
     * @return
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public RicevutaTelematicaNodo getRTperIUV(String iuv) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {

        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean ( "nodoSPCBean" );
        if ( StringUtils.isEmpty ( iuv ) )
            throw new DaoException ( "Parametro IUV obbligatorio" );
        else if ( !StringUtils.isAlphanumeric ( iuv ) ) {
            throw new DaoException ( "Parametro IUV non valido" );
        }
        
        RicevutaTelematicaNodo rt = nodoBean.recuperaRTperIUV(iuv);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "getRTperIUV()", "chiamata getRTperIUV", "getRTperIUV");

        return rt;
    }

    public RicevutaTelematicaNodo getRTperIUVTransazione(String iuv, String transactionId) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {

        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");
        if (StringUtils.isEmpty(iuv))
            throw new DaoException("Parametro IUV obbligatorio");
        if (StringUtils.isEmpty(transactionId))
            throw new DaoException("Parametro transaction id obbligatorio");
        if ( !StringUtils.isAlphanumeric ( iuv ) ) {
            throw new DaoException ( "Parametro IUV non valido" );
        }
        if ( !StringUtils.isAlphanumeric ( transactionId ) ) {
            throw new DaoException ( "Parametro transactionId non valido" );
        }

        RicevutaTelematicaNodo rt = nodoBean.recuperaRTperIUVTransazione(iuv, transactionId);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "getRTperIUVTransazione()", "chiamata getRTperIUVTransazione", "getRTperIUVTransazione");

        return rt;
    }

    public List<RicevutaTelematicaNodo> getListaRTperIUV(String iuv) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {

        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");
        if ( StringUtils.isEmpty ( iuv ) ) {
            throw new DaoException ( "Parametro IUV obbligatorio" );
        } else if ( !StringUtils.isAlphanumeric ( iuv ) ) {
            throw new DaoException ( "Parametro IUV non valido" );
        }

        List<RicevutaTelematicaNodo> elencoRt = nodoBean.recuperaListaRTperIUV(iuv);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "getListaRTperIUV()", "chiamata getListaRTperIUV", "getListaRTperIUV");

        return elencoRt;
    }

    /**
     * Recupera l'informativa PSP desiderata quando viene creata la RPT.
     * @param id
     * @return
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public InformativePSP recuperaInformativaPerId(Integer id) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {

        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        InformativePSP infoPSP = nodoBean.recuperaInformativaPerId(id);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "recuperaInformativaPerId()", "chiamata recuperaInformativaPerId", "recuperaInformativaPerId");

        return infoPSP;
    }
    /**
     * Recupera l'informativa PSP desiderata quando viene creata la RPT.
     * @param id
     * @return
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public InformativePSP recuperaInformativaPerIdentificativoPSP(String identificativoPSP) throws DaoException {

        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        InformativePSP infoPSP = nodoBean.recuperaInformativaPerIdentificativoPSP(identificativoPSP);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "recuperaInformativaPerId()", "chiamata recuperaInformativaPerId", "recuperaInformativaPerId");

        return infoPSP;
    }

    /**
     * Imposta l'attributo IdSession sulla tabella transazione. L'idSession e' un attributo restituito nella URL dal
     * Nodo SPC al fine di identificare univocamente il redirect di una transazione e viene usato per il ritorno all'applicazione fruitrice
     * @param transacionId
     * @param idSession
     * @throws RemoteException
     * @throws DaoException
     * @throws MissingParameterException
     */
    public void setIdSessionTransazione (String transacionId, String idSession) throws RemoteException,DaoException, MissingParameterException {
        log.debug("[PaymentBean::setIdSessionTransazione] BEGIN");
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        nodoBean.setIdSessionTransazione(transacionId, idSession);

        log.debug("[PaymentBean::setIdSessionTransazione] END");

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "setIdSessionTransazione()", "chiamata setIdSessionTransazione",
                        "setIdSessionTransazione");


    }
    public void registraTransazioneIuv (String transacionId, String iuv) throws RemoteException,DaoException, MissingParameterException {
        log.debug("[PaymentBean::registraTransazioneIuv] BEGIN");
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        nodoBean.registraTransazioneIuv(transacionId, iuv);

        log.debug("[PaymentBean::registraTransazioneIuv] END");

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "registraTransazioneIuv()", "chiamata registraTransazioneIuv",
                        "setIdSessionTransazione");

    }

    /**
     * Al ritorno sulla jsp di planaggio da un pagagamento eseguito tramite Nodo SPC, serve per reperire la transazione di appartenenza
     * @param idSession
     * @return
     * @throws RemoteException
     * @throws DaoException
     * @throws MissingParameterException
     */
    public Transazione findTransazioneByidSession(String idSession) throws RemoteException, DaoException, MissingParameterException
    {
        log.debug("[PaymentBean::findTransazioneByidSession] BEGIN");
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        Transazione transaction = nodoBean.recuperaIdTransazionePerIdSession(idSession);

        log.debug("[PaymentBean::findTransazioneByidSession] END");

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "findTransazioneByidSession()", "chiamata findTransazioneByidSession",
                        "findTransazioneByidSession");

        return transaction;
    }

    public List<NodoErrore> getErroriNodo (String applicationId, String transactionId) throws RemoteException, DaoException, MissingParameterException{
        log.debug("[PaymentBean::getErroriNodo] BEGIN");
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();
        if ( !StringUtil.isValidApplicationId ( applicationId ) ) {
            throw new DaoException ( "Parametro appId non valido" );
        }
        if ( !StringUtils.isAlphanumeric ( transactionId ) ) {
            throw new DaoException ( "Parametro transactionId non valido" );
        }
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        List<NodoErrore> elencoErrori= nodoBean.getErroriNodo(applicationId, transactionId);

        log.debug("[PaymentBean::getErroriNodo] END");

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "getErroriNodo()", "chiamata getErroriNodo", "getErroriNodo");

        return elencoErrori;
    }

    public void registraErroriNodo(List<NodoErrore> elencoErrori) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {

        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        nodoBean.registraErroriNodo(elencoErrori);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "registraErroriNodo()", "chiamata registraErroriNodo", "registraErroriNodo");
    }

    public IuvOtticoFruitore recuperaDatiFruitorePerIuvOttico(String iuvOttico, String iuvStandard) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {

        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        IuvOtticoFruitore iuv = null;

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        iuv = nodoBean.recuperaDatiFruitorePerIuvOttico(iuvOttico, iuvStandard);

        if (iuv == null)
            return null;

        log.debug ("Application detail:" + iuv.getApplicationDetail());
        log.debug("Application id :" + iuv.getApplicationDetail().getApplicationid());
        log.debug("Gateway :" + iuv.getApplicationDetail().getGatewayid());

        iuv.setElencoAcf(getCustomFields(iuv.getApplicationDetail().getApplicationid(), iuv.getApplicationDetail().getGatewayid()));

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "recuperaDatiFruitorePerIuvOttico()", "chiamata recuperaDatiFruitorePerIuvOttico", "recuperaDatiFruitorePerIuvOttico");

        return iuv;
    }

    public IbanEnteApplication recuperaIbanEnteApplication(IbanEnteApplication filtro) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {

        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        IbanEnteApplication ibanEnteApplication = nodoBean.recuperaIbanEnteApplication(filtro);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "recuperaIbanEnteApplication()", "chiamata recuperaIbanEnteApplication", "recuperaIbanEnteApplication ");

        return ibanEnteApplication;
    }

    public DettaglioFruitore recuperaDatiFruitorePerApplicationId(String applicationId) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {

        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        DettaglioFruitore dettaglioFruitore = null;

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        LoggerUtil.debug ( "RICERCA DATI FRUITORE PER APPLICATIONID:" + applicationId);

        dettaglioFruitore = nodoBean.recuperaDatiFruitorePerApplicationId(applicationId);

        LoggerUtil.debug ( "DETTAGLIO FRUITORE:" + dettaglioFruitore);
        LoggerUtil.debug ( "DETTAGLIO FRUITORE APP DETAIL:" + dettaglioFruitore.getApplicationDetail());
        LoggerUtil.debug ( "DETTAGLIO FRUITORE APP DETAIL GATEWAY:" + dettaglioFruitore.getApplicationDetail().getGatewayid());

        dettaglioFruitore.setElencoAcf(getCustomFields(applicationId, dettaglioFruitore.getApplicationDetail().getGatewayid()));

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "recuperaDatiFruitorePerApplicationId()", "chiamata recuperaDatiFruitorePerApplicationId", "recuperaDatiFruitorePerApplicationId");

        return dettaglioFruitore;
    }

    public void registraTransazioneExtraAttributes(TransazioneExtraAttribute[] tea) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        nodoBean.registraTransazioneExtraAttributes(tea);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "registraTransazioneExtraAttributes()", "chiamata registraTransazioneExtraAttributes", "registraTransazioneExtraAttributes");

    }

    public void errorMail(String appId, Transazione transaction, String body) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        CorePaymentBean coreBean = (CorePaymentBean) bf.getBean("corePaymentBean");

        coreBean.errorMail(appId, transaction, body);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "errorMail()", "chiamata errorMail", "errorMail");

    }

    /**
     * PRepara e ritorna l'url per il wisp in base alle scelte del fruitore (con
     * @param applicationId
     * @param transactionId
     * @param paramentriUrlWisp
     * @param prametriAggiuntivi
     * @return
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public String getUrlWisp(String applicationId, Transazione transaction,ParametriUrlWisp paramentriUrlWisp, List<ChiaveValore> prametriAggiuntivi) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();
        if ( !StringUtil.isValidApplicationId ( applicationId ) ) {
            throw new DaoException ( "Parametro appId non valido" );
        }
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        CorePaymentBean coreBean = (CorePaymentBean) bf.getBean("corePaymentBean");
        String gatewayIdPerNodo = nodoBean.getGatewayNodo(transaction.getApplicationId());
        Applicationcustomfields[] acf = coreBean.getCustomFields(applicationId, gatewayIdPerNodo);

        String url = nodoBean.gestioneRedirectWispInterno(applicationId, transaction, paramentriUrlWisp, prametriAggiuntivi, acf);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "errorMail()", "chiamata errorMail", "errorMail");
        return url;
    }

    public Applicationcustomfields[] recuperaApplicationCustomFieldsNodo(String keypa) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {

        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();
        Applicationcustomfields[] acf = null;
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        CorePaymentBean coreBean = (CorePaymentBean) bf.getBean("corePaymentBean");
        String gatewayIdPerNodo = nodoBean.getGatewayNodo(keypa);
        acf = coreBean.getCustomFields(keypa, gatewayIdPerNodo);


        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "recuperaApplicationCustomFieldsNodo()", "chiamata recuperaApplicationCustomFieldsNodo", "recuperaApplicationCustomFieldsNodo");
        return acf;
    }

    /**
     * Recupera i parametri WISP
     * @param filtro
     * @return
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public List<ParametroWisp> reuperaParametriWisp(ParametroWisp filtro) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        List<ParametroWisp> elenco = nodoBean.reuperaParametriWisp(filtro);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "reuperaParametriWisp()", "chiamata reuperaParametriWisp", "reuperaParametriWisp");
        return elenco;
    }

    /**
     * Cancella paraetro Wisp
     * @param filtro
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public void deleteParametroWisp(ParametroWisp filtro) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        nodoBean.deleteParametroWisp(filtro);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "errorMail()", "chiamata deleteParametroWisp", "deleteParametroWisp");
    }

    /**
     * Aggiorna la riga di DB che corrisponde ad una richiesta di selezione PSP sul WISP quando il nodo
     * redirige di nuovo a MDP dopo la scelta / annullamento / timeout
     * @param daAggiornare
     * @return
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public int aggiornaParametroWisp(ParametroWisp daAggiornare) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        int risultato = nodoBean.aggiornaParametroWisp(daAggiornare);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "aggiornaParametroWisp()", "chiamata aggiornaParametroWisp", "aggiornaParametroWisp");
        return risultato;
    }

    /**
     * Informa il fruitore della scelta effettuata per la transazione specificata
     * @param applicationId
     * @param transactionId
     * @return
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public InformativePSPScelto getSceltaWisp(String applicationId, String transactionId) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();
        if ( !StringUtil.isValidApplicationId ( applicationId ) ) {
            throw new DaoException ( "Parametro appId non valido" );
        }
        if ( !StringUtils.isAlphanumeric ( transactionId ) ) {
            throw new DaoException ( "Parametro transactionId non valido" );
        }
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        InformativePSPScelto risultato = nodoBean.getSceltaWisp(applicationId, transactionId);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "getSceltaWisp()", "chiamata getSceltaWisp", "getSceltaWisp");
        return risultato;
    }

    public int inserisciMultiVersamento(ElementoMultiversamento elem) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        int risultato = nodoBean.inserisciMultiVersamento(elem);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "aggiornaParametroWisp()", "chiamata aggiornaParametroWisp", "aggiornaParametroWisp");
        return risultato;
    }

    public String startTransazione ( Transazione t, TransazioneExtraAttribute [] tea, boolean multiBeneficiario )
                    throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();
        String url = startTransazione(t, tea, null, multiBeneficiario);
        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "startTransazione()", "chiamata startTransazione", "startTransazione");
        LoggerUtil.end();
        return url;
    }

    //RDI-45-MULTIBENEFICIARIO INIZIO
    public String getNumeroAvviso(String iuv, String applicationId, String gatewayId)  throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        String risultato = nodoBean.getNumeroAvviso(iuv, applicationId, gatewayId);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "getNumeroAvviso()", "chiamata getNumeroAvviso", "getNumeroAvviso");
        return risultato;
    }

    public int getCountIdCarrello(String identificativoDominio, String iuv)   throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException  {
        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        int risultato = nodoBean.getCountIdCarrello(identificativoDominio, iuv);

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "getCountIdCarrello()", "chiamata getCountIdCarrello", "getCountIdCarrello");
        return risultato;
    }
    
    public int findNexValueIdDominio( ) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException  {
        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();
        
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");
        
        int risultato = nodoBean.findNexValueIdDominio( );
        
        LoggerUtil.end();
        
        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "findNexValueIdDominio()", "chiamata findNexValueIdDominio", "findNexValueIdDominio");
        return risultato;
    }

    public boolean getAllGestioneCodiceContestoPagamentoFlag() throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException  {
        LoggerUtil.begin();
        StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
        stopWatch.start();

        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean("nodoSPCBean");

        boolean risultato = nodoBean.getAllGestioneCodiceContestoPagamentoFlag();

        LoggerUtil.end();

        stopWatch.stop();
        stopWatch.dumpElapsed("PaymentBean", "getAllGestioneCodiceContestoPagamentoFlag()", "chiamata getAllGestioneCodiceContestoPagamentoFlag", "getAllGestioneCodiceContestoPagamentoFlag");
        return risultato;
    }

    public boolean isPagato ( String iuv ) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        LoggerUtil.begin ();
        StopWatch stopWatch = new StopWatch ( Constants.APPLICATION_CODE );
        stopWatch.start ();

        BeanFactory bf = new XmlBeanFactory ( new ClassPathResource ( "beanContext.xml" ) );
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean ( "nodoSPCBean" );

        boolean risultato = nodoBean.isPagato ( iuv );

        LoggerUtil.end ();

        stopWatch.stop ();
        stopWatch.dumpElapsed ( "PaymentBean", "isPagato()", "chiamata isPagato", "isPagato" );
        return risultato;
    }
    
    public boolean isSpontaneo ( String iuv ) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        LoggerUtil.begin ();
        StopWatch stopWatch = new StopWatch ( Constants.APPLICATION_CODE );
        stopWatch.start ();

        BeanFactory bf = new XmlBeanFactory ( new ClassPathResource ( "beanContext.xml" ) );
        NodoSpcBean nodoBean = (NodoSpcBean) bf.getBean ( "nodoSPCBean" );

        boolean risultato = nodoBean.isSpontaneo ( iuv );

        LoggerUtil.end ();

        stopWatch.stop ();
        stopWatch.dumpElapsed ( "PaymentBean", "isSpontaneo()", "chiamata isSpontaneo", "isSpontaneo" );
        return risultato;
    }

    //RDI-45-MULTIBENEFICIARIO FINE
}
