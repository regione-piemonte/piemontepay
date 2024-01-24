/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.business.manager.impl;

import static it.csi.epay.epaypacatalogweb.business.manager.impl.CodiceVersamentoManagerImpl.PRINCIPALE;
import static it.csi.epay.epaypacatalogweb.business.manager.impl.CodiceVersamentoManagerImpl.SECONDARIO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import it.csi.epay.epaypacatalogweb.business.manager.EsportazioneManager;
import it.csi.epay.epaypacatalogweb.frontend.util.TipoAssociazioneMultibeneficiario;
import it.csi.epay.epaypacatalogweb.integration.facade.impl.EpaypacatalogsrvFacade;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AutorizzaEsportazioneDatiInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AutorizzaEsportazioneDatiOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.EpaypacatalogsrvException_Exception;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.Exception_Exception;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.UnrecoverableException_Exception;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.CodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.RisultatoRicercaCodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.ppay.VoceEntrataPPayVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.RisultatoRicercaRiferimentoContabileVO;
import it.csi.epay.epaypacatalogweb.model.tassonomia.TassonomiaVO;
import it.csi.epay.epaypacatalogweb.model.utente.RisultatoRicercaUtenteVO;
import it.csi.epay.epaypacatalogweb.util.CSVUtils;


@Service
public class EsportazioneManagerImpl implements EsportazioneManager {

    @Autowired
    private EpaypacatalogsrvFacade facade;

    private Logger logger = LoggerFactory.getLogger ( EsportazioneManager.class );

    public EsportazioneManagerImpl () {

    }

    private static final String CONTENT_DISPOSITION_KEY = "Content-Disposition";

    private static final String CONTENT_DISPOSITION_VALUE = "attachment; filename=";

    private static final String CONTENT_TRANSFER_ENCODING_KEY = "Content-Transfer-Encoding";

    private static final String CONTENT_TRANSFER_ENCODING_VALUE = "binary";

    private static final String CONTENT_ENCODING_KEY = "Content-Encoding";

    private static final String CONTENT_ENCODING_VALUE = "none";

    private static final String PRAGMA_KEY = "Pragma";

    private static final String PRAGMA_VALUE = "public";

    private static final String CACHE_CONTROL_KEY = "Cache-Control";

    private static final String CACHE_CONTROL_VALUE = "max-age=0";

    private static final String FORCE_DOWNLOAD = "application/force-download";

    private static final String TEMPLATE_BASE_PACKAGE = "";

    private static final String TEMPLATE_ESPORTAZIONE_VOCI_ENTRATA_FILENAME = "template_voci_entrata.xlsx";

    private static final String TEMPLATE_ESPORTAZIONE_CODICI_VERSAMENTO_FILENAME = "template_codici_versamento.xlsx";

    private static final String TEMPLATE_ESPORTAZIONE_CODICI_VERSAMENTO_COLLEGATI_FILENAME = "template_codici_versamento.xlsx";

    private static final String TEMPLATE_ESPORTAZIONE_RIFERIMENTI_CONTABILI_FILENAME = "template_riferimenti_contabili.xlsx";

    private static final String TEMPLATE_ESPORTAZIONE_UTENTI_FILENAME = "template_utenti.xlsx";

    private static final String TEMPLATE_ESPORTAZIONE_TASSONOMIA_FILENAME = "template_tassonomia_pagopa.xlsx";

    private final SimpleDateFormat sdf = new SimpleDateFormat ( "dd/MM/yyyy" );

    private static final String SI = "SI";

    private static final String NO = "NO";

    @Override
    public void esportaVociSpesaXlsx ( HttpServletResponse response, List<VoceEntrataPPayVO> vociEntrata ) throws IOException {

        logger.info ( "exporting XLSX for " + vociEntrata.size () + " entries" );

        List<String> idList = new ArrayList<> ();
        for ( VoceEntrataPPayVO o: vociEntrata ) {
            idList.add ( o.getId ().toString () );
        }
        autorizzaEsportazioneDati ( "VoceEntrata", idList );

        Resource resource = new ClassPathResource ( TEMPLATE_BASE_PACKAGE + TEMPLATE_ESPORTAZIONE_VOCI_ENTRATA_FILENAME );
        InputStream templateInputStream = resource.getInputStream ();

        XSSFWorkbook workbook = new XSSFWorkbook ( templateInputStream );
        XSSFSheet sheet = workbook.getSheetAt ( 0 );

        for ( int i = 0; i < vociEntrata.size (); i++ ) {
            VoceEntrataPPayVO voce = vociEntrata.get ( i );

            XSSFRow row = sheet.createRow ( i + 1 );

            setCellValue ( sheet, row.createCell ( 0 ), voce.getCodice () );
            setCellValue ( sheet, row.createCell ( 1 ), voce.getDescrizione () );
            setCellValue ( sheet, row.createCell ( 2 ), voce.getCodiceTematica () );
            setCellValue ( sheet, row.createCell ( 3 ), voce.getDescrizioneTematica () );
            setCellValue ( sheet, row.createCell ( 4 ), voce.getCodiceMacrotipo () );
            setCellValue ( sheet, row.createCell ( 5 ), voce.getDescrizioneMacrotipo () );
        }

        for ( int i = 0; i < 6; i++ ) {
            sheet.autoSizeColumn ( i );
        }

        // prima riga bloccata
        sheet.createFreezePane ( 0, 1 );

        // invio header
        String fileName = "voci_entrata_ppay.xlsx";
        preparaHeaderDownload ( response, fileName );

        // scrivo contenuto
        workbook.write ( response.getOutputStream () );
        workbook.close ();
    }

    @Override
    public void esportaVociSpesaCsv ( HttpServletResponse response, List<VoceEntrataPPayVO> vociEntrata ) throws IOException {

        logger.info ( "exporting CSV for " + vociEntrata.size () + " entries" );

        List<String> idList = new ArrayList<> ();
        for ( VoceEntrataPPayVO o: vociEntrata ) {
            idList.add ( o.getId ().toString () );
        }
        autorizzaEsportazioneDati ( "VoceEntrata", idList );

        String fileName = "voci_entrata_ppay.csv";
        preparaHeaderDownload ( response, fileName );

        OutputStreamWriter writer = new OutputStreamWriter ( response.getOutputStream () );

        //for header
        CSVUtils.writeLine ( writer, Arrays.asList (
            "Codice", "Descrizione", "Codice Tematica", "Descrizione Tematica",
            "Codice Macrotipo", "Descrizione Macrotipo" ), ';' );

        for ( VoceEntrataPPayVO d: vociEntrata ) {

            List<String> list = new ArrayList<> ();

            list.add ( d.getCodice () );
            list.add ( d.getDescrizione () );
            list.add ( d.getCodiceTematica () );
            list.add ( d.getDescrizioneTematica () );
            list.add ( d.getCodiceMacrotipo () );
            list.add ( d.getDescrizioneMacrotipo () );

            CSVUtils.writeLine ( writer, list, ';' );
        }

        writer.flush ();
        writer.close ();
    }

    @Override
    public void esportaCodiciVersamentoXlsx ( HttpServletResponse response, List<RisultatoRicercaCodiceVersamentoVO> list ) throws IOException {

        logger.info ( "exporting XLSX for " + list.size () + " entries" );

        List<String> idList = new ArrayList<> ();
        for ( RisultatoRicercaCodiceVersamentoVO o: list ) {
            if ( o.getId () != null ) {
                idList.add ( o.getId ().toString () );
            }
        }
        autorizzaEsportazioneDati ( "CodiceVersamento", idList );

        Resource resource = new ClassPathResource ( TEMPLATE_BASE_PACKAGE + TEMPLATE_ESPORTAZIONE_CODICI_VERSAMENTO_FILENAME );
        InputStream templateInputStream = resource.getInputStream ();

        XSSFWorkbook workbook = new XSSFWorkbook ( templateInputStream );
        XSSFSheet sheet = workbook.getSheetAt ( 0 );
        int indiceRiga = 0;

        for ( int i = 0; i < list.size (); i++ ) {
            RisultatoRicercaCodiceVersamentoVO voce = list.get ( i );
            if ( voce.getId () == null ) {
                continue;
            }

            XSSFRow row = sheet.createRow ( indiceRiga + 2 );
            indiceRiga++;

            populateRow ( sheet, row,
                voce.getCodice (),
                voce.getDescrizione (),
                voce.getCodiceVoceEntrata (),
                voce.getDescrizioneVoceEntrata (),
                voce.getDescrizioneTipoPagamento (),
                voce.getIban (),
                falseIfNull ( voce.getFlagCodiceCorrentePostaleTesoreria () ),
                voce.getBic (),
                voce.getIbanAppoggio (),
                falseIfNull ( voce.getFlagCodiceCorrentePostaleAppoggio () ),
                voce.getBicAppoggio (),
                falseIfNull ( voce.getFlagPresenzaBollettinoPostale () ),
                falseIfNull ( voce.getFattura () ),
                falseIfNull ( voce.getFlagInvioFlussi () ),
                voce.getEmail (),
                voce.getCodiceTematica (),
                voce.getDescrizioneTematica (),
                voce.getCodiceMacrotipo (),
                voce.getDescrizioneMacrotipo (),
                convertMultibeneficiario ( voce.getMultibeneficiario (), true ),
                convertMultibeneficiario ( voce.getMultibeneficiario (), false ) );

            if ( voce.getCodiciVersamentoCollegati () != null && voce.getCodiciVersamentoCollegati ().size () > 0 ) {
                for ( RisultatoRicercaCodiceVersamentoVO voceCollegata: voce.getCodiciVersamentoCollegati () ) {
                    if ( voceCollegata.getId () == null ) {
                        continue;
                    }

                    XSSFRow rowCollegata = sheet.createRow ( indiceRiga + 2 );
                    indiceRiga++;

                    populateRow ( sheet, rowCollegata,
                        voceCollegata.getCodice (),
                        voceCollegata.getDescrizione (),
                        voceCollegata.getCodiceVoceEntrata (),
                        voceCollegata.getDescrizioneVoceEntrata (),
                        voceCollegata.getDescrizioneTipoPagamento (),
                        voceCollegata.getIban (),
                        falseIfNull ( voceCollegata.getFlagCodiceCorrentePostaleTesoreria () ),
                        voceCollegata.getBic (),
                        voceCollegata.getIbanAppoggio (),
                        falseIfNull ( voceCollegata.getFlagCodiceCorrentePostaleAppoggio () ),
                        voceCollegata.getBicAppoggio (),
                        falseIfNull ( voceCollegata.getFlagPresenzaBollettinoPostale () ),
                        falseIfNull ( voceCollegata.getFattura () ),
                        falseIfNull ( voceCollegata.getFlagInvioFlussi () ),
                        voceCollegata.getEmail (),
                        voceCollegata.getCodiceTematica (),
                        voceCollegata.getDescrizioneTematica (),
                        voceCollegata.getCodiceMacrotipo (),
                        voceCollegata.getDescrizioneMacrotipo (),
                        convertMultibeneficiario ( voceCollegata.getMultibeneficiario (), true ),
                        convertMultibeneficiario ( voceCollegata.getMultibeneficiario (), false ) );
                }
            }
        }

        sheet.autoSizeColumn ( 1 );
        sheet.autoSizeColumn ( 3 );
        sheet.autoSizeColumn ( 4 );
        sheet.autoSizeColumn ( 5 );
        sheet.autoSizeColumn ( 8 );
        sheet.autoSizeColumn ( 14 );
        sheet.autoSizeColumn ( 16 );
        sheet.autoSizeColumn ( 18 );

        // prima riga bloccata
        sheet.createFreezePane ( 0, 2 );

        // invio header
        String fileName = "codici_versamento.xlsx";
        preparaHeaderDownload ( response, fileName );

        // scrivo contenuto
        workbook.write ( response.getOutputStream () );
        workbook.close ();
    }

    @Override
    public void esportaCodiciVersamentoCsv ( HttpServletResponse response, List<RisultatoRicercaCodiceVersamentoVO> list ) throws IOException {

        logger.info ( "exporting CSV for " + list.size () + " entries" );

        List<String> idList = new ArrayList<> ();
        for ( RisultatoRicercaCodiceVersamentoVO o: list ) {
            if ( o.getId () != null ) {
                idList.add ( o.getId ().toString () );
            }
        }
        autorizzaEsportazioneDati ( "CodiceVersamento", idList );

        String fileName = "codici_versamento.csv";
        preparaHeaderDownload ( response, fileName );

        OutputStreamWriter writer = new OutputStreamWriter ( response.getOutputStream () );

        //RD10 aggiunto iban e bic di appoggio
        //for header
        CSVUtils.writeLine ( writer, Arrays.asList (
            "Codice", "Descrizione", "Codice Voce Entrata", "Descrizione Voce entrata",
            "Tipo Pagamento", "IBAN", "C/C postale", "BIC", "IBAN di Appoggio", "C/C postale",
            "BIC di Appoggio", "Flag Presenza Bollettino Postale", "Fattura", "Flag invio flussi", "Email",
            "Codice Tematica", "Descrizione Tematica", "Codice Macrotipo", "Descrizione Macrotipo", "Multibeneficiario Primario",
            "Multibeneficiario Secondario" ), ';' );

        for ( RisultatoRicercaCodiceVersamentoVO voce: list ) {
            if ( voce.getId () == null ) {
                continue;
            }

            List<String> line = new ArrayList<> ();

            line.add ( toCsvString ( voce.getCodice () ) );
            line.add ( toCsvString ( voce.getDescrizione () ) );
            line.add ( toCsvString ( voce.getCodiceVoceEntrata () ) );
            line.add ( toCsvString ( voce.getDescrizioneVoceEntrata () ) );
            line.add ( toCsvString ( voce.getDescrizioneTipoPagamento () ) );
            line.add ( toCsvString ( voce.getIban () ) );
            line.add ( voce.getFlagCodiceCorrentePostaleTesoreria () != null && voce.getFlagCodiceCorrentePostaleTesoreria () ? "SI" : "NO" );
            line.add ( toCsvString ( voce.getBic () ) );
            line.add ( toCsvString ( voce.getIbanAppoggio () ) );
            line.add ( voce.getFlagCodiceCorrentePostaleAppoggio () != null && voce.getFlagCodiceCorrentePostaleAppoggio () ? "SI" : "NO" );
            line.add ( toCsvString ( voce.getBicAppoggio () ) );
            line.add ( voce.getFlagPresenzaBollettinoPostale () ? "SI" : "NO" );
            line.add ( voce.getFattura () != null && voce.getFattura () ? "SI" : "NO" );
            line.add ( voce.getFlagInvioFlussi () ? "SI" : "NO" );
            line.add ( toCsvString ( voce.getEmail () ) );
            line.add ( toCsvString ( voce.getCodiceTematica () ) );
            line.add ( toCsvString ( voce.getDescrizioneTematica () ) );
            line.add ( toCsvString ( voce.getCodiceMacrotipo () ) );
            line.add ( toCsvString ( voce.getDescrizioneMacrotipo () ) );
            line.add ( toCsvString ( convertMultibeneficiario ( voce.getMultibeneficiario (), true ) ) );
            line.add ( toCsvString ( convertMultibeneficiario ( voce.getMultibeneficiario (), false ) ) );

            CSVUtils.writeLine ( writer, line, ';' );

            if ( voce.getCodiciVersamentoCollegati () != null && voce.getCodiciVersamentoCollegati ().size () > 0 ) {
                for ( RisultatoRicercaCodiceVersamentoVO voceCollegata: voce.getCodiciVersamentoCollegati () ) {
                    if ( voceCollegata.getId () == null ) {
                        continue;
                    }

                    List<String> lineCollegata = new ArrayList<> ();

                    lineCollegata.add ( toCsvString ( voceCollegata.getCodice () ) );
                    lineCollegata.add ( toCsvString ( voceCollegata.getDescrizione () ) );
                    lineCollegata.add ( toCsvString ( voceCollegata.getCodiceVoceEntrata () ) );
                    lineCollegata.add ( toCsvString ( voceCollegata.getDescrizioneVoceEntrata () ) );
                    lineCollegata.add ( toCsvString ( voceCollegata.getDescrizioneTipoPagamento () ) );
                    lineCollegata.add ( toCsvString ( voceCollegata.getIban () ) );
                    lineCollegata.add ( voceCollegata.getFlagCodiceCorrentePostaleTesoreria () != null && voceCollegata.getFlagCodiceCorrentePostaleTesoreria ()
                                    ? "SI" : "NO" );
                    lineCollegata.add ( toCsvString ( voceCollegata.getBic () ) );
                    lineCollegata.add ( toCsvString ( voceCollegata.getIbanAppoggio () ) );
                    lineCollegata.add (
                        voceCollegata.getFlagCodiceCorrentePostaleAppoggio () != null && voceCollegata.getFlagCodiceCorrentePostaleAppoggio () ? "SI" : "NO" );
                    lineCollegata.add ( toCsvString ( voceCollegata.getBicAppoggio () ) );
                    lineCollegata.add ( voceCollegata.getFlagPresenzaBollettinoPostale () ? "SI" : "NO" );
                    lineCollegata.add ( voceCollegata.getFattura () != null && voceCollegata.getFattura () ? "SI" : "NO" );
                    lineCollegata.add ( voceCollegata.getFlagInvioFlussi () ? "SI" : "NO" );
                    lineCollegata.add ( toCsvString ( voceCollegata.getEmail () ) );
                    lineCollegata.add ( toCsvString ( voceCollegata.getCodiceTematica () ) );
                    lineCollegata.add ( toCsvString ( voceCollegata.getDescrizioneTematica () ) );
                    lineCollegata.add ( toCsvString ( voceCollegata.getCodiceMacrotipo () ) );
                    lineCollegata.add ( toCsvString ( voceCollegata.getDescrizioneMacrotipo () ) );
                    lineCollegata.add ( toCsvString ( convertMultibeneficiario ( voceCollegata.getMultibeneficiario (), true ) ) );
                    lineCollegata.add ( toCsvString ( convertMultibeneficiario ( voceCollegata.getMultibeneficiario (), false ) ) );

                    CSVUtils.writeLine ( writer, lineCollegata, ';' );
                }
            }
        }

        writer.flush ();
        writer.close ();
    }

    private String convertMultibeneficiario ( String multibeneficiario, boolean main ) {
        if ( null == multibeneficiario ) {
            return NO;
        }
        if ( main ) {
            if ( multibeneficiario.equalsIgnoreCase ( PRINCIPALE ) ) {
                return SI;
            } else {
                return NO;
            }
        } else {
            if ( multibeneficiario.contentEquals ( SECONDARIO ) ) {
                return SI;
            } else {
                return NO;
            }
        }
    }

    private String convertMultibeneficiario ( Boolean flagMb ) {
        if ( null == flagMb ) {
            return NO;
        }
        return flagMb ? SI : NO;
    }

    @Override
    public void esportaCodiciVersamentoCollegatiXlsx ( HttpServletResponse response, CodiceVersamentoVO codicePadre, List<CodiceVersamentoVO> list )
                    throws IOException {

        logger.info ( "exporting XLSX for " + list.size () + " entries" );

        List<String> idList = new ArrayList<> ();
        for ( CodiceVersamentoVO o: list ) {
            if ( o.getId () != null ) {
                idList.add ( o.getId ().toString () );
            }
        }
        autorizzaEsportazioneDati ( "CodiceVersamento", idList );

        Resource resource = new ClassPathResource ( TEMPLATE_BASE_PACKAGE + TEMPLATE_ESPORTAZIONE_CODICI_VERSAMENTO_COLLEGATI_FILENAME );
        InputStream templateInputStream = resource.getInputStream ();

        XSSFWorkbook workbook = new XSSFWorkbook ( templateInputStream );
        XSSFSheet sheet = workbook.getSheetAt ( 0 );
        int indiceRiga = 0;

        XSSFRow row = sheet.createRow ( indiceRiga + 2 );
        indiceRiga++;

        populateRow ( sheet, row,
            codicePadre.getCodice (),
            codicePadre.getDescrizione (),
            codicePadre.getCodiceVoceEntrata (),
            codicePadre.getDescrizioneVoceEntrata (),
            codicePadre.getDescrizioneTipoPagamento (),
            codicePadre.getIban (),
            falseIfNull ( codicePadre.getFlagCodiceCorrentePostaleTesoreria () ),
            codicePadre.getBic (),
            codicePadre.getIbanAppoggio (),
            falseIfNull ( codicePadre.getFlagCodiceCorrentePostaleAppoggio () ),
            codicePadre.getBicAppoggio (),
            falseIfNull ( codicePadre.getFlagPresenzaBollettinoPostale () ),
            falseIfNull ( codicePadre.getFattura () ),
            falseIfNull ( codicePadre.getFlagInvioFlussi () ),
            codicePadre.getEmail (),
            codicePadre.getCodiceTematica (),
            codicePadre.getDescrizioneTematica (),
            codicePadre.getCodiceMacrotipo (),
            codicePadre.getDescrizioneMacrotipo (),
            TipoAssociazioneMultibeneficiario.PRIMARIO.getId ().equals (
                codicePadre.getModalitaAssociazioneMultibeneficiario () )?"SI":"NO",
                                TipoAssociazioneMultibeneficiario.SECONDARIO.getId ().equals (
                                    codicePadre.getModalitaAssociazioneMultibeneficiario () )?"SI":"NO"
                    );
        

        for ( int i = 0; i < list.size (); i++ ) {
            CodiceVersamentoVO voce = list.get ( i );

            if ( voce.getId () == null ) {
                continue;
            }

            row = sheet.createRow ( indiceRiga + 2 );
            indiceRiga++;

            populateRow ( sheet, row,
                voce.getCodice (),
                voce.getDescrizione (),
                voce.getCodiceVersamentoPadre ().getCodiceVoceEntrata (),
                voce.getCodiceVersamentoPadre ().getDescrizioneVoceEntrata (),
                voce.getCodiceVersamentoPadre ().getDescrizioneTipoPagamento (),
                voce.getIban (),
                falseIfNull ( voce.getFlagCodiceCorrentePostaleTesoreria () ),
                voce.getBic (),
                voce.getIbanAppoggio (),
                falseIfNull ( voce.getFlagCodiceCorrentePostaleAppoggio () ),
                voce.getBicAppoggio (),
                falseIfNull ( voce.getFlagPresenzaBollettinoPostale () ),
                falseIfNull ( voce.getFattura () ),
                falseIfNull ( voce.getFlagInvioFlussi () ),
                voce.getEmail (),
                voce.getCodiceVersamentoPadre ().getCodiceTematica (),
                voce.getCodiceVersamentoPadre ().getDescrizioneTematica (),
                voce.getCodiceVersamentoPadre ().getCodiceMacrotipo (),
                voce.getCodiceVersamentoPadre ().getDescrizioneMacrotipo () ,
                TipoAssociazioneMultibeneficiario.PRIMARIO.getId ().equals (
                    voce.getModalitaAssociazioneMultibeneficiario () )?"SI":"NO",
                                    TipoAssociazioneMultibeneficiario.SECONDARIO.getId ().equals (
                                        voce.getModalitaAssociazioneMultibeneficiario () )?"SI":"NO");
        }

        sheet.autoSizeColumn ( 1 );
        sheet.autoSizeColumn ( 3 );
        sheet.autoSizeColumn ( 4 );
        sheet.autoSizeColumn ( 5 );
        sheet.autoSizeColumn ( 8 );
        sheet.autoSizeColumn ( 14 );
        sheet.autoSizeColumn ( 16 );
        sheet.autoSizeColumn ( 18 );

        // prima riga bloccata
        sheet.createFreezePane ( 0, 2 );

        // invio header
        String fileName = "codici_versamento_collegati.xlsx";
        preparaHeaderDownload ( response, fileName );

        // scrivo contenuto
        workbook.write ( response.getOutputStream () );
        workbook.close ();
    }

    @Override
    public void esportaCodiciVersamentoCollegatiCsv ( HttpServletResponse response, CodiceVersamentoVO codicePadre, List<CodiceVersamentoVO> list )
                    throws IOException {

        logger.info ( "exporting CSV for " + list.size () + " entries" );

        List<String> idList = new ArrayList<> ();
        for ( CodiceVersamentoVO o: list ) {
            if ( o.getId () != null ) {
                idList.add ( o.getId ().toString () );
            }
        }
        autorizzaEsportazioneDati ( "CodiceVersamento", idList );

        String fileName = "codici_versamento_collegati.csv";
        preparaHeaderDownload ( response, fileName );

        OutputStreamWriter writer = new OutputStreamWriter ( response.getOutputStream () );

        //for header
        CSVUtils.writeLine ( writer, Arrays.asList (
            "Codice", "Descrizione", "Codice Voce Entrata", "Descrizione Voce entrata",
            "Tipo Pagamento", "IBAN", "C/C postale", "BIC", "IBAN di Appoggio", "C/C postale",
            "BIC di Appoggio", "Flag Presenza Bollettino Postale", "Fattura", "Flag invio flussi", "Email",
            "Codice Tematica", "Descrizione Tematica", "Codice Macrotipo", "Descrizione Macrotipo" ), ';' );

        List<String> line = new ArrayList<> ();

        line.add ( toCsvString ( codicePadre.getCodice () ) );
        line.add ( toCsvString ( codicePadre.getDescrizione () ) );
        line.add ( toCsvString ( codicePadre.getCodiceVoceEntrata () ) );
        line.add ( toCsvString ( codicePadre.getDescrizioneVoceEntrata () ) );
        line.add ( toCsvString ( codicePadre.getDescrizioneTipoPagamento () ) );
        line.add ( toCsvString ( codicePadre.getIban () ) );
        line.add ( codicePadre.getFlagCodiceCorrentePostaleTesoreria () != null && codicePadre.getFlagCodiceCorrentePostaleTesoreria () ? "SI" : "No" );
        line.add ( toCsvString ( codicePadre.getBic () ) );
        line.add ( toCsvString ( codicePadre.getIbanAppoggio () ) );
        line.add ( codicePadre.getFlagCodiceCorrentePostaleAppoggio () != null && codicePadre.getFlagCodiceCorrentePostaleAppoggio () ? "SI" : "No" );
        line.add ( toCsvString ( codicePadre.getBicAppoggio () ) );
        line.add ( codicePadre.getFlagPresenzaBollettinoPostale () ? "SI" : "NO" );
        line.add ( codicePadre.getFattura () != null && codicePadre.getFattura () ? "SI" : "NO" );
        line.add ( codicePadre.getFlagInvioFlussi () ? "SI" : "NO" );
        line.add ( toCsvString ( codicePadre.getEmail () ) );
        line.add ( toCsvString ( codicePadre.getCodiceTematica () ) );
        line.add ( toCsvString ( codicePadre.getDescrizioneTematica () ) );
        line.add ( toCsvString ( codicePadre.getCodiceMacrotipo () ) );
        line.add ( toCsvString ( codicePadre.getDescrizioneMacrotipo () ) );
        line.add ( toCsvString ( TipoAssociazioneMultibeneficiario.PRIMARIO.getId ().equals (
            codicePadre.getModalitaAssociazioneMultibeneficiario () )?"SI":"NO" ) );
        line.add ( toCsvString ( TipoAssociazioneMultibeneficiario.SECONDARIO.getId ().equals (
            codicePadre.getModalitaAssociazioneMultibeneficiario () )?"SI":"NO" ) );
        

        CSVUtils.writeLine ( writer, line, ';' );

        for ( CodiceVersamentoVO voce: list ) {

            if ( voce.getId () == null ) {
                continue;
            }
            line = new ArrayList<> ();

            line.add ( toCsvString ( voce.getCodice () ) );
            line.add ( toCsvString ( voce.getDescrizione () ) );
            line.add ( toCsvString ( voce.getCodiceVersamentoPadre ().getCodiceVoceEntrata () ) );
            line.add ( toCsvString ( voce.getCodiceVersamentoPadre ().getDescrizioneVoceEntrata () ) );
            line.add ( toCsvString ( voce.getCodiceVersamentoPadre ().getDescrizioneTipoPagamento () ) );
            line.add ( toCsvString ( voce.getIban () ) );
            line.add ( voce.getFlagCodiceCorrentePostaleTesoreria () != null && voce.getFlagCodiceCorrentePostaleTesoreria () ? "SI" : "No" );
            line.add ( toCsvString ( voce.getBic () ) );
            line.add ( toCsvString ( voce.getIbanAppoggio () ) );
            line.add ( voce.getFlagCodiceCorrentePostaleAppoggio () != null && voce.getFlagCodiceCorrentePostaleAppoggio () ? "SI" : "No" );
            line.add ( toCsvString ( voce.getBicAppoggio () ) );
            line.add ( voce.getFlagPresenzaBollettinoPostale () ? "SI" : "NO" );
            line.add ( voce.getFattura () != null && voce.getFattura () ? "SI" : "NO" );
            line.add ( voce.getFlagInvioFlussi () ? "SI" : "NO" );
            line.add ( toCsvString ( voce.getEmail () ) );
            line.add ( toCsvString ( voce.getCodiceVersamentoPadre ().getCodiceTematica () ) );
            line.add ( toCsvString ( voce.getCodiceVersamentoPadre ().getDescrizioneTematica () ) );
            line.add ( toCsvString ( voce.getCodiceVersamentoPadre ().getCodiceMacrotipo () ) );
            line.add ( toCsvString ( voce.getCodiceVersamentoPadre ().getDescrizioneMacrotipo () ) );
            line.add ( toCsvString ( TipoAssociazioneMultibeneficiario.PRIMARIO.getId ().equals (
                voce.getModalitaAssociazioneMultibeneficiario () )?"SI":"NO" ) );
            line.add ( toCsvString ( TipoAssociazioneMultibeneficiario.SECONDARIO.getId ().equals (
                voce.getModalitaAssociazioneMultibeneficiario () )?"SI":"NO" ) );

            CSVUtils.writeLine ( writer, line, ';' );
        }

        writer.flush ();
        writer.close ();
    }

    @Override
    public void esportaRiferimentiContabiliXlsx ( HttpServletResponse response,
        List<RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO> listGrouped )
                    throws IOException {

        List<RisultatoRicercaRiferimentoContabileVO> list = new ArrayList<> ();
        if ( listGrouped != null ) {
            for ( RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO listGroup: listGrouped ) {
                if ( listGroup.getRiferimentiContabili () != null ) {
                    list.addAll ( listGroup.getRiferimentiContabili () );
                }
                if ( listGroup.getCodiciVersamentoCollegati () != null ) {
                    for ( RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO listGroupColl: listGroup.getCodiciVersamentoCollegati () ) {
                        if ( listGroupColl.getRiferimentiContabili () != null ) {
                            list.addAll ( listGroupColl.getRiferimentiContabili () );
                        }
                    }
                }
            }
        }

        logger.info ( "exporting XLSX for " + list.size () + " entries" );

        Resource resource = new ClassPathResource ( TEMPLATE_BASE_PACKAGE + TEMPLATE_ESPORTAZIONE_RIFERIMENTI_CONTABILI_FILENAME );
        InputStream templateInputStream = resource.getInputStream ();

        List<String> idList = new ArrayList<> ();
        for ( RisultatoRicercaRiferimentoContabileVO o: list ) {
            idList.add ( o.getId ().toString () );
        }
        autorizzaEsportazioneDati ( "RiferimentoContabile", idList );

        XSSFWorkbook workbook = new XSSFWorkbook ( templateInputStream );
        XSSFSheet sheet = workbook.getSheetAt ( 0 );

        for ( int i = 0; i < list.size (); i++ ) {
            RisultatoRicercaRiferimentoContabileVO voce = list.get ( i );

            XSSFRow row = sheet.createRow ( i + 2 );

            populateRow ( sheet, row,
                voce.getId (),
                voce.getNomeEnte (),
                voce.getTipoEnte (),
                voce.getCodiceCodiceVersamento (),
                voce.getDescrizioneCodiceVersamento (),
                voce.getTipologiaCodiceVersamento (),
                voce.getMailCodiceVersamento (),
                voce.getNoteCodiceVersamento (),
                voce.isFatturaCodiceVersamento () ? "S" : "N",
                voce.getIbanCodiceVersamento (),
                voce.getIbanAppoggioCodiceVersamento (),
                voce.getModalitaIntegrazioneCodiceVersamento (),
                voce.isStatoCodiceVersamento () ? "Attivo" : "Non attivo",
                voce.getCodiceVoceEntrata (),
                voce.getDescrizioneVoceEntrata (),
                voce.getCodiceTematica (),
                voce.getDescrizioneTematica (),
                voce.getCodiceMacrotipo (),
                voce.getDescrizioneMacrotipo (),
                voce.getDataInizioValidita (),
                voce.getDataFineValidita (),
                //                voce.getDescrizioneTipologiaDatoSpecificoRiscossione (),
                //                voce.getCodiceTipologiaDatoSpecificoRiscossione (),
                voce.getCodiceTipologiaDatoSpecificoRiscossione () + "/"
                    + voce.getDatoSpecificoRiscossione (),
                //                voce.getDescrizioneDatoSpecificoRiscossione (),
                voce.getTipoServizio (), //Tipo servizio
                voce.getDatoSpecificoRiscossioneTassonomia (), //Dato specifico riscossione tassonomia
                voce.getDataInizioValiditaCodiceTassonomico (), //data inizio validita tassonomia
                voce.getDataFineValiditaCodiceTassonomico (), //data fine validita tassonomia
                voce.getAnnoEsercizio (),
                "" /* TODO rimuovere da template */,
                voce.getAnnoAccertamento (),
                voce.getNumeroAccertamento (),
                voce.getNumeroCapitolo (),
                voce.getNumeroArticolo (),
                voce.getLivelloPdc (),
                voce.getTitolo (),
                voce.getTipologia (),
                voce.getCategoria (),
                convertMultibeneficiario ( voce.getFlagMbPrimario () ),
                convertMultibeneficiario ( voce.getFlagMbSecondario () ) );
        }

        for ( int i = 0; i < 13; i++ ) {
            sheet.autoSizeColumn ( i );
        }

        // prima riga bloccata
        sheet.createFreezePane ( 0, 2 );

        // invio header
        String fileName = "riferimenti_contabili.xlsx";
        preparaHeaderDownload ( response, fileName );

        // scrivo contenuto
        workbook.write ( response.getOutputStream () );
        workbook.close ();
    }

    @Override
    public void esportaRiferimentiContabiliCsv ( HttpServletResponse response,
        List<RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO> listGrouped )
                    throws IOException {

        List<RisultatoRicercaRiferimentoContabileVO> list = new ArrayList<> ();
        if ( listGrouped != null ) {
            for ( RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO listGroup: listGrouped ) {
                if ( listGroup.getRiferimentiContabili () != null ) {
                    list.addAll ( listGroup.getRiferimentiContabili () );
                }
                if ( listGroup.getCodiciVersamentoCollegati () != null ) {
                    for ( RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO listGroupColl: listGroup.getCodiciVersamentoCollegati () ) {
                        if ( listGroupColl.getRiferimentiContabili () != null ) {
                            list.addAll ( listGroupColl.getRiferimentiContabili () );
                        }
                    }
                }
            }
        }

        logger.info ( "exporting CSV for " + list.size () + " entries" );

        List<String> idList = new ArrayList<> ();
        for ( RisultatoRicercaRiferimentoContabileVO o: list ) {
            idList.add ( o.getId ().toString () );
        }
        autorizzaEsportazioneDati ( "RiferimentoContabile", idList );

        String fileName = "riferimenti_contabili.csv";
        preparaHeaderDownload ( response, fileName );

        OutputStreamWriter writer = new OutputStreamWriter ( response.getOutputStream () );

        //for header
        CSVUtils.writeLine ( writer, Arrays.asList (
            "Nome Ente",
            "Tipo Ente",

            "ID",
            "Codice Versamento",
            "Descrizione Codice Versamento",

            "Tipologia Codice Versamento",
            "Mail",
            "Note",
            "Fattura",
            "Iban",
            "Iban di appoggio",
            "Modalita Integrazione",
            "Stato codice versamento",

            "Codice Voce Entrata",
            "Descrizione Voce Entrata",
            "Codice Tematica",
            "Descrizione Tematica",
            "Codice Macrotipo",
            "Descrizione Macrotipo",
            "Data Inizio Validita",
            "Data Fine Validita",
            //            
            //            "Descrizione Tipologia Dato Specifico Riscossione",
            //            "Codice Tipologia Dato Specifico Riscossione",
            "Dato Specifico Riscossione", //modificato codice tipo +dato specifico della tabella riferimenti contabili

            //            "Descrizione Dato Specifico Riscossione",
            "Descrizione/Tipo Servizio", //modificato Campo tipo_servizio nella tabella EPAYCAT_T_TASSONOMIA.

            "Dato Specifico di riscossione tassonomia",
            "Data inizio validita codice tassonomico",
            "Data fine validita codice tassonomico",

            "Anno Esercizio",
            "Anno Accertamento",
            "Numero Accertamento",
            "Capitolo",
            "Numero Articolo",
            "V Livello Pdc",
            "Titolo",
            "Tipologia",
            "Categoria",
            "Multibeneficiario Primario",
            "Multibeneficiario Secondario" ), ';' );

        //        private String tipoServizio; //

        for ( RisultatoRicercaRiferimentoContabileVO voce: list ) {

            List<String> line = new ArrayList<> ();

            line.add ( toCsvString ( voce.getNomeEnte () ) ); //nome ente
            line.add ( toCsvString ( voce.getTipoEnte () ) ); // tipo ente

            line.add ( toCsvString ( voce.getId () ) );
            line.add ( toCsvString ( voce.getCodiceCodiceVersamento () ) );
            line.add ( toCsvString ( voce.getDescrizioneCodiceVersamento () ) );

            line.add ( toCsvString ( voce.getTipologiaCodiceVersamento () ) );
            line.add ( toCsvString ( voce.getMailCodiceVersamento () ) );
            line.add ( toCsvString ( voce.getNoteCodiceVersamento () ) );
            line.add ( toCsvString ( voce.isFatturaCodiceVersamento () ? "S" : "N" ) );
            line.add ( toCsvString ( voce.getIbanCodiceVersamento () ) );
            line.add ( toCsvString ( voce.getIbanAppoggioCodiceVersamento () ) );
            line.add ( toCsvString ( voce.getModalitaIntegrazioneCodiceVersamento () ) );
            line.add ( toCsvString ( voce.isStatoCodiceVersamento () ? "Attivo" : "Non attivo" ) );

            //            

            line.add ( toCsvString ( voce.getCodiceVoceEntrata () ) );
            line.add ( toCsvString ( voce.getDescrizioneVoceEntrata () ) );
            line.add ( toCsvString ( voce.getCodiceTematica () ) );
            line.add ( toCsvString ( voce.getDescrizioneTematica () ) );
            line.add ( toCsvString ( voce.getCodiceMacrotipo () ) );
            line.add ( toCsvString ( voce.getDescrizioneMacrotipo () ) );
            line.add ( toCsvString ( voce.getDataInizioValidita () != null ? sdf.format ( voce.getDataInizioValidita () ) : "" ) );
            line.add ( toCsvString ( voce.getDataFineValidita () != null ? sdf.format ( voce.getDataFineValidita () ) : "" ) );

            //            line.add ( toCsvString ( voce.getDescrizioneTipologiaDatoSpecificoRiscossione () ) );
            line.add ( toCsvString ( voce.getCodiceTipologiaDatoSpecificoRiscossione () + "/" +
                toCsvString ( voce.getDatoSpecificoRiscossione () ) ) );
            line.add ( toCsvString ( voce.getTipoServizio () ) );
            line.add ( toCsvString ( voce.getDatoSpecificoRiscossioneTassonomia () ) );
            line.add (
                toCsvString ( voce.getDataInizioValiditaCodiceTassonomico () != null ? sdf.format ( voce.getDataInizioValiditaCodiceTassonomico () ) : "" ) );
            line.add (
                toCsvString ( voce.getDataFineValiditaCodiceTassonomico () != null ? sdf.format ( voce.getDataFineValiditaCodiceTassonomico () ) : "" ) );

//            line.add ( toCsvString ( voce.getDescrizioneDatoSpecificoRiscossione () ) );
            line.add ( toCsvString ( voce.getAnnoEsercizio () ) );
            line.add ( toCsvString ( voce.getAnnoAccertamento () ) );
            line.add ( toCsvString ( voce.getNumeroAccertamento () ) );
            line.add ( toCsvString ( voce.getNumeroCapitolo () ) );
            line.add ( toCsvString ( voce.getNumeroArticolo () ) );
            line.add ( toCsvString ( voce.getLivelloPdc () ) );
            line.add ( toCsvString ( voce.getTitolo () ) );
            line.add ( toCsvString ( voce.getTipologia () ) );
            line.add ( toCsvString ( voce.getCategoria () ) );
            line.add ( toCsvString ( convertMultibeneficiario ( voce.getFlagMbPrimario () ) ) );
            line.add ( toCsvString ( convertMultibeneficiario ( voce.getFlagMbSecondario () ) ) );

            CSVUtils.writeLine ( writer, line, ';' );
        }

        writer.flush ();
        writer.close ();
    }

    @Override
    public void esportaUtentiXlsx ( HttpServletResponse response, List<RisultatoRicercaUtenteVO> list ) throws IOException {
        logger.info ( "exporting XLSX for " + list.size () + " entries" );

        List<String> idList = new ArrayList<> ();
        for ( RisultatoRicercaUtenteVO o: list ) {
            idList.add ( o.getId ().toString () );
        }
        autorizzaEsportazioneDati ( "epaycat_t_utente", idList );

        Resource resource = new ClassPathResource ( TEMPLATE_BASE_PACKAGE + TEMPLATE_ESPORTAZIONE_UTENTI_FILENAME );
        InputStream templateInputStream = resource.getInputStream ();

        XSSFWorkbook workbook = new XSSFWorkbook ( templateInputStream );
        XSSFSheet sheet = workbook.getSheetAt ( 0 );

        for ( int i = 0; i < list.size (); i++ ) {
            RisultatoRicercaUtenteVO voce = list.get ( i );

            XSSFRow row = sheet.createRow ( i + 1 );

            populateRow ( sheet, row,
                voce.getCodiceFiscale (),
                voce.getNome (),
                voce.getCognome (),
                voce.getEmail (),
                voce.getDataInizioValidita (),
                voce.getDataFineValidita () );
        }

        for ( int i = 0; i < 7; i++ ) {
            sheet.autoSizeColumn ( i );
        }

        // prima riga bloccata
        sheet.createFreezePane ( 0, 1 );

        // invio header
        String fileName = "utenti.xlsx";
        preparaHeaderDownload ( response, fileName );

        // scrivo contenuto
        workbook.write ( response.getOutputStream () );
        workbook.close ();
    }

    @Override
    public void esportaUtentiCsv ( HttpServletResponse response, List<RisultatoRicercaUtenteVO> list ) throws IOException {

        logger.info ( "exporting CSV for " + list.size () + " entries" );

        List<String> idList = new ArrayList<> ();
        for ( RisultatoRicercaUtenteVO o: list ) {
            idList.add ( o.getId ().toString () );
        }
        autorizzaEsportazioneDati ( "epaycat_t_utente", idList );

        String fileName = "utenti.csv";
        preparaHeaderDownload ( response, fileName );

        OutputStreamWriter writer = new OutputStreamWriter ( response.getOutputStream () );

        //for header
        CSVUtils.writeLine ( writer, Arrays.asList (
            "Codice Fiscale",
            "Nome",
            "Cognome",
            "Email",
            "Data Inizio Validita",
            "Data Fine Validita" ), ';' );

        for ( RisultatoRicercaUtenteVO voce: list ) {

            List<String> line = new ArrayList<> ();

            line.add ( toCsvString ( voce.getCodiceFiscale () ) );
            line.add ( toCsvString ( voce.getNome () ) );
            line.add ( toCsvString ( voce.getCognome () ) );
            line.add ( toCsvString ( voce.getEmail () ) );
            line.add ( toCsvString ( voce.getDataInizioValidita () != null ? sdf.format ( voce.getDataInizioValidita () ) : "" ) );
            line.add ( toCsvString ( voce.getDataFineValidita () != null ? sdf.format ( voce.getDataFineValidita () ) : "" ) );

            CSVUtils.writeLine ( writer, line, ';' );
        }

        writer.flush ();
        writer.close ();
    }

    @Override
    public void esportaTassonomiaXlsx ( HttpServletResponse response, List<TassonomiaVO> list ) throws IOException {
        logger.info ( "exporting XLSX for " + list.size () + " entries" );

        List<String> idList = new ArrayList<> ();
        for ( TassonomiaVO o: list ) {
            if ( o.getId () != null ) {
                idList.add ( o.getId ().toString () );
            }
        }

        autorizzaEsportazioneDati ( "epaycat_t_tassonomia", idList );

        Resource resource = new ClassPathResource ( TEMPLATE_BASE_PACKAGE + TEMPLATE_ESPORTAZIONE_TASSONOMIA_FILENAME );
        InputStream templateInputStream = resource.getInputStream ();

        XSSFWorkbook workbook = new XSSFWorkbook ( templateInputStream );
        XSSFSheet sheet = workbook.getSheetAt ( 0 );

        for ( int i = 0; i < list.size (); i++ ) {
            TassonomiaVO voce = list.get ( i );

            XSSFRow row = sheet.createRow ( i + 1 );

            setCellValue ( sheet, row.createCell ( 0 ), voce.getCodTipoEnteCreditore () );
            setCellValue ( sheet, row.createCell ( 1 ), voce.getTipoEnteCreditore () );

            setCellValue ( sheet, row.createCell ( 2 ), voce.getMacroArea () );
            setCellValue ( sheet, row.createCell ( 3 ), voce.getNomeMacroArea () );
            setCellValue ( sheet, row.createCell ( 4 ), voce.getDescrMacroArea () );
            setCellValue ( sheet, row.createCell ( 5 ), voce.getCodTipologiaServizio () );
            setCellValue ( sheet, row.createCell ( 6 ), voce.getTipoServizio () );
            setCellValue ( sheet, row.createCell ( 7 ), voce.getDesMotivoGiuridicoRiscossione () );
            setCellValue ( sheet, row.createCell ( 8 ), voce.getDescrTipoServizio () );
            setCellValue ( sheet, row.createCell ( 9 ), voce.getnVersioneTassonomia () );
            setCellValue ( sheet, row.createCell ( 10 ), voce.getDatiSpecificiIncasso () );
            setCellValue ( sheet, row.createCell ( 11 ), voce.getDataInizioValidita () );
            setCellValue ( sheet, row.createCell ( 12 ), voce.getDataFineValidita () );
        }

        for ( int i = 0; i < 6; i++ ) {
            sheet.autoSizeColumn ( i );
        }

        // prima riga bloccata
        sheet.createFreezePane ( 0, 1 );

        // invio header
        String fileName = "tassonomia_pagopa.xlsx";
        preparaHeaderDownload ( response, fileName );

        // scrivo contenuto
        workbook.write ( response.getOutputStream () );
        workbook.close ();

    }

    @Override
    public void esportaTassonomiaCsv ( HttpServletResponse response, List<TassonomiaVO> list ) throws IOException {

        logger.info ( "exporting CSV for " + list.size () + " entries" );

        List<String> idList = new ArrayList<> ();
        for ( TassonomiaVO o: list ) {
            idList.add ( o.getId ().toString () );
        }
        autorizzaEsportazioneDati ( "epaycat_t_tassonomia", idList );

        String fileName = "tassonomiaPagopa.csv";

        preparaHeaderDownload ( response, fileName );
        OutputStreamWriter writer = new OutputStreamWriter ( response.getOutputStream () );

        //for header
        CSVUtils.writeLine ( writer, Arrays.asList (
            "Codice Tipo Ente Creditore",
            "Tipo Ente Creditore",
            "Progressivo Macro Area Per Ente Creditore",
            "Nome Macro Area",
            "Descrizione Macro Area",
            "Codice Tipologia Servizio",
            "Tipo Servizio",
            "Motivo Giuridico Della Riscossione",
            "Descrizione Tipo Servizio",
            "Versione Tassonomia",
            "Dati Specifici Di Incasso",
            "Data Inizio Validita",
            "Data Fine Validita" ), ';' );

        for ( TassonomiaVO voce: list ) {

            List<String> line = new ArrayList<> ();

            line.add ( toCsvString ( voce.getCodTipoEnteCreditore () ) );
            line.add ( toCsvString ( voce.getTipoEnteCreditore () ) );
            line.add ( toCsvString ( voce.getMacroArea () ) );
            line.add ( toCsvString ( voce.getNomeMacroArea () ) );
            line.add ( toCsvString ( voce.getDescrMacroArea () ) );
            line.add ( toCsvString ( voce.getCodTipologiaServizio () ) );
            line.add ( toCsvString ( voce.getTipoServizio () ) );
            line.add ( toCsvString ( voce.getDesMotivoGiuridicoRiscossione () ) );
            line.add ( toCsvString ( voce.getDescrTipoServizio () ) );

            line.add ( toCsvString ( voce.getnVersioneTassonomia () ) );
            line.add ( toCsvString ( voce.getDatiSpecificiIncasso () ) );

            line.add ( toCsvString ( voce.getDataInizioValidita () != null ? sdf.format ( voce.getDataInizioValidita () ) : "" ) );
            line.add ( toCsvString ( voce.getDataFineValidita () != null ? sdf.format ( voce.getDataFineValidita () ) : "" ) );

            CSVUtils.writeLine ( writer, line, ';', '"' );

        }

        writer.flush ();
        writer.close ();

    }

    private String toCsvString ( Integer raw ) {
        if ( raw == null ) {
            return "";
        } else {
            return raw.toString ();
        }
    }

    private String toCsvString ( Long raw ) {
        if ( raw == null ) {
            return "";
        } else {
            return raw.toString ();
        }
    }

    private String toCsvString ( String raw ) {
        if ( raw == null ) {
            return "";
        } else {
            return raw;
        }
    }

    private void preparaHeaderDownload ( HttpServletResponse response, String filename ) {
        response.setHeader ( CONTENT_DISPOSITION_KEY, CONTENT_DISPOSITION_VALUE + filename );
        response.setHeader ( CONTENT_TRANSFER_ENCODING_KEY, CONTENT_TRANSFER_ENCODING_VALUE );
        response.setHeader ( CONTENT_ENCODING_KEY, CONTENT_ENCODING_VALUE );
        response.setHeader ( PRAGMA_KEY, PRAGMA_VALUE );
        response.setHeader ( CACHE_CONTROL_KEY, CACHE_CONTROL_VALUE );
        response.setContentType ( FORCE_DOWNLOAD );
    }

    private void populateRow ( XSSFSheet worksheet, XSSFRow row, Object... values ) {
        int cellindex = 0;
        for ( Object value: values ) {
            setCellValue ( worksheet, row.createCell ( cellindex++ ), value );
        }
    }

    private boolean falseIfNull ( Boolean b ) {
        if ( b == null ) {
            return false;
        } else {
            return b;
        }
    }

    private void setCellValue ( XSSFSheet worksheet, XSSFCell cell, Object object ) {

        if ( null != object ) {
            if ( object instanceof Integer ) {
                cell.getCellStyle ()
                    .setDataFormat ( worksheet.getWorkbook ().getCreationHelper ().createDataFormat ().getFormat ( "0" ) );
                cell.setCellValue ( (Integer) object );

            }
            if ( object instanceof Long ) {
                cell.getCellStyle ()
                    .setDataFormat ( worksheet.getWorkbook ().getCreationHelper ().createDataFormat ().getFormat ( "0" ) );
                cell.setCellValue ( (Long) object );
            }

            if ( object instanceof Double ) {
                cell.setCellValue ( ( (Double) object ).doubleValue () );
            }

            if ( object instanceof String ) {
                cell.setCellValue ( (String) object );
            }

            if ( object instanceof Date ) {

                cell.setCellValue ( sdf.format ( object ) );
            }

            if ( object instanceof Boolean ) {
                String value = (Boolean) object ? "SI" : "No";
                cell.setCellValue ( value );
            }
            if ( object instanceof BigDecimal ) {
                cell.setCellValue ( ( (BigDecimal) object ).doubleValue () );
            }
        }
    }

    private void autorizzaEsportazioneDati ( String entity, Collection<String> idList ) {

        AutorizzaEsportazioneDatiInput input = new AutorizzaEsportazioneDatiInput ();
        input.setEntity ( entity );
        input.getIdList ().clear ();
        for ( String id: idList ) {
            input.getIdList ().add ( id );
        }

        try {
            AutorizzaEsportazioneDatiOutput result = facade.autorizzaEsportazioneDati ( input );
            if ( !"OK".equals ( result.getCodiceEsito () ) ) {
                throw new RuntimeException ( "Errore nell'autorizzazione dell'esportazione: " + result.getDescrizioneEsito () );
            }
        } catch ( Exception_Exception | EpaypacatalogsrvException_Exception | UnrecoverableException_Exception e ) {
            throw new RuntimeException ( "Errore nell'autorizzazione dell'esportazione", e );
        }
    }

}
