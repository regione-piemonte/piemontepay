/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EpaypaTDatiSingolaRevocaId implements Serializable{
    


    
    @Column(name="id_rr")
    private Long idRr;
    @Column(name="singolo_importo_revocato")
    private BigDecimal singoloImportoRevocato;
    private String iur;
    @Column(name="causale_revoca")
    private String casualeRevoca;
    @Column(name="dati_aggiuntivi_revoca")
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EpaypaTDatiSingolaRevocaId)) return false;
        EpaypaTDatiSingolaRevocaId that = (EpaypaTDatiSingolaRevocaId) o;
        return Objects.equals(getIdRr(), that.getIdRr()) &&
                Objects.equals(getIur(), that.getIur());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getIdRr(), getIur());
    }

}
