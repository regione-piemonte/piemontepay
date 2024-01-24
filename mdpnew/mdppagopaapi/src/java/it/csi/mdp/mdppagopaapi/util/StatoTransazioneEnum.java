/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.util;


public enum StatoTransazioneEnum {
    NOT_INITIALIZED(new Long (0), "Not initialized" ),
    INITIALIZED(new Long (1), "Initialized" ),
    CONFIGURED(new Long (2), "Configured" ),
    STARTED(new Long (3), "Started" ),
    SUCCESFUL(new Long (4), "Successful" ),
    UNSUCCESFUL(new Long (5), "Unsuccessful" ),
    CANCELED(new Long (6), "Canceled" ),
    REFOUNDED(new Long (7), "Refunded" ),
    TO_BE_CONFIRMED(new Long (8), "To be confirmed" ),
    ATTESA_RT(new Long (9), "Attesa RT" );
    
     

    private final Long codice;  
    private final String descrizione; 
    
    StatoTransazioneEnum(Long codice, String descrizione) {
        this.codice = codice;
        this.descrizione = descrizione;
    }
   
    
    public Long getCodice () {
        return codice;
    }


    public String getDescrizione () {
        return descrizione;
    }

}
