/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import it.csi.epay.epayjob.utilities.LogUtil;
import it.csi.epay.epayservices.model.JobContextDto;


public class JobContext implements Serializable {

    private static final long serialVersionUID = 1L;

    private String componente;

    private String jobName;

    private String uuid;

    private LocalDateTime start;

    private LogUtil logger;

    private List<String> problemi;

    private String loggerPrefix;
    
   private String ambiente;
   
    public JobContext () {
        // nop
        problemi = new LinkedList<> ();
    }

    public JobContext ( String jobName ) {
        super ();
        componente = "epayjob";
        uuid = UUID.randomUUID ().toString ();
        this.jobName = jobName;
        problemi = new LinkedList<> ();
        start = LocalDateTime.now ();
        loggerPrefix = jobName + "[" + uuid + "]";
        logger = new LogUtil ( jobName );
        ambiente ="";
    }

    public JobContextDto estraiDto () {
        JobContextDto output = new JobContextDto ();
        output.setComponente ( componente );
        output.setJobName ( jobName );
        output.setProblemi ( problemi );
        output.setStart ( start );
        output.setUuid ( uuid );
        output.setAmbiente ( ambiente );
        return output;
    }

    public String getComponente () {
        return componente;
    }

    public void addProblem ( String problem ) {
        problemi.add ( LocalDateTime.now ().format ( DateTimeFormatter.ISO_LOCAL_DATE_TIME ) + " - " + problem );
    }

    public LogUtil getLogger () {
        return logger;
    }

    public String getJobName () {
        return jobName;
    }

    public String getUuid () {
        return uuid;
    }

    public LocalDateTime getStart () {
        return start;
    }

    public List<String> getProblemi () {
        return problemi;
    }

    public void debugStart ( String methodName ) {
        logger.debugStart ( loggerPrefix + "." + methodName );
    }

    public void debugEnd ( String methodName ) {
        logger.debugEnd ( loggerPrefix + "." + methodName );
    }

    public void debug ( String methodName, String message ) {
        logger.debug ( loggerPrefix + "." + methodName, message );
    }

    public void infoStart ( String methodName ) {
        logger.infoStart ( loggerPrefix + "." + methodName );
    }

    public void infoEnd ( String methodName ) {
        logger.infoEnd ( loggerPrefix + "." + methodName );
    }

    public void info ( String methodName, String message ) {
        logger.info ( loggerPrefix + "." + methodName, message );
    }

    public void warn ( String methodName, String message ) {
        logger.warn ( loggerPrefix + "." + methodName, message );
    }

    public void warn ( String methodName, String message, Throwable t ) {
        logger.warn ( loggerPrefix + "." + methodName, message, t );
    }

    public void error ( String methodName, String message ) {
        logger.error ( loggerPrefix + "." + methodName, message );
    }

    public void error ( String methodName, String message, Throwable t ) {
        logger.error ( loggerPrefix + "." + methodName, message, t );
    }

    public void debugStart () {
        logger.debugStart ( loggerPrefix );
    }

    public void debugEnd () {
        logger.debugEnd ( loggerPrefix );
    }

    public void debug ( String message ) {
        logger.debug ( loggerPrefix, message );
    }

    public void infoStart () {
        logger.infoStart ( loggerPrefix );
    }

    public void infoEnd () {
        logger.infoEnd ( loggerPrefix );
    }

    public void info ( String message ) {
        logger.info ( loggerPrefix, message );
    }

    public void warn ( String message ) {
        logger.warn ( loggerPrefix, message );
    }

    public void warn ( String message, Throwable t ) {
        logger.warn ( loggerPrefix, message, t );
    }

    public void error ( String message ) {
        logger.error ( loggerPrefix, message );
    }

    public void error ( String message, Throwable t ) {
        logger.error ( loggerPrefix, message, t );
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

    
    public String getAmbiente () {
        return ambiente;
    }

    
    public void setAmbiente ( String ambiente ) {
        this.ambiente = ambiente;
    }   
    
}
