/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymdpservices.exception;

public class EpayMdpServicesException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EpayMdpServicesException() {
		super();
	}

	/**
	 * @param String
	 *            messaggio
	 * @param Throwable
	 *            causa
	 */
	public EpayMdpServicesException(String message, Throwable cause) {
		super(message, cause);
	}

	public EpayMdpServicesException(String message) {
		super(message);
	}

	public EpayMdpServicesException(Throwable cause) {
		super(cause);
	}
}
