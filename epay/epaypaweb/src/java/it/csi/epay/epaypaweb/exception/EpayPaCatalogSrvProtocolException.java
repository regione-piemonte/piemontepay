/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.exception;

public class EpayPaCatalogSrvProtocolException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EpayPaCatalogSrvProtocolException(String message) {
		super(message);
	}

	public EpayPaCatalogSrvProtocolException(String message, Throwable cause) {
		super(message, cause);
	}

}
