/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.interfacews.epaymodric.enums;


public enum ModalitaAcquisizioneProvvisoriEnum {
    SERVIZI(new Integer("1"), "Servizi"),
    SIOPE(new Integer("2"), "xml - siope+"),
    XSL(new Integer("3"), "xls/csv"),
    GUI(new Integer("4"), "da GUI"),
    NESSUNA(new Integer("0"),"Nessuna")
    ;

    private final Integer codice;  
    private final String descrizione; 
    
    ModalitaAcquisizioneProvvisoriEnum(Integer codice, String descrizione) {
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
