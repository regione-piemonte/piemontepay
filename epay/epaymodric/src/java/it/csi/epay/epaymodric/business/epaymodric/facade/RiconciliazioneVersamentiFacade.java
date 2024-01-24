/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.facade;

import java.net.URL;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;

import it.csi.epay.epaymodric.business.epaymodric.utils.XmlUtil;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.EPayRiconciliazioneVersamentiPSP;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.EPayRiconciliazioneVersamentiPSP_Service;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.EsitoFlussiPagoPAResponse;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.ResponseType;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.RicercaProvvisoriPagoPARequest;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.RicercaProvvisoriPagoPAResponse;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.TestataTrasmissioneFlussiType;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.TrasmettiFlussiInErrorePagoPARequest;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.TrasmettiFlussiPagoPARequest;


@Service
public class RiconciliazioneVersamentiFacade {

    private static final String SEPARATORE = "**************************************************************";

    private EPayRiconciliazioneVersamentiPSP_Service service;

    private EPayRiconciliazioneVersamentiPSP port;

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    public RiconciliazioneVersamentiFacade () {
        super ();
        service = null;
        port = null;
    }

    private EPayRiconciliazioneVersamentiPSP getPort ( String endpointUrl ) {
        //        if ( service == null ) {
        try {
            service = new EPayRiconciliazioneVersamentiPSP_Service ( new URL ( endpointUrl ) );

            port = service.getEPayRiconciliazioneVersamentiPSPSOAP ();

            BindingProvider bp = (BindingProvider) port;

            Map<String, Object> context = bp.getRequestContext ();
            context.put ( BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl );

            Client client = ClientProxy.getClient ( port );
            client.getOutInterceptors ().add ( new LoggingInInterceptor () );
            client.getOutInterceptors ().add ( new LoggingOutInterceptor () );

            return port;

        } catch ( Exception e ) {
            service = null;
            port = null;
            throw new RuntimeException ( "Error contacting remote service", e );
        }
    }

    public ResponseType trasmettiFlussiPagoPA ( String endpointUrl, TrasmettiFlussiPagoPARequest parameters ) {
        return getPort ( endpointUrl ).trasmettiFlussiPagoPA ( parameters );
    }

    public RicercaProvvisoriPagoPAResponse ricercaProvvisoriPagoPA ( String endpointUrl, RicercaProvvisoriPagoPARequest parameters ) {
        long startTime = System.currentTimeMillis ();

        if ( logger.isDebugEnabled () ) {
            logger.debug ( SEPARATORE );
            logger.debug ( "RiconciliazioneVersamentiFacade.ricercaProvvisoriPagoPA( ... )" );
            logger.debug ( "Endpoint=" + endpointUrl );
            logger.debug ( "Input=\n" + XmlUtil.obj2Xml ( parameters ) );
            logger.debug ( "StartTime=" + startTime );
        }

        RicercaProvvisoriPagoPAResponse result = null;

        try {
            result = getPort ( endpointUrl ).ricercaProvvisoriPagoPA ( parameters );

            if ( logger.isDebugEnabled () ) {
                logger.debug ( "Output=\n" + XmlUtil.obj2Xml ( result ) );
            }

        } catch ( Exception e ) {

            if ( logger.isDebugEnabled () ) {
                logger.debug ( "ErrorClass=" + e.getClass ().getName () );
                logger.debug ( "ErrorMessage=" + e.getMessage () );
                logger.debug ( "ErrorStackTrace=" + ExceptionUtils.getStackTrace ( e ) );
            }

            throw e;

        } finally {

            if ( logger.isDebugEnabled () ) {
                long endTime = System.currentTimeMillis ();
                logger.debug ( "EndTime=" + endTime );
                logger.debug ( "Duration=" + ( endTime - startTime ) + " ms" );
                logger.debug ( SEPARATORE );
            }
        }

        return result;
    }

    public EsitoFlussiPagoPAResponse esitoFlussiPagoPA ( String endpointUrl, TestataTrasmissioneFlussiType parameters ) {
        long startTime = System.currentTimeMillis ();

        if ( logger.isDebugEnabled () ) {
            logger.debug ( SEPARATORE );
            logger.debug ( "RiconciliazioneVersamentiFacade.esitoFlussiPagoPA( ... )" );
            logger.debug ( "Endpoint=" + endpointUrl );
            logger.debug ( "Input=\n" + XmlUtil.obj2Xml ( parameters ) );
            logger.debug ( "StartTime=" + startTime );
        }

        EsitoFlussiPagoPAResponse result = null;

        try {
            result = getPort ( endpointUrl ).esitoFlussiPagoPA ( parameters );

            if ( logger.isDebugEnabled () ) {
                logger.debug ( "Output=\n" + XmlUtil.obj2Xml ( result ) );
            }

        } catch ( Exception e ) {

            if ( logger.isDebugEnabled () ) {
                logger.debug ( "ErrorClass=" + e.getClass ().getName () );
                logger.debug ( "ErrorMessage=" + e.getMessage () );
                logger.debug ( "ErrorStackTrace=" + ExceptionUtils.getStackTrace ( e ) );
            }

            throw e;

        } finally {

            if ( logger.isDebugEnabled () ) {
                long endTime = System.currentTimeMillis ();
                logger.debug ( "EndTime=" + endTime );
                logger.debug ( "Duration=" + ( endTime - startTime ) + " ms" );
                logger.debug ( SEPARATORE );
            }
        }

        return result;
    }

    public ResponseType trasmettiFlussiInErrorePagoPA ( String endpointUrl, TrasmettiFlussiInErrorePagoPARequest parameters ) {
        long startTime = System.currentTimeMillis ();

        if ( logger.isDebugEnabled () ) {
            logger.debug ( SEPARATORE );
            logger.debug ( "RiconciliazioneVersamentiFacade.trasmettiFlussiInErrorePagoPA( ... )" );
            logger.debug ( "Endpoint=" + endpointUrl );
            logger.debug ( "Input=\n" + XmlUtil.obj2Xml ( parameters ) );
            logger.debug ( "StartTime=" + startTime );
        }

        ResponseType result = null;

        try {
            result = getPort ( endpointUrl ).trasmettiFlussiInErrorePagoPA ( parameters );

            if ( logger.isDebugEnabled () ) {
                logger.debug ( "Output=\n" + XmlUtil.obj2Xml ( result ) );
            }

        } catch ( Exception e ) {

            if ( logger.isDebugEnabled () ) {
                logger.debug ( "ErrorClass=" + e.getClass ().getName () );
                logger.debug ( "ErrorMessage=" + e.getMessage () );
                logger.debug ( "ErrorStackTrace=" + ExceptionUtils.getStackTrace ( e ) );
            }

            throw e;

        } finally {

            if ( logger.isDebugEnabled () ) {
                long endTime = System.currentTimeMillis ();
                logger.debug ( "EndTime=" + endTime );
                logger.debug ( "Duration=" + ( endTime - startTime ) + " ms" );
                logger.debug ( SEPARATORE );
            }
        }

        return result;
    }
}
