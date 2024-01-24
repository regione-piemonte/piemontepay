/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import it.csi.epay.epayweb.utilities.LogUtil;

public class SessionTest extends HandlerInterceptorAdapter {

    protected LogUtil log = new LogUtil ( this.getClass () );

    private final static String VIEWHOME = "accesso_libero/home/home";

    @Override
    public boolean preHandle ( HttpServletRequest request, HttpServletResponse response, Object handler )
                    throws Exception {

        log.debug ( "preHandle", "Call to SessionTest.preHandle" );

        HttpSession session = request.getSession ( false );// don't create if it doesn't exist
        if ( ( session == null || session.isNew () ) ) {
            if ( ! ( null != request && null != request.getRequestURI () && isUrlAccessoDiretto ( request.getRequestURI (), request.getQueryString() ) ) ) {

                ModelAndView mav = new ModelAndView ( VIEWHOME );
                throw new ModelAndViewDefiningException ( mav );
            }
        }

        // conclusione_ok, conclusione_ko, conclusione_back case-insensitive

        return true;
    }

    private boolean isUrlAccessoDiretto ( String uri, String queryString ) {

        if ( uri.endsWith ( "accessoChiamanteEsterno" ) ) {
            return true;
        }

        if ( (uri.endsWith ( "accessoLibero/pagaSenzaIuv/datiPersonali" ) ||
                        uri.endsWith ( "accessoLibero/stampaAvviso/datiPersonali" )) &&
                        ("donatorinosolidale".equals ( queryString ) || "donazione".equals ( queryString )) ) {
            return true;
        }
       
        if ( uri.contains ( "/payment/conclusione_" ) ) {
            return true;
        }
        if ( uri.contains ( "/epayweb/payment/start" ) ) {
            return true;
        }


        return false;
    }
}
