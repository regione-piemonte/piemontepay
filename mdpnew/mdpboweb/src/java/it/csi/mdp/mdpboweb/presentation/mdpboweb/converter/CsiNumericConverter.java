/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.converter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Locale;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.util.TypeConversionException;

/**
 * Custom Type Converter per i tipi numerici.
 * 
 * @author [GUIGEN]
 */
public class CsiNumericConverter extends StrutsTypeConverter {

	/**
	 * 
	 * @param  context
	 * @param  values
	 * @param  toClass
	 * @return
	 * @see org.apache.struts2.util.StrutsTypeConverter#convertFromString(java.util.Map, java.lang.String[], java.lang.Class)
	 */
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if (values != null && values.length > 0 && values[0] != null
				&& values[0].trim().length() > 0) {
			String s = values[0].trim();
			NumberFormat df = NumberFormat.getNumberInstance(Locale.ITALY);

			if (toClass == Float.TYPE || toClass == Float.class
					|| toClass == Double.TYPE || toClass == Double.class) {
				df.setMinimumFractionDigits(2);
				df.setMaximumFractionDigits(2);
			}

			try {
				ParsePosition parsePosition = new ParsePosition(0);
				Number n = df.parse(s, parsePosition);
				if (parsePosition.getIndex() != s.length()) {
					throw new ParseException(
							"impossibile convertire l'intera stringa",
							parsePosition.getIndex());
				}

				// df.parse restituisce il tipo massimo quindi
				//  [1] double, Double; float, Float  => Double se specifico i decimali
				//  [2] double, Double; float, Float  => Long se non specifico i decimali
				//  [3] int, Integer; long, Long      => Long
				// bisogna in qualche modo fare un "downcast" in base a toClass

				// Se il tipo target e' un decimale occorre gestire il caso [2]
				if (toClass == Float.TYPE || toClass == Float.class
						|| toClass == Double.TYPE || toClass == Double.class) {
					if (n instanceof Long) {
						n = new Double(n.doubleValue());
					}
				}
				if (toClass == Integer.TYPE || toClass == Integer.class) {
					return ((Long) n).intValue();
				} else if (toClass == Float.TYPE || toClass == Float.class) {
					return ((Double) n).floatValue();
				}
				return n;
			} catch (ParseException e) {
				throw new TypeConversionException(e);
			}

		}
		return null;
	}

	/**
	 * 
	 * @param
	 * @param
	 * @return
	 * @see org.apache.struts2.util.StrutsTypeConverter#convertToString(java.util.Map, java.lang.Object)
	 */
	@Override
	public String convertToString(Map context, Object o) {
		NumberFormat df = NumberFormat.getNumberInstance(Locale.ITALY);

		if (o instanceof Integer || o instanceof Long) {
			return df.format(o);
		} else if (o instanceof Float || o instanceof Double) {
			df.setMinimumFractionDigits(2);
			df.setMaximumFractionDigits(2);
			return df.format(o);
		}

		return o.toString();
	}

}
