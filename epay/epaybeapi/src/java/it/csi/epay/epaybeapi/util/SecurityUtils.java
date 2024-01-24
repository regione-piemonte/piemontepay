/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.util;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.jboss.resteasy.spi.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import it.csi.epay.epaybeapi.business.ClientService;
import it.csi.epay.epaybeapi.business.impl.ConfigurazioneServiceImpl;
import it.csi.epay.epaybeapi.dto.security.ClientInfo;
import it.csi.epay.epaybeapi.dto.security.Scopes;
import it.csi.epay.epaybeapi.dto.security.Secured;


/**
 *
 */
public abstract class SecurityUtils {

    private static Logger logger = LoggerFactory.getLogger ( SecurityUtils.class );

    private static ClientService clientService = null;

    public static ClientInfo getCurrentClient () {
        if ( clientService == null ) {
            clientService = ClientService.getInstance ();
        }
        return clientService.getClientCorrente ();
    }

    @SuppressWarnings ( "deprecation" )
    public static void verificaAutorizzazioniUtenteCorrente ( Secured annotation ) {
        // verifico che l'annotation sia valida
        if ( ( annotation.permitAll () && annotation.denyAll () ) || ( ( annotation.permitAll () || annotation.denyAll () ) &&
            ( annotation.value ().length > 0 || annotation.hasAnyScope ().length > 0 ) ) ) {
            throw new RuntimeException (
                "Annotazione @Secured non valida: utilizzo contemporaneo di qualificatore assoluto e lista di authorities. " +
                    "Puoi utilizzare, in alternativa, una delle seguenti opzioni: " +
                    "[permitAll = true], [denyAll = true], [value = {non vuota} e/o hasAnyAuthority = {non vuota}]" );
        }

        ClientInfo client = getCurrentClient ();

        HttpServletRequest servletRequest = getCurrentRequest ();
        String url = ( servletRequest != null ) ? servletRequest.getRequestURL ().toString () : "<UNKNOWN>";

        // verifico notSecured
        if ( annotation.notSecured () ) {
            if ( ConfigurazioneServiceImpl.isCollaudoOrUpper () ) {
                logClientFailed ( client, url, "POLICY::NOT_SECURED" );
                throw new RuntimeException ( String.format ( Messages.SECURITY_ERROR_UNSECURED_ENDPOINT.getMessage (), servletRequest.getRequestURL () ) );
            } else {
                logger.warn (
                    "\n\n\tATTENZIONE: ENDPOINT PRIVO DI SPECIFICA DI SICUREZZA:" +
                        "\n\t" + servletRequest.getRequestURL ()
                        + "\n\tQUESTO ENDPOINT NON SARA' FUNZIONANTE IN AMBIENTE DI PRODUZIONE \n" );
                return;
            }
        }

        // verifico testOnly
        if ( annotation.testOnly () ) {
            if ( !ConfigurazioneServiceImpl.isTestOrLower () ) {
                logClientFailed ( client, url, "POLICY::TEST_ONLY" );
                throw new UnauthorizedException ();
            }
        }

        // verifico i qualificatori assoluti
        if ( annotation.permitAll () ) {
            return;
        }

        if ( annotation.denyAll () ) {
            logClientFailed ( client, url, "POLICY::DENY_ALL" );
            throw new UnauthorizedException ();
        }

        // verifico se e' richiesto authenticated = true
        if ( annotation.authenticated () ) {

            if ( client == null ) {
                logClientFailed ( null, url, "POLICY::CLIENT_REQUIRED" );
                throw new UnauthorizedException ();
            }

            if ( client.getAnonimo () == null || client.getAnonimo () ) {
                logClientFailed ( client, url, "POLICY:AUTHENTICATED" );
                throw new UnauthorizedException ();
            }
        }

        // verifico se e' richiesta una lista di scopes
        if ( annotation.value ().length > 0 ) {

            if ( client == null ) {
                logClientFailed ( null, url, "POLICY::CLIENT_REQUIRED" );
                throw new UnauthorizedException ();
            }

            for ( Scopes auth: annotation.value () ) {
                if ( !client.hasScope ( auth.name () ) ) {
                    logClientFailed ( client, url, auth );
                    throw new UnauthorizedException ();
                }
            }
        }

        // verifico se e' richiesta una lista di ruoli alternativi
        if ( annotation.hasAnyScope ().length > 0 ) {

            if ( client == null ) {
                logClientFailed ( null, url, "POLICY::CLIENT_REQUIRED" );
                throw new UnauthorizedException ();
            }

            boolean allowed = false;
            for ( Scopes auth: annotation.hasAnyScope () ) {
                if ( client.hasScope ( auth.name () ) ) {
                    allowed = true;
                    break;
                }
            }

            if ( !allowed ) {
                logClientFailed ( client, url, Arrays.asList ( annotation.hasAnyScope () ) );
                throw new UnauthorizedException ();
            }
        }

    }

    private static void logClientFailed ( ClientInfo client, String url, Object missing ) {
        logger.warn ( "accesso negato per il client: " + client + " all'endpoint " + url + " a causa di autorizzazione mancante: " + missing );
    }

    public static HttpServletRequest getCurrentRequest () {
        ServletRequestAttributes attributes;

        try {
            attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes ();
        } catch ( IllegalStateException e ) {
            return null;
        }

        if ( attributes == null ) {
            return null;
        }

        HttpServletRequest servletRequest = attributes.getRequest ();

        return servletRequest;
    }
}
