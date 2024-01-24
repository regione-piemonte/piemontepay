/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.business.manager.impl;

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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import it.csi.epay.epaysimweb.business.manager.EsportazioneManager;
import it.csi.epay.epaysimweb.common.Constants;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.DtoOutputWsProvvisori;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.DtoProvvisorio;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.FlussoOrigineErrorePagopaOutputDTO;
import it.csi.epay.epaysimweb.model.flussi.EsportazioneFlussoVO;
import it.csi.epay.epaysimweb.util.CSVUtils;


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

    private static final String TEMPLATE_ESPORTAZIONE_PROVVISORI_FILENAME_XLS = "Template_provvisori.xlsx";

    private static final String TEMPLATE_ESPORTAZIONE_PROVVISORI_FILENAME_CSV = "Template_provvisori.csv";

    private static final String TEMPLATE_ESPORTAZIONE_FLUSSI_FILENAME_XLSX = "template_flussi.xlsx";

    private static final String TEMPLATE_ESPORTAZIONE_FLUSSI_FILENAME_CSV = "esportazione_flusso.csv";

    private static final String TEMPLATE_ESPORTAZIONE_ERRORI_FILENAME_XLS = "Template_errori.xlsx";

    private static final String TEMPLATE_ESPORTAZIONE_ERRORI_FILENAME_CSV = "Template_errori.csv";

    private SimpleDateFormat sdf = new SimpleDateFormat ( "dd/MM/yyyy HH:mm:ss" );

    private SimpleDateFormat simpleDate = new SimpleDateFormat ( "dd/MM/yyyy" );

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

    private int populateRow ( XSSFSheet worksheet, XSSFRow row, Object... values ) {
        int cellindex = 0;
        for ( Object value: values ) {
            setCellValue ( worksheet, row.createCell ( cellindex++ ), value );
        }
        return values.length;
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
                voce.getDataMovimento () != null ? simpleDate.format ( voce.getDataMovimento ().toGregorianCalendar ().getTime () ) : "",
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
            line.add (
                toCsvString ( voce.getDataMovimento () != null ? simpleDate.format ( voce.getDataMovimento ().toGregorianCalendar ().getTime () ) : "" ) );
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

    @Override
    public void esportaListaFlussiXls ( HttpServletResponse response, List<EsportazioneFlussoVO> flussiDaEsportare ) throws IOException {

        Resource resource = new ClassPathResource ( TEMPLATE_BASE_PACKAGE + TEMPLATE_ESPORTAZIONE_FLUSSI_FILENAME_XLSX );
        InputStream templateInputStream = resource.getInputStream ();

        XSSFWorkbook workbook = new XSSFWorkbook ( templateInputStream );
        XSSFSheet sheet = workbook.getSheetAt ( 0 );
        int columns = 0;
        if ( null != flussiDaEsportare && flussiDaEsportare.size () > 0 ) {
            logger.info ( "exporting XLSX for " + flussiDaEsportare.size () + " entries" );
            for ( int i = 0; i < flussiDaEsportare.size (); i++ ) {
                EsportazioneFlussoVO voce = flussiDaEsportare.get ( i );

                XSSFRow row = sheet.createRow ( i + 1 );

                columns = populateRow ( sheet, row,
                    //FLUSSO ORIGINE
                    voce.getIdentificativoFlusso (),
                    voce.getCfEnteCreditore () + Constants.SEPARATOR_MINUS + voce.getDenominazioneEnte (),
                    voce.getIdentificativoPsp () + Constants.SEPARATOR_MINUS + voce.getDenominazionePsp (),
                    voce.getImportoTotalePagamenti (),
                    voce.getNumeroTotalePagamenti (),
                    voce.getImportoTotalePagamentiIntermediati (),
                    voce.getNumeroTotalePagamentiIntermediati (),
                    voce.getProvvisorioAnno (),
                    voce.getProvvisorioNumero (),
                    //FLUSSO SINTESI
                    voce.getCodiceVersamento (),
                    voce.getDescrizioneCodiceVersamento (),
                    voce.getDatiSpecificiDiRiscossione (),
                    voce.getDescrizioneDatiSpecifici (),
                    voce.getImportoQuotaAggregazione (),
                    voce.getNumeroPagamentiAggregazione (),
                    voce.getAccertamentoAnno (),
                    voce.getAccertamentoNumero (),
                    voce.getArticolo (),
                    voce.getCapitolo (),
                    voce.getPdc (),
                    //FLUSSO DETTAGLIO
                    voce.getAnagraficaPagatore (),
                    voce.getCausale (),
                    voce.getDescrizioneCausaleVersamento (),
                    voce.getIdentificativoUnicoVersamento (),
                    voce.getIdentificativoUnicoRiscossione (),
                    voce.getImportoSingoloVersamento (),
                    voce.getIndiceSingoloVersamento (),
                    voce.getDataPagamento () != null ? sdf.format ( voce.getDataPagamento () ) : "",
                                    voce.getEsitoPagamento () );

            }
        }
        for ( int i = 0; i < columns; i++ ) {
            sheet.autoSizeColumn ( i );
        }

        // prima riga bloccata
        sheet.createFreezePane ( 0, 1 );

        // invio header
        preparaHeaderDownload ( response, TEMPLATE_ESPORTAZIONE_FLUSSI_FILENAME_XLSX );

        // scrivo contenuto
        workbook.write ( response.getOutputStream () );
        workbook.close ();

    }

    @Override
    public void esportaListaFlussiCsv ( HttpServletResponse response, List<EsportazioneFlussoVO> flussiDaEsportare ) throws IOException {

        String fileName = TEMPLATE_ESPORTAZIONE_FLUSSI_FILENAME_CSV;
        preparaHeaderDownload ( response, fileName );

        OutputStreamWriter writer = new OutputStreamWriter ( response.getOutputStream () );

        //for header
        CSVUtils.writeLine ( writer, Arrays.asList (
            //FLUSSO ORIGINE
            "Identificativo flusso",
            "Istituto ricevente",
            "Psp",
            "Importo totale pagamenti",
            "Numero toatale pagamenti",
            "Importo totale pagamenti intermediati",
            "Numero toatale pagamenti intermediati",
            "Anno provvisorio",
            "Numero provvisorio",
            //FLUSSO SINTESI
            "Codice versamento",
            "Descrizione versamento",
            "Dati specifici riscossione",
            "Descrizione dati specifici",
            "Importo quota aggregazione",
            "Numero versamenti quota aggregazione",
            "Anno accertamento",
            "Numero accertamento",
            "Articolo",
            "Capitolo   ",
            "Piano dei conti",
            //FLUSSO DETTAGLIO
            "Anagrafica pagatore",
            "Causale versamento",
            "Descrizione causale",
            "IUV",
            "IUR",
            "Importo versamento",
            "Indice versamento",
            "Data pagamento",
                        "Esito pagamento" ), ';' );
        if ( null != flussiDaEsportare && flussiDaEsportare.size () > 0 ) {
            logger.info ( "exporting CSV for " + flussiDaEsportare.size () + " entries" );
            for ( EsportazioneFlussoVO voce: flussiDaEsportare ) {

                List<String> line = new ArrayList<> ();
                //FLUSSO ORIGINE
                line.add ( toCsvString ( voce.getIdentificativoFlusso () ) );
                line.add ( toCsvString ( voce.getCfEnteCreditore () + Constants.SEPARATOR_MINUS + voce.getDenominazioneEnte () ) );
                line.add ( toCsvString ( voce.getIdentificativoPsp () + Constants.SEPARATOR_MINUS + voce.getDenominazionePsp () ) );
                line.add ( toCsvString ( voce.getImportoTotalePagamenti () ) );
                line.add ( toCsvString ( voce.getNumeroTotalePagamenti () ) );
                line.add ( toCsvString ( voce.getImportoTotalePagamentiIntermediati () ) );
                line.add ( toCsvString ( voce.getNumeroTotalePagamentiIntermediati () ) );
                line.add ( toCsvString ( voce.getProvvisorioAnno () ) );
                line.add ( toCsvString ( voce.getProvvisorioNumero () ) );
                //FLUSSO SINTESI
                line.add ( toCsvString ( voce.getCodiceVersamento () ) );
                line.add ( toCsvString ( voce.getDescrizioneCodiceVersamento () ) );
                line.add ( toCsvString ( voce.getDatiSpecificiDiRiscossione () ) );
                line.add ( toCsvString ( voce.getDescrizioneDatiSpecifici () ) );
                line.add ( toCsvString ( voce.getImportoQuotaAggregazione () ) );
                line.add ( toCsvString ( voce.getNumeroPagamentiAggregazione () ) );
                line.add ( toCsvString ( voce.getAccertamentoAnno () ) );
                line.add ( toCsvString ( voce.getAccertamentoNumero () ) );
                line.add ( toCsvString ( voce.getArticolo () ) );
                line.add ( toCsvString ( voce.getCapitolo () ) );
                line.add ( toCsvString ( voce.getPdc () ) );
                //FLUSSO DETTAGLIO
                line.add ( toCsvString ( voce.getAnagraficaPagatore () ) );
                line.add ( toCsvString ( voce.getCausale () ) );
                line.add ( toCsvString ( voce.getDescrizioneCausaleVersamento () ) );
                line.add ( toCsvString ( voce.getIdentificativoUnicoVersamento () ) );
                line.add ( toCsvString ( voce.getIdentificativoUnicoRiscossione () ) );
                line.add ( toCsvString ( voce.getImportoSingoloVersamento () ) );
                line.add ( toCsvString ( voce.getIndiceSingoloVersamento () ) );
                line.add ( toCsvString ( voce.getDataPagamento () != null ? sdf.format ( voce.getDataPagamento () ) : "" ) );
                line.add ( toCsvString ( voce.getEsitoPagamento () ) );
                CSVUtils.writeLine ( writer, line, ';' );
            }
        }
        writer.flush ();
        writer.close ();

    }

    @Override
    public void esportaTemplateErroriXls ( HttpServletResponse response, List<FlussoOrigineErrorePagopaOutputDTO> items ) throws IOException {
        logger.info ( "exporting XLSX for " + items.size () + " entries" );

        Resource resource = new ClassPathResource ( TEMPLATE_BASE_PACKAGE + TEMPLATE_ESPORTAZIONE_ERRORI_FILENAME_XLS );
        InputStream templateInputStream = resource.getInputStream ();

        XSSFWorkbook workbook = new XSSFWorkbook ( templateInputStream );
        XSSFSheet sheet = workbook.getSheetAt ( 0 );

        for ( int i = 0; i < items.size (); i++ ) {
            FlussoOrigineErrorePagopaOutputDTO voce = items.get ( i );

            XSSFRow row = sheet.createRow ( i + 1 );

            populateRow ( sheet, row,
                voce.getIdentificativoFlusso (),
                voce.getCfEnteRicevente (),
                voce.getCodiceErrore (),
                voce.getCodiceVersamento (),
                voce.getDataInserimento () != null ? simpleDate.format ( voce.getDataInserimento ().toGregorianCalendar ().getTime () ) : "",
                                voce.getDataoraFlusso () != null ? simpleDate.format ( voce.getDataInserimento ().toGregorianCalendar ().getTime () ) : "",
                                                voce.getDataRegolamento () != null ? simpleDate.format ( voce.getDataInserimento ().toGregorianCalendar ().getTime () ) : "",
                                                                voce.getDescrizioneErrore (),
                                                                voce.getIbanRiversamentoFlusso (),
                                                                voce.getIdentificativoPsp (),
                                                                voce.getIdentificativoUnivocoRegolamento (),
                                                                voce.getImportoTotalePagamenti (),
                                                                voce.getImportoTotalePagamentiIntermediati (),
                                                                voce.getNumeroTotalePagamenti (),
                                                                voce.getNumeroTotalePagamentiIntermediati () );
        }

        for ( int i = 0; i < 28; i++ ) {
            sheet.autoSizeColumn ( i );
        }

        // prima riga bloccata
        sheet.createFreezePane ( 0, 1 );

        // invio header
        preparaHeaderDownload ( response, TEMPLATE_ESPORTAZIONE_ERRORI_FILENAME_XLS );

        // scrivo contenuto
        workbook.write ( response.getOutputStream () );
        workbook.close ();

    }

    @Override
    public void esportaTemplateErroriCsv ( HttpServletResponse response, List<FlussoOrigineErrorePagopaOutputDTO> items ) throws IOException {

        logger.info ( "exporting CSV for " + items.size () + " entries" );

        String fileName = TEMPLATE_ESPORTAZIONE_ERRORI_FILENAME_CSV;
        preparaHeaderDownload ( response, fileName );

        OutputStreamWriter writer = new OutputStreamWriter ( response.getOutputStream () );

        //for header
        CSVUtils.writeLine ( writer, Arrays.asList (
            "Identificativo flusso",
            "CF Ente ricevente",
            "Codice errore",
            "Codice versamento",
            "Data inserimento",
            "Data ora flusso",
            "Data regolamento",
            "Descrizione errore",
            "Iban riversamento flusso",
            "Identificativo PSP",
            "IUR",
            "Importo totale pagamenti",
            "Importo totale pagamenti intermediati",
            "Numero totale pagamenti",
            "Numero totale pagamenti intermediati"), ';' );

        for ( int i = 0; i < items.size (); i++ ) {

            List<String> line = new ArrayList<> ();

            FlussoOrigineErrorePagopaOutputDTO voce = items.get ( i );

            line.add ( toCsvString ( voce.getIdentificativoFlusso () ) );
            line.add ( toCsvString ( voce.getCfEnteRicevente () ) );
            line.add ( toCsvString ( voce.getCodiceErrore () ) );
            line.add ( toCsvString ( voce.getCodiceVersamento () ) );
            line.add ( toCsvString ( voce.getDataInserimento () != null ? simpleDate.format ( voce.getDataInserimento ().toGregorianCalendar ().getTime () ) : "" ) );
            line.add ( toCsvString ( voce.getDataoraFlusso () != null ? simpleDate.format ( voce.getDataoraFlusso ().toGregorianCalendar ().getTime () ) : "" ) );
            line.add ( toCsvString ( voce.getDataRegolamento () != null ? simpleDate.format ( voce.getDataRegolamento ().toGregorianCalendar ().getTime () ) : "" ) );
            line.add ( toCsvString ( voce.getDescrizioneErrore () ) );
            line.add ( toCsvString ( voce.getIbanRiversamentoFlusso () ) );
            line.add ( toCsvString ( voce.getIdentificativoPsp () ) );
            line.add ( toCsvString ( voce.getIdentificativoUnivocoRegolamento () ) );
            line.add ( toCsvString ( voce.getImportoTotalePagamenti ().doubleValue () ) );
            line.add ( toCsvString ( voce.getImportoTotalePagamentiIntermediati ().doubleValue () ) );
            line.add ( toCsvString ( voce.getNumeroTotalePagamenti () ) );
            line.add ( toCsvString ( voce.getNumeroTotalePagamentiIntermediati () ) );

            CSVUtils.writeLine ( writer, line, ';' );
        }

        writer.flush ();
        writer.close ();

    }
}
