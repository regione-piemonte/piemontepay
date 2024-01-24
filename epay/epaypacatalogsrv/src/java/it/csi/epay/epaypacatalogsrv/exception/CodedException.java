/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.exception;

public class CodedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer statusCode;
	private String messageKey;
	private String message;
	private String resultCode;
	private Object[] parameters;

	public CodedException(Integer statusCode, String resultCode, String messageKey) {
		super();
		this.statusCode = statusCode;
		this.messageKey = messageKey;
		this.resultCode = resultCode;
	}

	public CodedException(Integer statusCode, String resultCode, String messageKey, Object... parameters) {
		super();
		this.statusCode = statusCode;
		this.messageKey = messageKey;
		this.resultCode = resultCode;
		this.parameters = parameters;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

}
