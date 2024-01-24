/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package mdpiuvsrv;

import java.nio.charset.Charset;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;
import org.apache.ws.security.util.Base64;


public class Decrypt {

    protected static Logger log = Logger.getLogger ( "DECRYPT" );

    public static void main ( String [] args ) throws Exception {
        System.out.println ( operation ( false,
            "Wi7qhZ2ib2TcDs6GSAamlid8FFX/ph7ooqtthOW6Xr3rR6ZftFKlYADMeZiXHLmiODK+oEeSCytPpeeK5O8QMVlc9bLUThjlwYYhcrSIMn+S5fTM8flQzlU9JoUAHDEd" ) );

        System.out.println ( operation ( false,
            "VO/++qubyBA8mPCRErQwiw==" ) );

    }

    private static String operation ( boolean toEncode, String source ) throws Exception {
        byte [] b = Base64.decode ( new String ( "yYoThaIwl7gff2no8krJ6g==" ) );
        String sKey = new String ( b );
        System.out.println ( "sKey is " + sKey + " encoded in " + Charset.defaultCharset () );
        byte [] raw = sKey.getBytes ( "CP1252" );
        SecretKeySpec skeySpec = new SecretKeySpec ( raw, "AES" );
        System.out.println ( "Source string is " + source );
        byte [] processed = null;
        Cipher cipher = Cipher.getInstance ( "AES" );
        if ( toEncode == true ) {
            cipher.init ( Cipher.ENCRYPT_MODE, skeySpec );
        }
        if ( toEncode == false ) {
            cipher.init ( Cipher.DECRYPT_MODE, skeySpec );
        }
        if ( toEncode == true ) {
            processed = cipher.doFinal ( source.getBytes () );
        }
        if ( toEncode == false ) {
            processed = cipher.doFinal ( Base64.decode ( source ) );
        }
        String processedString = new String ( processed );
        if ( toEncode == true ) {
            processedString = Base64.encode ( processed );
        }
        System.out.println ( "Processed string: " + processedString );
        return processedString;
    }
}
