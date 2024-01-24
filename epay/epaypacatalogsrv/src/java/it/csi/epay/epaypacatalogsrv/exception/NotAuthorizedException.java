/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.exception;

import it.csi.epay.epaypacatalogsrv.vo.Constants;

public class NotAuthorizedException extends CodedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotAuthorizedException() {
		super(401, Constants.RESULT_CODES.NOT_ALLOWED, Constants.MESSAGES.NOT_ALLOWED);
	}

	public NotAuthorizedException(String messageCode) {
		super(401, Constants.RESULT_CODES.NOT_ALLOWED, messageCode);
	}

	public NotAuthorizedException(String messageCode, Object... parameters) {
		super(400, Constants.RESULT_CODES.NOT_ALLOWED, messageCode, parameters);
	}

}
