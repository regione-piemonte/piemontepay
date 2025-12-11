/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.api.interceptors;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.SecurityPrecedence;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PostProcessInterceptor;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;

import it.csi.epay.epayapi.api.impl.ErrorHandlerApiImpl;
import it.csi.epay.epayapi.api.util.SpringApplicationContextHelper;
import it.csi.epay.epayapi.business.ClientService;
import it.csi.epay.epayapi.business.ConfigurazioneService;
import it.csi.epay.epayapi.business.MessageService;
import it.csi.epay.epayapi.business.ProfilazioneClientService;
import it.csi.epay.epayapi.business.impl.ConfigurazioneServiceImpl;
import it.csi.epay.epayapi.business.impl.MessageServiceImpl;
import it.csi.epay.epayapi.dto.exception.ManagedException;
import it.csi.epay.epayapi.dto.security.ClientInfo;
import it.csi.epay.epayapi.util.Messages;
import it.csi.epay.epayapi.util.ParametriApplicativo;


@Provider
@ServerInterceptor
@SecurityPrecedence
public class AuthenticationInterceptor implements PreProcessInterceptor, PostProcessInterceptor {

    private static final Logger logger = LogManager.getLogger ( AuthenticationInterceptor.class );

    private static final String LOG_PREFIX = "[ClientAuthenticationFilter] ";

    public static final String CLIENTINFO_REQUESTATTR = "appDataCurrentClient";

    public final static String HEADER_AUTHORIZATION = "Authorization";

    public final static String HEADER_CLIENT_ATTIVO = "X-Client-Attivo";

    private static volatile MessageService messageService = null;

    private static MessageService getMessageService () {
        if ( messageService == null ) {
            synchronized ( ErrorHandlerApiImpl.class ) {
                if ( messageService == null ) {
                    messageService = (MessageService) SpringApplicationContextHelper.getBean ( MessageServiceImpl.class );
                }
            }
        }
        return messageService;
    }

    private ConfigurazioneService getConfigurazioneService () {
        return (ConfigurazioneService) SpringApplicationContextHelper.getBean ( ConfigurazioneServiceImpl.class );
    }

    private boolean isEnabled () {
        return getConfigurazioneService ().getConfig ( ParametriApplicativo.FILTER_CLIENT_AUTH_ENABLED ).asBoolean ();
    }

    @Override
    public ServerResponse preProcess ( HttpRequest req, ResourceMethod methodInvoked ) throws Failure, WebApplicationException {
        if ( !isEnabled () ) {
            return null;
        }

        ClientInfo clientInfo;
        String authHeader = null;

        HttpHeaders headers = req.getHttpHeaders ();
        if ( headers != null ) {
            List<String> headersAuth = headers.getRequestHeader ( HEADER_AUTHORIZATION );
            if ( headersAuth != null ) {
                if ( headersAuth.size () > 1 ) {
                    throw new ManagedException ( HttpStatus.BAD_REQUEST,
                        getMessageService ().getMessaggio ( Messages.AUTH_ERROR_TOO_MANY_HEADERS, HEADER_AUTHORIZATION ) );
                } else if ( headersAuth.size () == 1 ) {
                    authHeader = headersAuth.get ( 0 );
                }
            }
        }

        clientInfo = ProfilazioneClientService.getInstance ().caricaClientInfo ( authHeader );

        if ( clientInfo != null ) {
            if ( logger.isDebugEnabled () ) {
                logger.debug ( LOG_PREFIX + " caricato client info " + clientInfo );
            }

        } else {
            if ( logger.isDebugEnabled () ) {
                logger.debug ( LOG_PREFIX + " client non autenticato" );
            }
        }

        req.setAttribute ( CLIENTINFO_REQUESTATTR, clientInfo );

        return null;
    }

    @Override
    public void postProcess ( ServerResponse resp ) {
        final MultivaluedMap<String, Object> headers = resp.getMetadata ();

        headers.add ( HEADER_CLIENT_ATTIVO, ClientService.getInstance ().getClientCorrente ().getCodice () );
    }

}
