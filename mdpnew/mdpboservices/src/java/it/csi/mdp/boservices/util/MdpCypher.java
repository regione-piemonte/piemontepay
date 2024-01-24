/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;
import org.apache.ws.security.util.Base64;
public class MdpCypher
{
	public static String getMD5 (String param ) throws NoSuchAlgorithmException ,UnsupportedEncodingException
	{
		BASE64Encoder encoder = new BASE64Encoder();
		MessageDigest md = MessageDigest.getInstance("MD5");
		String sStringToDigest = param;

			md.update(sStringToDigest.getBytes("ISO-8859-1"));

		byte[] bMac = md.digest();
		String sMacEncoded = encoder.encodeBuffer(bMac);
		sMacEncoded = sMacEncoded.substring(0,24);
		return sMacEncoded;
	}
	public static String getSHA1 (String text ) throws NoSuchAlgorithmException, UnsupportedEncodingException  { 
	    MessageDigest md;
	    md = MessageDigest.getInstance("SHA-1");
	    byte[] sha1hash = new byte[40];
	    md.update(text.getBytes("iso-8859-1"), 0, text.length());
	    sha1hash = md.digest();
	    return new String(sha1hash);
	}
	public static String getSHA164 (String text ) throws NoSuchAlgorithmException, UnsupportedEncodingException  { 
	    MessageDigest sha;
	    sha = MessageDigest.getInstance("SHA-1");
	    byte[] sha1hash = new byte[40];
	    
	    sha1hash = text.getBytes("UTF-8");
	    sha.reset();
	    sha.update(sha1hash);
	    String passwdDigest = Base64.encode(sha.digest());
	    BASE64Encoder encoder = new BASE64Encoder();
	    passwdDigest = encoder.encode(java.security.MessageDigest.getInstance("SHA1").digest(sha1hash));
	    return passwdDigest;
	}
	private static String convertToHex(byte[] data) { 
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) { 
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do { 
                if ((0 <= halfbyte) && (halfbyte <= 9)) 
                    buf.append((char) ('0' + halfbyte));
                else 
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while(two_halfs++ < 1);
        } 
        return buf.toString();
    } 
}
