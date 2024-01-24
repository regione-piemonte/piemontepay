/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.launcher;

import it.csi.epay.epayjob.utilities.LogUtil;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;


/**
 *
 */
public abstract class AbstractLauncher {

    private static final LogUtil log = new LogUtil ( AbstractLauncher.class );

    protected static final String CONFIG_PROPERTIES = "../config/config.properties";

    protected static final String CONFIG_EPAYMODRIC_ENDPOINT = "service.epaymodricws.endpoint";

    protected static final String CONFIG_EPAYPACATALOG_ENDPOINT = "service.epaypacatalogsrv.endpoint";

    protected static Properties PROPERTIES;

    protected static Properties loadProperties () {
        final String methodName = "loadProperties";
        log.infoStart ( methodName );
        try ( FileInputStream inputStream = new FileInputStream ( CONFIG_PROPERTIES ) ) {
            Properties properties = new Properties ();
            properties.load ( inputStream );
            return properties;
        } catch ( Exception e ) {
            log.debug ( methodName, "errore lettura parametri. " + e.getMessage () );
            return null;
        } finally {
            log.infoEnd ( methodName );
        }
    }

    protected static URL wsdl ( String endpoint ) {
        if ( endpoint == null ) {
            return null;
        }

        try {
            if ( endpoint.toUpperCase ().endsWith ( "?WSDL" ) ) {
                return new URL ( endpoint );
            } else {
                if ( !endpoint.contains ( "?" ) ) {
                    return new URL ( endpoint + "?WSDL" );
                } else {
                    return new URL ( endpoint + "&WSDL" );
                }
            }
        } catch ( Exception e ) {
            throw new RuntimeException ( e );
        }
    }
}
