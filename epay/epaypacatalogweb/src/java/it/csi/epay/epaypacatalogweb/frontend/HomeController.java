/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.frontend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.csi.epay.epaypacatalogweb.common.Constants;
import it.csi.epay.epaypacatalogweb.common.exceptions.EnteNonPresenteException;
import it.csi.epay.epaypacatalogweb.util.SecurityUtils;


@Controller ( value = "/" )
public class HomeController extends AuthenticatedParentController {

    private final static String EPAYMODRICWEB = "epaymodricweb";
    private final static String EPAYSIMWEB = "epaysimweb";
    private final static String CONFIGPROP = "config";
    private final static String LOGOUTURL = "logout.url";

    private final Logger logger = Logger.getLogger ( HomeController.class );

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.LOGIN + "')" )
    @RequestMapping ( value = "" )
    public String homeImplicit ( Model model, HttpServletRequest request, HttpSession session ) {
        return "home";
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.LOGIN + "')" )
    @RequestMapping ( value = "home" )
    public String homeExplicit ( Model model, HttpServletRequest request, HttpSession session ) {
        return "home";
    }

    @Override
    public void loadComboboxes ( Model model ) {
        // Nothing here...
    }

    @RequestMapping ( value = "sessione-scaduta" )
    public String sessioneScaduta ( Model model, HttpServletRequest request, HttpServletResponse response ) {
        response.setStatus ( HttpStatus.FORBIDDEN.value () );
        model.addAttribute ( "message", "La tua sessione e' scaduta. Esegui nuovamente il login" );
        return "error";
    }

    @RequestMapping ( value = "accesso-negato" )
    public String accessoNegato ( Model model, HttpServletRequest request, HttpServletResponse response ) {
        response.setStatus ( HttpStatus.FORBIDDEN.value () );
        model.addAttribute ( "message", "Accesso negato. Non sei autorizzato ad eseguire l'operazione richiesta." );
        return "error";
    }

    @RequestMapping ( value = "spring_security_login" )
    public String loginFallitoFallback ( Model model, HttpServletRequest request, HttpServletResponse response,
        RedirectAttributes redirectAttributes ) {

        if ( request != null && request.getSession () != null ) {
            String cause = (String) request.getSession ().getAttribute ( "LOGIN_FAILED_CAUSE" );
            if ( cause != null && cause.equals ( EnteNonPresenteException.class.getName () ) ) {
                return "redirect:ente-scaduto";
            }
        }

        return "redirect:login-fallito";
    }

    @RequestMapping ( value = "login-fallito" )
    public String loginFallito ( Model model, HttpServletRequest request, HttpServletResponse response ) {

        response.setStatus ( HttpStatus.FORBIDDEN.value () );
        model.addAttribute ( "message", "Accesso all'applicativo non consentito dal server remoto." );
        return "loginfailed";
    }

    @RequestMapping ( value = "ente-scaduto" )
    public String loginFallitoEnteScaduto ( Model model, HttpServletRequest request, HttpServletResponse response ) {

        response.setStatus ( HttpStatus.FORBIDDEN.value () );
        model.addAttribute ( "message", "Sessione scaduta. Esegui nuovamente l'accesso" );
        return "accessexpired";
    }

    @RequestMapping ( value = "login-req" )
    public String loginRequested ( Model model, HttpServletRequest request, HttpServletResponse response ) {
        response.setStatus ( HttpStatus.FORBIDDEN.value () );
        model.addAttribute ( "message", "La tua sessione e' scaduta. Esegui nuovamente il login" );
        return "error";
    }

    @RequestMapping ( value = "identita" )
    public String identitaIride ( Model model, HttpServletRequest request, HttpServletResponse response ) {
        model.addAttribute ( "identita", SecurityUtils.getUser ().getIdentitaOriginale () );
        return "identita";
    }

    @RequestMapping ( value = "custom-logout" )
    public String customLogout ( HttpServletRequest request, HttpServletResponse response ) {

        //        StringBuffer urlStr = request.getRequestURL ();
        //        String uri = request.getRequestURI ();
        //        String host = urlStr.substring ( 0, urlStr.indexOf ( uri ) );
        //
        //        try {
        //            logoutExternal ( host, EPAYMODRICWEB, request );
        //        } catch ( MalformedURLException e ) {
        //            logger.error ( "EXCEPTION IN LOGOUT " + EPAYMODRICWEB + " : " + e );
        //            e.printStackTrace ();
        //        } catch ( IOException e ) {
        //            logger.error ( "EXCEPTION IN LOGOUT " + EPAYMODRICWEB + " : " + e );
        //            e.printStackTrace ();
        //        }
        //
        //
        //        try {
        //            logoutExternal ( host, EPAYSIMWEB, request );
        //        } catch ( MalformedURLException e ) {
        //            logger.error ( "EXCEPTION IN LOGOUT " + EPAYSIMWEB + " : " + e );
        //            e.printStackTrace ();
        //        } catch ( IOException e ) {
        //            logger.error ( "EXCEPTION IN LOGOUT " + EPAYSIMWEB + " : " + e );
        //            e.printStackTrace ();
        //        }


        Authentication auth = SecurityContextHolder.getContext ().getAuthentication ();
        if ( auth != null ) {
            new SecurityContextLogoutHandler ().logout ( request, response, auth );
        }
        SecurityContextHolder.getContext ().setAuthentication ( null );

        String logoutURL = java.util.ResourceBundle.getBundle ( CONFIGPROP ).getString ( LOGOUTURL );
        return "redirect:" + logoutURL;

    }

    /*
     * private void logoutExternal ( String host, String application, HttpServletRequest request ) throws MalformedURLException, IOException { URL url; int
     * responseCode = 0; url = new URL ( host + "/" + application + "/remote-logout" ); logger.info ( "TENTATIVO LOGOUT " + application + " : " + url.toString
     * () ); HttpURLConnection con = (HttpURLConnection) url.openConnection (); con.setRequestMethod ( "GET" ); con.setConnectTimeout ( 10000 );
     * Enumeration<String> headerEnumerator = request.getHeaderNames (); while ( headerEnumerator.hasMoreElements () ) { String headerName =
     * headerEnumerator.nextElement (); String headerValue = request.getHeader ( headerName ); con.setRequestProperty ( headerName, headerValue ); logger.info (
     * application + " HNAME : " + headerName + " - HVALUE : " + headerValue ); } responseCode = con.getResponseCode (); logger.info ( "RESPONSE LOGOUT " +
     * application + ", CODE: " + responseCode ); }
     */

    @RequestMapping ( value = "remote-logout" )
    public void remoteLogout ( HttpServletRequest request, HttpServletResponse response ) {
        Authentication auth = SecurityContextHolder.getContext ().getAuthentication ();
        if ( auth != null ) {
            new SecurityContextLogoutHandler ().logout ( request, response, auth );
        }
        SecurityContextHolder.getContext ().setAuthentication ( null );
    }

}
