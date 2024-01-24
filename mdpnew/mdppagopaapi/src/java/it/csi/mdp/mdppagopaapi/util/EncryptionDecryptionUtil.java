/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;


public class EncryptionDecryptionUtil {

    private EncryptionDecryptionUtil () {
        throw new IllegalStateException ( "Utility class" );
    }

    public static String encrypt ( final String data, final String sbKey ) {

        byte [] decodedKey = Base64.getDecoder ().decode ( sbKey );

        try {
            Cipher cipher = Cipher.getInstance ( "AES" );

            SecretKey originalKey = new SecretKeySpec ( Arrays.copyOf ( decodedKey, 16 ), "AES" );

            cipher.init ( Cipher.ENCRYPT_MODE, originalKey );

            byte [] cipherText = cipher.doFinal ( data.getBytes ( StandardCharsets.UTF_8 ) );

            return Base64.getEncoder ().encodeToString ( cipherText );

        } catch ( Exception e ) {
            throw new RuntimeException (
                "Error occured while encrypting data", e );
        }

    }

    public static String decrypt ( final String encryptedString, final String sbKey ) {

        byte [] decodedKey = Base64.getDecoder ().decode ( sbKey );

        try {
            Cipher cipher = Cipher.getInstance ( "AES" );

            SecretKey originalKey = new SecretKeySpec ( Arrays.copyOf ( decodedKey, 16 ), "AES" );

            cipher.init ( Cipher.DECRYPT_MODE, originalKey );

            byte [] cipherText = cipher.doFinal ( Base64.getDecoder ().decode ( encryptedString ) );

            return new String ( cipherText );

        } catch ( Exception e ) {
            throw new RuntimeException (
                "Error occured while decrypting data", e );
        }
    }

}
