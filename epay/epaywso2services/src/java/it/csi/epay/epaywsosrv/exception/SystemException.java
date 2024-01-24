/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.exception;

import javax.ejb.EJBException;

public class SystemException extends EJBException {
	private static final long serialVersionUID = 1L;

	private String errorCod;
	private String errorDes;
	private Throwable cause;

	public SystemException(String cod) {
		super(cod);
		this.setErrorCod(cod);
	}

	public SystemException(String cod, String des) {
		super(cod + ": " + des);
		this.setErrorCod(cod);
		this.setErrorDes(des);
	}

	public SystemException(String cod, String des, Throwable cause) {
		super(cod + ": " + des);
		this.setErrorCod(cod);
		this.setErrorDes(des);
		this.setCause(cause);
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

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}

}
