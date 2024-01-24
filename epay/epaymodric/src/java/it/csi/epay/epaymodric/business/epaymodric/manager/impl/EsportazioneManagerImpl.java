/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.cxf.common.util.CollectionUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import it.csi.epay.epaymodric.business.epaymodric.bo.StatoFlussoAggregato;
import it.csi.epay.epaymodric.business.epaymodric.facade.EpaywsoServicesFacade;
import it.csi.epay.epaymodric.business.epaymodric.manager.EsportazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.model.CblRStatoFlussoErrore;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTConfigurazione;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.repository.ConfigurazioneRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.StatoFlussoErroreRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.custom.FlussiOrigineRepositorySpec;
import it.csi.epay.epaymodric.business.epaymodric.utils.CSVUtils;
import it.csi.epay.epaymodric.business.epaymodric.utils.Costanti;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaFlussoOrigine;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.StatoFlussoAggregatoEnum;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.StatoFlussoEnum;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.TipoRichiestaEnum;
import it.csi.epay.epaymodric.util.wsdl.epaywso.EsitoRicercaStatiAggregatiWsoType;
import it.csi.epay.epaymodric.util.wsdl.epaywso.RicercaStatiAggregatiWsoRequestType;
import it.csi.epay.epaymodric.util.wsdl.epaywso.RicercaStatiAggregatiWsoResponseType;
import it.csi.epay.epaymodric.util.wsdl.epaywso.RicercaStatiAggregatiWsoTypeList;
import it.csi.epay.epaymodric.util.wsdl.epaywso.RicercaStatoAggregatoWsoType;
@Service
public class EsportazioneManagerImpl implements EsportazioneManager {

   @Autowired
   private StatoFlussoErroreRepository statoFlussoErroreRepository;
   
   @Autowired
   private EpaywsoServicesFacade wsoFacade;
   
   @Autowired
   private ConfigurazioneRepository confRepository;

   @Autowired
   private FlussiOrigineRepositorySpec flussiOrigineRepositorySpec;

    private final Logger logger = LogManager.getLogger ( this.getClass() );

    public EsportazioneManagerImpl () {

    }
    private static final String TEMPLATE_BASE_PACKAGE = "";

    private static final String TEMPLATE_FLUSSI_COMPLETI_FILENAME = "template_report_flussi_completi.xlsx";
    private static final String TEMPLATE_REPORT_CONTABILE_FILENAME = "template_report_contabile.xlsx";
    private static final int NUMERO_CAMPI_SOTTORIGHE_SINTESI = 7;
    private SimpleDateFormat sdf = new SimpleDateFormat ( "dd/MM/yyyy" );
    
    @Override
    @Transactional(readOnly = true)
    public List<String> generaFileReportXlsx ( DTOInputWsRicercaFlussoOrigine flussoInput, String prefix ) throws IOException {

        List<String> reportsFilesNames = new LinkedList<> ();
        //lettura file template
        Resource resource = new ClassPathResource ( TEMPLATE_BASE_PACKAGE + TEMPLATE_FLUSSI_COMPLETI_FILENAME );
        try ( Workbook wbTemplate = new XSSFWorkbook ( resource.getInputStream () ); ) {
            String suffix = ".xlsx";
            int contatoreFile = 1;

            // preparazione paginazione
            Page<CblTFlussoOrigine> list = null;
            do {
                list = flussiOrigineRepositorySpec.cercaFlussiPerFiltro ( flussoInput );
                if ( null != list ) {
                    HashMap<String,StatoFlussoAggregato> stati = calcolaStato ( list.getContent () );
                    int rowCounter = 0;
                    // creazione file temporaneo
                    File reportFile = File.createTempFile ( prefix + "_" + contatoreFile++ + "_", suffix );
                    reportsFilesNames.add ( reportFile.getAbsolutePath () );
                    reportFile.deleteOnExit ();
                    if ( logger.isDebugEnabled () ) {
                        logger.debug ( "Inizio creazione file: " + reportFile.getAbsolutePath () );
                    }
                    try ( SXSSFWorkbook wbss = new SXSSFWorkbook () ) {
                        // creazione excel a partire dal file template
                        clonaExcel ( wbTemplate, wbss );
                        SXSSFSheet sheet = wbss.getSheetAt ( 0 );
                        //popolamento excel
                        for ( int i = 0; i < list.getNumberOfElements (); i++ ) {
                            CblTFlussoOrigine voce = list.getContent ().get ( i );
                            StatoFlussoAggregato statoAggregato = stati.get ( voce.getIdentificativoFlusso () );
//                            statoAggregato.setStatoFlusso ( StatoFlussoAggregatoEnum.DEFAULT.getCodice () );
//                            statoAggregato.setDatoAggiuntivo ( StatoFlussoAggregatoEnum.DEFAULT.getDatoAggiuntivo () );

                            for ( int j = 0; j < voce.getCblTFlussoSintesis ().size (); j++ ) {

                                for ( int k = 0; k < voce.getCblTFlussoSintesis ().get ( j ).getCblTFlussoDettaglios ().size (); k++ ) {

                                    SXSSFRow row = sheet.createRow ( rowCounter + 1 );
                                    populateRow ( wbss, row,
                                        voce.getIdentificativoFlusso (),
                                        voce.getDataoraFlusso (),
                                        voce.getCblTFlussoSintesis ().get ( j ).getCodiceVersamento (),
                                        voce.getCblTFlussoSintesis ().get ( j ).getDescrizioneCodiceVersamento (),
                                        voce.getCblTFlussoSintesis ().get ( j ).getDatiSpecificiDiRiscossione (),
                                        voce.getIdentificativoIstitutoRicevente (),
                                        voce.getCblTPsp () != null ? voce.getCblTPsp ().getIdentificativoPsp () : null,
                                        voce.getImportoTotalePagamenti (),
                                        voce.getNumeroTotalePagamenti (),
                                        voce.getDataInserimento (),
                                        voce.getCblTFlussoSintesis ().get ( j ).getImportoQuotaAggregazione (),
                                        voce.getCblTFlussoSintesis ().get ( j ).getNumeroVersQuotaAggregazione (),
                                        voce.getCblTFlussoSintesis ().get ( j ).getAccertamentoNumero (),
                                        voce.getCblTFlussoSintesis ().get ( j ).getAccertamentoAnno (),
                                        voce.getCblTFlussoSintesis ().get ( j ).getProvvisorioAnno (),
                                        voce.getCblTFlussoSintesis ().get ( j ).getProvvisorioNro (),
                                        voce.getCblTFlussoSintesis ().get ( j ).getCblTFlussoDettaglios ().get ( k ).getCodicefiscalePagatore (),
                                        voce.getCblTFlussoSintesis ().get ( j ).getCblTFlussoDettaglios ().get ( k ).getAnagraficaPagatore (),
                                        voce.getCblTFlussoSintesis ().get ( j ).getCblTFlussoDettaglios ().get ( k ).getDescrizioneCausaleVersamento (),
                                        voce.getCblTFlussoSintesis ().get ( j ).getCblTFlussoDettaglios ().get ( k ).getImportoSingoloVersamento (),
                                        voce.getCblTFlussoSintesis ().get ( j ).getCblTFlussoDettaglios ().get ( k ).getIdentificativoUnicoVersamento (),
                                        voce.getCblTFlussoSintesis ().get ( j ).getCblTFlussoDettaglios ().get ( k ).getIndiceSingoloVersamento (),
                                        voce.getCblTFlussoSintesis ().get ( j ).getCblTFlussoDettaglios ().get ( k ).getEsitoPagamento (),
                                        voce.getCblTFlussoSintesis ().get ( j ).getCblTFlussoDettaglios ().get ( k ).getDataPagamento (),
                                        voce.getCblTFlussoSintesis ().get ( j ).getCblTFlussoDettaglios ().get ( k ).getTransactionid (),
                                        voce.getCblTFlussoSintesis ().get ( j ).getCblTFlussoDettaglios ().get ( k ).getIdentificativoUnicoRiscossione (),
                                        voce.getContatoreTentativi (),
                                        voce.getCblTEnte () != null ? voce.getCblTEnte ().getIbanTesoreria () : null,
                                        voce.getFlagFlussoIntermediato (),
                                        statoAggregato.getStatoFlusso (),
                                        statoAggregato.getDatoAggiuntivo (),
                                        new Date () );
                                    rowCounter++;
                                }
                            }
                        }
                        // scrivo contenuto
                        try ( OutputStream fileOutput = new FileOutputStream ( reportFile ) ) {
                            wbss.write ( fileOutput );
                        }
                        if ( logger.isDebugEnabled () ) {
                            logger.debug ( "Fine creazione file: " + reportFile.getAbsolutePath () );
                        }
                    }
                    flussoInput.setStart ( list.getNumber () + 1 );
                }
            } while ( null != list && list.hasNextPage () );
        }
        return reportsFilesNames;
    }
    
    private void clonaExcel ( Workbook oldWB, SXSSFWorkbook newWB ) {
        Sheet oldSheet = oldWB.getSheetAt ( 0 );
        SXSSFSheet newSheet = newWB.createSheet ( oldSheet.getSheetName () );
        // prima riga bloccata
        newSheet.createFreezePane ( 0, 1 );
        
        SXSSFRow newRow = newSheet.createRow ( 0 );
        Row oldRow = oldSheet.getRow ( 0 );
        for (int i = 0; i < oldRow.getLastCellNum(); i++) {
            Cell oldCell = oldRow.getCell(i);
            SXSSFCell newCell = newRow.createCell(i);
            newSheet.setColumnWidth ( i, oldSheet.getColumnWidth ( i ) );
            newRow.setHeight ( oldRow.getHeight () );
            // If the old cell is null jump to next cell
            if (oldCell == null) {
                newCell = null;
                continue;
            }
         // Copy style from old cell and apply to new cell
            CellStyle newCellStyle = newWB.createCellStyle();
            newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
            newCell.setCellStyle(newCellStyle);

            // If there is a cell comment, copy
            if (oldCell.getCellComment() != null) {
                newCell.setCellComment(oldCell.getCellComment());
            }

            // If there is a cell hyperlink, copy
            if (oldCell.getHyperlink() != null) {
                newCell.setHyperlink(oldCell.getHyperlink());
            }

            // Set the cell data type
            newCell.setCellType(oldCell.getCellTypeEnum());

            // Set the cell data value
            switch ( oldCell.getCellTypeEnum () ) {
            case BLANK :
                newCell.setCellValue ( oldCell.getStringCellValue () );
                break;
            case BOOLEAN :
                newCell.setCellValue ( oldCell.getBooleanCellValue () );
                break;
            case ERROR :
                newCell.setCellErrorValue ( oldCell.getErrorCellValue () );
                break;
            case FORMULA :
                newCell.setCellFormula ( oldCell.getCellFormula () );
                break;
            case NUMERIC :
                newCell.setCellValue ( oldCell.getNumericCellValue () );
                break;
            case STRING :
                newCell.setCellValue ( oldCell.getRichStringCellValue () );
                break;
            case _NONE :
                newCell.setCellValue ( oldCell.getStringCellValue ());
                break;
            }
        }
    }
    
    @Override
    public void generaFileReportContabileXlsx ( List<CblTFlussoOrigine> list, OutputStream outputStream ) throws IOException {
        logger.info ( "creating XLSX for " + list.size () + " entries" );

//        Resource resource = new ClassPathResource ( TEMPLATE_BASE_PACKAGE + TEMPLATE_REPORT_CONTABILE_FILENAME );
//        InputStream templateInputStream = resource.getInputStream ();
//
//        XSSFWorkbook workbook = new XSSFWorkbook ( templateInputStream );
//        XSSFSheet sheet = workbook.getSheetAt ( 0 );
//        int contatoreRighe = 0;
//        for ( int i = 0; i < list.size (); i++ ) {
//            CblTFlussoOrigine voce = list.get ( i );
//            StatoFlussoAggregato statoAggregato = calcolaStato ( voce );
//            for ( int j = 0; j < voce.getCblTFlussoSintesis ().size (); j++ ) {
//                for ( int l = 0; l < voce.getCblTFlussoSintesis ().get ( j ).getCblTFlussoDettaglios ().size (); l++ ) {
//                    XSSFRow row = sheet.createRow ( contatoreRighe + 1 );
//                    populateRow ( workbook, row,
//                        voce.getIdentificativoFlusso (),
//                        voce.getDataoraFlusso (),
//                        voce.getIdentificativoIstitutoRicevente (),
//                        voce.getCblTPsp () != null ? voce.getCblTPsp ().getDenominazionePsp () : null,
//                        voce.getImportoTotalePagamenti (),
//                        voce.getNumeroTotalePagamenti () );
//
//                    int definedCells = row.getPhysicalNumberOfCells ();
//                    for ( int num = 0; num < voce.getNumeroTotalePagamenti (); num++ ) {
//                        contatoreRighe++;
//                        int subRowCounter = contatoreRighe + 1;
//                        XSSFRow newRow = sheet.createRow ( subRowCounter );
//                        for ( int k = 0; k < voce.getCblTFlussoSintesis ().size (); k++ ) {
//                            populateSubRow ( wbss, newRow, definedCells,
//                                voce.getCblTFlussoSintesis ().get ( k ).getCodiceVersamento (),
//                                voce.getCblTFlussoSintesis ().get ( k ).getDatiSpecificiDiRiscossione (),
//                                voce.getCblTFlussoSintesis ().get ( k ).getDescrizioneCodiceVersamento (),
//                                voce.getCblTFlussoSintesis ().get ( k ).getImportoQuotaAggregazione (),
//                                voce.getCblTFlussoSintesis ().get ( k ).getNumeroVersQuotaAggregazione (),
//                                voce.getCblTFlussoSintesis ().get ( k ).getAccertamentoAnno (),
//                                voce.getCblTFlussoSintesis ().get ( k ).getAccertamentoNumero () );
//
//                        }
//
//                    }
//                    populateMainRowFinalPart ( wbss, row, definedCells + 7,
//                        voce.getCblTFlussoSintesis ().get ( j ).getProvvisorioAnno (),
//                        voce.getCblTFlussoSintesis ().get ( j ).getProvvisorioNro (),
//                        voce.getCblTEnte () != null ? voce.getCblTEnte ().getIbanTesoreria () : null,
//                        voce.getCblDStatoFlusso () != null ? voce.getCblDStatoFlusso ().getCodiceStato () : null,
//                        voce.getCblTEnte () != null ? voce.getCblTEnte ().getDenominazione () : null,
//                        voce.getCblTEnte () != null ? voce.getCblTEnte ().getCodiceFiscale () : null,
//                        voce.getDataInserimento (),
//                        statoAggregato.getStatoFlusso (),
//                        statoAggregato.getDatoAggiuntivo (),
//                        new Date () );
//
//                    contatoreRighe++;
//                }
//            }
//
//        }
//
//        for ( int i = 0; i < 23; i++ ) {
//            sheet.autoSizeColumn ( i );
//        }
//
//        // prima riga bloccata
//        sheet.createFreezePane ( 0, 1 );
//
//        // scrivo contenuto
//        workbook.write ( outputStream );
//        workbook.close ();
    }

    public void generaFileReportCsv ( DTOInputWsRicercaFlussoOrigine flussoInput, OutputStream outputStream ) throws IOException {

        OutputStreamWriter writer = new OutputStreamWriter ( outputStream );

        //for header
        CSVUtils.writeLine ( writer, Arrays.asList (
            "identificativo flusso",
            "data ora flusso",
            "codice versamento",
            "descrizione versamento",
            "dati specifici riscossione",
            "istituto ricevente",
            "PSP",
            "importo totale pagamenti",
            "numero totale pagamenti",
            "data inserimento",
            "importo quota aggregazione",
            "numero versamento quota aggregazione",
            "numero accertamento",
            "anno accertamento",
            "provvisorio anno",
            "provvisorio numero",
            "C.F. pagatore",
            "anagrafica pagatore",
            "causale",
            "importo versamento",
            "IUV",
            "indice versamento",
            "esito pagamento",
            "data pagamento",
            "ID transazione",
            "ID unico riscossione",
            "contatore tentativi",
            "IBAN riversamento flusso",
            "plurintermediato",
            "stato flusso aggregato",
            "dato aggiuntivo stato aggregato",
            "data ora inizio elaborazione report" ), ';' );
        Page<CblTFlussoOrigine> list = null;
        do {
            list = flussiOrigineRepositorySpec.cercaFlussiPerFiltro ( flussoInput );
            if ( null != list ) {
                HashMap<String, StatoFlussoAggregato> stati = calcolaStato ( list.getContent () );
                for ( CblTFlussoOrigine voce: list ) {
                    StatoFlussoAggregato statoAggregato = stati.get ( voce.getIdentificativoFlusso () );
                    for ( int j = 0; j < voce.getCblTFlussoSintesis ().size (); j++ ) {
                        for ( int k = 0; k < voce.getCblTFlussoSintesis ().get ( j ).getCblTFlussoDettaglios ().size (); k++ ) {
                            List<String> line = new ArrayList<> ();

                            line.add ( toCsvString ( voce.getIdentificativoFlusso () ) );
                            line.add ( toCsvString ( voce.getDataoraFlusso () ) );
                            line.add ( toCsvString ( voce.getCblTFlussoSintesis ().get ( j ).getCodiceVersamento () ) );
                            line.add ( toCsvString ( voce.getCblTFlussoSintesis ().get ( j ).getDescrizioneCodiceVersamento () ) );
                            line.add ( toCsvString ( voce.getCblTFlussoSintesis ().get ( j ).getDatiSpecificiDiRiscossione () ) );
                            line.add ( toCsvString ( voce.getIdentificativoIstitutoRicevente () ) );
                            line.add ( toCsvString ( voce.getCblTPsp () != null ? voce.getCblTPsp ().getDenominazionePsp () : null ) );
                            line.add ( toCsvString ( voce.getImportoTotalePagamenti () ) );
                            line.add ( toCsvString ( voce.getNumeroTotalePagamenti () ) );
                            line.add ( toCsvString ( voce.getDataInserimento () ) );
                            line.add ( toCsvString ( voce.getCblTFlussoSintesis ().get ( j ).getImportoQuotaAggregazione () ) );
                            line.add ( toCsvString ( voce.getCblTFlussoSintesis ().get ( j ).getNumeroVersQuotaAggregazione () ) );
                            line.add ( toCsvString ( voce.getCblTFlussoSintesis ().get ( j ).getAccertamentoNumero () ) );
                            line.add ( toCsvString ( voce.getCblTFlussoSintesis ().get ( j ).getAccertamentoAnno () ) );
                            line.add ( toCsvString ( voce.getCblTFlussoSintesis ().get ( j ).getProvvisorioAnno () ) );
                            line.add ( toCsvString ( voce.getCblTFlussoSintesis ().get ( j ).getProvvisorioNro () ) );
                            line.add (
                                toCsvString ( voce.getCblTFlussoSintesis ().get ( j ).getCblTFlussoDettaglios ().get ( k ).getCodicefiscalePagatore () ) );
                            line.add ( toCsvString ( voce.getCblTFlussoSintesis ().get ( j ).getCblTFlussoDettaglios ().get ( k ).getAnagraficaPagatore () ) );
                            line.add ( toCsvString (
                                voce.getCblTFlussoSintesis ().get ( j ).getCblTFlussoDettaglios ().get ( k ).getDescrizioneCausaleVersamento () ) );
                            line.add (
                                toCsvString ( voce.getCblTFlussoSintesis ().get ( j ).getCblTFlussoDettaglios ().get ( k ).getImportoSingoloVersamento () ) );
                            line.add ( toCsvString (
                                voce.getCblTFlussoSintesis ().get ( j ).getCblTFlussoDettaglios ().get ( k ).getIdentificativoUnicoVersamento () ) );
                            line.add (
                                toCsvString ( voce.getCblTFlussoSintesis ().get ( j ).getCblTFlussoDettaglios ().get ( k ).getIndiceSingoloVersamento () ) );
                            line.add ( toCsvString ( voce.getCblTFlussoSintesis ().get ( j ).getCblTFlussoDettaglios ().get ( k ).getEsitoPagamento () ) );
                            line.add ( toCsvString ( voce.getCblTFlussoSintesis ().get ( j ).getCblTFlussoDettaglios ().get ( k ).getDataPagamento () ) );
                            line.add ( toCsvString ( voce.getCblTFlussoSintesis ().get ( j ).getCblTFlussoDettaglios ().get ( k ).getTransactionid () ) );
                            line.add ( toCsvString (
                                voce.getCblTFlussoSintesis ().get ( j ).getCblTFlussoDettaglios ().get ( k ).getIdentificativoUnicoRiscossione () ) );
                            line.add ( toCsvString ( voce.getContatoreTentativi () ) );
                            line.add ( toCsvString ( voce.getCblTEnte () != null ? voce.getCblTEnte ().getIbanTesoreria () : null ) );
                            line.add ( toCsvString ( falseIfNull ( voce.getFlagFlussoIntermediato () ) ) );
                            line.add ( toCsvString ( statoAggregato.getStatoFlusso () ) );
                            line.add ( toCsvString ( statoAggregato.getDatoAggiuntivo () ) );
                            line.add ( toCsvString ( new Date () ) );

                            CSVUtils.writeLine ( writer, line, ';' );
                        }
                    }
                }
                flussoInput.setStart ( list.getNumber () + 1 );
            }
        } while ( null != list && list.hasNextPage () );
        writer.flush ();
        writer.close ();
    }
    
    public void generaFileReportContabileCsv (List<CblTFlussoOrigine> list, OutputStream outputStream ) throws IOException {

        logger.info ( "creating CSV for " + list.size () + " entries" );
        OutputStreamWriter writer = new OutputStreamWriter ( outputStream );

        //for header
        CSVUtils.writeLine ( writer, Arrays.asList (
            "identificativo flusso",
            "data ora flusso",
            "istituto ricevente",
            "PSP",
            "importo totale pagamenti",
            "numero totale pagamenti",
            "codice versamento",
            "dati specifici riscossione",
            "descrizione tassa",
            "importo quota aggregazione",
            "numero iuv(o parte di iuv) quota aggregazione",
            "numero accertamento",
            "anno accertamento",
            "numero provvisorio",
            "anno provvisorio",
            "IBAN riversamento",
            "stato flusso",
            "ente",
            "codice fiscale ente",
            "data inserimento",
            "stato flusso aggregato",
            "dato aggiuntivo stato flusso",
            "data ora inizio elaborazione"), ';' );

        for ( CblTFlussoOrigine voce: list ) {
        	for(int j = 0; j < voce.getCblTFlussoSintesis().size(); j++) {
        		for(int k = 0;  k < voce.getCblTFlussoSintesis().get(j).getCblTFlussoDettaglios().size(); k++) {
        			
        			List<String> line = new ArrayList<> ();
		
		            line.add(toCsvString(voce.getIdentificativoFlusso()));
		            line.add(toCsvString(voce.getDataoraFlusso()));
		            line.add(toCsvString(voce.getIdentificativoIstitutoRicevente()));
		            line.add(toCsvString(voce.getCblTPsp() != null ? voce.getCblTPsp().getDenominazionePsp() : null));
		            line.add(toCsvString(voce.getImportoTotalePagamenti()));
		            line.add(toCsvString(voce.getNumeroTotalePagamenti()));
		            line.add(toCsvString(voce.getCblTFlussoSintesis().get(j).getCodiceVersamento()));
		            line.add(toCsvString(voce.getCblTFlussoSintesis().get(j).getDatiSpecificiDiRiscossione()));
		            line.add(toCsvString(voce.getCblTFlussoSintesis().get(j).getDescrizioneCodiceVersamento()));
		            line.add(toCsvString(voce.getCblTFlussoSintesis().get(j).getImportoQuotaAggregazione()));
		            line.add(toCsvString(voce.getCblTFlussoSintesis().get(j).getNumeroVersQuotaAggregazione()));
		            line.add(toCsvString(voce.getCblTFlussoSintesis().get(j).getAccertamentoNumero()));
		            line.add(toCsvString(voce.getCblTFlussoSintesis().get(j).getAccertamentoAnno()));
		            line.add(toCsvString(voce.getCblTFlussoSintesis().get(j).getProvvisorioNro()));
		            line.add(toCsvString(voce.getCblTFlussoSintesis().get(j).getProvvisorioAnno()));
		            line.add(toCsvString(voce.getCblTEnte() != null ? voce.getCblTEnte().getIbanTesoreria(): null));
		            line.add(toCsvString(voce.getCblDStatoFlusso() != null ? voce.getCblDStatoFlusso().getCodiceStato() : null));
		            line.add(toCsvString(voce.getCblTEnte() != null ? voce.getCblTEnte().getDenominazione() : null));
		            line.add(toCsvString(voce.getCblTEnte() != null ? voce.getCblTEnte().getCodiceFiscale() : null));
//		            StatoFlussoAggregato statoAggregato = calcolaStato(voce); // da rivedere una volta abilitati i report contabili
//		            line.add(toCsvString(statoAggregato.getStatoFlusso()));
//		            line.add(toCsvString(statoAggregato.getDatoAggiuntivo()));
		            line.add(toCsvString(new Date ()));
		            
		            CSVUtils.writeLine ( writer, line, ';' );
        		}
        	}        
        }

        writer.flush ();
        writer.close ();
    }
    
    private HashMap<String, StatoFlussoAggregato> calcolaStato ( List<CblTFlussoOrigine> listaFlussi ) {

        List<CblTFlussoOrigine> listaflussiWSO = new LinkedList<> ();
        HashMap<String, StatoFlussoAggregato> mappaFlussi = new HashMap<> ();

        for ( CblTFlussoOrigine flussoOrigine: listaFlussi ) {
            StatoFlussoAggregato statoFlussoAggregato = new StatoFlussoAggregato ();
            mappaFlussi.put ( flussoOrigine.getIdentificativoFlusso (), statoFlussoAggregato );
            if ( !StringUtils.hasText ( flussoOrigine.getIdentificativoFlusso () ) || null == flussoOrigine.getCblDStatoFlusso () ) {
                statoFlussoAggregato.setStatoFlusso ( StatoFlussoAggregatoEnum.DEFAULT.getCodice () );
                statoFlussoAggregato.setDatoAggiuntivo ( StatoFlussoAggregatoEnum.DEFAULT.getDatoAggiuntivo () );
            }
            // parlare con flavio per individuare il flag invio flusso esteso. Visto che non e' presente sull'ente della tabella di modric.
            else if ( StatoFlussoEnum.BOZZA.getCodice ().equals ( flussoOrigine.getCblDStatoFlusso ().getCodiceStato () ) ) {
                statoFlussoAggregato.setStatoFlusso ( StatoFlussoAggregatoEnum.NON_ELABORATO_ANCORA_DA_ELABORARE.getCodice () );
                statoFlussoAggregato.setDatoAggiuntivo ( StatoFlussoAggregatoEnum.NON_ELABORATO_ANCORA_DA_ELABORARE.getDatoAggiuntivo () );
            } else if ( StatoFlussoEnum.IN_ERRORE.getCodice ().equals ( flussoOrigine.getCblDStatoFlusso ().getCodiceStato () ) ) {
                statoFlussoAggregato.setStatoFlusso ( StatoFlussoAggregatoEnum.NON_ELABORATO_ANCORA_DA_ELABORARE.getCodice () );
                statoFlussoAggregato.setDatoAggiuntivo ( StatoFlussoAggregatoEnum.NON_ELABORATO_ANCORA_DA_ELABORARE.getDatoAggiuntivo () );
            }
            //se identificativo_flusso presente su epaymodric.cbl_t_flusso_origine && stato_invio = 4
            // andra gestito con lo stato "Elaborato" - dato_aggiuntivo - "Non inviato".
            // Questo stato e corretto se il ciclo di vita termina su PEC;  se si prevedeva l'invio al gestionale e errato"
            else if ( StatoFlussoEnum.ELABORATO.getCodice ().equals ( flussoOrigine.getCblDStatoFlusso ().getCodiceStato () ) ) {
                if ( flussoOrigine.getIdentificativoFlusso () != null
                    && flussoOrigine.getCblDStatoFlusso ().getCodiceStato ().equals ( StatoFlussoEnum.BOZZA.getCodice () ) ) {
                    statoFlussoAggregato.setStatoFlusso ( StatoFlussoAggregatoEnum.NON_ELABORATO_BOZZA.getCodice () );
                    statoFlussoAggregato.setDatoAggiuntivo ( StatoFlussoAggregatoEnum.NON_ELABORATO_BOZZA.getDatoAggiuntivo () );
                } else if ( flussoOrigine.getIdentificativoFlusso () != null &&
                    ( flussoOrigine.getCblDStatoFlusso ().getCodiceStato ().equals ( StatoFlussoEnum.IN_ATTESA.getCodice () ) ||
                        flussoOrigine.getCblDStatoFlusso ().getCodiceStato ().equals ( StatoFlussoEnum.DA_SPACCHETTARE.getCodice () ) ) ) {
                    statoFlussoAggregato.setStatoFlusso ( StatoFlussoAggregatoEnum.NON_ELABORATO_IN_ATTESA.getCodice () );
                    statoFlussoAggregato.setDatoAggiuntivo ( StatoFlussoAggregatoEnum.NON_ELABORATO_IN_ATTESA.getDatoAggiuntivo () );
                } else if ( flussoOrigine.getIdentificativoFlusso () != null &&
                    ( flussoOrigine.getCblDStatoFlusso ().getCodiceStato ().equals ( StatoFlussoEnum.IN_ELABORAZIONE.getCodice () ) ||
                        flussoOrigine.getCblDStatoFlusso ().getCodiceStato ().equals ( StatoFlussoEnum.IN_ERRORE.getCodice () ) ) ) {
                    StringBuilder builder = new StringBuilder ();
                    statoFlussoAggregato.setStatoFlusso ( StatoFlussoAggregatoEnum.NON_ELABORATO_IN_ERRORE_MODRIC.getCodice () );
                    List<CblRStatoFlussoErrore> erroriFlusso = statoFlussoErroreRepository.findByCblTFlussoOrigine ( flussoOrigine );
                    String datoAggiuntivo = "In errore su modric: ";
                    builder.append ( datoAggiuntivo );
                    for ( CblRStatoFlussoErrore errore: erroriFlusso ) {
                        builder.append ( errore.getDescrizioneAggiuntivaErrore () + "\n" );
                    }
                    statoFlussoAggregato.setDatoAggiuntivo ( builder.toString () );
                } else if ( flussoOrigine.getIdentificativoFlusso () != null
                    && flussoOrigine.getCblDStatoFlusso ().getCodiceStato ().equals ( StatoFlussoEnum.ELABORATO.getCodice () ) ) {
                    statoFlussoAggregato.setStatoFlusso ( StatoFlussoAggregatoEnum.ELABORATO_NON_INVIATO.getCodice () );
                    statoFlussoAggregato.setDatoAggiuntivo ( StatoFlussoAggregatoEnum.ELABORATO_NON_INVIATO.getDatoAggiuntivo () );
                } else if ( flussoOrigine.getIdentificativoFlusso () != null &&
                    ( flussoOrigine.getCblDStatoFlusso ().getCodiceStato ().equals ( StatoFlussoEnum.NON_ELABORABILE.getCodice () ) ||
                        flussoOrigine.getCblDStatoFlusso ().getCodiceStato ().equals ( StatoFlussoEnum.NON_RICONCILIABILE.getCodice () ) ) ) {
                    statoFlussoAggregato.setStatoFlusso ( StatoFlussoAggregatoEnum.NON_ELABORATO_NON_ELABORABILE.getCodice () );
                    statoFlussoAggregato.setDatoAggiuntivo ( StatoFlussoAggregatoEnum.NON_ELABORATO_NON_ELABORABILE.getDatoAggiuntivo () );
                } else if ( flussoOrigine.getIdentificativoFlusso () != null &&
                    ( flussoOrigine.getCblDStatoFlusso ().getCodiceStato ().equals ( StatoFlussoEnum.ACQUISITO.getCodice () ) ||
                        flussoOrigine.getCblDStatoFlusso ().getCodiceStato ().equals ( StatoFlussoEnum.GENERATO.getCodice () ) ) ) {
                    statoFlussoAggregato.setStatoFlusso ( StatoFlussoAggregatoEnum.INVIATO_ENDPOINT.getDatoAggiuntivo () );
                    statoFlussoAggregato.setDatoAggiuntivo ( extractedEndPoint ( flussoOrigine ) );
                } else if ( flussoOrigine.getIdentificativoFlusso () != null
                    && flussoOrigine.getCblDStatoFlusso ().getCodiceStato ().equals ( StatoFlussoEnum.RIFIUTATO.getCodice () ) ) {
                    statoFlussoAggregato.setStatoFlusso ( StatoFlussoAggregatoEnum.NON_INVIATO_ENDPOINT.getCodice () );
                    statoFlussoAggregato.setDatoAggiuntivo ( extractedEndPoint ( flussoOrigine ) );
                } else if ( flussoOrigine.getIdentificativoFlusso () != null
                    && flussoOrigine.getCblDStatoFlusso ().getCodiceStato ().equals ( StatoFlussoEnum.DA_RIELABORARE.getCodice () ) ) {
                    statoFlussoAggregato.setStatoFlusso ( StatoFlussoAggregatoEnum.NON_ELABORATO_ANNULLATO.getCodice () );
                    statoFlussoAggregato.setDatoAggiuntivo ( StatoFlussoAggregatoEnum.NON_ELABORATO_ANNULLATO.getDatoAggiuntivo () );
                }
            } else {
                // inserisco flusso nell'elenco di quelli da chiedere lo stato a wso
                listaflussiWSO.add ( flussoOrigine );
            }
        }
        if ( !CollectionUtils.isEmpty ( listaflussiWSO ) ) {
            RicercaStatiAggregatiWsoRequestType request = new RicercaStatiAggregatiWsoRequestType ();
            RicercaStatiAggregatiWsoTypeList requestList = new RicercaStatiAggregatiWsoTypeList ();
            for ( CblTFlussoOrigine cblTFlussoOrigine: listaflussiWSO ) {
                RicercaStatoAggregatoWsoType type = new RicercaStatoAggregatoWsoType ();
                type.setIdFlusso ( cblTFlussoOrigine.getIdentificativoFlusso () );
                type.setCodiceFiscaleEnte ( cblTFlussoOrigine.getCblTEnte ().getCodiceFiscale () );
                requestList.getRicercaStatoAggregatoWso ().add ( type );
            }
            request.setIdTipoRichiesta ( BigInteger.valueOf ( TipoRichiestaEnum.TRASMETTI_FLUSSO_RENDICONTAZIONE_ESTESO.getId () ) );
            request.setRicercaStatoAggregatoWsoList ( requestList );
            RicercaStatiAggregatiWsoResponseType response = wsoFacade.ricercaStatiAggregatiWso ( request );
            if ( null != response && null != response.getEsitoRicercaStatiAggregatiWsoList ()
                && !response.getEsitoRicercaStatiAggregatiWsoList ().getEsitoRicercaStatiAggregatiWso ().isEmpty () ) {
                for ( EsitoRicercaStatiAggregatiWsoType esito: response.getEsitoRicercaStatiAggregatiWsoList ()
                    .getEsitoRicercaStatiAggregatiWso () ) {
                    StatoFlussoAggregato stato = mappaFlussi.get ( esito.getIdFlusso () );
                    stato.setStatoFlusso ( esito.getStatoFlusso () );
                    stato.setDatoAggiuntivo ( esito.getDatoAggiuntivoCodEsito () + " " + esito.getDatoAggiuntivoDescEsito () );
                }
            } else {
                for ( CblTFlussoOrigine cblTFlussoOrigine: listaflussiWSO ) {
                    StatoFlussoAggregato stato = mappaFlussi.get ( cblTFlussoOrigine.getIdentificativoFlusso () );
                    stato.setStatoFlusso ( StatoFlussoAggregatoEnum.NON_INVIATO_NON_DISPONIBILE.getCodice () );
                    stato.setDatoAggiuntivo ( StatoFlussoAggregatoEnum.NON_INVIATO_NON_DISPONIBILE.getDatoAggiuntivo () );
                }
            }
        }
        return mappaFlussi;
    }

	private String extractedEndPoint(CblTFlussoOrigine flussoOrigine) {
		CblTConfigurazione conf = confRepository.findByIdEnteAndNomeAttributo(flussoOrigine.getCblTEnte().getIdEnte(), Costanti.CONFIG_ATTR_SERVICE_ENDPOINT_INVIO_FLUSSI);
		String url = conf.getValore();
		String endPoint = "";
		if(url.matches("\\b(\\w*epaysim\\w*)\\b")) {
			endPoint = "Ai Simulatori";
		}
		else if(url.matches("\\b(\\w*contabilia\\w*)\\b")) {
			endPoint = "A Contabilia";
		}
		else if(url.matches("\\b(\\w*wso\\w*)\\b")) {
			endPoint = "Ad un gestionale esterno";
		}
		return endPoint;
	}

    private String toCsvString ( Integer raw ) {
        if ( raw == null ) {
            return "";
        } else {
            return raw.toString ();
        }
    }

    @SuppressWarnings ( "unused" )
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
    
    private String toCsvString(Boolean raw){
    	return raw.toString();
    }
    private String toCsvString(BigDecimal raw) {
    	return raw == null ? "" : raw.toString();
    }
    
    private String toCsvString(Date raw) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    	return raw == null ? "" : dateFormat.format(raw);
    }

    private void populateRow ( SXSSFWorkbook wbss, SXSSFRow row, Object... values ) {
        int cellindex = 0;
        for ( Object value: values ) {
            setCellValue ( wbss, row.createCell ( cellindex++ ), value );
        }
    }
    
    private void populateMainRowFinalPart ( SXSSFWorkbook wbss, SXSSFRow row, int mainRowIndex, Object... values ) {
        int cellindex = mainRowIndex;
        for ( Object value: values ) {
            setCellValue ( wbss, row.createCell ( cellindex++ ), value );
        }
    }
    
    private void populateSubRow ( SXSSFWorkbook wbss, SXSSFRow row, int subRowStartingIndex, Object... values ) {
        int cellindex = subRowStartingIndex;
        for ( Object value: values ) {
            setCellValue ( wbss, row.createCell ( cellindex++ ), value );
        }
    }

    private boolean falseIfNull ( Boolean b ) {
        if ( b == null ) {
            return false;
        } else {
            return b;
        }
    }

    private void setCellValue ( SXSSFWorkbook wbss, SXSSFCell cell, Object object ) {

        if ( null != object ) {
            if ( object instanceof Integer ) {
                cell.getCellStyle ()
                    .setDataFormat ( wbss.getCreationHelper ().createDataFormat ().getFormat ( "0" ) );
                cell.setCellValue ( (Integer) object );

            }
            if ( object instanceof Long ) {
                cell.getCellStyle ()
                    .setDataFormat ( wbss.getCreationHelper ().createDataFormat ().getFormat ( "0" ) );
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
                String value = (Boolean) object ? "Si" : "No";
                cell.setCellValue ( value );
            }
            if ( object instanceof BigDecimal ) {
                cell.setCellValue ( ( (BigDecimal) object ).doubleValue () );
            }
        }
    }
}
