/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.common;

public abstract class Constants {

    public static String ROUTING_SEPARATOR = "/";

    public static String SEARCH_RESULTS = "results";
    
    public static String TIPO_PAGAMENTO_SPONTANEO = "PS";

    public static class USE_CASES {

        public final static String LOGIN = "LOGIN";
        public final static String ADMIN = "ADMIN";
        public final static String MODIFICA_ENTE = "MODIFICA_ENTE";
        public final static String RICERCA_VOCE_ENTRATA = "RICERCA_VOCE_ENTRATA";
        public final static String RICERCA_CODICE_VERSAMENTO = "RICERCA_CODICE_VERSAMENTO";
        public final static String INSERISCI_CODICE_VERSAMENTO = "INSERISCI_CODICE_VERSAMENTO";
        public final static String MODIFICA_CODICE_VERSAMENTO = "MODIFICA_CODICE_VERSAMENTO";
        public final static String ELIMINA_CODICE_VERSAMENTO = "ELIMINA_CODICE_VERSAMENTO";
        public final static String RICERCA_RIFERIMENTO_CONTABILE = "RICERCA_RIFERIMENTO_CONTABILE";
        public final static String INSERISCI_RIFERIMENTO_CONTABILE = "INSERISCI_RIFERIMENTO_CONTABILE";
        public final static String MODIFICA_RIFERIMENTO_CONTABILE = "MODIFICA_RIFERIMENTO_CONTABILE";
        public final static String ELIMINA_RIFERIMENTO_CONTABILE = "ELIMINA_RIFERIMENTO_CONTABILE";
        public final static String RICERCA_UTENTE = "RICERCA_UTENTE";
        public final static String INSERISCI_UTENTE = "INSERISCI_UTENTE";
        public final static String MODIFICA_UTENTE = "MODIFICA_UTENTE";
        public final static String ELIMINA_UTENTE = "ELIMINA_UTENTE";
        public final static String AUTORIZZA_FUNZIONE = "AUTORIZZA_FUNZIONE";
        public final static String AUTORIZZA_CODICE_VERSAMENTO = "AUTORIZZA_CODICE_VERSAMENTO";
        public final static String RICERCA_TASSONOMIA = "RIC_TASSONOMIA";
        
        

        public final static String AMMINISTRATORE_ENTE = "AMMINISTRATORE_ENTE";
    }

    static {
    }
}
