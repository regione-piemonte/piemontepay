/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class LoggerUtil {

	private static final String LOGGER_NAME = "mdpcoreservicessrv";
	

	public static void debug(String message) {
		StackTraceElement[] traceElements = new Throwable().getStackTrace();
		StackTraceElement traceElement = traceElements[1];
		String className = traceElement.getClassName();
		String methodName = traceElement.getMethodName();
		Logger logger = Logger.getLogger(LOGGER_NAME);
		if (logger.isDebugEnabled())
			logger.debug("[" + className + "::" + methodName + "] " + message);
		else 
			System.out.println("[" + className + "::" + methodName + "] " + message);

	}

	public static void info(String message) {
		StackTraceElement[] traceElements = new Throwable().getStackTrace();
		StackTraceElement traceElement = traceElements[1];
		String className = traceElement.getClassName();
		String methodName = traceElement.getMethodName();
		Logger logger = Logger.getLogger(LOGGER_NAME);
		logger.info("[" + className + "::" + methodName + "] " + message);

	}

	public static void error(String message, Throwable ex) {
		StackTraceElement[] traceElements = new Throwable().getStackTrace();
		StackTraceElement traceElement = traceElements[1];
		String className = traceElement.getClassName();
		String methodName = traceElement.getMethodName();
		Logger logger = Logger.getLogger(LOGGER_NAME);
		logger.error("[" + className + "::" + methodName + "] " + message, ex);

	}

	public static void fatal(String message, Throwable ex) {
		StackTraceElement[] traceElements = new Throwable().getStackTrace();
		StackTraceElement traceElement = traceElements[1];
		String className = traceElement.getClassName();
		String methodName = traceElement.getMethodName();
		Logger logger = Logger.getLogger(LOGGER_NAME);
		logger.fatal("[" + className + "::" + methodName + "] " + message, ex);

	}

	public static void warn(String message) {
		StackTraceElement[] traceElements = new Throwable().getStackTrace();
		StackTraceElement traceElement = traceElements[1];
		String className = traceElement.getClassName();
		String methodName = traceElement.getMethodName();
		Logger logger = Logger.getLogger(LOGGER_NAME);
		logger.warn("[" + className + "::" + methodName + "] " + message);

	}

	/**
	 * traccia l'ingresso di un metodo su livello INFO
	 */
	public static void begin() {
		StackTraceElement[] traceElements = new Throwable().getStackTrace();
		StackTraceElement traceElement = traceElements[1];
		String className = traceElement.getClassName();
		String methodName = traceElement.getMethodName();
		Logger logger = Logger.getLogger(LOGGER_NAME);
		logger.info("[" + className + "::" + methodName + "] BEGIN");
	}

	/**
	 * traccia l'uscita di un metodo su livello INFO
	 */
	public static void end() {
		StackTraceElement[] traceElements = new Throwable().getStackTrace();
		StackTraceElement traceElement = traceElements[1];
		String className = traceElement.getClassName();
		String methodName = traceElement.getMethodName();
		Logger logger = Logger.getLogger(LOGGER_NAME);
		logger.info("[" + className + "::" + methodName + "] END");
	}

	/**
	 * Effettua il dump dell'oggetto in ingresso su livello DEBUG
	 * 
	 * @param o
	 */
	public static void dump(Object o) {
		StringBuffer buffer = new StringBuffer();
		dump(o, buffer);
		debug(buffer.toString());
	}

	/*
	 * Prepara i contenuti da inserire nel buffer
	 */
	private static void dump(Object o, StringBuffer buffer) {

		if (o == null)
			return;

		@SuppressWarnings("rawtypes")
		Class cl = o.getClass();

		if (cl.equals(Class.class) || cl.equals(BigDecimal.class))
			return;

		buffer.append("\n\n");

		if (!cl.isArray() && !(o instanceof List) && !(o instanceof Set)
				&& !(o instanceof Map)) {
			buffer.append("\t\t[").append(cl.getName()).append("] - ").append(
					"value: [").append(o).append("]");
			buffer.append("\n\n");
			return;
		}

		buffer.append("\t[" + cl.getName() + "]:\n");

		// Array
		if (cl.isArray()) {
			Object[] array = (Object[]) o;

			if (array.length == 0) {
				buffer.append("\t\tArray vuoto\n");
			} else {
				// buffer.append("eseguo il dump dei contenuti\n");
				for (int i = 0; i < array.length; i++) {
					dump(array[i], buffer);
				}
			}
		}

		// ArrayList
		if (cl.equals(ArrayList.class)) {
			ArrayList<?> l = (ArrayList<?>) o;

			if (l.isEmpty()) {
				buffer.append("\t\tArrayList vuoto\n");
			} else {			
				for (Object object : l) {
					dump(object, buffer);					
				}
			}
		}

		// Set
		if (o instanceof Set) {
			Set<?> s = (Set<?>) o;

			if (s.isEmpty()) {
				buffer.append("\t\tSet vuoto\n");
				return;
			}
			for (Object object : s) {
				dump(object, buffer);
			}
		}

		buffer.append("\n\n");
	}
	
	
	public static void dumpObject (String message, Object o) {
		
		Logger logger = Logger.getLogger(LOGGER_NAME);
		if (logger.isDebugEnabled()) {
			XStream xstream = new XStream(new DomDriver());
			StackTraceElement[] traceElements = new Throwable().getStackTrace();
			StackTraceElement traceElement = traceElements[1];
			String className = traceElement.getClassName();
			String methodName = traceElement.getMethodName();
			logger.debug("[" + className + "::" + methodName + "] " + message + "\n" + xstream.toXML(o));
		}
		
	}

	public static void fatal(String message) {
		StackTraceElement[] traceElements = new Throwable().getStackTrace();
		StackTraceElement traceElement = traceElements[1];
		String className = traceElement.getClassName();
		String methodName = traceElement.getMethodName();
		Logger logger = Logger.getLogger(className);
		logger.fatal("[" + className + "::" + methodName + "] " + message);

		
	}

}
