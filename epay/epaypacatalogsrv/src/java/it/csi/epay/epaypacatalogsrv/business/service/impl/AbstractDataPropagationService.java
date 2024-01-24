/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.util.CollectionUtils;

import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneDTO;
import it.csi.epay.epaypacatalogsrv.dto.LogTransazioneDTO;
import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoPropagazione;
import it.csi.epay.epaypacatalogsrv.model.LogTransazione;
import it.csi.epay.epaypacatalogsrv.model.Operazione;
import it.csi.epay.epaypacatalogsrv.model.Sottoscrittore;
import it.csi.epay.epaypacatalogsrv.model.Transazione;
import it.csi.epay.epaypacatalogsrv.repository.LogTransazioneRepository;
import it.csi.epay.epaypacatalogsrv.repository.OperazioneRepository;
import it.csi.epay.epaypacatalogsrv.repository.TransazioneRepository;


public abstract class AbstractDataPropagationService {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    protected OperazioneRepository operazioneRepository;

    protected TransazioneRepository transazioneRepository;

    protected LogTransazioneRepository logTransazioneRepository;

    protected Operazione operazione;

    public EsitoPropagazioneDTO executePropagate ( String operationCode ) {
        logger.debug ( "::executePropagate - START" );
        EsitoPropagazioneDTO esito = null;

        operazione = operazioneRepository.findOneByCodice ( operationCode );

        if ( operazione != null ) {
            if ( !CollectionUtils.isEmpty ( operazione.getSottoscrittori () ) ) {
                // Implementations will initialize the port and call the required operation for every sottoscrittore.
                esito = executePropagationLogic ();

                logger.debug ( "::salvaTransazione - START" );
                Long idTransazione = salvaTransazione ( esito );
                esito.setIdTransazione ( idTransazione );
                logger.debug ( "::salvaTransazione - END" );

                if ( esito.getEsito () == EsitoPropagazione.KO ) {
                    salvaLogTransazione ( idTransazione, esito.getEsitiSottoscrittori () );
                }
            } else {
                logger.error ( "::executePropagate - Attenzione! nessun sottoscrittore trovato per il servizio con operation code " + operationCode );
                esito = new EsitoPropagazioneDTO ( EsitoPropagazione.KO,
                    "Attenzione! nessun sottoscrittore trovato per il servizio con operation code " + operationCode );
            }
        } else {
            logger.error ( "::executePropagate - Attenzione! nessuna operazione trovata per l'operation code " + operationCode );
            esito = new EsitoPropagazioneDTO ( EsitoPropagazione.KO,
                "Attenzione! nessuna operazione trovata per l'operation code " + operationCode );
        }

        logger.debug ( "::executePropagate - END" );
        return esito;
    }

    /** Implement the "getXPort()" logic and call the required service(s) */
    protected abstract EsitoPropagazioneDTO executePropagationLogic ();

    private Long salvaTransazione ( EsitoPropagazioneDTO esito ) {
        Transazione transazione = new Transazione ();
        transazione.setOperazione ( operazione );
        transazione.setEsito ( esito.getEsito ().name () );
        transazione.setData ( new Date () );
        return transazioneRepository.save ( transazione ).getId ();
    }

    private void salvaLogTransazione ( Long idTransazione, List<LogTransazioneDTO> esitiSottoscrittore ) {
        logger.debug ( "::salvaLogTransazione - START" );
        if ( !CollectionUtils.isEmpty ( esitiSottoscrittore ) ) {
            for ( LogTransazioneDTO esito: esitiSottoscrittore ) {
                LogTransazione logTransazione = new LogTransazione ();
                logTransazione.setTransazione ( new Transazione ( idTransazione ) );
                logTransazione.setSottoscrittore ( new Sottoscrittore ( esito.getIdSottoscrittore () ) );
                logTransazione.setEsito ( esito.getEsito ().name () );
                logTransazione.setMessaggio ( esito.getMessaggio () );
                logTransazioneRepository.save ( logTransazione ).getId ();
            }
        }
        logger.debug ( "::salvaLogTransazione - END" );
    }

}
