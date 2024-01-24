/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.launcher;

import java.util.Map;

import javax.xml.ws.BindingProvider;

import it.csi.epay.epayjob.business.epaymodric.BatchService;
import it.csi.epay.epayjob.interfacews.epaymodric.EpaymodricJob;
import it.csi.epay.epayjob.utilities.CommonCliUtil;
import it.csi.epay.epayjob.utilities.LogUtil;

/**
 *
 */
public class InvioMailEpaymodric extends AbstractLauncher {

    private static LogUtil log = new LogUtil ( InvioMailEpaymodric.class );

    private static BatchService service;

    private static EpaymodricJob port;

    private static EpaymodricJob getPort ( String urlEndPoint ) {
        if ( service == null ) {
            try {
                service = new BatchService ( wsdl ( urlEndPoint ) );
                port = service.getBatchServicePort ();

                BindingProvider bp = (BindingProvider) port;

                Map<String, Object> context = bp.getRequestContext ();
                context.put ( BindingProvider.ENDPOINT_ADDRESS_PROPERTY, urlEndPoint );

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

    /**
     * @param args
     */
    public static void main ( String [] args ) {
        final String methodName = "main";
        log.infoStart ( methodName );
        try {
            CommonCliUtil cli = new CommonCliUtil ( args );
            log.info ( methodName, "server ws : " + cli.getServers () );
            String host = cli.getServers ();
            log.info ( methodName, "end point : " + host );
            log.info ( methodName, "Chiamata al servizio emailDequeue in corso..." );
            //getPort ( host ).emailDequeue ( new DtoInputVuoto () ); :TODO Rivedere servizio ed agganciarlo correttamente
            log.info ( methodName, "Servizio emailDequeue lanciato con successo!" );
        } catch ( Exception e ) {
            log.error ( methodName, e );
        }
        log.infoEnd ( methodName );
        System.exit ( 0 );
    }

}
