/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.interfacews.epaymodric.enums;


public enum StatoElaborazioneEnum {
    NON_ATTIVA ("NON ATTIVA", "Elaborazione non attiva"),
    SCHEDULATA ("SCHEDULATA", "Elaborazione schedulata"),
    AVVIATA ("AVVIATA", "Elaborazione avviata"),
    IN_ERRORE ("IN ERRORE", "Elaborazione in errore"),
    TERMINATA ("TERMINATA", "Elaborazione terminata"),
    FORZATA ("FORZATA", "Elaborazione per cui viene richiesta la riesecuzione")
    ;

    private final String codice;  
    private final String descrizione; 
    
    StatoElaborazioneEnum(String codice, String descrizione) {
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
