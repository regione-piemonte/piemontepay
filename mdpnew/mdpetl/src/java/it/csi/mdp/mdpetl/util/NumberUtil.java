/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.util;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

public class NumberUtil {

	public static BigDecimal decimalToBigDecimal(String val) {
		if (StringUtils.isEmpty(val)) {
			return null;
		}
		return new BigDecimal(StringUtils.replace(val, ",", "."));
	}

	public static BigDecimal importoToBigDecimal(String val) {
		if (StringUtils.isEmpty(val)) {
			return null;
		}
		String tmp = StringUtils.replace(val, ".", "");
		return decimalToBigDecimal(tmp);
	}

	public static String bigDecimalToImporto(BigDecimal d) {
		return formatDecimal(d, "#,##0.00");
	}

	public static String doubleToDecimal(BigDecimal d) {
		return formatDecimal(d, "0.00");
	}

	private static String formatDecimal(BigDecimal d, String pattern) {
		if (d == null) {
			return null;
		}

		NumberFormat f = NumberFormat.getInstance(Locale.ITALY);

		if (f instanceof DecimalFormat) {
			DecimalFormat decimalFormat = (DecimalFormat) f;

			decimalFormat.applyPattern(pattern);

			return decimalFormat.format(d);
		}
		return null;
	}

	public static String getValoreFormattato(BigDecimal valore) {
		return bigDecimalToImporto(valore);
	}

	public static String getValoreFormattato(Double valore) {
		if(valore==null){
			return "";
		}
		return bigDecimalToImporto(new BigDecimal(valore));
	}
	
	public static boolean isNumber(String s) {
		try{
			Integer.parseInt(s);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	/*
	 * public static BigDecimal sumBigDecimalValues(int digit, BigDecimal...
	 * values) { int sum = 0; int p = (int) Math.pow(10, digit); for (int i = 0;
	 * i < values.length; i++) { if (values[i] > 0.0) { sum +=
	 * roundBigDecimalToInt(digit, values[i]); } }
	 * 
	 * return (BigDecimal)sum / p;
	 * 
	 * }
	 * 
	 * public static int roundBigDecimalToInt(int digit, BigDecimal value) {
	 * final int p = (int) Math.pow(10, digit);
	 * 
	 * return (int) (value * p); }
	 */
}
