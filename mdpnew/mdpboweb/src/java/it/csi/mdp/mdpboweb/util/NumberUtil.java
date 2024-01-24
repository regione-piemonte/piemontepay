/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import org.apache.commons.lang.StringUtils;

public class NumberUtil {

    public static Double decimalToDouble(String val) {
        if (StringUtils.isEmpty(val)) {
            return null;
        }

        return Double.parseDouble(StringUtils.replace(val, ",", "."));
    }

    public static Double importoToDouble(String val) {
        if (StringUtils.isEmpty(val)) {
            return null;
        }

        String tmp = StringUtils.replace(val, ".", "");
        return decimalToDouble(tmp);
    }

    public static String doubleToImporto(Double d) {
        return formatDecimal(d, "#,##0.00");
    }

    public static String floatToImporto(Float d) {
        return formatDecimal(d, "#,##0.00");
    }
    
    public static String floatToPercentuale(Float d) {
    	return formatDecimal(d, "#,##0.####");
    }

    public static String doubleToDecimal(Double d) {
        return formatDecimal(d, "0.00");
    }

    private static String formatDecimal(Double d, String pattern) {
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

    private static String formatDecimal(Float d, String pattern) {
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
    
    public static Double sumDoubleValues(int digit, Double... values) {
        int sum = 0;
        int p = (int) Math.pow(10, digit);
        for (int i = 0; i < values.length; i++) {
            if (values[i] > 0.0) {
                sum += roundDoubleToInt(digit, values[i]);
            }
        }

        return (double)sum / p;

    }

    public static int roundDoubleToInt(int digit, Double value) {
        final int p = (int) Math.pow(10, digit);

        return (int) (value * p);
    }
    
    public static String convertiBooleanToString(boolean value,String valoreComparante){
		if(value){
			return "1";
		}
		return "0";
	}
    
	public  boolean convertiStringToBoolean(String value,String valoreComparante){
		if(value!=null && value.equals(valoreComparante)){
			return true;
		}
		return false;
	}
	
	public static boolean isNumber (String value){
		try{
			Integer.parseInt(value);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
}

