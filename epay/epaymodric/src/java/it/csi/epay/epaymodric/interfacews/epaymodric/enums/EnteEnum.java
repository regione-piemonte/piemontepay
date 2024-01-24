/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.interfacews.epaymodric.enums;


public enum EnteEnum {
    ENTE_SCONOSCIUTO("XXXX", "Ente sconosciuto"),
    ENTE_TEST("9999", "Ente test"),
    ;

    private final String codice;
    private final String descrizione; 
    
    EnteEnum(String codice, String descrizione) {
        this.codice = codice;
        this.descrizione = descrizione;
    }

    public String getCodice () {
        return codice;
    }
    
    public String getDescrizione () {
        return descrizione;
    }

}
