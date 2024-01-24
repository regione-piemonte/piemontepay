/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaybeapi.util.aspects;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import it.csi.epay.epaybeapi.util.EntityUtils;


@Component
@Aspect
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger ( this.getClass ().getName () );

    @Around ( value = "execution(* it.csi.epay.epaybeapi.business.impl..*.*(..))", argNames = "joinPoint" )
    public Object aroundBusinessImplCall ( ProceedingJoinPoint joinPoint ) throws Throwable {

        if ( logger.isDebugEnabled () ) {
            return logInputOutput ( joinPoint, this.getClass ().getName () + ".tracing.business" );
        } else {
            return joinPoint.proceed ();
        }
    }

    @Around ( value = "execution(* it.csi.epay.epaybeapi.integration.mapper..*.*(..))", argNames = "joinPoint" )
    public Object aroundMapperCall ( ProceedingJoinPoint joinPoint ) throws Throwable {

        if ( logger.isDebugEnabled () ) {
            return logInputOutput ( joinPoint, this.getClass ().getName () + ".tracing.mapper" );
        } else {
            return joinPoint.proceed ();
        }
    }

    private Object logInputOutput ( ProceedingJoinPoint joinPoint, String loggerPrefix ) throws Throwable {

        ServletRequestAttributes sra = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes () );
        if ( sra == null || sra.getRequest () == null ) {
            return joinPoint.proceed ();
        }

        Logger loggerBusinessInput = LoggerFactory.getLogger ( loggerPrefix + ".input" );
        Logger loggerBusinessOutput = LoggerFactory.getLogger ( loggerPrefix + ".output" );
        Logger loggerBusinessTiming = LoggerFactory.getLogger ( loggerPrefix + ".timing" );
        Logger loggerBusinessError = LoggerFactory.getLogger ( loggerPrefix + ".error" );

        boolean logInput = loggerBusinessInput.isDebugEnabled ();
        boolean logOutput = loggerBusinessOutput.isDebugEnabled ();
        boolean measureTiming = loggerBusinessTiming.isDebugEnabled ();
        boolean logError = loggerBusinessError.isDebugEnabled ();

        HttpServletRequest request = sra.getRequest ();

        String separator = "======================================================";
        String ray = getRequestRay ( request );
        String rayPrefix = "[RAY " + ray + "] ";
        Object result = null;
        Throwable threw = null;
        Long startTime = null;

        String signatureName = EntityUtils.getTargetObject ( joinPoint.getTarget () ).getSimpleName () + "." + joinPoint.getSignature ().getName ();
        StringBuilder methodLog = new StringBuilder ( "" );

        methodLog.append ( rayPrefix );
        methodLog.append ( signatureName );

        if ( logInput ) {
            methodLog.append ( "\n" );
            methodLog.append ( rayPrefix );
            methodLog.append ( signatureName );
            methodLog.append ( " INPUT = [" );

            String logStr = "";
            Object [] args = joinPoint.getArgs ();
            for ( int i = 0; i < args.length; i++ ) {
                if ( args [i] != null ) {
                    logStr += EntityUtils.represent ( args [i] ) + ", ";
                } else {
                    logStr += "null, ";
                }
            }
            if ( args.length > 0 ) {
                logStr = logStr.substring ( 0, logStr.length () - 2 );
            }
            logStr += "]";

            methodLog.append ( logStr );
        }

        if ( measureTiming ) {
            startTime = new Date ().getTime ();
        }

        try {
            result = joinPoint.proceed ();
        } catch ( Throwable e ) {
            threw = e;
        }

        if ( measureTiming ) {

            methodLog.append ( "\n" );
            methodLog.append ( rayPrefix );
            methodLog.append ( signatureName );
            methodLog.append ( " DURATION = " );
            methodLog.append ( ( ( new Date ().getTime () ) - startTime ) );
            methodLog.append ( " ms" );
        }

        if ( threw != null ) {
            if ( logError ) {
                methodLog.append ( "\n" );
                methodLog.append ( rayPrefix );
                methodLog.append ( signatureName );
                methodLog.append ( " THREW EXCEPTION: " );
                methodLog.append ( threw.getClass ().getName () );
                methodLog.append ( " - " );
                methodLog.append ( threw.getMessage () );

            }
        } else {
            if ( logOutput ) {
                methodLog.append ( "\n" );
                methodLog.append ( rayPrefix );
                methodLog.append ( signatureName );
                methodLog.append ( " OUTPUT = " );
                methodLog.append ( EntityUtils.represent ( result ) );
            }
        }

        logger.info ( "\n" + rayPrefix + separator + "\n" + methodLog.toString () + "\n" + rayPrefix + separator );

        if ( threw != null ) {
            if ( logError ) {
                logger.error ( rayPrefix + "%s THREW EXCEPTION in " + signatureName, threw );
            }

            // RETHROW EXCEPTION
            throw threw;
        }

        return result;
    }

    public static String getIdentationStringForCurrentRequestLogging () {
        ServletRequestAttributes sra = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes () );
        if ( sra == null || sra.getRequest () == null ) {
            return "[NO-REQ] ";
        }

        HttpServletRequest request = sra.getRequest ();

        String ray = getRequestRay ( request );
        String rayPrefix = "[RAY " + ray + "] ";
        return rayPrefix;
    }

    private static String getRequestRay ( HttpServletRequest request ) {

        String ray = (String) request.getAttribute ( "uniqueRayId" );
        if ( ray == null ) {
            return "???";
        }

        return ray;
    }
}
