/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.integration.epaywsosrv.impl;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import it.csi.epay.epaypaweb.exception.IntegrationException;
import it.csi.epay.epaypaweb.integration.EPaypaIntegrationServiceBase;
import it.csi.epay.epaypaweb.integration.epaywsosrv.EPaywsoBusinessService;
import it.csi.epay.epaypaweb.integration.epaywsosrv.interfaces.EpayPaEpaywsoSrvService;


public abstract class EpayPaEpywsoSrvServiceRoot extends EPaypaIntegrationServiceBase implements EpayPaEpaywsoSrvService {

    private static final String SERVICE_NS_URI = "http://www.csi.it/epay/epaywso/business";

    private static final String SERVICE_NAME = "EPaywsoBusinessService";

    private static final String BINDING_NAME = "EPaywsoBusinessServiceSOAP";


    protected final static String CODICE_APPLICATIVO_CLIENT = "EPAYPA";

    private URL endpointURL;

    private final String TARGET_NAMESPACE_URI = SERVICE_NS_URI;

    private final QName QNAME_WS_SERVICE = new QName ( TARGET_NAMESPACE_URI, SERVICE_NAME );

    private final QName QNAME_WS_PORT = new QName ( TARGET_NAMESPACE_URI, BINDING_NAME );

    protected EpayPaEpywsoSrvServiceRoot (String endpoint) throws IntegrationException {
        try {
//        	 String endpoint = ApplicationConfiguration.getApplicationConfiguration ()
//                     .getStringProperty ("epaywsosrv.url" );
        	
        	 endpointURL = new URL ( endpoint );
//            endpointURL = new URL ( "http://localhost:8080/epaywsosrv/EPaywsoBusinessService" );
            
           

        } catch ( MalformedURLException e ) {
            throw new IntegrationException ( e.getMessage (), e );
        }
    }


    protected EPaywsoBusinessService getSoapService () throws MalformedURLException {
        Service service = Service.create ( QNAME_WS_SERVICE );
        service.addPort ( QNAME_WS_PORT, SOAPBinding.SOAP11HTTP_BINDING, endpointURL.toString () );
        return service.getPort ( QNAME_WS_PORT, EPaywsoBusinessService.class );
    }

}
