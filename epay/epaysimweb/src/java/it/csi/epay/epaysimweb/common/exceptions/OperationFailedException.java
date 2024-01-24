/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.common.exceptions;

public class OperationFailedException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public OperationFailedException() {
        super();
    }

    public OperationFailedException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public OperationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperationFailedException(String message) {
        super(message);
    }

    public OperationFailedException(Throwable cause) {
        super(cause);
    }

}
