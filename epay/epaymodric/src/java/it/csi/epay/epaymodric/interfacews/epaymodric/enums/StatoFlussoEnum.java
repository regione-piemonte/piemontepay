/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.interfacews.epaymodric.enums;


public enum StatoFlussoEnum {
    DA_SPACCHETTARE("DA SPACCHETTARE", "Flusso in attesa di spacchettamento"),
    BOZZA ("BOZZA", "Bozza"),
    IN_ATTESA ("IN ATTESA", "Flusso in attesa di essere elaborato"),
    IN_ELABORAZIONE ("IN ELABORAZIONE", "Elaborazione in corso"),
    ELABORATO ("ELABORATO", "Elaborato"),
    IN_ERRORE ("IN ERRORE", "Errore durante l'elaborazione"),
    NON_ELABORABILE ("NON ELABORABILE", "Flusso non elaborabile"),
    NON_RICONCILIABILE ("NON RICONCILIABILE", "Flusso non riconciliabile"),
    ACQUISITO ("ACQUISITO", "Documento acquisito da Sistema Contabile"),
    GENERATO ("GENERATO", "Documento contabile Generato"),
    RIFIUTATO ("RIFIUTATO", "Rifiutato da sistema contabile"),
    FORZATO ("FORZATO", "Richiesta riesecuzione"),
    DA_RIELABORARE ("DA RIELABORARE", "Richiesta riesecuzione")
    ;

    private final String codice;  
    private final String descrizione; 
    
    StatoFlussoEnum(String codice, String descrizione) {
        this.codice = codice;
        this.descrizione = descrizione;
    }
    
    public boolean isElaborabile() {
        return 
           BOZZA.codice.equalsIgnoreCase ( getCodice() )
           || IN_ATTESA.codice.equalsIgnoreCase ( getCodice() )
           || FORZATO.codice.equalsIgnoreCase ( getCodice() )
           || DA_RIELABORARE.codice.equalsIgnoreCase ( getCodice() )
           ;
    }
    
    public String getCodice () {
        return codice;
    }
    
    public String getDescrizione () {
        return descrizione;
    }

}
