/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.api.interceptors;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jboss.resteasy.annotations.interception.Precedence;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PostProcessInterceptor;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.csi.epay.epaybeapi.api.impl.ErrorHandlerApiImpl;
import it.csi.epay.epaybeapi.util.EntityUtils;
import it.csi.epay.epaybeapi.util.SecurityUtils;


@Provider
@ServerInterceptor
@Precedence ( "BEFORE_SECURITY" )
public class RequestLoggingInterceptor implements PreProcessInterceptor, PostProcessInterceptor {

    private static Logger logger = LoggerFactory.getLogger ( RequestLoggingInterceptor.class );

    private static final String REQUEST_ATTRIBUTE_UNIQUE_RAY_ID = "uniqueRayId";

    private static final String REQUEST_ATTRIBUTE_START_TIME = "appDataRequestStartingTime";

    @Override
    public ServerResponse preProcess ( HttpRequest request, ResourceMethod methodInvoked ) throws Failure, WebApplicationException {

        return preProcessRequest ( request, methodInvoked );
    }

    private static ServerResponse preProcessRequest ( HttpRequest request, ResourceMethod methodInvoked ) throws Failure, WebApplicationException {
        String rayId = null;
        String prefix = null;
        List<String> resume = null;
        String line;
        HttpServletRequest servletRequest = null;

        if ( request.getAttribute ( REQUEST_ATTRIBUTE_UNIQUE_RAY_ID ) == null ) {
            request.setAttribute ( REQUEST_ATTRIBUTE_UNIQUE_RAY_ID, UUID.randomUUID ().toString () );
        }

        if ( request.getAttribute ( REQUEST_ATTRIBUTE_START_TIME ) == null ) {
            request.setAttribute ( REQUEST_ATTRIBUTE_START_TIME, new Long ( new java.util.Date ().getTime () ) );
        }

        rayId = (String) request.getAttribute ( REQUEST_ATTRIBUTE_UNIQUE_RAY_ID );
        prefix = "[RAY " + rayId + "] ";

        if ( logger.isDebugEnabled () ) {
            prefix = "[RAY " + rayId + "] ";
            resume = new LinkedList<> ();
            request.setAttribute ( "uniqueRayResume", resume );

            try {
                line = request.getHttpMethod () + " to " + request.getUri ().getRequestUri ().toURL ().toString ();
                logger.debug ( prefix + line );
                resume.add ( line );
            } catch ( MalformedURLException e ) {
                line = request.getHttpMethod () + " to <???>";
                logger.debug ( prefix + line );
                resume.add ( line );
            }
        }

        if ( logger.isTraceEnabled () ) {
            try {
                servletRequest = SecurityUtils.getCurrentRequest ();
                resume.add ( "" );

                if ( request.getInputStream () != null ) {
                    String requestBody = peekBody ( request );
                    if ( requestBody != null && !requestBody.isEmpty () ) {
                        resume.add ( "request body: \n" + requestBody + "\n" );
                    }
                }

                if ( servletRequest != null ) {
                    Map<?, ?> parameters = servletRequest.getParameterMap ();
                    if ( parameters != null ) {
                        for ( Entry<?, ?> param: parameters.entrySet () ) {
                            line = "request param " + param.getKey () + " = " + EntityUtils.represent ( param.getValue () );
                            resume.add ( line );
                        }
                    }

                    Cookie [] cookies = servletRequest.getCookies ();
                    if ( cookies != null ) {
                        for ( Cookie cookie: cookies ) {
                            line = "request cookie " + cookie.getName () + " = " + EntityUtils.represent ( cookie.getValue () );
                            resume.add ( line );
                        }
                    }
                }

            } catch ( Exception e ) {
                logger.warn ( "Could not log request content", e );
            }

            if ( request.getHttpHeaders () != null && request.getHttpHeaders ().getRequestHeaders () != null ) {
                for ( Entry<String, List<String>> headerEntry: request.getHttpHeaders ().getRequestHeaders ().entrySet () ) {
                    line = "request header " + headerEntry.getKey () + " = " + headerEntry.getValue ();
                    resume.add ( line );
                }
            }
        }

        return null;
    }

    private static void preProcessHttpServletRequest ( HttpServletRequest request ) throws Failure, WebApplicationException {
        String rayId = null;
        String prefix = null;
        List<String> resume = null;
        String line;
        HttpServletRequest servletRequest = null;

        if ( request.getAttribute ( REQUEST_ATTRIBUTE_UNIQUE_RAY_ID ) == null ) {
            request.setAttribute ( REQUEST_ATTRIBUTE_UNIQUE_RAY_ID, UUID.randomUUID ().toString () );
        }

        if ( request.getAttribute ( REQUEST_ATTRIBUTE_START_TIME ) == null ) {
            request.setAttribute ( REQUEST_ATTRIBUTE_START_TIME, new Long ( new java.util.Date ().getTime () ) );
        }

        rayId = (String) request.getAttribute ( REQUEST_ATTRIBUTE_UNIQUE_RAY_ID );
        prefix = "[RAY " + rayId + "] ";

        if ( logger.isDebugEnabled () ) {
            prefix = "[RAY " + rayId + "] ";
            resume = new LinkedList<> ();
            request.setAttribute ( "uniqueRayResume", resume );

            try {
                line = request.getMethod () + " to " + request.getRequestURI ();
                logger.debug ( prefix + line );
                resume.add ( line );
            } catch ( Exception e ) {
                line = request.getMethod () + " to <???>";
                logger.debug ( prefix + line );
                resume.add ( line );
            }
        }

        if ( logger.isTraceEnabled () ) {
            try {
                servletRequest = SecurityUtils.getCurrentRequest ();
                resume.add ( "" );

                if ( request.getInputStream () != null ) {
                    String requestBody = removeBody ( request );
                    if ( requestBody != null && !requestBody.isEmpty () ) {
                        resume.add ( "request body: \n" + requestBody + "\n" );
                    }
                }

                if ( servletRequest != null ) {
                    Map<?, ?> parameters = servletRequest.getParameterMap ();
                    if ( parameters != null ) {
                        for ( Entry<?, ?> param: parameters.entrySet () ) {
                            line = "request param " + param.getKey () + " = " + EntityUtils.represent ( param.getValue () );
                            resume.add ( line );
                        }
                    }

                    Cookie [] cookies = servletRequest.getCookies ();
                    if ( cookies != null ) {
                        for ( Cookie cookie: cookies ) {
                            line = "request cookie " + cookie.getName () + " = " + EntityUtils.represent ( cookie.getValue () );
                            resume.add ( line );
                        }
                    }
                }

            } catch ( Exception e ) {
                logger.warn ( "Could not log request content", e );
            }

            @SuppressWarnings ( "unchecked" )
            Enumeration<String> headerNames = request.getHeaderNames ();
            while ( headerNames != null && headerNames.hasMoreElements () ) {
                String headerName = headerNames.nextElement ();
                @SuppressWarnings ( "unchecked" )
                Enumeration<String> headers = request.getHeaders ( headerName );
                while ( headers != null && headers.hasMoreElements () ) {
                    line = "request header " + headerName + " = " + headers.nextElement ();
                    resume.add ( line );
                }
            }
        }
    }

    @Override
    public void postProcess ( ServerResponse response ) {
        postProcessResponse ( response );
    }

    @SuppressWarnings ( "unchecked" )
    public static void postProcessResponse ( Response response ) {
        String rayId = null;
        String prefix = null;
        List<String> resume = null;
        HttpServletRequest servletRequest = null;
        String line;

        if ( logger.isDebugEnabled () ) {
            try {
                servletRequest = SecurityUtils.getCurrentRequest ();
            } catch ( Exception e ) {
                logger.warn ( "Could not log request result", e );
                return;
            }

            rayId = (String) servletRequest.getAttribute ( REQUEST_ATTRIBUTE_UNIQUE_RAY_ID );
            if ( rayId == null ) {
                // request was not preprocessed
                preProcessHttpServletRequest ( servletRequest );
                rayId = (String) servletRequest.getAttribute ( REQUEST_ATTRIBUTE_UNIQUE_RAY_ID );
            }

            prefix = "[RAY " + rayId + "] ";
            resume = (List<String>) servletRequest.getAttribute ( "uniqueRayResume" );
            if ( resume == null ) {
                resume = new ArrayList<> ();
            }

            resume.add ( "\nresponse status: " + response.getStatus () + "" );
        }

        if ( logger.isTraceEnabled () ) {

            if ( response.getEntity () != null ) {
                line = "\nresponse entity: " + response.getEntity ().getClass ().getName () + "\n";
                resume.add ( line );
                line = "response content: \n" + EntityUtils.represent ( response.getEntity () ) + "\n";
                resume.add ( line );
            }
        }

        if ( logger.isDebugEnabled () ) {
            Throwable errorResult = (Throwable) servletRequest.getAttribute ( ErrorHandlerApiImpl.REQUEST_ATTRIBUTE_RESULT_EXCEPTION );
            if ( errorResult != null ) {
                line = "processing exception: " + errorResult.getClass ().getName () + "\n" + representException ( errorResult );
                resume.add ( line );
            }

            Long startTime = (Long) servletRequest.getAttribute ( REQUEST_ATTRIBUTE_START_TIME );
            if ( startTime != null ) {
                long duration = new java.util.Date ().getTime () - startTime;
                line = "processing duration: " + duration + " ms";
                resume.add ( line );
            }

        }

        if ( resume != null ) {
            String separator = "======================================================";

            StringBuilder builder = new StringBuilder ();
            builder.append ( separator );
            builder.append ( "\n" );

            for ( String resumeLine: resume ) {
                builder.append ( resumeLine );
                builder.append ( "\n" );
            }

            builder.append ( separator );

            StringBuilder outter = new StringBuilder ();
            Pattern pattern = Pattern.compile ( "(?m)(^)" );
            Matcher matcher = pattern.matcher ( builder.toString () );
            outter.append ( matcher.replaceAll ( prefix ) );

            logger.debug ( "\n" + outter.toString () );
        }
    }

    private static String representException ( Throwable errorResult ) {

        return ExceptionUtils.getStackTrace ( errorResult );
    }

    public static void postProcessResponseForError ( Response errorResponse ) {
        postProcessResponse ( errorResponse );
    }

    private static String peekBody ( HttpRequest request ) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream ();
        try {
            IOUtils.copy ( request.getInputStream (), baos );
            byte [] bytes = baos.toByteArray ();

            if ( bytes.length > 0 ) {
                String payload = new String ( bytes, "UTF-8" );

                request.setInputStream ( new ByteArrayInputStream ( bytes ) );

                return payload;
            } else {

                bytes = new byte [] {};
                request.setInputStream ( new ByteArrayInputStream ( bytes ) );

                return null;
            }

        } catch ( IOException ex ) {
            throw new RuntimeException ( ex );
        }
    }

    private static String removeBody ( HttpServletRequest request ) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream ();
        try {
            IOUtils.copy ( request.getInputStream (), baos );
            byte [] bytes = baos.toByteArray ();

            if ( bytes.length > 0 ) {
                String payload = new String ( bytes, "UTF-8" );
                return payload;
            } else {
                return null;
            }

        } catch ( IOException ex ) {
            throw new RuntimeException ( ex );
        }
    }

}
