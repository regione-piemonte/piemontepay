/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.launcher;

import java.util.Map;

import javax.xml.ws.BindingProvider;

import it.csi.epay.epayjob.business.epaypacatalogsrv.EpaypacatalogsrvService;
import it.csi.epay.epayjob.interfacews.epaypacatalogsrv.ElaboraCodaEmailInput;
import it.csi.epay.epayjob.interfacews.epaypacatalogsrv.ElaboraCodaEmailOutput;
import it.csi.epay.epayjob.interfacews.epaypacatalogsrv.Epaypacatalogsrv;
import it.csi.epay.epayjob.utilities.CommonCliUtil;
import it.csi.epay.epayjob.utilities.LogUtil;

/**
 *
 */
public class InvioMailEpaypacatalog extends AbstractLauncher {

    private static LogUtil log = new LogUtil ( InvioMailEpaypacatalog.class );

    private static EpaypacatalogsrvService service;

    private static Integer OK_CONSTANT_EPAYCATALOG = 200;

    private static Epaypacatalogsrv port;

    private static Epaypacatalogsrv getPort ( String urlEndPoint ) {
        if ( service == null ) {
            try {
                service = new EpaypacatalogsrvService ( wsdl ( urlEndPoint ) );
                port = service.getEpaypacatalogsrvPort ();

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
            log.info ( methodName, "Chiamata al servizio elaboraCodaMail in corso..." );
            ElaboraCodaEmailOutput result = getPort ( host ).elaboraCodaEmail ( new ElaboraCodaEmailInput () );
            if ( null == result || null == result.getCodiceStato () ) {
                log.error ( methodName, "Errore in fase di invocazione del servizio richiediEsitoFlussiDaSistemaContabile." );
            } else if ( !OK_CONSTANT_EPAYCATALOG.equals ( result.getCodiceStato () ) ) {
                log.error ( methodName, "Errore in fase di invocazione del servizio richiediEsitoFlussiDaSistemaContabile: " + result.getDescrizioneEsito () );
            } else {
                log.info ( methodName, "Servizio richiediEsitoFlussiDaSistemaContabile lanciato con successo!" );
            }
        } catch ( Exception e ) {
            log.error ( methodName, e );
        }
        log.infoEnd ( methodName );
        System.exit ( 0 );
    }

}
