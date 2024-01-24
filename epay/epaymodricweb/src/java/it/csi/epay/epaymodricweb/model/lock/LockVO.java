/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.model.lock;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;


public class LockVO implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private Boolean checkControl;
    
    @DateTimeFormat ( pattern = "dd/MM/yyyy" )
    private Date dataLockA;

    @DateTimeFormat ( pattern = "dd/MM/yyyy" )
    private Date dataLockDa;

    private String idEnte;

    private String descrizioneEnte;

    private String utente;

    public LockVO () {
        // TODO Auto-generated constructor stub
    }

    public Date getDataLockA () {
        return dataLockA;
    }

    public void setDataLockA ( Date dataLockA ) {
        this.dataLockA = dataLockA;
    }

    public Date getDataLockDa () {
        return dataLockDa;
    }

    public void setDataLockDa ( Date dataLockDa ) {
        this.dataLockDa = dataLockDa;
    }

    public String getIdEnte () {
        return idEnte;
    }

    public void setIdEnte ( String idEnte ) {
        this.idEnte = idEnte;
    }

    public String getDescrizioneEnte () {
        return descrizioneEnte;
    }

    public void setDescrizioneEnte ( String descrizioneEnte ) {
        this.descrizioneEnte = descrizioneEnte;
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getUtente () {
        return utente;
    }

    public void setUtente ( String utente ) {
        this.utente = utente;
    }

    
    public Boolean getCheckControl () {
        return checkControl;
    }

    
    public void setCheckControl ( Boolean checkControl ) {
        this.checkControl = checkControl;
    }

}
