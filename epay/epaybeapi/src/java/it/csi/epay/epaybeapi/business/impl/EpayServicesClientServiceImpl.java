/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaybeapi.business.EpayServicesClientService;
import it.csi.epay.epaybeapi.business.ProfilazioneClientService;
import it.csi.epay.epaybeapi.dto.monitoring.ServiceStatusDTO;
import it.csi.epay.epaybeapi.util.Monitorable;
import it.csi.epay.epayservices.interfaces.ejb.ComponentiImportoFacade;
import it.csi.epay.epayservices.interfaces.ejb.ConfigurazioneFacade;
import it.csi.epay.epayservices.interfaces.ejb.EnteFacade;
import it.csi.epay.epayservices.interfaces.ejb.ParametroFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.ComponentiImportoInput;
import it.csi.epay.epayservices.model.ComponentiImportoOutput;
import it.csi.epay.epayservices.model.Configurazione;
import it.csi.epay.epayservices.model.Ente;


/**
 * Implementazione del servizio client per epayservices
 */
@Service
@Transactional
public class EpayServicesClientServiceImpl implements EpayServicesClientService, Monitorable {

    private final static Logger logger = LoggerFactory.getLogger ( ProfilazioneClientService.class );

    @Autowired
    private ParametroFacade parametroFacade;

    @Autowired
    private EnteFacade enteFacade;

    @Autowired
    private ConfigurazioneFacade configurazioneFacade;
    
    @Autowired
    private ComponentiImportoFacade componentiImportoFacade;

    @Override
    public void test () {

        testParametroFacade ();
        testEnteFacade ();
        testConfigurazioneFacade ();

    }

    private String testParametroFacade () {
        String uuid = UUID.randomUUID ().toString ();
        String result = parametroFacade.test ( uuid );
        logger.info ( "test result = " + result );
        if ( ! ( result != null && result.startsWith ( " - value =" ) && result.contains ( uuid ) ) ) {
            throw new RuntimeException ( "Test Failed: unexpected result " + result );
        }
        return result;
    }

    private Ente testEnteFacade () {
        //test enteFacade
        List<Ente> ente = enteFacade.listaEntiConTasse ();
        if ( ente != null ) {
            return null;
        } else {
            throw new RuntimeException ( "Test Failed: not found" );
        }
    }

    private Configurazione testConfigurazioneFacade () {
        //test configurazioneFacade
        try {
            return configurazioneFacade.ricerca ( "", "" );
        } catch ( IllegalArgumentException e ) {
            throw new RuntimeException ( "Test Failed", e );
        } catch ( NoDataException e ) {
            throw new RuntimeException ( "Test Failed", e );
        }
    }

    @Override
    public ServiceStatusDTO checkStatus () {

        String name = "EpayServices EJB Client";
        List<Object> resultList = new ArrayList<> ();

        try {
            resultList.add ( testParametroFacade () );
            resultList.add ( testEnteFacade () );
            resultList.add ( testConfigurazioneFacade () );

            return ServiceStatusDTO.up ()
                .withName ( name )
                .withDetail ( "response", resultList ).build ();
        } catch ( Exception e ) {
            logger.error ( "Errore nel check dello stato del servizio", e );
            return ServiceStatusDTO.down ()
                .withName ( name )
                .withMessage ( "Errore nella verifica dello stato dei servizi" )
                .withDetail ( "exception", e )
                .withDetail ( "response", resultList )
                .build ();
        }

    }

	@Override
	public ComponentiImportoOutput estraiComponentiImporto(ComponentiImportoInput input) {
		return componentiImportoFacade.estraiComponentiImporto(input);
	}

}
