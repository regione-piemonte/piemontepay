/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaybeapi.dto.security;

/**
 * Enumerazione contenente tutti gli Scopes gestiti dall'applicativo
 */
public enum Scopes {
        VERIFICA_STATO ( "Monitoraggio dello stato applicativo" ),
        COMPONENTI_IMPORTO ( "Componenti importo" ),
        PLATFORM_MANAGEMENT ( "Gestione della piattaforma (backoffice)" ),
        STAMPA_AVVISO_PAGAMENTO ("Stampa avviso pagamento"),
        RICHIESTA_STATO_POS_DEB ("Richiesta stato posizione debitoria");
    

    private String descrizione;

    Scopes ( String descrizione ) {
        this.descrizione = descrizione;
    }

    public String getDescrizione () {
        return descrizione;
    }

}
