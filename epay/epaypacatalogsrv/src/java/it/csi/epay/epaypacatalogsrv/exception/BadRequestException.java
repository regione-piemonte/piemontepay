/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.exception;

import it.csi.epay.epaypacatalogsrv.vo.Constants;

public class BadRequestException extends CodedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadRequestException() {
		super(400, Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.BAD_REQUEST);
	}

	public BadRequestException(String messageCode) {
		super(400, Constants.RESULT_CODES.BAD_REQUEST, messageCode);
	}

	public BadRequestException(String messageCode, Object... parameters) {
		super(400, Constants.RESULT_CODES.BAD_REQUEST, messageCode, parameters);
	}

}
