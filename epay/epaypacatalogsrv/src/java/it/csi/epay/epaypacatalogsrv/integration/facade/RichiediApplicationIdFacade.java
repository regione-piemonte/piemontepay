/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.facade;

import java.net.URL;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.csi.epay.epaypacatalogsrv.business.enums.ParametriConfigurazione;
import it.csi.epay.epaypacatalogsrv.business.service.ConfigurazioneService;
import it.csi.epay.epaypacatalogsrv.integration.stubs.richiediapplicationid.RichiediApplicationId;
import it.csi.epay.epaypacatalogsrv.integration.stubs.richiediapplicationid.RichiediApplicationIdRequest;
import it.csi.epay.epaypacatalogsrv.integration.stubs.richiediapplicationid.RichiediApplicationIdResponse;
import it.csi.epay.epaypacatalogsrv.integration.stubs.richiediapplicationid.RichiediApplicationId_Service;
import it.csi.epay.epaypacatalogsrv.vo.ConfigurazioneVO;


@Service
public class RichiediApplicationIdFacade extends ParentFacade implements RichiediApplicationId {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    private static final QName SERVICE_NAME = new QName ( "http://www.csi.it/epay/epaywso/richiediapplicationid", "RichiediApplicationId" );

    @Autowired
    private ConfigurazioneService configurazioneService;

    private RichiediApplicationId_Service service;

    private RichiediApplicationId port;

    public RichiediApplicationIdFacade () {
        // TODO Auto-generated constructor stub
    }

    public RichiediApplicationIdFacade ( String endpoint ) {
        super ();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext ( this );
        service = null;
        port = null;

        ConfigurazioneVO configurazioneVO = configurazioneService.getParametro ( ParametriConfigurazione.RICHIEDI_APPLICATION_ID_ENDPOINT );

        if ( null == configurazioneVO || StringUtils.isBlank ( configurazioneVO.getValore () ) ) {
            throw new IllegalStateException (
                "Impossibile recuperare il parametro di configurazione" + ParametriConfigurazione.RICHIEDI_APPLICATION_ID_ENDPOINT.toString () );
        }

        logger.debug ( "istanzio servizio RichiediApplicationIdFacade su endpoint " + configurazioneVO.getValore () );

        Integer connectionTimeout = configurazioneService.getParametro ( ParametriConfigurazione.WSO2_CONNECTION_TIMEOUT ).asInteger ( 30000 );
        Integer receiveTimeout = configurazioneService.getParametro ( ParametriConfigurazione.WSO2_RECEIVE_TIMEOUT ).asInteger ( 30000 );

        if ( logger.isTraceEnabled () ) {
            logger.trace ( "configuro con connectionTimeout : " + connectionTimeout );
            logger.trace ( "configuro con receiveTimeout : " + receiveTimeout );
        }

        try {

            service = new RichiediApplicationId_Service ( new URL ( configurazioneVO.getValore () ), SERVICE_NAME );
            port = service.getRichiediApplicationId ();

            BindingProvider bp = (BindingProvider) port;
            Map<String, Object> context = bp.getRequestContext ();
            context.put ( BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint.replace ( "?WSDL", "" ) );
            Client client = ClientProxy.getClient ( port );
            HTTPConduit http = (HTTPConduit) client.getConduit ();
            HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy ();
            httpClientPolicy.setConnectionTimeout ( connectionTimeout );
            httpClientPolicy.setReceiveTimeout ( receiveTimeout );
            http.setClient ( httpClientPolicy );

            if ( configurazioneService.getParametro ( ParametriConfigurazione.WSO2_LOGGING_PAYLOAD ).asBoolean ( false ) ) {
                logger.trace ( "configuro con LoggingOutInterceptor abilitato" );
                logger.trace ( "configuro con LoggingInInterceptor abilitato" );
                client.getOutInterceptors ().add ( new LoggingOutInterceptor () );
                client.getOutInterceptors ().add ( new LoggingInInterceptor () );
            }

        } catch ( Exception e ) {
            service = null;
            port = null;
            logger.error ( "errore nella creazione del servizio remoto", e );
            throw new RuntimeException ( "Errore nella creazione del servizio remoto", e );
        }
    }

    private RichiediApplicationId getPort () {
        return port;
    }


    @Override
    public RichiediApplicationIdResponse richiediApplicationId ( RichiediApplicationIdRequest parameters ) {
        logger.trace ( "eseguo operazione richiediApplicationId" );
        return getPort ().richiediApplicationId ( parameters );
    }

}
