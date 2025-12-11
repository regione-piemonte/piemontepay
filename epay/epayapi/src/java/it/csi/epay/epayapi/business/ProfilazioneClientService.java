/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.business;

import it.csi.epay.epayapi.api.util.SpringApplicationContextHelper;
import it.csi.epay.epayapi.business.impl.ProfilazioneClientServiceImpl;
import it.csi.epay.epayapi.dto.security.ClientInfo;


/**
 * Servizio per la gestione della profilazione del client
 */
public interface ProfilazioneClientService {

    static ProfilazioneClientService getInstance () {
        return (ProfilazioneClientService) SpringApplicationContextHelper.getBean ( ProfilazioneClientServiceImpl.class );
    }

    /**
     * Carica le info di autenticazione per il client a partire da un header da authorization
     *
     * @param request la richiesta da cui estrapolare la firma digest
     * @return le informazioni sul client che ha firmato la richiesta
     */
    ClientInfo caricaClientInfo ( String authHeader );
}
