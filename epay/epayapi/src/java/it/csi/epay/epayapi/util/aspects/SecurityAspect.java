/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayapi.util.aspects;

import java.lang.reflect.Method;


import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.jboss.resteasy.spi.UnauthorizedException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import it.csi.epay.epayapi.dto.security.Secured;
import it.csi.epay.epayapi.util.EntityUtils;
import it.csi.epay.epayapi.util.SecurityUtils;

import org.apache.logging.log4j.LogManager;


@Component
@Aspect
public class SecurityAspect {

    private final Logger logger = LogManager.getLogger ( this.getClass ().getName () );

    @Before ( value = "execution(* it.csi.epay.epayapi.business.impl..*.*(..))", argNames = "joinPoint" )
    public void aroundBusinessImplCall ( JoinPoint joinPoint ) throws Throwable {

        Class<?> targetEnclosingClass = joinPoint.getTarget ().getClass ();
        Secured annotationOnController = AnnotationUtils.findAnnotation ( targetEnclosingClass, Secured.class );

        MethodSignature signature = (MethodSignature) joinPoint.getSignature ();
        Method method = signature.getMethod ();
        Secured annotationOnMethod = AnnotationUtils.findAnnotation ( method, Secured.class );

        try {
            if ( annotationOnController != null ) {
                if ( logger.isTraceEnabled () ) {
                    String signatureName = EntityUtils.getTargetObject ( joinPoint.getTarget () ).getSimpleName ();
                    logger.trace ( "verifico autorizzazioni per classe " + signatureName );
                }

                SecurityUtils.verificaAutorizzazioniUtenteCorrente ( annotationOnController );
            }

            if ( annotationOnMethod != null ) {
                if ( logger.isTraceEnabled () ) {
                    String signatureName = EntityUtils.getTargetObject ( joinPoint.getTarget () ).getSimpleName () + "." + joinPoint.getSignature ().getName ();
                    logger.trace ( "verifico autorizzazioni per metodo " + signatureName );
                }

                SecurityUtils.verificaAutorizzazioniUtenteCorrente ( annotationOnMethod );
            }
        } catch ( UnauthorizedException e ) {
            String signatureName = EntityUtils.getTargetObject ( joinPoint.getTarget () ).getSimpleName () + "." + joinPoint.getSignature ().getName ();
            logger.error ( "Accesso negato al metodo " + signatureName );
            throw e;
        }

        if ( logger.isTraceEnabled () && annotationOnController == null && annotationOnMethod == null ) {
            String signatureName = EntityUtils.getTargetObject ( joinPoint.getTarget () ).getSimpleName () + "." + joinPoint.getSignature ().getName ();
            logger.trace ( "nessuna autorizzazione richiesta per metodo " + signatureName );
        }
    }

}
