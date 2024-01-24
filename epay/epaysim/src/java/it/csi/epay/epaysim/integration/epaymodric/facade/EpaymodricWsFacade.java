/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.integration.epaymodric.facade;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.springframework.stereotype.Service;

import it.csi.epay.epaysim.integration.epaymodric.stubs.EpaymodricAcquisizione;
import it.csi.epay.epaysim.integration.epaymodric.stubs.EpaymodricAcquisizioneService;
import it.csi.epay.epaysim.integration.epaymodric.stubs.EpaymodricException_Exception;
import it.csi.epay.epaysim.integration.epaymodric.stubs.Exception_Exception;
import it.csi.epay.epaysim.integration.epaymodric.stubs.ResponseType;
import it.csi.epay.epaysim.integration.epaymodric.stubs.TrasmettiFlussoRendicontazioneExtRequestType;
import it.csi.epay.epaysim.integration.epaymodric.stubs.UnrecoverableException_Exception;
import it.csi.epay.epaysim.integration.facade.ParentFacade;


@Service
public class EpaymodricWsFacade extends ParentFacade implements EpaymodricAcquisizione {

    private EpaymodricAcquisizioneService service;

    private EpaymodricAcquisizione port;

    private String endPointUrl;

    public EpaymodricWsFacade () {
        super ();
    }

    public EpaymodricWsFacade ( String endpointUrl ) {
        super ();
        this.endPointUrl = endpointUrl;
        //endPointUrl = java.util.ResourceBundle.getBundle("config").getString("service.epaymodricws.endpoint");
        //endPointUrl = "http://localhost:8081/epaymodricApplEpaymodricWs/EpaymodricAcquisizioneService";
        service = null;
        port = null;
    }

    private EpaymodricAcquisizione getPort () {
        if ( service == null ) {
            try {
                try {
                    service = new EpaymodricAcquisizioneService ( new URL ( endPointUrl + "?WSDL" ) );
                } catch ( MalformedURLException e ) {
                    throw new RuntimeException ( e );
                }
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

    @Override
    public ResponseType trasmettiFlussoRendicontazioneExt ( TrasmettiFlussoRendicontazioneExtRequestType parameters )
                    throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception {

        return getPort ().trasmettiFlussoRendicontazioneExt ( parameters );
    }
}
