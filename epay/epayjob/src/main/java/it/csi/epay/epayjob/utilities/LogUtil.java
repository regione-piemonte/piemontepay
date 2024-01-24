/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.utilities;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Utility centralizzata per il logging.
 */
public class LogUtil  {

	private static final String PATTERN = "[%s.%s] - %s";
	private static final String STARTPATTERN = "[%s.%s] - Start.";
	private static final String ENDPATTERN = "[%s.%s] - End.";

	private static final String LOGGER_NAME = "epayjob";

	private final Logger logger;
	private final String className;
	private final boolean flagSyso = false;

    public LogUtil ( Class<?> clazz ) {
        logger = LogManager.getLogger ( LOGGER_NAME );
        className = clazz.getSimpleName ();
    }

    public LogUtil ( String className ) {
        logger = LogManager.getLogger ( LOGGER_NAME );
        this.className = className;
    }

	public void debugStart(String methodName){
		logger.debug(String.format(STARTPATTERN, className, methodName));
		if (flagSyso) {
            System.out.println("inizio " + methodName);
        }
	}
	public void debugEnd(String methodName){
		logger.debug(String.format(ENDPATTERN, className, methodName));
		if (flagSyso) {
            System.out.println("fine " + methodName);
        }
	}
	public void debug(String methodName, Object message) {
		logger.debug(String.format(PATTERN, className, methodName, message));
		if (flagSyso) {
            System.out.println(methodName + " - " + message);
        }
	}

	public void infoStart(String methodName){
		logger.info(String.format(STARTPATTERN, className, methodName));
		if (flagSyso) {
            System.out.println("inizio " + methodName);
        }
	}
	public void infoEnd(String methodName){
		logger.info(String.format(ENDPATTERN, className, methodName));
		if (flagSyso) {
            System.out.println("fine " + methodName);
        }
	}
	public void info(String methodName, Object message) {
		logger.info(String.format(PATTERN, className, methodName, message));
		if (flagSyso) {
            System.out.println(methodName + " - " + message);
        }
	}

	public void warn(String methodName, Object message) {
		logger.warn(String.format(PATTERN, className, methodName, message));
		if (flagSyso) {
            System.out.println(methodName + " - " + message);
        }
	}
	public void warn(String methodName, Object message, Throwable t) {
		logger.warn(String.format(PATTERN, className, methodName, message), t);
		if (flagSyso) {
            System.out.println(methodName + " - " + message);
        }
	}

	public void error(String methodName, Object message) {
		logger.error(String.format(PATTERN, className, methodName, message));
		if (flagSyso) {
            System.out.println(methodName + " - " + message);
        }
	}
	public void error(String methodName, Object message, Throwable t) {
		logger.error(String.format(PATTERN, className, methodName, message), t);
		if (flagSyso) {
            System.out.println(methodName + " - " + message);
        }
	}

	public boolean isDebugEnabled(){
		return logger.isDebugEnabled();
	}
	public boolean isInfoEnabled(){
		return logger.isInfoEnabled();
	}
	public boolean isTraceEnabled(){
		return logger.isTraceEnabled();
	}
}
