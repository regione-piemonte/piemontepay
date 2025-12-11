/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.api.interceptors;

import java.lang.reflect.Method;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.Precedence;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.UnauthorizedException;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;

import it.csi.epay.epayapi.api.impl.ErrorHandlerApiImpl;
import it.csi.epay.epayapi.api.util.SpringApplicationContextHelper;
import it.csi.epay.epayapi.business.MessageService;
import it.csi.epay.epayapi.business.impl.MessageServiceImpl;
import it.csi.epay.epayapi.dto.exception.ManagedException;
import it.csi.epay.epayapi.dto.security.Secured;
import it.csi.epay.epayapi.util.Messages;
import it.csi.epay.epayapi.util.SecurityUtils;


/**
 * Interceptor utilizzato per verificare che l'utente disponga delle autorizzazioni richieste prima di accedere ai metodi delle risorse RestEasy
 */
@Provider
@ServerInterceptor
@Precedence ( "AFTER_SECURITY" )
public class SecurityInterceptor implements PreProcessInterceptor {

    private final Logger logger = LogManager.getLogger ( SecurityInterceptor.class );

    private static final ServerResponse PROCEDI = null;

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

    /**
     * Verifica, prima dell'invocazione del metodo sulla risorsa, che l'utente corrente disponga delle eventuali autorizzazioni necessarie all'accesso
     */
    @Override
    public ServerResponse preProcess ( HttpRequest request, ResourceMethod methodInvoked ) throws Failure, WebApplicationException {

        return process ( methodInvoked.getResourceClass (), methodInvoked.getMethod () );
    }

    public ServerResponse process ( Class<?> resourceClass, Method method ) {

        try {
            // verifico se e' presente un annotation Secured a livello di interfaccia
            Secured annotationOnResource = AnnotationUtils.findAnnotation ( resourceClass, Secured.class );
            if ( annotationOnResource != null ) {
                SecurityUtils.verificaAutorizzazioniUtenteCorrente ( annotationOnResource );
            }

            // verifico se e' presente un annotation Secured a livello di metodo
            Secured annotationOnMethod = AnnotationUtils.findAnnotation ( method, Secured.class );
            if ( annotationOnMethod != null ) {
                SecurityUtils.verificaAutorizzazioniUtenteCorrente ( annotationOnMethod );
            }

            if ( annotationOnResource == null && annotationOnMethod == null ) {
                logger.warn ( "Missing access control specifiers on endpoint: " + resourceClass.getName () + "." + method.getName () );
            }

        } catch ( UnauthorizedException e ) {
            throw e;

        } catch ( Exception e ) {
            logger.error ( "errore imprevisto nel controllo delle autorizzazioni", e );

            throw new ManagedException ( HttpStatus.INTERNAL_SERVER_ERROR, getMessageService ().getMessaggio ( Messages.SECURITY_ERROR_CHECKING ) );
        }

        return PROCEDI;
    }

}
