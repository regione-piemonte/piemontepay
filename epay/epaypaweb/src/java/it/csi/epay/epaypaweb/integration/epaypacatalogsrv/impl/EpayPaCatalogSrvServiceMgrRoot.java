/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.integration.epaypacatalogsrv.impl;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import it.csi.epay.epaypaweb.exception.EpayPaCatalogSrvProtocolException;
import it.csi.epay.epaypaweb.exception.EpayPaCatalogSrvRemoteException;
import it.csi.epay.epaypaweb.exception.IntegrationException;
import it.csi.epay.epaypaweb.integration.EPaypaIntegrationServiceBase;
import it.csi.epay.epaypaweb.integration.epaypacatalogsrv.dto.ParentOutput;
import it.csi.epay.epaypaweb.integration.epaypacatalogsrv.interf.EpayPaCatalogSrvServiceMgr;
import it.csi.epay.epaypaweb.integration.epaypacatalogsrv.service.Epaypacatalogsrv;


public abstract class EpayPaCatalogSrvServiceMgrRoot extends EPaypaIntegrationServiceBase implements EpayPaCatalogSrvServiceMgr {

    private static final String SERVICE_NS_URI = "http://business.epaypacatalogsrv.epay.csi.it/";

    private static final String SERVICE_NAME = "Epaypacatalogsrv";

    private static final String BINDING_NAME = "EpaypacatalogsrvServiceSoapBinding";

    private final static String CODICE_ESITO_OK = "OK";

    protected final static String CODICE_APPLICATIVO_CLIENT = "EPAYPA";

    private URL endpointURL;

    private final String TARGET_NAMESPACE_URI = SERVICE_NS_URI;

    private final QName QNAME_WS_SERVICE = new QName ( TARGET_NAMESPACE_URI, SERVICE_NAME );

    private final QName QNAME_WS_PORT = new QName ( TARGET_NAMESPACE_URI, BINDING_NAME );

    protected EpayPaCatalogSrvServiceMgrRoot ( String endpointStr ) throws IntegrationException {
        try {
            endpointURL = new URL ( endpointStr );

        } catch ( MalformedURLException e ) {
            throw new IntegrationException ( e.getMessage (), e );
        }
    }

    protected void validaEsito ( ParentOutput output ) throws EpayPaCatalogSrvRemoteException {
        if ( output == null ) {
            throw new EpayPaCatalogSrvProtocolException ( "Output nullo non atteso" );
        }

        if ( CODICE_ESITO_OK.equals ( output.getCodiceEsito () ) ) {
            return;
        } else {
            throw new EpayPaCatalogSrvRemoteException ( "Errore restituito dal servizio: " +
                output.getCodiceEsito () + " - " + output.getDescrizioneEsito () );
        }
    }

    protected Epaypacatalogsrv getSoapService () throws MalformedURLException {
        Service service = Service.create ( QNAME_WS_SERVICE );
        service.addPort ( QNAME_WS_PORT, SOAPBinding.SOAP11HTTP_BINDING, endpointURL.toString () );
        return service.getPort ( QNAME_WS_PORT, Epaypacatalogsrv.class );
    }

}
