/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.util.ArrayList;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaymodric.business.epaymodric.manager.EsitoManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.RicercaStatoFlussoManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.StatoFlussoManager;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOStatoFlusso;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaStatoFlusso;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsEsito;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsStatoFlusso;
import it.csi.epay.epaymodric.interfacews.epaymodric.consts.CostantiErrori;

/**
 * @author vsgro
 * Caso d'uso: 2.2.19
 */

@Component
@Transactional
public class RicercaStatoFlussoManagerImpl implements RicercaStatoFlussoManager {

    private final Logger logger = LogManager.getLogger (this.getClass());

    @Autowired
    private EsitoManager esitoManager;

    @Autowired
    private StatoFlussoManager statoFlussoManager;

    @Override
    public DTOOutputWsStatoFlusso ricercaStatoFlusso ( DTOInputWsRicercaStatoFlusso dtoInputWsRicercaStatoFlusso ) {
        DTOOutputWsStatoFlusso outputWs = new DTOOutputWsStatoFlusso();
        logger.info ( "RicercaStatoFlussoServiceImpl.ricercaStatoFlusso: INIZIO" );

        DTOOutputWsEsito esito = esitoManager.getEsito ( CostantiErrori.WS_ESITO_OK_DEFAULT );
        outputWs.setEsito ( esito );
        if (dtoInputWsRicercaStatoFlusso==null) {
            esito = esitoManager.getEsito ( CostantiErrori.WS_ESITO_KO_NO_PARAM );
            outputWs.setEsito ( esito );
            outputWs.setStatiFlusso (
                new ArrayList<> ( outputWs.getStatiFlusso () ) );
        } else {
            try {
                ArrayList<DTOStatoFlusso> statiFlusso = statoFlussoManager.ricercaStatiFlussi ( );
                outputWs.setStatiFlusso ( statiFlusso );
            } catch ( Exception e ) {
                logger.error ( "FlussoManagerImpl.RicercaStatoFlussoServiceImpl: errore " + e.getMessage () );
                esito = esitoManager.getEsito ( CostantiErrori.ERRORE_DI_SISTEMA );
                outputWs.setEsito ( esito );
            }

            if ( outputWs.getEsito ()==null
                && (outputWs.getStatiFlusso () == null
                  || outputWs.getStatiFlusso ().size () == 0 )
              ) {
                esito = esitoManager.getEsito ( CostantiErrori.WS_ESITO_OK_DEFAULT );
                outputWs.setEsito ( esito );
            }
        }
        logger.info ( "RicercaStatoFlussoServiceImpl.ricercaStatoFlusso: FINE" );
        return outputWs;
    }
}
