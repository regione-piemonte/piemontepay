/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import it.csi.epay.epaymodricweb.common.exceptions.FileUploadException;
import it.csi.epay.epaymodricweb.model.provvisorio.ErroreUploadVO;
import it.csi.epay.epaymodricweb.model.provvisorio.ProvvisorioUploadVO;
import it.csi.epay.epaymodricweb.model.provvisorio.UploadFileVO;


public class CSVUtils {

    private static final char DEFAULT_SEPARATOR = ',';

    private static final String DEFAULT_SEPARATOR_SEMICOLON = ";";

    public static void writeLine ( Writer w, List<String> values ) throws IOException {
        writeLine ( w, values, DEFAULT_SEPARATOR, ' ' );
    }

    public static void writeLine ( Writer w, List<String> values, char separators ) throws IOException {
        writeLine ( w, values, separators, ' ' );
    }

    private static String followCVSformat ( String value ) {

        String result = value;
        if ( result.contains ( "\"" ) ) {
            result = result.replace ( "\"", "\"\"" );
        }
        return result;

    }

    public static void writeLine ( Writer w, List<String> values, char separators, char customQuote ) throws IOException {

        boolean first = true;

        if ( separators == ' ' ) {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder ();
        for ( String value: values ) {
            if ( !first ) {
                sb.append ( separators );
            }
            if ( customQuote == ' ' ) {
                sb.append ( followCVSformat ( value ) );
            } else {
                sb.append ( customQuote ).append ( followCVSformat ( value ) ).append ( customQuote );
            }

            first = false;
        }
        sb.append ( "\n" );
        w.append ( sb.toString () );

    }

    public static UploadFileVO mapFileToVO ( MultipartFile fileContent ) throws Exception {
        Assert.notNull ( fileContent, "File non presente!" );
        UploadFileVO uploadCSVVO = new UploadFileVO ();
        List<ProvvisorioUploadVO> inputList = new ArrayList<> ();
        uploadCSVVO.setProvvisori ( inputList );
        int rowNumber = 0;
        List<ErroreUploadVO> erroriList = new ArrayList<> ();
        try ( BufferedReader br = new BufferedReader ( new InputStreamReader ( fileContent.getInputStream () ) ) ) {
            String line;
            boolean firstLine = true;
            while ( ( line = br.readLine () ) != null ) {
                if ( !firstLine ) {
                    if ( StringUtils.hasText ( line ) ) {
                        String [] column = line.split ( DEFAULT_SEPARATOR_SEMICOLON );
                        if ( null != column && column.length > 0 ) {
                            try {
                                ProvvisorioUploadVO provvisorioUploadVO = mapLineToVO ( rowNumber, column );
                                if ( null != provvisorioUploadVO ) {
                                    inputList.add ( provvisorioUploadVO );
                                }
                            } catch ( FileUploadException e ) {
                                erroriList.addAll ( e.getErrori () );
                            }
                        }
                    }
                } else {
                    firstLine = !firstLine;
                }
                rowNumber++;
            }
        } catch ( IOException e ) {
            throw new Exception ( e );
        }
        uploadCSVVO.setErroriList ( erroriList );
        return uploadCSVVO;
    }

    private static ProvvisorioUploadVO mapLineToVO ( int rowNumber, String [] line ) throws FileUploadException {

        List<ErroreUploadVO> erroriList = new ArrayList<> ();
        ProvvisorioUploadVO provvisorioUploadVO = new ProvvisorioUploadVO ();
        //ignoro le linee vuote.
        if ( line == null || ( line.length != 7 && line.length > 1 ) ) {
            erroriList.add ( new ErroreUploadVO ( rowNumber, "La riga deve contenere 7 elementi" ) );
        } else {
            //1
            String identificativoFlusso = line [0];
            ErroreUploadVO erroreIdentificativoFlusso = new ErroreUploadVO ( rowNumber, 1, "Identificativo Flusso" );
            if ( !StringUtils.hasText ( identificativoFlusso ) ) {
                erroreIdentificativoFlusso.setErrore ( "Non valorizzato" );
            } else if ( identificativoFlusso.length () > 35 ) {
                erroreIdentificativoFlusso.setErrore ( "Contiene piu' di 35 caratteri" );
            }
            if ( StringUtils.hasText ( erroreIdentificativoFlusso.getErrore () ) ) {
                erroriList.add ( erroreIdentificativoFlusso );
            }
            //2
            Date dataMovimento = DateUtils.getDateFromString ( line [1] );
            ErroreUploadVO erroreDataMovimento = new ErroreUploadVO ( rowNumber, 2, "Data Movimento" );
            if ( null == dataMovimento ) {
                erroreDataMovimento.setErrore ( "Formato data non valido. Usare Formato GG/MM/AAAA" );
            }
            if ( StringUtils.hasText ( erroreDataMovimento.getErrore () ) ) {
                erroriList.add ( erroreDataMovimento );
            }
            //3
            String annoEsercizio = line [2];
            Integer annoEsercuzioInt = null;
            ErroreUploadVO erroreAnnoEsercizio = new ErroreUploadVO ( rowNumber, 3, "Anno Esercizio" );
            if ( !StringUtils.hasText ( annoEsercizio ) ) {
                erroreAnnoEsercizio.setErrore ( "Non e' specificato" );
            } else if ( !org.apache.commons.lang.StringUtils.isNumeric ( annoEsercizio ) ) {
                erroreAnnoEsercizio.setErrore ( "Non e' numerico" );
            }
            if ( StringUtils.hasText ( erroreAnnoEsercizio.getErrore () ) ) {
                erroriList.add ( erroreAnnoEsercizio );
            }else {
                annoEsercuzioInt = Integer.valueOf ( annoEsercizio );
            }
            //4
            String numeroProvvisorio = line [3];
            Integer numeroProvvisorioInt = null;
            ErroreUploadVO erroreNumeroProvvisorio = new ErroreUploadVO ( rowNumber, 4, "Numero Provvisorio" );
            if ( !StringUtils.hasText ( numeroProvvisorio ) ) {
                erroreNumeroProvvisorio.setErrore ( "Non e' specificato" );
            } else if ( !org.apache.commons.lang.StringUtils.isNumeric ( numeroProvvisorio ) ) {
                erroreNumeroProvvisorio.setErrore ( "Non e' numerico" );
            }
            if ( StringUtils.hasText ( erroreNumeroProvvisorio.getErrore () ) ) {
                erroriList.add ( erroreNumeroProvvisorio );
            }else {
                numeroProvvisorioInt = Integer.valueOf ( numeroProvvisorio );
            }
            //5
            String annoProvvisorio = line [4];
            Integer annoProvvisorioInt = null;
            ErroreUploadVO erroreAnnoProvvisorio = new ErroreUploadVO ( rowNumber, 5, "Anno Provvisorio" );
            if ( !StringUtils.hasText ( annoProvvisorio ) ) {
                erroreAnnoProvvisorio.setErrore ( "Non e' specificato" );
            } else if ( !org.apache.commons.lang.StringUtils.isNumeric ( annoProvvisorio ) ) {
                erroreAnnoProvvisorio.setErrore ( "Non e' numerico" );
            }
            if ( StringUtils.hasText ( erroreAnnoProvvisorio.getErrore () ) ) {
                erroriList.add ( erroreAnnoProvvisorio );
            } else {
                annoProvvisorioInt = Integer.valueOf ( annoProvvisorio );
            }
            //6
            BigDecimal importoProvvisorio = convertFromString ( line [5] );
            ErroreUploadVO erroreImportoProvvisorio = new ErroreUploadVO ( rowNumber, 6, "Importo Provvisorio" );
            if ( null == importoProvvisorio ) {
                erroreImportoProvvisorio.setErrore ( "Non e' valorizzato correttamente" );
            }
            if ( StringUtils.hasText ( erroreImportoProvvisorio.getErrore () ) ) {
                erroriList.add ( erroreImportoProvvisorio );
            }
            //7
            String descrizione = line [6];
            ErroreUploadVO erroreDescrizione = new ErroreUploadVO ( rowNumber, 7, "Descrizione" );
            if ( !StringUtils.hasText ( descrizione ) ) {
                erroreDescrizione.setErrore ( "Non e' valorizzato" );
            }
            if ( StringUtils.hasText ( erroreDescrizione.getErrore () ) ) {
                erroriList.add ( erroreDescrizione );
            }
            provvisorioUploadVO.setIdentificativoFlusso ( identificativoFlusso );
            provvisorioUploadVO.setDataMovimento ( dataMovimento );
            provvisorioUploadVO.setAnnoEsercizio ( annoEsercuzioInt );
            provvisorioUploadVO.setNumeroProvvisorio ( numeroProvvisorioInt );
            provvisorioUploadVO.setAnnoProvvisorio ( annoProvvisorioInt );
            provvisorioUploadVO.setImportoProvvisorio ( importoProvvisorio );
            provvisorioUploadVO.setDescrizione ( descrizione );
        }
        //se ci sono errori di validazione interrompo.
        if ( erroriList.size () > 0 ) {
            throw new FileUploadException ( erroriList );
        }
        return provvisorioUploadVO;
    }

    private static BigDecimal convertFromString ( String str ) {
        String importo = StringUtils.hasText ( str ) ? str.replaceAll ( ",", "." ) : null;
        try {
            return StringUtils.hasText ( importo ) ? new BigDecimal ( importo ) : null;
        } catch ( Exception e ) {
            return null;
        }
    }
}
