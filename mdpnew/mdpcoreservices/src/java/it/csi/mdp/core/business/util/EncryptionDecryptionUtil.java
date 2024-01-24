/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.util.Base64;

public class EncryptionDecryptionUtil {

    private EncryptionDecryptionUtil () {
    }

    public static String encrypt ( final String data, final String sbKey ) {

        if ( sbKey != null && data != null ) {
            byte [] raw = sbKey.getBytes ();
            SecretKeySpec skeySpec = new SecretKeySpec ( raw, "AES" );
            byte [] encrypted = null;
            try {
                Cipher cipher = Cipher.getInstance ( "AES" );
                cipher.init ( Cipher.ENCRYPT_MODE, skeySpec );
                encrypted = cipher.doFinal ( data.getBytes () );
                return Base64.encode ( encrypted );
            } catch ( InvalidKeyException e ) {
                e.printStackTrace ();
            } catch ( NoSuchAlgorithmException e ) {
                e.printStackTrace ();
            } catch ( NoSuchPaddingException e ) {
                e.printStackTrace ();
            } catch ( IllegalBlockSizeException e ) {
                e.printStackTrace ();
            } catch ( BadPaddingException e ) {
                e.printStackTrace ();
            }
        }
        return null;
    }

    public static String decrypt ( final String encryptedString, final String sbKey ) {
        if ( sbKey != null && encryptedString != null ) {
            byte [] raw = sbKey.getBytes ();
            SecretKeySpec skeySpec = new SecretKeySpec ( raw, "AES" );

            // Instantiate the cipher

            byte [] original = null;
            byte [] encrypted = encryptedString.getBytes ();
            try {
                encrypted = Base64.decode ( new String ( encrypted ) );
                Cipher cipher = Cipher.getInstance ( "AES" );

                cipher.init ( Cipher.DECRYPT_MODE, skeySpec );
                original = cipher.doFinal ( encrypted );
                return new String ( original );
            } catch ( InvalidKeyException e ) {
                e.printStackTrace ();
            } catch ( NoSuchAlgorithmException e ) {
                e.printStackTrace ();
            } catch ( NoSuchPaddingException e ) {
                e.printStackTrace ();
            } catch ( IllegalBlockSizeException e ) {
                e.printStackTrace ();
            } catch ( BadPaddingException e ) {
                e.printStackTrace ();
            } catch ( WSSecurityException e ) {
                e.printStackTrace ();
            }
        }
        return null;
    }

    public static String asHex ( byte buf[] ) {
        StringBuilder strbuf = new StringBuilder ( buf.length * 2 );
        int i;

        for ( i = 0; i < buf.length; i++ ) {
            if ( ( (int) buf [i] & 0xff ) < 0x10 )
                strbuf.append ( "0" );

            strbuf.append ( Long.toString ( (int) buf [i] & 0xff, 16 ) );
        }
        return strbuf.toString ();
    }
    
}
