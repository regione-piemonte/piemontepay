/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.facade;

import java.net.URL;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.csi.epay.epaypacatalogsrv.business.enums.ParametriConfigurazione;
import it.csi.epay.epaypacatalogsrv.business.service.ConfigurazioneService;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.AggiornaCodiceVersamentoRequest;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.AggiornaCodiceVersamentoResponse;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.AggiornaEnteRequest;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.AggiornaEnteResponse;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.AggiornaRiferimentoContabileRequest;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.AggiornaRiferimentoContabileResponse;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.AnnullaOperazioneRequest;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.AnnullaOperazioneResponse;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.ConfermaOperazioneRequest;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.ConfermaOperazioneResponse;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.CoopApplicativaPEC;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.CoopApplicativaPEC_Service;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.TestResourcesRequest;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.TestResourcesResponse;

public class CoopApplicativaPECFacade extends ParentFacade implements CoopApplicativaPEC {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @Autowired
    private ConfigurazioneService configurazioneService;

    private CoopApplicativaPEC_Service service;

    private CoopApplicativaPEC port;

    public CoopApplicativaPECFacade ( String endpoint ) {
        super ();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext ( this );
        service = null;
        port = null;

        logger.debug ( "istanzio servizio CoopApplicativaPECFacade su endpoint " + endpoint );

        Integer connectionTimeout = configurazioneService.getParametro ( ParametriConfigurazione.WSO2_CONNECTION_TIMEOUT ).asInteger ( 30000 );
        Integer receiveTimeout = configurazioneService.getParametro ( ParametriConfigurazione.WSO2_RECEIVE_TIMEOUT ).asInteger ( 30000 );

        if ( logger.isTraceEnabled () ) {
            logger.trace ( "configuro con connectionTimeout : " + connectionTimeout );
            logger.trace ( "configuro con receiveTimeout : " + receiveTimeout );
        }

        try {

            service = new CoopApplicativaPEC_Service ( new URL ( endpoint ) );
            port = service.getCoopApplicativaPEC ();

            BindingProvider bp = (BindingProvider) port;
            Map<String, Object> context = bp.getRequestContext ();
            context.put ( BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint.contains ( "mdpcoopapplicativa" ) ? endpoint.replace ( "?WSDL", "" ) : endpoint );
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

    private CoopApplicativaPEC getPort () {
        return port;
    }

    @Override
    public AggiornaEnteResponse aggiornaEnte ( AggiornaEnteRequest parameters ) {
        logger.trace ( "eseguo operazione aggiornaEnte" );
        return getPort ().aggiornaEnte ( parameters );
    }

    @Override
    public AnnullaOperazioneResponse annullaOperazione ( AnnullaOperazioneRequest parameters ) {
        logger.trace ( "eseguo operazione annullaOperazione" );
        return getPort ().annullaOperazione ( parameters );
    }

    @Override
    public ConfermaOperazioneResponse confermaOperazione ( ConfermaOperazioneRequest parameters ) {
        logger.trace ( "eseguo operazione confermaOperazione" );
        return getPort ().confermaOperazione ( parameters );
    }

    @Override
    public AggiornaCodiceVersamentoResponse aggiornaCodiceVersamento ( AggiornaCodiceVersamentoRequest parameters ) {
        logger.trace ( "eseguo operazione aggiornaCodiceVersamento" );
        return getPort ().aggiornaCodiceVersamento ( parameters );
    }

    @Override
    public AggiornaRiferimentoContabileResponse aggiornaRiferimentoContabile ( AggiornaRiferimentoContabileRequest parameters ) {
        logger.trace ( "eseguo operazione aggiornaRiferimentoContabile" );
        return getPort ().aggiornaRiferimentoContabile ( parameters );
    }

    @Override
    public TestResourcesResponse testResources ( TestResourcesRequest parameters ) {
        logger.trace ( "eseguo operazione testResources" );
        return getPort ().testResources ( parameters );
    }

}
