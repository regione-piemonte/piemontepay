/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaybeapi.business.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import it.csi.epay.epaybeapi.business.SchedulerService;


/**
 * Servizio per la gestione delle operazioni schedulate
 */
@Service
public class SchedulerServiceImpl implements SchedulerService, InitializingBean {

    // private static final String EPAYBEAPI_MASTER_INSTANCE = "EPAYBEAPI_MASTER_INSTANCE";

    private final static Logger logger = LoggerFactory.getLogger ( SchedulerService.class );

    //    @Autowired
    //    private LockService lockService;

    //    private ResourceNegotiator masterInstanceLease;

    @Scheduled ( fixedDelay = 60 * 1000 )
    public void everyMinute () {
        // logger.info ( "executing scheduled operation every minute " );
    }

    @Scheduled ( cron = "0 30 1 * * ?" )
    public void everyNight () {
        logger.info ( "executing scheduled operation for the night" );
    }

    public Boolean getMasterLease () {
        // return masterInstanceLease != null && masterInstanceLease.getLeaseStatus ();
        return false;
    }

    @Override
    public void afterPropertiesSet () throws Exception {
        // masterInstanceLease = new ResourceNegotiator ( EPAYBEAPI_MASTER_INSTANCE, TimeUnit.MINUTES, 5, lockService );
    }

}
