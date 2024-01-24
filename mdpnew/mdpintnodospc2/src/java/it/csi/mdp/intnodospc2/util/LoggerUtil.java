/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.intnodospc2.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class LoggerUtil {

	private static final Logger logger = Logger.getLogger("mdpintnodospc2srv");

	
	public static void debug(String message) {
		
		if (logger.isDebugEnabled()) {
			StackTraceElement[] traceElements = new Throwable().getStackTrace();
			StackTraceElement traceElement = traceElements[1];
			String className = traceElement.getClassName();
			String methodName = traceElement.getMethodName();
			
			logger.debug("[" + className + "::" + methodName + "] " + message);
		}
	}

	
	public static void info(String message) {
		
		if (logger.isInfoEnabled()) {
			StackTraceElement[] traceElements = new Throwable().getStackTrace();
			StackTraceElement traceElement = traceElements[1];
			String className = traceElement.getClassName();
			String methodName = traceElement.getMethodName();
			
			logger.info("[" + className + "::" + methodName + "] " + message);
		}
	}

	
	public static void error(String message) {
		
		StackTraceElement[] traceElements = new Throwable().getStackTrace();
		StackTraceElement traceElement = traceElements[1];
		String className = traceElement.getClassName();
		String methodName = traceElement.getMethodName();
		
		logger.error("[" + className + "::" + methodName + "] " + message);
	}
	
	
	public static void error(String message, Throwable ex) {
		
		StackTraceElement[] traceElements = new Throwable().getStackTrace();
		StackTraceElement traceElement = traceElements[1];
		String className = traceElement.getClassName();
		String methodName = traceElement.getMethodName();
		
		logger.error("[" + className + "::" + methodName + "] " + message, ex);
	}

	
	public static void warn(String message) {
		
		StackTraceElement[] traceElements = new Throwable().getStackTrace();
		StackTraceElement traceElement = traceElements[1];
		String className = traceElement.getClassName();
		String methodName = traceElement.getMethodName();
		
		logger.warn("[" + className + "::" + methodName + "] " + message);
	}
	
	
	public static void fatal(String message, Throwable ex) {
		
		StackTraceElement[] traceElements = new Throwable().getStackTrace();
		StackTraceElement traceElement = traceElements[1];
		String className = traceElement.getClassName();
		String methodName = traceElement.getMethodName();
		
		logger.fatal("[" + className + "::" + methodName + "] " + message, ex);
	}
	

	public static void fatal(String message) {

		StackTraceElement[] traceElements = new Throwable().getStackTrace();
		StackTraceElement traceElement = traceElements[1];
		String className = traceElement.getClassName();
		String methodName = traceElement.getMethodName();
		
		logger.fatal("[" + className + "::" + methodName + "] " + message);
	}


	public static void begin() {
		
		if (logger.isInfoEnabled()) {
			StackTraceElement[] traceElements = new Throwable().getStackTrace();
			StackTraceElement traceElement = traceElements[1];
			String className = traceElement.getClassName();
			String methodName = traceElement.getMethodName();
			logger.info("[" + className + "::" + methodName + "] BEGIN");
		}
	}


	public static void end() {
		if (logger.isInfoEnabled()) {
			StackTraceElement[] traceElements = new Throwable().getStackTrace();
			StackTraceElement traceElement = traceElements[1];
			String className = traceElement.getClassName();
			String methodName = traceElement.getMethodName();
			logger.info("[" + className + "::" + methodName + "] END");
		}
		
	}
	
	
	public static void dumpObject (String message, Object o) {
		
		if (logger.isDebugEnabled()) {
			XStream xstream = new XStream(new DomDriver());
			StackTraceElement[] traceElements = new Throwable().getStackTrace();
			StackTraceElement traceElement = traceElements[1];
			String className = traceElement.getClassName();
			String methodName = traceElement.getMethodName();
			logger.debug("[" + className + "::" + methodName + "] " + message + "\n" + xstream.toXML(o));
		}
		
	}

	public static void dump(Object o) {
		StringBuffer buffer = new StringBuffer();
		dump(o, buffer);
		debug(buffer.toString());
	}

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
	
	/*
	private String simpleClassFromFullyQualified (String fullyQualifiedClassName) {
		if (fullyQualifiedClassName == null) return null;
		
		return fullyQualifiedClassName.substring(fullyQualifiedClassName.lastIndexOf(".") +1, fullyQualifiedClassName.length());
	}*/
}
