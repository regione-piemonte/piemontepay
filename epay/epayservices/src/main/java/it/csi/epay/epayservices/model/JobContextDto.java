/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


public class JobContextDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String componente;

    private String jobName;

    private String uuid;

    private LocalDateTime start;

    private List<String> problemi;
    
    private String  ambiente;
    
    public JobContextDto () {
        // nop
        problemi = new LinkedList<> ();
    }

    public JobContextDto ( String jobName ) {
        super ();
        componente = "epayservices";
        uuid = UUID.randomUUID ().toString ();
        this.jobName = jobName;
        problemi = new LinkedList<> ();
        start = LocalDateTime.now ();
        ambiente= "";
    }

    public void addProblem ( String problem ) {
        problemi.add ( LocalDateTime.now ().format ( DateTimeFormatter.ISO_LOCAL_DATE_TIME ) + " - " + problem );
    }

    public String getComponente () {
        return componente;
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

    public void setComponente ( String componente ) {
        this.componente = componente;
    }

    public void setJobName ( String jobName ) {
        this.jobName = jobName;
    }

    public void setUuid ( String uuid ) {
        this.uuid = uuid;
    }

    public void setStart ( LocalDateTime start ) {
        this.start = start;
    }

    public void setProblemi ( List<String> problemi ) {
        this.problemi = problemi;
    }

    
    public String getAmbiente () {
        return ambiente;
    }

    
    public void setAmbiente ( String ambiente ) {
        this.ambiente = ambiente;
    }


}
