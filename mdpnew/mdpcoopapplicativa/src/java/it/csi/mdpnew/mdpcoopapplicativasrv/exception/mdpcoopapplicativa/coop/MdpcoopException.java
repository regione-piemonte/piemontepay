/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.exception.mdpcoopapplicativa.coop;

public class MdpcoopException extends Exception {
	private static final long serialVersionUID = 1L;

	private String errorCod;
	private String errorDes;

	public MdpcoopException(String cod) {
		super(cod);
		this.setErrorCod(cod);
	}

	public MdpcoopException(String cod, String des) {
		super(cod + ": " + des);
		this.setErrorCod(cod);
		this.setErrorDes(des);
	}

	public MdpcoopException(String cod, String des, Throwable cause) {
		super(cod + ": " + des, cause);
		this.setErrorCod(cod);
		this.setErrorDes(des);
	}

	public String getErrorCod() {
		return errorCod;
	}

	public void setErrorCod(String errorCod) {
		this.errorCod = errorCod;
	}

	public String getErrorDes() {
		return errorDes;
	}

	public void setErrorDes(String errorDes) {
		this.errorDes = errorDes;
	}

}
