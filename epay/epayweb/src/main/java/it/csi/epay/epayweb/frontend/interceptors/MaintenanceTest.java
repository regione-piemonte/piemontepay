/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import it.csi.epay.epayservices.interfaces.ejb.ParametroFacade;
import it.csi.epay.epayweb.business.messaggi.LevelMessage;
import it.csi.epay.epayweb.utilities.LogUtil;

public class MaintenanceTest extends HandlerInterceptorAdapter {

    protected LogUtil log = new LogUtil ( this.getClass () );

	private final static String VIEWNAMEHOME = "home_manutenzione/home";

	@Autowired
	ParametroFacade parametro;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

        log.debug ( "preHandle", "Call to MaintenanceTest.preHandle" );

		Boolean statoEpayServices = Boolean.parseBoolean(parametro.ricerca("app_online", "epayservices").getValore());
		Boolean statoEpayWeb = Boolean.parseBoolean(parametro.ricerca("app_online", "epayweb").getValore());

		if (!statoEpayServices || !statoEpayWeb) {
			ModelAndView mav = new ModelAndView(VIEWNAMEHOME);
			mav.addObject("levelMessage", LevelMessage.WARNING.getCssAlertClass());
			mav.addObject("message", "I servizi di pagamento sono stati momentaneamente disattivati per un aggiornamento del sistema. Verranno ripristinati non appena possibile, ci scusiamo per il disagio.");
			throw new ModelAndViewDefiningException(mav);
		}

		return true;
	}
}
