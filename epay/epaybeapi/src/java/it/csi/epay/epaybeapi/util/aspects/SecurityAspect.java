/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaybeapi.util.aspects;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.jboss.resteasy.spi.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import it.csi.epay.epaybeapi.dto.security.Secured;
import it.csi.epay.epaybeapi.util.EntityUtils;
import it.csi.epay.epaybeapi.util.SecurityUtils;


@Component
@Aspect
public class SecurityAspect {

    private Logger logger = LoggerFactory.getLogger ( this.getClass ().getName () );

    @Before ( value = "execution(* it.csi.epay.epaybeapi.business.impl..*.*(..))", argNames = "joinPoint" )
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
