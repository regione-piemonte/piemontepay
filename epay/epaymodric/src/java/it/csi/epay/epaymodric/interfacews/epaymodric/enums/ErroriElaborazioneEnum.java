/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.interfacews.epaymodric.enums;

public enum ErroriElaborazioneEnum {
        ERR_ACQUISIZIONE ( "001", "Errore in fase di acquisizione" ),
        ERR_SISTEMA_GENERICO ( "002", "Errore di sistema generico" ),
        ERR_BUSINESS_GENERICO ( "003", "Errore di business generico" ),
        ERR_RECUPERO_INFORMAZIONI ( "004", "Errore recupero informazioni : id_flusso {identificativo} mancante" )
        ;

    private final String codice;

    private final String descrizione;

    ErroriElaborazioneEnum ( String codice, String descrizione ) {
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
