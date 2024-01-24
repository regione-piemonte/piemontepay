/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.exception;

public class SchedulerException extends Exception {
	private static final long serialVersionUID = 1L;

	public SchedulerException() {
		super();
	}

	public SchedulerException(String message) {
		super(message);
	}

	public SchedulerException(Throwable cause) {
		super(cause);
	}

	public SchedulerException(String message, Throwable cause) {
		super(message, cause);
	}

}
