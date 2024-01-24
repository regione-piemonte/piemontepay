/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpmultiiuvsrv.exception.mdpmultiiuv;

public class MdpMultiIuvSrvException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MdpMultiIuvSrvException() {
		super();
	}

	/**
	 * @param String
	 *            messaggio
	 * @param Throwable
	 *            causa
	 */
	public MdpMultiIuvSrvException(String message, Throwable cause) {
		super(message, cause);
	}

	public MdpMultiIuvSrvException(String message) {
		super(message);
	}

	public MdpMultiIuvSrvException(Throwable cause) {
		super(cause);
	}
}
