/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


public class MacUtil {

	public static String generateMacPagopa ( String passphrase, String idTransazione, String urlBase, String iuv ) throws NoSuchAlgorithmException {
		var sToDigest = passphrase + "%%%%" + idTransazione + urlBase + iuv + "%%%%" + passphrase;
		var digest = MessageDigest.getInstance ( "SHA-256" );
		var bMac = digest.digest ( sToDigest.getBytes () );
		var mac = Base64.getEncoder ().encodeToString ( bMac );
		// questi caratteri non li vogliamo nelle url
		// URLEncoder.encode non usato poiché URLEncoder usa "+" per rappresentare spazi
		mac = mac.replaceAll("\\+", "a");
		mac = mac.replaceAll("/", "b");
		mac = mac.replaceAll("&", "c");
		mac = mac.replaceAll("=", "d");
		mac = mac.replaceAll("\\?", "e");
		mac = mac.replaceAll("#", "f");
		mac = mac.replaceAll("%", "g");
		return mac.substring ( 0, 35 );
	}

}
