/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.mdp;

public class MdpException extends RuntimeException {

	private static final long serialVersionUID = 2277495205608317490L;

	public MdpException() {
		super();
	}

	public MdpException(String message, Throwable cause) {
		super(message, cause);
	}

	public MdpException(String message) {
		super(message);
	}

	public MdpException(Throwable cause) {
		super(cause);
	}	
}
