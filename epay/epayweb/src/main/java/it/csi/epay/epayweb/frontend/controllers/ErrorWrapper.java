/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.controllers;
/*
 *  https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc#using-controlleradvice-classes * 
 */


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import it.csi.epay.epayweb.business.messaggi.LevelMessage;

@ControllerAdvice
public class ErrorWrapper extends _Controller {
	public static final String DEFAULT_ERROR_VIEW = "home_manutenzione/home";

	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(Exception e) {
		final String methodName = "defaultErrorHandler";
		ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);
		mav.addObject("levelMessage", LevelMessage.WARNING.getCssAlertClass());
		mav.addObject("message", "Si e' verificato un problema. Riprovare piu' tardi. Se il problema persiste, contattare l'assistenza.");
		if (e instanceof RuntimeException) {
			e.printStackTrace(System.err);
		}
		log.error(methodName, ExceptionUtils.getStackTrace(e));
		return mav;
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleError404(HttpServletRequest request, Exception e)   {
		ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);
		mav.addObject("levelMessage", LevelMessage.DANGER.getCssAlertClass());
		mav.addObject("message", "La pagina cercata non e' disponibile! (404 Error).");
		return mav;
    }
}
