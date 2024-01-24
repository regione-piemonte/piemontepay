/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.exception.epaysim;

/**
 * Classe base per tutte le eccezioni di tipo USER
 * @generated
 */
public class UserException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 3334869018095962262L;

    private String codiceErrore;

    /**
     * @generated
     */
    public UserException() {
        super();
    }

    /**
     * @generated
     */
    public UserException ( String codice, String message ) {
        super ( message );
        codiceErrore = codice;
    }

    /**
     * @generated
     */
    public UserException ( String codice, String message, Throwable cause ) {
        super(message, cause);
        codiceErrore = codice;
    }

    /**
     * ritorna il codice errore.
     * 
     * @return String codice errore
     */
    public String getCodiceErrore () {
        return codiceErrore;
    }

}
