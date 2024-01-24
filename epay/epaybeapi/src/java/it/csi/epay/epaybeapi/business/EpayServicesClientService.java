/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.business;

import it.csi.epay.epayservices.model.ComponentiImportoInput;
import it.csi.epay.epayservices.model.ComponentiImportoOutput;

/**
 * Servizio client per epayservices
 */
public interface EpayServicesClientService {

    /**
     * Testa la connessione agli EJB remoti
     */
    void test ();
    
    ComponentiImportoOutput estraiComponentiImporto ( ComponentiImportoInput input );
}
