/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.model.lock;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;


public class RicercaLockFiltroVO implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // campo finto per mostrare messaggi non relativi ad un campo specifico 
    // (sono sicuro che esiste un modo migliore per fare questa cosa)
    private String validitaGenerica;

    @DateTimeFormat ( pattern = "dd/MM/yyyy" )
    private Date dataLockA;

    @DateTimeFormat ( pattern = "dd/MM/yyyy" )
    private Date dataLockDa;

    private String idEnte;

    public RicercaLockFiltroVO () {
        // TODO Auto-generated constructor stub
    }

    public boolean isEmpty () {
        return ( idEnte == null || idEnte.isEmpty ()) &&
            StringUtils.isBlank ( idEnte ) ||
            (dataLockDa == null ||
            dataLockA == null);
    }
   
    public String getValiditaGenerica () {
        return validitaGenerica;
    }

    public void setValiditaGenerica ( String validitaGenerica ) {
        this.validitaGenerica = validitaGenerica;
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

}
