/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.common.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import it.csi.epay.epaymodricweb.common.config.LogConfig;
import it.csi.epay.epaymodricweb.frontend.AuthenticatedParentController;

@Aspect
public class RoleAspect {
	
	protected Logger LOGGER = LoggerFactory.getLogger(LogConfig.HANDLER_ASPECT);

	private final static String REDIRECT_TO = "/ruoli/seleziona";
	
	@Around(value="execution(* it.csi.epay.epaymodricweb.frontend..*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)", argNames = "joinPoint")
	public Object aroundTask(ProceedingJoinPoint joinPoint) throws Throwable {

		Object returnObject;

		Object target = joinPoint.getTarget();
		if (!(target instanceof AuthenticatedParentController)) {
			
			Signature signature = joinPoint.getSignature();
			Class<?> returnType = ((MethodSignature) signature).getReturnType();
			if (returnType == String.class) {
				returnObject = "redirect:" + REDIRECT_TO;
			}
			else if (returnType == ModelAndView.class) {
				RedirectView rv = new RedirectView(REDIRECT_TO, true);
				rv.setExposeModelAttributes(false);
				returnObject = new ModelAndView(rv);
			}
			else {
				returnObject = "redirect:" + REDIRECT_TO;
			}
		}
		else {
			returnObject = joinPoint.proceed();
		}
		
		return returnObject;
	}
}
