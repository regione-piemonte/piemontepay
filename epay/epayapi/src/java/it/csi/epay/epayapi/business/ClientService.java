/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.business;

import it.csi.epay.epayapi.api.util.SpringApplicationContextHelper;
import it.csi.epay.epayapi.business.impl.ClientServiceImpl;
import it.csi.epay.epayapi.dto.request.ClientRegistrationRequest;
import it.csi.epay.epayapi.dto.request.ClientUpdateRequest;
import it.csi.epay.epayapi.dto.response.ClientRegistrationResponse;
import it.csi.epay.epayapi.dto.security.ClientInfo;
import it.csi.epay.epayapi.dto.security.ClientInfoDTO;
import it.csi.epay.epayapi.integration.dto.EpayDChiamanteEsternoDTO;


/**
 * Servizio per la gestione del client
 */
public interface ClientService {

    /**
     * Ottiene informazioni sul client correntemente autenticato
     *
     * @return informazioni sul client corrente
     */
    ClientInfo getClientCorrente ();

    static ClientService getInstance () {
        return (ClientService) SpringApplicationContextHelper.getBean ( ClientServiceImpl.class );
    }

    /**
     * Ottiene informazioni sul client correntemente autenticato (DTO)
     *
     * @return DTO con informazioni sul client corrente
     */
    ClientInfoDTO getClientCorrenteDTO ();

    /**
     * Crea un nuovo client con le informazioni specificate
     *
     * @param request le informazioni anagrafiche del nuovo client
     * @return risultato della creazione con la passphrase assegnata (in chiaro)
     */
    ClientRegistrationResponse registerClient ( ClientRegistrationRequest request );

    /**
     * Ottiene informazioni sul fruitore specificato
     *
     * @param codice il codice del fruitore desiderato
     * @return informazioni sul fruitore desiderato
     */
    EpayDChiamanteEsternoDTO get ( String codice );

    /**
     * Aggiorna le informazioni del fruitore specificato
     *
     * @param codice il codice del fruitore desiderato
     * @param request le informazioni da aggiornare
     * @return informazioni sul fruitore aggiornato
     */
    EpayDChiamanteEsternoDTO update ( String codice, ClientUpdateRequest request );

    /**
     * Invalida il client corrispondente al codice specificato, valorizzandone la data fine validita'
     *
     * @param codice il codice del client
     */
    void delete ( String codice );
}
