/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("dati_singola_revoca")
public class DatiSingolaRevoca implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 6815005233626296623L;

    private Integer id;
    
    @XStreamAlias("singolo_importo_revocato")
    private Double singoloImportoRevocato;
   
    @XStreamAlias("iur")
    private String iur;
    
    @XStreamAlias("causale_revoca")    
    private String causaleRevoca;
    
    @XStreamAlias("dati_aggiuntivi_revoca")     
    private String datiAggiuntiviRevoca;

    
    public Integer getId () {
        return id;
    }

    
    public void setId ( Integer id ) {
        this.id = id;
    }

    
    public Double getSingoloImportoRevocato () {
        return singoloImportoRevocato;
    }

    
    public void setSingoloImportoRevocato ( Double singoloImportoRevocato ) {
        this.singoloImportoRevocato = singoloImportoRevocato;
    }

    
    public String getIur () {
        return iur;
    }

    
    public void setIur ( String iur ) {
        this.iur = iur;
    }

    
    public String getCausaleRevoca () {
        return causaleRevoca;
    }

    
    public void setCausaleRevoca ( String causaleRevoca ) {
        this.causaleRevoca = causaleRevoca;
    }

    
    public String getDatiAggiuntiviRevoca () {
        return datiAggiuntiviRevoca;
    }

    
    public void setDatiAggiuntiviRevoca ( String datiAggiuntiviRevoca ) {
        this.datiAggiuntiviRevoca = datiAggiuntiviRevoca;
    }

    
    public static long getSerialversionuid () {
        return serialVersionUID;
    }
    
    
}
