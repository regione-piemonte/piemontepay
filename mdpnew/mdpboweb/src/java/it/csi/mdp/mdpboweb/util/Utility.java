/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJBException;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;



public class Utility {

	protected static final Logger log = Logger
	.getLogger(Constants.APPLICATION_CODE + ".Utility");
	
	public static Integer getAnnoCorrente() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}



	/**
	 * Somma con la precisione data da BigDecimal un insieme di Double
	 * 
	 * @param a
	 * @return
	 */
	public static Double sum(Double... a) {
		BigDecimal res = new BigDecimal("0");
		for (int i = 0; i < a.length; i++) {
			Double d = a[i];
			if (d != null) {
				BigDecimal bd = new BigDecimal(d.toString());
				res = res.add(bd);
			}
		}
		return new Double(res.doubleValue());

	}

	/**
	 * Trasforma una stringa da Pippo_pluto_paperino in PippoPlutoPaperino
	 * 
	 * @param ca
	 * @return
	 */
	public static String capitalizeAfterUnderscore(String ca) {

		if (ca == null) {
			return null;
		}

		int i = ca.indexOf("_");
		while (i != -1) {
			ca = ca.substring(0, i)
					+ StringUtils.capitalize(ca.substring(i + 1));

			i = ca.indexOf("_");

		}
		return ca;
	}



	public static Object copyGetSetInstance(Object original) {

		Object s = null;
		try {
			s = original.getClass().newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Object obj = original;
		Method[] methods = obj.getClass().getDeclaredMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			if (methodName.startsWith("get")) {
				Object param;
				try {
					param = method.invoke(obj);
					// method.g
					Method setMethod = getSetMethod(methodName, obj.getClass());
					setMethod.invoke(s, param);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return s;
	}

	private static Method getSetMethod(String methodName, Class clas) {
		methodName = "set" + methodName.substring(3);
		Method[] methods = clas.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				return method;
			}
		}
		System.out.println("Impossibile trovare il metodo: " + methodName);
		return null;
	}

	public static byte[] readAllBytes(String filePath) throws Exception {
		File file = new File(filePath);
		return readAllBytes(file);
	}

	public static byte[] readAllBytes(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		byte[] b = new byte[(int) file.length()];
		fis.read(b);
		return b;
	}

	public static void printNestedException(Throwable t) {
		final String methodName = "printNestedException";
		if (t == null) {
			log.error("[null]: null");
			return;
		} else {
			log.error("[" + t.getClass().getSimpleName() + "]: "
					+ t.getMessage());

			Throwable cause;
			if (t instanceof EJBException) {
				cause = ((EJBException) t).getCausedByException();
			} else {
				cause = t.getCause();
			}
			printNestedException(cause);
		}

	}

	public static Throwable getRootCauseException(Throwable t) {
		final String methodName = "getRootCauseException";
		if (t == null) {
			log.error("[null]: null");
			return null;
		} else {
			log.error("[" + t.getClass().getSimpleName() + "]: "
					+ t.getMessage());

			Throwable cause;
			if (t instanceof EJBException) {
				cause = ((EJBException) t).getCausedByException();
			} else {
				cause = t.getCause();
			}
			if (cause == null) {
				return t;
			} else {
				return getRootCauseException(cause);
			}
		}

	}

	public static String splitCamelCase(String s) {
		String exp = String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])",
				"(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])");
		return s.replaceAll(exp, " ");
	}

	public static Object replaceNull(Object val,String rep){
		if(val==null){
			val=rep;
		}
		return val;
	}
	
	public static String encodeFilenameForDownload(String filename, String userAgent) throws Exception
	{
		//String userAgent = ServletActionContext.getRequest().getHeader("user-agent");

		boolean isInternetExplorer = (userAgent.indexOf("MSIE") > -1);

		if (isInternetExplorer)
			return "\"" + filename + "\"";

		return "\"" + MimeUtility.encodeWord(filename) + "\"";
	}
}
