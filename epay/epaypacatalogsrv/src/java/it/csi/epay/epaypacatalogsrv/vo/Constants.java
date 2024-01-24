/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.vo;

public class Constants {

    public final static String APPLICATION_NAME = "epaypacatalogsrv";

    public final static String APPLICATION_CODE = "EPAYPACATALOGSRV";

    public final static String DEFAULT_LANGUAGE = "it_IT";

    public final static String REQUEST_ATTRIBUTE_PRINCIPAL = "epayCatalogSrvPrincipal";

    public static final String CODICE_UTENTE_SISTEMA = "SISTEMA";

    public final static String CODICE_UTENTE_MIGRAZIONE = "MIGRATION_AGENT";
    
    public static final String PASSWORD_PASSPHRASE = "password.passphrase";
    
    public static final String PASSWORD_NOTIFICATION_PRICE = "password.notification.price";

    public static class USE_CASES {

        public final static String LOGIN = "LOGIN";

        public final static String RICERCA_VOCE_ENTRATA = "RICERCA_VOCE_ENTRATA";

        public final static String RICERCA_CODICE_VERSAMENTO = "RICERCA_CODICE_VERSAMENTO";

        public final static String INSERISCI_CODICE_VERSAMENTO = "INSERISCI_CODICE_VERSAMENTO";

        public final static String MODIFICA_CODICE_VERSAMENTO = "MODIFICA_CODICE_VERSAMENTO";

        public final static String ELIMINA_CODICE_VERSAMENTO = "ELIMINA_CODICE_VERSAMENTO";

        public final static String RICERCA_RIFERIMENTO_CONTABILE = "RICERCA_RIFERIMENTO_CONTABILE";

        public final static String INSERISCI_RIFERIMENTO_CONTABILE = "INSERISCI_RIFERIMENTO_CONTABILE";

        public final static String MODIFICA_RIFERIMENTO_CONTABILE = "MODIFICA_RIFERIMENTO_CONTABILE";

        public final static String ELIMINA_RIFERIMENTO_CONTABILE = "ELIMINA_RIFERIMENTO_CONTABILE";

        public final static String AMMINISTRATORE_ENTE = "AMMINISTRATORE_ENTE";

        public final static String MODIFICA_ENTE = "MODIFICA_ENTE";

        public final static String RICERCA_UTENTE = "RICERCA_UTENTE";

        public final static String INSERISCI_UTENTE = "INSERISCI_UTENTE";

        public final static String MODIFICA_UTENTE = "MODIFICA_UTENTE";

        public final static String ELIMINA_UTENTE = "ELIMINA_UTENTE";

        public final static String AUTORIZZA_FUNZIONE = "AUTORIZZA_FUNZIONE";

        public final static String AUTORIZZA_CODICE_VERSAMENTO = "AUTORIZZA_CODICE_VERSAMENTO";

        public final static String IMPERSONATED = "IMPERSONATED";

        public final static String RICERCA_TASSONOMIA = "RIC_TASSONOMIA";
        
        public final static String ADMIN = "ADMIN";
        
        public final static String ASSISTENZA = "ASSISTENZA";
        
        

        public final static String [] CDU_VIRTUALI_AMMINISTRATORE_ENTE = new String [] {
            AMMINISTRATORE_ENTE,
            MODIFICA_ENTE,
            RICERCA_UTENTE,
            INSERISCI_UTENTE,
            MODIFICA_UTENTE,
            ELIMINA_UTENTE,
            AUTORIZZA_FUNZIONE,
            AUTORIZZA_CODICE_VERSAMENTO
        };
    }

    public static class FRUITORI {

        public final static String EPAYPACATALOGWEB = "EPAYPACATALOGWEB";

        public final static String EPAYMODRICWEB = "EPAYMODRICWEB";

        public final static String EPAYSIMWEB = "EPAYSIMWEB";

        public final static String EPAYMODRIC = "EPAYMODRIC";

        public final static String UNIT_TEST = "TEST";

        public final static String EPAYPA = "EPAYPA";

        public final static String INTERNAL = "INTERNAL";
    }

    public static class SCOPES {

        public final static String WEBAPP = "WEBAPP";

        public final static String CLIENT_SERVICE = "CLIENT_SERVICE";

        public final static String PROFILAZIONE_UTENTE_CORRENTE = "PROFILAZIONE_UTENTE_CORRENTE";

        public final static String PROFILAZIONE_UTENTE = "PROFILAZIONE_UTENTE";

        public final static String CONFIGURATORE = "CONFIGURATORE";

        public final static String INTERNAL = "INTERNAL";

    }

    public static class RESULT_CODES {

        public final static String OK = "OK";

        public final static String NOT_ALLOWED = "NOT_ALLOWED";

        public final static String INTERNAL_ERROR = "INTERNAL_ERROR";

        public final static String BAD_REQUEST = "BAD_REQUEST";

        public final static String NOT_FOUND = "NOT_FOUND";

        public final static String WSO2_BROADCASTING_FAILED = "WSO2_BROADCASTING_FAILED";

    }

    public static class MESSAGES {

        public static final String OK = "OK";

        public static final String NOT_ALLOWED = "NOT_ALLOWED";

        public static final String INTERNAL_ERROR = "INTERNAL_ERROR";

        public static final String BAD_REQUEST = "BAD_REQUEST";

        public static final String NOT_FOUND = "NOT_FOUND";

        public static final String INVALID_CALLER = "INVALID_CALLER";

        public static final String INVALID_CF = "INVALID_CF";

        public static final String INVALID_CODICE_ENTE = "INVALID_CODICE_ENTE";

        public static final String INVALID_CODICE_RUOLO = "INVALID_CODICE_RUOLO";

        public static final String USER_NOT_FOUND = "USER_NOT_FOUND";

        public static final String ENTE_NOT_FOUND = "ENTE_NOT_FOUND";

        public static final String RUOLO_NOT_FOUND = "RUOLO_NOT_FOUND";

        public static final String ENTE_RUOLO_NOT_ASSOCIATED = "ENTE_RUOLO_NOT_ASSOCIATED";

        public static final String USER_NOT_ADMIN_OF_ENTE = "USER_NOT_ADMIN_OF_ENTE";

        public static final String FIELD_REQUIRED = "FIELD_REQUIRED";

        public static final String INVALID_FIELD = "INVALID_FIELD";

        public static final String FORBIDDEN_FIELD = "FORBIDDEN_FIELD";

        public static final String ENTE_WITHOUT_FLUSSI_ASSOCIATI = "ENTE_WITHOUT_FLUSSI_ASSOCIATI";

        public static final String UTENTE_ENTE_NOT_ASSOCIATED = "UTENTE_ENTE_NOT_ASSOCIATED";

        public static final String UTENTE_RUOLO_NOT_ASSOCIATED = "UTENTE_RUOLO_NOT_ASSOCIATED";

        public static final String CODICE_VERSAMENTO_NOT_FOUND = "CODICE_VERSAMENTO_NOT_FOUND";

        public static final String CODICE_VERSAMENTO_COLLEGATO_NOT_FOUND = "CODICE_VERSAMENTO_COLLEGATO_NOT_FOUND";

        public static final String CODICE_VERSAMENTO_ALREADY_EXISTING = "CODICE_VERSAMENTO_ALREADY_EXISTING";

        public static final String CODICE_VERSAMENTO_NUM_COLLEGATI_EXCEEDED = "CODICE_VERSAMENTO_NUM_COLLEGATI_EXCEEDED";

        public static final String CODICE_VERSAMENTO_DESCRIZIONE_UGUALE_PADRE = "CODICE_VERSAMENTO_DESCRIZIONE_UGUALE_PADRE";

        public static final String CODICE_VERSAMENTO_VOCE_ENTRATA_NON_MODIFICABILE = "CODICE_VERSAMENTO_VOCE_ENTRATA_NON_MODIFICABILE";

        public static final String CODICE_VERSAMENTO_NON_ELIMINABILE_ASSOCIATO = "CODICE_VERSAMENTO_NON_ELIMINABILE_ASSOCIATO";

        public static final String EMPTY_INPUT_NOT_ALLOWED = "EMPTY_INPUT_NOT_ALLOWED";

        public static final String FRUITORE_NOT_FOUND = "FRUITORE_NOT_FOUND";

        public static final String FRUITORE_NOT_VALID = "FRUITORE_NOT_VALID";

        public static final String CODICE_VERSAMENTO_NUM_RIFERIMENTI_EXCEEDED = "CODICE_VERSAMENTO_NUM_RIFERIMENTI_EXCEEDED";

        public static final String RIFERIMENTO_CONTABILE_NON_IN_VITA = "RIFERIMENTO_CONTABILE_NON_IN_VITA";
        public static final String RIFERIMENTO_CONTABILE_GENERICI_ERROR = "RIFERIMENTO_CONTABILE_GENERICI_ERROR";

        public static final String RIFERIMENTO_CONTABILE_NOT_FOUND = "RIFERIMENTO_CONTABILE_NOT_FOUND";

        public static final String CDU_NOT_FOUND = "CDU_NOT_FOUND";

        public static final String TEMATICA_NOT_FOUND = "TEMATICA_NOT_FOUND";

        public static final String UTENTE_NON_ELIMINABILE = "UTENTE_NON_ELIMINABILE";

        public static final String RIFERIMENTO_CONTABILE_CAMBIO_DSR_NON_CONSENTITO = "RIFERIMENTO_CONTABILE_CAMBIO_DSR_NON_CONSENTITO";

        public static final String CODICE_VERSAMENTO_NON_ELIMINABILE = "CODICE_VERSAMENTO_NON_ELIMINABILE";

        public static final String CODICE_VERSAMENTO_NON_ELIMINABILE_ASSOCIATO_COLLEGATI = "CODICE_VERSAMENTO_NON_ELIMINABILE_ASS_COLL";

        public static final String CODICE_VERSAMENTO_NON_ELIMINABILE_ASSOCIATO_ENTE = "CODICE_VERSAMENTO_NON_ELIMINABILE_ASS_ENTE";

        public static final String CODICE_VERSAMENTO_NON_ELIMINABILE_ASSOCIATO_UTENTE = "CODICE_VERSAMENTO_NON_ELIMINABILE_ASS_UTENTE";

        public static final String CODICE_VERSAMENTO_NON_ELIMINABILE_ASSOCIATO_RIFERIMENTI_CONTABILI = "CODICE_VERSAMENTO_NON_ELIMINABILE_ASS_RIF";

        public static final String RIFERIMENTO_CONTABILE_INVALID_DATE_SEQUENCE = "RIFERIMENTO_CONTABILE_INVALID_DATE_SEQUENCE";

        public static final String NESSUNA_VISIBILITA_GARANTITA_SU_TEMATICA = "NESSUNA_VISIBILITA_GARANTITA_SU_TEMATICA";

        public static final String VISIBILITA_TOTALE_NON_GARANTITA = "VISIBILITA_TOTALE_NON_GARANTITA";

        public static final String VISIBILITA_CODICE_VERSAMENTO_NON_GARANTITA = "VISIBILITA_CODICE_VERSAMENTO_NON_GARANTITA";

        public static final String MODIFICA_UTENTE_CORRENTE_NON_CONSENTITA = "MODIFICA_UTENTE_CORRENTE_NON_CONSENTITA";

        public final static String WSO2_BROADCASTING_FAILED = "WSO2_BROADCASTING_FAILED";

        public static final String RIFERIMENTO_CONTABILE_DATA_FINE_DATA_INIZIO = "RIFERIMENTO_CONTABILE_DF_DI";

        public static final String RIFERIMENTO_CONTABILE_DATA_FINE_FUTURA = "RIFERIMENTO_CONTABILE_DFF_FUTURA";

        public static final String USER_ADMIN_ENTE = "USER_ADMIN_ENTE";

        public static final String IBAN_CV_OR_ENTE_OBBLIGATORIO = "IBAN_CV_OR_ENTE_OBBLIGATORIO";

        public static final String IBAN_CV_EREDITATO_DA_ENTE = "IBAN_CV_EREDITATO_DA_ENTE";

        public static final String SUGGEST_RIFC_DDSR = "SUGGEST_RIFC_DDSR";

        public static final String UTENTE_ESISTENTE = "UTENTE_ESISTENTE";

        public static final String UTENTE_NOT_VALID = "UTENTE_NOT_VALID";
        
        public static final String RIFERIMENTO_ESISTENTE = "RIFERIMENTO_ESISTENTE";
        

        public static final String COD_TASSONOMICO_DIVERSO_PER_RIF_CONTABILI_IN_VITA = "COD_TASSONOMICO_DIVERSO_PER_RIF_CONTABILI_IN_VITA";

        public static final String RIFERIMENTO_DUPLICATO = "RIFERIMENTO_DUPLICATO";
        public static final String INCOERENZA_MULTIBENFICIARIO_COV_RIFERIMENTI = "INCOERENZA_MULTIBENFICIARIO_COV_RIFERIMENTI";
        
        public static final String INCOERENZA_MULTIBENFICIARIO_RIF_CONTABILE = "INCOERENZA_MULTIBENFICIARIO_RIF_CONTABILE";
        
        
        

        public static final String DT_INIZIO_FINE_VALIDITA_COD_TASSONOMICO_NON_CONGRUENTI = "DT_INIZIO_FINE_VALIDITA_TASSONOMIA_NON_CONGRUENTI";

        public static final String DATO_SPECIFICO_INCASSO_NUM_CARATTERI = "DATO_SPECIFICO_INCASSO_NUM_CARATTERI";

        public static final String ANNO_ACCERTAMENTO_OBBLIGATORIO_PS = "ANNO_ACCERTAMENTO_OBBLIGATORIO_PS";

        public static final String NUMERO_ACCERTAMENTO_OBBLIGATORIO_PS = "NUMERO_ACCERTAMENTO_OBBLIGATORIO_PS";

        public static final String ANNO_ACCERTAMENTO_ANNO_ESERCIZIO_UGUALI = "ANNO_ACCERTAMENTO_ANNO_ESERCIZIO_UGUALI";

        public static final String ANNO_ACCERTAMENTO_NUMERO_ACCERTAMENTO_VALORZZATI = "ANNO_ACCERTAMENTO_NUMERO_ACCERTAMENTO_VALORZZATI";

        public static final String NUMERO_ACCERTAMENTO_ANNO_ACCERTAMENTO_VALORZZATI = "NUMERO_ACCERTAMENTO_ANNO_ACCERTAMENTO_VALORZZATI";
        
        public static final String RIFERIMENTO_PRESENTE = "RIFERIMENTO_PRESENTE";
        
        public static final String GENERICO_E_NON_GENERICO_PRESENTI = "GENERICO_E_NON_GENERICO_PRESENTI";
        
        public static final String GENERICO_PRESENTE = "GENERICO_PRESENTE";
        
        public static final String IMPOSSIBILE_INSERIMENTO_GENERICO = "IMPOSSIBILE_INSERIMENTO_GENERICO";
        
        public static final String DOPPIO_RIFERIMENTO_SECONDARIO = "DOPPIO_RIFERIMENTO_SECONDARIO";


        
    }

    public static class OPERATIONS {

        public static final String AGGIORNA_ENTE = "AGENT";

    }

    public final static String DEFAULT_PROTOCOL = "application/json";
}
