/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.util;

import java.text.*;
import java.util.*;
import java.util.regex.*;

import org.apache.commons.beanutils.*;
import org.apache.commons.lang.*;

public class Validator {

	private Object bean;
	private Map<String, String> errors = new LinkedHashMap<String, String>();
	private static final String GENERIC_ERROR_KEY = "error_";

	public boolean hasErrors() {
		return !errors.isEmpty();
	}

	public Map getErrors() {
		return errors;
	}

	public void addError(String errorMsg) {
		addError(GENERIC_ERROR_KEY + (errors.size() + 1), errorMsg);
	}

	public void addError(String fieldKey, String errorMsg) {
		if (!errors.containsKey(fieldKey)) {
			errors.put(fieldKey, errorMsg);

			// system.out.println(MiscUtils.sprintf("ERROR key: %s - message: %s",
			// fieldKey, errorMsg));
		}
	}

	public void resetError() {
		errors = new HashMap<String, String>();
	}

	/** *********** metodi di controllo errori ********** */
	public void required(String fieldKey, String errMsg) {
		if (!isValidRequired(getValue(fieldKey))) {
			addError(fieldKey, errMsg);
		}
	}

	public void maxLength(String fieldKey, Integer maxLen, String errMsg) {
		if (!isValidMaxLength(getValue(fieldKey), maxLen)) {
			addError(fieldKey, errMsg);
		}
	}

	public void range(String fieldKey, Double min, Double max, String errMsg) {
		if (!isValidRange(getValue(fieldKey), min, max)) {
			addError(fieldKey, errMsg);
		}
	}

	public void email(String fieldKey, String errMsg) {
		if (!isValidEmail(getValue(fieldKey))) {
			addError(fieldKey, errMsg);
		}
	}

	// formato data: gg/mm/aaaa
	public void date(String fieldKey, String errMsg) {
		if (!isValidDate(getValue(fieldKey))) {
			addError(fieldKey, errMsg);
		}
	}

	public void positiveInteger(String fieldKey, String errMsg) {
		if (!isValidPositiveInteger(getValue(fieldKey))) {
			addError(fieldKey, errMsg);
		}
	}

	public void positiveDecimal(String fieldKey, String errMsg) {
		if (!isValidPositiveDecimal(getValue(fieldKey))) {
			addError(fieldKey, errMsg);
		}
	}

	public void importo(String fieldKey, String errMsg) {
		if (!isValidImporto(getValue(fieldKey))) {
			addError(fieldKey, errMsg);
		}
	}

	public void orario(String fieldKey, String errMsg) {
		if (!isValidOrario(getValue(fieldKey))) {
			addError(fieldKey, errMsg);
		}
	}

	public void mask(String fieldKey, String mask, String errMsg) {
		if (!isValidMask(getValue(fieldKey), mask)) {
			addError(fieldKey, errMsg);
		}
	}

	public void percentuale(String fieldKey, String errMsg) {
		if (!isValidPercentuale(getValue(fieldKey))) {
			addError(fieldKey, errMsg);
		}
	}

	public void passwordSafe(String fieldKey, String errMsg) {
		if (!isValidPasswordSafe(getValue(fieldKey))) {
			addError(fieldKey, errMsg);
		}
	}

	public void minLength(String fieldKey, Integer minLen, String errMsg) {
		if (!isValidMinLength(getValue(fieldKey), minLen)) {
			addError(fieldKey, errMsg);
		}

	}

	// //////////////////////////
	// metodi statici di validazione
	// //////////////////////////
	public static boolean isValidDate(String date) {
		try {
			if (StringUtils.isNumeric(date.replace('/', '0'))) {
				DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,
						Locale.ITALY);
				df.setLenient(false);
				df.parse(date);
			} else {
				return false;
			}
		} catch (Exception ex) {
			return false;
		}

		return true;
	}

	public static boolean isValidMask(String value, String mask) {
		// regexp:
		// http://articles.sitepoint.com/article/java-regex-api-explained

		if (StringUtils.isNotEmpty(value)) {
			Pattern pattern = Pattern.compile(mask);
			Matcher matcher = pattern.matcher(value);
			return matcher.find();
		}

		return true;
	}

	public static boolean isValidEmail(String value) {
		return isValidMask(
				value,
				"^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
	}

	
	public static boolean isValidRange(String value, Double min, Double max) {
		try {
			if (StringUtils.isNotEmpty(value)) {
				Double d = new Double(value);
				return (d >= min && d <= max);
			}
		} catch (Exception e) {
			System.out.print(e);
		}

		return true;
	}

	public static boolean isValidPasswordSafe(String value) {
		return isValidMask(value, "[A-Za-z]")
				&& isValidMask(value, "\\d")
				&& isValidMask(
						value,
						"[\\'\\[\\]\\@\\|\\\\^\\.\\!\"\\#\\$\\%\\&\\(\\)\\*\\+\\,\\-\\/\\:\\;\\<\\=\\>\\?\\_\\\\]");
	}

	public static boolean isValidOrario(String value) {
		if (StringUtils.isEmpty(value)) {
			return true;
		}

		if (!isValidMask(value, "^\\d+(\\.\\d{2})$")) {
			return false;
		}

		String minStr = StringUtils.substringAfter(value, ".");
		int min = Integer.parseInt(StringUtils.right(minStr, 1));

		return min < 60;
	}

	public static boolean isValidRequired(String value) {
		return StringUtils.isNotBlank(value);
	}

	public static boolean isValidImporto(String value) {
		return isValidMask(value, "^(\\d+|\\d{1,2}(\\.\\d{3})+)(\\,\\d+)?$");
	}

	public static boolean isValidUrl(String value) {
		return isValidMask(
				value,"/^(https|http:\\/\\/)?([^\\/]+)/i");
	}

	public static boolean isValidMaxLength(String value, Integer maxLen) {
		return StringUtils.isEmpty(value) || value.length() <= maxLen;
	}

	public static boolean isValidPositiveInteger(String value) {
		return isValidMask(value, "^\\d+$");
	}

	public static boolean isValidPercentuale(String value) {
		return isValidRange(value, (double) 0, (double) 100);
	}

	public static boolean isValidPositiveDecimal(String value) {
		return isValidMask(value, "^\\d+(\\,\\d+)?$");
	}

	public static boolean isValidMinLength(String value, Integer minLen) {
		return StringUtils.isEmpty(value) || value.length() >= minLen;
	}

	// /////////////////////////////////////
	// ////////////////////////////////////
	// /////////////////////////////////////
	private String getValue(String fieldKey) {
		try {
			Object o = PropertyUtils.getProperty(bean, fieldKey);

			if (o != null) {
				if (o instanceof String) {
					return (String) o;
				}

				if (o instanceof String[]) {
					return StringUtils.join((String[]) o, ",");
				}

				return o.toString();
			}

			return null;
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}

	private Integer getInt(String fieldKey) {
		String val = getValue(fieldKey);
		return val == null ? null : Integer.parseInt(val);
	}

	public static boolean isValidCodfisc(String value) {
		return StringUtils.isBlank(value)
				|| isValidMask(
						value,
						"^[A-Z]{6}[0-9LMNPQRSTUV]{2}[A-Z][0-9LMNPQRSTUV]{2}[A-Z][0-9LMNPQRSTUV]{3}[A-Z]$")
				&& isValidControlCharCodiceFiscale(value);
	}

	private static boolean isValidControlCharCodiceFiscale(String codiceFiscale) {
		return codiceFiscale.charAt(15) == calcControlCharCodiceFiscale(StringUtils.substring(
						codiceFiscale, 0, 15));
	}

	public static char calcControlCharCodiceFiscale(String string)
	 {
	  int sum = 0;

	  for (int i = 0; i < string.length(); i++)
	  {
	   char c = string.charAt(i);

	   int x = (Character.isDigit(c) ? Character.getNumericValue(c) : new String(
	     new char[] { c }).getBytes()[0] - 65);

	   sum += ((i + 1) % 2 == 0 ? x : new int[] { 1, 0, 5, 7, 9, 13, 15, 17, 19, 21, 2, 4, 18,
	     20, 11, 3, 6, 8, 12, 14, 16, 10, 22, 25, 24, 23 }[x]);
	  }

	  return (char) ((sum % 26) + 65);
	 }
	
	////////////////////////////////////////////
	
	/****************************************************
    Funzione per il controllo della partita IVA.
    Linguaggio: Java.
    Versione del: 2002-07-07
	 *****************************************************/

	public static String controllaPIVA(String pi,boolean nullisError){
	    int i, c, s;
	    
    	if(nullisError && (pi==null || pi.trim().length() == 0 )){
    		return "partita Iva non valorizzata";
    	}
	    
	    if( pi.length() != 11 ){
	        return "La lunghezza della partita IVA non &egrave;\n"
	        + "corretta: la partita IVA dovrebbe essere lunga\n"
	        + "esattamente 11 caratteri.\n";
	    }
	    
	    for( i=0; i<11; i++ ){
	        if( pi.charAt(i) < '0' || pi.charAt(i) > '9' ){
	            return "La partita IVA contiene dei caratteri non ammessi:\n"
	            + "la partita IVA dovrebbe contenere solo cifre.\n";
	        }
	    }
	    
	    s = 0;
	    for( i=0; i<=9; i+=2 ){
	        s += pi.charAt(i) - '0';
	    }
	    
	    for( i=1; i<=9; i+=2 ){
	        c = 2*( pi.charAt(i) - '0' );
	        if( c > 9 )  c = c - 9;
	        s += c;
	    }
	    
	    if( ( 10 - s%10 )%10 != pi.charAt(10) - '0' ){
	        return "La partita IVA non &egrave; valida:\n" + "il codice di controllo non corrisponde.";
	    }
	    return "";
	}
	
}
