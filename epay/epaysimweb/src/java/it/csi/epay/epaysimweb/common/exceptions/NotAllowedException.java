/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.common.exceptions;

public class NotAllowedException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -6661605560692802706L;

    public NotAllowedException() {
        super();
    }

    public NotAllowedException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAllowedException(String message) {
        super(message);
    }

    public NotAllowedException(Throwable cause) {
        super(cause);
    }

}
