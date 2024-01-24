/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.exception;

import it.csi.epay.epaypacatalogsrv.vo.Constants;


public class Wso2BroadcastingFailedException extends CodedException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Wso2BroadcastingFailedException () {
        super ( 500, Constants.RESULT_CODES.WSO2_BROADCASTING_FAILED, Constants.MESSAGES.WSO2_BROADCASTING_FAILED );
    }

    public Wso2BroadcastingFailedException ( String messageCode ) {
        super ( 500, Constants.RESULT_CODES.WSO2_BROADCASTING_FAILED, messageCode );
    }

    public Wso2BroadcastingFailedException ( String messageCode, Object... parameters ) {
        super ( 500, Constants.RESULT_CODES.WSO2_BROADCASTING_FAILED, messageCode, parameters );
    }

}
