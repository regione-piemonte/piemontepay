/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.business.manager.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

import it.csi.epay.epaymodricweb.business.manager.EsportazioneManager;
import it.csi.epay.epaymodricweb.business.manager.FlussoManager;
import it.csi.epay.epaymodricweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsProvvisori;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoProvvisorio;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.TipoFileReportEnum;
import it.csi.epay.epaymodricweb.model.flusso.EsportazioneFlussoVO;
import it.csi.epay.epaymodricweb.model.flusso.RicercaFlussoFiltroVO;
import it.csi.epay.epaymodricweb.model.report.FileReportVO;
import it.csi.epay.epaymodricweb.util.CSVUtils;


/**
 *
 */
@Service
public class EsportazioneManagerImpl implements EsportazioneManager {

    private Logger logger = LoggerFactory.getLogger ( EsportazioneManager.class );

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

    private static final String TEMPLATE_ESPORTAZIONE_FLUSSI_FILENAME = "template_flussi.xlsx";

    private static final String TEMPLATE_ESPORTAZIONE_PROVVISORI_FILENAME_XLS = "Template_provvisori.xlsx";

    private static final String TEMPLATE_ESPORTAZIONE_PROVVISORI_FILENAME_CSV = "Template_provvisori.csv";

    private static final String MODELLO_TEMPLATE_ESPORTAZIONE_PROVVISORI_FILENAME_XLS = "Modello_template_provvisori.xlsx";

    private static final String MODELLO_TEMPLATE_ESPORTAZIONE_PROVVISORI_FILENAME_CSV = "Modello_template_provvisori.csv";

    private SimpleDateFormat sdf = new SimpleDateFormat ( "dd/MM/yyyy HH:mm:ss" );
    private SimpleDateFormat simpleDate = new SimpleDateFormat ( "dd/MM/yyyy" );
    
    @Autowired
    private FlussoManager flussoManager;
    
    @Override
    public void esportaListaFlussiXls ( HttpServletResponse response,  RicercaFlussoFiltroVO filtro ) throws IOException, OperationFailedException {

        FileReportVO file = flussoManager.ricercaFlussiDaEsportare ( filtro, TipoFileReportEnum.XLS );
        // invio header
        preparaHeaderDownload ( response, file.getNomeFile () );
        response.getOutputStream ().write ( file.getContenutoFile () );
    }

    @Override
    public void esportaListaFlussiCsv ( HttpServletResponse response,  RicercaFlussoFiltroVO filtro ) throws IOException, OperationFailedException {

        FileReportVO file = flussoManager.ricercaFlussiDaEsportare ( filtro, TipoFileReportEnum.CSV );
        // invio header
        preparaHeaderDownload ( response, file.getNomeFile () );
        response.getOutputStream ().write ( file.getContenutoFile () );

    }

    private String toCsvString ( Integer raw ) {
        if ( raw == null ) {
            return "";
        } else {
            return raw.toString ();
        }
    }

    private String toCsvString ( Double raw ) {
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
                cell.setCellType ( org.apache.poi.ss.usermodel.CellType.NUMERIC );
                cell.setCellValue ( ( (Double) object ).doubleValue () );
            }

            if ( object instanceof String ) {
                cell.setCellValue ( (String) object );
            }

            if ( object instanceof Date ) {

                cell.setCellValue ( sdf.format ( object ) );
            }

            if ( object instanceof Boolean ) {
                String value = (Boolean) object ? "S?" : "No";
                cell.setCellValue ( value );
            }
            if ( object instanceof BigDecimal ) {
                cell.setCellType ( org.apache.poi.ss.usermodel.CellType.NUMERIC );
                cell.setCellValue ( ( (BigDecimal) object ).doubleValue () );
            }
        }
    }

    @Override
    public void esportaModelloTemplateProvvisoriCsv ( HttpServletResponse response ) throws IOException {
        downloadBytes ( TEMPLATE_BASE_PACKAGE, MODELLO_TEMPLATE_ESPORTAZIONE_PROVVISORI_FILENAME_CSV, response );
    }

    @Override
    public void esportaModelloTemplateProvvisoriXsl ( HttpServletResponse response ) throws IOException {
        downloadBytes ( TEMPLATE_BASE_PACKAGE, MODELLO_TEMPLATE_ESPORTAZIONE_PROVVISORI_FILENAME_XLS, response );
    }

    private void downloadBytes ( String basePath, String fileName, HttpServletResponse response ) throws IOException {
        preparaHeaderDownload ( response, fileName );

        Resource resource = new ClassPathResource ( basePath + fileName );
        InputStream templateInputStream = resource.getInputStream ();

        OutputStream writer = response.getOutputStream ();
        byte [] buffer = new byte [4096];
        int length;
        while ( ( length = templateInputStream.read ( buffer ) ) > 0 ) {
            writer.write ( buffer, 0, length );
        }
        templateInputStream.close ();
        writer.flush ();
        writer.close ();
    }

    @Override
    public void esportaTemplateProvvisoriXls ( HttpServletResponse response, DtoOutputWsProvvisori flussiDaEsportare ) throws IOException {

        logger.info ( "exporting XLSX for " + flussiDaEsportare.getProvvisori ().size () + " entries" );

        Resource resource = new ClassPathResource ( TEMPLATE_BASE_PACKAGE + TEMPLATE_ESPORTAZIONE_PROVVISORI_FILENAME_XLS );
        InputStream templateInputStream = resource.getInputStream ();

        XSSFWorkbook workbook = new XSSFWorkbook ( templateInputStream );
        XSSFSheet sheet = workbook.getSheetAt ( 0 );

        for ( int i = 0; i < flussiDaEsportare.getProvvisori ().size (); i++ ) {
            DtoProvvisorio voce = flussiDaEsportare.getProvvisori ().get ( i );

            XSSFRow row = sheet.createRow ( i + 1 );

            populateRow ( sheet, row,
                voce.getIdentificativoFlusso (),
                voce.getDataMovimento () != null ? simpleDate.format ( voce.getDataMovimento ().toGregorianCalendar ().getTime ()) : "",
                voce.getAnnoEsercizio (),
                voce.getNumeroProvvisorio (),
                voce.getAnnoProvvisorio (),
                voce.getImportoProvvisorio (),
                voce.getDescrizione () );
        }

        for ( int i = 0; i < 28; i++ ) {
            sheet.autoSizeColumn ( i );
        }

        // prima riga bloccata
        sheet.createFreezePane ( 0, 1 );

        // invio header
        preparaHeaderDownload ( response, TEMPLATE_ESPORTAZIONE_PROVVISORI_FILENAME_XLS );

        // scrivo contenuto
        workbook.write ( response.getOutputStream () );
        workbook.close ();

    }

    @Override
    public void esportaTemplateProvvisoriCsv ( HttpServletResponse response, DtoOutputWsProvvisori flussiDaEsportare ) throws IOException {

        logger.info ( "exporting CSV for " + flussiDaEsportare.getProvvisori ().size () + " entries" );

        String fileName = TEMPLATE_ESPORTAZIONE_PROVVISORI_FILENAME_CSV;
        preparaHeaderDownload ( response, fileName );

        OutputStreamWriter writer = new OutputStreamWriter ( response.getOutputStream () );

        //for header
        CSVUtils.writeLine ( writer, Arrays.asList (
            "Identificativo Flusso", "Data Movimento",
            "Anno Esercizio", "Numero Provvisorio", "Anno Provvisorio", "Importo Provvisorio", "Descrizione" ), ';' );

        for ( int i = 0; i < flussiDaEsportare.getProvvisori ().size (); i++ ) {

            List<String> line = new ArrayList<> ();

            DtoProvvisorio voce = flussiDaEsportare.getProvvisori ().get ( i );

            line.add ( toCsvString ( voce.getIdentificativoFlusso () ) );
            line.add ( toCsvString ( voce.getDataMovimento () != null ? simpleDate.format ( voce.getDataMovimento ().toGregorianCalendar ().getTime () ) : "" ) );
            line.add ( toCsvString ( voce.getAnnoEsercizio () ) );
            line.add ( toCsvString ( voce.getNumeroProvvisorio () ) );
            line.add ( toCsvString ( voce.getAnnoProvvisorio () ) );
            line.add ( toCsvString ( voce.getImportoProvvisorio ().doubleValue () ) );
            line.add ( toCsvString ( voce.getDescrizione () ) );

            CSVUtils.writeLine ( writer, line, ';' );
        }

        writer.flush ();
        writer.close ();

    }
}
