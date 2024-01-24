/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import it.csi.epay.epaypaweb.presentation.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

import static it.csi.epay.epaypaweb.util.Util.APPLICATION_CODE;

public class ShibbolethInterceptor implements Interceptor {
    static private final long serialVersionUID = 1L;
//    static private final String CLASSNAME = ShibbolethInterceptor.class.getSimpleName();
    static private final org.apache.logging.log4j.Logger log = LogManager.getLogger(APPLICATION_CODE + ".presentation");

    private static final String SHIBBOLETH_CF = "Shib-Identita-CodiceFiscale";
    private static final String SHIBBOLETH_NAME = "Shib-Identita-Nome";
    private static final String SHIBBOLETH_SURNAME = "Shib-Identita-Cognome";
    private static final String SHIBBOLETH_AUTH_ID = "Shib-Iride-IdentitaDigitale";

    private static final String HEADER_USER_AGENT_KEY = "user-agent";

    private static final String HEADER_USER_AGENT_VALUE_JMETER = "Apache-HttpClient";

    private static final String JMETER_SHIB_CF = "AAAAAA00B77B000F";

    private static final String JMETER_SHIB_ID = "AAAAAA00B77B000F/Csi Piemonte/Demo 20/IPA/202106101";

    @Override
    public void destroy() {
    }

    @Override
    public void init() {
    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {

		String result;

        HttpServletRequest httpRequest = ServletActionContext.getRequest();

		log.info ( "PRINTING INTERCEPTED REQUEST FROM SHIBBOLETH - START" );
		log.info ( "PRINTING INTERCEPTED REQUEST FROM SHIBBOLETH - HEADERS" );
        Enumeration<String> headers = httpRequest.getHeaderNames ();
        if ( headers != null ) {
            while ( headers.hasMoreElements () ) {
                String headerName = headers.nextElement ();
				log.info ( String.format ( "HEADER - %s: %s", headerName, httpRequest.getHeader ( headerName ) ) );
            }
        }
		log.info ( "PRINTING INTERCEPTED REQUEST FROM SHIBBOLETH - ATTRIBUTES" );
        Enumeration<String> attributes = httpRequest.getAttributeNames ();
        if ( null != attributes ) {
            while ( attributes.hasMoreElements () ) {
                String attributeName = attributes.nextElement ();
				log.info ( String.format ( "ATTRIBUTE - %s: %s", attributeName, httpRequest.getHeader ( attributeName ) ) );
            }
        }
		log.info ( "PRINTING INTERCEPTED REQUEST FROM SHIBBOLETH - COOKIES" );
        if ( httpRequest.getCookies () != null ) {
            for ( int i = 0; i < httpRequest.getCookies ().length; i++ ) {
                Cookie cookie = httpRequest.getCookies () [i];

				log.info ( String.format ( "COOKIE - %s:%s", cookie.getName (), cookie.getValue () ) );
            }
        }
		log.info ( "PRINTING INTERCEPTED REQUEST FROM SHIBBOLETH - END" );

		log.debug("SHIBBOLETH_CF:" + httpRequest.getHeader(SHIBBOLETH_CF));
		log.debug("SHIBBOLETH_NAME:" + httpRequest.getHeader(SHIBBOLETH_NAME));
		log.debug("SHIBBOLETH_SURNAME:" + httpRequest.getHeader(SHIBBOLETH_SURNAME));
		log.debug("SHIBBOLETH_AUTH_ID:" + httpRequest.getHeader(SHIBBOLETH_AUTH_ID));

        // LF Aggiunta user agent check per Test di Carico
//        if ( httpRequest.getHeader ( SHIBBOLETH_AUTH_ID ) == null && ( httpRequest.getHeader ( HEADER_USER_AGENT_KEY ) == null
//                        || !httpRequest.getHeader ( HEADER_USER_AGENT_KEY ).contains ( HEADER_USER_AGENT_VALUE_JMETER ) ) ) {
//            result = "authentication-error";
//
//        } else {
            if (httpRequest.getHeader ( HEADER_USER_AGENT_KEY ) != null
                            && httpRequest.getHeader ( HEADER_USER_AGENT_KEY ).contains ( HEADER_USER_AGENT_VALUE_JMETER ) ) {
				log.info ( "JMETER USER AGENT DETECTED, INJECTING SHIBBOLETH DATA" );
                actionInvocation.getInvocationContext ().put ( Constants.USER_CODE_CONTEXT_KEY, JMETER_SHIB_CF );
                actionInvocation.getInvocationContext ().put ( Constants.IRIDE_AUTH_TOKEN_KEY, JMETER_SHIB_ID );
            } else {
                actionInvocation.getInvocationContext().put(Constants.USER_CODE_CONTEXT_KEY, httpRequest.getHeader(SHIBBOLETH_CF));
                actionInvocation.getInvocationContext().put(Constants.IRIDE_AUTH_TOKEN_KEY, httpRequest.getHeader(SHIBBOLETH_AUTH_ID));
            }
            result = actionInvocation.invoke();
//        }
//        lw.stop();
        return result;
    }

}
