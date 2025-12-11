/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayapi.testbed.model;

import static org.mockito.Mockito.mock;

import java.lang.reflect.Method;
import java.security.InvalidParameterException;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import it.csi.epay.epayapi.api.interceptors.SecurityInterceptor;
import it.csi.epay.epayapi.testbed.config.TestConstants;


public abstract class ParentHttpCallTest extends ParentUnitTest {

    protected final Logger logger = Logger.getLogger ( ParentHttpCallTest.class );

    private MockHttpServletRequest request;

    private MockHttpServletResponse response;

    private SecurityInterceptor securityInterceptor = new SecurityInterceptor ();

    @Before
    public void beforeParentHttpCallTest () {
        resetRequest ();
        doFilterChain ();
    }

    protected void simulaRequestConIdentita ( String identity ) {
        resetRequest ();

        // request.addHeader ( DigeInterceptor.AUTH_ID_MARKER, identity );

        doFilterChain ();
    }

    protected void simulaRequestSenzaIdentita () {
        resetRequest ();
        doFilterChain ();
    }

    private void resetRequest () {
        request = new MockHttpServletRequest ();
        response = new MockHttpServletResponse ();

        // notify org.springframework.web.context.request.RequestContextListener
        ServletRequestAttributes sra = new ServletRequestAttributes ( request );
        RequestContextHolder.setRequestAttributes ( sra );

        getServletContext ().setAttribute (
            WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
            getApplicationContext () );

        request.setRequestURI ( TestConstants.REQUEST_URL_VALIDA );
    }

    private void doFilterChain () {
        // NOP
    }

    protected <T> T buildResource ( Class<T> cl ) {
        T output;
        try {
            output = cl.newInstance ();
        } catch ( InstantiationException | IllegalAccessException e ) {
            throw new RuntimeException ( e );
        }
        getApplicationContext ().getAutowireCapableBeanFactory ().autowireBean ( output );
        return output;
    }

    protected Response dispatch ( Class<?> resourceInterface, Class<?> resourceImplementation,
        String methodName, Class<?> [] argTypes, Object [] args ) {
        try {
            if ( resourceInterface == null || !resourceInterface.isInterface () ) {
                throw new InvalidParameterException ( "invalid resource interface" );
            }
            if ( resourceImplementation == null || resourceImplementation.isInterface () ) {
                throw new InvalidParameterException ( "invalid resource implementation" );
            }

            Method methodInterface = resourceInterface.getMethod ( methodName, argTypes );

            Response result = getSecurityInterceptor ().process ( resourceInterface, methodInterface );
            if ( result != null ) {
                return result;
            }

            Method methodImplementation = resourceImplementation.getMethod ( methodName, argTypes );
            Object implementation = buildResource ( resourceImplementation );

            result = (Response) methodImplementation.invoke ( implementation, args );
            return result;

        } catch ( Exception e ) {
            throw new RuntimeException ( "manual dispatch failed: " + e.getMessage (), e );
        }
    }

    public HttpHeaders getHttpHeaders () {
        return mock ( HttpHeaders.class );
    }

    public SecurityContext getSecurityContext () {
        return mock ( SecurityContext.class );
    }

    public MockHttpServletRequest getRequest () {
        return request;
    }

    public MockHttpServletResponse getResponse () {
        return response;
    }

    public SecurityInterceptor getSecurityInterceptor () {
        return securityInterceptor;
    }

}
