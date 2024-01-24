/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.api.impl;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import it.csi.epay.epaybeapi.api.StatoPosizioneDebitoriaApi;
import it.csi.epay.epaybeapi.api.util.SpringSupportedResource;
import it.csi.epay.epaybeapi.business.StatoPosizioneDebitoriaService;
import it.csi.epay.epayservices.model.StatoPosizioneDebitoriaInput;
import it.csi.epay.epayservices.model.StatoPosizioneDebitoriaOutput;


/**
 * Risorsa RestEasy che fornisce le implementazioni dei servizi che agiscono sui componenti dell'importo del pagamento.
 *
 * Nota: questa risorsa vale anche come esempio per l'introduzione al supporto di InitializingBean
 */
@SpringSupportedResource
public class StatoPosizioneDebitoriaApiImpl implements StatoPosizioneDebitoriaApi {

    private static Logger logger = LoggerFactory.getLogger ( StatoPosizioneDebitoriaApiImpl.class );

    @Autowired
    private StatoPosizioneDebitoriaService service;
    

    @Override
    public Response getStatoPosizioneDebitoria ( String organization, String paymentType, String iuv ) {
        logger.info ( "Try to call the service" );
        
        StatoPosizioneDebitoriaOutput result = service.getStatoPosizioneDebitoria ( new StatoPosizioneDebitoriaInput (organization, paymentType, iuv) );
        
        logger.info ( "Returning response" );
        
        
        return Response
                        .status(HttpStatus.OK.value()) 
                    .status ( result != null && result.getResult ().getCode ().equals ( "000" ) ? 200
                                    : result.getResult ().getCode ().equals ( "400" ) ? 400 : 500 )
                    .entity ( result ).build ();
    }
    

   

    
}
