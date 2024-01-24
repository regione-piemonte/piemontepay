/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.exception;

import it.csi.epay.epaypacatalogsrv.vo.Constants;

public class ManagedException extends CodedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ManagedException() {
		super(500, Constants.RESULT_CODES.INTERNAL_ERROR, Constants.MESSAGES.INTERNAL_ERROR);
	}

	public ManagedException(String messageCode) {
		super(500, Constants.RESULT_CODES.INTERNAL_ERROR, messageCode);
	}

}
