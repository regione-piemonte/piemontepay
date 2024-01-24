/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.interfacews.epaymodric.exception;

import javax.xml.ws.WebFault;

@WebFault
public class InvalidParametersException
		extends
			it.csi.epay.epaymodric.interfacews.epaymodric.exception.UserException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2745037458107187477L;

	/**
	 * @generated
	 */
	public InvalidParametersException() {
		super();
	}

	/**
	 * @param message
	 * @generated
	 */
	public InvalidParametersException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 * @generated
	 */
	public InvalidParametersException(String message, Throwable cause) {
		super(message, cause);
	}

}
