/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.uiutils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @generated
 */
public class Utility {

	/**
	 * Converte una stringa in una data nel formato italiano dd/mm/yyyy
	 * 
	 * @param from
	 *            as String
	 * @return Date
	 */
	public static Date getConvertDate(String from) {
		Date date = new Date();
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			// DateFormat formatter1 = new SimpleDateFormat()
			date = (Date) formatter.parse(from);
		} catch (Exception e) {
			return null;
		}
		return date;
	}

	/**
	 * Converte una data in una Stringa nel formato dd/mm/yyyy
	 * 
	 * @param from
	 *            as Date
	 * @return String
	 */
	public static String getConvertDate(Date from) {
		String s = "";
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			s = formatter.format(from);
		} catch (Exception e) {
			return s;
		}
		return s;
	}

	public static String getConvertDate(Long from) {
		String s = "";
		try {
			Date d = new Date(from);
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			s = formatter.format(d);
		} catch (Exception e) {
			return s;
		}
		return s;
	}

	/**
	 * Converte una stringa in un float nel formato italiano cioe' separatore la
	 * , e le cifre decimali passate
	 * 
	 * @param numero
	 *            as String
	 * @param cifre
	 *            as int         
	 * @return float
	 */
	public static float createFloat(String numero, int cifre) {
		try {
			Locale locale = Locale.ITALIAN;
			NumberFormat format = NumberFormat.getNumberInstance(locale);
			format.setMaximumFractionDigits(cifre); // what you seem to want
			format.setMinimumFractionDigits(cifre); // cifre dopo la virgola

			return Float.valueOf(format.parse(numero.trim()).toString())
					.floatValue();
		} catch (Exception e) {
			return Float.parseFloat("0");
		}

	}

	public static Date convertToDate(String date) {
		Date dateoutput = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dateoutput = sdf.parse(date);
		} catch (Exception e) {
			return null;
		}
		return dateoutput;
	}

	public static Method convertiMetodo(String metodo, Object oggetto) {
		Method method = null;
		try {
			PropertyDescriptor nameDescriptor = new PropertyDescriptor(metodo,
					oggetto.getClass());
			method = nameDescriptor.getReadMethod();
		} catch (IntrospectionException e) {
			throw new RuntimeException("Metodo non trovato", e.getCause());
		}

		return method;
	}

}
