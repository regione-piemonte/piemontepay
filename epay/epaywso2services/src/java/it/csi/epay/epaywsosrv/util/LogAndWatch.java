/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.util;

import it.csi.util.performance.StopWatch;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import static it.csi.epay.epaywsosrv.util.Util.APPLICATION_CODE;

public class LogAndWatch {
	static private final String LOGPX_FIRST_SYMBOL = "[";
	static private final String LOGPX_MIDLE_SYMBOL = "::";
	static private final String LOGPX_FINAL_SYMBOL = "]";

	private final Logger log;
	private final String className;
	private final String methodName;
	private String startLogpx;
	private String stopLogpx;
	private StopWatch watch;
	private final boolean isWatchEnabled;
	private final ArrayList<String> paramNames;
	private final ArrayList<Object> paramValues;
	private final ArrayList<String> resultNames;
	private final ArrayList<Object> resultValues;

	static public String buildLogpx(String className, String methodName) {
		return LOGPX_FIRST_SYMBOL.concat(className).concat(LOGPX_MIDLE_SYMBOL).concat(methodName).concat(LOGPX_FINAL_SYMBOL).concat(" ");
	}

	static public String buildLogpx(String className, String methodName, ArrayList<String> paramNames, ArrayList<Object> paramValues) {
		StringBuilder sb = new StringBuilder();
		sb.append(LOGPX_FIRST_SYMBOL);
		sb.append(className);
		sb.append(LOGPX_MIDLE_SYMBOL);
		sb.append(methodName);
		if (paramNames != null) {
			for (int i = 0, n = paramNames.size(); i < n; i++) {
				if (i == 0) {
					sb.append("(");
				}
				sb.append(paramNames.get(i));
				sb.append(":");
				Object paramValue = paramValues.get(i);
				if (paramValue != null) {
					String strParamValue = paramValue.toString();
					sb.append(paramValue instanceof String ? Util.quote(strParamValue) : strParamValue);
				} else {
					sb.append("null");
				}
				sb.append(i < n - 1 ? ", " : ")");
			}
		}
		sb.append(LOGPX_FINAL_SYMBOL);
		sb.append(" ");

		return sb.toString();
	}

	public LogAndWatch(Logger log, String className, String methodName) {
		this(log, className, methodName, true);
	}

	public LogAndWatch(Logger log, String className, String methodName, boolean isWatchEnabled) {
		this.log = log;
		this.className = className;
		this.methodName = methodName;
		this.startLogpx = buildLogpx(className, methodName);
		this.stopLogpx = buildLogpx(className, methodName);
		this.watch = new StopWatch(APPLICATION_CODE);
		this.isWatchEnabled = isWatchEnabled;
		this.paramNames = new ArrayList<String>();
		this.paramValues = new ArrayList<Object>();
		this.resultNames = new ArrayList<String>();
		this.resultValues = new ArrayList<Object>();
	}

	public Logger getLog() {
		return log;
	}

	public String getClassName() {
		return methodName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void start() {
		if (log.isDebugEnabled()) {
			log.debug(startLogpx + "START");
		}

		if (isWatchEnabled) {
			watch.start();
		}
	}

	public void stop() {
		if (isWatchEnabled) {
			watch.stop();
			watch.dumpElapsed(className, methodName, "", "");
		}

		if (log.isDebugEnabled()) {
			log.debug(stopLogpx + "END");
		}
	}

	public void addParam(String name, Object value) {
		if (name != null) {
			paramNames.add(name);
			paramValues.add(value != null ? value : "null");
			this.startLogpx = buildLogpx(className, methodName, paramNames, paramValues);
		}
	}

	public void addResult(String name, Object value) {
		if (name != null) {
			resultNames.add(name);
			resultValues.add(value != null ? value : "null");
			this.stopLogpx = buildLogpx(className, methodName, resultNames, resultValues);
		}
	}

	public void debug(String message) {
		log.debug(buildLogpx(className, methodName).concat(message));
	}

	public void debug(Throwable t) {
		log.debug(buildLogpx(className, methodName), t);
	}

	public void debug(String message, Throwable t) {
		log.debug(buildLogpx(className, methodName).concat(message), t);
	}

	public void info(String message) {
		log.info(buildLogpx(className, methodName).concat(message));
	}

	public void info(Throwable t) {
		log.info(buildLogpx(className, methodName), t);
	}

	public void info(String message, Throwable t) {
		log.info(buildLogpx(className, methodName).concat(message), t);
	}

	public void warn(String message) {
		log.warn(buildLogpx(className, methodName).concat(message));
	}

	public void warn(Throwable t) {
		log.warn(buildLogpx(className, methodName), t);
	}

	public void warn(String message, Throwable t) {
		log.warn(buildLogpx(className, methodName).concat(message), t);
	}

	public void error(String message) {
		log.error(buildLogpx(className, methodName).concat(message));
	}

	public void error(Throwable t) {
		log.error(buildLogpx(className, methodName), t);
	}

	public void error(String message, Throwable t) {
		log.error(buildLogpx(className, methodName).concat(message), t);
	}

	public boolean isDebugEnabled() {
		return log.isDebugEnabled();
	}

}
