/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.common.util.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import it.csi.epay.epaymodric.business.epaymodric.audit.CSILogAuditApplicationConfiguration;
import it.csi.epay.epaymodric.business.epaymodric.facade.EpaypacatalogsrvFacade;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.NotAuthorizedException;
import it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv.CallerInputDto;
import it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv.EpaypacatalogsrvException_Exception;
import it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv.Exception_Exception;
import it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv.GetProfilazioneUtenteOutput;
import it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv.UnrecoverableException_Exception;


public abstract class RequestContextUtils {

    private final static Logger logger = LogManager.getLogger ( "it.csi.epay.epaymodric.profilazione" );

    private final static String REQUEST_ATTRIBUTE_CONTEXT = "EPAYMODRIC_REQUEST_CONTEXT";

    private static EpaypacatalogsrvFacade getServizioProfilazione () {
        return CSILogAuditApplicationConfiguration.getApplicationContext ().getBean ( EpaypacatalogsrvFacade.class );
    }

    public static RequestAttributes getThreadContext () {
        return RequestContextHolder.currentRequestAttributes ();
    }

    public static void setThreadContext ( RequestAttributes input ) {
        RequestContextHolder.setRequestAttributes ( input, true );
    }

    public static RequestContext getRequestContext () {

        HttpServletRequest request;

        try {
            request = ( (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes () ).getRequest ();
        } catch ( Exception e ) {
            logger.error ( "errore nel reperimento della richiesta http", e );
            throw new RuntimeException ( "errore nel reperimento della richiesta http", e );
        }

        if ( request == null ) {
            throw new RuntimeException ( "richiesta non trovata" );
        } else {

            RequestContext rc = (RequestContext) request.getAttribute ( REQUEST_ATTRIBUTE_CONTEXT );
            if ( rc == null ) {
                rc = new RequestContext ();
                request.setAttribute ( REQUEST_ATTRIBUTE_CONTEXT, rc );
            }

            return rc;
        }
    }

    public static PrincipalVO getPrincipal () {
        RequestContext rc = getRequestContext ();
        if ( rc.getPrincipal () != null ) {
            return rc.getPrincipal ();
        }

        PrincipalVO newPrincipal = getPrincipalFromCaller ( rc.getCaller () );

        if ( newPrincipal == null ) {
            logger.error ( "autenticazione non fornita" );
            throw new NotAuthorizedException ( "autenticazione non fornita" );
        }

        rc.setPrincipal ( newPrincipal );
        return newPrincipal;
    }

    public static PrincipalVO getPrincipalIfPresent () {
        RequestContext rc = getRequestContext ();
        if ( rc.getPrincipal () != null ) {
            return rc.getPrincipal ();
        }

        PrincipalVO newPrincipal = getPrincipalFromCaller ( rc.getCaller () );

        rc.setPrincipal ( newPrincipal );
        return newPrincipal;
    }

    private static PrincipalVO getPrincipalFromCaller ( CallerInputDto caller ) {

        if ( caller == null || caller.getPrincipal () == null
                        || StringUtils.isEmpty ( caller.getPrincipal ().getCodiceFiscale () ) ) {
            return null;
        }

        GetProfilazioneUtenteOutput outputServizio;

        try {
            logger.info ( "richiedo profilazione al WS di epaycatalog per l'utente " + caller.getPrincipal ().getCodiceFiscale () );

            outputServizio = getServizioProfilazione ().getProfilazioneUtente ( caller );

            logger
            .info ( "richiesta di profilazione completata con esito: " + outputServizio.getCodiceEsito () + " - " + outputServizio.getDescrizioneEsito () );

        } catch ( Exception_Exception | EpaypacatalogsrvException_Exception | UnrecoverableException_Exception e ) {
            logger.error ( "autenticazione non validabile: " + e.getMessage (), e );
            throw new NotAuthorizedException ( "autenticazione non validabile: " + e.getMessage (), e );
        }

        if ( !outputServizio.getCodiceEsito ().equals ( "OK" ) ) {
            logger.error ( "autenticazione rifiutata: " + outputServizio.getDescrizioneEsito () );
            throw new NotAuthorizedException ( "autenticazione rifiutata: " + outputServizio.getDescrizioneEsito () );
        }

        PrincipalVO newPrincipal = new PrincipalVO (
            outputServizio.getUtente (),
            outputServizio.getEnte (),
            caller.getIp (),
            caller.getCodiceApplicativo () );

        return newPrincipal;
    }
}
