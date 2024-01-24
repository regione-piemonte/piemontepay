/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

/**
 * 
 * 
 * @author Gueye
 *
 */
public class LogAuditActionAspectManager {
	Logger logger = LogManager.getLogger(this.getClass());
    
    @Around(value = "it.csi.epay.epaymodric.business.epaymodric.manager.impl.LogAuditManagerImpl.salvaLogAudit")
    public void logAroundSalvaLogAudit(ProceedingJoinPoint joinPoint) throws Throwable
    {
    	logger.info("****LogAuditActionAspectManager.salvaLogAudit() : " + joinPoint.getSignature().getName() + ": Before Method Execution");
        try {
            joinPoint.proceed();
        } finally {
            
        }
        logger.info("****LogAuditActionAspectManager.salvaLogAudit() : " + joinPoint.getSignature().getName() + ": After Method Execution");
    }
}
    


