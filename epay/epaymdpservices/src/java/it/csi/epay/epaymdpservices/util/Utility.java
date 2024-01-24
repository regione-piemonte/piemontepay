/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymdpservices.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class Utility {
	
	public static String calcolaMAC(String passphrase, String applicationId, String iuv, String idMsgRicevuta, String timestamp) {

		String mac = "";

		String sToDigest = passphrase + "%%%%" + applicationId + iuv + idMsgRicevuta + timestamp + "%%%%" + passphrase;
		byte[] bMac = DigestUtils.sha256(sToDigest.getBytes());
		mac = Base64.encodeBase64String(bMac);
		mac = mac.substring(0, 35);
		
		return mac;
	}
	

	public static String calcolaMacVersamento(String passphrase, String applicationId, String identificativoUnivocoVersamento, String timestamp, String idMsgRicevuta) {
	    String sToDigest = passphrase + "%%%%" + applicationId + identificativoUnivocoVersamento + idMsgRicevuta  + timestamp + "%%%%" + passphrase;
		byte[] bMac = DigestUtils.sha256(sToDigest.getBytes());
		String mac = Base64.encodeBase64String (bMac);
		mac = mac.substring (0,35);
		return mac;
	}

	
	public static String calcolaMACModello3(String passphrase, String iuv, String timestamp) {
		
		String mac = "";
		
		String sToDigest = passphrase + "%%%%" + iuv  + timestamp + "%%%%" + passphrase;
		byte[] bMac = DigestUtils.sha256(sToDigest.getBytes());
		mac = Base64.encodeBase64String(bMac);
		mac = mac.substring(0, 35);
		
		return mac;
	}
	
	
	public static String generaMacChiediDatiPagamento(String passphrase, String identificativoUnivocoVersamento, String importoSingoloVersamento, String timestamp) {
		String sToDigest = passphrase + "%%%%" + identificativoUnivocoVersamento + importoSingoloVersamento + timestamp + "%%%%" + passphrase;
		System.out.println(sToDigest);
		byte[] bMac = DigestUtils.sha256(sToDigest.getBytes());
		String mac = Base64.encodeBase64String (bMac);
		mac = mac.substring (0,35);
		return mac;
	}

	public static String generaMACVerificaDatiPagamento(String passphrase, String iuv, String timestamp) {
		
		String mac = "";
		
		String sToDigest = passphrase + "%%%%" + iuv  + timestamp + "%%%%" + passphrase;
		byte[] bMac = DigestUtils.sha256(sToDigest.getBytes());
		mac = Base64.encodeBase64String(bMac);
		mac = mac.substring(0, 35);
		
		return mac;
	}
	
	public static String getTimestamp () {
		String timestamp = null;
		SimpleDateFormat sdf = new SimpleDateFormat("ddmmyyyy-hh:mm:ss:ms");
		timestamp = sdf.format(new Date());
		return timestamp;
	}
	
	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");
	}	
	
	public static Date testISO8601(final String str) {
		
		Calendar c = null;
		try{
			c = DatatypeConverter.parseDateTime(str);
		}
		catch (Exception e){
			throw e;
		}
		
		return c.getTime();
		
	}
}
