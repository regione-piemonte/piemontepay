/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import it.csi.epay.epaypacatalogweb.common.config.LogConfig;


/**
 *
 * @author lorenzo.fantini
 * Filtro di pre autenticazione usato da spring mvc (deriva da shibboleth)
 */
public class ShibbolethAuthenticationFilter extends RequestHeaderAuthenticationFilter {

    // RequestHeader
    private String shibbIdentity; // Shib-Iride-IdentitaDigitale

    private String shibbTestMode;

    private Logger logger = LoggerFactory.getLogger ( LogConfig.HANDLER_SECURITY );

    @Override
    protected Object getPreAuthenticatedPrincipal ( HttpServletRequest request ) {
        IdentityDetails principal = new IdentityDetails ();

        String shibbTestMode = this.shibbTestMode;
        if ( "true".equals ( shibbTestMode ) ) {
            shibbTestMode = "enabled";
        }

        //		String token = request.getHeader(shibbIdentity);
        String token = null;
        // CODICE FISCALE ENTE in arrivo da epaypa
        String codiceEnte = request.getParameter ( "CF_ENTE" );
        logger.info ( "[ShibbolethAuthenticationFilter::getPreAuthenticatedPrincipal] <" + request.getRequestURI () + "> (CF_ENTE::" + codiceEnte + ")" );
        try {
            if ( "enabled".equalsIgnoreCase ( shibbTestMode ) ) {
                token = "AAAAAA00B77B000F/80087670016/DEMO 20/CSI PIEMONTE/INFOCERT_3/20180601094558/16/6fHlNBkfJYKdDRZP24vl4w==";
                String [] splitted = token.split ( "/" );
                principal.setIdentity ( token );
                principal.setCodiceEnte ( StringUtils.isNotBlank ( codiceEnte ) ? codiceEnte : splitted [1] );
                principal.setCodiceUtente ( splitted [0] );
            } else {
                token = request.getHeader ( shibbIdentity );
                String [] splitted = token.split ( "/" );
                principal.setIdentity ( token );
                principal.setCodiceEnte ( StringUtils.isNotBlank ( codiceEnte ) ? codiceEnte : null );
                principal.setCodiceUtente ( splitted [0] );
            }
        } catch ( Exception e ) {
            logger.error ( "[ShibbolethAuthenticationFilter::getPreAuthenticatedPrincipal] <" + request.getRequestURI () + "> Errore elaborazione TOKEN", e );
            return null;
        }
        logger.info ( "[ShibbolethAuthenticationFilter::getPreAuthenticatedPrincipal] <" + request.getRequestURI () + "> (token::" + token + ")" );

        return principal;
    }

    public String getShibbTestMode () {
        return shibbTestMode;
    }

    public void setShibbTestMode ( String shibbTestMode ) {
        this.shibbTestMode = shibbTestMode;
    }

    public String getShibbIdentity () {
        return shibbIdentity;
    }

    public void setShibbIdentity ( String shibbIdentity ) {
        this.shibbIdentity = shibbIdentity;
    }

}
