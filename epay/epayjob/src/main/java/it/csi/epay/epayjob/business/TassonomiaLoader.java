/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.csi.epay.epayjob.business.ComparatoreDiListeDiverse.ComparatoreDiElementiDiversi;
import it.csi.epay.epayjob.business.ComparatoreDiListeDiverse.DifferenzaFraListeDiverse;
import it.csi.epay.epayjob.model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class TassonomiaLoader extends JobTassonomia {

    private static final String CONFIG_PROPERTIES = "config.properties";

    private Properties properties;

    private String urlGetAllTassonomie;

    private String urlAggiornaTassonomie;

    private String urlPagopaJson;

    private String pathFileJsonToLoad;

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    private static final String USER = "SYSTEM";

    public TassonomiaLoader ( JobContext jobContext ) {
        super ();
        this.jobContext = jobContext;
        final String methodName = "constructor";
        try {
            jobContext.infoStart ( methodName );

            InputStream inputStream = this.getClass ().getClassLoader ().getResourceAsStream ( CONFIG_PROPERTIES );
            properties = new Properties ();
            properties.load ( inputStream );

            urlGetAllTassonomie = properties.getProperty ( "service.tassonomia.get.all.endpoint" );
            urlAggiornaTassonomie = properties.getProperty ( "service.tassonomia.update.endpoint" );
            urlPagopaJson = properties.getProperty("service.tassonomia.pagopa.endpoint");
            pathFileJsonToLoad = properties.getProperty("service.tassonomia.path.file.json");

            if ( properties.isEmpty () ) {
                jobContext.debug ( methodName, "File di properties non trovato. L'elenco e' vuoto!" );
            }

        } catch ( Throwable t ) {
            jobContext.error ( methodName, "Errore nel metodo " + methodName, t );
        } finally {
            jobContext.infoEnd ( methodName );
        }
    }

    public void execute () throws Throwable {
        final String methodName = "execute";
        jobContext.infoStart ( methodName );
        try {
            doExecute ();
        } catch ( Throwable t ) {
            jobContext.error ( methodName, "Errore nell'esecuzione del Job", t );
            throw t;
        } finally {
            jobContext.infoEnd ( methodName );
        }
    }

    private void doExecute () throws IOException {
        final String methodName = "doExecute";
        final Date startTime = new Date ();
        jobContext.info ( methodName, "avvio elaborazione: " + startTime );
        // SCARICO IL JSON DA PAGOPA
        File initialFile = new File(pathFileJsonToLoad);
        InputStream inputStream = new FileInputStream(initialFile);
        // LO CONVERTO IN LINKEDLIST
        ObjectMapper objectMapper = new ObjectMapper ();
        TassonomiaModel [] tassonomiaModelArray = objectMapper.readValue ( inputStream, TassonomiaModel [].class );
        LinkedList<TassonomiaModel> listFromPAGOPA = new LinkedList<> ( Arrays.asList ( tassonomiaModelArray ) );
        if ( listFromPAGOPA.isEmpty () ) {
            jobContext.error ( "Error on reading PAGOPA repository, or the dataset is empty" );
        } else {
            doExecuteHelper ( listFromPAGOPA );
        }
    }

    @SuppressWarnings ( "unlikely-arg-type" )
    private void doExecuteHelper ( LinkedList<TassonomiaModel> fromPAGOPAlist ) throws IOException {
        // CHIAMO IL SERVIZIO PER LEGGERE LA TABELLA DELLA TASSONOMIA E LA SALVO IN MEMORIA
        String basicAuth = properties.getProperty ( "service.tassonomia.auth" );
        InputStream inputStream = doPostToRestService ( urlGetAllTassonomie, basicAuth, null );
        ObjectMapper objectMapper = new ObjectMapper ();
        RicercaTassonomiaOutput serviceResponse = objectMapper.readValue ( inputStream, RicercaTassonomiaOutput.class );
        // LOGICA DI CONTROLLI PRINCIPALE
        if ( serviceResponse.getCodiceStato () == 200 ) {
            // questa e' la lista da mandare al servizio di update/insert/delete logico
            LinkedList<TassonomiaOutputDto> listaOutput = new LinkedList<> ();
            List<TassonomiaOutputDto> fromDBlist = serviceResponse.getRisultati ();
            fromPAGOPAlist.stream ().forEach ( fromPAGOPA -> {
                if ( fromDBlist.contains ( fromPAGOPA ) ) {
                    TassonomiaOutputDto matched = fromDBlist.get ( fromDBlist.indexOf ( fromPAGOPA ) );
                    // se esiste controllare il numero di versione della tassonomia
                    if ( Integer.parseInt ( fromPAGOPA.getVersioneTassonomia () ) > Integer.parseInt ( matched.getnVersioneTassonomia () ) ) {
                        // se e' strettamente maggiore aggiorno
                        listaOutput.add ( createUpdateRecord ( fromPAGOPA, matched ) );
                    } else {
                        // altrimenti scarto
                        jobContext.info ( "Record discarded, version outdated: " + fromPAGOPA );
                    }
                } else {
                    // se non esiste, insert
                    listaOutput.add ( createInsertRecord ( fromPAGOPA ) );
                }
            } );
            // setto da cancellare i record su db che non erano presenti nel json di pagopa
            DifferenzaFraListeDiverse<TassonomiaModel, TassonomiaOutputDto> esitoComparazioneListe
            = ComparatoreDiListeDiverse.compara ( fromPAGOPAlist, fromDBlist,
                new ComparatoreDiElementiDiversi<TassonomiaModel, TassonomiaOutputDto> () {

                @Override
                public boolean compara ( TassonomiaModel fromPagoPa, TassonomiaOutputDto fromDB ) {
                    return match ( fromPagoPa, fromDB );
                }

                private boolean match ( TassonomiaModel fromPagoPa, TassonomiaOutputDto fromDB ) {
                    return ( fromPagoPa.getCodiceTipoEnteCreditore ().equalsIgnoreCase ( fromDB.getCodTipoEnteCreditore () ) &&
                                    fromPagoPa.getCodiceTipologiaServizio ().equalsIgnoreCase ( fromDB.getCodTipologiaServizio () ) &&
                                    fromPagoPa.getProgressivoMacroAreaPerEnteCreditore ().equalsIgnoreCase ( fromDB.getMacroArea () ) );
                }

            } );
            List<TassonomiaOutputDto> toBeDeleted = esitoComparazioneListe.getElementiSoloNellaSeconda ();
            toBeDeleted.forEach ( x -> {
                x.setToBeDelete ( true );
                x.setFlagCancellato ( true );
                x.setDataCancellazione ( new Date () );
            } );
            listaOutput.addAll ( toBeDeleted );
            // richiamo il servizio per insert/update/delete
            sendDataToServiceToUpdateTassonomiaTable ( listaOutput );
        } else {
            jobContext.error ( "The web service answer code is:" + serviceResponse.getCodiceStato () );
        }
    }

    private TassonomiaOutputDto createInsertRecord ( TassonomiaModel fromPAGOPA ) {
        TassonomiaOutputDto dto = new TassonomiaOutputDto ();
        dto.setToBeInsert ( true );
        dto.setFlagAggiornato ( false );
        dto.setUtenteInserimento ( USER );
        dto.setDataInserimento ( new Date () );
        dto.setCodTipoEnteCreditore ( fromPAGOPA.getCodiceTipoEnteCreditore () );
        dto.setTipoEnteCreditore ( fromPAGOPA.getTipoEnteCreditore () );
        dto.setMacroArea ( fromPAGOPA.getProgressivoMacroAreaPerEnteCreditore () );
        dto.setNomeMacroArea ( fromPAGOPA.getNomeMacroArea () );
        dto.setDescrMacroArea ( fromPAGOPA.getDescrizioneMacroArea () );
        dto.setCodTipologiaServizio ( fromPAGOPA.getCodiceTipologiaServizio () );
        dto.setTipoServizio ( fromPAGOPA.getTipoServizio () );
        dto.setMotivoGiuridicoRiscossione ( fromPAGOPA.getMotivoGiuridicoDellaRiscossione () );
        dto.setDescrTipoServizio ( fromPAGOPA.getDescrizioneTipoServizio () );
        dto.setnVersioneTassonomia ( fromPAGOPA.getVersioneTassonomia () );
        dto.setDatiSpecificiIncasso ( fromPAGOPA.getDatiSpecificiDiIncasso () );
        Date date;
        try {
            date = new SimpleDateFormat ( DATE_FORMAT ).parse ( fromPAGOPA.getDataInizioValidita () );
            dto.setDataInizioValidita ( date );
        } catch ( ParseException e ) {
            String message = "Error on parsing date DataInizioValidita from PAGOPA: " + fromPAGOPA;
            jobContext.error ( message, e);
            jobContext.error ( "Errore in fase di parsificazione", e);
            throw new RuntimeException ( message );
        }
        try {
            date = new SimpleDateFormat ( DATE_FORMAT ).parse ( fromPAGOPA.getDataFineValidita () );
            dto.setDataFineValidita ( date );
        } catch ( ParseException e ) {
            String message = "Error on parsing date DataFineValidita from PAGOPA: " + fromPAGOPA;
            jobContext.error ( message, e );
            jobContext.error ( e.getMessage () );
            throw new RuntimeException ( message );
        }
        return dto;
    }

    private TassonomiaOutputDto createUpdateRecord ( TassonomiaModel fromPAGOPA, TassonomiaOutputDto dto ) {
        boolean setFlag = false;
        dto.setToBeUpdate ( true );
        if ( !dto.getTipoEnteCreditore ().equalsIgnoreCase ( fromPAGOPA.getTipoEnteCreditore () ) ) {
            dto.setTipoEnteCreditore ( fromPAGOPA.getTipoEnteCreditore () );
        }
        if ( !dto.getNomeMacroArea ().equalsIgnoreCase ( fromPAGOPA.getNomeMacroArea () ) ) {
            dto.setNomeMacroArea ( fromPAGOPA.getNomeMacroArea () );
        }
        if ( !dto.getDescrMacroArea ().equalsIgnoreCase ( fromPAGOPA.getDescrizioneMacroArea () ) ) {
            dto.setDescrMacroArea ( fromPAGOPA.getDescrizioneMacroArea () );
        }
        if ( !dto.getTipoServizio ().equalsIgnoreCase ( fromPAGOPA.getTipoServizio () ) ) {
            dto.setTipoServizio ( fromPAGOPA.getTipoServizio () );
            setFlag = true;
        }
        if ( !dto.getMotivoGiuridicoRiscossione ().equalsIgnoreCase ( fromPAGOPA.getMotivoGiuridicoDellaRiscossione () ) ) {
            dto.setMotivoGiuridicoRiscossione ( fromPAGOPA.getMotivoGiuridicoDellaRiscossione () );
        }
        if ( !dto.getDescrTipoServizio ().equalsIgnoreCase ( fromPAGOPA.getDescrizioneTipoServizio () ) ) {
            dto.setDescrTipoServizio ( fromPAGOPA.getDescrizioneTipoServizio () );
        }
        if ( !dto.getDatiSpecificiIncasso ().equalsIgnoreCase ( fromPAGOPA.getDatiSpecificiDiIncasso () ) ) {
            dto.setDatiSpecificiIncasso ( fromPAGOPA.getDatiSpecificiDiIncasso () );
            setFlag = true;
        }
        Date date;
        try {
            date = new SimpleDateFormat ( DATE_FORMAT ).parse ( fromPAGOPA.getDataInizioValidita () );
        } catch ( ParseException e ) {
            String message = "Error on parsing date DataInizioValidita from PAGOPA: " + fromPAGOPA;
            jobContext.error ( message, e );
            jobContext.error ( e.getMessage () );
            throw new RuntimeException ( message );
        }
        if ( dto.getDataInizioValidita ().after ( date ) || dto.getDataInizioValidita ().before ( date ) ) {
            dto.setDataInizioValidita ( date );
            setFlag = true;
        }
        try {
            date = new SimpleDateFormat ( DATE_FORMAT ).parse ( fromPAGOPA.getDataFineValidita () );
        } catch ( ParseException e ) {
            String message = "Error on parsing date DataFineValidita from PAGOPA: " + fromPAGOPA;
            jobContext.error ( message, e );
            jobContext.error ( e.getMessage () );
            throw new RuntimeException ( message );
        }
        if ( dto.getDataFineValidita ().after ( date ) || dto.getDataFineValidita ().before ( date ) ) {
            dto.setDataFineValidita ( date );
            setFlag = true;
        }

        if ( setFlag ) {
            dto.setFlagAggiornato ( true );
            dto.setUtenteModifica ( USER );
            dto.setDataModifica ( new Date () );
        }

        return dto;
    }

    private void sendDataToServiceToUpdateTassonomiaTable ( LinkedList<TassonomiaOutputDto> list ) throws IOException {
        String basicAuth = properties.getProperty ( "service.tassonomia.auth" );
        TassonomiaUpdateInputDto tassonomiaUpdateInputDto = new TassonomiaUpdateInputDto ();
        tassonomiaUpdateInputDto.setTassonomie ( list );
        InputStream inputStream = doPostToRestService ( urlAggiornaTassonomie, basicAuth, tassonomiaUpdateInputDto );
        ObjectMapper objectMapper = new ObjectMapper ();
        UpdateTassonomieOutput serviceResponse = objectMapper.readValue ( inputStream, UpdateTassonomieOutput.class );

        if ( serviceResponse.getCodiceStato () == 200 ) {
            jobContext.info ( "Update delle Tassonomie eseguita con successo" );
        } else {
            jobContext.error ( "The web service (update one) answer code is:" + serviceResponse.getCodiceStato () );
        }
    }
}
