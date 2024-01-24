/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities;

import java.io.Serializable;

import org.jboss.logging.Logger;

/**
 * Utility centralizzata per il logging.
 */
public class LogUtil implements Serializable {

	private static final long serialVersionUID = -1493319176512157289L;
	private static final String PATTERN = "[%s.%s] - %s";
	private static final String STARTPATTERN = "[%s.%s] - Start.";
	private static final String ENDPATTERN = "[%s.%s] - End.";

	private static final String LOGGER_NAME = "epayservices";

	private Logger logger;
	private String className;

	public LogUtil(Class<?> clazz) {
		logger = Logger.getLogger(LOGGER_NAME);
		className = clazz.getSimpleName();
	}

    public LogUtil ( String className ) {
        logger = Logger.getLogger ( LOGGER_NAME );
        this.className = className;
    }

    public void debugStart ( String methodName ) {
        if ( logger.isDebugEnabled () ) {
            logger.debug ( String.format ( STARTPATTERN, className, methodName ) );
        }
    }

    public void debugEnd ( String methodName ) {
        if ( logger.isDebugEnabled () ) {
            logger.debug ( String.format ( ENDPATTERN, className, methodName ) );
        }
    }

    public void debug ( String methodName, Object message ) {
        if ( logger.isDebugEnabled () ) {
            logger.debug ( String.format ( PATTERN, className, methodName, message ) );
        }
    }

	public void infoStart(String methodName){
		logger.info(String.format(STARTPATTERN, className, methodName));
	}
	public void infoEnd(String methodName){
		logger.info(String.format(ENDPATTERN, className, methodName));
	}
	public void info(String methodName, Object message) {
		logger.info(String.format(PATTERN, className, methodName, message));
	}

	public void warn(String methodName, Object message) {
		logger.warn(String.format(PATTERN, className, methodName, message));
	}
	public void warn(String methodName, Object message, Throwable t) {
		logger.warn(String.format(PATTERN, className, methodName, message), t);
	}

	public void error(String methodName, Object message) {
		logger.error(String.format(PATTERN, className, methodName, message));
	}
	public void error(String methodName, Object message, Throwable t) {
		logger.error(String.format(PATTERN, className, methodName, message), t);
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
