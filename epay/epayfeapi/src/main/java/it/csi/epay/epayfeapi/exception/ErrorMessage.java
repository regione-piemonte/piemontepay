/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.exception;

import org.openapitools.model.Error;

import static it.csi.epay.epayfeapi.util.Constants.INTERNAL_ERROR_CODE;


public class ErrorMessage extends Error {

	public ErrorMessage ( String detail ) {
		super ();
		this.setStatus ( INTERNAL_ERROR_CODE );
		this.setCode ( CodeEnum.INTERNAL_ERROR );
		this.setDetail ( detail );
	}

}
