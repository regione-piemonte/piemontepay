/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.facade;

import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.springframework.stereotype.Service;

import it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws.Epaymodric;
import it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws.EpaymodricException_Exception;
import it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws.EpaymodricService;
import it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws.Exception_Exception;
import it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws.UnrecoverableException_Exception;


@Service
public class EpaymodricWsFacade extends ParentFacade {

    private EpaymodricService service;

    private Epaymodric port;

    public EpaymodricWsFacade () {
        super ();
        service = null;
        port = null;
    }

    private Epaymodric getPort ( String endPointUrl ) {
        if ( service == null ) {
            try {

                service = new EpaymodricService ();
                port = service.getEpaymodricPort ();

                BindingProvider bp = (BindingProvider) port;

                Map<String, Object> context = bp.getRequestContext ();
                context.put ( BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPointUrl );

                return port;

            } catch ( Exception e ) {
                service = null;
                port = null;
                throw new RuntimeException ( "Error contacting remote service", e );
            }
        } else {
            return port;
        }
    }

    public String testResources ( String endPointUrl, String dummy )
                    throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception {
        return getPort ( endPointUrl ).testResources ( dummy );
    }

}
