/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.api.impl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.epay.epayapi.api.ClientApi;
import it.csi.epay.epayapi.api.util.SpringSupportedResource;
import it.csi.epay.epayapi.business.ClientService;
import it.csi.epay.epayapi.dto.request.ClientRegistrationRequest;
import it.csi.epay.epayapi.dto.request.ClientUpdateRequest;
import it.csi.epay.epayapi.dto.response.ClientRegistrationResponse;
import it.csi.epay.epayapi.dto.security.ClientInfoDTO;
import it.csi.epay.epayapi.integration.dto.EpayDChiamanteEsternoDTO;


/**
 * Risorsa RestEasy per il reperimento di informazioni sull'utente corrente.
 *
 * Nota: questa risorsa vale anche come esempio per l'introduzione al supporto di InitializingBean
 */
@SpringSupportedResource
public class ClientApiImpl implements ClientApi {

    private static final Logger logger = LogManager.getLogger ( ClientApi.class );

    @Autowired
    private ClientService clientService;

    @Override
    public ClientInfoDTO getCurrentClient () {
        logger.trace ( "getCurrentClient()" );

		return clientService.getClientCorrenteDTO ();
    }

    @Override
    public ClientRegistrationResponse create ( ClientRegistrationRequest request ) {

        return clientService.registerClient ( request );
    }

    @Override
    public EpayDChiamanteEsternoDTO get ( String codice ) {

        return clientService.get ( codice );
    }

    @Override
    public EpayDChiamanteEsternoDTO update ( String codice, ClientUpdateRequest request ) {

        return clientService.update ( codice, request );
    }

    @Override
    public void delete ( String codice ) {

        clientService.delete ( codice );
    }

}
