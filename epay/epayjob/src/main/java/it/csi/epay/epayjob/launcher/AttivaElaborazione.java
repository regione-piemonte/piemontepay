/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.launcher;

import java.util.Map;

import javax.xml.ws.BindingProvider;

import it.csi.epay.epayjob.business.epaymodric.BatchService;
import it.csi.epay.epayjob.interfacews.epaymodric.DtoInputVuoto;
import it.csi.epay.epayjob.interfacews.epaymodric.DtoOutputWsMotoreDiRiconciliazione;
import it.csi.epay.epayjob.interfacews.epaymodric.EpaymodricJob;
import it.csi.epay.epayjob.utilities.CommonCliUtil;
import it.csi.epay.epayjob.utilities.LogUtil;

/**
 *
 */
public class AttivaElaborazione extends AbstractLauncher {

    private static LogUtil log = new LogUtil ( AttivaElaborazione.class );

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
            System.out.println("server ws : "+cli.getServers());
            log.info ( methodName, "server ws : " + cli.getServers () );
            String host = cli.getServers ();
            log.info ( methodName, "end point : " + host );
            log.info ( methodName, "Chiamata al servizio attivaElaborazione in corso..." );
            DtoOutputWsMotoreDiRiconciliazione result = getPort ( host ).attivaElaborazione ( new DtoInputVuoto () );
            if ( null == result || null == result.getEsito () ) {
                log.error ( methodName, "Errore in fase di invocazione del servizio attivaElaborazione." );
            } else if ( !"000".equals ( result.getEsito ().getEsito () ) ) {
                log.error ( methodName, "Errore in fase di invocazione del servizio attivaElaborazione: " + result.getEsito ().getDescrizione () );
            } else {
                log.info ( methodName, "Servizio attivaElaborazione lanciato con successo!" );
            }
        } catch ( Exception e ) {
            log.error ( methodName, e );
            e.printStackTrace ();
            System.out.println ( "--- inizio stack trace ---" );
            System.out.println ( e.getStackTrace () );
            System.out.println ( "--- fine stack trace ---" );
        }
        log.infoEnd ( methodName );
        System.exit ( 0 );
    }

}
