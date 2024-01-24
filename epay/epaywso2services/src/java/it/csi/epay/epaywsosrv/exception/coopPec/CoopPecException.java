/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.exception.coopPec;

import javax.xml.ws.WebFault;

@WebFault
public class CoopPecException extends UserException {

    public final int errorCode;

    public CoopPecException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    private static final long serialVersionUID = -8751121532792901520L;

    public CoopPecException() {
        super();
        this.errorCode = -1;
    }

    public CoopPecException(String message) {
        super(message);
        this.errorCode = -1;
    }

    public CoopPecException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = -1;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
