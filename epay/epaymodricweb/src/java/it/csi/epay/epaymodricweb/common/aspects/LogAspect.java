/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.common.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.csi.epay.epaymodricweb.common.config.LogConfig;

@Aspect
public class LogAspect {
	
	protected Logger LOGGER = LoggerFactory.getLogger(LogConfig.HANDLER_ASPECT);

	@Around(value="execution(* it.csi.epay.epaymodricweb..*.*(..))", argNames = "joinPoint")
	public Object aroundTask(ProceedingJoinPoint joinPoint) throws Throwable {
	
		if (!joinPoint.getSignature().toShortString().contains("HandlerInterceptor")) {
			LOGGER.info(String.format("[frontend] - %s", joinPoint.getSignature().toShortString()));
		}
		
		return joinPoint.proceed();
	}
	
}
