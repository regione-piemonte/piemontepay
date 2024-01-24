/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.facade;

import java.net.URL;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.springframework.stereotype.Service;

import it.csi.epay.epaymodric.util.wsdl.epayflussicompletipsp.EPayFlussiCompletiPSP;
import it.csi.epay.epaymodric.util.wsdl.epayflussicompletipsp.EPayFlussiCompletiPSP_Service;
import it.csi.epay.epaymodric.util.wsdl.epayflussicompletipsp.TrasmettiFlussiPagoPARequest;
import it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp.ResponseType;



@Service
public class FlussiCompletiFacade {

    private EPayFlussiCompletiPSP_Service service;

    private EPayFlussiCompletiPSP port;

    public FlussiCompletiFacade () {
        super ();
        service = null;
        port = null;
    }

    private EPayFlussiCompletiPSP getPort ( String endpointUrl ) {
        try {
            service = new EPayFlussiCompletiPSP_Service ( new URL ( endpointUrl ) );

            port = service.getEPayFlussiCompletiPSPSOAP();

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

    public ResponseType trasmettiFlussiCompletiPagoPA ( String endpointUrl, TrasmettiFlussiPagoPARequest parameters ) {
        return getPort ( endpointUrl ).trasmettiFlussiPagoPA ( parameters );
    }
}
