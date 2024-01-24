/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymdpservices.business;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.persistence.EntityExistsException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.ws.BindingProvider;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import it.csi.epay.epaymdpservices.business.dto.decoder.ErroreCodiceMessaggio;
import it.csi.epay.epaymdpservices.business.dto.decoder.ErroreCodiceMessaggioSuggerimento;
import it.csi.epay.epaymdpservices.business.dto.decoder.EsitoPagamentoAgIDDecoder;
import it.csi.epay.epaymdpservices.business.dto.multiversamento.DatiAccertamentoRPT;
import it.csi.epay.epaymdpservices.business.dto.multiversamento.DatiSingoloVersamentoRPT;
import it.csi.epay.epaymdpservices.business.dto.multiversamento.ElencoDatiVersamento;
import it.csi.epay.epaymdpservices.business.dto.multiversamento.MapEntry;
import it.csi.epay.epaymdpservices.business.dto.multiversamento.Metadata;
import it.csi.epay.epaymdpservices.business.interfaces.EpayMdpServicesBeanLocal;
import it.csi.epay.epaymdpservices.dto.epaymdpservices.ChiaveValore;
import it.csi.epay.epaymdpservices.dto.epaymdpservices.EsitoRiceviEsito;
import it.csi.epay.epaymdpservices.dto.epaymdpservices.EsitoRiceviRT;
import it.csi.epay.epaymdpservices.dto.epaymdpservices.EsitoVerificaDatiPagamento;
import it.csi.epay.epaymdpservices.dto.epaymdpservices.ParametriRiceviEsito;
import it.csi.epay.epaymdpservices.dto.epaymdpservices.ParametriRiceviRT;
import it.csi.epay.epaymdpservices.exception.EpayMdpServicesException;
import it.csi.epay.epaymdpservices.exception.ErrorParameterException;
import it.csi.epay.epaymdpservices.exception.MissingParameterException;
import it.csi.epay.epaymdpservices.util.EpayMdpServicesConstants;
import it.csi.epay.epaymdpservices.util.LoggerUtil;
import it.csi.epay.epaymdpservices.util.TeaConstants;
import it.csi.epay.epaymdpservices.util.Utility;
import it.csi.epay.epaymdpservices.webservices.epaywso.CorpoRTType;
import it.csi.epay.epaymdpservices.webservices.epaywso.CorpoRTType.ElencoRT;
import it.csi.epay.epaymdpservices.webservices.epaywso.RTType;
import it.csi.epay.epaymdpservices.webservices.epaywso.ResponseType;
import it.csi.epay.epaymdpservices.webservices.epaywso.ResultType;
import it.csi.epay.epaymdpservices.webservices.epaywso.Sportello2EPaywso;
import it.csi.epay.epaymdpservices.webservices.epaywso.Sportello2EPaywso_Service;
import it.csi.epay.epaymdpservices.webservices.epaywso.TestataRTType;
import it.csi.epay.epaymdpservices.webservices.epaywso.TrasmettiRTRequest;
import it.csi.epay.epaymdpservices.webservices.interfaces.EsitoChiediDatiPagamento;
import it.csi.epay.epaymdpservices.webservices.interfaces.TransazioneExtraAttribute;
import it.csi.epay.epayservices.interfaces.ejb.EpayMdpServicesFacade;
import it.csi.epay.epayservices.interfaces.ejb.PagamentoFacade;
import it.csi.epay.epayservices.interfaces.ejb.ParametroFacade;
import it.csi.epay.epayservices.interfaces.ejb.RichiestaRevocaFacade;
import it.csi.epay.epayservices.interfaces.exception.MoreResultException;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.interfaces.rs.exception.NotificationPriceServiceException;
import it.csi.epay.epayservices.model.AggiornamentoDatiMarcaDaBollo;
import it.csi.epay.epayservices.model.DatiSingolaRevoca;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.Errori;
import it.csi.epay.epayservices.model.EsitiRicevuti;
import it.csi.epay.epayservices.model.NotificationPriceOutput;
import it.csi.epay.epayservices.model.MarcaDigitale;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.PagamentoComponenti;
import it.csi.epay.epayservices.model.PagamentoRiferimenti;
import it.csi.epay.epayservices.model.PagamentoSecondario;
import it.csi.epay.epayservices.model.PagamentoSecondarioComponenti;
import it.csi.epay.epayservices.model.Parametro;
import it.csi.epay.epayservices.model.RegistroVersamenti;
import it.csi.epay.epayservices.model.Rr;
import it.csi.epay.epayservices.model.Rt;
import it.csi.epay.epayservices.model.StatoPagamento;
import it.csi.epay.epayservices.model.TaglioMarca;
import it.csi.epay.epayservices.model.TransazioneMdp;
import it.csi.mdp.generatedvo.marcadabollo.TipoMarcaDaBollo;
import it.csi.mdp.generatedvo.pagamenti.CtDatiSingoloPagamentoRT;
import it.csi.mdp.generatedvo.pagamenti.CtRicevutaTelematica;
import it.csi.mdp.generatedvo.pagamenti.StTipoAllegatoRicevuta;


@ConcurrencyManagement ( ConcurrencyManagementType.BEAN )
@Singleton
public class EpayMdpServicesBean implements EpayMdpServicesBeanLocal {

    private static final String ENV_PROPERTIES = "META-INF/env.properties";

    //@Autowired
    @Resource ( lookup = "java:global/epayservices/epayservices-ejb/ParametroFacade" )
    private ParametroFacade parametroFacade;

    @Resource ( lookup = "java:global/epayservices/epayservices-ejb/EpayMdpServicesFacade" )
    private EpayMdpServicesFacade mdpServiceFacade;

    @Resource ( lookup = "java:global/epayservices/epayservices-ejb/PagamentoFacade" )
    private PagamentoFacade pagamentoFacade;

    @Resource ( lookup = "java:global/epayservices/epayservices-ejb/RichiestaRevocaFacade" )
    private RichiestaRevocaFacade epayServicesRR;
    
    @Resource ( lookup = "java:global/epayservices/epayservices-ejb/ChiamataEsternaSincronaSplitFacadeV1")
    private it.csi.epay.epayservices.interfaces.ejb.v1.ChiamataEsternaSincronaSplitFacade chiamataEsternaSincronaSplitFacade;


    private static JAXBContext contextFlussoJAXBMetadata;
    private static JAXBContext contextFlussoJAXBElencoDatiVersamento;
    private static JAXBContext contextFlussoJAXBCtRicevutaTelematica;
    

    // Dichiarazione consegna lollini
    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 50, 60, TimeUnit.SECONDS, new LinkedTransferQueue<Runnable>() {
        /**
         *
         */
        private static final long serialVersionUID = 3598312414601757128L;

        @Override
        public boolean offer(Runnable e) {
            return tryTransfer(e);
        }
    });

    // istannzio il consegna lolli in modo statico con la qualcosa retry
    static {
        executor.setRejectedExecutionHandler ( new RejectedExecutionHandler () {

            @Override
            public void rejectedExecution ( Runnable r, ThreadPoolExecutor executor ) {
                LoggerUtil.info ( "coda piena e max thread raggiunti, aggiungo in coda!" );
                try {
                    executor.getQueue ().put ( r );
                } catch ( InterruptedException e ) {
                    Thread.currentThread ().interrupt ();
                }
            }
        } );
    }

    @PreDestroy
    public void onDestroInterruptThreads () {
        // per terminare quando si chiude il contesto (dentro un metodo Close)
        executor.shutdown ();
        try {
            if ( !executor.awaitTermination ( 5000, TimeUnit.MILLISECONDS ) ) {
                executor.shutdownNow ();
            }
        } catch ( InterruptedException e ) {
            Thread.currentThread ().interrupt ();
        }
    }

    @PostConstruct
    public static void init () throws JAXBException {
        contextFlussoJAXBMetadata = JAXBContext.newInstance ( Metadata.class );
        contextFlussoJAXBElencoDatiVersamento = JAXBContext.newInstance ( ElencoDatiVersamento.class );
        contextFlussoJAXBCtRicevutaTelematica = JAXBContext.newInstance ( CtRicevutaTelematica.class );
    }

    //riceviRT - Begin
    @Override
    public EsitoRiceviRT getEsitoRiceviRT ( ParametriRiceviRT parametri ) throws IllegalArgumentException, NoDataException,
    MissingParameterException, ErrorParameterException, EpayMdpServicesException {

        LoggerUtil.begin ();

        EsitoPagamentoAgIDDecoder esitoPagamentoAgIDDecoder = new EsitoPagamentoAgIDDecoder ();
        EsitoRiceviRT esitoRiceviRT = null;
        //Parametri di controllo
        Parametro passphrase = parametroFacade.ricerca ( EpayMdpServicesConstants.GRUPPO_PARAM_MDP, EpayMdpServicesConstants.PARAM_PASSPHRASE );
        if ( passphrase == null || passphrase.getValore ().equals ( "" ) ) {
            LoggerUtil.error ( "ERRORE - passphrase non valorizzato" );
            Errori errore = compilaErrore ( new Date (), EpayMdpServicesConstants.ERRORE_PASSHPRASE, null, null, parametri.getIuv (), null, 1, null );
            mdpServiceFacade.inserisciErrore ( errore );
            throw new MissingParameterException ( EpayMdpServicesConstants.ERRORE_PASSHPRASE );
        }

        if ( parametri.getApplicationId () == null || parametri.getApplicationId ().equals ( "" ) ) {
            Errori errore
            = compilaErrore ( new Date (), EpayMdpServicesConstants.ERRORE_ID_APPLICATION_IS_NECESSARY, null, null, parametri.getIuv (), null, 1, null );
            mdpServiceFacade.inserisciErrore ( errore );
            LoggerUtil.error ( "ERRORE - ERRORE - ApplicationId non valorizzato" );
            throw new MissingParameterException ( EpayMdpServicesConstants.ERRORE_ID_APPLICATION_IS_NECESSARY );
        }

        if ( parametri.getTransactionId () == null || parametri.getTransactionId ().equals ( "" ) ) {
            Errori errore
            = compilaErrore ( new Date (), EpayMdpServicesConstants.ERRORE_TRANSACTION_ID_IS_NECESSARY, null, null, parametri.getIuv (), null, 1, null );
            mdpServiceFacade.inserisciErrore ( errore );
            LoggerUtil.error ( " - ERRORE - TransactionId non valorizzato" );
            throw new MissingParameterException ( EpayMdpServicesConstants.ERRORE_TRANSACTION_ID_IS_NECESSARY );
        }

        if ( parametri.getDataOraMsgRicevuta () == null || parametri.getDataOraMsgRicevuta ().equals ( "" ) ) {
            Errori errore = compilaErrore ( new Date (), EpayMdpServicesConstants.ERRORE_ORA_MSG_IS_NECESSARY, null, null, parametri.getIuv (), null, 1, null );
            mdpServiceFacade.inserisciErrore ( errore );
            LoggerUtil.error ( " - ERRORE - DataOraMsgRicevuta non valorizzato" );
            throw new MissingParameterException ( EpayMdpServicesConstants.ERRORE_ORA_MSG_IS_NECESSARY );
        }

        if ( parametri.getIdMsgRicevuta () == null || parametri.getIdMsgRicevuta ().equals ( "" ) ) {
            Errori errore
            = compilaErrore ( new Date (), EpayMdpServicesConstants.ERRORE_ID_MSG_RICEVUTA_IS_NECESSARY, null, null, parametri.getIuv (), null, 1, null );
            mdpServiceFacade.inserisciErrore ( errore );
            LoggerUtil.error ( " - ERRORE - IdMsgRicevuta non valorizzato" );
            throw new MissingParameterException ( EpayMdpServicesConstants.ERRORE_ID_MSG_RICEVUTA_IS_NECESSARY );
        }

        /*
         * if(parametri.getTipoFirma() == null || parametri.getTipoFirma().equals("")){ errore = compilaErrore(null,
         * EpayMdpServicesConstants.ERRORE_TIPO_FIRMA_IS_NECESSARY, null, null, null, null, null, null); mdpServiceFacade.inserisciErrore(errore);
         * LoggerUtil.error(" - ERRORE - TipoFirma non valorizzato"); MissingParameterException mex = new
         * MissingParameterException(EpayMdpServicesConstants.ERRORE_TIPO_FIRMA_IS_NECESSARY); throw mex; }
         */

        if ( parametri.getIuv () == null || parametri.getIuv ().equals ( "" ) ) {
            Errori errore = compilaErrore ( new Date (), EpayMdpServicesConstants.ERRORE_IUV_IS_NECESSARY, null, null, parametri.getIuv (), null, 1, null );
            mdpServiceFacade.inserisciErrore ( errore );
            LoggerUtil.error ( " - ERRORE - Iuv non valorizzato" );
            throw new MissingParameterException ( EpayMdpServicesConstants.ERRORE_IUV_IS_NECESSARY );
        }

        if ( parametri.getCodEsitoPagamento () == null || parametri.getCodEsitoPagamento ().equals ( "" ) ) {
            Errori errore
            = compilaErrore ( new Date (), EpayMdpServicesConstants.ERRORE_COD_ESITO_PAGAM_IS_NECESSARY, null, null, parametri.getIuv (), null, 1, null );
            mdpServiceFacade.inserisciErrore ( errore );
            LoggerUtil.error ( " - ERRORE - CodiceEsitoPagamento non valorizzato" );
            throw new MissingParameterException ( EpayMdpServicesConstants.ERRORE_COD_ESITO_PAGAM_IS_NECESSARY );
        }

        if ( parametri.getDescEsitoPagamento () == null || parametri.getDescEsitoPagamento ().equals ( "" ) ) {
            Errori errore
            = compilaErrore ( new Date (), EpayMdpServicesConstants.ERRORE_DESC_ESITO_PAGAM_IS_NECESSARY, null, null, parametri.getIuv (), null, 1, null );
            mdpServiceFacade.inserisciErrore ( errore );
            LoggerUtil.error ( " - ERRORE - DescEsitoPagamento non valorizzato" );
            throw new MissingParameterException ( EpayMdpServicesConstants.ERRORE_DESC_ESITO_PAGAM_IS_NECESSARY );
        }

        if ( parametri.getIdMsgRichiesta () == null || parametri.getIdMsgRichiesta ().equals ( "" ) ) {
            Errori errore
            = compilaErrore ( new Date (), EpayMdpServicesConstants.ERRORE_ID_MSG_RICHIESTA_IS_NECESSARY, null, null, parametri.getIuv (), null, 1, null );
            mdpServiceFacade.inserisciErrore ( errore );
            LoggerUtil.error ( " - ERRORE - IdMsgRichiesta non valorizzata" );
            throw new MissingParameterException ( EpayMdpServicesConstants.ERRORE_ID_MSG_RICHIESTA_IS_NECESSARY );
        }

        if ( parametri.getTimestamp () == null || parametri.getTimestamp ().equals ( "" ) ) {
            Errori errore
            = compilaErrore ( new Date (), EpayMdpServicesConstants.ERRORE_TIMESTAMP_IS_NECESSARY, null, null, parametri.getIuv (), null, 1, null );
            mdpServiceFacade.inserisciErrore ( errore );
            LoggerUtil.error ( " - ERRORE - Timestamp non valorizzato" );
            throw new MissingParameterException ( EpayMdpServicesConstants.ERRORE_TIMESTAMP_IS_NECESSARY );
        }

        if ( parametri.getMac () == null || parametri.getMac ().equals ( "" ) ) {
            Errori errore = compilaErrore ( new Date (), EpayMdpServicesConstants.ERRORE_NO_MAC, null, null, parametri.getIuv (), null, 1, null );
            mdpServiceFacade.inserisciErrore ( errore );
            LoggerUtil.error ( " - ERRORE - Mac non valorizzato" );
            throw new MissingParameterException ( EpayMdpServicesConstants.ERRORE_NO_MAC );
        }

        //VERIFICA mac
        String macCalcolato = Utility.calcolaMAC ( passphrase.getValore (), parametri.getApplicationId (), parametri.getIuv (), parametri.getIdMsgRicevuta (),
            parametri.getTimestamp () );
        LoggerUtil.debug ( " - macCalcolato=" + macCalcolato );

        if ( !macCalcolato.equals ( parametri.getMac () ) ) {
            Errori errore = compilaErrore ( new Date (), EpayMdpServicesConstants.ERRORE_MAC_NON_RICONOSCIUTO, null, null, parametri.getIuv (), null, 1, null );
            mdpServiceFacade.inserisciErrore ( errore );
            LoggerUtil.error ( " - ERRORE - Mac non riconosciuto" );
            throw new ErrorParameterException ( EpayMdpServicesConstants.ERRORE_MAC_NON_RICONOSCIUTO );
        }
        //select su epay_t_registro_versamenti: ottengo ultimo record inserito
        RegistroVersamenti versamento = new RegistroVersamenti();
        CtRicevutaTelematica rtObj = null;
        try {
            versamento = esisteRT ( parametri.getIuv (), parametri.getTransactionId () );

            if ( StatoPagamento.SUCCESSO.getId ().equals ( versamento.getIdStato () )
                            && esitoPagamentoAgIDDecoder.isPagamentoNonEseguito ( Integer.parseInt ( parametri.getCodEsitoPagamento () ) ) ) {
                EpayMdpServicesException epx = new EpayMdpServicesException ( EpayMdpServicesConstants.ERRORE_RT_NEGATIVA_PER_PAGAMENTO_ESEGUITO );
            }

            TransazioneMdp transazione = esisteTransazione ( parametri.getTransactionId () );
            if ( transazione != null ) {
                try {
                    Unmarshaller unmarshallerPerRT = creaUnMarshallerRT ();
                    rtObj = (CtRicevutaTelematica) unmarshallerPerRT.unmarshal ( new ByteArrayInputStream ( parametri.getRtData () ) );
                    transazione.setRagioneSocialePsp ( rtObj.getIstitutoAttestante ().getDenominazioneAttestante () );
                    transazione.setIdentificativoPsp ( rtObj.getIstitutoAttestante ().getIdentificativoUnivocoAttestante ().getCodiceIdentificativoUnivoco () );
                    mdpServiceFacade.aggiornaTransazione ( transazione );
                    verificaEstrazioneMarcaDaBollo ( rtObj, parametri );
                } catch ( SAXException | JAXBException e ) {
                    LoggerUtil.error ( "IMPOSSIBILE ESEGUIRE L'UNMARSHALLING DELLA RT, Transazione non aggiornata", e );
                    throw new EpayMdpServicesException ( "IMPOSSIBILE ESEGUIRE L'UNMARSHALLING DELLA RT, Transazione non aggiornata" );
                }
            }

        } catch ( NoDataException e ) {
            Errori errore
            = compilaErrore ( new Date (), EpayMdpServicesConstants.ERRORE_NO_RECORD_TRANSACTION_FOUND, null, null, parametri.getIuv (), null, 1, null );
            mdpServiceFacade.inserisciErrore ( errore );
            LoggerUtil.error ( " - ERRORE - Non ci sono record con id transazione e iuv uguali a quelli dell'invocazione della riceviRT" );
            throw new EpayMdpServicesException ( EpayMdpServicesConstants.ERRORE_NO_RECORD_TRANSACTION_FOUND );
        }



        Pagamento p = pagamentoFacade.ricerca ( versamento.getIdPagamento () );
        //Ricercare il pagamento secondario, se presente si tratta di pagamento multibeneficiario
        PagamentoSecondario pSecondario = ricavaPagamentoSecondario ( versamento );
        boolean isRtPagamentoPrimario= (null== pSecondario || p.getIdentificativoDominio ().equals ( rtObj.getDominio ().getIdentificativoDominio () ));
        Integer idStatoPagamento
        = ricavaStatoPagamento ( parametri.getCodEsitoPagamento () , versamento.getIdPagamento (), rtObj.getDominio ().getIdentificativoDominio (),  isRtPagamentoPrimario, pSecondario );


        //Valorizzazione oggetto per inserimento su DB
        RegistroVersamenti versamentoNew = new RegistroVersamenti ();
        versamentoNew.setIdPagamento ( versamento.getIdPagamento () );
        versamentoNew.setDataOperazione ( new Date () );
        versamentoNew.setAnagraficaVersante ( versamento.getAnagraficaVersante () );
        if (!isRtPagamentoPrimario)
        {
            versamentoNew.setIdPagamentoSecondario ( pSecondario.getIdPagamentoSecondario () );
        }
        versamentoNew.setIdStato ( idStatoPagamento );

        versamentoNew.setIdTransazione ( parametri.getTransactionId () );
        versamentoNew.setIuv ( parametri.getIuv () );
        versamentoNew.setRisultato ( parametri.getDescEsitoPagamento () );
        versamentoNew.setOrigineInserimento ( EpayMdpServicesConstants.ORIGINE_INSERIMENTO );

        Long idRegistro = pagamentoFacade.inserisciRegistroVersamentiEGestioneStato ( versamentoNew );

        LoggerUtil.debug ( " - idRegistro=" + idRegistro );

        //Inserimento oggetto su DB (epay_t_rt):
        Rt rt = new Rt ();
        rt.setIdRegistro ( idRegistro );
        rt.setCodEsitoPagamento ( Integer.parseInt ( parametri.getCodEsitoPagamento () ) );
        LoggerUtil.debug ( parametri.getDataOraMsgRicevuta () );
        try {
            Date dtMsgRicevuta = Utility.testISO8601 ( parametri.getDataOraMsgRicevuta () );
            Timestamp dtTimeMsgRicevuta = new Timestamp ( dtMsgRicevuta.getTime () );
            LoggerUtil.debug ( " - dtMsgRicevutaa " + dtMsgRicevuta );
            rt.setDataoraMsgRicevuta ( dtTimeMsgRicevuta );
        } catch ( IllegalArgumentException e ) {
            Errori errore = compilaErrore ( new Date (), EpayMdpServicesConstants.ERRORE_FORMATO_ORA_MSG, null, null, parametri.getIuv (), null, 1, null );
            mdpServiceFacade.inserisciErrore ( errore );
            LoggerUtil.error ( " - Errore formato DataOraMsgRicevuta " + parametri.getDataOraMsgRicevuta () );
            throw new EpayMdpServicesException ( EpayMdpServicesConstants.ERRORE_FORMATO_ORA_MSG );
        }
        rt.setDescEsitoPagamento ( parametri.getDescEsitoPagamento () );
        rt.setIdApplicazione ( parametri.getApplicationId () );
        rt.setIdMsgRicevuta ( parametri.getIdMsgRicevuta () );
        rt.setIdMsgRichiesta ( parametri.getIdMsgRichiesta () );
        rt.setIdTransazione ( parametri.getTransactionId () );
        rt.setIuv ( parametri.getIuv () );
        rt.setRicevutaPdf ( null );//Da valorizzare al di fuori di questa procedura
        rt.setRtXml ( parametri.getRtData () );
        rt.setTipoFirma ( parametri.getTipoFirma () );

        // RDI-23 2019
        if ( rtObj != null ) {
            if ( rtObj.getDatiPagamento () != null ) {
                rt.setImportoTotalePagato ( rtObj.getDatiPagamento ().getImportoTotalePagato () );
            }
        }

        Long idRt = inserisciRT ( rt );
        LoggerUtil.debug ( " - idRt=" + idRt );


        //prima di creare il WS e fare qualsiasi controllo verifico se la RT deve essere inviata.
        //        Pagamento p = pagamentoFacade.ricerca ( versamento.getIdPagamento () );


        // CSI_PAG-467 (04/2020):
        // continuo con l'invio a wso solo se il tipo pagamento consente l'invio della RT

        if ( null != p && p.getTipoPagamento () != null && Boolean.TRUE.equals ( p.getTipoPagamento ().getFlagInvioRT () ) ) {
            //Dopo la ricezione dell'RT passo a invocare il servizio per spedire
            //a WSO2 l'RT appena inserita
            LoggerUtil.debug ( "Tentativo di invio in modo async. RT: "+ rt.getIuv () + " Transaction_id: " + rt.getIdTransazione ());
            executor.submit(new Runnable() {

                @Override
                public void run () {
                    try {
                        LoggerUtil.debug ( "Invio in modo async: " + rt.getIuv () + " Transaction_id: " + rt.getIdTransazione ());
                        inviaRT2Wso2 ( p, rt );
                    } catch ( Exception e ) {
                        LoggerUtil.error ( "Errore in fase di invio a wso2 della RT con iuv: " + rt.getIuv () + " Transaction_id: " + rt.getIdTransazione (), e);
                    }
                }
            });
        } else {
            LoggerUtil.debug ( "RT con iuv: "+ parametri.getIuv () +" e transaction id: "+ parametri.getTransactionId () + " non inviata a WSO");
        }

        esitoRiceviRT = compilaEsitoRiceviRT ( EpayMdpServicesConstants.ESITO_OK, "", "" );

        LoggerUtil.end ();

        return esitoRiceviRT;
    }

    private Integer ricavaStatoPagamento ( String codiceEsitoPagamentoAttuale, Long idPagamentoPrimario,
        String identificativoDominio, boolean isRtPagamentoPrimario, PagamentoSecondario pSecondario )  {
        EsitoPagamentoAgIDDecoder esitoPagamentoAgIDDecoder = new EsitoPagamentoAgIDDecoder ();
        Integer idStatoPagamento = null;
        if (null!= pSecondario)
        {
            Rt rtPrecedente= null;
            if (isRtPagamentoPrimario)
            {
                try {
                    rtPrecedente= pagamentoFacade.ricercaRtPerPagamentoEPagamentoSecondario ( idPagamentoPrimario, pSecondario.getIdPagamentoSecondario () );
                } catch ( NoDataException e ) {
                    return  StatoPagamento.IN_ATTESA_SECONDA_RT.getId ();
                }
            }
            else
            {
                if (!pSecondario.getIdentificativoDominio ().equals ( identificativoDominio ))
                {
                    Errori errore = compilaErrore ( new Date (), EpayMdpServicesConstants.ERRORE_RT_NON_ASSOCIABILE_PAGAMENTO, idPagamentoPrimario, null, null, null, 1, null );
                    mdpServiceFacade.inserisciErrore ( errore );
                    LoggerUtil.error ( " -  Rt non associabile a nessun pagamento " + idPagamentoPrimario );
                    throw new EpayMdpServicesException ( EpayMdpServicesConstants.ERRORE_RT_NON_ASSOCIABILE_PAGAMENTO );
                }
                try {
                    rtPrecedente= pagamentoFacade.ricercaRtPerPagamentoPrimario ( idPagamentoPrimario );
                } catch ( NoDataException e ) {
                    return  StatoPagamento.IN_ATTESA_SECONDA_RT.getId ();
                }
            }

            //Inserire controlli su risultato rt
            //Da veriricare. Il controllo potrebbe essere fatto direttamente inserendo una condizione nella query
            //            lo mantengo qui per ora in attesa di sapere se i controlli devono essere modificati
            if (( esitoPagamentoAgIDDecoder.isPagamentoEseguito ( Integer.parseInt (codiceEsitoPagamentoAttuale ) )
                            && !esitoPagamentoAgIDDecoder.isPagamentoEseguito ( rtPrecedente.getCodEsitoPagamento  () )  )
                            ||
                            ( esitoPagamentoAgIDDecoder.isPagamentoEseguito ( rtPrecedente.getCodEsitoPagamento ()  )
                                            && !esitoPagamentoAgIDDecoder.isPagamentoEseguito ( Integer.parseInt (codiceEsitoPagamentoAttuale) ) ) )
            {
                return  StatoPagamento.IN_ATTESA_SECONDA_RT.getId ();
            }
        }

        if ( esitoPagamentoAgIDDecoder.isPagamentoEseguito ( Integer.parseInt (codiceEsitoPagamentoAttuale ) ) ) {
            idStatoPagamento=  StatoPagamento.SUCCESSO.getId () ;
        } else if ( esitoPagamentoAgIDDecoder.isPagamentoNonEseguito ( Integer.parseInt ( codiceEsitoPagamentoAttuale) ) ) {
            idStatoPagamento=  StatoPagamento.FALLITO.getId () ;
        } else {
            idStatoPagamento=  StatoPagamento.NON_DEFINITO.getId ();
        }
        return idStatoPagamento;
    }

    private PagamentoSecondario ricavaPagamentoSecondario ( RegistroVersamenti versamento ) {
        PagamentoSecondario pSecondario= null;
        try {
            pSecondario = pagamentoFacade.ricercaPagamentoSecondarioByIdPagamentoPrincipale ( versamento.getIdPagamento () );
        } catch ( IllegalArgumentException e1 ) {
            Errori errore = compilaErrore ( new Date (), EpayMdpServicesConstants.ERRORE_COD_ESITO_PAGAM_IS_NECESSARY, versamento.getIdPagamento (), null,null, null, 1, null );
            mdpServiceFacade.inserisciErrore ( errore );
            LoggerUtil.error ( " - Errore parametri ingresso ricerca pagamento secondario " + versamento.getIdPagamento () );
            throw new EpayMdpServicesException ( EpayMdpServicesConstants.ERRORE_FORMATO_ORA_MSG );
        } catch ( MoreResultException e1 ) {
            Errori errore = compilaErrore ( new Date (), EpayMdpServicesConstants.ERRORE_COD_ESITO_PAGAM_IS_NECESSARY, versamento.getIdPagamento (), null,null, null, 1, null );
            mdpServiceFacade.inserisciErrore ( errore );
            LoggerUtil.error ( " - Errore atteso un solo risultato " + versamento.getIdPagamento () );
        }
        return pSecondario;
    }



    /**
     * Controlla se la RT afferisce ad una marca da bollo e ne estrae lo IUBD per la registrazione sul DB
     *
     * @param rtObj
     * @throws JAXBException
     * @throws SAXException
     * @throws NoDataException
     */
    private void verificaEstrazioneMarcaDaBollo ( CtRicevutaTelematica rtObj, ParametriRiceviRT parametri ) throws NoDataException {

        for ( CtDatiSingoloPagamentoRT pagamento: rtObj.getDatiPagamento ().getDatiSingoloPagamento () ) {

            if ( pagamento.getAllegatoRicevuta () != null && StTipoAllegatoRicevuta.BD.equals ( pagamento.getAllegatoRicevuta ().getTipoAllegatoRicevuta () ) ) {

                try {
                    //reperimento elemento tramite DOM
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    ByteArrayInputStream bis = new ByteArrayInputStream ( pagamento.getAllegatoRicevuta ().getTestoAllegato () ) ;
                    Document doc = db.parse(bis);
                    NodeList iubdList = doc.getElementsByTagName("IUBD");
                    Node iubd = iubdList.item(0);
                    if (iubd == null )
                        throw new NoDataException("Non trovato lo IUBD nell'allegato della marca da bollo");
                    // una volta trovato il valore aggiorna epay_t_marca
                    LoggerUtil.info("TROVATO IUBD = " + iubd.getTextContent() +" PER IUV = " + rtObj.getDatiPagamento ().getIdentificativoUnivocoVersamento ());
                    AggiornamentoDatiMarcaDaBollo marca = new AggiornamentoDatiMarcaDaBollo ();
                    marca.setIuv ( rtObj.getDatiPagamento ().getIdentificativoUnivocoVersamento () );
                    marca.setIubd ( iubd.getTextContent() );
                    mdpServiceFacade.aggiornaMarcaDaBollo ( marca );

                    //	                gestioneRegistroVersamentiMarca ( rtObj, parametri );
                } catch (IOException | ParserConfigurationException | SAXException e) {
                    LoggerUtil.error("ERRORE ELABORANDO L'XML DELLA MARCA DA BOLLO ALL'INTERNO DELLA RICEVUTA", e);
                    throw new NoDataException("ERRORE ELABORANDO L'XML DELLA MARCA DA BOLLO ALL'INTERNO DELLA RICEVUTA", e);
                }
            }
        }

    }

    /**
     * Gestione del registro versamenti per le marche da bollo
     *
     * @param rtObj
     * @param parametri
     * @throws NoDataException
     */
    private void gestioneRegistroVersamentiMarca ( CtRicevutaTelematica rtObj, ParametriRiceviRT parametri ) throws NoDataException {
        RegistroVersamenti versamento = esisteRT ( rtObj.getDatiPagamento ().getIdentificativoUnivocoVersamento (), parametri.getTransactionId () );
        EsitoPagamentoAgIDDecoder esitoPagamentoAgIDDecoder = new EsitoPagamentoAgIDDecoder ();
        RegistroVersamenti versamentoNew = new RegistroVersamenti ();
        versamentoNew.setIdPagamento ( versamento.getIdPagamento () );
        versamentoNew.setDataOperazione ( new Date () );
        versamentoNew.setAnagraficaVersante ( versamento.getAnagraficaVersante () );
        //Esito Pagamento
        if ( esitoPagamentoAgIDDecoder.isPagamentoEseguito ( Integer.parseInt ( parametri.getCodEsitoPagamento () ) ) ) {
            versamentoNew.setIdStato ( StatoPagamento.SUCCESSO.getId () );
        } else if ( esitoPagamentoAgIDDecoder.isPagamentoNonEseguito ( Integer.parseInt ( parametri.getCodEsitoPagamento () ) ) ) {
            versamentoNew.setIdStato ( StatoPagamento.FALLITO.getId () );
        } else {
            versamentoNew.setIdStato (  StatoPagamento.NON_DEFINITO.getId () );
        }
        versamentoNew.setIdTransazione ( versamento.getIdTransazione () );
        versamentoNew.setIuv ( versamento.getIuv () );
        versamentoNew.setRisultato ( parametri.getDescEsitoPagamento () );
        versamentoNew.setOrigineInserimento ( EpayMdpServicesConstants.ORIGINE_INSERIMENTO );

        Long idRegistro = pagamentoFacade.inserisciRegistroVersamentiEGestioneStato ( versamentoNew );

        LoggerUtil.debug ( " - idRegistro=" + idRegistro );
    }

    private void inviaRT2Wso2 ( Pagamento p, Rt rt ) {
        try {
            LoggerUtil.debug ( "Lettura da " + ENV_PROPERTIES );

            InputStream inputStream = this.getClass ().getClassLoader ().getResourceAsStream ( ENV_PROPERTIES );

            if ( inputStream == null ) {
                LoggerUtil.debug ( "Input stream configurazione non trovato" );
            }

            Properties properties = new Properties ();
            properties.load ( inputStream );
            String url = properties.getProperty ( "sportello2epaywso.wsdl" );
            LoggerUtil.info ( "url wso : " + url );

            TrasmettiRTRequest req = createSoapRequest ( p, rt );

            if ( req != null ) {
                callService ( url, req );
            }
        } catch ( Exception e ) {
            LoggerUtil.error ( "errore in inviaRT2Wso2: " + e.getMessage (), e );
            throw new RuntimeException ( "Errore in inviaRT2Wso2: " + e.getMessage (), e );
        }
    }

    private ResultType callService ( String url, TrasmettiRTRequest trasmettiRTRequest ) {
        URL urlService = null;
        try {
            urlService = new URL ( url );
        } catch ( MalformedURLException e ) {
            throw new RuntimeException ( "Errore nel parsing della url: " + url );
        }
        Sportello2EPaywso_Service service = new Sportello2EPaywso_Service ( urlService );
        Sportello2EPaywso sportello2EPaywso = service.getSportello2EPaywsoSOAP ();

        String urlNoWsdl = url.replace ( "?wsdl", "" ).replace ( "?WSDL", "" );
        ( (BindingProvider) sportello2EPaywso ).getRequestContext ().put ( BindingProvider.ENDPOINT_ADDRESS_PROPERTY, urlNoWsdl );

        ResponseType responseType = sportello2EPaywso.trasmettiRT ( trasmettiRTRequest );
        return responseType.getResult ();
    }

    private TrasmettiRTRequest createSoapRequest ( Pagamento p, Rt rt ) throws IllegalArgumentException, NoDataException {
        if ( rt == null ) {
            return null;
        }

        TrasmettiRTRequest req = new TrasmettiRTRequest ();

        TestataRTType testata = new TestataRTType ();
        CorpoRTType corpo = new CorpoRTType ();
        ElencoRT eRT = new ElencoRT ();
        RTType rtCurr = new RTType ();

        testata.setCFEnteCreditore ( p.getEnte ().getCodiceFiscale () );
        testata.setCodiceVersamento ( p.getTipoPagamento ().getCodiceVersamento () );

        String time = "" + ( new Date ().getTime () );
        int late = time.length () - 7;
        if ( late <= 0 ) {
            late = 0;
        }
        String tuid = "TRT" + rt.getIuv () + time.substring ( late );

        //3 + 25 + 7 =

        //IUV = 25
        //IDRT = 19
        //TRT = 3
        //TIME = 13 -> 1576227543750
        //->35 == DIM SU DB DI IDMESSAGGIO
        testata.setIdMessaggio ( tuid );
        testata.setNumeroRT ( 1 );

        rtCurr.setId ( "" + tuid );
        rtCurr.setXML ( rt.getRtXml () );

        eRT.getRT ().add ( rtCurr );
        corpo.setElencoRT ( eRT );
        req.setTestata ( testata );
        req.setCorpoRT ( corpo );

        LoggerUtil.debug ( "CF Ente - " + req.getTestata ().getCFEnteCreditore () );
        LoggerUtil.debug ( "CVers - " + req.getTestata ().getCodiceVersamento () );
        LoggerUtil.debug ( "IdMess - " + req.getTestata ().getIdMessaggio () );
        LoggerUtil.debug ( "NumRT - " + req.getTestata ().getNumeroRT () );
        LoggerUtil.debug ( "ID - " + rtCurr.getId () );
        return req;
    }

    @Override
    public EsitoVerificaDatiPagamento verificaDatiPagamento ( String iuv, String timestamp, String mac )
                    throws IllegalArgumentException, NoDataException, MissingParameterException, ErrorParameterException, EpayMdpServicesException {
        LoggerUtil.begin ();

        EsitoVerificaDatiPagamento esito = new EsitoVerificaDatiPagamento ();
        esito.setEsito ( EpayMdpServicesConstants.ESITO_KO );

        Parametro passphrase;
        Pagamento pagamento = null;
        Long idPagamento = null;
        Long idRegistroVersamento = null;
        Long idTransazione = null;
        String macCalcolato = null;
        ErroreCodiceMessaggio codificaErrore = null;

        try {
            if ( LoggerUtil.isDebugEnabled () ) {
                LoggerUtil.debug ( "step 1 - ricerca parametro passphrase" );
            }
            passphrase = parametroFacade.ricerca ( EpayMdpServicesConstants.GRUPPO_PARAM_MDP, EpayMdpServicesConstants.PARAM_PASSPHRASE );
        } catch ( NoDataException e ) {
            LoggerUtil.warn ( "La ricerca della passhprase ha sollevato NoDataException" );
            passphrase = null;
        } catch ( Throwable e ) {
            passphrase = null;
            LoggerUtil.error ( "Errore nella ricerca della passphrase", e );
            codificaErrore
            = new ErroreCodiceMessaggio ( EpayMdpServicesConstants.PAA_SYSTEM_ERROR, "Si e' verificato un errore nella ricerca della passhprase." );
        }

        if ( codificaErrore == null ) {
            if ( passphrase == null ) {
                codificaErrore = new ErroreCodiceMessaggioSuggerimento ( EpayMdpServicesConstants.PAA_SYSTEM_ERROR,
                    "Non e' stata configurata nessuna passhprase.", "configurare correttamente il parametro " + EpayMdpServicesConstants.GRUPPO_PARAM_MDP + "."
                                    + EpayMdpServicesConstants.PARAM_PASSPHRASE );
            }
        }

        if ( codificaErrore == null ) {
            if ( LoggerUtil.isDebugEnabled () ) {
                LoggerUtil.debug ( "step 2 - ricerca pagamento per IUV" );
            }
            try {
                pagamento = pagamentoFacade.ricercaOttimizzata ( iuv );
                idPagamento = pagamento.getIdPagamento ();
                if (Boolean.TRUE.equals(pagamento.getRequiresCostUpdate())) {
                    
                    codificaErrore= attualizzaImporto ( iuv, pagamento );
                }
            } catch ( NoDataException e ) {
                LoggerUtil.warn ( "La ricerca del pagamento ha sollevato NoDataException" );
                pagamento = null;
            } catch ( Throwable t ) {
                LoggerUtil.error ( "Errore imprevisto nella ricerca del pagamento", t );
                codificaErrore
                = new ErroreCodiceMessaggio ( EpayMdpServicesConstants.PAA_SYSTEM_ERROR, "Si e' verificato un errore nella ricerca del pagamento." );
            }
        }

        if ( codificaErrore == null ) {
            if ( pagamento == null ) {
                codificaErrore = new ErroreCodiceMessaggioSuggerimento ( EpayMdpServicesConstants.PAA_PAGAMENTO_SCONOSCIUTO, "Pagamento non trovato.",
                                "fornire uno IUV valido" );
            }
        }

        if ( codificaErrore == null ) {
            if ( LoggerUtil.isDebugEnabled () ) {
                LoggerUtil.debug ( "step 3 - calcolo MAC modello 3" );
            }
            try {
                macCalcolato = Utility.calcolaMACModello3 ( passphrase.getValore (), iuv, timestamp );
            } catch ( Throwable t ) {
                LoggerUtil.error ( "Errore nel calcolo del MAC modello 3", t );
                codificaErrore = new ErroreCodiceMessaggio ( EpayMdpServicesConstants.PAA_SYSTEM_ERROR,
                                "Si e' verificato un errore nel calcolo del MAC modello 3." );
                macCalcolato = null;
            }
        }

        if ( codificaErrore == null ) {
            if ( LoggerUtil.isDebugEnabled () ) {
                LoggerUtil.debug ( "step 4 - verifiche pagamento modello 3" );
            }
            try {
                codificaErrore = verifichePagamentoModello3 ( iuv, timestamp, mac, pagamento, passphrase, macCalcolato );
            } catch ( Throwable t ) {
                LoggerUtil.error ( "Errore imprevisto nelle verifiche dei dati pagamento modello 3", t );
                codificaErrore = new ErroreCodiceMessaggio ( EpayMdpServicesConstants.PAA_SYSTEM_ERROR,
                                "Si e' verificato un errore imprevisto nelle verifiche dei dati pagamento modello 3." );
            }
        }

        if ( codificaErrore == null ) {
            if ( LoggerUtil.isDebugEnabled () ) {
                LoggerUtil.debug ( "step 5 - verifica stato di pagabilita' del pagamento" );
            }
            try {
                if ( ( StatoPagamento.findById ( pagamento.getIdStatoCorrente () ) ).isPagabile () ) {
                    esito.setImportoDovuto ( String.valueOf ( pagamento.getImporto () ) );
                    esito.setCausaleVersamento ( StringUtils.substring ( pagamento.getCausale (), 0, 140 ) );
                    esito.setTimestamp ( Utility.getTimestamp () );
                    esito.setMac ( Utility.generaMACVerificaDatiPagamento ( passphrase.getValore (), iuv, esito.getTimestamp () ) );
                    esito.setDataFineValiditaPagamento ( pagamento.getFineValidita () );
                    esito.setDescrizionePagamento ( pagamento.getCausale () );
                } else {
                    codificaErrore = new ErroreCodiceMessaggioSuggerimento ( EpayMdpServicesConstants.PAA_SYSTEM_ERROR,
                        "Stato del pagamento non pagabile", "verificare che lo stato del pagamento abbia il flag pagabile a true" );
                }
            } catch ( Throwable t ) {
                LoggerUtil.error ( "Errore imprevisto nella verifica dello stato di pagabilita' del pagamento", t );
                codificaErrore = new ErroreCodiceMessaggioSuggerimento ( EpayMdpServicesConstants.PAA_SYSTEM_ERROR,
                    "Si e' verificato un errore imprevisto nella verifica dello stato di pagabilita' del pagamento.",
                                "verificare che lo stato esista, sia previsto in enum StatoPagamento e sia pagabile" );
            }
        }

        if ( codificaErrore == null ) {
            if ( LoggerUtil.isDebugEnabled () ) {
                LoggerUtil.debug ( "step 6 - inserimento del registro versamento" );
            }
            try {
                RegistroVersamenti registroVersamento = new RegistroVersamenti ();
                registroVersamento.setAnagraficaVersante ( pagamento.getVersante () );
                registroVersamento.setDataOperazione ( new Date () );
                registroVersamento.setIdPagamento ( pagamento.getIdPagamento () );
                registroVersamento.setIdStato ( StatoPagamento.DA_PAGARE.getId () );
                registroVersamento.setIuv ( iuv );
                registroVersamento.setOrigineInserimento ( EpayMdpServicesConstants.ORIGINE_MDPSERVICES_VERIFICA_DATI_PAGAMENTO );
                idRegistroVersamento = pagamentoFacade.inserisciRegistroVersamentiEGestioneStato ( registroVersamento );
            } catch ( Throwable t ) {
                LoggerUtil.error ( "Errore imprevisto nell'inserimento del registro versamento", t );
                codificaErrore = new ErroreCodiceMessaggio ( EpayMdpServicesConstants.PAA_SYSTEM_ERROR,
                                "Si e' verificato un errore imprevisto nell'inserimento del registro versamento" );
            }
        }

        if ( codificaErrore != null ) {
            // AB - EPAY-257: Inserito riga su registro versamenti anche se non pagabile.
            if ( null != pagamento ) {
                try {
                    RegistroVersamenti registroVersamento = new RegistroVersamenti ();
                    registroVersamento.setAnagraficaVersante ( pagamento.getVersante () );
                    registroVersamento.setDataOperazione ( new Date () );
                    registroVersamento.setIdPagamento ( pagamento.getIdPagamento () );
                    registroVersamento.setIdStato ( pagamento.getIdStatoCorrente () );
                    registroVersamento.setIuv ( iuv );
                    registroVersamento.setOrigineInserimento ( EpayMdpServicesConstants.ORIGINE_MDPSERVICES_VERIFICA_DATI_PAGAMENTO );
                    idRegistroVersamento = pagamentoFacade.inserisciRegistroVersamentiEGestioneStato ( registroVersamento );
                } catch ( Throwable t ) {
                    LoggerUtil.error ( "Errore imprevisto nell'inserimento del registro versamento", t );
                    codificaErrore = new ErroreCodiceMessaggio ( EpayMdpServicesConstants.PAA_SYSTEM_ERROR,
                                    "Si e' verificato un errore imprevisto nell'inserimento del registro versamento" );
                }
            }
            String descCorrezione = null;
            if ( codificaErrore instanceof ErroreCodiceMessaggioSuggerimento ) {
                descCorrezione = ( (ErroreCodiceMessaggioSuggerimento) codificaErrore ).getSuggerimento ();
            }
            Errori errore = compilaErrore ( new Date (), codificaErrore.getMessaggioErrore (), idPagamento, idRegistroVersamento,
                iuv, idTransazione, 1, descCorrezione );

            try {
                LoggerUtil.dumpObject ( "Errore da persistere in MDP", errore );
                mdpServiceFacade.inserisciErrore ( errore );
            } catch ( Exception e ) {
                LoggerUtil.error ( "Errore nell'inserimento del log errore in MDP", e );
            }

            LoggerUtil.warn ( " - ERRORE NELL'ELABORAZIONE - " + codificaErrore.getMessaggioErrore () );
            esito.setEsito ( EpayMdpServicesConstants.ESITO_KO );
            esito.setCodiceErrore ( codificaErrore.getCodice () );
            esito.setMessaggioErrore ( codificaErrore.getMessaggioErrore () );
            return esito;
        } else {
            esito.setEsito ( EpayMdpServicesConstants.ESITO_OK );
            esito.setCodiceErrore ( null );
            esito.setMessaggioErrore ( null );
        }

        LoggerUtil.dumpObject ( "Risposta: ", esito );
        LoggerUtil.end ();
        return esito;
    }

    @Override
    public EsitoChiediDatiPagamento chiediDatiPagamento ( String iuv, String timestamp, String mac, String importoVersamento, String transactionId )
                    throws IllegalArgumentException, NoDataException, MissingParameterException, ErrorParameterException, EpayMdpServicesException {
        LoggerUtil.begin ();
        Pagamento pagamento = null;

        EsitoChiediDatiPagamento risposta = new EsitoChiediDatiPagamento ();
        risposta.setEsito ( EpayMdpServicesConstants.ESITO_KO );
        ErroreCodiceMessaggio codificaErrore = null;

        Long idPagamento = null;
        Long idRegistroVersamento = null;
        Long idTransazione = null;

        Parametro passphrase;
        String macCalcolato = null;
        TransazioneMdp transazione;
        BigDecimal importoorig = null;

        try {
            if ( LoggerUtil.isDebugEnabled () ) {
                LoggerUtil.debug ( "step 1 - ricerca parametro passphrase" );
            }
            passphrase = parametroFacade.ricerca ( EpayMdpServicesConstants.GRUPPO_PARAM_MDP, EpayMdpServicesConstants.PARAM_PASSPHRASE );
        } catch ( NoDataException e ) {
            LoggerUtil.warn ( "La ricerca della passhprase ha sollevato NoDataException" );
            passphrase = null;
        } catch ( Throwable e ) {
            passphrase = null;
            LoggerUtil.error ( "Errore nella ricerca della passphrase", e );
            codificaErrore
            = new ErroreCodiceMessaggio ( EpayMdpServicesConstants.PAA_SYSTEM_ERROR, "Si e' verificato un errore nella ricerca della passhprase." );
        }

        if ( codificaErrore == null ) {
            if ( passphrase == null ) {
                codificaErrore = new ErroreCodiceMessaggioSuggerimento ( EpayMdpServicesConstants.PAA_SYSTEM_ERROR,
                    "Non e' stata configurata nessuna passhprase.", "configurare correttamente il parametro " + EpayMdpServicesConstants.GRUPPO_PARAM_MDP + "."
                                    + EpayMdpServicesConstants.PARAM_PASSPHRASE );
            }
        }

        if ( codificaErrore == null ) {
            if ( LoggerUtil.isDebugEnabled () ) {
                LoggerUtil.debug ( "step 2 - ricerca pagamento per IUV" );
            }
            try {
                pagamento = pagamentoFacade.ricerca ( iuv );
                idPagamento = pagamento.getIdPagamento ();
                 importoorig= pagamento.getImporto ();
                
                LoggerUtil.dumpObject("Pagamento con flag nuovo: ", pagamento ); //FIXME [AF] rimuovere - solo sviluppo
                if (Boolean.TRUE.equals(pagamento.getRequiresCostUpdate())) {
                	
                	codificaErrore= attualizzaImporto ( iuv, pagamento );
                }
            } catch ( NoDataException e ) {
                LoggerUtil.warn ( "La ricerca del pagamento ha sollevato NoDataException" );
                pagamento = null;
            } catch ( Throwable t ) {
                LoggerUtil.error ( "Errore imprevisto nella ricerca del pagamento", t );
                codificaErrore = new ErroreCodiceMessaggio ( EpayMdpServicesConstants.PAA_SYSTEM_ERROR, "Si e' verificato un errore nella ricerca del pagamento." );
            }
        }

        if ( codificaErrore == null ) {
            if ( pagamento == null ) {
                codificaErrore = new ErroreCodiceMessaggioSuggerimento ( EpayMdpServicesConstants.PAA_PAGAMENTO_SCONOSCIUTO, "Pagamento non trovato.",
                                "fornire uno IUV valido" );
            }
        }

        if ( codificaErrore == null ) {
            if ( LoggerUtil.isDebugEnabled () ) {
                LoggerUtil.debug ( "step 3 - calcolo MAC per richiesta" );
            }
            try {
                macCalcolato = Utility.generaMacChiediDatiPagamento ( passphrase.getValore (), iuv, importoVersamento, timestamp );
            } catch ( Throwable t ) {
                LoggerUtil.error ( "Errore nel calcolo del MAC per la richiesta", t );
                codificaErrore = new ErroreCodiceMessaggio ( EpayMdpServicesConstants.PAA_SYSTEM_ERROR,
                                "Si e' verificato un errore nel calcolo del MAC per la richiesta." );
                macCalcolato = null;
            }
        }

        if ( codificaErrore == null ) {
            if ( LoggerUtil.isDebugEnabled () ) {
                LoggerUtil.debug ( "step 4 - verifiche pagamento modello 3" );
            }
            try {
                codificaErrore = verifichePagamentoModello3 ( iuv, timestamp, mac, pagamento, passphrase, macCalcolato );
            } catch ( Throwable t ) {
                LoggerUtil.error ( "Errore imprevisto nelle verifiche dei dati pagamento modello 3", t );
                codificaErrore = new ErroreCodiceMessaggio ( EpayMdpServicesConstants.PAA_SYSTEM_ERROR,
                                "Si e' verificato un errore imprevisto nelle verifiche dei dati pagamento modello 3." );
            }
        }

        if ( codificaErrore == null ) {
            if ( LoggerUtil.isDebugEnabled () ) {
                LoggerUtil.debug ( "step 6 - generazione MAC di verifica" );
            }
            try {
                risposta.setTimestamp ( Utility.getTimestamp () );
                risposta.setMac ( Utility.generaMACVerificaDatiPagamento ( passphrase.getValore (), iuv, risposta.getTimestamp () ) );
            } catch ( Throwable t ) {
                LoggerUtil.error ( "Errore imprevisto nel calcolo del MAC di verifica", t );
                codificaErrore = new ErroreCodiceMessaggio ( EpayMdpServicesConstants.PAA_SYSTEM_ERROR,
                                "Si e' verificato un errore imprevisto nel calcolo del MAC di verifica." );
            }

        }

        if ( codificaErrore == null ) {
            if ( LoggerUtil.isDebugEnabled () ) {
                LoggerUtil.debug ( "step 7 - compilazione TEAs" );
            }
            risposta.getTea ()
            .add ( makeTea ( TeaConstants.TEA_ANAGRAFICA_PAGATORE,
                pagamento.getPagatore ().getFlagPersonaFisica () ? ( pagamento.getPagatore ().getNome () + " " + pagamento.getPagatore ().getCognome () )
                                : StringUtils.substring (pagamento.getPagatore ().getRagioneSociale (), 0, 70 ) ) );

            risposta.getTea ()
            .add ( makeTea ( TeaConstants.TEA_TIPO_IDENTIFICATIVO_UNIVOCO_PAGATORE, pagamento.getPagatore ().getFlagPersonaFisica () ? "F" : "G" ) );
            risposta.getTea ().add ( makeTea ( TeaConstants.TEA_CODICE_IDENTIFICATIVO_UNIVOCO_PAGATORE, pagamento.getPagatore ().getCodiceFiscale () ) );

            if ( !StringUtils.isEmpty ( pagamento.getPagatore ().getIndirizzo () ) ) {
                risposta.getTea ().add ( makeTea ( TeaConstants.TEA_INDIRIZZO_PAGATORE, pagamento.getPagatore ().getIndirizzo () ) );
                if ( !StringUtils.isEmpty ( pagamento.getPagatore ().getCivico () ) ) {
                    risposta.getTea ().add ( makeTea ( TeaConstants.TEA_CIVICO_PAGATORE, pagamento.getPagatore ().getCivico () ) );
                }

                if ( !StringUtils.isEmpty ( pagamento.getPagatore ().getCap () ) ) {
                    risposta.getTea ().add ( makeTea ( TeaConstants.TEA_CAP_PAGATORE, pagamento.getPagatore ().getCap () ) );
                }
            }
            
            risposta.getTea ().add ( makeTea ( TeaConstants.TEA_MAIL_PAGATORE, pagamento.getPagatore ().getEmail () ) );

            risposta.getTea ().add ( makeTea ( TeaConstants.TEA_LOCALITA_PAGATORE, pagamento.getPagatore ().getLocalita () ) );
            risposta.getTea ().add ( makeTea ( TeaConstants.TEA_PROVINCIA_PAGATORE, pagamento.getPagatore ().getProvincia () ) );
            risposta.getTea ().add ( makeTea ( TeaConstants.TEA_NAZIONE_PAGATORE, pagamento.getPagatore ().getNazione () ) );
            risposta.getTea ().add ( makeTea ( TeaConstants.TEA_CAUSALE_VERSAMENTO, pagamento.getCausale () ) );
            risposta.getTea ().add ( makeTea ( TeaConstants.TEA_IDENTIFICATIVO_UNIVOCO_VERSAMENTO, pagamento.getIuvRegistroVersamenti () ) );
            if ( !CollectionUtils.isEmpty ( pagamento.getComponenti () ) ) {
                // :TODO capire come gestilo meglio in caso di più componenti.
                risposta.getTea ()
                    .add ( makeTea ( TeaConstants.TEA_DATI_SPECIFICI_RISCOSSIONE, pagamento.getComponenti ().get ( 0 ).getDatiSpecificiRiscossione () ) );
            } else {
                risposta.getTea ()
                    .add ( makeTea ( TeaConstants.TEA_DATI_SPECIFICI_RISCOSSIONE, pagamento.getTipoPagamento ().getDatiSpecificiRiscossione () ) );
            }
            
            risposta.getTea ().add ( makeTea ( TeaConstants.TEA_AUTENTICAZIONE_SOGGETTO, "N/A" ) );
            //campi nuovi
            if ( null == pagamento.getTipoPagamento ().getFlagMultibeneficiario () )
                pagamento.getTipoPagamento ().setFlagMultibeneficiario ( Boolean.FALSE );
            risposta.getTea ().add ( makeTea ( TeaConstants.TEA_MULTIBENEFICIARIO, pagamento.getTipoPagamento ().getFlagMultibeneficiario ().toString () ) );
            risposta.getTea ().add ( makeTea ( TeaConstants.TEA_IMPORTO_TOT_DA_VARSARE, pagamento.getImporto ().toString () ) );

            if ( null != pagamento.getDataScadenza () ) {
                DateFormat df = new SimpleDateFormat ( "yyyy-MM-dd" );
                String dateFormat = df.format ( pagamento.getDataScadenza () );
                risposta.getTea ().add ( makeTea ( TeaConstants.TEA_DATA_FINE_VALIDITA_PAGAMENTO, dateFormat ) );
            }
            
            try 
            {
                if ( !StringUtils.isEmpty ( iuv ) )
                {
                       MarcaDigitale marca=  mdpServiceFacade.ricercaMarcaDaBolloByIuv ( iuv );
                       if (null!= marca )
                       {
                           if (!StringUtils.isEmpty ( marca.getHashDocumento () )  )
                           {
                               risposta.getTea ().add ( makeTea ( TeaConstants.TEA_HASH_DOCUMENTO, marca.getHashDocumento () ) );
                           }
                           
                           if (!StringUtils.isEmpty ( marca.getProvincia () )  )
                           {
                               risposta.getTea ().add ( makeTea ( TeaConstants.TEA_PROVINCIA_RESIDENZA, marca.getProvincia () ) );
                           }
                       }
                }
            }
            catch (NoDataException e) {
                LoggerUtil.debug ( "Nessuna marca da bollo " );
            }
            
            try 
            {
                if ( null!= importoVersamento )
                {
                    TaglioMarca taglioMarca=  mdpServiceFacade.ricercaTaglioMarcaByImporto ( new BigDecimal ( importoVersamento ) ) ;
                    if (null!= taglioMarca && !StringUtils.isEmpty (taglioMarca.getCodiceMarca ())   )
                    {
                        risposta.getTea ().add ( makeTea ( TeaConstants.TEA_TIPO_BOLLO, taglioMarca.getCodiceMarca ()) );
                    }
                }
            }
            catch (NoDataException e) {
                LoggerUtil.debug ( "Nessun tipo marca" );
            }
            
           
            
            

            // TODO da valorizzare in analisi non era descritto cosa mettere
            // TEA_RETENTION_DATE
            // TEA_LAST_PAYMENT

        }

        //TODO PAOLO RDI-024 - START
        if ( codificaErrore == null ) {
            if ( LoggerUtil.isDebugEnabled () ) {
                LoggerUtil.debug ( "step 8 - compilazione TEA multiversamento" );
            }

            TransazioneExtraAttribute tea = new TransazioneExtraAttribute ();
            tea.setName ( TeaConstants.TEA_MULTIVERSAMENTO );
            String xml = null;

            try {
                LoggerUtil.info ( "TEA_MULTIVERSAMENTO - CONVERSIONE BASE 64 DI XML" );
                xml = getMultiversamentoXml ( pagamento );
                if ( LoggerUtil.isDebugEnabled () ) {
                    LoggerUtil.debug ( "XML MULTIVERSAMENTO: " + xml );
                }
                if ( xml != null ) {
                    String xmlBase64 = Base64.encodeBase64String ( xml.getBytes () );
                    xmlBase64 = xmlBase64.replaceAll ( "\r", "" ).replaceAll ( "\n", "" );
                    if ( xmlBase64 != null ) {
                        tea.setValue ( xmlBase64 );
                    }
                }
                LoggerUtil.info ( "TEA_MULTIVERSAMENTO PUSHED IN TEA" );
            } catch ( JAXBException e ) {
                LoggerUtil.error ( "Si e' verificato un errore nel calcolo del TEA Multiversamento", e );
                // TODO: VERIFICARE RDI 24 PER DIRETTIVE IN CASO DI TEA NON CALCOLABILE
            }

            risposta.getTea ().add ( tea );
        }
        //TODO PAOLO RDI-024 - STOP

        if ( codificaErrore == null ) {
            if ( LoggerUtil.isDebugEnabled () ) {
                LoggerUtil.debug ( "step 9 - compilazione TEA datiAggiuntivi" );
            }

            TransazioneExtraAttribute tea = new TransazioneExtraAttribute ();
            tea.setName ( TeaConstants.TEA_DATIAGGIUNTIVI );
            String xml = null;

            try {
                LoggerUtil.info ( "TEA_DATIAGGIUNTIVI - CONVERSIONE BASE 64 DI XML" );
                xml = getDatiAggiuntiviXml ( pagamento );
                if ( LoggerUtil.isDebugEnabled () ) {
                    LoggerUtil.debug ( "XML DATIAGGIUNTIVI: " + xml );
                }
                if ( xml != null ) {
                    String xmlBase64 = Base64.encodeBase64String ( xml.getBytes () );
                    xmlBase64 = xmlBase64.replaceAll ( "\r", "" ).replaceAll ( "\n", "" );
                    if ( xmlBase64 != null ) {
                        tea.setValue ( xmlBase64 );
                    }
                }
                LoggerUtil.info ( "TEA_DATIAGGIUNTIVI PUSHED IN TEA" );
            } catch ( JAXBException e ) {
                LoggerUtil.error ( "Si e' verificato un errore nel calcolo del TEA datiAggiuntivi", e );

            }

            risposta.getTea ().add ( tea );
        }

        if ( codificaErrore == null ) {
            if ( LoggerUtil.isDebugEnabled () ) {
                LoggerUtil.debug ( "step 9 - inserimento transazione MDP" );
            }
            
            
//          MODELLO UNICO- Controllare esistenza transazionemdp, se non presente inserire, se no aggioranare
            transazione = pagamentoFacade.ricercaTransazioneMdp ( transactionId );
              
            
            if (null == transazione)
            {
                try {
                    transazione = new TransazioneMdp ();
                    transazione.setIdTransazione ( transactionId );
                    transazione.setIuv ( iuv );
                    transazione.setModelloPagamentoPsp ( 4 );
                    transazione.setTipoVersamentoPsp ( "PO" );

                    LoggerUtil.dumpObject ( "Inserimento transazione MDP", transazione );
                    pagamentoFacade.inserisciTransazioneMdp ( transazione );
                }catch ( Throwable t ) {
                    //#AC20230322 - Se la transazione esiste gia' non valorizzo codificaErrore
                    if(t.getCause() instanceof EntityExistsException) {
                        LoggerUtil.warn( "Transazione gia' presente in MDP");
                    }else {
                        LoggerUtil.error ( "Errore imprevisto nell'inserimento della transazione in MDP", t );
                        codificaErrore = new ErroreCodiceMessaggio ( EpayMdpServicesConstants.PAA_SYSTEM_ERROR,
                                        "Si e' verificato un errore imprevisto nell'inserimento della transazione in MDP." );
                    }
                } 
            }
            else if (!iuv.equals ( transazione.getIuv () ))
            {
                codificaErrore = new ErroreCodiceMessaggio ( EpayMdpServicesConstants.PAA_SYSTEM_ERROR,
                                "Lo iuv della transazione salvata non corrisponde allo iuv passato" );
            }
            else
            {
                try {
                    transazione.setModelloPagamentoPsp ( 4 );
                    transazione.setTipoVersamentoPsp ( "PO" );

                    LoggerUtil.dumpObject ( "Aggiornamento transazione MDP", transazione );
                    pagamentoFacade.aggiornaTransazioneMdp ( transazione );
                }catch ( Throwable t ) {
                    
                        LoggerUtil.error ( "Errore imprevisto nell'aggiornamento della transazione in MDP", t );
                        codificaErrore = new ErroreCodiceMessaggio ( EpayMdpServicesConstants.PAA_SYSTEM_ERROR,
                                        "Si e' verificato un errore imprevisto nell'aggiornamento della transazione in MDP." );
                }
            }
        }

        if ( codificaErrore == null ) {
            if ( LoggerUtil.isDebugEnabled () ) {
                LoggerUtil.debug ( "step 10 - inserimento registro versamento" );
            }
            try {
                RegistroVersamenti registroVersamento = new RegistroVersamenti ();
                registroVersamento.setAnagraficaVersante ( pagamento.getVersante () );
                registroVersamento.setDataOperazione ( new Date () );
                registroVersamento.setIdPagamento ( pagamento.getIdPagamento () );
                registroVersamento.setIdStato ( StatoPagamento.DA_PAGARE.getId () );
                registroVersamento.setIuv ( iuv );
                registroVersamento.setIdTransazione ( transactionId );
                registroVersamento.setOrigineInserimento ( EpayMdpServicesConstants.ORIGINE_MDPSERVICES_CHIEDI_DATI_PAGAMENTO );
                idRegistroVersamento = pagamentoFacade.inserisciRegistroVersamentiEGestioneStato ( registroVersamento );

            } catch ( Throwable t ) {
                LoggerUtil.error ( "Errore imprevisto nell'inserimento del registro versamento", t );
                codificaErrore = new ErroreCodiceMessaggio ( EpayMdpServicesConstants.PAA_SYSTEM_ERROR,
                                "Si e' verificato un errore imprevisto nell'inserimento del registro versamento." );
            }
        }

        if ( codificaErrore != null ) {
            // AB - EPAY-257: Inserito riga su registro versamenti anche se non pagabile.
            if ( null != pagamento ) {
                try {
                    RegistroVersamenti registroVersamento = new RegistroVersamenti ();
                    registroVersamento.setAnagraficaVersante ( pagamento.getVersante () );
                    registroVersamento.setIdTransazione(transactionId);
                    registroVersamento.setDataOperazione ( new Date () );
                    registroVersamento.setIdPagamento ( pagamento.getIdPagamento () );
                    registroVersamento.setIdStato ( pagamento.getIdStatoCorrente () );
                    registroVersamento.setIuv ( iuv );
                    registroVersamento.setOrigineInserimento ( EpayMdpServicesConstants.ORIGINE_MDPSERVICES_CHIEDI_DATI_PAGAMENTO );
                    idRegistroVersamento = pagamentoFacade.inserisciRegistroVersamentiEGestioneStato ( registroVersamento );
                } catch ( Throwable t ) {
                    LoggerUtil.error ( "Errore imprevisto nell'inserimento del registro versamento", t );
                    codificaErrore = new ErroreCodiceMessaggio ( EpayMdpServicesConstants.PAA_SYSTEM_ERROR,
                                    "Si e' verificato un errore imprevisto nell'inserimento del registro versamento" );
                }
            }
            String descCorrezione = null;
            if ( codificaErrore instanceof ErroreCodiceMessaggioSuggerimento ) {
                descCorrezione = ( (ErroreCodiceMessaggioSuggerimento) codificaErrore ).getSuggerimento ();
            }
            Errori errore = compilaErrore ( new Date (), codificaErrore.getMessaggioErrore (), idPagamento, idRegistroVersamento,
                iuv, idTransazione, 1, descCorrezione );

            try {
                LoggerUtil.dumpObject ( "Errore da persistere in MDP", errore );
                mdpServiceFacade.inserisciErrore ( errore );
            } catch ( Exception e ) {
                LoggerUtil.error ( "Errore nell'inserimento del log errore in MDP", e );
            }

            LoggerUtil.warn ( " - ERRORE NELL'ELABORAZIONE - " + codificaErrore.getMessaggioErrore () );
            risposta.setEsito ( EpayMdpServicesConstants.ESITO_KO );
            risposta.setCodErrore ( codificaErrore.getCodice () );
            risposta.setMessaggioErrore ( codificaErrore.getMessaggioErrore () );
        } else {
            if ( StringUtils.isBlank ( importoVersamento ) || BigDecimal.ZERO.setScale ( 2 ).equals ( new BigDecimal ( importoVersamento ).setScale ( 2 ) )
                || importoorig.setScale ( 2 ).compareTo ( new BigDecimal ( importoVersamento ).setScale ( 2 ) ) != 0 ) {
                risposta.setMessaggioErrore (
                    "Pagamento per iuv " + iuv + " con importo " + importoVersamento + " Non trovato. Trovato importo: " + pagamento.getImporto () );
                risposta.setEsito ( EpayMdpServicesConstants.ESITO_KO );
                risposta.setCodErrore ( EpayMdpServicesConstants.PAA_ATTIVA_RPT_IMPORTO_NON_VALIDO );
            } else {
                risposta.setEsito ( EpayMdpServicesConstants.ESITO_OK );
                risposta.setCodErrore ( null );
                risposta.setMessaggioErrore ( null );
            }
        }

        LoggerUtil.dumpObject ( "Risposta: ", risposta );
        LoggerUtil.end ();
        return risposta;
    }


    
    

    private ErroreCodiceMessaggio attualizzaImporto ( String iuv, Pagamento pagamento ) throws NotificationPriceServiceException, NoDataException {
        NotificationPriceOutput rispostaNotificationPrice = pagamentoFacade.chiamaNotificationPrice(
            pagamento.getEnte().getCodiceFiscale(), 
            iuv, 
            pagamento.getTipoPagamento ().getUrlAttualizzazionePnd () , 
            pagamento.getTipoPagamento ().getCredenzialiPnd ());
//        NotificationPriceMock mock = new NotificationPriceMock ();
//        NotificationPriceOutput rispostaNotificationPrice = mock.getNotificationSimple ();
//        NotificationPriceOutput rispostaNotificationPrice = mock.getError ();
        
        if (StringUtils.isNotEmpty(rispostaNotificationPrice.getCode())) {
           return new ErroreCodiceMessaggioSuggerimento ( EpayMdpServicesConstants.PAA_ATTIVA_RPT_IMPORTO_NON_VALIDO, "Pagamento non attualizzabile - " + rispostaNotificationPrice.getDetail(),
                            "Riprovare piu' tardi" );
        } else {
            pagamento.setRequiresCostUpdate(rispostaNotificationPrice.getRequiresCostUpdate());
            pagamento.setImporto(rispostaNotificationPrice.getImportoTotale());
//                      pagamentoFacade.attualizzaImporto(pagamento.getIdPagamento(), pagamento.getImporto(), pagamento.getRequiresCostUpdate());
            gestioneComponenti(pagamento, rispostaNotificationPrice, pagamento.getEnte());
            pagamento = pagamentoFacade.ricerca ( iuv );
        }
        return null;
    }


    private void gestioneComponenti(Pagamento pagamento, NotificationPriceOutput rispostaNotificationPrice, Ente ente) {


    	if (Boolean.TRUE.equals ( pagamento.getTipoPagamento ().getFlagMultibeneficiario () ))
        {
    		chiamataEsternaSincronaSplitFacade.aggiornaPosizioneDebitoriaMultibeneficiarioUsoInterno(NotificationPriceHelper.getAggiornaPosizioneDebitoriaMultibeneficiario(pagamento, rispostaNotificationPrice), ente, pagamento);
        } else
        {
        	chiamataEsternaSincronaSplitFacade.aggiornaPosizioneDebitoriaUsoInterno(NotificationPriceHelper.getAggiornaPosizioneDebitoriaChiamanteEsternoInput(pagamento, rispostaNotificationPrice), ente, pagamento);
        }




    }

    private TransazioneExtraAttribute makeTea ( String name, String value ) {
        TransazioneExtraAttribute tea = new TransazioneExtraAttribute ();
        tea.setName ( name );
        tea.setValue ( value );
        return tea;
    }

    private String getDatiAggiuntiviXml ( Pagamento pagamento ) throws JAXBException {
        Marshaller marshaller = contextFlussoJAXBMetadata.createMarshaller ();
        StringWriter sw = new StringWriter ();
        boolean mapEntryValue = false;

        Metadata obj = new Metadata ();
        List<MapEntry> listMap = new ArrayList<MapEntry> ();

        if ( null == pagamento.getCodicePagamentoRifEnte () && pagamento.getRiferimenti ().isEmpty () )
            return null;

        if ( null != pagamento.getCodicePagamentoRifEnte () ) {
            MapEntry map = new MapEntry ();
            map.setKey ( "identificativo posizione debitoria esterna" );
            map.setValue ( pagamento.getCodicePagamentoRifEnte () );
            listMap.add ( map );
            mapEntryValue = true;
        }
        if ( !pagamento.getRiferimenti ().isEmpty () ) {
            for ( PagamentoRiferimenti pagRiferimenti: pagamento.getRiferimenti () ) {
                MapEntry map = new MapEntry ();
                map.setKey ( pagRiferimenti.getNome () );
                map.setValue ( pagRiferimenti.getValore () );
                listMap.add ( map );
                mapEntryValue = true;
            }
        }
        
        if (Boolean.TRUE.equals ( pagamento.getTipoPagamento ().getFlagMultibeneficiario () )
                        && 
                       !StringUtils.isEmpty (  pagamento.getComponenti ().get ( 0 ).getCodiceTributo ())
                       ) 
        {
            MapEntry map = new MapEntry ();
            map.setKey ( EpayMdpServicesConstants.KEY_TARI_TEFA_PAGAMENTO_PRINCIPALE);
            map.setValue ((!StringUtils.isEmpty (  pagamento.getTipoPagamento ().getEpayTEnti ().getCodiceIstat ())?pagamento.getTipoPagamento ().getEpayTEnti ().getCodiceIstat ():"")
                +"|"+ (null!=pagamento.getComponenti ().get ( 0 ).getAnnoAccertamento ()?pagamento.getComponenti ().get ( 0 ).getAnnoAccertamento ():pagamento.getAnnoRiferimento ())
                +"|"+ (!StringUtils.isEmpty (  pagamento.getComponenti ().get ( 0 ).getCodiceTributo ())?pagamento.getComponenti ().get ( 0 ).getCodiceTributo ():"")

                            );
            listMap.add ( map );


            MapEntry mapsec = new MapEntry ();
            mapsec.setKey ( EpayMdpServicesConstants.KEY_TARI_TEFA_PAGAMENTO_SECONDARIO);
            mapsec.setValue ((!StringUtils.isEmpty ( pagamento.getEntiSecondari ().get ( 0 ).getCodiceIstat () ) ?pagamento.getEntiSecondari ().get ( 0 ).getCodiceIstat ():"")
                +"|"+ (null!=pagamento.getComponentiSecondari  ().get ( 0 ).getAnnoAccertamento ()?pagamento.getComponentiSecondari ().get ( 0 ).getAnnoAccertamento ():pagamento.getAnnoRiferimento ())
                +"|"+EpayMdpServicesConstants.CODICE_TRIBUTO_TARI_TEFA_PAGAMENTO_SECONDARIO
                            );
            listMap.add ( mapsec );

            mapEntryValue = true;
        }
        obj.setMapEntry ( listMap );
        if ( mapEntryValue ) {
            marshaller.marshal ( obj, sw );
            String result = sw.toString ();
            return result;
        } else
            return null;

    }

    private String getMultiversamentoXml ( Pagamento pagamento ) throws JAXBException {
        Marshaller marshaller = contextFlussoJAXBElencoDatiVersamento.createMarshaller ();
        StringWriter sw = new StringWriter ();

        ElencoDatiVersamento obj = new ElencoDatiVersamento ();

        if ( CollectionUtils.isEmpty ( pagamento.getComponenti () ) ) {
            DatiAccertamentoRPT dacc = null;
            DatiSingoloVersamentoRPT datiVersamento = new DatiSingoloVersamentoRPT ();

            if ( ( pagamento.getTipoPagamento ().getAnnoAccertamento () != null ) &&
                            ( pagamento.getTipoPagamento ().getNumeroAccertamento () != null ) ) {
                dacc = new DatiAccertamentoRPT ();
                Integer nacc = Integer.parseInt ( pagamento.getTipoPagamento ().getNumeroAccertamento () );
                dacc.setAnnoAccertamento ( pagamento.getTipoPagamento ().getAnnoAccertamento ().intValue () );
                dacc.setNumeroAccertamento ( nacc );
            }

            datiVersamento.setCausaleVersamento ( pagamento.getCausale () );
            datiVersamento.setDatiSpecificiRiscossione ( pagamento.getTipoPagamento ().getDatiSpecificiRiscossione () );
            datiVersamento.setImportoSingoloVersamento ( pagamento.getImporto () );
            datiVersamento.setDatiAccertamento ( dacc );

            obj.getDatiSingoloVersamento ().add ( datiVersamento );

        } else {
            //pagamento principale
            for ( PagamentoComponenti componente: pagamento.getComponenti () ) {

                DatiSingoloVersamentoRPT datiVersamento = new DatiSingoloVersamentoRPT ();
                DatiAccertamentoRPT datiAccertamento = null;

                if ( ( componente.getAnnoAccertamento () != null ) && ( componente.getNumeroAccertamento () != null ) ) {
                    datiAccertamento = new DatiAccertamentoRPT ();
                    datiAccertamento.setAnnoAccertamento ( componente.getAnnoAccertamento () );
                    try {
                        if ( componente.getNumeroAccertamento () != null ) {
                            datiAccertamento.setNumeroAccertamento ( Integer.parseInt ( componente.getNumeroAccertamento () ) );
                        }
                    } catch ( NumberFormatException e ) {
                        LoggerUtil.error ( "######### - ERRORE NUMERO ACCERTAMENTO NON NUMERICO :" + componente.getNumeroAccertamento () );
                    }
                }

                datiVersamento.setImportoSingoloVersamento ( componente.getImporto () );
                datiVersamento.setCausaleVersamento ( componente.getCausale () );
                datiVersamento.setDatiSpecificiRiscossione ( componente.getDatiSpecificiRiscossione () );
                datiVersamento.setImportoSingoloVersamento ( componente.getImporto () );
                datiVersamento.setDatiAccertamento ( datiAccertamento );

                //TODO verificare se idApplicazione preso dal tipo pagamento va bene, in quanto uguale per tutti gli elementi
                if ( pagamento.getTipoPagamento ().getFlagMultibeneficiario () ) {
                    datiVersamento.setApplicationId ( pagamento.getTipoPagamento ().getIdApplicazione () );
                }

                obj.getDatiSingoloVersamento ().add ( datiVersamento );
            }

            //pagamento secondario
            for ( PagamentoSecondarioComponenti componente: pagamento.getComponentiSecondari () ) {

                DatiSingoloVersamentoRPT datiVersamento = new DatiSingoloVersamentoRPT ();
                DatiAccertamentoRPT datiAccertamento = null;
                if ( ( componente.getAnnoAccertamento () != null ) && ( componente.getNumeroAccertamento () != null ) ) {
                    datiAccertamento = new DatiAccertamentoRPT ();
                    datiAccertamento.setAnnoAccertamento ( componente.getAnnoAccertamento () );
                    try {
                        if ( componente.getNumeroAccertamento () != null ) {
                            datiAccertamento.setNumeroAccertamento ( Integer.parseInt ( componente.getNumeroAccertamento () ) );
                        }
                    } catch ( NumberFormatException e ) {
                        LoggerUtil.error ( "######### - ERRORE NUMERO ACCERTAMENTO NON NUMERICO :" + componente.getNumeroAccertamento () );
                    }
                }

                datiVersamento.setImportoSingoloVersamento ( componente.getImporto () );
                datiVersamento.setCausaleVersamento ( componente.getCausale () );
                datiVersamento.setDatiSpecificiRiscossione ( componente.getDatiSpecificiRiscossione () );
                datiVersamento.setImportoSingoloVersamento ( componente.getImporto () );
                datiVersamento.setDatiAccertamento ( datiAccertamento );

                //idApplicazione va ricavato dal tipo pagamento secondario, salvato nel componente al momento della ricerca
                if ( pagamento.getTipoPagamento ().getFlagMultibeneficiario () ) {
                    datiVersamento.setApplicationId ( componente.getApplicationId  () );
                }

                obj.getDatiSingoloVersamento ().add ( datiVersamento );
            }
        }

        marshaller.marshal ( obj, sw );
        String result = sw.toString ();
        return result;
    }

    //ilaria
    private EsitoRiceviEsito riceviRR ( ParametriRiceviEsito parametri ) {
        EsitoRiceviEsito esito = new EsitoRiceviEsito ();
        //recupero parametri aggiuntivi
        List<ChiaveValore> elencoParametriAggiuntivi = parametri.getDatiAggiuntivi ();
        Rr rr = preparaRR ( elencoParametriAggiuntivi );
        Long idRR;
        try {
            idRR = epayServicesRR.inserisci ( rr );
            List<DatiSingolaRevoca> dettagli = preparaDettagli ( elencoParametriAggiuntivi );

            for ( DatiSingolaRevoca dettaglio: dettagli ) {
                epayServicesRR.inserisciSingolaRevoca ( dettaglio, idRR.intValue () );
            }

            esito = new EsitoRiceviEsito ();
            esito.setMessaggioErrore ( "RR inserita" );
            esito.setEsito ( idRR + "" );

        } catch ( Exception e ) {
            esito = new EsitoRiceviEsito ();
            esito.setCodiceErrore ( "RR non inserita" );
            //	esito.setEsito(idRR+"");
        }

        //}
        return esito;
    }

    private static boolean isRR ( List<ChiaveValore> elencoParametriAggiuntivi ) {
        //CSI_PAG-415 - aggiunto il controllo
        if ( elencoParametriAggiuntivi != null ) {
			for ( ChiaveValore chiaveValore : elencoParametriAggiuntivi ) {
				if ( chiaveValore.getChiave ().equalsIgnoreCase ( "isRR" ) ) {
					return true;
				}
			}
        }
        return false;
    }

    private String serchDettaglio ( List<ChiaveValore> elencoParametri, String chiave ) {
        if ( !CollectionUtils.isEmpty ( elencoParametri ) && StringUtils.isNoneBlank ( chiave ) ) {
            Optional<ChiaveValore> trovato = elencoParametri.stream ().filter ( new Predicate<ChiaveValore> () {

                @Override
                public boolean test ( ChiaveValore t ) {
                    return t.getChiave ().equalsIgnoreCase ( chiave );
                }
            } ).findFirst ();
            if ( trovato.isPresent () ) {
                return trovato.get ().getValore ();
            }
        }
        return null;
    }
    private List<DatiSingolaRevoca> preparaDettagli ( List<ChiaveValore> elencoParametriAggiuntivi ) {

        List<DatiSingolaRevoca> dettagli = new ArrayList<> ();

        for ( int i = 1; i <= 5; i++ ) {

            String importo = serchDettaglio ( elencoParametriAggiuntivi, "singolo_importo_revocato" + i );
            String iur = serchDettaglio ( elencoParametriAggiuntivi, "iur" + i );
            String causale = serchDettaglio ( elencoParametriAggiuntivi, "causale_revoca" + i );
            String add = serchDettaglio ( elencoParametriAggiuntivi, "dati_aggiuntivi_revoca" + i );

            if ( importo != null ) {
                DatiSingolaRevoca dett = new DatiSingolaRevoca ();

                BigDecimal importoBig = BigDecimal.ZERO;

                try {
                    DecimalFormat df = (DecimalFormat) NumberFormat.getInstance ( Locale.ITALY );
                    df.setParseBigDecimal ( true );
                    importoBig = (BigDecimal) df.parseObject ( importo );
                } catch ( ParseException e ) {
                    e.printStackTrace ();
                }

                dett.setCausaleRevoca ( causale );
                dett.setDatiAggiuntiviRevoca ( add );
                dett.setIur ( iur );
                dett.setSingoloImportoRevocato ( importoBig );

                dettagli.add ( dett );
            }
        }
        return dettagli;
    }

    private Rr preparaRR ( List<ChiaveValore> elencoParametriAggiuntivi ) {
        Rr rr = new Rr ();
        for ( Iterator iterator = elencoParametriAggiuntivi.iterator (); iterator.hasNext (); ) {
            ChiaveValore chiaveValore = (ChiaveValore) iterator.next ();
            switch ( chiaveValore.getChiave () ) {
            case "application_id" :
                rr.setApplicationId ( chiaveValore.getValore () );
                break;
            case "codice_contesto_pagamento" :
                rr.setCodiceContestoPagamento ( chiaveValore.getValore () );
                break;
            case "codice_identificativo_univoco_attestante" :
                rr.setCodiceIdentificativoUnivocoAttestante ( chiaveValore.getValore () );
                break;
            case "data_ora_messaggio_revoca" :
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat ( "yyyy-MM-dd hh:mm:ss" );
                    Date parsedDate = dateFormat.parse ( chiaveValore.getValore () );
                    rr.setDataOraMessaggioRevoca ( new java.sql.Timestamp ( parsedDate.getTime () ) );
                } catch ( ParseException e ) {
                    e.printStackTrace ();
                }
                break;
            case "id_dominio" :
                rr.setIdDominio ( chiaveValore.getValore () );
                break;
            case "identificativo_messaggio_revoca" :
                rr.setIdentificativoMessaggioRevoca ( chiaveValore.getValore () );
                break;
            case "denominazione_istituto_attestante" :
                rr.setDenominazioneIstitutoAttestante ( chiaveValore.getValore () );
                break;
            case "importo_totale_revocato" :
                BigDecimal importoTot = new BigDecimal ( chiaveValore.getValore () );
                rr.setImportoTotaleRevocato ( importoTot );
                break;
            case "iuv" :
                rr.setIuv ( chiaveValore.getValore () );
                break;
            case "tipo_revoca" :
                rr.setTipoRevoca ( chiaveValore.getValore () );
                break;
                //            case "xml" :
                //            	//aggiungere if valore vuoto
                //            	byte[] byteXml = Base64.decodeBase64(chiaveValore.getValore ().getBytes());
                //            	rr.setRrXml(byteXml);
                //            	break;
            case "id_rr" :
                Long idRR = Long.valueOf ( chiaveValore.getValore () );
                rr.setIdRr ( idRR );
                break;
            }
            if ( chiaveValore.getChiave ().equals ( "xml" ) ) {
                //aggiungere if valore vuoto
                byte [] byteXml = Base64.decodeBase64 ( chiaveValore.getValore ().getBytes () );
                rr.setRrXml ( byteXml );
            }
        }

        return rr;
    }

    @Override
    public EsitoRiceviEsito riceviEsito ( ParametriRiceviEsito parametri )
                    throws IllegalArgumentException, NoDataException, MissingParameterException, ErrorParameterException, EpayMdpServicesException {
        LoggerUtil.begin ();
        List<ChiaveValore> elencoParametriAggiuntivi = parametri.getDatiAggiuntivi ();
        //Verifico se ho una RR richiesta di revoca
        if ( isRR ( elencoParametriAggiuntivi ) ) {
            EsitoRiceviEsito risposta = riceviRR ( parametri );
            return risposta;
        } else {

            EsitoRiceviEsito risposta = new EsitoRiceviEsito ();
            risposta.setEsito ( EpayMdpServicesConstants.ESITO_KO );
            Pagamento pagamento = null;

            Parametro passphrase = parametroFacade.ricerca ( EpayMdpServicesConstants.GRUPPO_PARAM_MDP, EpayMdpServicesConstants.PARAM_PASSPHRASE );
            ErroreCodiceMessaggio codificaErrore = null;
            try {
                pagamento = pagamentoFacade.ricerca ( parametri.getIuv () );
				String macCalcolato = Utility.calcolaMacVersamento ( passphrase.getValore (), parametri.getApplicationId (), parametri.getIuv (), parametri.getTimestamp (), parametri.getIdMsgRicevuta () );
				codificaErrore = verificheComuni ( parametri.getIuv (), parametri.getMac (), pagamento, macCalcolato );

            } catch ( NoDataException e ) {
                codificaErrore = new ErroreCodiceMessaggio ();
                codificaErrore.setCodice ( EpayMdpServicesConstants.PAA_PAGAMENTO_SCONOSCIUTO );
                String messaggioDaInviareRegistrare = "Pagamento per iuv " + parametri.getIuv () + " non trovato";
                codificaErrore.setMessaggioErrore ( messaggioDaInviareRegistrare );
            }

            if ( codificaErrore != null ) {
                Errori errore = compilaErrore ( new Date (), codificaErrore.getMessaggioErrore (), null, null, parametri.getIuv (), null, 1, null );
                mdpServiceFacade.inserisciErrore ( errore );
                LoggerUtil.error ( " - ERRORE - " + codificaErrore.getMessaggioErrore () );
                risposta.setEsito ( EpayMdpServicesConstants.ESITO_KO );
                risposta.setCodiceErrore ( codificaErrore.getCodice () );
                risposta.setMessaggioErrore ( codificaErrore.getMessaggioErrore () );
                return risposta;
            }

            RegistroVersamenti registroVersamento = new RegistroVersamenti ();
            registroVersamento.setIdPagamento ( pagamento.getIdPagamento () );
            registroVersamento.setDataOperazione ( new Date () );
            registroVersamento.setAnagraficaVersante ( pagamento.getVersante () );
            //Esito Pagamento
            registroVersamento.setIdStato ( StatoPagamento.SUCCESSO.getId () );
            registroVersamento.setIdTransazione ( parametri.getTransactionId () );
            registroVersamento.setIuv ( pagamento.getIuv () );
            registroVersamento.setRisultato ( parametri.getDescEsitoPagamento () );
            registroVersamento.setOrigineInserimento ( EpayMdpServicesConstants.ORIGINE_INSERIMENTO_RICEVI_ESITO );

            //Inserimento oggetto su DB (epay_t_registro_versamenti)
            EsitiRicevuti esitiRce = new EsitiRicevuti ();

            //FIX Salvataggio IUR per EPAY-55
            esitiRce.setIur ( parametri.getIdentificativoUnivocoRiscossione () );

            esitiRce.setCodEsitoPagamento ( new Integer ( parametri.getCodEsitoPagamento () ) );
            esitiRce.setDataPagamento ( null != parametri.getDataEsitoSingoloPagamento () ? new Timestamp (
                parametri.getDataEsitoSingoloPagamento ().toGregorianCalendar ().getTimeInMillis () ) : null );
            esitiRce.setDescEsitoPagamento ( parametri.getDescEsitoPagamento () );
            esitiRce.setIdApplicazione ( parametri.getApplicationId () );
            esitiRce.setIdentificativoPsp ( parametri.getIdentificativoPSP () );
            // id_modalita_ricezione_esito corrispondente a ‘receipt da pagoPA’ se il campo con chiave = FlagReceipt della ElencoParametriAggiuntivi della riceviEsito ha come Valore = Ture
            String flagReceipt = serchDettaglio ( elencoParametriAggiuntivi, "flagReceipt" );
            if ( Boolean.parseBoolean ( flagReceipt ) )
                esitiRce.setIdModalitaRicezione ( new Integer ( EpayMdpServicesConstants.MODALITARICEZIONE_ESITO_PAGO_PA ) );
            else
                esitiRce.setIdModalitaRicezione ( new Integer ( EpayMdpServicesConstants.MODALITARICEZIONE_ESITO_NODO_SPC ) );
            esitiRce.setIdTransazione ( parametri.getTransactionId () );
            esitiRce.setImporto ( parametri.getImportoPagato () );
            esitiRce.setRagioneSocialePsp ( parametri.getDenominazionePSP () );
            esitiRce.setIdentificativoPsp ( parametri.getIdentificativoPSP () );
            esitiRce.setIuv ( null );
            String paymentDateTime = serchDettaglio ( elencoParametriAggiuntivi, "paymentDateTime" );
            if ( null != paymentDateTime ) {
                try {
                    Date date = new SimpleDateFormat ( "yyyy-MM-dd'T'HH:mm:ss" ).parse ( paymentDateTime );
                    esitiRce.setDataOraOperazione ( date );
                } catch ( ParseException e ) {

                }
            }

            registroVersamento.setEsitoRicevuto ( esitiRce );

            pagamentoFacade.inserisciRegistroVersamentiEGestioneStato ( registroVersamento );
          
            try {
                
                for (ChiaveValore chiaveValore: elencoParametriAggiuntivi)
                {
                    if (chiaveValore.getChiave ().startsWith ( "MBDAttachment_" ))
                        
                                    
                    {
                        JAXBContext jc = JAXBContext.newInstance(TipoMarcaDaBollo.class);
                        XMLInputFactory xif = XMLInputFactory.newFactory();
                        XMLStreamReader xsr = xif.createXMLStreamReader(new StreamSource(new ByteArrayInputStream(Base64.decodeBase64 (chiaveValore.getValore ()))));
                        Unmarshaller unmarshaller1 = jc.createUnmarshaller();
                         @SuppressWarnings ( "unchecked" )
                        TipoMarcaDaBollo value = ((JAXBElement<TipoMarcaDaBollo>)unmarshaller1.unmarshal(xsr)).getValue();
                         
                        AggiornamentoDatiMarcaDaBollo marca = new AggiornamentoDatiMarcaDaBollo ();
                        marca.setIuv ( parametri.getIuv ());
                        marca.setIubd ( value.getIUBD () );
                        mdpServiceFacade.aggiornaMarcaDaBollo ( marca );
                    }
                }
            } catch ( JAXBException | XMLStreamException | RuntimeException e ) {
                
                String messaggioDaInviareRegistrare = "Errore in iseriment marca da bollo per iuv " + parametri.getIuv () ;
                Errori errore = compilaErrore ( new Date (), messaggioDaInviareRegistrare, null, null, parametri.getIuv (), null, 1, null );
                mdpServiceFacade.inserisciErrore ( errore );
                LoggerUtil.error ( " - ERRORE - " + messaggioDaInviareRegistrare );
                e.printStackTrace ();
                risposta.setEsito ( EpayMdpServicesConstants.ESITO_KO );
                risposta.setCodiceErrore ( EpayMdpServicesConstants.PAA_SYSTEM_ERROR );
                risposta.setMessaggioErrore ( messaggioDaInviareRegistrare );
                return risposta;
            } 
            
            

            risposta.setEsito ( EpayMdpServicesConstants.ESITO_OK );
            LoggerUtil.end ();
            return risposta;
        }
    }

    private ErroreCodiceMessaggio verifichePagamentoModello3 ( String iuv, String timestamp, String mac,
        Pagamento pagamento, Parametro passphrase, String macCalcolato ) {

		ErroreCodiceMessaggioSuggerimento codificaErrore = verificheComuni ( iuv, mac, pagamento, macCalcolato );

        if ( codificaErrore != null ) {
            return codificaErrore;
        }

        if ( pagamento.getImporto () == null || BigDecimal.ZERO.setScale ( 2 ).compareTo ( pagamento.getImporto ().setScale ( 2 ) ) >= 0 ) {
            codificaErrore = new ErroreCodiceMessaggioSuggerimento ();
            codificaErrore.setCodice ( EpayMdpServicesConstants.PAA_SYSTEM_ERROR );
            String messaggioDaInviareRegistrare = "IUV " + iuv + " con importo minore o uguale a zero.";
            codificaErrore.setMessaggioErrore ( messaggioDaInviareRegistrare );
            return codificaErrore;
        }
        
        Errori err = new Errori ();
        if ( null != pagamento.getFineValidita () ) {
            LocalDate dtFV = pagamento.getFineValidita ().toInstant ()
                .atZone ( ZoneId.systemDefault () )
                .toLocalDate ();
            LocalDate dtNow = LocalDate.now ();
            if ( dtFV.isBefore ( dtNow ) ) {
                codificaErrore = new ErroreCodiceMessaggioSuggerimento ();
                codificaErrore.setCodice ( EpayMdpServicesConstants.PAA_PAGAMENTO_ANNULLATO );
                String messaggioDaInviareRegistrare = "IUV " + iuv + " relativo ad un pagamento annullato o non piu valido";
                codificaErrore.setMessaggioErrore ( messaggioDaInviareRegistrare );
                codificaErrore.setSuggerimento ( "Verificare la data fine validita' del pagamento " );
                return codificaErrore;
            }
        }

        switch ( StatoPagamento.findById ( pagamento.getIdStatoCorrente () ) ) {
        case TRANSAZIONE_AVVIATA :
        case IN_ATTESA :
            codificaErrore = new ErroreCodiceMessaggioSuggerimento ();
            codificaErrore.setCodice ( EpayMdpServicesConstants.PAA_PAGAMENTO_IN_CORSO );
            codificaErrore.setMessaggioErrore ( "IUV " + iuv + " relativo ad un pagamento avviato" );
            codificaErrore.setSuggerimento ( "Verificare lo stato del pagamento: non deve essere pari a " + StatoPagamento.IN_ATTESA.getId () );
            //aggiungere record in epay_errori
            err.setId_stato_errore ( 1 );
            err.setApplicativo ( "EpayMdpServices" );
            err.setIuv ( iuv );
            err.setDescrizione ( "verificaDatiPagamento - IUV " + iuv + " relativo ad un pagamento in corso" );
            mdpServiceFacade.inserisciErrore ( err );
            return codificaErrore;
        case INVALIDATO :
            codificaErrore = new ErroreCodiceMessaggioSuggerimento ();
            codificaErrore.setCodice ( EpayMdpServicesConstants.PAA_PAGAMENTO_ANNULLATO );
            codificaErrore.setMessaggioErrore ( "IUV " + iuv + " relativo ad un pagamento annullato" );
            codificaErrore.setSuggerimento ( "Verificare lo stato del pagamento: non deve essere pari a " + StatoPagamento.INVALIDATO.getId () );
            return codificaErrore;
            // RDI 01 : NUOVO STATO PAGAMENTO
        case REVOCATO :
            codificaErrore = new ErroreCodiceMessaggioSuggerimento ();
            codificaErrore.setCodice ( EpayMdpServicesConstants.PAA_PAGAMENTO_ANNULLATO );
            codificaErrore.setMessaggioErrore ( "IUV " + iuv + " relativo ad un pagamento revocato" );
            codificaErrore.setSuggerimento ( "Verificare lo stato del pagamento: non deve essere pari a " + StatoPagamento.REVOCATO.getId () );
            //aggiungere record in epay_errori
            err = new Errori ();
            err.setId_stato_errore ( 1 );
            err.setApplicativo ( "EpayMdpServices" );
            err.setIuv ( iuv );
            err.setDescrizione (
                "verificaDatiPagamento - IUV " + iuv + " relativo ad un pagamento non pi? valido ? data fine validit?= " + pagamento.getFineValidita () );
            mdpServiceFacade.inserisciErrore ( err );
            return codificaErrore;
        case COMPILATO :
            codificaErrore = new ErroreCodiceMessaggioSuggerimento ();
            codificaErrore.setCodice ( EpayMdpServicesConstants.PAA_PAGAMENTO_IN_CORSO );
            codificaErrore.setMessaggioErrore ( "IUV " + iuv + " relativo ad un pagamento avviato" );
            codificaErrore.setSuggerimento ( "Verificare lo stato del pagamento: non deve essere pari a " + StatoPagamento.COMPILATO.getId () );
            //aggiungere record in epay_errori
            err = new Errori ();
            err.setId_stato_errore ( 1 );
            err.setApplicativo ( "EpayMdpServices" );
            err.setIuv ( iuv );
            err.setDescrizione ( "verificaDatiPagamento - IUV " + iuv + " relativo ad un pagamento in corso" );
            mdpServiceFacade.inserisciErrore ( err );
            return codificaErrore;
        default :
            Pagamento pagamentoAttivo = null;
            try {
                pagamentoAttivo = pagamentoFacade.ricercaSoloAttivi ( iuv );
            } catch ( NoDataException e ) {
                pagamentoAttivo = null;
            }
            if ( pagamentoAttivo == null ) {
                codificaErrore = new ErroreCodiceMessaggioSuggerimento ();
                codificaErrore.setCodice ( EpayMdpServicesConstants.PAA_PAGAMENTO_SCADUTO );
                codificaErrore.setMessaggioErrore ( "IUV " + iuv + " relativo ad un pagamento scaduto" );
                codificaErrore.setSuggerimento ( "Verificare la data fine validita' del pagamento " );
                //aggiungere record in epay_errori
                err = new Errori ();
                err.setId_stato_errore ( 1 );
                err.setApplicativo ( "EpayMdpServices" );
                err.setIuv ( iuv );
                err.setDescrizione ( "IUV " + iuv + " relativo ad un pagamento scaduto in data " + pagamento.getDataScadenza () );
                mdpServiceFacade.inserisciErrore ( err );
                return codificaErrore;
            }
            break;
        }
        return null;
    }

	private ErroreCodiceMessaggioSuggerimento verificheComuni ( String iuv, String mac, Pagamento pagamento, String macCalcolato ) {
		if ( !macCalcolato.equals ( mac ) ) {
			if ( LoggerUtil.isDebugEnabled () ) {
				LoggerUtil.debug ( "il MAC fornito e' " + mac );
			}
			if ( null == macCalcolato || !macCalcolato.equals ( mac ) ) {
				ErroreCodiceMessaggioSuggerimento codificaErrore = new ErroreCodiceMessaggioSuggerimento ();
				codificaErrore.setCodice ( EpayMdpServicesConstants.PAA_SYSTEM_ERROR );
				codificaErrore.setMessaggioErrore ( EpayMdpServicesConstants.ERRORE_MAC_NON_RICONOSCIUTO );
				codificaErrore.setSuggerimento ( "verificare il calcolo del MAC" );
				//aggiungere record in epay_errori
				Errori e = new Errori ();
				e.setId_stato_errore ( 1 );
				e.setApplicativo ( "EpayMdpServices" );
				e.setIuv ( iuv );
				e.setDescrizione ( codificaErrore.getMessaggioErrore () );
				mdpServiceFacade.inserisciErrore ( e );
				return codificaErrore;
			}
		}

        if ( pagamento == null ) {
            ErroreCodiceMessaggioSuggerimento codificaErrore = new ErroreCodiceMessaggioSuggerimento ();
            codificaErrore.setCodice ( EpayMdpServicesConstants.PAA_PAGAMENTO_SCONOSCIUTO );
            String messaggioDaInviareRegistrare = "Pagamento per iuv " + iuv + " non trovato";
            codificaErrore.setMessaggioErrore ( messaggioDaInviareRegistrare );
            codificaErrore.setSuggerimento ( "verificare che lo IUV fornito sia corretto" );
            //aggiungere record in epay_errori
            Errori e = new Errori ();
            e.setId_stato_errore ( 1 );
            e.setApplicativo ( "EpayMdpServices" );
            e.setIuv ( iuv );
            e.setDescrizione ( "verificaDatiPagamento - IUV non trovato" );
            mdpServiceFacade.inserisciErrore ( e );
            return codificaErrore;
        }

        if ( StatoPagamento.SUCCESSO.getId ().equals ( pagamento.getIdStatoCorrente () ) ) {
            ErroreCodiceMessaggioSuggerimento codificaErrore = new ErroreCodiceMessaggioSuggerimento ();
            codificaErrore.setCodice ( EpayMdpServicesConstants.PAA_PAGAMENTO_DUPLICATO );
            String messaggioDaInviareRegistrare = "IUV " + iuv + " relativo ad un pagamento gia' eseguito";
            codificaErrore.setMessaggioErrore ( messaggioDaInviareRegistrare );
            codificaErrore.setSuggerimento ( "verificare lo stato del pagamento: non deve essere " + StatoPagamento.SUCCESSO.getId () );
            //aggiungere record in epay_errori
            Errori e = new Errori ();
            e.setId_stato_errore ( 1 );
            e.setApplicativo ( "EpayMdpServices" );
            e.setIuv ( iuv );
            e.setDescrizione ( "verificaDatiPagamento - IUV relativo ad un pagamento gi? eseguito" );
            mdpServiceFacade.inserisciErrore ( e );
            return codificaErrore;
        }

        return null;
    }

    private EsitoRiceviRT compilaEsitoRiceviRT ( String esito, String codiceErrore, String messaggioErrore ) {
        EsitoRiceviRT esitoRiceviRT = new EsitoRiceviRT ();
        esitoRiceviRT.setCodiceErrore ( codiceErrore );
        esitoRiceviRT.setEsito ( esito );
        esitoRiceviRT.setMessaggioErrore ( messaggioErrore );;

        return esitoRiceviRT;

    }

    private Errori compilaErrore ( Date timeStp, String descr, Long idPagam, Long idRegVers, String iuv, Long idTransaz, Integer idSTatoErr,
        String descCorrezione ) {
        Errori errore = new Errori ();

        errore.setApplicativo ( "EpayMdpServices" );
        if ( timeStp != null ) {
            errore.setData ( timeStp );
        }
        if ( descr != null && descr != "" ) {
            errore.setDescrizione ( descr );
        }
        if ( idPagam != null && idPagam > 0 ) {
            errore.setId_pagamento ( idPagam );
        }
        if ( idRegVers != null && idRegVers > 0 ) {
            errore.setId_registro_versamento ( idRegVers );
        }
        if ( iuv != null && iuv != "" ) {
            errore.setIuv ( iuv );
        }
        if ( idTransaz != null && idTransaz > 0 ) {
            errore.setId_transazione ( idTransaz );
        }
        if ( idSTatoErr != null && idSTatoErr > 0 ) {
            errore.setId_stato_errore ( idSTatoErr );
        }
        if ( descCorrezione != null && descCorrezione != "" ) {
            errore.setDesc_correzione ( descCorrezione );
        }

        return errore;
    }

    private RegistroVersamenti esisteRT ( String iuv, String idTransazione ) throws NoDataException {

        RegistroVersamenti ultimoVersamento = new RegistroVersamenti ();
        try {
            ultimoVersamento = mdpServiceFacade.ricercaUltimaRegistrazioneVersamento ( iuv, idTransazione );
        } catch ( NoDataException e ) {
            throw e;
        }

        return ultimoVersamento;

    }

    private TransazioneMdp esisteTransazione ( String idTransazione ) throws NoDataException {

        TransazioneMdp transazione = new TransazioneMdp ();
        try {
            TransazioneMdp transazioneRicerca = new TransazioneMdp ();
            transazioneRicerca.setIdTransazione ( idTransazione );
            transazione = mdpServiceFacade.ricercaTransazione ( transazioneRicerca );
        } catch ( NoDataException e ) {
            throw e;
        }

        return transazione;

    }

    private Long inserisciRT ( Rt rt ) {

        Long ret = mdpServiceFacade.inserisciRt ( rt );
        return ret;
    }

    private Unmarshaller creaUnMarshallerRT ()
                    throws JAXBException, SAXException {
        Unmarshaller unmarshaller = contextFlussoJAXBCtRicevutaTelematica.createUnmarshaller ();
        SchemaFactory sf = SchemaFactory.newInstance ( "http://www.w3.org/2001/XMLSchema" );
        Source so = new StreamSource ( this.getClass ().getResourceAsStream ( "/META-INF/PagInf_RPT_RT_6_2_0.xsd" ) );
        Schema s = sf.newSchema ( so );
        unmarshaller.setSchema ( s );
        return unmarshaller;
    }
   
}
