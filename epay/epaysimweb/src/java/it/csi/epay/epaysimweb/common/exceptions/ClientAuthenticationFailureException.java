/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.common.exceptions;

public class ClientAuthenticationFailureException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -6661605560692802706L;

    public ClientAuthenticationFailureException() {
        super();
    }

    public ClientAuthenticationFailureException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ClientAuthenticationFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientAuthenticationFailureException(String message) {
        super(message);
    }

    public ClientAuthenticationFailureException(Throwable cause) {
        super(cause);
    }

}
