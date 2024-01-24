/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.exception;

/** eccezione persistence -> business */
public class CannotAcquireLockException extends SchedulerException {
	private static final long serialVersionUID = 1L;

	public CannotAcquireLockException() {
		super();
	}

}
