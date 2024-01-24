/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.audit;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component
public class CSILogAuditApplicationConfiguration implements ApplicationContextAware {

    public boolean getAuditEnabled () {
        return true;
    }

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext ( ApplicationContext applicationContext ) throws BeansException {
        CSILogAuditApplicationConfiguration.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext () {
        return applicationContext;
    }

}
