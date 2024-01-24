/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;

import it.csi.epay.epayservices.utilities.LogUtil;


public class JobContext extends JobContextDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private LogUtil logger;

    private String loggerPrefix;

    public JobContext () {
        // nop
        super ();
    }

    public JobContext ( String jobName ) {
        super ( jobName );
        loggerPrefix = jobName + "[" + getUuid () + "]";
        logger = new LogUtil ( loggerPrefix );
    }


    public LogUtil getLogger () {
        return logger;
    }

    public void debugStart ( String methodName ) {
        logger.debugStart ( methodName );
    }

    public void debugEnd ( String methodName ) {
        logger.debugEnd ( methodName );
    }

    public void debug ( String methodName, String message ) {
        logger.debug ( methodName, message );
    }

    public void infoStart ( String methodName ) {
        logger.infoStart ( methodName );
    }

    public void infoEnd ( String methodName ) {
        logger.infoEnd ( methodName );
    }

    public void info ( String methodName, String message ) {
        logger.info ( methodName, message );
    }

    public void warn ( String methodName, String message ) {
        logger.warn ( methodName, message );
    }

    public void warn ( String methodName, String message, Throwable t ) {
        logger.warn ( methodName, message, t );
    }

    public void error ( String methodName, String message ) {
        logger.error ( methodName, message );
    }

    public void error ( String methodName, String message, Throwable t ) {
        logger.error ( methodName, message, t );
    }

    public boolean isDebugEnabled () {
        return logger.isDebugEnabled ();
    }

    public boolean isInfoEnabled () {
        return logger.isInfoEnabled ();
    }

    public boolean isTraceEnabled () {
        return logger.isTraceEnabled ();
    }
}
