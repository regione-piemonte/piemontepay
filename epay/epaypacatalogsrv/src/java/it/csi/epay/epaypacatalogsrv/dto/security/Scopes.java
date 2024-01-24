/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.dto.security;

/**
 * Enumerazione contenente tutti gli Scopes gestiti dall'applicativo
 */
public enum Scopes {
        VERIFICA_STATO ( "Monitoraggio dello stato applicativo" ),
        CREAZIONE_IUV ( "Creazione IUV" ),
        PLATFORM_MANAGEMENT ( "Gestione della piattaforma (backoffice)" ),
        STAMPA_RT_GE ("Stampa RT da gestionale esterno"),
        DATI_SPEC_RISC ( "Richiesta dati specifici Riscossione" ),
       RIFERIMENTI_CONTABILI ("Riferimenti Contabili");

    private String descrizione;

    Scopes ( String descrizione ) {
        this.descrizione = descrizione;
    }

    public String getDescrizione () {
        return descrizione;
    }

}
