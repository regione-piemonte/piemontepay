/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;


public class EncryptUtil {

	private static final String sbKey = ""; // insert key here

	private static final String ALGORITHM = "AES";

	private static String encrypt ( final String data ) {
		var decodedKey = Base64.getDecoder ().decode ( sbKey );
		try {
			var cipher = Cipher.getInstance ( ALGORITHM );
			var originalKey = new SecretKeySpec ( Arrays.copyOf ( decodedKey, 16 ), ALGORITHM );
			cipher.init ( Cipher.ENCRYPT_MODE, originalKey );
			var cipherText = cipher.doFinal ( data.getBytes ( StandardCharsets.UTF_8 ) );
			return Base64.getEncoder ().encodeToString ( cipherText );
		} catch ( Exception e ) {
			throw new RuntimeException ( "Error occured while encrypting data", e );
		}
	}

	public static void main ( String[] args ) {
		System.out.println ( encrypt ( args[0] ) );
	}
}
