/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.exception;

import javax.xml.ws.WebFault;

@WebFault
public class EpaypacatalogsrvException
		extends
			it.csi.epay.epaypacatalogsrv.exception.UserException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8751121532792901520L;

	/**
	 * @generated
	 */
	public EpaypacatalogsrvException() {
		super();
	}

	/**
	 * @param message
	 * @generated
	 */
	public EpaypacatalogsrvException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 * @generated
	 */
	public EpaypacatalogsrvException(String message, Throwable cause) {
		super(message, cause);
	}

}
