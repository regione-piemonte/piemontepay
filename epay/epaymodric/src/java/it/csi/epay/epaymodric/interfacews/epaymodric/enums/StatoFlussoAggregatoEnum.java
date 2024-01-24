/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.interfacews.epaymodric.enums;


public enum StatoFlussoAggregatoEnum {
	VUOTO("",""),
    NON_ELABORATO_ANCORA_DA_ELABORARE("Non elaborato", "Ancora da elaborare"),
    DEFAULT("Non disponibile", "Stato flusso ancora da definire"),
    NON_ELABORATO_IN_ERRORE_MPD("Non elaborato", "In errore su MDP"),
    INVIATO_ENDPOINT("Inviato", ""),
    NON_INVIATO_ENDPOINT("Non inviato", ""),
    ELABORATO_NON_INVIATO("Elaborato", "Non inviato"),
    NON_ELABORATO_IN_ERRORE_MODRIC("Non elaborato", "In errore su modric"),
    NON_ELABORATO_NON_ELABORABILE("Non elaborato", "Non elaborabile"),
    NON_ELABORATO_IN_ATTESA("Non elaborato", "Flusso in attesa di essere elaborato o impostato come non elaborabile"),
    NON_ELABORATO_BOZZA("Non elaborato", "Flusso in bozza"),
    NON_ELABORATO_ANNULLATO("Non elaborato", "Annullato"),
    NON_INVIATO_WSO_DIVERSO_5("Non inviato", ""),
    NON_INVIATO_WSO_DIVERSO_000("Non inviato", ""),
    NON_INVIATO_NON_DISPONIBILE("Non inviato", "Non disponibile")
    
    ;

    private final String codice;  
    private final String datoAggiuntivo; 
    
    StatoFlussoAggregatoEnum(String codice, String datoAggiuntivo) {
        this.codice = codice;
        this.datoAggiuntivo = datoAggiuntivo;
    }
    
    public String getCodice () {
        return codice;
    }
    
    public String getDatoAggiuntivo () {
        return datoAggiuntivo;
    }

}
