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
public class RichiediEsitoFlussiDaSistemaContabile extends AbstractLauncher {

    private static LogUtil log = new LogUtil ( RichiediEsitoFlussiDaSistemaContabile.class );

    private static BatchService service;

    private static EpaymodricJob port;

    private static EpaymodricJob getPort ( String urlEndPoint ) {
        if ( service == null ) {
            try {
                service = new BatchService ();
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
            log.info ( methodName, "Chiamata al servizio richiediEsitoFlussiDaSistemaContabile in corso..." );
//            DtoOutputWsEsito result = getPort ( host ).richiediEsitoFlussiDaSistemaContabile (); :TODO Rivedere servizio ed agganciarlo correttamente
//            if ( null == result || null == result.getEsito () ) {
//                log.error ( methodName, "Errore in fase di invocazione del servizio richiediEsitoFlussiDaSistemaContabile." );
//            } else if ( !"000".equals ( result.getEsito () ) ) {
//                log.error ( methodName, "Errore in fase di invocazione del servizio richiediEsitoFlussiDaSistemaContabile: " + result.getDescrizione () );
//            } else {
//                log.info ( methodName, "Servizio richiediEsitoFlussiDaSistemaContabile lanciato con successo!" );
//            }
        } catch ( Exception e ) {
            log.error ( methodName, e );
        }
        log.infoEnd ( methodName );
        System.exit ( 0 );
    }

}
