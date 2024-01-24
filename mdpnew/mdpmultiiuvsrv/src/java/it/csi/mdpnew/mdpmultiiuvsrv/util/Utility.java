/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpmultiiuvsrv.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class Utility {

	private static Logger log = Logger.getLogger(MdpMultiIuvConstants.MDPIUV_ROOT_LOG_CATEGORY + ".util");

	public static String getDataGiuliana(String dataOdierna) {

		StringBuffer sb = new StringBuffer();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date date = format.parse(dataOdierna);
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);

			// levo le 2 cifre del millennio e tengo solo l'anno
			sb.append(String.valueOf(calendar.get(Calendar.YEAR)).substring(2)).append(paddingDay(calendar.get(Calendar.DAY_OF_YEAR)));

		}
		catch (Exception e) {
			log.error("[Utility::calcolaMAC] BEGIN", e);
		}

		return sb.toString();
	}

	public static String paddingDay(int day) {
		String dayString = String.valueOf(day);

		if (day < 10)
			dayString = "00" + dayString;
		else if (day < 100)
			dayString = "0" + dayString;

		return dayString;

	}

	public static String mettiZero(String s, int lunghCampo) {

		String ris = "";

		if (null != s) {
			int zeriMancanti = lunghCampo - s.length();
			ris = s;
			while (zeriMancanti > 0) {
				ris = 0 + ris;
				zeriMancanti--;
			}
		}

		return ris;

	}

	protected Logger getLogger(String subsystem) {
		if (subsystem != null)
			return Logger.getLogger(MdpMultiIuvConstants.LOGGER_PREFIX + "." + subsystem);
		else
			return Logger.getLogger(MdpMultiIuvConstants.LOGGER_PREFIX);
	}

	public static String calcolaMAC(String passphrase, String codiceVersamento, String applicationId, String timestamp) {

		log.debug("[Utility::calcolaMAC] BEGIN");
		String mac = "";

		String sToDigest = passphrase + "%%%%" + codiceVersamento + applicationId + "%%%%" + timestamp + passphrase;
		byte[] bMac = DigestUtils.sha256(sToDigest.getBytes());
		mac = Base64.encodeBase64String(bMac);
		log.debug("[Utility::calcolaMAC] mac = " + mac);
		mac = mac.substring(0, 35);
		log.debug("[Utility::calcolaMAC] mac = " + mac);

		log.debug("[Utility::calcolaMAC] END");

		return mac;
	}
	
	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");
	}		

}
