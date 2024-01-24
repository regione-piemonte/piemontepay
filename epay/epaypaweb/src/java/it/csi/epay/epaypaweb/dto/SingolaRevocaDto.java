/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;



public class SingolaRevocaDto extends ElementoFlussoDto implements Serializable{
    
    
    private Long idRr;
    
    private BigDecimal singoloImportoRevocato;
    
    private String iur;
    
    private String casualeRevoca;
    
    private String datiAggiuntiRevoca;

    
    public Long getIdRr () {
        return idRr;
    }

    
    public void setIdRr ( Long idRr ) {
        this.idRr = idRr;
    }

    
    public BigDecimal getSingoloImportoRevocato () {
        return singoloImportoRevocato;
    }

    
    public void setSingoloImportoRevocato ( BigDecimal singoloImportoRevocato ) {
        this.singoloImportoRevocato = singoloImportoRevocato;
    }

    
    public String getIur () {
        return iur;
    }

    
    public void setIur ( String iur ) {
        this.iur = iur;
    }

    
    public String getCasualeRevoca () {
        return casualeRevoca;
    }

    
    public void setCasualeRevoca ( String casualeRevoca ) {
        this.casualeRevoca = casualeRevoca;
    }

    
    public String getDatiAggiuntiRevoca () {
        return datiAggiuntiRevoca;
    }

    
    public void setDatiAggiuntiRevoca ( String datiAggiuntiRevoca ) {
        this.datiAggiuntiRevoca = datiAggiuntiRevoca;
    }

}
