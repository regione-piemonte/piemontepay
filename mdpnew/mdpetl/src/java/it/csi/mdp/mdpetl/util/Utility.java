/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Calendar;

import com.thoughtworks.xstream.XStream;


public class Utility {
	private static LogUtil log = new LogUtil(Utility.class);



	public static Integer getAnnoCorrente() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}


	public static String fieldsToString(Object obj) {

		XStream xs = new XStream();
		return "\n" + xs.toXML(obj);
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
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static String convertStreamToString(InputStream is) throws IOException {
		if (is != null) {
			Writer writer = new StringWriter();

			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is,
						"UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} finally {
				is.close();
			}
			return writer.toString();
		} else {
			return "";
		}
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
//
//	public static void printNestedException(Throwable t) {
//		final String methodName = "printNestedException";
//		if (t == null) {
//			log.error(methodName, "[null]: null");
//			return;
//		} else {
//			log.error(methodName, "[" + t.getClass().getSimpleName() + "]: "
//					+ t.getMessage());
//
//			Throwable cause;
//			if (t instanceof EJBException) {
//				cause = ((EJBException) t).getCausedByException();
//			} else {
//				cause = t.getCause();
//			}
//			printNestedException(cause);
//		}
//
//	}
//
//	public static Throwable getRootCauseException(Throwable t) {
//		final String methodName = "getRootCauseException";
//		if (t == null) {
//			log.error(methodName, "[null]: null");
//			return null;
//		} else {
//			log.error(methodName, "[" + t.getClass().getSimpleName() + "]: "
//					+ t.getMessage());
//
//			Throwable cause;
//			if (t instanceof EJBException) {
//				cause = ((EJBException) t).getCausedByException();
//			} else {
//				cause = t.getCause();
//			}
//			if (cause == null) {
//				return t;
//			} else {
//				return getRootCauseException(cause);
//			}
//		}
//
//	}

	public static String splitCamelCase(String s) {
		String exp = String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])",
				"(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])");
		return s.replaceAll(exp, " ");
	}

	

	
	// ///
	/*
	 * 
	 * --Query per ottenere in automatico i campi per costruire un oggetto java
	 * che mappi 1 a 1 una tabella sul db.
	 * 
	 * SELECT 'private ' ||(CASE WHEN data_type = 'VARCHAR2' THEN 'String' WHEN
	 * data_type = 'DATE' THEN 'Date' WHEN data_type = 'NUMBER' AND data_scale =
	 * 0 THEN 'Integer' WHEN data_type = 'NUMBER' AND data_scale <> 0 THEN
	 * 'BigDecimal' ELSE data_type END) ||' ' ||Lower(Substr(column_name,1,1))
	 * ||Substr(Replace(Initcap(column_name),'_',''),2) ||'; //' ||column_name
	 * || (CASE WHEN data_type = 'VARCHAR2' THEN ' (' || data_length || ')' WHEN
	 * data_type = 'DATE' THEN '' WHEN data_type = 'NUMBER' AND data_scale = 0
	 * THEN ' (' || data_precision || ')' WHEN data_type = 'NUMBER' AND
	 * data_scale <> 0 THEN ' (' || data_precision || ',' || data_scale || ')'
	 * ELSE data_type END) FROM all_tab_columns WHERE table_name LIKE
	 * '%MIA_TABELLA%' ORDER BY column_name
	 */

	/*
	 * 
	 * SELECT 'MIA_VARIABILE_DTO' ||(CASE WHEN data_type = 'VARCHAR2' THEN
	 * '.set'||Upper(Substr(column_name,1,1))||Substr(Replace(Initcap(column_name),'_',''),2)||'(rs.getString("'||column_name||'"));'
	 * WHEN data_type = 'DATE' THEN
	 * '.set'||Upper(Substr(column_name,1,1))||Substr(Replace(Initcap(column_name),'_',''),2)||'(rs.getDate("'||column_name||'"));'
	 * WHEN data_type = 'NUMBER' AND data_scale = 0 THEN
	 * '.set'||Upper(Substr(column_name,1,1))||Substr(Replace(Initcap(column_name),'_',''),2)||'(new
	 * Integer(rs.getInt("'||column_name||'")));' WHEN data_type = 'NUMBER' AND
	 * data_scale <> 0 THEN
	 * '.set'||Upper(Substr(column_name,1,1))||Substr(Replace(Initcap(column_name),'_',''),2)||'(rs.getBigDecimal("'||column_name||'"));'
	 * ELSE data_type END) FROM all_tab_columns WHERE table_name like
	 * '%MIA_TABELLA%' ORDER BY column_name
	 */
}
