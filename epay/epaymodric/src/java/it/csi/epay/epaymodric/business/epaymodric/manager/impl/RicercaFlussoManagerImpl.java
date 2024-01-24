/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.epay.epaymodric.business.epaymodric.manager.AuditManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.EsitoManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.FlussoDettaglioManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.FlussoManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.FlussoOrigineManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.RicercaFlussoManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.FlussiUtilityWS;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaFlussoDettaglio;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaFlussoSintesi;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsEsito;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsFlussoDettaglio;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsFlussoSintesi;
import it.csi.epay.epaymodric.interfacews.epaymodric.consts.CostantiErrori;

/**
 * @author vsgro
 * Caso d'uso: 2.2.19
 */

@Component
public class RicercaFlussoManagerImpl implements RicercaFlussoManager {

    private final Logger logger = LogManager.getLogger (this.getClass());

    @Autowired
    private EsitoManager esitoManager;

    @Autowired
    FlussoOrigineManager flussoOrigineManager;

    @Autowired
    FlussoManager flussoManager;

    @Autowired
    FlussoDettaglioManager flussoDettaglioManager;
    
    @Autowired
    AuditManager auditManager;

    @Override
    public DTOOutputWsFlussoOrigine ricercaFlussoOrigine ( DTOInputWsRicercaFlussoOrigine dtoInputWsRicercaFlussoOrigine ) {
        DTOOutputWsFlussoOrigine outputWs = new DTOOutputWsFlussoOrigine();
        logger.info ( "RicercaFlussoServiceImpl.ricercaFlussoOrigine: INIZIO" );
        DTOOutputWsEsito esito = esitoManager.getEsito ( CostantiErrori.WS_ESITO_OK_DEFAULT );
        outputWs.setEsito ( esito );
        if ( dtoInputWsRicercaFlussoOrigine == null ) {
            esito = esitoManager.getEsito ( CostantiErrori.WS_ESITO_KO_NO_PARAM );
            outputWs.setEsito ( esito );
            outputWs.setFlussiOrigine (
                new LinkedList<> ( outputWs.getFlussiOrigine () ) );
        } else {

            if ( FlussiUtilityWS.isEmptyDTOInputWsRicercaFlussoOrigine ( dtoInputWsRicercaFlussoOrigine ) ) {
                esito = esitoManager.getEsito ( CostantiErrori.WS_ESITO_KO_IS_EMPTY );
                outputWs.setEsito ( esito );
            } else if ( !FlussiUtilityWS.isOkParamsDataElaborazione ( dtoInputWsRicercaFlussoOrigine ) ) {
                esito = esitoManager.getEsito ( CostantiErrori.WS_ESITO_KO_NO_PARAM, "Data Elaborazione e Identificativo Istituto Ricevente" );
                outputWs.setEsito ( esito );
            } else {
                outputWs = flussoOrigineManager.ricercaFlussoOrigine ( dtoInputWsRicercaFlussoOrigine );
                if ( outputWs.getEsito ()==null
                      && (outputWs.getFlussiOrigine () == null
                        || outputWs.getFlussiOrigine ().size () == 0 )
                    ) {
                    esito = esitoManager.getEsito ( CostantiErrori.WS_ESITO_OK_DEFAULT );
                    outputWs.setEsito ( esito );
                }
            }
        }

        logger.info ( "RicercaFlussoServiceImpl.ricercaFlussoOrigine: FINE" );
        return outputWs;
    }

    @Override
    public DTOOutputWsFlussoSintesi ricercaFlussiSintesi ( DTOInputWsRicercaFlussoSintesi dtoInputWsRicercaFlussoSintesi ) {
        DTOOutputWsFlussoSintesi outputWs = new DTOOutputWsFlussoSintesi();
        logger.info ( "RicercaFlussoServiceImpl.ricercaFlussiSintesi: INIZIO" );

        DTOOutputWsEsito esito = esitoManager.getEsito ( CostantiErrori.WS_ESITO_OK_DEFAULT );
        outputWs.setEsito ( esito );
        if ( dtoInputWsRicercaFlussoSintesi == null
            || dtoInputWsRicercaFlussoSintesi.getIdFlussoOrigine () == null ) {
            esito = esitoManager.getEsito ( CostantiErrori.WS_ESITO_KO_NO_PARAM );
            outputWs.setEsito ( esito );

            outputWs.setFlussiSintesi (
                new ArrayList<> ( outputWs.getFlussiSintesi () ) );
        } else {
            outputWs =
                flussoManager.ricercaFlussoSintesi (
                    Long.valueOf ( dtoInputWsRicercaFlussoSintesi.getIdFlussoOrigine () )
                );
            if ( outputWs.getEsito ()==null
                && (outputWs.getFlussiSintesi () == null
                  || outputWs.getFlussiSintesi ().size () == 0 )
              ) {
                esito = esitoManager.getEsito ( CostantiErrori.WS_ESITO_OK_DEFAULT );
                outputWs.setEsito ( esito );
            }
        }
        logger.info ( "RicercaFlussoServiceImpl.ricercaFlussiSintesi: FINE" );
        return outputWs;
    }

    @Override
    public DTOOutputWsFlussoDettaglio ricercaFlussiDettaglio (
        DTOInputWsRicercaFlussoDettaglio dtoInputWsRicercaFlussoDettaglio )
    {
        DTOOutputWsFlussoDettaglio outputWs = new DTOOutputWsFlussoDettaglio();
        logger.info ( "RicercaFlussoServiceImpl.ricercaFlussiDettaglio: INIZIO" );
        DTOOutputWsEsito esito = esitoManager.getEsito ( CostantiErrori.WS_ESITO_OK_DEFAULT );
        outputWs.setEsito ( esito );
        if ( dtoInputWsRicercaFlussoDettaglio == null
            || dtoInputWsRicercaFlussoDettaglio.getIdFlussoSintesi () == null ) {
            esito = esitoManager.getEsito ( CostantiErrori.WS_ESITO_KO_NO_PARAM );
            outputWs.setEsito ( esito );

            outputWs.setFlussiDettaglio (
                new ArrayList<> ( outputWs.getFlussiDettaglio () ) );
        } else {
        	
        	List<String> target= new ArrayList<String>();
        	target.add( "ID FLUSSO SINTESI: "+dtoInputWsRicercaFlussoDettaglio.getIdFlussoSintesi ());
        	auditManager.preExport("cbl_t_flusso_dettaglio",target);
            outputWs =
                flussoDettaglioManager.ricercaFlussiDettaglio (
                    Long.valueOf (dtoInputWsRicercaFlussoDettaglio.getIdFlussoSintesi () )
                );
            if ( outputWs.getEsito ()==null
                && (outputWs.getFlussiDettaglio () == null
                  || outputWs.getFlussiDettaglio ().size () == 0 )
              ) {
                esito = esitoManager.getEsito ( CostantiErrori.WS_ESITO_OK_DEFAULT );
                outputWs.setEsito ( esito );
            }
        }

        logger.info ( "RicercaFlussoServiceImpl.ricercaFlussiDettaglio: FINE" );
        return outputWs;
    }

}
