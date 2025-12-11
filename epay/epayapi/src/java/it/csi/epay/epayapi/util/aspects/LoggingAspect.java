/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayapi.util.aspects;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import it.csi.epay.epayapi.util.EntityUtils;


@Component
@Aspect
public class LoggingAspect {

    private final Logger logger = LogManager.getLogger ( this.getClass ().getName () );

    @Around ( value = "execution(* it.csi.epay.epayapi.business.impl..*.*(..))", argNames = "joinPoint" )
    public Object aroundBusinessImplCall ( ProceedingJoinPoint joinPoint ) throws Throwable {

        if ( logger.isDebugEnabled () ) {
            return logInputOutput ( joinPoint, this.getClass ().getName () + ".tracing.business" );
        } else {
            return joinPoint.proceed ();
        }
    }

    @Around ( value = "execution(* it.csi.epay.epayapi.integration.mapper..*.*(..))", argNames = "joinPoint" )
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

        Logger loggerBusinessInput = LogManager.getLogger ( loggerPrefix + ".input" );
        Logger loggerBusinessOutput = LogManager.getLogger ( loggerPrefix + ".output" );
        Logger loggerBusinessTiming = LogManager.getLogger ( loggerPrefix + ".timing" );
        Logger loggerBusinessError = LogManager.getLogger ( loggerPrefix + ".error" );

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

            StringBuilder logStr = new StringBuilder ();
            Object [] args = joinPoint.getArgs ();
			for ( Object arg : args ) {
				if ( arg != null ) {
					logStr.append ( EntityUtils.represent ( arg ) ).append ( ", " );
				} else {
					logStr.append ( "null, " );
				}
			}
            if ( args.length > 0 ) {
                logStr = new StringBuilder ( logStr.substring ( 0, logStr.length () - 2 ) );
            }
            logStr.append ( "]" );

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
		return "[RAY " + ray + "] ";
    }

    private static String getRequestRay ( HttpServletRequest request ) {

        String ray = (String) request.getAttribute ( "uniqueRayId" );
        if ( ray == null ) {
            return "???";
        }

        return ray;
    }
}
