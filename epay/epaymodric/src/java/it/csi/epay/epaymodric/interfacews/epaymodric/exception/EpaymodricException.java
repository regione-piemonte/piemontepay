/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.interfacews.epaymodric.exception;

import javax.xml.ws.WebFault;

@WebFault
public class EpaymodricException
extends
it.csi.epay.epaymodric.interfacews.epaymodric.exception.UserException {

    public static final int EMAIL_DEACTIVATED = 1;

    public final int errorCode;

    public EpaymodricException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     *
     */
    private static final long serialVersionUID = -8751121532792901520L;

    /**
     * @generated
     */
    public EpaymodricException() {
        super();
        this.errorCode = -1;
    }

    /**
     * @param message
     * @generated
     */
    public EpaymodricException(String message) {
        super(message);
        this.errorCode = -1;
    }

    /**
     * @param message
     * @param cause
     * @generated
     */
    public EpaymodricException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = -1;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
