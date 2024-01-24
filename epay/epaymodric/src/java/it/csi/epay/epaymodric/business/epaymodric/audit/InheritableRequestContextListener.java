/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.audit;

import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.request.ServletRequestAttributes;


public class InheritableRequestContextListener extends RequestContextListener {

    private static final String REQUEST_ATTRIBUTES_ATTRIBUTE = InheritableRequestContextListener.class.getName () + ".REQUEST_ATTRIBUTES";

    @Override
    public void requestInitialized ( ServletRequestEvent requestEvent ) {
        if ( ! ( requestEvent.getServletRequest () instanceof HttpServletRequest ) ) {
            throw new IllegalArgumentException (
                "Request is not an HttpServletRequest: " + requestEvent.getServletRequest () );
        }
        HttpServletRequest request = (HttpServletRequest) requestEvent.getServletRequest ();
        ServletRequestAttributes attributes = new ServletRequestAttributes ( request );
        request.setAttribute ( REQUEST_ATTRIBUTES_ATTRIBUTE, attributes );
        LocaleContextHolder.setLocale ( request.getLocale () );
        RequestContextHolder.setRequestAttributes ( attributes, true );
    }
}
