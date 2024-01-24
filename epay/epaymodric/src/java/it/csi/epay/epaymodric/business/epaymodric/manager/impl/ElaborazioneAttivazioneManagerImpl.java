/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;

import it.csi.epay.epaymodric.business.epaymodric.bo.Configurazione;
import it.csi.epay.epaymodric.business.epaymodric.bo.Elaborazione;
import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.manager.ConfigurazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.ElaborazioneAttivazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.ElaborazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.FlussoOrigineManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.InvioMailManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.RiconciliazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte;
import it.csi.epay.epaymodric.business.epaymodric.repository.EnteRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.ErroreRepository;
import it.csi.epay.epaymodric.business.epaymodric.security.RequestContextUtils;
import it.csi.epay.epaymodric.business.epaymodric.utils.Costanti;
import it.csi.epay.epaymodric.business.epaymodric.utils.VerificheEnteUtil;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsElaborazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsMotoreDiRiconciliazione;
import it.csi.epay.epaymodric.interfacews.epaymodric.consts.CostantiErrori;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.EmailEnum;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.StatoElaborazioneEnum;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.EpaymodricException;


/**
 * @author vsgro
 */
@Service
public class ElaborazioneAttivazioneManagerImpl implements ElaborazioneAttivazioneManager {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @Autowired
    private ElaborazioneManager elaborazioneManager;

    @Autowired
    ErroreRepository erroreRepository;

    @Autowired
    private RiconciliazioneManager riconciliazioneManager;

    @Autowired
    private ConfigurazioneManager configurazioneManager;

    @Autowired
    private EnteRepository enteRepository;

    @Autowired
    private InvioMailManager invioMailManager;
    
    @Autowired
    private FlussoOrigineManager flussoOrigineManager;

    @Override
    @Async
    public void attivaElaborazione (List<String> statiDaEscludere) throws EpaymodricException {

        try {//recupero end point
            // recupero di tutti gli enti elaborabili per la giornata odierna

            List<CblTEnte> cblTEnteList = enteRepository.recuperaEntiElaborabili ();

            // leggo il parametro di configurazione NUM_MAX_THREAD
            Configurazione configurazione = configurazioneManager.leggiAttributoGenerale ( Costanti.CONFIG_ATTR_NUM_MAX_THREAD );

            // leggo il parametro di configurazione CONFIG_TEMPO_MAX_ATTESA
            Configurazione tempoMaxAttesaConfig = configurazioneManager.leggiAttributoGenerale ( Costanti.CONFIG_TEMPO_MAX_ATTESA );           

            int tempoMaxAttesa = Integer.MIN_VALUE;

            if ( null != tempoMaxAttesaConfig && StringUtils.hasText ( tempoMaxAttesaConfig.getValore () ) ) {
                try {
                    tempoMaxAttesa = Integer.valueOf ( tempoMaxAttesaConfig.getValore () );
                } catch ( NumberFormatException e ) {
                    throw new EpaymodricException ( "Errore durante la lettura del tempo massimo di attesa" );
                }
            } else {
                throw new EpaymodricException (
                                "Impossibile lanciare l'elaborazione: il parametro TEMPO_MAX_ATTESA non e' presente all'interno della tabella cbl_t_configurazione" );
            }

            int numMaxThread = Integer.MIN_VALUE;

            if ( null != configurazione && StringUtils.hasText ( configurazione.getValore () ) ) {
                try {
                    numMaxThread = Integer.valueOf ( configurazione.getValore () );
                } catch ( NumberFormatException e ) {
                    throw new EpaymodricException ( "Errore durante la lettura del numero massimo di thread lanciabili" );
                }
            } else {
                throw new EpaymodricException (
                                "Impossibile lanciare l'elaborazione: il parametro NUM_MAX_THREAD non e' presente all'interno della tabella cbl_t_configurazione" );
            }

            if ( !CollectionUtils.isEmpty ( cblTEnteList ) ) {

                List<CblTEnte> entiAttivabili = new ArrayList<CblTEnte> ();

                for ( CblTEnte ente: cblTEnteList ) {

                    if ( ente != null ) {

                        //determino se e' il giorno giusto per elaborare l'ente

                        boolean attivabile = false;

                        if ( logger.isDebugEnabled () ) {
                            logger.debug ( "ENTE IS " + ente.toString () );
                        }
                        Calendar cal = Calendar.getInstance ();
                        attivabile = VerificheEnteUtil.verificaEnteSchedulazione ( ente, cal );

                        boolean elaborazioniTerminateOggi = elaborazioneManager.esistonoElaborazioniTerminateOggi ( ente.getIdEnte () );

                        if ( elaborazioniTerminateOggi ) {
                            attivabile = false;
                            logger.info ( String.format ( "Sono presenti elaborazioni terminate oggi per l'ente %s, l'elaborazione non proseguira'",
                                ente.getCodiceFiscale () ) );
                        }

                        logger.info (
                            String.format ( "L'elaborazione per l'ente %s %s attivabile", ente.getCodiceFiscale (), ( attivabile ? " e' " : " non e' " ) ) );

                        if ( attivabile ) {
                            DTOInputWsElaborazione inElab = new DTOInputWsElaborazione ();
                            inElab.setCodiceFiscaleEnte ( ente.getCodiceFiscale () );
                            inElab.setStatoDaImpostare ( StatoElaborazioneEnum.SCHEDULATA.getCodice () );
                            elaborazioneManager.inserisciElaborazione ( inElab );

                            entiAttivabili.add ( ente );

                        }
                    }
                }

                if ( entiAttivabili.size () > 0 ) {
                    //ExecutorService pool = Executors.newFixedThreadPool ( numMaxThread );
                	ThreadPoolExecutor pool = new ThreadPoolExecutor(numMaxThread, numMaxThread, tempoMaxAttesa, TimeUnit.SECONDS, new ArrayBlockingQueue<>(entiAttivabili.size ()));

                    List<Future<DTOOutputWsMotoreDiRiconciliazione>> futures = new ArrayList<> ();

                    for ( CblTEnte ente: entiAttivabili ) {
                    	logger.debug ( "THREAD N " + futures.size() + " ATTIVATO X ENTE: " + ente.getDenominazione());                   	
                        
                    	//*****************************************************************************************************************************
                    	////ottimizzazione elaborazioni flussi SB2021 06
                    	//*****************************************************************************************************************************

                        // leggo i parametri di configurazione dell'ente
                        Map<String, Configurazione> configurazioneEnte = configurazioneManager.recuperaConfigurazionePerElaborazione ( ente.getIdEnte () );                    	
                    	Configurazione numeroMassimoFlussiPerPagina = configurazioneEnte.get ( Costanti.PAGE_SIZE_FLUSSI_ORIG );
                    	Integer pageSize = new Integer ( numeroMassimoFlussiPerPagina.getValore () );
                    	logger.info ( String.format ( "Numero massimo di flussi origine per pagina: %d", pageSize ) );

                    	//inizio a recuperare l'elaborazione
                    	List<Elaborazione> elaborazioniForzate = elaborazioneManager.recuperaElaborazioniForzate ( ente.getIdEnte () );
                    	//se nn ci sono elaborazioni forzate prendo eventuali elaborazioni normali
                    	if ( null == elaborazioniForzate || elaborazioniForzate.size () == 0 ) {                         
                    		Elaborazione elaborazione = elaborazioneManager.recuperaElaborazioneSchedulata ( ente.getIdEnte () );
                    		if (elaborazione!=null) elaborazioniForzate.add(elaborazione);
                        }else logger.info ( String.format ( "Nessuna elaborazione in stato schedulata per l'ente: %s", ente.getDenominazione () ) );
                    	
                    	//ciclo sulle elaborazioni/elaborazioni forzate
                    	for ( Elaborazione elaborazione: elaborazioniForzate ) {
	                    	long numFlussiDaElaborare 	= flussoOrigineManager.contaFlussiOrigineDaElaborare ( ente.getCodiceFiscale (), elaborazione.getListaFlussi (), statiDaEscludere );
	                    	logger.info ( String.format ( "Totale flussi: %d", numFlussiDaElaborare ) );
	                    	int numeroPagine = (int) ( numFlussiDaElaborare / pageSize ) + 1;	
	                    	logger.info ( String.format ( "Totale pagine: %d", numeroPagine ) );	
	                    	 List<FlussoOrigine> flussiOrigineOriginali= new ArrayList<FlussoOrigine>();
	                    	 List<FlussoOrigine> flussiElaborati= new ArrayList<FlussoOrigine>();
	                    	
     
	                    	for ( int i = 0; i < numeroPagine; i++ ) {	        
	                       		boolean firstPage=false;
	                    		boolean lastPage=false;	

	                    		if (i==0)firstPage=true;
	                    		if (i==numeroPagine-1)lastPage=true;
	                    		logger.info ( String.format ( "Inizio elaborazione pagina %d di %d", i + 1, numeroPagine ) );
	                    		//parte thread - parallelizzazione	               	                    		
	                    		futures.add ( execute ( ente, pool , statiDaEscludere, elaborazione,  i, pageSize, firstPage, lastPage,
	                    				flussiOrigineOriginali , flussiElaborati) );
	                    		 
	                    	}
                    	}// for elaborazioni
   	                   	
                    }

                    pool.shutdown ();                   

                    try {
                        pool.awaitTermination ( tempoMaxAttesa, TimeUnit.SECONDS );
                    } catch ( InterruptedException e ) {
                        String message = "Elaborazione multi ente in timeout";
                        
                        logger.debug ( "THREAD TOTALI " + futures.size());

                        throw new EpaymodricException ( message );
                    }

                    boolean elaborazioneTerminata = elaborazioneManager.isElaborazioneTerminataPerEnti ( cblTEnteList );

                    if ( !elaborazioneTerminata ) {
                        String message = "Elaborazione completata con errori. Esistono enti con elaborazione in stato SCHEDULATA";
                        throw new EpaymodricException ( message );
                    }
                }

            }
        } catch ( Exception e ) {
            logger.error ( "Errore durante l'elaborazione", e );
            invioMailManager.invioMail ( EmailEnum.ERRORE_ELABORAZIONE, CostantiErrori.ERRORE_DI_SISTEMA, null, null, e.getMessage () );
            throw new EpaymodricException ( e.getMessage () );
        }

    }
    


    public Future<DTOOutputWsMotoreDiRiconciliazione> execute ( final CblTEnte ente, ExecutorService pool, List<String> statiDaEscludere, Elaborazione elaborazione, 
    															int pageIdx, Integer pageSize, boolean firstPage, boolean lastPage 
    															, List<FlussoOrigine> flussiOrigineOriginali, List<FlussoOrigine> flussiElaborati) {

        RequestAttributes parentContext = RequestContextUtils.getThreadContext ();

        return pool.submit ( new Callable<DTOOutputWsMotoreDiRiconciliazione> () {
            @Override
            public DTOOutputWsMotoreDiRiconciliazione call () throws Exception {
                logger.info ( String.format ( "Nuovo thread lanciato per l'ente %s", ente.getIdEnte () ) );

                RequestContextUtils.setThreadContext ( parentContext );

                // leggo i parametri di configurazione dell'ente
                Map<String, Configurazione> configurazione=configurazioneManager.recuperaConfigurazionePerElaborazione ( ente.getIdEnte () );

                return elaboraEnte ( configurazione, ente, statiDaEscludere, elaborazione,  pageIdx, pageSize, firstPage, lastPage, 
                		flussiOrigineOriginali ,  flussiElaborati);
            }
        } );
    }
    
/**
 * 
 * @param configurazione
 * @param ente
 * @param statiDaEscludere
 * @param elaborazione
 * @param flussiOrigineDaElaborare
 * @return
 */
    private DTOOutputWsMotoreDiRiconciliazione elaboraEnte ( Map<String, Configurazione> configurazione, CblTEnte ente , List<String> statiDaEscludere,
    														Elaborazione elaborazione, int pageIdx, Integer pageSize, boolean firstPage, boolean lastPage
    														,List<FlussoOrigine> flussiOrigineOriginali, List<FlussoOrigine> flussiElaborati) {

        //elaboro prima tutti gli enti che hanno elaborazioni in stato forzata
//
//        List<Elaborazione> elaborazioniForzate = elaborazioneManager.recuperaElaborazioniForzate ( ente.getIdEnte () );
//        if ( null != elaborazioniForzate && elaborazioniForzate.size () > 0 ) {
//
//            for ( Elaborazione elaborazione: elaborazioniForzate ) {
//                riconciliazioneManager.eseguiRieseguiElaborazione ( configurazione, elaborazione, statiDaEscludere );
//            }
//
//        } else {
//            logger.info ( String.format ( "Nessuna elaborazione forzata per l'ente %s", ente.getDenominazione () ) );
//        }
//
//        Elaborazione elaborazione = elaborazioneManager.recuperaElaborazioneSchedulata ( ente.getIdEnte () );
//
//        if ( null != elaborazione ) {
//            //esegui / riesegui
//            riconciliazioneManager.eseguiRieseguiElaborazione ( configurazione, elaborazione, statiDaEscludere );
//        } else {
//            logger.info ( String.format ( "Nessuna elaborazione in stato schedulata per l'ente: %s", ente.getDenominazione () ) );
//        }
	    	
    	//ottimizzazione elaborazioni flussi SB2021 06
    	riconciliazioneManager.eseguiRieseguiElaborazione ( configurazione, elaborazione, statiDaEscludere, pageIdx, pageSize, firstPage, lastPage , 
    			flussiOrigineOriginali,  flussiElaborati );
	    return null;

    }

}
