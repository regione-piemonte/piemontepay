/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.util;

import java.net.NetworkInterface;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class FingerprintHelper {

    private static Logger logger = LoggerFactory.getLogger ( FingerprintHelper.class );

    private volatile static String instanceId;

    private volatile static Object instanceIdLock = new Object ();

    private volatile static Random randomGenerator;

    static {
        getInstanceId ();
    }

    public static Random getRandomGenerator () {
        return randomGenerator;
    }

    public static String getInstanceId () {
        return getInstanceId ( false );
    }

    public static String getInstanceId ( boolean threadDependent ) {

        if ( instanceId == null ) {
            synchronized ( instanceIdLock ) {
                if ( instanceId == null ) {
                    try {
                        instanceId = computeInstanceId ();
                    } catch ( Exception e ) {
                        throw new RuntimeException ( "Error computing unique instance ID", e );
                    }
                    logger.debug ( "computed instance UID: " + instanceId );
                    randomGenerator = new Random ( instanceId.hashCode () );
                }
            }
        }

        if ( threadDependent ) {
            return instanceId + ";THREAD=" + Thread.currentThread ().getName ();
        } else {
            return instanceId + ";THREAD=INDEPENDENT";
        }
    }

    private static String computeInstanceId () throws Exception {

        StringBuilder sb = new StringBuilder ();

        sb.append ( "UUID=" );
        sb.append ( UUID.randomUUID ().toString () );
        sb.append ( ";" );

        sb.append ( "CT=" );
        sb.append ( LocalDateTime.now ().format ( DateTimeFormatter.ofPattern ( "uuuu-MM-dd'T'HH:mm:ss.SSSSSSSSS" ) ) );
        sb.append ( ";" );

        Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces ();
        while ( nis.hasMoreElements () ) {
            NetworkInterface ni = nis.nextElement ();
            if ( ni.isVirtual () || ni.getHardwareAddress () == null || ni.getHardwareAddress ().length < 1 || !ni.isUp () || ni.isLoopback () ) {
                logger.trace ( "(virtual) " + ni.getName () );
            } else {
                logger.trace ( "NETWORK INTERFACE: " + ni.getName () + " " + ni.getDisplayName () );

                byte [] mac = ni.getHardwareAddress ();

                StringBuilder sb2 = new StringBuilder ();
                for ( int i = 0; i < mac.length; i++ ) {
                    sb2.append ( String.format ( "%02X%s", mac [i], ( i < mac.length - 1 ) ? ":" : "" ) );
                }

                String macStr = sb2.toString ();

                logger.trace ( "MAC: " + macStr );
                sb.append ( "NI=" );
                sb.append ( macStr );
                sb.append ( ";" );

            }
        }

        return sb.toString ();
    }

}
