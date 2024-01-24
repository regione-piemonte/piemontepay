/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.business.mdpcoopapplicativa;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.xml.soap.SOAPException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.ws.security.util.Base64;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import it.csi.mdpnew.mdpcoopapplicativasrv.business.mdpcoopapplicativa.interfaces.MdpcoopapplicativaBusinessInterface;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.db.ConfigDTO;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.AggiornaCodiceVersamentoRequest;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.AggiornaEnteRequest;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.AnnullaOperazioneRequest;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.ConfermaOperazioneRequest;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.EnteType;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.ProtocolloAggiornamentoAzioneType;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.ResponseType;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.ResultType;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdprichiediapplicationid.coop.ws.RichiediApplicationIdRequest;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdprichiediapplicationid.coop.ws.RichiediApplicationIdResponse;
import it.csi.mdpnew.mdpcoopapplicativasrv.exception.mdpcoopapplicativa.ErrorParameterException;
import it.csi.mdpnew.mdpcoopapplicativasrv.exception.mdpcoopapplicativa.MdpcoopapplicativaSrvException;
import it.csi.mdpnew.mdpcoopapplicativasrv.exception.mdpcoopapplicativa.coop.CoopPecException;
import it.csi.mdpnew.mdpcoopapplicativasrv.exception.mdpcoopapplicativa.coop.PersistenceException;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTApplication;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTApplicationCustomFields;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTApplicationCustomFieldsTemp;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTApplicationDetail;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTApplicationDetailTemp;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTApplicationTemp;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTEnte;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTEnteTemp;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTErrore;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTTemplate;
import it.csi.mdpnew.mdpcoopapplicativasrv.util.MdpcoopapplicativaConstants;
import it.csi.mdpnew.mdpcoopapplicativasrv.util.coop.CostantiErrori;
import it.csi.mdpnew.mdpcoopapplicativasrv.util.coop.TempUtility;
import it.csi.util.beanlocatorfactory.ServiceBeanLocator;

public class MdpcoopapplicativaBean implements SessionBean, MdpcoopapplicativaBusinessInterface {

    private static final long serialVersionUID = 1L;

    protected MdpcoopapplicativaImpl delegate = null;

    ClassPathResource cp = new ClassPathResource ( "/WEB-INF/classes/env.properties" );

    private Properties envProps = new Properties ();

    public SessionContext ctx = null;

    private final static String PROGETTO = "EPAY";

    public boolean hasSelfCheck () throws it.csi.csi.wrapper.CSIException {
        return delegate.hasSelfCheck ();
    }

    @Override
    public void ejbActivate () throws EJBException, RemoteException {
    }

    @Override
    public void ejbPassivate () throws EJBException, RemoteException {
    }

    @Override
    public void ejbRemove () throws EJBException, RemoteException {
    }

    public void ejbCreate () {
        Logger logger = getLogger ( "ejb" );
        logger.debug ( "[MdpiuvBean::create] - BEGIN" );

        logger.debug ( "[MdpiuvBean::create] - END" );
    }

    @Override
    public void setSessionContext ( SessionContext ctx ) throws EJBException, RemoteException {

        Logger logger = getLogger ( null );
        logger.debug ( "[MdpiuvBean::setSessionContext] - START" );
        this.ctx = ctx;

        Object implInitOptions = null;

        ApplicationContext context;
        try {
            context = new ClassPathXmlApplicationContext ( "beanContext.xml" );
        } catch ( BeansException e ) {

            logger.error ( "[MdpiuvBean::setSessionContext] - errore : " + e.getMessage () );
        }

        logger.debug ( "[MdpiuvBean::setSessionContext] caricato ClassPathXmlApplicationContext " );

        this.ctx = ctx;

        delegate = (MdpcoopapplicativaImpl) ServiceBeanLocator.getBeanByName ( "delegate" );

        //createImpl(implInitOptions);
        logger.debug ( "[MdpiuvBean::setSessionContext] - END" );
    }

    protected Logger getLogger ( String subsystem ) {
        if ( subsystem != null ) {
            return Logger.getLogger ( MdpcoopapplicativaConstants.LOGGER_PREFIX + "." + subsystem );
        } else {
            return Logger.getLogger ( MdpcoopapplicativaConstants.LOGGER_PREFIX );
        }
    }

    @Override
    public ResponseType aggiornaCodiceVersamento ( AggiornaCodiceVersamentoRequest aggiornaCodiceVersamentoRequest ) throws PersistenceException {

        Logger logger = getLogger ( "ejb" );

        ResponseType response = returnStandardSuccessfulOutput ();

        if ( null == aggiornaCodiceVersamentoRequest || !StringUtils.isNotBlank ( aggiornaCodiceVersamentoRequest.getIdOperazione () ) ) {
            return returnErrorOutput ( CostantiErrori.WS_ESITO_KO_NO_PARAM );
        }

        try {
            String azione = "";
            ProtocolloAggiornamentoAzioneType azioneRequest = ( null != aggiornaCodiceVersamentoRequest.getProtocolloAggiornamentoAzione () ) ? aggiornaCodiceVersamentoRequest.getProtocolloAggiornamentoAzione () : null;

            if ( null != azioneRequest ) {

                switch ( azioneRequest ) {
                case INSERIMENTO :
                    azione = TempUtility.AZIONE_INSERIMENTO;
                    break;
                case MODIFICA :
                    azione = TempUtility.AZIONE_MODIFICA;
                    break;
                case CANCELLAZIONE :
                    azione = TempUtility.AZIONE_CANCELLAZIONE;
                    break;
                default :
                    throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                                    "Azione non riconosciuta" );
                }
            } else {
                throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                                "Azione non presente" );
            }

            //Codice versamento non trovato
            if ( null == aggiornaCodiceVersamentoRequest.getCodiceVersamento ()
                            || !StringUtils.isNotBlank ( aggiornaCodiceVersamentoRequest.getCodiceVersamento ().getCFEnte () ) ) {
                throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                    String.format ( "Codice fiscale ente non presente" ) );
            }

            String cfEnte     = aggiornaCodiceVersamentoRequest.getCodiceVersamento ().getCFEnte ();
            String ibanCvers  = aggiornaCodiceVersamentoRequest.getCodiceVersamento ().getIban ();
            String bicCodVers = aggiornaCodiceVersamentoRequest.getCodiceVersamento ().getBic ();
            String ibanAppoggio = aggiornaCodiceVersamentoRequest.getCodiceVersamento().getIbanAppoggio();
            String ibanAppoggioPostale = String.valueOf(aggiornaCodiceVersamentoRequest.getCodiceVersamento().isFlagCodiceCorrentePostaleAppoggio());
            String ibanAccreditoPostale = String.valueOf(aggiornaCodiceVersamentoRequest.getCodiceVersamento().isFlagCodiceCorrentePostaleTesoreria());
            String bicAppoggio = aggiornaCodiceVersamentoRequest.getCodiceVersamento().getBicAppoggio();

            if ( StringUtils.isNotEmpty ( ibanCvers ) || StringUtils.isNotEmpty ( ibanAppoggio ) ) {
                insertUpdateCodiceVersamentoNew ( azione, cfEnte, ibanCvers, bicCodVers, aggiornaCodiceVersamentoRequest.getIdOperazione (),
                    ibanAppoggio, ibanAppoggioPostale, ibanAccreditoPostale, bicAppoggio );
            }
        } catch ( Throwable t ) {
            if ( t instanceof CoopPecException ) {
                logger.error ( t );
                String stringErrorCode = Integer.toString ( ( (CoopPecException) t ).getErrorCode () );
                response = returnErrorOutput ( stringErrorCode );
            } else {
                logger.error ( "Errore in fase di aggiornaCodiceVersamento: ", t );
                response = returnErrorOutput ( CostantiErrori.WS_ESITO_KO_DEFAULT );
            }
        }

        return response;
    }

    //LA MODIFICA DI UN IBAN IN FASE DI AGGIORNAMENTO CODICE VERSAMENTO FA SCATTARE L'INSERIMENTO/MODIFICA DELLA APPLICATION

    private void insertUpdateCodiceVersamentoNew ( String azione, String cfEnte, String ibanCodVers, String bicCodVers, String idOperazione,
        String ibanAppoggio, String ibanAppoggioPostale, String ibanAccreditoPostale,String bicAppoggio) throws Exception {

        Logger logger = getLogger ( "ejb" );
        String applicationIdWork;


        //In questo caso bisogna copiare applicationcustomfields e application id dell'ente
        //(trovati entrando sulla applicationcustomfields where application id = EPAY_[CF_ENTE]1),


        //L'ente e' allineato all'ultimo progressivo staccato non dimenticarlo
        MdpTEnte ente = delegate.findOneEnte ( cfEnte );

        if(ente == null) {
            throw new Exception("ENTE NON PRESENTE IN TABELLA - MODIFICA COD VERSAMENTO IMPOSSIBILE");
        }

        try {
            applicationIdWork = delegate.richiediApplicationIdNew( cfEnte, ibanCodVers, ibanAppoggio,idOperazione );
        } catch (MdpcoopapplicativaSrvException e) {
            throw new Exception(CostantiErrori.ERRORE_DI_SISTEMA);
        }


        //Qui application c'e' per forza visto che l'ente esiste quindi non puo' creare applications_0
        int progressivoCorrente = getEnteProgressivoApplicationIdZeroBased ( cfEnte );

        //     String applicationIdWork = getApplicationId ( cfEnte, ibanCodVers );
        String applicationIdLast = getAppIdCustom ( ente.getPartita_iva (), progressivoCorrente );

        boolean isApplicationIdNew = false;

        logger.debug ( "APPID CUR FOUND : " + applicationIdWork );
        logger.debug ( "APPID OLD       : " + applicationIdLast );

        //Non ho trovato application id per ente+iban per cui ne genero uno nuovo con +1 sul progressivo ente
        if ( StringUtils.isEmpty ( applicationIdWork ) ) {

            //Controllo sul progressivo
            //Se progressivo e' null vale 1 come minimo (quindi devo settarlo a 0 la prima volta)

            if ( ente.getProgressivo_application_id () == null ) {
                ente.setProgressivo_application_id ( progressivoCorrente );
            }
            applicationIdWork = getAppIdCustom ( ente.getPartita_iva (), progressivoCorrente + 1 );

            isApplicationIdNew = true;

            logger.debug ( "APPID CUR CHANGED : " + applicationIdWork );
        }

        logger.debug ( "IS APPL NEW : " + isApplicationIdNew );

        //Riutilizzo la tabella custom e modifico solo i campi variati

        MdpTApplicationCustomFields campoIbanTesoreria= new MdpTApplicationCustomFields();
        campoIbanTesoreria.setFieldvalue ( encode ( "NO", false ) );
        campoIbanTesoreria.setFieldname ( "ibanTesoreria" );

        boolean campoNuovoIbanTesoreriaAdded = false;

        if ( isApplicationIdNew == false ) {

            bloccaElaborazione ( applicationIdWork );

            campoIbanTesoreria.setApplicationid ( applicationIdWork );

            List<MdpTApplicationCustomFields> campiCustom = delegate.findAllApplicationCustomFields ( applicationIdWork );

            if ( ( campiCustom != null ) && ( campiCustom.size () > 0 ) ) {
                for ( MdpTApplicationCustomFields campo: campiCustom ) {

                    campoIbanTesoreria.setGateway_id ( campo.getGateway_id () );

                    if ( StringUtils.containsIgnoreCase ( campo.getFieldname (), "ibantesoreria" ) ) {
                        campo.setFieldvalue ( encode ( "NO", false ));
                        campoNuovoIbanTesoreriaAdded = true;
                    }

                    if ( StringUtils.containsIgnoreCase ( campo.getFieldname (), "bicaccredito" ) ) {
                        campo.setFieldvalue ( encode ( bicCodVers, false ) );
                    }

                    if ( StringUtils.equalsIgnoreCase ( campo.getFieldname (), "ibanaccredito" ) ) {
                        campo.setFieldvalue ( encode ( ibanCodVers, false ) );
                    }

                    if ( StringUtils.equalsIgnoreCase ( campo.getFieldname (), "ibanappoggio" ) ) {
                        campo.setFieldvalue ( encode ( ibanAppoggio, false ) );
                    }

                    if ( StringUtils.equalsIgnoreCase ( campo.getFieldname (), "ibanaccreditopostale" ) ) {
                        campo.setFieldvalue ( encode ( ibanAccreditoPostale, false ) );
                    }

                    if ( StringUtils.equalsIgnoreCase ( campo.getFieldname (), "ibanappoggiopostale" ) ) {
                        campo.setFieldvalue ( encode ( ibanAppoggioPostale, false ) );
                    }

                    campo.setApplicationid ( applicationIdWork );
                }

                delegate.persistAppCustomTemp ( TempUtility.buildApplicationCustomTemp ( campiCustom, idOperazione ) );
            }
        }

        if ( isApplicationIdNew == true ) {

            //Se devo inserire un nuovo application copio dalla application precedente, la applicationIdLast

            //parto dal presupposto che ci sia sempre almeno 1 application id relativo alla propagazione dell'ente

            MdpTApplication campiApplication = delegate.getApplication ( applicationIdLast );
            List<MdpTApplicationDetail> campiDettaglio = delegate.findAllApplicationDetail ( applicationIdLast );
            List<MdpTApplicationCustomFields> campiCustom = delegate.findAllApplicationCustomFields ( applicationIdLast );

            bloccaElaborazione ( applicationIdWork );

            //------------------------------------------------//

            if ( campiApplication != null ) {
                String applicationDescription = PROGETTO + " " + ente.getDescrizione () + " " + (progressivoCorrente+1);

                campiApplication.setId ( applicationIdWork );
                campiApplication.setApplicationname ( applicationDescription );

                delegate.persistAppTemp ( TempUtility.buildApplicationTemp ( campiApplication, idOperazione ) );
            }

            //------------------------------------------------//

            if ( ( campiCustom != null ) && ( campiCustom.size () > 0 ) ) {

                for ( MdpTApplicationCustomFields campo: campiCustom ) {

                    campoIbanTesoreria.setGateway_id ( campo.getGateway_id () );
                    campoIbanTesoreria.setApplicationid ( applicationIdWork);

                    if ( StringUtils.containsIgnoreCase ( campo.getFieldname (), "ibantesoreria" ) ) {
                        campo.setFieldvalue ( encode ( "NO", false ) );
                        campoNuovoIbanTesoreriaAdded = true;
                    }

                    if ( StringUtils.containsIgnoreCase ( campo.getFieldname (), "bicaccredito" ) ) {
                        campo.setFieldvalue ( encode ( bicCodVers, false ) );
                    }

                    if ( StringUtils.equalsIgnoreCase ( campo.getFieldname (), "ibanaccredito" ) ) {
                        campo.setFieldvalue ( encode ( ibanCodVers, false ) );
                    }

                    if ( StringUtils.equalsIgnoreCase ( campo.getFieldname (), "bicappoggio" ) ) {
                        campo.setFieldvalue ( encode ( bicAppoggio, false ) );
                    }

                    if ( StringUtils.equalsIgnoreCase ( campo.getFieldname (), "ibanappoggio" ) ) {
                        campo.setFieldvalue ( encode ( ibanAppoggio, false ) );
                    }

                    if ( StringUtils.equalsIgnoreCase ( campo.getFieldname (), "ibanaccreditopostale" ) ) {
                        campo.setFieldvalue ( encode ( ibanAccreditoPostale, false ) );
                    }

                    if ( StringUtils.equalsIgnoreCase ( campo.getFieldname (), "ibanappoggiopostale" ) ) {
                        campo.setFieldvalue ( encode ( ibanAppoggioPostale, false ) );
                    }

                    if ( StringUtils.containsIgnoreCase ( campo.getFieldname (), "merchantid" ) ) {
                        campo.setFieldvalue ( encode ( ente.getPartita_iva (), false ) );
                    }

                    campo.setApplicationid ( applicationIdWork );
                }
                delegate.persistAppCustomTemp ( TempUtility.buildApplicationCustomTemp ( campiCustom, idOperazione ) );
            }

            //------------------------------------------------//

            if ( ( campiDettaglio != null ) && ( campiDettaglio.size () > 0 ) ) {
                for ( MdpTApplicationDetail campo: campiDettaglio ) {
                    campo.setApplicationid ( applicationIdWork );
                }

                delegate.persistAppDetailTemp ( TempUtility.buildApplicationDetailTemp ( campiDettaglio, idOperazione ) );
            }

            //E' stata creata una nuova applicazione quindi si deve aggiornare il progressivo application id dell'ente
            MdpTEnteTemp enteTemp = TempUtility.buildEnteTemp(ente, progressivoCorrente+1);
            enteTemp.setIdOperazione ( idOperazione );
            delegate.persistEnteTemp(enteTemp);
        }

        if(campoNuovoIbanTesoreriaAdded == false) {
            List<MdpTApplicationCustomFields> campoIbanTesoreriaList = new ArrayList<MdpTApplicationCustomFields> ();
            campoIbanTesoreria.setApplicationid(applicationIdWork);
            campoIbanTesoreriaList.add ( campoIbanTesoreria );

            delegate.persistAppCustomTemp ( TempUtility.buildApplicationCustomTemp ( campoIbanTesoreriaList, idOperazione ) );
        }
    }



    //LA MODIFICA DI UN IBAN IN FASE DI AGGIORNAMENTO CODICE VERSAMENTO FA SCATTARE L'INSERIMENTO/MODIFICA DELLA APPLICATION

    private void insertUpdateCodiceVersamento ( String azione, String cfEnte, String ibanCodVers, String bicCodVers, String idOperazione, String ibanAppoggio ) throws Exception {

        //In questo caso bisogna copiare applicationcustomfields e application id dell'ente
        //(trovati entrando sulla applicationcustomfields where application id = EPAY_[CF_ENTE]1),

        Logger logger = getLogger ( "ejb" );

        //L'ente e' allineato all'ultimo progressivo staccato non dimenticarlo
        MdpTEnte ente = delegate.findOneEnte ( cfEnte );

        if(ente == null) {
            throw new Exception("ENTE NON PRESENTE IN TABELLA - MODIFICA COD VERSAMENTO IMPOSSIBILE");
        }

        if ( ente.getProgressivo_application_id () == null ) {
            ente.setProgressivo_application_id ( 0 );
        }

        //Qui application c'e' per forza visto che l'ente esiste quindi non puo' creare applications_0
        int progressivoCorrente = getEnteProgressivoApplicationIdZeroBased ( cfEnte );

        String applicationIdWork = getApplicationId ( cfEnte, ibanCodVers );
        String applicationIdLast = getAppIdCustom ( ente.getPartita_iva (), progressivoCorrente );

        boolean isApplicationIdNew = false;

        logger.debug ( "APPID CUR FOUND : " + applicationIdWork );
        logger.debug ( "APPID OLD       : " + applicationIdLast );

        //Non ho trovato application id per ente+iban per cui ne genero uno nuovo con +1 sul progressivo ente
        if ( StringUtils.isEmpty ( applicationIdWork ) ) {

            //Controllo sul progressivo
            //Se progressivo e' null vale 1 come minimo (quindi devo settarlo a 0 la prima volta)

            if ( ente.getProgressivo_application_id () == null ) {
                ente.setProgressivo_application_id ( progressivoCorrente );
            }
            applicationIdWork = getAppIdCustom ( ente.getPartita_iva (), progressivoCorrente + 1 );

            isApplicationIdNew = true;

            logger.debug ( "APPID CUR CHANGED : " + applicationIdWork );
        }

        logger.debug ( "IS APPL NEW : " + isApplicationIdNew );

        //Riutilizzo la tabella custom e modifico solo i campi variati

        MdpTApplicationCustomFields campoIbanTesoreria= new MdpTApplicationCustomFields();
        campoIbanTesoreria.setFieldvalue ( encode ( "NO", false ) );
        campoIbanTesoreria.setFieldname ( "ibanTesoreria" );

        boolean campoNuovoIbanTesoreriaAdded = false;

        if ( isApplicationIdNew == false ) {

            bloccaElaborazione ( applicationIdWork );

            campoIbanTesoreria.setApplicationid ( applicationIdWork );

            List<MdpTApplicationCustomFields> campiCustom = delegate.findAllApplicationCustomFields ( applicationIdWork );

            if ( ( campiCustom != null ) && ( campiCustom.size () > 0 ) ) {
                for ( MdpTApplicationCustomFields campo: campiCustom ) {

                    campoIbanTesoreria.setGateway_id ( campo.getGateway_id () );

                    if ( StringUtils.containsIgnoreCase ( campo.getFieldname (), "ibantesoreria" ) ) {
                        campo.setFieldvalue ( encode ( "NO", false ) );
                        campoNuovoIbanTesoreriaAdded = true;
                    }

                    if ( StringUtils.containsIgnoreCase ( campo.getFieldname (), "bicaccredito" ) ) {
                        campo.setFieldvalue ( encode ( bicCodVers, false ) );
                    }

                    if ( StringUtils.equalsIgnoreCase ( campo.getFieldname (), "ibanaccredito" ) ) {
                        campo.setFieldvalue ( encode ( ibanCodVers, false ) );
                    }

                    if ( StringUtils.equalsIgnoreCase ( campo.getFieldname (), "ibanappoggio" ) ) {
                        campo.setFieldvalue ( encode ( ibanAppoggio, false ) );
                    }
                    campo.setApplicationid ( applicationIdWork );
                }

                delegate.persistAppCustomTemp ( TempUtility.buildApplicationCustomTemp ( campiCustom, idOperazione ) );
            }
        }

        if ( isApplicationIdNew == true ) {

            //Se devo inserire un nuovo application copio dalla application precedente, la applicationIdLast

            //parto dal presupposto che ci sia sempre almeno 1 application id relativo alla propagazione dell'ente

            MdpTApplication campiApplication = delegate.getApplication ( applicationIdLast );
            List<MdpTApplicationDetail> campiDettaglio = delegate.findAllApplicationDetail ( applicationIdLast );
            List<MdpTApplicationCustomFields> campiCustom = delegate.findAllApplicationCustomFields ( applicationIdLast );

            bloccaElaborazione ( applicationIdWork );

            //------------------------------------------------//

            if ( campiApplication != null ) {
                String applicationDescription = PROGETTO + " " + ente.getDescrizione () + " " + ente.getProgressivo_application_id ();

                campiApplication.setId ( applicationIdWork );
                campiApplication.setApplicationname ( applicationDescription );

                delegate.persistAppTemp ( TempUtility.buildApplicationTemp ( campiApplication, idOperazione ) );
            }

            //------------------------------------------------//

            if ( ( campiCustom != null ) && ( campiCustom.size () > 0 ) ) {
                for ( MdpTApplicationCustomFields campo: campiCustom ) {

                    campoIbanTesoreria.setGateway_id ( campo.getGateway_id () );
                    campoIbanTesoreria.setApplicationid ( applicationIdWork);

                    if ( StringUtils.containsIgnoreCase ( campo.getFieldname (), "ibantesoreria" ) ) {
                        campo.setFieldvalue ( encode ( "NO", false ) );
                        campoNuovoIbanTesoreriaAdded = true;
                    }

                    if ( StringUtils.containsIgnoreCase ( campo.getFieldname (), "bicaccredito" ) ) {
                        campo.setFieldvalue ( encode ( bicCodVers, false ) );
                    }

                    if ( StringUtils.equalsIgnoreCase ( campo.getFieldname (), "ibanaccredito" ) ) {
                        campo.setFieldvalue ( encode ( ibanCodVers, false ) );
                    }
                    if ( StringUtils.equalsIgnoreCase ( campo.getFieldname (), "ibanappoggio" ) ) {
                        campo.setFieldvalue ( encode ( ibanAppoggio, false ) );
                    }
                    if ( StringUtils.containsIgnoreCase ( campo.getFieldname (), "merchantid" ) ) {
                        campo.setFieldvalue ( encode ( ente.getPartita_iva (), false ) );
                    }

                    campo.setApplicationid ( applicationIdWork );
                }

                delegate.persistAppCustomTemp ( TempUtility.buildApplicationCustomTemp ( campiCustom, idOperazione ) );
            }

            //------------------------------------------------//

            if ( ( campiDettaglio != null ) && ( campiDettaglio.size () > 0 ) ) {
                for ( MdpTApplicationDetail campo: campiDettaglio ) {
                    campo.setApplicationid ( applicationIdWork );
                }

                delegate.persistAppDetailTemp ( TempUtility.buildApplicationDetailTemp ( campiDettaglio, idOperazione ) );
            }
        }

        if(campoNuovoIbanTesoreriaAdded == false) {
            List<MdpTApplicationCustomFields> campoIbanTesoreriaList = new ArrayList<MdpTApplicationCustomFields> ();
            campoIbanTesoreriaList.add ( campoIbanTesoreria );

            delegate.persistAppCustomTemp ( TempUtility.buildApplicationCustomTemp ( campoIbanTesoreriaList, idOperazione ) );
        }
    }

    private void bloccaElaborazione(String applicationId) throws Exception {
        //        List<MdpTApplicationCustomFieldsTemp> tempFull = delegate.findAllApplicationCustomFieldsTempByAppId ( applicationId );
        //
        //        if(tempFull != null && tempFull.size ()>0) {
        //            throw new Exception("TABELLA TEMP PIENA PER APPLICATION ID " + applicationId);
        //        }
    }

    //Questo codice non viene mai raggiunto nel caso di 1 iban su 2 application diverse

    private void aggiornamentoIbanTesoreria (String appIdToSkip, AggiornaEnteRequest enteReq ) throws ParseException {
        Logger logger = getLogger ( "ejb" );

        //Iban e ebic come me li aspetto sulla applicationcustomfield

        String ibanTesoreriaEncoded = encode ( enteReq.getEnte ().getIbanTesoreria (), false );
        String bicTesoreriaEncoded  = encode ( enteReq.getEnte ().getBicTesoreria (), false );

        //Cerco le application che hanno una relazione con l'ente corrente e
        //se il valore del loro iban tesoreria coincide con quello vecchio
        //dell'ente allora faccio la modifica aggiornando iban e bic

        List<MdpTApplicationCustomFields> applications = delegate.findAllApplicationCustomFieldsByAppidParziale ( "EPAY_" + enteReq.getEnte ().getCodiceFiscale () );

        logger.debug ( "Modifica delle applications di " + "EPAY_" + enteReq.getEnte ().getCodiceFiscale () );

        logger.debug ( "IBAN TESORERIA OLD:" + ibanTesoreriaEncoded );
        logger.debug ( "BIC  TESORERIA OLD:" + bicTesoreriaEncoded );

        for ( MdpTApplicationCustomFields app: applications ) {

            if(app.getApplicationid ().equalsIgnoreCase ( appIdToSkip )) {
                continue;
            }

            if ( StringUtils.containsIgnoreCase ( app.getFieldname (), "bicaccredito" ) && bicTesoreriaEncoded.equals ( app.getFieldvalue () ) ) {
                MdpTApplicationCustomFieldsTemp custom = new MdpTApplicationCustomFieldsTemp ();

                logger.debug ( "BIC ACCREDITO TROVATO PER CAMBIAMENTO" );

                org.springframework.beans.BeanUtils.copyProperties ( app, custom );

                custom.setFieldvalue ( encode ( enteReq.getEnte ().getBic (), false ) );
                custom.setIdOperazione ( enteReq.getIdOperazione () );
                delegate.persistAppCustomTemp ( custom );
            }

            if ( (StringUtils.containsIgnoreCase ( app.getFieldname (), "ibanaccredito" ) && !StringUtils.containsIgnoreCase ( app.getFieldname (), "postale" ) )
                            && ibanTesoreriaEncoded.equals ( app.getFieldvalue () ) ) {
                MdpTApplicationCustomFieldsTemp custom = new MdpTApplicationCustomFieldsTemp ();

                logger.debug ( "IBAN ACCREDITO TROVATO PER CAMBIAMENTO" );

                org.springframework.beans.BeanUtils.copyProperties ( app, custom );

                custom.setFieldvalue ( encode ( enteReq.getEnte ().getIban (), false ) );
                custom.setIdOperazione ( enteReq.getIdOperazione () );
                delegate.persistAppCustomTemp ( custom );
            }
        }
    }

    private void insertUpdateEnte ( String applicationId, MdpTEnte enteTemp, AggiornaEnteRequest request) throws Exception {
        Logger logger = getLogger ( "ejb" );

        List<MdpTApplicationDetailTemp> appDetailTemp = new ArrayList<MdpTApplicationDetailTemp> ();
        List<MdpTApplicationCustomFieldsTemp> appCustomTemp = new ArrayList<MdpTApplicationCustomFieldsTemp> ();

        MdpTTemplate gateway = delegate.getGateway ();

        List<MdpTTemplate> templates = delegate.findAllTemplates ( gateway.getValore (), PROGETTO );

        MdpTApplicationDetailTemp itemDetailTemp = new MdpTApplicationDetailTemp ();

        logger.debug ( "APP ID " + applicationId );

        String applicationDescriptionNew = PROGETTO + " " + enteTemp.getDescrizione () + " " + enteTemp.getProgressivo_application_id ();

        MdpTApplication oldApplication = delegate.findOneApplication ( applicationId );

        bloccaElaborazione(applicationId);

        //Giusto per avere una descrizione coerente col progressivo originario
        if (oldApplication != null ) {
            applicationDescriptionNew = oldApplication.getApplicationname ();
        }

        MdpTApplicationTemp appTemp = new MdpTApplicationTemp ();
        appTemp.setApplicationname ( applicationDescriptionNew );
        appTemp.setId ( applicationId );
        appTemp.setIdOperazione ( request.getIdOperazione ());
        appTemp.setProgetto ( PROGETTO );
        appTemp.setValido_dal ( new Timestamp ( new Date ().getTime () ) );

        for ( MdpTTemplate template: templates ) {

            MdpTApplicationCustomFieldsTemp itemCustomTemp = new MdpTApplicationCustomFieldsTemp ();

            if ( template.getTabella_riferimento ().equalsIgnoreCase ( "application" ) ) {
                populateApplication ( applicationDescriptionNew, PROGETTO, template, appTemp, enteTemp );

                logger.debug ( "######################################## APP " + appTemp.toString () + " CHIAVE = " + template.getChiave () + " - VALORE " + template.getValore () );
            }

            if ( template.getTabella_riferimento ().equalsIgnoreCase ( "applicationcustomfields" ) ) {

                populateApplicationCustomFieldsTemp (template, itemCustomTemp, request.getEnte (),(oldApplication==null));
                if(itemCustomTemp.getFieldname()!=null && !StringUtils.isEmpty(itemCustomTemp.getFieldname())) {
                    logger.debug ( "######################################## APP_CUSTOM " + itemCustomTemp.toString () + " CHIAVE = " + template.getChiave () + " - VALORE =" + template.getValore () );

                    itemCustomTemp.setGateway_id ( gateway.getValore () );
                    itemCustomTemp.setApplicationid ( applicationId );
                    itemCustomTemp.setIdOperazione ( request.getIdOperazione ());

                    appCustomTemp.add ( itemCustomTemp );
                }
            }

            if ( template.getTabella_riferimento ().equalsIgnoreCase ( "applicationdetail" ) ) {

                itemDetailTemp.setIdOperazione ( request.getIdOperazione ());
                populateApplicationDetails ( applicationId, gateway.getValore (), template, itemDetailTemp, enteTemp.getPartita_iva () );

                logger.debug ( "##########A############################## APP_DETAIL " + itemDetailTemp.toString () + " CHIAVE = " + template.getChiave () + " - VALORE " + template.getValore () );
            }
        }

        appDetailTemp.add ( itemDetailTemp );

        delegate.persistAppTemp ( appTemp );
        delegate.persistAppDetailTemp ( appDetailTemp );
        delegate.persistAppCustomTemp ( appCustomTemp );

        aggiornamentoIbanTesoreria(applicationId,request);
    }

    private void populateApplicationDetails ( String appId, String gateway, MdpTTemplate template, MdpTApplicationDetailTemp temp, String codicefiscale ) throws ParseException {
        String chiave = template.getChiave ();
        String valore = template.getValore ();

        if ( StringUtils.containsIgnoreCase ( chiave, "applicationurlback" ) ) {
            temp.setApplicationurlback ( valore );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "applicationurlcancel" ) ) {
            temp.setApplicationurlcancel ( valore );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "applicationurlerror" ) ) {
            temp.setApplicationurlerror ( valore );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "applicationurlresponseko" ) ) {
            temp.setApplicationurlresponseko ( valore );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "applicationurlresponseok" ) ) {
            temp.setApplicationurlresponseok ( valore );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "codiceistat" ) ) {
            temp.setCodiceistat ( valore );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "contocorrenteposte" ) ) {
            temp.setContocorrenteposte ( valore );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "enabled" ) ) {
            temp.setEnabled ( valore );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "flagreturlmdp" ) ) {
            temp.setFlagreturlmdp ( valore );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "mac_avvio" ) ) {
            temp.setMac_avvio ( valore );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "mac_esito" ) ) {
            temp.setMac_esito ( valore );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "mail2buyerko" ) ) {
            temp.setMail2buyerko ( valore );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "mail2buyerok" ) ) {
            temp.setMail2buyerok ( valore );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "mail2esercko" ) ) {
            temp.setMail2esercko ( valore );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "mail2esercok" ) ) {
            temp.setMail2esercok ( valore );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "mail2sysko" ) ) {
            temp.setMail2sysko ( valore );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "mail2sysok" ) ) {
            temp.setMail2sysok ( valore );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "paymentmodeid" ) ) {
            temp.setPaymentmodeid ( valore );
        }

        NumberFormat nf = NumberFormat.getInstance ( Locale.ITALIAN );

        if ( StringUtils.containsIgnoreCase ( chiave, "pgactioncode" ) ) {
            temp.setPgactioncode ( new BigDecimal ( nf.parse ( valore ).toString () ) );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "pgcontabcode" ) ) {
            temp.setPgcontabcode ( new BigDecimal ( nf.parse ( valore ).toString () ) );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "sogliaa" ) ) {
            temp.setSogliaa ( new BigDecimal ( nf.parse ( valore ).toString () ) );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "sogliada" ) ) {
            temp.setSogliada ( new BigDecimal ( nf.parse ( valore ).toString () ) );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "valorecommissionemax" ) ) {
            temp.setValorecommissionemax ( new BigDecimal ( nf.parse ( valore ).toString () ) );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "valorecommissionemin" ) ) {
            temp.setValorecommissionemin ( new BigDecimal ( nf.parse ( valore ).toString () ) );
        }

        if ( StringUtils.containsIgnoreCase ( chiave, "tipobollettinoposte" ) ) {
            temp.setTipobollettinoposte ( valore );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "tipocommissione" ) ) {
            temp.setTipocommissione ( valore );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "tipodocumentoposte" ) ) {
            temp.setTipodocumentoposte ( valore );
        }

        //-- CAMPI VARIABILI --//

        if ( StringUtils.containsIgnoreCase ( chiave, "applicationid" ) ) {
            temp.setApplicationid ( appId );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "gatewayid" ) ) {
            temp.setGatewayid ( gateway );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "merchantid" ) ) {
            temp.setMerchantid ( codicefiscale );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "merchantidpassword" ) ) {
            temp.setMerchantidpassword ( "" );
        }

    }

    private void populateApplicationCustomFieldsTemp (MdpTTemplate template, MdpTApplicationCustomFieldsTemp temp, EnteType ente,boolean isNewApplication ) {
        String chiave = template.getChiave ();
        String valore = template.getValore ();
        boolean cifrd = template.isCampo_cifrato ();
        String descrz = template.getDescrizione ();

        chiave = StringUtils.remove ( chiave, '{' );
        chiave = StringUtils.remove ( chiave, '}' );

        /* MDPNEW-87 | Lo 'sparpagliatore' cancella dati su MDP - START */
        /*Se e' una nuova applicazione devono essere inseriti i campi relativi all'iban e bic di appoggio senza essere valorizzati*/
        if(isNewApplication && ( (StringUtils.containsIgnoreCase ( chiave, "ibanAppoggio" ) && !StringUtils.containsIgnoreCase ( chiave, "postale" )) ||
                        StringUtils.containsIgnoreCase ( chiave, "bicAppoggio" ) )) {
            temp.setFielddescription ( descrz );
            temp.setFieldname ( chiave );
            temp.setFieldvalue( encode ( StringUtils.EMPTY, cifrd )); //Imposto a stringa vuota il valore
            return;
        }else if ( (StringUtils.containsIgnoreCase ( chiave, "ibanAppoggio" ) && !StringUtils.containsIgnoreCase ( chiave, "postale" )) ||
                        StringUtils.containsIgnoreCase ( chiave, "bicAppoggio" ) ) {
            //System.out.println ( "SALTO AGGIORNAMENTO CAMPO: {" + chiave + "}" );
            return;
        }
        /* MDPNEW-87 | Lo 'sparpagliatore' cancella dati su MDP - END */

        temp.setFielddescription ( descrz );
        temp.setFieldname ( chiave );

        // System.out.println ( "POPOLO TEMP: {" + chiave + "} = {...}, valore = {" + valore + "}" );

        //A SEGUIRE SOLO I DATI VARIABILI
        if ( StringUtils.containsIgnoreCase ( chiave, "bicAccredito" ) ) {
            temp.setFieldvalue ( encode ( ente.getBic (), cifrd ) );
        } else if ( StringUtils.equalsIgnoreCase( chiave, "ibanAccredito" ) ) {
            temp.setFieldvalue ( encode ( ente.getIban (), cifrd ) );
        } else if ( StringUtils.containsIgnoreCase ( chiave, "ibanTesoreria" ) ) {
            temp.setFieldvalue ( encode ( "SI" , cifrd ) );
        } else if ( StringUtils.containsIgnoreCase ( chiave, "capBeneficiario" ) ) {
            temp.setFieldvalue ( encode ( ente.getCap (), cifrd ) );
        } else if ( StringUtils.containsIgnoreCase ( chiave, "civicoBeneficiario" ) ) {
            temp.setFieldvalue ( encode ( ente.getCivico (), cifrd ) );
        } else if ( StringUtils.containsIgnoreCase ( chiave, "codiceIdentificativoUnivocoBeneficiario" ) ) {
            temp.setFieldvalue ( encode ( ente.getCodiceFiscale (), cifrd ) );
        } else if ( StringUtils.containsIgnoreCase ( chiave, "denominazioneBeneficiario" ) ) {
            temp.setFieldvalue ( encode ( ente.getDenominazione (), cifrd ) );
        } else if ( StringUtils.containsIgnoreCase ( chiave, "identificativoDominio" ) ) {
            temp.setFieldvalue ( encode ( ente.getCodiceFiscale (), cifrd ) );
        } else if ( StringUtils.containsIgnoreCase ( chiave, "indirizzoBeneficiario" ) ) {
            temp.setFieldvalue ( encode ( ente.getIndirizzo (), cifrd ) );
        } else if ( StringUtils.containsIgnoreCase ( chiave, "localitaBeneficiario" ) ) {
            temp.setFieldvalue ( encode ( ente.getLocalita (), cifrd ) );
        } else if ( StringUtils.containsIgnoreCase ( chiave, "provinciaBeneficiario" ) ) {
            temp.setFieldvalue ( encode ( ente.getSiglaProvincia (), cifrd ) );
        } else if ( StringUtils.containsIgnoreCase ( chiave, "ibanAppoggioPostale" ) ) {
            temp.setFieldvalue ( encode ( String.valueOf ( ente.isFlagCodiceCorrentePostaleAppoggio () ), cifrd ) );
        } else if ( StringUtils.containsIgnoreCase ( chiave, "ibanAccreditoPostale" ) ) {
            temp.setFieldvalue ( encode ( String.valueOf ( ente.isFlagCodiceCorrentePostaleTesoreria () ), cifrd ) );
        } else {
            // System.out.println ( "chiave non elaborata: {" + chiave + "}" );
            temp.setFieldvalue ( encode ( valore, cifrd ) );
        }
    }

    public String encode ( String toEncode, boolean giaCifrato ) {
        Logger logger = getLogger ( "ejb" );

        if ( giaCifrato ) {
            return toEncode;
        }

        //Il campo valore sulla tabella delle applicationCustomFields
        //non e' nullable per cui serve sempre un valore visto che
        //eventualmente iban normale, tesoreria, appoggio e' nullable
        if ( toEncode == null ) {
            toEncode = "";
        }

        try {
            byte [] raw = getKey ();

            SecretKeySpec skeySpec = new SecretKeySpec ( raw, "AES" );

            Cipher cipher = Cipher.getInstance ( "AES" );

            cipher.init ( Cipher.ENCRYPT_MODE, skeySpec );

            byte [] processed = cipher.doFinal ( toEncode.getBytes () );

            return Base64.encode ( processed );
        } catch ( Exception dax ) {
            logger.error ( "[MdpDaoImpl::encode] ERROR(data)", dax );
            throw new MdpcoopapplicativaSrvException ( dax.getMessage () );
        }
    }

    private byte [] getKey () throws Exception {
        Logger logger = getLogger ( "ejb" );

        getAllConfig ();

        logger.debug ( "SKEY DB LOCATION : " + envProps.getProperty ( "sKeyDb" ) );
        FileInputStream stream = new FileInputStream ( envProps.getProperty ( "sKeyDb" ) );
        int len = stream.available ();
        byte [] b = new byte [len];
        stream.read ( b );
        b = Base64.decode ( new String ( b ) );
        stream.close ();

        return b;
    }

    private Properties getAllConfig () throws ErrorParameterException, SOAPException {
        Logger logger = getLogger ( "ejb" );

        logger.debug ( "[MdpCoopApplicativa-MdpcoopapplicativaBean::getAllConfig] - BEGIN" );
        logger.debug ( "[MdpCoopApplicativa-MdpcoopapplicativaBean::getAllConfig] - cp.getPath() " + cp.getPath () );

        try {
            envProps.load ( cp.getInputStream () );
            List<ConfigDTO> lconf = delegate.findAllConfig ();

            if ( lconf != null ) {
                logger.debug ( "ITEMS IN CONFIG : " + lconf.size () );
            } else {
                logger.debug ( "NO ITEMS FOUND IN CONFIG" );
            }

            for ( int i = 0; i < lconf.size (); i++ ) {
                ConfigDTO c = lconf.get ( i );
                logger.debug ( "[MdpCoopApplicativa-MdpcoopapplicativaBean::getAllConfig] config:" + c.toString () );
                envProps.put ( c.getKey (), c.getValue () );
            }
        } catch ( IOException ioe ) {
            logger.error ( "[MdpCoopApplicativa-MdpcoopapplicativaBean::getAllConfig] Errore: ", ioe );
            ErrorParameterException epm = new ErrorParameterException ( MdpcoopapplicativaConstants.ERRORE_GENERICO );
            throw epm;
        }
        logger.debug ( "[MdpCoopApplicativa-MdpcoopapplicativaBean::getAllConfig] - END" );

        return envProps;
    }

    private void populateApplication ( String application, String progetto, MdpTTemplate template, MdpTApplicationTemp temp, MdpTEnte ente ) {
        String chiave = template.getChiave ();
        String valore = template.getValore ();

        String cliente = ente.getDescrizione ();

        if(!StringUtils.isEmpty ( cliente )) {
            if(cliente.length () >= 50) {
                cliente = cliente.substring ( 0, 50);
            }
        }

        if ( StringUtils.containsIgnoreCase ( chiave, "esercente_email" ) ) {
            temp.setEsercemail ( valore );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "numero_verde" ) ) {
            temp.setNumeroverde ( valore );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "redirect_newmdp" ) ) {
            temp.setRedirect_newmdp ( new BigDecimal ( valore ) );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "referente" ) ) {
            temp.setReferentecsi ( valore );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "note" ) ) {
            temp.setNote ( null );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "applicationname" ) ) {
            temp.setApplicationname ( application );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "progetto" ) ) {
            temp.setProgetto ( progetto );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "cliente" ) ) {
            temp.setCliente ( cliente );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "valido_dal" ) ) {
            temp.setValido_dal ( new Timestamp ( new Date ().getTime () ) );
        }
        if ( StringUtils.containsIgnoreCase ( chiave, "valido_al" ) ) {
            temp.setValido_al ( null );
        }
    }

    private void confermaTabelleApplicationTemp ( String idOperazione ) throws Exception  {

        Logger logger = getLogger ( "ejb" );

        List<MdpTApplicationTemp> appListTemp = delegate.findAllApplicationTemp ( idOperazione );
        List<MdpTApplicationDetailTemp> appListDetailTemp = delegate.findAllApplicationDetailTemp ( idOperazione );
        List<MdpTApplicationCustomFieldsTemp> appListCustomTemp = delegate.findAllApplicationCustomTemp ( idOperazione );

        MdpTApplication appTmp = null;
        List<MdpTApplicationDetail> appDetail = null;
        List<MdpTApplicationCustomFields> appCustom = null;

        //LA CHIAVE PER INSERT UPDATE E' SEMPRE APPLICATION ID

        //APPLICATION
        if ( appListTemp != null && appListTemp.size () > 0 ) {
            appTmp = TempUtility.buildApplication ( appListTemp.get ( 0 ) );
            MdpTApplication alreadyPresent = delegate.findOneApplication ( appTmp.getId () );
            delegate.persistApp ( appTmp, ( alreadyPresent != null ) );
        }

        //CUSTOM
        if ( appListCustomTemp != null && appListCustomTemp.size () > 0 ) {
            List<MdpTApplicationCustomFields> alreadyPresent = delegate.findAllApplicationCustomFields ( appListCustomTemp.get ( 0 ).getApplicationid () );
            appCustom = TempUtility.buildApplicationCustom ( appListCustomTemp );
            delegate.persistAppCustom ( appCustom, ( alreadyPresent != null && alreadyPresent.size () > 0 ), false );
        }

        //DETTAGLIO
        if ( appListDetailTemp != null && appListDetailTemp.size () > 0 ) {
            List<MdpTApplicationDetail> alreadyPresent = delegate.findAllApplicationDetail ( appListDetailTemp.get ( 0 ).getApplicationid () );
            appDetail = TempUtility.buildApplicationDetail ( appListDetailTemp );
            delegate.persistAppDetail ( appDetail, ( alreadyPresent != null && alreadyPresent.size () > 0 ) );
        }
    }

    private String decode ( String value ) throws Exception {
        byte [] raw = getKey ();

        SecretKeySpec skeySpec = new SecretKeySpec ( raw, "AES" );

        Cipher cipher = Cipher.getInstance ( "AES" );

        cipher.init ( Cipher.DECRYPT_MODE, skeySpec );

        byte [] processed = cipher.doFinal ( Base64.decode ( value ) );

        return new String ( processed );
    }

    private String getAppIdCustom ( String cfEnte, int progressivo ) {
        return "EPAY_" + cfEnte + "_" + progressivo;
    }

    private String getApplicationId ( String cfEnte, String iban ) {
        Logger logger = getLogger ( "ejb" );

        logger.debug ( "Ricerca appId " + cfEnte + " " + iban );
        String appId = delegate.richiediApplicationId ( cfEnte, iban );

        logger.debug ( "AppId value is " + appId );

        return appId;
    }

    @Override
    public ResponseType aggiornaEnte ( AggiornaEnteRequest aggiornaEnteRequest ) throws PersistenceException {

        Logger logger = getLogger ( "ejb" );

        ResponseType response = returnStandardSuccessfulOutput ();

        //CARICAMENTO PROGRESSIVO
        logger.debug ( "Creazione enteTemp" );

        String cfEnte  = aggiornaEnteRequest.getEnte ().getCodiceFiscale ();
        String ibanOld = aggiornaEnteRequest.getEnte ().getIbanTesoreria ();
        String ibanNew = aggiornaEnteRequest.getEnte ().getIban ();

        if(ibanOld == null) {
            ibanOld = "";
        }

        MdpTEnte ente = delegate.findOneEnte ( cfEnte );

        Integer progressivoApp = getEnteProgressivoApplicationIdZeroBased ( cfEnte );

        String applicationIdWork = getApplicationId ( cfEnte, ibanOld);

        //Devo staccare un application solo se applicationIdWork NON e' valorizzato
        //ossia se NON c'e' una application associata all'iban tesoreria originale
        //Cambiato o meno non importa l'application si stacca solo se non ne
        //esiste uno associato a un iban precedente
        //Nel caso dell'ente l'application e' sempre e solo 1

        if (StringUtils.isEmpty ( applicationIdWork ) ) {
            progressivoApp += 1;
            applicationIdWork = getAppIdCustom ( cfEnte, progressivoApp );
        }

        //CREAZIONE ENTE TEMP
        MdpTEnteTemp enteTemp = new MdpTEnteTemp ();

        if(progressivoApp == 0) {
            progressivoApp = 1;
        }

        if ( ente == null ) {
            //Costruisco l'ente temp da zero, l'ente_id viene settato in fase di conferma
            enteTemp.setAttivo ( "1" );
            enteTemp.setEnte_id ( "0000" );
        } else {
            //Costruisco l'ente temp a partire dall'ente esistente
            enteTemp = TempUtility.buildEnteTemp ( ente, progressivoApp );
        }

        logger.debug ( "###################################################################" );
        logger.debug ( "APPLICATION ID :" + applicationIdWork );
        logger.debug ( "IBAN OLD:" + ibanOld );
        logger.debug ( "IBAN NEW:" + ibanNew );
        logger.debug ( "###################################################################" );

        //
        //ENTE ID : IL CALCOLO E' FATTO ALL'ATTO DELLA PERSIST DELLA ENTE E IGNORO QUESTO VALORE
        //
        enteTemp.setProgressivo_application_id ( progressivoApp );
        enteTemp.setFlag_invio_flusso_base ( aggiornaEnteRequest.getEnte ().isRicezioneFlussoBaseRendicontazione () );
        enteTemp.setFlag_invio_flusso_esteso ( aggiornaEnteRequest.getEnte ().isRiconciliazioneVersamenti () );
        enteTemp.setIdOperazione ( aggiornaEnteRequest.getIdOperazione () );
        enteTemp.setPartita_iva ( aggiornaEnteRequest.getEnte ().getCodiceFiscale () );
        enteTemp.setDescrizione ( aggiornaEnteRequest.getEnte ().getDenominazione ());
        enteTemp.setFlag_gestione_ppay ( aggiornaEnteRequest.getEnte ().isFlagGestionePpay () );

        delegate.persistEnteTemp ( enteTemp );

        try {
            insertUpdateEnte ( applicationIdWork, enteTemp, aggiornaEnteRequest);
        } catch ( Exception e ) {
            logger.error ( "Errore in fase di modifica ente related : ", e );
            return returnErrorOutput ( CostantiErrori.WS_ESITO_KO_DEFAULT );
        }

        return response;
    }

    @Override
    public ResponseType annullaOperazione ( AnnullaOperazioneRequest annullaOperazioneRequest ) throws PersistenceException {
        Logger logger = getLogger ( "ejb" );

        if ( null != annullaOperazioneRequest && StringUtils.isNotBlank ( annullaOperazioneRequest.getIdOperazione () ) ) {
            List<MdpTEnteTemp> enteTemp = null;
            List<MdpTApplicationTemp> appTemp = null;

            try {
                enteTemp = delegate.findEnteTempByIdOperazione ( annullaOperazioneRequest.getIdOperazione () );
                appTemp  = delegate.findAllApplicationTemp ( annullaOperazioneRequest.getIdOperazione () );

                if ( null != enteTemp ) {
                    ResponseType enteTempResp = deleteEntityEnteTemp ( annullaOperazioneRequest.getIdOperazione () );
                    return enteTempResp;
                } else if ( null != appTemp ) {
                    ResponseType item = deleteAppTemp ( annullaOperazioneRequest.getIdOperazione () );
                    return item;
                } else {
                    return returnErrorResponseType ( CostantiErrori.ERRORE_DATI_MANCANTI,
                        String.format ( "L'operazione con id %s non e' presente su base dati.", annullaOperazioneRequest.getIdOperazione () ) );
                }
            } catch ( Throwable t ) {
                logger.error ( "Errore in fase di annullaOperazione : ", t );
                return returnErrorOutput ( CostantiErrori.WS_ESITO_KO_DEFAULT );
            }
        } else {
            return returnErrorOutput ( CostantiErrori.WS_ESITO_KO_NO_PARAM );
        }
    }

    @Override
    public ResponseType confermaOperazione ( ConfermaOperazioneRequest confermaOperazioneRequest ) throws PersistenceException {
        ResponseType response = returnStandardSuccessfulOutput ();

        Logger logger = getLogger ( "ejb" );

        boolean REMOVE_TMP = true;

        try {
            List<MdpTEnteTemp> enteTemp = delegate.findEnteTempByIdOperazione ( confermaOperazioneRequest.getIdOperazione () );
            List<MdpTApplicationTemp> applicationTemp = delegate.findAllApplicationTemp ( confermaOperazioneRequest.getIdOperazione () );
            List<MdpTApplicationCustomFieldsTemp> appCustomTemp = delegate.findAllApplicationCustomFieldsTemp ( confermaOperazioneRequest.getIdOperazione () );

            logger.debug ( "Ente temp is null : " + ( enteTemp == null ) );
            logger.debug ( "App temp is null  : " + ( applicationTemp == null ) );
            logger.debug ( "Idoperazione      : " + confermaOperazioneRequest.getIdOperazione () );

            if ( applicationTemp != null ) {
                logger.debug ( "App temp size        : " + ( applicationTemp.size () ) );
            }
            if ( appCustomTemp   != null ) {
                logger.debug ( "App custom temp size : " + ( appCustomTemp.size () ) );
            }

            boolean tempApplicationFull = false;

            tempApplicationFull |= ( null != applicationTemp ) && ( applicationTemp.size () > 0 );
            tempApplicationFull |= ( null != appCustomTemp ) && ( appCustomTemp.size () > 0 );

            //Faccio indifferentemente confrma applications ed ente

            if ( tempApplicationFull ) {
                confermaTabelleApplicationTemp ( confermaOperazioneRequest.getIdOperazione () );
            }

            String currAppId = "";

            //Ricavo l'applicationid per usarlo sulla tabella di raccordo

            if ( applicationTemp.size () > 0 ) {
                currAppId = applicationTemp.get ( 0 ).getId ();

                if ( ( null != enteTemp ) && ( enteTemp.size () > 0 ) ) {
                    confermaEnte ( enteTemp, currAppId );
                }
            }

            if (REMOVE_TMP) {
                delegate.removeApplicationTemp ( confermaOperazioneRequest.getIdOperazione () );
                delegate.removeApplicationCustomTemp ( confermaOperazioneRequest.getIdOperazione () );
                delegate.removeApplicationDetailTemp ( confermaOperazioneRequest.getIdOperazione () );
                delegate.removeEnteTemp ( confermaOperazioneRequest.getIdOperazione () );
            }

            return returnStandardSuccessfulOutput ();
        } catch ( Throwable t ) {
            logger.error ( "Errore in fase di confermaOperazione: ", t );
            response = returnErrorOutput ( CostantiErrori.WS_ESITO_KO_DEFAULT );
        }

        return response;
    }

    private int getEnteProgressivoApplicationIdZeroBased ( String cfEnte) {

        if ( StringUtils.isEmpty ( cfEnte ) ) {
            return 1;
        }

        MdpTEnte ente = delegate.findOneEnte ( cfEnte );
        MdpTApplication application = delegate.findMaxApplication ( cfEnte );

        if(application == null) {
            return 0;
        }

        String [] app = application.getId ().split ( "_" );

        if ( ( ente != null ) && ( app != null ) && ( app.length == 3 ) ) {
            int idCorrente = ente.getProgressivo_application_id ();
            int idApplication = idCorrente;

            try {
                idApplication = Integer.parseInt ( app [2] );
            } catch ( NumberFormatException e ) {
                return 0;
            }

            return idApplication;
        }

        return 0;
    }

    private void confermaEnte ( List<MdpTEnteTemp> enteTempIn, String appId ) throws PersistenceException {

        Logger logger = getLogger ( "ejb" );

        boolean presentEnte = true;

        for ( MdpTEnteTemp enteTemp: enteTempIn ) {

            MdpTEnte enteCurrent = delegate.findOneEnte ( enteTemp.getPartita_iva () );

            //PRIMO INSERIMENTO DI UN ENTE -> INSERT

            if ( enteCurrent == null ) {
                presentEnte = false;
                enteCurrent = new MdpTEnte ();
                Integer indice = delegate.getEnteMaxId ();

                if ( null == indice ) {
                    indice = 0;
                }

                enteCurrent.setProgressivo_application_id ( 1 );
                enteCurrent.setEnte_id ( StringUtils.leftPad ( "" + ( indice + 1 ), 4, "0" ) );
            }

            MdpTEnte enteToPersist = TempUtility.buildEnte ( enteCurrent, enteTemp );

            //Calcolo in fase di insert della tabella ente il valore
            //del progressivo prendendolo direttamente dalla application
            //sulla base della partita iva incrementando di 1 il valore
            int progressivo = getEnteProgressivoApplicationIdZeroBased ( enteToPersist.getPartita_iva () );

            if(progressivo == 0) {
                progressivo = 1;
            }

            enteToPersist.setProgressivo_application_id ( progressivo );

            delegate.persistEnte ( enteToPersist, presentEnte );

            //------------------------------------------------//
            //Tabella di raccordo
            //------------------------------------------------//
            if ( ( delegate.isPresentRelazEnteApp ( enteToPersist.getEnte_id (), appId ) == false ) && ( !StringUtils.isEmpty ( appId ) ) ) {
                delegate.persistRelation ( enteToPersist.getEnte_id (), appId );
            }
        }
    }

    @SuppressWarnings ( { "unchecked", "rawtypes" } )
    private ResponseType deleteEntityEnteTemp ( String idOperazione ) throws PersistenceException {
        Logger logger = getLogger ( "ejb" );

        try {
            delegate.removeEnteTemp ( idOperazione );
            return returnStandardSuccessfulOutput ();
        } catch ( Throwable t ) {
            logger.error ( "Errore in fase di deleteEntityEnteTemp : ", t );
            return returnErrorOutput ( CostantiErrori.WS_ESITO_KO_DEFAULT );
        }
    }

    @SuppressWarnings ( { "unchecked", "rawtypes" } )
    private ResponseType deleteAppTemp ( String idOperazione ) throws PersistenceException {
        Logger logger = getLogger ( "ejb" );

        try {
            delegate.removeApplicationTemp ( idOperazione );
            delegate.removeApplicationCustomTemp ( idOperazione );
            delegate.removeApplicationDetailTemp ( idOperazione );
            return returnStandardSuccessfulOutput ();
        } catch ( Throwable t ) {
            logger.error ( "Errore in fase di deleteEntityEnteTemp : ", t );
            return returnErrorOutput ( CostantiErrori.WS_ESITO_KO_DEFAULT );
        }
    }

    private ResponseType returnErrorOutput ( String codiceErrore ) throws PersistenceException {
        ResponseType responseType = new ResponseType ();
        ResultType result = new ResultType ();
        Logger logger = getLogger ( "ejb" );

        MdpTErrore errore = delegate.findOneErroreByCodiceErrore ( codiceErrore );

        if ( null == errore || !StringUtils.isNotBlank ( errore.getDescrizioneErrore () ) ) {
            result.setCodice ( CostantiErrori.WS_ESITO_KO_DEFAULT );
            result.setMessaggio ( "Si e' verificato un errore durante l'elaborazione" );
            logger.warn (
                String.format ( "Non e' stato possibile recuperare l'errore associato al codice %s, verra' utilizzato un errore di default.", codiceErrore ) );
        } else {
            result.setCodice ( codiceErrore );
            result.setMessaggio ( errore.getDescrizioneErrore () );
        }

        responseType.setResult ( result );
        return responseType;
    }

    private ResponseType returnErrorResponseType ( String codiceErrore, String descrizione ) {
        ResponseType responseType = new ResponseType ();
        ResultType result = new ResultType ();

        if ( StringUtils.isNotBlank ( codiceErrore ) && StringUtils.isNotBlank ( descrizione ) ) {
            result.setCodice ( codiceErrore );
            result.setMessaggio ( descrizione );
        } else {
            result.setCodice ( CostantiErrori.WS_ESITO_KO_DEFAULT );
            result.setMessaggio ( "Si e' verificato un errore durante l'elaborazione" );
        }
        responseType.setResult ( result );
        return responseType;
    }

    private ResponseType returnStandardSuccessfulOutput () {
        ResponseType responseType = new ResponseType ();
        ResultType result = new ResultType ();
        result.setCodice ( CostantiErrori.WS_ESITO_OK_DEFAULT );
        result.setMessaggio ( "Operazione completata correttamente" );
        responseType.setResult ( result );
        return responseType;
    }

    @Override
    public RichiediApplicationIdResponse richiediApplicationId ( RichiediApplicationIdRequest request ) {
        RichiediApplicationIdResponse response = new RichiediApplicationIdResponse ();

        Logger logger = getLogger ( "ejb" );

        ResponseType responseType = null;

        if ( null == request || StringUtils.isBlank ( request.getCodiceFiscaleEnte () )
                        || ( StringUtils.isBlank ( request.getIbanCodiceVersamento () ) && StringUtils.isBlank ( request.getIbanEnte () ) ) ) {
            responseType = returnErrorResponseType ( CostantiErrori.WS_ESITO_KO_NO_PARAM,
                            "Parametri di ricerca minimi non forniti: valorizzare il codice fiscale dell'ente e uno tra iban ente e iban codice versamento" );
        }

        String codiceFiscaleEnte = request.getCodiceFiscaleEnte ();

        String iban = StringUtils.isBlank ( request.getIbanCodiceVersamento () ) ? request.getIbanEnte () : request.getIbanCodiceVersamento ();

        //CSI_PAG-316
        //String applicationId    = delegate.richiediApplicationId ( codiceFiscaleEnte, iban );
        //String applicationIdTmp = delegate.richiediApplicationIdTmp ( codiceFiscaleEnte, iban );
        String applicationIdNew = null;
        try {
            applicationIdNew = delegate.richiediApplicationIdNew( codiceFiscaleEnte, iban, request.getIbanAppoggio(), request.getIdOperazione() );
        } catch (MdpcoopapplicativaSrvException e) {
            responseType = returnErrorResponseType ( CostantiErrori.ERRORE_DI_SISTEMA, e.getMessage());
            response.setEsito ( responseType );
            return response;
        }

        //CSI_PAG-316
        //int progressivo = getGreaterApplicationId(applicationId, applicationIdTmp);

        //CSI_PAG-316
        //if(progressivo > 0) applicationId = "EPAY_" + codiceFiscaleEnte + "_" + progressivo;

        //logger.debug ( "PROGRESSIVO:" + applicationIdNew);

        if ( StringUtils.isBlank ( applicationIdNew ) ) {
            responseType = returnErrorResponseType ( CostantiErrori.ERRORE_DATI_MANCANTI,
                String.format ( "L'application id per l'ente %s e iban %s non e' disponibile ", codiceFiscaleEnte, iban ) );
        } else {
            response.setApplicationId ( applicationIdNew );
            responseType = returnStandardSuccessfulOutput ();
        }

        response.setEsito ( responseType );

        return response;
    }

    private int getGreaterApplicationId(String a,String b) {

        if(a==null) {
            a = "";
        }
        if(b==null) {
            b =  "";
        }

        String[] aArr = a.split ( "_" );
        String[] bArr = b.split ( "_" );

        Logger logger = getLogger ( "ejb" );

        String itemA = "";
        String itemB = "";

        logger.debug ( "RICERCA PROGRESSIVO APPLICATION: [" + a + "]-[" + b + "]");

        //EPAY_CF_PROGRESSIVO
        if(aArr.length == 3) {
            itemA = aArr[2];
        }
        if(bArr.length == 3) {
            itemB = bArr[2];
        }

        int intA = 0;
        int intB = 0;

        logger.debug ( "VALUTAZIONE: [" + itemA + "]-[" + itemB + "]");

        try {
            intA = Integer.parseInt ( itemA );
        } catch(NumberFormatException e) {}

        try {
            intB = Integer.parseInt ( itemB );
        } catch(NumberFormatException e) {}


        return Math.max ( intA, intB );
    }
}
