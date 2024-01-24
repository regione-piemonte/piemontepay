/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.api.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.csi.epay.epaybeapi.api.MockNotificationPriceAdapterClient;
import it.csi.epay.epaybeapi.api.util.SpringSupportedResource;
import it.csi.epay.epaybeapi.dto.response.NotificationPriceOutput;


/**
 * Risorsa RestEasy per il reperimento di informazioni sull'utente corrente.
 *
 * Nota: questa risorsa vale anche come esempio per l'introduzione al supporto di InitializingBean
 */
@SpringSupportedResource
public class MockNotificationPriceAdapterClientImpl implements MockNotificationPriceAdapterClient {

    private static Logger logger = LoggerFactory.getLogger ( MockNotificationPriceAdapterClient.class );

    @Override
    public NotificationPriceOutput getMockNotificationPrice () {
        logger.info ( "START" );
            NotificationPriceOutput output = new NotificationPriceOutput ();
            double doub= Math.random ();
            output.setImportoTotale (new BigDecimal (doub*100).setScale ( 2, RoundingMode.HALF_DOWN ));
            output.setRequiresCostUpdate ( true );
            logger.info ( "END" );
        return output;
    }

    

}
