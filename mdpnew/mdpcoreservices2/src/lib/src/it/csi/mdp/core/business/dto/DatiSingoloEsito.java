/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("dati_singolo_esito")
public class DatiSingoloEsito implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 471284299350917096L;
    
    private Integer id;
    
    @XStreamAlias("dati_singolo_esito")
    private Double singoloImportoRevocato;
    @XStreamAlias("iur")
    private String iur;
    @XStreamAlias("causale_esito")
    private String causaleEsito;
    @XStreamAlias("dati_aggiuntivi_esito")
    private String datiAggiuntiviEsito;
    

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
    
    public String getCausaleEsito () {
        return causaleEsito;
    }
    
    public void setCausaleEsito ( String causaleEsito ) {
        this.causaleEsito = causaleEsito;
    }
    
    public String getDatiAggiuntiviEsito () {
        return datiAggiuntiviEsito;
    }
    
    public void setDatiAggiuntiviEsito ( String datiAggiuntiviEsito ) {
        this.datiAggiuntiviEsito = datiAggiuntiviEsito;
    }
    
    public static long getSerialversionuid () {
        return serialVersionUID;
    }
}
