/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpmultiiuvsrv.exception.mdpmultiiuv;

public class ErrorParameterException extends Exception {

	private static final long serialVersionUID = 1L;

	public ErrorParameterException(String message) {
		super(message);

	}

	public ErrorParameterException(String message, Throwable cause) {
		super(message, cause);
	}

}
