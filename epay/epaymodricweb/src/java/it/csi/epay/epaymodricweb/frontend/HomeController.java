/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.frontend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller(value = "/")
public class HomeController extends AuthenticatedParentController {

    private final static String EPAYPACATALOGWEB = "epaypacatalogweb";

    private final static String EPAYSIMWEB = "epaysimweb";

    private final static String CONFIGPROP = "config";

    private final static String LOGOUTURL = "logout.url";

    private final Logger logger = LogManager.getLogger ( HomeController.class );


    @RequestMapping(value = "")
    public String homeImplicit(Model model, HttpServletRequest request, HttpSession session) {
        return "home";
    }

    @RequestMapping(value = "home")
    public String homeExplicit(Model model, HttpServletRequest request, HttpSession session) {
        return "home";
    }

    @Override
    public void loadComboboxes(Model model) {
        // Nothing here...
    }

    @RequestMapping(value = "sessione-scaduta")
    public String sessioneScaduta(Model model, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        model.addAttribute("message", "La tua sessione  scaduta. Esegui nuovamente il login");
        return "error";
    }

    @RequestMapping(value = "accesso-negato")
    public String accessoNegato(Model model, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        model.addAttribute("message", "Accesso negato. Non sei autorizzato ad eseguire l'operazione richiesta.");
        return "error";
    }

    @RequestMapping ( value = "spring_security_login" )
    public String loginFallitoFallback ( Model model, HttpServletRequest request, HttpServletResponse response,
        RedirectAttributes redirectAttributes ) {
        return "redirect:login-fallito";
    }

    @RequestMapping ( value = "login-fallito" )
    public String loginFallito ( Model model, HttpServletRequest request, HttpServletResponse response ) {

        response.setStatus ( HttpStatus.FORBIDDEN.value () );
        model.addAttribute ( "message", "Accesso all'applicativo non consentito dal server remoto." );
        return "accessdenied";
    }

    @RequestMapping ( value = "custom-logout" )
    public String customLogout ( HttpServletRequest request, HttpServletResponse response ) {

        /*
         * StringBuffer urlStr = request.getRequestURL (); String uri = request.getRequestURI (); String host = urlStr.substring ( 0, urlStr.indexOf ( uri ) );
         * try { logoutExternal ( host, EPAYPACATALOGWEB, request ); } catch ( MalformedURLException e ) { logger.error ( "EXCEPTION IN LOGOUT " +
         * EPAYPACATALOGWEB + " : " + e ); e.printStackTrace (); } catch ( IOException e ) { logger.error ( "EXCEPTION IN LOGOUT " + EPAYPACATALOGWEB + " : " +
         * e ); e.printStackTrace (); } try { logoutExternal ( host, EPAYSIMWEB, request ); } catch ( MalformedURLException e ) { logger.error (
         * "EXCEPTION IN LOGOUT " + EPAYSIMWEB + " : " + e ); e.printStackTrace (); } catch ( IOException e ) { logger.error ( "EXCEPTION IN LOGOUT " +
         * EPAYSIMWEB + " : " + e ); e.printStackTrace (); }
         */

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
