/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import it.csi.epay.epaymodric.business.epaymodric.bo.StatoFlussoErrore;
import it.csi.epay.epaymodric.business.epaymodric.manager.FlussoRendicontazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.InvioMailManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.StatoFlussoErroreManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.ErroreUtility;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.FlussiUtility;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDErrore;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDStatoFlusso;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTCatalogo;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoDettaglio;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoSintesi;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTPsp;
import it.csi.epay.epaymodric.business.epaymodric.repository.CatalogoRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.CategoriaIuvRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.EnteRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.ErroreRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.FlussoDettaglioRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.FlussoOrigineRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.FlussoSintesiRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.PspRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.StatoFlussoRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.TipoAcquisizioneRepository;
import it.csi.epay.epaymodric.business.epaymodric.utils.Costanti;
import it.csi.epay.epaymodric.business.epaymodric.utils.DateUtils;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsAcquisizioneFlussoRendicontazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsSpacchettamentoFlussoRendicontazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsElaborazione;
import it.csi.epay.epaymodric.interfacews.epaymodric.consts.CostantiErrori;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.EmailEnum;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.StatoFlussoEnum;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.TipoAcquisizione;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.EpaymodricException;
import it.csi.epay.epaymodric.util.wsdl.flussorendicontazione.DatiSingoloPagamentoType;
import it.csi.epay.epaymodric.util.wsdl.flussorendicontazione.PagamentoIntermediatoType;
import it.csi.epay.epaymodric.util.wsdl.flussorendicontazione.ResponseType;
import it.csi.epay.epaymodric.util.wsdl.flussorendicontazione.ResultType;
import it.csi.epay.epaymodric.util.wsdl.flussorendicontazione.TestataFlussoRendicontazioneExtType;
import it.csi.epay.epaymodric.util.wsdl.flussorendicontazione.TrasmettiFlussoRendicontazioneExtRequestType;


@Service
public class FlussoRendicontazioneManagerImpl implements FlussoRendicontazioneManager {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @Autowired
    private FlussoOrigineRepository flussoOrigineRepository;

    @Autowired
    private PspRepository pspRepository;

    @Autowired
    private FlussoDettaglioRepository flussoDettaglioRepository;

    @Autowired
    private CatalogoRepository catalogoRepository;

    @Autowired
    private StatoFlussoRepository statoFlussoRepository;

    @Autowired
    private FlussoSintesiRepository flussoSintesiRepository;

    @Autowired
    private InvioMailManager invioMailManager;

    @Autowired
    private ErroreRepository erroreRepository;

    @Autowired
    private StatoFlussoErroreManager statoFlussoErroreManager;

    @Autowired
    private StatoFlussoRepository statoflussoRepository;

    @Autowired
    private EnteRepository enteRepository;

    @Autowired
    private TipoAcquisizioneRepository tipoAcquisizioneRepository;

    @Autowired
    private CategoriaIuvRepository categoriaIuvRepository;

    @Override
    public ResponseType acquisciFlussoRendicontazione ( TrasmettiFlussoRendicontazioneExtRequestType inputReadonly, TipoAcquisizione tipoAcquisizione ) {
        logger.info ( "FlussiRendicontazioneServiceImpl.acquisciFlussoRendicontazione: INIZIO" );

        DTOOutputWsElaborazione result = new DTOOutputWsElaborazione ();

        CblTEnte istitutoRicevente = null;

        CblTFlussoOrigine savedAs = null;

        //        TrasmettiFlussoRendicontazioneExtRequestType recordTEST = new TrasmettiFlussoRendicontazioneExtRequestType ();e

        try {
            //            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
            //                "<pay_i:FlussoRiversamento xmlns:pay_i=\"http://www.digitpa.gov.it/schemas/2011/Pagamenti/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"/opt/proctele/resources/FlussoRiversamento_1_0_4.xsd\">\r\n"
            //                +
            //                "  <pay_i:versioneOggetto>1.0</pay_i:versioneOggetto>\r\n" +
            //                "  <pay_i:identificativoFlusso>2018-05-18BCITITMM-0000000034</pay_i:identificativoFlusso>\r\n" +
            //                "  <pay_i:dataOraFlusso>2018-05-20T03:19:20</pay_i:dataOraFlusso>\r\n" +
            //                "  <pay_i:identificativoUnivocoRegolamento>0306928448796710489999999999IT</pay_i:identificativoUnivocoRegolamento>\r\n" +
            //                "  <pay_i:dataRegolamento>2018-05-18</pay_i:dataRegolamento>\r\n" +
            //                "  <pay_i:istitutoMittente>\r\n" +
            //                "    <pay_i:identificativoUnivocoMittente>\r\n" +
            //                "      <pay_i:tipoIdentificativoUnivoco>B</pay_i:tipoIdentificativoUnivoco>\r\n" +
            //                "      <pay_i:codiceIdentificativoUnivoco>BCITITMM</pay_i:codiceIdentificativoUnivoco>\r\n" +
            //                "    </pay_i:identificativoUnivocoMittente>\r\n" +
            //                "    <pay_i:denominazioneMittente>Intesa Sanpaolo</pay_i:denominazioneMittente>\r\n" +
            //                "  </pay_i:istitutoMittente>\r\n" +
            //                "  <pay_i:istitutoRicevente>\r\n" +
            //                "    <pay_i:identificativoUnivocoRicevente>\r\n" +
            //                "      <pay_i:tipoIdentificativoUnivoco>G</pay_i:tipoIdentificativoUnivoco>\r\n" +
            //                "      <pay_i:codiceIdentificativoUnivoco>80087670016</pay_i:codiceIdentificativoUnivoco>\r\n" +
            //                "    </pay_i:identificativoUnivocoRicevente>\r\n" +
            //                "    <pay_i:denominazioneRicevente>Regione Piemonte</pay_i:denominazioneRicevente>\r\n" +
            //                "  </pay_i:istitutoRicevente>\r\n" +
            //                "  <pay_i:numeroTotalePagamenti>6</pay_i:numeroTotalePagamenti>\r\n" +
            //                "  <pay_i:importoTotalePagamenti>798.90</pay_i:importoTotalePagamenti>\r\n" +
            //                "  <pay_i:datiSingoliPagamenti>\r\n" +
            //                "    <pay_i:identificativoUnivocoVersamento>RF71181370001T00200000003</pay_i:identificativoUnivocoVersamento>\r\n" +
            //                "    <pay_i:identificativoUnivocoRiscossione>15265118432491712011</pay_i:identificativoUnivocoRiscossione>\r\n" +
            //                "    <pay_i:indiceDatiSingoloPagamento>1</pay_i:indiceDatiSingoloPagamento>\r\n" +
            //                "    <pay_i:singoloImportoPagato>180.18</pay_i:singoloImportoPagato>\r\n" +
            //                "    <pay_i:codiceEsitoSingoloPagamento>0</pay_i:codiceEsitoSingoloPagamento>\r\n" +
            //                "    <pay_i:dataEsitoSingoloPagamento>2018-05-17</pay_i:dataEsitoSingoloPagamento>\r\n" +
            //                "    <pay_i:codiceVersamento>T002</pay_i:codiceVersamento>\r\n" +
            //                "    <pay_i:transactionId>PRD000000002244898</pay_i:transactionId>\r\n" +
            //                "    <pay_i:statoInvioFruitore>S</pay_i:statoInvioFruitore>\r\n" +
            //                "    <pay_i:datiSpecificiRiscossione>2/1210</pay_i:datiSpecificiRiscossione>\r\n" +
            //                "    <pay_i:anagraficaPagatore>CHIARAVIGLIO VALTER</pay_i:anagraficaPagatore>\r\n" +
            //                "    <pay_i:codiceIdentificativoUnivocoPagatore>CHRVTR56R06B791L</pay_i:codiceIdentificativoUnivocoPagatore>\r\n" +
            //                "    <pay_i:causaleVersamento>/RFS/RF71181370001T00200000003/180.18</pay_i:causaleVersamento>\r\n" +
            //                "  </pay_i:datiSingoliPagamenti>\r\n" +
            //                "  <pay_i:datiSingoliPagamenti>\r\n" +
            //                "    <pay_i:identificativoUnivocoVersamento>15265313073334849011</pay_i:identificativoUnivocoVersamento>\r\n" +
            //                "    <pay_i:identificativoUnivocoRiscossione>15265313073334849011</pay_i:identificativoUnivocoRiscossione>\r\n" +
            //                "    <pay_i:indiceDatiSingoloPagamento>1</pay_i:indiceDatiSingoloPagamento>\r\n" +
            //                "    <pay_i:singoloImportoPagato>68.7</pay_i:singoloImportoPagato>\r\n" +
            //                "    <pay_i:codiceEsitoSingoloPagamento>0</pay_i:codiceEsitoSingoloPagamento>\r\n" +
            //                "    <pay_i:dataEsitoSingoloPagamento>2018-05-17</pay_i:dataEsitoSingoloPagamento>\r\n" +
            //                "    <pay_i:codiceVersamento>C001</pay_i:codiceVersamento>\r\n" +
            //                "    <pay_i:transactionId>PRD000000002025740</pay_i:transactionId>\r\n" +
            //                "    <pay_i:statoInvioFruitore>S</pay_i:statoInvioFruitore>\r\n" +
            //                "    <pay_i:datiSpecificiRiscossione>9/30615/E.3.01.03.02.000</pay_i:datiSpecificiRiscossione>\r\n" +
            //                "    <pay_i:anagraficaPagatore>CALORE VERDE SRL</pay_i:anagraficaPagatore>\r\n" +
            //                "    <pay_i:codiceIdentificativoUnivocoPagatore>02799890047</pay_i:codiceIdentificativoUnivocoPagatore>\r\n" +
            //                "    <pay_i:causaleVersamento>/RFB/15265313073334849011/68.7</pay_i:causaleVersamento>\r\n" +
            //                "  </pay_i:datiSingoliPagamenti>\r\n" +
            //                "  <pay_i:datiSingoliPagamenti>\r\n" +
            //                "    <pay_i:identificativoUnivocoVersamento>RF65181370001C00100000014</pay_i:identificativoUnivocoVersamento>\r\n" +
            //                "    <pay_i:identificativoUnivocoRiscossione>15265321364821726011</pay_i:identificativoUnivocoRiscossione>\r\n" +
            //                "    <pay_i:indiceDatiSingoloPagamento>1</pay_i:indiceDatiSingoloPagamento>\r\n" +
            //                "    <pay_i:singoloImportoPagato>127.6</pay_i:singoloImportoPagato>\r\n" +
            //                "    <pay_i:codiceEsitoSingoloPagamento>0</pay_i:codiceEsitoSingoloPagamento>\r\n" +
            //                "    <pay_i:dataEsitoSingoloPagamento>2018-05-17</pay_i:dataEsitoSingoloPagamento>\r\n" +
            //                "    <pay_i:codiceVersamento>C001</pay_i:codiceVersamento>\r\n" +
            //                "    <pay_i:transactionId>PRD000000002025740</pay_i:transactionId>\r\n" +
            //                "    <pay_i:statoInvioFruitore>S</pay_i:statoInvioFruitore>\r\n" +
            //                "    <pay_i:datiSpecificiRiscossione>9/30615/E.3.01.03.02.000</pay_i:datiSpecificiRiscossione>\r\n" +
            //                "    <pay_i:anagraficaPagatore>CASALINGHI S.T.O. SRL</pay_i:anagraficaPagatore>\r\n" +
            //                "    <pay_i:codiceIdentificativoUnivocoPagatore>00582330031</pay_i:codiceIdentificativoUnivocoPagatore>\r\n" +
            //                "    <pay_i:causaleVersamento>/RFS/RF65181370001C00100000014/127.6</pay_i:causaleVersamento>\r\n" +
            //                "  </pay_i:datiSingoliPagamenti>\r\n" +
            //                "  <pay_i:datiSingoliPagamenti>\r\n" +
            //                "    <pay_i:identificativoUnivocoVersamento>RF16181370001T00200000023</pay_i:identificativoUnivocoVersamento>\r\n" +
            //                "    <pay_i:identificativoUnivocoRiscossione>15265374475584919011</pay_i:identificativoUnivocoRiscossione>\r\n" +
            //                "    <pay_i:indiceDatiSingoloPagamento>1</pay_i:indiceDatiSingoloPagamento>\r\n" +
            //                "    <pay_i:singoloImportoPagato>32.43</pay_i:singoloImportoPagato>\r\n" +
            //                "    <pay_i:codiceEsitoSingoloPagamento>0</pay_i:codiceEsitoSingoloPagamento>\r\n" +
            //                "    <pay_i:dataEsitoSingoloPagamento>2018-05-17</pay_i:dataEsitoSingoloPagamento>\r\n" +
            //                "    <pay_i:codiceVersamento>T002</pay_i:codiceVersamento>\r\n" +
            //                "    <pay_i:transactionId>PRD000000002244999</pay_i:transactionId>\r\n" +
            //                "    <pay_i:statoInvioFruitore>S</pay_i:statoInvioFruitore>\r\n" +
            //                "    <pay_i:datiSpecificiRiscossione>2/1210</pay_i:datiSpecificiRiscossione>\r\n" +
            //                "    <pay_i:anagraficaPagatore>TEST 1</pay_i:anagraficaPagatore>\r\n" +
            //                "    <pay_i:codiceIdentificativoUnivocoPagatore>SROLSN77S61C722O</pay_i:codiceIdentificativoUnivocoPagatore>\r\n" +
            //                "    <pay_i:causaleVersamento>/RFS/RF16181370001T00200000023/32.43</pay_i:causaleVersamento>\r\n" +
            //                "  </pay_i:datiSingoliPagamenti>\r\n" +
            //                "  <pay_i:datiSingoliPagamenti>\r\n" +
            //                "    <pay_i:identificativoUnivocoVersamento>RF50181370001TF0000000410</pay_i:identificativoUnivocoVersamento>\r\n" +
            //                "    <pay_i:identificativoUnivocoRiscossione>15265386422644965011</pay_i:identificativoUnivocoRiscossione>\r\n" +
            //                "    <pay_i:indiceDatiSingoloPagamento>1</pay_i:indiceDatiSingoloPagamento>\r\n" +
            //                "    <pay_i:singoloImportoPagato>266.63</pay_i:singoloImportoPagato>\r\n" +
            //                "    <pay_i:codiceEsitoSingoloPagamento>0</pay_i:codiceEsitoSingoloPagamento>\r\n" +
            //                "    <pay_i:dataEsitoSingoloPagamento>2018-05-17</pay_i:dataEsitoSingoloPagamento>\r\n" +
            //                "    <pay_i:codiceVersamento>TF00</pay_i:codiceVersamento>\r\n" +
            //                "    <pay_i:transactionId>PRD000000002246928</pay_i:transactionId>\r\n" +
            //                "    <pay_i:statoInvioFruitore>S</pay_i:statoInvioFruitore>\r\n" +
            //                "    <pay_i:datiSpecificiRiscossione>9/E.1.01.01.48.000</pay_i:datiSpecificiRiscossione>\r\n" +
            //                "    <pay_i:anagraficaPagatore>Enrico Rossi</pay_i:anagraficaPagatore>\r\n" +
            //                "    <pay_i:codiceIdentificativoUnivocoPagatore>RSSNRC83B24D205L</pay_i:codiceIdentificativoUnivocoPagatore>\r\n" +
            //                "    <pay_i:causaleVersamento>/RFS/RF50181370001TF0000000410/266.63</pay_i:causaleVersamento>\r\n" +
            //                "  </pay_i:datiSingoliPagamenti>\r\n" +
            //                "  <pay_i:datiSingoliPagamenti>\r\n" +
            //                "    <pay_i:identificativoUnivocoVersamento>181170560898637</pay_i:identificativoUnivocoVersamento>\r\n" +
            //                "    <pay_i:identificativoUnivocoRiscossione>15265388788634976011</pay_i:identificativoUnivocoRiscossione>\r\n" +
            //                "    <pay_i:indiceDatiSingoloPagamento>1</pay_i:indiceDatiSingoloPagamento>\r\n" +
            //                "    <pay_i:singoloImportoPagato>123.36</pay_i:singoloImportoPagato>\r\n" +
            //                "    <pay_i:codiceEsitoSingoloPagamento>0</pay_i:codiceEsitoSingoloPagamento>\r\n" +
            //                "    <pay_i:dataEsitoSingoloPagamento>2018-05-17</pay_i:dataEsitoSingoloPagamento>\r\n" +
            //                "    <pay_i:codiceVersamento>TF00</pay_i:codiceVersamento>\r\n" +
            //                "    <pay_i:transactionId>PRD000000002246928</pay_i:transactionId>\r\n" +
            //                "    <pay_i:statoInvioFruitore>S</pay_i:statoInvioFruitore>\r\n" +
            //                "    <pay_i:datiSpecificiRiscossione>9/E.1.01.01.48.000</pay_i:datiSpecificiRiscossione>\r\n" +
            //                "    <pay_i:anagraficaPagatore>LucaDaniele</pay_i:anagraficaPagatore>\r\n" +
            //                "    <pay_i:codiceIdentificativoUnivocoPagatore>DNLLCU93T13A124N</pay_i:codiceIdentificativoUnivocoPagatore>\r\n" +
            //                "    <pay_i:causaleVersamento>/RFS/181170560898637/123.36</pay_i:causaleVersamento>\r\n" +
            //                "  </pay_i:datiSingoliPagamenti>\r\n" +
            //                "</pay_i:FlussoRiversamento>\r\n" +
            //                "";

            //            byte [] flussoRiversamento = Base64.getEncoder ().encode ( xml.getBytes () );
            //            recordTEST.setFlussoRiversamento ( flussoRiversamento );
            //
            //            TestataFlussoRendicontazioneExtType testataTEST = new TestataFlussoRendicontazioneExtType ();
            //            GregorianCalendar c = new GregorianCalendar ();
            //            c.set ( 2018, 5 - 1, 18 );
            //            try {
            //                testataTEST.setDataRegolamento ( DatatypeFactory.newInstance ().newXMLGregorianCalendar ( c ) );
            //            } catch ( DatatypeConfigurationException e ) {
            //                e.printStackTrace ();
            //            }
            //            testataTEST.setIdentificativoFlusso ( "2018-05-18BCITITMM-0000000034" );
            //
            //            PagamentiIntermediati pagamentiIntermediati = new PagamentiIntermediati ();
            //            PagamentoIntermediatoType pinter1 = new PagamentoIntermediatoType ();
            //            PagamentoIntermediatoType pinter2 = new PagamentoIntermediatoType ();
            //            PagamentoIntermediatoType pinter3 = new PagamentoIntermediatoType ();
            //            PagamentoIntermediatoType pinter4 = new PagamentoIntermediatoType ();
            //            PagamentoIntermediatoType pinter5 = new PagamentoIntermediatoType ();
            //            PagamentoIntermediatoType pinter6 = new PagamentoIntermediatoType ();
            //
            //            DatiSingoliPagamenti sinPag1 = new DatiSingoliPagamenti ();
            //            DatiSingoliPagamenti sinPag2 = new DatiSingoliPagamenti ();
            //            DatiSingoliPagamenti sinPag3 = new DatiSingoliPagamenti ();
            //            DatiSingoliPagamenti sinPag4 = new DatiSingoliPagamenti ();
            //            DatiSingoliPagamenti sinPag5 = new DatiSingoliPagamenti ();
            //            DatiSingoliPagamenti sinPag6 = new DatiSingoliPagamenti ();
            //
            //            //-----------------------------------------------------------//
            //            DatiSingoloPagamentoType pag1 = new DatiSingoloPagamentoType ();
            //            pag1.setDataEsitoSingoloPagamento ( testataTEST.getDataRegolamento () );
            //            pag1.setSingoloImportoPagato ( new BigDecimal ( "180.18" ) );
            //            pag1.setIUV ( "RF71181370001T00200000003" );
            //            pag1.setIUR ( "15265118432491712011" );
            //            pag1.setIndiceDatiPagamento ( 1 );
            //            pag1.setCodiceVersamento ( "T002" );
            //            pag1.setTransactionId ( "PRD000000002244898" );
            //            pag1.setDatiSpecificiRiscossione ( "2/1210" );
            //            SoggettoType pagatore1 = new SoggettoType ();
            //            SoggettoType versante1 = new SoggettoType ();
            //            PersonaFisicaType pf1 = new PersonaFisicaType ();
            //            pf1.setNome ( "VALTER" );
            //            pf1.setCognome ( "VALTER" );
            //            pagatore1.setPersonaFisica ( pf1 );
            //            pag1.setAnagraficaPagatore ( pagatore1 );
            //            pag1.setAnagraficaVersante ( versante1 );
            //            pag1.setDescrizioneCausaleVersamento ( "/RFS/RF71181370001T00200000003/180.18" );
            //            sinPag1.setDatiSingoloPagamento ( pag1 );
            //            //-----------------------------------------------------------//
            //            DatiSingoloPagamentoType pag2 = new DatiSingoloPagamentoType ();
            //            pag2.setDataEsitoSingoloPagamento ( testataTEST.getDataRegolamento () );
            //            pag2.setSingoloImportoPagato ( new BigDecimal ( "68.7" ) );
            //            pag2.setIUV ( "15265313073334849011" );
            //            pag2.setIUR ( "15265313073334849011" );
            //            pag2.setIndiceDatiPagamento ( 1 );
            //            pag2.setCodiceVersamento ( "C001" );
            //            pag2.setTransactionId ( "PRD000000002025740" );
            //            pag2.setDatiSpecificiRiscossione ( "9/30615/E.3.01.03.02.000" );
            //            it.csi.epay.epaymodric.util.wsdl.flussorendicontazione.SoggettoType pagatore2 = new SoggettoType ();
            //            SoggettoType versante2 = new SoggettoType ();
            //            PersonaFisicaType pf2 = new PersonaFisicaType ();
            //            pf2.setNome ( "CALORE" );
            //            pf2.setCognome ( "VERDE" );
            //            pagatore2.setPersonaFisica ( pf2 );
            //            pag2.setAnagraficaPagatore ( pagatore2 );
            //            pag2.setAnagraficaVersante ( versante2 );
            //            pag2.setDescrizioneCausaleVersamento ( "/RFB/15265313073334849011/68.7" );
            //            sinPag2.setDatiSingoloPagamento ( pag2 );
            //            //-----------------------------------------------------------//
            //            DatiSingoloPagamentoType pag3 = new DatiSingoloPagamentoType ();
            //            pag3.setDataEsitoSingoloPagamento ( testataTEST.getDataRegolamento () );
            //            pag3.setSingoloImportoPagato ( new BigDecimal ( "127.6" ) );
            //            pag3.setIUV ( "RF65181370001C00100000014" );
            //            pag3.setIUR ( "15265321364821726011" );
            //            pag3.setIndiceDatiPagamento ( 1 );
            //            pag3.setCodiceVersamento ( "C001" );
            //            pag3.setTransactionId ( "PRD000000002025740" );
            //            pag3.setDatiSpecificiRiscossione ( "9/30615/E.3.01.03.02.000" );
            //            SoggettoType pagatore3 = new SoggettoType ();
            //            SoggettoType versante3 = new SoggettoType ();
            //            PersonaFisicaType pf3 = new PersonaFisicaType ();
            //            pf3.setNome ( "CASALINGHI" );
            //            pf3.setCognome ( "SRL" );
            //            pagatore3.setPersonaFisica ( pf3 );
            //            pag3.setAnagraficaPagatore ( pagatore3 );
            //            pag3.setAnagraficaVersante ( versante3 );
            //            pag3.setDescrizioneCausaleVersamento ( "/RFS/RF65181370001C00100000014/127.6" );
            //            sinPag3.setDatiSingoloPagamento ( pag3 );
            //            //-----------------------------------------------------------//
            //            DatiSingoloPagamentoType pag4 = new DatiSingoloPagamentoType ();
            //            pag4.setDataEsitoSingoloPagamento ( testataTEST.getDataRegolamento () );
            //            pag4.setSingoloImportoPagato ( new BigDecimal ( "32.43" ) );
            //            pag4.setIUV ( "RF16181370001T00200000023" );
            //            pag4.setIUR ( "15265374475584919011" );
            //            pag4.setIndiceDatiPagamento ( 1 );
            //            pag4.setCodiceVersamento ( "T002" );
            //            pag4.setTransactionId ( "PRD000000002244999" );
            //            pag4.setDatiSpecificiRiscossione ( "2/1210" );
            //            SoggettoType pagatore4 = new SoggettoType ();
            //            SoggettoType versante4 = new SoggettoType ();
            //            PersonaFisicaType pf4 = new PersonaFisicaType ();
            //            pagatore4.setPersonaFisica ( pf4 );
            //            pagatore4.setIdentificativoUnivocoFiscale ( "SROLSN77S61C722O" );
            //            pag4.setAnagraficaPagatore ( pagatore4 );
            //            pag4.setAnagraficaVersante ( versante4 );
            //            pag4.setDescrizioneCausaleVersamento ( "/RFS/RF16181370001T00200000023/32.43" );
            //            sinPag4.setDatiSingoloPagamento ( pag4 );
            //            //-----------------------------------------------------------//
            //            DatiSingoloPagamentoType pag5 = new DatiSingoloPagamentoType ();
            //            pag5.setDataEsitoSingoloPagamento ( testataTEST.getDataRegolamento () );
            //            pag5.setSingoloImportoPagato ( new BigDecimal ( "266.63" ) );
            //            pag5.setIUV ( "RF50181370001TF0000000410" );
            //            pag5.setIUR ( "15265386422644965011" );
            //            pag5.setIndiceDatiPagamento ( 1 );
            //            pag5.setCodiceVersamento ( "TF00" );
            //            pag5.setTransactionId ( "PRD000000002246928" );
            //            pag5.setDatiSpecificiRiscossione ( "9/E.1.01.01.48.000" );
            //            SoggettoType pagatore5 = new SoggettoType ();
            //            SoggettoType versante5 = new SoggettoType ();
            //            PersonaFisicaType pf5 = new PersonaFisicaType ();
            //            pf5.setNome ( "Enrico" );
            //            pf5.setCognome ( "ROSSI" );
            //            pagatore5.setIdentificativoUnivocoFiscale ( "RSSNRC83B24D205L" );
            //            pagatore5.setPersonaFisica ( pf5 );
            //            pag5.setAnagraficaPagatore ( pagatore5 );
            //            pag5.setAnagraficaVersante ( versante5 );
            //            pag5.setDescrizioneCausaleVersamento ( "/RFS/RF50181370001TF0000000410/266.63" );
            //            sinPag5.setDatiSingoloPagamento ( pag5 );
            //            //-----------------------------------------------------------//
            //            DatiSingoloPagamentoType pag6 = new DatiSingoloPagamentoType ();
            //            pag6.setDataEsitoSingoloPagamento ( testataTEST.getDataRegolamento () );
            //            pag6.setSingoloImportoPagato ( new BigDecimal ( "123.36" ) );
            //            pag6.setIUV ( "181170560898637" );
            //            pag6.setIUR ( "15265388788634976011" );
            //            pag6.setIndiceDatiPagamento ( 1 );
            //            pag6.setCodiceVersamento ( "TF00" );
            //            pag6.setTransactionId ( "PRD000000002246928" );
            //            pag6.setDatiSpecificiRiscossione ( "9/E.1.01.01.48.000" );
            //            SoggettoType pagatore6 = new SoggettoType ();
            //            SoggettoType versante6 = new SoggettoType ();
            //            PersonaFisicaType pf6 = new PersonaFisicaType ();
            //            pf6.setNome ( "Luca" );
            //            pf6.setCognome ( "Daniele" );
            //            pagatore6.setPersonaFisica ( pf6 );
            //            pag6.setAnagraficaPagatore ( pagatore6 );
            //            pag6.setAnagraficaVersante ( versante6 );
            //            pag6.setDescrizioneCausaleVersamento ( "/RFS/181170560898637/123.36" );
            //            sinPag6.setDatiSingoloPagamento ( pag6 );
            //            //-----------------------------------------------------------//
            //            pinter1.setDatiSingoliPagamenti ( sinPag1 );
            //            pinter2.setDatiSingoliPagamenti ( sinPag2 );
            //            pinter3.setDatiSingoliPagamenti ( sinPag3 );
            //            pinter4.setDatiSingoliPagamenti ( sinPag4 );
            //            pinter5.setDatiSingoliPagamenti ( sinPag5 );
            //            pinter6.setDatiSingoliPagamenti ( sinPag6 );
            //
            //            pagamentiIntermediati.getPagamentoIntermediato ().add ( pinter1 );
            //            pagamentiIntermediati.getPagamentoIntermediato ().add ( pinter2 );
            //            pagamentiIntermediati.getPagamentoIntermediato ().add ( pinter3 );
            //            pagamentiIntermediati.getPagamentoIntermediato ().add ( pinter4 );
            //            pagamentiIntermediati.getPagamentoIntermediato ().add ( pinter5 );
            //            pagamentiIntermediati.getPagamentoIntermediato ().add ( pinter6 );
            //
            //            testataTEST.setImportoTotalePagamentiFlusso (
            //                pag1.getSingoloImportoPagato ().add ( pag2.getSingoloImportoPagato () ).add ( pag3.getSingoloImportoPagato () )
            //                    .add ( pag4.getSingoloImportoPagato () ).add ( pag5.getSingoloImportoPagato () )
            //                    .add ( pag6.getSingoloImportoPagato () ) );
            //
            //            testataTEST.setImportoTotalePagamentiIntermediati ( testataTEST.getImportoTotalePagamentiFlusso () );
            //            testataTEST.setNumeroTotalePagamentiFlusso ( new BigInteger ( "" + pagamentiIntermediati.getPagamentoIntermediato ().size () ) );
            //            testataTEST.setNumeroTotalePagamentiIntermediati ( new BigInteger ( "" + pagamentiIntermediati.getPagamentoIntermediato ().size () ) );
            //            testataTEST.setCFEnteCreditore ( "80087670016" );
            //
            //            recordTEST.setPagamentiIntermediati ( pagamentiIntermediati );
            //            recordTEST.setTestata ( testataTEST );
            //            recordTEST.setFlussoRiversamento ( flussoRiversamento );

            TrasmettiFlussoRendicontazioneExtRequestType core = new TrasmettiFlussoRendicontazioneExtRequestType ();

            //In produzione riverso l'oggetto della request
            //in core per farne marshalling e salvarlo su db

            //core = testRecord;
            core = inputReadonly;

            String xmlResult = null;

            try {
                JAXBContext context = JAXBContext.newInstance ( TrasmettiFlussoRendicontazioneExtRequestType.class );

                Marshaller m = context.createMarshaller ();
                m.setProperty ( Marshaller.JAXB_FORMATTED_OUTPUT, true );

                StringWriter sw = new StringWriter ();
                m.marshal ( core, sw );

                xmlResult = sw.toString ();

                //Salvataggio dentro la tabella di origine...

            } catch ( Exception r ) {
                logger.error ( r );
            }

            CblTFlussoOrigine flussoOrigine = new CblTFlussoOrigine ();

            flussoOrigine.setCblDTipoAcquisizione ( tipoAcquisizioneRepository.findOneByCodice ( tipoAcquisizione.name () ) );
            istitutoRicevente = enteRepository.findByCodiceFiscale ( core.getTestata ().getCFEnteCreditore () );

            CblDStatoFlusso statoFlussoDaSpacchettare = statoflussoRepository.findByCodiceStato ( StatoFlussoEnum.DA_SPACCHETTARE.getCodice () );
            CblDStatoFlusso statoFlussoInAttesa = statoflussoRepository.findByCodiceStato ( StatoFlussoEnum.IN_ATTESA.getCodice () );
            CblDStatoFlusso statoFlussoNonRiconciliabile = statoFlussoRepository.findByCodiceStato ( StatoFlussoEnum.NON_RICONCILIABILE.getCodice () );

            //------------------------------------------------------------------------------------------//
            //Controllo sulla PSP ed eventuale creazione
            //------------------------------------------------------------------------------------------//
            String idPsp = core.getTestata ().getIdPSP ();

            CblTPsp pspCurr = pspRepository.findByIdentificativoPsp ( idPsp );

            if ( ( pspCurr == null ) || ( pspCurr.getId () <= 0 ) ) {
                if ( logger.isDebugEnabled () ) {
                    logger.debug ( "Creazione della psp dalla testata" );
                }

                CblTPsp pspNew = new CblTPsp ();
                pspNew.setDenominazionePsp ( core.getTestata ().getDenominazionePSP () );
                pspNew.setFlagRiconciliabile ( true );
                pspNew.setIdentificativoPsp ( idPsp );

                pspCurr = pspRepository.saveAndFlush ( pspNew );
            }

            checkFlussoOrigine ( istitutoRicevente, core );

            flussoOrigine.setCblTElaborazione ( null );

            if ( istitutoRicevente != null ) {
                flussoOrigine.setCblDStatoFlusso ( statoFlussoDaSpacchettare );
                flussoOrigine.setCblTEnte ( istitutoRicevente );
            } else {
                flussoOrigine.setCblDStatoFlusso ( statoFlussoInAttesa );
            }

            flussoOrigine.setDataoraFlusso ( new Timestamp ( core.getTestata ().getDataOraMessaggio ().toGregorianCalendar ().getTime ().getTime () ) );
            flussoOrigine.setIdentificativoFlusso ( core.getTestata ().getIdentificativoFlusso () );
            flussoOrigine.setIdentificativoIstitutoRicevente ( istitutoRicevente.getCodiceFiscale () );
            flussoOrigine.setImportoTotalePagamenti ( core.getTestata ().getImportoTotalePagamentiFlusso () );
            flussoOrigine.setImportoTotalePagamentiIntermediati ( core.getTestata ().getImportoTotalePagamentiIntermediati () );
            flussoOrigine.setImportoTotalePagamentiNonIntermediati ( core.getTestata ().getImportoTotalePagamentiNonIntermediati () );
            flussoOrigine.setImportoTotalePagamentiSconosciuti ( core.getTestata ().getImportoTotalePagamentiSconosciuti ());
            flussoOrigine.setNumeroTotalePagamenti ( core.getTestata ().getNumeroTotalePagamentiFlusso ().intValue () );
            flussoOrigine.setNumeroTotalePagamentiIntermediati ( core.getTestata ().getNumeroTotalePagamentiIntermediati ().intValue () );
            flussoOrigine.setNumeroTotalePagamentiNonIntermediati ( core.getTestata ().getNumeroTotalePagamentiNonIntermediati ().intValue () );
            flussoOrigine.setNumeroTotalePagamentiSconosciuti ( core.getTestata ().getNumeroTotalePagamentiSconosciuti ().intValue () );
            flussoOrigine.setDataRegolamento ( new java.sql.Date ( DateUtils.xmlGregorianCalendarToDate ( core.getTestata ().getDataRegolamento () ).getTime () ) );
            flussoOrigine.setIdentificativoUnivocoRegolamento ( core.getTestata ().getIdentificativoUnivocoRegolamento () );

            //

            flussoOrigine.setCblTPsp ( pspCurr );
            flussoOrigine.setXmlFlusso ( xmlResult );
            flussoOrigine.setContatoreTentativi ( 0 );
            flussoOrigine.setUtenteInserimento ( Costanti.DEFAULT_UTENTE_SISTEMA );
            flussoOrigine.setDataInserimento ( new Timestamp ( System.currentTimeMillis () ) );
            flussoOrigine.setFlagFlussoIntermediato(flussoOrigine.getImportoTotalePagamenti().compareTo(flussoOrigine.getImportoTotalePagamentiIntermediati())!=0?Boolean.TRUE :Boolean.FALSE);
            
            if ( ( core.getPagamentiIntermediati () == null || CollectionUtils.isEmpty ( core.getPagamentiIntermediati ().getPagamentoIntermediato () ) )
                && ( core.getPagamentiNonIntermediati () == null || CollectionUtils.isEmpty ( core.getPagamentiNonIntermediati ().getPagamentoIntermediato () ) )
                && ( core.getPagamentiSconosciuti () == null || CollectionUtils.isEmpty ( core.getPagamentiSconosciuti ().getPagamentoIntermediato () ) ) ) {
                logger.warn ( "PAGAMENTI INTERMEDIATI NON PRESENTI, IL FLUSSO VERRA' SALVATO IN STATO 'NON RICONCILIABILE'" );
                flussoOrigine.setCblDStatoFlusso ( statoFlussoNonRiconciliabile );
                result.getRisposta ().setCodiceDescrizione ( "000",
                    "Elaborazione completata con successo. Flusso in stato NON RICONCILIABILE - Assenza pagamenti intermediati" );
            } else {
                result.getRisposta ().setCodiceDescrizione ( "000", "Elaborazione completata con successo" );
            }

            savedAs = flussoOrigineRepository.save ( flussoOrigine );
        } catch ( Exception e ) {
            String errorCode = CostantiErrori.FLUSSO_ORIGINE_NON_INSERITO;
            String descrizioneErrore = "";
            try {
                if ( StringUtils.hasText ( e.getMessage () ) ) {
                    String message = e.getMessage ();
                    if ( message.contains ( "already exists" ) ) {
                        errorCode = CostantiErrori.RETURN_ORIGINE_PRESENTE_WARN;
                    } else if ( message.contains ( "null" ) ) {
                        errorCode = CostantiErrori.FLUSSO_ORIGINE_NULLFIELD;
                        descrizioneErrore = e.getMessage ();
                    } else {
                        errorCode = CostantiErrori.FLUSSO_ORIGINE_NON_INSERITO;
                    }
                }
            } catch ( Exception e1 ) {
                logger.error ( "Errore in fase determinazione del probelma di salvataggio del flusso origine!", e );
            }
            CblDErrore errore = erroreRepository.findByCodiceErrore ( errorCode );
            result.getRisposta ().setCodiceDescrizione ( errore.getCodiceErrore (),
                StringUtils.hasText ( descrizioneErrore ) ? descrizioneErrore : errore.getDescrizioneErrore () );
            inviaMailRispostaErroreSpacchettamento ( result );
        }

        try {
            if ( ( savedAs != null ) && ( istitutoRicevente != null ) ) {
                spacchettaSingoloFlussoConTestBusiness ( istitutoRicevente, savedAs );
            }
        } catch ( Exception e ) {
            CblDErrore errore = erroreRepository.findByCodiceErrore ( CostantiErrori.ALCUNI_FLUSSI_NON_SPACCHETTATI );
            result.getRisposta ().setCodiceDescrizione ( errore.getCodiceErrore (), errore.getDescrizioneErrore () + " - " + e.getMessage () );
            inviaMailRispostaErroreSpacchettamento ( result );
        }

        ResponseType response = new ResponseType ();
        response.setResult ( new ResultType () );
        response.getResult ().setCodice ( result.getRisposta ().getCodice () );
        response.getResult ().setMessaggio ( result.getRisposta ().getDescrizione () );
        logger.info ( "FlussiRendicontazioneServiceImpl.acquisciFlussoRendicontazione: FINE" );
        return response;
    }

    private void inviaMailRispostaErroreSpacchettamento ( DTOOutputWsElaborazione result ) {

        String messaggioAdd = "";

        messaggioAdd += "Descrizione dettagliata a seguire\n";
        messaggioAdd += "\nCodice:" + result.getRisposta ().getCodice ();
        messaggioAdd += "\nDescrizione:" + result.getRisposta ().getDescrizione ();
        //messaggioAdd += "\nStack:" + result.getRisposta ().getStacktrace ();
        //messaggioAdd += "\nEsito:" + result.getRisposta ().getEsito ();
        messaggioAdd += "\nTimestamp:" + result.getRisposta ().getTimestamp ();

        invioMailManager.invioMail ( EmailEnum.ERRORE_FLUSSO, CostantiErrori.ERRORE_SPACCHETTAMENTO,
            result.getIdEnte (),
            null, messaggioAdd );
    }

    private void checkFlussoOrigine ( CblTEnte istitutoRicevente, TrasmettiFlussoRendicontazioneExtRequestType core ) throws IllegalArgumentException {
        String err = "Il campo %s risulta nullo";

        Assert.notNull ( core.getTestata ().getCFEnteCreditore (), String.format ( err, "cfEnteCreditore" ) );
        Assert.notNull ( core.getTestata ().getDataOraMessaggio (), String.format ( err, "dataOraMessaggio" ) );
        Assert.notNull ( core.getTestata ().getIdentificativoFlusso (), String.format ( err, "identificativoFlusso" ) );
        Assert.notNull ( core.getTestata ().getImportoTotalePagamentiFlusso (), String.format ( err, "istitutoimportoTotalePagamentiFlusso" ) );
        Assert.notNull ( core.getTestata ().getImportoTotalePagamentiIntermediati (), String.format ( err, "importoTotalePagamentiIntermediati" ) );
        Assert.notNull ( core.getTestata ().getNumeroTotalePagamentiFlusso (), String.format ( err, "numeroTotalePagamentiFlusso" ) );
        Assert.notNull ( core.getTestata ().getNumeroTotalePagamentiIntermediati (), String.format ( err, "numeroTotalePagamentiIntermediati" ) );
        Assert.notNull ( core.getTestata ().getDataRegolamento (), String.format ( err, "dataRegolamento" ) );
        Assert.notNull ( core.getTestata ().getIdentificativoUnivocoRegolamento (), String.format ( err, "identificativoUnivocoRegolamento" ) );

        Assert.notNull ( istitutoRicevente, String.format ( err, "istitutoRicevente" ) );
    }

    @Override
    public DTOOutputWsElaborazione
    spacchettaFlussoRendicontazioneDaSpacchettareByEnteByIdentificativoFlusso ( DTOInputWsSpacchettamentoFlussoRendicontazione in ) {
        logger.info ( "FlussiRendicontazioneServiceImpl.spacchettaFlussoRendicontazioneDaSpacchettareByEnteByIdentificativoFlusso: INIZIO" );

        DTOOutputWsElaborazione result = new DTOOutputWsElaborazione ();

        List<String> flussiKo = new ArrayList<> ();

        CblTEnte ente = enteRepository.findByCodiceFiscale ( in.getCodiceFiscaleEnte () );

        List<String> identificativiFlusso = new ArrayList<> ();
        identificativiFlusso.add ( in.getIdFlussoOrigine () );

        CblDStatoFlusso statoFlussoDaSpacchettare = statoFlussoRepository.findByCodiceStato ( StatoFlussoEnum.DA_SPACCHETTARE.getCodice () );
        CblDStatoFlusso statoFlussoInAttesa = statoFlussoRepository.findByCodiceStato ( StatoFlussoEnum.IN_ATTESA.getCodice () );

        List<CblDStatoFlusso> listStatoFlusso = new ArrayList<> ();
        listStatoFlusso.add ( statoFlussoDaSpacchettare );
        listStatoFlusso.add ( statoFlussoInAttesa );

        List<CblTFlussoOrigine> flussoOrigine
        = flussoOrigineRepository.findByIdentificativoIstitutoRiceventeAndIdentificativoFlussoInAndCblDStatoFlussoInOrderByIdAsc (
            in.getCodiceFiscaleEnte (),
            identificativiFlusso,
            listStatoFlusso );

        //List<CblTFlussoOrigine> flussoOrigine = flussoOrigineRepository.findByIdentificativoFlussoAndIdentificativoIstitutoRiceventeOrderByIdAsc ( in.getIdFlussoOrigine (), in.getCodiceFiscaleEnte () );

        //        flussoOrigineRepository.findByIdentificativoIstitutoRiceventeAndIdentificativoFlussoInAndCblDStatoFlussoInOrderByIdAsc ( in.getIdFlussoOrigine (),
        //            in.get, cblDStatiFlusso );
        try {
            spacchettaSingoloFlussoConTestBusiness ( ente, flussoOrigine.get ( 0 ) );
        } catch ( Exception e ) {
            flussiKo.add ( in.getIdFlussoOrigine () + " -> " + e.getLocalizedMessage () + " - FLUSSO SCARTATO" );
        }

        if ( flussiKo.size () > 0 ) {
            String flussiIgnorati = "";
            for ( Iterator<String> iterator = flussiKo.iterator (); iterator.hasNext (); ) {
                flussiIgnorati += "\n" + iterator.next ();
            }

            CblDErrore errore = erroreRepository.findByCodiceErrore ( CostantiErrori.ALCUNI_FLUSSI_NON_SPACCHETTATI );
            result.getRisposta ().setCodiceDescrizione ( errore.getCodiceErrore (), errore.getDescrizioneErrore () + " - Flussi ignorati : " + flussiIgnorati );

            inviaMailRispostaErroreSpacchettamento ( result );
        } else {
            //result.getRisposta ().setEsito ( "Flusso trattato con successo" );
            result.getRisposta ().setCodiceDescrizione ( CostantiErrori.WS_ESITO_OK_DEFAULT, "Flussi trattati con successo" );
        }

        logger.info ( "FlussiRendicontazioneServiceImpl.spacchettaFlussoRendicontazioneDaSpacchettareByEnteByIdentificativoFlusso: FINE" );

        return result;
    }

    @Override
    public DTOOutputWsElaborazione spacchettaFlussiRendicontazioneDaSpacchettareByEnte ( DTOInputWsAcquisizioneFlussoRendicontazione in ) {
        logger.info ( "FlussiRendicontazioneServiceImpl.spacchettaFlussiRendicontazioneDaSpacchettareByEnte: INIZIO" );

        DTOOutputWsElaborazione result = new DTOOutputWsElaborazione ();

        List<String> flussiKo = new ArrayList<> ();

        CblTEnte enteDb = enteRepository.findByCodiceFiscale ( in.getCodiceFiscale () );

        CblDStatoFlusso statoFlussoDaSpacchettare = statoFlussoRepository.findByCodiceStato ( StatoFlussoEnum.DA_SPACCHETTARE.getCodice () );
        CblDStatoFlusso statoFlussoInAttesa = statoFlussoRepository.findByCodiceStato ( StatoFlussoEnum.IN_ATTESA.getCodice () );

        List<CblDStatoFlusso> listStatoFlusso = new ArrayList<> ();
        listStatoFlusso.add ( statoFlussoDaSpacchettare );
        listStatoFlusso.add ( statoFlussoInAttesa );

        List<CblTFlussoOrigine> flussiOrigine
        = flussoOrigineRepository.findByCblTEnteAndCblDStatoFlussoInOrderByIdAsc ( enteDb, listStatoFlusso );

        for ( Iterator<CblTFlussoOrigine> iteratorflussiOrigine = flussiOrigine.iterator (); iteratorflussiOrigine.hasNext (); ) {

            CblTFlussoOrigine cblTFlussoOrigineCurrent = iteratorflussiOrigine.next ();

            try {
                spacchettaSingoloFlussoConTestBusiness ( enteDb, cblTFlussoOrigineCurrent );
            } catch ( EpaymodricException e ) {
                flussiKo.add ( cblTFlussoOrigineCurrent.getIdentificativoFlusso () + " -> " + e.getLocalizedMessage () + " - FLUSSO SCARTATO" );
            }
        }

        if ( flussiKo.size () > 0 ) {
            String flussiIgnorati = "";
            for ( Iterator<String> iterator = flussiKo.iterator (); iterator.hasNext (); ) {
                flussiIgnorati += "\n" + iterator.next ();
            }
            //result.getRisposta ().setEsito ( "Alcuni flussi sono stati ignorati per errori" );
            CblDErrore errore = erroreRepository.findByCodiceErrore ( CostantiErrori.ALCUNI_FLUSSI_NON_SPACCHETTATI );
            result.getRisposta ().setCodiceDescrizione ( errore.getCodiceErrore (), errore.getDescrizioneErrore () + " - Flussi ignorati : " + flussiIgnorati );

            inviaMailRispostaErroreSpacchettamento ( result );
        } else {
            //result.getRisposta ().setEsito ( "Flussi trattati con successo" );
            result.getRisposta ().setCodiceDescrizione ( CostantiErrori.WS_ESITO_OK_DEFAULT, "Flussi trattati con successo" );
        }

        logger.info ( "FlussiRendicontazioneServiceImpl.spacchettaFlussiRendicontazioneDaSpacchettareByEnte: FINE" );
        return result;
    }

    @Transactional ( rollbackFor = EpaymodricException.class, propagation = Propagation.REQUIRES_NEW )
    public void inserisciErroreSpacchettamento ( CblTEnte ente, CblTFlussoOrigine flussoOrigine, String errorCode, String dettaglio, boolean cambioStato ) {
        logger.info ( "FlussoRendicontazioneManagerImpl.inserisciErrore: INIZIO" );
        CblDErrore errore = erroreRepository.findByCodiceErrore ( errorCode );

        //TODO : Se il codice di errore non e' presente sulla errori allora va bene uguale ne metteremo uno di tipo NOT_FOUND appena disponibile
        //TODO La gestione della mail di segnalazione va fatta contestualmente all'errore

        StatoFlussoErrore statoFlussoErrore = new StatoFlussoErrore ();
        statoFlussoErrore.setDataAggiornamentoStato ( new Timestamp ( System.currentTimeMillis () ) );
        statoFlussoErrore.setDescrizioneAggiuntivaErrore ( dettaglio + " - FLUSSO : " + flussoOrigine.getIdentificativoFlusso () );
        statoFlussoErrore.setFlussoOrigine ( FlussiUtility.getFlussoOrigine ( flussoOrigine ) );
        statoFlussoErrore.setErrore ( ErroreUtility.getErrore ( errore ) );

        statoFlussoErroreManager.inserisciFlussoErrore ( statoFlussoErrore );

        if ( cambioStato == true ) {
            CblTFlussoOrigine origine = flussoOrigineRepository.findOne ( flussoOrigine.getId () );

            CblDStatoFlusso flussoErroreStato = statoFlussoRepository.findByCodiceStato ( StatoFlussoEnum.IN_ERRORE.getCodice () );
            origine.setCblDStatoFlusso ( flussoErroreStato );

            flussoOrigineRepository.save ( origine );
        }

        logger.info ( "FlussoRendicontazioneManagerImpl.inserisciErrore: FINE" );
    }

    @Override
    @Transactional ( rollbackFor = EpaymodricException.class, propagation = Propagation.REQUIRES_NEW )
    public void spacchettaSingoloFlussoUndo ( CblTEnte enteDb, CblTFlussoOrigine flusso ) throws EpaymodricException {
        logger.info ( "FlussoRendicontazioneManagerImpl.spacchettaSingoloFlussoUndo: INIZIO" );

        List<CblTFlussoSintesi> flussiSintesi
        = flussoSintesiRepository.findByCblTFlussoOrigineIdentificativoFlussoAndCblTEnte ( flusso.getIdentificativoFlusso (), enteDb );

        if ( flussiSintesi.size () > 0 ) {

            for ( Iterator<CblTFlussoSintesi> iterator = flussiSintesi.iterator (); iterator.hasNext (); ) {
                CblTFlussoSintesi cblTFlussoSintesi = iterator.next ();
                flussoDettaglioRepository.deleteByCblTFlussoOrigine ( cblTFlussoSintesi.getId () );
            }

            flussoSintesiRepository.delete ( flussiSintesi );

            CblDStatoFlusso statoflusso = statoFlussoRepository.findByCodiceStato ( StatoFlussoEnum.BOZZA.getCodice () );
            flusso.setCblDStatoFlusso ( statoflusso );

            flussoOrigineRepository.save ( flusso );

            try {
                flussoDettaglioRepository.flush ();
                flussoSintesiRepository.flush ();
                flussoOrigineRepository.flush ();
            } catch ( Exception e ) {
            }
        }

        logger.info ( "FlussoRendicontazioneManagerImpl.spacchettaSingoloFlussoUndo: FINE" );
    }

    @Override
    @Transactional ( rollbackFor = EpaymodricException.class, propagation = Propagation.REQUIRES_NEW )
    public void spacchettaSingoloFlussoConTestBusiness ( CblTEnte enteDb, CblTFlussoOrigine flussoOrigine ) throws EpaymodricException {
        logger.info ( "FlussoRendicontazioneManagerImpl.spacchettaFlusso: INIZIO" );

        //        Integer totaleFlussoCalcolatoNumeroPagamenti = 0;
        //        BigDecimal totaleFlussoCalcolatoDaPagamenti = BigDecimal.ZERO;

        TrasmettiFlussoRendicontazioneExtRequestType flussoRendicontazioneEsteso = null;
        //        CtFlussoRiversamento flussoRiversamento = null;

        try {
            flussoRendicontazioneEsteso = FlussiUtility.getFlussoRendicontazioneEsteso ( flussoOrigine.getXmlFlusso () );
            //flussoRiversamento = FlussiUtility.getFlussoRiversamento ( flussoOrigine.getXmlFlusso () );
        } catch ( JAXBException e ) {
            String typo = "Il flusso ha errori di formato xml";

            CblDStatoFlusso cblDStatoFlussoErrore = statoFlussoRepository.findByCodiceStato ( StatoFlussoEnum.IN_ERRORE.getCodice () );

            flussoOrigine.setCblDStatoFlusso ( cblDStatoFlussoErrore );
            flussoOrigineRepository.save ( flussoOrigine );

            inserisciErroreSpacchettamento ( enteDb, flussoOrigine, CostantiErrori.ERRORE_SPACCHETTAMENTO, typo, true );

            throw new EpaymodricException ( typo );
        }

        //------------------------------------------------------------------------------------------//
        //Controllo flusso gia' spacchettato
        //------------------------------------------------------------------------------------------//
        List<String> identificativoFlusso = new ArrayList<> ();
        identificativoFlusso.add ( flussoRendicontazioneEsteso.getTestata ().getIdentificativoFlusso () );

        TestataFlussoRendicontazioneExtType testata = flussoRendicontazioneEsteso.getTestata ();

        List<CblTFlussoSintesi> alreadyPresentSintesi
        = flussoSintesiRepository.findByCblTEnteAndCblTFlussoOrigineIdentificativoFlussoIn ( enteDb, identificativoFlusso );

        if ( alreadyPresentSintesi.size () > 0 ) {
            String typo = "Flusso gia' spacchettato";

            inserisciErroreSpacchettamento ( enteDb, flussoOrigine, CostantiErrori.ERRORE_SPACCHETTAMENTO_ESISTENTE, typo, false );

            throw new EpaymodricException ( typo );
        }

        //        String nullCodiceVersamentoDatiRiscossione = "";

        //------------------------------------------------------------------------------------------//
        //Controllo codiceversamento + dati specifici riscossione
        //------------------------------------------------------------------------------------------//
        //        BigDecimal importoNonItermediatoTestata = testata.getImportoTotalePagamentiFlusso ().subtract ( testata.getImportoTotalePagamentiIntermediati () );
        //        BigDecimal importoNonIntermediatoCalcolato = BigDecimal.ZERO;
        //
        //        if ( null != flussoRendicontazioneEsteso.getPagamentiIntermediati ()
        //            && null != flussoRendicontazioneEsteso.getPagamentiIntermediati ().getPagamentoIntermediato () ) {
        //            for ( Iterator<PagamentoIntermediatoType> iterator = flussoRendicontazioneEsteso.getPagamentiIntermediati ().getPagamentoIntermediato ().iterator ();
        //                            iterator.hasNext (); ) {
        //                PagamentoIntermediatoType pagamentoIntermediato = iterator.next ();
        //
        //                DatiSingoloPagamentoType singoloPagamento = pagamentoIntermediato.getDatiSingoliPagamenti ().getDatiSingoloPagamento ();
        //
        //                totaleFlussoCalcolatoDaPagamenti = totaleFlussoCalcolatoDaPagamenti.add ( singoloPagamento.getSingoloImportoPagato () );
        //                totaleFlussoCalcolatoNumeroPagamenti++;
        //
        //                boolean nullValue = false;
        //
        //                nullValue |= (( singoloPagamento.getDatiSpecificiRiscossione () == null ) || (singoloPagamento.getDatiSpecificiRiscossione ().isEmpty ()));
        //                nullValue |= (( singoloPagamento.getCodiceVersamento () == null ) || (singoloPagamento.getCodiceVersamento ().isEmpty ()));
        //
        //                if ( nullValue ) {
        //                    if ( singoloPagamento.getTransactionId () != null )
        //                        nullCodiceVersamentoDatiRiscossione = " -> TransactionID : " + singoloPagamento.getTransactionId ();
        //                    else if ( singoloPagamento.getIUV () != null )
        //                        nullCodiceVersamentoDatiRiscossione = " -> Identificativo versamento : " + singoloPagamento.getIUV ();
        //                }
        //
        //                if ( nullValue ) {
        //                    importoNonIntermediatoCalcolato = importoNonIntermediatoCalcolato.add ( singoloPagamento.getSingoloImportoPagato () );
        //                }
        //            }
        //        }

        //------------------------------------------------------------------------------------------//
        //Controllo sui pagamenti intermediati
        //------------------------------------------------------------------------------------------//
        //if ( importoNonItermediatoTestata.compareTo ( importoNonIntermediatoCalcolato ) != 0 ) {
        //    String typo = "Discrepanza fra il totale dei pagamenti non intermediati calcolati (" + importoNonIntermediatoCalcolato
        //                  + ") e il totale dei pagamenti non intermediati di testata (" + importoNonItermediatoTestata + ")" + nullCodiceVersamentoDatiRiscossione;
        //  inserisciErroreSpacchettamento ( enteDb, flussoOrigine, CostantiErrori.TOTALE_PAGAMENTI_INTERMEDIATI, typo, true );
        //
        //  throw new EpaymodricException ( typo );
        //}

        //------------------------------------------------------------------------------------------//
        //Controllo sul plurimandatario con dati specifici riscossione o codice versamento a null
        //------------------------------------------------------------------------------------------//
        //Cassato in data 18 settembre 2018
        //------------------------------------------------------------------------------------------//
        //if ( ( enteDb.getEntePlurintermediato () == false ) && ( nullCodiceVersamentoDatiRiscossione.isEmpty () == false ) ) {
        //    String typo
        //    = "Ente non pluriintermediato con dati specifici riscossione/codice versamento non presenti nel pagamento" + nullCodiceVersamentoDatiRiscossione;
        //
        //  inserisciErroreSpacchettamento ( enteDb, flussoOrigine, CostantiErrori.DATI_VERSAMENTO_MANCANTI, typo, true );
        //
        //  throw new EpaymodricException ( typo );
        //}

        //------------------------------------------------------------------------------------------//
        //Controllo sul plurimandatario e importi totali pagamenti
        //------------------------------------------------------------------------------------------//
        //Cassato in data 18 settembre 2018
        //------------------------------------------------------------------------------------------//
        //boolean controlloPagamenti = ( testata.getImportoTotalePagamentiFlusso ().compareTo ( testata.getImportoTotalePagamentiIntermediati () ) == 0 );
        //
        //if ( ( enteDb.getEntePlurintermediato () == true ) && ( controlloPagamenti == false ) ) {
        //   String typo
        //    = "Ente pluriintermediato con dati specifici riscossione/codice versamento non presenti nel pagamento" + nullCodiceVersamentoDatiRiscossione;
        //
        //  inserisciErroreSpacchettamento ( enteDb, flussoOrigine, CostantiErrori.DATI_VERSAMENTO_MANCANTI, typo, true );
        //
        //  throw new EpaymodricException ( typo );
        //}
        //
        //if ( ( enteDb.getEntePlurintermediato () == false ) && ( controlloPagamenti == false ) ) {
        //  String typo
        //  = "Ente non pluriintermediato con dati specifici riscossione/codice versamento non presenti nel pagamento" + nullCodiceVersamentoDatiRiscossione;
        //
        //  inserisciErroreSpacchettamento ( enteDb, flussoOrigine, CostantiErrori.DATI_VERSAMENTO_MANCANTI, typo, true );
        //
        //  throw new EpaymodricException ( typo );
        //}

        //------------------------------------------------------------------------------------------//
        //Controllo sul totale dei pagamenti del flusso
        //------------------------------------------------------------------------------------------//
        //Cassato in data 18 settembre 2018
        //------------------------------------------------------------------------------------------//
        //if ( totaleFlussoCalcolatoDaPagamenti.compareTo ( testata.getImportoTotalePagamentiIntermediati () ) != 0 ) {
        //    String typo = "Discrepanza fra il totale flusso dei pagamenti calcolato (" + totaleFlussoCalcolatoDaPagamenti.toPlainString ()
        //      + ") e il totale dei pagamenti intermediati di testata ("
        //      + testata.getImportoTotalePagamentiIntermediati ().toString () + ")";
        //
        //  inserisciErroreSpacchettamento ( enteDb, flussoOrigine, CostantiErrori.ERRORE_SPACCHETTAMENTO, typo, true );
        //
        //  throw new EpaymodricException ( typo );
        //}

        //------------------------------------------------------------------------------------------//
        //Controllo sul totale dei pagamenti del flusso
        //------------------------------------------------------------------------------------------//
        //Cassato in data 18 settembre 2018
        //------------------------------------------------------------------------------------------//
        //if ( totaleFlussoCalcolatoNumeroPagamenti.compareTo ( testata.getNumeroTotalePagamentiIntermediati ().intValue () ) != 0 ) {
        //  String typo = "Discrepanza fra il totale  del numero totale dei pagamenti calcolato (" + totaleFlussoCalcolatoNumeroPagamenti
        //      + ") e il numero totale dei pagamenti intermediati ("
        //      + testata.getNumeroTotalePagamentiIntermediati () + ")";
        //
        //  inserisciErroreSpacchettamento ( enteDb, flussoOrigine, CostantiErrori.ERRORE_SPACCHETTAMENTO, typo, true );
        //
        //  throw new EpaymodricException ( typo );
        //}
        //        //Forzatura di sicurezza
        //        int blockItems = 0;
        //        int blockCatalog = 0;
        //
        //        List<CblTCatalogo> catalogo = catalogoRepository.findByIdEnte ( enteDb.getIdEnte () );
        //
        //        for ( Iterator iterator = singoliPagamenti.iterator (); iterator.hasNext (); ) {
        //
        //            CtDatiSingoliPagamenti ctDatiSingoliPagamenti = (CtDatiSingoliPagamenti) iterator.next ();
        //
        //            String codiceVersamentoCurr = ctDatiSingoliPagamenti.getCodiceVersamento ();
        //            String datiRiscossioneCurr = ctDatiSingoliPagamenti.getDatiSpecificiRiscossione ();
        //
        //            if ( ( codiceVersamentoCurr == null ) || ( datiRiscossioneCurr == null ) ) {
        //                ctDatiSingoliPagamenti.setCodiceVersamento ( "" + catalogo.get ( blockCatalog ).getCodiceVersamento () );
        //                ctDatiSingoliPagamenti.setDatiSpecificiRiscossione ( "" + catalogo.get ( blockCatalog ).getDatiSpecificiRiscossione () );
        //                blockItems++;
        //                if ( blockItems >= 20 ) {
        //                    blockItems = 0;
        //                    blockCatalog++;
        //                }
        //                if ( blockCatalog > catalogo.size () ) {
        //                    blockCatalog = 0;
        //                }
        //            }
        //        }

        List<PagamentoIntermediatoType> singoliPagamenti = new LinkedList<> ();
        if ( null != flussoRendicontazioneEsteso.getPagamentiIntermediati ()
            && !CollectionUtils.isEmpty ( flussoRendicontazioneEsteso.getPagamentiIntermediati ().getPagamentoIntermediato () ) ) {
            singoliPagamenti.addAll ( flussoRendicontazioneEsteso.getPagamentiIntermediati ().getPagamentoIntermediato () );
        }
        // cercare codice versamento per i non intermediati.
        if ( null != flussoRendicontazioneEsteso.getPagamentiNonIntermediati ()
            && !CollectionUtils.isEmpty ( flussoRendicontazioneEsteso.getPagamentiNonIntermediati ().getPagamentoIntermediato () ) ) {
            for ( PagamentoIntermediatoType pi: flussoRendicontazioneEsteso.getPagamentiNonIntermediati ().getPagamentoIntermediato () ) {
                if ( "----".equals ( pi.getDatiSingoliPagamenti ().getDatiSingoloPagamento ().getCodiceVersamento () ) ) {
                    List<CblTCatalogo> covs = catalogoRepository.recuperaCodiceVersamentoByEnteAndDSRAndAnnoAndDataAndFlagAnnullato ( enteDb.getIdEnte (),
                        pi.getDatiSingoliPagamenti ().getDatiSingoloPagamento ().getDatiSpecificiRiscossione (),
                        flussoRendicontazioneEsteso.getTestata ().getDataRegolamento ().getYear (),
                        DateUtils.xmlGregorianCalendarToDate ( pi.getDatiSingoliPagamenti ().getDatiSingoloPagamento ().getDataEsitoSingoloPagamento () ) );
                    if ( !CollectionUtils.isEmpty ( covs ) && covs.size () == 1 ) {
                        pi.getDatiSingoliPagamenti ().getDatiSingoloPagamento ().setCodiceVersamento ( covs.get ( 0 ).getCodiceVersamento () );
                    }
                }
                singoliPagamenti.add ( pi );
            }
        }
        
        if ( null != flussoRendicontazioneEsteso.getPagamentiSconosciuti ()
            && !CollectionUtils.isEmpty ( flussoRendicontazioneEsteso.getPagamentiSconosciuti ().getPagamentoIntermediato () ) ) {
            singoliPagamenti.addAll ( flussoRendicontazioneEsteso.getPagamentiSconosciuti ().getPagamentoIntermediato () );
        }
        
        //singoliPagamenti.addAll ( flussoRendicontazioneEsteso.getPagamentiSconosciuti ().getPagamentoIntermediato () );//valutare bene se salvarli e ignorarli oppure non salvarli
        while ( singoliPagamenti.size () > 0 ) {

            //----------------------------------------------------------------------------------//
            //Ciclo sulla tabella dei pagamenti alla ricerca dei pagamenti comuni per accorparli
            //----------------------------------------------------------------------------------//
            List<DatiSingoloPagamentoType> singoliPagamentiAccorpati = new ArrayList<> ();

            DatiSingoloPagamentoType pagamentoStart = singoliPagamenti.get ( 0 ).getDatiSingoliPagamenti ().getDatiSingoloPagamento ();

            for ( int i = ( singoliPagamenti.size () - 1 ); i >= 0; i-- ) {

                DatiSingoloPagamentoType singoloPagamentoCorrente = singoliPagamenti.get ( i ).getDatiSingoliPagamenti ().getDatiSingoloPagamento ();

                boolean found = true;

                if ( logger.isDebugEnabled () ) {
                    logger.debug ( "Pagamenti in processing " + singoliPagamenti.size () );
                }
                String codiceVersamentoStart = pagamentoStart.getCodiceVersamento ();
                String datiRiscossioneStart = pagamentoStart.getDatiSpecificiRiscossione ();
                Integer annoAccertamentoStart = pagamentoStart.getAnnoAccertamento () != null ? pagamentoStart.getAnnoAccertamento () : 0;;
                Integer numeroAccertamentoStart = pagamentoStart.getNumeroAccertamento () != null ? pagamentoStart.getNumeroAccertamento () : 0;
                boolean pagamentoNegativoStart = ( pagamentoStart.getSingoloImportoPagato ().compareTo ( BigDecimal.ZERO ) == -1 );

                String codiceVersamentoCurr = singoloPagamentoCorrente.getCodiceVersamento ();
                String datiRiscossioneCurr = singoloPagamentoCorrente.getDatiSpecificiRiscossione ();
                Integer annoAccertamentoCurr = singoloPagamentoCorrente.getAnnoAccertamento () != null ? singoloPagamentoCorrente.getAnnoAccertamento () : 0;
                Integer numeroAccertamentoCurr
                = singoloPagamentoCorrente.getNumeroAccertamento () != null ? singoloPagamentoCorrente.getNumeroAccertamento () : 0;
                boolean pagamentoNegativoCurr = ( singoloPagamentoCorrente.getSingoloImportoPagato ().compareTo ( BigDecimal.ZERO ) == -1 );

                boolean nullValueStart = false;
                boolean nullValueCurr = false;

                nullValueStart |= ( codiceVersamentoStart == null );
                nullValueStart |= ( datiRiscossioneStart == null );
                nullValueStart |= ( annoAccertamentoStart == null );
                nullValueStart |= ( numeroAccertamentoStart == null );
                nullValueCurr |= ( codiceVersamentoCurr == null );
                nullValueCurr |= ( datiRiscossioneCurr == null );
                nullValueCurr |= ( annoAccertamentoCurr == null );
                nullValueCurr |= ( numeroAccertamentoCurr == null );

                if ( nullValueStart ) {
                    codiceVersamentoStart = "EMPTY";
                    datiRiscossioneStart = "EMPTY";
                    annoAccertamentoCurr = 0;
                    numeroAccertamentoCurr = 0;
                }

                if ( nullValueCurr ) {
                    codiceVersamentoCurr = "EMPTY";
                    datiRiscossioneCurr = "EMPTY";
                    annoAccertamentoCurr = 0;
                    numeroAccertamentoCurr = 0;
                }

                found &= ( codiceVersamentoStart.equalsIgnoreCase ( codiceVersamentoCurr ) );
                found &= ( datiRiscossioneStart.equalsIgnoreCase ( datiRiscossioneCurr ) );
                found &= ( annoAccertamentoStart.equals ( annoAccertamentoCurr ) );
                found &= ( numeroAccertamentoStart.equals ( numeroAccertamentoCurr ) );
                found &= ( pagamentoNegativoStart == pagamentoNegativoCurr );

                if ( found ) {
                    singoliPagamentiAccorpati.add ( singoloPagamentoCorrente );
                    singoliPagamenti.remove ( i );
                }
            }

            if ( singoliPagamentiAccorpati.size () > 0 ) {
                if ( logger.isDebugEnabled () ) {
                    logger.debug ( "Pagamenti in accorpati " + singoliPagamentiAccorpati.size () );
                }
                String codiceVersamento = singoliPagamentiAccorpati.get ( 0 ).getCodiceVersamento ();
                String datiSpecificiRiscossione = singoliPagamentiAccorpati.get ( 0 ).getDatiSpecificiRiscossione ();
                Integer annoAccertamento = singoliPagamentiAccorpati.get ( 0 ).getAnnoAccertamento ();
                Integer numeroAccertamento = singoliPagamentiAccorpati.get ( 0 ).getNumeroAccertamento ();

                if ( null == codiceVersamento || null == datiSpecificiRiscossione || codiceVersamento.isEmpty () || datiSpecificiRiscossione.isEmpty () ) {
                    inserisciErroreSpacchettamento ( enteDb, flussoOrigine, CostantiErrori.ERRORE_SPACCHETTAMENTO,
                        "Codice versamento (" + codiceVersamento + ") o dati specifici riscossione (" + datiSpecificiRiscossione + ") non valorizzati", true );
                    continue;
                }

                BigDecimal importoQuotaAggregazione = BigDecimal.ZERO;
                BigDecimal numeroVersamenti = BigDecimal.ZERO;

                for ( Iterator<DatiSingoloPagamentoType> iterator = singoliPagamentiAccorpati.iterator (); iterator.hasNext (); ) {
                    DatiSingoloPagamentoType ctDatiSingoliPagamenti = iterator.next ();

                    importoQuotaAggregazione = importoQuotaAggregazione.add ( ctDatiSingoliPagamenti.getSingoloImportoPagato () );
                    numeroVersamenti = numeroVersamenti.add ( BigDecimal.ONE );
                }

                List<CblTCatalogo> catalogoList = catalogoRepository.findByIdEnteAndCodiceVersamentoAndDatiSpecificiRiscossioneAndAnnoEsercizio (
                    enteDb.getIdEnte (),
                    codiceVersamento,
                    datiSpecificiRiscossione,
                    flussoRendicontazioneEsteso.getTestata ().getDataRegolamento ().getYear () );

                Calendar cal = Calendar.getInstance ();

                cal.set ( Calendar.YEAR, 1000 );

                Date dataPagamentoCalcolata = cal.getTime ();

                for ( Iterator<DatiSingoloPagamentoType> iterator = singoliPagamentiAccorpati.iterator (); iterator.hasNext (); ) {

                    DatiSingoloPagamentoType datiSingoloPagamento = iterator.next ();

                    if ( datiSingoloPagamento != null && datiSingoloPagamento.getDataEsitoSingoloPagamento () != null
                                    && DateUtils.xmlGregorianCalendarToDate ( datiSingoloPagamento.getDataEsitoSingoloPagamento () ).after ( dataPagamentoCalcolata ) ) {
                        dataPagamentoCalcolata = DateUtils.xmlGregorianCalendarToDate ( datiSingoloPagamento.getDataEsitoSingoloPagamento () );
                    }
                }

                CblTFlussoSintesi flussoSintesi
                = salvaFlussoSintesi ( enteDb, flussoOrigine, catalogoList, codiceVersamento, datiSpecificiRiscossione, importoQuotaAggregazione,
                    numeroVersamenti, annoAccertamento, numeroAccertamento, dataPagamentoCalcolata );

                if ( flussoSintesi.getId () != null ) {

//                    //Salvo una ulteriore riga di sintesi nel caso di :
//                    //importo totale pagamenti flusso > importo totale pagamenti intermediati
//                    //numero totale pagamenti flusso  > numero totale pagamenti intermediati
//
//                    boolean saveDummyRow = false;
//
//                    saveDummyRow |= ( testata.getImportoTotalePagamentiFlusso ().compareTo ( testata.getImportoTotalePagamentiIntermediati () ) > 0 );
//                    saveDummyRow |= ( testata.getNumeroTotalePagamentiFlusso ().compareTo ( testata.getNumeroTotalePagamentiIntermediati () ) > 0 );
//
//                    if ( saveDummyRow ) {
//                        BigDecimal importoNonIntermediatoCalcolatoTestata
//                        = testata.getImportoTotalePagamentiFlusso ().subtract ( testata.getImportoTotalePagamentiIntermediati () );
//
//                        salvaFlussoSintesi ( enteDb, flussoOrigine, catalogoList, null, null, importoNonIntermediatoCalcolatoTestata, numeroVersamenti,
//                            annoAccertamento, numeroAccertamento, null );
//                    }

                    if ( logger.isDebugEnabled () ) {
                        logger.debug ( "Flusso sintesi in salvataggio " + flussoSintesi.getId () );
                    }
                    for ( Iterator<DatiSingoloPagamentoType> iterator = singoliPagamentiAccorpati.iterator (); iterator.hasNext (); ) {
                        DatiSingoloPagamentoType datiSingoloPagamento = iterator.next ();

                        salvaFlussoDettaglio ( catalogoList, flussoSintesi, datiSingoloPagamento );
                    }

                } else {
                    //In caso di errori di inserimento della sintesi
                    String typo = "ID sintesi nullo - inserimento dettaglio fallito";

                    inserisciErroreSpacchettamento ( enteDb, flussoOrigine, CostantiErrori.ERRORE_SPACCHETTAMENTO, typo, true );

                    throw new EpaymodricException ( typo );
                }
            }
        }
        
        
        //Salvo una ulteriore riga di sintesi nel caso di :
        //importo totale pagamenti flusso > importo totale pagamenti intermediati
        //numero totale pagamenti flusso  > numero totale pagamenti intermediati

        boolean saveDummyRow = false;

        saveDummyRow |= ( testata.getImportoTotalePagamentiFlusso ().compareTo ( testata.getImportoTotalePagamentiIntermediati ().add ( testata.getImportoTotalePagamentiNonIntermediati () ).add ( testata.getImportoTotalePagamentiSconosciuti () ) ) > 0 );
        saveDummyRow |= ( testata.getNumeroTotalePagamentiFlusso ().compareTo ( testata.getNumeroTotalePagamentiIntermediati ().add ( testata.getNumeroTotalePagamentiNonIntermediati () ).add ( testata.getNumeroTotalePagamentiSconosciuti () ) ) > 0 );

        if ( saveDummyRow ) {
            BigDecimal importoNonIntermediatoCalcolatoTestata = testata.getImportoTotalePagamentiFlusso ()
                .subtract ( testata.getImportoTotalePagamentiIntermediati () ).subtract ( testata.getImportoTotalePagamentiNonIntermediati () );
            BigDecimal numeroPagamentiNonIntermediatoCalcolatoTestata = new BigDecimal ( testata.getNumeroTotalePagamentiFlusso ()
                .subtract ( testata.getNumeroTotalePagamentiIntermediati () ).subtract ( testata.getNumeroTotalePagamentiNonIntermediati () ) );

            salvaFlussoSintesi ( enteDb, flussoOrigine, null, null, null, importoNonIntermediatoCalcolatoTestata, numeroPagamentiNonIntermediatoCalcolatoTestata,
                null, null, null );
        }
        
        

        //TODO GESTIONE DELLO STORICO DELLA ORIGINE

        //        CblTStoricoFlussoOrigine flussoOrigineStorico = new CblTStoricoFlussoOrigine ();

        //        flussoOrigineStorico.setContatoreTentativi (  );
        //        flussoOrigineStorico.setDataInserimento (  );
        //        flussoOrigineStorico.setDataoraFlusso (  );
        //        flussoOrigineStorico.setIdElaborazione (  );
        //        flussoOrigineStorico.setIdentificativoFlusso (  );
        //        flussoOrigineStorico.setIdentificativoIstitutoRicevente (  );
        //        flussoOrigineStorico.setIdentificativoPsp (  );
        //        flussoOrigineStorico.setIdIstitutoRicevente (  );
        //        flussoOrigineStorico.setIdStatoFlusso (  );
        //        flussoOrigineStorico.setImportoTotalePagamenti (  );
        //        flussoOrigineStorico.set
        //        flussoOrigineStorico.set
        //
        //
        //        storicoFlussoOrigineRepository.saveAndFlush ( flussoOrigineStorico );

        CblDStatoFlusso cblDStatoFlussoBozza = statoFlussoRepository.findByCodiceStato ( StatoFlussoEnum.BOZZA.getCodice () );

        flussoOrigine.setCblDStatoFlusso ( cblDStatoFlussoBozza );
        flussoOrigineRepository.save ( flussoOrigine );
//          :TODO da togliere lollo
//        flussoOrigine.setNumeroTotalePagamenti ( testata.getNumeroTotalePagamentiFlusso ().intValue () );
//        flussoOrigine.setImportoTotalePagamenti ( testata.getImportoTotalePagamentiFlusso () );
//        flussoOrigine.setNumeroTotalePagamentiIntermediati ( testata.getNumeroTotalePagamentiIntermediati ().intValue () );
//        flussoOrigine.setImportoTotalePagamentiIntermediati ( testata.getImportoTotalePagamentiIntermediati () );

        flussoDettaglioRepository.flush ();
        flussoSintesiRepository.flush ();
        flussoOrigineRepository.flush ();

        logger.info ( "#####################################################################################################" );
        logger.info ( "SALVATAGGIO FLUSSO SINTESI " + flussoOrigine.getIdentificativoFlusso () );
        logger.info ( "#####################################################################################################" );

        logger.info ( "FlussoRendicontazioneManagerImpl.spacchettaFlusso: FINE" );
    }

    @Deprecated
    public List<String> spacchettaFlussiRendicontazioneww ( DTOInputWsAcquisizioneFlussoRendicontazione in ) {
        logger.info ( "FlussoRendicontazioneManagerImpl.spacchettaFlussiRendicontazione: INIZIO" );

        List<String> flussiKo = new ArrayList<> ();

        CblTEnte enteDb = enteRepository.findByCodiceFiscale ( in.getCodiceFiscale () );

        CblDStatoFlusso statoFlussoDaSpacchettare = statoFlussoRepository.findByCodiceStato ( StatoFlussoEnum.DA_SPACCHETTARE.getCodice () );

        List<CblDStatoFlusso> listStatoFlussoDaSpacchettare = new ArrayList<> ();
        listStatoFlussoDaSpacchettare.add ( statoFlussoDaSpacchettare );

        List<CblTFlussoOrigine> flussiOrigine
        = flussoOrigineRepository.findByCblTEnteAndCblDStatoFlussoInOrderByIdAsc ( enteDb, listStatoFlussoDaSpacchettare );

        for ( Iterator<CblTFlussoOrigine> iteratorflussiOrigine = flussiOrigine.iterator (); iteratorflussiOrigine.hasNext (); ) {

            CblTFlussoOrigine cblTFlussoOrigineCurrent = iteratorflussiOrigine.next ();

            try {
                spacchettaSingoloFlussoConTestBusiness ( enteDb, cblTFlussoOrigineCurrent );
            } catch ( EpaymodricException e ) {
                flussiKo.add ( cblTFlussoOrigineCurrent.getIdentificativoFlusso () + " -> " + e.getLocalizedMessage () + " - FLUSSO SCARTATO" );
            }
        }

        logger.info ( "FlussoRendicontazioneManagerImpl.spacchettaFlussiRendicontazione: FINE" );
        return flussiKo;
    }

    @Transactional ( propagation = Propagation.MANDATORY )
    private CblTFlussoDettaglio salvaFlussoDettaglio ( List<CblTCatalogo> catalogoList,
        CblTFlussoSintesi flussoSintesi,
        DatiSingoloPagamentoType datiSingoloPagamento ) {

        CblTFlussoDettaglio dettaglio = new CblTFlussoDettaglio ();

        GregorianCalendar dataPagamentoXml = datiSingoloPagamento.getDataEsitoSingoloPagamento ().toGregorianCalendar ();
        Timestamp dataSingoloPagamento = new Timestamp ( dataPagamentoXml.getTimeInMillis () );

        String nomeCognome = "";

        if ( datiSingoloPagamento.getAnagraficaPagatore ().getPersonaFisica () != null ) {
            if ( datiSingoloPagamento.getAnagraficaPagatore ().getPersonaFisica ().getNome () != null ) {
                if ( !datiSingoloPagamento.getAnagraficaPagatore ().getPersonaFisica ().getNome ().isEmpty () ) {
                    nomeCognome += datiSingoloPagamento.getAnagraficaPagatore ().getPersonaFisica ().getNome ();
                    nomeCognome += " ";
                }
            }

            if ( datiSingoloPagamento.getAnagraficaPagatore ().getPersonaFisica ().getCognome () != null ) {
                if ( !datiSingoloPagamento.getAnagraficaPagatore ().getPersonaFisica ().getCognome ().isEmpty () ) {
                    nomeCognome += datiSingoloPagamento.getAnagraficaPagatore ().getPersonaFisica ().getCognome ();
                }
            }

            nomeCognome = nomeCognome.trim ();
        }

        if ( datiSingoloPagamento.getAnagraficaPagatore ().getPersonaGiuridica () != null ) {
            nomeCognome += datiSingoloPagamento.getAnagraficaPagatore ().getPersonaGiuridica ().getRagioneSociale ();
        }

        if ( null != datiSingoloPagamento.getCategoriaIUV () ) {
            dettaglio.setCblDCategoriaIuv ( categoriaIuvRepository.findOneByCodice ( datiSingoloPagamento.getCategoriaIUV ().name () ) );
        }
        dettaglio.setAnagraficaPagatore ( nomeCognome );
        dettaglio.setDescrizioneCausaleVersamento ( datiSingoloPagamento.getDescrizioneCausaleVersamento () );
        dettaglio.setCblTFlussoSintesi ( flussoSintesi );
        dettaglio.setCodicefiscalePagatore ( datiSingoloPagamento.getAnagraficaPagatore ().getIdentificativoUnivocoFiscale () );
        dettaglio.setCodiceVersamento ( datiSingoloPagamento.getCodiceVersamento () );
        dettaglio.setDataPagamento ( dataSingoloPagamento );
        dettaglio.setDatiSpecificiDiRiscossione ( datiSingoloPagamento.getDatiSpecificiRiscossione () );
        dettaglio.setEsitoPagamento ( datiSingoloPagamento.getCodiceEsitoPagamento () );
        dettaglio.setIdentificativoUnicoRiscossione ( datiSingoloPagamento.getIUR () );
        dettaglio.setIdentificativoUnicoVersamento ( datiSingoloPagamento.getIUV () );
        dettaglio.setImportoSingoloVersamento ( datiSingoloPagamento.getSingoloImportoPagato () );
        dettaglio.setTransactionid ( datiSingoloPagamento.getTransactionId () );
        dettaglio.setIndiceSingoloVersamento ( datiSingoloPagamento.getIndiceDatiPagamento () );
        dettaglio.setUtenteInserimento ( Costanti.DEFAULT_UTENTE_SISTEMA );
        dettaglio.setDataInserimento ( new Timestamp ( System.currentTimeMillis () ) );

        CblTFlussoDettaglio item = flussoDettaglioRepository.saveAndFlush ( dettaglio );

        return item;
    }

    @Transactional ( propagation = Propagation.MANDATORY )
    private CblTFlussoSintesi salvaFlussoSintesi ( CblTEnte ente,
        CblTFlussoOrigine cblTFlussoOrigine,
        List<CblTCatalogo> catalogoList,
        String codiceVersamento,
        String datiRiscossione,
        BigDecimal importoQuotaAggregazione,
        BigDecimal numeroVersamentiQuote, Integer accertamentoAnno, Integer accertamentoNumero, Date dataPagamentoCalcolata ) throws EpaymodricException {

        //Sintesi
        CblTFlussoSintesi sintesi = new CblTFlussoSintesi ();

        //Dati dal flusso XML
        sintesi.setCblTEnte ( ente ); //Id istituto ricevente
        sintesi.setCblTFlussoOrigine ( cblTFlussoOrigine );
        sintesi.setCblRStatoFlussoErrore ( null );
        sintesi.setCodiceVersamento ( codiceVersamento );
        sintesi.setDatiSpecificiDiRiscossione ( datiRiscossione );

        sintesi.setImportoQuotaAggregazione ( importoQuotaAggregazione );
        sintesi.setNumeroVersQuotaAggregazione ( numeroVersamentiQuote );
        sintesi.setProvvisorioAnno ( null );
        sintesi.setProvvisorioNro ( null );
        sintesi.setAccertamentoAnno ( null != accertamentoAnno && accertamentoAnno.intValue () > 0 ? accertamentoAnno.intValue () : null );
        sintesi.setAccertamentoNumero ( null != accertamentoNumero && accertamentoNumero.intValue () > 0 ? accertamentoNumero.intValue () : null );

        sintesi.setUtenteInserimento ( Costanti.DEFAULT_UTENTE_SISTEMA );
        sintesi.setDataInserimento ( new Timestamp ( System.currentTimeMillis () ) );

        sintesi.setDataPagamentoCalcolata ( null != dataPagamentoCalcolata ? new java.sql.Date ( dataPagamentoCalcolata.getTime () ) : null );

        CblTFlussoSintesi item = flussoSintesiRepository.save ( sintesi );

        return item;
    }

    public void doRollback () {
        TransactionAspectSupport.currentTransactionStatus ().setRollbackOnly ();
    }

    //
    //    public List<String> spacchettaFlussoRendicontazioneffff ( DTOInputWsSpacchettamentoFlussoRendicontazione in ) {
    //        logger.info ( "FlussoRendicontazioneManagerImpl.spacchettaFlussoRendicontazione: INIZIO" );
    //
    //        List<String> flussiKo = new ArrayList<String> ();
    //
    //        CblTEnte ente = enteRepository.findByCodiceFiscale ( in.getCodiceFiscaleEnte () );
    //
    //        List<String> identificativiFlusso = new ArrayList<String> ();
    //        identificativiFlusso.add ( in.getIdFlussoOrigine () );
    //
    //        CblDStatoFlusso statoFlusso = statoFlussoRepository.findByCodiceStato ( StatoFlussoEnum.DA_SPACCHETTARE.getCodice () );
    //        List<CblDStatoFlusso> statiFlusso = new ArrayList<CblDStatoFlusso> ();
    //        statiFlusso.add ( statoFlusso );
    //
    //        List<CblTFlussoOrigine> flussoOrigine
    //            = flussoOrigineRepository.findByIdentificativoIstitutoRiceventeAndIdentificativoFlussoInAndCblDStatoFlussoInOrderByIdAsc (
    //                in.getCodiceFiscaleEnte (),
    //                identificativiFlusso,
    //                statiFlusso );
    //
    //        //List<CblTFlussoOrigine> flussoOrigine = flussoOrigineRepository.findByIdentificativoFlussoAndIdentificativoIstitutoRiceventeOrderByIdAsc ( in.getIdFlussoOrigine (), in.getCodiceFiscaleEnte () );
    //
    //        //        flussoOrigineRepository.findByIdentificativoIstitutoRiceventeAndIdentificativoFlussoInAndCblDStatoFlussoInOrderByIdAsc ( in.getIdFlussoOrigine (),
    //        //            in.get, cblDStatiFlusso );
    //        try {
    //            spacchettaSingoloFlussoConTestBusiness ( ente, flussoOrigine.get ( 0 ) );
    //        } catch ( Exception e ) {
    //            flussiKo.add ( in.getIdFlussoOrigine () + " -> " + e.getLocalizedMessage () + " - FLUSSO SCARTATO" );
    //        }
    //
    //        logger.info ( "FlussoRendicontazioneManagerImpl.spacchettaFlussoRendicontazione: FINE" );
    //        return flussiKo;
    //    }
}
