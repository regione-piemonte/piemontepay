/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.math.BigDecimal;


public class TipoRevocaDto {
    
    private BigDecimal id;
    
    private String codice ;
    
    private String descrizione ;

    
    public BigDecimal getId () {
        return id;
    }

    
    public void setId ( BigDecimal id ) {
        this.id = id;
    }

    
    public String getCodice () {
        return codice;
    }

    
    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    
    public String getDescrizione () {
        return descrizione;
    }

    
    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

}
