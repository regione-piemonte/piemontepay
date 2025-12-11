/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayapi.business.impl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import it.csi.epay.epayapi.business.SchedulerService;


/**
 * Servizio per la gestione delle operazioni schedulate
 */
@Service
public class SchedulerServiceImpl implements SchedulerService, InitializingBean {

    // private static final String EPAYAPI_MASTER_INSTANCE = "EPAYAPI_MASTER_INSTANCE";

    private final static Logger logger = LogManager.getLogger ( SchedulerService.class );

	@Scheduled ( fixedDelay = 60 * 1000 )
    public void everyMinute () {
        // logger.info ( "executing scheduled operation every minute " );
    }

    @Scheduled ( cron = "0 30 1 * * ?" )
    public void everyNight () {
        logger.info ( "executing scheduled operation for the night" );
    }

	@Override
    public void afterPropertiesSet () {
        // masterInstanceLease = new ResourceNegotiator ( EPAYAPI_MASTER_INSTANCE, TimeUnit.MINUTES, 5, lockService );
    }

}
