/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import it.csi.epay.epaymodricweb.common.exceptions.FileUploadException;
import it.csi.epay.epaymodricweb.model.provvisorio.ErroreUploadVO;
import it.csi.epay.epaymodricweb.model.provvisorio.ProvvisorioUploadVO;
import it.csi.epay.epaymodricweb.model.provvisorio.UploadFileVO;


public class XLSUtils {

    public static UploadFileVO mapFileToVO ( MultipartFile fileContent ) {
        Assert.notNull ( fileContent, "File non presente!" );
        UploadFileVO uploadFileVO = new UploadFileVO ();
        List<ProvvisorioUploadVO> inputList = new ArrayList<> ();
        uploadFileVO.setProvvisori ( inputList );
        InputStream stream = null;
        XSSFWorkbook workbook = null;
        List<ErroreUploadVO> erroriList = new ArrayList<ErroreUploadVO> ();
        try {
            stream = fileContent.getInputStream ();
            workbook = new XSSFWorkbook ( stream );
            XSSFSheet sheet = workbook.getSheetAt ( 0 );

            Iterator<Row> rowIterator = sheet.iterator ();

            while ( rowIterator.hasNext () ) {
                try {
                    Row row = rowIterator.next ();
                    //Skip header
                    if ( row.getRowNum () == 0 )
                        continue;
                    inputList.add ( mapLineToVO ( row ) );
                } catch ( FileUploadException e ) {
                    erroriList.addAll ( e.getErrori () );
                }
            }
        } catch ( Exception reason ) {
            reason.printStackTrace ();
        } finally {
            if ( workbook != null )
                try {
                    workbook.close ();
                } catch ( IOException e ) {
                    // TODO Auto-generated catch block
                    e.printStackTrace ();
                }
            if ( stream != null )
                try {
                    stream.close ();
                } catch ( IOException e ) {
                    // TODO Auto-generated catch block
                    e.printStackTrace ();
                }
        }
        uploadFileVO.setErroriList ( erroriList );
        return uploadFileVO;
    }

    private static ProvvisorioUploadVO mapLineToVO ( Row row ) throws FileUploadException {
        //        Assert.isTrue ( row.getLastCellNum () == 7, "La linea deve contenere esattamente 7 campi!" );

        List<ErroreUploadVO> erroriList = new ArrayList<ErroreUploadVO> ();
        //1
        String identificativoFlusso = null;
        ErroreUploadVO erroreIdentificativoFlusso = new ErroreUploadVO ( row.getRowNum (), 1, "Identificativo Flusso" );
        if ( null != row.getCell ( 0 ) ) {
            if ( row.getCell ( 0 ).getCellTypeEnum () == CellType.STRING ) {
                identificativoFlusso = row.getCell ( 0 ).getStringCellValue ();
                if ( !StringUtils.hasText ( identificativoFlusso ) ) {
                    erroreIdentificativoFlusso.setErrore ( "Non Valorizzato" );
                } else if ( identificativoFlusso.length () > 35 ) {
                    erroreIdentificativoFlusso.setErrore ( "Contiene piu' di 35 caratteri" );
                }
            } else {
                erroreIdentificativoFlusso.setErrore ( "Non e' di tipo stringa" );
            }
        } else {
            erroreIdentificativoFlusso.setErrore ( "Non Valorizzato" );
        }
        if ( StringUtils.hasText ( erroreIdentificativoFlusso.getErrore () ) ) {
            erroriList.add ( erroreIdentificativoFlusso );
        }
        //2
        Date dataMovimento = null;
        ErroreUploadVO erroreDataMovimento = new ErroreUploadVO ( row.getRowNum (), 2, "Data Movimento" );
        if ( null != row.getCell ( 1 ) ) {
            if ( row.getCell ( 1 ).getCellTypeEnum () == CellType.NUMERIC && DateUtil.isCellDateFormatted ( row.getCell ( 1 ) ) ) {
                dataMovimento = row.getCell ( 1 ).getDateCellValue ();
                if ( null == dataMovimento ) {
                    erroreDataMovimento.setErrore ( "Formato data non valido. Usare Formato GG/MM/AAAA" );
                }
            } else {
                erroreDataMovimento.setErrore ( "Non e' di tipo data" );
            }
        } else {
            erroreDataMovimento.setErrore ( "Non Valorizzato" );
        }
        if ( StringUtils.hasText ( erroreDataMovimento.getErrore () ) ) {
            erroriList.add ( erroreDataMovimento );
        }
        //3
        Integer annoEsercizio = null;
        ErroreUploadVO erroreAnnoEsercizio = new ErroreUploadVO ( row.getRowNum (), 3, "Anno Esercizio" );
        if ( null != row.getCell ( 2 ) ) {
            if ( row.getCell ( 2 ).getCellTypeEnum () == CellType.NUMERIC && !DateUtil.isCellDateFormatted ( row.getCell ( 2 ) ) ) {
                annoEsercizio = (int) row.getCell ( 2 ).getNumericCellValue ();
                if ( null == annoEsercizio || Integer.valueOf ( 0 ).equals ( annoEsercizio ) ) {
                    erroreAnnoEsercizio.setErrore ( "Non Valorizzato" );
                }
            } else {
                erroreAnnoEsercizio.setErrore ( "Non e' di tipo numerico" );
            }
        } else {
            erroreAnnoEsercizio.setErrore ( "Non Valorizzato" );
        }
        if ( StringUtils.hasText ( erroreAnnoEsercizio.getErrore () ) ) {
            erroriList.add ( erroreAnnoEsercizio );
        }
        //4
        Integer numeroProvvisorio = null;
        ErroreUploadVO erroreNumeroProvvisorio = new ErroreUploadVO ( row.getRowNum (), 4, "Numero Provvisorio" );
        if ( null != row.getCell ( 3 ) ) {
            if ( row.getCell ( 3 ).getCellTypeEnum () == CellType.NUMERIC ) {
                if ( DateUtil.isCellDateFormatted ( row.getCell ( 3 ) ) ) {
                    erroreNumeroProvvisorio.setErrore ( "Non e' di tipo numerico" );
                } else {
                    numeroProvvisorio = (int) row.getCell ( 3 ).getNumericCellValue ();
                }
            } else {
                erroreNumeroProvvisorio.setErrore ( "Non e' di tipo numerico" );
            }
        } else {
            erroreNumeroProvvisorio.setErrore ( "Non Valorizzato" );
        }
        if ( StringUtils.hasText ( erroreNumeroProvvisorio.getErrore () ) ) {
            erroriList.add ( erroreNumeroProvvisorio );
        }
        //5
        Integer annoProvvisorio = null;
        ErroreUploadVO erroreAnnoProvvisorio = new ErroreUploadVO ( row.getRowNum (), 5, "Anno Provvisorio" );

        if ( null != row.getCell ( 4 ) ) {
            if ( row.getCell ( 4 ).getCellTypeEnum () == CellType.NUMERIC ) {
                if ( DateUtil.isCellDateFormatted ( row.getCell ( 4 ) ) ) {
                    erroreAnnoProvvisorio.setErrore ( "Non e' di tipo numerico" );
                } else {
                    annoProvvisorio = (int) row.getCell ( 4 ).getNumericCellValue ();
                    if ( null == annoProvvisorio || annoProvvisorio == 0 ) {
                        erroreAnnoProvvisorio.setErrore ( "Non Valorizzato" );
                    }
                }
            } else {
                erroreAnnoProvvisorio.setErrore ( "Non e' di tipo numerico" );
            }
        } else {
            erroreAnnoProvvisorio.setErrore ( "Non Valorizzato" );
        }
        if ( StringUtils.hasText ( erroreAnnoProvvisorio.getErrore () ) ) {
            erroriList.add ( erroreAnnoProvvisorio );
        }
        //6
        BigDecimal importoProvvisorio = null;
        ErroreUploadVO erroreImportoProvvisorio = new ErroreUploadVO ( row.getRowNum (), 6, "Importo Provvisorio" );
        if ( null != row.getCell ( 5 ) ) {
            if ( row.getCell ( 5 ).getCellTypeEnum () == CellType.NUMERIC && !DateUtil.isCellDateFormatted ( row.getCell ( 5 ) ) ) {
                double aus = row.getCell ( 5 ).getNumericCellValue ();
                if ( aus == 0 ) {
                    erroreImportoProvvisorio.setErrore ( "Non Valorizzato Correttamente" );
                } else {
                    importoProvvisorio = BigDecimal.valueOf ( row.getCell ( 5 ).getNumericCellValue () );
                }
            } else {
                erroreImportoProvvisorio.setErrore ( "Non e' di tipo numerico" );
            }
        } else {
            erroreImportoProvvisorio.setErrore ( "Non Valorizzato" );
        }
        if ( StringUtils.hasText ( erroreImportoProvvisorio.getErrore () ) ) {
            erroriList.add ( erroreImportoProvvisorio );
        }
        //7
        String descrizione = null;
        ErroreUploadVO erroreDescrizione = new ErroreUploadVO ( row.getRowNum (), 7, "Descrizione" );
        if ( null != row.getCell ( 6 ) ) {
            if ( row.getCell ( 6 ).getCellTypeEnum () == CellType.STRING ) {
                descrizione = row.getCell ( 6 ).getStringCellValue ();
                if ( !StringUtils.hasText ( descrizione ) ) {
                    erroreDescrizione.setErrore ( "Non Valorizzato" );
                }
            } else {
                erroreDescrizione.setErrore ( "Non e' di tipo stringa." );
            }
        } else {
            erroreDescrizione.setErrore ( "Non Valorizzato" );
        }
        if ( StringUtils.hasText ( erroreDescrizione.getErrore () ) ) {
            erroriList.add ( erroreDescrizione );
        }
        //se ci sono errori di validazione interrompo.
        if ( erroriList.size () > 0 ) {
            throw new FileUploadException ( erroriList );
        }

        ProvvisorioUploadVO provvisorioUploadVO = new ProvvisorioUploadVO ();

        provvisorioUploadVO.setIdentificativoFlusso ( identificativoFlusso );
        provvisorioUploadVO.setDataMovimento ( dataMovimento );
        provvisorioUploadVO.setAnnoEsercizio ( annoEsercizio );
        provvisorioUploadVO.setNumeroProvvisorio ( numeroProvvisorio );
        provvisorioUploadVO.setAnnoProvvisorio ( annoProvvisorio );
        provvisorioUploadVO.setImportoProvvisorio ( importoProvvisorio );
        provvisorioUploadVO.setDescrizione ( descrizione );
        return provvisorioUploadVO;
    }

}
