/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.common.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.csi.epay.epaypacatalogweb.common.config.LogConfig;

@Aspect
public class BusinessAspect {

	private ObjectMapper om;
	
	protected Logger LOGGER = LoggerFactory.getLogger(LogConfig.HANDLER_ASPECT);

	@Around(value="execution(* it.csi.epay.epaypacatalogweb.business.manager.impl..*.*(..))", argNames = "joinPoint")
	public Object aroundManagerCall(ProceedingJoinPoint joinPoint) throws Throwable {
	
		String logStr = String.format("[manager] - %s (", joinPoint.getSignature().getName());
		Object result = null;
        logStr += "...)";
		
		LOGGER.info(logStr);
		
		try {
			result = joinPoint.proceed();
		}
		catch (Throwable e) {
			LOGGER.error("[manager] THROW - " + e, e);
			throw e;
		}
		
		return result;
	}
	
	@Around(value="execution(* it.csi.epay.epaypacatalogweb.business.facade..*.*(..))", argNames = "joinPoint")
	public Object aroundServiceCall(ProceedingJoinPoint joinPoint) throws Throwable {
	
		String logStr = String.format("[service] - %s (", joinPoint.getSignature().getName());
		Object result = null;

        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
        	logStr += serParam(args[i]) + ", ";
        }
        if (args.length > 0) {
        	logStr = logStr.substring(0,  logStr.length() - 2);
        }
        logStr += ")";
		
		LOGGER.info(logStr);
		
		try {
			result = joinPoint.proceed();
		}
		catch (Throwable e) {
			LOGGER.error("[service] THROW - " + e, e);
			throw e;
		}
		
		return result;
	}
	
	private String serParam(Object raw) {
		try {
			if (raw instanceof String) {
				return "\"" + (String) raw + "\"";
			}
			else if (raw instanceof Object) {
				if (om == null) om = new ObjectMapper();
				return om.writeValueAsString(raw);
			}
			else {
				return String.valueOf(raw);
			}
		}
		catch (Exception e) {
			return String.valueOf(raw);
		}
	}
	
}
