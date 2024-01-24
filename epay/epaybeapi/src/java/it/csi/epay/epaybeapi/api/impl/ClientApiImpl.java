/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.epay.epaybeapi.api.ClientApi;
import it.csi.epay.epaybeapi.api.util.SpringSupportedResource;
import it.csi.epay.epaybeapi.business.ClientService;
import it.csi.epay.epaybeapi.dto.request.ClientRegistrationRequest;
import it.csi.epay.epaybeapi.dto.request.ClientUpdateRequest;
import it.csi.epay.epaybeapi.dto.response.ClientRegistrationResponse;
import it.csi.epay.epaybeapi.dto.security.ClientInfoDTO;
import it.csi.epay.epaybeapi.integration.dto.EpayDChiamanteEsternoDTO;


/**
 * Risorsa RestEasy per il reperimento di informazioni sull'utente corrente.
 *
 * Nota: questa risorsa vale anche come esempio per l'introduzione al supporto di InitializingBean
 */
@SpringSupportedResource
public class ClientApiImpl implements ClientApi {

    private static Logger logger = LoggerFactory.getLogger ( ClientApi.class );

    @Autowired
    private ClientService clientService;

    @Override
    public ClientInfoDTO getCurrentClient () {
        logger.trace ( "getCurrentClient()" );

        ClientInfoDTO currentClient = clientService.getClientCorrenteDTO ();

        return currentClient;
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
