/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.service.impl;

import it.csi.epay.epaypacatalogsrv.business.enums.EmailEnum;
import it.csi.epay.epaypacatalogsrv.business.enums.ParametriConfigurazione;
import it.csi.epay.epaypacatalogsrv.business.service.ConfigurazioneService;
import it.csi.epay.epaypacatalogsrv.business.service.InvioMailService;
import it.csi.epay.epaypacatalogsrv.business.service.PropagazioneExecutorService;
import it.csi.epay.epaypacatalogsrv.business.service.PropagazioneService;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.DettaglioErrorePropagazioneFruitorePerAssistenzaDTO;
import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneCommitDTO;
import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneDTO;
import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneDatiDTO;
import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneRollbackDTO;
import it.csi.epay.epaypacatalogsrv.dto.EsitoVerificaStatoServizioDTO;
import it.csi.epay.epaypacatalogsrv.dto.enums.AzioneDaPropagare;
import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoCommitFruitore;
import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoInvioFruitore;
import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoPropagazione;
import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoPropagazioneFruitore;
import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoRollbackFruitore;
import it.csi.epay.epaypacatalogsrv.dto.enums.OperazioneDaPropagare;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.LogTransazione;
import it.csi.epay.epaypacatalogsrv.model.Operazione;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.model.Servizio;
import it.csi.epay.epaypacatalogsrv.model.Sottoscrittore;
import it.csi.epay.epaypacatalogsrv.model.StoricoRiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.model.Transazione;
import it.csi.epay.epaypacatalogsrv.model.Utente;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.LogTransazioneRepository;
import it.csi.epay.epaypacatalogsrv.repository.OperazioneRepository;
import it.csi.epay.epaypacatalogsrv.repository.TransazioneRepository;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


@Component
public class PropagazioneServiceImpl implements PropagazioneService {

    private final Logger logger = LogManager.getLogger ( PropagazioneService.class );

    @Autowired
    protected EnteRepository enteRepository;

    @Autowired
    protected OperazioneRepository operazioneRepository;

    @Autowired
    protected TransazioneRepository transazioneRepository;

    @Autowired
    protected LogTransazioneRepository logTransazioneRepository;

    @Autowired
    protected PropagazioneExecutorService propagazioneExecutorService;

    @Autowired
    protected ConfigurazioneService configurazioneService;

    @Autowired
    protected InvioMailService invioMailService;

    private String ibanOriginale;
    private String bicOriginale;
    
    @Override
    @Transactional
    public EsitoPropagazioneDTO propagaEnte ( Ente corrente, AzioneDaPropagare azione, Long idTransazione, boolean doCommit, String ibanOriginale,String bicOriginale ) {

        this.ibanOriginale = ibanOriginale;
        this.bicOriginale = bicOriginale;
        
        if ( !isPropagazioneAbilitata () ) {
            logger.debug ( "la propagazione non e' abilitata" );
            return new EsitoPropagazioneDTO ( EsitoPropagazione.NONE, "Propagazione non abilitata" );
        }

        return propaga ( corrente, OperazioneDaPropagare.ENTE, azione, idTransazione, doCommit );
    }

    @Override
    @Transactional
    public EsitoPropagazioneDTO propagaCodiceVersamento ( CodiceVersamento corrente, AzioneDaPropagare azione, Long idTransazione, boolean doCommit ) {

        if ( !isPropagazioneAbilitata () ) {
            logger.debug ( "la propagazione non e' abilitata" );
            return new EsitoPropagazioneDTO ( EsitoPropagazione.NONE, "Propagazione non abilitata" );
        }

        return propaga ( corrente, OperazioneDaPropagare.CVERS, azione, idTransazione, doCommit );
    }

    @Override
    @Transactional
    public EsitoPropagazioneDTO propagaRiferimentoContabile ( RiferimentoContabile corrente, AzioneDaPropagare azione, Long idTransazione, boolean doCommit ) {

        if ( !isPropagazioneAbilitata () ) {
            logger.debug ( "la propagazione non e' abilitata" );
            return new EsitoPropagazioneDTO ( EsitoPropagazione.NONE, "Propagazione non abilitata" );
        }

        return propaga ( corrente, OperazioneDaPropagare.RIFCT, azione, idTransazione, doCommit );
    }

    private EsitoPropagazioneDTO propaga ( Object corrente, OperazioneDaPropagare codiceOperazione, AzioneDaPropagare azione, Long idTransazione,
        boolean doCommit ) {
        logger.debug ( "richiesta propagazione di azione " + azione + " per " + corrente );

        /*
         * FASE 0 : preparo la transazione
         */
        TransazioneBundle bundle = preparaTransazione ( codiceOperazione, azione, corrente, idTransazione, doCommit );

        if ( bundle == null ) {
            return new EsitoPropagazioneDTO ( EsitoPropagazione.NONE, "Nessuna propagazione necessaria" );
        }

        bundle.ibanOriginale = ibanOriginale;
        bundle.bicOriginale = bicOriginale;
        
        /*
         * ENTRY POINT
         */
        fase0verificaStatoServizi ( bundle );

        return bundle.esito;
    }

    private TransazioneBundle preparaTransazione ( OperazioneDaPropagare codiceOperazione, AzioneDaPropagare azione, Object oggettoDaPropagare,
        Long idTransazione, boolean doCommit ) {

        TransazioneBundle bundle = new TransazioneBundle ();

        bundle.oggettoDaPropagare = oggettoDaPropagare;
        bundle.azione = azione;

        /*
         * ottengo informazioni sull'operazione, sul servizio e sui sottoscrittori
         */
        bundle.operazione = operazioneRepository.findOneByCodice ( codiceOperazione.name () );

        if ( bundle.operazione == null ) {
            logger.error ( "Operazione non registrata: " + codiceOperazione.name () );
            throw new RuntimeException ( "Operazione non registrata: " + codiceOperazione.name () );
        }

        bundle.servizio = bundle.operazione.getServizio ();

        bundle.sottoscrittori = bundle.operazione.getSottoscrittori ();

        if ( bundle.sottoscrittori.size () < 1 ) {
            logger.warn ( "Nessun sottoscrittore registrato per l'operazione " + codiceOperazione );
            return null;
        }

        /*
         * ottengo la lista dei sottoscrittori ordinati per priorita'
         */
        bundle.sottoscrittori.sort ( new Comparator<Sottoscrittore> () {

            @Override
            public int compare ( Sottoscrittore o1, Sottoscrittore o2 ) {
                return ( o1.getPriorita () != null ? o1.getPriorita () : new Integer ( Integer.MAX_VALUE ) )
                    .compareTo ( ( o2.getPriorita () != null ? o2.getPriorita () : new Integer ( Integer.MAX_VALUE ) ) );
            }

        } );

        logger.debug (
            "avvio propagazione dell'operazione " + codiceOperazione + "-" + azione + " per servizio " + bundle.servizio.getDescrizione () + " a "
                + bundle.sottoscrittori.size () + " sottoscrittori" );

        /*
         * FASE 0 : Inizializzo la transazione e un record per ogni sottoscrittore in stato WAITING
         */
        if ( idTransazione == null ) {
            bundle.transazione = getNuovaTransazione ( bundle.operazione );
        } else {
            bundle.transazione = transazioneRepository.findOne ( idTransazione );
        }

        bundle.doCommit = doCommit;

        bundle.prefissoLog = "[TRANSAZIONE " + bundle.transazione.getIdTransazioneEsterna () + "] ";

        logger.debug (
            bundle.prefissoLog + "inizializzata la transazione " + bundle.transazione.getIdTransazioneEsterna () + " con id interno "
                + bundle.transazione.getId () + " in stato "
                + bundle.transazione.getEsito () );

        /*
         * Inizializzo un record per ogni sottoscrittore in stato WAITING
         */
        bundle.statoSottoscrittori = new ArrayList<> ();

        for ( Sottoscrittore sottoscrittore: bundle.sottoscrittori ) {
            LogTransazione statoSottoscrittore = getLogTransazione ( bundle.transazione, sottoscrittore );
            bundle.statoSottoscrittori.add ( statoSottoscrittore );
        }

        logStatoSottoscrittori ( "PHASE0_PREPARING", bundle );

        bundle.codiceEsito = EsitoPropagazione.PENDING;

        return bundle;
    }

    private void fase0verificaStatoServizi ( TransazioneBundle bundle ) {
        logStatoSottoscrittori ( "PHASE0_CHECK_SERVICE_STATUS", bundle );

        Integer numMaxThread = configurazioneService.getParametro ( ParametriConfigurazione.NUM_MAX_THREAD ).asInteger ( 5 );
        long tempoMaxAttesa = configurazioneService.getParametro ( ParametriConfigurazione.WSO2_CONNECTION_TIMEOUT ).asInteger ( 30 );

        ExecutorService pool = Executors.newFixedThreadPool ( Math.min ( numMaxThread, bundle.statoSottoscrittori.size () ) );

        List<Future<EsitoVerificaStatoServizioDTO>> futures = new ArrayList<> ();

        for ( LogTransazione statoSottoscrittore: bundle.statoSottoscrittori ) {
            futures.add ( execute ( statoSottoscrittore, bundle.servizio, pool ) );
        }

        pool.shutdown ();

        boolean statusCheckOk = true;

        try {
            pool.awaitTermination ( tempoMaxAttesa, TimeUnit.SECONDS );
        } catch ( InterruptedException e ) {
            // TIMEOUT
            statusCheckOk = false;
            bundle.riassuntoErroriPropagazione.add ( "Timeout durante la connessione preliminare ai servizi." );
        }

        // VERIFICA STATO
        if ( statusCheckOk ) {
            for ( Future<EsitoVerificaStatoServizioDTO> result: futures ) {
                if ( result.isDone () ) {
                    try {
                        if ( !result.get ().getOk () ) {
                            statusCheckOk = false;
                            bundle.riassuntoErroriPropagazione.add ( "Il sottoscrittore \"" + result.get ().getSottoscrittore ().getDescrizione () +
                                "\" non e' pronto alla ricezione dei dati: " + result.get ().getMessaggio () );
                        }
                    } catch ( InterruptedException | ExecutionException e ) {
                        logger.error ( "errore non previsto durante la verifica dello stato dei servizi", e );
                        bundle.riassuntoErroriPropagazione.add ( "errore non previsto durante la verifica: " + e.getMessage () );
                        statusCheckOk = false;
                    }

                } else {
                    statusCheckOk = false;
                }
            }
        }

        if ( statusCheckOk ) {
            fase1inviaDati ( bundle );
        } else {
            bundle.codiceEsito = EsitoPropagazione.KO;
            fase3termina ( bundle );
        }
    }

    public Future<EsitoVerificaStatoServizioDTO> execute ( final LogTransazione statoSottoscrittore, final Servizio servizio, ExecutorService pool ) {

        return pool.submit ( new Callable<EsitoVerificaStatoServizioDTO> () {

            @Override
            public EsitoVerificaStatoServizioDTO call () throws Exception {
                logger.debug ( String.format ( "Nuovo thread lanciato per la verifica dello stato del sottoscrittore %s",
                    statoSottoscrittore.getSottoscrittore ().getCodice () ) );

                EsitoVerificaStatoServizioDTO result = propagazioneExecutorService.testStatoServizio ( statoSottoscrittore.getSottoscrittore (), servizio );

                statoSottoscrittore.setMessaggio ( result.getMessaggio () );

                if ( !result.getOk () ) {
                    statoSottoscrittore.setEsito ( EsitoPropagazioneFruitore.UNAVAILABLE.name () );
                }

                return result;
            }
        } );
    }

    private void fase1inviaDati ( TransazioneBundle bundle ) {

        Servizio servizio = bundle.servizio;
        List<LogTransazione> statoSottoscrittori = bundle.statoSottoscrittori;

        logStatoSottoscrittori ( "PHASE1_SENDING_DATA", bundle );

        boolean fase1ok = true;

        for ( LogTransazione statoSottoscrittore: statoSottoscrittori ) {
            Sottoscrittore sottoscrittore = statoSottoscrittore.getSottoscrittore ();

            String endpoint = sottoscrittore.getIndirizzo () + servizio.getWsdl ();

            logger.debug ( bundle.prefissoLog + "avvio propagazione per sottoscrittore " + sottoscrittore.getDescrizione () + " all'endpoint " + endpoint );

            EsitoPropagazioneDatiDTO esitoObj;

            try {
                if ( bundle.oggettoDaPropagare instanceof Ente ) {
                    Ente enteNew = (Ente) bundle.oggettoDaPropagare;
                    
                    esitoObj = propagazioneExecutorService.propagaEnte (
                        bundle.transazione, sottoscrittore, bundle.servizio ,enteNew, bundle.azione, bundle.ibanOriginale, bundle.bicOriginale );
                } else if ( bundle.oggettoDaPropagare instanceof CodiceVersamento ) {
                    esitoObj = propagazioneExecutorService.propagaCodiceVersamento (
                        bundle.transazione, sottoscrittore, bundle.servizio, (CodiceVersamento) bundle.oggettoDaPropagare, bundle.azione );
                } else if ( bundle.oggettoDaPropagare instanceof RiferimentoContabile ) {
                    esitoObj = propagazioneExecutorService.propagaRiferimentoContabile (
                        bundle.transazione, sottoscrittore, bundle.servizio, (RiferimentoContabile) bundle.oggettoDaPropagare, bundle.azione );
                } else if ( bundle.oggettoDaPropagare instanceof StoricoRiferimentoContabile ) {
                    esitoObj = propagazioneExecutorService.propagaRiferimentoContabile (
                        bundle.transazione, sottoscrittore, bundle.servizio, (StoricoRiferimentoContabile) bundle.oggettoDaPropagare, bundle.azione );
                } else {
                    throw new RuntimeException ( "oggetto non propagabile: " + bundle.oggettoDaPropagare.getClass ().getName () );
                }
            } catch ( Exception e ) {
                esitoObj = new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.FAILED, "Errore imprevisto: " + e.getMessage () );
            }

            // aggiorno stato sottoscrittore
            statoSottoscrittore.setEsito ( esitoObj.getEsito ().name () );
            statoSottoscrittore.setMessaggio ( esitoObj.getMessaggio () );

            logger.debug ( bundle.prefissoLog + "esito della propagazione per il sottoscrittore " + sottoscrittore.getCodice () + " : "
                + esitoObj.getEsito ().name () + " - " + esitoObj.getMessaggio () );

            /*
             * ora puo' essere FAILED, REJECTED, COMMIT_PENDING,
             */

            boolean invioOk = false;

            if ( esitoObj.getEsito () == EsitoInvioFruitore.COMPLETED ) {
                // procedo con gli altri fruitori
                invioOk = true;
                statoSottoscrittore.setEsito ( EsitoPropagazioneFruitore.COMMIT_PENDING.name () );

            } else if ( esitoObj.getEsito () == EsitoInvioFruitore.FAILED ) {
                logger.debug ( bundle.prefissoLog + "tentativo di propagazione fallito" );
				bundle.riassuntoErroriPropagazione.add ( "L'invio dei dati al sottoscrittore \"" + sottoscrittore.getDescrizione () + "\" e' fallito: "
                    + esitoObj.getMessaggio () );
                statoSottoscrittore.setEsito ( EsitoPropagazioneFruitore.FAILED.name () );
                statoSottoscrittore.setMessaggio ( esitoObj.getMessaggio () );

            } else if ( esitoObj.getEsito () == EsitoInvioFruitore.REJECTED ) {
                logger.debug ( bundle.prefissoLog + "tentativo di propagazione rifiutato" );
				bundle.riassuntoErroriPropagazione.add ( "L'invio dei dati al sottoscrittore \"" + sottoscrittore.getDescrizione () + "\" e' fallito: "
                    + esitoObj.getMessaggio () );
                statoSottoscrittore.setEsito ( EsitoPropagazioneFruitore.REJECTED.name () );
                statoSottoscrittore.setMessaggio ( esitoObj.getMessaggio () );

            } else {
                // stato non atteso
                logger.error ( bundle.prefissoLog + "stato non atteso: " + esitoObj.getEsito () );
				bundle.messaggiPerAssistenza.add (
                    new DettaglioErrorePropagazioneFruitorePerAssistenzaDTO (
                        statoSottoscrittore.getSottoscrittore (), "stato non atteso durante la fase 1: " + esitoObj.getEsito (),
                        null ) );
                bundle.riassuntoErroriPropagazione
                    .add ( "L'invio dei dati al sottoscrittore \"" + sottoscrittore.getDescrizione () + "\" e' terminato in uno stato non riconosciuto" );

                statoSottoscrittore.setEsito ( EsitoPropagazioneFruitore.FAILED.name () );
                statoSottoscrittore.setMessaggio ( "terminato in uno stato non riconosciuto" );
            }

            if ( invioOk ) {
                logger.debug ( bundle.prefissoLog + "tentativo di propagazione riuscito" );
            } else {
                fase1ok = false;
                break;
            }

            logStatoSottoscrittori ( "PHASE1_SENDING_DATA", bundle );
        }

        logStatoSottoscrittori ( "PHASE1_SENDING_DATA_COMPLETED", bundle );

        if ( fase1ok ) {
            logger.debug ( bundle.prefissoLog + "la fase 1 della transazione e' terminata con successo" );
            fase2InviaCommit ( bundle );
        } else {
            logger.warn ( bundle.prefissoLog + "la fase 1 della transazione e' fallita" );
            fase1BinvioFruitoriFallito ( bundle );
        }
    }

    private void fase1BinvioFruitoriFallito ( TransazioneBundle bundle ) {
        List<LogTransazione> statoSottoscrittori = bundle.statoSottoscrittori;

        /*
         * Marco la transazione come globalmente FALLITA
         */
        bundle.codiceEsito = EsitoPropagazione.KO;

        logger.debug ( bundle.prefissoLog + "avvio la sequenza di rollback controllato" );

        logStatoSottoscrittori ( "PHASE1B_ROLLBACK", bundle );

        /*
         * Tento invio di rollback a fruitori che avevano gia' accettato il messaggio della fase 1 ora gli stati possibili sono: WAITING, FAILED, REJECTED,
         * COMMIT_PENDING,
         */
        for ( LogTransazione statoSottoscrittore: statoSottoscrittori ) {
            boolean tryRollback = false;

            if ( statoSottoscrittore.getEsito ().equals ( EsitoPropagazioneFruitore.WAITING.name () ) ) {
                /*
                 * la transazione non e' ancora stata inviata. non e' necessario eseguire il rollback
                 */

            } else if ( statoSottoscrittore.getEsito ().equals ( EsitoPropagazioneFruitore.FAILED.name () ) ) {
                /*
                 * la transazione e' fallita e probabilmente non ha raggiunto il fruitore. il fruitore non ha registrato la transazione, quindi non e' possibile
                 * inviare un rollback
                 */

            } else if ( statoSottoscrittore.getEsito ().equals ( EsitoPropagazioneFruitore.REJECTED.name () ) ) {
                /*
                 * la transazione e' stata attivamente rifiutata. il fruitore non ha registrato la transazione, quindi non e' possibile inviare un rollback
                 */

            } else if ( statoSottoscrittore.getEsito ().equals ( EsitoPropagazioneFruitore.COMMIT_PENDING.name () ) ) {
                /*
                 * la transazione e' stata accettata e il fruitore e' in attesa di commit. occorre inviare un rollback
                 */

                tryRollback = true;

            } else {
                logger.error ( bundle.prefissoLog + "stato non atteso: " + statoSottoscrittore.getEsito () );
                bundle.messaggiPerAssistenza.add ( new DettaglioErrorePropagazioneFruitorePerAssistenzaDTO ( statoSottoscrittore.getSottoscrittore (),
                    "stato non atteso durante la fase 1B: " + statoSottoscrittore.getEsito (), null ) );
            }

            if ( !tryRollback ) {
                logger.debug (
                    bundle.prefissoLog + "non e' necessaria alcuna azione per il sottoscrittore " + statoSottoscrittore.getSottoscrittore ().getCodice () +
                        " in stato " + statoSottoscrittore.getEsito () );
            } else {
                logger.debug (
                    bundle.prefissoLog + "tento il rollback per il sottoscrittore " + statoSottoscrittore.getSottoscrittore ().getCodice () + " in stato "
                        + statoSottoscrittore.getEsito () );

                EsitoPropagazioneRollbackDTO esitoObj;

                try {
                    esitoObj = propagazioneExecutorService.propagaRollback (
                        bundle.transazione, statoSottoscrittore.getSottoscrittore (), bundle.servizio );
                } catch ( Exception e ) {
                    esitoObj = new EsitoPropagazioneRollbackDTO ( EsitoRollbackFruitore.FAILED, "Errore imprevisto: " + e.getMessage () );
                }

                EsitoRollbackFruitore esitoRollback = esitoObj.getEsito ();

                logger.debug ( bundle.prefissoLog + "esito del rollback per il sottoscrittore " + statoSottoscrittore.getSottoscrittore ().getCodice ()
                    + " : " + esitoRollback.name () );

                boolean rollbackOk = true;

                if ( esitoRollback == EsitoRollbackFruitore.COMPLETED ) {
					statoSottoscrittore.setEsito ( EsitoPropagazioneFruitore.ROLLED_BACK.name () );
                    statoSottoscrittore.setMessaggio ( "Operazione annullata (rollback accettato)" );

                } else if ( esitoRollback == EsitoRollbackFruitore.FAILED ) {
                    rollbackOk = false;
                    statoSottoscrittore.setEsito ( EsitoPropagazioneFruitore.ROLLBACK_FAILED.name () );
                    statoSottoscrittore.setMessaggio ( "Errore durante il rollback: " + esitoObj.getMessaggio () );

                    bundle.messaggiPerAssistenza.add (
                        new DettaglioErrorePropagazioneFruitorePerAssistenzaDTO ( statoSottoscrittore.getSottoscrittore (),
                            "la transazione e' stata preparata ma non e' stata annullata perche' il fruitore non ha risposto alla richiesta di annullamento. " +
                                "E' necessario verificare che la transazione non sia rimasta aperta per il fruitore interessato.",
                            esitoObj.getMessaggio () ) );
                    bundle.necessitaAssitenza = true;

                } else if ( esitoRollback == EsitoRollbackFruitore.REJECTED ) {
                    rollbackOk = false;
                    statoSottoscrittore.setEsito ( EsitoPropagazioneFruitore.ROLLBACK_REJECTED.name () );
                    statoSottoscrittore.setMessaggio ( "Errore durante il rollback: " + esitoObj.getMessaggio () );

                    bundle.messaggiPerAssistenza.add (
                        new DettaglioErrorePropagazioneFruitorePerAssistenzaDTO ( statoSottoscrittore.getSottoscrittore (),
                            "la transazione e' stata preparata ma non e' stata annullata perche' il fruitore ha rifiutato la richiesta di annullamento. " +
                                "E' necessario verificare che la transazione non sia rimasta aperta per il fruitore interessato.",
                            esitoObj.getMessaggio () ) );
                    bundle.necessitaAssitenza = true;

                } else {
                    bundle.messaggiPerAssistenza.add (
                        new DettaglioErrorePropagazioneFruitorePerAssistenzaDTO ( statoSottoscrittore.getSottoscrittore (),
                            "esito rollback non atteso durante la fase 1B: " + esitoRollback, null ) );
                    bundle.necessitaAssitenza = true;

                    rollbackOk = false;
                    statoSottoscrittore.setEsito ( EsitoPropagazioneFruitore.ROLLBACK_FAILED.name () );
                    statoSottoscrittore.setMessaggio ( "Esito rollback non atteso durante la fase 1B: " + esitoRollback );
                }

                if ( rollbackOk ) {
                    logger.debug ( bundle.prefissoLog + "il rollback e' avvenuto correttamente" );
                } else {
                    logger.warn ( bundle.prefissoLog + "il rollback e' fallito. Sara' necessario un intervento dell'assistenza" );
                    bundle.necessitaAssitenza = true;
                }
            }

            logStatoSottoscrittori ( "PHASE1B_ROLLBACK", bundle );
        }

        logStatoSottoscrittori ( "PHASE1B_ROLLBACK_COMPLETED", bundle );

        fase3termina ( bundle );
    }

    private void fase2InviaCommit ( TransazioneBundle bundle ) {
        /*
         * Ora le transazioni sono tutte per forza in stato COMMIT_PENDING
         */

        if ( !bundle.doCommit ) {
            logStatoSottoscrittori ( "PHASE2_SKIPPING_COMMIT", bundle );
            fase2AinvioCommitSkippato ( bundle );
        }

        Servizio servizio = bundle.servizio;
        List<LogTransazione> statoSottoscrittori = bundle.statoSottoscrittori;

        logStatoSottoscrittori ( "PHASE2_SENDING_COMMIT", bundle );

        boolean fase2ok = true;

        for ( LogTransazione statoSottoscrittore: statoSottoscrittori ) {
            Sottoscrittore sottoscrittore = statoSottoscrittore.getSottoscrittore ();

            String endpoint = sottoscrittore.getIndirizzo () + servizio.getWsdl ();

            logger.debug ( bundle.prefissoLog + "avvio commit per sottoscrittore " + sottoscrittore.getDescrizione () + " all'endpoint " + endpoint );

            EsitoPropagazioneCommitDTO esitoObj;

            try {
                esitoObj = propagazioneExecutorService.propagaCommit (
                    bundle.transazione, statoSottoscrittore.getSottoscrittore (), bundle.servizio );
            } catch ( Exception e ) {
                esitoObj = new EsitoPropagazioneCommitDTO ( EsitoCommitFruitore.FAILED, "Errore imprevisto: " + e.getMessage () );
            }

            EsitoCommitFruitore esitoCommit = esitoObj.getEsito ();

            logger.debug ( bundle.prefissoLog + "esito del commit per il sottoscrittore " + sottoscrittore.getCodice () + " : " + esitoCommit );

            boolean commitOk;

            if ( esitoCommit == EsitoCommitFruitore.COMPLETED ) {
                commitOk = true;
                statoSottoscrittore.setEsito ( EsitoPropagazioneFruitore.COMMITTED.name () );
                statoSottoscrittore.setMessaggio ( esitoObj.getMessaggio () );

            } else if ( esitoCommit == EsitoCommitFruitore.FAILED ) {
                commitOk = false;
                statoSottoscrittore.setEsito ( EsitoPropagazioneFruitore.COMMIT_FAILED.name () );
                statoSottoscrittore.setMessaggio ( esitoObj.getMessaggio () );

                bundle.necessitaAssitenza = true;
                bundle.messaggiPerAssistenza.add (
                    new DettaglioErrorePropagazioneFruitorePerAssistenzaDTO ( statoSottoscrittore.getSottoscrittore (),
                        "la transazione non e' stata committata perche' il fruitore non ha risposto alla richiesta di commit. " +
                            "E' necessario correggere la situazione dati per il fruitore interessato.",
                        esitoObj.getMessaggio () ) );
			} else if ( esitoCommit == EsitoCommitFruitore.REJECTED ) {
                commitOk = false;
                statoSottoscrittore.setEsito ( EsitoPropagazioneFruitore.COMMIT_REJECTED.name () );
                statoSottoscrittore.setMessaggio ( esitoObj.getMessaggio () );

                bundle.necessitaAssitenza = true;
                bundle.messaggiPerAssistenza.add (
                    new DettaglioErrorePropagazioneFruitorePerAssistenzaDTO ( statoSottoscrittore.getSottoscrittore (),
                        "la transazione non e' stata committata perche' il fruitore ha rifiutato la richiesta di commit. " +
                            "E' necessario correggere la situazione dati per il fruitore interessato.",
                        esitoObj.getMessaggio () ) );

			} else {
                bundle.messaggiPerAssistenza.add (
                    new DettaglioErrorePropagazioneFruitorePerAssistenzaDTO ( statoSottoscrittore.getSottoscrittore (),
                        "esito commit non atteso durante la fase 2: " + esitoCommit, null ) );
                commitOk = false;
                statoSottoscrittore.setEsito ( EsitoPropagazioneFruitore.COMMIT_FAILED.name () );
                statoSottoscrittore.setMessaggio ( "esito non atteso: " + esitoCommit );
            }

            if ( commitOk ) {
                logger.debug ( bundle.prefissoLog + "il commit e' avvenuto correttamente" );
            } else {
                logger.warn ( bundle.prefissoLog + "il commit e' fallito per uno o piu'fruitori. Sara' necessario un intervento dell'assistenza" );
                fase2ok = false;
            }

            logStatoSottoscrittori ( "PHASE2_SENDING_COMMIT", bundle );
        }

        if ( fase2ok ) {
            fase2AinvioCommitRiuscito ( bundle );
        } else {
            fase2BinvioCommitFallito ( bundle );
        }
    }

    private void fase2AinvioCommitRiuscito ( TransazioneBundle bundle ) {
        /*
         * Marco la transazione come globalmente RIUSCITA
         */
        bundle.codiceEsito = EsitoPropagazione.OK;
        fase3termina ( bundle );
    }

    private void fase2AinvioCommitSkippato ( TransazioneBundle bundle ) {
        /*
         * Marco la transazione come globalmente RIUSCITA
         */
        bundle.codiceEsito = EsitoPropagazione.PENDING;
        fase3termina ( bundle );
    }

    private void fase2BinvioCommitFallito ( TransazioneBundle bundle ) {

        List<LogTransazione> statoSottoscrittori = bundle.statoSottoscrittori;
        /*
         * Marco la transazione come globalmente OK ma sara' necessario intervento dell'assistenza
         */
        bundle.codiceEsito = EsitoPropagazione.OK;

        // TODO ricordarsi di settare dove necessario: bundle.necessitaAssitenza = true;

        logger.debug ( bundle.prefissoLog + "avvio la sequenza di analisi post commit fallito" );

        logStatoSottoscrittori ( "PHASE2B_COMMIT_FAILED", bundle );

        /*
         * fruitori in stati possibili : COMMIT_PENDING, COMMIT_FAILED, COMMIT_REJECTED, COMMITTED
         */
        for ( LogTransazione statoSottoscrittore: statoSottoscrittori ) {

            if ( statoSottoscrittore.getEsito ().equals ( EsitoPropagazioneFruitore.COMMIT_FAILED.name () ) ) {
                
            } else if ( statoSottoscrittore.getEsito ().equals ( EsitoPropagazioneFruitore.COMMIT_REJECTED.name () ) ) {
                
            } else if ( statoSottoscrittore.getEsito ().equals ( EsitoPropagazioneFruitore.COMMITTED.name () ) ) {
                /*
                 * la transazione e' stata commitata correttamente
                 */

            } else {
                logger.error ( bundle.prefissoLog + "stato non atteso: " + statoSottoscrittore.getEsito () );
                bundle.messaggiPerAssistenza.add ( new DettaglioErrorePropagazioneFruitorePerAssistenzaDTO ( statoSottoscrittore.getSottoscrittore (),
                    "stato non atteso durante la fase 2B: " + statoSottoscrittore.getEsito (), null ) );
            }

        }

        fase3termina ( bundle );
    }

    private void fase3termina ( TransazioneBundle bundle ) {

        logStatoSottoscrittori ( "PHASE3_END", bundle );

        logger.debug ( bundle.prefissoLog + "finalizzo la transazione " + bundle.transazione.getIdTransazioneEsterna () );

        logger.debug (
            bundle.prefissoLog + "la transazione " + bundle.transazione.getIdTransazioneEsterna () + " si e' conclusa con esito " + bundle.codiceEsito );

        String messaggio;

        if ( bundle.codiceEsito == EsitoPropagazione.OK ) {
            messaggio = "Transazione completata con successo";

        } else if ( bundle.codiceEsito == EsitoPropagazione.KO ) {
            if ( bundle.riassuntoErroriPropagazione == null || bundle.riassuntoErroriPropagazione.size () < 1 ) {
                messaggio = "Transazione FALLITA";
            } else {
                messaggio = "";
                for ( String detail: bundle.riassuntoErroriPropagazione ) {
                    messaggio += detail + ";";
                }
                messaggio = messaggio.substring ( 0, messaggio.length () - 1 );
            }

        } else if ( bundle.codiceEsito == EsitoPropagazione.NONE ) {
            messaggio = "Transazione non avviata";

        } else if ( bundle.codiceEsito == EsitoPropagazione.PENDING ) {
            messaggio = "Transazione in sospeso";

        } else {
            messaggio = "Transazione in stato ignoto";

        }

        bundle.esito = new EsitoPropagazioneDTO ( bundle.codiceEsito, messaggio );

        bundle.transazione.setEsito ( bundle.codiceEsito.name () );

        try {
            propagazioneExecutorService.salvaStatoTransazione ( bundle.transazione,
                bundle.necessitaAssitenza ||
                    ( bundle.codiceEsito != EsitoPropagazione.OK && bundle.codiceEsito != EsitoPropagazione.PENDING ) ? bundle.statoSottoscrittori : null );

        } catch ( Exception e ) {
            // errore nel salvataggio locale. I fruitori hanno recepito le modifiche, il nodo locale no. Invio mail all'assistenza
            bundle.necessitaAssitenza = true;
            bundle.messaggiPerAssistenza.add (
                new DettaglioErrorePropagazioneFruitorePerAssistenzaDTO ( null,
                    "Si e' verificato un errore imprevisto nella chiusura della transazione locale: " + e.getMessage () +
                        "E' necessario applicare nuovamente la modifica richiesta al database locale.",
                    e.getMessage () ) );
        }

        logger.debug ( bundle.prefissoLog + "ESITO TRANSAZIONE: " + bundle.esito.getEsito () + " - " + bundle.esito.getMessaggio () );

        fase4verificaNecessitaAssistenza ( bundle );
    }

    private void fase4verificaNecessitaAssistenza ( TransazioneBundle bundle ) {
        if ( !bundle.necessitaAssitenza ) {
            logger.debug ( bundle.prefissoLog + "la transazione " + bundle.transazione.getIdTransazioneEsterna () + " non necessita di assistenza esterna." );

            return;
        }

        logger.warn ( bundle.prefissoLog + "la transazione " + bundle.transazione.getIdTransazioneEsterna () + " necessita di assistenza esterna." );

        logStatoSottoscrittori ( "PHASE4_REQUESTING_ASSISTANCE", bundle );

        inviaMailPerAssistenzaNecessaria ( bundle );
    }

    private void inviaMailPerAssistenzaNecessaria ( TransazioneBundle bundle ) {
        String titolo = "Intervento manuale necessario su";
        String descrizioneErrori = "";

        for ( DettaglioErrorePropagazioneFruitorePerAssistenzaDTO messaggio: bundle.messaggiPerAssistenza ) {
            titolo += messaggio.getSottoscrittore ().getDescrizione () + ", ";
        }
        titolo = titolo.substring ( 0, titolo.length () - 2 );

        for ( DettaglioErrorePropagazioneFruitorePerAssistenzaDTO messaggio: bundle.messaggiPerAssistenza ) {
            String sotto = "; <br /> il sottoscrittore ha riportato: " + messaggio.getMessaggioSottoscrittore ();
            
            if(StringUtils.isEmpty ( messaggio.getMessaggioSottoscrittore () )) sotto = "";
            
            descrizioneErrori += "[ "
                            + messaggio.getSottoscrittore ().getCodice () + " ] "
                            + messaggio.getSottoscrittore ().getDescrizione () + " : <br />" 
                            + messaggio.getMessaggio () + sotto + "<br /><br />";
        }

        Map<String, String> parametri = new HashMap<> ();

        parametri.put ( "titolo", titolo );
        parametri.put ( "descrizione_errori", descrizioneErrori );

        invioMailService.inviaMail (
            EmailEnum.ASSISTENZA_WSO2,
            InvioMailServiceImpl.DEFAULT_DESTINATARIO,
            SecurityUtils.getCurrentIdEnte (), parametri );
    }

    @Override
    public EsitoPropagazioneDTO propagaUtente ( Utente corrente, AzioneDaPropagare azione, Long idTransazione, boolean doCommit ) {

        if ( !isPropagazioneAbilitata () ) {
            logger.debug ( "la propagazione non e' abilitata" );
            return new EsitoPropagazioneDTO ( EsitoPropagazione.NONE, "Propagazione non abilitata" );
        }

        return nonNecessario ();
    }

    private EsitoPropagazioneDTO nonNecessario () {
		return new EsitoPropagazioneDTO ( EsitoPropagazione.NONE, "Propagazione non necessaria" );
    }

    protected Transazione getNuovaTransazione ( Operazione operazione ) {
        Transazione transazione = new Transazione ();

        transazione.setId ( null );
        transazione.setOperazione ( operazione );
        transazione.setEsito ( EsitoPropagazione.PENDING.name () );
        transazione.setData ( new Date () );
        transazione.setIdTransazioneEsterna ( UUID.randomUUID ().toString () );

        return transazione;
    }

    protected LogTransazione getLogTransazione ( Transazione t, Sottoscrittore s ) {
        LogTransazione logTransazione = new LogTransazione ();
        logTransazione.setTransazione ( t );
        logTransazione.setSottoscrittore ( s );
        logTransazione.setEsito ( EsitoPropagazioneFruitore.WAITING.name () );
        logTransazione.setMessaggio ( null );
        return logTransazione;
    }

    protected void logStatoSottoscrittori ( String codiceFase, TransazioneBundle bundle ) {
        List<LogTransazione> statoSottoscrittori = bundle.statoSottoscrittori;

        if ( statoSottoscrittori.size () < 1 ) {
            return;
        }

        Transazione t = statoSottoscrittori.get ( 0 ).getTransazione ();

        logger.debug ( bundle.prefissoLog + "la transazione " + t.getIdTransazioneEsterna () + " e' in fase " + codiceFase + " in stato " + bundle.codiceEsito );

        logger.debug ( bundle.prefissoLog + "stato dei sottoscrittori per la transazione " + t.getIdTransazioneEsterna () + " :" );

        for ( LogTransazione s: statoSottoscrittori ) {
            logger.debug (
                bundle.prefissoLog + "\t" + s.getSottoscrittore ().getCodice () + " - in stato " + s.getEsito ()
                    + ( s.getMessaggio () != null ? " - " + s.getMessaggio () : "" ) );
        }
    }

    @Override
    public Boolean isPropagazioneAbilitata () {
        return configurazioneService.getParametro ( ParametriConfigurazione.WSO2_ENABLE ).asBoolean ( false );
    }

    private static class TransazioneBundle {

        AzioneDaPropagare azione;

        Transazione transazione;

        Operazione operazione;

        Servizio servizio;

        List<Sottoscrittore> sottoscrittori;

        List<LogTransazione> statoSottoscrittori;

        List<DettaglioErrorePropagazioneFruitorePerAssistenzaDTO> messaggiPerAssistenza = new ArrayList<> ();

        List<String> riassuntoErroriPropagazione = new ArrayList<> ();

        Boolean necessitaAssitenza = false;

        EsitoPropagazione codiceEsito = EsitoPropagazione.PENDING;

        EsitoPropagazioneDTO esito = null;

        String prefissoLog;

        Object oggettoDaPropagare;

        Boolean doCommit;
        
        String ibanOriginale;
        String bicOriginale;
    }

    @Override
    public EsitoPropagazioneDTO propagaRiferimentoContabile ( StoricoRiferimentoContabile corrente, AzioneDaPropagare azione, Long idTransazione,
        boolean doCommit ) {

        if ( !isPropagazioneAbilitata () ) {
            logger.debug ( "la propagazione non e' abilitata" );
            return new EsitoPropagazioneDTO ( EsitoPropagazione.NONE, "Propagazione non abilitata" );
        }

        return propaga ( corrente, OperazioneDaPropagare.RIFCT, azione, idTransazione, doCommit );
    }

}
