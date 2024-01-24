/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.exception;

import it.csi.epay.epaypacatalogsrv.vo.Constants;

public class NotFoundException extends CodedException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException() {
		super(404, Constants.RESULT_CODES.NOT_FOUND, Constants.MESSAGES.NOT_FOUND);
	}

	public NotFoundException(String messageCode) {
		super(404, Constants.RESULT_CODES.NOT_FOUND, messageCode);
	}

    public NotFoundException ( String messageCode, Object... parameters ) {
        super ( 404, Constants.RESULT_CODES.NOT_FOUND, messageCode, parameters );
    }

}
