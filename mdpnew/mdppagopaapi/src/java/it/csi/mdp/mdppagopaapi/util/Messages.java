/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.util;

/**
 *
 */

public enum Messages {
        // ERROR HANDLER
        HTTP_ERROR_CONSTRAINT_VIOLATION_EXCEPTION ( "I dati inviati non sono validi." ),
        HTTP_ERROR_CONSTRAINT_VIOLATION_EXCEPTION_ARGS ( "I dati inviati non sono validi: %s" ),
        HTTP_ERROR_BAD_REQUEST ( "La richiesta inoltrata non e' formalmente corretta." ),
        HTTP_ERROR_UNAUTHORIZED ( "Non sei autorizzato a compiere l'operazione richiesta." ),
        HTTP_ERROR_CONFLICT ( "Si e' verificato un conflitto sulla risorsa specificata." ),
        HTTP_ERROR_INTERNAL_SERVER_ERROR ( "Si e' verificato un errore imprevisto." ),
        HTTP_ERROR_METHOD_NOT_ALLOWED ( "L'operazione richiesta non e' disponibile per la risorsa desiderata." ),
        HTTP_ERROR_NOT_ACCEPTABLE ( "Il server non puo' produrre una risposta nel formato richiesto." ),
        HTTP_ERROR_NOT_FOUND ( "La risorsa richiesta non e' stata trovata." ),
        HTTP_ERROR_READER_ERROR ( "Il server non puo' interpretare la richiesta nel formato specificato." ),
        HTTP_ERROR_WRITING_ERROR ( "Il server non e' riuscito ad inoltrare la risposta nel formato specificato." ),
        HTTP_ERROR_UNEXPECTED ( "Si e' verificato un errore imprevisto." ),
        // SECURITY INTERCEPTOR
        SECURITY_ERROR_CHECKING ( "Errore in fase di controllo delle autorizzazioni." ),
        SECURITY_ERROR_UNSECURED_ENDPOINT ( "ENDPOINT PRIVO DI SPECIFICA DI SICUREZZA %s" ),
        // AUTHENTICATION
        AUTH_ERROR_TOO_MANY_HEADERS ( "Troppi headers %s" ),
        AUTH_FAILED_UNSUPPORTED_STRATEGY ( "Strategia di autenticazione non supportata" ),
        AUTH_FAILED_INVALID_CLIENT_CODE ( "Nessun fruitore valido per il codice specificato" ),
        AUTH_FAILED_EXPIRED_CLIENT ( "Fruitore non valido" ),
        AUTH_FAILED_INVALID_PASSWORD ( "Password non valida" ),
        // GENERIC VALIDATION
        FIELD_NOT_NULL ( "Il campo %s e' obbligatorio" ),
        FIELD_LENGTH ( "Il campo %s deve essere lungo tra %s e %s caratteri" ),
        // CLIENT CRUD
        CLIENT_CRUD_CODE_CONFLICT ( "Il codice specificato e' gia' associato ad un altro fruitore" ),
        CLIENT_CRUD_CLASS_NOT_FOUND ( "Il codice classe specificato non e' valido" ),
        ;

    private String defaultValue;

    public String getMessage () {
        return defaultValue;
    }

    private Messages ( String defaultValue ) {
        this.defaultValue = defaultValue;
    }
}
