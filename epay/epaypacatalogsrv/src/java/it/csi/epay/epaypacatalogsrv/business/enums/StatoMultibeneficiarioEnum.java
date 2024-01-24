/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.enums;


public enum StatoMultibeneficiarioEnum {
    OK(new Integer("1"), "OK"),
    INCOMPLETE(new Integer("2"), "INCOMPLETE"),
    NONE(new Integer("3"), "NONE"),
    ;

    private final Integer codice;  
    private final String descrizione; 
    
    StatoMultibeneficiarioEnum(Integer codice, String descrizione) {
        this.codice = codice;
        this.descrizione = descrizione;
    }

    public Integer getCodice () {
        return codice;
    }
    
    public String getDescrizione () {
        return descrizione;
    }

}
