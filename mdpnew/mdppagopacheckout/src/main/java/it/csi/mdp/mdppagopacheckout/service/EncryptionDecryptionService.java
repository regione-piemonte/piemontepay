/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.service;

import io.quarkus.runtime.StartupEvent;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Base64;


@ApplicationScoped
@Transactional
public class EncryptionDecryptionService {

	private String sbKey;

	void onStart ( @Observes StartupEvent event ) throws Exception {
		var inputStream = Thread.currentThread ().getContextClassLoader ().getResourceAsStream ( "skeydb.txt" );
		assert inputStream != null;
		var reader = new BufferedReader ( new InputStreamReader ( inputStream ) );
		sbKey = reader.readLine ();
		reader.close ();
	}

	public String decrypt ( final String encryptedString ) {
		var decodedKey = Base64.getDecoder ().decode ( sbKey );
		try {
			var ALGORITHM = "AES";
			var cipher = Cipher.getInstance ( ALGORITHM );
			var originalKey = new SecretKeySpec ( Arrays.copyOf ( decodedKey, 16 ), ALGORITHM );
			cipher.init ( Cipher.DECRYPT_MODE, originalKey );
			var cipherText = cipher.doFinal ( Base64.getDecoder ().decode ( encryptedString ) );
			return new String ( cipherText );
		} catch ( Exception e ) {
			throw new RuntimeException ( "Error occured while decrypting data", e );
		}
	}
}
