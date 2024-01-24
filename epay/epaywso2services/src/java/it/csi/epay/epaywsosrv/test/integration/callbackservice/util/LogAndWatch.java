/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.test.integration.callbackservice.util;

import it.csi.util.performance.StopWatch;
import org.apache.logging.log4j.Logger;

import static it.csi.epay.epaywsosrv.util.Util.APPLICATION_CODE;

public class LogAndWatch {
	private final Logger log;
	private final String className;
	private final String methodName;
	private final String logpx;
	private final StopWatch watch;
	private final boolean isWatchEnabled;

	static public String buildLogpx(String className, String methodName) {
		return "[".concat(className).concat("::").concat(methodName).concat("] ");
	}

	public LogAndWatch(Logger log, String className, String methodName) {
		this(log, className, methodName, true);
	}

	public LogAndWatch(Logger log, String className, String methodName, boolean isWatchEnabled) {
		this.log = log;
		this.className = className;
		this.methodName = methodName;
		this.logpx = buildLogpx(className, methodName);
		this.watch = new StopWatch(APPLICATION_CODE);
		this.isWatchEnabled = isWatchEnabled;
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

	public String getLogpx() {
		return logpx;
	}

	public void start() {
		if (log.isDebugEnabled()) {
			log.debug(logpx + "START");
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
			log.debug(logpx + "END");
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
