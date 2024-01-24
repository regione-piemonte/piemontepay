/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.interfaces.rs.exception;

public class NotificationPriceServiceException extends Exception {
	private static final long serialVersionUID = 4700757887070254101L;
	
	String code;
	
	public NotificationPriceServiceException(final String code, final String message) {
		super(message);
		this.code = code;
	}
	
	public NotificationPriceServiceException(final String code, final String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

}
